package de.lengsfeld.anlz4sqr.model;


public class HistoryView implements TableViewStrategy {

	private String[] titles = { "Name", "Address", "Zip", "City", "Country", "Been Here"};

	@Override
	public String[] tableView() {

		System.out.println("--------------------------");
		System.out.println("HistoryView Selected");
		System.out.println("--------------------------");
		return titles;
	}

}
