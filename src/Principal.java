import java.util.Scanner;

public class Principal {
    static Scanner sc = new Scanner(System.in);

    static int menu() {
        boolean respuestaValida = false;
        int respuesta = -1;

        while (!respuestaValida) {
            try {
                System.out.println("1. LISTAR TODOS LOS SERVICIOS");
                System.out.println("2. LISTAR MIS SERVICIOS");
                System.out.println("3. AÑADIR SERVICIO");
                System.out.println("4. CERRAR SESIÓN");
                System.out.println("0. SALIR");
                System.out.println("------------------------------");
                System.out.print("Respuesta: ");
                respuesta = sc.nextInt();
                sc.nextLine();
                respuestaValida = true;
            } catch (Exception e) {
                System.out.println("ERROR: LA RESPUESTA DEBE SER UN NÚMERO ENTERO.");
                sc.nextLine();
            }
        }
        return respuesta;

    }

    public static void main(String[] args) {
        int codUsuario = -1;
        int respuesta = -1;
        while (codUsuario == -1) {
            codUsuario = UsuariosBD.iniciarSesion();

            while (respuesta != 0 && codUsuario != -1) {
                respuesta = menu();
                switch (respuesta) {
                    case 0: System.out.println("SALIENDO DEL PROGRAMA"); break;
                    case 1:
                        Servicios.listarServicios();
                        break;
                    case 2:
                        // Servicios.listarServicios(codUsuario);
                        break;
                    case 3:
                        Servicios.crearServicio(codUsuario);
                        break;
                    case 4:
                        System.out.println("CERRANDO SESIÓN");
                        codUsuario = -1;
                        break;

                    default:
                        System.out.println("Respuesta inválida.");
                        break;
                }
            }
        }
    }
}
