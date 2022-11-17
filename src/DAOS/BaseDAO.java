
package DAOS;


//import java.awt.Dimension;
//import java.awt.Toolkit;
import java.sql.SQLException;
//import javax.swing.JOptionPane;
//import java.awt.Frame;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
//import javax.swing.JComponent;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JTable;
//import javax.swing.KeyStroke;

public abstract class BaseDAO<T> {
    private Connection con;
    
    /**
    *Esta es la conexion a la bd de todas las daos
     * @return conexion de la bd
    */
    public Connection conectar() {
        String url = "jdbc:sqlite:proyectopomodoro.db";
        try {

            System.out.println("Conectando a la base de datos");
            con = DriverManager.getConnection(url);
            if (con != null) {
                System.out.println("Conexion con exito!");
                return con;
            } else {
                System.out.println("Error: Con es invalido");
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    /**
    *metodo para consultar las tablas y todos su regsitros y psteriormente listarlos
     * @return los registros de la tabla
     * @throws java.lang.Exception
    */
    public abstract ArrayList<T> consultar() throws Exception; 
    
    /**
    *Este sirve para insertar una entidad dentro de la tabla correspondiente
     * @param entidad que se quiere insertar como regsitro
     * @return 
     * @throws java.lang.Exception
    */
    public abstract boolean insertar(T entidad) throws Exception;
    
    /**
    *Este metodo sirve para actualizar el registro con la id de la entidad colocada, que toma como 
    * referencia para editar el registro de la tabla
     * @param entidad a editar
     * @throws java.lang.Exception
    */
    public abstract void actualizar(T entidad) throws Exception;
    
    /**
    * Este metodo consultura atravez de la id colocada 
     * @param id la id que sera utilizada para realizar la consulta
     * @return consulta realizada
     * @throws java.lang.Exception
    */
    public abstract T consultarPorId(Integer id) throws Exception;
    
    /**
    *Con este metodo se eliminara el registro con la id colocada
     * @param id que se eliminara
     * @throws java.lang.Exception
    */
    public abstract void eliminar (Integer id) throws Exception;
    
    /**
    *Este metodo consultara y regresara una lista que tengan la misma id (metodo para buscar)
     * @param id la id a buscar
     * @return la lista con las consultas
     * @throws java.lang.Exception
    */
    public abstract ArrayList<T> consultarPorIdLista(Integer id) throws Exception;
    
    /**
    *Este metodo buscara el elemento que se colocque dentro del parametro y conforme a las validaciones
     * @param elemento el elemento que se buscara
     * @return el regsitro encontrado
     * @throws java.lang.Exception
    */
    public abstract ArrayList<T> Buscar(String elemento) throws Exception; 
    
//    public static void success(String title, String msg) {
//        JFrame jf = new JFrame();
//        jf.setAlwaysOnTop(true);
//        JOptionPane.showMessageDialog(jf, msg, title, JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    public static void error(String title, String msg) {
//        JFrame jf = new JFrame();
//        jf.setAlwaysOnTop(true);
//        JOptionPane.showMessageDialog(jf, msg, title, JOptionPane.ERROR_MESSAGE);
//    }
//
//    public static boolean question(String title, String message) {
//        JFrame jf = new JFrame();
//        jf.setAlwaysOnTop(true);
//        int reply = JOptionPane.showConfirmDialog(jf, message, title, JOptionPane.YES_NO_OPTION);
//        return reply == JOptionPane.YES_OPTION;
//    }

//    public static void centerFrame(Frame frame) {
//        //Obtiene el tamaño de la pantalla
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//
//        // Obtiene el tamaño de la ventana de la aplicación
//        Dimension frameSize = frame.getSize();
//
//        // Se asegura que el tamaño de la ventana de la aplicación
//        // no exceda el tamaño de la pantalla
//        if (frameSize.height > screenSize.height) {
//            frameSize.height = screenSize.height;
//        }
//        if (frameSize.width > screenSize.width) {
//            frameSize.width = screenSize.width;
//        }
//
//        // Centra la ventana de la aplicación sobre la pantalla
//        frame.setLocation((screenSize.width - frameSize.width) / 2,
//                (screenSize.height - frameSize.height) / 2);
//    }

//    public static void centerFrame(JDialog frame) {
//        //Obtiene el tamaño de la pantalla
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//
//        // Obtiene el tamaño de la ventana de la aplicación
//        Dimension frameSize = frame.getSize();
//
//        // Se asegura que el tamaño de la ventana de la aplicación
//        // no exceda el tamaño de la pantalla
//        if (frameSize.height > screenSize.height) {
//            frameSize.height = screenSize.height;
//        }
//        if (frameSize.width > screenSize.width) {
//            frameSize.width = screenSize.width;
//        }
//
//        // Centra la ventana de la aplicación sobre la pantalla
//        frame.setLocation((screenSize.width - frameSize.width) / 2,
//                (screenSize.height - frameSize.height) / 2);
//    }
//
//    public static boolean isNumeric(String str) {
//        try {
//            Double.parseDouble(str);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
}
