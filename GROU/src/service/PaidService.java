package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.YukyuSinseiDao;
import entity.JoinUserApp;
import entity.Paid;
import entity.PaidApplication;
import util.DbUtil;

public class PaidService {

	public PaidService() {
	}

	public List<Paid> findPaid(int user_id) {
		List<Paid> list = new ArrayList<>();

		try (Connection conn = DbUtil.getConnection()) {

			YukyuSinseiDao yukyuSinseiDao = new YukyuSinseiDao(conn);
			list = yukyuSinseiDao.findPaid(user_id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<JoinUserApp> findPaidUser(String paidDay) {

		List<JoinUserApp> list = new ArrayList<JoinUserApp>();
		try (Connection conn = DbUtil.getConnection()) {

			YukyuSinseiDao yukyuSinseiDao = new YukyuSinseiDao(conn);
			list = yukyuSinseiDao.findPaidUser(paidDay);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<PaidApplication> findAllPaid() {
		List<PaidApplication> list = new ArrayList<PaidApplication>();
		try (Connection conn = DbUtil.getConnection()) {

			YukyuSinseiDao yukyuSinseiDao = new YukyuSinseiDao(conn);
			list = yukyuSinseiDao.findAllPaid();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

}
