package appis;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import models.Infant;
//import librerias.oracle.jdbc.driver.OracleDriver;

public class ConexionBaseDatos {

    public Infant[] registroActual;
    int registrosMax = 500;
    public int cont;
    
    
    Connection conn;
    Statement stmnt;
    public ConexionBaseDatos() {
        try{
            //Se importa la Clase que contiene el Driver para conectar con Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","guarderia_admin","admin");
           
            this.stmnt = conn.createStatement();
            this.obtenerRegistroInfantes();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public void obtenerRegistroInfantes(){
        try{
            //Cada que se rrellena se debe de vaciar de nuevo
            this.registroActual = new Infant[this.registrosMax];
            ResultSet rs = this.stmnt.executeQuery("select * from INFANT");
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            //Con esto controlo que lugar voy del arreglo de infantes
            this.cont = 0;
            //Variable para hacer el parseo de objeto a String en una linea
            String[] resultados = new String[cols];
            Object[] obj = new Object[cols];
            while(rs.next()){
                for(int i=0;i<cols;i++){
                    obj[i]=rs.getObject(i+1);
                    resultados[i] =new String(obj[i].toString());
                }
                //Se crea un nuevo infante y se le pasa a su respectiva posicion
                this.registroActual[cont]= new Infant(
                        Integer.parseInt( resultados[0] ) ,Integer.parseInt( resultados[3] )
                        ,resultados[1],resultados[2],resultados[4],resultados[5],resultados[6],
                        resultados[7],resultados[8],resultados[9],resultados[10],resultados[11]
                );
                //Incrementamos el contador para guardarlo en una nueva casilla
                this.cont++;
            }
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
