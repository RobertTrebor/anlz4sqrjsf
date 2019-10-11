package de.lengsfeld.anlz4sqr.connect;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public final class FSManager {

	FSConnect fs = FSConnect.getInstance();
	FoursquareApi foursquareApi;

	public FSManager() {
		foursquareApi = fs.getFoursquareApi();
	}

	public Result<VenuesSearchResult> collectVenues(String ll, String query,
			String categoryId) throws FoursquareApiException {

		System.setProperty("java.net.useSystemProxies", "true");

		Result<VenuesSearchResult> result = foursquareApi
				.venuesSearch(ll, null, null, null, query, 50, null,
						categoryId, null, null, null, null, null);

		System.out.println("FSManager.java - Now using coordinates: " + ll);
		if (result.getMeta().getCode() == 200) {
			// if query was ok we can finally do something with the data
			System.out.println("\tOK! ");
			System.out.print("FSManager.java - Number of items: "
					+ result.getResult().getVenues().length + "\t");
			for (CompactVenue venue : result.getResult().getVenues()) {
				System.out.print(venue.getName() + "\t");
			}
			System.out.println("\nAgain OK! ");
			return result;

		} else {
			// TODO: Proper error handling
			System.out.println("Error occured: ");
			System.out.println("  code: " + result.getMeta().getCode());
			System.out.println("  type: " + result.getMeta().getErrorType());
			System.out
					.println("  detail: " + result.getMeta().getErrorDetail());
		}
		return result;
	}

	public Result<VenuesSearchResult> draw3(String coordinates, String query,
			String categoryId) {
		Result<VenuesSearchResult> result = null;
		try {
			result = collectVenues(coordinates, query, categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}