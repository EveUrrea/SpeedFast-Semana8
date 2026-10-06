package cl.speedfast.model;
import java.time.LocalDateTime;
public class Entrega {
    private int id, pedidoId, repartidorId; private LocalDateTime fechaHora; private String direccion, repartidor;
    public Entrega(int id,int pedidoId,int repartidorId,LocalDateTime fechaHora,String direccion,String repartidor){this.id=id;this.pedidoId=pedidoId;this.repartidorId=repartidorId;this.fechaHora=fechaHora;this.direccion=direccion;this.repartidor=repartidor;}
    public Entrega(int pedidoId,int repartidorId,LocalDateTime fechaHora){this(0,pedidoId,repartidorId,fechaHora,"","");}
    public int getId(){return id;} public int getPedidoId(){return pedidoId;} public int getRepartidorId(){return repartidorId;} public LocalDateTime getFechaHora(){return fechaHora;} public String getDireccion(){return direccion;} public String getRepartidor(){return repartidor;}
}
