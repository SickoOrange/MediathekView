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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import mediathek.Daten;
import mediathek.Konstanten;
import mediathek.daten.DDaten;
import mediathek.gui.PanelVorlage;
import mediathek.gui.dialog.DialogHilfe;
import mediathek.tool.GuiFunktionen;
import mediathek.tool.GuiKonstanten;

public class PanelEinstellungen extends PanelVorlage {

    private static String userAgentManuel = "";

    public PanelEinstellungen(DDaten d) {
        super(d);
        initComponents();
        ddaten = d;
        init();
        jSpinnerDownload.addChangeListener(new BeobSpinnerDownload());
        jCheckBoxFehler.addActionListener(new BeobCheckboxFehler());
        jCheckBoxHinweise.addActionListener(new BeobHinweise());
        jCheckBoxMax.addActionListener(new BeobCheckboxMax());
        String[] theme = new String[GuiKonstanten.THEME.length];
        for (int i = 0; i < GuiKonstanten.THEME.length; ++i) {
            theme[i] = GuiKonstanten.THEME[i][0];
        }
        jComboBoxLook.setModel(new DefaultComboBoxModel(theme));
        if (Daten.system[Konstanten.SYSTEM_LOOK_NR].equals("")) {
            Daten.system[Konstanten.SYSTEM_LOOK_NR] = "1";
        }
        jComboBoxLook.setSelectedIndex(Integer.parseInt(Daten.system[Konstanten.SYSTEM_LOOK_NR]));
        jComboBoxLook.addActionListener(new BeobLook());
        jButtonHilfe.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogHilfe(null, true, "\n"
                        + "Dieser Text wird als User-Agent\n"
                        + "an den Webserver übertragen. Das enstpricht\n"
                        + "der Kennung die auch die Browser senden.").setVisible(true);
            }
        });
        jRadioButtonAuto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setUserAgent();
            }
        });
        jRadioButtonManuel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setUserAgent();
            }
        });
        jTextFieldUserAgent.getDocument().addDocumentListener(new BeobUserAgent());
        jCheckBoxFlvstreamer.addActionListener(new BeobRtmp());
        jCheckBoxAufloesung.addActionListener(new BeobAufloesung());
    }

    @Override
    public void neuLaden() {
        init();
    }

    @Override
    public void neuLadenSofort() {
        jCheckBoxFehler.setSelected(Boolean.parseBoolean(Daten.system[Konstanten.SYSTEM_FEHLER_ANZEIGEN_NR]));
        jCheckBoxHinweise.setSelected(Boolean.parseBoolean(Daten.system[Konstanten.SYSTEM_HINWEIS_ANZEIGEN_NR]));
    }

    private void init() {
        initUserAgent();
        jCheckBoxFehler.setSelected(Boolean.parseBoolean(Daten.system[Konstanten.SYSTEM_FEHLER_ANZEIGEN_NR]));
        jCheckBoxHinweise.setSelected(Boolean.parseBoolean(Daten.system[Konstanten.SYSTEM_HINWEIS_ANZEIGEN_NR]));
        jCheckBoxMax.setSelected(Boolean.parseBoolean(Daten.system[Konstanten.SYSTEM_START_MAX_NR]));
        if (Daten.system[Konstanten.SYSTEM_MAX_DOWNLOAD_NR].equals("")) {
            jSpinnerDownload.setValue(1);
            Daten.system[Konstanten.SYSTEM_MAX_DOWNLOAD_NR] = "1";
        } else {
            jSpinnerDownload.setValue(Integer.parseInt(Daten.system[Konstanten.SYSTEM_MAX_DOWNLOAD_NR]));
        }
    }

    private void initUserAgent() {
        jRadioButtonAuto.setText(Konstanten.USER_AGENT_DEFAULT);
        if (Daten.isUserAgentAuto()) {
            jRadioButtonAuto.setSelected(true);
            jTextFieldUserAgent.setText(userAgentManuel);
        } else {
            jRadioButtonManuel.setSelected(true);
            jTextFieldUserAgent.setText(Daten.system[Konstanten.SYSTEM_USER_AGENT_NR]);
        }
        jTextFieldUserAgent.setEditable(jRadioButtonManuel.isSelected());
    }

    private void setUserAgent() {
        Daten.setGeaendert();
        if (jRadioButtonAuto.isSelected()) {
            Daten.setUserAgentAuto();
            userAgentManuel = jTextFieldUserAgent.getText();
            jTextFieldUserAgent.setText(userAgentManuel);
            Daten.system[Konstanten.SYSTEM_USER_AGENT_NR] = "";
        } else {
            if (userAgentManuel.equals("")) {
                userAgentManuel = Konstanten.USER_AGENT_DEFAULT;
            }
            Daten.system[Konstanten.SYSTEM_USER_AGENT_NR] = userAgentManuel;
            jTextFieldUserAgent.setText(Daten.system[Konstanten.SYSTEM_USER_AGENT_NR]);
        }
        jTextFieldUserAgent.setEditable(jRadioButtonManuel.isSelected());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jCheckBoxFehler = new javax.swing.JCheckBox();
        jCheckBoxMax = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxLook = new javax.swing.JComboBox();
        jCheckBoxHinweise = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSpinnerDownload = new javax.swing.JSpinner();
        jCheckBoxFlvstreamer = new javax.swing.JCheckBox();
        jCheckBoxAufloesung = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldUserAgent = new javax.swing.JTextField();
        jButtonHilfe = new javax.swing.JButton();
        jRadioButtonAuto = new javax.swing.JRadioButton();
        jRadioButtonManuel = new javax.swing.JRadioButton();

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jCheckBoxFehler.setText("Fehlermeldungen anzeigen");

        jCheckBoxMax.setText("Programmfenster maximiert starten");

        jLabel1.setText("Look and Feel:");

        jComboBoxLook.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBoxHinweise.setText("Hinweise anzeigen");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxHinweise)
                            .addComponent(jCheckBoxFehler))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxMax)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxLook, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(16, 16, 16))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxFehler)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxHinweise)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxLook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Allgemeine Einstellungen der Filme"));

        jLabel3.setText("max. parallele Downloads beim Laden der Abos ( empf: 2 ):");

        jSpinnerDownload.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9, 1));

        jCheckBoxFlvstreamer.setText("Flash-Filme ( RTMP-Protokoll ): Url für flvstreamer vorbereiten");

        jCheckBoxAufloesung.setText("Filme in niedriger Auflösung zeigen");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBoxFlvstreamer)
                        .addComponent(jCheckBoxAufloesung))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jCheckBoxFlvstreamer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxAufloesung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinnerDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("User-Agent"));

        jButtonHilfe.setText("Hilfe");

        buttonGroup1.add(jRadioButtonAuto);
        jRadioButtonAuto.setText("jRadioButton1");

        buttonGroup1.add(jRadioButtonManuel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonManuel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUserAgent))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonHilfe)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonAuto)
                    .addComponent(jButtonHilfe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jRadioButtonManuel)
                    .addComponent(jTextFieldUserAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonHilfe, jTextFieldUserAgent});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonHilfe;
    private javax.swing.JCheckBox jCheckBoxAufloesung;
    private javax.swing.JCheckBox jCheckBoxFehler;
    private javax.swing.JCheckBox jCheckBoxFlvstreamer;
    private javax.swing.JCheckBox jCheckBoxHinweise;
    private javax.swing.JCheckBox jCheckBoxMax;
    private javax.swing.JComboBox jComboBoxLook;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonAuto;
    private javax.swing.JRadioButton jRadioButtonManuel;
    private javax.swing.JSpinner jSpinnerDownload;
    private javax.swing.JTextField jTextFieldUserAgent;
    // End of variables declaration//GEN-END:variables

    private class BeobSpinnerDownload implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            Daten.system[Konstanten.SYSTEM_MAX_DOWNLOAD_NR] =
                    String.valueOf(((Number) jSpinnerDownload.getModel().getValue()).intValue());
            DDaten.setGeaendert();
        }
    }

    private class BeobCheckboxFehler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Daten.system[Konstanten.SYSTEM_FEHLER_ANZEIGEN_NR] = Boolean.toString(jCheckBoxFehler.isSelected());
            ddaten.setGeaendertPanel();
        }
    }

    private class BeobHinweise implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Daten.system[Konstanten.SYSTEM_HINWEIS_ANZEIGEN_NR] = Boolean.toString(jCheckBoxHinweise.isSelected());
            ddaten.setGeaendertPanel();
        }
    }

    private class BeobCheckboxMax implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Daten.system[Konstanten.SYSTEM_START_MAX_NR] = Boolean.toString(jCheckBoxMax.isSelected());
            ddaten.setGeaendertPanel();
        }
    }

    private class BeobUserAgent implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            tus();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            tus();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            tus();
        }

        private void tus() {
            Daten.setUserAgentManuel(jTextFieldUserAgent.getText());
            Daten.setGeaendert();
        }
    }

    private class BeobLook implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (GuiFunktionen.setLook(ddaten.mediathekGui, Integer.parseInt(String.valueOf(jComboBoxLook.getSelectedIndex())))) {
                Daten.system[Konstanten.SYSTEM_LOOK_NR] = String.valueOf(jComboBoxLook.getSelectedIndex());
                ddaten.setGeaendertPanel();
            } else {
                Daten.system[Konstanten.SYSTEM_LOOK_NR] = "1";
                jComboBoxLook.setSelectedIndex(1);
                GuiFunktionen.setLook(ddaten.mediathekGui, 1);
            }

        }
    }

    private class BeobRtmp implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //////////////////////////////////
            DDaten.system[Konstanten.SYSTEM_RTMP_FLVSTREAMER_NR] = Boolean.toString(jCheckBoxFlvstreamer.isSelected());
            DDaten.setGeaendert();
        }
    }

    private class BeobAufloesung implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /////////////////////////////
            DDaten.system[Konstanten.SYSTEM_LEITUNG_LOW_NR] = Boolean.toString(jCheckBoxAufloesung.isSelected());
            DDaten.setGeaendert();
        }
    }
}