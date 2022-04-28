package YouTubeTrends.model;

import java.util.Date;

public class Administrators extends Persons{

	protected Date lastLogin;

	/**
	 * @param userId
	 * @param firstName
	 * @param lastName
	 */
	public Administrators(int userId, String firstName, String lastName, Date lastLogin) {
		super(userId, firstName, lastName);
		this.lastLogin = lastLogin;
	}

	public Administrators(int userId, Date lastLogin) {
		super(userId);
		this.lastLogin = lastLogin;
	}


	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	

}
