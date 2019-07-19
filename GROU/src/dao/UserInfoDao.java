package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User_info;
import util.DbUtil;

public class UserInfoDao {

	private final String SELECT_ALL = "SELECT * FROM user_info ORDER BY user_id";
    private final String SELECT_BY_LOGIN_ID = "SELECT login_id FROM user_info WHERE login_id =?";
	private final String SELECT_ID = "SELECT * FROM user_info WHERE user_id=?";
    private final String UPDATE = "UPDATE user_info SET user_name = ?, birthday = TO_DATE(?,'YYYY/MM/DD'), login_id = ?, password = ? WHERE user_id = ?";
    private final String SELECT_BY_LOGIN_ID_EXCLUDING_USER_ID = "SELECT user_name, birthday, login_id, password, FROM user_info WHERE login_id = ? AND user_id <> ?";
    private final String SELECT_USER = "SELECT user_name, birthday, login_id, password, FROM user_info WHERE login_id = ?";
    private final String INSERT = "INSERT INTO user_info (user_name, birthday, login_id, password, hiredate, basic, role_id) VALUES (?,TO_DATE(?,'YYYY/MM/DD'),?,?,TO_DATE(?,'YYYY/MM/DD'),?,(SELECT max(role_id) FROM role WHERE role_name=?))";


	private Connection connection = DbUtil.getConnection();

	public UserInfoDao(Connection connection) {
		this.connection = connection;
	}

	public UserInfoDao() {

	}

	/**
	* user_id、passwordによる検索
	*/
	public User_info findByLoginIdAndPassword(String id, String pass) {
		try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_LOGIN_ID_AND_PASS)) {
			stmt.setString(1, id);
			stmt.setString(2, pass);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				return new User_info(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("login_id"),
						rs.getString("password"), rs.getDate("birthday"), rs.getDate("hiredate"),
						rs.getInt("basic"), rs.getInt("role_id"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static final String SELECT_BY_LOGIN_ID_AND_PASS = "SELECT * FROM user_info WHERE login_id = ? AND password = ?";


	/**
	 * 全件検索
	 */
	public List<User_info> findAlll() {
		List<User_info> list = new ArrayList<User_info>();

		try (Connection conn = DbUtil.getConnection()) {
			UserInfoDao userInfoDao = new UserInfoDao(conn);
			list = userInfoDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}



	//全件取得
	public List<User_info> findAll() {

		ArrayList<User_info> list = new ArrayList<>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User_info userInfo = new User_info(rs.getInt("user_id"), rs.getString("user_name"),
						rs.getString("login_id"), rs.getString("password"), rs.getDate("birthday"),
						rs.getDate("hiredate"), rs.getInt("basic"), rs.getInt("role_id"));

				list.add(userInfo);
			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}
		return list;
	}



    //ログインIDでチェック
	public User_info findByLoginId(String login_id) {

    	try(PreparedStatement stmt = connection.prepareStatement(SELECT_BY_LOGIN_ID)) {
    		stmt.setString(1, login_id);

    		ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User_info u = new User_info();
				return u;
			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
    	}
		return null;
    }

	//user_idによる検索
	public List<User_info> findId(int user_id) {
		ArrayList<User_info> list = new ArrayList<>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ID)) {
			stmt.setInt(1, user_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				User_info userInfo = new User_info(rs.getInt("user_id"), rs.getString("user_name"),
						rs.getString("login_id"), rs.getString("password"), rs.getDate("birthday"),
						rs.getDate("hiredate"), rs.getInt("basic"), rs.getInt("role_id"));

				list.add(userInfo);

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

		return list;

	}


	public User_info findByUserId(Integer userId) {

		User_info userInfo = null;

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ID)) {
			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				userInfo = new User_info();
				userInfo.setUser_name(rs.getString("user_name"));

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

		return userInfo;

	}

    //ユーザ登録
	public void insert(String user_name, Date birthday, String login_id, String password, Date hiredate, Integer basic, String role_name) {
		try (PreparedStatement stmt = connection.prepareStatement(INSERT)) {
			stmt.setString(1, user_name);
	        stmt.setDate(2, birthday);
	        stmt.setString(3, login_id);
	        stmt.setString(4, password);
	        stmt.setDate(5, hiredate);
	        stmt.setInt(6, basic);
	        stmt.setString(7, role_name);

	        stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //ユーザ変更
	public User_info findByLoginIdExcludingUserId(String login_id, int excludingUser_id) {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_LOGIN_ID_EXCLUDING_USER_ID)) {
            stmt.setString(1, login_id);
            stmt.setInt(2, excludingUser_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User_info u = new User_info(
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("login_id"),
                        rs.getDate("birthday"));

                return u;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

	public void update(User_info user_info) {
		try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
			stmt.setString(1, user_info.getUser_name());
			stmt.setDate(2, user_info.getBirthday());
			stmt.setString(3, user_info.getLogin_id());
			stmt.setString(4, user_info.getPassword());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<User_info> find(){
		ArrayList<User_info> list = new ArrayList<>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_USER)){
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User_info ui = new User_info();
				ui.setUser_name(rs.getString("user_name"));
				ui.setBirthday(rs.getDate("birthday"));
				ui.setLogin_id(rs.getString("login_id"));
				ui.setPassword(rs.getString("password"));
				list.add(ui);
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
}
