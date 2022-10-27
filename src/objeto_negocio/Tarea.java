
package Objeto_negocio;

public class Tarea {
    private String nombre;
    private String descripcion;
    private Estado estado;

    public Tarea() {
    }

    public Tarea(String nombre, String descripcion, Estado estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tarea{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }
}
