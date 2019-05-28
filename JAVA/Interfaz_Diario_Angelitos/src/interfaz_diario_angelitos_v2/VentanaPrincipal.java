
package interfaz_diario_angelitos_v2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Infant;
import models.ListaAsistencia;
import models.TutorsInfant;

public class VentanaPrincipal extends javax.swing.JFrame {

    VentanaRegistro ventRegistro;
    //El modelo es la conexion entre la tabla de la interfaz y la 
    DefaultTableModel modeloTablaPrincipal;
    Object [] filasPrincipal;
    
    DefaultTableModel modeloTablaAñadir;
    Object [] filasTablaAñadir;
    //Variables para llenar las tablas y datos
    Infant niñoActual;
    TutorsInfant tutoresNiño;
    ListaAsistencia listaAsistencia;
    //Variables para auxiliar el control de la pestaña de añadir niño
    boolean niñoSeleccionado = false;
    boolean tutorSeleccionado = false;
    
    public VentanaPrincipal() {
        initComponents();
        this.ventRegistro = new VentanaRegistro( getFrame() );
        setLocationRelativeTo(null);
        
        //Se actualiza la fecha del sistema 
        this.date1.setText(fechaActual());
        
        this.tutoresNiño = new TutorsInfant();
        this.modeloTablaAñadir = (DefaultTableModel) jTablaRegistro.getModel();
        this.jTablaRegistro.setModel(modeloTablaAñadir);  
        this.filasTablaAñadir=new Object[5];
        
        //Variable para actualizar y llenar los datos de asistencia actuales
        this.listaAsistencia = ventRegistro.coneccionBdd.obtenerListaAsistenciaActual();
        //Conectamos la tabla de la interfaz con la tabla que tenemos 
        //en el modelo
        this.modeloTablaPrincipal = (DefaultTableModel) attendanceListTable.getModel();
        this.attendanceListTable.setModel(modeloTablaPrincipal);
        this.filasPrincipal=new Object[5];
        
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
        //this.coneccionBdd.obtenerRegistroInfantes();
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
    }
    
    public void refrescarNiño(JTable tabla) throws IOException{
        this.niñoActual = this.ventRegistro.coneccionBdd.registroActual[tabla.getSelectedRow()];
        this.nombreL.setText( this.niñoActual.name_inf+" "+niñoActual.surnames  );
        this.edadL.setText( String.valueOf( this.niñoActual.age ) );
        this.telefonosL.setText( this.niñoActual.tel );
        this.Alergias.setText( this.niñoActual.allergies );
        this.servicioNumeroMedicoL.setText( this.niñoActual.medical_service+": "+this.niñoActual.num_service);
        this.fechaRegistroL.setText( this.niñoActual.reg_date );
        this.addLbl.setText("Seleccione\nun niño");
    }
    
