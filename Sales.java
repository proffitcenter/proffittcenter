/*
 * Sales.java
 *
 * Created on 10 April 2008, 11:53
 */
package proffittcenter;

import java.awt.Cursor;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 *
 * @author  HP_Owner
 */
public class Sales extends EscapeDialog  {

    private Preferences root = Preferences.userNodeForPackage(getClass());
    private static ResourceBundle bundle = ResourceBundle.getBundle("proffittcenter/resource/Sales");
    private int till;
    private int selection = -1;
    private Integer id;
    private PreparedStatement salesStatement;
    private JDBCTableModel jtm;
    private final MyTableCellRenderer mtcr;
    private Date fromDate;
    private Date toDate;

    /** Creates new form Sales
     * @param parent
     */
    public Sales(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mtcr = new MyTableCellRenderer();
        jTable1.setDefaultRenderer(Money.class, mtcr);
        jTable1.setDefaultRenderer(PerCent.class, mtcr);
        Main.mainHelpBroker.enableHelpKey(getRootPane(), "Sales", Main.mainHelpSet);
        getRootPane().setDefaultButton(detailsButton);
    }

    public void execute(int tillId,java.sql.Date fromDate,java.sql.Date toDate) {
        till = tillId;
        this.fromDate= fromDate;
        this.toDate = toDate;
        drawGrid();
        selection = -1;
        id = 0;
        Audio.play("Tada");
        invoiceButton.setEnabled(Main.hardware.isInvoicePrinter());
        jTextField1.requestFocus();
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        closeButton2 = new javax.swing.JButton();
        detailsButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        invoiceButton = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("proffittcenter/resource/Sales"); // NOI18N
        setTitle(bundle.getString("Sales.title_2")); // NOI18N
        setLocationByPlatform(true);
        setName("Sales"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jTable1.addMouseListener(formListener);
        jTable1.addFocusListener(formListener);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        closeButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proffittcenter/resource/Close24.png"))); // NOI18N
        closeButton2.setBorderPainted(false);
        closeButton2.setContentAreaFilled(false);
        closeButton2.addActionListener(formListener);

        detailsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proffittcenter/resource/Info.png"))); // NOI18N
        detailsButton.setBorderPainted(false);
        detailsButton.setContentAreaFilled(false);
        detailsButton.addActionListener(formListener);

        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proffittcenter/resource/Receipt.png"))); // NOI18N
        printButton.setMnemonic('P');
        printButton.setText(null);
        printButton.setToolTipText(bundle.getString("Sales.printButton.toolTipText")); // NOI18N
        printButton.setContentAreaFilled(false);
        printButton.addActionListener(formListener);

        jTextField1.setBorder(null);
        jTextField1.addKeyListener(formListener);

        invoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proffittcenter/resource/print_edit.gif"))); // NOI18N
        invoiceButton.setToolTipText(bundle.getString("Sales.invoiceButton.toolTipText")); // NOI18N
        invoiceButton.setContentAreaFilled(false);
        invoiceButton.addActionListener(formListener);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invoiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 993, Short.MAX_VALUE)
                .addComponent(detailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(closeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(555, 555, 555)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(555, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(invoiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {closeButton2, detailsButton});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1129, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.FocusListener, java.awt.event.KeyListener, java.awt.event.MouseListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == closeButton2) {
                Sales.this.closeButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == detailsButton) {
                Sales.this.detailsButtonActionPerformed(evt);
            }
            else if (evt.getSource() == printButton) {
                Sales.this.printButtonActionPerformed(evt);
            }
            else if (evt.getSource() == invoiceButton) {
                Sales.this.invoiceButtonActionPerformed(evt);
            }
        }

        public void focusGained(java.awt.event.FocusEvent evt) {
            if (evt.getSource() == jTable1) {
                Sales.this.jTable1FocusGained(evt);
            }
        }

        public void focusLost(java.awt.event.FocusEvent evt) {
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == jTextField1) {
                Sales.this.jTextField1KeyReleased(evt);
            }
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jTable1) {
                Sales.this.jTable1MouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

private void closeButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButton2ActionPerformed
    setVisible(false);
}//GEN-LAST:event_closeButton2ActionPerformed

private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    jTextField1.requestFocus();
}//GEN-LAST:event_jTable1MouseClicked

private void detailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsButtonActionPerformed
    selection = jTable1.getSelectedRow();
    if (selection < 0) {//no selected row
        return;
    }
    selection=jTable1.convertRowIndexToModel(selection);
    id=(Integer) jTable1.getModel().getValueAt(selection, 0);
    Main.sale1.execute(id, 0, false, till,true,true);
    jTextField1.requestFocus();
}//GEN-LAST:event_detailsButtonActionPerformed

