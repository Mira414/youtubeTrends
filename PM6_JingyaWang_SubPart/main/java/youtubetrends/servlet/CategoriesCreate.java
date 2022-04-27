package youtubetrends.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import youtubetrends.dal.CategoriesDao;
import youtubetrends.model.Categories;


@WebServlet("/categoriesCreate")
public class CategoriesCreate extends HttpServlet {
	
	protected CategoriesDao categoriessDao;
	
	@Override
	public void init() throws ServletException {
		categoriessDao = CategoriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CategoriesCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing categories.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String title = req.getParameter("title");
        Boolean assignable = Boolean.parseBoolean(req.getParameter("assignable"));
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Invalid title");
        } else {
	        try {
	        	// Exercise: parse the input for StatusLevel.
				Categories categories = new Categories(title,assignable);
	        	categories = categoriessDao.create(categories);
	        	messages.put("success", "Successfully created " + title);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/categoriesCreate.jsp").forward(req, resp);
    }
}
