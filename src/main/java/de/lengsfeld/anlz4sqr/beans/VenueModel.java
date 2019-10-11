package de.lengsfeld.anlz4sqr.beans;

import fi.foyt.foursquare.api.entities.CompactVenue;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

public class VenueModel extends ListDataModel implements
		SelectableDataModel<CompactVenue>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VenueModel() {

	}

	public VenueModel(List<CompactVenue> data) {
		super(data);
	}
	
	@Override
	public Object getRowKey(CompactVenue venue) {
		// TODO Auto-generated method stub
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out
				.println("public Object getRowKey(Result<VenuesSearchResult> result)");
		System.out
				.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		return venue.getName();
	}

	@Override
	public CompactVenue getRowData(String arg0) {
		// TODO Auto-generated method stub
		System.out
				.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out
				.println("public Result<VenuesSearchResult> getRowData(String arg0)");
		System.out
				.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		return null;
	}

}
