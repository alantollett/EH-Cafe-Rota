package com.alantollett.EHCafeRota;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class RotaWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JFrame frame = this;
	
	public RotaWindow(List<Staff> staff) {
		getContentPane().setForeground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();		
		setBounds(0, 0, screen.width/3, (int)(screen.height/2.2)); // makes the window half the size of the screen
		setLocationRelativeTo(null); // centres the screen
		setTitle("EH - Cafe Rota Generator");
		
		Rota rota = new Rota(staff);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();		
		JLabel header = new JLabel("                           Cafe Rota 19/11/2018");
		header.setHorizontalAlignment(SwingConstants.LEFT);
		header.setIcon(new ImageIcon(RotaWindow.class.getResource("/com/alantollett/EHCafeRota/EH_Logo.png")));
		header.setForeground(Color.WHITE);
		header.setBackground(Color.BLACK);
		header.setOpaque(true);
		
		JLabel roleLabel = new JLabel("Role");
		roleLabel.setForeground(Color.DARK_GRAY);
		roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel amLabel = new JLabel("8:00 - 11:00");
		amLabel.setForeground(Color.DARK_GRAY);
		amLabel.setHorizontalAlignment(SwingConstants.CENTER);
		amLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lunchLabel = new JLabel("11:00 - 14:00");
		lunchLabel.setForeground(Color.DARK_GRAY);
		lunchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lunchLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel pmLabel = new JLabel("14:00 - 17:00");
		pmLabel.setForeground(Color.DARK_GRAY);
		pmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pmLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel supervisorLabel = new JLabel("Supervisors");
		supervisorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		supervisorLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel supervisorText = new JLabel("supervisor text");
		supervisorText.setBackground(Color.WHITE);
		supervisorText.setOpaque(true);
		supervisorText.setHorizontalAlignment(SwingConstants.CENTER);
		if(!rota.getSupervisors().isEmpty()) {
			supervisorText.setText(Arrays.toString(rota.getSupervisors().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		
		JLabel cookLabel = new JLabel("Cook");
		cookLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cookLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel cookText = new JLabel("cook text");
		cookText.setBackground(Color.WHITE);
		cookText.setOpaque(true);
		cookText.setHorizontalAlignment(SwingConstants.CENTER);
		if(rota.getCook() != null) {
			cookText.setText(rota.getCook());
		}
		
		JLabel storesLabel = new JLabel("Stores");
		storesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		storesLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel storesText = new JLabel("cook text");
		storesText.setOpaque(true);
		storesText.setHorizontalAlignment(SwingConstants.CENTER);
		storesText.setBackground(Color.WHITE);
		if(rota.getStores() != null) {
			storesText.setText(rota.getStores());
		}
		
		/*
		 * Till 31
		 */
		JLabel till31Label = new JLabel("Till 31");
		till31Label.setHorizontalAlignment(SwingConstants.CENTER);
		till31Label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel till31Am = new JLabel(" ");
		till31Am.setOpaque(true);
		till31Am.setHorizontalAlignment(SwingConstants.CENTER);
		till31Am.setBackground(Color.WHITE);
		if(!rota.getTillsAm().isEmpty() && rota.getTillsAm().size() >= 1) {
			till31Am.setText(rota.getTillsAm().get(0));
		}
		
		JLabel till31Lunch = new JLabel(" ");
		till31Lunch.setOpaque(true);
		till31Lunch.setHorizontalAlignment(SwingConstants.CENTER);
		till31Lunch.setBackground(Color.WHITE);
		if(!rota.getTillsLunch().isEmpty() && rota.getTillsLunch().size() >= 1) {
			till31Lunch.setText(rota.getTillsLunch().get(0));
		}
		
		JLabel till31Pm = new JLabel(" ");
		till31Pm.setOpaque(true);
		till31Pm.setHorizontalAlignment(SwingConstants.CENTER);
		till31Pm.setBackground(Color.WHITE);
		if(!rota.getTillsPm().isEmpty() && rota.getTillsPm().size() >= 1) {
			till31Pm.setText(rota.getTillsPm().get(0));
		}
		
		/*
		 * Till 32
		 */
		JLabel till32Label = new JLabel("Till 32");
		till32Label.setHorizontalAlignment(SwingConstants.CENTER);
		till32Label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel till32Am = new JLabel(" ");
		till32Am.setOpaque(true);
		till32Am.setHorizontalAlignment(SwingConstants.CENTER);
		till32Am.setBackground(Color.WHITE);
		if(!rota.getTillsAm().isEmpty() && rota.getTillsAm().size() >= 2) {
			till32Am.setText(rota.getTillsAm().get(1));
		}
		
		JLabel till32Lunch = new JLabel(" ");
		till32Lunch.setOpaque(true);
		till32Lunch.setHorizontalAlignment(SwingConstants.CENTER);
		till32Lunch.setBackground(Color.WHITE);
		if(!rota.getTillsLunch().isEmpty() && rota.getTillsLunch().size() >= 2) {
			till32Lunch.setText(rota.getTillsLunch().get(1));
		}
		
		JLabel till32Pm = new JLabel(" ");
		till32Pm.setOpaque(true);
		till32Pm.setHorizontalAlignment(SwingConstants.CENTER);
		till32Pm.setBackground(Color.WHITE);
		if(!rota.getTillsPm().isEmpty() && rota.getTillsPm().size() >= 2) {
			till32Pm.setText(rota.getTillsPm().get(1));
		}
		
		/*
		 * Till 33
		 */
		JLabel till33Label = new JLabel("Till 33");
		till33Label.setHorizontalAlignment(SwingConstants.CENTER);
		till33Label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel till33Am = new JLabel(" ");
		till33Am.setOpaque(true);
		till33Am.setHorizontalAlignment(SwingConstants.CENTER);
		till33Am.setBackground(Color.WHITE);
		if(!rota.getTillsAm().isEmpty() && rota.getTillsAm().size() >= 3) {
			till33Am.setText(rota.getTillsAm().get(2));
		}
		
		JLabel till33Lunch = new JLabel(" ");
		till33Lunch.setOpaque(true);
		till33Lunch.setHorizontalAlignment(SwingConstants.CENTER);
		till33Lunch.setBackground(Color.WHITE);
		if(!rota.getTillsLunch().isEmpty() && rota.getTillsLunch().size() >= 3) {
			till33Lunch.setText(rota.getTillsLunch().get(2));
		}
		
		JLabel till33Pm = new JLabel(" ");
		till33Pm.setOpaque(true);
		till33Pm.setHorizontalAlignment(SwingConstants.CENTER);
		till33Pm.setBackground(Color.WHITE);
		if(!rota.getTillsPm().isEmpty() && rota.getTillsPm().size() >= 3) {
			till33Pm.setText(rota.getTillsPm().get(2));
		}
		
		/*
		 * Till 34
		 */		
		JLabel till34Label = new JLabel("Till 34");
		till34Label.setHorizontalAlignment(SwingConstants.CENTER);
		till34Label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel till34Am = new JLabel(" ");
		till34Am.setOpaque(true);
		till34Am.setHorizontalAlignment(SwingConstants.CENTER);
		till34Am.setBackground(Color.WHITE);
		if(!rota.getTillsAm().isEmpty() && rota.getTillsAm().size() >= 4) {
			till34Am.setText(rota.getTillsAm().get(3));
		}
		
		JLabel till34Lunch = new JLabel(" ");
		till34Lunch.setOpaque(true);
		till34Lunch.setHorizontalAlignment(SwingConstants.CENTER);
		till34Lunch.setBackground(Color.WHITE);
		if(!rota.getTillsLunch().isEmpty() && rota.getTillsLunch().size() >= 4) {
			till34Lunch.setText(rota.getTillsLunch().get(3));
		}
		
		JLabel till34Pm = new JLabel(" ");
		till34Pm.setOpaque(true);
		till34Pm.setHorizontalAlignment(SwingConstants.CENTER);
		till34Pm.setBackground(Color.WHITE);
		if(!rota.getTillsPm().isEmpty() && rota.getTillsPm().size() >= 4) {
			till34Pm.setText(rota.getTillsPm().get(3));
		}
		
		/*
		 * Till 35 (Ice Cream)
		 */
		JLabel till35Label = new JLabel("Till 35");
		till35Label.setHorizontalAlignment(SwingConstants.CENTER);
		till35Label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel till35Am = new JLabel(" ");
		till35Am.setOpaque(true);
		till35Am.setHorizontalAlignment(SwingConstants.CENTER);
		till35Am.setBackground(Color.WHITE);
		if(!rota.getIceCreamAm().isEmpty()) {
			till35Am.setText(Arrays.toString(rota.getIceCreamAm().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		
		JLabel till35Lunch = new JLabel(" ");
		till35Lunch.setOpaque(true);
		till35Lunch.setHorizontalAlignment(SwingConstants.CENTER);
		till35Lunch.setBackground(Color.WHITE);
		if(!rota.getIceCreamLunch().isEmpty()) {
			till35Lunch.setText(Arrays.toString(rota.getIceCreamLunch().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		
		JLabel till35Pm = new JLabel(" ");
		till35Pm.setOpaque(true);
		till35Pm.setHorizontalAlignment(SwingConstants.CENTER);
		till35Pm.setBackground(Color.WHITE);
		if(!rota.getIceCreamPm().isEmpty()) {
			till35Pm.setText(Arrays.toString(rota.getIceCreamPm().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		
		JLabel tablesLabel = new JLabel("Tables");
		tablesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tablesLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel tablesAm = new JLabel(" ");
		tablesAm.setOpaque(true);
		tablesAm.setHorizontalAlignment(SwingConstants.CENTER);
		tablesAm.setBackground(Color.WHITE);
		if(!rota.getTablesAm().isEmpty()) {
			tablesAm.setText(Arrays.toString(rota.getTablesAm().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		
		JLabel tablesLunch = new JLabel(" ");
		tablesLunch.setOpaque(true);
		tablesLunch.setHorizontalAlignment(SwingConstants.CENTER);
		tablesLunch.setBackground(Color.WHITE);
		if(!rota.getTablesLunch().isEmpty()) {
			tablesLunch.setText(Arrays.toString(rota.getTablesLunch().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		
		JLabel tablesPm = new JLabel(" ");
		tablesPm.setOpaque(true);
		tablesPm.setHorizontalAlignment(SwingConstants.CENTER);
		tablesPm.setBackground(Color.WHITE);
		if(!rota.getTablesPm().isEmpty()) {
			tablesPm.setText(Arrays.toString(rota.getTablesPm().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		
		JLabel stockLabel = new JLabel("Stock");
		stockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stockLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel stockAm = new JLabel(" ");
		stockAm.setOpaque(true);
		stockAm.setHorizontalAlignment(SwingConstants.CENTER);
		stockAm.setBackground(Color.WHITE);
		if(rota.getStockAm() != null) {
			stockAm.setText(rota.getStockAm());
		}
		
		JLabel stockLunch = new JLabel(" ");
		stockLunch.setOpaque(true);
		stockLunch.setHorizontalAlignment(SwingConstants.CENTER);
		stockLunch.setBackground(Color.WHITE);
		if(rota.getStockLunch() != null) {
			stockLunch.setText(rota.getStockLunch());
		}
		
		JLabel stockPm = new JLabel(" ");
		stockPm.setOpaque(true);
		stockPm.setHorizontalAlignment(SwingConstants.CENTER);
		stockPm.setBackground(Color.WHITE);
		if(rota.getStockPm() != null) {
			stockPm.setText(rota.getStockPm());
		}
		
		JLabel floatersLabel = new JLabel("Support");
		floatersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		floatersLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel floatersAm = new JLabel(" ");
		floatersAm.setOpaque(true);
		floatersAm.setHorizontalAlignment(SwingConstants.CENTER);
		floatersAm.setBackground(Color.WHITE);
		if(!rota.getFloaterAm().isEmpty()) {
			floatersAm.setText(Arrays.toString(rota.getFloaterAm().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		
		JLabel floatersLunch = new JLabel(" ");
		floatersLunch.setOpaque(true);
		floatersLunch.setHorizontalAlignment(SwingConstants.CENTER);
		floatersLunch.setBackground(Color.WHITE);
		if(!rota.getFloaterLunch().isEmpty()) {
			floatersLunch.setText(Arrays.toString(rota.getFloaterLunch().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		
		JLabel floatersPm = new JLabel(" ");
		floatersPm.setOpaque(true);
		floatersPm.setHorizontalAlignment(SwingConstants.CENTER);
		floatersPm.setBackground(Color.WHITE);
		if(!rota.getFloaterPm().isEmpty()) {
			floatersPm.setText(Arrays.toString(rota.getFloaterPm().toArray()).replaceAll("\\[", "").replaceAll("\\]", ""));
		}		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(header, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(roleLabel, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
							.addGap(17)
							.addComponent(amLabel, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addGap(37)
							.addComponent(lunchLabel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(pmLabel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(cookLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(supervisorLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(storesLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(till31Label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(till32Label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(till33Label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(till34Label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(till35Label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(tablesLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(stockLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(floatersLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(floatersAm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
									.addGap(343))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(stockAm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
										.addGap(343))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(tablesAm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
											.addGap(343))
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(storesText, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
											.addComponent(supervisorText, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
											.addComponent(cookText, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(till31Am, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
													.addComponent(till32Am, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
													.addComponent(till33Am, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
													.addComponent(till34Am, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
													.addComponent(till35Am, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
												.addGap(43)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(till32Lunch, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
														.addGap(38)
														.addComponent(till32Pm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(till31Lunch, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
														.addGap(38)
														.addComponent(till31Pm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(till33Lunch, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
														.addGap(38)
														.addComponent(till33Pm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(till34Lunch, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
														.addGap(38)
														.addComponent(till34Pm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(till35Lunch, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
														.addGap(38)
														.addComponent(till35Pm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(tablesLunch, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
														.addGap(38)
														.addComponent(tablesPm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(stockLunch, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
														.addGap(38)
														.addComponent(stockPm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
													.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
															.addComponent(floatersLunch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
														.addGap(38)
														.addComponent(floatersPm, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))))))))))
					.addGap(45))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(header, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(roleLabel)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(amLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(pmLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(lunchLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(supervisorLabel)
						.addComponent(supervisorText))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cookLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(cookText))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(storesLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(storesText))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(till31Label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(till31Am)
						.addComponent(till31Lunch)
						.addComponent(till31Pm))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(till32Label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(till32Am)
						.addComponent(till32Lunch)
						.addComponent(till32Pm))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(till33Label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(till33Lunch)
						.addComponent(till33Pm)
						.addComponent(till33Am))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(till34Label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(till34Lunch)
						.addComponent(till34Pm)
						.addComponent(till34Am))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(till35Label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(till35Lunch)
						.addComponent(till35Pm)
						.addComponent(till35Am))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tablesLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(tablesAm)
						.addComponent(tablesLunch)
						.addComponent(tablesPm))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(stockLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(stockAm)
						.addComponent(stockLunch)
						.addComponent(stockPm))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(floatersLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(floatersAm)
						.addComponent(floatersLunch)
						.addComponent(floatersPm))
					.addGap(18)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		
		
	}
}
