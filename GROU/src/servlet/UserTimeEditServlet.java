package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Attendance;
import service.UserTimeEditService;
import util.ParamUtil;

@WebServlet("/userTimeEditServlet")
public class UserTimeEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//選択されたユーザのidを取得
		Attendance att = (Attendance) session.getAttribute("attendanceInformation");
		System.out.println("test : " + att.getUser_id());
		System.out.println("test : " + att.getDay());
		int userId = att.getUser_id();

		//年
		String sYear = request.getParameter("year");
		int year = Integer.parseInt(sYear);
		//月
		String sMonth = request.getParameter("month");
		int month = Integer.parseInt(sMonth);
		//日
		String sDay = request.getParameter("day");
		int day = Integer.parseInt(sDay);
		//休憩時間(分)
		String breakTime = request.getParameter("breakTime");
		if(!ParamUtil.isNullOrEmpty(breakTime)) {
			breakTime = "60";
		}
		//出勤時間の時
		String sStartHour = request.getParameter("startTime1");
		//出勤時間の時が未入力なら9:00にして、入力されていたらそれを入れる
		int startHour = 9;
		if(!ParamUtil.isNullOrEmpty(sStartHour)) {
			startHour = Integer.parseInt(sStartHour);
		}
		//出勤時間の分
		//出勤時間の時が未入力なら9:00にして、入力されていたらそれを入れる
		String sStartMin = request.getParameter("startTime2");
		int startMin = 0;
		if(!ParamUtil.isNullOrEmpty(sStartMin)) {
			startMin = Integer.parseInt(sStartMin);
		}
		//退勤時間の時
		String sEndHour = request.getParameter("endTime1");
		int endHour = 18;
		if(!ParamUtil.isNullOrEmpty(sEndHour)) {
			endHour = Integer.parseInt(sEndHour);
		}
		//退勤時間の分
		String sEndMin = request.getParameter("endTime2");
		int endMin = 0;
		if(!ParamUtil.isNullOrEmpty(sEndMin)) {
			endMin = Integer.parseInt(sEndMin);
		}

		Calendar cal = Calendar.getInstance();

		if(startHour > 24 || endHour > 24 || startMin > 59 || endMin > 59) {
			request.setAttribute("message", "時間が正しくありません。");
			request.getRequestDispatcher("/userTimeEdit.jsp").forward(request, response);
		}

		String goingWork = ParamUtil.getStringDate(startHour, startMin);
		String leavingWork = ParamUtil.getStringDate(endHour, endMin);

		cal.setLenient(true);
		cal.set(year, month - 1, day);

		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		String[] week_name = { "日", "月", "火", "水", "木", "金", "土" };
//		UserTimeEditDao uted = new UserTimeEditDao();
		UserTimeEditService utes = new UserTimeEditService();

		utes.userTimeEdit(userId, year, month, day, week_name[week], goingWork, leavingWork, breakTime);
		request.getRequestDispatcher("/infomationChange.jsp").forward(request, response);
	}
}
