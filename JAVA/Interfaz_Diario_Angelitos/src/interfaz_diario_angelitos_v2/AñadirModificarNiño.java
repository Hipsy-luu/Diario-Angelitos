/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_diario_angelitos_v2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import models.Infant;

/**
 *
 * @author Usuario
 */
public class AñadirModificarNiño extends javax.swing.JFrame {

    VentanaRegistro ventanaRegistro;
    AñadirModificarTutor ventanaTutor;
    
    //Niño que se muestra en la ventana
    Infant niñoActual;
    String pathFotografia = "";
    
    public AñadirModificarNiño( VentanaRegistro ventanaRegistro ) {
        initComponents();
        setLocationRelativeTo(null);
        this.ventanaTutor = new AñadirModificarTutor( this.getFrame());
        //Creamos la coneccion entre esta ventana a la de registro
        this.ventanaRegistro = ventanaRegistro;
        //Se inicializa el niño de la ventana
        this.niñoActual = new Infant();
    }
    //Variable tipo opcion que nos ayuda a saber si se añade o se modifica
    //un niño ( 0 = añadir , 1 = modificar)
    public int opc = 0;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        opcLabel = new javax.swing.JLabel();
        aId = new javax.swing.JTextField();
        aNom = new javax.swing.JTextField();
        aApell = new javax.swing.JTextField();
        aEdad = new javax.swing.JTextField();
        aFechaNac = new javax.swing.JTextField();
        aTel = new javax.swing.JTextField();
        aDireccion = new javax.swing.JTextField();
        aFechaReg = new javax.swing.JTextField();
        aAlergias = new javax.swing.JTextField();
        aServicoMedico = new javax.swing.JTextField();
        aNumeroServicio = new javax.swing.JTextField();
        aTutor = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        aGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        aFoto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        fotografia = new javax.swing.JLabel();
        aCancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("Diario Angelitos");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diary (1).png"))); // NOI18N

        opcLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        opcLabel.setText("[Añadir niño]");

        aId.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aId.setForeground(new java.awt.Color(102, 102, 102));
        aId.setText("ID");
        aId.setFocusable(false);

