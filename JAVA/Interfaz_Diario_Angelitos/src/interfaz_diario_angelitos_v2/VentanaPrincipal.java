
package interfaz_diario_angelitos_v2;

///////////////////////////////////////////

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import java.awt.Point;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


///////////////////////////////////////////
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Detalles;
import models.Infant;
import models.ListaAsistencia;
import models.TutorsInfant;

public class VentanaPrincipal extends javax.swing.JFrame {

    VentanaRegistro ventRegistro;
    //El modelo es la conexion entre la tabla de la interfaz y la 
    DefaultTableModel modeloTablaAsistencias;
    Object [] filasAsistencias;
    
    DefaultTableModel modeloTablaAñadir;
    Object [] filasTablaAñadir;
    
    DefaultTableModel modeloTablaHistorial;
    Object [] filasTablaHistorial;
    //Variables para llenar las tablas y datos
    Infant niñoActual;
    TutorsInfant tutoresNiño;
    ListaAsistencia listaAsistencia;
    ListaAsistencia listaAsistenciaHistorial;
    //Variables para auxiliar el control de la pestaña de añadir niño
    boolean niñoSeleccionado = false;
    boolean tutorSeleccionado = false;
    //Variable de control de la lista actual de asistencia que ayuda a saber
    //cuando se estan mostrando las entradas o las salidas
    boolean mostrandoEntradas = true;
    //cuando se estan mostrando las entradas o las salidas
    boolean mostrandoEntradasHistorial = true;
    //Variables para el calendario
    JFXPanel calendarioPanel;
    
    private DatePicker inPicker;
    private DatePicker fmPicker;
    private DatePicker toPicker;
    private static final String pattern = "dd-MMM-yy";
    enum DateParameterType { FROM_DATE, TO_DATE };
    
    public VentanaPrincipal() {
        initComponents();
        this.ventRegistro = new VentanaRegistro( getFrame() );
        setLocationRelativeTo(null);
        //Se inicializa el calendario
        
        //Se actualiza la fecha del sistema 
        this.date1.setText(fechaActual());
        
        this.tutoresNiño = new TutorsInfant();
        this.modeloTablaAñadir = (DefaultTableModel) jTablaRegistro.getModel();
        this.jTablaRegistro.setModel(modeloTablaAñadir);  
        this.filasTablaAñadir=new Object[5];
        
        //Conectamos la tabla de la interfaz con la tabla que tenemos 
        //en el modelo
        this.modeloTablaAsistencias = (DefaultTableModel) listaAsistenciaTable.getModel();
        this.listaAsistenciaTable.setModel(modeloTablaAsistencias);
        this.filasAsistencias=new Object[5];
        
        this.modeloTablaHistorial = 
                (DefaultTableModel) listaAsistenciaHistorialTable.getModel();
        this.listaAsistenciaHistorialTable.setModel(modeloTablaHistorial);
        this.filasTablaHistorial=new Object[5];
        
        this.actualizarTablaListaAsistencia(0);
        this.actualizarTablaConNiños();
        this.limpiarDatosNiño();
        /*add.setToolTipText("Entro ()niño");
        search.setToolTipText("Buscar");
        record.setToolTipText("Registro");*/
    }
    //Nos sirve para llamarla cada que querramos obtene la fecha actual del SO
    public String fechaActual(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
        return dateFormat.format(date);
    }
    
    public void actualizarTablaConNiños(){
        
        //Borramos el primer campo hasta que este vacia la tabla
        while(0<this.modeloTablaAñadir.getRowCount()){
            this.modeloTablaAñadir.removeRow(0);
        }
        for(int x=0;x<this.ventRegistro.coneccionBdd.cantRegistros;x++){
            filasTablaAñadir[0]=this.ventRegistro.coneccionBdd.registroActual[x].id_inf;
            filasTablaAñadir[1]=this.ventRegistro.coneccionBdd.registroActual[x].name_inf + " " +  this.ventRegistro.coneccionBdd.registroActual[x].surnames;
            filasTablaAñadir[2]=this.ventRegistro.coneccionBdd.registroActual[x].age;
            filasTablaAñadir[3]=this.ventRegistro.coneccionBdd.registroActual[x].tel;
            filasTablaAñadir[4]=this.ventRegistro.coneccionBdd.registroActual[x].reg_date;
            this.modeloTablaAñadir.addRow(filasTablaAñadir); 
        }
        
        addNiño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-de-acuerdo-45.png")));
        this.addLbl.setText("Seleccione un niño");
        this.instrucLbl.setText("Seleccione un niño de la lista para continuar:");
        this.addNiño.setEnabled(false);
        this.niñoSeleccionado = false; 
        this.tutorSeleccionado = false;
        //Se habilita la barra de busqueda y el boton de lista completa
        this.rBuscar.setEnabled(true);
        this.btnRegistroCompleto.setEnabled(true);
    }
    
