package cl.speedfast.model;
public class Pedido {
    private int id; private String direccion, tipo, estado;
    public Pedido(int id,String direccion,String tipo,String estado){this.id=id;this.direccion=direccion;this.tipo=tipo;this.estado=estado;}
    public Pedido(String direccion,String tipo,String estado){this(0,direccion,tipo,estado);}
    public int getId(){return id;} public String getDireccion(){return direccion;} public String getTipo(){return tipo;} public String getEstado(){return estado;}
    @Override public String toString(){return id + " - " + direccion;}
}
