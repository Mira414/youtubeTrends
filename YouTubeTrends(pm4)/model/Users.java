package YouTubeTrends.model;

import java.util.Date;

public class Users extends Persons {

	protected Date DoB;

	public Users(int UserId, String FirstName, String LastName,Date DoB) {
		super(UserId,FirstName,LastName);
		this.DoB = DoB;	
	}
	
	public Users(int UserId,Date DoB) {
		super(UserId);
		this.DoB = DoB;	
	}
	
	
	/**
	 * @return the doB
	 */
	public Date getDoB() {
		return DoB;
	}

	/**
	 * @param doB the doB to set
	 */
	public void setDoB(Date doB) {
		DoB = doB;
	}


}
