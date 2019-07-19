package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SessionInfo;
import entity.User_info;
import service.SalaryService;
import service.UserInfoService;
import util.ParamUtil;

@WebServlet("/userList")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String selectButton = request.getParameter("selectButton");
		SessionInfo sessionInfo = ParamUtil.getSessionInfo(request.getSession());

		request.getSession().setAttribute("sessionInfo", sessionInfo);

		//ユーザ一覧画面（管理者）の「編集する」を押した時の処理
		if(selectButton.equals("timeEdit")) {

			UserInfoService userInfoService = new UserInfoService();
			List<User_info> listUser = userInfoService.findAll();

			sessionInfo.setResisterUserList(listUser);

			request.getRequestDispatcher("userListByManager.jsp").forward(request, response);
			return;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//パラメーター取得
		request.setCharacterEncoding("UTF-8");

		String selectYearAndMonth = request.getParameter("selectYearAndMonth");
		String selectSalaryCalculation = request.getParameter("selectSalaryCalculation");
		String userList = request.getParameter("userList");
		String push = request.getParameter("push");

		//インスタンス取得
		SalaryService salaryService = new SalaryService();
		List<Integer> userIdList = new ArrayList<>();
		SessionInfo sessionInfo = ParamUtil.getSessionInfo(request.getSession());
		User_info ui = (User_info)request.getSession().getAttribute("userinfo");

		//テスト
			System.out.println(push);
		//-----

		if(ParamUtil.isNullOrEmpty(push)) {

			if(ParamUtil.isNullOrEmpty(selectYearAndMonth)) {

				request.setAttribute("uError", "「決定」ボタンを押してください");

			} else if(!ParamUtil.isNullOrEmpty(selectYearAndMonth)) {

				if( ui.getRole_id() == 1) {

				String yearStr = request.getParameter("year");
				String monthStr = request.getParameter("month");
				Integer year = ParamUtil.checkAndParseInt(yearStr);
				Integer month = ParamUtil.checkAndParseInt(monthStr);

				//テスト
				System.out.println("決定ボタンが押されました");
				System.out.println("その時に取得した値/"+ year +"/" + month);
				//-----

				request.setAttribute("year", year);
				request.setAttribute("month", month);
				request.setAttribute("push", "push");

				}

			}

			if( ui.getRole_id() == 2) {

				String[] userIdStrList = request.getParameterValues("salaryResult");
				String yearStr = request.getParameter("year");
				String monthStr = request.getParameter("month");
				List<Integer> userIdIntegerList = ParamUtil.getListByCheckbox(userIdStrList);
				Integer year = ParamUtil.checkAndParseInt(yearStr);
				Integer month = ParamUtil.checkAndParseInt(monthStr);
				List<String> salaryResult = salaryService.getSalaryResultByUserIdListAndYearAndMonth(userIdIntegerList, year, month);

				//テスト
				System.out.println("決定ボタンが押されました");
				System.out.println("その時に取得した値/"+ year +"/" + month);
				for(String a: userIdStrList) {
				System.out.println(a);
			}

			for(String a: salaryResult) {
				System.out.println(a);
			}
			//-----

			//給与計算結果を「○」「×」で表示
			for(int i = 0 ; i < salaryResult.size() ; i++) {
				sessionInfo.getResisterUserList().get(i).setSalaryResult(salaryResult.get(i));
			}

			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("push", "push");

			}

		}

		if(ui.getRole_id() == 2) {

			String[] userIdStrList = request.getParameterValues("salaryResult");
			String yearStr = request.getParameter("year");
			String monthStr = request.getParameter("month");
			List<Integer> userIdIntegerList = ParamUtil.getListByCheckbox(userIdStrList);
			Integer year = ParamUtil.checkAndParseInt(yearStr);
			Integer month = ParamUtil.checkAndParseInt(monthStr);
			List<String> salaryResult = salaryService.getSalaryResultByUserIdListAndYearAndMonth(userIdIntegerList, year, month);

			//テスト
			System.out.println("決定ボタンが押されました");
			System.out.println("その時に取得した値/"+ year +"/" + month);
			for(String a: userIdStrList) {
				System.out.println(a);
			}
			for(String a: salaryResult) {
				System.out.println(a);
			}
			//-----

			//給与計算結果を「○」「×」で表示
			for(int i = 0 ; i < salaryResult.size() ; i++) {

				sessionInfo.getResisterUserList().get(i).setSalaryResult(salaryResult.get(i));

			}

			request.setAttribute("year", year);
			request.setAttribute("month", month);

		}

		//checkboxからuserIdを取得して給与計算する際の処理
		if(!ParamUtil.isNullOrEmpty(selectSalaryCalculation)) {

			String[] checkbox = request.getParameterValues("checkbox");

			if(ParamUtil.isNullOrEmptyForObject(checkbox)) {
				request.setAttribute("salaryError","チェックボックスにチェックを入れてください");
				request.getRequestDispatcher("userListByAccounting.jsp").forward(request, response);
			}

			String hiddenYear = request.getParameter("hiddenYear");
			String hiddenMonth = request.getParameter("hiddenMonth");
			Integer hiddenYearInteger = ParamUtil.checkAndParseInt(hiddenYear);
			Integer hiddenMonthInteger = ParamUtil.checkAndParseInt(hiddenMonth);

			userIdList = ParamUtil.getListByCheckbox(checkbox);

			//テスト
			System.out.println("↓チェックボックスにチェックを入れた後に取得した情報↓");
			for(Integer a : userIdList) {
				System.out.println(a);
			}
			System.out.println(hiddenYearInteger + "/" + hiddenMonthInteger + "/");
			//-----

			if(salaryService.findByUserIdListAndYearAndMonth(userIdList, hiddenYearInteger, hiddenMonthInteger)) {

				request.setAttribute("salaryError", "いずれかのユーザの給与データが無いので計算できません。");
				request.setAttribute("salaryErrorLater","給与データがないユーザは「計算する」を押して計算処理してください");

			} else if (!salaryService.findByUserIdListAndYearAndMonth(userIdList, hiddenYearInteger, hiddenMonthInteger) &&
					    !ParamUtil.isNullOrEmpty(hiddenYear) && !ParamUtil.isNullOrEmpty(hiddenMonth)) {

				salaryService.updateByUserIdFromUserListAndYearAndMonth(userIdList, hiddenYearInteger, hiddenMonthInteger);
				request.setAttribute("salaryComplete","給与計算が完了しました");

			}

			request.getRequestDispatcher("userListByAccounting.jsp").forward(request, response);

		}

		//権限に応じて遷移する画面を切り替える処理
		if(userList.equals("manager")) {

			request.getRequestDispatcher("userListByManager.jsp").forward(request, response);

		} else if(userList.equals("accounting")) {

			request.getRequestDispatcher("userListByAccounting.jsp").forward(request, response);

		}

	}
}
