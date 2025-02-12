package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GroupTodo;

public class GroupTodoDAO {
	//グループTODOリストを全検索してリストに格納して返す
	public List<GroupTodo> findAll(Connection con, int groupId){
		List<GroupTodo> list = new ArrayList<GroupTodo>();
		
		try {
			String sql = "select * from groups_todo_items where group_id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupId);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						GroupTodo groupTodo = new GroupTodo();
						
						groupTodo.setId(rs.getInt("id"));
						groupTodo.setGroupId(rs.getInt("group_id"));
						groupTodo.setAccountId(rs.getString("account_id"));
						groupTodo.setRegistrationAt(rs.getDate("registration_at"));
						groupTodo.setExpirationAt(rs.getDate("expiration_at"));
						groupTodo.setFinishedAt(rs.getDate("finished_at"));
						groupTodo.setTodoItem(rs.getString("todo_item"));
						groupTodo.setIsDeleted(rs.getInt("is_deleted"));
						groupTodo.setCreatedAt(rs.getTimestamp("created_at"));
						groupTodo.setUpdatedAt(rs.getTimestamp("updated_at"));
						
						list.add(groupTodo);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	//IDでグループTODOリストを１件検索して返す
	public GroupTodo findOne(Connection con, int id) {
		GroupTodo groupTodo = new GroupTodo();
		
		try {
			String sql = "select * from groups_todo_items where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, id);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						groupTodo.setId(rs.getInt("id"));
						groupTodo.setGroupId(rs.getInt("group_id"));
						groupTodo.setAccountId(rs.getString("account_id"));
						groupTodo.setRegistrationAt(rs.getDate("registration_at"));
						groupTodo.setExpirationAt(rs.getDate("expiration_at"));
						groupTodo.setFinishedAt(rs.getDate("finished_at"));
						groupTodo.setTodoItem(rs.getString("todo_item"));
						groupTodo.setIsDeleted(rs.getInt("is_deleted"));
						groupTodo.setCreatedAt(rs.getTimestamp("created_at"));
						groupTodo.setUpdatedAt(rs.getTimestamp("updated_at"));
					}else {
						groupTodo = null;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return groupTodo;
	}
	
	//グループTODOリストを１件追加する
	public boolean create(Connection con, GroupTodo groupTodo) {
		try {
			String sql = "insert into groups_todo_items (group_id, account_id, registration_at, expiration_at, finished_at, todo_item, is_deleted, created_at) value (?, ?, ?, ?, ?, ?, ?, ?)";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupTodo.getGroupId());
				pStmt.setString(2, groupTodo.getAccountId());
				pStmt.setDate(3, groupTodo.getRegistrationAt());
				pStmt.setDate(4, groupTodo.getExpirationAt());
				pStmt.setDate(5, groupTodo.getFinishedAt());
				pStmt.setString(6, groupTodo.getTodoItem());
				pStmt.setInt(7, groupTodo.getIsDeleted());
				pStmt.setTimestamp(8, groupTodo.getCreatedAt());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//グループTODOリストを１件更新する
	public boolean update(Connection con, GroupTodo groupTodo) {
		try {
			String sql = "update groups_todo_items set group_id = ?, registration_at = ?, expiration_at = ?, finished_at = ?, todo_item = ?, is_deleted = ?, updated_at = ? where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupTodo.getGroupId());
				pStmt.setDate(2, groupTodo.getRegistrationAt());
				pStmt.setDate(3, groupTodo.getExpirationAt());
				pStmt.setDate(4, groupTodo.getFinishedAt());
				pStmt.setString(5, groupTodo.getTodoItem());
				pStmt.setInt(6, groupTodo.getIsDeleted());
				pStmt.setTimestamp(7, groupTodo.getUpdatedAt());
				pStmt.setInt(8, groupTodo.getId());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//グループTODOリストのIDを１件更新する
	public boolean update(Connection con, String oldAccountId, String newAccountId) {
		try {
			String sql = "update groups_todo_items set account_id = ? where account_id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setString(1, newAccountId);
				pStmt.setString(2, oldAccountId);
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
}
