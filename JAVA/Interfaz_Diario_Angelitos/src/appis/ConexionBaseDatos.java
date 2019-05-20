package appis;

import algoritmosApoyo.TrieAutocompletar;
import java.awt.Frame;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import models.Infant;
import models.Tutor;
import models.TutorsInfant;
//import librerias.oracle.jdbc.driver.OracleDriver;

public class ConexionBaseDatos {

    Frame frame;

    public Infant[] registroActual;
    int registrosMax = 500;
    public int cantRegistros;
    public int sig_id_inf;
    //Este Objeto me ayuda a saber el numero indice de cada nombre en la tabla
    //que coincide con lo que esta escrito en la barra de buscar
    public TrieAutocompletar dicionarioNombresNiños;

    Connection conn;
    Statement stmnt;
    public boolean exitoConsulta;

    public ConexionBaseDatos(Frame frame) {
        this.frame = frame;
        try {
            //Se importa la Clase que contiene el Driver para conectar con Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "guarderia_admin", "admin");

            this.stmnt = conn.createStatement();
            //Esta Funcion me actualiza el registro de los infantes y ademas el diccionario
            //de nombres donde consulto coincidencias y guardo sus indices
            this.obtenerRegistroInfantes();
            
            //this.dicionarioNombresNiños.printDic( this.dicionarioNombresNiños.diccionario , "");

            this.dicionarioNombresNiños.buscarCoincidencias( "" );
            /*for(int x=0; x< this.dicionarioNombresNiños.cont; x++){
                System.out.println( this.dicionarioNombresNiños.coincidencias[x].nombre );
            }*/
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }

    public void obtenerRegistroInfantes() {
        try {
            //Cada que se rrellena se debe de vaciar de nuevo
            this.registroActual = new Infant[this.registrosMax];
            this.dicionarioNombresNiños = new TrieAutocompletar();
            this.dicionarioNombresNiños.diccionario.marcarUsado(123456789);
            ResultSet rs = this.stmnt.executeQuery("select * from INFANT ORDER BY id_inf  ASC ");
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            //Con esto controlo que lugar voy del arreglo de infantes
            this.cantRegistros = 0;
            //Variable para hacer el parseo de objeto a String en una linea
            String[] resultados = new String[cols];
            Object[] obj = new Object[cols];
            this.sig_id_inf = 0;
            while (rs.next()) {
                for (int i = 0; i < cols; i++) {
                    obj[i] = rs.getObject(i + 1);
                    resultados[i] = new String(obj[i].toString());
                }
                //Se crea un nuevo infante y se le pasa a su respectiva posicion
                this.registroActual[cantRegistros] = new Infant(
                        Integer.parseInt(resultados[0]), Integer.parseInt(resultados[3]),
                         resultados[1], resultados[2], resultados[4], resultados[5], resultados[6],
                        resultados[7], resultados[8], resultados[9], resultados[10], resultados[11]
                );
                //Se guarda el id mas alto para calcular el siguiente
                if (this.sig_id_inf < this.registroActual[cantRegistros].id_inf) {
                    this.sig_id_inf = this.registroActual[cantRegistros].id_inf;
                }
                //Se añade el nombre del niño al diccionario
                this.dicionarioNombresNiños.insertWord(
                        this.registroActual[cantRegistros].name_inf.toLowerCase(),
                        this.registroActual[cantRegistros].id_inf);
        
                //Incrementamos el contador para guardarlo en una nueva casilla
                //Incrementamos el contador de niños que existen
                this.cantRegistros++;
            }
            this.exitoConsulta = true;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }

    //Esta funcion inserta un niño en la bdd
    public void añadirNiño(String id_inf, String age, String name_inf, String surnames,
            String birth_day, String dir, String tel, String reg_date,
            String image_path, String allergies, String medicalService,
            String numService
    ) {
        try {//'"+  +"'
            this.stmnt.executeQuery(
                    "INSERT INTO INFANT VALUES( '" + id_inf + "','" + name_inf + "','" + surnames + "','"
                    + age + "',TO_DATE('" + birth_day + "', 'YYYY-MM-DD') ,'" + dir + "','"
                    + tel + "',TO_DATE('" + reg_date + "', 'YYYY-MM-DD') ,'" + image_path + "','"
                    + allergies + "','" + medicalService + "','" + numService + "')"
            );
            //si se logra insertar un niño se actualiza el registro de niños actual 
            this.obtenerRegistroInfantes();
            this.exitoConsulta = true;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }

    //Esta funcion modifica un niño en la bdd segun su id_inf
    public void modificarNiño(String id_inf, String age, String name_inf, String surnames,
            String birth_day, String dir, String tel, String reg_date,
            String image_path, String allergies, String medicalService,
            String numService
    ) {
        try {
            String comando = "UPDATE INFANT "
                    + "SET name= '" + name_inf + "' ,surnames= '" + surnames + "' ,"
                    + "age=" + age + ",birth_day=TO_DATE('" + birth_day + "', 'yyyy-mm-dd'),"
                    + "dir='" + dir + "',tel='" + tel + "',reg_date=TO_DATE('" + reg_date + "', 'yyyy-mm-dd'),"
                    + "image_path='" + image_path + "',allergies='" + allergies + "',"
                    + "medical_service='" + medicalService + "',num_service='" + numService + "'"
                    + "WHERE id_inf = " + id_inf + " ";
            this.stmnt.executeQuery(comando);
            //si se logra insertar un niño se actualiza el registro de niños actual 
            this.obtenerRegistroInfantes();
            this.exitoConsulta = true;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }
    //Esta funcion elimina un niño en la bdd segun su id_inf
    public void eliminarNiño(String id_inf) {
        try {
            String comando = "DELETE FROM INFANT WHERE id_inf = " + id_inf + " ";
            this.stmnt.executeQuery(comando);
            //si se logra eliminar un niño se actualiza el registro de niños actual 
            this.obtenerRegistroInfantes();
            this.exitoConsulta = true;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }
    
    public TutorsInfant listaTutoresInfante(String id_inf){
        TutorsInfant tutoresInfante = new TutorsInfant();
        try{
            ResultSet rs = this.stmnt.executeQuery(
                    "SELECT "
                            + "t.id_tut , t.name_tut , t.surnames ,t.age , t.tel , t.dir , t.email , t.work_place "+
                    "from INF_TUT i "+
                    "join TUTORS t "+
                    "on(i.id_tut = t.id_tut) "+
                    "where i.id_inf = "+id_inf+" "+
                    "ORDER BY t.id_tut  ASC "
            );
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            //Con esto controlo que lugar voy del arreglo de infantes
            tutoresInfante.numTutores = 0;
            //Variable para hacer el parseo de objeto a String en una linea
            String[] resultados = new String[cols];
            Object[] obj = new Object[cols];
            while( rs.next() && tutoresInfante.numTutores < 4) {
                for (int i = 0; i < cols; i++) {
                    obj[i] = rs.getObject(i + 1);
                    resultados[i] = new String(obj[i].toString());
                }
                //Se crea un nuevo infante y se le pasa a su respectiva posicion
                tutoresInfante.tutoresNiño[tutoresInfante.numTutores] = new Tutor(
                        Integer.parseInt(resultados[0]), resultados[1] ,
                        resultados[2], Integer.parseInt( resultados[3]) , resultados[4], 
                        resultados[5], resultados[6],resultados[7]
                );
                //Incrementamos el contador para guardarlo en una nueva casilla
                //Incrementamos el contador de tutores que existen
                tutoresInfante.numTutores++;
            }
            this.exitoConsulta = true;
            tutoresInfante.id_inf=id_inf;
            return tutoresInfante;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
            return tutoresInfante;
        }
    }
    
    public void insertarRelacionTutInf(String id_inf ){
        try {
            String comando = 
                "INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )"+
                "VALUES( nin_tut_id_seq.nextval , "+id_inf+" , tut_id_seq.currval )";
            ;
            this.stmnt.executeQuery(comando);
            this.exitoConsulta = true;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }
    
    public void insertarTutor(String id_inf, String name_tut,String surnames,String age,
            String tel,String dir,String email,String work_place){
        try {
            String comando = 
                "INSERT INTO TUTORS ( id_tut , name_tut , surnames ,age , tel , dir , email , work_place )"+
                "VALUES( tut_id_seq.nextval , '"+name_tut+"','"+surnames+"',"+age+",'"+tel+"','"+dir+"','"+email+"','"+work_place+"')";
            this.stmnt.executeQuery(comando);
            this.insertarRelacionTutInf(id_inf);
            this.exitoConsulta = true;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }
    
    public void modificarTutor( String id_tut , String name_tut,String surnames,String age,
            String tel,String dir,String email,String work_place){
        try {
            String comando = 
                    "UPDATE TUTORS SET name_tut='"+name_tut+"' ,"+
                    " surnames='"+surnames+"' , age="+age+" ,"+
                    " tel='"+tel+"' , dir='"+dir+"' ,"+
                    " email='"+email+"' , work_place='"+work_place+
                    "'  WHERE id_tut = "+id_tut;
            this.stmnt.executeQuery(comando);
            this.exitoConsulta = true;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }
}
