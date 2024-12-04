import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
     // Datos de conexión a la base de datos (localhost)
    // static final String HOST = "localhost";
    // static final String DATABASE = "appconjunta";
    // static final String USER = "root";
    // static final String PASSWORD = "";
    // static final String PORT = "3306";

    static final String HOST = "6gpe8.h.filess.io";
    static final String DATABASE = "appConjunta_turndance";
    static final String USER = "appConjunta_turndance";
    static final String PASSWORD = "6264969cbd635585af7ef3ea17a3b74b1348774d";
    static final String PORT = "3305";

    /**
     * Conecta con la base de datos
     *
     * @return Conexión con la base de datos
     */
    public static Connection conectar() {
        Connection con = null;


        String url = "jdbc:mysql://" + Conexion.HOST + ":" + Conexion.PORT + "/" + Conexion.DATABASE;


        try {
            con = DriverManager.getConnection(url, Conexion.USER, Conexion.PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la BD.");
        }


        return con;
    }
}

