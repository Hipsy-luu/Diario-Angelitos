package appis;

import algoritmosApoyo.TrieAutocompletar;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import models.Detalles;
import models.Infant;
import models.ListaAsistencia;
import models.ListaHermanosInfante;
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
                        this.registroActual[cantRegistros].name_inf.toLowerCase()+
                                " "+this.registroActual[cantRegistros].surnames.toLowerCase(),
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
            //Se eliminan las relaciones de los hermanos primero
            String comando = "DELETE FROM INF_INF WHERE id_inf_a = " + id_inf +
                    " or id_inf_b = "+id_inf;
            this.stmnt.executeQuery(comando);
            //Se eliminan las relaciones con los tutores
            comando = "DELETE FROM INF_TUT WHERE id_inf = " + id_inf + " ";
            this.stmnt.executeQuery(comando);
            //Se elimina el infante despues de eliminar totas sus relaciones
            comando = "DELETE FROM INFANT WHERE id_inf = " + id_inf + " ";
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
    
    public void insertarRelacionTutInf(String id_inf ,String id_tut){
        try {
            String comando;
            int checadorRelacion = 0;
            ListaHermanosInfante hermanosInfante =  listaHermanosInfante(id_inf);
            for(int x = 0;x < hermanosInfante.numHermanos; x++){
                comando = 
                    "SELECT COUNT(*) FROM INF_TUT WHERE "+ 
                    " id_inf = "+String.valueOf( 
                            hermanosInfante.hermanosNiño[x].id_inf )+
                    " and id_tut = "+ id_tut +" ";
                ResultSet rs = this.stmnt.executeQuery(comando);
                while( rs.next() ) {
                    checadorRelacion =new Integer( rs.getObject(1).toString() );
                }
                if(checadorRelacion==0){
                    comando = 
                    "INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )"+
                    "VALUES( nin_tut_id_seq.nextval , '"+
                            hermanosInfante.hermanosNiño[x].id_inf+"' , '"+id_tut+"' )";
                    this.stmnt.executeQuery(comando);
                }
            }
            comando = 
                "INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )"+
                "VALUES( nin_tut_id_seq.nextval , '"+id_inf+"' , '"+id_tut+"' )";
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
            //Se crea el tutor
            String comando = 
                "INSERT INTO TUTORS ( id_tut , name_tut , surnames ,age , tel , dir , email , work_place )"+
                "VALUES( tut_id_seq.nextval , '"+name_tut+"','"+surnames+"',"+age+",'"+tel+"','"+dir+"','"+email+"','"+work_place+"')";
            this.stmnt.executeQuery(comando);
            //Se pide el id del ultimo elemto creado de los tutores
            comando = "select tut_id_seq.currval from DUAL";
            ResultSet rs = this.stmnt.executeQuery(comando);
            String id_tutor = "";
            while( rs.next() ) {
                id_tutor =new String( rs.getObject(1).toString() );
            };
            this.insertarRelacionTutInf(id_inf,id_tutor);
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
    
    //Esta funcion elimina un tutor en la bdd segun su id_inf
    public void eliminarTutor(String id_tut) {
        try {
            String comando = "DELETE FROM INF_TUT WHERE id_tut = " + id_tut + " ";
            this.stmnt.executeQuery(comando);
            comando = "DELETE FROM TUTORS WHERE id_tut = " + id_tut + " ";
            this.stmnt.executeQuery(comando);
            this.exitoConsulta = true;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }
    
    public void añadirHermano(String id_inf_a ,String id_inf_b){
        try {
            
            //Se añade el hermano si la relacion hermano no existia
            String comando = 
                    "SELECT COUNT(*) FROM INF_INF WHERE "+ 
                    " id_inf_a = " +id_inf_a + " and id_inf_b = "+id_inf_b+
                    " or id_inf_a = " +id_inf_b + " and id_inf_b = "+id_inf_a;
            ResultSet rs = this.stmnt.executeQuery(comando);
            
            int checador = 0;
            while( rs.next() ) {
                checador =new Integer( rs.getObject(1).toString() );
            }
            if(checador==0){
                comando = "INSERT INTO INF_INF ( id_rela_bro , id_inf_a , id_inf_b )"+
                "VALUES( nin_nin_id_seq.nextval , "+id_inf_a+" , "+id_inf_b+" )";
                this.stmnt.executeQuery(comando);
                //Se copian los tutores del hermano que se acaba de añadir
                TutorsInfant tutoresHermano = new TutorsInfant();
                tutoresHermano = listaTutoresInfante(id_inf_b);
            
                for(int x = 0; x < tutoresHermano.numTutores; x++){
                    int checadorTutor = 0;

                    String comandoChecador = 
                        "SELECT COUNT(*) FROM INF_TUT WHERE "+ 
                        " id_inf = "+id_inf_a+" and id_tut = "+tutoresHermano.tutoresNiño[x].id_tut;
                    rs = this.stmnt.executeQuery(comandoChecador);
                    while( rs.next() ) {
                        checadorTutor =new Integer( rs.getObject(1).toString() );
                    }
                    //Si no se habia añadido la relacion se añade
                    if(checadorTutor==0){
                        this.insertarRelacionTutInf(id_inf_a, String.valueOf( tutoresHermano.tutoresNiño[x].id_tut ) );
                    }
                }
                
                this.exitoConsulta = true;
            }else{
                JOptionPane.showMessageDialog(this.frame, "El hermano ya se añadio");
                this.exitoConsulta = false;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }
    
    public void eliminarHermano(String id_inf_a ,String id_inf_b){
        try {
            String comando = 
                    "DELETE FROM INF_INF WHERE "+ 
                    " id_inf_a = " +id_inf_a + " and id_inf_b = "+id_inf_b+
                    " or id_inf_a = " +id_inf_b + " and id_inf_b = "+id_inf_a;
            this.stmnt.executeQuery(comando);
            this.exitoConsulta = true;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }
    
    public ListaHermanosInfante listaHermanosInfante(String id_inf){
        ListaHermanosInfante hermanosInfante = new ListaHermanosInfante();
        try{
            ResultSet rs = this.stmnt.executeQuery(
                    "SELECT p.id_inf , p.name , p.surnames , p.age ,	p.birth_day  , p.dir  ,	p.tel , p.reg_date , p.image_path,	p.allergies, p.medical_service, p.num_service" +
                    " from INFANT p" +
                    " WHERE p.id_inf IN (" +
                    "   SELECT a.id_inf" +
                    "   from INFANT a" +
                    "   join INF_INF b" +
                    "   on(b.id_inf_a = a.id_inf )" +
                    "   where b.id_inf_b = "+id_inf +
                    " ) or p.id_inf IN (" +
                    "   SELECT c.id_inf" +
                    "   from INFANT c" +
                    "   join INF_INF d" +
                    "   on(d.id_inf_b = c.id_inf )" +
                    "   where d.id_inf_a = "+id_inf +
                    " ) ORDER BY p.id_inf  ASC "
            );
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            //Con esto controlo que lugar voy del arreglo de infantes
            hermanosInfante.numHermanos = 0;
            //Variable para hacer el parseo de objeto a String en una linea
            String[] resultados = new String[cols];
            Object[] obj = new Object[cols];
            while( rs.next() && hermanosInfante.numHermanos < 30) {
                for (int i = 0; i < cols; i++) {
                    obj[i] = rs.getObject(i + 1);
                    resultados[i] = new String(obj[i].toString());
                }
                //Se crea un nuevo infante y se le pasa a su respectiva posicion
                hermanosInfante.hermanosNiño[hermanosInfante.numHermanos] = new Infant(
                        Integer.valueOf(resultados[0]) , Integer.valueOf(resultados[3]) , resultados[1] , resultados[2] ,
                        resultados[4] , resultados[5] ,	resultados[6] , resultados[7] ,
                        resultados[8] ,	resultados[9] , resultados[10] , resultados[11]
                );
                //Incrementamos el contador para guardarlo en una nueva casilla
                //Incrementamos el contador de hermanos que existen
                hermanosInfante.numHermanos++;
            }
            this.exitoConsulta = true;
            hermanosInfante.id_inf=id_inf;
            return hermanosInfante;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
            return hermanosInfante;
        }
    }
    
    public void ingresoNuevoHistorial(String id_inf,String id_tut){
        try {
            //Comprobamos que no se añada
            if(this.comprobacionEntrada(id_inf)){
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                java.util.Date date = new java.util.Date();
                String comando= "INSERT INTO DAILY_ENTRIES ( "
                + "id_ent , id_rela_tut_inf , date_ent , obs ) "+
                "VALUES( daly_ent_id_seq.nextval , "+idRelacionTutorInfante(
                    id_inf,id_tut)+" , "+
                " TO_DATE('"+dateFormat.format(date)+"', 'DD/MM/YYYY HH24:MI:SS'), ' ' ) "+
                "  ";
                this.stmnt.executeQuery(comando);
                this.exitoConsulta = true;
            }else{
                JOptionPane.showMessageDialog(this.frame, "Ya se añadio a la lista");
                this.exitoConsulta = false;
            }
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
        }
    }
    //Esta funcion regresa un verdadero si el infante no se a añadido al dia de hoy
    //a la lista de entradas
    public boolean comprobacionEntrada(String id_inf) throws SQLException{
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        
        String comando = 
                "SELECT COUNT(*) FROM DAILY_ENTRIES WHERE "+ 
                " id_rela_tut_inf IN ("+ 
                "SELECT id_rela_tut_inf FROM INF_TUT "+ 
                "WHERE id_inf = "+id_inf+") and to_char(date_ent,'dd/MM/yyyy') = '"+
                dateFormat.format(date)+"'"
                ;
        ResultSet rs = this.stmnt.executeQuery(comando);

        int comprovacion = 0;
        while( rs.next() ) {
            comprovacion =new Integer( rs.getObject(1).toString() );
        }
        if(comprovacion==0){
            return true;
        }else{
            return false;
        }
    }
     
    public String idRelacionTutorInfante(String id_inf,String id_tut) throws SQLException{
        String comando = 
                "SELECT id_rela_tut_inf FROM INF_TUT WHERE "+ 
                " id_inf = " +id_inf + " and id_tut = "+id_tut;
        ResultSet rs = this.stmnt.executeQuery(comando);

        String id_rela_tut_inf = "";
        while( rs.next() ) {
            id_rela_tut_inf =new String( rs.getObject(1).toString() );
        }
        return id_rela_tut_inf;
    }
    
    public ListaAsistencia obtenerListaAsistenciaActual(){
        ListaAsistencia listaNueva = new ListaAsistencia(4000);
        try{
            ResultSet rs = this.stmnt.executeQuery(
                    "SELECT i.id_inf , i.name , i.surnames , i.age , i.tel , i.allergies , i.medical_service\n" +
                "    , i.num_service , i.reg_date , i.image_path , de.obs , t.id_tut , t.name_tut , t.surnames , t.tel\n" +
                "FROM DAILY_ENTRIES de " +
                "JOIN INF_TUT it " +
                "ON(de.id_rela_tut_inf=it.id_rela_tut_inf) " +
                "JOIN INFANT i " +
                "ON(it.id_inf=i.id_inf) " +
                "JOIN TUTORS t " +
                "ON(it.id_tut=t.id_tut) " +
                "WHERE to_char(DE.date_ent,'dd/MM/yyyy') = ( " +
                "    SELECT TO_CHAR(CURRENT_DATE, 'dd/MM/yyyy') " +
                "    FROM dual " +
                ") " +
                "and 0 = ( " +
                "    SELECT COUNT(*) " +
                "    FROM DAILY_DEPARTURES " +
                "    WHERE id_rela_tut_inf IN ( " +
                "        SELECT id_rela_tut_inf " +
                "        FROM INF_TUT " +
                "        WHERE id_inf = i.id_inf " +
                "        and to_char(date_dep,'dd/MM/yyyy') = ( " +
                "            SELECT TO_CHAR(CURRENT_DATE, 'dd/MM/yyyy') " +
                "            FROM dual " +
                "        ) " +
                "    ) " +
                ") "
            );
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            //Con esto controlo que lugar voy del arreglo de infantes
            listaNueva.numInf = 0;
            //Variable para hacer el parseo de objeto a String en una linea
            String[] resultados = new String[cols];
            Object[] obj = new Object[cols];
            while( rs.next() && listaNueva.numInf < listaNueva.maxInf ) {
                for (int i = 0; i < cols; i++) {
                    obj[i] = rs.getObject(i + 1);
                    String data=new String(obj[i].toString());
                    resultados[i] = data;
                }
                //Se crea un nuevo infante y se le pasa a su respectiva posicion
                Detalles nuevoDetalle = new Detalles(
                        Integer.parseInt(resultados[0]), resultados[1] ,
                        resultados[2], Integer.parseInt(resultados[3]) , 
                        resultados[4], resultados[5], resultados[6],resultados[7],
                        resultados[8], resultados[9], resultados[10],resultados[11],
                        resultados[12], resultados[13], resultados[14]
                );
                listaNueva.detalles[listaNueva.numInf] = nuevoDetalle;
                //Incrementamos el contador para guardarlo en una nueva casilla
                //Incrementamos el contador de tutores que existen
                listaNueva.numInf++;
            }
            this.exitoConsulta = true;
            return listaNueva;
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(this.frame, e);
            this.exitoConsulta = false;
            return listaNueva;
        }
    }
}
