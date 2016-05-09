package menjacnica.kontroler;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.Valuta;
import menjacnica.gui.DodajKursGUI;
import menjacnica.gui.IzvrsiZamenuGUI;
import menjacnica.gui.MenjacnicaGUI;
import menjacnica.gui.ObrisiKursGUI;
import menjacnica.gui.models.MenjacnicaTableModel;
import menjacnica.interfejs.MenjacnicaInterface;

public class GUIKontroler {

	private static MenjacnicaGUI glavniProzor;
	private static MenjacnicaInterface sistem;
	private static DodajKursGUI dodajKursGui;
	private static ObrisiKursGUI obrisiKursGui;
	private static IzvrsiZamenuGUI izvrsiZamenuGui;
	private static Valuta valuta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					sistem = new Menjacnica();

					glavniProzor = new MenjacnicaGUI();
					glavniProzor.setLocationRelativeTo(null);
					glavniProzor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(glavniProzor.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak", JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static void prikaziAboutProzor() {
		JOptionPane.showMessageDialog(glavniProzor.getContentPane(), "Autor: Bojan Tomic, Verzija 1.0",
				"O programu Menjacnica", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(glavniProzor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				sistem.ucitajIzFajla(file.getAbsolutePath());
				prikaziSveValute();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void prikaziSveValute() {
		MenjacnicaTableModel model = (MenjacnicaTableModel) (glavniProzor.getTable().getModel());
		model.staviSveValuteUModel(sistem.vratiKursnuListu());
	}

	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(glavniProzor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				sistem.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void prikaziDodajKursGUI() {
		if (dodajKursGui == null) {
			dodajKursGui = new DodajKursGUI();
			dodajKursGui.setLocationRelativeTo(glavniProzor.getContentPane());
			dodajKursGui.setVisible(true);
		} else {
			dodajKursGui.toFront();
		}
	}

	public static void zatvoriDodajKursGUI() {
		if (dodajKursGui != null) {
			dodajKursGui.dispose();
			dodajKursGui = null;
		}
	}

	public static void unesiKurs() {
		try {
			Valuta valuta = new Valuta();

			// Punjenje podataka o valuti
			valuta.setNaziv(dodajKursGui.getTextFieldNaziv().getText());
			valuta.setSkraceniNaziv(dodajKursGui.getTextFieldSkraceniNaziv().getText());
			valuta.setSifra((Integer) (dodajKursGui.getSpinnerSifra().getValue()));
			valuta.setProdajni(Double.parseDouble(dodajKursGui.getTextFieldProdajniKurs().getText()));
			valuta.setKupovni(Double.parseDouble(dodajKursGui.getTextFieldKupovniKurs().getText()));
			valuta.setSrednji(Double.parseDouble(dodajKursGui.getTextFieldSrednjiKurs().getText()));

			// Dodavanje valute u kursnu listu
			sistem.dodajValutu(valuta);

			// Osvezavanje glavnog prozora
			prikaziSveValute();

			// Zatvaranje DodajValutuGUI prozora
			zatvoriDodajKursGUI();

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(dodajKursGui.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void prikaziObrisiKursGUI() {
		if (obrisiKursGui == null) {
			if (glavniProzor.getTable().getSelectedRow() != -1) {
				MenjacnicaTableModel model = (MenjacnicaTableModel) (glavniProzor.getTable().getModel());

				valuta = model.vratiValutu(glavniProzor.getTable().getSelectedRow());

				obrisiKursGui = new ObrisiKursGUI();
				prikaziValutu();

				obrisiKursGui.setLocationRelativeTo(glavniProzor.getContentPane());
				obrisiKursGui.setVisible(true);
			}
		} else {
			obrisiKursGui.toFront();
		}
	}

	public static void zatvoriObrisiKursGUI() {
		if (obrisiKursGui != null) {
			obrisiKursGui.dispose();
			obrisiKursGui = null;
		}
	}

	private static void prikaziValutu() {
		// Prikaz podataka o valuti
		obrisiKursGui.getTextFieldNaziv().setText(valuta.getNaziv());
		obrisiKursGui.getTextFieldSkraceniNaziv().setText(valuta.getSkraceniNaziv());
		obrisiKursGui.getTextFieldSifra().setText("" + valuta.getSifra());
		obrisiKursGui.getTextFieldProdajniKurs().setText("" + valuta.getProdajni());
		obrisiKursGui.getTextFieldKupovniKurs().setText("" + valuta.getKupovni());
		obrisiKursGui.getTextFieldSrednjiKurs().setText("" + valuta.getSrednji());
	}

	public static void obrisiValutu() {
		try {
			sistem.obrisiValutu(valuta);

			prikaziSveValute();
			zatvoriObrisiKursGUI();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(obrisiKursGui.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void prikaziIzvrsiZamenuGUI() {
		if (izvrsiZamenuGui == null) {
			if (glavniProzor.getTable().getSelectedRow() != -1) {
				MenjacnicaTableModel model = (MenjacnicaTableModel) (glavniProzor.getTable().getModel());

				valuta = model.vratiValutu(glavniProzor.getTable().getSelectedRow());

				izvrsiZamenuGui = new IzvrsiZamenuGUI();
				prikaziValutuIzvrsiZamenaGui();

				izvrsiZamenuGui.setLocationRelativeTo(glavniProzor.getContentPane());
				izvrsiZamenuGui.setVisible(true);
			}
		} else {
			izvrsiZamenuGui.toFront();
		}
	}

	public static void zatvoriIzvrsiZamenuGUI() {
		if (izvrsiZamenuGui != null) {
			izvrsiZamenuGui.dispose();
			izvrsiZamenuGui = null;
		}
	}

	private static void prikaziValutuIzvrsiZamenaGui() {
		izvrsiZamenuGui.getTextFieldProdajniKurs().setText("" + valuta.getProdajni());
		izvrsiZamenuGui.getTextFieldKupovniKurs().setText("" + valuta.getKupovni());
		izvrsiZamenuGui.getTextFieldValuta().setText(valuta.getSkraceniNaziv());
	}

	public static void izvrsiZamenu(boolean prodaja, String iznos) {
		try {
			double konacniIznos = sistem.izvrsiTransakciju(valuta, prodaja, Double.parseDouble(iznos));

			izvrsiZamenuGui.getTextFieldKonacniIznos().setText("" + konacniIznos);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(izvrsiZamenuGui.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
