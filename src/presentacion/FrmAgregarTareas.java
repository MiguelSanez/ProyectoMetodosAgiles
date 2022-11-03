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

/**
 *
 * @author USER
 */
public class FrmAgregarTareas extends javax.swing.JFrame {

    /**
     * Creates new form FrmTareas
     */
    public FrmAgregarTareas() {
        initComponents();
        this.lblCaracteres.setText("100"); 
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

        jPanel1 = new javax.swing.JPanel();
        lblDatosTarea = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        lblCaracteres = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        imagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDatosTarea.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDatosTarea.setText("Datos de la tarea");
        lblDatosTarea.setToolTipText("");
        jPanel1.add(lblDatosTarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, 20));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitulo.setText("Descripcion");
        jPanel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));
        jPanel1.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 310, -1));

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDescripcion.setText("Titulo");
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

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, -1, -1));

        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/MenuTareas.png"))); // NOI18N
        imagen.setText("jLabel1");
        jPanel1.add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(-170, 0, 570, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(txtTitulo.getText().length()>0&&txtDescripcion.getText().length()>0){
            Tarea tarea=new Tarea(txtTitulo.getText().toLowerCase(),txtDescripcion.getText().toLowerCase(),Estado.PENDIENTE);
            TareaDAO tareaDao = new TareaDAO();
            if(tareaDao.repetidoTitulo(txtTitulo.getText().toLowerCase())){
                JOptionPane.showMessageDialog(null, "Titulo repetido, porfavor coloque otro");
            }else if(tareaDao.repetidoDescripcion(txtDescripcion.getText().toLowerCase())){
                JOptionPane.showMessageDialog(null, "Descripcion repetida, porfavor coloque otra");
            }else{
                try {
                    tareaDao.insertar(tarea);
                    System.out.println("aqui aun no se bloquea");
                    frmMenu.cargarTareasPendientes();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error "+ex.getMessage());
                    Logger.getLogger(FrmAgregarTareas.class.getName()).log(Level.SEVERE, null, ex);
                }
               if(tareaDao.señal){
               JOptionPane.showMessageDialog(null, "Tarea guardada con exito");
               }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Porfavor coloque el titulo y la descripcion");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void lblCaracteresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCaracteresKeyPressed
       
    }//GEN-LAST:event_lblCaracteresKeyPressed

    public void jFrameMenu(FrmMenuTareas j){
        this.frmMenu= j;
    } 
    private void txtDescripcionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescripcionCaretUpdate
        this.lblCaracteres.setText(String.valueOf(100-txtDescripcion.getText().length()));
        int tamaño=100-txtDescripcion.getText().length();
        if(tamaño<0){ 
            this.btnAgregar.setEnabled(false);
        }if(tamaño>=0){ 
            this.btnAgregar.setEnabled(true);
        }
    }//GEN-LAST:event_txtDescripcionCaretUpdate

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
            java.util.logging.Logger.getLogger(FrmAgregarTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarTareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAgregarTareas().setVisible(true);                
            }
        });
    }

    //this.lblCaracteres.setText(String.valueOf(txtDescripcion.getText().length()));
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
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