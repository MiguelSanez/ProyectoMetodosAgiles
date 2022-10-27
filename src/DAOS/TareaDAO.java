
package DAOS;

import Objeto_negocio.Tarea;
import java.sql.SQLException;
import java.sql.Statement;

public class TareaDAO {
    
    private Tarea tarea;

    public TareaDAO(Tarea tarea) {
        this.tarea = tarea;
    }
    
    public boolean save() {
        try {
            String sql = String.format("insert or replace into tareas "
                    + "(id, nombre, descripcion, estado)",
                    null, tarea.getNombre(), tarea.getDescripcion(), 1);
            System.out.println(sql);
            Statement s = BaseDAO.drv.createQuery();
            s.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            BaseDAO.error("Ha ocurrido un problema", e.getMessage());
        }
        return false;
    }
    
}
