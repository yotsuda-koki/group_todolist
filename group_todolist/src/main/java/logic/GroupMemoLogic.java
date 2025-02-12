package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.GroupMemoDAO;
import datebase.DBconnection;
import model.GroupMemo;

public class GroupMemoLogic {
	public List<GroupMemo> findAll(int groupId) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupMemoDAO dao = new GroupMemoDAO();
			
			return dao.findAll(con, groupId);
		}
	}
	
	public GroupMemo find(int id) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupMemoDAO dao = new GroupMemoDAO();
			
			return dao.findOne(con, id);
		}
	}
	
	public boolean create(GroupMemo groupMemo) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupMemoDAO dao = new GroupMemoDAO();
			
			return dao.create(con, groupMemo);
		}
	}
	
	public boolean update(GroupMemo groupMemo) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupMemoDAO dao = new GroupMemoDAO();
			
			return dao.update(con, groupMemo);
		}
	}
	
	public boolean update(String oldAccountId, String newAccountId) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupMemoDAO dao = new GroupMemoDAO();
			
			return dao.update(con, oldAccountId, newAccountId);
		}
	}
}
