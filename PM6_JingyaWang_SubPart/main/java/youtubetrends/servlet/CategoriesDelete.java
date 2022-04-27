package youtubetrends.servlet;

import youtubetrends.dal.*;
import youtubetrends.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/categoriesDelete")
public class CategoriesDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Categories");        
        req.getRequestDispatcher("/CategoriesDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));
        if (categoryId == null ) {
            messages.put("title", "Invalid categoryId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Categories.
	        Categories category = new Categories(categoryId);
	        try {
	        	Categories categories = categoriessDao.delete(category);
	        	// Update the message.
		        if (categories == null) {
		            messages.put("title", "Successfully deleted " + categoryId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + categoryId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CategoriesDelete.jsp").forward(req, resp);
    }
}
