import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KullaniciGirisi extends JFrame {

	private JPanel contentPane;
	private JTextField kullanici_adi_alani;
	private JTextField parola_alani;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullaniciGirisi frame = new KullaniciGirisi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KullaniciGirisi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton giris = new JButton("Giris Yap");
		giris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kullanici_adi = kullanici_adi_alani.getText();
				String parola = parola_alani.getText();
				String message;
				if(kullanici_adi.equals("Elif Nil Beðce") && parola.equals("123456")) {
					message = "Hoþgeldiniz " +kullanici_adi;
				}
				else if(!kullanici_adi.equals("Elif Nil Beðce") && parola.equals("123456")) {
					message= "Kullanýcý adý hatalý..";
				}
				else if(kullanici_adi.equals("Elif Nil Beðce") && !parola.equals("123456")) {
					message="Parola hatalý..";
				}
				else {
					message="Kullanýcý adý ve parola hatalý..";
				}
				if(message.equals("Hoþgeldiniz " +kullanici_adi)) {
					
					JOptionPane.showMessageDialog(giris, message);
					//setVisible(false); mesaj penceresini kapatýr 
					System.exit(0); //outputu kapatýr 
				}
				else {
					JOptionPane.showMessageDialog(giris, message);
					
				}
				kullanici_adi_alani.setText("");
				parola_alani.setText("");
				
						
			}
		});
		giris.setBounds(159, 182, 85, 21);
		contentPane.add(giris);
		
		kullanici_adi_alani = new JTextField();
		kullanici_adi_alani.setBounds(107, 63, 179, 19);
		contentPane.add(kullanici_adi_alani);
		kullanici_adi_alani.setColumns(10);
		
		parola_alani = new JTextField();
		parola_alani.setBounds(107, 112, 179, 19);
		contentPane.add(parola_alani);
		parola_alani.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kullanici adi:");
		lblNewLabel.setBounds(32, 66, 85, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Parola:");
		lblNewLabel_1.setBounds(32, 115, 85, 13);
		contentPane.add(lblNewLabel_1);
	}
}
