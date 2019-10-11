package de.lengsfeld.anlz4sqr.test;

import de.lengsfeld.anlz4sqr.connect.FSManager;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public class Test {
	private Result<VenuesSearchResult> result = null;
	private CompactVenue[] venue = null;
	private CompactVenue selectedVenue;

	private String latitude = "52.531227";
	private String longitude = "13.403921";
	private String coordinates = latitude + "," + longitude;
	private String query;
	private String category;

	public Test() {
		System.setProperty("java.net.useSystemProxies", "true");
		result = new FSManager().draw3(coordinates, query, category);
		venue = result.getResult().getVenues();
		System.out.println("Venue: " + venue.toString());
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Result<VenuesSearchResult> getResult() {
		result = new FSManager().draw3(coordinates, query, category);
		return result;
	}

	public void setResult(Result<VenuesSearchResult> result) {
		this.result = result;
	}

	public CompactVenue[] getVenue() {
		return venue;
	}

	public CompactVenue getSelectedVenue() {
		return selectedVenue;
	}

	public void setSelectedVenue(CompactVenue selectedVenue) {
		this.selectedVenue = selectedVenue;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Test().getVenue().toString());
	}

}
