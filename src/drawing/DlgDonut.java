package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;


import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtR;
	private JTextField txtInnerR;
	private boolean confirm;
	private Donut donut;
	private JButton btnInnerColor;
	private JButton btnOutlineColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setTitle("Add/modify donut");
		setBackground(Color.WHITE);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.YELLOW);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("CENTER OF DONUT:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 5);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 2;
			gbc_txtX.gridy = 1;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 5);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 2;
			gbc_txtY.gridy = 2;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Radius:");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 0;
			gbc_lblNewLabel_3.gridy = 3;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			txtR = new JTextField();
			GridBagConstraints gbc_txtR = new GridBagConstraints();
			gbc_txtR.insets = new Insets(0, 0, 5, 5);
			gbc_txtR.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtR.gridx = 2;
			gbc_txtR.gridy = 3;
			contentPanel.add(txtR, gbc_txtR);
			txtR.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Inner radius:");
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 0;
			gbc_lblNewLabel_4.gridy = 4;
			contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		}
		{
			txtInnerR= new JTextField();
			GridBagConstraints gbc_txtInnerR = new GridBagConstraints();
			gbc_txtInnerR.insets = new Insets(0, 0, 5, 5);
			gbc_txtInnerR.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtInnerR.gridx = 2;
			gbc_txtInnerR.gridy = 4;
			contentPanel.add(txtInnerR, gbc_txtInnerR);
			txtInnerR.setColumns(10);
		}
		
				btnInnerColor = new JButton("Inner Color");
				btnInnerColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color innerColor = JColorChooser.showDialog(null, "Inner color", btnInnerColor.getBackground());
						if (innerColor != null)
							btnInnerColor.setBackground(innerColor);

					}
				});
				btnInnerColor.setBackground(Color.WHITE);
				GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
				gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
				gbc_btnInnerColor.gridx = 0;
				gbc_btnInnerColor.gridy = 5;
				contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		
				btnOutlineColor = new JButton("Outline Color");
				btnOutlineColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color outlineColor = JColorChooser.showDialog(null, "Outline color",
								btnOutlineColor.getBackground());
						if (outlineColor != null)
							btnOutlineColor.setBackground(outlineColor);

					}
				});
				
						btnOutlineColor.setBackground(Color.BLACK);
						GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
						gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 5);
						gbc_btnOutlineColor.gridx = 0;
						gbc_btnOutlineColor.gridy = 6;
						contentPanel.add(btnOutlineColor, gbc_btnOutlineColor);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty()
								|| txtR.getText().trim().isEmpty() || txtInnerR.getText().trim().isEmpty()) {
							setConfirm(false);
							JOptionPane.showMessageDialog(null, "Please, fill in all the fields!", "Error",
									JOptionPane.ERROR_MESSAGE);

						} else {
							try {
								if (Integer.parseInt(txtInnerR.getText().toString()) <= 0
										|| Integer.parseInt(txtR.getText().toString()) <= 0
										|| Integer.parseInt(txtX.getText().toString()) < 0
										|| Integer.parseInt(txtY.getText().toString()) < 0)
									JOptionPane.showMessageDialog(null, "All values must be positive!", "Error",
											JOptionPane.ERROR_MESSAGE);
								else {
									if (Integer.parseInt(txtInnerR.getText().toString()) < Integer
											.parseInt(txtR.getText().toString())) {
										donut = new Donut(
												new Point(Integer.parseInt(txtX.getText().toString()),
														Integer.parseInt(txtY.getText().toString())),
												Integer.parseInt(txtR.getText().toString()),Integer.parseInt(txtInnerR.getText().toString()), false,
												btnOutlineColor.getBackground(), btnInnerColor.getBackground());

										setConfirm(true);
										setVisible(false);
									} else {
										JOptionPane.showMessageDialog(null,"Inner radius must be smaller than radius!", "Error",JOptionPane.ERROR_MESSAGE);
									}

								}
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Only numbers are allowed!", "Error",JOptionPane.ERROR_MESSAGE);
							}

						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtR() {
		return txtR;
	}

	public void setTxtR(JTextField txtR) {
		this.txtR = txtR;
	}

	public JTextField getTxtInnerR() {
		return txtInnerR;
	}

	public void setTxtInnerR(JTextField txtInnerR) {
		this.txtInnerR = txtInnerR;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		this.donut = donut;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnOutlineColor() {
		return btnOutlineColor;
	}

	public void setBtnOutlineColor(JButton btnOutlineColor) {
		this.btnOutlineColor = btnOutlineColor;
	}

}
