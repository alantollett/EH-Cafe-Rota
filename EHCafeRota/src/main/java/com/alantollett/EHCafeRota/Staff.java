package com.alantollett.EHCafeRota;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Staff {
	
	private String name;
	private boolean supervisor; // default false
	private boolean cook; // true if Ros / Denise
	private boolean tills; // default true for other staff
	private boolean iceCream; // default true for other staff
	private boolean stock; // default true for other staff
	private boolean stores; // true if Mark
	
	public Staff(File staffFile) {
		// creates a buffered reader for the file so each line can be read, catches file exceptions
		try(BufferedReader reader = new BufferedReader(new FileReader(staffFile))){ 
			String line;
			while((line = reader.readLine()) != null) { //while the reader can continue reading
				if(line.startsWith("Name: ")) {
					name = line.split(": ")[1];
				}else if(line.startsWith("Supervisor: ")) {
					supervisor = isTrue(line);
				}else if(line.startsWith("Cook:")) {
					cook = isTrue(line);
				}else if(line.startsWith("Tills:")) {
					tills = isTrue(line);
				}else if(line.startsWith("IceCream: ")) {
					iceCream = isTrue(line);
				}else if(line.startsWith("Stock")) {
					stock = isTrue(line);
				}else if(line.startsWith("Stores: ")) {
					stores = isTrue(line);
				}
			}
			reader.close();
		}catch(FileNotFoundException notFound) {
			notFound.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	// returns true if the end of the "line" of the staff file is true
	private boolean isTrue(String line) {
		if(line.split(": ")[1].equals("true")) {
			return true;
		}else {
			return false;
		}
	}
	
	// updates the values stored in the text file for the staff member
	public void updateFile() {
		File file = new File("Staff/" + name + ".txt");
		try(PrintWriter writer = new PrintWriter(file)){
			writer.println("Name: " + name);
			writer.println("Supervisor: " + supervisor);
			writer.println("Cook: " + cook);
			writer.println("Tills: " + tills);
			writer.println("IceCream: " + iceCream);
			writer.println("Stock: " + stock);
			writer.println("Stores: " + stores);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(boolean supervisor) {
		this.supervisor = supervisor;
	}
	public boolean getCook() {
		return cook;
	}
	public void setCook(boolean cook) {
		this.cook = cook;
	}
	public boolean getTills() {
		return tills;
	}
	public void setTills(boolean tills) {
		this.tills = tills;
	}
	public boolean getIceCream() {
		return iceCream;
	}
	public void setIceCream(boolean iceCream) {
		this.iceCream = iceCream;
	}
	public boolean getStock() {
		return stock;
	}
	public void setStock(boolean stock) {
		this.stock = stock;
	}
	public boolean getStores() {
		return stores;
	}
	public void setStores(boolean stores) {
		this.stores = stores;
	}



}
