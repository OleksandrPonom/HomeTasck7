package org.example;

import org.example.database.Database;
import org.example.database.DatabaseCreateService;
import org.example.database.DatabaseInitService;

import java.time.LocalDate;
import java.util.logging.Logger;

public class AppInit {
	private static final Logger LOGGER = Logger.getLogger(AppInit.class.getName());
	public static void main(String[] args) {
		Database database = Database.getInstance();
		new DatabaseCreateService().initDB(database);
		DatabaseInitService databaseService = new DatabaseInitService(database);

		boolean insertWorker = databaseService.createNewWorker(
				"Jayms Drim", LocalDate.now().minusYears(30),
				"Junior", 20000);
		LOGGER.info("insertWorker " + insertWorker);

		boolean insertClient = databaseService.createNewClient("Maria Jonson");
		LOGGER.info("insertClient " + insertClient);

		boolean insertProject = databaseService.createNewProject(
				1, LocalDate.now().minusMonths(30),
				LocalDate.now().minusMonths(5)
		);
		LOGGER.info("insertProject " + insertProject);

		boolean insertPW = databaseService.createDependenceProjectWorker(
				2, 3);
		LOGGER.info("insertPW " + insertPW);
	}
}
