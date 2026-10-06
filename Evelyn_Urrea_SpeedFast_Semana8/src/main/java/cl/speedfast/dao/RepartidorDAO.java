package cl.speedfast.dao;
import cl.speedfast.db.ConexionDB; import cl.speedfast.model.Repartidor;
import java.sql.*; import java.util.*;
public class RepartidorDAO {
 public void create(Repartidor x)throws SQLException{String q="INSERT INTO repartidores(nombre) VALUES(?)";try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement(q)){s.setString(1,x.getNombre());s.executeUpdate();}}
 public List<Repartidor> readAll()throws SQLException{List<Repartidor> out=new ArrayList<>();try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement("SELECT id_repartidor,nombre FROM repartidores ORDER BY id_repartidor");ResultSet r=s.executeQuery()){while(r.next())out.add(new Repartidor(r.getInt("id_repartidor"),r.getString("nombre")));}return out;}
 public void update(int id,Repartidor x)throws SQLException{try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement("UPDATE repartidores SET nombre=? WHERE id_repartidor=?")){s.setString(1,x.getNombre());s.setInt(2,id);s.executeUpdate();}}
 public void delete(int id)throws SQLException{try(Connection c=ConexionDB.getConnection();PreparedStatement s=c.prepareStatement("DELETE FROM repartidores WHERE id_repartidor=?")){s.setInt(1,id);s.executeUpdate();}}
}
