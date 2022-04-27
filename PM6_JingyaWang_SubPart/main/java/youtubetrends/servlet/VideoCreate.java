package youtubetrends.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import youtubetrends.dal.CategoriesDao;
import youtubetrends.dal.CountriesDao;
import youtubetrends.dal.UsersDao;
import youtubetrends.dal.VideosDao;
import youtubetrends.model.Categories;
import youtubetrends.model.Countries;
import youtubetrends.model.Users;
import youtubetrends.model.Videos;


@WebServlet("/videocreate")
public class VideoCreate extends HttpServlet {

  protected VideosDao videosDao;

  @Override
  public void init() throws ServletException {
    videosDao = VideosDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    //Just render the JSP.
    req.getRequestDispatcher("/VideoCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    CountriesDao countriesDao = CountriesDao.getInstance();
    CategoriesDao categoriesDao = CategoriesDao.getInstance();
    UsersDao usersDao = UsersDao.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String stringTrendingDate = req.getParameter("TrendingDate");
    String title = req.getParameter("Title");
    String stringPublishTime = req.getParameter("PublishTime");
    String tags = req.getParameter("Tags");
    long views = Long.parseLong(req.getParameter("Views"));
    int commentCount = Integer.parseInt(req.getParameter("CommentCount"));
    String thumbnailLink = req.getParameter("ThumbnailLink");
    int dislikes = Integer.parseInt(req.getParameter("Dislikes"));
    boolean commentsDisabled = Boolean.parseBoolean(req.getParameter("CommentsDisabled"));
    boolean ratingsDisabled = Boolean.parseBoolean(req.getParameter("RatingsDisabled"));
    boolean videoErrorOrRemoved = Boolean.parseBoolean(req.getParameter("VideoErrorOrRemoved"));
    String description = req.getParameter("Description");
    Countries countries = null;
    try {
      countries = countriesDao.getCountryByCountryId(
          Integer.parseInt(req.getParameter("CountryId")));
    } catch (NumberFormatException | SQLException e3) {
      // TODO Auto-generated catch block
      e3.printStackTrace();
    }
    Categories categories = null;
    try {
      categories = categoriesDao.getCategoryByCategoryId(
          Integer.parseInt(req.getParameter("CategoryId")));
    } catch (NumberFormatException | SQLException e2) {
      // TODO Auto-generated catch block
      e2.printStackTrace();
    }
    Users user = null;
    try {
      user = usersDao.getUserByUserId(Integer.parseInt(req.getParameter("UserId")));
    } catch (NumberFormatException | SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // dob must be in the format yyyy-mm-dd.
    Date trendingDate = new Date();
    Date publishTime = new Date();
    try {
      trendingDate = dateFormat.parse(stringTrendingDate);
      publishTime = dateFormat.parse(stringPublishTime);
    } catch (ParseException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    try {
      // Exercise: parse the input for StatusLevel.
      Videos reshare = new Videos(title, stringTrendingDate, new Timestamp(publishTime.getTime()),
          tags,
          views, commentCount, thumbnailLink, dislikes, commentsDisabled, ratingsDisabled,
          videoErrorOrRemoved, description, categories, user, countries);
      reshare = videosDao.create(reshare);
      messages.put("success", "Successfully created " + title);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    req.getRequestDispatcher("/VideoCreate.jsp").forward(req, resp);
  }
}
