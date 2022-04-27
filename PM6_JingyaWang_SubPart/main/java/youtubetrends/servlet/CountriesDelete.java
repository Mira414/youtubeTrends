package youtubetrends.servlet;


import youtubetrends.dal.CountriesDao;
import youtubetrends.model.Countries;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/countriesDelete")
public class CountriesDelete extends HttpServlet {
	
	protected CountriesDao countriesDao;
	
	@Override
	public void init() throws ServletException {
		countriesDao = CountriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Countries");
        req.getRequestDispatcher("/CountriesDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String countriesId = req.getParameter("CountryId");
        if (countriesId == null || countriesId.trim().isEmpty()) {
            messages.put("title", "Invalid countriesId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Countries countries = new Countries(Integer.parseInt(countriesId));

	        try {
	        	countries = countriesDao.delete(countries);
	        	// Update the message.
		        if (countries == null) {
		            messages.put("title", "Successfully deleted " + countriesId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + countriesId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CountriesDelete.jsp").forward(req, resp);
    }
}
