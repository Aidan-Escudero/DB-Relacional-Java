package practica.ej1.modelo;

/**
 *
 * @author Aidan Escudero - DAM1D
 */

public class Articulo {

    // PROPIEDADES
    private String codart;
    private String desc;
    private float precio;
    private int stock;

    // COMPORTAMIENTOS
    public Articulo(String codart, String desc, float precio, int stock) {
        this.codart = codart;
        this.desc = desc;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodart() {
        return codart;
    }

    public void setCodart(String codart) {
        this.codart = codart;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
