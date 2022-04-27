package youtubetrends.model;

import java.util.Date;

public class VideoTrends {

  private Integer videoTrendId;
  private Videos videoId;
  private Date trendingDate;
  private String description;

  public VideoTrends(Integer videoTrendId) {
    super();
    this.videoTrendId = videoTrendId;
  }

  public VideoTrends(Videos videoId, Date trendingDate, String description) {
    super();
    this.videoId = videoId;
    this.trendingDate = trendingDate;
    this.description = description;
  }

  public VideoTrends(Integer videoTrendId, Videos videoId, Date trendingDate, String description) {
    super();
    this.videoTrendId = videoTrendId;
    this.videoId = videoId;
    this.trendingDate = trendingDate;
    this.description = description;
  }

  public Integer getVideoTrendId() {
    return videoTrendId;
  }

  public void setVideoTrendId(Integer videoTrendId) {
    this.videoTrendId = videoTrendId;
  }

  public Videos getVideoId() {
    return videoId;
  }

  public void setVideoId(Videos videoId) {
    this.videoId = videoId;
  }

  public Date getTrendingDate() {
    return trendingDate;
  }

  public void setTrendingDate(Date trendingDate) {
    this.trendingDate = trendingDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


}