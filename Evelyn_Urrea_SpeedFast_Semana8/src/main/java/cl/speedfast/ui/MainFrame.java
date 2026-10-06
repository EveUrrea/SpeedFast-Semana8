package cl.speedfast.ui;
import javax.swing.*; import java.awt.*;
public class MainFrame extends JFrame {
 public MainFrame(){super("SpeedFast — Gestión de pedidos");setDefaultCloseOperation(EXIT_ON_CLOSE);setSize(900,600);setLocationRelativeTo(null);JTabbedPane tabs=new JTabbedPane();tabs.addTab("Repartidores",new RepartidorPanel());tabs.addTab("Pedidos",new PedidoPanel());tabs.addTab("Entregas",new EntregaPanel());add(tabs,BorderLayout.CENTER);}
}
