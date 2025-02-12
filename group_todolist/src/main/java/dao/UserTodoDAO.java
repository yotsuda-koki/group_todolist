package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserTodo;

public class UserTodoDAO {
	
	//ユーザーのTODOリストを全検索してリストに格納して返す
	public List<UserTodo> findAll(Connection con,int userId) {
		List<UserTodo> list = new ArrayList<UserTodo>();
		
		try {
			String sql = "select * from users_todo_items where user_id = ? order by expiration_at asc";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, userId);
				
				try(ResultSet rs = pStmt.executeQuery()){
					while(rs.next()) {
						UserTodo userTodo = new UserTodo();
						
						userTodo.setId(rs.getInt("id"));
						userTodo.setUserId(rs.getInt("user_id"));
						userTodo.setRegistrationAt(rs.getDate("registration_at"));
						userTodo.setExpirationAt(rs.getDate("expiration_at"));
						userTodo.setFinishedAt(rs.getDate("finished_at"));
						userTodo.setTodoItem(rs.getString("todo_item"));
						userTodo.setIsDeleted(rs.getInt("is_deleted"));
						userTodo.setCreatedAt(rs.getTimestamp("created_at"));
						userTodo.setUpdatedAt(rs.getTimestamp("updated_at"));
						
						list.add(userTodo);				
					}

				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	//IDでTODOリストを１件検索して返す
	public UserTodo findOne(Connection con, int id) {
		UserTodo userTodo = new UserTodo();
		
		try {
			String sql = "select * from users_todo_items where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, id);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						userTodo.setId(rs.getInt("id"));
						userTodo.setUserId(rs.getInt("user_id"));
						userTodo.setRegistrationAt(rs.getDate("registration_at"));
						userTodo.setExpirationAt(rs.getDate("expiration_at"));
						userTodo.setFinishedAt(rs.getDate("finished_at"));
						userTodo.setTodoItem(rs.getString("todo_item"));
						userTodo.setIsDeleted(rs.getInt("is_deleted"));
						userTodo.setCreatedAt(rs.getTimestamp("created_at"));
						userTodo.setUpdatedAt(rs.getTimestamp("updated_at"));
					}else {
						userTodo = null;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return userTodo;
	}
	
	//TODOを１件追加する
	public boolean create(Connection con, UserTodo userTodo) {
		try {
			String sql = "insert into users_todo_items (user_id, registration_at, expiration_at, todo_item, is_deleted, created_at) value (?, ?, ?, ?, ?, ?)";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, userTodo.getUserId());
				pStmt.setDate(2, userTodo.getRegistrationAt());
				pStmt.setDate(3, userTodo.getExpirationAt());
				pStmt.setString(4, userTodo.getTodoItem());
				pStmt.setInt(5, userTodo.getIsDeleted());
				pStmt.setTimestamp(6, userTodo.getCreatedAt());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//TODOをIDで検索して１件更新する
	public boolean update(Connection con, UserTodo userTodo) {
		try {
			String sql = "update users_todo_items set user_id = ?, registration_at = ?, expiration_at = ?, finished_at = ?, todo_item = ?, is_deleted = ?, updated_at = ? where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, userTodo.getUserId());
				pStmt.setDate(2, userTodo.getRegistrationAt());
				pStmt.setDate(3, userTodo.getExpirationAt());
				pStmt.setDate(4, userTodo.getFinishedAt());
				pStmt.setString(5, userTodo.getTodoItem());
				pStmt.setInt(6, userTodo.getIsDeleted());
				pStmt.setTimestamp(7, userTodo.getUpdatedAt());
				pStmt.setInt(8, userTodo.getId());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
}
