package entity;

import java.util.List;

/**
 * セッション情報を纏めて管理するためのクラス
 */

public class SessionInfo {
	List<Attendance> attendanceList;//出退勤状況一覧

	private User_info resisterUser;
	private Role login;

	private List<User_info> resisterUserList;
	private List<Role> role;
	private List<Role> roleList;
	private List<String> salaryResult;

	private User_info registerUser;
	private User_info prevUpdateUser;
	private User_info updateUser;



	//ゲッター及びセッター

	public void setRegisterUser(User_info _registerUser) {
		registerUser = _registerUser;
	}

	public User_info getRegisterUser() {
		return registerUser;
	}

	public void setPrevUpdateUser(User_info _prevUpdateUser) {
		prevUpdateUser = _prevUpdateUser;
	}

	public User_info getPrevUpdateUser() {
		return prevUpdateUser;
	}

	public void setUpdateUser(User_info _updateUser) {
		updateUser = _updateUser;
	}

	public User_info getUpdateUser() {
		return updateUser;
	}

	public void setRoleList(List<Role> _roleList) {
		roleList = _roleList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<Attendance> _attendanceList) {
		this.attendanceList = _attendanceList;
	}

	public List<String> getSalaryResult() {
		return salaryResult;
	}

	public void setSalaryResult(List<String> salaryResult) {
		this.salaryResult = salaryResult;
	}

	public User_info getResisterUser() {
		return resisterUser;
	}

	public void setResisterUser(User_info resisterUser) {
		this.resisterUser = resisterUser;
	}

	public List<User_info> getResisterUserList() {
		return resisterUserList;
	}

	public void setResisterUserList(List<User_info> resisterUserList) {
		this.resisterUserList = resisterUserList;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public Role getLogin() {
		return login;
	}

	public void setLogin(Role login) {
		this.login = login;
	}

}
