package com.alantollett.EHCafeRota;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Rota {
	
	List<Staff> allUsableStaff = new ArrayList<Staff>(); // stores a list of all usable staff
	List<Staff> potentialCooks = new ArrayList<Staff>();
	List<Staff> potentialStores = new ArrayList<Staff>();

	private List<String> supervisors = new ArrayList<String>();
	private String cook;
	private String stores;
	private List<String> tillsAm = new ArrayList<String>();
	private List<String> tillsLunch = new ArrayList<String>();
	private List<String> tillsPm = new ArrayList<String>();
	private String stockAm;
	private String stockLunch;
	private String stockPm;
	private List<String> tablesAm = new ArrayList<String>();
	private List<String> tablesLunch = new ArrayList<String>();
	private List<String> tablesPm= new ArrayList<String>();
	private List<String> iceCreamAm = new ArrayList<String>();
	private List<String> iceCreamLunch = new ArrayList<String>();
	private List<String> iceCreamPm = new ArrayList<String>();
	private List<String> floaterAm = new ArrayList<String>();
	private List<String> floaterLunch = new ArrayList<String>();
	private List<String> floaterPm = new ArrayList<String>();
	
	
	public Rota(List<Staff> staff) {
		Iterator<Staff> iter = staff.iterator();
		while (iter.hasNext()) {
			Staff s = iter.next();
			// adds supervisors to the rota, then removes them from staff as no longer necessary
			if(s.getSupervisor()) {
				addSupervisor(s.getName());
				iter.remove();
			// adds potential cooks to the cook list, keeps them in staff list just in case they don't get selected
			}else if(s.getCook()) {
				potentialCooks.add(s);
				iter.remove();
			// adds potential stores people to the stores list, keeps them in staff list just in case they don't get selected
			}else if(s.getStores()) {
				potentialStores.add(s);
				iter.remove();
			}
		}
		
		// pick a random cook or, if none of the normal cooks are in, selects a random member of staff
		if(potentialCooks.isEmpty()) {
			setCook(takeStaff(staff).getName());
		}else {
			setCook(takeStaff(potentialCooks).getName());
		}
		// selects a random stores person if normal stores person isn't in
		if(potentialStores.isEmpty()) {
			setStores(takeStaff(staff).getName());
		}else {
			setStores(takeStaff(potentialStores).getName());
		}
		
		updateUsableStaff(staff);
		
		if(allUsableStaff.size() >= 3) {
			generate(allUsableStaff, 1);
			updateUsableStaff(staff);
			generate(allUsableStaff, 2);
			updateUsableStaff(staff);
			generate(allUsableStaff, 3);			
		}else {
			JOptionPane.showMessageDialog(new Frame(),
				    "There is not enough staff in today to generate a ",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*
	 * METHODS
	 */
	private void updateUsableStaff(List<Staff> staff) {
		allUsableStaff.clear();
		for(Staff s : staff) {
			allUsableStaff.add(s);
		}
	}
	
	// am = 1, lunch = 2, pm = 3
	private void generate(List<Staff> staff, int dayPart) {
		// if there is 7 people, put 4 on tills, then one on tables, stock and then icecream.
		// if after this there are any staff left, add an extra to tables, then ice cream, and then make floaters
		if(staff.size() >=  7) {
			generateTills(staff, 4, dayPart);
			if(dayPart == 1) {
				addTablesAm(takeStaff(staff).getName());
				setStockAm(takeStaff(staff, "stock").getName());
				addIceCreamAm(takeStaff(staff, "icecream").getName());
				
				while(!staff.isEmpty()) {
					if(getTablesAm().size() == 1) {
						addTablesAm(takeStaff(staff).getName());
					}else if(getIceCreamAm().size() == 1) {
						addIceCreamAm(takeStaff(staff, "icecream").getName());
					}else {
						addFloaterAm(takeStaff(staff).getName());
					}
				}
			}else if(dayPart == 2) {
				addTablesLunch(takeStaff(staff).getName());
				setStockLunch(takeStaff(staff, "stock").getName());
				addIceCreamLunch(takeStaff(staff, "icecream").getName());
				
				while(!staff.isEmpty()) {
					if(getTablesLunch().size() == 1) {
						addTablesLunch(takeStaff(staff).getName());
					}else if(getIceCreamLunch().size() == 1) {
						addIceCreamLunch(takeStaff(staff, "icecream").getName());
					}else {
						addFloaterLunch(takeStaff(staff).getName());
					}
				}
			}else if(dayPart == 3) {
				addTablesPm(takeStaff(staff).getName());
				setStockPm(takeStaff(staff, "stock").getName());
				addIceCreamPm(takeStaff(staff, "icecream").getName());
				
				while(!staff.isEmpty()) {
					if(getTablesPm().size() == 1) {
						addTablesPm(takeStaff(staff).getName());
					}else if(getIceCreamPm().size() == 1) {
						addIceCreamPm(takeStaff(staff, "icecream").getName());
					}else {
						addFloaterPm(takeStaff(staff).getName());
					}
				}
			}
		// if only six, sacrifice iceCream
		}else if(staff.size() == 6) {
			generateTills(staff, 4, dayPart);
			if(dayPart == 1) {
				addTablesAm(takeStaff(staff).getName());
				setStockAm(takeStaff(staff, "stock").getName());
			}else if(dayPart == 2) {
				addTablesLunch(takeStaff(staff).getName());
				setStockLunch(takeStaff(staff, "stock").getName());
			}if(dayPart == 3) {
				addTablesPm(takeStaff(staff).getName());
				setStockPm(takeStaff(staff, "stock").getName());
			}
		// if only 5, only put 3 on tills
		}else if(staff.size() == 5) {
			generateTills(staff, 3, dayPart);
			if(dayPart == 1) {
				addTablesAm(takeStaff(staff).getName());
				setStockAm(takeStaff(staff, "stock").getName());
			}else if(dayPart == 2) {
				addTablesLunch(takeStaff(staff).getName());
				setStockLunch(takeStaff(staff, "stock").getName());
			}else if(dayPart == 3) {
				addTablesPm(takeStaff(staff).getName());
				setStockPm(takeStaff(staff, "stock").getName());
			}
		// if only 4, put 3 on tills still, but replace one tables and stock with one floater
		}else if(staff.size() == 4) {
			generateTills(staff, 3, dayPart);
			if(dayPart == 1) {
				addFloaterAm(takeStaff(staff).getName());
			}else if(dayPart == 2) {
				addFloaterLunch(takeStaff(staff).getName());
			}else if(dayPart == 3) {
				addFloaterPm(takeStaff(staff).getName());
			}
		// if only 3 staff, put two on tills and make one floater
		}else if(staff.size() == 3) {
			generateTills(staff, 2, dayPart);
			if(dayPart == 1) {
				addFloaterAm(takeStaff(staff).getName());
			}else if(dayPart == 2) {
				addFloaterLunch(takeStaff(staff).getName());
			}else if(dayPart == 3) {
				addFloaterPm(takeStaff(staff).getName());
			}
		// if less than 3 staff, it is not worth generating a rota
		}
	}
	
	
	private void generateTills(List<Staff> staff, int maxTills, int dayPart) {
		Random rand = new Random();
		for(int i = 0; i < maxTills; i++) {
			boolean canDoRole = false;
			int count = 0;
			while(!canDoRole || count >= 20) {
				System.out.println(i);
				int index = rand.nextInt(staff.size());
				if(staff.get(index).getTills()) {
					canDoRole = true;
					if(dayPart == 1) {
						addTillsAm(staff.get(index).getName());
					}else if(dayPart == 2) {
						addTillsLunch(staff.get(index).getName());
					}else if(dayPart == 3) {
						addTillsPm(staff.get(index).getName());
					}
					staff.remove(index);
				}else {
					count++;
				}
			}
		}
	}
	
	// used if role is icecream or tables
	private Staff takeStaff(List<Staff> staff, String role) {
		Random rand = new Random();
		boolean canDoRole = false;
		int count = 0;
		while(!canDoRole || count >= 20) {
			int index = rand.nextInt(staff.size());
			Staff returned = staff.get(index);
			
			switch(role) {
			case "icecream":
				if(returned.getIceCream()) {
					canDoRole = true;
					break;
				}
			case "stock":
				if(returned.getStock()) {
					canDoRole = true;
					break;
				}
			}
			if(canDoRole) {
				staff.remove(index);
				return returned; 
			}else {
				count ++;
			}
		}
		return null;
	}
	
	private Staff takeStaff(List<Staff> staff) {
		Random rand = new Random();
		int index = rand.nextInt(staff.size());
		Staff returned = staff.get(index);
		staff.remove(index);
		return returned;
	}
	
	
	/*
	 * GETTERS AND SETTERS
	 */
	public List<String> getSupervisors() {
		return supervisors;
	}

	public void addSupervisor(String name) {
		this.supervisors.add(name);
	}

	public String getCook() {
		return cook;
	}

	public void setCook(String cook) {
		this.cook = cook;
	}

	public String getStores() {
		return stores;
	}

	public void setStores(String stores) {
		this.stores = stores;
	}

	public String getStockAm() {
		return stockAm;
	}

	public void setStockAm(String stockAm) {
		this.stockAm = stockAm;
	}

	public String getStockLunch() {
		return stockLunch;
	}

	public void setStockLunch(String stockLunch) {
		this.stockLunch = stockLunch;
	}

	public String getStockPm() {
		return stockPm;
	}

	public void setStockPm(String stockPm) {
		this.stockPm = stockPm;
	}

	public List<String> getTillsAm() {
		return tillsAm;
	}

	public void addTillsAm(String name) {
		this.tillsAm.add(name);
	}

	public List<String> getTillsLunch() {
		return tillsLunch;
	}

	public void addTillsLunch(String name) {
		this.tillsLunch.add(name);
	}

	public List<String> getTillsPm() {
		return tillsPm;
	}

	public void addTillsPm(String name) {
		this.tillsPm.add(name);
	}

	public List<String> getTablesAm() {
		return tablesAm;
	}

	public void addTablesAm(String name) {
		this.tablesAm.add(name);
	}

	public List<String> getTablesLunch() {
		return tablesLunch;
	}

	public void addTablesLunch(String name) {
		this.tablesLunch.add(name);
	}

	public List<String> getTablesPm() {
		return tablesPm;
	}

	public void addTablesPm(String name) {
		this.tablesPm.add(name);
	}

	public List<String> getIceCreamAm() {
		return iceCreamAm;
	}

	public void addIceCreamAm(String name) {
		this.iceCreamAm.add(name);
	}

	public List<String> getIceCreamLunch() {
		return iceCreamLunch;
	}

	public void addIceCreamLunch(String name) {
		this.iceCreamLunch.add(name);
	}

	public List<String> getIceCreamPm() {
		return iceCreamPm;
	}

	public void addIceCreamPm(String name) {
		this.iceCreamPm.add(name);
	}

	public List<String> getFloaterAm() {
		return floaterAm;
	}

	public void addFloaterAm(String name) {
		this.floaterAm.add(name);
	}

	public List<String> getFloaterLunch() {
		return floaterLunch;
	}

	public void addFloaterLunch(String name) {
		this.floaterLunch.add(name);
	}

	public List<String> getFloaterPm() {
		return floaterPm;
	}

	public void addFloaterPm(String name) {
		this.floaterPm.add(name);
	}
	
}
