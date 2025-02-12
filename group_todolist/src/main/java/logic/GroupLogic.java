package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.GroupDAO;
import datebase.DBconnection;
import model.Group;
import model.GroupMember;

public class GroupLogic {
	public List<Group> find() throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupDAO dao = new GroupDAO();
			
			return dao.findAll(con);
		}
	}
	
	public Group find(int id) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupDAO dao = new GroupDAO();
			
			return dao.findOne(con, id);
		}
	}
	
	public List<Group> findbyGroupId(List<GroupMember> groupMemberList) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupDAO dao = new GroupDAO();
			
			return dao.findbyGroupId(con, groupMemberList);
		}
	}
	
	public List<Group> search(String searchValue) throws ClassNotFoundException, SQLException{
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupDAO dao = new GroupDAO();
			
			return dao.search(con, searchValue);
		}
	}
	
	public Group findByName(String groupName) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupDAO dao = new GroupDAO();
			
			return dao.findByName(con, groupName);
		}
	}
	
	public int create(Group group) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupDAO dao = new GroupDAO();
			
			return dao.create(con, group);
		}
	}
	
	public int update(Group group) throws ClassNotFoundException, SQLException {
		
		try(DBconnection db = new DBconnection()){
			Connection con = db.getInstance();
			GroupDAO dao = new GroupDAO();
			
			return dao.update(con, group);
		}
	}
}
