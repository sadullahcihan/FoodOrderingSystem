
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

public class PaymentFrame {

	private JFrame frmPayment;
	private JTextField paymentype;
	private JButton btnLogIn;
	private JButton btnRegister;

	public PaymentFrame() {
		frmPayment = new JFrame();
		frmPayment.setTitle("FOOD ORDERING SYSTEM");
		frmPayment.setBounds(100, 100, 700, 200);
		frmPayment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPayment.getContentPane().setLayout(null);

		JLabel lblHeader = new JLabel("PAYMENT");
		lblHeader.setBounds(120, 5, 150, 14);
		frmPayment.getContentPane().add(lblHeader);

		JLabel lblEmailORphone = new JLabel("Payment Type 1:Online 2: Payment at the door: ");
		lblEmailORphone.setBounds(55, 31, 500, 14);
		frmPayment.getContentPane().add(lblEmailORphone);

		paymentype = new JTextField();
		paymentype.setBounds(400, 31, 120, 20);
		paymentype.setColumns(10);
		frmPayment.getContentPane().add(paymentype);

		btnLogIn = new JButton("PAY");

		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paymentype.getText().equals("1")) {
					JOptionPane.showMessageDialog(frmPayment, "The cost has been paid by CC");
				} else if (paymentype.getText().equals("2")) {
					JOptionPane.showMessageDialog(frmPayment, "The cost will be paid at the door");
				} else {
					JOptionPane.showMessageDialog(frmPayment, "SELECT 1 OR 2");
				}
			}
		});
		btnLogIn.setBounds(55, 105, 89, 23);
		frmPayment.getContentPane().add(btnLogIn);
	}

	public JFrame getFrame() {
		return frmPayment;
	}

	public void setFrame(JFrame frmRegister) {
		this.frmPayment = frmRegister;
	}
}