package cl.speedfast.dao;
import cl.speedfast.db.ConexionDB; import cl.speedfast.model.Pedido;
import java.sql.*; import java.util.*;
public class PedidoDAO {
 public void create(Pedido x)throws SQLException{String q="INSERT INTO pedidos(direccion,tipo,estado) VALUES(?,?,?)";try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement(q)){s.setString(1,x.getDireccion());s.setString(2,x.getTipo());s.setString(3,x.getEstado());s.executeUpdate();}}
 public List<Pedido> readAll()throws SQLException{return readAll("","");}
 public List<Pedido> readAll(String estado,String tipo)throws SQLException{List<Pedido> out=new ArrayList<>();String q="SELECT id_pedido,direccion,tipo,estado FROM pedidos WHERE (?='' OR estado=?) AND (?='' OR tipo=?) ORDER BY id_pedido";try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement(q)){s.setString(1,estado);s.setString(2,estado);s.setString(3,tipo);s.setString(4,tipo);try(ResultSet r=s.executeQuery()){while(r.next())out.add(new Pedido(r.getInt("id_pedido"),r.getString("direccion"),r.getString("tipo"),r.getString("estado")));}}return out;}
 public void update(int id,Pedido x)throws SQLException{try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement("UPDATE pedidos SET direccion=?,tipo=?,estado=? WHERE id_pedido=?")){s.setString(1,x.getDireccion());s.setString(2,x.getTipo());s.setString(3,x.getEstado());s.setInt(4,id);s.executeUpdate();}}
 public void delete(int id)throws SQLException{try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement("DELETE FROM pedidos WHERE id_pedido=?")){s.setInt(1,id);s.executeUpdate();}}
}