        aNom.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aNom.setForeground(new java.awt.Color(102, 102, 102));
        aNom.setText("Nombre...");
        aNom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aNomMouseClicked(evt);
            }
        });

        aApell.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aApell.setForeground(new java.awt.Color(102, 102, 102));
        aApell.setText("Apellido...");
        aApell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aApellMouseClicked(evt);
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

        aFechaNac.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aFechaNac.setForeground(new java.awt.Color(102, 102, 102));
        aFechaNac.setText("Fecha de Nacimiento...");
        aFechaNac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aFechaNacMouseClicked(evt);
            }
        });

        aTel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aTel.setForeground(new java.awt.Color(102, 102, 102));
        aTel.setText("Telefono...");
        aTel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aTelMouseClicked(evt);
            }
        });

        aDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aDireccion.setForeground(new java.awt.Color(102, 102, 102));
        aDireccion.setText("Direccion...");
        aDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aDireccionMouseClicked(evt);
            }
        });

        aFechaReg.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aFechaReg.setForeground(new java.awt.Color(102, 102, 102));
        aFechaReg.setText("Fecha de Registro...");
        aFechaReg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aFechaRegMouseClicked(evt);
            }
        });

        aAlergias.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aAlergias.setForeground(new java.awt.Color(102, 102, 102));
        aAlergias.setText("Alergias...");
        aAlergias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aAlergiasMouseClicked(evt);
            }
        });

        aServicoMedico.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aServicoMedico.setForeground(new java.awt.Color(102, 102, 102));
        aServicoMedico.setText("Servicio Medico...");
        aServicoMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aServicoMedicoMouseClicked(evt);
            }
        });

        aNumeroServicio.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aNumeroServicio.setForeground(new java.awt.Color(102, 102, 102));
        aNumeroServicio.setText("Numero Servicio Medico...");
        aNumeroServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aNumeroServicioMouseClicked(evt);
            }
        });

        aTutor.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        aTutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-familia-hombre-mujer-40.png"))); // NOI18N
        aTutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aTutorActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel6.setText("Añadir Tutor");

        aGuardar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        aGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-guardar-cerrar-40.png"))); // NOI18N
        aGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aGuardarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel5.setText("Listo");

        aFoto.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-cámara-slr-40.png"))); // NOI18N
        aFoto.setText("Foto");

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        fotografia.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fotografia, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fotografia, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        aCancelar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        aCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-guardar-cerrar-40.png"))); // NOI18N
        aCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aCancelarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel7.setText("Cancelar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(opcLabel)))
                                .addGap(0, 269, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(aDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aFechaReg, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aServicoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aNumeroServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(79, 79, 79)
                                        .addComponent(aFoto))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(aTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)))
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(aGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel5)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel7))
                                            .addComponent(aCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aFechaNac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(aNom, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aId, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aApell, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(aTel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(opcLabel)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aApell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(aFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aFechaReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(aAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aServicoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aNumeroServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aTutor)
                            .addComponent(aGuardar)
                            .addComponent(aCancelar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jLabel7))))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private AñadirModificarNiño getFrame(){
        return this;
    }
    
    
    
    public void refrescarNiño() throws IOException{
        this.actualizarTitulo();
        File f = new File(niñoActual.image_path);
        BufferedImage folderImage = ImageIO.read(f);
        if(this.opc == 0){
            this.aId.setText( String.valueOf( niñoActual.id_inf ) );
            this.aNom.setText( "Nombre...");
            this.aApell.setText( "Apellido..." );
            this.aEdad.setText( String.valueOf( niñoActual.age ) );
            this.aFechaNac.setText( "Formato de la Fecha : yyyy-mm-dd" );
            this.aTel.setText( "Telefono..." );
            this.aDireccion.setText( "Direccion..." );
            this.aFechaReg.setText( "Formato de la Fecha : yyyy-mm-dd" );
            this.aAlergias.setText( "Alergias..." );
            this.aServicoMedico.setText( "Servicio Medico..." );
            this.aNumeroServicio.setText( "Num. Servicio Med..." );
            this.fotografia.setIcon(new javax.swing.ImageIcon(folderImage));
            this.pathFotografia = niñoActual.image_path;;
        }else if(this.opc == 1){
            this.aId.setText( String.valueOf( niñoActual.id_inf ) );
            this.aNom.setText( niñoActual.name_inf );
            this.aApell.setText( niñoActual.surnames );
            this.aEdad.setText( String.valueOf( niñoActual.age ) );
            this.aFechaNac.setText( niñoActual.birth_day );
            this.aTel.setText( niñoActual.tel );
            this.aDireccion.setText( niñoActual.dir );
            this.aFechaReg.setText( niñoActual.reg_date );
            this.aAlergias.setText( niñoActual.allergies );
            this.aServicoMedico.setText( niñoActual.medical_service );
            this.aNumeroServicio.setText( niñoActual.num_service );
            this.fotografia.setIcon(new javax.swing.ImageIcon(folderImage));
            this.pathFotografia = niñoActual.image_path;;
        }
        
        //this.fotografia.setIcon( Icon() );
    }
    
    private void aGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aGuardarActionPerformed
        //La opc 0  es para insertar el niño nuevo en la tabla INFANT
        if(this.opc == 0){
            this.ventanaRegistro.coneccionBdd.añadirNiño(
                //Integer.parseInt( this.aId.getText() ), 
                //Integer.parseInt( this.aEdad.getText()) ,
                this.aId.getText() , this.aEdad.getText(),
                this.aNom.getText(),this.aApell.getText(), 
                this.aFechaNac.getText(),this.aDireccion.getText(), 
                this.aTel.getText(),this.aFechaReg.getText(),
                this.pathFotografia,this.aAlergias.getText(),
                this.aServicoMedico.getText(),this.aNumeroServicio.getText()
            );
            //La opcion 1 es para modificarlo
        }else if(this.opc == 1){
            this.ventanaRegistro.coneccionBdd.modificarNiño(
                this.aId.getText() , this.aEdad.getText(),
                this.aNom.getText(),this.aApell.getText(), 
                this.aFechaNac.getText(),this.aDireccion.getText(), 
                this.aTel.getText(),this.aFechaReg.getText(),
                this.pathFotografia,this.aAlergias.getText(),
                this.aServicoMedico.getText(),this.aNumeroServicio.getText()
            );
        }
        if(this.ventanaRegistro.coneccionBdd.exitoConsulta){
            //Se actualiza la vista de la tabla para que cargue el nuevo Infante añadido
            this.ventanaRegistro.actualizarTabla();
            //Hacemos la vista del registro visible
            this.ventanaRegistro.setVisible(true);
            //Ocultamos la vista del registro
            //setVisible(false);
            dispose();
        }
        
    }//GEN-LAST:event_aGuardarActionPerformed

    public void actualizarTitulo(){
        if(this.opc==0){
            this.opcLabel.setText("[Añadir Niño]");
        }else if(this.opc==1){
            this.opcLabel.setText("[Modificar Niño]");
        }
    }
    private void aApellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aApellMouseClicked
        if(this.opc==0){aApell.setText("");}
    }//GEN-LAST:event_aApellMouseClicked

    private void aEdadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aEdadMouseClicked
       if(this.opc==0){ aEdad.setText(""); }
    }//GEN-LAST:event_aEdadMouseClicked

    private void aDireccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aDireccionMouseClicked
        if(this.opc==0){aDireccion.setText("");}
    }//GEN-LAST:event_aDireccionMouseClicked

    private void aTelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aTelMouseClicked
        if(this.opc==0){aTel.setText(""); } 
    }//GEN-LAST:event_aTelMouseClicked

    private void aTutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aTutorActionPerformed
        this.ventanaTutor.cargarVista( String.valueOf(this.niñoActual.id_inf) );
        this.ventanaTutor.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_aTutorActionPerformed

    private void aNomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aNomMouseClicked
        if(this.opc==0){
          aNom.setText("");
        }
    }//GEN-LAST:event_aNomMouseClicked

    private void aFechaRegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aFechaRegMouseClicked
        if(this.opc==0){ this.aFechaReg.setText("");}
    }//GEN-LAST:event_aFechaRegMouseClicked

    private void aFechaNacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aFechaNacMouseClicked
        if(this.opc==0){ this.aFechaNac.setText("");}
    }//GEN-LAST:event_aFechaNacMouseClicked

    private void aAlergiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aAlergiasMouseClicked
        if(this.opc==0){ this.aAlergias.setText("");}
    }//GEN-LAST:event_aAlergiasMouseClicked

    private void aServicoMedicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aServicoMedicoMouseClicked
        if(this.opc==0){ this.aServicoMedico.setText(""); }
    }//GEN-LAST:event_aServicoMedicoMouseClicked

    private void aNumeroServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aNumeroServicioMouseClicked
        if(this.opc==0){ this.aNumeroServicio.setText(""); }
    }//GEN-LAST:event_aNumeroServicioMouseClicked

    private void aCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aCancelarActionPerformed
        this.ventanaRegistro.setVisible(true);
        //Ocultamos la vista del niño
        setVisible(false);
    }//GEN-LAST:event_aCancelarActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aAlergias;
    private javax.swing.JTextField aApell;
    private javax.swing.JButton aCancelar;
    private javax.swing.JTextField aDireccion;
    private javax.swing.JTextField aEdad;
    private javax.swing.JTextField aFechaNac;
    private javax.swing.JTextField aFechaReg;
    private javax.swing.JButton aFoto;
    private javax.swing.JButton aGuardar;
    private javax.swing.JTextField aId;
    private javax.swing.JTextField aNom;
    private javax.swing.JTextField aNumeroServicio;
    private javax.swing.JTextField aServicoMedico;
    private javax.swing.JTextField aTel;
    private javax.swing.JButton aTutor;
    private javax.swing.JLabel fotografia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel opcLabel;
    // End of variables declaration//GEN-END:variables
}
