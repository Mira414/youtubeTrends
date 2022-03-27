package YouTubeTrends.model;

import java.util.Date;
public class Comments {


	protected int commentId;
	protected String content;
	protected Date created;
	protected Users user;
	/**
	 * @param commentId
	 * @param content
	 * @param created
	 * @param user
	 */
	public Comments(int commentId, String content, Date created, Users user) {
		this.commentId = commentId;
		this.content = content;
		this.created = created;
		this.user = user;
	}


	/**
	 * @return the commentId
	 */
	public int getCommentId() {
		return commentId;
	}


	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}


	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}


	/**
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}


}
