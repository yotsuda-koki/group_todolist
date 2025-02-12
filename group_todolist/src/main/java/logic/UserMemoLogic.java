package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.UserMemoDAO;
import datebase.DBconnection;
import model.UserMemo;

public class UserMemoLogic {
	
	public List<UserMemo> findAll(int userId) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserMemoDAO dao = new UserMemoDAO();
			
			return dao.findAll(con, userId);
		}
	}
	
	public UserMemo findOne(int id) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserMemoDAO dao = new UserMemoDAO();
			
			return dao.findOne(con, id);
		}
	}
	
	public boolean create(UserMemo userMemo) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserMemoDAO dao = new UserMemoDAO();
			
			return dao.create(con, userMemo);
		}
	}
	
	public boolean update(UserMemo userMemo) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserMemoDAO dao = new UserMemoDAO();
			
			return dao.update(con, userMemo);
		}
	}
}
