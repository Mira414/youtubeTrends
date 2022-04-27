package youtubetrends.model;

import java.sql.Timestamp;

public class Videos {

  protected int videoId;
  protected String title;
  protected String trendingDate;
  protected Timestamp publishTime;
  protected String tags;
  protected long views;
  protected long commentCount;
  protected String thumbnailLink;
  protected long dislikes;
  protected boolean commentsDisabled;
  protected boolean ratingsDisabled;
  protected boolean videoErrorOrRemoved;
  protected String description;
  protected Categories categoryId;
  protected Users userId;
  protected Countries countryId;

  public Videos(String title, String trendingDate, Timestamp publishTime, String tags, long views,
      long commentCount, String thumbnailLink, long dislikes, boolean commentsDisabled,
      boolean ratingsDisabled, boolean videoErrorOrRemoved, String description,
      Categories categoryId,
      Users userId, Countries countryId) {
    this.title = title;
    this.trendingDate = trendingDate;
    this.publishTime = publishTime;
    this.tags = tags;
    this.views = views;
    this.commentCount = commentCount;
    this.thumbnailLink = thumbnailLink;
    this.dislikes = dislikes;
    this.commentsDisabled = commentsDisabled;
    this.ratingsDisabled = ratingsDisabled;
    this.videoErrorOrRemoved = videoErrorOrRemoved;
    this.description = description;
    this.categoryId = categoryId;
    this.userId = userId;
    this.countryId = countryId;
  }

  public Videos(int videoId, String title, String trendingDate, Timestamp publishTime,
      String tags, long views, long commentCount, String thumbnailLink, long dislikes,
      boolean commentsDisabled, boolean ratingsDisabled, boolean videoErrorOrRemoved,
      String description) {
    this.videoId = videoId;
    this.title = title;
    this.trendingDate = trendingDate;
    this.publishTime = publishTime;
    this.tags = tags;
    this.views = views;
    this.commentCount = commentCount;
    this.thumbnailLink = thumbnailLink;
    this.dislikes = dislikes;
    this.commentsDisabled = commentsDisabled;
    this.ratingsDisabled = ratingsDisabled;
    this.videoErrorOrRemoved = videoErrorOrRemoved;
    this.description = description;
  }

  public Videos(int videoId, String title, String trendingDate, Timestamp publishTime,
      String tags, long views, long commentCount, String thumbnailLink, long dislikes,
      boolean commentsDisabled, boolean ratingsDisabled, boolean videoErrorOrRemoved,
      String description, Categories categoryId, Users userId, Countries countryId) {
    this.videoId = videoId;
    this.title = title;
    this.trendingDate = trendingDate;
    this.publishTime = publishTime;
    this.tags = tags;
    this.views = views;
    this.commentCount = commentCount;
    this.thumbnailLink = thumbnailLink;
    this.dislikes = dislikes;
    this.commentsDisabled = commentsDisabled;
    this.ratingsDisabled = ratingsDisabled;
    this.videoErrorOrRemoved = videoErrorOrRemoved;
    this.description = description;
    this.categoryId = categoryId;
    this.userId = userId;
    this.countryId = countryId;
  }

  public int getVideoId() {
    return videoId;
  }

  public void setVideoId(int videoId) {
    this.videoId = videoId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTrendingDate() {
    return trendingDate;
  }

  public void setTrendingDate(String trendingDate) {
    this.trendingDate = trendingDate;
  }

  public Timestamp getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(Timestamp publishTime) {
    this.publishTime = publishTime;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public long getViews() {
    return views;
  }

  public void setViews(long views) {
    this.views = views;
  }

  public long getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(long commentCount) {
    this.commentCount = commentCount;
  }

  public String getThumbnailLink() {
    return thumbnailLink;
  }

  public void setThumbnailLink(String thumbnailLink) {
    this.thumbnailLink = thumbnailLink;
  }

  public long getDislikes() {
    return dislikes;
  }

  public void setDislikes(long dislikes) {
    this.dislikes = dislikes;
  }

  public boolean isCommentsDisabled() {
    return commentsDisabled;
  }

  public void setCommentsDisabled(boolean commentsDisabled) {
    this.commentsDisabled = commentsDisabled;
  }

  public boolean isRatingsDisabled() {
    return ratingsDisabled;
  }

  public void setRatingsDisabled(boolean ratingsDisabled) {
    this.ratingsDisabled = ratingsDisabled;
  }

  public boolean isVideoErrorOrRemoved() {
    return videoErrorOrRemoved;
  }

  public void setVideoErrorOrRemoved(boolean videoErrorOrRemoved) {
    this.videoErrorOrRemoved = videoErrorOrRemoved;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Categories getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Categories categoryId) {
    this.categoryId = categoryId;
  }

  public Users getUserId() {
    return userId;
  }

  public void setUserId(Users userId) {
    this.userId = userId;
  }

  public Countries getCountryId() {
    return countryId;
  }

  public void setCountryId(Countries countryId) {
    this.countryId = countryId;
  }
}
