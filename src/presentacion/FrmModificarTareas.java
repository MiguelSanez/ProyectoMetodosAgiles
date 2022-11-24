/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import DAOS.TareaDAO;
import Objeto_negocio.Estado;
import Objeto_negocio.Tarea;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FrmModificarTareas extends javax.swing.JFrame {

    /**
     * Creates new form FrmTareas
     */
    private Tarea tareaModificar;
    private String nombreTareaAntigua;
    private String descripcionTareaAntigua;
    
    
    public FrmModificarTareas(Tarea tareaModificacion) {
        initComponents();
        setLocationRelativeTo(null);
        this.lblCaracteres.setText("100"); 
        this.tareaModificar=tareaModificacion;
        this.txtTitulo.setText(tareaModificar.getNombre());
        nombreTareaAntigua=tareaModificar.getNombre();
        this.txtDescripcion.setText(tareaModificar.getDescripcion());
        descripcionTareaAntigua=tareaModificar.getDescripcion();
    }

    FrmMenuTareas frmMenu;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        lblDatosTarea = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        lblCaracteres = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        imagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDatosTarea.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDatosTarea.setText("Datos de la tarea");
        lblDatosTarea.setToolTipText("");
        jPanel1.add(lblDatosTarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, 20));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo.setText("Descripción");
        jPanel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });
        jPanel1.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 310, -1));

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDescripcion.setText("Título");
        jPanel1.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDescripcionCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 350, 150));

        jLabel1.setText("Cantidad de caracteres restantes:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, 20));

        lblCaracteres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCaracteresKeyPressed(evt);
            }
        });
        jPanel1.add(lblCaracteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 30, 20));

        btnCancelar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 100, 30));

        btnModificar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 100, 30));

        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tareas.png"))); // NOI18N
        imagen.setText("jLabel1");
        jPanel1.add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(-170, 0, 570, 420));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtTitulo.getText().length()>0&&txtDescripcion.getText().length()>0){
            Tarea tarea=new Tarea(txtTitulo.getText().toLowerCase().trim(),txtDescripcion.getText().toLowerCase().trim(),Estado.PENDIENTE);
            TareaDAO tareaDao = new TareaDAO();
            tareaDao.llenarLista();
            tareaDao.modificacionListas(nombreTareaAntigua, descripcionTareaAntigua);
            if(tareaDao.repetidoTituloModificacion(txtTitulo.getText().toLowerCase().trim())){
                JOptionPane.showMessageDialog(null, "titulo repetido, por favor coloque otro");
                txtTitulo.setText(nombreTareaAntigua);
            }else if(tareaDao.repetidoDescripcionModificacion(txtDescripcion.getText().toLowerCase().trim())){
                JOptionPane.showMessageDialog(null, "descripcion repetida, por favor coloque otra");
                txtTitulo.setText(descripcionTareaAntigua);
            }else{
                try {
                    tarea.setId(tareaModificar.getId());
                    tareaDao.actualizarParaFrmModificacion(tarea);
                    frmMenu.cargarTareasPendientes();
                    frmMenu.cargarTareasEnProgreso();
                    JOptionPane.showMessageDialog(this, "Se actualizo la tarea", "Operacion realizada", JOptionPane.INFORMATION_MESSAGE); 
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error "+ex.getMessage());
                    Logger.getLogger(FrmModificarTareas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor coloque el título y la descripcion");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void lblCaracteresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCaracteresKeyPressed
       
    }//GEN-LAST:event_lblCaracteresKeyPressed

    public void jFrameMenu(FrmMenuTareas j){
        this.frmMenu= j;
    } 
    
    
    public void tareaAModificar(Tarea tarea){
        this.tareaModificar= tarea;
    } 
    
    private void txtDescripcionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescripcionCaretUpdate
        this.lblCaracteres.setText(String.valueOf(100-txtDescripcion.getText().length()));
        int tamaño=100-txtDescripcion.getText().length();
        if(tamaño<0){ 
            this.btnModificar.setEnabled(false);
        }if(tamaño>=0){ 
            this.btnModificar.setEnabled(true);
        }
    }//GEN-LAST:event_txtDescripcionCaretUpdate

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
         this.txtTitulo.setText(frmMenu.tareaModificacion.getNombre());
    }//GEN-LAST:event_txtTituloActionPerformed


    //this.lblCaracteres.setText(String.valueOf(txtDescripcion.getText().length()));
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCaracteres;
    private javax.swing.JLabel lblDatosTarea;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables

}