    public void actualizarTablaListaAsistencia(int opc){
        //Variable para actualizar y llenar los datos de asistencia actuales
        if(opc==0){
            this.listaAsistencia = ventRegistro.coneccionBdd.obtenerListaAsistenciaActual(0);
            this.mostrarListaLbl.setText("salidas");
            this.mostrandoListaTipoLbl.setText("Mostrando las Entradas del dia de hoy:");
            this.mostrandoEntradas=true;
        }else if(opc==1){
            this.listaAsistencia = ventRegistro.coneccionBdd.obtenerListaAsistenciaActual(1);
            this.mostrarListaLbl.setText("entradas");
            this.mostrandoListaTipoLbl.setText("Mostrando las Salidas del dia de hoy:");
            this.mostrandoEntradas=false;
        }
        this.seleccionandoTutorSalida = false;
        //this.coneccionBdd.obtenerRegistroInfantes();
        //Borramos el primer campo hasta que este vacia la tabla
        while(0<this.modeloTablaAsistencias.getRowCount()){
            this.modeloTablaAsistencias.removeRow(0);
        }
        for(int x=0;x<this.listaAsistencia.numInf;x++){
            filasAsistencias[0]=this.listaAsistencia.detalles[x].i_name+" "+
                    this.listaAsistencia.detalles[x].i_surnames;
            filasAsistencias[1]=this.listaAsistencia.detalles[x].de_obs;
            filasAsistencias[2]=this.listaAsistencia.detalles[x].t_name_tut+" "+
                    this.listaAsistencia.detalles[x].t_surnames;
            filasAsistencias[3]=this.listaAsistencia.detalles[x].t_tel;
            this.modeloTablaAsistencias.addRow(filasAsistencias); 
        }
        this.obsGuardarBtn.setEnabled(false);
        this.darSalidaBtn.setEnabled(false);
        this.observacion.setEnabled(false);
        this.observacion.setText("");
        this.limpiarDatosNiño();
    }
    
    public void actualizarTablaListaAsistenciaHistorial(String fecha,int opc){
        //Variable para actualizar y llenar los datos de asistencia actuales
        if(opc==0){
            this.listaAsistenciaHistorial = ventRegistro.coneccionBdd.
                    obtenerListaAsistenciaHistorial(fecha,0);
            this.mostrarListaHistorialLbl.setText("salidas");
            this.fechaSelec.setText("Mostrando las Entradas del dia: "+fecha);
            this.mostrandoEntradasHistorial=true;
        }else if(opc==1){
            this.listaAsistenciaHistorial = ventRegistro.coneccionBdd.
                    obtenerListaAsistenciaHistorial(fecha,1);
            this.mostrarListaHistorialLbl.setText("entradas");
            this.fechaSelec.setText("Mostrando las Salidas del dia: "+fecha);
            this.mostrandoEntradasHistorial=false;
        }
        //this.seleccionandoTutorSalida = false;
        //this.coneccionBdd.obtenerRegistroInfantes();
        //Borramos el primer campo hasta que este vacia la tabla
        while(0<this.modeloTablaHistorial.getRowCount()){
            this.modeloTablaHistorial.removeRow(0);
        }
        for(int x=0;x<this.listaAsistenciaHistorial.numInf;x++){
            filasTablaHistorial[0]=this.listaAsistenciaHistorial.detalles[x].i_name+" "+
                    this.listaAsistenciaHistorial.detalles[x].i_surnames;
            filasTablaHistorial[1]=this.listaAsistenciaHistorial.detalles[x].de_obs;
            filasTablaHistorial[2]=this.listaAsistenciaHistorial.detalles[x].t_name_tut+" "+
                    this.listaAsistenciaHistorial.detalles[x].t_surnames;
            filasTablaHistorial[3]=this.listaAsistenciaHistorial.detalles[x].t_tel;
            this.modeloTablaHistorial.addRow(filasTablaHistorial); 
        }
        //this.obsGuardarBtn.setEnabled(false);
        //this.darSalidaBtn.setEnabled(false);
        //this.observacion.setEnabled(false);
        //this.observacion.setText("");
        //this.limpiarDatosNiño();
    }
    
