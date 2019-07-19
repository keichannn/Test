package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Role;

public class RoleDao {

    private final String SELECT_ALL = "SELECT * FROM role ORDER BY role_id";
    private final String SELECT_ID = "SELECT role_id, role_name FROM role WHERE role_id=?";
    private Connection connection;

    public RoleDao(Connection connection) {
        this.connection = connection;
    }

    //全件取得
    public List<Role> findAll() {
        ArrayList<Role> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

            	Role role = new Role(rs.getInt("role_id"),rs.getString("role_name"));

                list.add(role);

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return list;
    }

    public Role findByRoleId(Integer role_id) {

		Role role = null;

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ID)) {
			stmt.setInt(1, role_id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				role = new Role();
				role.setRole_name(rs.getString("role_name"));

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

		return role;

	}
}
