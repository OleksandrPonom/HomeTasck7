package org.example.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseInitService {
	private PreparedStatement insertWorkerST;
	private PreparedStatement insertClientST;
	private PreparedStatement insertProjectST;
	private PreparedStatement insertProjectWorkerST;

	public DatabaseInitService(Database database){
		Connection connection = database.getConnection();
		try {
			insertWorkerST = connection.prepareStatement(
					"INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)"
			);

			insertClientST = connection.prepareStatement(
					"INSERT INTO client (name) VALUES (?)"
			);

			insertProjectST = connection.prepareStatement(
					"INSERT INTO project (client_id, start_date, finish_date) VALUES (?, ?, ?)"
			);

			insertProjectWorkerST = connection.prepareStatement(
					"INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?)"
			);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean createNewWorker(String name, LocalDate birthday, String level, int salary){
		try {
			insertWorkerST.setString(1, name);
			insertWorkerST.setDate(2, java.sql.Date.valueOf(birthday));
			insertWorkerST.setString(3, level);
			insertWorkerST.setInt(4, salary);
			return insertWorkerST.executeUpdate() == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean createNewClient(String name){
		try {
			insertClientST.setString(1, name);
			return insertClientST.executeUpdate() == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean createNewProject(long clientId, LocalDate startDate, LocalDate finishDate){
		try {
			insertProjectST.setLong(1, clientId);
			insertProjectST.setDate(2, java.sql.Date.valueOf(startDate));
			insertProjectST.setDate(3, java.sql.Date.valueOf(finishDate));
			return insertProjectST.executeUpdate() == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean createDependenceProjectWorker(long projectId, long workerId){
		try {
			insertProjectWorkerST.setLong(1, projectId);
			insertProjectWorkerST.setLong(2, workerId);
			return insertProjectWorkerST.executeUpdate() == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