    public void borrarVistaAsistenciaHistorial(){
        this.mostrarListaHistorialLbl.setText("entradas");
        this.fechaSelec.setText("Mostrando las Entradas del dia: ");
        this.mostrandoEntradasHistorial=false;
        //Borramos el primer campo hasta que este vacia la tabla
        while(0<this.modeloTablaHistorial.getRowCount()){
            this.modeloTablaHistorial.removeRow(0);
        }
    }
    
    public void actualizarTablaConTutores(){
        this.tutoresNiño = new TutorsInfant();
        this.tutoresNiño = this.ventRegistro.coneccionBdd.listaTutoresInfante(String.valueOf(
                this.niñoActual.id_inf)
        );
        
        //Borramos el primer campo hasta que este vacia la tabla
        while(0<this.modeloTablaAñadir.getRowCount()){
            this.modeloTablaAñadir.removeRow(0);
        }
        for(int x=0;x<this.tutoresNiño.numTutores;x++){
            filasTablaAñadir[0]=this.tutoresNiño.tutoresNiño[x].id_tut;
            filasTablaAñadir[1]=this.tutoresNiño.tutoresNiño[x].name_tut +
                    " " + this.tutoresNiño.tutoresNiño[x].surnames;
            filasTablaAñadir[2]=this.tutoresNiño.tutoresNiño[x].age;
            filasTablaAñadir[3]=this.tutoresNiño.tutoresNiño[x].tel;
            filasTablaAñadir[4]="";
            this.modeloTablaAñadir.addRow(filasTablaAñadir); 
        }
        this.instrucLbl.setText("Seleccione el tutor que lo trajo:");
        addNiño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-de-acuerdo-45.png")));
        this.addLbl.setText("Añadir niño");
        this.addNiño.setEnabled(false);
        this.rBuscar.setEnabled(false);
        this.btnRegistroCompleto.setEnabled(false);
    }
    
    public void refrescarNiño(JTable tabla,int opc) throws IOException{
        if(opc==0){
            int id_inf_Selected = (int) this.jTablaRegistro.getValueAt(
                   this.jTablaRegistro.getSelectedRow(),0);
           //int indexSelected = (int) this.jTablaRegistro.getSelectedRow();
            //Se cargan los datos del niño seleccionado
           //this.ventanaNiño.niñoActual = this.coneccionBdd.registroActual[indexSelected];
           this.niñoActual = this.ventRegistro.coneccionBdd.obtenerInfante(
                   String.valueOf(id_inf_Selected)
           );
        }else{
            this.niñoActual = new Infant();
            Detalles detalles = this.listaAsistencia.detalles[tabla.getSelectedRow()];
            this.niñoActual.name_inf = detalles.i_name;
            this.niñoActual.surnames = detalles.i_surnames;
            this.niñoActual.birth_day = "";
            this.niñoActual.dir = "";
            this.niñoActual.age = detalles.i_age;
            this.niñoActual.image_path = detalles.i_image_path;
            this.niñoActual.allergies = detalles.i_allergies;
            this.niñoActual.tel = detalles.i_tel;
            this.observacion.setText(detalles.de_obs);
            this.niñoActual.num_service = detalles.i_num_service;
            this.niñoActual.medical_service = detalles.i_medical_service;
            this.niñoActual.reg_date = detalles.i_reg_date;
            this.niñoActual.id_inf = detalles.i_id_inf;
        }
        
        this.nombreL.setText( this.niñoActual.name_inf+" "+niñoActual.surnames  );
        this.edadL.setText( String.valueOf( this.niñoActual.age ) );
        this.telefonosL.setText( this.niñoActual.tel );
        this.Alergias.setText( this.niñoActual.allergies );
        this.servicioNumeroMedicoL.setText( this.niñoActual.medical_service+": "+this.niñoActual.num_service);
        this.fechaRegistroL.setText( this.niñoActual.reg_date );
        this.addLbl.setText("Seleccione un niño");
    }
    
