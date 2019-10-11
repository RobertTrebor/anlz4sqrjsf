package de.lengsfeld.anlz4sqr.beans;

import de.lengsfeld.anlz4sqr.connect.FSManager;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class BasicBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MapBean mapBean;

	@Inject
	private CategoriesController categoriesController;

	private FSManager fsManager = new FSManager();

	private Result<VenuesSearchResult> result;
	private List<CompactVenue> venues;
	private CompactVenue selectedVenue;

	private String coordinates;
	private String query;
	private String category;

	public BasicBean() {
		System.setProperty("java.net.useSystemProxies", "true");
	}

	public void loadResult() {
		category = categoriesController.getCategoryId();
		if (category == "0000"){
			category = "";
		}
		System.out.print("BasicBean.java - category: " + category);
		coordinates = mapBean.getCoordinates();
		System.out.println("\t coordinates");
		try {
			result = fsManager.draw3(coordinates, query,
					category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResult(result);
		venues = Arrays.asList(result.getResult().getVenues());
		setVenues(venues);
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Result<VenuesSearchResult> getResult() {
		return result;
	}

	public void setResult(Result<VenuesSearchResult> result) {
		this.result = result;
	}

	public List<CompactVenue> getVenues() {
		return venues;
	}

	public void setVenues(List<CompactVenue> venues) {
		this.venues = venues;
	}

	public CompactVenue getSelectedVenue() {
		if(selectedVenue != null) {
			System.out.println(selectedVenue.getName());
			System.out.println(selectedVenue.getLocation().getCity());
			System.out.println(selectedVenue.getLocation().getCountry());
			System.out.println(selectedVenue.getLocation().getCrossStreet());
			selectedVenue.getHereNow().getCount();
			System.out.println();
		}
		return selectedVenue;
	}

	public void setSelectedVenue(CompactVenue selectedVenue) {
		this.selectedVenue = selectedVenue;
	}
}