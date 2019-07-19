package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.SessionInfo;
import entity.User_info;
import service.UserInfoService;
import util.ParamUtil;

@WebServlet("/UserChangeServlet")
public class UserChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String user_name = request.getParameter("user_name");
		String birthday = request.getParameter("birthday");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);
		User_info updateUser = sessionInfo.getUpdateUser();

		boolean hasError = false;

		if (ParamUtil.isNullOrEmpty(user_name)) {
            request.setAttribute("nameErrMsg", "名前は必須です");
            hasError = true;
        } else {
            updateUser.setUser_name(user_name);
        }

		if (ParamUtil.isNullOrEmptyForObject(birthday)) {
            request.setAttribute("birthErrMsg", "生年月日は必須です");
            hasError = true;
        } else {
            updateUser.setBirthday(Date.valueOf(birthday));
        }

		if (ParamUtil.isNullOrEmpty(login_id)) {
            request.setAttribute("idErrMsg", "IDは必須です");
            hasError = true;
        } else {
            updateUser.setLogin_id(login_id);
        }

		if (ParamUtil.isNullOrEmpty(password)) {
            request.setAttribute("passErrMsg", "パスワードは必須です");
            hasError = true;
        } else {
            updateUser.setPassword(password);
        }

		if (hasError) {
            request.getRequestDispatcher("/userInfoChange.jsp").forward(request, response);
            return;
        }

		User_info prevUser = sessionInfo.getPrevUpdateUser();

		if (prevUser.equals(updateUser)) {
            request.setAttribute("errMsg", "1項目以上変更してください");
            request.getRequestDispatcher("/userInfoChange.jsp").forward(request, response);
            return;
        }

		UserInfoService userInfoService = new UserInfoService();

		if (userInfoService.existsUserByLoginIdExcludingUserId(login_id, updateUser.getUser_id())) {
            request.setAttribute("errMsg", "IDが重複しています");
            request.getRequestDispatcher("/userInfoChange.jsp").forward(request, response);
            return;
        }

			request.getRequestDispatcher("/changeConfirm.jsp").forward(request, response);
	}

}
