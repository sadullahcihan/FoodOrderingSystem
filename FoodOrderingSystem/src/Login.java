
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmLogIn;
	private JTextField emailORphone;
	private JPasswordField passwordField;
	private JButton btnLogIn;
	private JButton btnRegister;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		frmLogIn = new JFrame();
		frmLogIn.setTitle("FOOD ORDERING SYSTEM");
		frmLogIn.setBounds(100, 100, 400, 200);
		frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogIn.getContentPane().setLayout(null);

		JLabel lblHeader = new JLabel("LOG IN");
		lblHeader.setBounds(120, 5, 150, 14);
		frmLogIn.getContentPane().add(lblHeader);

		JLabel lblEmailORphone = new JLabel("Email or Phone Number: ");
		lblEmailORphone.setBounds(55, 31, 150, 14);
		frmLogIn.getContentPane().add(lblEmailORphone);

		emailORphone = new JTextField();
		emailORphone.setBounds(200, 31, 120, 20);
		emailORphone.setColumns(10);
		frmLogIn.getContentPane().add(emailORphone);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(55, 65, 78, 14);
		frmLogIn.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(200, 65, 120, 20);
		frmLogIn.getContentPane().add(passwordField);

		btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Customer user = null;
				Authorization checking = new Authorization();
				try {
					user = checking.login(emailORphone.getText(), passwordField.getText());
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (user != null) {
					JOptionPane.showMessageDialog(frmLogIn, "Login Successful!\nWelcome " + user.getName());
					MainMenu mp = null;
					try {
						mp = new MainMenu();
						mp.getFrame().setVisible(true);
					} catch (IOException e) {
						e.printStackTrace();
					}
					frmLogIn.setVisible(false);
				} else {
					emailORphone.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(frmLogIn, "Wrong user email or phone number and/or password","!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogIn.setBounds(200, 105, 89, 23);
		frmLogIn.getContentPane().add(btnLogIn);

		// register button
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register mp = new Register();
				mp.getFrame().setVisible(true);
				frmLogIn.setVisible(false);
			}
		});
		btnRegister.setBounds(55, 105, 89, 23);
		frmLogIn.getContentPane().add(btnRegister);
	}
	
}