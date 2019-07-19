package service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.UserInfoDao;
import entity.User_info;
import util.DbUtil;

public class UserInfoService {

	public UserInfoService() {}

	public List<User_info> findAll() {

		List<User_info> list = null;

		   try (Connection conn = DbUtil.getConnection()) {

			   UserInfoDao userInfoDao = new UserInfoDao(conn);
			   list = userInfoDao.findAll();
			   return list;

		   } catch (Exception e) {
	         e.printStackTrace();
		   }

		   return list;

	}

	public User_info findByUserId(Integer userId) {

		   try (Connection conn = DbUtil.getConnection()) {
			   UserInfoDao userInfoDao = new UserInfoDao(conn);

			   return userInfoDao.findByUserId(userId);

		   } catch (Exception e) {
	         e.printStackTrace();
		   }

		   return null;
	   }

	public void insert(String user_name, Date birthday, String login_id, String password, Date hiredate, Integer basic, String role_name) {
        try (Connection conn = DbUtil.getConnection()) {
            UserInfoDao userInfoDao = new UserInfoDao(conn);
            userInfoDao.insert(user_name, birthday, login_id, password, hiredate, basic, role_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	   public User_info findByLoginId(String login_id) {
	        try (Connection conn = DbUtil.getConnection()) {
	            UserInfoDao userInfoDao = new UserInfoDao(conn);
	            return userInfoDao.findByLoginId(login_id);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	   public boolean existsUserByLoginId(String login_id) {
	        return findByLoginId(login_id) != null;
	    }

	public boolean existsUserByLoginIdExcludingUserId(String login_id, int findByLoginIdExcludingUserId) {
	        try (Connection conn = DbUtil.getConnection()) {
	            UserInfoDao userInfoDao = new UserInfoDao(conn);
	            return userInfoDao.findByLoginIdExcludingUserId(login_id, findByLoginIdExcludingUserId) != null;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return false;
	    }

	public void update(User_info user_info) {
        try (Connection conn = DbUtil.getConnection()) {
            UserInfoDao userInfoDao = new UserInfoDao(conn);
            userInfoDao.update(user_info);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

	   public static List<User_info> find() {
		   List<User_info> list = new ArrayList<User_info>();

		   try (Connection conn = DbUtil.getConnection()) {
	        	UserInfoDao uid = new UserInfoDao(conn);
	            list = uid.find();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	   }

	/**
	* ログイン認証処理
	*/

	public User_info authentication(String id, String pass) {
		try (Connection conn = DbUtil.getConnection()) {
			UserInfoDao userInfoDao = new UserInfoDao(conn);
			User_info user = userInfoDao.findByLoginIdAndPassword(id, pass);

			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
