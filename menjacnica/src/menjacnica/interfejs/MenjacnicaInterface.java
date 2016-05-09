package menjacnica.interfejs;

import java.util.LinkedList;

import menjacnica.Valuta;

public interface MenjacnicaInterface {

	public void dodajValutu(Valuta valuta);

	public void obrisiValutu(Valuta valuta);

	public double izvrsiTransakciju(Valuta valuta, boolean prodaja, double iznos);

	public LinkedList<Valuta> vratiKursnuListu();

	public void ucitajIzFajla(String putanja);

	public void sacuvajUFajl(String putanja);

}