    public void limpiarDatosNiño(){
        this.nombreL.setText( " " );
        this.edadL.setText( " " );
        this.telefonosL.setText( " " );
        this.Alergias.setText( " " );
        this.servicioNumeroMedicoL.setText( " " );
        this.fechaRegistroL.setText( " " );
        this.obsGuardarBtn.setEnabled(false);
        this.darSalidaBtn.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        derechePrincipal = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombreL = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        edadL = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        telefonosL = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Alergias = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        servicioNumeroMedicoL = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fechaRegistroL = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        calendarPanel1 = new com.github.lgooddatepicker.components.CalendarPanel();
        jButton1 = new javax.swing.JButton();
        regresarPPrincipalBtn = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cambiarVistaHistorialBtn = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        mostrarListaHistorialLbl = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        izquierdoPrincipal = new javax.swing.JTabbedPane();
        principalP = new javax.swing.JPanel();
        record = new javax.swing.JButton();
        obsGuardarBtn = new javax.swing.JButton();
        historialAsistencias = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAsistenciaTable = new javax.swing.JTable();
        add = new javax.swing.JButton();
        darSalidaBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        observacion = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        cambiarVistaBtn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        mostrarListaLbl = new javax.swing.JLabel();
        mostrandoListaTipoLbl = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        añadirNiño = new javax.swing.JPanel();
        btnRegistroCompleto = new javax.swing.JButton();
        addLbl = new javax.swing.JLabel();
        rBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaRegistro = new javax.swing.JTable();
        addNiño = new javax.swing.JButton();
        cancelarAñadirHermano = new javax.swing.JButton();
        cancelarAñadirHermanoLbl = new javax.swing.JLabel();
        instrucLbl = new javax.swing.JLabel();
        historial = new javax.swing.JPanel();
        observacionHistorialLvl = new javax.swing.JLabel();
        fechaSelec = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaAsistenciaHistorialTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("DiarioAngelitos"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        derechePrincipal.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));

        jLabel4.setText("Datos del menor");
        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-usuario-de-género-neutro-100.png"))); // NOI18N
        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N

        jLabel5.setText("Nombre");
        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        nombreL.setText("jLabel16");

        jLabel6.setText("Edad");
        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        edadL.setText("jLabel16");

        jLabel7.setText("Telefonos");
        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        telefonosL.setText("jLabel16");

        jLabel11.setText("Alergias");
        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        Alergias.setText("jLabel16");

        jLabel12.setText("Servicio medico y numero");
        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        servicioNumeroMedicoL.setText("jLabel16");

