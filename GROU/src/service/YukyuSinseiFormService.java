package service;

import java.sql.Connection;

import dao.YukyuSinseiFormDao;
import util.DbUtil;

public class YukyuSinseiFormService {

	public YukyuSinseiFormService() {

	}

	public void sinsei(String appli_date, Integer user_id, String preferred_date, String remarks) {
		try (Connection conn = DbUtil.getConnection()) {

			YukyuSinseiFormDao ysfd = new YukyuSinseiFormDao(conn);
			ysfd.sinsei(appli_date, user_id, preferred_date, remarks);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
