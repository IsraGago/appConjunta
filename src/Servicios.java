import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Servicios {
    public static boolean eliminarMiServicio(int codUsuario){
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        listarServicios(codUsuario);
        System.out.print("Introduce el código de servicio del servicio que quieres eliminar: ");
        try {
            sentencia = conexion.createStatement();
            String codServicio = System.console().readLine();
            String sql = "Delete FROM servicios where codServicio = "+codServicio+" AND codUsuario = "+codUsuario+"";
            int resultado = sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            if (resultado == 1) {
                System.out.println("Servicio eliminado con exito.");
            } else {
                System.out.println("Error al intentar eliminar el servicio");
            }
            System.out.println("----------------------------------------------");
            return resultado == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
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

            ResultSet resultado = sentencia.executeQuery(
                    "SELECT codServicio,usuario,fechaCreacion,titulo FROM servicios inner join usuarios on servicios.codUsuario = usuarios.codUsuario");
            System.out.println("codServicio \t titulo \t usuario \t fecha de creacion");
            while (resultado.next()) {
                // Procesa los datos
                int codServicio = resultado.getInt("codServicio");
                String usuario = resultado.getString("usuario");
                Timestamp fechaCreacion = resultado.getTimestamp("fechaCreacion");
                String titulo = resultado.getString("titulo");

                // Procesa los datos
                System.out.printf("%d \t\t %s \t %s \t %s%n", codServicio, titulo, usuario, fechaCreacion);

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

            ResultSet resultado = sentencia.executeQuery(
                    "SELECT codServicio,usuario,fechaCreacion,titulo FROM servicios inner join usuarios on servicios.codUsuario = usuarios.codUsuario where servicios.codUsuario ="
                            + codUsuario);

            System.out.println("codServicio \t titulo \t usuario \t fecha de creacion");
            while (resultado.next()) {
                // Procesa los datos
                int codServicio = resultado.getInt("codServicio");
                String usuario = resultado.getString("usuario");
                Timestamp fechaCreacion = resultado.getTimestamp("fechaCreacion");
                String titulo = resultado.getString("titulo");

                // Procesa los datos
                System.out.printf("%d \t\t %s \t %s \t %s%n", codServicio, titulo, usuario, fechaCreacion);
            }
            System.out.println("------------------------------------------------------------------");

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarServiciosNoSolicitados(int codUsuario) {
        Connection conexion = Conexion.conectar();

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            String sql = "SELECT s.codServicio,u.usuario,s.titulo,s.fechaCreacion "+
            "FROM usuarios_servicios us inner join servicios s on s.codServicio = us.codServicio"+
            " inner join usuarios u on u.codUsuario = us.codUsuario where us.codUsuario !="+codUsuario+" ;";

            ResultSet resultado = sentencia.executeQuery(sql);
            System.out.println("codServicio \t titulo \t usuario \t fecha de creacion");
            while (resultado.next()) {
                // Procesa los datos
                int codServicio = resultado.getInt("codServicio");
                String usuario = resultado.getString("usuario");
                Timestamp fechaCreacion = resultado.getTimestamp("fechaCreacion");
                String titulo = resultado.getString("titulo");

                // Procesa los datos
                System.out.printf("%d \t\t %s \t %s \t %s%n", codServicio, titulo, usuario, fechaCreacion);

            }
            System.out.println("------------------------------------------------------------------");

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarServiciosSolicitados(int codUsuario) {
        Connection conexion = Conexion.conectar();

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            String sql = "SELECT s.codServicio,u.usuario,s.titulo,s.fechaCreacion \r\n" + //
                                "FROM usuarios_servicios us inner join servicios s on s.codServicio = us.codServicio\r\n" + //
                                "inner join usuarios u on u.codUsuario = us.codUsuario\r\n" + //
                                "where us.codUsuario = "+codUsuario+";";

            ResultSet resultado = sentencia.executeQuery(sql);
            System.out.println("codServicio \t titulo \t usuario \t fecha de creacion");
            while (resultado.next()) {
                // Procesa los datos
                int codServicio = resultado.getInt("codServicio");
                String usuario = resultado.getString("usuario");
                Timestamp fechaCreacion = resultado.getTimestamp("fechaCreacion");
                String titulo = resultado.getString("titulo");

                // Procesa los datos
                System.out.printf("%d \t\t %s \t %s \t %s%n", codServicio, titulo, usuario, fechaCreacion);

            }
            System.out.println("------------------------------------------------------------------");

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean solicitarServicio(int codUsuario) {
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        try {
            listarServiciosNoSolicitados(codUsuario);
            sentencia = conexion.createStatement();
            System.out.print("Código del servicio que quiere solicitar (0 para cancelar): ");
            String codServicio = System.console().readLine();

            if (codServicio.equals("0")) {
                return false;
            }
            String sql = "INSERT INTO usuarios_servicios (codUsuario,codServicio) VALUES ('"
                    + codUsuario + "','" + codServicio + "')";

            int resultado = sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            if (resultado == 1) {
                System.out.println("Servicio solicitado con éxito.");
                System.out.println("---------------------------------------");
            }
            return resultado == 1;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("ERROR: no se ha podido solicitar el servicio");
            return false;
        }
    }
}
