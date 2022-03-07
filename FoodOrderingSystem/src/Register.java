
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

public class Register {

	private JFrame frmRegister;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JButton btnRegister;
	private JButton btnPremium;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField birthdayField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Register() {
		frmRegister = new JFrame();
		frmRegister.setTitle("FOOD ORDERING SYSTEM");
		frmRegister.setBounds(100, 100, 400, 400);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);

		JLabel lblHeader = new JLabel("REGISTER");
		lblHeader.setBounds(170, 5, 150, 14);
		frmRegister.getContentPane().add(lblHeader);

		// name
		JLabel lblName = new JLabel("Name And Surname: ");
		lblName.setBounds(55, 50, 120, 14);
		frmRegister.getContentPane().add(lblName);
		nameField = new JTextField();
		nameField.setBounds(200, 50, 120, 20);
		frmRegister.getContentPane().add(nameField);

		JLabel lblEmailORphone = new JLabel("Email: ");
		lblEmailORphone.setBounds(55, 80, 150, 14);
		frmRegister.getContentPane().add(lblEmailORphone);
		emailField = new JTextField();
		emailField.setBounds(200, 80, 120, 20);
		emailField.setColumns(10);
		frmRegister.getContentPane().add(emailField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(55, 110, 78, 14);
		frmRegister.getContentPane().add(lblPassword);
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 110, 120, 20);
		frmRegister.getContentPane().add(passwordField);

		// phone
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(55, 140, 78, 14);
		frmRegister.getContentPane().add(lblPhone);
		phoneField = new JTextField();
		phoneField.setBounds(200, 140, 120, 20);
		frmRegister.getContentPane().add(phoneField);

		// adress
		JLabel lblAddress = new JLabel("Adress: ");
		lblAddress.setBounds(55, 170, 78, 14);
		frmRegister.getContentPane().add(lblAddress);
		addressField = new JTextField();
		addressField.setBounds(200, 170, 120, 20);
		frmRegister.getContentPane().add(addressField);
		// birthday
		JLabel lblBirthday = new JLabel("Birthday:");
		lblBirthday.setBounds(55, 200, 78, 14);
		frmRegister.getContentPane().add(lblBirthday);
		birthdayField = new JTextField();
		birthdayField.setBounds(200, 200, 120, 20);
		frmRegister.getContentPane().add(birthdayField);
		// new user
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (phoneField.getText().equals("") || emailField.getText().equals("")
							|| passwordField.getText().equals("")) {
						JOptionPane.showMessageDialog(frmRegister, "Write email, phone, and password", "!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					Customer user = null;
					Authorization checking = new Authorization();
					user = new User(nameField.getText(), emailField.getText(), passwordField.getText(),
							phoneField.getText(), addressField.getText(), birthdayField.getText());
					user = checking.register(user, false);
					if (user == null) {
						JOptionPane.showMessageDialog(frmRegister,
								"The phone number belongs to someone. Please try another number");
					} else {
						JOptionPane.showMessageDialog(frmRegister, "Register succesfully!");
						MainMenu mp = new MainMenu(); // transaction to menu
						mp.getFrame().setVisible(true);
						frmRegister.setVisible(false);
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmRegister, "I/O ERROR!");
					e.printStackTrace();
				}
			}
		});
		btnRegister.setBounds(200, 310, 89, 23);
		frmRegister.getContentPane().add(btnRegister);

		// prem button
		btnPremium = new JButton("Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (phoneField.getText().equals("") || emailField.getText().equals("")
							|| passwordField.getText().equals("")) {
						JOptionPane.showMessageDialog(frmRegister, "Write email, phone, and password", "!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					Customer userP = null;
					Authorization checking = new Authorization();
					userP = new PremiumUser(nameField.getText(), emailField.getText(), passwordField.getText(),
							phoneField.getText(), addressField.getText(), birthdayField.getText());
					userP = checking.register(userP, true);
					if (userP == null) {
						JOptionPane.showMessageDialog(frmRegister,
								"The phone number belongs to someone. Please try another number", "!",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frmRegister, "Register succesfully!");
						MainMenu mp = new MainMenu(); // transaction to menu
						mp.getFrame().setVisible(true);
						frmRegister.setVisible(false);
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frmRegister, "I/O ERROR", "!", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		btnPremium.setBounds(55, 310, 89, 23);
		frmRegister.getContentPane().add(btnPremium);
	}

	public JFrame getFrame() {
		return frmRegister;
	}

	public void setFrame(JFrame frmRegister) {
		this.frmRegister = frmRegister;
	}
}
