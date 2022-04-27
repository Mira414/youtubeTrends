package youtubetrends.servlet;



import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import youtubetrends.dal.CategoriesDao;
import youtubetrends.model.Categories;


@WebServlet("/categoriesUpdate")
public class CategoriesUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String categoryId = req.getParameter("categoryId");
        if (categoryId == null) {
            messages.put("success", "Please enter a valid categoryId.");
        } else {
        	try {
        		Categories category = categoriesDao.getCategoryByCategoryId(Integer.parseInt(categoryId));
        		if(category == null) {
        			messages.put("success", "Category title does not exist.");
        		}
        		req.setAttribute("category", category);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/categoriesUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String categoryId = req.getParameter("categoryId");
        if (categoryId == null) {
            messages.put("success", "Please enter a valid categoryId.");
        } else {
        	try {
        		Categories category = categoriesDao.getCategoryByCategoryId(Integer.parseInt(categoryId));
        		if(category == null) {
        			messages.put("success", "categoryId does not exist. No update to perform.");
        		} else {
        			String newTitle = req.getParameter("newTitle");
        			String newAssignable = req.getParameter("newAssignable");
        			if (newTitle == null || newTitle.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid newTitle.");
        	        } else {
						category = categoriesDao.updateContent(category, newTitle,Boolean.parseBoolean(newAssignable));
        	        	messages.put("success", "Successfully updated " + category.getTitle());
        	        }
        		}
        		req.setAttribute("category", category);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/categoriesUpdate.jsp").forward(req, resp);
    }
}
