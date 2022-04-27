package youtubetrends.model;

import java.util.Date;

public class LikeDetail {

	protected int likesId;
	protected int videoId;
	protected String videoTitle;
	protected String userName;
	protected Date created;

	/**
	 * without likesId
	 * @param videoId
	 * @param videoTitle
	 * @param userName
	 * @param created
	 */
	public LikeDetail(int videoId, String videoTitle, String userName, Date created) {
		super();
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.userName = userName;
		this.created = created;
	}

	/**
	 * all fields
	 * @param likesId
	 * @param videoId
	 * @param videoTitle
	 * @param userName
	 * @param created
	 */
	public LikeDetail(int likesId, int videoId, String videoTitle, String userName, Date created) {
		super();
		this.likesId = likesId;
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.userName = userName;
		this.created = created;
	}

	public int getLikesId() {
		return likesId;
	}

	public void setLikesId(int likesId) {
		this.likesId = likesId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	
}