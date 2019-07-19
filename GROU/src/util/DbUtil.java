package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DB接続用Utilityクラス
 */
public class DbUtil {
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost/agroup", "keisuke", "keisuke");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
