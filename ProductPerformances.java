/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProductPerformances.java
 *
 * Created on 06-Mar-2011, 11:19:35
 */

package proffittcenter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dave
 */
public class ProductPerformances extends EscapeDialog {

    String productPerformances = " SELECT Products.ID AS ID,"
            + "Products.Description AS Description, "
            + "Suppliers.Description AS Supplier, "
            + "Packs.Size AS 'PackSize', "
            + "PackSuppliers.Price AS 'Wholesale_price', "
            + "Products.Price as 'RetailPrice', "
//          0=None, Registered, Unregistered 1=salestax or wholesale
            + "IF(?=0,(10000 - (PackSuppliers.Price  * (10000 + Taxes.Rate)) / (Packs.Size * Products.Price)),"
            + " (10000 - PackSuppliers.Price * 10000 / (Packs.Size * Products.Price*10))) AS Margin "
            + "FROM Products,Suppliers,Packs,PackSuppliers,Taxes,Skus "
            + "WHERE PackSuppliers.Supplier=Suppliers.ID "
            + "AND Packs.ID=PackSuppliers.Pack "
            + "AND Packs.Product =Products.ID "
            + "AND Products.Sku=Skus.ID "
            + "AND Skus.Tax=Taxes.ID "
            + "ORDER BY PackSuppliers.WhenCreated DESC,Description ";
    private JDBCTableModel jtm;
    private ResourceBundle bundle = ResourceBundle.getBundle("proffittcenter/resource/ProductPerformances");;
    private final MyTableCellRenderer mtcr;
    private int value;
    
    /** Creates new form ProductPerformances */
    public ProductPerformances(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mtcr = new MyTableCellRenderer();
        jTable1.setDefaultRenderer(Money.class, mtcr);
        jTable1.setDefaultRenderer(PerCent.class, mtcr);
        jTable1.setSurrendersFocusOnKeystroke(true);
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("proffittcenter/resource/ProductPerformances"); // NOI18N
        setTitle(bundle.getString("Title")); // NOI18N
        setName("productPerformances"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void execute(){
        Audio.play("TaDa");
        try {
            PreparedStatement ps = Main.getConnection().prepareStatement(productPerformances);
            Regime rr = Main.shop.regimeIs();
            if (rr == Regime.NONE || rr == Regime.REGISTERED || rr == Regime.UNREGISTERED) {
                value=0;
            } else if (rr == Regime.WHOLESALE || rr == Regime.SALESTAX) {
                value=1;
            }
            ps.setInt(1, value);
            HashSet  editable=new HashSet();
            jtm = new JDBCTableModel( ps, bundle, jTable1,editable);
        } catch (SQLException ex) {
            Logger.getLogger(ProductPerformances.class.getName()).log(Level.SEVERE, null, ex);
        }
        setVisible(true);
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProductPerformances dialog = new ProductPerformances(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.execute();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
