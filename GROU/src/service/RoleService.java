package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.RoleDao;
import entity.Role;
import util.DbUtil;

public class RoleService {

    public List<Role> findAll() {

        List<Role> list = new ArrayList<>();

        try (Connection conn = DbUtil.getConnection()) {
        	RoleDao roleDao = new RoleDao(conn);
            list = roleDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Role> find() {

        List<Role> list = new ArrayList<Role>();

        try (Connection conn = DbUtil.getConnection()) {
        	RoleDao roleDao = new RoleDao(conn);
            list = roleDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public Role findByRoleId(Integer role_id) {

		   try (Connection conn = DbUtil.getConnection()) {
			   RoleDao roleDao = new RoleDao(conn);

			   return roleDao.findByRoleId(role_id);

		   } catch (Exception e) {
	         e.printStackTrace();
		   }

		   return null;
	   }
}
