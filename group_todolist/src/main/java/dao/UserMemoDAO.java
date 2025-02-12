package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserMemo;

public class UserMemoDAO {
	
	//ユーザーIDでメモを検索してリストに格納して返す
	public List<UserMemo> findAll(Connection con, int userId){
		List<UserMemo> list = new ArrayList<UserMemo>();
		
		
		try {
			String sql = "select * from users_memo_items where user_id = ? ORDER BY created_at DESC";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, userId);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						
						UserMemo userMemo = new UserMemo();
						
						userMemo.setId(rs.getInt("id"));
						userMemo.setUserId(rs.getInt("user_id"));
						userMemo.setMemoItem(rs.getString("memo_item"));
						userMemo.setIsDeleted(rs.getInt("is_deleted"));
						userMemo.setCreatedAt(rs.getTimestamp("created_at"));
						userMemo.setUpdatedAt(rs.getTimestamp("updated_at"));
						
						list.add(userMemo);
					}
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	//メモを１件検索して返す
	public UserMemo findOne(Connection con, int id) {
		UserMemo userMemo = new UserMemo();
		
		try {
			String sql = "select * from users_memo_items where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, id);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						userMemo.setId(rs.getInt("id"));
						userMemo.setUserId(rs.getInt("user_id"));
						userMemo.setMemoItem(rs.getString("memo_item"));
						userMemo.setIsDeleted(rs.getInt("is_deleted"));
						userMemo.setCreatedAt(rs.getTimestamp("created_at"));
						userMemo.setUpdatedAt(rs.getTimestamp("updated_at"));
					}else {
						userMemo = null;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return userMemo;
	}
	
	//メモを１件追加する
	public boolean create(Connection con,UserMemo userMemo) {
		try {
			String sql = "insert into users_memo_items (user_id, memo_item, is_deleted, created_at) value (?, ?, ?, ?)";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, userMemo.getUserId());
				pStmt.setString(2, userMemo.getMemoItem());
				pStmt.setInt(3, userMemo.getIsDeleted());
				pStmt.setTimestamp(4, userMemo.getCreatedAt());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//メモを１件更新する
	public boolean update(Connection con,UserMemo userMemo) {
		try {
			String sql = "update users_memo_items set is_deleted = ?, updated_at = ? WHERE ID = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, userMemo.getIsDeleted());
				pStmt.setTimestamp(2, userMemo.getUpdatedAt());
				pStmt.setInt(3, userMemo.getId());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
}
