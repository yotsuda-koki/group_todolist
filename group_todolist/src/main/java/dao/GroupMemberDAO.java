package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GroupMember;


public class GroupMemberDAO {
	
	//グループメンバーを全検索してリストに格納して返す
	public List<GroupMember> findAll(Connection con){
		List<GroupMember> list = new ArrayList<GroupMember>();
		
		try {
			String sql = "select * from groups_member";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql);
					ResultSet rs = pStmt.executeQuery()){
				while(rs.next()) {
					GroupMember groupMember = new GroupMember();
					
					groupMember.setId(rs.getInt("id"));
					groupMember.setGroupId(rs.getInt("group_id"));
					groupMember.setUserId(rs.getInt("user_id"));
					groupMember.setAdmin(rs.getInt("admin"));
					groupMember.setIsDeleted(rs.getInt("is_deleted"));
					groupMember.setCreatedAt(rs.getTimestamp("created_at"));
					groupMember.setUpdatedAt(rs.getTimestamp("updated_at"));
					
					list.add(groupMember);
				}
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			return null;
		}
		return list;
	}
	
	//IDでグループメンバーを１件検索して返す
	public GroupMember findOne(Connection con, int id) {
		GroupMember groupMember = new GroupMember();
		
		try {
			String sql = "select * from groups_member where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, id);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						groupMember.setId(rs.getInt("id"));
						groupMember.setGroupId(rs.getInt("group_id"));
						groupMember.setUserId(rs.getInt("user_id"));
						groupMember.setAdmin(rs.getInt("admin"));
						groupMember.setIsDeleted(rs.getInt("is_deleted"));
						groupMember.setCreatedAt(rs.getTimestamp("created_at"));
						groupMember.setUpdatedAt(rs.getTimestamp("updated_at"));
					}else {
						groupMember = null;
					}
				}
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			return null;
		}
		return groupMember;
	}
	
	//ユーザーIDからグループIDを検索してリストに格納して返す
	public List<GroupMember> findByUserId(Connection con, int userId){
		List<GroupMember> list = new ArrayList<GroupMember>();
		
		try {
			String sql = "select group_id from groups_member where user_id = ? and is_deleted = 0";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, userId);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						GroupMember groupMember = new GroupMember();
						
						groupMember.setGroupId(rs.getInt("group_id"));
						
						list.add(groupMember);
					}
				}
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			return null;
		}
		return list;
	}
	
	//ユーザーIDからグループIDを検索してリストに格納して返す
	public List<Integer> findGroupId(Connection con, int userId){
		List<Integer> groupIds = new ArrayList<>();
		
		try {
			String sql = "select group_id from groups_member where user_id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, userId);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						int groupId = rs.getInt("group_id");
						
						groupIds.add(groupId);
					}
				}
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			return null;
		}
		return groupIds;
	}
	
	//グループIDからユーザーIDを検索してリストに格納して返す
	public List<Integer> findMem(Connection con, int groupId){
	    List<Integer> userIds = new ArrayList<>();
	    
	    try {
	        String sql = "SELECT user_id FROM groups_member WHERE group_id = ? and is_deleted = 0";
	        
	        try (PreparedStatement pStmt = con.prepareStatement(sql)){
	            
	            pStmt.setInt(1, groupId);
	            
	            try (ResultSet rs = pStmt.executeQuery()){
	                
	                while (rs.next()) {
	                    int userId = rs.getInt("user_id");
	                    userIds.add(userId);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	    return userIds;
	}
	
	//グループIDとユーザーIDからアドミンを検索
	public int findAdmin(Connection con, int groupId, int userId) {
		
		GroupMember groupMember = new GroupMember();
		
		try {
			String sql = "select admin from groups_member where group_id = ? and user_id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupId);
				pStmt.setInt(2, userId);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {

						groupMember.setAdmin(rs.getInt("admin"));

					}else {
						groupMember = null;
					}
				}
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			return e.getErrorCode();
		}
		return groupMember.getAdmin();
	}
	
	//グループIDとユーザーIDからIDを検索
	public int findId(Connection con, int groupId, int userId) {
		
		GroupMember groupMember = new GroupMember();
		
		try {
			String sql = "select id from groups_member where group_id = ? and user_id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupId);
				pStmt.setInt(2, userId);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {

						groupMember.setId(rs.getInt("id"));

					}else {
						groupMember = null;
					}
				}
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			return e.getErrorCode();
		}
		return groupMember.getId();
	}
	
	//グループメンバーを１件追加する
	public boolean create(Connection con, GroupMember groupMember) {
		try {
			String sql = "insert into groups_member (group_id, user_id, admin, is_deleted, created_at) value (?, ?, ?, ?, ?)";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupMember.getGroupId());
				pStmt.setInt(2, groupMember.getUserId());
				pStmt.setInt(3, groupMember.getAdmin());
				pStmt.setInt(4, groupMember.getIsDeleted());
				pStmt.setTimestamp(5, groupMember.getCreatedAt());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//グループメンバーを１件更新する
	public boolean update(Connection con, GroupMember groupMember) {
		try {
			String sql = "update groups_member set admin = ?, is_deleted = ?, updated_at = ? where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupMember.getAdmin());
				pStmt.setInt(2, groupMember.getIsDeleted());
				pStmt.setTimestamp(3, groupMember.getUpdatedAt());
				pStmt.setInt(4, groupMember.getId());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
}
