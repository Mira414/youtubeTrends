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

import youtubetrends.dal.CountriesDao;
import youtubetrends.model.Countries;


@WebServlet("/countriesUpdate")
public class CountriesUpdate extends HttpServlet {
	
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

        // Retrieve Countries and validate.
        String CountryName = req.getParameter("CountryId");
        if (CountryName == null || CountryName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CountryName.");
        } else {
        	try {
        		Countries countries = countriesDao.getCountryByCountryId(Integer.parseInt(CountryName));
        		if(countries == null) {
        			messages.put("success", "CountryName does not exist.");
        		}
        		req.setAttribute("countries", countries);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CountriesUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve Countries and validate.
        String CountryName = req.getParameter("CountryId");
        if (CountryName == null || CountryName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CountryName.");
        } else {
        	try {
        		Countries countries = countriesDao.getCountryByCountryId(Integer.parseInt(CountryName));
        		if(countries == null) {
        			messages.put("success", "CountryName does not exist. No update to perform.");
        		} else {
        			String NewCountryName = req.getParameter("NewCountryName");
        			if (NewCountryName == null || NewCountryName.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid NewCountryName.");
        	        } else {
        	        	countries = countriesDao.updateContent(countries, NewCountryName);
        	        	messages.put("success", "Successfully updated " + CountryName);
        	        }
        		}
        		req.setAttribute("countries", countries);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CountriesUpdate.jsp").forward(req, resp);
    }
}
