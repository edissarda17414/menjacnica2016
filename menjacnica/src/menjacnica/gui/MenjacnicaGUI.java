package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import menjacnica.Menjacnica;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;
import menjacnica.kontroler.GUIKontroler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnObrisiKurs;
	private JButton btnIzvrsiZamenu;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnAbout;
	private JMenuItem mntmNew;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;

	// klasa na logickom nivou
	//protected Menjacnica sistem;
	private JTable table;

	/*
	 * STAVLJENO U KONTROLER 
	 * public static void main(String[] args) {
	 * EventQueue.invokeLater(new Runnable() { public void run() { try {
	 * MenjacnicaGUI frame = new MenjacnicaGUI(); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUIKontroler.ugasiAplikaciju();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenjacnicaGUI.class.getResource("/icons/Screenshot.png")));
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 722, 470);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.EAST);

		//sistem = new Menjacnica();
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			panel.setPreferredSize(new Dimension(160, 10));
			panel.add(getBtnNewButton());
			panel.add(getBtnObrisiKurs());
			panel.add(getBtnIzvrsiZamenu());
		}
		return panel;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Dodaj kurs");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// prikaziDodajKursGUI();
					GUIKontroler.prikaziDodajKursGUI();
				}
			});
			btnNewButton.setPreferredSize(new Dimension(140, 25));
		}
		return btnNewButton;
	}

	private JButton getBtnObrisiKurs() {
		if (btnObrisiKurs == null) {
			btnObrisiKurs = new JButton("Obrisi kurs");
			btnObrisiKurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// prikaziObrisiKursGUI();
					GUIKontroler.prikaziObrisiKursGUI();
				}
			});
			btnObrisiKurs.setPreferredSize(new Dimension(140, 25));
		}
		return btnObrisiKurs;
	}

	private JButton getBtnIzvrsiZamenu() {
		if (btnIzvrsiZamenu == null) {
			btnIzvrsiZamenu = new JButton("Izvrsi zamenu");
			btnIzvrsiZamenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//prikaziIzvrsiZamenuGUI();
					GUIKontroler.prikaziIzvrsiZamenuGUI();
				}
			});
			btnIzvrsiZamenu.setPreferredSize(new Dimension(140, 25));
		}
		return btnIzvrsiZamenu;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnAbout());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmNew());
			mnFile.add(getMntmSave());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	private JMenu getMnAbout() {
		if (mnAbout == null) {
			mnAbout = new JMenu("Help");
			mnAbout.setMnemonic(KeyEvent.VK_A);
			mnAbout.add(getMntmAbout());
		}
		return mnAbout;
	}

	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem("Open");
			mntmNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ucitajIzFajla();
					GUIKontroler.ucitajIzFajla();
				}
			});
			mntmNew.setIcon(new ImageIcon(
					MenjacnicaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
			mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		}
		return mntmNew;
	}

	private JMenuItem getMntmSave() {
		if (mntmSave == null) {
			mntmSave = new JMenuItem("Save");
			mntmSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// sacuvajUFajl();
					GUIKontroler.sacuvajUFajl();
				}
			});
			mntmSave.setIcon(new ImageIcon(
					MenjacnicaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
			mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		}
		return mntmSave;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ugasiAplikaciju();
				}
			});
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		}
		return mntmExit;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.prikaziAboutProzor();
				}
			});
		}
		return mntmAbout;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}

	private void ugasiAplikaciju() {
		/*
		 * DODATO U KONTROLER int opcija =
		 * JOptionPane.showConfirmDialog(contentPane,
		 * "Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
		 * JOptionPane.YES_NO_OPTION);
		 * 
		 * if (opcija == JOptionPane.YES_OPTION) System.exit(0);
		 */

		GUIKontroler.ugasiAplikaciju();
	}

	private void prikaziAboutProzor() {
		/*
		 * DODATO U KONTROLER JOptionPane.showMessageDialog(contentPane,
		 * "Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
		 * JOptionPane.INFORMATION_MESSAGE);
		 */
	}

	private void sacuvajUFajl() {
		/*
		 * DODATO U KONTROLER try { JFileChooser fc = new JFileChooser(); int
		 * returnVal = fc.showSaveDialog(contentPane);
		 * 
		 * if (returnVal == JFileChooser.APPROVE_OPTION) { File file =
		 * fc.getSelectedFile();
		 * 
		 * sistem.sacuvajUFajl(file.getAbsolutePath()); } } catch (Exception e1)
		 * { JOptionPane.showMessageDialog(contentPane, e1.getMessage(),
		 * "Greska", JOptionPane.ERROR_MESSAGE); }
		 */
	}

	private void ucitajIzFajla() {
		/*
		 * DODATO U KONTROLER try { JFileChooser fc = new JFileChooser(); int
		 * returnVal = fc.showOpenDialog(contentPane);
		 * 
		 * if (returnVal == JFileChooser.APPROVE_OPTION) { File file =
		 * fc.getSelectedFile(); sistem.ucitajIzFajla(file.getAbsolutePath());
		 * prikaziSveValute(); } } catch (Exception e1) {
		 * JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "Greska",
		 * JOptionPane.ERROR_MESSAGE); }
		 */
	}

	protected void prikaziSveValute() {
		/*
		 * DODATO U KONTROLER MenjacnicaTableModel model =
		 * (MenjacnicaTableModel) (table.getModel());
		 * model.staviSveValuteUModel(sistem.vratiKursnuListu());
		 */
	}

	private void prikaziDodajKursGUI() {
		/*
		 * DODATO U KONTROLER DodajKursGUI prozor = new DodajKursGUI(this);
		 * prozor.setLocationRelativeTo(contentPane); prozor.setVisible(true);
		 */
	}

	private void prikaziObrisiKursGUI() {
		/*
		 * DODATO U KONTROLER 
		 * if (table.getSelectedRow() != -1) {
		 * MenjacnicaTableModel model = (MenjacnicaTableModel)
		 * (table.getModel()); 
		 * ObrisiKursGUI prozor = new ObrisiKursGUI(this,
		 * model.vratiValutu(table.getSelectedRow()));
		 * prozor.setLocationRelativeTo(contentPane); prozor.setVisible(true); }
		 */
	}

	private void prikaziIzvrsiZamenuGUI() {
		/* DODATO U KONTROLER
		if (table.getSelectedRow() != -1) {
			MenjacnicaTableModel model = (MenjacnicaTableModel) (table.getModel());
			IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(this, model.vratiValutu(table.getSelectedRow()));
			prozor.setLocationRelativeTo(contentPane);
			prozor.setVisible(true);
		}
		*/
	}

	public JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new MenjacnicaTableModel());
		}
		return table;
	}
}
