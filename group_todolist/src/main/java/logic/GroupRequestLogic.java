package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.GroupRequestDAO;
import datebase.DBconnection;
import model.GroupRequest;

public class GroupRequestLogic {
	public List<GroupRequest> find() throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupRequestDAO dao = new GroupRequestDAO();
			
			return dao.findAll(con);
		}
	}
	
	public List<GroupRequest> findByGroup(int groupId) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupRequestDAO dao = new GroupRequestDAO();
			
			return dao.findByGroup(con, groupId);
		}
	}
	
	public GroupRequest find(int id) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupRequestDAO dao = new GroupRequestDAO();
			
			return dao.findOne(con, id);
		}
	}
	
	public GroupRequest find(int userId, int groupId) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupRequestDAO dao = new GroupRequestDAO();
			
			return dao.findOne(con, userId, groupId);
		}
	}
	
	public List<Integer> findByPermission0(int userId) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupRequestDAO dao = new GroupRequestDAO();
			
			return dao.findByPermission0(con, userId);
		}
	}
	
	public List<Integer> findByPermission2(int userId) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupRequestDAO dao = new GroupRequestDAO();
			
			return dao.findByPermission2(con, userId);
		}
	}
	
	public boolean create(GroupRequest groupRequest) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupRequestDAO dao = new GroupRequestDAO();
			
			return dao.create(con, groupRequest);
		}
	}
	
	public boolean update(GroupRequest groupRequest) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupRequestDAO dao = new GroupRequestDAO();
			
			return dao.update(con, groupRequest);
		}
	}
}
