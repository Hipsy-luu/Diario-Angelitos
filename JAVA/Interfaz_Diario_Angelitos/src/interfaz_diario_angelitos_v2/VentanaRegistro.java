/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_diario_angelitos_v2;

import algoritmosApoyo.TrieAutocompletar;
import appis.ConexionBaseDatos;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Infant;

/**
 *
 * @author Usuario
 */
public class VentanaRegistro extends javax.swing.JFrame {

    JFrame ventanaPrincipal;
    //Ventana para añadir y modificar un niño
    AñadirModificarNiño ventanaNiño;
    //El modelo es la conexion entre la tabla de la interfaz y la 
    DefaultTableModel modeloTablaRegistro;
    Object [] fila;
    //Conexion con la base de datos que nos puede hacer las consultas
    ConexionBaseDatos coneccionBdd;
    
    public VentanaRegistro( JFrame ventanaPrincipal ) {
        initComponents();
        setLocationRelativeTo(null);
        //Se crea la coneccion entre la ventana principal y esta
        this.ventanaPrincipal = ventanaPrincipal;
        //Se crea una nueva ventana que contiene la ventana del niño
        this.ventanaNiño= new AñadirModificarNiño( getFrame() );
        
        //Variable para añadir los elementos a la tabla
        fila=new Object[5];
        //Conectamos la tabla de la interfaz con la tabla que tenemos 
        //en el modelo
        modeloTablaRegistro = (DefaultTableModel) jTablaRegistro.getModel();
        jTablaRegistro.setModel(modeloTablaRegistro);
        
        
        //Coneccion a la base de datos
        this.coneccionBdd = new ConexionBaseDatos(getFrame());
        
        //Se actualiza la tabla siempre que se haga algo en la tabla de Infantes
        this.actualizarTabla();
    }

