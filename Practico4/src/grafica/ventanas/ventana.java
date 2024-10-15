package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventana {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana window = new ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAgregarFolio = new JButton("Agregar Folio");
		btnAgregarFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregarFolio.setBounds(20, 30, 113, 23);
		frame.getContentPane().add(btnAgregarFolio);
		
		JButton btnAgregarRevision = new JButton("Agregar Revision");
		btnAgregarRevision.setBounds(163, 30, 113, 23);
		frame.getContentPane().add(btnAgregarRevision);
		
		JButton btnBorrarFolio = new JButton("Borrar Folio");
		btnBorrarFolio.setBounds(303, 30, 110, 23);
		frame.getContentPane().add(btnBorrarFolio);
		
		JButton btnListarFolios = new JButton("Listar Folios");
		btnListarFolios.setBounds(20, 64, 113, 23);
		frame.getContentPane().add(btnListarFolios);
		
		JButton btnListarRevisiones = new JButton("Listar Revisiones");
		btnListarRevisiones.setBounds(163, 64, 113, 23);
		frame.getContentPane().add(btnListarRevisiones);
		
		JButton btnFolioMasRevisado = new JButton("Folio Mas Revisado");
		btnFolioMasRevisado.setBounds(303, 64, 110, 23);
		frame.getContentPane().add(btnFolioMasRevisado);
	}
}
