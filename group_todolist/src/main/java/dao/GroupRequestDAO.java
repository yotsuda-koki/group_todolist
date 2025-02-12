package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GroupRequest;

public class GroupRequestDAO {
	
	//グループリクエストを全検索してリストに格納して返す
	public List<GroupRequest> findAll(Connection con){
		List<GroupRequest> list = new ArrayList<GroupRequest>();
		
		try {
			String sql = "select * from groups_request";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql);
					ResultSet rs = pStmt.executeQuery()){
				while(rs.next()) {
					GroupRequest grouprequest = new GroupRequest();
					
					grouprequest.setId(rs.getInt("id"));
					grouprequest.setGroupId(rs.getInt("group_id"));
					grouprequest.setUserId(rs.getInt("user_id"));
					grouprequest.setUserName(rs.getString("user_name"));
					grouprequest.setAccountId(rs.getString("account_id"));
					grouprequest.setPermission(rs.getInt("permission"));
					grouprequest.setIsDeleted(rs.getInt("is_deleted"));
					grouprequest.setRequestedAt(rs.getTimestamp("requested_at"));
					
					list.add(grouprequest);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public List<GroupRequest> findByGroup(Connection con, int groupId){
		List<GroupRequest> list = new ArrayList<GroupRequest>();
		

		try {
			String sql = "select * from groups_request where group_id = ? and permission = 0";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupId);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						GroupRequest grouprequest = new GroupRequest();
						
						grouprequest.setId(rs.getInt("id"));
						grouprequest.setGroupId(rs.getInt("group_id"));
						grouprequest.setUserId(rs.getInt("user_id"));
						grouprequest.setUserName(rs.getString("user_name"));
						grouprequest.setAccountId(rs.getString("account_id"));
						grouprequest.setPermission(rs.getInt("permission"));
						grouprequest.setIsDeleted(rs.getInt("is_deleted"));
						grouprequest.setRequestedAt(rs.getTimestamp("requested_at"));
						
						list.add(grouprequest);
					}
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	//IDでグループリクエストを１件検索して返す
	public GroupRequest findOne(Connection con, int id) {
		GroupRequest grouprequest = new GroupRequest();
		
		try {
			String sql = "select * from groups_request where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, id);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						grouprequest.setId(rs.getInt("id"));
						grouprequest.setGroupId(rs.getInt("group_id"));
						grouprequest.setUserId(rs.getInt("user_id"));
						grouprequest.setUserName(rs.getString("user_name"));
						grouprequest.setAccountId(rs.getString("account_id"));
						grouprequest.setPermission(rs.getInt("permission"));
						grouprequest.setIsDeleted(rs.getInt("is_deleted"));
						grouprequest.setRequestedAt(rs.getTimestamp("requested_at"));
					}else {
						grouprequest = null;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return grouprequest;
	}
	
	public GroupRequest findOne(Connection con, int userId, int groupId) {
		
		GroupRequest grouprequest = new GroupRequest();
		
		try {
			String sql = "select * from groups_request where user_id = ? and group_id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, userId);
				pStmt.setInt(2, groupId);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						grouprequest.setId(rs.getInt("id"));
						grouprequest.setGroupId(rs.getInt("group_id"));
						grouprequest.setUserId(rs.getInt("user_id"));
						grouprequest.setUserName(rs.getString("user_name"));
						grouprequest.setAccountId(rs.getString("account_id"));
						grouprequest.setPermission(rs.getInt("permission"));
						grouprequest.setIsDeleted(rs.getInt("is_deleted"));
						grouprequest.setRequestedAt(rs.getTimestamp("requested_at"));
					}else {
						grouprequest = null;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return grouprequest;
	}
	
	//ユーザーIDとPermission=0からグループIDを検索してリストに格納して返す
	public List<Integer> findByPermission0(Connection con, int user_id) {
		List<Integer> requestIds = new ArrayList<>();
		
		try {
			String sql = "select group_id from groups_request where user_id = ? and permission = 0";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, user_id);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						
						int grouprequest = rs.getInt("group_id");

						requestIds.add(grouprequest);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return requestIds;
	}
	
	//ユーザーIDとPermission=2からグループIDを検索してリストに格納して返す
	public List<Integer> findByPermission2(Connection con, int user_id) {
		List<Integer> requestIds = new ArrayList<>();
		
		try {
			String sql = "select group_id from groups_request where user_id = ? and permission = 2";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, user_id);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						
						int grouprequest = rs.getInt("group_id");

						requestIds.add(grouprequest);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return requestIds;
	}
	
	//グループリクエストを１件追加する
	public boolean create(Connection con, GroupRequest groupRequest) {
		try {
			String sql = "insert into groups_request (group_id, user_id, user_name, account_id, permission, is_deleted, requested_at) value (?, ?, ?, ?, ?, ?, ?)";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupRequest.getGroupId());
				pStmt.setInt(2, groupRequest.getUserId());
				pStmt.setString(3, groupRequest.getUserName());
				pStmt.setString(4, groupRequest.getAccountId());
				pStmt.setInt(5, groupRequest.getPermission());
				pStmt.setInt(6, groupRequest.getIsDeleted());
				pStmt.setTimestamp(7, groupRequest.getRequestedAt());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//グループリクエストを１件更新する
	public boolean update(Connection con, GroupRequest grouprequest) {
		try {
			String sql = "update groups_request set permission = ? where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, grouprequest.getPermission());
				pStmt.setInt(2, grouprequest.getId());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
}
