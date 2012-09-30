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
package mediathek.tool;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import mediathek.controller.io.starter.Start;
import mediathek.daten.DDaten;
import mediathek.daten.DatenDownload;

public class CellRendererDownloads extends DefaultTableCellRenderer {

    private DDaten ddaten;
    private JProgressBar progressBar = new JProgressBar(0, 100);
    private JPanel panel = new JPanel(new BorderLayout());

    public CellRendererDownloads(DDaten d) {
        ddaten = d;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        try {
            setBackground(null);
            setForeground(null);
            setFont(null);
            setIcon(null);
            setHorizontalAlignment(SwingConstants.LEADING);
            super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            int r = table.convertRowIndexToModel(row);
            int c = table.convertColumnIndexToModel(column);
            String url = table.getModel().getValueAt(r, DatenDownload.DOWNLOAD_URL_NR).toString();
            // Abos
            boolean abo = !table.getModel().getValueAt(r, DatenDownload.DOWNLOAD_ABO_NR).equals("");
            // Starts
            Start s = ddaten.starterClass.getStart(url);
            if (s != null) {
                switch (s.status) {
                    case Start.STATUS_INIT:
                        if (isSelected) {
                            setBackground(GuiKonstanten.DOWNLOAD_FARBE_WAIT_SEL);
                            panel.setBackground(GuiKonstanten.DOWNLOAD_FARBE_WAIT_SEL);
                        } else {
                            setBackground(GuiKonstanten.DOWNLOAD_FARBE_WAIT);
                            panel.setBackground(GuiKonstanten.DOWNLOAD_FARBE_WAIT);
                        }
                        break;
                    case Start.STATUS_RUN:
                        if (isSelected) {
                            setBackground(GuiKonstanten.DOWNLOAD_FARBE_RUN_SEL);
                            panel.setBackground(GuiKonstanten.DOWNLOAD_FARBE_RUN_SEL);
                        } else {
                            setBackground(GuiKonstanten.DOWNLOAD_FARBE_RUN);
                            panel.setBackground(GuiKonstanten.DOWNLOAD_FARBE_RUN);
                        }
                        break;
                    case Start.STATUS_FERTIG:
                        if (isSelected) {
                            setBackground(GuiKonstanten.DOWNLOAD_FARBE_FERTIG_SEL);
                            panel.setBackground(GuiKonstanten.DOWNLOAD_FARBE_FERTIG_SEL);
                        } else {
                            setBackground(GuiKonstanten.DOWNLOAD_FARBE_FERTIG);
                            panel.setBackground(GuiKonstanten.DOWNLOAD_FARBE_FERTIG);
                        }
                        break;
                    case Start.STATUS_ERR:
                        if (isSelected) {
                            setBackground(GuiKonstanten.DOWNLOAD_FARBE_ERR_SEL);
                            panel.setBackground(GuiKonstanten.DOWNLOAD_FARBE_ERR_SEL);
                        } else {
                            setBackground(GuiKonstanten.DOWNLOAD_FARBE_ERR);
                            panel.setBackground(GuiKonstanten.DOWNLOAD_FARBE_ERR);
                        }
                        break;
                }
                if (c == DatenDownload.DOWNLOAD_PROGRESS_NR) {
                    progressBar = new JProgressBar(0, 1000);
                    progressBar.setBorder(BorderFactory.createEmptyBorder());
                    progressBar.setStringPainted(true);
                    panel = new JPanel(new BorderLayout());
                    panel.add(progressBar);
                    panel.setBorder(BorderFactory.createEmptyBorder());
                    int i = Integer.parseInt(s.datenDownload.arr[DatenDownload.DOWNLOAD_PROGRESS_NR]);
                    setHorizontalAlignment(SwingConstants.CENTER);
                    if (i == -1) {
                        // noch nicht gestartet
                        this.setText("");
                    } else if (i == 0) {
                        this.setText("warten");
                    } else if (i == 1) {
                        this.setText("gestartet");
                    } else if (1 < i && i < 1000) {
                        progressBar.setValue(i);
                        double d = i / 10.0;
                        progressBar.setString(Double.toString(d) + "%");
                        return panel;
                    } else if (i == 1000) {
                        if (s != null) {
                            if (s.status == Start.STATUS_ERR) {
                                this.setText("fehlerhaft");
                            } else {
                                this.setText("fertig");
                            }
                        } else {
                            this.setText("fertig");
                        }
                    }
                }
            } else {
                if (c == DatenDownload.DOWNLOAD_PROGRESS_NR) {
                    this.setText("");
                }
            }
            if (c == DatenDownload.DOWNLOAD_ABO_NR) {
                setFont(new java.awt.Font("Dialog", Font.BOLD, 12));
                if (abo) {
                    setForeground(GuiKonstanten.ABO_FOREGROUND);
                } else {
                    setForeground(GuiKonstanten.DOWNLOAD_FOREGROUND);
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/nein_12.png")));
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            }
            if (c == DatenDownload.DOWNLOAD_PROGRAMM_RESTART_NR) {
                boolean restart = ddaten.listeDownloads.getDownloadByUrl(url).isRestart();
                setHorizontalAlignment(SwingConstants.CENTER);
                if (restart) {
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/ja_16.png")));
                } else {
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/nein_12.png")));
                }
            }
        } catch (Exception ex) {
            Log.fehlerMeldung(758200166, this.getClass().getName(), ex);
        }
        return this;
    }
}