    //Funciones que nos ayudaran con la interfaz
    //Cada que se añade un niño se actualiza la tabla
    public void actualizarTabla(){
        //Borramos el primer campo hasta que este vacia la tabla
        while(0<modeloTablaRegistro.getRowCount()){
            modeloTablaRegistro.removeRow(0);
        }
        for(int x=0;x<this.coneccionBdd.cantRegistros;x++){
            fila[0]=this.coneccionBdd.registroActual[x].id_inf;
            fila[1]=this.coneccionBdd.registroActual[x].name_inf + " " +  this.coneccionBdd.registroActual[x].surnames;
            fila[2]=this.coneccionBdd.registroActual[x].age;
            fila[3]=this.coneccionBdd.registroActual[x].tel;
            fila[4]=this.coneccionBdd.registroActual[x].reg_date;
            modeloTablaRegistro.addRow(fila); 
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnRegistroCompleto = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        close = new javax.swing.JButton();
        rBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaRegistro = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnRegistroCompleto.setText("Registro Completo");
        btnRegistroCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroCompletoActionPerformed(evt);
            }
        });

        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-editar-45.png"))); // NOI18N
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-niños-45.png"))); // NOI18N
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel2.setText("Editar");

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-eliminar-45.png"))); // NOI18N
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel3.setText("Borrar");

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel1.setText("Añadir niño");

        close.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        close.setText("Cerrar");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        rBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        rBuscar.setForeground(new java.awt.Color(102, 102, 102));
        rBuscar.setText("Buscar...");
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
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
        jScrollPane1.setViewportView(jTablaRegistro);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel4.setText("Registro");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(close))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(rBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnRegistroCompleto)))
                                .addGap(0, 10, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(142, 142, 142)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(25, 25, 25))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(close)
                        .addGap(5, 5, 5))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistroCompleto))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add)
                            .addComponent(edit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(delete))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Nuevo método para conseguir el JFrame de la Ventana de Registro
    private VentanaRegistro getFrame(){
        return this;
    }
    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        //Hacemos visible la principal
        this.ventanaPrincipal.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_closeActionPerformed
    //Funcion para abrir la ventana y ponerla en modo de añadir niño 
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        //Se indica que tipo de operacion debe hacer la ventana (0 para nuevo niño)
        this.ventanaNiño.opc = 0;
        //Se carga un nuevo niño en la interfaz
        this.ventanaNiño.niñoActual = new Infant();
        this.ventanaNiño.niñoActual.id_inf = this.coneccionBdd.sig_id_inf+1;
        try {
            //Se refresca la interfaz
            this.ventanaNiño.refrescarNiño();
        } catch (IOException ex) {
            Logger.getLogger(VentanaRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se hace visible la ventana del niño
        this.ventanaNiño.setVisible(true);
        //Ocultamos esta ventana
        setVisible(false);
    }//GEN-LAST:event_addActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        //Se indica que tipo de operacion debe hacer la ventana (0 para nuevo niño 1 para modificar)
        this.ventanaNiño.opc = 1;
        try{
            int indexSelected = (int) this.jTablaRegistro.getValueAt(this.jTablaRegistro.getSelectedRow(),0);
            //Se cargan los datos del niño seleccionado
           this.ventanaNiño.niñoActual = this.coneccionBdd.registroActual[indexSelected];
           try {
               //Se actualiza en la interfaz el niño cargado en la ventana
               this.ventanaNiño.refrescarNiño();
           } catch (IOException ex) {
               Logger.getLogger(VentanaRegistro.class.getName()).log(Level.SEVERE, null, ex);
           }
           //Se hace visible la ventana del niño
           this.ventanaNiño.setVisible(true);
           //Ocultamos esta ventana
           setVisible(false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this.getFrame(), "Seleccione un niño por favor");
        }
    }//GEN-LAST:event_editActionPerformed

    private void rBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rBuscarMouseClicked
    rBuscar.setText("");
    }//GEN-LAST:event_rBuscarMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        try{
            //Se saca el id del niño seleccionado y se elimina
            int indexSelected = (int) this.jTablaRegistro.getValueAt(this.jTablaRegistro.getSelectedRow(),0);
            this.coneccionBdd.eliminarNiño( Integer.toString(indexSelected) );
           //Se actualiza la vista de la tabla
            this.actualizarTabla();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this.getFrame(), "Seleccione un niño por favor");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void rBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rBuscarKeyTyped
        if(this.rBuscar.getText()!=""){
            if(this.coneccionBdd.cantRegistros < 1 ){
                JOptionPane.showMessageDialog(null, "Registro de niños Vacio");
                this.rBuscar.setText("");
            }else{
                this.coneccionBdd.dicionarioNombresNiños.buscarCoincidencias( this.rBuscar.getText() );
                //Borramos el primer campo hasta que este vacia la tabla
                while(0<modeloTablaRegistro.getRowCount()){
                    modeloTablaRegistro.removeRow(0);
                }
                for(int x=0;x<this.coneccionBdd.dicionarioNombresNiños.cont;x++){
                    fila[0]=this.coneccionBdd.registroActual[
                            this.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                            ].id_inf;
                    fila[1]=this.coneccionBdd.registroActual[
                            this.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                            ].name_inf + " " +  this.coneccionBdd.registroActual[x].surnames;
                    fila[2]=this.coneccionBdd.registroActual[
                            this.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                            ].age;
                    fila[3]=this.coneccionBdd.registroActual[
                            this.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                            ].tel;
                    fila[4]=this.coneccionBdd.registroActual[
                            this.coneccionBdd.dicionarioNombresNiños.coincidencias[x].index
                            ].reg_date;
                    modeloTablaRegistro.addRow(fila); 
                }
            }
        }else if(this.rBuscar.getText()==""){
            this.actualizarTabla();
        }
    }//GEN-LAST:event_rBuscarKeyTyped

    private void btnRegistroCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroCompletoActionPerformed
        this.actualizarTabla();
    }//GEN-LAST:event_btnRegistroCompletoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton btnRegistroCompleto;
    private javax.swing.JButton close;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaRegistro;
    private javax.swing.JTextField rBuscar;
    // End of variables declaration//GEN-END:variables
}
