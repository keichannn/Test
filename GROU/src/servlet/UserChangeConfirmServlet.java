package servlet;

import java.io.IOException;

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

@WebServlet("/UserChangeConfirmServlet")
public class UserChangeConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String rePassword = request.getParameter("rePassword");

		HttpSession session = request.getSession();
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        User_info updateUser = sessionInfo.getUpdateUser();

        if (!updateUser.getPassword().equals(rePassword)) {
            request.setAttribute("errMsg", "前画面で入力したパスワードと一致しません");
            request.getRequestDispatcher("/userInfoChange.jsp").forward(request, response);
            return;
        }

        UserInfoService userInfoService = new UserInfoService();
        userInfoService.update(updateUser);

        sessionInfo.setPrevUpdateUser(null);
        sessionInfo.setUpdateUser(null);

        request.getRequestDispatcher("/infomationChange.jsp").forward(request, response);
	}

}
