
package DAOS;

import Objeto_negocio.Estado;
import Objeto_negocio.Tarea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class TareaDAO extends BaseDAO<Tarea> implements Comparator<Tarea>{
    
    private Tarea tarea;
    public boolean señal;
    public ArrayList<Tarea> listaTareas=new ArrayList<>();
    
    @Override
    public void actualizar(Tarea entidad) throws Exception {
        String sql = "UPDATE tareas SET nombre=?, descripcion=?, estado=? WHERE id=?";
         PreparedStatement ps;
         try {
            Connection conexion = conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getDescripcion());
            ps.setInt(3, entidad.getEstado().ordinal());
            ps.setInt(4,entidad.getId());

            ps.executeUpdate();
            ps.close();
            System.out.println("Proceso ejecutado con exito");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void actualizarParaFinalizar(Tarea entidad) throws Exception {
        String sql = "UPDATE tareas SET nombre=?, descripcion=?, estado=?, fecha=?, hora=? WHERE id=?";
         PreparedStatement ps;
         try {
            Connection conexion = conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getDescripcion());
            ps.setInt(3, entidad.getEstado().ordinal());
            DateFormat formatoFecha=DateFormat.getDateInstance(DateFormat.SHORT);
            Date fecha=new Date();
            ps.setString(4, formatoFecha.format(fecha));
            DateFormat formatoHora=DateFormat.getTimeInstance(DateFormat.SHORT);
            ps.setString(5, formatoHora.format(fecha));
            ps.setInt(6,entidad.getId());

            ps.executeUpdate();
            ps.close();
            System.out.println("Proceso ejecutado con exito");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void llenarLista(){
        String sql = "select * from tareas";
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conexion=this.conectar();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tarea t = new Tarea();
                t.setNombre(rs.getString(2));
                t.setDescripcion(rs.getString(3));
                t.setEstado(estadoInt(rs.getInt(4)));
                listaTareas.add(t);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean repetidoTitulo(String titulo){
//        String sql = "select * from tareas where nombre =?";
//        PreparedStatement ps;
//        ResultSet rs;
//        try {
//            Connection conexion=this.conectar();
//            ps = conexion.prepareStatement(sql);
//            ps.setString(1, titulo);
//            rs = ps.executeQuery();
//            if(rs.next())return true;
//            System.out.println("Proceso ejecutado con exito");
//            señal=false;
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return false;

       for (int i = 0; i < listaTareas.size()-1; i++) {
            if(listaTareas.get(i).getNombre().equals(titulo)){
                return true;
            }
        }
        señal=false;
        return false;
    }   
    
    public boolean repetidoDescripcion(String descripcion){
//        String sql = "select * from tareas where descripcion =?";
//        PreparedStatement ps;
//        ResultSet rs;
//        try {
//            Connection conexion=this.conectar();
//            ps = conexion.prepareStatement(sql);
//            ps.setString(1, descripcion);
//            rs = ps.executeQuery();
//            if(rs.next())return true;
//            System.out.println("Proceso ejecutado con exito");
//            señal=false;
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return false;

        for (int i = 0; i < listaTareas.size()-1; i++) {
            if(listaTareas.get(i).getDescripcion().equals(descripcion)){
                return true;
            }
        }
        señal=false;
        return false;
    } 

    @Override
    public ArrayList<Tarea> consultar() throws Exception {
        ArrayList<Tarea> tareas = new ArrayList();
        String sql = "select * from tareas";
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conexion=this.conectar();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tarea t = new Tarea();
                t.setNombre(rs.getString(2));
                t.setDescripcion(rs.getString(3));
                t.setEstado(estadoInt(rs.getInt(4)));
                tareas.add(t);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>(tareas);
    }
    
    public Estado estadoInt(Integer estado){
        switch(estado){
            case 0:
                return Estado.PENDIENTE;
            case 1:
                return Estado.EN_PROGRESO;
            case 2:
                return Estado.TERMINADA;
            default:
                break;
        }
        return null;
    }
    
    public ArrayList<Tarea> consultarEstado(Estado estadoSel) throws Exception {
        ArrayList<Tarea> tareas = new ArrayList();
        String sql = "select * from tareas where estado=?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conexion=this.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, estadoSel.ordinal());
            rs = ps.executeQuery();
            while (rs.next()) {
                Tarea t = new Tarea();
                t.setId(rs.getInt(1));
                t.setNombre(rs.getString(2));
                t.setDescripcion(rs.getString(3));
                t.setEstado(estadoInt(rs.getInt(4)));
                tareas.add(t);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>(tareas);
    }
    
    public ArrayList<Tarea> consultarEstadoTerminado(Estado terminado) throws Exception {
        ArrayList<Tarea> tareas = new ArrayList();
        String sql = "select * from tareas where estado=?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conexion=this.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, terminado.ordinal());
            rs = ps.executeQuery();
            while (rs.next()) {
                Tarea t = new Tarea();
                t.setId(rs.getInt(1));
                t.setNombre(rs.getString(2));
                t.setDescripcion(rs.getString(3));
                t.setEstado(estadoInt(rs.getInt(4)));
                t.setFecha(ParseFecha(rs.getString(5),rs.getString(6)));
                t.setHora(rs.getString(6));
                tareas.add(t);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Collections.sort(tareas, new TareaDAO());
        System.out.println(tareas.toString());
        return new ArrayList<>(tareas);
    }
    
    public static Date ParseFecha(String fecha, String hora){
        System.out.println(fecha);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = new GregorianCalendar();
        Date fechaDate = null;
        Date fechaFinal=null;
        try {
            fechaDate = formato.parse(fecha);
            cal.setTime(fechaDate);
            cal.set(Calendar.HOUR_OF_DAY, hora(hora));
            cal.set(Calendar.MINUTE, minuto(hora));
            fechaFinal= cal.getTime();
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaFinal;
    }
    
    public static int hora(String hora){
        String horaSub=hora.substring(0, 2);
        return Integer.parseInt(horaSub);
    }
    
    public static int minuto(String hora){
        String minutoSub=hora.substring(3, 5);
        return Integer.parseInt(minutoSub);
    }
    
    @Override
    public void eliminar(Integer id) throws Exception {
         String sql = "DELETE FROM tareas WHERE id=?";
        PreparedStatement ps;
         Connection conexion;
        try {
            conexion = conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, id.toString());
            ps.executeUpdate();
            ps.close();
            System.out.println("Proceso ejecutado con exito");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList Buscar(String elemento) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertar(Tarea entidad) throws Exception {
        String sql = "INSERT INTO tareas(nombre,descripcion,estado) VALUES(?,?,?)";
        PreparedStatement ps;
        try {
             System.out.println("aqui aun no se bloquea");
            Connection conexion=this.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getDescripcion());
            ps.setInt(3, 0);

            ps.executeUpdate();
            señal=true;

            System.out.println("Proceso ejecutado con exito");
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<Tarea> consultarPorIdLista(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Tarea consultarPorId(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int compare(Tarea o1, Tarea o2) {
        return o2.getFecha().compareTo(o1.getFecha());
    }
}
