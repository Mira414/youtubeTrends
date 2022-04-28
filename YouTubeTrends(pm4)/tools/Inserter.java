package YouTubeTrends.tools;

import java.sql.Date;

import java.sql.SQLException;
import java.util.List;

import YouTubeTrends.dal.*;
import YouTubeTrends.model.*;

public class Inserter {

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        //create function exercise

        UsersDao usersDao = UsersDao.getInstance();
        Date date = new Date(20211005);
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

        //read function exercise
        Persons p1 = personsDao.getPersonByUserName(1);
        System.out.format("Reading person: UserId:%s FirstName:%s LastName:%s \n",
                p1.getUserId(), p1.getFirstName(), p1.getLastName());

        List<Persons> personsList = personsDao.getPersonsFromFirstName("1");
        for (Persons p : personsList) {
            System.out.format("Looping persons: FirstName:%s LastName:%s UserId:%s \n",
                    p.getFirstName(), p.getLastName(), p.getUserId());
        }
        
		Users u1 = usersDao.getUserByUserId(1);
		System.out.format("Reading users: UserId:%s FirstName:%s LastName:%s \n",
				u1.getUserId(), u1.getFirstName(), u1.getLastName());

        List<Users> userList = usersDao.getUsersByFirstName("user");
        for (Users p : userList) {
            System.out.format("Looping users: FirstName:%s LastName:%s UserId:%s DoB:%s \n",
                    p.getFirstName(), p.getLastName(), p.getUserId(), p.getDoB());
        }

        Comments c1 = commentsDao.getCommentById(1);
        System.out.format("Reading comment: CommentId:%s Created:%s Content:%s \n",
                c1.getCommentId(), c1.getCreated(), c1.getContent());

        List<Administrators> administratorsList = administratorsDao.getAdministratorsFromFirstName(
                "ad");
        for (Administrators administrator : administratorsList) {
            System.out.format(
                    "Reading administrators: FirstName:%s LastName:%s UserId:%s LastLogin:%s \n",
                    administrator.getFirstName(), administrator.getLastName(),
                    administrator.getUserId(), administrator.getLastLogin());
        }

        //exercise update function
        Persons personUpdate = personsDao.updateLastName(person1, "update");
        System.out.format("Updating person1: 1:%s 2:%s \n",
                person1.getUserId(), person1.getLastName());
        Users userUpdate = usersDao.updateLastName(user1, "hahah");
        System.out.format("Updating administrator1: 1:%s 2:%s \n",
                user1.getUserId(), user1.getLastName());
        Administrators administrators1 = administratorsDao.updateLastName(administrators, "hhh");
        System.out.format("Updating administrator1: 1:%s 2:%s \n",
                administrators.getUserId(), administrators.getLastName());

        //exercise delete function
         usersDao.delete(user1);
         
         personsDao.delete(person1);
         
         commentsDao.delete(c1);
    }
}



