package teoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aidan
 */

public class TeoriaGestionBaseDatos {

    // 1. Configuración de los parámetros de conexión
    // El formato es:                  jdbc:[tipobd]://[host]:[puerto]/[nombre_bd] (¡si existe!)
    private static final String URL = "jdbc:mysql://localhost:3306/mi-base-de-datos";
    private static final String USER = "root";
    private static final String PASSWORD = "tu_contraseña";

    public static void main(String[] args) {

        // Usamos try-with-resources para que la conexión y el statement se cierren solo al terminar
        // Incluso si hay un error. Si no, habría que cerrarla manualmente con conexion.close()
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("Conexión establecida con EXITO");

            // 2. Crear el objeto Statement (el mensajero que lleva el SQL)
            Statement statement = conexion.createStatement();

            // 3. Crear una tabla (si no existe)
            String sqlCrearTabla = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nombre VARCHAR(50) NOT NULL,"
                + "email VARCHAR(50)"
                + ")";

            // executeUpdate se usa para CREATE, INSERT, UPDATE, DELETE (DML)
            statement.executeUpdate(sqlCrearTabla);
            System.out.println("Tabla USUARIOS creada");

            // 4. Insertar un dato en la tabla de prueba
            String sqlInsertar = "INSER INTO usuarios (nombre, email) VALUES ('Juan', 'juan@email.com')";
            statement.executeUpdate(sqlInsertar);
            System.out.println("Usuario INSERTADO");

            // 5. Consultar los datos
            String sqlConsulta = "SELECT * FROM usuarios";
            // executeUpdate se usa para SELECT (devuelve un ResultSet)
            ResultSet rs = statement.executeQuery(sqlConsulta);

            System.out.println("--- LISTA DE USUARIOS ---");
            while (rs.next()) {

                // Obtenemos los datos por el nombre de la columna
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println("ID " + id + "| Nombre " + nombre);
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión o SQL");
            e.printStackTrace(); // Imprime en consola el "rastero de la pila"
        }

    }
}
