package cl.speedfast.model;
public class Repartidor {
    private int id; private String nombre;
    public Repartidor(int id, String nombre) { this.id=id; this.nombre=nombre; }
    public Repartidor(String nombre) { this(0,nombre); }
    public int getId(){return id;} public String getNombre(){return nombre;}
    @Override public String toString(){return id + " - " + nombre;}
}
