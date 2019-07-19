package entity;

//給与テーブル
public class Salary {

	//フィールド
	private Integer user_id;
	private Integer year;
	private Integer month;
	private Integer basic;
	private Integer overtime_work;
	private Integer deduction_amount;
	private Integer payment_amount;

	//コンストラクター

	public Salary() {}

	public Salary(Integer user_id, Integer year, Integer month, Integer basic, Integer overtime_work,
			Integer deduction_amount, Integer payment_amount) {
		super();
		this.user_id = user_id;
		this.year = year;
		this.month = month;
		this.basic = basic;
		this.overtime_work = overtime_work;
		this.deduction_amount = deduction_amount;
		this.payment_amount = payment_amount;
	}

	//セッター、ゲッター
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getBasic() {
		return basic;
	}

	public void setBasic(Integer basic) {
		this.basic = basic;
	}

	public Integer getOvertime_work() {
		return overtime_work;
	}

	public void setOvertime_work(Integer overtime_work) {
		this.overtime_work = overtime_work;
	}

	public Integer getDeduction_amount() {
		return deduction_amount;
	}

	public void setDeduction_amount(Integer deduction_amount) {
		this.deduction_amount = deduction_amount;
	}

	public Integer getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(Integer payment_amount) {
		this.payment_amount = payment_amount;
	}

}
