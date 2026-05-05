package practica.ej1.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 * Clase encargada de gestionar la conexión con la base de datos. Implementa la creación automática de la base de datos y sus tablas si no existen al iniciar la
 *
 * @author Aidan Escudero - DAM1D
 * @version 1.0
 */

public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost:3306/";
    private static final String USER = "root";
    private static final String PWD = "";

    // COMPORTAMIENTOS
    /**
     * Registra un mensaje de error en un archivo de texto externo. Si el archivo no existe, lo crea automáticamente. Si existe, añade la información al final sin borrar el contenido previo
     *
     * @param mensajeError El texto descriptivo de la excepción o error producido
     */
    public static void escribirLog(String mensajeError) {

        // Obtiene el tiempo del momento del error
        String fecha = LocalDateTime.now().toString();

        try (FileWriter fw = new FileWriter("log.txt", true); PrintWriter pw = new PrintWriter(fw)) {

            // Se imprime el mensaje de error junto con la fecha
            pw.println(fecha + " || " + mensajeError);

        } catch (IOException e) {

            System.err.println("No se pudo escribir en el log: " + e.getMessage());
        }
    }

    /**
     * Intenta establecer una conexión con el servidor MariaDb y asegura la existencia de la infraestructura de datos necesario (BBDD y sus tablas)
     *
     * El proceso sigue este orden: 1. Conecta con el servidor local de XAMPP. 2. Crea la base de datos 'Acme' si no existe. 3. Selecciona la base de datos 'Acme' para su uso. 4. Crea la tabla 'Articulos' siguiendo las especificaciones
     *
     * Si ocurre cualquier error de SQL, se registra en log.txt y se aborta la ejecución del programa.
     *
     * @return Un objeto {@link Connection} configurado y listo para operar con la tabla Articulos
     * @see #escribirLog(java.lang.String)
     */
    public static Connection conectar() {

        // Declara la conexión. (Fuera del try para que luego pueda ser retornada en el método)
        Connection cn = null; // Se pone como nula para que no nos de problema de SQLException

        try { // Intenta

            // Conectar con la base de datos
            cn = DriverManager.getConnection(URL, USER, PWD);

            try (Statement stmt = cn.createStatement()) {

                stmt.execute("CREATE DATABASE IF NOT EXISTS Acme");
                stmt.execute("USE Acme");
                stmt.execute("CREATE TABLE articulos ( "
                    + "cod_art VARCHAR(5) PRIMARY KEY, "
                    + "descripcion VARCHAR(25) NOT NULL, "
                    + "precio FLOAT NOT NULL, "
                    + "stock INTEGER NOT NULL"
                    + ");");
            }

        } catch (SQLException e) {

            escribirLog(e.toString());

            // Aborta el programa
            System.exit(0);
        }
        return cn;
    }
}
