
package DAOS;

import Objeto_negocio.Estado;
import Objeto_negocio.Tarea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TareaDAO extends BaseDAO<Tarea> {
    
    private Tarea tarea;
    public boolean señal;
    
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
    
    public boolean repetidoTitulo(String titulo){
        String sql = "select * from tareas where nombre =?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conexion=this.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, titulo);
            rs = ps.executeQuery();
            if(rs.next())return true;
            System.out.println("Proceso ejecutado con exito");
            señal=false;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }   
    
    public boolean repetidoDescripcion(String descripcion){
        String sql = "select * from tareas where descripcion =?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conexion=this.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, descripcion);
            rs = ps.executeQuery();
            if(rs.next())return true;
            System.out.println("Proceso ejecutado con exito");
            señal=false;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
    
    @Override
    public void eliminar(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
}
