/*
 *   MediathekView
 *   Copyright (C) 2008 W. Xaver
 *   W.Xaver[at]googlemail.com
 *   http://zdfmediathk.sourceforge.net/
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package mediathek.daten;

import mediathek.tool.DatumZeit;
import mediathek.tool.GermanStringSorter;
import mediathek.tool.GuiKonstanten;

public class DatenFilm implements Comparable<DatenFilm> {
    //Tags Filme

    public static final String FILME = "Filme";
    public static final int FILME_MAX_ELEM = 12;
    //
    public static final String FILM_NR = "Nr"; // wird vor dem Speichern gelöscht!
    public static final int FILM_NR_NR = 0;
    public static final String FILM_SENDER = "Sender";
    public static final int FILM_SENDER_NR = 1;
    public static final String FILM_THEMA = "Thema";
    public static final int FILM_THEMA_NR = 2;
    public static final String FILM_TITEL = "Titel";
    public static final int FILM_TITEL_NR = 3;
    public static final String FILM_DATUM = "Datum";
    public static final int FILM_DATUM_NR = 4;
    public static final String FILM_ZEIT = "Zeit";
    public static final int FILM_ZEIT_NR = 5;
    public static final String FILM_URL = "Url";
    public static final int FILM_URL_NR = 6;
    public static final String FILM_URL_ORG = "UrlOrg";
    public static final int FILM_URL_ORG_NR = 7;
    public static final String FILM_URL_RTMP = "UrlRTMP";
    public static final int FILM_URL_RTMP_NR = 8;
    public static final String FILM_URL_AUTH = "UrlAuth";
    public static final int FILM_URL_AUTH_NR = 9;
    public static final String FILM_URL_THEMA = "UrlThema";//url des Themas zum nachladen
    public static final int FILM_URL_THEMA_NR = 10;
    public static final String FILM_ABO_NAME = "Abo-Name";// wird vor dem Speichern gelöscht!
    public static final int FILM_ABO_NAME_NR = 11;
    public static final String[] FILME_COLUMN_NAMES = {FILM_NR, FILM_SENDER, FILM_THEMA, FILM_TITEL, FILM_DATUM, FILM_ZEIT,
        FILM_URL, FILM_URL_ORG, FILM_URL_RTMP, FILM_URL_AUTH, FILM_URL_THEMA, FILM_ABO_NAME
    };
    public String[] arr;

    public DatenFilm() {
        makeArr();
    }

    public DatenFilm(String ssender, String tthema, String urlThema, String ttitel, String uurl, String datum, String zeit) {
        makeArr();
        arr[FILM_SENDER_NR] = ssender;
        arr[FILM_THEMA_NR] = tthema;
        arr[FILM_TITEL_NR] = ttitel;
        arr[FILM_URL_NR] = uurl;
        arr[FILM_DATUM_NR] = DatumZeit.checkDatum(datum, arr[FILM_SENDER_NR] + " " + arr[FILM_THEMA_NR] + " " + arr[FILM_TITEL_NR]);
        arr[FILM_ZEIT_NR] = DatumZeit.checkZeit(arr[FILM_DATUM_NR], zeit, arr[FILM_SENDER_NR] + " " + arr[FILM_THEMA_NR] + " " + arr[FILM_TITEL_NR]);
        arr[FILM_URL_THEMA_NR] = urlThema;
////////        arr[FILM_DATEI_NR] = GuiFunktionen.getDateiName( uurl);
    }

    public DatenFilm(String ssender, String tthema, String urlThema, String ttitel, String uurl, String uurlorg, String uurlRtmp,
            String datum, String zeit) {
        makeArr();
        arr[FILM_SENDER_NR] = ssender;
        arr[FILM_THEMA_NR] = tthema;
        arr[FILM_TITEL_NR] = ttitel;
        arr[FILM_URL_NR] = uurl;
        arr[FILM_URL_ORG_NR] = uurlorg;
        arr[FILM_URL_RTMP_NR] = uurlRtmp;
        arr[FILM_URL_THEMA_NR] = urlThema;
        arr[FILM_DATUM_NR] = DatumZeit.checkDatum(datum, arr[FILM_SENDER_NR] + " " + arr[FILM_THEMA_NR] + " " + arr[FILM_TITEL_NR]);
        arr[FILM_ZEIT_NR] = DatumZeit.checkZeit(arr[FILM_DATUM_NR], zeit, arr[FILM_SENDER_NR] + " " + arr[FILM_THEMA_NR] + " " + arr[FILM_TITEL_NR]);
    }

//    public DatenFilm leitungAendern(boolean low) {
//        DatenFilm film = this.getCopy();
//        if (low) {
//            if (film.arr[Konstanten.FILM_SENDER_NR].equalsIgnoreCase(Konstanten.SENDER_3SAT)) {
//                //3Sat
//                film.arr[Konstanten.FILM_URL_NR] = film.arr[Konstanten.FILM_URL_NR].replace("/veryhigh/", "/300/");
//            } else if (film.arr[Konstanten.FILM_SENDER_NR].equalsIgnoreCase(Konstanten.SENDER_ZDF)) {
//                //ZDF
//                film.arr[Konstanten.FILM_URL_NR] = film.arr[Konstanten.FILM_URL_NR].replace("/veryhigh/", "/300/");
//            } else if (film.arr[Konstanten.FILM_SENDER_NR].equalsIgnoreCase(Konstanten.SENDER_NDR)) {
//                //NDR
//                film.arr[Konstanten.FILM_URL_NR] = film.arr[Konstanten.FILM_URL_NR].replace(".hq.", ".lo.");
//            }
//        }
//        return film;
//    }
    public String getIndex() {
        // liefert einen eindeutigen Index für die Filmliste
        return arr[FILM_SENDER_NR] + arr[FILM_THEMA_NR] + arr[FILM_URL_NR];
    }

    public DatenFilm getClean() {
        DatenFilm ret = new DatenFilm();
        for (int i = 0; i < arr.length; ++i) {
            ret.arr[i] = new String(this.arr[i]);
        }
        // vor dem Speichern nicht benötigte Felder löschen
        ret.arr[FILM_NR_NR] = "";
        ret.arr[FILM_ABO_NAME_NR] = "";
        return ret;
    }

    public DatenFilm getCopy() {
        DatenFilm ret = new DatenFilm();
        for (int i = 0; i < arr.length; ++i) {
            ret.arr[i] = new String(this.arr[i]);
        }
        return ret;
    }

    @Override
    public int compareTo(DatenFilm arg0) {
        int ret = 0;
        GermanStringSorter sorter = GermanStringSorter.getInstance();
        if ((ret = sorter.compare(arr[FILM_SENDER_NR], arg0.arr[FILM_SENDER_NR])) == 0) {
            ret = sorter.compare(arr[FILM_THEMA_NR], arg0.arr[FILM_THEMA_NR]);
        }
        return ret;
    }

    public String getUrlOrg() {
        return getUrlOrg(arr[FILM_URL_RTMP_NR], arr[FILM_URL_ORG_NR], arr[FILM_URL_NR]);
    }

    public static String getUrlOrg(String url_rtmp, String url_org, String url) {
        //wenn es ein url_rtmp ("--host .....") gipt, steht die org in url_org ("rtmp://......"), ansonsten ist die url_org leer
        //und es gibt nur url und es wird bei Flash-Urls entweder "-r" davorgesetzt ("für flvstreamer vorbereiten ..") oder nicht
        String ret;
        if (!url_org.equals("")) {
            ret = url_org;
        } else {
            if (url.startsWith(GuiKonstanten.RTMP_FLVSTREAMER)) {
                ret = url.substring(GuiKonstanten.RTMP_FLVSTREAMER.length());
            } else {
                ret = url;
            }
        }
        return ret;
    }

    private void makeArr() {
        arr = new String[FILME_MAX_ELEM];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = "";
        }
    }
}