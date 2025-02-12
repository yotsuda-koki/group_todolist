package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.UserDAO;
import datebase.DBconnection;
import model.User;

public class UserLogic {
	
	public List<User> find() throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.findAll(con);
		}
	}	
	
	public User find(int id) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.findOne(con, id);
		}
	}	
	
	public User find(String emailOrAccountId, String password) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.findOne(con, emailOrAccountId, password);
		}
	}
	
	public List<User> find(List<Integer> userIds) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.findUsersByIds(con, userIds);
		}
	}
	
	public int create(User user) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.create(con, user);
		}
	}
	
	public int update(User user) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.update(con, user);
		}
	}
	
    public int updatePassword(int userId, String newPassword) throws ClassNotFoundException, SQLException {
        try (DBconnection db = new DBconnection()) {
            Connection con = db.getInstance();
            UserDAO dao = new UserDAO();
            return dao.updatePassword(con, userId, newPassword);
        }
    }
	
}
