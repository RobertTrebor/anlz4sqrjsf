package de.lengsfeld.anlz4sqr.beans;

import de.lengsfeld.anlz4sqr.connect.FSConnectWeb;
import de.lengsfeld.anlz4sqr.connect.FSConnector;
import de.lengsfeld.anlz4sqr.connect.FSManager;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenueHistory;
import fi.foyt.foursquare.api.entities.VenueHistoryGroup;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class BasicBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MapBean mapBean;

	@Inject
	private CategoriesController categoriesController;

	private FSManager fsManager = new FSManager();
	private FSConnector fsConnect = FSConnectWeb.getInstance();

	private List<CompactVenue> venues;
	private List<VenueHistory> venueHistories;
	private CompactVenue selectedVenue;
	private Integer view = 0;

	private String coordinates;
	private String query;
	private String category;

	public BasicBean() {
		System.setProperty("java.net.useSystemProxies", "true");
	}

	public void connect() throws IOException {
		String authorizationCode = fsConnect.authorize();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		String sessionId = ";jsessionid=" + facesContext.getExternalContext().getSessionId(false);
		response.sendRedirect(authorizationCode + sessionId);
	}

	public void onStart(){
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Map map = request.getParameterMap();
        if(map.containsKey("code")){
        	String[] headers = (String[]) map.get("code");
			fsConnect.authorizeToken(headers[0]);
		}
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
			if(view == 0){
				Result<VenuesSearchResult> result = fsManager.draw3(coordinates, query,
						category);
				venues = Arrays.asList(result.getResult().getVenues());
				setVenues(venues);
				setView(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadHistory(){
		setView(1);
		Result<VenueHistoryGroup> result = fsManager.venueHistory();
		if(result != null) {
			VenueHistoryGroup venueHistoryGroup = result.getResult();
			venueHistoryGroup.getCount();
			venueHistories = Arrays.asList(venueHistoryGroup.getItems());
			setVenueHistories(venueHistories);
		}
	}

	public void update(){
		if(view == 0){
			loadResult();
		} else {
			loadHistory();
		}
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<CompactVenue> getVenues() {
		return venues;
	}

	public void setVenues(List<CompactVenue> venues) {
		this.venues = venues;
	}

	public List<VenueHistory> getVenueHistories() {
		return venueHistories;
	}

	public void setVenueHistories(List<VenueHistory> venueHistories) {
		this.venueHistories = venueHistories;
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

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

}