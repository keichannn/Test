package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Attendance;
import entity.Salary;
import entity.User_info;
import service.AttendanceService;
import service.SalaryService;
import service.UserInfoService;
import util.ParamUtil;

@WebServlet("/userListOfSelectLink")
public class UserListOfSelectLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		//パラメーター取得
		String userIdBySyuttaikinjoukyou = request.getParameter("syuttaikinjoukyou");
		String userIdBySalaryCalculation = request.getParameter("salaryCalculation");
		String userIdBySalaryCalculationAction = request.getParameter("salaryCalculationAction");
		String userIdByUserTimeEdit = request.getParameter("userTimeEdit");
		String userIdByMeisai = request.getParameter("meisai");
		String yearStr = request.getParameter("hiddenYearStr");
		String monthStr = request.getParameter("hiddenMonthStr");
		Integer year = ParamUtil.checkAndParseInt(yearStr);
		Integer month = ParamUtil.checkAndParseInt(monthStr);
		String push = request.getParameter("push");

		//テスト
		System.out.println(push);
		//-----

		//サービスクラス取得
		SalaryService salaryService = new SalaryService();
		UserInfoService userInfoService = new UserInfoService();
		AttendanceService attendanceService = new AttendanceService();

		//ユーザ一覧画面の「出退勤状況」を押した時の処理
		if (!ParamUtil.isNullOrEmpty(userIdBySyuttaikinjoukyou)) {

			Integer integerOfUserId = ParamUtil.checkAndParseInt(userIdBySyuttaikinjoukyou);
			List<Attendance> attendanceList = attendanceService.findByYearAndMonth2(integerOfUserId, year, month);
			User_info userInfo = userInfoService.findByUserId(integerOfUserId);

			if (ParamUtil.isNullOrEmptyForObject(attendanceList)) {

				request.setAttribute("uError", "「決定」ボタンを押していないか、出退勤データがありません");
				request.getRequestDispatcher("userListByAccounting.jsp").forward(request, response);

			}

			//テスト
			System.out.println("↓ユーザ一覧画面の「出退勤状況」を押した時に取得した情報↓");
			System.out.println(integerOfUserId);
			//-----

			request.setAttribute("u_Id", integerOfUserId);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("user", userInfo.getUser_name());
			request.setAttribute("attendanceList", attendanceList);
			request.getRequestDispatcher("syuttaikinjoukyouByUserList.jsp").forward(request, response);

			//ユーザ一覧画面の「給与計算」を押した時の処理
		} else if (!ParamUtil.isNullOrEmpty(userIdBySalaryCalculation)) {

			Integer userId = ParamUtil.checkAndParseInt(userIdBySalaryCalculation);
			Salary salary = salaryService.findByIdAndYearAndMonth(userId, year, month);

			//テスト
			System.out.println("↓ユーザ一覧画面の「給与計算」を押した時に取得した情報↓");
			System.out.print(userId + "//" + year + "//" + month);
			//-----

			if (ParamUtil.isNullOrEmpty(push)) {

				request.setAttribute("uError", "「決定」ボタンが押されていません");
				request.getRequestDispatcher("userListByAccounting.jsp").forward(request, response);

			} else if(ParamUtil.isNullOrEmptyForObject(salary)) {

				salaryService.insertAutoByUserIdAndYearAndMonth(userId, year, month);
				salary = salaryService.findByIdAndYearAndMonth(userId, year, month);

			}

			//テスト
				System.out.print(
					salary.getBasic() + "//" + salary.getOvertime_work() + "//" + salary.getDeduction_amount() + "//");
			//-----

			request.setAttribute("isNotError", "テスト");
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.getSession().setAttribute("userInfo", userInfoService.findByUserId(userId));
			request.getSession().setAttribute("salaryConfirm", salary);
			request.getSession().setAttribute("salaryCalculationResult",
					salary.getBasic() + salary.getOvertime_work() - salary.getDeduction_amount());
			request.getRequestDispatcher("salaryCalculation.jsp").forward(request, response);

			//給与計算画面の「確定」を押した時の処理
		} else if (!ParamUtil.isNullOrEmpty(userIdBySalaryCalculationAction)) {

			Integer userId = ParamUtil.checkAndParseInt(userIdBySalaryCalculationAction);

			//テスト
			System.out.println("↓給与計算画面での「確定」ボタンを押したときに取得した値↓");
			System.out.println(userId+"/"+year+"/"+month);
			salaryService.updateByUserId(userId, year, month);

			request.setAttribute("complete", "支給額を登録しました");
			request.getRequestDispatcher("salaryCalculation.jsp").forward(request, response);

			//ユーザ一覧画面の「編集する」を押した時の処理
		} else if (!ParamUtil.isNullOrEmpty(userIdByUserTimeEdit)) {

			Integer userId = ParamUtil.checkAndParseInt(userIdByUserTimeEdit);

			//テスト
			System.out.println("↓ユーザ一覧画面の「時刻編集」を押した時に取得した情報↓");
			System.out.print(userId);
			System.out.print(year);
			System.out.print(month);
			//-----

			Attendance attendance = attendanceService.findByYearAndMonth(userId, year, month);

			if (ParamUtil.isNullOrEmptyForObject(attendance)) {
				request.setAttribute("userId", userId);
				request.setAttribute("error", "「決定」ボタンを押していないか、出退勤データがありません");
				request.getRequestDispatcher("userListByManager.jsp").forward(request, response);
			}

			request.getSession().setAttribute("attendanceInformation", attendance);
			request.getRequestDispatcher("userTimeEdit.jsp").forward(request, response);

			//ユーザ一覧画面の「明細を見る」を押した時の処理
		} else if (!ParamUtil.isNullOrEmptyForObject(userIdByMeisai)) {

			Integer userId = ParamUtil.checkAndParseInt(userIdByMeisai);
			Salary salary = salaryService.findByIdAndYearAndMonth(userId, year, month);

			if (ParamUtil.isNullOrEmptyForObject(salary)) {

				request.setAttribute("userId", userId);
				request.setAttribute("error", "「決定」ボタンを押していないか、給与データがありません");
				request.getRequestDispatcher("userListByManager.jsp").forward(request, response);

			}

			//テスト
			System.out.println("↓ユーザ一覧画面の「給与明細」を押した時に取得した情報↓");
			System.out.print(userId);
			System.out.print(year);
			System.out.print(month);
			//-----

			HttpSession session = request.getSession();
			session.setAttribute("yearMeisai", year);

			request.setAttribute("payMonth", month);
			request.setAttribute("salary", salary);
			request.getRequestDispatcher("meisai.jsp").forward(request, response);

		}

	}

}
