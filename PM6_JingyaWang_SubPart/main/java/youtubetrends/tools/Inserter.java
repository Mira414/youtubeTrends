package youtubetrends.tools;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import youtubetrends.dal.AdministratorsDao;
import youtubetrends.dal.CategoriesDao;
import youtubetrends.dal.CommentsDao;
import youtubetrends.dal.CountriesDao;
import youtubetrends.dal.LikesDao;
import youtubetrends.dal.PersonsDao;
import youtubetrends.dal.SearchHistoryDao;
import youtubetrends.dal.UsersDao;
import youtubetrends.dal.VideoTrendsDao;
import youtubetrends.dal.VideosDao;
import youtubetrends.model.Administrators;
import youtubetrends.model.Categories;
import youtubetrends.model.Comments;
import youtubetrends.model.Countries;
import youtubetrends.model.Likes;
import youtubetrends.model.Persons;
import youtubetrends.model.SearchHistory;
import youtubetrends.model.Users;
import youtubetrends.model.VideoTrends;
import youtubetrends.model.Videos;


/**
 * main() runner, used for the app demo.
 * <p>
 * Instructions: 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H. 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {


  public static void main(String[] args) throws SQLException {
    Date date = new Date(20211005);
    //create function exercise
    UsersDao usersDao = UsersDao.getInstance();
    Users user1 = new Users(1, "user", "user", date);
    user1 = usersDao.create(user1);

    PersonsDao personsDao = PersonsDao.getInstance();
    Persons person1 = new Persons(1, "1", "2");
    person1 = personsDao.create(person1);

    AdministratorsDao administratorsDao = AdministratorsDao.getInstance();
    Administrators administrators = new Administrators(1, "ad", "ad", new Date(20201010));
    administrators = administratorsDao.create(administrators);

    CommentsDao commentsDao = CommentsDao.getInstance();
    Comments comments = new Comments(1, "cm", new Date(20201205), user1);
    comments = commentsDao.create(comments);

    CountriesDao countriesDao = CountriesDao.getInstance();
    Countries countries = new Countries(1, "US");
    countries = countriesDao.create(countries);
    CategoriesDao categoriesDao = CategoriesDao.getInstance();
    Categories categories = new Categories(1, "title", true);
    categories = categoriesDao.create(categories);
    Countries countries1 = countriesDao.getCountryByCountryId(1);
    System.out.format("Reading countries: countryId:%s countryName:%s \n",
        countries1.getCountryId(), countries1.getCountryName());
    Categories categories1 = categoriesDao.getCategoryByCategoryId(1);
    System.out.format("Reading categories1: CategoryId:%s Title:%s Assignable: %b\n",
        categories1.getCategoryId(), categories1.getTitle(), categories1.getAssignable());
    VideosDao videosDao = VideosDao.getInstance();
    Videos videos = new Videos("title", "trendingDate", new Timestamp(date.getTime()), "tags",
        Long.parseLong("536143"), Long.parseLong("538"), "thumbnailLink", Long.parseLong("53688"),
        true, true, true, "description", categories1, user1, countries1);
    videos = videosDao.create(videos);
    LikesDao likesDao = LikesDao.getInstance();
    Likes likes = new Likes(1, videos, user1, date);
    likes = likesDao.create(likes);
    VideoTrendsDao videoTrendsDao = VideoTrendsDao.getInstance();
    VideoTrends videoTrends = new VideoTrends(1, videos, date, "description");
    videoTrends = videoTrendsDao.create(videoTrends);
    SearchHistoryDao searchHistoryDao = SearchHistoryDao.getInstance();
    SearchHistory searchHistory = new SearchHistory(date, "search", 2, user1);
    searchHistory = searchHistoryDao.create(searchHistory);
//    read function exercise
    Persons p1 = personsDao.getPersonByUserName(1);
    System.out.format("Reading person: UserId:%s FirstName:%s LastName:%s \n", p1.getUserId(),
        p1.getFirstName(), p1.getLastName());

    List<Persons> personsList = personsDao.getPersonsFromFirstName("1");
    for (Persons p : personsList) {
      System.out.format("Looping persons: FirstName:%s LastName:%s UserId:%s \n", p.getFirstName(),
          p.getLastName(), p.getUserId());
    }

    Users u1 = usersDao.getUserByUserId(1);
    System.out.format("Reading users: UserId:%s FirstName:%s LastName:%s \n", u1.getUserId(),
        u1.getFirstName(), u1.getLastName());

    List<Users> userList = usersDao.getUsersByFirstName("user");
    for (Users p : userList) {
      System.out.format("Looping users: FirstName:%s LastName:%s UserId:%s DoB:%s \n",
          p.getFirstName(), p.getLastName(), p.getUserId(), p.getDoB());
    }

    Videos videos1 = videosDao.getVideosById(1);
    System.out.format(
        "Reading videos: VideoId:%d Title:%s TrendingDate:%s PublishTime: %s Tags:%s Views: %d commentCount: %d thumbnailLink: %s dislikes: %d commentsDisabled:%b ratingsDisabled:%b videoErrorOrRemoved:%b description: %s category: %s Users:%s countryId: %s  \n",
        videos.getVideoId(), videos.getTitle(), videos.getTrendingDate(), videos.getPublishTime(),
        videos.getTags(), videos.getViews(), videos.getCommentCount(), videos.getThumbnailLink(),
        videos.getDislikes(), videos.isCommentsDisabled(), videos.isRatingsDisabled(),
        videos.isVideoErrorOrRemoved(), videos.getDescription(), videos.getCategoryId(),
        videos.getUserId(), videos.getCountryId());
    List<Videos> v1 = videosDao.getVideosByTitle("title");
    for (Videos a : v1) {
      System.out.format(
          "Reading videos: VideoId:%d Title:%s TrendingDate:%s PublishTime: %s Tags:%s Views: %d commentCount: %d thumbnailLink: %s dislikes: %d commentsDisabled:%b ratingsDisabled:%b videoErrorOrRemoved:%b description: %s category: %s Users:%s countryId: %s  \n",
          a.getVideoId(), a.getTitle(), a.getTrendingDate(), a.getPublishTime(), a.getTags(),
          a.getViews(), a.getCommentCount(), a.getThumbnailLink(), a.getDislikes(),
          a.isCommentsDisabled(), a.isRatingsDisabled(), a.isVideoErrorOrRemoved(),
          a.getDescription(), a.getCategoryId(), a.getUserId(), a.getCountryId());
    }
    VideoTrends videoTrends1 = videoTrendsDao.getVideoTrendsById(1);
    System.out.format(
        "Looping videoTrends: VideoTrendId:%s Title:%s TrendingDate:%s Description:%s \n",
        videoTrends1.getVideoTrendId(), videoTrends1.getVideoId().getTitle(),
        videoTrends1.getTrendingDate(), videoTrends1.getDescription());

    Comments c1 = commentsDao.getCommentById(1);
    System.out.format("Reading comment: CommentId:%s Created:%s Content:%s \n", c1.getCommentId(),
        c1.getCreated(), c1.getContent());

    List<Administrators> administratorsList = administratorsDao.getAdministratorsFromFirstName(
        "ad");
    for (Administrators administrator : administratorsList) {
      System.out.format(
          "Reading administrators: FirstName:%s LastName:%s UserId:%s LastLogin:%s \n",
          administrator.getFirstName(), administrator.getLastName(), administrator.getUserId(),
          administrator.getLastLogin());
    }

    //exercise update function
    Persons personUpdate = personsDao.updateLastName(person1, "update");
    System.out.format("Updating person1: 1:%s 2:%s \n", person1.getUserId(), person1.getLastName());
    Users userUpdate = usersDao.updateLastName(user1, "hahah");
    System.out.format("Updating administrator1: 1:%s 2:%s \n", user1.getUserId(),
        user1.getLastName());
    Administrators administrators1 = administratorsDao.updateLastName(administrators, "hhh");
    System.out.format("Updating administrator1: 1:%s 2:%s \n", administrators.getUserId(),
        administrators.getLastName());

    Videos videos2 = videosDao.updateTitle(videos, "new title");
    System.out.format(
        "Reading videos: VideoId:%d Title:%s TrendingDate:%s PublishTime: %s Tags:%s Views: %d commentCount: %d thumbnailLink: %s dislikes: %d commentsDisabled:%b ratingsDisabled:%b videoErrorOrRemoved:%b description: %s category: %s Users:%s countryId: %s  \n",
        videos2.getVideoId(), videos2.getTitle(), videos2.getTrendingDate(),
        videos2.getPublishTime(), videos2.getTags(), videos2.getViews(), videos2.getCommentCount(),
        videos2.getThumbnailLink(), videos2.getDislikes(), videos2.isCommentsDisabled(),
        videos2.isRatingsDisabled(), videos2.isVideoErrorOrRemoved(), videos2.getDescription(),
        videos2.getCategoryId(), videos2.getUserId(), videos2.getCountryId());
    VideoTrends videoTrends2 = videoTrendsDao.update(videoTrends1, "new des");
    System.out.format(
        "Looping videoTrends: VideoTrendId:%s Title:%s TrendingDate:%s Description:%s \n",
        videoTrends2.getVideoTrendId(), videoTrends2.getVideoId().getTitle(),
        videoTrends2.getTrendingDate(), videoTrends2.getDescription());
    SearchHistory searchHistory1 = SearchHistoryDao.getInstance()
        .getSearchHistoryBySearchKeyWords("search");
    System.out.format(
        "Looping searchHistory: ID:%s SearchKeyWords:%s SearchTime:%s FirstName:%s \n",
        searchHistory1.getID(), searchHistory1.getSearchKeyWords(), searchHistory1.getSearchTime(),
        searchHistory1.getUser().getFirstName());
    Likes likes1 = LikesDao.getInstance().getLikeById(1);
    System.out.format("Looping likes1: ID:%s Created:%s Title:%s FirstName:%s \n",
        likes1.getLikesId(), likes1.getCreated(), likes1.getVideo().getTitle(),
        likes1.getUser().getFirstName());
    //exercise delete function todo remove delete
//    usersDao.delete(user1);
//
//    personsDao.delete(person1);
//
//    commentsDao.delete(c1);
//    videosDao.delete(videos);
//    countriesDao.delete(countries);
//    categoriesDao.delete(categories);
//    likesDao.delete(likes);
//    videoTrendsDao.delete(videoTrends);
//    searchHistoryDao.delete(searchHistory);

  }
}