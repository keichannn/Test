package entity;

//出退勤テーブル
public class Attendance {

	//フィールド
	private Integer user_id;
	private Integer year;
	private Integer month;
	private Integer day;
	private String week;
	private String going_work;
	private String leaving_work;
	private String break_start;
	private Integer christian;

	//コンストラクター

	public Attendance() {}



	public Attendance(Integer user_id, Integer month, Integer day, String week, String going_work,
			String leaving_work, String break_start, Integer christian) {
		super();
		this.user_id = user_id;
		this.month = month;
		this.day = day;
		this.week = week;
		this.going_work = going_work;
		this.leaving_work = leaving_work;
		this.break_start = break_start;
		this.christian = christian;
	}



	public Attendance(Integer user_id, Integer year, Integer month, Integer day, String week, String going_work, String leaving_work,
			String break_start,Integer christian) {

		this(user_id, month, day,week, going_work,leaving_work, break_start,christian);
		this.year = year;

	}

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

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getGoing_work() {
		return going_work;
	}

	public void setGoing_work(String going_work) {
		this.going_work = going_work;
	}

	public String getLeaving_work() {
		return leaving_work;
	}

	public void setLeaving_work(String leaving_work) {
		this.leaving_work = leaving_work;
	}

	public String getBreak_start() {
		return break_start;
	}

	public void setBreak_start(String break_start) {
		this.break_start = break_start;
	}
	public Integer getChristian() {
		return christian;
	}

	public void setChristian(Integer christian) {
		this.christian = christian;
	}
}
