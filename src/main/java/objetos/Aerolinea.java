package objetos;

import org.bson.types.ObjectId;

/**
 *
 * @author pedro
 */
public class Aerolinea {
    private ObjectId id;
    private String nombre;
    private String pais;
    private String moneda;
    private boolean economica;

    public Aerolinea() {
    }

    public Aerolinea(String nombre, String pais, String moneda, boolean economica) {
        this.nombre = nombre;
        this.pais = pais;
        this.moneda = moneda;
        this.economica = economica;
    }

    public Aerolinea(ObjectId id, String nombre, String pais, String moneda, boolean economica) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.moneda = moneda;
        this.economica = economica;
    }

    public Aerolinea(ObjectId id) {
        this.id = id;
    }
    
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public boolean isEconomica() {
        return economica;
    }

    public void setEconomica(boolean economica) {
        this.economica = economica;
    }

    @Override
    public String toString() {
        return "Aerolinea{" + "id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", moneda=" + moneda + ", economica=" + economica + '}';
    }

  
    
}
