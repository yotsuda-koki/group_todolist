package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Group;
import model.GroupMember;
import settings.DatebaseSettings;


public class GroupDAO {
	
	//グループを全検索してリストに格納して返す
	public List<Group> findAll(Connection con){
		List<Group> list = new ArrayList<Group>();
		
		try {
			String sql = "select * from groups";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql);
					ResultSet rs = pStmt.executeQuery()){
				while(rs.next()) {
					Group group = new Group();
					
					group.setId(rs.getInt("id"));
					group.setGroupName(rs.getString("group_name"));
					group.setGroupInfo(rs.getString("group_info"));
					group.setIsDeleted(rs.getInt("is_deleted"));
					group.setCreatedAt(rs.getTimestamp("created_at"));
					group.setUpdatedAt(rs.getTimestamp("updated_at"));
					
					list.add(group);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	//IDでグループを１件検索して返す
	public Group findOne(Connection con, int id) {
		Group group = new Group();
		
		try {
			String sql = "select * from groups where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, id);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						group.setId(rs.getInt("id"));
						group.setGroupName(rs.getString("group_name"));
						group.setGroupInfo(rs.getString("group_info"));
						group.setIsDeleted(rs.getInt("is_deleted"));
						group.setCreatedAt(rs.getTimestamp("created_at"));
						group.setUpdatedAt(rs.getTimestamp("updated_at"));
					}else {
						group = null;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return group;
	}
	
	//グループメンバーリストのグループIDからグループリストを検索して返す
	public List<Group> findbyGroupId(Connection con, List<GroupMember> groupMemberList){
		List<Group> list = new ArrayList<Group>();
		
		try {
			String sql = "select * from groups where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				for(GroupMember groupMember : groupMemberList) {
					
					pStmt.setInt(1, groupMember.getGroupId());
					
					try(ResultSet rs = pStmt.executeQuery()){
						
						while(rs.next()) {
							Group group = new Group();
							
							group.setId(rs.getInt("id"));
							group.setGroupName(rs.getString("group_name"));
							group.setGroupInfo(rs.getString("group_info"));
							group.setIsDeleted(rs.getInt("is_deleted"));
							group.setCreatedAt(rs.getTimestamp("created_at"));
							group.setUpdatedAt(rs.getTimestamp("updated_at"));
							
							list.add(group);
						}
					}
				
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	
	//searchWordでリストを検索して返す
	public List<Group> search(Connection con, String searchWord){
		List<Group> list = new ArrayList<>();
		
		try{
			
			String sql = "SELECT * FROM groups WHERE group_name LIKE ? OR group_info LIKE ? ORDER BY id ASC";

			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setString(1, "%" + searchWord + "%");
				pStmt.setString(2, "%" + searchWord + "%");
				
				try(ResultSet rs = pStmt.executeQuery()){
			
					while(rs.next()) {
						Group group = new Group();
						
						group.setId(rs.getInt("id"));
						group.setGroupName(rs.getString("group_name"));
						group.setGroupInfo(rs.getString("group_info"));
						group.setIsDeleted(rs.getInt("is_deleted"));
						group.setCreatedAt(rs.getTimestamp("created_at"));
						group.setUpdatedAt(rs.getTimestamp("updated_at"));
						
						list.add(group);
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public Group findByName(Connection con, String groupName) {
		Group group = new Group();
		
		try {
			String sql = "select * from groups where group_name = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setString(1, groupName);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						group.setId(rs.getInt("id"));
						group.setGroupName(rs.getString("group_name"));
						group.setGroupInfo(rs.getString("group_info"));
						group.setIsDeleted(rs.getInt("is_deleted"));
						group.setCreatedAt(rs.getTimestamp("created_at"));
						group.setUpdatedAt(rs.getTimestamp("updated_at"));
					}else {
						group = null;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return group;
	}
	
	//グループを１件追加する
	public int create(Connection con, Group group) {
		try {
			String sql = "insert into groups (group_name, group_info, is_deleted, created_at) value (?, ?, ?, ?)";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setString(1, group.getGroupName());
				pStmt.setString(2, group.getGroupInfo());
				pStmt.setInt(3, group.getIsDeleted());
				pStmt.setTimestamp(4, group.getCreatedAt());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
			return e.getErrorCode();
		}
		return DatebaseSettings.SUCCESS;
	}
	
	//グループを１件更新する
	public int update(Connection con, Group group) {
		try {
			String sql = "update groups set group_name = ?, group_info = ?, is_deleted = ?, updated_at = ? where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setString(1, group.getGroupName());
				pStmt.setString(2, group.getGroupInfo());
				pStmt.setInt(3, group.getIsDeleted());
				pStmt.setTimestamp(4, group.getUpdatedAt());
				pStmt.setInt(5, group.getId());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {

			e.printStackTrace();
			
			return e.getErrorCode();
		}
		return DatebaseSettings.SUCCESS;
	}
}
