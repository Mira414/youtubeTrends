package YouTubeTrends.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import YouTubeTrends.model.*;

public class UsersDao  extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		super();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users Users) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(Users.getUserId(), Users.getFirstName(),
			Users.getLastName()));

		String insertUser = "INSERT INTO Users(UserId,DoB) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setInt(1, Users.getUserId());
			insertStmt.setTimestamp(2, new Timestamp(Users.getDoB().getTime()));
			insertStmt.executeUpdate();
			return Users;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Update the LastName of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateLastName(Users user, String newLastName) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updateLastName(user, newLastName);
		return user;
	}

	/**
	 * Delete the Users instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, user.getUserId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserId=" + user.getUserId());
			}
			super.delete(user);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Users getUserByUserId(int userId) throws SQLException {
		// To build an User object, we need the Persons record, too.
		String selectUser =
			"SELECT Users.UserId AS UserId, FirstName, LastName, DoB " +
			"FROM Users INNER JOIN Persons " +
			"  ON Users.UserId = Persons.UserId " +
			"WHERE Users.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				Users User = new Users(userId, firstName, lastName, dob);
				return User;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Users> getUsersByFirstName(String firstName)
			throws SQLException {
		List<Users> Users = new ArrayList<Users>();
		String selectUsers =
			"SELECT Users.UserId AS UserId, FirstName, LastName, DoB " +
			"FROM Users INNER JOIN Persons " +
			"  ON Users.UserId = Persons.UserId " +
			"WHERE Persons.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int UserId = results.getInt("UserId");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				Users User = new Users(UserId, resultFirstName, lastName, dob);
				Users.add(User);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return Users;
	}
}
