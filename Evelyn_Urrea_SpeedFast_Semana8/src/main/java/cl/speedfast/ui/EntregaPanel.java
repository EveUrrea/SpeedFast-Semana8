package cl.speedfast.ui;

import cl.speedfast.dao.EntregaDAO;
import cl.speedfast.dao.PedidoDAO;
import cl.speedfast.dao.RepartidorDAO;
import cl.speedfast.model.Entrega;
import cl.speedfast.model.Pedido;
import cl.speedfast.model.Repartidor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Formulario CRUD y filtros de entregas. */
public class EntregaPanel extends JPanel {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final EntregaDAO dao = new EntregaDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final RepartidorDAO repartidorDAO = new RepartidorDAO();
    private final JComboBox<Pedido> pedido = new JComboBox<>(), filtroPedido = new JComboBox<>();
    private final JComboBox<Repartidor> repartidor = new JComboBox<>(), filtroRepartidor = new JComboBox<>();
    private final JTextField fecha = new JTextField(16);
    private final DefaultTableModel modelo = UiSupport.model("ID", "ID Pedido", "ID Repartidor", "Dirección", "Repartidor", "Fecha y hora");
    private final JTable tabla = UiSupport.table(modelo);

    public EntregaPanel() {
        setLayout(new BorderLayout(8, 8));
        JPanel superior = new JPanel(new GridLayout(3, 1));
        superior.add(UiSupport.fields(new JLabel("Pedido:"), pedido, new JLabel("Repartidor:"), repartidor,
                new JLabel("Fecha/hora (AAAA-MM-DD HH:MM):"), fecha));
        JButton guardar = new JButton("Guardar"), eliminar = new JButton("Eliminar"), limpiar = new JButton("Limpiar"), actualizar = new JButton("Actualizar combos");
        superior.add(UiSupport.buttons(guardar, eliminar, limpiar, actualizar));
        JButton filtrar = new JButton("Filtrar"), mostrarTodo = new JButton("Mostrar todas");
        superior.add(UiSupport.fields(new JLabel("Pedido:"), filtroPedido, new JLabel("Repartidor:"), filtroRepartidor, filtrar, mostrarTodo));
        add(superior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        guardar.addActionListener(e -> guardar());
        eliminar.addActionListener(e -> eliminar());
        limpiar.addActionListener(e -> { tabla.clearSelection(); fecha.setText(ahora()); });
        actualizar.addActionListener(e -> { cargarCombos(); cargarTabla(); });
        filtrar.addActionListener(e -> cargarTabla(idPedidoFiltro(), idRepartidorFiltro()));
        mostrarTodo.addActionListener(e -> { filtroPedido.setSelectedIndex(0); filtroRepartidor.setSelectedIndex(0); cargarTabla(); });
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                int fila = tabla.convertRowIndexToModel(tabla.getSelectedRow());
                seleccionarPedido(Integer.parseInt(modelo.getValueAt(fila, 1).toString()));
                seleccionarRepartidor(Integer.parseInt(modelo.getValueAt(fila, 2).toString()));
                fecha.setText(modelo.getValueAt(fila, 5).toString());
            }
        });
        fecha.setText(ahora());
        cargarCombos();
        cargarTabla();
    }

    private String ahora() { return LocalDateTime.now().format(FORMATO); }
    private int idSeleccionado() { int f=tabla.getSelectedRow(); return f<0?-1:Integer.parseInt(modelo.getValueAt(tabla.convertRowIndexToModel(f),0).toString()); }
    private int idPedidoFiltro() { return filtroPedido.getSelectedItem() instanceof Pedido p ? p.getId() : 0; }
    private int idRepartidorFiltro() { return filtroRepartidor.getSelectedItem() instanceof Repartidor r ? r.getId() : 0; }
    private void seleccionarPedido(int id) { for(int i=0;i<pedido.getItemCount();i++) if(pedido.getItemAt(i).getId()==id){pedido.setSelectedIndex(i);break;} }
    private void seleccionarRepartidor(int id) { for(int i=0;i<repartidor.getItemCount();i++) if(repartidor.getItemAt(i).getId()==id){repartidor.setSelectedIndex(i);break;} }

    private void cargarCombos() {
        try {
            pedido.removeAllItems(); filtroPedido.removeAllItems(); filtroPedido.addItem(null);
            for(Pedido p:pedidoDAO.readAll()){pedido.addItem(p);filtroPedido.addItem(p);}
            repartidor.removeAllItems(); filtroRepartidor.removeAllItems(); filtroRepartidor.addItem(null);
            for(Repartidor r:repartidorDAO.readAll()){repartidor.addItem(r);filtroRepartidor.addItem(r);}
        } catch(Exception ex) { UiSupport.error(this,ex); }
    }
    private void guardar() {
        if(pedido.getSelectedItem()==null || repartidor.getSelectedItem()==null){JOptionPane.showMessageDialog(this,"Registra primero un pedido y un repartidor.");return;}
        try {
            LocalDateTime momento=LocalDateTime.parse(fecha.getText().trim(),FORMATO);
            Pedido p=(Pedido)pedido.getSelectedItem(); Repartidor r=(Repartidor)repartidor.getSelectedItem();
            Entrega x=new Entrega(p.getId(),r.getId(),momento); int id=idSeleccionado();
            if(id<0){dao.create(x);JOptionPane.showMessageDialog(this,"Entrega registrada.");}
            else{dao.update(id,x);JOptionPane.showMessageDialog(this,"Entrega actualizada.");}
            tabla.clearSelection();fecha.setText(ahora());cargarCombos();cargarTabla();
        } catch(java.time.format.DateTimeParseException ex){JOptionPane.showMessageDialog(this,"Usa el formato AAAA-MM-DD HH:MM para la fecha y hora.");}
        catch(Exception ex){UiSupport.error(this,ex);}
    }
    private void eliminar() {
        int id=idSeleccionado(); if(id<0){JOptionPane.showMessageDialog(this,"Selecciona una entrega.");return;}
        if(JOptionPane.showConfirmDialog(this,"¿Eliminar entrega seleccionada?","Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            try{dao.delete(id);JOptionPane.showMessageDialog(this,"Entrega eliminada.");cargarCombos();cargarTabla();}
            catch(Exception ex){UiSupport.error(this,ex);}
    }
    private void cargarTabla(){cargarTabla(0,0);}
    private void cargarTabla(int pedidoId,int repartidorId){
        try{modelo.setRowCount(0);for(Entrega x:dao.readAll(pedidoId,repartidorId))modelo.addRow(new Object[]{x.getId(),x.getPedidoId(),x.getRepartidorId(),x.getDireccion(),x.getRepartidor(),x.getFechaHora().format(FORMATO)});}
        catch(Exception ex){UiSupport.error(this,ex);}
    }
}
