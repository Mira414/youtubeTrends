/* by Yiyang Bu */

package youtubetrends.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import youtubetrends.model.Countries;


/**
 * Data access object (DAO) class to interact with the underlying Users table in your MySQL
 * instance. This is used to store {@link Countries} into your MySQL instance and retrieve {@link
 * Countries} from MySQL instance.
 */
public class CountriesDao {

  // Single pattern: instantiation is limited to one object.
  private static CountriesDao instance = null;
  protected ConnectionManager connectionManager;

  protected CountriesDao() {
    connectionManager = new ConnectionManager();
  }

  public static CountriesDao getInstance() {
    if (instance == null) {
      instance = new CountriesDao();
    }
    return instance;
  }

  /**
   * Save the {@link Countries} instance by storing it in your MySQL instance. This runs a INSERT
   * statement.
   *
   * @throws SQLException
   */
  public Countries create(Countries Country) throws SQLException {
    String insertUser = "INSERT INTO Countries(CountryId, CountryName) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertUser);
      insertStmt.setString(1, Country.getCountryId().toString());
      insertStmt.setString(2, Country.getCountryName());

      insertStmt.executeUpdate();

      return Country;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
    }
  }

  /**
   * Delete the {@link Countries} instance. This runs a DELETE statement.
   *
   * @throws SQLException
   */
  public Countries delete(Countries Country) throws SQLException {
    String deleteCountry = "DELETE FROM Countries WHERE CountryId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCountry);
      deleteStmt.setString(1, Country.getCountryId().toString());
      deleteStmt.executeUpdate();

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

  /**
   * Get the {@link Countries} record by fetching it from your MySQL instance. This runs a SELECT
   * statement and returns a single {@link Countries} instance.
   *
   * @throws SQLException
   */
  public Countries getCountryByCountryId(Integer CountryId) throws SQLException {
    String selectCountry = "SELECT CountryId, CountryName FROM Countries WHERE CountryId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCountry);
      selectStmt.setString(1, CountryId.toString());

      results = selectStmt.executeQuery();

      if (results.next()) {
        String CountryName = results.getString("CountryName");
        Countries Country = new Countries(CountryId, CountryName);
        return Country;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }
	public Countries updateContent(Countries countries, String countryName) throws SQLException {
		String updateComment = "UPDATE Countries SET CountryName=?  WHERE CountryId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateComment);
			updateStmt.setString(1, countryName);
			updateStmt.setInt(2, countries.getCountryId());
			updateStmt.executeUpdate();

			// Update the Countries param before returning to the caller.
			countries.setCountryName(countryName);
			return countries;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
}
