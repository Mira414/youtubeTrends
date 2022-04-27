package youtubetrends.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import youtubetrends.dal.VideosDao;
import youtubetrends.model.Videos;


@WebServlet("/videoupdate")
public class VideoUpdate extends HttpServlet {

  protected VideosDao videoDao;

  @Override
  public void init() throws ServletException {
    videoDao = VideosDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve user and validate.
    String userName = req.getParameter("title");

    try {
      Videos blogUser = videoDao.getVideosByTitle(userName).get(0);
      if (blogUser == null) {
        messages.put("success", "UserName does not exist.");
      }
      req.setAttribute("videos", blogUser);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    req.getRequestDispatcher("/VideoUpdate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Retrieve user and validate.
    String title = req.getParameter("title");
    try {
      Videos videos = videoDao.getVideosByTitle(title).get(0);
      String newLastName = req.getParameter("newTitle");
      videos = videoDao.updateTitle(videos, newLastName);
      messages.put("success", "Successfully updated " + newLastName);
      req.setAttribute("videos", videos);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    req.getRequestDispatcher("/VideoUpdate.jsp").forward(req, resp);
  }
}
