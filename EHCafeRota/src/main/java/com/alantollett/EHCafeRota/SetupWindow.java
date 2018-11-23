package com.alantollett.EHCafeRota;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class SetupWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private List<Staff> staff = new ArrayList<Staff>();
	ArrayList<Staff> todaysStaff = new ArrayList<Staff>();
	
	//CheckBoxes
	JCheckBox supervisorCheck;
	JCheckBox cookCheck;
	JCheckBox tillsCheck;
	JCheckBox iceCreamCheck;
	JCheckBox stockCheck;
	JCheckBox storesCheck;
	
	public SetupWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();		
		setBounds(0, 0, screen.width/4, (int)(screen.height/2.2)); // makes the window half the size of the screen
		setLocationRelativeTo(null); // centres the screen
		setTitle("EH - Cafe Rota Generator");
		
		JLabel header = new JLabel("");
		header.setHorizontalAlignment(SwingConstants.LEFT);
		header.setBackground(Color.BLACK);
		header.setOpaque(true); //makes background visible
		header.setIcon(new ImageIcon(getClass().getResource("EH_Logo.png")));
		
		supervisorCheck = new JCheckBox("Supervisor");
		supervisorCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		supervisorCheck.setHorizontalAlignment(SwingConstants.CENTER);
		
		cookCheck = new JCheckBox("       Cook");
		cookCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cookCheck.setHorizontalAlignment(SwingConstants.CENTER);
		
		tillsCheck = new JCheckBox("         Tills");
		tillsCheck.setHorizontalAlignment(SwingConstants.CENTER);
		tillsCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		iceCreamCheck = new JCheckBox("Ice cream");
		iceCreamCheck.setHorizontalAlignment(SwingConstants.CENTER);
		iceCreamCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		stockCheck = new JCheckBox("      Stock");
		stockCheck.setHorizontalAlignment(SwingConstants.CENTER);
		stockCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		storesCheck = new JCheckBox("     Stores");
		storesCheck.setHorizontalAlignment(SwingConstants.CENTER);
		storesCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		final JLabel updatedLabel = new JLabel("Updated Successfully");
		updatedLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		updatedLabel.setForeground(new Color(34, 139, 34));
		updatedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updatedLabel.setVisible(false);
		
		// creates a combo box and adds a list of staff members from file names in staff folder
		final JComboBox<String> staffComboBox = new JComboBox<String>();
		// updates check boxes for the newly select staff member when comboBox is updated
		staffComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Staff s : staff) {
					if(s.getName().equals(staffComboBox.getSelectedItem())) {
						updateCheckBoxes(s);
					}
				}
			}
		});
		staffComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		File folder = new File("Staff");
		File[] listOfFiles = folder.listFiles();
		for(File file : listOfFiles) {
			Staff s = new Staff(file); //creates a new instance of staff class
			staff.add(s); //adds the instance to the list of staff
			staffComboBox.addItem(s.getName()); //adds the staff members name to comboBox
		}
		// creates a staff instance of the selected item and updates the check boxes for that staff
		updateCheckBoxes(new Staff(new File("staff/" + staffComboBox.getSelectedItem() + ".txt")));
		
		// when clicked, updates the booleans for selected staff member and outputs to file
		JButton updateButton = new JButton("Update Preferences");
		updateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for(Staff s : staff) {
					if(s.getName().equals(staffComboBox.getSelectedItem())) {
						s.setSupervisor(supervisorCheck.isSelected());
						s.setCook(cookCheck.isSelected());
						s.setTills(tillsCheck.isSelected());
						s.setIceCream(iceCreamCheck.isSelected());
						s.setStock(stockCheck.isSelected());
						s.setStores(storesCheck.isSelected());
						s.updateFile();
						// makes updated label visible for two seconds
						updatedLabel.setVisible(true);
						new java.util.Timer().schedule( 
						        new java.util.TimerTask() {
						            @Override
						            public void run() {
						                updatedLabel.setVisible(false);
						            }
						        }, 
						        1000 
						);

					}
				}
			}
		});
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 15));	
		
		JLabel selectRotaLabel = new JLabel("Select a Rota File:");
		selectRotaLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		selectRotaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		final JTextField filePath = new JTextField();

		JButton browseButton = new JButton("Browse");
		browseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// gets the user to select a valid file
			    FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
			    dialog.setMode(FileDialog.LOAD);
			    dialog.setBounds(0, 0, screen.width/2, screen.height/2);
			    dialog.setLocationRelativeTo(null);
			    dialog.setVisible(true);
			    //if the file is null or the file does not exist, display error and return
			    if(dialog.getFile() == null || !dialog.getFile().endsWith(".xlsx")) {
					JOptionPane.showMessageDialog(new Frame(),
						    "The rota file is not selected or is not a valid file.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
			    	return;
			    }
			    filePath.setText(dialog.getDirectory() + dialog.getFile());
			    
			    // creates a workbook instance for the file
				Workbook workBook = null;
				try {
					workBook = WorkbookFactory.create(new File(filePath.getText()));
				} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
					e.printStackTrace();
				}
				Sheet sheet = workBook.getSheet("Rota");
			    
				LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int dayOfMonth = localDate.getDayOfMonth();

				int column = dayOfMonth;
				ArrayList<String> newStaff = new ArrayList<String>();
				// for all staff on the file, check if they are meant to be in today, if so 
				// create a staff instance for the user (creates a new file w/default values if needed)
				for (int rowIndex = 3; rowIndex<sheet.getPhysicalNumberOfRows(); rowIndex++){
				    Row row = CellUtil.getRow(rowIndex, sheet);
				    Cell cell = CellUtil.getCell(row, column);
				    if(cell.getStringCellValue().equals("E")) {
				    	String staffName = CellUtil.getCell(row, 0).getStringCellValue();
				    	File staffFile = new File("Staff/" + staffName + ".txt");				    	
				    	// if the staff file does not exist, creates a new one with values
				    	if(!staffFile.exists()) {
				    		try {
								staffFile.createNewFile();
								try(PrintWriter writer = new PrintWriter(staffFile)){
									writer.println("Name: " + staffName);
									if(rowIndex < 8) {
										writer.println("Supervisor: " + true);
										writer.println("Cook: " + false);
										writer.println("Tills: " + false);
										writer.println("IceCream: " + false);
										writer.println("Stock: " + false);
										writer.println("Stores: " + false);
									}else {
										writer.println("Supervisor: " + false);
										writer.println("Cook: " + false);
										writer.println("Tills: " + true);
										writer.println("IceCream: " + true);
										writer.println("Stock: " + true);
										writer.println("Stores: " + false);
									}
									writer.close();
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
				    		staff.add(new Staff(staffFile));
				    		staffComboBox.addItem(staffName); 
				    		newStaff.add(staffName);
				    	}
			    		todaysStaff.add(new Staff(staffFile));
				    }
				}
				// all of todays staff are added to the list of staff, now alert user of new staff so they can update booleans
				if(!newStaff.isEmpty()) {
					JOptionPane.showMessageDialog(new Frame(),
						    "New staff found - please update their preferences.\n\n" + newStaff.toString(),
						    "New Staff Detected",
						    JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		browseButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton generateButton = new JButton("Generate Rota");
		generateButton.addMouseListener(new MouseAdapter() {
			@Override // if a file is selected and still exists and is an excel spreadsheet, generates rota...
			public void mouseClicked(MouseEvent arg0) {
				if(filePath != null && (new File(filePath.getText()).exists()) && filePath.getText().endsWith(".xlsx")) {
					dispose(); //removes current window before generating rota
					RotaWindow rotaWindow = new RotaWindow(todaysStaff);
					rotaWindow.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(new Frame(),
						    "The rota file is not selected or is not a valid file.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		generateButton.setFont(new Font("Tahoma", Font.PLAIN, 15));						
		
		GroupLayout gl_contentPane = new GroupLayout(getContentPane());
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(header, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(filePath, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(browseButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(150)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(storesCheck, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(stockCheck, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(iceCreamCheck, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(tillsCheck, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(supervisorCheck, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(cookCheck, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
					.addGap(150))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(162)
					.addComponent(generateButton, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addGap(162))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(130)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(selectRotaLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
						.addComponent(updateButton, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
						.addComponent(updatedLabel, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
						.addComponent(staffComboBox, Alignment.LEADING, 0, 204, Short.MAX_VALUE))
					.addGap(130))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(header, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(staffComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(updatedLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(supervisorCheck)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cookCheck)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tillsCheck, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(iceCreamCheck, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stockCheck, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(storesCheck, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(selectRotaLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(browseButton, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(filePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(generateButton)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		getContentPane().setLayout(gl_contentPane);
		setVisible(true);
	}
	
	public void updateCheckBoxes(Staff s) {
		supervisorCheck.setSelected(s.getSupervisor());
		cookCheck.setSelected(s.getCook());
		tillsCheck.setSelected(s.getTills());
		iceCreamCheck.setSelected(s.getIceCream());
		stockCheck.setSelected(s.getStock());
		storesCheck.setSelected(s.getStores());
	}
}
