package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.GroupTodoDAO;
import datebase.DBconnection;
import model.GroupTodo;

public class GroupTodoLogic {
	public List<GroupTodo> findAll(int groupId) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupTodoDAO dao = new GroupTodoDAO();
			
			return dao.findAll(con, groupId);
		}
	}
	
	public GroupTodo find(int id) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupTodoDAO dao = new GroupTodoDAO();
			
			return dao.findOne(con, id);
		}
	}
	
	public boolean create(GroupTodo groupTodo) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupTodoDAO dao = new GroupTodoDAO();
			
			return dao.create(con, groupTodo);
		}
	}
	
	public boolean update(GroupTodo groupTodo) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupTodoDAO dao = new GroupTodoDAO();
			
			return dao.update(con, groupTodo);
		}
	}
	
	public boolean update(String oldAccountId, String newAccountId) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupTodoDAO dao = new GroupTodoDAO();
			
			return dao.update(con, oldAccountId, newAccountId);
		}
	}
}