        jLabel8.setText("Fecha de ingreso");
        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        fechaRegistroL.setText("jLabel16");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Alergias)
                                    .addComponent(servicioNumeroMedicoL)
                                    .addComponent(telefonosL)
                                    .addComponent(nombreL)
                                    .addComponent(edadL)
                                    .addComponent(fechaRegistroL)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(jLabel5)
                .addGap(9, 9, 9)
                .addComponent(nombreL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edadL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(telefonosL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Alergias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(8, 8, 8)
                .addComponent(servicioNumeroMedicoL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fechaRegistroL)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        derechePrincipal.addTab("tab2", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        calendarPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendarPanel1MouseClicked(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        regresarPPrincipalBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-carpeta-de-usuario-45.png"))); // NOI18N
        regresarPPrincipalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarPPrincipalBtnActionPerformed(evt);
            }
        });

        jLabel16.setText("Regresar");
        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        cambiarVistaHistorialBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-carpeta-de-documentos-45.png"))); // NOI18N
        cambiarVistaHistorialBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cambiarVistaHistorialBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarVistaHistorialBtnActionPerformed(evt);
            }
        });

        jLabel21.setText("Mostrar");
        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        mostrarListaHistorialLbl.setText("salidas");
        mostrarListaHistorialLbl.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(calendarPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cambiarVistaHistorialBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mostrarListaHistorialLbl)
                            .addComponent(regresarPPrincipalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calendarPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(cambiarVistaHistorialBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mostrarListaHistorialLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(regresarPPrincipalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        derechePrincipal.addTab("tab1", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(derechePrincipal)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(derechePrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 320, 510));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diary (1).png"))); // NOI18N
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N

        jLabel1.setText("Diario Angelitos");
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N

        jLabel13.setText("Fecha:");
        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        date1.setText("00/00/0000");
        date1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        izquierdoPrincipal.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        principalP.setBackground(new java.awt.Color(255, 255, 255));

        record.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-dosier-45.png"))); // NOI18N
        record.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        record.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordActionPerformed(evt);
            }
        });

        obsGuardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-carpeta-de-documentos-45.png"))); // NOI18N
        obsGuardarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        obsGuardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obsGuardarBtnActionPerformed(evt);
            }
        });

        historialAsistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-carpeta-de-usuario-45.png"))); // NOI18N
        historialAsistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialAsistenciasActionPerformed(evt);
            }
        });

        jLabel10.setText("Historial");
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jLabel18.setText("Observación:");
        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jLabel9.setText("Registro");
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        listaAsistenciaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Infante", "Observaciones", "Tutor", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listaAsistenciaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaAsistenciaTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaAsistenciaTable);

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-más-45.png"))); // NOI18N
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        darSalidaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-eliminar-45 (2).png"))); // NOI18N
        darSalidaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        darSalidaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darSalidaBtnActionPerformed(evt);
            }
        });

        jLabel15.setText("Añadir");
        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        observacion.setColumns(20);
        observacion.setRows(5);
        jScrollPane3.setViewportView(observacion);

        jLabel14.setText("Guardar");
        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        cambiarVistaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-carpeta-de-documentos-45.png"))); // NOI18N
        cambiarVistaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cambiarVistaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarVistaBtnActionPerformed(evt);
            }
        });

        jLabel20.setText("Mostrar");
        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        mostrarListaLbl.setText("salidas");
        mostrarListaLbl.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        mostrandoListaTipoLbl.setText("Mostrando las Entradas del dia de hoy:");
        mostrandoListaTipoLbl.setBackground(new java.awt.Color(0, 0, 0));
        mostrandoListaTipoLbl.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        mostrandoListaTipoLbl.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setText("Dar salida");
        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        javax.swing.GroupLayout principalPLayout = new javax.swing.GroupLayout(principalP);
        principalP.setLayout(principalPLayout);
        principalPLayout.setHorizontalGroup(
            principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalPLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalPLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(433, Short.MAX_VALUE))
                    .addGroup(principalPLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(obsGuardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(principalPLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel14)))
                        .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(principalPLayout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(record, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(principalPLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(historialAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(16, 16, 16))))
            .addGroup(principalPLayout.createSequentialGroup()
                .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalPLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cambiarVistaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mostrarListaLbl)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(darSalidaBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mostrandoListaTipoLbl)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        principalPLayout.setVerticalGroup(
            principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalPLayout.createSequentialGroup()
                .addComponent(mostrandoListaTipoLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(principalPLayout.createSequentialGroup()
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(darSalidaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addGap(12, 12, 12)
                        .addComponent(cambiarVistaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mostrarListaLbl))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalPLayout.createSequentialGroup()
                            .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(record, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(historialAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalPLayout.createSequentialGroup()
                            .addComponent(obsGuardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        izquierdoPrincipal.addTab("", principalP);

        añadirNiño.setBackground(new java.awt.Color(255, 255, 255));

        btnRegistroCompleto.setText("Registro Completo");
        btnRegistroCompleto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnRegistroCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroCompletoActionPerformed(evt);
            }
        });

        addLbl.setText("Añadir niño");
        addLbl.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N

        rBuscar.setText("Buscar...");
        rBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        rBuscar.setForeground(new java.awt.Color(102, 102, 102));
        rBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rBuscarFocusGained(evt);
            }
        });
        rBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rBuscarMouseClicked(evt);
            }
        });
        rBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rBuscarKeyTyped(evt);
            }
        });

        jTablaRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Edad", "Telefono", "Fecha de Registro"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablaRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaRegistroMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablaRegistro);

        addNiño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-de-acuerdo-45.png"))); // NOI18N
        addNiño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNiñoActionPerformed(evt);
            }
        });

        cancelarAñadirHermano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-cancelar-45.png"))); // NOI18N
        cancelarAñadirHermano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarAñadirHermanoActionPerformed(evt);
            }
        });

        cancelarAñadirHermanoLbl.setText("Cancelar");
        cancelarAñadirHermanoLbl.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N

        instrucLbl.setText("Seleccione un niño de la lista para continuar:");
        instrucLbl.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N

        javax.swing.GroupLayout añadirNiñoLayout = new javax.swing.GroupLayout(añadirNiño);
        añadirNiño.setLayout(añadirNiñoLayout);
        añadirNiñoLayout.setHorizontalGroup(
            añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(añadirNiñoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(añadirNiñoLayout.createSequentialGroup()
                        .addGroup(añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(añadirNiñoLayout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(addLbl))
                            .addGroup(añadirNiñoLayout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(addNiño, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cancelarAñadirHermanoLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cancelarAñadirHermano, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(157, 157, 157))
                    .addGroup(añadirNiñoLayout.createSequentialGroup()
                        .addGroup(añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(instrucLbl)
                            .addGroup(añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, añadirNiñoLayout.createSequentialGroup()
                                    .addComponent(rBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRegistroCompleto))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 34, Short.MAX_VALUE))))
        );
        añadirNiñoLayout.setVerticalGroup(
            añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(añadirNiñoLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistroCompleto))
                .addGap(18, 18, 18)
                .addComponent(instrucLbl)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addNiño, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarAñadirHermano, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addLbl)
                    .addComponent(cancelarAñadirHermanoLbl))
                .addGap(17, 17, 17))
        );

        izquierdoPrincipal.addTab("", añadirNiño);

        historial.setBackground(new java.awt.Color(255, 255, 255));

        observacionHistorialLvl.setText("Observación:");
        observacionHistorialLvl.setBackground(new java.awt.Color(0, 0, 0));
        observacionHistorialLvl.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        observacionHistorialLvl.setForeground(new java.awt.Color(0, 0, 0));

        fechaSelec.setText("Dia de consulta:");
        fechaSelec.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        fechaSelec.setForeground(new java.awt.Color(60, 63, 65));

        listaAsistenciaHistorialTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Infante", "Observaciones", "Tutor", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listaAsistenciaHistorialTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaAsistenciaHistorialTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(listaAsistenciaHistorialTable);

        javax.swing.GroupLayout historialLayout = new javax.swing.GroupLayout(historial);
        historial.setLayout(historialLayout);
        historialLayout.setHorizontalGroup(
            historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historialLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(observacionHistorialLvl, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaSelec, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(historialLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );
        historialLayout.setVerticalGroup(
            historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historialLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fechaSelec)
                .addGap(349, 349, 349)
                .addComponent(observacionHistorialLvl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(historialLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
        );

        izquierdoPrincipal.addTab("", historial);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(date1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(izquierdoPrincipal)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(date1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(izquierdoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 570, 510));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarAñadirHermanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAñadirHermanoActionPerformed
        this.tutoresNiño = new TutorsInfant();
        this.actualizarTablaConNiños();
        this.actualizarTablaListaAsistencia(0);
        this.limpiarDatosNiño();
        this.izquierdoPrincipal.setSelectedIndex(0);
    }//GEN-LAST:event_cancelarAñadirHermanoActionPerformed
    
    private void addNiñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNiñoActionPerformed
        if(this.niñoSeleccionado==false && this.seleccionandoTutorSalida == false){
            this.actualizarTablaConTutores();
            this.niñoSeleccionado = true; 
        }else if(this.niñoSeleccionado && this.tutorSeleccionado && this.seleccionandoTutorSalida == false){
            //Aqui va donde se añade
            this.ventRegistro.coneccionBdd.ingresoNuevoHistorial(
                String.valueOf(this.niñoActual.id_inf),
                String.valueOf(this.tutoresNiño.tutoresNiño[jTablaRegistro.getSelectedRow()].id_tut));
            if(this.ventRegistro.coneccionBdd.exitoConsulta){
                JOptionPane.showMessageDialog(this.getFrame(), "Añadido a la lista");
                this.actualizarTablaListaAsistencia(0);
            }
            this.tutoresNiño = new TutorsInfant();
            this.actualizarTablaConNiños();
            this.limpiarDatosNiño();
            this.izquierdoPrincipal.setSelectedIndex(0);
        }else if(this.niñoSeleccionado==false && this.tutorSeleccionado && this.seleccionandoTutorSalida){
            //Aqui va donde se borra
            this.ventRegistro.coneccionBdd.salidaNuevaHistorial(
                String.valueOf(this.niñoActual.id_inf),
                String.valueOf(this.tutoresNiño.tutoresNiño[jTablaRegistro.getSelectedRow()].id_tut));
            if(this.ventRegistro.coneccionBdd.exitoConsulta){
                JOptionPane.showMessageDialog(this.getFrame(), "Sacado de la lista");
                this.actualizarTablaListaAsistencia(0);
                this.limpiarDatosNiño();
            }
            this.tutoresNiño = new TutorsInfant();
            this.actualizarTablaConNiños();
            this.limpiarDatosNiño();
            this.izquierdoPrincipal.setSelectedIndex(0);
        }
    }//GEN-LAST:event_addNiñoActionPerformed

    private void jTablaRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaRegistroMouseClicked
        try {   
            if(this.niñoSeleccionado==false && this.seleccionandoTutorSalida==false){
                this.refrescarNiño(this.jTablaRegistro,0);
            }else{
                this.tutorSeleccionado = true;
            }
            
            this.addLbl.setText("Continuar");
            this.addNiño.setEnabled(true);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTablaRegistroMouseClicked

    private void rBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rBuscarKeyTyped
        if(this.rBuscar.getText()!=""){
            if(this.ventRegistro.coneccionBdd.cantRegistros < 1 ){
                JOptionPane.showMessageDialog(null, "Registro de niños Vacio");
                this.rBuscar.setText("");
            }else{
                this.ventRegistro.coneccionBdd.dicionarioNombresNiños.buscarCoincidencias( this.rBuscar.getText().toLowerCase() );
                //Borramos el primer campo hasta que este vacia la tabla
                while(0<modeloTablaAñadir.getRowCount()){
                    modeloTablaAñadir.removeRow(0);
                }
                for(int x=0;x<this.ventRegistro.coneccionBdd.dicionarioNombresNiños.cont;x++){
                    filasTablaAñadir[0]=this.ventRegistro.coneccionBdd.registroActual[
                    this.ventRegistro.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                    ].id_inf;
                    filasTablaAñadir[1]=this.ventRegistro.coneccionBdd.registroActual[
                    this.ventRegistro.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                    ].name_inf + " " +this.ventRegistro.coneccionBdd.registroActual[
                    this.ventRegistro.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                    ].surnames;
                    filasTablaAñadir[2]=this.ventRegistro.coneccionBdd.registroActual[
                    this.ventRegistro.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                    ].age;
                    filasTablaAñadir[3]=this.ventRegistro.coneccionBdd.registroActual[
                    this.ventRegistro.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                    ].tel;
                    filasTablaAñadir[4]=this.ventRegistro.coneccionBdd.registroActual[
                    this.ventRegistro.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                    ].reg_date;
                    modeloTablaAñadir.addRow(filasTablaAñadir);
                }
            }
        }else if(this.rBuscar.getText()==""){
            this.actualizarTablaConNiños();
        }
    }//GEN-LAST:event_rBuscarKeyTyped

    private void rBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rBuscarMouseClicked
        rBuscar.setText("");
    }//GEN-LAST:event_rBuscarMouseClicked

    private void btnRegistroCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroCompletoActionPerformed
        this.actualizarTablaConNiños();
    }//GEN-LAST:event_btnRegistroCompletoActionPerformed
    boolean seleccionandoTutorSalida = false;
    private void darSalidaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darSalidaBtnActionPerformed
        this.actualizarTablaConTutores();
        this.addNiño.setEnabled(false);
        this.seleccionandoTutorSalida = true;
        this.instrucLbl.setText("Seleccione el tutor que paso por el niño:");
        
        JOptionPane.showMessageDialog(this.getFrame(), "Seleccione quien paso por el niño por favor");
        
        this.izquierdoPrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_darSalidaBtnActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        this.limpiarDatosNiño();
        this.izquierdoPrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_addActionPerformed

    private void historialAsistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialAsistenciasActionPerformed
        this.borrarVistaAsistenciaHistorial();
        this.izquierdoPrincipal.setSelectedIndex(2);
        this.derechePrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_historialAsistenciasActionPerformed

    private void recordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordActionPerformed
        ventRegistro.cambiarBotones(1);
        ventRegistro.setVisible(true);
        dispose();
    }//GEN-LAST:event_recordActionPerformed

    private void regresarPPrincipalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarPPrincipalBtnActionPerformed
        //Borramos el primer campo hasta que este vacia la tabla
        while(0<this.modeloTablaAsistencias.getRowCount()){
            this.modeloTablaAsistencias.removeRow(0);
        }
        this.observacionHistorialLvl.setText("Observación: ");
        this.izquierdoPrincipal.setSelectedIndex(0);
        this.derechePrincipal.setSelectedIndex(0);
    }//GEN-LAST:event_regresarPPrincipalBtnActionPerformed

    private void rBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rBuscarFocusGained
        this.rBuscar.setText("");
    }//GEN-LAST:event_rBuscarFocusGained

    private void listaAsistenciaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaAsistenciaTableMouseClicked
        try {
            this.refrescarNiño(this.listaAsistenciaTable,1);
            if(this.mostrandoEntradas){
                this.obsGuardarBtn.setEnabled(true);
                this.darSalidaBtn.setEnabled(true);
                this.observacion.setEnabled(true);
            }else{
                this.obsGuardarBtn.setEnabled(true);
                this.darSalidaBtn.setEnabled(false);
                this.observacion.setEnabled(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listaAsistenciaTableMouseClicked

    private void obsGuardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obsGuardarBtnActionPerformed
        if(this.mostrandoEntradas){
            this.ventRegistro.coneccionBdd.modificarObservacionEntradaSalida(
            String.valueOf(
                this.listaAsistencia.detalles[
                    listaAsistenciaTable.getSelectedRow()
                ].id_ent_sal ),
                " "+this.observacion.getText() , 
                0
            );
            this.actualizarTablaListaAsistencia(0);
        }else{
            this.ventRegistro.coneccionBdd.modificarObservacionEntradaSalida(
            String.valueOf(
                this.listaAsistencia.detalles[
                    listaAsistenciaTable.getSelectedRow()
                ].id_ent_sal ),
                " "+this.observacion.getText() , 
                1
            );
            this.actualizarTablaListaAsistencia(1);
        }
        if(this.ventRegistro.coneccionBdd.exitoConsulta){
            JOptionPane.showMessageDialog(
                this.getFrame(), "Observacion Modificada"
            );
        }
    }//GEN-LAST:event_obsGuardarBtnActionPerformed

    private void cambiarVistaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarVistaBtnActionPerformed
        if(this.mostrandoEntradas){
            this.actualizarTablaListaAsistencia(1);
        }else{
            this.actualizarTablaListaAsistencia(0);
        }
        
    }//GEN-LAST:event_cambiarVistaBtnActionPerformed

    private void calendarPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarPanel1MouseClicked
        
    }//GEN-LAST:event_calendarPanel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            Date date = Date.from(this.calendarPanel1.getSelectedDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDate = dateFormat.format(date); 
            this.fechaSelec.setText("Dia de consulta: "+strDate);
            this.actualizarTablaListaAsistenciaHistorial(strDate, 0);
            this.mostrandoEntradasHistorial=true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(
                this.getFrame(), "Seleccione un dia por favor"
            );
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listaAsistenciaHistorialTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaAsistenciaHistorialTableMouseClicked
        this.observacionHistorialLvl.setText(
            "Observación:"+
            this.listaAsistenciaHistorial.detalles[
                this.listaAsistenciaHistorialTable.getSelectedRow()
            ].de_obs
        );
    }//GEN-LAST:event_listaAsistenciaHistorialTableMouseClicked

    private void cambiarVistaHistorialBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarVistaHistorialBtnActionPerformed
        if(this.mostrandoEntradasHistorial){
            try{
                Date date = Date.from(this.calendarPanel1.getSelectedDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
                String strDate = dateFormat.format(date); 
                this.fechaSelec.setText("Dia de consulta: "+strDate);
                this.actualizarTablaListaAsistenciaHistorial(strDate, 1);
            }catch(Exception e){
                JOptionPane.showMessageDialog(
                    this.getFrame(), "Seleccione un dia por favor"
                );
            }
        }else{
            try{
                Date date = Date.from(this.calendarPanel1.getSelectedDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
                String strDate = dateFormat.format(date); 
                this.fechaSelec.setText("Dia de consulta: "+strDate);
                this.actualizarTablaListaAsistenciaHistorial(strDate, 0);
                this.mostrandoEntradasHistorial=true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(
                    this.getFrame(), "Seleccione un dia por favor"
                );
            }
        }
    }//GEN-LAST:event_cambiarVistaHistorialBtnActionPerformed
    //Nuevo método para conseguir el JFrame de la Ventana Principal
    private JFrame getFrame(){
        return this;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Alergias;
    private javax.swing.JButton add;
    private javax.swing.JLabel addLbl;
    private javax.swing.JButton addNiño;
    private javax.swing.JPanel añadirNiño;
    private javax.swing.JButton btnRegistroCompleto;
    private com.github.lgooddatepicker.components.CalendarPanel calendarPanel1;
    private javax.swing.JButton cambiarVistaBtn;
    private javax.swing.JButton cambiarVistaHistorialBtn;
    private javax.swing.JButton cancelarAñadirHermano;
    private javax.swing.JLabel cancelarAñadirHermanoLbl;
    private javax.swing.JButton darSalidaBtn;
    private javax.swing.JLabel date1;
    private javax.swing.JTabbedPane derechePrincipal;
    private javax.swing.JLabel edadL;
    private javax.swing.JLabel fechaRegistroL;
    private javax.swing.JLabel fechaSelec;
    private javax.swing.JPanel historial;
    private javax.swing.JButton historialAsistencias;
    private javax.swing.JLabel instrucLbl;
    private javax.swing.JTabbedPane izquierdoPrincipal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTablaRegistro;
    private javax.swing.JTable listaAsistenciaHistorialTable;
    private javax.swing.JTable listaAsistenciaTable;
    private javax.swing.JLabel mostrandoListaTipoLbl;
    private javax.swing.JLabel mostrarListaHistorialLbl;
    private javax.swing.JLabel mostrarListaLbl;
    private javax.swing.JLabel nombreL;
    private javax.swing.JButton obsGuardarBtn;
    private javax.swing.JTextArea observacion;
    private javax.swing.JLabel observacionHistorialLvl;
    private javax.swing.JPanel principalP;
    private javax.swing.JTextField rBuscar;
    private javax.swing.JButton record;
    private javax.swing.JButton regresarPPrincipalBtn;
    private javax.swing.JLabel servicioNumeroMedicoL;
    private javax.swing.JLabel telefonosL;
    // End of variables declaration//GEN-END:variables
}
