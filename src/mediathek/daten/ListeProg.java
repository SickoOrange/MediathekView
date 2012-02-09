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

package mediathek.daten;

import mediathek.tool.TModel;
import mediathek.tool.GuiKonstanten;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ListeProg extends LinkedList<DatenProg> {

    public void addProg(String name, String pfad, String schalter) {
        add(new DatenProg(name, pfad, schalter, Boolean.FALSE.toString()));
    }

//    public void addProg(String name, String pfad, String schalter, boolean standard) {
//        add(new DatenProg(name, pfad, schalter, standard));
//    }

    public DatenProg remove(String name) {
        DatenProg ret = null;
        Iterator<DatenProg> it = this.iterator();
        DatenProg prog;
        while (it.hasNext()) {
            prog = it.next();
            if (prog.arr[DatenProg.PROGRAMM_NAME_NR].equals(name)) {
                it.remove();
                ret = prog;
                break;
            }
        }
        return ret;
    }

//    public DatenProg getProgAbo(Daten daten, int art, String thema) {
//        //Aus der Liste das Programm für das Thema des Abos suchen
//        //wird kein passender Eintrag gefunden wird der letzte genommen
//        DatenProg ret = null;
//        String name = null;
//        boolean gefunden = true;
//        try {
//            //erst Programmnamen suchen
//            Iterator<DatenAbo> itAbo = daten.listeAbo.iterator();
//            DatenAbo abo;
//            while (itAbo.hasNext()) {
//                abo = itAbo.next();
//                if (abo.arr[Konstanten.ABO_THEMA_NR].equals(thema)) {
//                    name = abo.arr[Konstanten.ABO_PGRUPPE_NR];
//                    break;
//                }
//            }
//            if (name != null) {
//                Iterator<DatenProg> it = this.iterator();
//                while (it.hasNext()) {
//                    ret = it.next();
//                    if (ret.arr[DatenProg.PROGRAMM_NAME_NR].equals(name)) {
//                        gefunden = true;
//                        break;
//                    }
//                }
//            }
//            if (!gefunden) {
//                ret = this.getFirst();
//                daten.fehler.fehlerMeldung("Fehler",
//                                           new String[]{"Es wurde kein passendes Programm für ein Abo gefunden.",
//                                                        "Thema: " + thema});
//            }
//        } catch (Exception ex) {
//            daten.fehler.fehlerMeldung("Fehler", new String[]{"ListeProg.getProgAbo",
//                                                              ex.getMessage(),
//                                                              "Es wurde kein passendes Programm für ein Abo gefunden.",
//                                                              "Thema: " + thema});
//        }
//        return ret;
//    }

    public int auf(int idx, boolean auf) {
        DatenProg prog = this.remove(idx);
        int neu = idx;
        if (auf) {
            if (neu > 0) {
                --neu;
            }
        } else {
            if (neu < this.size()) {
                ++neu;
            }
        }
        this.add(neu, prog);
        return neu;
    }

    public TModel getModel() {
        TModel model;
        Object[][] object;
        DatenProg daten;
        int i = 0;
        if (this.size() > 0) {
            ListIterator<DatenProg> iterator = this.listIterator(0);
            object = new Object[this.size()][DatenProg.PROGRAMM_MAX_ELEM];
            while (iterator.hasNext()) {
                daten = iterator.next();
                object[i] = daten.arr;
                ++i;
            }
            model = new TModel(object, DatenProg.PROGRAMM_COLUMN_NAMES);
        } else {
            model = new TModel(new Object[0][DatenProg.PROGRAMM_MAX_ELEM], DatenProg.PROGRAMM_COLUMN_NAMES);
        }
        return model;
    }

    public String[] getObjectDataCombo() {
        String[] object;
        DatenProg daten;
        int i = 0;
        ListIterator<DatenProg> iterator = this.listIterator(0);
        object = new String[this.size()];
        while (iterator.hasNext()) {
            daten = iterator.next();
            object[i] = daten.arr[DatenProg.PROGRAMM_NAME_NR];
            ++i;
        }
        return object;
    }

}