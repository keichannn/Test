package entity;


//権限テーブル
public class Role {

	//フィールド
	private Integer role_id;
	private String role_name;

	//コンストラクター

	public Role() {}

	public Role(Integer role_id, String role_name) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
	}
	//セッター、ゲッター
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
}
