package de.lengsfeld.anlz4sqr.model;

import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenueHistory;
import fi.foyt.foursquare.api.entities.VenueHistoryGroup;

import javax.swing.table.AbstractTableModel;

public class HistoryTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private Result<VenueHistoryGroup> result;
    private String[] titles;

    public HistoryTableModel(Result<VenueHistoryGroup> result,
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
            return result.getResult().getItems().length;
        } else
            return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {

        VenueHistory[] venues = result.getResult().getItems();
		CompactVenue venue = venues[rowIndex].getVenue();

        switch (titles[colIndex]) {
            case "Been Here":
                return venues[rowIndex].getBeenHere();
            case "Name":
                return venue.getName();
            case "Count":
                return venue.getHereNow().getCount();
            case "Url":
                return venue.getUrl();
            case "CheckinsCount":
                return venue.getStats().getCheckinsCount();
            case "UsersCount":
                return venue.getStats().getUsersCount();

            case "*Name":
                return venue.getLocation().getName();
            case "Address":
                return venue.getLocation().getAddress();
            case "Zip":
                return venue.getLocation().getPostalCode();
            case "City":
                return venue.getLocation().getCity();
            case "Country":
                return venue.getLocation().getCountry();
            case "CrossStreet":
                return venue.getLocation().getCrossStreet();
            case "Distance":
                return venue.getLocation().getDistance();
            case "Lat":
                return venue.getLocation().getLat();
            case "Lng":
                return venue.getLocation().getLng();
            case "State":
                return venue.getLocation().getState();
            case "Category":
                for (Category c : venue.getCategories()) {
                    return c.getName();
                }
            case "Categories":
                for (Category c : venue.getCategories()) {
                    return c.getPluralName();
                }
            case "CategoryId":
                for (Category c : venue.getCategories()) {
                    return c.getId();
                }
            case "MultipleCategories":
                String categories = "";
                for (Category c : venue.getCategories()) {
                    categories += c.getName() + ", " + venue.getCategories().length;
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
