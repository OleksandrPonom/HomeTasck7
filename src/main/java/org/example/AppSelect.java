package org.example;

import org.example.database.Database;
import org.example.database.DatabaseSelectService;

public class AppSelect {
	public static void main(String[] args) {
		Database database = Database.getInstance();
		DatabaseSelectService databaseService = new DatabaseSelectService(database);

		databaseService.printWorkerInfo(1);

		databaseService.printClientInfo(1);

		databaseService.printProjectInfo(1);
	}
}
