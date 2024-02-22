package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseSelectService {
	private static final Logger LOGGER = Logger.getLogger(DatabaseSelectService.class.getName());
	private PreparedStatement selectWorkerST;
	private PreparedStatement selectClientST;
	private PreparedStatement selectProjectST;

	public DatabaseSelectService(Database database){
		Connection connection = database.getConnection();
		try {
			selectWorkerST = connection.prepareStatement(
					"SELECT name, birthday, level, salary FROM worker WHERE id = ?"
			);

			selectClientST = connection.prepareStatement(
					"SELECT name FROM client WHERE id = ?"
			);

			selectProjectST = connection.prepareStatement(
					"SELECT client_id, start_date, finish_date FROM project WHERE id = ?"
			);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean printWorkerInfo(long id){
		try {
			selectWorkerST.setLong(1, id);
		} catch (Exception ex){
			return false;
		}

		try (ResultSet rs = selectWorkerST.executeQuery();){
			if(!rs.next()){
				LOGGER.info("Worker with id " + id + " not found!");
				return false;
			}
			String name = rs.getString("name");
			String birthday = rs.getString("birthday");
			String level = rs.getString("level");
			int salary = rs.getInt("salary");
			LOGGER.info("name: " + name +
					", birthday: " + birthday +
					", level: " + level +
					", salary: " + salary);
			return true;
		} catch (Exception ex){
			return false;
		}
	}

	public boolean printClientInfo(long id){
		try {
			selectClientST.setLong(1, id);
		} catch (Exception ex){
			return false;
		}

		try (ResultSet rs = selectClientST.executeQuery();){
			if(!rs.next()){
				LOGGER.info("Client with id " + id + " not found!");
				return false;
			}
			String name = rs.getString("name");
			LOGGER.info("name: " + name);
			return true;
		} catch (Exception ex){
			return false;
		}
	}

	public boolean printProjectInfo(long id){
		try {
			selectProjectST.setLong(1, id);
		} catch (Exception ex){
			return false;
		}

		try (ResultSet rs = selectProjectST.executeQuery();){
			if(!rs.next()){
				LOGGER.info("Project with id " + id + " not found!");
				return false;
			}
			long clientId = rs.getLong("client_id");
			String startDate = rs.getString("start_date");
			String finishDate = rs.getString("finish_date");
			LOGGER.info("clientId: " + clientId +
					", start_date: " + startDate +
					", finish_date: " + finishDate);
			return true;
		} catch (Exception ex){
			return false;
		}
	}

}

