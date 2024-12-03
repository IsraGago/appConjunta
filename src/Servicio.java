public class Servicio {
    private int codUsuario;
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private String maxUsuarios;

    public Servicio(String titulo, String descripcion, String ubicacion, String maxUsuarios, int codUsuario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.maxUsuarios = maxUsuarios;
    }
    public int getCodUsuario() {
        return codUsuario;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public String getMaxUsuarios() {
        return maxUsuarios;
    }
}
