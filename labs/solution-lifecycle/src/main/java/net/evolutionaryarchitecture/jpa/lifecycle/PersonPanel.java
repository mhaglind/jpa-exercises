package net.evolutionaryarchitecture.jpa.lifecycle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;

public class PersonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblId = null;
	private JTextField txtId = null;
	private JLabel lblFirstName = null;
	private JLabel lblLastName = null;
	private JTextField txtFirstName = null;
	private JTextField txtLastName = null;
	private JButton btnUpdate = null;
	private JButton btnDelete = null;
	private JButton btnNew = null;
	private JButton btnSearch = null;
	private JTextArea txtException = null;
	private JLabel lblDescription = null;
	private JTextField txtDescription = null;

	private PersonService personService = new PersonService();

	private Person currentPerson = null;

	/**
	 * This is the default constructor
	 */
	public PersonPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblDescription = new JLabel();
		lblDescription.setBounds(new Rectangle(5, 80, 71, 16));
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDescription.setText("Description");
		lblLastName = new JLabel();
		lblLastName.setBounds(new Rectangle(5, 55, 61, 16));
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblLastName.setText("Last name");
		lblFirstName = new JLabel();
		lblFirstName.setBounds(new Rectangle(5, 30, 61, 16));
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFirstName.setText("First name");
		lblId = new JLabel();
		lblId.setBounds(new Rectangle(5, 5, 51, 16));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblId.setText("Id");
		this.setSize(280, 272);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(280, 272));
		this.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		this.setFont(new Font("Tahoma", Font.PLAIN, 10));
		this.add(lblId, null);
		this.add(getTxtId(), null);
		this.add(lblFirstName, null);
		this.add(lblLastName, null);
		this.add(getTxtFirstName(), null);
		this.add(getTxtLastName(), null);
		this.add(getBtnUpdate(), null);
		this.add(getBtnDelete(), null);
		this.add(getBtnNew(), null);
		this.add(getBtnSearch(), null);
		this.add(getTxtException(), null);
		this.add(lblDescription, null);
		this.add(getTxtDescription(), null);
	}

	/**
	 * This method initializes txtId
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setBounds(new Rectangle(80, 5, 71, 21));
			txtId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return txtId;
	}

	/**
	 * This method initializes txtFirstName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtFirstName() {
		if (txtFirstName == null) {
			txtFirstName = new JTextField();
			txtFirstName.setBounds(new Rectangle(80, 30, 71, 21));
			txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return txtFirstName;
	}

	/**
	 * This method initializes txtLastName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtLastName() {
		if (txtLastName == null) {
			txtLastName = new JTextField();
			txtLastName.setBounds(new Rectangle(80, 55, 71, 21));
			txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return txtLastName;
	}

	/**
	 * This method initializes btnUpdate
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton();
			btnUpdate.setBounds(new Rectangle(15, 245, 86, 21));
			btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnUpdate.setMnemonic(KeyEvent.VK_U);
			btnUpdate.setText("Update");
			btnUpdate.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {

						currentPerson.setFirstName(txtFirstName.getText());
						currentPerson.setLastName(txtLastName.getText());
						currentPerson.setDescription(txtDescription.getText());

						personService.updatePerson(currentPerson);

						txtException.setText("");
					} catch (Exception ex) {
						txtException.setText(ex.getCause().getMessage());
						ex.printStackTrace();
					}
				}
			});
		}
		return btnUpdate;
	}

	/**
	 * This method initializes btnDelete
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setBounds(new Rectangle(105, 245, 81, 21));
			btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnDelete.setMnemonic(KeyEvent.VK_D);
			btnDelete.setText("Delete");
			btnDelete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						personService.deletePerson(Long.parseLong(txtId
								.getText()));
						txtFirstName.setText("");
						txtLastName.setText("");
						txtId.setText("");
						txtDescription.setText("");
						txtException.setText("");
					} catch (Exception ex) {
						txtException.setText(ex.getMessage());
						ex.printStackTrace();
					}
				}
			});
		}
		return btnDelete;
	}

	/**
	 * This method initializes btnNew
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton();
			btnNew.setBounds(new Rectangle(190, 245, 76, 21));
			btnNew.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnNew.setMnemonic(KeyEvent.VK_I);
			btnNew.setText("Insert");
			btnNew.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Long id = personService.addPerson(
								txtFirstName.getText(), txtLastName.getText(),
								txtDescription.getText());
						currentPerson = personService.findPerson(id);
						txtId.setText("" + currentPerson.getId());
						txtException.setText("");
					} catch (Exception ex) {
						txtException.setText(ex.getMessage());
						ex.printStackTrace();
					}
				}
			});
		}
		return btnNew;
	}

	/**
	 * This method initializes btnSearch
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton();
			btnSearch.setBounds(new Rectangle(165, 5, 81, 21));
			btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnSearch.setMnemonic(KeyEvent.VK_S);
			btnSearch.setText("Search");
			btnSearch.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						currentPerson = personService.findPerson(Long
								.parseLong(txtId.getText()));
						txtFirstName.setText(currentPerson.getFirstName());
						txtLastName.setText(currentPerson.getLastName());
						txtDescription.setText(currentPerson.getDescription());
						txtException.setText("");
					} catch (Exception ex) {
						txtException.setText(ex.getMessage());
						ex.printStackTrace();
					}
				}
			});
		}
		return btnSearch;
	}

	/**
	 * This method initializes txtException
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtException() {
		if (txtException == null) {
			txtException = new JTextArea();
			txtException.setBounds(new Rectangle(5, 175, 256, 61));
			txtException.setForeground(Color.red);
			txtException.setBackground(new Color(238, 238, 238));
			txtException.setWrapStyleWord(true);
			txtException.setLineWrap(true);
			txtException.setFont(new Font("Tahoma", Font.PLAIN, 10));
			txtException.setEditable(false);
		}
		return txtException;
	}

	/**
	 * This method initializes txtDescription
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDescription() {
		if (txtDescription == null) {
			txtDescription = new JTextField();
			txtDescription.setBounds(new Rectangle(80, 80, 186, 21));
			txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return txtDescription;
	}

}
