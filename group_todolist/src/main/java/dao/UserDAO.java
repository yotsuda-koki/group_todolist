package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import model.User;
import settings.DatebaseSettings;

public class UserDAO {

    // パスワードをハッシュ化する
    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // ユーザー作成（パスワードをハッシュ化して保存）
    public int create(Connection con, User user) {
        try {
            String sql = "INSERT INTO users (email, account_id, password, user_name, is_deleted, created_at) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pStmt = con.prepareStatement(sql)) {
                pStmt.setString(1, user.getEmail());
                pStmt.setString(2, user.getAccountId());
                pStmt.setString(3, hashPassword(user.getPassword()));
                pStmt.setString(4, user.getUserName());
                pStmt.setInt(5, user.getIsDeleted());
                pStmt.setTimestamp(6, user.getCreatedAt());

                pStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getErrorCode();
        }
        return DatebaseSettings.SUCCESS;
    }
    
    // ユーザーを全検索してリストに格納して返す
    public List<User> findAll(Connection con) {
        List<User> list = new ArrayList<>();

        try {
            String sql = "SELECT id, email, account_id, user_name, is_deleted, created_at, updated_at FROM users";

            try (PreparedStatement pStmt = con.prepareStatement(sql);
                 ResultSet rs = pStmt.executeQuery()) {

                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setAccountId(rs.getString("account_id"));
                    user.setUserName(rs.getString("user_name"));
                    user.setIsDeleted(rs.getInt("is_deleted"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    user.setUpdatedAt(rs.getTimestamp("updated_at"));

                    list.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ユーザー一覧の取得に失敗しました: " + e.getMessage());
            return new ArrayList<>(); 
        }

        return list;
    }


    // ユーザー検索（ログイン用)
    public User findOne(Connection con, String emailOrAccountId, String plainPassword) {
        User user = null;

        try {
            String sql = "SELECT * FROM users WHERE email = ? OR account_id = ?";

            try (PreparedStatement pStmt = con.prepareStatement(sql)) {
                pStmt.setString(1, emailOrAccountId);
                pStmt.setString(2, emailOrAccountId);

                try (ResultSet rs = pStmt.executeQuery()) {
                    if (rs.next()) {
                        String hashedPassword = rs.getString("password");


                        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
                            user = new User();
                            user.setId(rs.getInt("id"));
                            user.setEmail(rs.getString("email"));
                            user.setAccountId(rs.getString("account_id"));
                            user.setPassword(hashedPassword); 
                            user.setUserName(rs.getString("user_name"));
                            user.setIsDeleted(rs.getInt("is_deleted"));
                            user.setCreatedAt(rs.getTimestamp("created_at"));
                            user.setUpdatedAt(rs.getTimestamp("updated_at"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }
    
    // ID でユーザーを 1 件検索して返す
    public User findOne(Connection con, int id) {
        User user = null;

        try {
            String sql = "SELECT id, email, account_id, password, user_name, is_deleted, created_at, updated_at FROM users WHERE id = ?";
            
            try (PreparedStatement pStmt = con.prepareStatement(sql)) {
                pStmt.setInt(1, id);

                try (ResultSet rs = pStmt.executeQuery()) {
                    if (rs.next()) {
                        user = new User();
                        user.setId(rs.getInt("id"));
                        user.setEmail(rs.getString("email"));
                        user.setAccountId(rs.getString("account_id"));
                        user.setPassword(rs.getString("password"));
                        user.setUserName(rs.getString("user_name"));
                        user.setIsDeleted(rs.getInt("is_deleted"));
                        user.setCreatedAt(rs.getTimestamp("created_at"));
                        user.setUpdatedAt(rs.getTimestamp("updated_at"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ユーザー取得時にエラーが発生しました: " + e.getMessage());
        }

        return user;
    }


    // パスワード変更（ハッシュ化して更新）
    public int updatePassword(Connection con, int userId, String newPassword) {
        try {
        	
        	// すでにハッシュ化されていない場合のみハッシュ化
            if (!newPassword.startsWith("$2a$")) {
                newPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt()); 
            }
        	
            String sql = "UPDATE users SET password = ? WHERE id = ?";

            try (PreparedStatement pStmt = con.prepareStatement(sql)) {
                pStmt.setString(1, newPassword);
                pStmt.setInt(2, userId);

                pStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getErrorCode();
        }
        return DatebaseSettings.SUCCESS;
    }

    // ユーザーをIDで検索して１件更新する
    public int update(Connection con, User user) {
        try {
            String sql = "UPDATE users SET email = ?, account_id = ?, user_name = ?, is_deleted = ? WHERE id = ?";

            try (PreparedStatement pStmt = con.prepareStatement(sql)) {
                pStmt.setString(1, user.getEmail());
                pStmt.setString(2, user.getAccountId());
                pStmt.setString(3, user.getUserName());
                pStmt.setInt(4, user.getIsDeleted());
                pStmt.setInt(5, user.getId());

                pStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getErrorCode();
        }
        return DatebaseSettings.SUCCESS;
    }
    
    // ユーザーIDのリストを基にユーザーを取得する
    public List<User> findUsersByIds(Connection con, List<Integer> userIds) {
        List<User> userList = new ArrayList<>();
        
        if (userIds == null || userIds.isEmpty()) {
            return userList;
        }

        try {
            StringBuilder placeholders = new StringBuilder();
            for (int i = 0; i < userIds.size(); i++) {
                placeholders.append("?");
                if (i < userIds.size() - 1) {
                    placeholders.append(",");
                }
            }

            String sql = "SELECT * FROM users WHERE id IN (" + placeholders.toString() + ")";

            try (PreparedStatement pStmt = con.prepareStatement(sql)) {
                // プレースホルダーにユーザーIDをセット
                for (int i = 0; i < userIds.size(); i++) {
                    pStmt.setInt(i + 1, userIds.get(i));
                }

                try (ResultSet rs = pStmt.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setEmail(rs.getString("email"));
                        user.setAccountId(rs.getString("account_id"));
                        user.setUserName(rs.getString("user_name"));
                        user.setIsDeleted(rs.getInt("is_deleted"));
                        user.setCreatedAt(rs.getTimestamp("created_at"));
                        user.setUpdatedAt(rs.getTimestamp("updated_at"));

                        userList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
