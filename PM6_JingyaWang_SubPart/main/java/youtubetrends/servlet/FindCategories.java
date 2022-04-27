package youtubetrends.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import youtubetrends.dal.CategoriesDao;
import youtubetrends.model.Categories;



@WebServlet("/findCategories")
public class FindCategories extends HttpServlet {
	
	protected CategoriesDao categoriesDao;
	
	@Override
	public void init() throws ServletException {
        categoriesDao = CategoriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Categories categories = null;
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String title = req.getParameter("categoryId");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	// Retrieve Categories, and store as a message.
        	try {
            	categories = categoriesDao.getCategoryByCategoryId(Integer.parseInt(title));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindCategories.jsp.
        	messages.put("previousTitle", title);
        }
        req.setAttribute("Categories", categories);
        
        req.getRequestDispatcher("/FindCategories.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Categories categories = null;
        
        // Retrieve and validate name.
        // title is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindCategories.jsp).
        String title = req.getParameter("categoryId");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	// Retrieve Categories, and store as a message.
        	try {
        		categories = categoriesDao.getCategoryByCategoryId(Integer.parseInt(title));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + title);
        }
        req.setAttribute("categories", categories);
        
        req.getRequestDispatcher("/FindCategories.jsp").forward(req, resp);
    }
}
