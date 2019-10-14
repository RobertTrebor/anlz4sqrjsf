package de.lengsfeld.anlz4sqr.model;

import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CheckinGroup;

import javax.swing.table.AbstractTableModel;

public class CheckinsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private CheckinGroup result;
	private String[] titles;

	public CheckinsTableModel(CheckinGroup result,
                              String... titles) {
		super();
		this.titles = titles;
		this.result = result;
	}

	@Override
	public int getColumnCount() {
		return titles.length;
	}

	@Override
	public int getRowCount() {
		if (result != null) {
			return result.getItems().length;
		} else
			return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {

		Checkin[] venue = result.getItems();

		switch (titles[colIndex]) {
		case "Last Name":
			String lastName = "";
			if(venue[rowIndex].getUser() != null && venue[rowIndex].getUser().getLastName() != null) {
				lastName = venue[rowIndex].getUser().getLastName();
			}
			return lastName;
		case "Venue":
			return venue[rowIndex].getVenue().getName();
		case "Created at":
			return venue[rowIndex].getCreatedAt();
		case "Location":
			return venue[rowIndex].getVenue().getLocation();
		case "Address":
			return venue[rowIndex].getVenue().getLocation().getAddress();
		case "Zip":
			return venue[rowIndex].getVenue().getLocation().getPostalCode();
		case "City":	
			return venue[rowIndex].getVenue().getLocation().getCity();
		case "Country":
			return venue[rowIndex].getVenue().getLocation().getCountry();
		case "CrossStreet":
			return venue[rowIndex].getVenue().getLocation().getCrossStreet();
		case "Distance":
			return venue[rowIndex].getVenue().getLocation().getDistance();
		case "Lat":
			return venue[rowIndex].getVenue().getLocation().getLat();
		case "Lng":
			return venue[rowIndex].getVenue().getLocation().getLng();
		case "State":			
			return venue[rowIndex].getVenue().getLocation().getState();
		case "Category":
			for(Category c : venue[rowIndex].getVenue().getCategories()) {
				return c.getName();
			} 
		case "Categories":
			for(Category c : venue[rowIndex].getVenue().getCategories()) {
				return c.getPluralName();
			}		 
		case "CategoryId":
			for(Category c : venue[rowIndex].getVenue().getCategories()) {
				return c.getId();
			}
		case "MultipleCategories":
			String categories = "";
			for(Category c : venue[rowIndex].getVenue().getCategories()) {
				categories += c.getName() + ", " + venue[rowIndex].getVenue().getCategories().length;
			}
			return categories;
		}
		return 0;
	}

	@Override
	public String getColumnName(int column) {
		return titles[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
			return false;
		}
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}
}
