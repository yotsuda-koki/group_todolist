package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GroupMemo;

public class GroupMemoDAO {

	//グループメモをグループIDで検索してリストに格納して返す
	public List<GroupMemo> findAll(Connection con, int groupId){
		List<GroupMemo> list = new ArrayList<GroupMemo>();
		
		try {
			String sql = "select * from groups_memo_items where group_id = ? ORDER BY created_at DESC";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupId);
				
				try(ResultSet rs = pStmt.executeQuery()){
				
					while(rs.next()) {
						GroupMemo groupMemo = new GroupMemo();
						
						groupMemo.setId(rs.getInt("id"));
						groupMemo.setGroupId(rs.getInt("group_id"));
						groupMemo.setAccountId(rs.getString("account_id"));
						groupMemo.setMemoItem(rs.getString("memo_item"));
						groupMemo.setIsDeleted(rs.getInt("is_deleted"));
						groupMemo.setCreatedAt(rs.getTimestamp("created_at"));
						groupMemo.setUpdatedAt(rs.getTimestamp("updated_at"));
						
						list.add(groupMemo);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	//IDでグループメモを１件検索して返す
	public GroupMemo findOne(Connection con, int id) {
		GroupMemo groupMemo = new GroupMemo();
		
		try {
			String sql = "select * from groups_memo_items where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, id);
				
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						groupMemo.setId(rs.getInt("id"));
						groupMemo.setGroupId(rs.getInt("group_id"));
						groupMemo.setAccountId(rs.getString("account_id"));
						groupMemo.setMemoItem(rs.getString("memo_item"));
						groupMemo.setIsDeleted(rs.getInt("is_deleted"));
						groupMemo.setCreatedAt(rs.getTimestamp("created_at"));
						groupMemo.setUpdatedAt(rs.getTimestamp("updated_at"));
					}else {
						groupMemo = null;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return groupMemo;
	}
	
	//グループメモを１件追加する
	public boolean create(Connection con, GroupMemo groupMemo) {
		try {
			String sql = "insert into groups_memo_items (group_id, account_id, memo_item, is_deleted, created_at) value (?, ?, ?, ?, ?)";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupMemo.getGroupId());
				pStmt.setString(2, groupMemo.getAccountId());
				pStmt.setString(3, groupMemo.getMemoItem());
				pStmt.setInt(4, groupMemo.getIsDeleted());
				pStmt.setTimestamp(5, groupMemo.getCreatedAt());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//グループメモを１件更新する
	public boolean update(Connection con, GroupMemo groupMemo) {
		try {
			String sql = "update groups_memo_items set is_deleted = ?, updated_at = ? where id = ?";
			
			try(PreparedStatement pStmt = con.prepareStatement(sql)){
				
				pStmt.setInt(1, groupMemo.getIsDeleted());
				pStmt.setTimestamp(2, groupMemo.getUpdatedAt());
				pStmt.setInt(3, groupMemo.getId());
				
				pStmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	//グループメモのIDを１件更新する
	public boolean update(Connection con, String oldAccountId, String newAccountId) {
		try {
			String sql = "update groups_memo_items set account_id = ? where account_id = ?";
			
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
