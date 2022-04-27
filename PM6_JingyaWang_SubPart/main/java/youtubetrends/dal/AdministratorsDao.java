package youtubetrends.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import youtubetrends.model.Administrators;
import youtubetrends.model.Persons;


public class AdministratorsDao extends PersonsDao {

  private static AdministratorsDao instance = null;

  protected AdministratorsDao() {
    super();
  }

  public static AdministratorsDao getInstance() {
    if (instance == null) {
      instance = new AdministratorsDao();
    }
    return instance;
  }

  public Administrators create(Administrators administrators) throws SQLException {
    create(new Persons(administrators.getUserId(), administrators.getFirstName(),
        administrators.getLastName()));
    String insertAdministrators = "INSERT INTO Administrators(UserId, LastLogin) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertAdministrators);

      insertStmt.setInt(1, administrators.getUserId());
      insertStmt.setTimestamp(2, new Timestamp(administrators.getLastLogin().getTime()));
      insertStmt.executeUpdate();
      return administrators;
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

  public Administrators updateLastName(Administrators administrators, String newLastName)
      throws SQLException {
    super.updateLastName(administrators, newLastName);
    return administrators;
  }

  public Administrators delete(Administrators administrator) throws SQLException {
    String deleteAdministrator = "DELETE FROM Administrators WHERE UserId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteAdministrator);
      deleteStmt.setInt(1, administrator.getUserId());
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException(
            "No records available to delete for UserId=" + administrator.getUserId());
      }

      // Then also delete from the superclass.
      // Notes:
      // 1. Due to the fk constraint (ON DELETE CASCADE), we could simply call
      //    super.delete() without even needing to delete from Administrators first.
      // 2. BlogPosts has a fk constraint on Users with the reference option
      //    ON DELETE SET NULL. If the BlogPosts fk reference option was instead
      //    ON DELETE RESTRICT, then the caller would need to delete the referencing
      //    BlogPosts before this User can be deleted.
      //    Example to delete the referencing BlogPosts:
      //    List<BlogPosts> posts = BlogPostsDao.getBlogPostsForUser(User.getUserId());
      //    for(BlogPosts p : posts) BlogPostsDao.delete(p);
      super.delete(administrator);

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

  public List<Administrators> getAdministratorsFromFirstName(String firstName) throws SQLException {
    List<Administrators> administratorsList = new ArrayList<>();
    String selectAdministrators =
        "SELECT Administrators.UserId AS UserId, FirstName, LastName, LastLogin "
            + "FROM Administrators INNER JOIN Persons "
            + "  ON Administrators.UserId = Persons.UserId " + "WHERE Persons.FirstName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAdministrators);
      selectStmt.setString(1, firstName);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int UserId = results.getInt("UserId");
        String resultFirstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        Date LastLogin = new Date(results.getTimestamp("LastLogin").getTime());
        Administrators administrators = new Administrators(UserId, resultFirstName, lastName,
            LastLogin);
        administratorsList.add(administrators);
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
    return administratorsList;
  }

  public Administrators getAdministratorsByUserId(int UserId) throws SQLException {
    String selectAdministrator =
        "SELECT Administrators.UserId AS UserId, FirstName, LastName, LastLogin "
            + "FROM Administrators INNER JOIN Persons "
            + "  ON Administrators.UserId = Persons.UserId " + "WHERE Administrators.UserId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAdministrator);
      selectStmt.setInt(1, UserId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        int resultUserId = results.getInt("UserId");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        Date LastLogin = new Date(results.getTimestamp("LastLogin").getTime());
        Administrators administrators = new Administrators(resultUserId, firstName, lastName,
            LastLogin);
        return administrators;
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


}
