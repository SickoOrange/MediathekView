/*
 * MediathekView
 * Copyright (C) 2008 W. Xaver
 * W.Xaver[at]googlemail.com
 * http://zdfmediathk.sourceforge.net/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package mediathek.tool;

import java.awt.Color;

public class GuiKonstanten {

    public static final String TextUpdateSuchen = "Eimal am Tag nach einer neuen Programmversion suchen";
    //Sender
//    public static final String SENDER_PREFX = "xml"; //für den XML-Standard
    public static final String[][] THEME = {{"", ""},//das muss da sein
        {"System", ""}, // und das auch!!
        {"Metal", "javax.swing.plaf.metal.MetalLookAndFeel"},
        {"Nimbus", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"},
        {"GTK-Linux", "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"}
    };
    public static final String IMPORT_WEBSITE = "import-website";
    public static final String IMPORT_URL = "import-url";
    public static final String IMPORT_DATEI = "import-datei";
    public static final String IMPORT_WEBSITE_TEXT = "Filme laden (Website)";
    public static final String IMPORT_URL_TEXT = "Filme laden (Url)";
    public static final String IMPORT_DATEI_TEXT = "Filme laden (Datei)";
    public static final String DIREKTE_DOWNLOAD_SUFFIX = "mp4,mp3,m4v,flv";
    public static final String DIREKTE_DOWNLOAD_PRAEFIX = "http";
    //Formate zu Zippen
    public static final String FORMAT_ZIP = ".zip";
    public static final String FORMAT_BZ2 = ".bz2";
    // für das Anpassen der URL für den flvstreamer
    public static final String RTMP_FLVSTREAMER = "-r ";
    public static final String RTMP_PRTOKOLL = "rtmp";
    // Auto-Neu-Laden
    public static final int NEU_LADEN_IN = 75 * 60; // Sekunden
    //
    //
    public static final int UPDATE_FILME_AUS = 0; // nix
    public static final int UPDATE_FILME_URL = 1; // manuell laden, Url automatisch wählen
    public static final int UPDATE_FILME_AUTO = 2; // beim Start, immer mal wieder, + Url auto
    //
    // Farben
    public static Color FARBE_GRAU = new Color(225, 225, 225);
    public static Color FARBE_GRAU_SEL = new Color(190, 190, 190);
    // Tabelle Abos
    public static Color ABO_FOREGROUND = new Color(138,67,0);
    public static Color ABO = new Color(255, 245, 229);
    public static Color ABO_SEL = new Color(255, 204, 127);
    public static Color DOWNLOAD_FOREGROUND = new Color(0,72,138);
    public static Color DOWNLOAD = new Color(229, 239, 255);
    public static Color DOWNLOAD_SEL = new Color(127, 178, 255);
    // Tabelle Downloads
    public static Color DOWNLOAD_FARBE_WAIT = new Color(239, 244, 255);
    public static Color DOWNLOAD_FARBE_WAIT_SEL = new Color(199, 206, 222);
    public static Color DOWNLOAD_FARBE_RUN = new Color(241, 228, 188);
    public static Color DOWNLOAD_FARBE_RUN_SEL = new Color(206, 178, 92);
    public static Color DOWNLOAD_FARBE_FERTIG = new Color(188, 241, 195);
    public static Color DOWNLOAD_FARBE_FERTIG_SEL = new Color(115, 206, 92);
    public static Color DOWNLOAD_FARBE_ERR = new Color(241, 188, 221);
    public static Color DOWNLOAD_FARBE_ERR_SEL = new Color(206, 92, 128);
    public static Color DOWNLOAD_FARBE_ABO = new Color(0, 50, 120);
    public static Color DOWNLOAD_FARBE_DOWNLOAD = new Color(0, 90, 0);
    public static Color DOWNLOAD_FARBE_LIVE = new Color(130, 0, 0);

}