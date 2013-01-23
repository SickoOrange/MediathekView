/*    
 *    MediathekView
 *    Copyright (C) 2008   W. Xaver
 *    W.Xaver[at]googlemail.com
 *    http://zdfmediathk.sourceforge.net/
 *    
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mediathek.gui.dialogEinstellungen;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import mediathek.daten.DDaten;
import mediathek.tool.EscBeenden;
import mediathek.tool.GuiFunktionen;
import mediathek.tool.Log;

public class DialogZiel extends javax.swing.JDialog {

    public boolean ok = false;
    public String ziel = "";
    private Component parentComponent = null;
    private DDaten ddaten = null;

    /**
     *
     * @param parent
     * @param modal
     * @param d
     * @param pfad
     * @param name
     */
    public DialogZiel(java.awt.Frame parent, DDaten dd, boolean modal, String pfad, String name) {
        super(parent, modal);
        parentComponent = parent;
        ddaten = dd;
        initComponents();
        jButtonOk.addActionListener(new OkBeobachter());
        jButtonZiel.addActionListener(new ZielBeobachter());
        if (name.equals("")) {
            name = "name.xml";
        }
        jTextFieldPfad.setText(GuiFunktionen.addsPfad(pfad.equals("") ? GuiFunktionen.getHomePath() : pfad, name));
        new EscBeenden(this) {
            @Override
            public void beenden_() {
                ok = false;
                beenden();
            }
        };
    }

    private boolean check() {
        String pfad = jTextFieldPfad.getText();
        if (pfad.equals("")) {
            JOptionPane.showMessageDialog(parentComponent, "Pfad ist leer", "Fehlerhafter Pfad!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (new File(pfad).exists()) {
            if (JOptionPane.showConfirmDialog(parentComponent, "Die Datei existiert schon!", "Überschreiben?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return false;
            }
        }
        ziel = pfad;
        return true;
    }

    private void beenden() {
        this.dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonZiel = new javax.swing.JButton();
        jTextFieldPfad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonZiel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/fileopen_16.png"))); // NOI18N

        jLabel1.setText("Ziel:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPfad, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonZiel)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPfad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonZiel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonZiel, jTextFieldPfad});

        jButtonOk.setText("Ok");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonOk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonZiel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldPfad;
    // End of variables declaration//GEN-END:variables

    private class OkBeobachter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (check()) {
                ok = true;
                beenden();
            }
        }
    }

    private class ZielBeobachter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //we can use native chooser on Mac...
            if (ddaten.mediathekGui.isMac()) {
                FileDialog chooser = new FileDialog(ddaten.mediathekGui, "Logdatei speichern");
                chooser.setMode(FileDialog.SAVE);
                chooser.setVisible(true);
                if (chooser.getFile() != null) {
                    try {
                        jTextFieldPfad.setText(new File(chooser.getFile()).getAbsolutePath());
                    } catch (Exception ex) {
                        Log.fehlerMeldung(639874637, Log.FEHLER_ART_PROG, "DialogZielDatei.ZielBeobachter", ex);
                    }
                }
            } else {
                int returnVal;
                JFileChooser chooser = new JFileChooser();
                if (!jTextFieldPfad.getText().equals("")) {
                    String pfad = jTextFieldPfad.getText();
                    if (pfad.contains(File.separator)) {
                        pfad = pfad.substring(0, pfad.lastIndexOf(File.separator));
                        chooser.setCurrentDirectory(new File(pfad));
                    } else {
                        chooser.setCurrentDirectory(new File(jTextFieldPfad.getText()));
                    }
                }
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setFileHidingEnabled(false);
                returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        jTextFieldPfad.setText(chooser.getSelectedFile().getAbsolutePath());
                    } catch (Exception ex) {
                        Log.fehlerMeldung(362259105, Log.FEHLER_ART_PROG, "DialogZielDatei.ZielBeobachter", ex);
                    }
                }
            }
        }
    }
}
