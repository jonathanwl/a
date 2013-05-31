package com.wasoft.calldb.business.impl.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wasoft.calldb.BasicOperation;
import com.wasoft.calldb.CallDbException;
import com.wasoft.calldb.SqlParameterExt;
import com.wasoft.calldb.business.AbstractDbInterface;
import com.wasoft.calldb.sql.Row;
import com.wasoft.calldb.sql.value.DoubleValue;
import com.wasoft.calldb.sql.value.IntValue;
import com.wasoft.calldb.sql.value.LongValue;
import com.wasoft.calldb.sql.value.ShortValue;
import com.wasoft.calldb.sql.value.StringValue;
import com.wasoft.calldb.sql.value.TimeValue;
import com.wasoft.dataserver.ws.common.bean.BmBean;

import com.wasoft.model.xtgl.Dbtrace;

/*
 * ͨ�����ݴ�����
 */
public class Wasys3_commonImpl extends AbstractDbInterface {

	public Wasys3_commonImpl() {
		super();
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	/**
	 * ͨ�ò�ѯ
	 * 
	 * @param �򵥵ı�׼SQL��䣬��������������ݿ��SQL
	 * @return
	 * @throws CallDbException
	 */
	public List openCommon_GETTABLE(Object obj) throws CallDbException {
		String sql = (String) obj;
		sql = parse_replace(sql);

		return bo.queryToList(sql);
	}

	/* ��ʼ��¼��־ */
	public void executeCommon_BEGINDBTRACE(Object obj) throws CallDbException {
		Dbtrace dbtrace = (Dbtrace) obj;
		SqlParameterExt spx = new SqlParameterExt();
		spx.add(new StringValue(dbtrace.getDbtrace_id()));
		spx.add(new LongValue(dbtrace.getUserid()));
		spx.add(new StringValue(dbtrace.getIp()));
		spx.add(new StringValue(dbtrace.getModule_name()));
		spx.add(new StringValue(dbtrace.getWebservice_method()));
		// spx.add(new StringValue(dbtrace.getMethod_name()));
		spx.add(new TimeValue(dbtrace.getStart_time()));
		String sql = "insert into t_wa_sys_dbtrace(dbtrace_id,userid,ip,module_name,webservice_method,start_time) values(?,?,?,?,?,?)";
		bo.setSqlParameterExt(spx);
		try {
			bo.execute(sql);
			bo.clearParameters();
		} catch (Exception err) {
			xlog.error("��ʼ���ݸ��ٲ��ɹ���" + err.getMessage());
			throw new CallDbException("��ʼ���ݸ��ٲ��ɹ���");
		}
	}

	/* ������־��¼ */
	public void executeCommon_ENDDBTRACE(Object obj) throws CallDbException {
		Dbtrace dbtrace = (Dbtrace) obj;
		SqlParameterExt spx = new SqlParameterExt();
		spx.add(new TimeValue(dbtrace.getEnd_time()));
		spx.add(new DoubleValue(dbtrace.getDuration()));
		spx.add(new IntValue(dbtrace.getRet_count()));
		spx.add(new StringValue(dbtrace.getMethod_name()));
		spx.add(new StringValue(dbtrace.getDbtrace_id()));
		String sql = "update t_wa_sys_dbtrace set end_time=?, duration=?, ret_count=?,sql_code=? where dbtrace_id=?";
		bo.setSqlParameterExt(spx);
		try {
			bo.execute(sql);
			bo.clearParameters();
		} catch (Exception err) {
			xlog.error("�������ݸ��ٲ��ɹ���" + err.getMessage());
			throw new CallDbException("�������ݸ��ٲ��ɹ���");
		}
	}

	@SuppressWarnings("unchecked")
	public List openCommon_GETTABLE1(Object obj) throws CallDbException,
			SQLException {
		String sql = (String) obj;
		sql = parse_replace(sql);
		ResultSet rs = bo.query(sql);
		List list = new ArrayList();

		for (int i = 0; i < rs.getMetaData().getColumnCount(); ++i) {
			boolean bool = true;
			int j = 0;
			int k = rs.getMetaData().getColumnType(i + 1);
			String str2 = rs.getMetaData().getColumnName(i + 1).toUpperCase();
			String str3 = rs.getMetaData().getColumnTypeName(i + 1)
					.toLowerCase();
			if ((!(str3.equals("clob"))) && (!(str3.equals("blob"))))
				j = rs.getMetaData().getPrecision(i + 1);
			if (rs.getMetaData().isNullable(i + 1) == 0)
				bool = false;
			ArrayList list2 = new ArrayList();
			list2.add(str2);
			list2.add(Integer.valueOf(k));
			list2.add(Integer.valueOf(j));
			list2.add(Boolean.valueOf(bool));
			list.add(list2);
		}
		return list;
	}

	public void executeCommon_SETTABLE(Object obj) throws CallDbException {
		String sql = (String) obj;
		sql = parse_replace(sql);
		SqlParameterExt spx = new SqlParameterExt();
		try {
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
		}
	}

	// ��λ�˺���ʾ��ѯ
	// public List <Row> openCommon_Gz_Dw_Tscx(Object obj) throws
	// CallDbException
	public List<Row> openCommon_Gz_Dw_Tscx(Object obj, int pagesize, int pagenum)
			throws CallDbException {
		BmBean pmt = (BmBean) obj;
		SqlParameterExt spx = null;
		String s = "";
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(pmt.getBm().trim())); // p1 ��λ����
			spx.add(new StringValue(pmt.getMc().trim())); // p2 ��λ����
			spx.add(new StringValue(pmt.getA001().trim())); // p3 ְ���˺�
			spx.add(new StringValue(pmt.getA002().trim())); // p4 ְ������
			spx.add(new LongValue(pmt.getUid())); // p5 �û�����id

		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			s = "p_gz_dw_tscx(?p1,?p2,?p3,?p4,?p5)";
			switch (databaseType) {
			case BasicOperation.DB_TYPE_ORACLE:
				bo.setSqlParameterExt(spx);
				bo.setSqlParameterExt(spx);
				String tableName = "tmp_gz_dw_tscx";
				String queryStatement = "select * from tmp_gz_dw_tscx order by bm";
				// return bo.queryProcedureToList(s, tableName, queryStatement);
				return bo.queryProcedureToList(s, tableName, queryStatement,
						pagesize, pagenum);
			case BasicOperation.DB_TYPE_DB2:
				bo.setSqlParameterExt(spx);
				return bo.queryProcedureToList(s, null);
			case BasicOperation.DB_TYPE_SYBASE:
				bo.setSqlParameterExt(spx);
				return bo.queryProcedureToList(s, null);
			default:
				throw new CallDbException("δʵ�ֵ����ݿ�����!");
			}
		} catch (Exception err) {
			throw new CallDbException("��λ�˺���ʾ��ѯ���ɹ�!" + err.getMessage());
		}
	}

	// ת�Ƶ�λ�˺���ʾ��ѯ
	public List<Row> openCommon_Gz_Dw_Zytscx(Object obj) throws CallDbException {
		BmBean pmt = (BmBean) obj;
		SqlParameterExt spx = null;
		String s = "";
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(pmt.getBm().trim())); // p1 ��λ����
			spx.add(new StringValue(pmt.getMc().trim())); // p2 ��λ����
			spx.add(new StringValue(pmt.getYxdw().trim())); // p3 ת�Ƶ�λ�˺� ����Ϊ��
			spx.add(new ShortValue(pmt.getYwlb())); // p4
													// 1-�˻��ϲ���2-�ڲ�ת�ƣ�3-��鼯��ת����4-��鼯��ת��
			spx.add(new LongValue(pmt.getUid())); // p5 �û�����id

		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			s = "p_gz_dw_zytscx(?p1,?p2,?p3,?p4,?p5)";
			switch (databaseType) {
			case BasicOperation.DB_TYPE_ORACLE:
				bo.setSqlParameterExt(spx);
				bo.setSqlParameterExt(spx);
				String tableName = "tmp_gz_dw_tscx";
				String queryStatement = "select * from tmp_gz_dw_zytscx order by bm";
				return bo.queryProcedureToList(s, tableName, queryStatement);
			case BasicOperation.DB_TYPE_DB2:
				bo.setSqlParameterExt(spx);
				return bo.queryProcedureToList(s, null);
			case BasicOperation.DB_TYPE_SYBASE:
				bo.setSqlParameterExt(spx);
				return bo.queryProcedureToList(s, null);
			default:
				throw new CallDbException("δʵ�ֵ����ݿ�����!");
			}
		} catch (Exception err) {
			throw new CallDbException("ת�Ƶ�λ�˺���ʾ��ѯ���ɹ�!" + err.getMessage());
		}
	}

	// ��λ�˺�����У��
	// �ڵ�λ�˺�������У�������Ϣ�󣨻��ߵ�λ�˺ŵ������淵�ص�λ�˺ź󣩣�
	// �������뿪ʱ�����˷�����ͨ�������ĵ�λ�˺�ȥ��ѯ��λ������Ϣ���жϵ�λ�˺��Ƿ���ڣ�������ѯ���д�����Ķ�Ӧ������
	public List<Row> openCommon_Gz_Dw_Sfcz_Chk(Object obj)
			throws CallDbException {
		BmBean pmt = (BmBean) obj;
		String sql = "";
		String dwbm = pmt.getBm().trim();
		long userid = pmt.getUid();
		sql = "select * from bm_a003 a where bm='"
				+ dwbm
				+ "' and dwzt not in('00','05') and "
				+ "(exists(select a075 from hr_yg_sjqx_gjd where a075=a.gjdbm and userid="
				+ userid
				+ ") "
				+ "or gjdbm like replace((select min(a075) from hr_yg_sjqx_gjd where userid="
				+ userid
				+ "),'00','%')) and "
				+ "(exists(select a073 from hr_yg_sjqx_cbwd where a073=a.cbwdbm and userid="
				+ userid
				+ ") "
				+ "or cbwdbm like (select nvl(min(a073),'%') from hr_yg_sjqx_cbwd where userid="
				+ userid + "))";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openCommon_Gz_Dw_Sfcz_Chk]����"
					+ e.getMessage());
		}
	}

	/*
	 * �ڿ�鼯��ת��ת���ĶԷ���λ�˺�������У�������Ϣ�󣨻��ߵ�λ�˺ŵ����� �淵�ص�λ�˺ź󣩣��������뿪ʱ�����˷�����ͨ�������ĵ�λ�˺�ȥ��ѯ��
	 * λ������Ϣ���жϵ�λ�˺��Ƿ���ڣ�������ѯ���д�����Ķ�Ӧ������
	 */
	public List<Row> openCommon_Gz_Dw_Zysfcz_Chk(Object obj)
			throws CallDbException {
		BmBean pmt = (BmBean) obj;
		String sql = "";
		String yxdw = pmt.getYxdw().trim();
		String dwbm = pmt.getBm().trim();
		long userid = pmt.getUid();
		sql = "select * from bm_a003 where bm='" + dwbm
				+ "' and dwzt not in('00','05') and gjdbm<>(select gjdbm  "
				+ "from bm_a003 where bm='" + yxdw
				+ "') and gjdbm like (select substr(min(a075),1,2)||'%'  "
				+ "from hr_yg_sjqx_gjd where userid=" + userid + "))";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openCommon_Gz_Dw_Zysfcz_Chk]����"
					+ e.getMessage());
		}
	}

	public List<Row> openCommon_GZ_DWCX(Object obj) throws CallDbException {
		BmBean pmt = (BmBean) obj;
		String sql = "";
		String gjdbm = pmt.getGjdbm().trim().equals("00") ? "" : pmt.getGjdbm()
				.trim();
		String cbwd = pmt.getCbwdbm().trim().equals("") ? "" : " and "
				+ sqlCharIndex("cbwdbm", "'" + pmt.getCbwdbm().trim() + "'")
				+ "<>0 ";
		sql = "select bm,mc from bm_a003 where bm='" + pmt.getBm().trim()
				+ "' and gjdbm like '" + gjdbm + "%'" + cbwd
				+ " and dwzt not in ('00','05')";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openCommon_GZ_DWCX]����"
					+ e.getMessage());
		}
	}

	public List<Row> openCommon_GETGJD(Object obj) throws CallDbException {
		long uid = Long.parseLong((String) obj);
		String sql = "";
		sql = "select * from bm_a075 where exists(select a075 from hr_yg_sjqx_gjd where a075=bm_a075.bm and userid="
				+ uid
				+ ") or bm like replace((select min(a075) from hr_yg_sjqx_gjd where userid="
				+ uid + "),'00','%') order by bm";

		try {
			SqlParameterExt spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openCommon_GETGJD]����"
					+ e.getMessage());
		}
	}

	// �鼯���������
	public List<Row> openCommon_GETGJYH(Object obj) throws CallDbException {
		BmBean pmt = (BmBean) obj;
		String sql = "";
		String gjdbm = pmt.getGjdbm();
		long uid = pmt.getUid();
		sql = "select * from bm_p015 where (exists(select p015 from hr_yg_sjqx_yh where p015=bm_p015.bm and userid="
				+ uid
				+ ") or bm like replace((select nvl(min(p015),'00') from hr_yg_sjqx_yh where userid="
				+ uid
				+ "),'00','%')) and "
				+ "(exists(select a075 from hr_yg_sjqx_gjd where a075=bm_p015.gjdbm and userid="
				+ uid
				+ ") or gjdbm like "
				+ " replace((select min(a075) from hr_yg_sjqx_gjd where userid="
				+ uid + "),'00','%'))";
		if (!gjdbm.trim().equals("")
				&& !gjdbm.trim().substring(2, 4).equals("00")) {
			sql += " and gjdbm='" + gjdbm + "'";
		}
		sql += " order by bm";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openCommon_GETGJYH]����"
					+ e.getMessage());
		}
	}

	// ���й��������ѯ
	public List<Row> openCommon_GETYHWD(Object obj) throws CallDbException {
		BmBean pmt = (BmBean) obj;
		String sql = "";
		String yh = pmt.getYh();
		long uid = pmt.getUid();
		sql = "select * from bm_a073 where (exists(select a073 from hr_yg_sjqx_cbwd where a073=bm_a073.bm and userid="
				+ uid
				+ ") or bm like replace((select nvl(min(a073),'00') from hr_yg_sjqx_cbwd where userid="
				+ uid
				+ "),'00','%')) and "
				+ "(exists(select a075 from hr_yg_sjqx_gjd where a075=bm_a073.gjdbm and userid="
				+ uid
				+ ") or gjdbm like "
				+ " replace((select min(a075) from hr_yg_sjqx_gjd where userid="
				+ uid + "),'00','%'))";
		if (!yh.trim().equals("")) {
			sql += " and yhbm='" + yh + "'";
		}
		sql += " order by bm";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openCommon_GETYHWD]����"
					+ e.getMessage());
		}
	}

	// �鼯�㡢���й��������ѯ
	public List<Row> openCommon_GETGJYHWD(Object obj) throws CallDbException {
		BmBean pmt = (BmBean) obj;
		String sql = "";
		String yh = pmt.getYh();
		String gjdbm = pmt.getGjdbm();
		long uid = pmt.getUid();
		sql = "select * from bm_a073 where (exists(select a073 from hr_yg_sjqx_cbwd where a073=bm_a073.bm and userid="
				+ uid
				+ ") or bm like replace((select nvl(min(a073),'00') from hr_yg_sjqx_cbwd where userid="
				+ uid
				+ "),'00','%')) and "
				+ "(exists(select a075 from hr_yg_sjqx_gjd where a075=bm_a073.gjdbm and userid="
				+ uid
				+ ") or gjdbm like "
				+ " replace((select min(a075) from hr_yg_sjqx_gjd where userid="
				+ uid + "),'00','%'))";
		if (!gjdbm.trim().equals("")
				&& !gjdbm.trim().substring(2, 4).equals("00")) {
			sql += " and gjdbm='" + gjdbm + "'";
		}
		if (!yh.trim().equals("")) {
			sql += " and yhbm='" + yh + "'";
		}
		sql += " order by bm";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openCommon_GETGJYHWD]����"
					+ e.getMessage());
		}
	}

	public List<Row> openCommon_GD_DkHtCx(Object obj) throws CallDbException {
		BmBean pmt = (BmBean) obj;
		String sql = "";
		String gjdbm = "'" + pmt.getGjdbm().trim() + "'";
		long userid = pmt.getUid();
		String cbwdbm = "'" + pmt.getCbwd() + "'";
		String yhbm = "'" + pmt.getYh() + "'";
		String dkzt = pmt.getE043().trim();
		String xm = pmt.getE002().trim();
		String zjhm = pmt.getE003().trim();
		String kkzh = pmt.getE021().trim();
		String yhzh = pmt.getE022().trim();

		sql = "select a.e001,a.e002,a.e003,a.e021,a.e022,a.e034,a.e042,a.a000,"
				+ sqlStrInSelIsNull("d.mc")
				+ " e043,"
				+ sqlStrInSelIsNull("f.mc")
				+ " a076,"
				+ sqlStrInSelIsNull("g.mc")
				+ " p016,"
				+ sqlStrInSelIsNull("h.mc")
				+ " a074 from gd_dk_zz a "
				+ "left join bm_hkfs b on b.bm=a.e028 "
				+ "left join (select g000,g001,g002,g013,g004,a000 from gd_sq_sqr where g011='2' ) c on c.g000=a.g000 "
				+ "left join bm_dkzt d on d.bm=a.e043 "
				+ "left join bm_a075 f on f.bm=a.a075 "
				+ "left join bm_p015 g on g.bm=a.p015 "
				+ "left join bm_a073 h on h.bm=a.a073 "
				+ "where a.e002 like '"
				+ xm
				+ "%' and a.e003 like '"
				+ zjhm
				+ "%' and a.e021 like '"
				+ kkzh
				+ "%' and a.e022 like '"
				+ yhzh
				+ "%' "
				+ "and e043 like '"
				+ dkzt
				+ "%' and a.a075 like "
				+ sqlReplace(gjdbm, "'00'", "'%'")
				+ " and (exists(select a075 from hr_yg_sjqx_gjd where a075=a.a075 and userid="
				+ userid
				+ ") or a.a075 like "
				+ sqlReplace(
						"(select min(a075) from hr_yg_sjqx_gjd where userid="
								+ userid + ")", "'00'", "'%'")
				+ ") and "
				+ "a.p015 like "
				+ sqlReplace(yhbm, "' '", "'%'")
				+ " and (exists(select p015 from hr_yg_sjqx_yh where p015=a.p015"
				+ " and userid="
				+ userid
				+ ") or a.p015 like "
				+ sqlReplace("(select " + sqlreplaceIsNull("min(p015)", " ")
						+ " from hr_yg_sjqx_yh where userid=" + userid + ")",
						"' '", "'%'")
				+ ") and a.a073 like "
				+ sqlReplace(cbwdbm, "' '", "'%'")
				+ " and(exists(select a073 from hr_yg_sjqx_cbwd where "
				+ "a073=a.a073 and userid="
				+ userid
				+ ") or a.a073 like "
				+ sqlReplace("(select " + sqlreplaceIsNull("min(a073)", " ")
						+ " from hr_yg_sjqx_cbwd where userid=" + userid + ")",
						"' '", "'%'") + ") order by a.e001";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openCommon_GD_DkHtCx]����"
					+ e.getMessage());
		}
	}

	// ������ͨ�ò�ѯ

	





}
