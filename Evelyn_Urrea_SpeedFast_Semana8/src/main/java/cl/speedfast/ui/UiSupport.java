package cl.speedfast.ui;
import javax.swing.*; import javax.swing.table.DefaultTableModel; import java.awt.*;
final class UiSupport {
 private UiSupport(){}
 static DefaultTableModel model(String...cols){return new DefaultTableModel(cols,0){@Override public boolean isCellEditable(int r,int c){return false;}};}
 static JTable table(DefaultTableModel m){JTable t=new JTable(m);t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);t.setAutoCreateRowSorter(true);return t;}
 static JPanel buttons(JButton...bs){JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT));for(JButton b:bs)p.add(b);return p;}
 static JPanel fields(JComponent...cs){JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT));for(JComponent c:cs)p.add(c);return p;}
 static void error(Component p,Exception e){JOptionPane.showMessageDialog(p,"No se pudo completar la operación. Revisa la conexión y los datos.\nDetalle: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
 static boolean required(String s){return s!=null&&!s.trim().isEmpty();}
}
