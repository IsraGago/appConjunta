import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Servicios {
    public static boolean crearServicio(int codUsuario) {
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        try {
            System.out.println("Crear servicio");
            sentencia = conexion.createStatement();
            System.out.print("Título: ");
            String titulo = System.console().readLine();
            System.out.print("Descripción: ");
            String descripcion = System.console().readLine();
            System.out.print("Ubicación: ");
            String ubicacion = System.console().readLine();
            System.out.print("Número máximo de personas: ");
            String maxUsuarios = System.console().readLine();

            String sql = "INSERT INTO servicios (codUsuario,titulo,descripcion,ubicacion,maxUsuarios) VALUES ('"
                    + codUsuario + "','" + titulo + "','" + descripcion + "','" + ubicacion + "','" + maxUsuarios
                    + "')";
            int resultado = sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            if (resultado == 1) {
                System.out.println("Servicio insertado con éxito.");
            }
            return resultado == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void listarServicios() {
        Connection conexion = Conexion.conectar();

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT codServicio,usuario,fechaCreacion,titulo FROM servicios inner join usuarios on servicios.codUsuario = usuarios.codUsuario");

            while (resultado.next()) {
                // Procesa los datos
                int codServicio = resultado.getInt("codServicio");
                String usuario = resultado.getString("usuario");
                Timestamp fechaCreacion = resultado.getTimestamp("fechaCreacion");
                String titulo = resultado.getString("titulo");

                // Procesa los datos
                System.out.println(
                        "Código del servicio: " + codServicio +", título del servicio: "+titulo +", usuario creador: " + usuario + ", fecha de creación: " + fechaCreacion);
            }
            System.out.println("------------------------------------------------------------------");

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void listarServicios(int codUsuario) {
        Connection conexion = Conexion.conectar();

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT codServicio,usuario,fechaCreacion,titulo FROM servicios inner join usuarios on servicios.codUsuario = usuarios.codUsuario where servicios.codUsuario ="+codUsuario);

            while (resultado.next()) {
                // Procesa los datos
                int codServicio = resultado.getInt("codServicio");
                String usuario = resultado.getString("usuario");
                Timestamp fechaCreacion = resultado.getTimestamp("fechaCreacion");
                String titulo = resultado.getString("titulo");

                // Procesa los datos
                System.out.println(
                        "Código del servicio: " + codServicio +", título del servicio: "+titulo +", usuario creador: " + usuario + ", fecha de creación: " + fechaCreacion);
            }
            System.out.println("------------------------------------------------------------------");

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
