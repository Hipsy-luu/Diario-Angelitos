package interfaz_diario_angelitos_v2;

import appis.ConexionBaseDatos;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import models.TutorsInfant;

public class AñadirModificarTutor extends javax.swing.JFrame {

    
    AñadirModificarNiño ventanaNiño;
    
    TutorsInfant tutoresNiño;
    DefaultTableModel modeloTablaTutores;
    Object[] fila;
    
    public AñadirModificarTutor(AñadirModificarNiño ventanaNiño) {
        initComponents();
        setLocationRelativeTo(null);
        this.tutoresNiño = new TutorsInfant();
        this.modeloTablaTutores = (DefaultTableModel) tablaTutores.getModel();
        //Variable para añadir los elementos a la tabla
        this.fila=new Object[5];
        
        this.ventanaNiño = ventanaNiño;
    }
    
    private AñadirModificarTutor getFrame(){
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        formularioTutor = new javax.swing.JTabbedPane();
        panelModificar = new javax.swing.JPanel();
        cNombre = new javax.swing.JTextField();
        cApellidos = new javax.swing.JTextField();
        cCorreo = new javax.swing.JTextField();
        cTrabajo = new javax.swing.JTextField();
        cTelefono = new javax.swing.JTextField();
        modiTutorGuard = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        cEdad = new javax.swing.JTextField();
        cDireccion = new javax.swing.JTextField();
        borrarTutor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelAñadir = new javax.swing.JPanel();
        aNombre = new javax.swing.JTextField();
        aApellidos = new javax.swing.JTextField();
        aCorreo = new javax.swing.JTextField();
        aTrabajo = new javax.swing.JTextField();
        aTelefono = new javax.swing.JTextField();
        addTutorGuard = new javax.swing.JButton();
        aEdad = new javax.swing.JTextField();
        aDireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTutores = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tCerrar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        tCerrar.setText("Cerrar");
        tCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCerrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("Diario Angelitos");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diary (1).png"))); // NOI18N

        formularioTutor.setFocusCycleRoot(true);
        formularioTutor.setFocusable(false);
        formularioTutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formularioTutorMouseClicked(evt);
            }
        });

        panelModificar.setFocusTraversalPolicyProvider(true);
        panelModificar.setRequestFocusEnabled(false);
        panelModificar.setVerifyInputWhenFocusTarget(false);
        panelModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelModificarMouseClicked(evt);
            }
        });

        cNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cNombre.setForeground(new java.awt.Color(102, 102, 102));
        cNombre.setText("Nombre...");

        cApellidos.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cApellidos.setForeground(new java.awt.Color(102, 102, 102));
        cApellidos.setText("Apellido...");

        cCorreo.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cCorreo.setForeground(new java.awt.Color(102, 102, 102));
        cCorreo.setText("Correo...");

        cTrabajo.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cTrabajo.setForeground(new java.awt.Color(102, 102, 102));
        cTrabajo.setText("Lugar de trabajo...");

        cTelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cTelefono.setForeground(new java.awt.Color(102, 102, 102));
        cTelefono.setText("Telefono...");

        modiTutorGuard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-guardar-cerrar-40.png"))); // NOI18N
        modiTutorGuard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modiTutorGuardActionPerformed(evt);
            }
        });

        idLabel.setText("0");

        cEdad.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cEdad.setForeground(new java.awt.Color(102, 102, 102));
        cEdad.setText("Edad...");

        cDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cDireccion.setForeground(new java.awt.Color(102, 102, 102));
        cDireccion.setText("Direccion..");

        borrarTutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-eliminar-45.png"))); // NOI18N
        borrarTutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarTutorActionPerformed(evt);
            }
        });

        jLabel3.setText("Eliminar");

        jLabel5.setText("Guardar Cambios");

        javax.swing.GroupLayout panelModificarLayout = new javax.swing.GroupLayout(panelModificar);
        panelModificar.setLayout(panelModificarLayout);
        panelModificarLayout.setHorizontalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cNombre)
                        .addComponent(cApellidos)
                        .addComponent(cCorreo)
                        .addComponent(cTrabajo, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                        .addComponent(cTelefono))
                    .addComponent(idLabel)
                    .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cEdad)
                        .addComponent(cDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelModificarLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelModificarLayout.createSequentialGroup()
                        .addComponent(borrarTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modiTutorGuard, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))
                    .addGroup(panelModificarLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(70, 70, 70))))
        );
        panelModificarLayout.setVerticalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idLabel)
                .addGap(9, 9, 9)
                .addComponent(cNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modiTutorGuard)
                    .addComponent(borrarTutor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        formularioTutor.addTab("Modificar tutor", panelModificar);

        panelAñadir.setEnabled(false);
        panelAñadir.setFocusable(false);
        panelAñadir.setRequestFocusEnabled(false);

        aNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aNombre.setForeground(new java.awt.Color(102, 102, 102));
        aNombre.setText("Nombre...");
        aNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aNombreMouseClicked(evt);
            }
        });

        aApellidos.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aApellidos.setForeground(new java.awt.Color(102, 102, 102));
        aApellidos.setText("Apellido...");
        aApellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aApellidosMouseClicked(evt);
            }
        });

        aCorreo.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aCorreo.setForeground(new java.awt.Color(102, 102, 102));
        aCorreo.setText("Correo...");
        aCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aCorreoMouseClicked(evt);
            }
        });

        aTrabajo.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aTrabajo.setForeground(new java.awt.Color(102, 102, 102));
        aTrabajo.setText("Lugar de trabajo...");
        aTrabajo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aTrabajoMouseClicked(evt);
            }
        });

        aTelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aTelefono.setForeground(new java.awt.Color(102, 102, 102));
        aTelefono.setText("Telefono...");
        aTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aTelefonoMouseClicked(evt);
            }
        });

        addTutorGuard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-guardar-cerrar-40.png"))); // NOI18N
        addTutorGuard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTutorGuardActionPerformed(evt);
            }
        });

        aEdad.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aEdad.setForeground(new java.awt.Color(102, 102, 102));
        aEdad.setText("Edad...");
        aEdad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aEdadMouseClicked(evt);
            }
        });

        aDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aDireccion.setForeground(new java.awt.Color(102, 102, 102));
        aDireccion.setText("Direccion..");
        aDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aDireccionMouseClicked(evt);
            }
        });

        jLabel4.setText("Añadir Tutor");

        javax.swing.GroupLayout panelAñadirLayout = new javax.swing.GroupLayout(panelAñadir);
        panelAñadir.setLayout(panelAñadirLayout);
        panelAñadirLayout.setHorizontalGroup(
            panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(aEdad)
                    .addComponent(aDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aTelefono)
                    .addComponent(aTrabajo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addComponent(aCorreo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aApellidos, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aNombre))
                .addGap(26, 26, 26))
            .addGroup(panelAñadirLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(addTutorGuard, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAñadirLayout.setVerticalGroup(
            panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAñadirLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(aNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addTutorGuard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        formularioTutor.addTab("Añadir tutor", panelAñadir);

        tablaTutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaTutores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTutoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTutores);

        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-huella-dactilar-40.png"))); // NOI18N
        jButton2.setText("Huella");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tCerrar))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(formularioTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(tCerrar)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jButton2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(formularioTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void cargarVista(String id_inf){
        this.idLabel.setText( "0" );
        this.cNombre.setText( "Nombre...");
        this.cApellidos.setText( "Apellidos..." );
        this.cCorreo.setText( "Correo..." );
        this.cTrabajo.setText( "Lugar de Trabajo..." );
        this.cEdad.setText( "Edad..." );
        this.cDireccion.setText( "Direccion..." );
        this.cTelefono.setText( "Telefono..." );
        
        this.aNombre.setText( "Nombre...");
        this.aApellidos.setText( "Apellidos..." );
        this.aCorreo.setText( "Correo..." );
        this.aTrabajo.setText( "Lugar de Trabajo..." );
        this.aEdad.setText( "Edad..." );
        this.aDireccion.setText( "Direccion..." );
        this.aTelefono.setText( "Telefono..." );
        
        this.cNombre.setFocusable(false);
        this.cApellidos.setFocusable(false);
        this.cCorreo.setFocusable(false);
        this.cTrabajo.setFocusable(false);
        this.cEdad.setFocusable(false);
        this.cDireccion.setFocusable(false);
        this.cTelefono.setFocusable(false);
        this.modiTutorGuard.setEnabled(false);
        this.borrarTutor.setEnabled(false);
        
        this.formularioTutor.setSelectedIndex(0);
        
        this.tutoresNiño.id_inf = id_inf;
        this.tutoresNiño = new TutorsInfant();
        this.tutoresNiño = this.ventanaNiño.ventanaRegistro.coneccionBdd.listaTutoresInfante(id_inf);
        //Borramos el primer campo hasta que este vacia la tabla
        while(0<modeloTablaTutores.getRowCount()){
            modeloTablaTutores.removeRow(0);
        }
        for(int x=0;x< this.tutoresNiño.numTutores;x++ ){
            fila[0]=this.tutoresNiño.tutoresNiño[x].id_tut;
            fila[1]=this.tutoresNiño.tutoresNiño[x].name_tut + " " + this.tutoresNiño.tutoresNiño[x].surnames;
            modeloTablaTutores.addRow(fila); 
        }
    }
    private void aTelefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aTelefonoMouseClicked
        aTelefono.setText("");
    }//GEN-LAST:event_aTelefonoMouseClicked

    private void aTrabajoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aTrabajoMouseClicked
        aTrabajo.setText("");
    }//GEN-LAST:event_aTrabajoMouseClicked

    private void aCorreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aCorreoMouseClicked
        aCorreo.setText("");
    }//GEN-LAST:event_aCorreoMouseClicked

    private void aApellidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aApellidosMouseClicked
        aApellidos.setText("");
    }//GEN-LAST:event_aApellidosMouseClicked

    private void aNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aNombreMouseClicked
        aNombre.setText("");
    }//GEN-LAST:event_aNombreMouseClicked

    private void tCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCerrarActionPerformed
        this.ventanaNiño.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_tCerrarActionPerformed

    private void tablaTutoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTutoresMouseClicked
        this.formularioTutor.setSelectedIndex(0);
        int indexSelected = this.tablaTutores.getSelectedRow();
        this.idLabel.setText( String.valueOf( this.tutoresNiño.tutoresNiño[indexSelected].id_tut ) );
        this.cNombre.setText( this.tutoresNiño.tutoresNiño[indexSelected].name_tut );
        this.cApellidos.setText( this.tutoresNiño.tutoresNiño[indexSelected].surnames );
        this.cCorreo.setText( this.tutoresNiño.tutoresNiño[indexSelected].email );
        this.cTrabajo.setText( this.tutoresNiño.tutoresNiño[indexSelected].work_place );
        this.cEdad.setText( String.valueOf( this.tutoresNiño.tutoresNiño[indexSelected].age ) );
        this.cDireccion.setText( this.tutoresNiño.tutoresNiño[indexSelected].dir );
        this.cTelefono.setText( this.tutoresNiño.tutoresNiño[indexSelected].tel );
        this.cNombre.setFocusable(true);
        this.cApellidos.setFocusable(true);
        this.cCorreo.setFocusable(true);
        this.cTrabajo.setFocusable(true);
        this.cEdad.setFocusable(true);
        this.cDireccion.setFocusable(true);
        this.cTelefono.setFocusable(true);
        this.modiTutorGuard.setEnabled(true);
        this.borrarTutor.setEnabled(true);
    }//GEN-LAST:event_tablaTutoresMouseClicked

    private void modiTutorGuardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modiTutorGuardActionPerformed
        this.ventanaNiño.ventanaRegistro.coneccionBdd.modificarTutor( 
            this.idLabel.getText(),
            this.cNombre.getText(),
            this.cApellidos.getText(),
            this.cEdad.getText(),
            this.cTelefono.getText(),
            this.cDireccion.getText(),
            this.cCorreo.getText(),
            this.cTrabajo.getText()
        );
        if(this.ventanaNiño.ventanaRegistro.coneccionBdd.exitoConsulta){
            this.cargarVista(this.tutoresNiño.id_inf);
        }
    }//GEN-LAST:event_modiTutorGuardActionPerformed

    private void formularioTutorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formularioTutorMouseClicked
        if(this.tutoresNiño.numTutores>3){
            this.formularioTutor.setSelectedIndex(0);
           
        }
         System.out.println(" " +tutoresNiño.numTutores );
    }//GEN-LAST:event_formularioTutorMouseClicked

    private void aEdadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aEdadMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_aEdadMouseClicked

    private void aDireccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aDireccionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_aDireccionMouseClicked

    private void addTutorGuardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTutorGuardActionPerformed
        this.ventanaNiño.ventanaRegistro.coneccionBdd.insertarTutor( 
            this.tutoresNiño.id_inf,
            this.aNombre.getText(),
            this.aApellidos.getText(),
            this.aEdad.getText(),
            this.aTelefono.getText(),
            this.aDireccion.getText(),
            this.aCorreo.getText(),
            this.aTrabajo.getText()
        );
        if(this.ventanaNiño.ventanaRegistro.coneccionBdd.exitoConsulta){
            this.cargarVista(this.tutoresNiño.id_inf);
        }
    }//GEN-LAST:event_addTutorGuardActionPerformed

    private void borrarTutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarTutorActionPerformed
        JOptionPane.showMessageDialog(this.getFrame(), "El tutor se borrara tambien de los hermanos");
            
        int indexSelected = this.tablaTutores.getSelectedRow();
        this.idLabel.setText( String.valueOf( this.tutoresNiño.tutoresNiño[indexSelected].id_tut ) );
        this.ventanaNiño.ventanaRegistro.coneccionBdd.eliminarTutor( 
                String.valueOf(
                        this.tutoresNiño.tutoresNiño[indexSelected].id_tut
                )
        );
        
        if(this.ventanaNiño.ventanaRegistro.coneccionBdd.exitoConsulta){
            this.cargarVista(this.tutoresNiño.id_inf);
        }
    }//GEN-LAST:event_borrarTutorActionPerformed

    private void panelModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelModificarMouseClicked
       
    }//GEN-LAST:event_panelModificarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aApellidos;
    private javax.swing.JTextField aCorreo;
    private javax.swing.JTextField aDireccion;
    private javax.swing.JTextField aEdad;
    private javax.swing.JTextField aNombre;
    private javax.swing.JTextField aTelefono;
    private javax.swing.JTextField aTrabajo;
    private javax.swing.JButton addTutorGuard;
    private javax.swing.JButton borrarTutor;
    private javax.swing.JTextField cApellidos;
    private javax.swing.JTextField cCorreo;
    private javax.swing.JTextField cDireccion;
    private javax.swing.JTextField cEdad;
    private javax.swing.JTextField cNombre;
    private javax.swing.JTextField cTelefono;
    private javax.swing.JTextField cTrabajo;
    private javax.swing.JTabbedPane formularioTutor;
    private javax.swing.JLabel idLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modiTutorGuard;
    private javax.swing.JPanel panelAñadir;
    private javax.swing.JPanel panelModificar;
    private javax.swing.JButton tCerrar;
    private javax.swing.JTable tablaTutores;
    // End of variables declaration//GEN-END:variables
}
