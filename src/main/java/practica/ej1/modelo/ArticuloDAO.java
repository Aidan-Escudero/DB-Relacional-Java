package practica.ej1.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase DAO (Data Access Object) para la entidad Articulo. Centraliza todas las operaciones CRUD (Create, Read, Update, Delete) sobre la tabla Articulos en la base de datos.
 *
  * @author aidan
 */

public class ArticuloDAO {

    // PROPIEDADES
    private Connection cn;

    // COMPORTAMIENTOS
    public ArticuloDAO(Articulo art, Conexion cn) {
        this.cn = Conexion.conectar();
    }

    public boolean insertar(Articulo a) {

        boolean insertado = false;
        String sql = "INSERT INTO Articulos (cod_art, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {

            // Reemplaza los "?" con valores de la clase Articulo introducida
            ps.setString(1, a.getCodart());
            ps.setString(2, a.getDesc());
            ps.setFloat(3, a.getPrecio());
            ps.setInt(4, a.getStock());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                insertado = true;
            }

        } catch (SQLException e) {

            Conexion.escribirLog(e.toString());
        }
        return insertado;
    }

    /**
     * Recupera todos los registros de la tabla Articulos.
     *
     * @param a
     * @return Una lista de objetos {@link Articulo}. Si no hay datos, devuelve una lista vacía.
     */

    public boolean eliminar(Articulo a) {

        boolean eliminado = false;
        String sql = "DELETE FROM Articulos WHERE cod_art = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {

            // Reemplaza los "?" con valores de la clase Articulo introducida
            ps.setString(1, a.getCodart());

            try (FileWriter fw = new FileWriter("historico.txt", true); PrintWriter pw = new PrintWriter(fw)) {

                // Imprime los datos del articulo a borrar
                pw.println("BAJA: " + a.getCodart() + " - " + a.getDesc() + a.getPrecio() + a.getStock());
            }

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                eliminado = true;
            }

        } catch (SQLException e) {

            Conexion.escribirLog("ERROR. SQL en baja: " + e.toString());
        } catch (IOException e) {
            Conexion.escribirLog("ERROR. Fichero histórico: " + e.getMessage());
        }

        return eliminado;
    }

    public ArrayList<Articulo> listarArticulos() {

        ArrayList<Articulo> lista = new ArrayList<>();
        String consultaSQL = "SELECT * FROM articulos";

        try (PreparedStatement ps = cn.prepareStatement(consultaSQL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                // 1. Extrae datos de la fila actual del ResultSet
                String codArt = rs.getString("cod_art");
                String desc = rs.getString("descripcion");
                float precio = rs.getFloat("precio");
                int stock = rs.getInt("stock");

                // 2. Crea el objeto Articulo con esos datos
                Articulo art = new Articulo(codArt, desc, precio, stock);

                // 3. Añade el objeto a la 'lista'
                lista.add(art);
            }

        } catch (SQLException e) {
            Conexion.escribirLog("ERROR. Al listar: " + e.getMessage());
        }

        return lista;
    }

    public Articulo consultarArticulo(String codigo) {

        Articulo art = null;
        String sql = "SELECT * FROM articulos WHERE cod_art = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    art = new Articulo(
                        rs.getString("cod_art"),
                        rs.getString("descripcion"),
                        rs.getFloat("precio"),
                        rs.getInt("stock"));
                }
            }

        } catch (SQLException e) {
            Conexion.escribirLog("ERROR. al consultar artículo: " + e.getMessage());
        }

        return art;
    }

}
