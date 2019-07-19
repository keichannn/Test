package entity;

public class JoinUserApp {

	private String user_name;
	private Integer appli_states;
	private String remarks;
	private Integer user_id;

	public JoinUserApp(String user_name, Integer appli_states, String remarks, Integer user_id) {
		super();
		this.user_name = user_name;
		this.appli_states = appli_states;
		this.remarks = remarks;
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getAppli_states() {
		return appli_states;
	}

	public void setAppli_states(Integer appli_states) {
		this.appli_states = appli_states;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
