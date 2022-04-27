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

import youtubetrends.dal.CountriesDao;
import youtubetrends.model.Countries;


@WebServlet("/countriesCreate")
public class CountriesCreate extends HttpServlet {
	
	protected CountriesDao CountriessDao;
	
	@Override
	public void init() throws ServletException {
		CountriessDao = CountriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CountrisCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing countries.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String countryName = req.getParameter("CountryName");
        if (countryName == null || countryName.trim().isEmpty()) {
            messages.put("success", "Invalid CountryName");
        } else {
	        try {
	        	// Exercise: parse the input for StatusLevel.
				Countries countries = new Countries(countryName);
	        	countries = CountriessDao.create(countries);
	        	messages.put("success", "Successfully created " + countryName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CountriesCreate.jsp").forward(req, resp);
    }
}
