package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.YukyuSinseiDao;
import entity.JoinUserApp;
import entity.Paid;
import entity.PaidApplication;
import util.DbUtil;

public class YukyuSinseiService {

	public YukyuSinseiService(){

	}

	public List<Paid> findPaid(int user_id) {
		List<Paid> list = new ArrayList<Paid>();
		try (Connection conn = DbUtil.getConnection()) {

			YukyuSinseiDao ysd = new YukyuSinseiDao(conn);
            list = ysd.findPaid(user_id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
	}

	public List<JoinUserApp> findPaidUser(String paidDay) {
		List<JoinUserApp> list = new ArrayList< JoinUserApp>();
		try (Connection conn = DbUtil.getConnection()) {

			YukyuSinseiDao ysd = new YukyuSinseiDao(conn);
            list = ysd.findPaidUser(paidDay);

        } catch (Exception e) {
            e.printStackTrace();
        }
		return list;
	}

	public List<PaidApplication> findAllPaid() {
		List<PaidApplication> list = new ArrayList<PaidApplication>();
		try (Connection conn = DbUtil.getConnection()) {

			YukyuSinseiDao ysd = new YukyuSinseiDao(conn);
            list = ysd.findAllPaid();

        } catch (Exception e) {
            e.printStackTrace();
        }
		return list;
	}

	public void ninsyo(Integer user_id, java.sql.Date date) {
		try (Connection conn = DbUtil.getConnection()) {

			YukyuSinseiDao ysd = new YukyuSinseiDao(conn);
            ysd.ninsyo(user_id, date);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void hiNinsyo(Integer user_id, java.sql.Date date) {
		try (Connection conn = DbUtil.getConnection()) {

			YukyuSinseiDao ysd = new YukyuSinseiDao(conn);
            ysd.hiNinsyo(user_id, date);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
