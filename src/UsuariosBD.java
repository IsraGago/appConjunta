import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UsuariosBD {

    /**
     * Lista los usuarios de la base de datos
     */
    public static void listarUsuarios() {
        Connection conexion = Conexion.conectar();

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT * FROM usuarios");

            while (resultado.next()) {
                // Procesa los datos
                int codUsuario = resultado.getInt("codUsuario");
                String usuario = resultado.getString("usuario");
                // String password = resultado.getString("password");
                Timestamp createdAt = resultado.getTimestamp("fechaAlta");

                // Procesa los datos
                System.out.println(
                        "Código: " + codUsuario + ", nombre de usuario: " + usuario + ", fecha de alta: " + createdAt);
            }

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Comprueba si un usuario y contraseña son correctos
     * 
     * @param usuario  Usuario
     * @param password Contraseña
     * @return true si el usuario y contraseña son correctos
     */
    public static int loginUsuario(String usuario, String password) {
        int codUsuario = -1;
        boolean loginOk = false;
        Connection conexion = Conexion.conectar();

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT * FROM usuarios WHERE usuario LIKE '" + usuario + "'");

            if (resultado.next()) {
                // Si existe el usuario valida la contraseña con BCrypt
                byte[] passwordHashed = resultado.getString("password").getBytes(StandardCharsets.UTF_8);
                BCrypt.Result resultStrict = BCrypt.verifyer(BCrypt.Version.VERSION_2Y).verifyStrict(
                        password.getBytes(StandardCharsets.UTF_8),
                        passwordHashed);
                loginOk = resultStrict.verified;
                loginOk = validarHash2Y(password, resultado.getString("password"));
                if (loginOk) {
                    codUsuario = resultado.getInt("codUsuario");
                }
            }

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return loginOk;
        return codUsuario;
    }

    /**
     * Cambia la contraseña de un usuario
     * 
     * @param usuario  Usuario
     * @param password Nueva contraseña
     * @return true si se cambió la contraseña
     */
    public static boolean cambiarPassword(String usuario, String password) {
        boolean cambiarPassword = false;
        Connection conexion = Conexion.conectar();

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            int resultado = sentencia.executeUpdate("UPDATE usuarios SET password='" + generarStringHash2Y(password)
                    + "' WHERE usuario LIKE '" + usuario + "'");

            if (resultado == 1) {
                // Si se cambió la contraseña
                cambiarPassword = true;
            }

            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cambiarPassword;
    }

    /*
     * FUNCIONES BCRYPT: generar hash y validar hash
     */
    /**
     * Valida un hash de BCrypt
     * 
     * @param password Contraseña en texto claro
     * @param hash2y   Hash de BCrypt
     * @return true si la contraseña es correcta
     */
    private static boolean validarHash2Y(String password, String hash2y) {
        return BCrypt.verifyer(BCrypt.Version.VERSION_2Y)
                .verifyStrict(password.getBytes(StandardCharsets.UTF_8),
                        hash2y.getBytes(StandardCharsets.UTF_8)).verified;
    }

    /**
     * Genera un hash de BCrypt
     * 
     * @param password Contraseña en texto claro
     * @return Hash de BCrypt
     */
    private static String generarStringHash2Y(String password) {
        char[] bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToChar(13, password.toCharArray());
        return String.valueOf(bcryptChars);
    }

    /**
     * Inicia sesión de usuario
     * Solicita credenciales de inicio de sesión, y si son correctas devuelve el
     * nombre de usuario.
     * 
     * @return Usuario que ha iniciado sesión
     */
    public static int iniciarSesion() {
        do {
            System.out.println("LOGIN DE USUARIO");
            System.out.print("Usuario: ");
            String usuario = System.console().readLine();
            System.out.print("Contraseña: ");
            String password = new String(System.console().readPassword());
            int codUsuario = loginUsuario(usuario, password);
            if (codUsuario != -1) {
                return codUsuario;
            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }
        } while (true);
    }

    /**
     * Crea un nuevo usuario
     * Solicita credenciales de nuevo usuario, y si se crea correctamente devuelve
     * true
     * 
     * @return true si se creó el usuario
     */
    public static boolean crearUsuario() {
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            System.out.print("Usuario: ");
            String usuario = System.console().readLine();
            System.out.print("Contraseña: ");
            String password = new String(System.console().readPassword());
            // System.out.print("Confirmar contraseña: ");
            // String passwordConfirmar = new String(System.console().readPassword());
            // if (password != passwordConfirmar) { return false;}
            int resultado = sentencia
                    .executeUpdate("INSERT INTO usuarios (usuario, password) VALUES ('" + usuario + "', '"
                            + generarStringHash2Y(password) + "')");
            sentencia.close();
            conexion.close();
            return resultado == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            // System.out.println("Error al crear el usuario");
            return false;
        }
    }

    /**
     * Método principal de ejemplo
     */
    public static void main(String[] args) {
        int codUsuario = -1;
        System.out.println("\n*******************");
        System.out.println("GESTIÓN DE USUARIOS");
        System.out.println("*******************\n");
        System.out.println("BASE DE DATOS: " + Conexion.DATABASE + " en " + Conexion.HOST + ":" + Conexion.PORT);
        String opcion;
        do {
            System.out.println();
            System.out.println("1. LISTADO DE USUARIOS:");
            System.out.println("2. CREACIÓN DE USUARIO");
            System.out.println("3. LOGIN DE USUARIO");
            System.out.println("4. CAMBIO DE CONTRASEÑA");
            System.out.println("5. CREAR SERVICIO");
            System.out.println("0. SALIR");

            System.out.println();
            System.out.print("Opción: ");
            opcion = System.console().readLine();
            System.out.println();

            switch (opcion) {
                case "1":
                    listarUsuarios();
                    break;
                case "2":
                    System.out.println(crearUsuario() ? "Usuario creado" : "Error al crear el usuario");
                    break;
                case "3":
                    codUsuario = iniciarSesion();
                    System.out.println("tu código de usuario es: " + codUsuario);
                    // System.out.println("LOGIN DE USUARIO");
                    // System.out.print("Usuario: ");
                    // String usuario = System.console().readLine();
                    // System.out.print("Contraseña: ");
                    // String password = new String(System.console().readPassword());
                    // System.out.println(loginUsuario(usuario, password) ? "Login OK" : "Login
                    // KO");
                    break;
                case "4":
                    System.out.println("CAMBIO DE CONTRASEÑA");
                    System.out.print("Usuario: ");
                    String usuarioCambio = System.console().readLine();
                    System.out.print("Nueva contraseña: ");
                    String newPassword = new String(System.console().readPassword());
                    System.out.println(
                            cambiarPassword(usuarioCambio, newPassword) ? "Contraseña cambiada"
                                    : "Error al cambiar la contraseña");
                    break;
                case "5":
                    if (codUsuario != -1) {
                        Servicios.crearServicio(codUsuario);
                    } else {
                        System.out.println("Debes iniciar sesión para usar esta función");
                    }
                    break;
                case "0":
                    System.out.println("Hasta pronto...\n");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!opcion.equals("0"));

    }

}