private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
    jTextField1.requestFocus();
}//GEN-LAST:event_jTable1FocusGained

private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
    selection = jTable1.getSelectedRow();
    if (selection < 0) {//no selected row
        Main.receiptPrinter.printReceipt(id,true);
        return;
    }
    selection=jTable1.convertRowIndexToModel(selection);
    id=(Integer) jTable1.getModel().getValueAt(selection, 0);
    Main.receiptPrinter.printReceipt(id,true);
    jTextField1.requestFocus();
}//GEN-LAST:event_printButtonActionPerformed

private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
        Audio.play("Beep");
        this.setVisible(false);
    }else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_PRINTSCREEN) {
        selection = jTable1.getSelectedRow();
    if (selection < 0) {//no selected row
        return;
    }
    selection=jTable1.convertRowIndexToModel(selection);
    id=(Integer) jTable1.getModel().getValueAt(selection, 0);
    Main.receiptPrinter.printReceipt(id,true);
    }else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
        selection = jTable1.getSelectedRow();
        if (selection <= 0) {//no selected row
            return;
        }
        jTable1.setRowSelectionInterval(selection-1, selection-1);
    } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
        selection = jTable1.getSelectedRow();
        if (selection >= jTable1.getRowCount()-1) {//no selected row
            return;
        }
        jTable1.setRowSelectionInterval(selection+1, selection+1);
    } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_I) {
        selection = jTable1.getSelectedRow();
        if (selection < 0) {//no selected row
            return;
        }
        selection = jTable1.convertRowIndexToModel(selection);
        id = (Integer) jTable1.getModel().getValueAt(selection, 0);
        Main.sale1.execute(id, 0, false, till, false, false);
    }
}//GEN-LAST:event_jTextField1KeyReleased

private void invoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceButtonActionPerformed
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    selection = jTable1.getSelectedRow();
    if (selection < 0) {//no selected row
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        return;
    }
    selection=jTable1.convertRowIndexToModel(selection);
    id=(Integer) jTable1.getModel().getValueAt(selection, 0);
    Main.invoice.print(id,0,true);
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    jTextField1.requestFocus();
}//GEN-LAST:event_invoiceButtonActionPerformed

private void drawGrid() {
        try {
            salesStatement = Main.getConnection().prepareStatement(SQL.sales );
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            salesStatement.setInt(1, till);
            salesStatement.setInt(2, till);
            salesStatement.setDate(3, fromDate);
            salesStatement.setDate(4, toDate);
            if(till==0){
                salesStatement.setInt(5, Integer.MAX_VALUE);
            } else {
                salesStatement.setInt(5, 1000);
            }
            HashSet  editable=new HashSet();
            jtm = new JDBCTableModel( salesStatement, bundle, jTable1, editable);
        } catch (SQLException ex) {
            Audio.play("Ring");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Sales dialog = new Sales(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton2;
    private javax.swing.JButton detailsButton;
    private javax.swing.JButton invoiceButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton printButton;
    // End of variables declaration//GEN-END:variables

}