    public void limpiarDatosNiño(){
        this.nombreL.setText( " " );
        this.edadL.setText( " " );
        this.telefonosL.setText( " " );
        this.Alergias.setText( " " );
        this.servicioNumeroMedicoL.setText( " " );
        this.fechaRegistroL.setText( " " );
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nombreL = new javax.swing.JLabel();
        edadL = new javax.swing.JLabel();
        telefonosL = new javax.swing.JLabel();
        Alergias = new javax.swing.JLabel();
        servicioNumeroMedicoL = new javax.swing.JLabel();
        fechaRegistroL = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        izquierdoPrincipal = new javax.swing.JTabbedPane();
        principalP = new javax.swing.JPanel();
        record = new javax.swing.JButton();
        observacion = new javax.swing.JButton();
        historialAsistencias = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendanceListTable = new javax.swing.JTable();
        add = new javax.swing.JButton();
        darSalida = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
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
        historialAsistencias1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        attendanceListHistorialTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("DiarioAngelitos"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-usuario-de-género-neutro-100.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel4.setText("Datos del menor");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel5.setText("Nombre");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel6.setText("Edad");

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel7.setText("Telefonos");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel8.setText("Fecha de ingreso");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel11.setText("Alergias");

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel12.setText("Servicio medico y numero");

        nombreL.setText("jLabel16");

        edadL.setText("jLabel16");

        telefonosL.setText("jLabel16");

        Alergias.setText("jLabel16");

        servicioNumeroMedicoL.setText("jLabel16");

        fechaRegistroL.setText("jLabel16");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 66, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(94, 94, 94))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Alergias)
                            .addComponent(fechaRegistroL)
                            .addComponent(servicioNumeroMedicoL)
                            .addComponent(telefonosL)
                            .addComponent(nombreL)
                            .addComponent(edadL))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreL)
                .addGap(2, 2, 2)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edadL)
                .addGap(6, 6, 6)
                .addComponent(jLabel7)
                .addGap(2, 2, 2)
                .addComponent(telefonosL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(4, 4, 4)
                .addComponent(Alergias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(1, 1, 1)
                .addComponent(servicioNumeroMedicoL)
                .addGap(1, 1, 1)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaRegistroL)
                .addGap(30, 30, 30))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 260, 510));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diary (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("Diario Angelitos");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel13.setText("Fecha:");

        date1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        date1.setText("00/00/0000");

        izquierdoPrincipal.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        principalP.setBackground(new java.awt.Color(255, 255, 255));

        record.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-dosier-45.png"))); // NOI18N
        record.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        record.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordActionPerformed(evt);
            }
        });

        observacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-carpeta-de-documentos-45.png"))); // NOI18N
        observacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        historialAsistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-carpeta-de-usuario-45.png"))); // NOI18N
        historialAsistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialAsistenciasActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setText("Historial");

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel18.setText("Observación:");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel9.setText("Registro");

        attendanceListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lista de Asistencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(attendanceListTable);

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-más-45.png"))); // NOI18N
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        darSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-eliminar-45 (2).png"))); // NOI18N
        darSalida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        darSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darSalidaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel15.setText("Añadir");

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel17.setText("Eliminar");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel14.setText("Guardar");

        javax.swing.GroupLayout principalPLayout = new javax.swing.GroupLayout(principalP);
        principalP.setLayout(principalPLayout);
        principalPLayout.setHorizontalGroup(
            principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalPLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalPLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalPLayout.createSequentialGroup()
                        .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(principalPLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel15))
                                .addComponent(darSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalPLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(principalPLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel14)))
                        .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(principalPLayout.createSequentialGroup()
                                .addGap(18, 52, Short.MAX_VALUE)
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
        );
        principalPLayout.setVerticalGroup(
            principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalPLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(principalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalPLayout.createSequentialGroup()
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(darSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
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
                            .addComponent(observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        izquierdoPrincipal.addTab("", principalP);

        añadirNiño.setBackground(new java.awt.Color(255, 255, 255));

        btnRegistroCompleto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnRegistroCompleto.setText("Registro Completo");
        btnRegistroCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroCompletoActionPerformed(evt);
            }
        });

        addLbl.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        addLbl.setText("Añadir niño");

        rBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        rBuscar.setForeground(new java.awt.Color(102, 102, 102));
        rBuscar.setText("Buscar...");
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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

        cancelarAñadirHermanoLbl.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        cancelarAñadirHermanoLbl.setText("Cancelar");

        instrucLbl.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        instrucLbl.setText("Seleccione un niño de la lista para continuar:");

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
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(añadirNiñoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addLbl)
                    .addComponent(cancelarAñadirHermanoLbl))
                .addGap(17, 17, 17))
        );

        izquierdoPrincipal.addTab("", añadirNiño);

        historial.setBackground(new java.awt.Color(255, 255, 255));

        historialAsistencias1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-carpeta-de-usuario-45.png"))); // NOI18N
        historialAsistencias1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialAsistencias1ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel16.setText("Regresar");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel19.setText("Observación:");

        attendanceListHistorialTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lista de Asistencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(attendanceListHistorialTable);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane5.setViewportView(jTextArea2);

        javax.swing.GroupLayout historialLayout = new javax.swing.GroupLayout(historial);
        historial.setLayout(historialLayout);
        historialLayout.setHorizontalGroup(
            historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historialLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(historialLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(historialLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(historialLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(historialAsistencias1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(102, 102, 102))))
        );
        historialLayout.setVerticalGroup(
            historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, historialLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(historialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(historialLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(historialAsistencias1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
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
                .addComponent(izquierdoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 570, 510));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarAñadirHermanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAñadirHermanoActionPerformed
        this.tutoresNiño = new TutorsInfant();
        this.actualizarTablaConNiños();
        this.limpiarDatosNiño();
        this.izquierdoPrincipal.setSelectedIndex(0);
    }//GEN-LAST:event_cancelarAñadirHermanoActionPerformed
    
    private void addNiñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNiñoActionPerformed
        if(this.niñoSeleccionado==false){
            this.actualizarTablaConTutores();
            this.niñoSeleccionado = true; 
        }else if(this.niñoSeleccionado && this.tutorSeleccionado){
            //Aqui va donde se añade
            this.ventRegistro.coneccionBdd.ingresoNuevoHistorial(
                String.valueOf(this.niñoActual.id_inf),
                String.valueOf(this.tutoresNiño.tutoresNiño[jTablaRegistro.getSelectedRow()].id_tut));
            if(this.ventRegistro.coneccionBdd.exitoConsulta){
                JOptionPane.showMessageDialog(this.getFrame(), "Añadido a la lista");
            }
            this.tutoresNiño = new TutorsInfant();
            this.actualizarTablaConNiños();
            this.limpiarDatosNiño();
            this.izquierdoPrincipal.setSelectedIndex(0);
            
        }
    }//GEN-LAST:event_addNiñoActionPerformed

    private void jTablaRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaRegistroMouseClicked
        try {   
            if(this.niñoSeleccionado==false){
                this.refrescarNiño(this.jTablaRegistro);
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
                this.ventRegistro.coneccionBdd.dicionarioNombresNiños.buscarCoincidencias( this.rBuscar.getText() );
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
                    ];
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

    private void darSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_darSalidaActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        this.izquierdoPrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_addActionPerformed

    private void historialAsistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialAsistenciasActionPerformed
        this.izquierdoPrincipal.setSelectedIndex(2);
    }//GEN-LAST:event_historialAsistenciasActionPerformed

    private void recordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordActionPerformed
        ventRegistro.cambiarBotones(1);
        ventRegistro.setVisible(true);
        dispose();
    }//GEN-LAST:event_recordActionPerformed

    private void historialAsistencias1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialAsistencias1ActionPerformed
        this.izquierdoPrincipal.setSelectedIndex(0);
    }//GEN-LAST:event_historialAsistencias1ActionPerformed

    private void rBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rBuscarFocusGained
        this.rBuscar.setText("");
    }//GEN-LAST:event_rBuscarFocusGained
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
    private javax.swing.JTable attendanceListHistorialTable;
    private javax.swing.JTable attendanceListTable;
    private javax.swing.JPanel añadirNiño;
    private javax.swing.JButton btnRegistroCompleto;
    private javax.swing.JButton cancelarAñadirHermano;
    private javax.swing.JLabel cancelarAñadirHermanoLbl;
    private javax.swing.JButton darSalida;
    private javax.swing.JLabel date1;
    private javax.swing.JLabel edadL;
    private javax.swing.JLabel fechaRegistroL;
    private javax.swing.JPanel historial;
    private javax.swing.JButton historialAsistencias;
    private javax.swing.JButton historialAsistencias1;
    private javax.swing.JLabel instrucLbl;
    private javax.swing.JTabbedPane izquierdoPrincipal;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTablaRegistro;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel nombreL;
    private javax.swing.JButton observacion;
    private javax.swing.JPanel principalP;
    private javax.swing.JTextField rBuscar;
    private javax.swing.JButton record;
    private javax.swing.JLabel servicioNumeroMedicoL;
    private javax.swing.JLabel telefonosL;
    // End of variables declaration//GEN-END:variables
}
