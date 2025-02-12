package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.GroupMemberDAO;
import datebase.DBconnection;
import model.GroupMember;

public class GroupMemberLogic {
		public List<GroupMember> find() throws ClassNotFoundException, SQLException{
			
			try(DBconnection db = new DBconnection()){
				Connection con = db.getInstance();
				GroupMemberDAO dao = new GroupMemberDAO();
				
				return dao.findAll(con);
			}
		}
		
		public GroupMember find(int id) throws ClassNotFoundException, SQLException {
			
			try(DBconnection db = new DBconnection()){
				Connection con = db.getInstance();
				GroupMemberDAO dao = new GroupMemberDAO();
				
				return dao.findOne(con, id);
			}
		}
		
		public List<GroupMember> findByUserId(int userId) throws ClassNotFoundException, SQLException{
			
			try(DBconnection db = new DBconnection()){
				Connection con = db.getInstance();
				GroupMemberDAO dao = new GroupMemberDAO();
				
				return dao.findByUserId(con, userId);
			}
		}
		
		public List<Integer> findGroupId(int userId) throws ClassNotFoundException, SQLException{
			
			try(DBconnection db = new DBconnection()){
				Connection con = db.getInstance();
				GroupMemberDAO dao = new GroupMemberDAO();
				
				return dao.findGroupId(con, userId);
			}
		}
		
		public List<Integer> findMem(int groupId) throws ClassNotFoundException, SQLException{
			
			try(DBconnection db = new DBconnection()){
				Connection con = db.getInstance();
				GroupMemberDAO dao = new GroupMemberDAO();
				
				return dao.findMem(con, groupId);
			}
		}
		
		public int findAdmin(int groupId, int userId) throws ClassNotFoundException, SQLException {
			
			try(DBconnection db = new DBconnection()){
				Connection con = db.getInstance();
				GroupMemberDAO dao = new GroupMemberDAO();
				
				return dao.findAdmin(con, groupId, userId);
			}
		}
		
		public int findId(int groupId, int userId) throws ClassNotFoundException, SQLException {
			
			try(DBconnection db = new DBconnection()){
				Connection con = db.getInstance();
				GroupMemberDAO dao = new GroupMemberDAO();
				
				return dao.findId(con, groupId, userId);
			}
		}
		
		public boolean create(GroupMember groupMember) throws ClassNotFoundException, SQLException {
			
			try(DBconnection db = new DBconnection()){
				Connection con = db.getInstance();
				GroupMemberDAO dao = new GroupMemberDAO();
				
				return dao.create(con, groupMember);
			}
		}
		
		public boolean update(GroupMember groupMember) throws ClassNotFoundException, SQLException {
			
			try(DBconnection db = new DBconnection()){
				Connection con = db.getInstance();
				GroupMemberDAO dao = new GroupMemberDAO();
				
				return dao.update(con, groupMember);
			}
		}
}
