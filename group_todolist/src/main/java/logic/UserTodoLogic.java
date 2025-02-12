package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.UserTodoDAO;
import datebase.DBconnection;
import model.UserTodo;

public class UserTodoLogic {
	
	public List<UserTodo> findAll(int userId) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserTodoDAO dao = new UserTodoDAO();
			
			return dao.findAll(con, userId);
		}
	}
	
	public UserTodo findOne(int id) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserTodoDAO dao = new UserTodoDAO();
			
			return dao.findOne(con, id);
		}
	}
	
	public boolean create(UserTodo userTodo) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserTodoDAO dao = new UserTodoDAO();
			
			return dao.create(con, userTodo);
		}
	}
	
	public boolean update(UserTodo userTodo) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			UserTodoDAO dao = new UserTodoDAO();
			
			return dao.update(con, userTodo);
		}
	}
}
