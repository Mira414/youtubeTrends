package youtubetrends.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import youtubetrends.model.Categories;
import youtubetrends.model.Countries;
import youtubetrends.model.Users;
import youtubetrends.model.Videos;


public class VideosDao {

  private static VideosDao instance = null;
  protected ConnectionManager connectionManager;

  protected VideosDao() {
    connectionManager = new ConnectionManager();
  }

  public static VideosDao getInstance() {
    if (instance == null) {
      instance = new VideosDao();
    }
    return instance;
  }

  public Videos create(Videos videos) throws SQLException {
    String insertReshare =
        "INSERT INTO Videos (TrendingDate,Title,PublishTime,Tags,Views,CommentCount,ThumbnailLink,Dislikes,CommentsDisabled,RatingsDisabled,VideoErrorOrRemoved,Description,CategoryId,CountryId,UserId)\n"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReshare, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, videos.getTrendingDate());
      insertStmt.setString(2, videos.getTitle());
      insertStmt.setTimestamp(3, videos.getPublishTime());
      insertStmt.setString(4, videos.getTags());
      insertStmt.setLong(5, videos.getViews());
      insertStmt.setLong(6, videos.getCommentCount());
      insertStmt.setString(7, videos.getThumbnailLink());
      insertStmt.setLong(8, videos.getDislikes());
      insertStmt.setBoolean(9, videos.isCommentsDisabled());
      insertStmt.setBoolean(10, videos.isRatingsDisabled());
      insertStmt.setBoolean(11, videos.isVideoErrorOrRemoved());
      insertStmt.setString(12, videos.getDescription());
      insertStmt.setInt(13, videos.getCategoryId().getCategoryId());
      insertStmt.setInt(14, videos.getCountryId().getCountryId());
      insertStmt.setInt(15, videos.getUserId().getUserId());
      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int reshareId = -1;
      if (resultKey.next()) {
        reshareId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      videos.setVideoId(reshareId);
      return videos;
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
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }

