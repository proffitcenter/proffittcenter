/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CashUpSelect.java
 *
 * Created on 01-Dec-2009, 10:39:05
 */
package proffittcenter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author HP_Owner
 */
public class SelectCashup extends EscapeDialog {

    private int tillID;
    private int selection = -1;
    private int idSelected = -1;
    DefaultListModel listModel;
    String findCashUps = "SELECT ID,DATE(WhenCreated)AS WhenCreated,TillID FROM CashUps WHERE (TillID=? OR 0=?) AND Reconciled =0 ORDER BY WhenCreated DESC  ";
    String findFloats = "SELECT ID FROM CashUps WHERE (TillID=? OR 0=?) AND Reconciled =1 ORDER BY WhenCreated DESC  ";
    ResourceBundle bundle = ResourceBundle.getBundle("proffittcenter/resource/CashupSelect");
    Preferences root = Preferences.userNodeForPackage(getClass());
    private int returnValue;

    /** Creates new form CashUpSelect */
    public SelectCashup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Main.mainHelpBroker.enableHelpKey(getRootPane(), "Cashupselect", Main.mainHelpSet);
        JRootPane rp = getRootPane();
        rp.setDefaultButton(okButton);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        cashupIDList = new JList();
        jLabel1 = new JLabel();
        okButton = new JButton();
        closeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ResourceBundle bundle = ResourceBundle.getBundle("proffittcenter/resource/CashupSelect"); // NOI18N
        setTitle(bundle.getString("title")); // NOI18N
        setName("SelectCashup"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        cashupIDList.setName("cashupIDList"); // NOI18N
        cashupIDList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                cashupIDListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(cashupIDList);

        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setText(bundle.getString("SelectCashup.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        okButton.setIcon(new ImageIcon(getClass().getResource("/proffittcenter/resource/OK.png"))); // NOI18N
        okButton.setContentAreaFilled(false);
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        closeButton.setIcon(new ImageIcon(getClass().getResource("/proffittcenter/resource/Close24.png"))); // NOI18N
        closeButton.setContentAreaFilled(false);
        closeButton.setName("closeButton"); // NOI18N
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        closeButton.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                closeButtonKeyReleased(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(SwingConstants.VERTICAL, new Component[] {closeButton, okButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed

                setVisible(false);
}//GEN-LAST:event_closeButtonActionPerformed

    private void closeButtonKeyReleased(KeyEvent evt) {//GEN-FIRST:event_closeButtonKeyReleased
        setVisible(false);
}//GEN-LAST:event_closeButtonKeyReleased

    private void okButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        returnValue=idSelected;
        setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cashupIDListValueChanged(ListSelectionEvent evt) {//GEN-FIRST:event_cashupIDListValueChanged
        int is = cashupIDList.getSelectedIndex();
        String s;
        if(is!=-1){
            s = (String) listModel.getElementAt(is);
        } else {
            return;
        }
        String[] c=s.split("\\s");
        idSelected = Integer.parseInt(c[0]);
    }//GEN-LAST:event_cashupIDListValueChanged

    public int executeCashups(int selectedTill) {
        try {
            returnValue=-1;
            this.setTitle("Select cashup");
            tillID = selectedTill;
            PreparedStatement selectionStatement = Main.getConnection().prepareStatement(findCashUps);
            selectionStatement.setInt(1, tillID);
            selectionStatement.setInt(2, tillID);
            ResultSet rs = selectionStatement.executeQuery();
            listModel = new DefaultListModel();
            while (rs.next()) {
                listModel.addElement(rs.getString("ID")+"     "+
                        rs.getString("TillID")
                        +"  "+rs.getString("WhenCreated"));
            }
            if(listModel.isEmpty()){
                //nothing to display
                Audio.play("Ring");
                return returnValue;
            }
            cashupIDList.setModel(listModel);
            idSelected = -1;
            Audio.play("TaDa");
            setVisible(true);
            return returnValue;
        } catch (SQLException ex) {
            Logger.getLogger(SelectCashup.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

      public int executeFloat(int selectedTill) {
        try {
            returnValue=-1;
            this.setTitle("Select float");
            tillID = selectedTill;
            PreparedStatement selectionStatement = Main.getConnection().prepareStatement(findFloats);
            selectionStatement.setInt(1, tillID);
            selectionStatement.setInt(2, tillID);
            ResultSet rs = selectionStatement.executeQuery();
            listModel = new DefaultListModel();
            while (rs.next()) {
                listModel.addElement(rs.getString("ID"));
            }
            if(listModel.isEmpty()){
                //nothing to display
                return 0;
            }
            cashupIDList.setModel(listModel);
            idSelected = -1;
            Audio.play("TaDa");
            //FormRestore.createPosition(this, root);
            setVisible(true);
            //FormRestore.destroyPosition(this, root);
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SelectCashup.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                SelectCashup dialog = new SelectCashup(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JList cashupIDList;
    private JButton closeButton;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JButton okButton;
    // End of variables declaration//GEN-END:variables
}
