

package presentacion;

import DAOS.TareaDAO;
import Objeto_negocio.Estado;
import Objeto_negocio.Tarea;
import DAOS.SClip;
import java.awt.Color;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class FrmMenuTareas extends javax.swing.JFrame {

    /** Creates new form FrmMenuTareas */
    
    private TareaDAO tareaDao;
    private ArrayList<Tarea> tareasPendientes;
    private ArrayList<Tarea> tareasEnProgreso;
    private ArrayList<Tarea> tareasTerminadas;
    private int minutos;
    private int segundos;
    private boolean descanso;
    private boolean mensaje;
    private int numDescansos;
    private Timer timer;
    private boolean ignoreDrag = false;
    public DefaultTableModel modeloTablaEnProgreso;
    boolean elementoSeleccionado=false;
    int indexEnProgeso;
    DefaultTableModel modelo;
    public Tarea tareaModificacion;
    private SClip sonido;
    
    public FrmMenuTareas() {
        initComponents();
        setLocationRelativeTo(null);
        this.timer= new Timer(1, acciones);
        this.tareaDao=new TareaDAO();
        this.minutos=0;
        this.segundos=0;
        this.descanso=false;
        this.mensaje=false;
        this.numDescansos=0;
        this.sonido = new SClip("sonido/notificacion.wav");
        this.cargarTareasPendientes();
        this.cargarTareasEnProgreso();
        this.cargarTareasTerminadas();
        if(this.tareasEnProgreso.isEmpty()) btnIniciarReanudar.setEnabled(false);
        btnPausar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    private int opciones;
    private int opciones2;
    private int auxOpcion=-1;
    private String [] botones={"Sí","No"};
    private String [] botones2={"Confirmar","Omitir"};
    private ActionListener acciones = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(!sonido.isPlaying()){
                if(segundos != 0 || minutos != 0) {
                    if(segundos==1 && minutos==0 && !descanso){
                        if(auxOpcion==0){
                            descanso=true;
                        } else if(auxOpcion==1){
                            numDescansos++;
                            System.out.println(numDescansos);
                            if(numDescansos==4) numDescansos=0;
                        }
                        segundos--;
                        sonido.loop();
                    } else if(segundos==1 && minutos==0 && descanso){
                        numDescansos++;
                        System.out.println(numDescansos);
                        if(numDescansos==4) numDescansos=0;
                        descanso=false;
                        segundos--;
                        sonido.loop();
                    } else if(segundos!=0){
                        segundos--;

                    } else if(minutos!=0){
                        minutos--;
                        segundos=59;
                    }
                    actualizarTemporizador();
                    if(segundos==5 && minutos==0){
                        if(descanso){
                            JOptionPane.showMessageDialog(null, "El tiempo de descanso está por terminar", "Tiempo de descanso finaliza pronto", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            opciones= JOptionPane.showOptionDialog(null, "El tiempo de actividades está por terminar", "Tiempo de actividades finaliza pronto", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,botones2,botones2[0]);
                            if(opciones==1){
                                opciones2= JOptionPane.showOptionDialog(null, "¿Deseas omitir?", "Confirmación omición", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,botones,botones[0]);
                                if(opciones2==0){
                                    auxOpcion=1;
                                }else{
                                    auxOpcion=0;
                                }
                            }else{
                                auxOpcion= opciones;
                            }
                        }
                        mensaje= true;
                    }
                }
                else if(mensaje){

                    establecerTiempo();
                }
            }
        }
        
    };
    
    private void actualizarTemporizador() {
        String tiempo = (minutos<=9?"0":"")+minutos+":"+(segundos<=9?"0":"")+segundos;
        lblTemporizador.setText(tiempo);
        lblTemporizador.repaint();
        lblNumDescansos.setText(numDescansos+"");
        lblNumDescansos.repaint();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTemporizador = new javax.swing.JLabel();
        btnIniciarReanudar = new javax.swing.JButton();
        lblNumDescansos = new javax.swing.JLabel();
        lblInformacionDescansos = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnPausar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTerminado = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbEnProgreso = new javax.swing.JTable();
        btnArribaEnProgreso = new javax.swing.JButton();
        btnAbajoEnProgreso = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPendiente = new javax.swing.JTable();
        btnRegresarPendientes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSonido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(28, 86, 164));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 1140, 53));

        jPanel1.setBackground(new java.awt.Color(88, 116, 241));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 620, 10, 730);
        jPanel1.add(jLabel2, gridBagConstraints);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTemporizador.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lblTemporizador.setText("00:00");
        jPanel2.add(lblTemporizador, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 170, -1, -1));

        btnIniciarReanudar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnIniciarReanudar.setText("Iniciar");
        btnIniciarReanudar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarReanudarActionPerformed(evt);
            }
        });
        jPanel2.add(btnIniciarReanudar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 240, 130, 30));

        lblNumDescansos.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblNumDescansos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumDescansos.setText("0");
        jPanel2.add(lblNumDescansos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, 30, -1));

        lblInformacionDescansos.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblInformacionDescansos.setText("Número de descansos");
        jPanel2.add(lblInformacionDescansos, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 50, 130, -1));

        btnAgregar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAgregar.setText("Agregar tarea");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 140, 130, 30));

        btnPausar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnPausar.setText("Pausar");
        btnPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPausar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 280, 130, 30));

        btnCancelar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 320, 130, 30));

        Salir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jPanel2.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 410, 130, 30));

        tbTerminado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripción", "Fecha", "Hora"
            }
        ));
        jScrollPane3.setViewportView(tbTerminado);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 340, 430));

        tbEnProgreso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripción"
            }
        ));
        tbEnProgreso.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tbEnProgresoMouseDragged(evt);
            }
        });
        tbEnProgreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEnProgresoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbEnProgreso);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 260, 430));

        btnArribaEnProgreso.setText("Arriba");
        btnArribaEnProgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArribaEnProgresoActionPerformed(evt);
            }
        });
        jPanel2.add(btnArribaEnProgreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 70, 40));

        btnAbajoEnProgreso.setText("Abajo");
        btnAbajoEnProgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbajoEnProgresoActionPerformed(evt);
            }
        });
        jPanel2.add(btnAbajoEnProgreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, 70, 40));

        tbPendiente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripción"
            }
        ));
        tbPendiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPendienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPendiente);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 430));

        btnRegresarPendientes.setText("Regresar a tareas pendientes");
        btnRegresarPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarPendientesActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegresarPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, -1, -1));

        jLabel1.setText("En caso de no terminar seleccione la tarea, no confirme y seleccione el boton");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 424, 30));

        btnModificar.setText("Modificar descripcion");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 470, -1, -1));

        btnSonido.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSonido.setText("Pausar sonido");
        btnSonido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSonidoActionPerformed(evt);
            }
        });
        jPanel2.add(btnSonido, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, -1, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1140, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
     FrmAgregarTareas agregar=new FrmAgregarTareas();
     agregar.jFrameMenu(this);
     agregar.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tbPendienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPendienteMouseClicked
       try{
           int indiceFelaSeleccionada = this.tbPendiente.getSelectedRow(); 
           Tarea tarea= this.tareasPendientes.get(indiceFelaSeleccionada);
           tarea.setEstado(Estado.EN_PROGRESO);
           tareaDao.actualizar(tarea);
           this.cargarTareasPendientes();
           this.cargarTareasEnProgreso();
        }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "No se pudieron obtener los datos de la tabla " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_tbPendienteMouseClicked

    private void tbEnProgresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEnProgresoMouseClicked
        try{
           int opcion = JOptionPane.showOptionDialog(this, "¿Desea finalizar la tarea?", "Finalizar tarea", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,botones,botones[0]);
           
            if(opcion==0){
                int indiceFelaSeleccionada = this.tbEnProgreso.getSelectedRow(); 
                Tarea tarea= this.tareasEnProgreso.get(this.econtrarElemento(indiceFelaSeleccionada));
                tarea.setEstado(Estado.TERMINADA);
                tareaDao.actualizarParaFinalizar(tarea);
                this.cargarTareasEnProgreso();
                this.cargarTareasTerminadas();
                JOptionPane.showMessageDialog(null, "Tarea finalizada con éxito", "Tarea terminada", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "No se pudieron obtener los datos de la tabla " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_tbEnProgresoMouseClicked

    private int econtrarElemento(Integer indiceFila){
        System.out.println("aqui entra 2do");
        for (int i = 0; i < tareasEnProgreso.size(); i++) {
            if(tareasEnProgreso.get(i).getNombre().equals(modeloTablaEnProgreso.getValueAt(indiceFila, 0))){
                return i;
            }
        }
        return 0;
    }
    private void tbEnProgresoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEnProgresoMouseDragged
//        tbEnProgreso.setDragEnabled(true);
//        if (!ignoreDrag) {
//            // We only call this method when a user has NOT clicked on a cell that is already selected 
//            super.processMouseMotionEvent(evt);                        
//        }else{
//
//            // Start a table row drag operation
//            tbEnProgreso.getTransferHandler().exportAsDrag(tbEnProgreso, evt, DnDConstants.ACTION_COPY);
//
//        }
    }//GEN-LAST:event_tbEnProgresoMouseDragged

    private void btnIniciarReanudarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarReanudarActionPerformed
        empezarTemporizador();
        btnIniciarReanudar.setEnabled(false);
        btnIniciarReanudar.setText("Reanudar");
        btnPausar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }//GEN-LAST:event_btnIniciarReanudarActionPerformed

    private void btnPausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausarActionPerformed
        timer.stop();
        JOptionPane.showMessageDialog(null, "El temporizador está en pausa", "El temporizador está en pausa", JOptionPane.INFORMATION_MESSAGE);
        btnIniciarReanudar.setEnabled(true);
        btnPausar.setEnabled(false);
    }//GEN-LAST:event_btnPausarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int opcion = JOptionPane.showOptionDialog(this, "¿Desea restablecer el temporizador?", "Restablecer temporizador", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,botones,botones[0]);
           
        if(opcion==0){
            if(timer.isRunning()) 
            {
                timer.stop();
                btnIniciarReanudar.setEnabled(true);
            }
            btnIniciarReanudar.setText("Iniciar");
            btnPausar.setEnabled(false);
            btnCancelar.setEnabled(false);
            minutos=0; segundos=0;
            actualizarTemporizador();
         }
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        if(timer.isRunning()) 
        {
            timer.stop();
        }
        this.dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void btnRegresarPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarPendientesActionPerformed
           try {
            int indiceFelaSeleccionada = this.tbEnProgreso.getSelectedRow(); 
          if(indiceFelaSeleccionada==-1){  
           JOptionPane.showMessageDialog(this, "Selecciona la tarea que se desee regresar", "error", JOptionPane.INFORMATION_MESSAGE);
           }else{ 
            System.out.println("aqui entra 1ero");
            Tarea tarea= this.tareasEnProgreso.get(this.econtrarElemento(indiceFelaSeleccionada));
            tarea.setEstado(Estado.PENDIENTE);
            tareaDao.actualizar(tarea);
            this.cargarTareasEnProgreso();
            this.cargarTareasPendientes();
            JOptionPane.showMessageDialog(null, "Tarea regresada con éxito", "Tarea terminada", JOptionPane.INFORMATION_MESSAGE);
            } 
       } catch (Exception ex) {
            Logger.getLogger(FrmMenuTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegresarPendientesActionPerformed

    private void btnArribaEnProgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArribaEnProgresoActionPerformed
     int indiceFelaSeleccionada = this.tbEnProgreso.getSelectedRow(); 
     if(indiceFelaSeleccionada==-1){  
     JOptionPane.showMessageDialog(this, "Selecciona la tarea que desee subir", "Seleccione tarea", JOptionPane.INFORMATION_MESSAGE);
     }else{ 
        if(elementoSeleccionado==false){
            modelo=(DefaultTableModel)tbEnProgreso.getModel();
            elementoSeleccionado=true;
        }
        indexEnProgeso=tbEnProgreso.getSelectedRow();
        if(indexEnProgeso>0){
            modelo.moveRow(indexEnProgeso, indexEnProgeso, indexEnProgeso - 1);
            tbEnProgreso.setRowSelectionInterval(indexEnProgeso - 1, indexEnProgeso - 1);
            tbEnProgreso.setSelectionBackground(Color.DARK_GRAY);
        } 
      }
    }//GEN-LAST:event_btnArribaEnProgresoActionPerformed

    private void btnAbajoEnProgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbajoEnProgresoActionPerformed
     int indiceFelaSeleccionada = this.tbEnProgreso.getSelectedRow(); 
     if(indiceFelaSeleccionada==-1){  
     JOptionPane.showMessageDialog(this, "Selecciona la tarea que desee bajar", "Seleccione tarea", JOptionPane.INFORMATION_MESSAGE);
     }else{  
        if(elementoSeleccionado==false){
            modelo=(DefaultTableModel)tbEnProgreso.getModel();
            elementoSeleccionado=true;
        }
        indexEnProgeso=tbEnProgreso.getSelectedRow();
        if(indexEnProgeso<modelo.getRowCount() - 1){
            modelo.moveRow(indexEnProgeso, indexEnProgeso, indexEnProgeso + 1);
            tbEnProgreso.setRowSelectionInterval(indexEnProgeso + 1, indexEnProgeso + 1);
            tbEnProgreso.setSelectionBackground(Color.DARK_GRAY);
        }  
      }
    }//GEN-LAST:event_btnAbajoEnProgresoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       this.eliminarTotal();
    }//GEN-LAST:event_btnEliminarActionPerformed

      private void eliminarTotal(){
        int indiceFelaSeleccionadaPendientes = this.tbPendiente.getSelectedRow();
        int indiceFelaSeleccionadaEnProgreso = this.tbEnProgreso.getSelectedRow();
         if(indiceFelaSeleccionadaEnProgreso==-1 && indiceFelaSeleccionadaPendientes==-1){  
          JOptionPane.showMessageDialog(this, "Selecciona la tarea que se desee eliminar", "error", JOptionPane.INFORMATION_MESSAGE);
         }else if(indiceFelaSeleccionadaPendientes>-1){
             this.eliminarTareaPendientes();
         }else if(indiceFelaSeleccionadaEnProgreso>-1){
             this.eliminarTareaEnProgreso();
         }
    }
    
    private void eliminarTareaPendientes(){
        int indiceFelaSeleccionada = this.tbPendiente.getSelectedRow();
       if(indiceFelaSeleccionada==-1){  
           JOptionPane.showMessageDialog(this, "Selecciona la tarea que se desee eliminar", "error", JOptionPane.INFORMATION_MESSAGE);
       }else{ 
        int OpcionSeleccionada=JOptionPane.showConfirmDialog(this, "Desea eliminar el elemento seleccionado","Confirmacion",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(OpcionSeleccionada==JOptionPane.YES_OPTION){
            Integer id=tareasPendientes.get(indiceFelaSeleccionada).getId();
           try{
              this.tareaDao.eliminar(id);
           }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "no se pudo lograr la eliminacion de la tarea seleccionada", "Error", JOptionPane.ERROR_MESSAGE); 
           }
           this.cargarTareasPendientes();
           JOptionPane.showMessageDialog(this, "Se elimino la tarea", "Operacion realizada", JOptionPane.INFORMATION_MESSAGE); 
        }
       }
    }
    
    private void eliminarTareaEnProgreso(){
        int indiceFelaSeleccionada = this.tbEnProgreso.getSelectedRow();
       if(indiceFelaSeleccionada==-1){  
           JOptionPane.showMessageDialog(this, "Selecciona la tarea que se desee eliminar", "error", JOptionPane.INFORMATION_MESSAGE);
       }else{ 
        int OpcionSeleccionada=JOptionPane.showConfirmDialog(this, "Desea eliminar el elemento seleccionado","Confirmacion",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(OpcionSeleccionada==JOptionPane.YES_OPTION){
            Integer id = this.tareasEnProgreso.get(this.econtrarElemento(indiceFelaSeleccionada)).getId();
            try{
              this.tareaDao.eliminar(id);
           }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "no se pudo lograr la eliminacion de la tarea seleccionada", "Error", JOptionPane.ERROR_MESSAGE); 
           }
            this.cargarTareasEnProgreso();
           JOptionPane.showMessageDialog(this, "Se elimino la tarea", "Operacion realizada", JOptionPane.INFORMATION_MESSAGE);
        }
       }
    }
    
    //para eliminar elementos si se requiere
    private void eliminarTareaFinalizada(){
       int indiceFelaSeleccionada = this.tbTerminado.getSelectedRow();
       if(indiceFelaSeleccionada==-1){  
           JOptionPane.showMessageDialog(this, "Selecciona la tarea que se desee eliminar", "error", JOptionPane.INFORMATION_MESSAGE);
       }else{  
          int OpcionSeleccionada=JOptionPane.showConfirmDialog(this, "Desea eliminar el elemento seleccionado","Confirmacion",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(OpcionSeleccionada==JOptionPane.YES_OPTION){
           Integer id=tareasTerminadas.get(indiceFelaSeleccionada).getId();
           try{
              this.tareaDao.eliminar(id);
           }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "no se pudo lograr la eliminacion de la tarea seleccionada", "Error", JOptionPane.ERROR_MESSAGE); 
           }
           this.cargarTareasTerminadas();
           JOptionPane.showMessageDialog(this, "Se elimino la tarea", "Operacion realizada", JOptionPane.INFORMATION_MESSAGE); 
        }
      }  
    }  
      
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.modificarTareas();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSonidoActionPerformed
        if(sonido.isPlaying()){
            sonido.stop();
        }
    }//GEN-LAST:event_btnSonidoActionPerformed

    public void modificarTareas(){
      int indiceFelaSeleccionadaPendientes = this.tbPendiente.getSelectedRow();
        int indiceFelaSeleccionadaEnProgreso = this.tbEnProgreso.getSelectedRow();
         if(indiceFelaSeleccionadaEnProgreso==-1 && indiceFelaSeleccionadaPendientes==-1){  
          JOptionPane.showMessageDialog(this, "Selecciona la tarea que se desee modificar", "error", JOptionPane.INFORMATION_MESSAGE);
         }else if(indiceFelaSeleccionadaPendientes>-1){
             Tarea tareaMod = this.tareasPendientes.get(indiceFelaSeleccionadaPendientes);
             FrmModificarTareas modificar=new FrmModificarTareas(tareaMod);
             modificar.jFrameMenu(this);
             modificar.setVisible(true);
         }else if(indiceFelaSeleccionadaEnProgreso>-1){
             Tarea tareaMod = this.tareasEnProgreso.get(this.econtrarElemento(indiceFelaSeleccionadaEnProgreso));
             FrmModificarTareas modificar=new FrmModificarTareas(tareaMod);
             modificar.jFrameMenu(this);
             modificar.setVisible(true);
         } 
    }
        
    public void cargarTareasPendientes(){
        try{
            this.tareasPendientes=this.tareaDao.consultarEstado(Estado.PENDIENTE);
            DefaultTableModel modeloTabla=(DefaultTableModel)this.tbPendiente.getModel();
            modeloTabla.setRowCount(0);
            for (Tarea tarea : tareasPendientes) {
                Object[] filaDatos=new Object[2];
                filaDatos[0]=tarea.getNombre();
                filaDatos[1]=tarea.getDescripcion();
                modeloTabla.addRow(filaDatos);
        }
        }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "No se pudieron obtener los datos de la tabla"+" "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    public void cargarTareasEnProgreso(){
        try{
            this.tareasEnProgreso=this.tareaDao.consultarEstado(Estado.EN_PROGRESO);
            modeloTablaEnProgreso=(DefaultTableModel)this.tbEnProgreso.getModel();
            modeloTablaEnProgreso.setRowCount(0);
            for (Tarea tarea : tareasEnProgreso) {
                Object[] filaDatos=new Object[2];
                filaDatos[0]=tarea.getNombre();
                filaDatos[1]=tarea.getDescripcion();
                modeloTablaEnProgreso.addRow(filaDatos);
            }
            if(!tareasEnProgreso.isEmpty()){
                habilitarBotonIniciar();
            }else{
                restablecer();
            }
        }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "No se pudieron obtener los datos de la tabla"+" "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    private void cargarTareasTerminadas(){
        try{    
            this.tareasTerminadas=this.tareaDao.consultarEstadoTerminado(Estado.TERMINADA);
            DefaultTableModel modeloTabla=(DefaultTableModel)this.tbTerminado.getModel();
            modeloTabla.setRowCount(0);
            for (Tarea tarea : tareasTerminadas) {
                Object[] filaDatos=new Object[4];
                filaDatos[0]=tarea.getNombre();
                filaDatos[1]=tarea.getDescripcion();
                filaDatos[2]=fechaOrdenada(tarea.getFecha());
                filaDatos[3]=tarea.getHora();
                modeloTabla.addRow(filaDatos);
            }
        }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "No se pudieron obtener los datos de la tabla"+" "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    private String fechaOrdenada(Date date){
        Calendar calendario=new GregorianCalendar();
        calendario.setTime(date);
        String dia,mes,annio;
        dia=Integer.toString(calendario.get(Calendar.DATE));
        mes=Integer.toString(calendario.get(Calendar.MONTH)+1);
        annio=Integer.toString(calendario.get(Calendar.YEAR));
        return dia+"/"+mes+"/"+annio;
    }
    
    public boolean comprobarVacioTablaEnProgreso(){
        if(tareasEnProgreso.isEmpty()){
            return true;
        }
        return false;
    }
    
    public void habilitarBotonIniciar(){
        btnIniciarReanudar.setEnabled(true);
    }
    
    public void restablecer(){
        if(timer.isRunning())timer.stop();
        minutos=0;
        segundos=0;
        numDescansos=0;
        descanso=false;
        btnIniciarReanudar.setEnabled(false);
        btnPausar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
    private void empezarTemporizador(){
        if(minutos==0 && segundos==0){
            establecerTiempo();
        }
        timer.start();
    }
    
    private void establecerTiempo(){
        if(descanso){
            switch(numDescansos){
                case 3: 
                    minutos=10;
                    break;
                default: 
                    minutos= 3;
                    break;
            }
        }else{
            minutos=5;
        }
        actualizarTemporizador();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Salir;
    private javax.swing.JButton btnAbajoEnProgreso;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnArribaEnProgreso;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIniciarReanudar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPausar;
    private javax.swing.JButton btnRegresarPendientes;
    private javax.swing.JButton btnSonido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblInformacionDescansos;
    private javax.swing.JLabel lblNumDescansos;
    private javax.swing.JLabel lblTemporizador;
    private javax.swing.JTable tbEnProgreso;
    private javax.swing.JTable tbPendiente;
    private javax.swing.JTable tbTerminado;
    // End of variables declaration//GEN-END:variables

}
