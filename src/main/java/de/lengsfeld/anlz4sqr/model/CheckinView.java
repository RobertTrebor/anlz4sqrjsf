package de.lengsfeld.anlz4sqr.model;


public class CheckinView implements TableViewStrategy {

	private String[] titles = { "Last Name", "First Name", "Venue", "City", "Address", "Country",
			"Created at"};

	@Override
	public String[] tableView() {

		System.out.println("--------------------------");
		System.out.println("CheckinView Selected");
		System.out.println("--------------------------");
		return titles;
	}

}
