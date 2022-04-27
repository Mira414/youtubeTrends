package youtubetrends.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import youtubetrends.dal.CountriesDao;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import youtubetrends.dal.CategoriesDao;
import youtubetrends.model.Countries;

@WebServlet("/findCountries")
public class FindCountries extends HttpServlet {

	protected CountriesDao CountriesDao;

	@Override
	public void init() throws ServletException {
		CountriesDao = CountriesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		Countries countries = null;

		// Retrieve and validate name.
		// CountryName is retrieved from the URL query string.
		String CountryName = req.getParameter("CountryId");
		if (CountryName == null || CountryName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			// Retrieve Countries, and store as a message.
			try {
				countries = CountriesDao.getCountryByCountryId(Integer.parseInt(CountryName));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + CountryName);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindCountries.jsp.
			messages.put("previousCountryName", CountryName);
		}
		req.setAttribute("Countries", countries);

		req.getRequestDispatcher("/FindCountries.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		Countries Countries = null;

		// Retrieve and validate name.
		// CountryName is retrieved from the form POST submission. By default, it
		// is populated by the URL query string (in FindCountries.jsp).
		String CountryName = req.getParameter("CountryId");
		if (CountryName == null || CountryName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			// Retrieve Countries, and store as a message.
			try {
				Countries = CountriesDao.getCountryByCountryId(Integer.parseInt(CountryName));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + CountryName);
		}
		req.setAttribute("countries", Countries);

		req.getRequestDispatcher("/FindCountries.jsp").forward(req, resp);
	}
}
