package entity;

import java.sql.Date;

//有給テーブル
public class Paid {

	//フィールド
	//LocalTime = タイムゾーンなし、時間のみ
	//LocalDate = タイムゾーンなし、日付のみ
	//LocalDateTime = タイムゾーンなし、日付と時間
	private Integer paid_id;
	private Date paid_date;
	private Integer user_id;
	private Integer usage_status;
	private Integer available_days;
	private Integer application_id;

	//残り有給用のフィールド
	private Integer remaining;

	//コンストラクター
	public Paid() {}

	//残り有給を見るためのコンストラクター
	public Paid(Integer remaining) {
		this.setRemaining(remaining);
	}

	public Paid(Integer paid_id, Date paid_date, Integer user_id, Integer usage_status, Integer available_days,
			Integer application_id) {
		super();
		this.paid_id = paid_id;
		this.paid_date = paid_date;
		this.user_id = user_id;
		this.usage_status = usage_status;
		this.available_days = available_days;
		this.application_id = application_id;
	}

	//セッター、ゲッター
	public Integer getPaid_id() {
		return paid_id;
	}

	public void setPaid_id(Integer paid_id) {
		this.paid_id = paid_id;
	}
	public Date getPaid_date() {
		return paid_date;
	}
	public void setPaid_date(Date paid_date) {
		this.paid_date = paid_date;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getUsage_status() {
		return usage_status;
	}
	public void setUsage_status(Integer usage_status) {
		this.usage_status = usage_status;
	}
	public Integer getAvailable_day() {
		return available_days;
	}
	public void setAvailable_day(Integer available_days) {
		this.available_days = available_days;
	}
	public Integer getApplication_id() {
		return application_id;
	}
	public void setApplication_id(Integer application_id) {
		this.application_id = application_id;
	}

	public Integer getRemaining() {
		return remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}
}