  public Videos delete(Videos videos) throws SQLException {
    String deleteReshare = "DELETE FROM Videos WHERE videoId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReshare);
      deleteStmt.setInt(1, videos.getVideoId());
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

  public Videos deleteTitle(String title) throws SQLException {
    String deleteReshare = "DELETE FROM Videos WHERE title=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReshare);
      deleteStmt.setString(1, title);
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

  public Videos getVideosById(int videoId) throws SQLException {
    String selectReshare = "select VideoId, TrendingDate, Title, PublishTime, Tags, Views, CommentCount, ThumbnailLink, Dislikes, CommentsDisabled, RatingsDisabled, VideoErrorOrRemoved, Description, CategoryId, CountryId, UserId from videos where videoId = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReshare);
      selectStmt.setInt(1, videoId);
      results = selectStmt.executeQuery();
      CountriesDao countriesDao = CountriesDao.getInstance();
      CategoriesDao categoriesDao = CategoriesDao.getInstance();
      UsersDao usersDao = UsersDao.getInstance();
      if (results.next()) {
        int resultReshareId = results.getInt("VideoId");
        String trendingDate = results.getString("TrendingDate");
        String title = results.getString("Title");
        Timestamp publishTime = results.getTimestamp("PublishTime");
        String tags = results.getString("Tags");
        long views = results.getLong("Views");
        long commentCount = results.getLong("CommentCount");
        String thumbnailLink = results.getString("ThumbnailLink");
        long dislikes = results.getInt("Dislikes");
        boolean commentsDisabled = results.getBoolean("CommentsDisabled");
        boolean ratingsDisabled = results.getBoolean("RatingsDisabled");
        boolean videoErrorOrRemoved = results.getBoolean("VideoErrorOrRemoved");
        String description = results.getString("Description");
        Categories categories = categoriesDao.getCategoryByCategoryId(results.getInt("CategoryId"));
        Countries countries = countriesDao.getCountryByCountryId(results.getInt("CountryId"));
        Users user = usersDao.getUserByUserId(results.getInt("UserId"));
        Videos reshare = new Videos(title, trendingDate, publishTime, tags, views, commentCount,
            thumbnailLink, dislikes, commentsDisabled, ratingsDisabled, videoErrorOrRemoved,
            description, categories, user, countries);
        return reshare;
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

  public List<Videos> getVideosByTitle(String title) throws SQLException {
    List<Videos> persons = new ArrayList<>();
    String selectPersons = "select VideoId, TrendingDate, Title, PublishTime, Tags, Views, CommentCount, ThumbnailLink, Dislikes, CommentsDisabled, RatingsDisabled, VideoErrorOrRemoved, Description, CategoryId, CountryId, UserId from videos where title = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    CountriesDao countriesDao = CountriesDao.getInstance();
    CategoriesDao categoriesDao = CategoriesDao.getInstance();
    UsersDao usersDao = UsersDao.getInstance();
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectPersons);
      selectStmt.setString(1, title);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int resultReshareId = results.getInt("VideoId");
        String trendingDate = results.getString("TrendingDate");
        String title2 = results.getString("Title");
        Timestamp publishTime = results.getTimestamp("PublishTime");
        String tags = results.getString("Tags");
        long views = results.getLong("Views");
        long commentCount = results.getLong("CommentCount");
        String thumbnailLink = results.getString("ThumbnailLink");
        long dislikes = results.getInt("Dislikes");
        boolean commentsDisabled = results.getBoolean("CommentsDisabled");
        boolean ratingsDisabled = results.getBoolean("RatingsDisabled");
        boolean videoErrorOrRemoved = results.getBoolean("VideoErrorOrRemoved");
        String description = results.getString("Description");
        Categories categories = categoriesDao.getCategoryByCategoryId(results.getInt("CategoryId"));
        Countries countries = countriesDao.getCountryByCountryId(results.getInt("CountryId"));
        Users user = usersDao.getUserByUserId(results.getInt("UserId"));
        Videos reshare = new Videos(resultReshareId, title2, trendingDate, publishTime, tags, views,
            commentCount,
            thumbnailLink, dislikes, commentsDisabled, ratingsDisabled, videoErrorOrRemoved,
            description, categories, user, countries);
        persons.add(reshare);
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
    return persons;
  }
  public List<Videos> gethottestVideosByTime(String time) throws SQLException {
    List<Videos> persons = new ArrayList<>();
    String selectPersons = "select VideoId, TrendingDate, Title, PublishTime, Tags, Views, CommentCount, ThumbnailLink, Dislikes, CommentsDisabled, RatingsDisabled, VideoErrorOrRemoved, Description from videos where videos.PublishTime = ? order by videos.Views desc limit 10;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectPersons);
      selectStmt.setString(1, time);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int resultReshareId = results.getInt("VideoId");
        String trendingDate = results.getString("TrendingDate");
        String title2 = results.getString("Title");
        Timestamp publishTime = results.getTimestamp("PublishTime");
        String tags = results.getString("Tags");
        long views = results.getLong("Views");
        long commentCount = results.getLong("CommentCount");
        String thumbnailLink = results.getString("ThumbnailLink");
        long dislikes = results.getInt("Dislikes");
        boolean commentsDisabled = results.getBoolean("CommentsDisabled");
        boolean ratingsDisabled = results.getBoolean("RatingsDisabled");
        boolean videoErrorOrRemoved = results.getBoolean("VideoErrorOrRemoved");
        String description = results.getString("Description");
        Videos reshare = new Videos(resultReshareId, title2, trendingDate, publishTime, tags, views,
            commentCount,
            thumbnailLink, dislikes, commentsDisabled, ratingsDisabled, videoErrorOrRemoved,
            description);
        persons.add(reshare);
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
    return persons;
  }
  public Videos updateTitle(Videos videos, String title) throws SQLException {
    String updateCreditCard = "UPDATE videos SET Title = ? WHERE VideoId = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateCreditCard);
      updateStmt.setString(1, title);
      updateStmt.setInt(2, videos.getVideoId());
      updateStmt.executeUpdate();
      videos.setTitle(title);
      return videos;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  //TODO: From line 309 to 360 are what I added, If any error happens, feel free to adjust them
  public List<Videos> getVideosByTopViews(int topn) throws SQLException {
	    List<Videos> videos = new ArrayList<>();
	    String selectVideos = "SELECT VideoId, TrendingDate, Title, PublishTime, Tags, "
	    		+ "Views, CommentCount, ThumbnailLink, Dislikes, CommentsDisabled, "
	    		+ "RatingsDisabled, VideoErrorOrRemoved, Description "
	    		+ "FROM Videos \n"
	    		+ "ORDER BY Views\n"
	    		+ "LIMIT ?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectVideos);
	      selectStmt.setInt(1, topn);
	      results = selectStmt.executeQuery();
	      while (results.next()) {
	        int videoId = results.getInt("VideoId");
	        String trendingDate = results.getString("TrendingDate");
	        String title = results.getString("Title");
	        Timestamp publishTime = results.getTimestamp("PublishTime");
	        String tags = results.getString("Tags");
	        long views = results.getLong("Views");
	        long commentCount = results.getLong("CommentCount");
	        String thumbnailLink = results.getString("ThumbnailLink");
	        long dislikes = results.getInt("Dislikes");
	        boolean commentsDisabled = results.getBoolean("CommentsDisabled");
	        boolean ratingsDisabled = results.getBoolean("RatingsDisabled");
	        boolean videoErrorOrRemoved = results.getBoolean("VideoErrorOrRemoved");
	        String description = results.getString("Description");
	        Videos video = new Videos(videoId, title, trendingDate, publishTime, tags, views,
	            commentCount, thumbnailLink, dislikes, commentsDisabled, ratingsDisabled, 
	            videoErrorOrRemoved, description);
	        videos.add(video);
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
	    return videos;
	  }
}
