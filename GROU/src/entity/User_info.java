package entity;

import java.sql.Date;

//社員テーブル
public class User_info {

	//フィールド
	//LocalTime = タイムゾーンなし、時間のみ
	//LocalDate = タイムゾーンなし、日付のみ
	//LocalDateTime = タイムゾーンなし、日付と時間
	private Integer user_id;
	private String user_name;
	private String login_id;
	private String password;
	private Date birthday;
	private Date hiredate;
	private Integer basic;
	private Integer role_id;
	private String role_name;
	private Role role;
	private String salaryResult;

	//コンストラクター

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public void setBasic(Integer basic) {
		this.basic = basic;
	}


	public User_info() {}


	public User_info(String user_name, String login_id, String password, Date birthday) {
		this.user_name = user_name;
		this.login_id = login_id;
		this.password = password;
		this.birthday = birthday;
	}

	public User_info(String user_name, String login_id, String password, Date birthday,
			Date hiredate, Integer basic, Role role) {
		this(user_name, login_id, password, birthday);
		this.hiredate = hiredate;
		this.basic = basic;
		this.role = role;
	}

	public User_info(Integer user_id, String user_name, String login_id, String password, Date birthday,
			Date hiredate, Integer basic, Integer role_id) {

		this.user_id = user_id;
		this.user_name = user_name;
		this.login_id = login_id;
		this.password = password;
		this.birthday = birthday;
		this.hiredate = hiredate;
		this.basic = basic;
		this.role_id = role_id;
	}
//有給申請者を見るためのコンストラクター
	public User_info(String user_name) {
		this.user_name = user_name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	//セッター、ゲッター
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getBasic() {
		return basic;
	}

	public void setDasic(Integer dasic) {
		this.basic = dasic;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}


	public String getRole_name() {
		return role_name;
	}


	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getSalaryResult() {
		return salaryResult;
	}


	public void setSalaryResult(String salaryResult) {
		this.salaryResult = salaryResult;
	}

}
