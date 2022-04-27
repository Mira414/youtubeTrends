1. How to execute the project?

- Firstly, please open the 'ConnectionManager.java' file under 'src/main/java/youtubetrends/dal' folder.
  And then check the 'user' and 'password', and change these two variables to your own user name and password. 
  Please make sure you can connect to your MySQL with these two variables.

- Secondly, please open MySQL and make sure your MySQL IDE contains all 'youtubetrends' tables,
  And please make sure you have inserted data in all the tables.

- Finally, back to Eclipse IDE and Run this project on server.

2. Functions Illusitation on Web page

- Create Video

- Delete Video

- Search Video By Title

- Update Video

- Delete Country By CountryId

- Delete Catergories By CatergoryId

The above functions have been introduced in detail in the report of Milestone 4.
The following functions are related to the performance of our products.

- Find out the hottest Videos

  (1) Firstly, click the 'Hottest Video' button in order to enter the hottest videos searching page.

  (2) Then, After enter a valid target time, click submit button to begin search

  (3) Finally, the result top 10 videos will be listed in the following table.

- Analyze the likes or dislikes of specific videos

  Pay Attention: This function is used to prump the video Likes information.
  Since the data of 'Likes' table in our demo project are limited, therefore, only the videos which
  have been record in Likes table can view their 'Video Likes' information. 
  So, if you randomlly click the 'Likes' button of one video, the come out page may be empty.
  This is normal, because the one you click is not record in the 'Likes' table.

  (1) Firstly, you can create a temp .sql file in order to execute the following query.
    ```
   USE youtubetrends;
   
   SELECT Title, Views, Dislikes
   FROM Videos INNER JOIN Likes
   ON Videos.VideoId = Likes.VideoId;
   ```
  Then, after execution, you can get information about the videos that are crossed by 'Likes' table and 'Videos' table.
  (2) Secondly, select the title name from the above result table, and use the 'Search Video' function, so that you can get
  a list of Videos with the target title.

  (3) Thirdly, by comparing the number of Views and Dislikes corresponding to this title in the table in (1), you can find 
  the exact one recorded in Likes for Video.

  (4) Finally, click the 'Likes' button, then you can get the Video likes analysis page.

- Find the top N Optimal Ads time based on the Views of Videos data. 

  In our project, we assume that the optimal ad time is the publish time corresponding to the video with more views.

  (1) Firslty, click 'Optimal Ads Time' button to enter the corresponding search page.

  (2) Secondly, enter how many result videos you want in the blank position. (which is the N of top N).
       Then click submit button.

  (3) Finally, you can get the top N ads Time including the corresponding videos information so that you can
       connecting with the video creator. 
