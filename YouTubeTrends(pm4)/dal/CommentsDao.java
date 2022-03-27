package YouTubeTrends.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import YouTubeTrends.model.*;

public class CommentsDao {
	protected ConnectionManager connectionManager;

	private static CommentsDao instance = null;
	
	protected CommentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CommentsDao getInstance() {
		if(instance == null) {
			instance = new CommentsDao();
		}
		return instance;
	}

	public Comments create(Comments Comment) throws SQLException {
		String insertComment =
			"INSERT INTO Comments(Content, Created, UserId) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertComment,
				Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, Comment.getContent());
			insertStmt.setTimestamp(2, new Timestamp(Comment.getCreated().getTime()));
			insertStmt.setInt(3, Comment.getUser().getUserId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int CommentId = -1;
			if(resultKey.next()) {
				CommentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			Comment.setCommentId(CommentId);
			return Comment;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Comments getCommentById(int CommentId) throws SQLException {
		String selectComment =
			"SELECT CommentId,Content,Created,userId FROM Comments WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComment);
			selectStmt.setInt(1,CommentId);
			results = selectStmt.executeQuery();
			UsersDao userDao = UsersDao.getInstance();
			if(results.next()) {
				int resultCommentId = results.getInt("CommentId");
				Date created = results.getDate("Created");
				String content = results.getString("Content");
				int userId = results.getInt("UserId");
				Users user = userDao.getUserByUserId(userId);
				
				Comments Comment = new Comments(resultCommentId, content, created, user);
				return Comment;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}


	public Comments delete(Comments Comment) throws SQLException {
		String deleteComment = "DELETE FROM Comments WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteComment);
			deleteStmt.setInt(1, Comment.getCommentId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
}
