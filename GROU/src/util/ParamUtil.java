package util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import entity.SessionInfo;

public class ParamUtil {

	/**
	* 引数に指定した文字列がnull、または空文字かを判定
	*/
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}


	 /**
     * 引数に指定したオブジェクトがnull、または空文字かを判定
     */
    public static boolean isNullOrEmptyForObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

	/**
	 * 引数に指定した文字列が数値に変換できるかを判定
	 */
	public static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	/**
	 * 引数に指定した文字列が数値に変換して返却する。
	 * 変換できない場合はnullを返却する。
	 */
	public static Integer checkAndParseInt(String str) {
		if (isNumber(str)) {
			return Integer.parseInt(str);
		} else {
			return null;
		}
	}

	/**
	 * 引数に指定したセッションオブジェクトから、SessionInfoオブジェクトを取得する。
	 * セッションに保存されていない場合は、SessionInfoオブジェクトを新規作成する。
	 */
	public static SessionInfo getSessionInfo(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");

		if (sessionInfo == null) {
			return new SessionInfo();
		} else {
			return sessionInfo;
		}
	}

	public static List<Integer> getListByCheckbox(String[] checkbox) {

		List<Integer> list = new ArrayList<>();

		int x;
		for (String a : checkbox) {
			x = Integer.parseInt(a);
			list.add(x);
		}

		return list;

	}

	/**時と分を受け取り、09:00みたいな形にするやつ*/
	public static String getStringDate(int hour, int min) {
		String sHour = null;
		String sMin = null;
		String workTime = null;
		//時間が0～9なら先頭に0を付け足す。 9→09
		if (hour < 10) {
			sHour = "0" + hour;
		} else {
			sHour = Integer.toString(hour);
		}
		//分が0～9なら先頭に0を付け足す。 9→09
		if (min < 10) {
			sMin = "0" + min;
		} else {
			sMin = Integer.toString(min);
		}
		//09:00みたいな形にする
		workTime = sHour + ":" + sMin;

		return workTime;
	}

	public static String getRoleNameByRoleId(Integer targetRole_id, List<entity.Role> roleList) {
        for (entity.Role role : roleList) {
            if (role.getRole_id() == targetRole_id) {
                return role.getRole_name();
            }
        }

        return "";
    }
}
