package datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import settings.DatebaseSettings;

public class DBconnection implements AutoCloseable {
	
	
	
	private Connection con;
	
	
	public DBconnection() throws ClassNotFoundException , SQLException{
		
		//ドライバーの読み込み
		Class.forName(DatebaseSettings.DRIVER_NAME);
		
		//データベースコネクションをフィールドに保存
		this.con = DriverManager.getConnection(DatebaseSettings.URL, DatebaseSettings.USER, DatebaseSettings.PASS);
			
		
	}

	
	public Connection getInstance() throws SQLException, ClassNotFoundException {
		//データベースコネクションを返却
		return this.con;
	}
	
	
	//データベースコネクションを閉じる
	@Override
	public void close() {
		try {
			this.con.close();
		} catch (Exception e) {
			
		}
	}


}

