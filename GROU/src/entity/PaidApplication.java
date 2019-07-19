package entity;

import java.sql.Date;

//有給申請テーブル
public class PaidApplication {

	//フィールド
	//LocalTime = タイムゾーンなし、時間のみ
	//LocalDate = タイムゾーンなし、日付のみ
	//LocalDateTime = タイムゾーンなし、日付と時間
	private Integer application_id;
	private Date appli_date;
	private Integer user_id;
	private Date preferred_date;
	private String remarks;
	private Integer appli_states;

	//コンストラクター

	public PaidApplication() {}

	public PaidApplication(Integer application_id, Date appli_date, Integer user_id, Date preferred_date,String remarks,
			Integer appli_states) {
		super();
		this.application_id = application_id;
		this.appli_date = appli_date;
		this.user_id = user_id;
		this.preferred_date = preferred_date;
		this.remarks = remarks;
		this.appli_states = appli_states;
	}

	//有給申請日の取得用
	public PaidApplication(Date preferred_date) {
		this.preferred_date = preferred_date;
	}

	public PaidApplication(int appli_states) {
		this.appli_states = appli_states;
	}

	//セッター、ゲッター
	public Integer getApplication_id() {
		return application_id;
	}
	public void setApplication_id(Integer application_id) {
		this.application_id = application_id;
	}
	public Date getAppli_date() {
		return appli_date;
	}
	public void setAppli_date(Date appli_date) {
		this.appli_date = appli_date;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Date getPreferred_date() {
		return preferred_date;
	}
	public void setPreferred_date(Date preferred_date) {
		this.preferred_date = preferred_date;
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

}
