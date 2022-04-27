/* by Yiyang Bu */

package youtubetrends.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import youtubetrends.model.Categories;
import youtubetrends.model.Users;


/**
 * Data access object (DAO) class to interact with the underlying Users table in your MySQL
 * instance. This is used to store {@link Users} into your MySQL instance and retrieve {@link Users}
 * from MySQL instance.
 */
public class CategoriesDao {

  // Single pattern: instantiation is limited to one object.
  private static CategoriesDao instance = null;
  protected ConnectionManager connectionManager;

  protected CategoriesDao() {
    connectionManager = new ConnectionManager();
  }

  public static CategoriesDao getInstance() {
    if (instance == null) {
      instance = new CategoriesDao();
    }
    return instance;
  }

  /**
   * Save the {@link Categories} instance by storing it in your MySQL instance. This runs a INSERT
   * statement.
   *
   * @throws SQLException
   */
  public Categories create(Categories category) throws SQLException {
    String insertUser = "INSERT INTO Categories(CategoryId, Title, Assignable) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertUser);
      insertStmt.setString(1, category.getCategoryId().toString());
      insertStmt.setString(2, category.getTitle());
      insertStmt.setBoolean(3, category.getAssignable());

      insertStmt.executeUpdate();

      return category;
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
   * Delete the {@link Categories} instance. This runs a DELETE statement.
   *
   * @throws SQLException
   */
  public Categories delete(Categories category) throws SQLException {
    String deleteCategory = "DELETE FROM Categories WHERE CategoryId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCategory);
      deleteStmt.setString(1, category.getCategoryId().toString());
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
   * Get the {@link Categories} record by fetching it from your MySQL instance. This runs a SELECT
   * statement and returns a single {@link Categories} instance.
   *
   * @throws SQLException
   */
  public Categories getCategoryByCategoryId(Integer CategoryId) throws SQLException {
    String selectCategory = "SELECT CategoryId, Title, Assignable FROM Categories WHERE CategoryId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCategory);
      selectStmt.setString(1, CategoryId.toString());

      results = selectStmt.executeQuery();

      if (results.next()) {
        String title = results.getString("Title");
        Boolean Assignable = Boolean.parseBoolean(results.getString("Assignable"));
        Categories category = new Categories(CategoryId, title, Assignable);
        return category;
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
	/**
	 * Update the content of the Categories instance.
	 * This runs a UPDATE statement.
	 */
	public Categories updateContent(Categories categories, String title, boolean assignable) throws SQLException {
		String updateComment = "UPDATE Categories SET Title=?,Assignable=? WHERE CategoryId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateComment);
			updateStmt.setString(1, title);
			updateStmt.setBoolean(2, assignable);
			updateStmt.setInt(3, categories.getCategoryId());
			updateStmt.executeUpdate();

			// Update the categories param before returning to the caller.
			categories.setTitle(title);
			categories.setAssignable(assignable);
			return categories;
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