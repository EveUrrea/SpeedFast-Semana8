package cl.speedfast.dao;
import cl.speedfast.db.ConexionDB; import cl.speedfast.model.*;
import java.sql.*; import java.time.LocalDateTime; import java.util.*;
public class EntregaDAO {
 public void create(Entrega x)throws SQLException{try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement("INSERT INTO entregas(id_pedido,id_repartidor,fecha_hora) VALUES(?,?,?)")){s.setInt(1,x.getPedidoId());s.setInt(2,x.getRepartidorId());s.setTimestamp(3,Timestamp.valueOf(x.getFechaHora()));s.executeUpdate();}}
 public List<Entrega> readAll()throws SQLException{return readAll(0,0);}
 public List<Entrega> readAll(int pedidoId,int repartidorId)throws SQLException{List<Entrega> out=new ArrayList<>();String q="SELECT e.id_entrega,e.id_pedido,e.id_repartidor,e.fecha_hora,p.direccion,r.nombre FROM entregas e JOIN pedidos p ON p.id_pedido=e.id_pedido JOIN repartidores r ON r.id_repartidor=e.id_repartidor WHERE (?=0 OR e.id_pedido=?) AND (?=0 OR e.id_repartidor=?) ORDER BY e.id_entrega";try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement(q)){s.setInt(1,pedidoId);s.setInt(2,pedidoId);s.setInt(3,repartidorId);s.setInt(4,repartidorId);try(ResultSet rs=s.executeQuery()){while(rs.next())out.add(new Entrega(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getTimestamp(4).toLocalDateTime(),rs.getString(5),rs.getString(6)));}}return out;}
 public void update(int id,Entrega x)throws SQLException{try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement("UPDATE entregas SET id_pedido=?,id_repartidor=?,fecha_hora=? WHERE id_entrega=?")){s.setInt(1,x.getPedidoId());s.setInt(2,x.getRepartidorId());s.setTimestamp(3,Timestamp.valueOf(x.getFechaHora()));s.setInt(4,id);s.executeUpdate();}}
 public void delete(int id)throws SQLException{try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement("DELETE FROM entregas WHERE id_entrega=?")){s.setInt(1,id);s.executeUpdate();}}
}
