package com.wasoft.calldb.business.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Pattern;

import com.ibm.db2.jcc.a.b;
import com.wasoft.calldb.BasicOperation;
import com.wasoft.calldb.CallDbException;
import com.wasoft.calldb.SqlParameterExt;
import com.wasoft.calldb.business.AbstractDbInterface;
import com.wasoft.calldb.sql.BlobDataBean;
import com.wasoft.calldb.sql.NoSuchColumnException;
import com.wasoft.calldb.sql.Row;
import com.wasoft.calldb.sql.UnsupportedConversionException;
import com.wasoft.calldb.sql.value.*;
import com.wasoft.calldb.util.DateUtils;
import com.wasoft.calldb.util.XTool;
import com.wasoft.dataserver.ws.da.bean.*;

public class DaServiceImpl extends AbstractDbInterface {
	public DaServiceImpl() {
		super();
	}

	public String toString() {
		return getClass().getName();
	}

	public List<Row> openDA_GETID(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			String sql = "select " + bean.getTblName() + "_ID.NEXTVAL"
					+ " as id from DUAL";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("��ȡid��Ϣʧ�ܣ�");
		}
	}

	// ����Ŀ¼�ϴ�
	public void executeDA_JNML_ADD(Object obj) throws CallDbException {
		JnmlBean jnml = (JnmlBean) obj;
		SqlParameterExt spx = null;
		spx = new SqlParameterExt();
		spx.add(new LongValue(jnml.getId())); // p1
		spx.add(new StringValue(jnml.getFileName()));
		spx.add(new StringValue(jnml.getYgdh()));
		spx.add(new BytesValue(jnml.getClyx()));
		String sql = "insert into ef_jnml(id,cl,ygdh,clyx) values(?,?,?,?)";
		bo.setSqlParameterExt(spx);
		try {
			bo.execute(sql);
			bo.clearParameters();
		} catch (Exception err) {
			xlog.error("�ϴ����ɹ���" + err.getMessage());
			throw new CallDbException("�ϴ����ɹ���");
		}
	}

	public void executeDA_INIT_ARCHIVE(Object obj) throws CallDbException {
		short ret = 0;
		DagszBean bean = (DagszBean) obj;
		String sql = "P_ARCHIVE_INIT(?p1,?p2,?p3,?p4,?p5)";
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx.add(new IntValue(bean.getDass()));
			spx.add(new IntValue(bean.getDags()));
			spx.add(new IntValue(bean.getDaps()));
			spx.add(new IntValue(bean.getDahs()));
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);
		} catch (Exception e) {
			throw new CallDbException("�������Ϸ���" + e.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[DA_DELEARCHIVE]����"
					+ e.getMessage());
		}
	}

	public List<Row> openDA_SELEHOUSE(Object obj) throws CallDbException {

		XndaBean bean = (XndaBean) obj;
		String sql = "select * from ef_dagsz where and ?=?";
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(bean.getBj()));
			spx.add(new StringValue(bean.getNum()));
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_SELEHOUSE]����"
					+ e.getMessage());
		}
	}

	public List<Row> openDA_SELEDASALL(Object obj) throws CallDbException {
		String sql = "select distinct(DASH) from ef_dagsz ";
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_SELEHOUSE]����"
					+ e.getMessage());
		}
	}

	public List<Row> openDA_SELEDAGALL(Object obj) throws CallDbException {
		DagszBean bean = (DagszBean) obj;
		String sql = "select distinct(GH) from ef_dagsz where dash=?";
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(bean.getDash()));
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_SELEDAGALL]����"
					+ e.getMessage());
		}
	}

	public List<Row> openDA_GETFIRSTTREE(Object obj) throws CallDbException {
		// WslbBean bean = (WslbBean) obj;
		String sql = "select distinct(second) from ef_wslb where first=?";
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue("���鵵��"));
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_SELEDAGALL]����"
					+ e.getMessage());
		}
	}

	/***
	 * ����Ŀ¼���� daiht
	 * 
	 * @param obj
	 * @return
	 * @throws CallDbException
	 */
	public List<Row> openDA_GETWSTYPEFIRSTTREE(Object obj)
			throws CallDbException {
		WsTypeBean bean = (WsTypeBean) obj;
		StringBuffer sql = new StringBuffer("");
		sql.append("select TYPEID,PID,MC,ID from ef_wstype where 1=1 ");

		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			if (null != bean.getPid() && !"".equals(bean.getPid())) {
				sql.append("  and pid=? ");
				spx.add(new StringValue(bean.getPid()));

			}
			if (null != bean.getTypeid() && !"".equals(bean.getTypeid())) {
				sql.append("  and typeid=? ");
				spx.add(new StringValue(bean.getTypeid()));

			}
			sql.append("order by  typeid asc,pid asc,id asc");
			System.out.println("===========dht============>" + sql.toString());
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql.toString());
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_GETWSTYPEFIRSTTREE]����"
					+ e.getMessage());
		}
	}

	public List<Row> openDA_SELEDAS(Object obj) throws CallDbException {
		String dash = (String) obj;
		String sql = "select distinct(gh) from ef_dagsz where dash=?";
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(dash));
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_SELEHOUSE]����"
					+ e.getMessage());
		}
	}

	public void executeEF_FILEARCHIVE(Object obj) throws CallDbException {

		// DajbxxBean bean = (DajbxxBean) obj;

		String sql = "update into ef_gjda (zgzh,cbwd) values (?,?)";

		SqlParameterExt spx = new SqlParameterExt();
		try {
			// spx.add(new StringValue(bean.getId()));
			// spx.add(new StringValue(bean.getTblName()));
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[EF_FILEARCHIVE]����"
					+ e.getMessage());
		}
	}

	/**
	 * ���ӵ���ϵͳ ����ɾ����¼����������־ 2010��9��7�� wl
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_DELEARCHIVE(Object obj) throws CallDbException {

		PublicInfoBean bean = (PublicInfoBean) obj;
		String[] idString = bean.getIdString().split(";");
		String sql = "delete from " + bean.getTblName() + "where id = ?";

		SqlParameterExt spx = new SqlParameterExt();
		try {
			for (int i = 0; i < idString.length; i++) {
				// spx.add(new StringValue(bean.getTblName()));
				spx.add(new LongValue(Long.parseLong(idString[i])));
				bo.setSqlParameterExt(spx);
				bo.execute(sql);
			}
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[DA_DELEARCHIVE(]����"
					+ e.getMessage());
		}
	}

	public void executeDA_CANCEL(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		SqlParameterExt spx = null;
		String sql = "UPDATE EF_DAJBXX SET F_STATE=? ,ZXRBM=?,ZXR=?,ZXRQ=? ,ZXYY=? ,ZXBCNX=? WHERE ID=?";
		String[] idString = bean.getIdString().split(";");
		// String[] idString = bean.getIdString().split(";");
		String zxr = bean.getZxr();
		String zxrbm = bean.getZxrbm();
		for (int i = 0; i < idString.length; i++) {
			try {
				spx = new SqlParameterExt();
				spx.add(new StringValue("ע��"));
				spx.add(new StringValue(zxrbm));
				spx.add(new StringValue(zxr));
				spx.add(new DateValue(XTool.Str2Date(DateUtils.getCurDate())));
				spx.add(new StringValue("�������������ѵ�"));
				spx.add(new IntValue(5));
				spx.add(new LongValue(Long.parseLong(idString[i])));

			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}
			try {
				bo.setSqlParameterExt(spx);
				bo.execute(sql);
			} catch (Exception e) {
				throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
			}
		}
	}

	/**
	 * �����ע�� jcl 2011-4-20
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_TXCANCEL(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		SqlParameterExt spx = null;
		String sql = "UPDATE EF_TXDAJBXX SET F_STATE=? ,ZXRBM=?,ZXR=?,ZXRQ=? WHERE ID=?";
		String[] idString = bean.getIdString().split(";");
		// String[] idString = bean.getIdString().split(";");
		String zxr = bean.getZxr();
		String zxrbm = bean.getZxrbm();
		for (int i = 0; i < idString.length; i++) {
			try {
				spx = new SqlParameterExt();
				spx.add(new StringValue("ע��"));
				spx.add(new StringValue(zxrbm));
				spx.add(new StringValue(zxr));
				spx.add(new DateValue(XTool.Str2Date(DateUtils.getCurDate())));
				spx.add(new LongValue(Long.parseLong(idString[i])));

			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}
			try {
				bo.setSqlParameterExt(spx);
				bo.execute(sql);
			} catch (Exception e) {
				throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
			}
		}
	}

	/**
	 * wl ���õ�uoload����
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_FILE_UPLOAD(Object obj) throws CallDbException {
		BlobBean bean = (BlobBean) obj;
		DajbxxBean da = new DajbxxBean();
		SqlParameterExt spx = null;
		String tblName = "";
		if (!"".equals(bean.getTableName()) || bean.getTableName() != null) {
			tblName = bean.getTableName();
		} else {
			tblName = "EF_FILE";
		}
		System.out.println(tblName + "============tblName");
		da.setTblName(tblName);
		String sql = "insert into " + tblName
				+ " (id,p_id,cl,clbm) values (?,?,?,?) ";
		List<Row> list = openDA_GETID(da);
		Row row = list.get(0);
		String id = "";
		try {
			id = row.getTrimString("id");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			spx = new SqlParameterExt();
			spx.add(new LongValue(Long.parseLong(id)));
			spx.add(new StringValue(bean.getP_id()));
			spx.add(new StringValue(bean.getName()));
			spx.add(new StringValue("001"));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
			BlobDataBean bdb = new BlobDataBean();
			bdb.setKey(id);
			bdb.setData(bean.getData());
			FILE_UPDATE(bdb, tblName);
		} catch (Exception e) {
			throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
		}
	}

	/**
	 * ƽ̨�ļ����ϴ����÷��� wl ���ݿ��Ѿ����ڼ�¼�������Ӧ���ϴ��ļ�����
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	private void FILE_UPDATE(Object obj, String tblname) throws CallDbException {

		BlobDataBean pmt = null;
		SqlParameterExt spx = null;
		String s = "";
		try {
			pmt = (BlobDataBean) obj;
			spx = new SqlParameterExt();
			try {
				spx.add(new BytesValue(pmt.getData()));
				spx.add(new LongValue(Long.valueOf(pmt.getKey()))); // id ����
			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}
			s = "update " + tblname + " set F_CONTENt=? where id=?";

			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.update(s);
			bo.clearParameters();
			bo.execute("commit");
		} catch (Exception e) {
			xlog.debug(e.getMessage());
			throw new CallDbException("����" + tblname + "[BLOB]ʧ�ܣ�");
		} finally {
			bo.closestmt();
		}
	}

	/**
	 * �ĵ��ϵĹ鵵 jcl 11-8-8
	 * 
	 */
	public void executeDA_ARCHIVE(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		long ret = 0;
		SqlParameterExt spx = null;
		String f_type = bean.getF_type();
		System.out.println(f_type+"===************************************");
		spx = new SqlParameterExt();
		try {
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getJsrbm()));
			spx.add(new StringValue(bean.getJsr()));
			spx.add(new StringValue(bean.getDash()));
			spx.add(new StringValue(bean.getGh()));
			spx.add(new StringValue(bean.getGrow()));
			spx.add(new StringValue(bean.getPh()));
			spx.add(new StringValue(bean.getHh()));
			spx.add(new IntValue(Integer.parseInt(bean.getSxh())));
			spx.add(new StringValue(bean.getBmjb()));
			spx.add(new IntValue(bean.getBcnx()));
			spx.add(new LongValue(ret), SqlParameterExt.SQL_OUTPUT);
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			if (f_type.trim().equals("4")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLDK(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("2")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLGJ(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("3")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLZQ(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("5")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLLP(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("6")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLWS(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("1")) {
				System.out.println("��λ�鵵������");
				bo
						.executeProcedure("P_ARCHIVE_ALLDW(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("7")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLZL(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("13")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLZL(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("10")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLZL(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("12")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLZL(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("9")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLFILE(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("8")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLFILE(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			if (f_type.trim().equals("11")) {
				bo
						.executeProcedure("P_ARCHIVE_ALLFILE(?p1,?p2,?p3,?p4,?P5,?P6,?P7,?P8,?P9,?P10,?p11,?p12)");
			}
			bo.closestmt();
		} catch (Exception err) {
			throw new CallDbException(err.getMessage());
		}
	}

	/**
	 * ������鵵����2011��4��14�� wm
	 * 
	 * @param obj
	 * @throws CallDbException
	 * @throws CallDbException
	 */
	public void executeDA_TXARCHIVE(Object obj) throws CallDbException {
		TxdajbxxBean bean = (TxdajbxxBean) obj;
		short ret = 0;
		SqlParameterExt spx = null;
		String f_type = bean.getF_type();
		spx = new SqlParameterExt();
		try {
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getJsrbm()));
			spx.add(new StringValue(bean.getJsr()));
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.executeProcedure("P_ARCHIVE_ALLTX(?p1,?p2,?p3,?p4)");

			ret = spx.get(4).getShort();
			if (ret == 0) {
				System.out.println(f_type + "�鵵�ɹ���");
			}
			bo.closestmt();
		} catch (Exception err) {
			throw new CallDbException(err.getMessage());
		}
	}

	/**
	 * �����鵵���÷��� jcl 2011-8-8
	 * 
	 * @param object
	 */

	public void executeDA_ARCHIVEALL(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		Row row = null;
		String[] idString = bean.getIdString().split(";");
		String f_type = bean.getF_type();
		int ret = 0;
		String sql = "";
		List<Row> list = null;
		list = this.openDA_Archive_All_P(obj);
		DagszBean[] beans = new DagszBean[list.size()];
		SqlParameterExt spx = null;
		try {
			for (int i = 0; i < idString.length; i++) {
				spx = new SqlParameterExt();
				try {
					row = list.get(i);
					long id = Long.parseLong(idString[i]);
					spx.add(new LongValue(id));
					spx.add(new StringValue(bean.getJsrbm()));
					spx.add(new StringValue(bean.getJsr()));
					spx.add(new StringValue(row.getTrimString("dash")));
					spx.add(new StringValue(row.getTrimString("gh")));
					spx.add(new StringValue(row.getTrimString("grow")));
					spx.add(new StringValue(row.getTrimString("hh")));
					spx.add(new LongValue(Integer.parseInt(row
							.getTrimString("dno"))));
					String zt = bean.getBmjb() == null ? "��ͨ" : bean.getBmjb();
					spx.add(new StringValue(zt));
					spx.add(new LongValue(bean.getBcnx() == 0 ? 20 : bean
							.getBcnx()));
					spx.add(new LongValue(ret), SqlParameterExt.SQL_OUTPUT);
				} catch (Exception err) {
					throw new CallDbException("��" + i + "��������Ϸ�!"
							+ err.getMessage());
				}
				try {
					bo.clearParameters();
					bo.setSqlParameterExt(spx);
					if (f_type.trim().equals("4")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLDK(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("2")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLGJ(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("3")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLZQ(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("5")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLLP(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("6")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLWS(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("1")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLDW(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("7")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLZL(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("13")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLZL(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("10")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLZL(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("12")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLZL(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("9")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLFILE(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("8")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLFILE(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					if (f_type.trim().equals("11")) {
						bo
								.executeProcedure("P_ARCHIVE_ALLFILE(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?P9,?p10,?p11)");
					}
					bo.closestmt();
				} catch (Exception err) {
					throw new CallDbException(err.getMessage());
				}
			}
		} catch (Exception e) {
			throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
		}
	}

	/**
	 * ��ǩ�ϵľ���Ŀ¼��
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_SELEJNML(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		SqlParameterExt spx = null;
		String sql = " ";
		try {
			sql = "select * from ef_jnml where dalx = ? order by clbm";
			spx = new SqlParameterExt();
			spx.add(new StringValue(bean.getF_type()));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
		}
	}

	/**
	 * ����Ŀ¼����ɨ��ͼƬ�ķ��� 2010��9��25��add by HD
	 * ����ef_jnml������������id��cl����,�ٵ���UPDATE_JNML_BLOB()��������ͼƬ
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_JNML_CLYX_ADD(Object obj) throws CallDbException {
		ImageScanBean isb = (ImageScanBean) obj;
		String sql = "insert into EF_JNML(YGDH,ID,CL) values(?,?,?)";
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx.add(new StringValue(isb.getYgdh()));// Ԥ�鵵���������ef_dajbxx
			spx.add(new LongValue(Long.valueOf(isb.getId())));// ID ����
			// NUMBER����
			spx.add(new StringValue(isb.getCl()));// ��������
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
			// �޸�ef_jnml���blob�ֶ�
			BlobDataBean bdb = new BlobDataBean();
			bdb.setKey(isb.getId());
			bdb.setData(isb.getClyx());

			// Type = 1 ʹ������ID���޸�����,Type = 2 ʹ��YGDH��CLBM�����ֶ����޸�����
			bdb.setType((short) 1);
			UPDATE_JNML_BLOB(bdb);

		} catch (Exception e) {
			xlog.debug(e.getMessage());
			throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
		}
	}

	/**
	 * ����Ŀ¼�޸�ɨ��ͼƬ�ķ��� 2010��9��25��add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_JNML_CLYX_CHG(Object obj) throws CallDbException {
		ImageScanBean isb = (ImageScanBean) obj;
		try {
			BlobDataBean bdb = new BlobDataBean();
			bdb.setKey(isb.getYgdh());
			bdb.setKey1(isb.getClbm());
			bdb.setData(isb.getClyx());
			// Type = 1 ʹ������ID���޸�����,Type = 2 ʹ��YGDH��CLBM�����ֶ����޸�����
			bdb.setType((short) 2);
			UPDATE_JNML_BLOB(bdb);

		} catch (Exception e) {
			xlog.debug(e.getMessage());
			throw new CallDbException("ִ��[JNML_CLYX_CHG]��������" + e.getMessage());
		}
	}

	/**
	 * ���¾���Ŀ¼��blob�ֶ�clyx�ķ��� EF_JNML.CLYX 2010��9��25��add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	private void UPDATE_JNML_BLOB(Object obj) throws CallDbException {

		BlobDataBean pmt = null;
		SqlParameterExt spx = null;
		String s = "";
		try {
			pmt = (BlobDataBean) obj;
			spx = new SqlParameterExt();
			switch (pmt.getType()) {
			case 1:
				try {
					spx.add(new BytesValue(pmt.getData()));
					spx.add(new LongValue(Long.valueOf(pmt.getKey()))); // id ����
					// number����
				} catch (Exception err) {
					throw new CallDbException("�������Ϸ�!" + err.getMessage());
				}
				s = "update EF_JNML set clyx=? where id=?";
				break;
			case 2:
				try {
					spx.add(new BytesValue(pmt.getData()));
					spx.add(new StringValue(pmt.getKey()));
					spx.add(new StringValue(pmt.getKey1()));
				} catch (Exception err) {
					throw new CallDbException("�������Ϸ�!" + err.getMessage());
				}
				s = "update EF_JNML set clyx=? where ygdh=? and clbm=?";
				break;
			default:
				throw new Exception("δ����ĸ��²�����");
			}
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.update(s);
			bo.clearParameters();
			bo.execute("commit");
		} catch (Exception e) {
			xlog.debug(e.getMessage());
			throw new CallDbException("����EF_JNML.CLYX[BLOB]ʧ�ܣ�");
		} finally {
			bo.closestmt();
		}
	}

	/**
	 * ��������Ȩ֤����Ŀ¼��blob�ֶ�clyx�ķ��� EF_TXJNML.CLYX 2011��4��20��add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	private void UPDATE_TXJNML_BLOB(Object obj) throws CallDbException {

		BlobDataBean pmt = null;
		SqlParameterExt spx = null;
		String s = "";
		try {
			pmt = (BlobDataBean) obj;
			spx = new SqlParameterExt();
			switch (pmt.getType()) {
			case 1:
				try {
					spx.add(new BytesValue(pmt.getData()));
					spx.add(new LongValue(Long.valueOf(pmt.getKey()))); // id ����
					// number����
				} catch (Exception err) {
					throw new CallDbException("�������Ϸ�!" + err.getMessage());
				}
				s = "update EF_TXJNML set clyx=? where id=?";
				break;
			case 2:
				try {
					spx.add(new BytesValue(pmt.getData()));
					spx.add(new StringValue(pmt.getKey()));
					spx.add(new StringValue(pmt.getKey1()));
				} catch (Exception err) {
					throw new CallDbException("�������Ϸ�!" + err.getMessage());
				}
				s = "update EF_TXJNML set clyx=? where ygdh=? and clbm=?";
				break;
			default:
				throw new Exception("δ����ĸ��²�����");
			}
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.update(s);
			bo.clearParameters();
			bo.execute("commit");
		} catch (Exception e) {
			xlog.debug(e.getMessage());
			throw new CallDbException("����EF_TXJNML.CLYX[BLOB]ʧ�ܣ�");
		} finally {
			bo.closestmt();
		}
	}

	public void executeDA_DESTROY(Object obj) throws CallDbException {
		SqlParameterExt spx = null;
		DajbxxBean bean = (DajbxxBean) obj;
		String[] idString = bean.getIdString().split(";");
		String xhr = bean.getJsr();
		String xhrbm = bean.getJsrbm();
		String sql = "p_dele_archive(?p1,?p2,?p3)";
		for (int i = 0; i < idString.length; i++) {
			long id = Long.parseLong(idString[i]);
			try {
				spx = new SqlParameterExt();
				spx.add(new LongValue(id));
				spx.add(new StringValue(xhr));
				spx.add(new StringValue(xhrbm));
			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}
			try {
				bo.clearParameters();
				bo.setSqlParameterExt(spx);
				bo.executeProcedure(sql);
			} catch (Exception e) {
				throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
			}
		}
	}

	/**
	 * ����Ŀ¼�����ϴ��ķ��� wl
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	@SuppressWarnings("unchecked")
	public void openDA_ARCHIVE_ADD_BY_ID(Object obj) throws CallDbException {
		JnmlBean bean = (JnmlBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "update ef_jnml where id = ?";
		List list = new ArrayList();
		try {
			spx.add(new LongValue(bean.getId()));// ����������¼��ID
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		bo.setSqlParameterExt(spx);
		list = bo.queryToList(sql);

		try {
			if (list.size() != 0) {

				BlobDataBean bdb = new BlobDataBean();
				bdb.setKey(String.valueOf(bean.getId()));
				bdb.setData(bean.getClyx());
				// Type = 1 ʹ������ID���޸�����,Type = 2 ʹ��YGDH��CLBM�����ֶ����޸�����
				bdb.setType((short) 1);
				UPDATE_JNML_BLOB(bdb);
			} else {
				throw new CallDbException("����������Ϣ����û����ص���!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void executeDA_ARCHIVE_ADD(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		long ret = (long) 0;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			// V_JSR IN VARCHAR,
			// V_JSRQ IN DATE,
			// V_BCNX IN SMALLINT,
			// V_BMJB IN VARCHAR,
			// V_GH IN VARCHAR,
			// V_HH IN VARCHAR,
			// V_SXH IN SMALLINT,
			// V_F_TYPE IN VARCHAR, -- ��������
			// V_F_CREATOR_NAME IN VARCHAR,
			// V_F_CREATOR_ID IN NUMBER,
			// V_F_CREATOR_DEPT_NAME IN VARCHAR,
			// V_F_CREATOR_DEPT_ID IN NUMBER,
			// V_F_CREATOR_TIME IN DATE,
			spx.add(new StringValue(bean.getTblName()));// ҵ���ͬʱҲ����һ����¼
			spx.add(new StringValue(bean.getTblName() + ".ID.NEXTVAL"));// ��һ��ȡҵ������е��ַ���
			spx.add(new StringValue(bean.getDah()));// ��������
			spx.add(new StringValue(" "));// Ԥ�鵵���������ef_dajbxx
			spx.add(new StringValue(bean.getJsr()));// ���ϱ���
			spx.add(new DateValue(XTool.Str2Date(bean.getJsrq()))); // ��������
			spx.add(new LongValue(ret), SqlParameterExt.SQL_OUTPUT);// ����������¼��ID
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_jnml_clyx_add(?p1,?p2,?p3,?p4)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);

		try {
			ret = spx.get(4).getLong();
			if (ret != 0) {
				BlobDataBean bdb = new BlobDataBean();
				bdb.setKey(String.valueOf(ret));
				// bdb.setData(isb.getClyx());
				// Type = 1 ʹ������ID���޸�����,Type = 2 ʹ��YGDH��CLBM�����ֶ����޸�����
				bdb.setType((short) 1);
				UPDATE_JNML_BLOB(bdb);
			} else {
				throw new CallDbException("����������Ϣ����û����ص���!");
			}
		} catch (UnsupportedConversionException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ����Ȩ֤����Ŀ¼����ɨ��ͼƬ�ķ��� 2011��4��20��add by HD
	 * ����ef_txjnml������������id��cl����,�ٵ���UPDATE_JNML_BLOB()��������ͼƬ
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_TXJNML_CLYX_ADD_BY_PROC(Object obj)
			throws CallDbException {
		ImageScanBean isb = (ImageScanBean) obj;
		long ret = (long) 0;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx.add(new StringValue(isb.getYgdh()));// Ԥ�鵵���������ef_dajbxx
			spx.add(new StringValue(isb.getClbm()));// ���ϱ���
			spx.add(new StringValue(isb.getCl()));// ��������
			spx.add(new LongValue(ret), SqlParameterExt.SQL_OUTPUT);// ����������¼��ID
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_txjnml_clyx_add(?p1,?p2,?p3,?p4)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);

		try {
			ret = spx.get(4).getLong();
			if (ret != 0) {
				BlobDataBean bdb = new BlobDataBean();
				bdb.setKey(String.valueOf(ret));
				bdb.setData(isb.getClyx());
				// Type = 1 ʹ������ID���޸�����,Type = 2 ʹ��YGDH��CLBM�����ֶ����޸�����
				bdb.setType((short) 1);
				UPDATE_TXJNML_BLOB(bdb);
			} else {
				throw new CallDbException("û����ص���!");
			}
		} catch (UnsupportedConversionException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ����Ŀ¼����ɨ��ͼƬ�ķ��� 2010��9��25��add by HD
	 * ����ef_jnml������������id��cl����,�ٵ���UPDATE_JNML_BLOB()��������ͼƬ
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_JNML_CLYX_ADD_BY_PROC(Object obj)
			throws CallDbException {
		DajbxxBean daBean = (DajbxxBean) obj;
		long ret = (long) 0;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx.add(new StringValue(daBean.getYgdh()));// Ԥ�鵵��
			spx.add(new StringValue(daBean.getYwlsh()));// ҵ����ˮ��
			spx.add(new StringValue(daBean.getF_type()));// ��������
			spx.add(new StringValue(daBean.getClbm()));// ���ϱ���
			spx.add(new StringValue(daBean.getCl()));// ��������
			spx.add(new StringValue(daBean.getCzy()));// ����Ա
			spx.add(new DateValue(XTool.Str2Date(ImageUploadBean.getData())));// ��������
			spx.add(new LongValue(ret), SqlParameterExt.SQL_OUTPUT);// ����������¼��ID
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_jnml_clyx_add(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);

		try {
			ret = spx.get(8).getLong();
			if (ret != 0) {
				BlobDataBean bdb = new BlobDataBean();
				bdb.setKey(String.valueOf(ret));
				bdb.setData(daBean.getClyx());
				// Type = 1 ʹ������ID���޸�����,Type = 2 ʹ��YGDH��CLBM�����ֶ����޸�����
				bdb.setType((short) 1);
				UPDATE_JNML_BLOB(bdb);
			} else {
				throw new CallDbException("����������Ϣ����û����ص���!");
			}
		} catch (UnsupportedConversionException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ����Ŀ¼����ɨ��ͼƬ�ķ��� 2010��9��25��add by HD
	 * ����ef_jnml������������id��cl����,�ٵ���UPDATE_JNML_BLOB()��������ͼƬ
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_JNML_CLYX_QUERY(Object obj) throws CallDbException {

		ImageScanBean isb = (ImageScanBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new LongValue(Long.valueOf(isb.getId())));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select clyx from ef_jnml where id = ?";
			String field = "clyx";
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			ResultSet rs = bo.query(sql);
			byte[] b = null;
			if (rs.next()) {
				if (databaseType == BasicOperation.DB_TYPE_ORACLE) {
					java.sql.Blob blob = rs.getBlob(field);
					b = blob.getBytes(1, (int) blob.length()); // ��BLObȡ���ֽ�������
				} else {
					b = rs.getBytes(field);
				}
			}
			isb.setClyx(b);
		} catch (Exception e) {
			throw new CallDbException("��ȡBLOB����ʧ�ܣ�");
		}
	}

	/**
	 * �������Ŀ¼����ɨ��ͼƬ�ķ��� 2011��4��21��add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_TXJNML_CLYX_QUERY(Object obj) throws CallDbException {

		ImageScanBean isb = (ImageScanBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new LongValue(Long.valueOf(isb.getId())));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select clyx from ef_txjnml where id = ?";
			String field = "clyx";
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			ResultSet rs = bo.query(sql);
			byte[] b = null;
			if (rs.next()) {
				if (databaseType == BasicOperation.DB_TYPE_ORACLE) {
					java.sql.Blob blob = rs.getBlob(field);
					b = blob.getBytes(1, (int) blob.length()); // ��BLObȡ���ֽ�������
				} else {
					b = rs.getBytes(field);
				}
			}
			isb.setClyx(b);
		} catch (Exception e) {
			throw new CallDbException("��ȡBLOB����ʧ�ܣ�");
		}
	}

	/**
	 * ����������Ϣ���ѯ���� by id
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	@SuppressWarnings("unchecked")
	public List openDA_DAJBXX_QUERY(Object obj) throws CallDbException {
		DajbxxBean daBean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new LongValue(daBean.getId()));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select * from ef_dajbxx where id = ?";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("����������Ϣ��ѯʧ�ܣ�");
		}
	}

	// ������
	public List openDA_ZZBB(Object obj) throws CallDbException {
		DajbxxBean daBean = (DajbxxBean) obj;
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CallDbException("�������Ϸ�!" + e.getMessage());
		}
		try {
			String sql = "select count(*),f_type from ef_dajbxx group by f_type";
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("����������Ϣ��ѯʧ�ܣ�");
		}
	}

	/**
	 * ��ѯ�����б�Ĺ��÷��� 2011-8-5 wl
	 * 
	 * @param obj
	 * @return
	 * @throws CallDbException
	 */
	public List<Row> openDA_ONLY_COMBOX(Object obj) throws CallDbException {
		ComboxBean comboxBean = (ComboxBean) obj;
		String sql = "select " + comboxBean.getDisplay() + " as display,"
				+ comboxBean.getValue() + " as value from "
				+ comboxBean.getTblName();
		try {
			return bo.queryToList(sql);
		} catch (Exception e) {
			xlog.debug(e.getMessage());
			throw new CallDbException("�����б��ѯʧ�ܣ�");
		}
	}

	public void executeDA_WS_SAVE(Object obj) throws CallDbException {
		WsdaBean bean = (WsdaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "p_dajbxx_ws_add(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?p9,?p10,?p11)";
		int ret = 0;
		try {
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(bean.getTm()));
			spx.add(new StringValue(bean.getYjml()));
			spx.add(new StringValue(bean.getEjml()));
			spx.add(new StringValue(bean.getSjml()));
			spx.add(new StringValue(bean.getZrz()));
			spx.add(new StringValue(bean.getZrbm()));
			spx.add(new StringValue(bean.getCjr()));
			spx.add(new StringValue(bean.getGlb()));
			spx.add(new IntValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			ret = spx.get(11).getInt();
		} catch (Exception err) {
			throw new CallDbException("���鵵���������!" + err.getMessage());
		}
		if (ret == 1) {
			throw new CallDbException("�����Ѵ���!", ret);
		}
	}

	/**
	 * ���ӵ���ϵͳ ������������ķ��� 2011��3��7�� gjf ��da����ef_ts_save������Ӧ �޸� 2011-3-11 by wl
	 * 
	 * @param bean
	 * @return
	 */
	public void executeDA_TS_SAVE(Object obj) throws CallDbException {
		TsdaBean bean = (TsdaBean) obj;
		short ret = VL_THREE;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "P_DAJBXX_TS_ADD(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8)";
		try {
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(bean.getTm()));
			spx.add(new StringValue(bean.getZtlb()));
			spx.add(new StringValue(bean.getZrz()));
			spx.add(new StringValue(bean.getCjr()));
			spx.add(new StringValue(bean.getGlb()));//��������
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			ret = spx.get(8).getShort();
		} catch (Exception err) {
			throw new CallDbException("�������ӳ���!" + err.getMessage());
		}
	}

	/**
	 * ����������Ϣ���ӵķ���
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_ARCHIVE_SAVE(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "P_DAJBXX_ADD(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?p9,?p10)";
		short ret = VL_THREE;
		try {
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(bean.getTjr()));
			spx.add(new StringValue(bean.getTjrbm()));
			spx.add(new StringValue(bean.getF_state()));
			spx.add(new StringValue("����"));// ��������
			spx.add(new StringValue(bean.getGh()));
			spx.add(new StringValue(bean.getHh()));
			// spx.add(new StringValue(bean.getSxh()));
			// spx.add(new IntValue(bean.getBcnx()));
			// spx.add(new StringValue(bean.getBmjb()));
			// spx.add(new StringValue(bean.getCfwz()));
			spx.add(new DateValue(XTool.Str2Date(DateUtils.getCurDate())));
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			ret = spx.get(10).getShort();
		} catch (Exception err) {
			throw new CallDbException("�������Ӳ��ɹ�!" + err.getMessage());
		}
		if (ret == VL_THREE) {
			throw new CallDbException("�����Ѵ���!", ret);
		} else if (ret == VL_FOUR) {
			throw new CallDbException("�����������ʹ���!!", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_DKDA_ADD]�������Ӳ��ɹ�!!", ret);
		}
	}

	/**
	 * ����������Ϣ��ѯ�ķ��� 2010��10��12�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	@SuppressWarnings("unchecked")
	public List openDA_DAJBXX_CHECK(Object obj) throws CallDbException {
		DajbxxBean daBean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(daBean.getYgdh()));
			spx.add(new StringValue(daBean.getYwlsh()));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select count(*) as dasum from ef_dajbxx where ygdh = ? and ywlsh = ?";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("����������Ϣ���ʧ�ܣ�");
		}

	}

	/**
	 * �����������Ϣ��ѯ�ķ��� 2011��4��20�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	@SuppressWarnings("unchecked")
	public List openDA_TXDAJBXX_CHECK(Object obj) throws CallDbException {
		DajbxxBean daBean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(daBean.getYgdh()));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = " select count(*) as dasum from ef_txdajbxx where ygdh = ? ";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("����������Ϣ���ʧ�ܣ�");
		}

	}

	/**
	 * ����������Ϣ���ӵķ��� 2010��10��12�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	@SuppressWarnings("unchecked")
	public List openDA_DAJBXX_CHECK_FOR_TXDA(Object obj) throws CallDbException {
		TxdajbxxBean daBean = (TxdajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(daBean.getYgdh()));
			spx.add(new StringValue("����"));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = " select count(*) as dasum from ef_dajbxx where ygdh = ? and f_type = ? ";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("����������Ϣ���FOR TXDAʧ�ܣ�");
		}

	}

	/**
	 * �����������Ϣ���ӵķ��� 2011��4��20�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_TXDKDA_ADD(Object obj) throws CallDbException {
		TxdajbxxBean dabean = (TxdajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {
			spx.add(new StringValue(dabean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(dabean.getTjrbm()));// �ύ�˱���
			spx.add(new StringValue(dabean.getTjr()));// �ύ��
			spx.add(new DateValue(XTool.Str2Date(dabean.getTjrq())));// �ύ����
			spx.add(new IntValue(dabean.getClfs())); // ���Ϸ���
			spx.add(new StringValue(dabean.getF_type()));// ��������
			spx.add(new StringValue(dabean.getTxqzh()));// ����Ȩ֤��
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_txdkda_add(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(8).getShort();
		} catch (Exception err) {
			throw new CallDbException("����Ȩ֤�������Ӳ��ɹ�!" + err.getMessage());
		}
		if (ret == VL_THREE) {
			throw new CallDbException("û�й����Ĵ����!", ret);
		} else if (ret == VL_FOUR) {
			throw new CallDbException("����Ȩ֤�����Ѵ���!!", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_TXDKDA_ADD]�������Ӳ��ɹ�!!", ret);
		}
	}

	/**
	 * �����������Ϣ���ӵķ��� 2010��10��12�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_DKDA_ADD(Object obj) throws CallDbException {
		DajbxxBean dabean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {
			spx.add(new StringValue(dabean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(dabean.getYwlsh()));// ҵ����ˮ��
			spx.add(new StringValue(dabean.getF_type()));// ��������
			spx.add(new StringValue(dabean.getCzy()));// ����Ա
			spx.add(new DateValue(XTool.Str2Date(ImageUploadBean.getData())));// �������ڻ�õ�ǰ����
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_dkda_add(?p1,?p2,?p3,?p4,?p5,?p6)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(6).getShort();
			System.out.println(ret+"=======retsfdfsd�صص�����");
		} catch (Exception err) {
			throw new CallDbException("��������Ӳ��ɹ�!" + err.getMessage());
		}
		if (ret == VL_THREE) {
			throw new CallDbException("���ڵ�������ͬ�ĵ���!", ret);
		} else if (ret == VL_FOUR) {
			throw new CallDbException("û���ҵ�����������Ϣ!", ret);
		} else if (ret == VL_FIVE) {
			throw new CallDbException("������ͬ��ҵ�񵵰�!", ret);
		} else if (ret == VL_EIGHT) {
			throw new CallDbException("�������ʹ���", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_DKDA_ADD]�������Ӳ��ɹ�!!", ret);
		}
	}

	/**
	 * ¥�̵���������Ϣ���ӵķ��� 2011��3��7�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_LPDA_ADD(Object obj) throws CallDbException {
		DajbxxBean dabean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {
			spx.add(new StringValue(dabean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(dabean.getYwlsh()));// ҵ����ˮ��
			spx.add(new StringValue(dabean.getF_type()));// ��������
			spx.add(new StringValue(dabean.getCzy()));// ����Ա
			spx.add(new DateValue(XTool.Str2Date(ImageUploadBean.getData())));// �������ڻ�õ�ǰ����
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_lpda_add(?p1,?p2,?p3,?p4,?p5,?p6)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(6).getShort();
		} catch (Exception err) {
			throw new CallDbException("¥�̵������Ӳ��ɹ�!" + err.getMessage());
		}
		if (ret == VL_THREE) {
			throw new CallDbException("���ڵ�������ͬ�ĵ���!", ret);
		} else if (ret == VL_FOUR) {
			throw new CallDbException("û���ҵ�����������Ϣ!", ret);
		} else if (ret == VL_FIVE) {
			throw new CallDbException("������ͬ��ҵ�񵵰�!", ret);
		} else if (ret == VL_EIGHT) {
			throw new CallDbException("�������ʹ���", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_LPDA_ADD]�������Ӳ��ɹ�!!", ret);
		}
	}

	/**
	 * �鼯��λ����������Ϣ���ӵķ��� 2011��2��17�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_DWDA_ADD(Object obj) throws CallDbException {
		DajbxxBean dabean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {
			spx.add(new StringValue(dabean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(dabean.getYwlsh()));// ҵ����ˮ��
			spx.add(new StringValue(dabean.getF_type()));// ��������
			spx.add(new StringValue(dabean.getCzy()));// ����Ա
			spx.add(new DateValue(XTool.Str2Date(ImageUploadBean.getData())));// �������ڻ�õ�ǰ����
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_dwda_add(?p1,?p2,?p3,?p4,?p5,?p6)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(6).getShort();
		} catch (Exception err) {
			throw new CallDbException("��λ�������Ӳ��ɹ�!" + err.getMessage());
		}
		if (ret == VL_THREE) {
			throw new CallDbException("���ڵ�������ͬ�ĵ���!", ret);
		} else if (ret == VL_FOUR) {
			throw new CallDbException("û���ҵ�����������Ϣ!", ret);
		} else if (ret == VL_FIVE) {
			throw new CallDbException("������ͬ��ҵ�񵵰�!", ret);
		} else if (ret == VL_EIGHT) {
			throw new CallDbException("�������ʹ���", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_DWDA_ADD]�������Ӳ��ɹ�!!", ret);
		}
	}

	/**
	 * �鼯���˵���������Ϣ���ӵķ��� 2011��2��18�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_GJDA_ADD(Object obj) throws CallDbException {
		DajbxxBean dabean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {
			spx.add(new StringValue(dabean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(dabean.getYwlsh()));// ҵ����ˮ��
			spx.add(new StringValue(dabean.getF_type()));// ��������
			spx.add(new StringValue(dabean.getCzy()));// ����Ա
			spx.add(new DateValue(XTool.Str2Date(ImageUploadBean.getData())));// �������ڻ�õ�ǰ����
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_gjda_add(?p1,?p2,?p3,?p4,?p5,?p6)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(6).getShort();
		} catch (Exception err) {
			throw new CallDbException("�鼯�������Ӳ��ɹ�!" + err.getMessage());
		}
		if (ret == VL_THREE) {
			throw new CallDbException("���ڵ�������ͬ�ĵ���!", ret);
		} else if (ret == VL_FOUR) {
			throw new CallDbException("û���ҵ�����������Ϣ!", ret);
		} else if (ret == VL_FIVE) {
			throw new CallDbException("������ͬ��ҵ�񵵰�!", ret);
		} else if (ret == VL_EIGHT) {
			throw new CallDbException("�������ʹ���", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_GJDA_ADD]�������Ӳ��ɹ�!!", ret);
		}
	}

	/**
	 * �鼯֧ȡ����������Ϣ���ӵķ��� 2011��2��21�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_ZQDA_ADD(Object obj) throws CallDbException {
		DajbxxBean dabean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {
			spx.add(new StringValue(dabean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(dabean.getYwlsh()));// ҵ����ˮ��
			spx.add(new StringValue(dabean.getF_type()));// ��������
			spx.add(new StringValue(dabean.getCzy()));// ����Ա
			spx.add(new DateValue(XTool.Str2Date(ImageUploadBean.getData())));// �������ڻ�õ�ǰ����
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_zqda_add(?p1,?p2,?p3,?p4,?p5,?p6)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(6).getShort();
		} catch (Exception err) {
			throw new CallDbException("֧ȡ�������Ӳ��ɹ�!" + err.getMessage());
		}
		if (ret == VL_THREE) {
			throw new CallDbException("���ڵ�������ͬ�ĵ���!", ret);
		} else if (ret == VL_FOUR) {
			throw new CallDbException("û���ҵ�����������Ϣ!", ret);
		} else if (ret == VL_FIVE) {
			throw new CallDbException("������ͬ��ҵ�񵵰�!", ret);
		} else if (ret == VL_EIGHT) {
			throw new CallDbException("�������ʹ���", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_ZQDA_ADD]�������Ӳ��ɹ�!!", ret);
		}
	}

//	/**
//	 * ����Ŀ¼��Ϣ��ѯ�ķ��� 2010��10��12�� add by HD
//	 * 
//	 * 2011��7��6�� chg by HD
//	 * 
//	 * * 2011��7��11�� chg by HD
//	 * 
//	 * @param obj
//	 * @throws CallDbException
//	 */
//
//	@SuppressWarnings("unchecked")
//	public List openDA_JNML_CLYX_QUERY_FOR_OLD_APP(Object obj)
//			throws CallDbException {
//
//		String dabh = openDA_DABH_QUERY(obj);
//
//		DajbxxBean daBean = (DajbxxBean) obj;
//		System.out.println(daBean.getF_type()+"==========��������������������");
//		SqlParameterExt spx = null;
//		String f_type = daBean.getF_type();
//		if(f_type==null||f_type.length()==0){
//			f_type=daBean.getType();
//		}
//		spx = new SqlParameterExt();
//
//		try {
//			spx = new SqlParameterExt();
//			spx.add(new StringValue(f_type));// ��������
//			spx.add(new StringValue(daBean.getYgdh()));// Ԥ�鵵��
//		} catch (Exception err) {
//			throw new CallDbException("�������Ϸ�!" + err.getMessage());
//		}
//
//		String sql = "";
//
//		/*
//		 * ef_dalx���浵������,1-��λ,2-�鼯,3-֧ȡ,4-����,5-¥��
//		 * 
//		 * 2011��7��21�� chg by hd
//		 * 
//		 * 2011��7��25�� chg by hd
//		 */
//		if (dabh == null || "".equals(dabh.trim())) {
//			if ("4".equals(daBean.getF_type())
//					|| "3".equals(daBean.getF_type())) {
//				return null;
//			}
//			sql = "select id, ygdh, clbm, cl, zt, czfs, F_CREATOR_NAME, F_CREATOR_DEPT_TIME, F_UPDATER_NAME, F_UPDATER_TIME , ( select f_type from ef_dalx where id = ? ) as f_type from ef_jnml where ygdh = ? and zt = '����' order by clbm";
//		} else {
//			sql = "select id, ygdh, clbm, cl, zt, czfs, F_CREATOR_NAME, F_CREATOR_DEPT_TIME, F_UPDATER_NAME, F_UPDATER_TIME , ( select f_type from ef_dalx where id = ? ) as f_type from ef_jnml where ygdh = ? and zt = '����' and id in ( "
//					+ dabh + " ) order by clbm";
//		}
//
//		try {
//			bo.setSqlParameterExt(spx);
//			return bo.queryToList(sql);
//		} catch (Exception e) {
//			throw new CallDbException("����Ŀ¼��Ϣ���ʧ�ܣ�");
//		}
//	}
	
	
	
	
	/**
	 * ����Ŀ¼��Ϣ��ѯ�ķ��� 2010��10��12�� add by HD
	 * 
	 * 2011��7��6�� chg by HD
	 * 
	 * * 2011��7��11�� chg by HD
	 * 
	 * 2012��3��7�� chg by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	@SuppressWarnings("unchecked")
	public List openDA_JNML_CLYX_QUERY_FOR_OLD_APP(Object obj)
			throws CallDbException {

		DajbxxBean daBean = (DajbxxBean) obj;

		SqlParameterExt spx = null;

		spx = new SqlParameterExt();

		String sql = "";

		/*
		 * ef_dalx���浵������,1-��λ,2-�鼯,3-֧ȡ,4-����,5-¥��
		 * 
		 * 2011��7��21�� chg by hd
		 * 
		 * 2011��7��25�� chg by hd
		 * 
		 * 2012��3��7�� chg by hd
		 */
		if ("4".equals(daBean.getF_type()) || "3".equals(daBean.getF_type())) {

			try {
				spx = new SqlParameterExt();
				spx.add(new StringValue(daBean.getF_type()));// ��������
				spx.add(new StringValue(daBean.getYgdh()));// Ԥ�鵵��
				spx.add(new StringValue(daBean.getYgdh()));// Ԥ�鵵��
				spx.add(new StringValue(daBean.getYwlsh()));// ҵ����ˮ��
			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}

			sql = "select id, ygdh, clbm, cl, zt, czfs, F_CREATOR_NAME, F_CREATOR_DEPT_TIME, F_UPDATER_NAME, F_UPDATER_TIME , ( select f_type from ef_dalx where id = ? ) as f_type from ef_jnml where ygdh = ? and id in ( select jnml_id from ef_dabh where ygdh = ? and ywlsh = ? ) and (zt = '����' or zt = '����') order by clbm";

		} else {

			try {
				spx = new SqlParameterExt();
				spx.add(new StringValue(daBean.getF_type()));// ��������
				spx.add(new StringValue(daBean.getYgdh()));// Ԥ�鵵��
			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}

			sql = "select id, ygdh, clbm, cl, zt, czfs, F_CREATOR_NAME, F_CREATOR_DEPT_TIME, F_UPDATER_NAME, F_UPDATER_TIME , ( select f_type from ef_dalx where id = ? ) as f_type from ef_jnml where ygdh = ? and (zt = '����' or zt = '����') order by clbm";

		}

		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("����Ŀ¼��Ϣ���ʧ�ܣ�");
		}
	}
	
	

	/**
	 * ��ѯ������ŵķ��� 2011��7��11�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	@SuppressWarnings("unchecked")
	public String openDA_DABH_QUERY(Object obj) throws CallDbException {

		DajbxxBean daBean = (DajbxxBean) obj;

		String f_type = daBean.getF_type();
		if(f_type==null||f_type.length()==0){
			f_type=daBean.getType();
		}
		System.out.println(f_type+"������� ��� ��������");
		String tablename = "";

		java.util.List list = null;

		String dabh = "";

		if ("4".equals(f_type)) {
			tablename = "ef_dkda";
		} else if ("3".equals(f_type)) {
			tablename = "ef_zqda";
		} else {
			return dabh;
		}

		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(daBean.getYgdh()));
			spx.add(new StringValue(daBean.getYwlsh()));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select dabh from " + tablename
					+ " where ygdh = ? and ywlsh = ?";

			bo.setSqlParameterExt(spx);

			list = bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("������Ų�ѯʧ�ܣ�");
		}

		if (list != null && list.size() > 0) {
			Row row = (Row) list.get(0);
			try {
				dabh = row.getTrimString("dabh");
				System.out.println(dabh+"-------------------dabh");
			} catch (NoSuchColumnException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedConversionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dabh;
	}

	/**
	 * ���������Ŀ¼��Ϣ��ѯ�ķ��� 2011��4��21�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	@SuppressWarnings("unchecked")
	public List openDA_TXJNML_CLYX_QUERY_FOR_OLD_APP(Object obj)
			throws CallDbException {
		TxdajbxxBean daBean = (TxdajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(daBean.getYgdh()));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select id,ygdh,clbm,cl from ef_txjnml where ygdh = ? order by clbm";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("����Ŀ¼��Ϣ���ʧ�ܣ�");
		}
	}

	/**
	 * ���˹鼯�������ϸ��õ������֧ȡҵ����
	 * 
	 * 2011��7��12�� add by HD
	 * 
	 * 2012��3��21�� chg by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_GJDA_TO_YW(Object obj) throws CallDbException {

		DajbxxBean dabean = (DajbxxBean) obj;

		SqlParameterExt spx = new SqlParameterExt();

		short ret = VL_THREE;

		try {

			spx.add(new StringValue(dabean.getYgdh())); // p1Ԥ�鵵��
			spx.add(new StringValue(dabean.getYwlsh())); // p2ҵ����ˮ��
			spx.add(new StringValue(dabean.getF_type())); // p3��������

			spx.add(new StringValue(dabean.getCzy())); // p4����Ա
			spx.add(new DateValue(XTool.Str2Date(ImageUploadBean.getData())));// p5�������ڻ�õ�ǰ����

			spx.add(new LongValue(dabean.getId())); // p6����id

			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// p7����ֵ,0�ɹ�
			// <>0ʧ��

		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_gjda_to_yw(?p1,?p2,?p3,?p4,?p5,?p6,?p7)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(7).getShort();
		} catch (Exception err) {
			throw new CallDbException("�������ò���ʧ��!" + err.getMessage());
		}
		if (ret == VL_EIGHT) {
			throw new CallDbException("����ĵ�������!", ret);
		} else if (ret == VL_THREE) {
			throw new CallDbException("���ڵ�������ͬ�ĵ���!", ret);
		} else if (ret == VL_FOUR) {
			throw new CallDbException("û���ҵ�����������Ϣ!", ret);
		} else if (ret == VL_FIVE) {
			throw new CallDbException("������ͬ��ҵ�񵵰�!", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_GJDA_TO_YW]�����ύ���ɹ�!!", ret);
		}
		//2012��3��21�� chg by hd
		// executeDA_DABH_FORMAT(dabean);
	}

	/**
	 * ���������,Ŀǰ֧��ef_dkda��ef_zqda
	 * 
	 * 2011��7��12�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	@SuppressWarnings("unchecked")
	public void executeDA_DABH_FORMAT(Object obj) throws CallDbException {

		DajbxxBean dabean = (DajbxxBean) obj;

		java.util.List rows = null;

		SqlParameterExt spx = null;

		String tablenameString = "";
		String dabh = "";
		String dabhArr[] = null;

		if ("4".equals(dabean.getF_type())) {
			tablenameString = "ef_dkda";
		} else if ("3".equals(dabean.getF_type())) {
			tablenameString = "ef_zqda";
		} else {
			return;
		}
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(dabean.getYgdh()));
			spx.add(new StringValue(dabean.getYwlsh()));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = " select dabh from " + tablenameString
					+ " where ygdh = ? and ywlsh = ? ";

			bo.setSqlParameterExt(spx);

			rows = bo.queryToList(sql);

			if (rows != null && rows.size() > 0) {
				Row row = (Row) rows.get(0);
				dabh = row.getTrimString("dabh");
				if ("".equals(dabh)) {
					return;
				} else {
					dabhArr = dabh.split(",");
					for (int i = 0; i < dabhArr.length; i++) {
						dabhArr[i] = dabhArr[i].trim();
					}
					dabh = "";
					TreeSet<String> ts = new TreeSet<String>();
					ts.addAll(java.util.Arrays.asList(dabhArr));
					Iterator<String> tsi = ts.iterator();
					while (tsi.hasNext()) {
						if ("".equals(dabh.trim())) {
							dabh += tsi.next().trim();
						} else {
							dabh += "," + tsi.next().trim();
						}
					}
				}
			} else {
				return;
			}

		} catch (Exception e) {
			throw new CallDbException("������Ų�ѯʧ�ܣ�");
		}

		if (dabh == null || "".equals(dabh.trim())) {
			return;
		} else {
			try {
				spx = new SqlParameterExt();
				spx.add(new StringValue(dabh));
				spx.add(new StringValue(dabean.getYgdh()));
				spx.add(new StringValue(dabean.getYwlsh()));
			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}
			String sql = " update " + tablenameString
					+ " set dabh = ? where ygdh = ? and ywlsh = ? ";
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.update(sql);
			bo.clearParameters();
			if (databaseType != BasicOperation.DB_TYPE_DB2) {
				bo.execute("commit");
			}
		}
	}

	/*
	 * ����������Ϣ���ӵķ��� 2010��10��12�� add by HD
	 * 
	 * @param obj
	 * 
	 * @throws CallDbException
	 */

	public void executeDA_DKDA_CHG(Object obj) throws CallDbException {
		DkdaBean dabean = (DkdaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {

			spx.add(new StringValue(dabean.getYgdh())); // p1Ԥ�鵵��
			spx.add(new StringValue(dabean.getYwlsh())); // p2ҵ����ˮ��

			spx.add(new StringValue(dabean.getCbwd())); // p3�а�����
			spx.add(new StringValue(dabean.getCbwdbm())); // p4�а��������
			spx.add(new StringValue(dabean.getGjd())); // p5�鼯��
			spx.add(new StringValue(dabean.getGjdbm())); // p6�鼯�����

			spx.add(new StringValue(dabean.getZgxm())); // p6ְ������
			spx.add(new StringValue(dabean.getZgzh())); // p7ְ���˺�
			spx.add(new StringValue(dabean.getZjhm())); // p8֤������
			spx.add(new StringValue(dabean.getXb())); // p9�Ա�
			spx.add(new StringValue(dabean.getDw())); // p10��λ����

			spx.add(new StringValue(dabean.getDwzh())); // p11��λ�˺�
			spx.add(new StringValue(dabean.getDkhtbh())); // p12�����ͬ���
			spx.add(new IntValue(dabean.getDknx())); // p13��������
			spx.add(new DateValue(XTool.Str2Date((dabean.getDkhtrq())))); // p14�����ͬ����
			spx.add(new DateValue(XTool.Str2Date((dabean.getFkrq())))); // p15�ſ�����

			spx.add(new StringValue(dabean.getFkyh())); // p16�ſ�����
			spx.add(new StringValue(dabean.getGflx())); // p17��������
			spx.add(new StringValue(dabean.getGfxxdz())); // p18������ϸ��ַ

			spx.add(new StringValue(dabean.getTjrbm())); // p19�ύ��ID
			spx.add(new StringValue(dabean.getTjr())); // p20�ύ��
			spx.add(new DateValue(XTool.Str2Date((dabean.getTjrq())))); // p21�ύ����
			spx.add(new IntValue(dabean.getClfs())); // p22���Ϸ���
			spx.add(new StringValue(dabean.getF_state())); // p23����״̬

			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// p24����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_dkda_chg(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?p9,?p10,?p11,?p12,?p13,?p14,?p15,?p16,?p17,?p18,?p19,?p20,?p21,?p22,?p23,?p24,?p25)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(25).getShort();
		} catch (Exception err) {
			throw new CallDbException("������ύ���ɹ�!" + err.getMessage());
		}
		if (ret == VL_FOUR) {
			throw new CallDbException("����������Ϣ������!", ret);
		} else if (ret == VL_SEVEN) {
			throw new CallDbException("�����������!!", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_DKDA_CHG]�����ύ���ɹ�!!", ret);
		}

	}

	/**
	 * ¥�̵���������Ϣ���ӵķ��� 2011��3��7�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_LPDA_CHG(Object obj) throws CallDbException {
		LpdaBean dabean = (LpdaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {

			spx.add(new StringValue(dabean.getYgdh())); // p1Ԥ�鵵��
			spx.add(new StringValue(dabean.getYwlsh())); // p2ҵ����ˮ��

			spx.add(new StringValue(dabean.getCbwd())); // p3�а�����
			spx.add(new StringValue(dabean.getCbwdbm())); // p4�а��������

			spx.add(new StringValue(dabean.getGjd())); // p5�鼯��
			spx.add(new StringValue(dabean.getGjdbm())); // p6�鼯�����

			spx.add(new StringValue(dabean.getXm())); // p6��Ŀ����
			spx.add(new StringValue(dabean.getXmbh())); // p7��Ŀ���

			spx.add(new StringValue(dabean.getTjrbm())); // p9�ύ��ID
			spx.add(new StringValue(dabean.getTjr())); // p10�ύ��
			spx.add(new DateValue(XTool.Str2Date((dabean.getTjrq())))); // p11�ύ����

			spx.add(new IntValue(dabean.getClfs())); // p12���Ϸ���
			spx.add(new StringValue(dabean.getF_state())); // p13����״̬

			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// p14����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_lpda_chg(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?p9,?p10,?p11,?p12,?p13,?p14)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(14).getShort();
		} catch (Exception err) {
			throw new CallDbException("¥�̵����ύ���ɹ�!" + err.getMessage());
		}
		if (ret == VL_FOUR) {
			throw new CallDbException("�����ڵ�����Ϣ!", ret);
		} else if (ret == VL_SEVEN) {
			throw new CallDbException("ҵ�񵵰�������!", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_LPDA_CHG]�����ύ���ɹ�!!", ret);
		}

	}

	/**
	 * �鼯��λ����������Ϣ���ӵķ��� 2011��2��17�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_DWDA_CHG(Object obj) throws CallDbException {
		DwdaBean dabean = (DwdaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {

			spx.add(new StringValue(dabean.getYgdh())); // p1Ԥ�鵵��
			spx.add(new StringValue(dabean.getYwlsh())); // p2ҵ����ˮ��

			spx.add(new StringValue(dabean.getCbwd())); // p3�а�����
			spx.add(new StringValue(dabean.getCbwdbm())); // p4�а��������
			spx.add(new StringValue(dabean.getGjd())); // p5�鼯��
			spx.add(new StringValue(dabean.getGjdbm())); // p6�鼯�����

			spx.add(new StringValue(dabean.getDw())); // p7��λ����
			spx.add(new StringValue(dabean.getDwzh())); // p8��λ�˺�
			spx.add(new StringValue(dabean.getDwdz())); // p9��λ��ַ
			spx.add(new StringValue(dabean.getCkzh())); // p10��λ��ַ
			spx.add(new DateValue(XTool.Str2Date((dabean.getKhrq())))); // p11��������

			spx.add(new StringValue(dabean.getGjzgybm())); // p12�鼯ר��Ա����
			spx.add(new StringValue(dabean.getGjzgy())); // p13�鼯ר��Ա
			spx.add(new StringValue(dabean.getZzjgdm())); // p14��֯��������

			spx.add(new StringValue(dabean.getTjrbm())); // p15�ύ��ID
			spx.add(new StringValue(dabean.getTjr())); // p16�ύ��
			spx.add(new DateValue(XTool.Str2Date((dabean.getTjrq())))); // p17�ύ����
			spx.add(new IntValue(dabean.getClfs())); // p18���Ϸ���
			spx.add(new StringValue(dabean.getF_state())); // p19����״̬

			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// p19����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_dwda_chg(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?p9,?p10,?p11,?p12,?p13,?p14,?p15,?p16,?p17,?p18,?p19,?p20)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(20).getShort();
		} catch (Exception err) {
			throw new CallDbException("��λ�����ύ���ɹ�!" + err.getMessage());
		}
		if (ret == VL_FOUR) {
			throw new CallDbException("�����ڵ�����Ϣ!", ret);
		} else if (ret == VL_SEVEN) {
			throw new CallDbException("ҵ�񵵰�������!", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_DWDA_CHG]�����ύ���ɹ�!!", ret);
		}

	}

	/**
	 * �鼯���˵���������Ϣ���ӵķ��� 2011��2��18�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_GJDA_CHG(Object obj) throws CallDbException {
		GjdaBean dabean = (GjdaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {

			spx.add(new StringValue(dabean.getYgdh())); // p1Ԥ�鵵��
			spx.add(new StringValue(dabean.getYwlsh())); // p2ҵ����ˮ��

			spx.add(new StringValue(dabean.getCbwd())); // p3�а�����
			spx.add(new StringValue(dabean.getCbwdbm())); // p4�а��������
			spx.add(new StringValue(dabean.getGjd())); // p5�鼯��
			spx.add(new StringValue(dabean.getGjdbm())); // p6�鼯�����

			spx.add(new StringValue(dabean.getDw())); // p6��λ����
			spx.add(new StringValue(dabean.getDwzh())); // p7��λ�˺�
			spx.add(new StringValue(dabean.getZgzh())); // p8ְ���˺�
			spx.add(new StringValue(dabean.getZgxm())); // p9ְ������
			spx.add(new DateValue(XTool.Str2Date((dabean.getKhrq())))); // p10��������

			spx.add(new DateValue(XTool.Str2Date((dabean.getXhrq())))); // p11��������
			spx.add(new StringValue(dabean.getXb())); // p12�Ա�
			spx.add(new StringValue(dabean.getZjhm())); // p13֤������

			spx.add(new StringValue(dabean.getTjrbm())); // p14�ύ��ID
			spx.add(new StringValue(dabean.getTjr())); // p15�ύ��
			spx.add(new DateValue(XTool.Str2Date((dabean.getTjrq())))); // p16�ύ����
			spx.add(new IntValue(dabean.getClfs())); // p17���Ϸ���
			spx.add(new StringValue(dabean.getF_state())); // p18����״̬

			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// p20����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_gjda_chg(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?p9,?p10,?p11,?p12,?p13,?p14,?p15,?p16,?p17,?p18,?p19,?p20)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(20).getShort();
		} catch (Exception err) {
			throw new CallDbException("�鼯�����ύ���ɹ�!" + err.getMessage());
		}
		if (ret == VL_FOUR) {
			throw new CallDbException("����������Ϣ������!", ret);
		} else if (ret == VL_SEVEN) {
			throw new CallDbException("�鼯����������!!", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_GJDA_CHG]�����ύ���ɹ�!!", ret);
		}

	}

	/**
	 * �鼯֧ȡ����������Ϣ���ӵķ��� 2011��2��21�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_ZQDA_CHG(Object obj) throws CallDbException {
		ZqdaBean dabean = (ZqdaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_THREE;
		try {

			spx.add(new StringValue(dabean.getYgdh())); // p1Ԥ�鵵��
			spx.add(new StringValue(dabean.getYwlsh())); // p2ҵ����ˮ��

			spx.add(new StringValue(dabean.getCbwd())); // p3�а�����
			spx.add(new StringValue(dabean.getCbwdbm())); // p4�а��������
			spx.add(new StringValue(dabean.getGjd())); // p5�鼯��
			spx.add(new StringValue(dabean.getGjdbm())); // p6�鼯�����

			spx.add(new StringValue(dabean.getDw())); // p7��λ����
			spx.add(new StringValue(dabean.getDwzh())); // p8��λ�˺�
			spx.add(new StringValue(dabean.getZgzh())); // p9ְ���˺�
			spx.add(new StringValue(dabean.getZgxm())); // p10ְ������
			spx.add(new DateValue(XTool.Str2Date((dabean.getSczqrq())))); // p11�ϴ�֧ȡ����

			spx.add(new DateValue(XTool.Str2Date((dabean.getZqrq())))); // p12֧ȡ����
			spx.add(new StringValue(dabean.getZqyt())); // p13֧ȡ��;
			spx.add(new StringValue(dabean.getZjhm())); // p14֤������
			spx.add(new StringValue(dabean.getTqlsh())); // p15��ǰ��ˮ��
			spx.add(new StringValue(dabean.getRzczy())); // p16���˲���Ա

			spx.add(new StringValue(dabean.getDjbh())); // p17���ݱ��
			spx.add(new StringValue(dabean.getFhczy())); // p18ҵ�񸴺˲���Ա
			spx.add(new DateValue(XTool.Str2Date((dabean.getSprq())))); // p19��������

			spx.add(new StringValue(dabean.getTjrbm())); // p20�ύ��ID
			spx.add(new StringValue(dabean.getTjr())); // p21�ύ��
			spx.add(new DateValue(XTool.Str2Date((dabean.getTjrq())))); // p22�ύ����
			spx.add(new IntValue(dabean.getClfs())); // p23���Ϸ���
			spx.add(new StringValue(dabean.getF_state())); // p24����״̬

			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// p25����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_zqda_chg(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?p9,?p10,?p11,?p12,?p13,?p14,?p15,?p16,?p17,?p18,?p19,?p20,?p21,?p22,?p23,?p24,?p25)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);
		try {
			ret = spx.get(25).getShort();
		} catch (Exception err) {
			throw new CallDbException("�鼯֧ȡ�����ύ���ɹ�!" + err.getMessage());
		}
		if (ret == VL_FOUR) {
			throw new CallDbException("����������Ϣ������!", ret);
		} else if (ret == VL_SEVEN) {
			throw new CallDbException("֧ȡ����������!!", ret);
		} else if (ret != VL_ZERO) {
			throw new CallDbException("[executeDA_ZQDA_CHG]�����ύ���ɹ�!!", ret);
		}

	}

	public void executeDA_BLOB(Object obj) throws CallDbException {
		BlobBean blobbean = (BlobBean) obj;

		String sql = "update EF_JNML set clyx = ?,cl=? where id = 1";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			spx.add(new BytesValue(blobbean.getData()));// ͼƬ
			spx.add(new StringValue(blobbean.getName()));
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.update(sql);
			bo.clearParameters();
			if (databaseType != BasicOperation.DB_TYPE_DB2) {
				bo.execute("commit");
			}
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[da_blob]����" + e.getMessage());
		}

	}

	public void executeDA_BLOB_QUERY(Object obj) throws CallDbException {
		BlobBean pmt = (BlobBean) obj;
		SqlParameterExt spx = null;
		// String sql = "select clyx,cl from EF_JNML where id =?";
		try {
			spx = new SqlParameterExt();
			spx.add(new LongValue(pmt.getId()));
			bo.setSqlParameterExt(spx);
			String sql = "";
			if (pmt.getTableName() != null && pmt.getTableName().length() > 0) {
				sql = "select f_content,cl from " + pmt.getTableName()
						+ " where id =?";
				ResultSet rs = bo.query(sql);
				if (rs.next()) {
					pmt.setName(rs.getString("cl"));
					if (databaseType == BasicOperation.DB_TYPE_ORACLE) {
						java.sql.Blob blob = rs.getBlob("f_content");
						pmt.setData(blob.getBytes(1, (int) blob.length())); // ��BLObȡ���ֽ�������
					} else {
						pmt.setData(rs.getBytes("f_content"));
					}
				}
			} else {
				sql = "select clyx,cl from EF_JNML where id =?";
				ResultSet rs = bo.query(sql);
				if (rs.next()) {
					pmt.setName(rs.getString("cl"));
					if (databaseType == BasicOperation.DB_TYPE_ORACLE) {
						java.sql.Blob blob = rs.getBlob("clyx");
						pmt.setData(blob.getBytes(1, (int) blob.length())); // ��BLObȡ���ֽ�������
					} else {
						pmt.setData(rs.getBytes("clyx"));
					}
				}
			}
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[da_blob]����" + e.getMessage());
		}

	}

	public void executeDA_TQCLBH(Object obj) throws CallDbException {
		BlobBean pmt = (BlobBean) obj;
		SqlParameterExt spx = null;
		String s = "";
		short ret = VL_ONE;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(" "), SqlParameterExt.SQL_OUTPUT); // p1
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT); // p2
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			s = "p_tqclbh(?p1,?p2)";
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(s);
			pmt.setName(spx.get(1).getString());
		} catch (Exception err) {
			throw new CallDbException("���ɹ�!" + err.getMessage());
		}
	}

	public void executeDA_QYBH(Object obj) throws CallDbException {
		BlobBean pmt = (BlobBean) obj;
		SqlParameterExt spx = null;
		String s = "";
		short ret = VL_ONE;
		try {
			spx = new SqlParameterExt();
			spx.add(new ShortValue(Short.parseShort(pmt.getName())));
			spx.add(new StringValue(" "), SqlParameterExt.SQL_OUTPUT); // p2
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT); // p3
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			s = "p_qybh(?p1,?p2,?p3)";
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(s);
			pmt.setName(spx.get(2).getString());
		} catch (Exception err) {
			throw new CallDbException("���ɹ�!" + err.getMessage());
		}
	}

	/**
	 * flex �����Ҳ�ѯ 2011-2-23 xy
	 */
	@SuppressWarnings("unchecked")
	public List openDA_DASH_QUERY(Object obj) throws CallDbException {
		// XndaBean daBean = (XndaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select dash from ef_dagsz group by dash";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��[openDA_DASH_QUERY]ʧ�ܣ�");
		}
	}

	/**
	 * flex �������ѯ 2011-2-23 xy
	 */
	@SuppressWarnings("unchecked")
	public List openDA_GH_QUERY(Object obj) throws CallDbException {
		XndaBean daBean = (XndaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select gh from ef_dagsz where dash='"
					+ daBean.getDash() + "' group by gh";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��[openDA_GH_QUERY]ʧ�ܣ�");
		}
	}

	/**
	 * flex ������ 2011-2-23 xy
	 */
	@SuppressWarnings("unchecked")
	public List openDA_HH_QUERY(Object obj) throws CallDbException {
		XndaBean daBean = (XndaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select hh from ef_dagsz where gh='" + daBean.getGh()
					+ "' and " + "dash='" + daBean.getDash() + "' group by hh";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��[openDA_HH_QUERY]ʧ�ܣ�");
		}
	}

	/**
	 * flex ������ 2011-2-23 xy
	 */
	@SuppressWarnings("unchecked")
	public List openDA_DAH_QUERY(Object obj) throws CallDbException {
		XndaBean daBean = (XndaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select dah from ef_dajbxx where hh='"
					+ daBean.getHh() + "' and gh='" + daBean.getGh()
					+ "' and dash='" + daBean.getDash() + "'";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��[openDA_HH_QUERY]ʧ�ܣ�");
		}
	}

	/**
	 * �����ҳ�ʼ�� 2011-5-31 wm
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_INIT(Object obj) throws CallDbException {
		InitDaBean daBean = (InitDaBean) obj;
		short ret = 0;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "P_ARCHIVE_INIT(?p1,?p2,?p3,?p4,?p5,?p6)";
		try {
			spx.add(new IntValue(daBean.getGhnum())); // ����������
			spx.add(new IntValue(daBean.getGrownum())); // ����������
			spx.add(new IntValue(daBean.getHhnum())); // �����и���
			spx.add(new IntValue(daBean.getMaxnum())); // ��������
			spx.add(new IntValue(daBean.getPhnum())); //��������
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_INPUT_OUTPUT);
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			ret = spx.get(6).getShort();
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}

	}

	/**
	 * flex ���⵵������ʽ����ӵ����� 2011-5-30 wm
	 */
	public void executeDA_STYLE(Object obj) throws CallDbException {
		DagszBean daBean = (DagszBean) obj;
		short ret = 0;
		String sql = "";
		SqlParameterExt spx = new SqlParameterExt();
		try {
			if ("add".equals(daBean.getBmjb())) {
				sql = "P_ADDDASH(?p1,?p2,?p3)"; // ��ӵ����� �ɹ�0 ʧ��1
				spx.add(new StringValue(daBean.getDash()));
				spx.add(new StringValue(daBean.getBum()));
				spx.add(new ShortValue(ret), SqlParameterExt.SQL_INPUT_OUTPUT);
				bo.setSqlParameterExt(spx);
				bo.executeProcedure(sql);
				ret = spx.get(3).getShort();
			} else if ("adddg".equals(daBean.getBmjb())) {
				sql = "P_ADDGH(?p1,?p2,?p3)"; // ��ӵ����� �ɹ�ret4 ʧ��5
				spx.add(new StringValue(daBean.getDash()));// �����Һ�
				spx.add(new StringValue(daBean.getDagh()));// ������
				spx.add(new ShortValue(ret), SqlParameterExt.SQL_INPUT_OUTPUT);
				bo.setSqlParameterExt(spx);
				bo.executeProcedure(sql);
				ret = spx.get(3).getShort();
			} else if ("addhh".equals(daBean.getBmjb())) {
				sql = "P_ADDHH(?p1,?p2,?p3,?p4,?p5)"; // ��ӵ����� �ɹ�ret4 ʧ��5
				spx.add(new StringValue(daBean.getDash()));// �����Һ�
				spx.add(new StringValue(daBean.getDagh())); // ���Ӻ�
				spx.add(new StringValue(daBean.getDagrow()));// �����к�
				spx.add(new StringValue(daBean.getDahh()));// ������
				spx.add(new ShortValue(ret), SqlParameterExt.SQL_INPUT_OUTPUT);
				bo.setSqlParameterExt(spx);
				bo.executeProcedure(sql);
				ret = spx.get(5).getShort();
			} else if ("delgh".equals(daBean.getBmjb())) {
				sql = "P_DELGH(?p1,?p2,?p3)"; // ɾ�������� �ɹ�ret4 ʧ��5
				spx.add(new StringValue(daBean.getDash())); // �����Һ�
				spx.add(new StringValue(daBean.getDagh())); // ���
				spx.add(new ShortValue(ret), SqlParameterExt.SQL_INPUT_OUTPUT);
				bo.setSqlParameterExt(spx);
				bo.executeProcedure(sql);
				ret = spx.get(3).getShort();
			} else if ("deldash".equals(daBean.getBmjb())) {
				sql = "P_DELDASH(?p1,?p2)"; // ɾ�������� �ɹ�ret4 ʧ��5
				spx.add(new StringValue(daBean.getDash()));
				spx.add(new ShortValue(ret), SqlParameterExt.SQL_INPUT_OUTPUT);
				bo.setSqlParameterExt(spx);
				bo.executeProcedure(sql);
				ret = spx.get(2).getShort();
			} else if ("dahSet".equals(daBean.getBmjb())) {
				sql = "P_XNUPDATEWZ(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8)"; // ����������
				// �ɹ�ret4
				// ʧ��5
				spx.add(new StringValue(daBean.getDash()));
				spx.add(new StringValue(daBean.getDagh()));
				spx.add(new StringValue(daBean.getDaph()));
				spx.add(new StringValue(daBean.getDagrow()));
				spx.add(new StringValue(daBean.getDahh()));
				spx.add(new StringValue(daBean.getDno()));
				spx.add(new StringValue(daBean.getF_type()));
				spx.add(new ShortValue(ret), SqlParameterExt.SQL_INPUT_OUTPUT);
				bo.setSqlParameterExt(spx);
				bo.executeProcedure(sql);
				ret = spx.get(8).getShort();
			}
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}

		if (ret == 1) {
			throw new CallDbException("���Ƚ��г�ʼ��");
		}

	}

	// ���ݲ���ID��ȡ���ű��
	public String getFNo(long did) throws CallDbException {
		String fno = "";
		String sql = "select F_NO from t_mk_sys_dept where ID=" + did;
		try {
			List<Row> rList = bo.queryToList(sql);
			Row row = rList.get(0);
			fno = row.getTrimString("F_NO");
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[getFNo]��������" + e.getMessage());
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fno;
	}

	// ����������Ϣ������combox 2011-7-19 wm
	public List<Row> openDA_COMBOX(Object obj) throws CallDbException {
		//@SuppressWarnings("unused");
		DagszBean z = (DagszBean) obj;
		String str = "select dash from ef_dagsz  where f_type = '"
				+ z.getF_type() + "' and bum ='" + z.getId()
				+ "' group by dash";
		try {
			return bo.queryToList(str);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_COMBOX]����" + e.getMessage());
		}

	}

	// ����������Ϣ������combox 2011-6-25 wm
	public List<Row> openDA_GHCOMBOX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;
		String str = "select gh from ef_dagsz where dash='" + z.getDash()
				+ "' and f_type='" + z.getF_type()
				+ "' and dabh='0' and bum ='" + z.getId() + "' group by gh";

		try {
			return bo.queryToList(str);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_GHCOMBOX]����" + e.getMessage());
		}
	}

	// ����������Ϣ��������combox 2011-6-25 wm
	public List<Row> openDA_GHROWCOMBOX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;

		String str = "select grow from ef_dagsz where dash='" + z.getDash()
				+ "'" + " and gh='" + z.getDagh() + "' and f_type='"
				+ z.getF_type() + "'" + "and dabh='0' and bum='" + z.getId()
				+ "' group by grow";

		try {
			return bo.queryToList(str);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_GHROWCOMBOX]����" + e.getMessage());
		}
	}

	// ����������Ϣ������combox 2011-2-23 wm
	public List<Row> openDA_HHCOMBOX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;

		String str = "select hh from ef_dagsz where dash='" + z.getDash()
				+ "' " + "and gh='" + z.getDagh() + "' and grow='"
				+ z.getDagrow() + "' and ph='"+z.getDaph()+"' and f_type='" + z.getF_type() + "' "
				+ "and dabh='0' and bum='" + z.getId() + "' group by hh order by TO_NUMBER(SUBSTR(hh,2,length(hh)-2))";

		try {
			return bo.queryToList(str);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_HHCOMBOX]����" + e.getMessage());
		}
	}

	// ���⵵����combox 2011-7-19 wm
	public List<Row> openDA_COMBOXX(Object obj) throws CallDbException {
		DagszBean daBean = (DagszBean) obj;
		String str = "select dash from ef_dagsz where bum='"+daBean.getId()+"' group by dash";

		try {
			return bo.queryToList(str);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_COMBOXX]����" + e.getMessage());
		}
	}
	
	// ���⵵����combox 2011-7-19 wm
	public List<Row> openDA_GHCOMBOXX(Object obj) throws CallDbException {
		DagszBean daBean = (DagszBean) obj;
		String str = "select dash,gh from ef_dagsz where dash='"
				+ daBean.getDash()+"' and bum='"+daBean.getId()
				+ "' group by gh,dash order by TO_NUMBER(SUBSTR(gh,2,length(gh)-2))";

		try {
			return bo.queryToList(str);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_GHCOMBOXX]����" + e.getMessage());
		}
	}

	//���⵵������combox 2012-9-4 wm
	public List<Row> openDA_PHCOMBOXX(Object obj) throws CallDbException{
		DagszBean z = (DagszBean) obj;
		String str = "select ph from ef_dagsz where dash='" + z.getDash()
				      + "'" + " and gh='" + z.getDagh() + "' and bum='" + z.getId()
				      +"' group by ph order by ph";
		try{
			return bo.queryToList(str);
		}catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_PHCOMBEOXX]����"+e.getMessage());
		}
	}
	// ���⵵������ 2011-7-19 wm
	public List<Row> openDA_GHROWCOMBOXX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;
		String str = "select grow from ef_dagsz where dash='" + z.getDash()
				+ "'" + " and gh='" + z.getDagh() + "' and ph='"+z.getDaph()+"' and bum='" + z.getId()
				+ "' group by grow order by grow";
		try {
			return bo.queryToList(str);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_GHROWCOMBOXX]����" + e.getMessage());
		}
	}

	// ���⵵����combox 2011-7-19 wm
	public List<Row> openDA_HHCOMBOXX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;

		String str = "select hh from ef_dagsz where dash='" + z.getDash()
				+ "' and gh='" + z.getDagh() + "' and grow='" + z.getDagrow()
				+ "' and ph='"+z.getDaph()+"' and bum='"+z.getId()
				+ "' group by hh order by TO_NUMBER(SUBSTR(hh,2,length(hh)-2))";

		try {
			return bo.queryToList(str);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_HHCOMBOXX]����" + e.getMessage());
		}
	}

	// �����е���˳���combox 2011-7-19 wm
	public List<Row> openDA_DNOCOMBOXX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;

		String sql = "select dno from ef_dagsz where dash='"+z.getDash()
					+"' and gh='"+z.getDagh()+"' and ph='"+z.getDaph()+"' and grow='"+z.getDagrow()
					+"' and hh='"+z.getDahh()+"'" +
				     " group by dno order by dno";

		try {
			return bo.queryToList(sql);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_DNOCOMBOXX]����" + e.getMessage());
		}
	}
	// ��ѯ�������еĵ�����Ϣ wm
	public List<Row> openDA_JBXXByHH(Object obj) throws CallDbException {
		DagszBean x = (DagszBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "select * from ef_dajbxx where dash =? and  gh = ? and ph = ? and grow=? and hh = ?";
		try {
			spx.add(new StringValue(x.getDash()));
			spx.add(new StringValue(x.getDagh()));
			spx.add(new StringValue(x.getDaph()));
			spx.add(new StringValue(x.getDagrow()));
			spx.add(new StringValue(x.getDahh()));
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_JBXXByHH]����" + e.getMessage());
		}

	}
	
	/*
	 * ʵ�嵵��������õķ���
	 */

	public void executeDA_EFSTBORROW(Object obj) throws CallDbException {
		JyjlBean bean = (JyjlBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "insert into EF_JYJL(DAH,JYR,JYRQ,DASH,GH,GROW,PH,HH,DJR,DALX,JYJS,JYMD,NHRQ,BCDZ,YGDH,SXH,JYBH,ID,DAZT,F_STATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			spx.add(new StringValue(bean.getDah()));
			spx.add(new StringValue(bean.getJyr()));
			spx.add(new DateValue(XTool.Str2Date((bean.getJyrq()))));
			spx.add(new StringValue(bean.getDash()));
			spx.add(new StringValue(bean.getGh()));
			spx.add(new StringValue(bean.getGrow()));
			spx.add(new StringValue(bean.getPh()));
			spx.add(new StringValue(bean.getHh()));
			spx.add(new StringValue(bean.getDjr()));
			spx.add(new StringValue(bean.getDalx()));
			spx.add(new IntValue(bean.getJyjs()));
			spx.add(new StringValue(bean.getJymd()));
			spx.add(new DateValue(XTool.Str2Date((bean.getNhrq()))));
			spx.add(new StringValue(bean.getBcdz()));
			spx.add(new StringValue(bean.getYgdh()));// Ԥ�鵵������
			spx.add(new IntValue(bean.getSxh()));
			spx.add(new StringValue(bean.getJybh()));
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getDazt()));
			spx.add(new StringValue(bean.getF_state()));

		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
		} catch (Exception err) {
			throw new CallDbException("ʵ�������Ϣ�������!" + err.getMessage());
		}
	}

	/*
	 * ʵ�嵵���黹���õķ���
	 */

	public void executeDA_EFSTGHBORROW(Object obj) throws CallDbException {
		JyjlBean bean = (JyjlBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "update EF_JYJL set GHRQ = ?,F_STATE=? where id = ?";
		try {
			spx.add(new DateValue(XTool.Str2Date((bean.getGhrq()))));
			spx.add(new StringValue(bean.getF_state()));
			spx.add(new LongValue(bean.getId()));

		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
		} catch (Exception err) {
			throw new CallDbException("�����黹��������!" + err.getMessage());
		}
	}

	/*
	 * public void executeDA_BLOB(Object obj) throws CallDbException { BlobBean
	 * blobbean = (BlobBean) obj;
	 * 
	 * String sql = "update EF_JNML set clyx = ?,cl=? where id = 1"; try {
	 * SqlParameterExt spx = new SqlParameterExt(); spx.add(new
	 * BytesValue(blobbean.getData()));// ͼƬ spx.add(new
	 * StringValue(blobbean.getName())); bo.clearParameters();
	 * bo.setSqlParameterExt(spx); bo.update(sql); bo.clearParameters(); if
	 * (databaseType != BasicOperation.DB_TYPE_DB2) { bo.execute("commit"); } }
	 * catch (Exception e) { throw new CallDbException("ִ�з���[da_blob]����" +
	 * e.getMessage()); } }
	 */

	/**
	 * ���ļ�¼����ɨ��ͼƬ�ķ��� ����ef_FILE������������id��cl����,�ٵ���UPDATE_JNML_BLOB_Entity()��������ͼƬ
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_JNML_CLYX_ADD_BY_PROC_Entity(Object obj)
			throws CallDbException {
		ImageScanBean isb = (ImageScanBean) obj;
		long ret = (long) 0;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx.add(new StringValue(isb.getYgdh()));// is.getYgdh()�õ�����ID
			spx.add(new StringValue(isb.getClbm()));// ���ϱ���
			spx.add(new StringValue(isb.getCl()));// ��������
			spx.add(new LongValue(ret), SqlParameterExt.SQL_OUTPUT);// ����������¼��ID
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_jnml_clyx_add_Entity(?p1,?p2,?p3,?p4)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);

		try {
			ret = spx.get(4).getLong();
			if (ret != 0) {
				BlobDataBean bdb = new BlobDataBean();
				bdb.setKey(String.valueOf(ret));
				bdb.setData(isb.getClyx());
				// Type = 1 ʹ������ID���޸�����,Type = 2 ʹ��YGDH��CLBM�����ֶ����޸�����
				bdb.setType((short) 1);
				UPDATE_JNML_BLOB_Entity(bdb);
			} else {
				throw new CallDbException("����������Ϣ����û����ص���!");
			}
		} catch (UnsupportedConversionException e) {
			e.printStackTrace();
		}

	}

	private void UPDATE_JNML_BLOB_Entity(Object obj) throws CallDbException {

		BlobDataBean pmt = null;
		SqlParameterExt spx = null;
		String s = "";
		try {
			pmt = (BlobDataBean) obj;
			spx = new SqlParameterExt();
			switch (pmt.getType()) {
			case 1:
				try {
					spx.add(new BytesValue(pmt.getData()));
					spx.add(new LongValue(Long.valueOf(pmt.getKey()))); // id ����
					// number����
				} catch (Exception err) {
					throw new CallDbException("�������Ϸ�!" + err.getMessage());
				}
				s = "update EF_FILE set F_CONTENT=? where id=?";
				break;
			case 2:
				try {
					spx.add(new BytesValue(pmt.getData()));
					spx.add(new StringValue(pmt.getKey()));
					spx.add(new StringValue(pmt.getKey1()));
				} catch (Exception err) {
					throw new CallDbException("�������Ϸ�!" + err.getMessage());
				}
				s = "update EF_FILE set F_CONTENT=? where ygdh=? and clbm=?";
				break;
			default:
				throw new Exception("δ����ĸ��²�����");
			}
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.update(s);
			bo.clearParameters();
			bo.execute("commit");
		} catch (Exception e) {
			xlog.debug(e.getMessage());
			throw new CallDbException("����EF_JNML.F_CONTENT[BLOB]ʧ�ܣ�");
		} finally {
			bo.closestmt();
		}
	}

	/**
	 * ���ټ�¼����ɨ��ͼƬ�ķ��� ����ef_FILE������������id��cl����,�ٵ���UPDATE_JNML_BLOB_Destroy()��������ͼƬ
	 * 2011��3��2�� by jcl ��������������������������������������
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_JNML_CLYX_ADD_BY_PROC_Destroy(Object obj)
			throws CallDbException {
		ImageScanBean isb = (ImageScanBean) obj;
		long res = (long) 0;
		SqlParameterExt spx = new SqlParameterExt();
		@SuppressWarnings("unused")
		SqlParameterExt sps = new SqlParameterExt();
		@SuppressWarnings("unused")
		String[] idString = isb.getYgdh().split(";");
		;
		String sql = "P_JNML_CLYX_ADD_DESTROY(?p1,?p2)";
		try {
			spx.add(new StringValue(isb.getCl()));// ��������
			spx.add(new LongValue(res), SqlParameterExt.SQL_OUTPUT);// ����������¼��ID
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			bo.clearParameters();
			res = spx.get(2).getLong();
		} catch (Exception err) {
			throw new CallDbException("ִ�д洢����!" + err.getMessage());
		}
		// ѭ������ÿ����¼��PID
		this.updateDestroy(res, isb);
		try {
			if (res != 0) {
				BlobDataBean bdb = new BlobDataBean();
				bdb.setKey(String.valueOf(res));
				bdb.setData(isb.getClyx());
				// Type = 1 ʹ������ID���޸�����,Type = 2 ʹ��YGDH��CLBM�����ֶ����޸�����
				bdb.setType((short) 1);
				UPDATE_JNML_BLOB_Entity(bdb);
			} else {
				throw new CallDbException("����������Ϣ����û����ص���!");
			}
		} catch (Exception ee) {
			throw new CallDbException("���²���Ӱ�����!" + ee.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private void UPDATE_JNML_BLOB_Destroy(Object obj) throws CallDbException {

		BlobDataBean pmt = null;
		SqlParameterExt spx = null;
		String s = "";
		try {
			pmt = (BlobDataBean) obj;
			spx = new SqlParameterExt();
			switch (pmt.getType()) {
			case 1:
				try {
					spx.add(new BytesValue(pmt.getData()));
					spx.add(new LongValue(Long.valueOf(pmt.getKey()))); // id ����
					// number����
				} catch (Exception err) {
					throw new CallDbException("�������Ϸ�!" + err.getMessage());
				}
				s = "update EF_FILE set F_CONTENT=? where id=?";
				break;
			case 2:
				try {
					spx.add(new BytesValue(pmt.getData()));
					spx.add(new StringValue(pmt.getKey()));
					spx.add(new StringValue(pmt.getKey1()));
				} catch (Exception err) {
					throw new CallDbException("�������Ϸ�!" + err.getMessage());
				}
				s = "update EF_FILE set F_CONTENT=? where ygdh=? and clbm=?";
				break;
			default:
				throw new Exception("δ����ĸ��²�����");
			}
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.update(s);
			bo.clearParameters();
			bo.execute("commit");
		} catch (Exception e) {
			xlog.debug(e.getMessage());
			throw new CallDbException("����EF_JNML.F_CONTENT[BLOB]ʧ�ܣ�");
		} finally {
			bo.closestmt();
		}
	}

	/**
	 * �������뵥����ɨ��ͼƬ�ķ��� ����ef_FILE������������id��cl����,�ٵ���UPDATE_JNML_BLOB_ENTITY()��������ͼƬ
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_JNML_CLYX_QUERY_ENTITY(Object obj)
			throws CallDbException {

		ImageScanBean isb = (ImageScanBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new LongValue(Long.valueOf(isb.getId())));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select F_CONTENT from ef_file where id = ?";
			String field = "F_CONTENT";
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			ResultSet rs = bo.query(sql);
			byte[] b = null;
			if (rs.next()) {
				if (databaseType == BasicOperation.DB_TYPE_ORACLE) {
					java.sql.Blob blob = rs.getBlob(field);
					b = blob.getBytes(1, (int) blob.length()); // ��BLObȡ���ֽ�������
				} else {
					b = rs.getBytes(field);
				}
			}
			isb.setClyx(b);
		} catch (Exception e) {
			throw new CallDbException("��ȡBLOB����ʧ�ܣ�1");
		}
	}

	/**
	 * ���ٲ鿴ͼƬ
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_JNML_CLYX_QUERY_DESTROY(Object obj)
			throws CallDbException {

		ImageScanBean isb = (ImageScanBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new LongValue(Long.valueOf(isb.getId())));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select F_CONTENT from ef_file where id = ? and CLBM='002'";
			String field = "F_CONTENT";
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			ResultSet rs = bo.query(sql);
			byte[] b = null;
			if (rs.next()) {
				if (databaseType == BasicOperation.DB_TYPE_ORACLE) {
					java.sql.Blob blob = rs.getBlob(field);
					b = blob.getBytes(1, (int) blob.length()); // ��BLObȡ���ֽ�������
				} else {
					b = rs.getBytes(field);
				}
			}
			isb.setClyx(b);
		} catch (Exception e) {
			throw new CallDbException("��ȡBLOB����ʧ�ܣ�1");
		}
	}

	/**
	 * ��ѯEF_JNML���Ϸ���
	 * 
	 * @param ygdh
	 *            retrun
	 */
	@SuppressWarnings("unchecked")
	public List openDA_GETSELCLFS(Object obj) throws CallDbException {
		String ygdh = (String) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(ygdh));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			String sql = "select count(*) as num from EF_JNML where ygdh=? ";
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��[openDA_GETSELCLFS]ʧ�ܣ�");
		}
	}

	/**
	 * ���������ƽ� 2011-03-11 gjf
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_TRANSFERALL(Object obj) throws CallDbException {
		System.out.println("impl==================");
		DajbxxBean bean = (DajbxxBean) obj;
		String[] idString = bean.getIdString().split(";");
		@SuppressWarnings("unused")
		short ret = 0;
		String sql = "";
		SqlParameterExt spx = null;
		try {
			for (int i = 0; i < idString.length; i++) {
				spx = new SqlParameterExt();
				try {
					long id = Long.parseLong(idString[i]);
					spx.add(new StringValue(bean.getTjr()));
					spx.add(new StringValue(bean.getTjrbm()));
					spx.add(new DateValue(XTool
							.Str2Date(DateUtils.getCurDate())));
					spx.add(new LongValue(id));
				} catch (Exception err) {
					throw new CallDbException("��" + i + "��������Ϸ�!"
							+ err.getMessage());
				}
				try {
					sql = "update ef_dajbxx set f_state='Ԥ�鵵',thyy='',tjr=?,tjrbm=?,tjrq=? where id=?";
					bo.setSqlParameterExt(spx);
					bo.execute(sql);
					System.out.println("��" + i + "���ƽ��ɹ���");
				} catch (Exception e) {
					throw new CallDbException("ִ��" + sql + "����"
							+ e.getMessage());
				}
			}

		} catch (Exception e) {
			throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
		}
	}

	public void executeDA_ZL_SAVE(Object obj) throws CallDbException {
		ZldaBean bean = (ZldaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "p_dajbxx_zl_add(?p1,?p2,?p3,?p4,?p5,?p6,?p7)";
		int ret = 0;
		try {
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(bean.getTm()));
			spx.add(new StringValue(bean.getZtlb()));
			spx.add(new StringValue(bean.getCjr()));
			spx.add(new StringValue(bean.getGlb()));//����
			spx.add(new IntValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			ret = spx.get(7).getInt();
		} catch (Exception err) {
			throw new CallDbException("���鵵���������!" + err.getMessage());
		}
		if (ret == 1) {
			throw new CallDbException("�����Ѵ���!", ret);
		}
	}

	/**
	 * ��������ɨ�����ӵķ���
	 * 
	 * 2011��3��10�� add by HD
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_TEST_FOR_SCAN(Object obj) throws CallDbException {
		DaBean dabean = (DaBean) obj;

		SqlParameterExt spx = null;
		spx = new SqlParameterExt();
		spx.add(new IntValue(dabean.getClfs())); // p1
		spx.add(new StringValue(dabean.getDah())); // p2
		String sql = "insert into ef_test(id,dah) values(?,?)";
		bo.setSqlParameterExt(spx);
		try {
			bo.execute(sql);
			bo.clearParameters();
		} catch (Exception err) {
			xlog.error("ef_test���벻�ɹ���" + err.getMessage());
			throw new CallDbException("ef_test����벻�ɹ���");
		}

	}

	// ����һ��Ŀ¼������ 2011-3-11 xy
	public List<Row> openDA_YJMLCOMBOX(Object obj) throws CallDbException {
		XndaBean z = (XndaBean) obj;
		String zd = "";
		if ("YJML".equals(z.getBj())) {
			zd = "select  first from EF_WSLB where RANK='1'";
		}
		if ("EJML".equals(z.getBj())) {
			/*
			 * zd = "select second from EF_WSLB where RANK='2' and first='" +
			 * z.getDash() + "'";
			 */
			zd = "select second from EF_WSLB WHERE RANK='2' AND P_ID=(SELECT ID FROM EF_WSLB WHERE FIRST='"
					+ z.getDash() + "')";
		}
		if ("SJML".equals(z.getBj())) {/*
										 * zd =
										 * "select  THIRD from EF_WSLB where RANK='3' and first='"
										 * + z.getDash() + "' and second='" +
										 * z.getGh() + "'";
										 */
			zd = "select THIRD from EF_WSLB where RANK='3' and P_ID=(SELECT ID FROM EF_WSLB WHERE SECOND='"
					+ z.getGh() + "')";
		}
		return bo.queryToList(zd);
	}

	/*
	 * Ӱ��黹���÷��� @param ID @return
	 */
	public void executeDA_EFARCHIVECCCHG(Object obj) throws CallDbException {
		Long ID = Long.parseLong((String) obj);
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "update EF_JNML set ZT = '����' where id = ?";
		try {
			spx.add(new LongValue(ID));

		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
		} catch (Exception err) {
			throw new CallDbException("�����黹��������!" + err.getMessage());
		}
	}

	// jcl ��ǩ����
	public void executeDA_PANEL_SAVE(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;

		String sql = "update EF_DAJBXX set BMJB = ?,GH= ?,HH= ?,DASH= ?,GROW= ? where YGDH =?";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			spx.add(new StringValue(bean.getBmjb()));
			spx.add(new StringValue(bean.getGh()));
			spx.add(new StringValue(bean.getHh()));
			spx.add(new StringValue(bean.getDash()));
			spx.add(new StringValue(bean.getGrow()));
			spx.add(new StringValue(bean.getYgdh()));
			// spx.add(new LongValue(Long.valueOf(bean.getSxh())));
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
			bo.clearParameters();
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[DA_PANEL_SAVE]����" + e.getMessage());
		}
		String sql1 = "update EF_DAGSZ set DNUM=DNUM+1 where GH=? and HH=? and DASH=? and GROW=?";
		try {
			SqlParameterExt spx1 = new SqlParameterExt();
			spx1.add(new StringValue(bean.getGh()));
			spx1.add(new StringValue(bean.getHh()));
			spx1.add(new StringValue(bean.getCbwd()));
			spx1.add(new StringValue(bean.getGjd()));
			bo.clearParameters();
			bo.setSqlParameterExt(spx1);
			bo.execute(sql1);
			bo.clearParameters();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������鵵��ǩ���� 2011��4��14�� wm
	 * 
	 * @throws CallDbException
	 */
	public void executeDA_TXPANEL_SAVE(Object obj) throws CallDbException {
		TxdajbxxBean bean = (TxdajbxxBean) obj;
		String sql = "UPDATE EF_TXDAJBXX SET BMJB=?,GH=?,HH=?,DASH=?,GROW=? WHERE YGDH=?";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			spx.add(new StringValue(bean.getBmjb()));
			spx.add(new StringValue(bean.getGh()));
			spx.add(new StringValue(bean.getHh()));
			spx.add(new StringValue(bean.getCbwd()));
			spx.add(new StringValue(bean.getGjd()));
			spx.add(new StringValue(bean.getYgdh()));
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
			bo.clearParameters();
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			throw new CallDbException("ִ�з���[TXDA_PANEL_SAVE]����"
					+ e.getMessage());
		}

	}

	/**
	 * �����˻� 2011-03-21 gjf
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_RETURNBACKALL(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		System.out.println("1111111111111");
		String sql = "update ef_dajbxx set f_state='��״̬',clzt='�˻�',thyy=? where id=?";
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			try {
				spx.add(new StringValue(bean.getThyy()));
				spx.add(new LongValue(bean.getId()));
				bo.setSqlParameterExt(spx);
				bo.execute(sql);
			} catch (Exception e) {
				throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
			}

		} catch (Exception e) {
			throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
		}
	}

	/**
	 * ��ҵ�񵵰� ɾ��
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_DELALL(Object obj) throws CallDbException {
		SqlParameterExt spx = null;
		PublicInfoBean bean = (PublicInfoBean) obj;
		String[] idString = bean.getIdString().split(";");
		String tableName = bean.getTblName();
		String sql = "p_dele_all(?p1,?p2)";
		for (int i = 0; i < idString.length; i++) {
			long id = Long.parseLong(idString[i]);
			try {
				spx = new SqlParameterExt();
				spx.add(new LongValue(id));
				spx.add(new StringValue(tableName));
			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}
			try {
				bo.clearParameters();
				bo.setSqlParameterExt(spx);
				bo.executeProcedure(sql);
			} catch (Exception e) {
				throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
			}
		}
	}

	/***************************************************************************
	 * ɾ������(ͨ�� ) 2011/3/29 daiht
	 * 
	 * @param obj
	 *            һ�㴫�����,һ������ID;
	 * @throws CallDbException
	 */
	public void executeDA_DELALL_TY(Object obj) throws CallDbException {
		SqlParameterExt spx = null;
		PublicInfoBean bean = (PublicInfoBean) obj;
		String[] idString = bean.getIdString().split(";");
		String tableName = bean.getTblName();
		String sql = "delete from " + tableName + " t where t.id=?";
		for (int i = 0; i < idString.length; i++) {
			long id = Long.parseLong(idString[i]);
			try {
				spx = new SqlParameterExt();
				// spx.add(new StringValue(tableName));
				spx.add(new LongValue(id));

			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}
			try {
				bo.clearParameters();
				bo.setSqlParameterExt(spx);
				bo.execute(sql);// ִ��SQL
				// bo.executeProcedure(sql);
			} catch (Exception e) {
				throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
			}
		}
	}

	// EF_WSLBXX
	public void executeDA_WSLBXX(Object obj) throws CallDbException {
		SqlParameterExt spx = null;
		String keyname = "";
		String keyname2 = "";
		String rank = "";
		Map map = (Map) obj;
		PublicInfoBean bean = (PublicInfoBean) map.get("bean");
		String[] idString = bean.getIdString().split(";");
		String tableName = bean.getTblName();
		keyname = null != map.get("keyname") ? map.get("keyname").toString()
				: "";
		keyname2 = null != map.get("keyname1") ? map.get("keyname1").toString()
				: "";
		rank = null != map.get("rank") ? map.get("rank").toString() : "";
		String sqlitems = "";
		String sqlmain = "";
		if (3 == Integer.valueOf(rank)) {
			sqlmain = "delete from " + tableName + " t where t.id=?";
		}
		if (2 == Integer.valueOf(rank)) {
			sqlmain = "delete from " + tableName + " t where t.id=?";
			sqlitems = "delete from " + tableName + " t where t.first='"
					+ keyname + "'and t.second='" + keyname2 + "' and t.rank>2";
		}
		if (1 == Integer.valueOf(rank)) {
			sqlmain = "delete from " + tableName + " t where t.id=?";
			sqlitems = "delete from " + tableName + " t where t.first='"
					+ keyname + "' and t.rank>1";
		}
		for (int i = 0; i < idString.length; i++) {
			long id = Long.parseLong(idString[i]);
			try {
				spx = new SqlParameterExt();
				// spx.add(new StringValue(tableName));
				spx.add(new LongValue(id));

			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}
			try {
				bo.clearParameters();
				bo.setSqlParameterExt(spx);
				bo.execute(sqlmain);// ִ��SQL
				if (!"".equals(sqlitems)) {
					bo.clearParameters();
					bo.execute(sqlitems);
				}
				// bo.executeProcedure(sql);
			} catch (Exception e) {
				throw new CallDbException("ִ��" + sqlmain + "����"
						+ e.getMessage());
			}
		}
	}

	/**
	 * ����ɾ�������б� 2011-10-19 by czm
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_WSLBDEL(Object obj) throws CallDbException {
		PublicInfoBean bean = (PublicInfoBean) obj;
		String id = bean.getIdString();
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "select * from ef_wslb where p_id=?";
		String sql1 = "delete from ef_wslb where id=?";
		try {
			spx.add(new StringValue(id));
		} catch (Exception e) {
			throw new CallDbException("�������Ϸ�!" + e.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			List<Row> tableNameList = bo.queryToList(sql.trim());
			for (int i = 0; i < tableNameList.size(); i++) {
				SqlParameterExt spx2 = new SqlParameterExt();
				String ids = tableNameList.get(i).getString("ID");
				System.out.println(ids + "-------------");
				spx2.add(new StringValue(ids));
				bo.setSqlParameterExt(spx2);
				List<Row> tlist = bo.queryToList(sql.trim());
				System.out.println(tlist.size() + "-----------------99999999");
				for (int j = 0; j < tlist.size(); j++) {
					String ida = tlist.get(i).getString("ID");
					SqlParameterExt spx1 = new SqlParameterExt();
					spx1.add(new StringValue(ida));
					bo.setSqlParameterExt(spx1);
					bo.execute(sql1);
				}
				SqlParameterExt spx3 = new SqlParameterExt();
				spx3.add(new StringValue(ids));
				bo.setSqlParameterExt(spx3);
				bo.execute(sql1);
			}
			SqlParameterExt spx4 = new SqlParameterExt();
			spx4.add(new StringValue(id));
			bo.setSqlParameterExt(spx4);
			bo.execute(sql1);
		} catch (Exception e) {

		}
	}

	/**
	 * ����Ŀ¼�嵥���ϱ����ѯ 2011-10-24 by czm
	 * 
	 * @param obj
	 * @return
	 * @throws CallDbException
	 */
	public List<Row> openDA_GetCLBM(Object obj) throws CallDbException {
		ClbmBean clBean = (ClbmBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String tableName = clBean.getTableName() + "1";
		String sql = "select * from EF_SYS where NAME=?";
		spx.add(new StringValue(tableName));
		bo.setSqlParameterExt(spx);
		List<Row> list = bo.queryToList(sql);
		System.out.println(list.size() + "---");
		if (list.size() == 0) {
			AddClbm(clBean);
		}
		List<Row> rlist = bo.queryToList(sql);
		UpdateClbm(clBean);
		return rlist;
	}

	/**
	 * ����Ŀ¼�嵥��Ÿ��� 2011-10-24 by czm
	 * 
	 * @param clBean
	 * @throws CallDbException
	 */
	public void UpdateClbm(ClbmBean clBean) throws CallDbException {
		System.out.println("������!!!!!!!!!!!!");
		String tableName = clBean.getTableName() + "1";
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "update EF_SYS set SEQ=SEQ+0.5 where NAME=?";
		try {
			spx.add(new StringValue(tableName));
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
		} catch (Exception e) {
			throw new CallDbException("�������Ϸ�!" + e.getMessage());
		}
	}

	/**
	 * ����Ŀ¼�嵥������ 2011-10-24 by czm
	 * 
	 * @param clBean
	 * @throws CallDbException
	 */
	public void AddClbm(ClbmBean clBean) throws CallDbException {
		String tableName = clBean.getTableName() + "1";
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "insert into EF_SYS(NAME,SEQ) values(?,?)";
		try {
			spx.add(new StringValue(tableName));
			spx.add(new IntValue(clBean.getCsz()));
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
		} catch (Exception e) {
			throw new CallDbException("�������Ϸ�!" + e.getMessage());
		}
	}

	/**
	 * ����YGDH��ѯ�������ͱ��� 2011.5.5 add by wm
	 * 
	 * @param daBean
	 * @return
	 * @throws CallDbException
	 */
	public String getTableNameByYGDH(String daBean) throws CallDbException {
		String tableName = "";
		SqlParameterExt spx = new SqlParameterExt();
		String sql = " select f_type from ef_dajbxx where ygdh = ? ";
		try {
			spx.add(new StringValue(daBean));
		} catch (Exception e) {
			throw new CallDbException("�������Ϸ�!" + e.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			List<Row> tableNameList = bo.queryToList(sql.trim());
			if (tableNameList.size() != 0) {
				Row row = (Row) tableNameList.get(0);
				String f_type = row.getTrimString("f_type");
				if ("5".equals(f_type)) {
					tableName = "EF_LPDA";
				}
				if ("4".equals(f_type)) {
					tableName = "EF_DKDA";
				}
				if ("3".equals(f_type)) {
					tableName = "EF_ZQDA";
				}
				if ("2".equals(f_type)) {
					tableName = "EF_GJDA";
				}
				if ("1".equals(f_type)) {
					tableName = "EF_DWDA";
				}
			}
		} catch (Exception e) {
			throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
		}
		return tableName;
	}

	/**
	 * ɾ��ҵ�񵵰�(ͨ��)2011.3.29 zy
	 * 
	 * @param obj
	 * @throws CallDbException
	 */

	public void executeDA_DA_DEL(Object obj) throws CallDbException {
		DajbxxBean dabean = (DajbxxBean) obj;

		String tableName = "";

		SqlParameterExt spx = null;

		String[] grouptalbeName = dabean.getYgdh().toString().split("-");

		for (int i = 0; i < grouptalbeName.length; i++) {

			spx = new SqlParameterExt();

			tableName = getTableNameByYGDH(grouptalbeName[i]); // 2011.5.5 add
			// by wm

			short ret = VL_FOUR;
			try {

				spx.add(new StringValue(grouptalbeName[i])); // p1Ԥ�鵵��

				// spx.add(new StringValue(dabean.getTableName); // p2ҵ�����
				spx.add(new StringValue(tableName)); // 2011.5.5 add by wm

				spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// p3����ֵ,0�ɹ�
				// <>0ʧ��
			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}
			String sql = "P_ARCHIVE_DEL_YW(?p1,?p2,?p3)";
			try {
				bo.setSqlParameterExt(spx);
				bo.executeProcedure(sql);
				ret = spx.get(3).getShort();
			} catch (Exception err) {
				throw new CallDbException("���Ϊ" + grouptalbeName[i]
						+ "�ĵ���ɾ��ʧ��!" + err.getMessage());
			}
			if (ret == VL_ONE) {
				throw new CallDbException("���Ϊ" + grouptalbeName[i] + "�ĵ���������",
						ret);
			} else if (ret == VL_TWO) {
				throw new CallDbException("���Ϊ" + grouptalbeName[i]
						+ "�ĵ����ѹ鵵������ɾ��!", ret);
			}
		}
	}

	// k
	public void updateDestroy(long res, ImageScanBean bean)
			throws CallDbException {
		SqlParameterExt spx = new SqlParameterExt();
		String[] idString = bean.getYgdh().split(";");
		String sql = "";
		for (int i = 0; i < idString.length; i++) {
			spx = new SqlParameterExt();
			try {
				long id = Long.parseLong(idString[i]);
				spx.add(new LongValue(res));
				spx.add(new LongValue(id));
			} catch (Exception err) {
				throw new CallDbException("��" + i + "��������Ϸ�!"
						+ err.getMessage());
			}
			try {
				sql = "Update EF_XHJL SET P_ID=? where id=?";
				bo.setSqlParameterExt(spx);
				bo.execute(sql);
			} catch (Exception e) {
				throw new CallDbException("ִ��" + sql + "����" + e.getMessage());
			}
		}

	}

	/**
	 * ��ҵ�� ���±��� by gjf
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_RS_SAVE(Object obj) throws CallDbException {
		RsdaBean bean = (RsdaBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "p_dajbxx_rs_add(?p1,?p2,?p3,?p4,?p5,?p6,?p7,?p8,?p9,?p10,?p11,?p12,?p13,?p14,?p15,?p16,?p17,?p18,?p19,?p20,?p21,?p22,?p23,?p24,?p25,?p26)";
		int ret = 0;
		try {
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(bean.getBh()));
			spx.add(new StringValue(bean.getName()));
			spx.add(new StringValue(bean.getSex()));
			spx.add(new StringValue(bean.getMz()));
			spx.add(new StringValue(bean.getSfzh()));
			spx.add(new StringValue(bean.getCsrq()));
			spx.add(new LongValue(bean.getAge()));
			spx.add(new StringValue(bean.getHyzk()));
			spx.add(new StringValue(bean.getCjgzsj()));
			spx.add(new LongValue(bean.getGl()));
			spx.add(new StringValue(bean.getRdtsj()));
			spx.add(new StringValue(bean.getDh()));
			spx.add(new StringValue(bean.getPhone()));
			spx.add(new StringValue(bean.getEmail()));
			spx.add(new StringValue(bean.getQq()));
			spx.add(new StringValue(bean.getXl()));
			spx.add(new StringValue(bean.getSchool()));
			spx.add(new StringValue(bean.getZy()));
			spx.add(new StringValue(bean.getAddress()));
			spx.add(new StringValue(bean.getHkszd()));
			spx.add(new StringValue(bean.getJg()));
			// spx.add(new StringValue(bean.getDjrq()));
			spx.add(new StringValue(bean.getDjr()));
			spx.add(new StringValue(bean.getGlb()));//��������
			// System.out.println(bean.getDjrq()+"=========================����");
			spx.add(new IntValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			ret = spx.get(26).getInt();
		} catch (Exception err) {
			throw new CallDbException("���µ����������!" + err.getMessage());
		}
		if (ret == 1) {
			throw new CallDbException("�����Ѵ���!", ret);
		}
	}

	/**
	 * �����������Ϣ��ѯfor����ϵͳ2.0 2011��4��13�� add by HD
	 * 
	 * @param obj
	 * @return
	 * @throws CallDbException
	 */

	@SuppressWarnings("unchecked")
	public List openDA_DKDA_QUERY_FOR_OLD_APP(Object obj)
			throws CallDbException {

		DkdaBean bean = (DkdaBean) obj;

		String sql = " select a.dah as dah,a.ygdh as ygdh,a.f_state as f_state,a.f_type as f_type,a.tjr,a.tjrq,a.jsr,a.jsrq,a.bmjb,a.bcnx,a.cfwz,a.clfs as clfs,"
				+ " b.cbwd,b.cbwdbm,b.gjd,b.gjdbm,b.dkhtbh,b.dkhtrq,b.fkyh,b.fkrq,b.zgxm,b.zjhm,b.gfxxdz,b.dknx "
				+ " from ef_dajbxx a,ef_dkda b where a.ygdh = b.ygdh and "
				+ " a.ygdh like ? and a.dah like ? and a.f_type like ? and a.tjr like ? and a.f_state = ? "
				+ " and b.cbwdbm like ? and b.zjhm like ? and b.zgxm like ? and b.fkyh like ? and b.dkhtbh like ? ";

		SqlParameterExt spx = null;

		try {

			spx = new SqlParameterExt();

			spx.add(new StringValue(bean.getYgdh().trim() + "%"));

			spx.add(new StringValue(bean.getDah().trim() + "%"));

			spx.add(new StringValue(bean.getF_type().trim() + "%"));

			spx.add(new StringValue(bean.getTjr().trim() + "%"));

			spx.add(new StringValue(bean.getF_state()));

			spx.add(new StringValue(bean.getCbwdbm().trim() + "%"));

			spx.add(new StringValue(bean.getZjhm().trim() + "%"));

			spx.add(new StringValue(bean.getZgxm().trim() + "%"));

			spx.add(new StringValue(bean.getFkyh().trim() + "%"));

			spx.add(new StringValue(bean.getDkhtbh().trim() + "%"));

			if (bean.getCxrq1() != null && !"".equals(bean.getCxrq1().trim())
					&& bean.getCxrq2() != null
					&& !"".equals(bean.getCxrq2().trim())) {

				sql += " and b.dkhtrq between ? and ? ";

				spx.add(new DateValue(XTool.Str2Date(bean.getCxrq1())));

				spx.add(new DateValue(XTool.Str2Date(bean.getCxrq2())));

			}

			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql.trim());

		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_DKDA_QUERY_FOR_OLD_APP]����"
					+ e.getMessage());
		}
	}

	/**
	 * �鿴�����˻� 2011��4��18�� add by wm
	 * 
	 * @param obj
	 * @return
	 * @throws CallDbException
	 */
	@SuppressWarnings("unchecked")
	public List openDA_THDA_QUERY_FOR_OLD_APP(Object obj)
			throws CallDbException {

		DajbxxBean bean = (DajbxxBean) obj;
		String sql = "select ygdh,dah,f_type,f_state,clfs,tjr,jsr,bmjb,bcnx,cfwz,tjrq,thyy from ef_dajbxx where ygdh like ? and dah like ? "
				+ " and tjr like ? and f_state= ? and f_type in ('4','2','3','1','5')";
		SqlParameterExt spx = null;

		try {

			spx = new SqlParameterExt();

			spx.add(new StringValue(bean.getYgdh().trim() + "%"));

			spx.add(new StringValue(bean.getDah().trim() + "%"));

			spx.add(new StringValue(bean.getTjr().trim() + "%"));

			spx.add(new StringValue(bean.getF_state().trim()));

			if (bean.getF_type() != null && !"".equals(bean.getF_type().trim())) {

				sql += " and f_type like ? ";

				spx.add(new StringValue(bean.getF_type().trim() + "%"));
			}

			if (bean.getCxrq1() != null && !"".equals(bean.getCxrq1().trim())
					&& bean.getCxrq2() != null
					&& !"".equals(bean.getCxrq2().trim())) {

				sql += " and tjrq between ? and ? ";

				spx.add(new DateValue(XTool.Str2Date(bean.getCxrq1())));

				spx.add(new DateValue(XTool.Str2Date(bean.getCxrq2())));

			}

			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql.trim());

		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_THDA_QUERY_FOR_OLD_APP]����"
					+ e.getMessage());
		}
	}

	// ���ݴ����ͬ��Ų�ѯ�������Ϣ
	public List<Row> getYgdhByDkhtbh(String dkhtbh) {

		String sql = " select * from ef_dkda where dkhtbh = ? ";
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(dkhtbh));
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql.trim());
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * ������������� 2011��4��20�� add by wm
	 * 
	 * @param obj
	 * @param dkhtbh
	 * @param txqzh
	 * @return
	 * @throws CallDbException
	 */
	@SuppressWarnings("unchecked")
	public List openDA_TxDA_QUERY_FOR_OLD_APP(Object obj)
			throws CallDbException {
		TxDkdaBean daBean = (TxDkdaBean) obj;
		String ygdh = "";
		if (!daBean.getDkhtbh().equals("")) {
			List ygdhList = getYgdhByDkhtbh(daBean.getDkhtbh());
			if (ygdhList.size() != 0) {
				Row row = (Row) ygdhList.get(0);
				try {
					ygdh = row.getTrimString("ygdh");
				} catch (NoSuchColumnException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedConversionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		String sql = "select a.dah as dah,a.ygdh as ygdh,a.f_state as f_state,a.f_type as f_type, "
				+ " a.tjr,a.tjrq,a.jsr,a.jsrq,a.bmjb,a.bcnx,a.cfwz,a.clfs as clfs, "
				+ " b.txqzh,b.qhr,b.qhrq,b.thr,b.bgyy,b.bgrq,b.bgr"
				+ " from ef_txdajbxx a,ef_txdkda b where a.ygdh = b.ygdh"
				+ " and a.ygdh like ? and a.dah like ? and a.f_type = ? and a.tjr like ? and a.f_state = ? "
				+ " and b.txqzh like ? ";
		SqlParameterExt spx = null;
		try {

			spx = new SqlParameterExt();
			if (!ygdh.equals("")) {
				spx.add(new StringValue(ygdh.toString().trim() + "%"));
			} else {
				spx.add(new StringValue(daBean.getYgdh().trim() + "%"));
			}
			spx.add(new StringValue(daBean.getDah().trim() + "%"));
			spx.add(new StringValue(daBean.getF_type().trim()));
			spx.add(new StringValue(daBean.getTjr().trim() + "%"));
			spx.add(new StringValue(daBean.getF_state()));
			spx.add(new StringValue(daBean.getTxqzh().trim() + "%"));
			if (daBean.getCxrq1() != null
					&& !"".equals(daBean.getCxrq1().trim())
					&& daBean.getCxrq2() != null
					&& !"".equals(daBean.getCxrq2().trim())) {

				sql += " and tjrq between ? and ? ";

				spx.add(new DateValue(XTool.Str2Date(daBean.getCxrq1())));

				spx.add(new DateValue(XTool.Str2Date(daBean.getCxrq2())));

			}
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql.trim());
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_TxDA_QUERY_FOR_OLD_APP]��������");
		}
	}

	public void executeDA_RZ_SAVE(Object obj) throws CallDbException {
		RzBean bean = (RzBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "p_rz_add(?p1,?p2,?p3,?p4,?p5,?p6)";
		int ret = 0;
		try {
			spx.add(new StringValue(bean.getYgdh()));// Ԥ�鵵������
			spx.add(new StringValue(bean.getCzybm()));
			spx.add(new StringValue(bean.getCzy()));
			spx.add(new StringValue(bean.getKsrq()));
			spx.add(new StringValue(bean.getCznr()));
			spx.add(new IntValue(ret), SqlParameterExt.SQL_OUTPUT);// ����ֵ,0�ɹ�
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			ret = spx.get(6).getInt();
		} catch (Exception err) {
			throw new CallDbException("��־�������!" + err.getMessage());
		}
		if (ret == 1) {
			throw new CallDbException("��־�Ѵ���!", ret);
		}
	}

	// ���ݵ������Ͳ�ѯ��������
	public List<Row> openDA_BM_JNMLSZByDalx(Object obj) throws CallDbException {
		DaSzJnmlBean daSzJnmlBean = (DaSzJnmlBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "SELECT CLBM,CL FROM BM_JNMLSZ,EF_DASZJNML WHERE PCLBM = CLBM AND DALX=?";
		try {
			spx.add(new StringValue(daSzJnmlBean.getDalx()));
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql.trim());
		} catch (Exception ex) {
			throw new CallDbException("��ѯ���ݱ�BM_JNMLSZ����" + ex.getMessage());
		}
	}

	public void executeDA_DelCl(Object obj) throws CallDbException {
		DaSzJnmlBean bean = (DaSzJnmlBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "delete from EF_DASZJNML where dalx = ? and pclbm=?";
		try {
			spx.add(new StringValue(bean.getDalx()));
			spx.add(new StringValue(bean.getPclbm()));
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
			bo.clearParameters();
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[DA_DelCl]����" + e.getMessage());
		}

	}

	// ���Ӳ�������
	public void executeDA_AddCL_JNML(Object obj) throws CallDbException {
		JnmlSzBean jnmlSzBean = (JnmlSzBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "INSERT INTO BM_JNMLSZ (ID,CLBM,CL) VALUES(?,?,?)";
		try {
			spx.add(new LongValue(jnmlSzBean.getId()));
			spx.add(new StringValue(jnmlSzBean.getClbm()));
			spx.add(new StringValue(jnmlSzBean.getCl()));
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
			bo.clearParameters();
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[DA_AddCL_JNML]����" + e.getMessage());
		}
	}

	// ��ѯ���еĲ�������
	public List<Row> openDA_GetAllCl(Object obj) throws CallDbException {
		String sql = "select ID,CL,CLBM from BM_JNMLSZ";
		try {
			return bo.queryToList(sql);
		} catch (CallDbException e) {
			throw new CallDbException("ִ�з���[DA_GetAllCl]����" + e.getMessage());
		}
	}

	// ��ѯ���еĲ�������
	public List<Row> openDA_GetAllWslb(Object obj) throws CallDbException {
		String sql = "select ID,FIRST from EF_WSLB where RANK=1";
		try {
			return bo.queryToList(sql);
		} catch (CallDbException e) {
			throw new CallDbException("ִ�з���[DA_GetAllCl]����" + e.getMessage());
		}
	}

	public List<Row> openDA_GetAllWslb1(Object obj) throws CallDbException {
		String sql = "select ID,SECOND from EF_WSLB where RANK=2";
		try {
			return bo.queryToList(sql);
		} catch (CallDbException e) {
			throw new CallDbException("ִ�з���[DA_GetAllCl]����" + e.getMessage());
		}
	}

	// ��ѯ��������
	public List<Row> openDA_GetSelDept(Object obj) throws CallDbException {
		String sql = "SELECT ID,F_CAPTION FROM T_MK_SYS_DEPT";
		try {
			return bo.queryToList(sql.trim());
		} catch (Exception e) {
			throw new CallDbException("��ѯT_MK_SYS_DEPT����" + e.getMessage());
		}
	}

	public void executeDA_Archive_All(Object obj) {
		TerBean bean = (TerBean) obj;
		TerBean[] beans = null;
		SqlParameterExt spx = new SqlParameterExt();
		int ret = 0;
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		spx.add(new LongValue(bean.getDk()));
		Row row = null;
		int a = 0;
		int dk;
		int b;
		int c;
		int d;
		int f;
	}

	public List<Row> DK_Archive_All(Object obj) {
		String sql;
		TerBean bean = (TerBean) obj;
		try {
			sql = "select dash,gh,grow,hh,damax,dnum from ef_dagsz where  bum='������' and f_type ='����'  order by dash,gh,grow,hh,damax,dnum ";
			return bo.queryToList(sql.trim());
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	// ��ѯ���еĵ�������
	public List<Row> openDA_GetAllDalx(Object obj) throws CallDbException {
		String sql = "select * from EF_DALX";
		try {
			return bo.queryToList(sql);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_GetAllDalx]����" + e.getMessage());
		}
	}

	// Ϊ������Ӳ���
	public void executeDA_AddDaszJnml(Object obj) throws CallDbException {
		DaSzJnmlBean bean = (DaSzJnmlBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "insert into EF_DASZJNML(ID,YJFS,FYJFS,SFBX,DALX,BZ,PCLBM)values(?,?,?,?,?,?,?)";
		try {
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getYjfs()));
			spx.add(new StringValue(bean.getFyjfs()));
			spx.add(new StringValue(bean.getSfbx()));
			spx.add(new StringValue(bean.getDalx()));
			spx.add(new StringValue(bean.getBz()));
			spx.add(new StringValue(bean.getPclbm()));
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
			bo.clearParameters();
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_AddDaszJnml]����" + e.getMessage());
		}
	}

	/******************************************* ADD ******/
	

	// ��ȡÿ�������������
	public List<Row> openDA_GHROWCOMBOXXX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;
		String sql = "select b.grow,b.ph,b.hh,b.f_type,count(DNO)as dmax,"+
					 " (select  count(dno) from ef_dagsz a where a.dash='"+z.getDash()+"' and a.gh='"+z.getDagh()+"' and a.grow='"+z.getDagrow()+"' and a.ph='"+z.getDaph()+"'"+
			         " and a.hh=b.hh and a.f_type=b.f_type and dabh !='0') as dnum"+
			         " from ef_dagsz b where b.dash='"+z.getDash()+"' and b.gh='"+z.getDagh()+"' and b.grow='"+z.getDagrow()+"' and b.ph='"+z.getDaph()+"'  group by b.grow,b.ph,b.hh,b.f_type order by b.grow,b.ph,TO_NUMBER(SUBSTR(b.hh,2,length(b.hh)-2))";
		try {
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_GHROWCOMBOXXX]����" + e.getMessage());
		}
	}
	// ���ݵ����Һţ���ţ��кţ��кţ���ȡÿ�������ŵ����� 2011-7-27 wm
	public List<Row> openDA_GetDnum(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		String sql = "select count(dno) as dnum from ef_dagsz where dash=? and gh=? and grow = ? and hh=? and f_type=? and dabh !='0'";
		try {
			spx.add(new StringValue(z.getDash()));
			spx.add(new StringValue(z.getDagh()));
			spx.add(new StringValue(z.getDagrow()));
			spx.add(new StringValue(z.getDahh()));
			spx.add(new StringValue(z.getF_type()));
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��[openDA_GetDnum]����" + e.getMessage());
		}
	}

//	/*
//	 * ����Ŀ¼��ʾ�б���ã� ���ôη��� add by zy 2011.8.11
//	 */
//	public List<Row> openDA_GET_JNML_VIEW_RESULTS(Object obj) {
//		List<Row> list = null;
//		SqlParameterExt spx = new SqlParameterExt();
//		String sql;
//		DajbxxBean bean = (DajbxxBean) obj;
//		try {
//			bean = this.selectType(bean);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		String type = bean.getType();
//		System.out.println(type+"--------------type");
//		try {
//			// 1-��λ,2-�鼯,3-֧ȡ,4-����,5-¥��,6-����,7-�Ƽ�,8-��Ƭ,9-¼��,10-ʵ��,11-֪ʶ,12-����,13-����
//			if (type.trim().equals("4") || type.trim().equals("3")) {
//				String dabh = openDA_DABH_QUERY(bean);
//				spx.add(new StringValue(bean.getYgdh()));
//				sql = "select id, ygdh,  cl , zt , yjfs , fyfs , fyys , sfqq from ef_jnml where ygdh = ? and zt = '����' and id in ( "
//						+ dabh + " ) order by clbm";
//				bo.setSqlParameterExt(spx);
//				list = bo.queryToList(sql.trim());
//			}
//			if (type.trim().equals("1") || type.trim().equals("2")
//					|| type.trim().equals("5") || type.trim().equals("6")
//					|| type.trim().equals("7") || type.trim().equals("10")
//					|| type.trim().equals("12") || type.trim().equals("13")) {
//				spx.add(new StringValue(bean.getYgdh()));
//				sql = "select id, ygdh,  cl , zt , yjfs , fyfs , fyys , sfqq from ef_jnml where ygdh = ? and zt = '����' order by clbm";
//				bo.setSqlParameterExt(spx);
//				list = bo.queryToList(sql.trim());
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("ִ��openDA_GET_JNML_VIEW_RESULTS��������");
//		}
//		return list;
//		// TODO Auto-generated catch block
//	}

	
	
	/**
	 * ����Ŀ¼��ʾ�б���ã� ���ôη��� add by zy 2011.8.11
	 * 
	 * 2012-3-29 chg by hd   ������Ÿ��º�,����ϵͳ����Ŀ¼��ͼ��ѯ��������
	 */

	public List<Row> openDA_GET_JNML_VIEW_RESULTS(Object obj) {
		List<Row> list = null;
		SqlParameterExt spx = new SqlParameterExt();
		String sql;
		DajbxxBean bean = (DajbxxBean) obj;

		try {
			bean = this.selectType(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//String type = bean.getF_type();
		String type = bean.getType();
		try {
			if ("4".equals(type) || "3".equals(type)) {
				spx.add(new StringValue(bean.getYgdh()));
				spx.add(new StringValue(bean.getYgdh()));
				spx.add(new StringValue(bean.getYwlsh()));
				sql = "select id, ygdh,  cl , zt , yjfs , fyfs , fyys , sfqq from ef_jnml where ygdh = ? and (zt = '����' or zt='����') and id in (select jnml_id from ef_dabh where ygdh = ? and ywlsh = ?) order by clbm";
				bo.setSqlParameterExt(spx);
				list = bo.queryToList(sql.trim());

			} else {
				spx.add(new StringValue(bean.getYgdh()));
				sql = "select id, ygdh,  cl , zt , yjfs , fyfs , fyys , sfqq from ef_jnml where ygdh = ? and (zt = '����' or zt='����') order by clbm";
				bo.setSqlParameterExt(spx);
				list = bo.queryToList(sql.trim());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ִ��openDA_GET_JNML_VIEW_RESULTS��������");
		}
		return list;
		// TODO Auto-generated catch block
	}
	
	
	
	/*
	 * ����EF_DAJBXX���ID��ѯF_TYPE ��ygdh ��ywlsh
	 */
	public DajbxxBean selectType(DajbxxBean bean) throws CallDbException {

		String type = "";
		SqlParameterExt spx = new SqlParameterExt();
		List<Row> list = null;
		Row row = null;
		String sql = "select f_type , ygdh , ywlsh  from ef_dajbxx where id=?";
		try {
			spx.add(new LongValue(bean.getId()));
		} catch (Exception e) {
			throw new CallDbException("�������Ϸ�" + e.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			list = bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��selectType��������" + e.getMessage());
		}
		if (list != null && list.size() > 0) {
			row = (Row) list.get(0);
			try {
				bean.setType(row.getTrimString("f_type"));
				bean.setYgdh(row.getTrimString("ygdh"));
				bean.setYwlsh(row.getTrimString("ywlsh"));
				System.out.println(row.getTrimString("f_type")+"------"+row.getTrimString("ygdh"));
			} catch (NoSuchColumnException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedConversionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return bean;
	}

	/************************************************************/
	/**
	 * 2011��7��15�� jcl
	 * 
	 * @param daBean
	 * @return
	 */
	public List<Row> openDA_Archive_All_P(Object obj) {
		List<Row> list = null;
		SqlParameterExt spx = new SqlParameterExt();
		String sql;
		DajbxxBean bean = (DajbxxBean) obj;
		try {
			spx.add(new LongValue(bean.getId()));
			spx.add(new StringValue(bean.getF_type()));
			spx.add(new LongValue(bean.getClfs()));
			sql = "select dash,gh,grow,hh,dno from(select dash,gh,grow,hh,dno from ef_dagsz where bum=? and F_type=? and dabh='0' order by id,dash,TO_NUMBER(SUBSTR(gh,2,length(gh)-2)),grow,hh,dno desc) where rownum<=?";
			bo.setSqlParameterExt(spx);
			list = bo.queryToList(sql.trim());
		} catch (Exception e) {
			System.out.println("ִ��DA_Archive_All_P��������");
		}
		return list;
	}

	// ----------------------
	// ����������Ϣ����˳���combox 2011-8-9wm
	public List<Row> openDA_SXHCOMBOX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;

		String str = "select dno from ef_dagsz where dash='" + z.getDash()
				+ "' and gh='" + z.getDagh() + "' " + "and grow='"
				+ z.getDagrow() + "' and ph='"+z.getDaph()+"'and hh='" + z.getDahh() + "' and f_type='"
				+ z.getF_type() + "' and dabh='0' and bum='" + z.getId()
				+ "' group by dno order by dno";
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_SXHCOMBOX]����" + e.getMessage());
		}
	}

	//���⵵���Ҳ�ѯ���ݲ��Ų�ѯ 2012-9-12 wm
	public List<Row> openDA_SELBUM(Object obj,int pagesize,int pagenum) throws CallDbException{
		DagszBean bean = (DagszBean) obj;
		String sql = "SELECT T.F_CAPTION,count(*) as count FROM EF_DAGSZ D,T_MK_SYS_DEPT T,T_MK_SYS_USER U"+
					 " WHERE (T.ID=D.BUM) AND (U.F_DEPT_ID=T.ID) ";
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(bean.getIsAdmin()==2){
			sql+="GROUP BY (T.F_CAPTION)  ORDER BY T.F_CAPTION";
		}else{
			sql+=" AND (U.F_DEPT_ID="+bean.getId()+") GROUP BY (T.F_CAPTION)  ORDER BY T.F_CAPTION ";
		};
		try {
			return bo.queryToList(sql,pagesize,pagenum);
		} catch (Exception e) {
			throw new CallDbException("����Ŀ¼��Ϣ���ʧ�ܣ�");
		}
		
	}
	/*
	 * �����еĴ������ѯ2011-9-20ygp
	 */
	// �������ѯ
	public List<Row> openDA_DKCOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;

		String str = "SELECT A.DAH AS DAH,A.GH AS GH,A.HH AS HH,"
				+ "B.ZGZH AS ZGZH,B.ZGXM AS ZGXM,B.ZJHM AS ZJHM,"
				+ "B.DWZH AS DWZH,B.DW AS DW,B.DKNX AS DKNX,B.GJD "
				+ "AS GJD,B.GFLX AS GFLX,B.CBWD AS CBWD,A.CFWZ "
				+ "AS CFWZ,A.ID AS ID FROM EF_DAJBXX  A,"
				+ "EF_DKDA B WHERE (A.F_STATE='�鵵' and A.F_TYPE='4' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH) "
				+ "AND(A.DAH like '%"
				+ A.getDah()
				+ "%') "
				+ "AND(B.ZGZH like '%"
				+ A.getZgzh()
				+ "%') "
				+ "AND(B.ZGXM like '%"
				+ A.getZgxm()
				+ "%') "
				+ "AND(B.ZJHM like '%"
				+ A.getZjhm()
				+ "%') "
				+ "AND (B.DWZH like '%"
				+ A.getDWZH()
				+ "%')"
				+ "AND (B.GJD like '%"
				+ A.getGjd()
				+ "%') "
				+ "AND(B.GFLX like '%"
				+ A.getGFLX()
				+ "%') "
				+ "AND(B.CBWD like '%"
				+ A.getCBWD()
				+ "%') "
				+ "AND (A.CFWZ like '%" + A.getCfwz() + "%')";
		if (A.getBj().equals("xuzhe")) {
			str = "SELECT A.DAH AS DAH,A.GH AS GH,A.HH AS HH,"
					+ "B.ZGZH AS ZGZH,B.ZGXM AS ZGXM,B.ZJHM AS ZJHM,"
					+ "B.DWZH AS DWZH,B.DW AS DW,B.DKNX AS DKNX,B.GJD "
					+ "AS GJD,B.GFLX AS GFLX,B.CBWD AS CBWD,A.CFWZ "
					+ "AS CFWZ,A.ID AS ID FROM EF_DAJBXX  A,"
					+ "EF_DKDA B WHERE (A.F_STATE='�鵵' and A.F_TYPE='4' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH) "
					+ "AND(A.ID in ("
					+ A.getDah().substring(0, A.getDah().length() - 1)
					+ ")) "
					+ "AND(B.ZGZH like '%"
					+ A.getZgzh()
					+ "%') "
					+ "AND(B.ZGXM like '%"
					+ A.getZgxm()
					+ "%') "
					+ "AND(B.ZJHM like '%"
					+ A.getZjhm()
					+ "%') "
					+ "AND (B.DWZH like '%"
					+ A.getDWZH()
					+ "%')"
					+ "AND (B.GJD like '%"
					+ A.getGjd()
					+ "%') "
					+ "AND(B.GFLX like '%"
					+ A.getGFLX()
					+ "%') "
					+ "AND(B.CBWD like '%"
					+ A.getCBWD()
					+ "%') "
					+ "AND (A.CFWZ like '%" + A.getCfwz() + "%')";
		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_DKCOMBOX]����" + e.getMessage());
		}
	}

	/*
	 * �����еĵ�λ�鼯������ѯ����2011-9-20ygp
	 */
	// ��λ�鼯������ѯ
	public List<Row> openDA_DWGJCOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;

		String str = "SELECT B.ID AS ID,A.DAH AS DAH,A.GH AS GH,"
				+ "A.HH AS HH,B.DWZH AS DWZH,B.DW AS DW,B.DWDZ AS DWDZ,"
				+ "B.GJD AS GJD,B.CBWD AS CBWD,B.GJZGY AS GJZGY,"
				+ "to_char(B.KHRQ,'yyyy-mm-dd') AS KHRQ,"
				+ "to_char(B.XHRQ,'yyyy-mm-dd') AS XHRQ,"
				+ "to_char(B.FCRQ,'yyyy-mm-dd') AS FCRQ,"
				+ "A.CFWZ AS CFWZ FROM EF_DAJBXX A,EF_DWDA B "
				+ "WHERE (A.F_STATE='�鵵' and A.F_TYPE='1' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH) "
				+ "AND(A.DAH like '%" + A.getDah() + "%') AND (B.DWZH like '%"
				+ A.getDWZH() + "%') " + "AND (B.DW like '%" + A.getDW()
				+ "%')" + "AND (B.DWDZ like '%" + A.getDWDZ() + "%')"
				+ "AND (B.GJD like '%" + A.getGjd() + "%')"
				+ "AND (B.CBWD like '%" + A.getCBWD() + "%')";
		if (A.getBj().equals("xuzhe")) {

			str = "SELECT B.ID AS ID,A.DAH AS DAH,A.GH AS GH,"
					+ "A.HH AS HH,B.DWZH AS DWZH,B.DW AS DW,B.DWDZ AS DWDZ,"
					+ "B.GJD AS GJD,B.CBWD AS CBWD,B.GJZGY AS GJZGY,"
					+ "to_char(B.KHRQ,'yyyy-mm-dd') AS KHRQ,"
					+ "to_char(B.XHRQ,'yyyy-mm-dd') AS XHRQ,"
					+ "to_char(B.FCRQ,'yyyy-mm-dd') AS FCRQ,"
					+ "A.CFWZ AS CFWZ FROM EF_DAJBXX A,EF_DWDA B "
					+ "WHERE (A.F_STATE='�鵵' and A.F_TYPE='1' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH) "
					+ "AND(A.ID in ("
					+ A.getDah().substring(0, A.getDah().length() - 1)
					+ ")) AND (B.DWZH like '%" + A.getDWZH() + "%') "
					+ "AND (B.DW like '%" + A.getDW() + "%')"
					+ "AND (B.DWDZ like '%" + A.getDWDZ() + "%')"
					+ "AND (B.GJD like '%" + A.getGjd() + "%')"
					+ "AND (B.CBWD like '%" + A.getCBWD() + "%')";

		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_GRGJCOMBOX]����" + e.getMessage());
		}

	}

	/*
	 * ���Ǳ���ĸ��˹鼯��ѯ���� 2011��9��20ygp
	 */
	// ���˹鼯������ѯ
	public List<Row> openDA_GRGJCOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;

		String str = "SELECT A.DAH AS DAH,A.GH AS GH,A.HH AS HH,B.ZGZH AS ZGZH,"
				+ "B.ZGXM AS ZGXM,B.DWZH AS DWZH,B.DW AS DW,B.ZJHM AS ZJHM,"
				+ "B.XB AS XB,B.KHRQ AS KHRQ,B.GJD AS GJD,B.CBWD AS CBWD,"
				+ "A.CFWZ AS CFWZ,A.YGDH AS YGDH,A.ID AS ID,A.JSR AS JSR,"
				+ "A.JSRQ AS JSRQ FROM EF_DAJBXX  A,EF_GJDA B "
				+ "WHERE (A.F_STATE='�鵵' and A.F_TYPE='2' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH) "
				+ "AND (A.DAH like '%"
				+ A.getDah()
				+ "%') "
				+ "AND (B.ZGZH like '%"
				+ A.getZgzh()
				+ "%') "
				+ "AND (B.ZGXM like '%"
				+ A.getZgxm()
				+ "%') "
				+ "AND (B.DWZH like '%"
				+ A.getDWZH()
				+ "%') "
				+ "AND (B.DW like '%"
				+ A.getDW()
				+ "%') "
				+ "AND (B.ZJHM like '%"
				+ A.getZjhm()
				+ "%') "
				+ "AND (B.GJD like '%"
				+ A.getGjd()
				+ "%') "
				+ "AND (B.CBWD like '%" + A.getCBWD() + "%')";
		if (A.getBj().equals("xuzhe")) {
			str = "SELECT A.DAH AS DAH,A.GH AS GH,A.HH AS HH,B.ZGZH AS ZGZH,"
					+ "B.ZGXM AS ZGXM,B.DWZH AS DWZH,B.DW AS DW,B.ZJHM AS ZJHM,"
					+ "B.XB AS XB,B.KHRQ AS KHRQ,B.GJD AS GJD,B.CBWD AS CBWD,"
					+ "A.CFWZ AS CFWZ,A.YGDH AS YGDH,A.ID AS ID,A.JSR AS JSR,"
					+ "A.JSRQ AS JSRQ FROM EF_DAJBXX  A,EF_GJDA B "
					+ "WHERE (A.F_STATE='�鵵' and A.F_TYPE='2' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH) "
					+ "AND (A.ID in ("
					+ A.getDah().substring(0, A.getDah().length() - 1)
					+ ")) "
					+ "AND (B.ZGZH like '%"
					+ A.getZgzh()
					+ "%') "
					+ "AND (B.ZGXM like '%"
					+ A.getZgxm()
					+ "%') "
					+ "AND (B.DWZH like '%"
					+ A.getDWZH()
					+ "%') "
					+ "AND (B.DW like '%"
					+ A.getDW()
					+ "%') "
					+ "AND (B.ZJHM like '%"
					+ A.getZjhm()
					+ "%') "
					+ "AND (B.GJD like '%"
					+ A.getGjd()
					+ "%') "
					+ "AND (B.CBWD like '%" + A.getCBWD() + "%')";
		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_GRGJCOMBOX]����" + e.getMessage());
		}

	}

	/*
	 * �����е�¥�̹鼯������ѯ����2011-9-20ygp
	 */
	// ¥�̹鼯������ѯ
	public List<Row> openDA_LPGJCOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;

		String str = "SELECT A.ID AS ID,A.DAH AS DAH,A.GH AS GH,A.HH AS HH,B.XMBH AS XMBH,"
				+ "B.XM AS XM,B.GJD AS GJD,B.CBWD AS CBWD,"
				+ "to_char(A.JSRQ,'yyyy-mm-dd') AS JSRQ,A.BMJB AS BMJB,"
				+ "A.CFWZ AS CFWZ FROM EF_DAJBXX A,EF_LPDA B "
				+ "WHERE (A.F_STATE='�鵵'and A.F_TYPE='5' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH) "
				+ "AND(A.DAH like '%"
				+ A.getDah()
				+ "%')"
				+ "AND(B.XMBH like '%"
				+ A.getXmbh()
				+ "%') "
				+ "AND(B.XM like '%"
				+ A.getXm()
				+ "%')"
				+ "AND (B.GJD like '%"
				+ A.getGjd()
				+ "%') "
				+ "AND (B.CBWD like '%"
				+ A.getCBWD()
				+ "%')";
		if (A.getBj().equals("xuzhe")) {
			str = "SELECT A.ID AS ID,A.DAH AS DAH,A.GH AS GH,A.HH AS HH,B.XMBH AS XMBH,"
					+ "B.XM AS XM,B.GJD AS GJD,B.CBWD AS CBWD,"
					+ "to_char(A.JSRQ,'yyyy-mm-dd') AS JSRQ,A.BMJB AS BMJB,"
					+ "A.CFWZ AS CFWZ FROM EF_DAJBXX A,EF_LPDA B "
					+ "WHERE (A.F_STATE='�鵵'and A.F_TYPE='5' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH) "
					+ "AND(A.ID in ("
					+ A.getDah().substring(0, A.getDah().length() - 1)
					+ "))"
					+ "AND(B.XMBH like '%"
					+ A.getXmbh()
					+ "%') "
					+ "AND(B.XM like '%"
					+ A.getXm()
					+ "%')"
					+ "AND (B.GJD like '%"
					+ A.getGjd()
					+ "%') "
					+ "AND (B.CBWD like '%" + A.getCBWD() + "%')";
		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_LPGJCOMBOX]����" + e.getMessage());
		}

	}

	/*
	 * ���Ǳ���鵵������ѯ 2011-9-19ygp
	 */
	// �鵵������ѯ
	public List<Row> openDA_GDDACOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;

		try {

			String str = "SELECT A.YGDH,A.DAH,A.GH,A.HH,A.BCNX,A.BMJB,A.CLFS,A.TJR,"
					+ "to_char(TJRQ,'yyyy-mm-dd') AS TJRQ,B.F_TYPE,A.CFWZ,B.ID "
					+ "FROM EF_DAJBXX A,EF_DALX B "
					+ "WHERE (A.F_STATE='�鵵' AND A.F_TYPE=B.ID) "
					+ "and (A.DAH like '%"
					+ A.getDah()
					+ "%')"
					+ "AND (A.TJR like '%"
					+ A.getTjr()
					+ "%')"
					+ A.getTjrq()
					+ A.getTjrq2()
					+ "AND(B.F_TYPE like '%"
					+ A.getF_type()
					+ "%') order by TJRQ";

			if (A.getBj().equals("xuzhe")) {
				str = "SELECT A.YGDH,A.DAH,A.GH,A.HH,A.BCNX,A.BMJB,A.CLFS,A.TJR,"
						+ "to_char(TJRQ,'yyyy-mm-dd') AS TJRQ,B.F_TYPE,A.CFWZ,B.ID "
						+ "FROM EF_DAJBXX A,EF_DALX B "
						+ "WHERE (A.F_STATE='�鵵' AND A.F_TYPE=B.ID) "
						+ "and (A.ID in ("
						+ A.getDah().substring(0, A.getDah().length() - 1)
						+ "))"
						+ "AND (A.TJR like '%"
						+ A.getTjr()
						+ "%')"
						+ A.getTjrq()
						+ A.getTjrq2()
						+ "AND(B.F_TYPE like '%"
						+ A.getF_type() + "%') order by TJRQ";
			}
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_GDDACOMBOX]����" + e.getMessage());
		}

	}

	/*
	 * �����е����⵵����ѯ2011-9-20ygp
	 */
	// ���⵵����ѯ
	public List<Row> openDA_TSDACOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;

		String str = "SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,A.GH AS GH,A.HH AS HH,"
				+ "A.BMJB AS BMJB,A.BCNX AS BCNX,A.CLFS AS CLFS,A.TJR AS TJR,A.TJRQ AS TJRQ,"
				+ "A.JSR AS JSR,A.JSRQ AS JSRQ,A.ZXR AS ZXR,A.ZXRQ AS ZXRQ,A.ZXYY AS ZXYY,"
				+ "A.SXH AS SXH,A.F_STATE AS F_STATE,B.TM AS TM,C.F_TYPE AS ZTLB,"
				+ "B.ZRZ AS ZRZ,A.CFWZ AS CFWZ  FROM EF_DAJBXX A,EF_TSDA B,EF_DALX C WHERE "
				+ "(A.F_STATE='�鵵' and A.F_TYPE in(7,8,9,10,11,13) and A.YGDH=B.YGDH AND A.F_TYPE=C.ID) AND "
				+ "(A.DAH like '%"
				+ A.getDah()
				+ "%') AND (B.TM like '%"
				+ A.getTM()
				+ "%') AND "
				+ "(B.ZTLB like '%"
				+ A.getZTLB()
				+ "%') AND (B.ZRZ like '%"
				+ A.getZRZ()
				+ "%') AND "
				+ "(A.CFWZ like '%" + A.getCfwz() + "%')";
		if (A.getBj().equals("xuzhe")) {
			str = "SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,A.GH AS GH,A.HH AS HH,"
					+ "A.BMJB AS BMJB,A.BCNX AS BCNX,A.CLFS AS CLFS,A.TJR AS TJR,A.TJRQ AS TJRQ,"
					+ "A.JSR AS JSR,A.JSRQ AS JSRQ,A.ZXR AS ZXR,A.ZXRQ AS ZXRQ,A.ZXYY AS ZXYY,"
					+ "A.SXH AS SXH,A.F_STATE AS F_STATE,B.TM AS TM,C.F_TYPE AS ZTLB,"
					+ "B.ZRZ AS ZRZ,A.CFWZ AS CFWZ  FROM EF_DAJBXX A,EF_TSDA B,EF_DALX C WHERE "
					+ "(A.F_STATE='�鵵' and A.F_TYPE in(7,8,9,10,11,13) and A.YGDH=B.YGDH AND A.F_TYPE=C.ID) AND "
					+ "(A.ID in ("
					+ A.getDah().substring(0, A.getDah().length() - 1)
					+ ")) AND (B.TM like '%"
					+ A.getTM()
					+ "%') AND "
					+ "(B.ZTLB like '%"
					+ A.getZTLB()
					+ "%') AND (B.ZRZ like '%"
					+ A.getZRZ()
					+ "%') AND "
					+ "(A.CFWZ like '%" + A.getCfwz() + "%')";
		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_TSDACOMBOX]����" + e.getMessage());
		}

	}

	/*
	 * �����е��������¼����2011-9-20ygp
	 */
	// �������¼
	public List<Row> openDA_TXMCOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;
		String str = "SELECT ID,DAH,GH,HH,TJR,to_char(TJRQ,'yyyy-mm-dd') AS TJRQ,BMJB,"
				+ "BCNX FROM EF_DAJBXX WHERE (DAH like '" + A.getDah() + "')";
		if (A.getBj().equals("xuzhe")) {
			str = "SELECT ID,DAH,GH,HH,TJR,to_char(TJRQ,'yyyy-mm-dd') AS TJRQ,BMJB,"
					+ "BCNX FROM EF_DAJBXX WHERE (EF_DAJBXX.ID in ("
					+ A.getDah().substring(0, A.getDah().length() - 1) + "))";
		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_TSDACOMBOX]����" + e.getMessage());
		}

	}

	/*
	 * �����е����鵵��2011-9-20ygp
	 */
	// ���鵵��-
	public List<Row> openDA_WSCOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;

		String str = "SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,"
				+ "A.TJR AS TJR,A.TJRQ AS TJRQ,A.JSR AS JSR,"
				+ "A.DASH AS DASH,A.GH AS GH,A.YWLSH AS YWLSH,"
				+ "A.GROW AS GROW,A.HH AS HH,A.BMJB AS BMJB,"
				+ "A.JSRQ AS JSRQ,A.BCNX AS BCNX,A.ZXR AS ZXR,"
				+ "A.ZXRQ AS ZXRQ,A.ZXYY AS ZXYY,A.CLFS AS CLFS,"
				+ "A.SXH AS SXH,C.F_TYPE AS F_TYPE,A.F_STATE AS F_STATE,"
				+ "B.TM AS TM,B.YJML AS YJML,B.EJML AS EJML,"
				+ "B.SJML AS SJML,B.ZRZ AS ZRZ,A.CFWZ AS CFWZ,"
				+ "C.ID AS F_TYPEB FROM EF_DAJBXX A ,EF_WSDA B ,"
				+ "EF_DALX C  "
				+ "WHERE (A.F_STATE='�鵵' and A.F_TYPE='6' and A.YGDH=B.YGDH and A.F_TYPE=C.ID) "
				+ "AND(A.DAH like '%" + A.getDah() + "%') AND(B.TM like '%"
				+ A.getTM() + "%') " + "AND(B.YJML like '%" + A.getYJML()
				+ "%') AND (B.EJML like '%" + A.getEJML() + "%')"
				+ "AND(B.SJML like '%" + A.getSJML() + "%') AND (B.ZRZ like '%"
				+ A.getZRZ() + "%')";
		if (A.getBj().equals("xuzhe")) {
			str = "SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,"
					+ "A.TJR AS TJR,A.TJRQ AS TJRQ,A.JSR AS JSR,"
					+ "A.DASH AS DASH,A.GH AS GH,A.YWLSH AS YWLSH,"
					+ "A.GROW AS GROW,A.HH AS HH,A.BMJB AS BMJB,"
					+ "A.JSRQ AS JSRQ,A.BCNX AS BCNX,A.ZXR AS ZXR,"
					+ "A.ZXRQ AS ZXRQ,A.ZXYY AS ZXYY,A.CLFS AS CLFS,"
					+ "A.SXH AS SXH,C.F_TYPE AS F_TYPE,A.F_STATE AS F_STATE,"
					+ "B.TM AS TM,B.YJML AS YJML,B.EJML AS EJML,"
					+ "B.SJML AS SJML,B.ZRZ AS ZRZ,A.CFWZ AS CFWZ,"
					+ "C.ID AS F_TYPEB FROM EF_DAJBXX A ,EF_WSDA B ,"
					+ "EF_DALX C  "
					+ "WHERE (A.F_STATE='�鵵' and A.F_TYPE='6' and A.YGDH=B.YGDH and A.F_TYPE=C.ID) "
					+ "AND(A.ID in ("
					+ A.getDah().substring(0, A.getDah().length() - 1)
					+ ")) AND(B.TM like '%" + A.getTM() + "%') "
					+ "AND(B.YJML like '%" + A.getYJML()
					+ "%') AND (B.EJML like '%" + A.getEJML() + "%')"
					+ "AND(B.SJML like '%" + A.getSJML()
					+ "%') AND (B.ZRZ like '%" + A.getZRZ() + "%')";
		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_WSCOMBOX����" + e.getMessage());
		}
	}

	/*
	 * �����е����ټ�¼����2011-9-20ygp
	 */
	// ���ټ�¼
	public List<Row> openDA_XHCOMBOX(Object obj) throws CallDbException {
		XhjlBean A = (XhjlBean) obj;

		String str = "SELECT A.ID,A.DAH,B.F_TYPE AS DALX ,A.GJD,A.XHR,to_char(XHRQ,'yyyy-mm-dd') AS XHRQ,A.XHJDR,"
				+ "A.DAZT,A.XHYY FROM EF_XHJL A,EF_DALX B "
				+ "WHERE(DALX='2' and A.DALX=B.ID)AND (A.DAH like '%"
				+ A.getDAH()
				+ "%') AND "
				+ "(A.DALX like '%"
				+ A.getDALX()
				+ "%') AND (A.GJD like '%"
				+ A.getGJD()
				+ "%') AND "
				+ "(A.XHR like '%"
				+ A.getXHR()
				+ "%') "
				+ A.getXHRQ()
				+ A.getXHRQ2()
				+ "AND (A.XHJDR like '%"
				+ A.getXHJDR()
				+ "%') AND(A.DAZT like '%"
				+ A.getDAZT()
				+ "%') AND "
				+ "(A.XHYY like '%" + A.getXHYY() + "%')";
		if (A.getBJ().equals("xuzhe")) {
			str = "SELECT A.ID,A.DAH,B.F_TYPE AS DALX ,A.GJD,A.XHR,to_char(XHRQ,'yyyy-mm-dd') AS XHRQ,A.XHJDR,"
					+ "A.DAZT,A.XHYY FROM EF_XHJL A,EF_DALX B "
					+ "WHERE(DALX='2' and A.DALX=B.ID)AND (A.ID in ("
					+ A.getDAH().substring(0, A.getDAH().length() - 1)
					+ ")) AND "
					+ "(A.DALX like '%"
					+ A.getDALX()
					+ "%') AND (A.GJD like '%"
					+ A.getGJD()
					+ "%') AND "
					+ "(A.XHR like '%"
					+ A.getXHR()
					+ "%') "
					+ A.getXHRQ()
					+ A.getXHRQ2()
					+ "AND (A.XHJDR like '%"
					+ A.getXHJDR()
					+ "%') AND(A.DAZT like '%"
					+ A.getDAZT()
					+ "%') AND "
					+ "(A.XHYY like '%" + A.getXHYY() + "%')";
		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_XHCOMBOX����" + e.getMessage());
		}
	}

	/*
	 * �����ص�֧ȡ�����鼯����2011-9-20ygp
	 */
	// ֧ȡ�����鼯
	public List<Row> openDA_ZQDACOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;

		String str = "SELECT A.DAH AS DAH,A.GH AS GH,A.HH AS HH,B.ZGZH AS ZGZH,"
				+ "B.ZGXM AS ZGXM,B.ZJHM AS ZJHM,B.ZQRQ AS ZQRQ,B.DWZH AS DWZH,"
				+ "B.DW AS DW,A.CFWZ AS CFWZ,A.YGDH AS YGDH,A.ID AS ID "
				+ "FROM EF_DAJBXX A,EF_ZQDA B "
				+ "WHERE (A.F_STATE='�鵵' AND A.F_TYPE='3' and A.YGDH=B.YGDH AND A.YWLSH=B.YWLSH)"
				+ "AND(A.DAH like '%"
				+ A.getDah()
				+ "%') "
				+ "AND (B.ZGZH like '%"
				+ A.getZgzh()
				+ "%')"
				+ "AND(B.ZGXM like '%"
				+ A.getZgxm()
				+ "%')"
				+ "AND (B.ZJHM like '%"
				+ A.getZjhm()
				+ "%')"
				+ "AND(B.DWZH like '%" + A.getDWZH() + "%')";
		if (A.getBj().equals("xuzhe")) {
			str = "SELECT A.DAH AS DAH,A.GH AS GH,A.HH AS HH,B.ZGZH AS ZGZH,"
					+ "B.ZGXM AS ZGXM,B.ZJHM AS ZJHM,B.ZQRQ AS ZQRQ,B.DWZH AS DWZH,"
					+ "B.DW AS DW,A.CFWZ AS CFWZ,A.YGDH AS YGDH,A.ID AS ID "
					+ "FROM EF_DAJBXX A,EF_ZQDA B "
					+ "WHERE (A.F_STATE='�鵵' AND A.F_TYPE='3' and A.YGDH=B.YGDH AND A.YWLSH=B.YWLSH)"
					+ "AND(A.ID in ("
					+ A.getDah().substring(0, A.getDah().length() - 1) + ")) "
					+ "AND (B.ZGZH like '%" + A.getZgzh() + "%')"
					+ "AND(B.ZGXM like '%" + A.getZgxm() + "%')"
					+ "AND (B.ZJHM like '%" + A.getZjhm() + "%')"
					+ "AND(B.DWZH like '%" + A.getDWZH() + "%')";
		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_ZQDACOMBOX����" + e.getMessage());
		}
	}

	/*
	 * �����е�ע�������鼯����2011-9-20ygp
	 */
	// ע�������鼯
	public List<Row> openDA_ZXCOMBOX(Object obj) throws CallDbException {
		DajbxxBean A = (DajbxxBean) obj;

		String str = "SELECT A.ID,A.YGDH,A.DAH,A.GH,A.HH,A.BCNX,A.BMJB,A.CLFS,B.F_TYPE,A.ZXR,"
				+ "to_char(ZXRQ,'yyyy-mm-dd') AS ZXRQ,A.ZXYY,A.CFWZ FROM "
				+ "EF_DAJBXX A,EF_DALX B WHERE(A.F_STATE='ע��' AND A.F_TYPE=B.ID)AND (A.DAH like '%"
				+ A.getDah()
				+ "%' ) "
				+ "AND(B.F_TYPE like '%"
				+ A.getF_type()
				+ "%') "
				+ "AND (A.ZXR like '%"
				+ A.getZxr()
				+ "%') "
				+ A.getZxrq() + A.getZxrq2();
		if (A.getBj().equals("xuzhe")) {
			str = "SELECT A.ID,A.YGDH,A.DAH,A.GH,A.HH,A.BCNX,A.BMJB,A.CLFS,B.F_TYPE,A.ZXR,"
					+ "to_char(ZXRQ,'yyyy-mm-dd') AS ZXRQ,A.ZXYY,A.CFWZ FROM "
					+ "EF_DAJBXX A,EF_DALX B WHERE(A.F_STATE='ע��' AND A.F_TYPE=B.ID)AND (A.ID in ("
					+ A.getDah().substring(0, A.getDah().length() - 1)
					+ ") ) "
					+ "AND(B.F_TYPE like '%"
					+ A.getF_type()
					+ "%') "
					+ "AND (A.ZXR like '%"
					+ A.getZxr()
					+ "%') "
					+ A.getZxrq()
					+ A.getZxrq2();
		}
		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_ZXCOMBOX����" + e.getMessage());
		}

	}

	// �������ͳ�� xy
	public List<Row> openDA_CJTJ_REPORT(Object obj) throws CallDbException {
		TJReportBean bean = (TJReportBean) obj;
		String str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
				+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
				+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
				+

				bean.getJYRQ_begin()
				+ bean.getJYRQ_end()
				+ bean.getNHRQ_begin()
				+ bean.getNHRQ_end()
				+ "ORDER BY EF_JYJL.ID";
		// 1
		if (!bean.getJYRQ_begin().equals(" ")
				&& !bean.getJYRQ_end().equals(" ")
				&& bean.getNHRQ_begin().equals(" ")
				&& bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ "and"
					+ bean.getJYRQ_end()
					+ bean.getNHRQ_begin()
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}

		// 2
		if (!bean.getJYRQ_begin().equals(" ") && bean.getJYRQ_end().equals(" ")
				&& !bean.getNHRQ_begin().equals(" ")
				&& bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ bean.getJYRQ_end()
					+ "AND"
					+ bean.getNHRQ_begin()
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}
		if (!bean.getJYRQ_begin().equals(" ") && bean.getJYRQ_end().equals(" ")
				&& bean.getNHRQ_begin().equals(" ")
				&& !bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ bean.getJYRQ_end()
					+ "AND"
					+ bean.getNHRQ_begin()
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}
		if (bean.getJYRQ_begin().equals(" ") && !bean.getJYRQ_end().equals(" ")
				&& !bean.getNHRQ_begin().equals(" ")
				&& bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ bean.getJYRQ_end()
					+ "AND"
					+ bean.getNHRQ_begin()
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}
		if (bean.getJYRQ_begin().equals(" ") && !bean.getJYRQ_end().equals(" ")
				&& bean.getNHRQ_begin().equals(" ")
				&& !bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ bean.getJYRQ_end()
					+ "AND"
					+ bean.getNHRQ_begin()
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}
		// 3
		if (bean.getJYRQ_begin().equals(" ") && bean.getJYRQ_end().equals(" ")
				&& !bean.getNHRQ_begin().equals(" ")
				&& !bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ bean.getJYRQ_end()
					+ bean.getNHRQ_begin()
					+ "AND"
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}
		// 4
		if (!bean.getJYRQ_begin().equals(" ")
				&& !bean.getJYRQ_end().equals(" ")
				&& !bean.getNHRQ_begin().equals(" ")
				&& bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ "and"
					+ bean.getJYRQ_end()
					+ "and"
					+ bean.getNHRQ_begin()
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}
		if (!bean.getJYRQ_begin().equals(" ")
				&& !bean.getJYRQ_end().equals(" ")
				&& bean.getNHRQ_begin().equals(" ")
				&& !bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ "and"
					+ bean.getJYRQ_end()
					+ "and"
					+ bean.getNHRQ_begin()
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}
		// 5
		if (!bean.getJYRQ_begin().equals(" ") && bean.getJYRQ_end().equals(" ")
				&& !bean.getNHRQ_begin().equals(" ")
				&& !bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ bean.getJYRQ_end()
					+ "and"
					+ bean.getNHRQ_begin()
					+ "and"
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}

		if (bean.getJYRQ_begin().equals(" ") && !bean.getJYRQ_end().equals(" ")
				&& !bean.getNHRQ_begin().equals(" ")
				&& !bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ bean.getJYRQ_end()
					+ "and"
					+ bean.getNHRQ_begin()
					+ "and"
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}
		// 6
		if (!bean.getJYRQ_begin().equals(" ")
				&& !bean.getJYRQ_end().equals(" ")
				&& !bean.getNHRQ_begin().equals(" ")
				&& !bean.getNHRQ_end().equals(" ")) {
			str = "SELECT EF_JYJL.DAH,EF_JYJL.YGDH,EF_JYJL.DALX,EF_JYJL.DJR,EF_JYJL.GH,"
					+ "EF_JYJL.HH,EF_JYJL.JYR,EF_JYJL.JYRQ,EF_JYJL.NHRQ,EF_JYJL.BCDZ,"
					+ "EF_JYJL.JYMD,EF_JYJL.ID,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ FROM EF_JYJL WHERE "
					+

					bean.getJYRQ_begin()
					+ "AND"
					+ bean.getJYRQ_end()
					+ "and"
					+ bean.getNHRQ_begin()
					+ "and"
					+ bean.getNHRQ_end()
					+ "ORDER BY EF_JYJL.ID";
		}

		try {
			return bo.queryToList(str);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_CJTJ_REPORT]����" + e.getMessage());
		}

	}

	// ��λ�鼯����ͳ�� xy
	public List<Row> openDA_TJDWGJ_REPORT(Object obj) throws CallDbException {
		TJReportBean bean = (TJReportBean) obj;

		try {
			return bo.queryToList(bean.getStr());

		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_TJDWGJ_REPORT]����" + e.getMessage());
		}

	}

	// ���������б� 2011-09-23 BY YGP
	public List<Row> openDA_GETBCNX(Object obj) throws CallDbException {
		SqlParameterExt spx = null;
		DajbxxBean pmt = (DajbxxBean) obj;
		String sql = "	SELECT ID,BCNX FROM EF_DAJBXX order by bcnx";

		try {
			spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_GETBCNX]����" + e.getMessage());
		}
	}

	/**********************************************************************************
	 * ϵͳ���ã�������� 2011-09-23 BY DHT *
	 **********************************************************************************/
	// ���������б� 2011-09-23 BY DHT
	public List<Row> openDA_GETDALX(Object obj) throws CallDbException {
		SqlParameterExt spx = null;
		DaLxBean pmt = (DaLxBean) obj;
		String sql = "	SELECT ID,F_TYPE,TBLNAME,LXBZ,DAH_PNAME FROM EF_DALX A ";

		try {
			spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_GETDALX]����" + e.getMessage());
		}
	}

	// �����б� 2011-09-23 DHT
	public List<Row> openDA_GETJNMLSZ(Object obj) throws CallDbException {
		SqlParameterExt spx = null;
		JnmlSzBean pmt = (JnmlSzBean) obj;
		String sql = " SELECT ID,CL,CLBM  FROM  BM_JNMLSZ B  ";
		try {
			spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ�з���[openDA_GETJNMLSZ]����"
					+ e.getMessage());
		}
	}

	/**
	 * �������������� 2011-09-23 by DHT
	 */
	public void executeDA_DASZJNML_ADD(Object obj) throws CallDbException {
		DaSzJnmlBean pmt = (DaSzJnmlBean) obj;
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt(); // ����ת��
			spx.add(new LongValue(Long.parseLong(pmt.getYjfs()))); //
			spx.add(new LongValue(Long.parseLong(pmt.getFyjfs()))); //
			spx.add(new LongValue(Long.parseLong(pmt.getSfbx())));
			spx.add(new StringValue(pmt.getDalx()));//
			spx.add(new StringValue(pmt.getBz()));//
			spx.add(new StringValue(pmt.getPclbm()));//
			spx.add(new ShortValue((short) 1), SqlParameterExt.SQL_OUTPUT); // --
																			// ����ֵ
																			// 0
																			// �ɹ�,1�Ѵ���,99ʧ��
		} catch (Exception e) {
			pmt.setRet((short) 99);
			throw new CallDbException("ִ�з������ݿ��������" + e.getMessage());
		}
		String sql = "p_da_daszjnml_add(?p1,?p2,?p3,?p4,?p5,?p6,?p7)";
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			// ��ȡ����ֵ
			pmt.setRet(spx.get(7).getShort());
		} catch (Exception e) {
			pmt.setRet((short) 99);
			throw new CallDbException("ִ�з���[executeDA_DASZJNML_ADD]����"
					+ e.getMessage());
		}
	}

	/**
	 * �����������ɾ�� 2011-09-23 by DHT
	 */
	public void executeDA_DASZJNML_DEL(Object obj) throws CallDbException {
		DaSzJnmlBean pmt = (DaSzJnmlBean) obj;
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt(); // ����ת��
			spx.add(new LongValue(pmt.getId())); // ID
			spx.add(new ShortValue((short) 1), SqlParameterExt.SQL_OUTPUT);
			// ����ֵ��0��ȷ��1������ 99-ɾ��ʧ��
		} catch (Exception e) {
			pmt.setRet((short) 99);
			throw new CallDbException("ִ�з������ݿ��������" + e.getMessage());
		}
		String sql = "p_da_daszjnml_del(?p1,?p2)";
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			// ��ȡ����ֵ
			pmt.setRet(spx.get(2).getShort());
		} catch (Exception e) {
			pmt.setRet((short) 99);
			throw new CallDbException("ִ�з���[executeDA_DASZJNML_DEL]����"
					+ e.getMessage());
		}
	}

	/**
	 * ������������޸� 2011-09-23 by DHT
	 */
	public void executeDA_DASZJNML_CHG(Object obj) throws CallDbException {
		DaSzJnmlBean pmt = (DaSzJnmlBean) obj;
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt(); // ����ת��
			spx.add(new LongValue(pmt.getId()));// ID
			spx.add(new LongValue(Long.parseLong(pmt.getYjfs()))); //
			spx.add(new LongValue(Long.parseLong(pmt.getFyjfs()))); //
			spx.add(new LongValue(Long.parseLong(pmt.getSfbx())));
			// spx.add(new StringValue(pmt.getDalx()));//
			spx.add(new StringValue(pmt.getBz()));//
			// spx.add(new StringValue(pmt.getPclbm()));//
			spx.add(new ShortValue((short) 1), SqlParameterExt.SQL_OUTPUT); // --
																			// ����ֵ
																			// 0
																			// �ɹ�,1������,99ʧ��
		} catch (Exception e) {
			pmt.setRet((short) 99);
			throw new CallDbException("ִ�з������ݿ��������" + e.getMessage());
		}
		String sql = "p_da_daszjnml_chg(?p1,?p2,?p3,?p4,?p5,?p6)";
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			// ��ȡ����ֵ
			pmt.setRet(spx.get(6).getShort());
		} catch (Exception e) {
			pmt.setRet((short) 99);
			throw new CallDbException("ִ�з���[executeDA_DASZJNML_ADD]����"
					+ e.getMessage());
		}
	}
	//-------------------------------------------------------------------------������ѯ����
	/**
	 * ������ѯ-���鵵����ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getWscxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		WsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(WsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,B.TM AS TM,A.TJR AS TJR,A.TJRQ AS TJRQ,A.GROW AS GROW," +
				"A.DASH AS DASH,A.GH AS GH,A.HH AS HH,A.SXH AS SXH,A.JSR AS JSR,A.BMJB AS BMJB," +
				"A.JSRQ AS JSRQ,A.BCNX AS BCNX,A.ZXR AS ZXR,A.ZXRQ AS ZXRQ,A.ZXYY AS ZXYY,A.CLFS AS CLFS," +
				"A.F_TYPE AS F_TYPE,A.F_STATE AS F_STATE,B.YJML AS YJML,B.EJML AS EJML,B.SJML AS SJML," +
				"B.ZRZ AS ZRZ,A.CFWZ AS CFWZ FROM EF_DAJBXX A,EF_WSDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='6' and A.YGDH=B.YGDH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getYjml()))){
			sbf+="AND B.YJML LIKE '%"+pmt.getYjml()+"%' ";
		}
		if(!("".equals(pmt.getEjml()))){
			sbf+="AND B.EJML LIKE '%"+pmt.getEjml()+"%' ";
		}
		if(!("".equals(pmt.getSjml()))){
			sbf+="AND B.SJML LIKE '%"+pmt.getSjml()+"%' ";
		}
		if(!("".equals(pmt.getZrz()))){
			sbf+="AND B.ZRZ LIKE '%"+pmt.getZrz()+"%' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-���⵵����ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getTscxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(TsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,A.DASH AS DASH," +
				"A.GH AS GH,A.GROW AS GROW,A.HH AS HH,A.BMJB AS BMJB,A.BCNX AS BCNX,A.CLFS AS CLFS," +
				"A.TJR AS TJR,A.TJRQ AS TJRQ,A.JSR AS JSR,A.JSRQ AS JSRQ,A.ZXR AS ZXR,A.ZXRQ AS ZXRQ," +
				"A.ZXYY AS ZXYY,A.SXH AS SXH,C.F_TYPE AS F_TYPE,A.F_STATE AS F_STATE,B.TM AS TM," +
				"B.ZTLB AS ZTLB,B.ZRZ AS ZRZ,A.CFWZ AS CFWZ"+
				" FROM EF_DAJBXX A,EF_TSDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE in(7,8,9,10,11,13) and A.YGDH=B.YGDH AND B.ZTLB=C.ID) ");
		
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getCfwz()))){
			sbf+="AND B.CFWZ LIKE '%"+pmt.getCfwz()+"%' ";
		}
		if(!("".equals(pmt.getF_type()))){
			sbf+="AND C.F_TYPE LIKE '%"+pmt.getF_type()+"%' ";
		}
		if(!("".equals(pmt.getZrz()))){
			sbf+="AND B.ZRZ LIKE '%"+pmt.getZrz()+"%' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}

	/**
	 * ������ѯ-�Ƽ�������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getZlcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(ZldaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,B.TM AS TM,A.GROW AS GROW," +
				"A.DASH AS DASH,A.GH AS GH,A.HH AS HH,A.BMJB AS BMJB," +
				"A.BCNX AS BCNX," +
				"A.F_TYPE AS F_TYPE,A.YWLSH AS YWLSH,B.CJR AS CJR,B.CJRQ AS CJRQ" +
				" FROM EF_DAJBXX A,EF_ZLDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='7' and A.YGDH=B.YGDH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-��Ƭ������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getZpcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(TsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,B.TM AS TM,A.GROW AS GROW," +
				"A.DASH AS DASH,A.GH AS GH,A.HH AS HH,A.BMJB AS BMJB," +
				"A.BCNX AS BCNX," +
				"A.F_TYPE AS F_TYPE,A.YWLSH AS YWLSH,B.CJR AS CJR,B.CJRQ AS CJRQ" +
				" FROM EF_DAJBXX A,EF_TSDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='8' and A.YGDH=B.YGDH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-¼�񵵰���ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getLxcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(TsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,B.TM AS TM,A.GROW AS GROW," +
				"A.DASH AS DASH,A.GH AS GH,A.HH AS HH,A.BMJB AS BMJB," +
				"A.BCNX AS BCNX," +
				"A.F_TYPE AS F_TYPE,A.YWLSH AS YWLSH,B.CJR AS CJR,B.CJRQ AS CJRQ" +
				" FROM EF_DAJBXX A,EF_TSDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='9' and A.YGDH=B.YGDH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-ʵ�ﵵ����ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getSwcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(ZldaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,B.TM AS TM,A.GROW AS GROW," +
				"A.DASH AS DASH,A.GH AS GH,A.HH AS HH,A.BMJB AS BMJB," +
				"A.BCNX AS BCNX," +
				"A.F_TYPE AS F_TYPE,A.YWLSH AS YWLSH,B.CJR AS CJR,B.CJRQ AS CJRQ" +
				" FROM EF_DAJBXX A,EF_ZLDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='10' and A.YGDH=B.YGDH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-֪ʶ������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getZscxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(TsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,B.TM AS TM,A.GROW AS GROW," +
				"A.DASH AS DASH,A.GH AS GH,A.HH AS HH,A.BMJB AS BMJB," +
				"A.BCNX AS BCNX," +
				"A.F_TYPE AS F_TYPE,A.YWLSH AS YWLSH,B.CJR AS CJR,B.CJRQ AS CJRQ" +
				" FROM EF_DAJBXX A,EF_TSDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='11' and A.YGDH=B.YGDH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-���Ƶ�����ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getFzcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(ZldaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,B.TM AS TM,A.GROW AS GROW," +
				"A.DASH AS DASH,A.GH AS GH,A.HH AS HH,A.BMJB AS BMJB," +
				"A.BCNX AS BCNX," +
				"A.F_TYPE AS F_TYPE,A.YWLSH AS YWLSH,B.CJR AS CJR,B.CJRQ AS CJRQ" +
				" FROM EF_DAJBXX A,EF_ZLDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='13' and A.YGDH=B.YGDH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-�鵵������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getGdcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(DaBean)obj;
		sbf=("SELECT A.YGDH AS YGDH,A.DAH AS DAH,A.DASH AS DASH,A.GROW AS GROW,A.GH AS GH,A.HH AS HH," +
				"A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-MM-dd') AS TJRQ," +
				"B.F_TYPE AS F_TYPE,A.CFWZ AS CFWZ,A.ID AS ID " +
				"FROM EF_DAJBXX A,EF_DALX B WHERE (A.F_STATE='�鵵' AND A.F_TYPE=B.ID)");
		
		if(!("".equals(pmt.getTjr()))){
			sbf+="AND A.TJR LIKE '%"+pmt.getTjr()+"%' ";
		}
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getF_type()))){
			sbf+="AND B.F_TYPE LIKE '%"+pmt.getF_type()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(A.TJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(A.TJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-ע��������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getZxcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(DaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,A.GH AS GH," +
				"A.HH AS HH,A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS,B.F_TYPE AS F_TYPE,A.ZXR AS ZXR," +
				"to_char(A.ZXRQ,'yyyy-mm-dd') AS ZXRQ,A.ZXYY AS ZXYY,A.CFWZ AS CFWZ" +
				" FROM EF_DAJBXX A,EF_DALX B WHERE (A.F_STATE='ע��' and A.F_TYPE=B.ID) ");
		
		if(!("".equals(pmt.getZxr()))){
			sbf+="AND A.ZXR LIKE '%"+pmt.getZxr()+"%' ";
		}
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getF_type()))){
			sbf+="AND B.F_TYPE LIKE '%"+pmt.getF_type()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(A.ZXRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(A.ZXRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-���ٵ�����ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getXhcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(ZdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH," +
				"B.F_TYPE AS F_TYPE,A.XHR AS XHR,A.GJD AS GJD,A.DAZT AS DAZT," +
				"to_char(A.XHRQ,'yyyy-mm-dd') AS XHRQ,A.XHYY AS XHYY,A.XHJDR AS XHJDR"+
				" FROM EF_XHJL A,EF_DALX B WHERE (A.DAZT='����' and A.DALX=B.ID) ");
		
		if(!("".equals(pmt.getXhr()))){
			sbf+="AND A.XHR LIKE '%"+pmt.getXhr()+"%' ";
		}
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getF_type()))){
			sbf+="AND B.F_TYPE LIKE '%"+pmt.getF_type()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(A.XHRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(A.XHRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		if(!("".equals(pmt.getXhjdr()))){
			sbf+="AND A.XHJDR LIKE '%"+pmt.getXhjdr()+"%' ";
		}
		if(!("".equals(pmt.getGjd()))){
			sbf+="AND A.GJD LIKE '%"+pmt.getGjd()+"%' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-�������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getDkcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DkdaBean pmt = null;
		SqlParameterExt spx = new SqlParameterExt();
		String sbf="";
		pmt=(DkdaBean)obj;
		sbf=("SELECT A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.PH AS PH,A.GROW AS GROW," +
				"A.HH AS HH,A.SXH as SXH,B.ZGZH AS ZGZH,B.ZGXM AS ZGXM,B.ZJHM AS ZJHM,B.DWZH AS DWZH," +
				"B.DW AS DW,B.DKNX AS DKNX,B.GFLX AS GFLX,B.GJD AS GJD,B.CBWD AS CBWD,A.ID AS ID,"+
				"A.CFWZ AS CFWZ,A.BMJB AS BMJB,C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB"+
				" FROM EF_DAJBXX  A,EF_DKDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='4' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getZgzh()))){
			sbf+="AND B.XHR LIKE '%"+pmt.getZgzh()+"%' ";
		}
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getZgxm()))){
			sbf+="AND B.ZGXM LIKE '%"+pmt.getZgxm()+"%' ";
		}
		if(!("".equals(pmt.getZjhm()))){
			sbf+="AND B.ZJHM LIKE '%"+pmt.getZjhm()+"%' ";
		}
		if(!("".equals(pmt.getDwzh()))){
			sbf+="AND B.DWZH LIKE '%"+pmt.getDwzh()+"%' ";
		}
		if(!("".equals(pmt.getGflx()))){
			sbf+="AND B.GFLX LIKE '%"+pmt.getGflx()+"%' ";
		}
		if(!("".equals(pmt.getGjd()))){
			sbf+="AND B.GJD LIKE '%"+pmt.getGjd()+"%' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			System.out.println("�ǹ���Ա");
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
			System.out.println("���ǹ���Ա");
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-���˹鼯������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getGjcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		GjdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(GjdaBean)obj;
		sbf=("SELECT A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW," +
				"A.HH AS HH,B.ZGZH AS ZGZH,B.ZGXM AS ZGXM,B.DWZH AS DWZH,B.DW AS DW,B.ZJHM AS ZJHM," +
				"B.XB AS XB,to_char(B.KHRQ,'yyyy-mm-dd') AS KHRQ,B.GJD AS GJD,B.CBWD AS CBWD,"+
				"A.CFWZ AS CFWZ,A.YGDH AS YGDH,A.ID AS ID,A.JSR AS JSR,A.JSRQ AS JSRQ,A.BMJB AS BMJB,C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB"+
				" FROM EF_DAJBXX  A,EF_GJDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='2' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getZgzh()))){
			sbf+="AND B.ZGZH LIKE '%"+pmt.getZgzh()+"%' ";
		}
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getZgxm()))){
			sbf+="AND B.ZGXM LIKE '%"+pmt.getZgxm()+"%' ";
		}
		if(!("".equals(pmt.getZjhm()))){
			sbf+="AND B.ZJHM LIKE '%"+pmt.getZjhm()+"%' ";
		}
		if(!("".equals(pmt.getDwzh()))){
			sbf+="AND B.DWZH LIKE '%"+pmt.getDwzh()+"%' ";
		}
		if(!("".equals(pmt.getDw()))){
			sbf+="AND B.DW LIKE '%"+pmt.getDw()+"%' ";
		}
		if(!("".equals(pmt.getGjd()))){
			sbf+="AND B.GJD LIKE '%"+pmt.getGjd()+"%' ";
		}
		/**
		 *  �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-����֧ȡ������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getZqcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZqdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(ZqdaBean)obj;
		sbf=("SELECT A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH," +
				"B.ZGZH AS ZGZH,B.ZGXM AS ZGXM,B.ZJHM AS ZJHM,B.ZQRQ AS ZQRQ,B.DWZH AS DWZH," +
				"B.DW AS DW,A.CFWZ AS CFWZ,A.YGDH AS YGDH,A.ID AS ID,A.BMJB AS BMJB,"+
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB"+
				" FROM EF_DAJBXX A,EF_ZQDA B,EF_DALX C WHERE (A.F_STATE='�鵵' AND A.F_TYPE='3' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getZgzh()))){
			sbf+="AND B.ZGZH LIKE '%"+pmt.getZgzh()+"%' ";
		}
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getZgxm()))){
			sbf+="AND B.ZGXM LIKE '%"+pmt.getZgxm()+"%' ";
		}
		if(!("".equals(pmt.getZjhm()))){
			sbf+="AND B.ZJHM LIKE '%"+pmt.getZjhm()+"%' ";
		}
		if(!("".equals(pmt.getDwzh()))){
			sbf+="AND B.DWZH LIKE '%"+pmt.getDwzh()+"%' ";
		}
		
		/**
		 *  �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-¥�̵�����ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getLpcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		LpdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(LpdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.DASH AS DASH,A.GH ASGH,A.GROW AS GROW,A.HH AS HH," +
				"B.XMBH AS XMBH,B.XM AS XM,B.GJD AS GJD,B.CBWD AS CBWD,A.BMJB AS BMJB,to_char(A.JSRQ,'yyyy-mm-dd') AS JSRQ," +
				"A.CFWZ AS CFWZ,C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB"+
				" FROM EF_DAJBXX A,EF_LPDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='5' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID) ");
		
		
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getXmbh()))){
			sbf+="AND A.XMBM LIKE '%"+pmt.getXmbh()+"%' ";
		}
		if(!("".equals(pmt.getXm()))){
			sbf+="AND A.XM LIKE '%"+pmt.getXm()+"%' ";
		}
		if(!("".equals(pmt.getGjd()))){
			sbf+="AND A.Gjd LIKE '%"+pmt.getGjd()+"%' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-��λ�鼯������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getDwcxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DwdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(DwdaBean)obj;
		sbf=("SELECT A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH,B.DWZH AS DWZH,B.DW AS DW," +
				"B.DWDZ AS DWDZ,B.GJD AS GJD,B.CBWD AS CBWD,B.GJZGY AS GJZGY,to_char(B.KHRQ,'yyyy-mm-dd') AS KHRQ," +
				"to_char(B.XHRQ,'yyyy-mm-dd') AS XHRQ,to_char(B.FCRQ,'yyyy-mm-dd') AS FCRQ,A.BMJB AS BMJB,A.CFWZ AS CFWZ,"+
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB,A.ID AS ID"+
				" FROM EF_DAJBXX A,EF_DWDA B,EF_DALX C WHERE (A.F_STATE='�鵵' and A.F_TYPE='1' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getDwdz()))){
			sbf+="AND B.DWDZ LIKE '%"+pmt.getDwdz()+"%' ";
		}
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getDwzh()))){
			sbf+="AND B.DWZH LIKE '%"+pmt.getDwzh()+"%' ";
		}
		if(!("".equals(pmt.getDw()))){
			sbf+="AND B.DW LIKE '%"+pmt.getDw()+"%' ";
		}
		/**
		 *  �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		if(!("".equals(pmt.getGjd()))){
			sbf+="AND B.GJD LIKE '%"+pmt.getGjd()+"%' ";
		}
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-���µ�����ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getRscxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		RsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(RsdaBean)obj;
		sbf=("SELECT A.ID AS ID,B.ENAME AS ENAME,A.DAH AS DAH,A.YGDH AS YGDH," +
				"A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH,A.YWLSH AS YWLSH,A.BMJB AS BMJB," +
				"B.DJR AS DJR,A.BCNX AS BCNX,B.DJRQ AS DJRQ,C.F_TYPE AS F_TYPE" +
				" FROM EF_DAJBXX A ,EF_RSDA B ,EF_DALX C WHERE (A.F_STATE='�鵵' AND A.F_TYPE='12' AND A.YGDH=B.YGDH AND A.F_TYPE=C.ID)");
		
		if(!("".equals(pmt.getDjr()))){
			sbf+="AND B.DJR LIKE '%"+pmt.getDjr()+"%' ";
		}
		if(!("".equals(pmt.getName()))){
			sbf+="AND B.ENAME LIKE '%"+pmt.getName()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ���ٹ���-�����ٹ���
	 * @author zwh 2011��12��15��
	 *
	 */
	public List<Row> openDA_GetDxhglData(Object obj, int pagesize, int pagenum) throws CallDbException {
		XhjlBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (XhjlBean) obj;

		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ," +
				"A.JSR AS JSR,to_char(A.JSRQ,'yyyy-mm-dd') AS JSRQ,A.CLFS AS CLFS,A.BCNX AS BCNX," +
				"A.BMJB AS BMJB,A.CFWZ AS CFWZ,A.GH AS GH,A.HH AS HH,B.F_TYPE AS F_TYPE," +
				"A.F_STATE AS F_STATE "+ 
				"FROM EF_DAJBXX A,EF_DALX B "+ 
				"WHERE (A.F_STATE='ע��'  and (months_between(sysdate,A.zxrq))>A.zxbcnx*12 AND A.F_TYPE=B.ID)");

		if(!("".equals(pmt.getDah()))){
			sbf+=" AND A.DAH like '%"+pmt.getDah()+"%'";
		}
		if(!("".equals(pmt.getTjrq_begin()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') >= '"+pmt.getTjrq_begin()+"'";
		}
		if(!("".equals(pmt.getTjrq_end()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') <= '"+pmt.getTjrq_end()+"'";
		}
		if(!("".equals(pmt.getJsrq_begin()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') >= '"+pmt.getJsrq_begin()+"'";
		}
		if(!("".equals(pmt.getJsrq_end()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') <= '"+pmt.getJsrq_end()+"'";
		}
		if(!("".equals(pmt.getType()))){
			sbf+=" AND B.F_TYPE like '%"+pmt.getType()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by TJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ���ٹ���-���ٹ���
	 * @author zwh 2011��12��15��
	 *
	 */
	public List<Row> openDA_GetXhglData(Object obj, int pagesize, int pagenum) throws CallDbException {
		XhjlBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (XhjlBean) obj;

		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.GJD AS GJD,A.XHR AS XHR,to_char(A.XHRQ,'yyyy-mm-dd') AS XHRQ," +
				"A.XHJDR AS XHJDR,A.DAZT AS DAZT,A.XHYY AS XHYY,B.F_TYPE AS F_TYPE "+ 
				"FROM EF_XHJL A,EF_DALX B "+
				"WHERE (A.DALX=B.ID)");

		if(!("".equals(pmt.getDah()))){
			sbf+=" AND A.DAH like '%"+pmt.getDah()+"%'";
		}
		if(!("".equals(pmt.getXhrq_begin()))){
			sbf+=" AND to_char(A.XHRQ,'yyyy-mm-dd') >= '"+pmt.getXhrq_begin()+"'";
		}
		if(!("".equals(pmt.getXhrq_end()))){
			sbf+=" AND to_char(A.XHRQ,'yyyy-mm-dd') <= '"+pmt.getXhrq_end()+"'";
		}
		if(!("".equals(pmt.getXHJDR()))){
			sbf+=" AND A.XHJDR like '%"+pmt.getXHJDR()+"%'";
		}
		if(!("".equals(pmt.getDAZT()))){
			sbf+=" AND A.DAZT like '%"+pmt.getDAZT()+"%'";
		}
		if(!("".equals(pmt.getType()))){
			sbf+=" AND B.F_TYPE like '%"+pmt.getType()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by XHRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ��������-����������
	 * @author zwh 2011��12��15��
	 *
	 */
	public List<Row> openDA_GetDjdglData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DjdglMXBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (DjdglMXBean) obj;

		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ," +
				"A.JSR AS JSR,to_char(A.JSRQ,'yyyy-mm-dd') AS JSRQ,A.ZXR AS ZXR,A.ZXRQ AS ZXRQ," +
				"A.ZXYY AS ZXYY,A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS,A.CFWZ AS CFWZ," +
				"A.GH AS GH,A.HH AS HH,A.SXH AS SXH,B.F_TYPE AS F_TYPE "+ 
				"FROM EF_DAJBXX A,EF_DALX B "+
				"WHERE ((A.F_STATE='�鵵') and (months_between(sysdate,A.tjrq))>A.bcnx*12 AND A.F_TYPE=B.ID) ");

		if(!("".equals(pmt.getDah()))){
			sbf+=" AND A.DAH like '%"+pmt.getDah()+"%'";
		}
		if(!("".equals(pmt.getTjr()))){
			sbf+=" AND A.TJR like '%"+pmt.getTjr()+"%'";
		}
		if(!("".equals(pmt.getTjrq_begin()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') >= '"+pmt.getTjrq_begin()+"'";
		}
		if(!("".equals(pmt.getTjrq_end()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') <= '"+pmt.getTjrq_end()+"'";
		}
		if(!("".equals(pmt.getJsrq_begin()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') >= '"+pmt.getJsrq_begin()+"'";
		}
		if(!("".equals(pmt.getJsrq_end()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') <= '"+pmt.getJsrq_end()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by ZXRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ��������-��������
	 * @author zwh 2011��12��15��
	 *
	 */
	public List<Row> openDA_GetJdglData(Object obj, int pagesize, int pagenum) throws CallDbException {
		JdglMXBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (JdglMXBean) obj;

		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.PH AS PH,A.HH AS HH, A.SXH AS SXH,A.BCNX AS BCNX," +
				"A.BMJB AS BMJB,A.CLFS AS CLFS,B.F_TYPE AS F_TYPE,A.ZXR AS ZXR," +
				"to_char(A.ZXRQ,'yyyy-mm-dd') AS ZXRQ,A.ZXYY AS ZXYY,A.CFWZ AS CFWZ "+
				"FROM EF_DAJBXX A,EF_DALX B "+
				"WHERE (A.F_STATE='ע��' AND A.F_TYPE=B.ID) ");

		if(!("".equals(pmt.getDah()))){
			sbf+=" AND A.DAH like '%"+pmt.getDah()+"%'";
		}
		if(!("".equals(pmt.getType()))){
			sbf+=" AND B.F_TYPE like '%"+pmt.getType()+"%'";
		}
		if(!("".equals(pmt.getZxr()))){
			sbf+=" AND A.ZXR like '%"+pmt.getZxr()+"%'";
		}
		if(!("".equals(pmt.getZxrq_begin()))){
			sbf+=" AND to_char(A.ZXRQ,'yyyy-mm-dd') >= '"+pmt.getZxrq_begin()+"'";
		}
		if(!("".equals(pmt.getZxrq_end()))){
			sbf+=" AND to_char(A.ZXRQ,'yyyy-mm-dd') <= '"+pmt.getZxrq_end()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by ZXRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}

	
	/**
	 * ������˹鵵-���鵵���鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetWsDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		WsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (WsdaBean) obj;

		sbf=("SELECT A.ID AS ID,B.TM AS TM,A.TJR AS TJR,A.DASH AS DASH,A.GROW AS GROW,A.GH AS GH,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ," +
				"B.ZRZ AS ZRZ,A.HH AS HH,A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS,B.YJML AS YJML," +
				"B.EJML AS EJML,B.SJML AS SJML,C.F_TYPE AS F_TYPE,A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH "+ 
				"FROM "+ 
				"EF_DAJBXX A ,EF_WSDA B,EF_DALX C "+
				"WHERE "+
				"(A.F_STATE='Ԥ�鵵' AND A.F_TYPE='6' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		if(!("".equals(pmt.getTjrq_begin()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') >= '"+pmt.getTjrq_begin()+"'";
		}
		if(!("".equals(pmt.getTjrq_end()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') <= '"+pmt.getTjrq_end()+"'";
		}
		if(!("".equals(pmt.getZrz()))){
			sbf+=" AND B.ZRZ like '%"+pmt.getZrz()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by TJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	

	/**
	 * ������˹鵵-�Ƽ������鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetKjDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (ZldaBean) obj;

		sbf=("SELECT B.TM AS TM,B.ZTLB AS ZTLB,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.BCNX AS BCNX,A.BMJB AS BMJB," +
				"A.ID AS ID,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH,A.F_TYPE AS F_TYPEB "+
				"FROM "+
				"EF_DAJBXX A ,EF_ZLDA B,EF_DALX C "+
				"WHERE "+ 
				"(A.F_STATE='Ԥ�鵵' AND A.F_TYPE='7' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE) ");

		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getBegin()+"'";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getEnd()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������˹鵵-��Ƭ�����鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetZpDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (TsdaBean) obj;

		sbf=("SELECT B.TM AS TM,B.ZTLB AS ZTLB,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"B.ZRZ AS ZRZ,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.BCNX AS BCNX," +
				"A.BMJB AS BMJB,A.ID AS ID,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH,A.F_TYPE AS F_TYPEB "+
				"FROM "+ 
				"EF_DAJBXX A ,EF_TSDA B,EF_DALX C "+
				"WHERE "+ 
				"(A.F_STATE='Ԥ�鵵' AND A.F_TYPE='8' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE) ");

		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getBegin()+"'";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getEnd()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������˹鵵-���µ����鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetRsDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		RsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (RsdaBean) obj;

		sbf=("SELECT B.ENAME AS ENAME,A.ID AS ID,A.BMJB AS BMJB,A.BCNX AS BCNX,A.TJR AS TJR," +
				"B.PHONE AS PHONE,B.ADDRESS AS ADDRESS," +
				"B.JG AS JG,to_char(B.DJRQ,'yyyy-MM-dd') AS DJRQ,B.DJR AS DJR,A.F_TYPE AS F_TYPEB," +
				"A.YGDH AS YGDH,C.F_TYPE AS F_TYPE,to_char(A.TJRQ,'yyyy-MM-dd') AS TJRQ "+
				"FROM "+
				"EF_DAJBXX A ,EF_RSDA B,EF_DALX C "+
				"WHERE "+
				"(A.F_STATE='Ԥ�鵵' AND A.F_TYPE='12' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getEname()))){
			sbf+=" AND B.ENAME like '%"+pmt.getEname()+"%'";
		}
		if(!("".equals(pmt.getBmjb()))){
			sbf+=" AND B.BMJB like '%"+pmt.getBmjb()+"%'";
		}
		if(!("".equals(pmt.getDjrq_begin()))){
			sbf+=" AND to_char(B.DJRQ,'yyyy-mm-dd') >= '"+pmt.getDjrq_begin()+"'";
		}
		if(!("".equals(pmt.getDjrq_end()))){
			sbf+=" AND to_char(B.DJRQ,'yyyy-mm-dd') <= '"+pmt.getDjrq_end()+"'";
		}
		if(!("".equals(pmt.getDjr()))){
			sbf+=" AND B.DJR like '%"+pmt.getDjr()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by DJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	

	/**
	 * ������˹鵵-¼�񵵰��鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetLxDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (TsdaBean) obj;

		sbf=("SELECT B.TM AS TM,B.ZTLB AS ZTLB,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"B.ZRZ AS ZRZ,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.BCNX AS BCNX," +
				"A.BMJB AS BMJB,A.ID AS ID,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH,A.F_TYPE AS F_TYPEB "+
				"FROM "+
				"EF_DAJBXX A ,EF_TSDA B,EF_DALX C "+ 
				"WHERE "+
				"(A.F_STATE='Ԥ�鵵' AND A.F_TYPE='9' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
		if(!("".equals(pmt.getCjrq_begin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getCjrq_begin()+"'";
		}
		if(!("".equals(pmt.getCjrq_end()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getCjrq_end()+"'";
		}
		
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	

	/**
	 * ������˹鵵-ʵ�ﵵ���鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetSwDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (ZldaBean) obj;

		sbf=("SELECT B.TM AS TM,B.ZTLB AS ZTLB,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.BCNX AS BCNX,A.BMJB AS BMJB," +
				"A.ID AS ID,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH,A.F_TYPE AS F_TYPEB "+
				"FROM "+ 
				"EF_DAJBXX A ,EF_ZLDA B,EF_DALX C "+
				"WHERE "+ 
				"(A.F_STATE='Ԥ�鵵' AND A.F_TYPE='10' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
		if(!("".equals(pmt.getCjrq_begin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getCjrq_begin()+"'";
		}
		if(!("".equals(pmt.getCjrq_end()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getCjrq_end()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������˹鵵-֪ʶ�����鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetZsDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (TsdaBean) obj;

		sbf=("SELECT B.TM AS TM,B.ZTLB AS ZTLB,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"B.ZRZ AS ZRZ,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.BCNX AS BCNX," +
				"A.BMJB AS BMJB,A.ID AS ID,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH," +
				"A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH "+
				"FROM "+ 
				"EF_DAJBXX A ,EF_TSDA B,EF_DALX C "+
				"WHERE "+ 
				"(A.F_STATE='Ԥ�鵵' AND A.F_TYPE='11' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
		if(!("".equals(pmt.getCjrq_begin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getCjrq_begin()+"'";
		}
		if(!("".equals(pmt.getCjrq_end()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getCjrq_end()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	

	/**
	 * ������˹鵵-���ƻ��˵����鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetFzDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (ZldaBean) obj;
		
		sbf=("SELECT B.TM AS TM,B.ZTLB AS ZTLB,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.BCNX AS BCNX,A.BMJB AS BMJB," +
				"A.ID AS ID,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH,A.F_TYPE AS F_TYPEB "+
				"FROM "+
				"EF_DAJBXX A ,EF_ZLDA B,EF_DALX C "+
				"WHERE "+
				"(A.F_STATE='Ԥ�鵵' AND A.F_TYPE='13' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
		if(!("".equals(pmt.getCjrq_begin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getCjrq_begin()+"'";
		}
		if(!("".equals(pmt.getCjrq_end()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getCjrq_end()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}

	/**
	 * ������˹鵵-�ۺϹ鵵����
	 * @author zwh 2011��12��20��
	 *
	 */
	public List<Row> openDA_GetZhGdGlData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (ZldaBean) obj;

		sbf=("SELECT A.ID AS ID,A.GH AS GH,A.HH AS HH,A.BMJB AS BMJB,A.BCNX AS BCNX,A.CLFS AS CLFS," +
				"A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.JSR AS JSR,A.JSRQ AS JSRQ," +
				"A.YGDH AS YGDH,B.F_TYPE AS F_TYPE,A.F_TYPE AS F_TYPEB "+
				"FROM "+
				"EF_DAJBXX A,EF_DALX B "+ 
				"WHERE "+
				"(A.F_STATE='Ԥ�鵵' and A.F_TYPE=B.ID)");

		if(!("".equals(pmt.getTjr()))){
			sbf+=" AND A.TJR like '%"+pmt.getTjr()+"%'";
		}
		if(!("".equals(pmt.getTjrq_begin()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') >= '"+pmt.getTjrq_begin()+"'";
		}
		if(!("".equals(pmt.getTjrq_end()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') <= '"+pmt.getTjrq_end()+"'";
		}
		if(!("".equals(pmt.getJsr()))){
			sbf+=" AND A.JSR like '%"+pmt.getJsr()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by TJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}

	/**
	 * ������˹鵵-���˴���鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetGrDkGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DkdaBean pmt = null;
		SqlParameterExt spx = new SqlParameterExt();
		String sbf="";
		pmt = (DkdaBean) obj;

		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,B.ZGXM AS ZGXM,B.XB AS XB,B.ZJHM AS ZJHM,B.DW AS DW," +
				"A.GH AS GH,A.HH AS HH,A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS,B.CBWD AS CBWD," +
				"B.GJD AS GJD,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,B.FKYH AS FKYH," +
				"to_char(B.FKRQ,'yyyy-mm-dd') AS FKRQ,B.DKNX AS DKNX,B.GFLX AS GFLX,B.GFXXDZ AS GFXXDZ," +
				"C.F_TYPE AS F_TYPE,A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH "+
				"FROM "+ 
				"EF_DAJBXX A,EF_DKDA B,EF_DALX C "+ 
				"WHERE (A.F_STATE='Ԥ�鵵' and A.F_TYPE='4' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getZgxm()))){
			sbf+=" AND B.ZGXM like '%"+pmt.getZgxm()+"%'";
		}
		if(!("".equals(pmt.getZjhm()))){
			sbf+=" AND B.ZJHM like '%"+pmt.getZjhm()+"%'";
		}
		if(!("".equals(pmt.getYwlsh()))){
			sbf+=" AND A.YWLSH like '%"+pmt.getYwlsh()+"%'";
		}
		if(!("".equals(pmt.getTjr()))){
			sbf+=" AND A.TJR like '%"+pmt.getTjr()+"%'";
		}
		if(!("".equals(pmt.getTjrq_begin()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') >= '"+pmt.getTjrq_begin()+"'";
		}
		if(!("".equals(pmt.getTjrq_end()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') <= '"+pmt.getTjrq_end()+"'";
		}
		if(!("".equals(pmt.getGflx()))){
			sbf+=" AND B.GFLX like '%"+pmt.getGflx()+"%'";
		}
		/**
		 * �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by TJRQ DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}

	/**
	 * ������˹鵵-��λ�鼯�鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetDwGjGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DwdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (DwdaBean) obj;

		sbf=("SELECT B.DW AS DW,B.DWDZ AS DWDZ,A.GH AS GH,A.HH AS HH,A.BCNX AS BCNX,A.BMJB AS BMJB," +
				"A.CLFS AS CLFS,B.CBWD AS CBWD,B.GJD AS GJD,A.ID AS ID," +
				"to_char(B.KHRQ,'yyyy-mm-dd') AS KHRQ,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH," +
				"A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH "+ 
				"FROM "+ 
				"EF_DAJBXX A,EF_DWDA B,EF_DALX C "+ 
				"WHERE "+
				"(A.F_STATE='Ԥ�鵵' and A.F_TYPE='1' and A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getDw()))){
			sbf+=" AND B.DW like '%"+pmt.getDw()+"%'";
		}
		if(!("".equals(pmt.getCbwd()))){
			sbf+=" AND B.CBWD like '%"+pmt.getCbwd()+"%'";
		}
		if(!("".equals(pmt.getGjd()))){
			sbf+=" AND B.GJD like '%"+pmt.getGjd()+"%'";
		}
		if(!("".equals(pmt.getKhrq_begin()))){
			sbf+=" AND to_char(B.KHRQ,'yyyy-mm-dd') >= '"+pmt.getKhrq_begin()+"'";
		}
		if(!("".equals(pmt.getKhrq_end()))){
			sbf+=" AND to_char(B.KHRQ,'yyyy-mm-dd') <= '"+pmt.getKhrq_end()+"'";
		}
		/**
		 *  �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by KHRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	
	/**
	 * ������˹鵵-���˹鼯�鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetGrGjGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		GjdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (GjdaBean) obj;

		sbf=("SELECT A.ID AS ID,B.ZGXM AS ZGXM,B.XB AS XB,B.ZJHM AS ZJHM,A.GH AS GH,A.HH AS HH," +
				"A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS,B.CBWD AS CBWD,B.GJD AS GJD,B.DW AS DW," +
				"C.F_TYPE AS F_TYPE,A.YGDH AS YGDH,A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH"+
				" FROM "+ 
				"EF_GJDA B,EF_DAJBXX A,EF_DALX C "+
				"WHERE "+ 
				"(A.F_STATE='Ԥ�鵵' and A.F_TYPE='2' and A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getZgxm()))){
			sbf+=" AND B.ZGXM like '%"+pmt.getZgxm()+"%'";
		}
		if(!("".equals(pmt.getZjhm()))){
			sbf+=" AND B.ZJHM like '%"+pmt.getZjhm()+"%'";
		}
		if(!("".equals(pmt.getCbwd()))){
			sbf+=" AND B.CBWD like '%"+pmt.getCbwd()+"%'";
		}
		if(!("".equals(pmt.getGjd()))){
			sbf+=" AND B.GJD like '%"+pmt.getGjd()+"%'";
		}
		if(!("".equals(pmt.getDw()))){
			sbf+=" AND B.DW like '%"+pmt.getDw()+"%'";
		}

		/**
		 *  �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by GJD DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}

	/**
	 * ������˹鵵-¥�̵����鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetLpDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		LpdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (LpdaBean) obj;

		sbf=("SELECT A.GH AS GH,A.HH AS HH,A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS,B.XM AS XM,"+
				"B.CBWD AS CBWD,B.GJD AS GJD,A.ID AS ID,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH,"+
				"A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH "+
				"FROM "+
				"EF_DAJBXX A,EF_LPDA B,EF_DALX C "+ 
				"WHERE (A.F_STATE='Ԥ�鵵' and A.F_TYPE='5' and A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getBmjb()))){
			sbf+=" AND A.BMJB like '%"+pmt.getBmjb()+"%'";
		}
		if(!("".equals(pmt.getXm()))){
			sbf+=" AND B.XM like '%"+pmt.getXm()+"%'";
		}
		if(!("".equals(pmt.getCbwd()))){
			sbf+=" AND B.CBWD like '%"+pmt.getCbwd()+"%'";
		}
		if(!("".equals(pmt.getGjd()))){
			sbf+=" AND B.GJD like '%"+pmt.getGjd()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by GJD DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������˹鵵-֧ȡ�鵵
	 * @author zwh 2011��12��20��
	 */
	public List<Row> openDA_GetZqDaGdData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZqdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (ZqdaBean) obj;

		sbf=("SELECT A.ID AS ID,B.ZGXM AS ZGXM,B.ZGZH AS ZGZH,B.ZJHM AS ZJHM,B.DW AS DW,B.DWZH AS DWZH," +
				"A.GH AS GH,A.HH AS HH,A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS,B.CBWD AS CBWD," +
				"B.GJD AS GJD,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ," +
				"to_char(B.ZQRQ,'yyyy-mm-dd') AS ZQRQ,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH," +
				"A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH "+ 
				"FROM "+ 
				"EF_DAJBXX A ,EF_ZQDA B,EF_DALX C "+
				"WHERE "+
				"(A.F_STATE='Ԥ�鵵' AND A.F_TYPE='3' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getZgxm()))){
			sbf+=" AND B.ZGXM like '%"+pmt.getZgxm()+"%'";
		}
		if(!("".equals(pmt.getZgzh()))){
			sbf+=" AND B.ZGZH like '%"+pmt.getZgzh()+"%'";
		}
		if(!("".equals(pmt.getDw()))){
			sbf+=" AND B.DW like '%"+pmt.getDw()+"%'";
		}
		if(!("".equals(pmt.getCbwd()))){
			sbf+=" AND B.CBWD like '%"+pmt.getCbwd()+"%'";
		}
		if(!("".equals(pmt.getTjrq_begin()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') >= '"+pmt.getTjrq_begin()+"'";
		}
		if(!("".equals(pmt.getTjrq_end()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') <= '"+pmt.getTjrq_end()+"'";
		}
		if(!("".equals(pmt.getZqrq_begin()))){
			sbf+=" AND to_char(B.ZQRQ,'yyyy-mm-dd') >= '"+pmt.getZqrq_begin()+"'";
		}
		if(!("".equals(pmt.getZqrq_end()))){
			sbf+=" AND to_char(B.ZQRQ,'yyyy-mm-dd') <= '"+pmt.getZqrq_end()+"'";
		}
		/**
		 *  �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by ZQRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ����ɨ��¼��-¼�񵵰�
	 * @author zwh 2011��12��17��
	 *
	 */
	public List<Row> openDA_GetLxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		LxdaMXBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (LxdaMXBean) obj;

		sbf=("SELECT A.ID AS ID,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ,B.TM AS TM "+ 
				"FROM EF_DAJBXX A ,EF_TSDA B,EF_DALX C "+ 
				"WHERE (A.F_STATE='��״̬' AND A.CLZT='����'  AND A.F_TYPE='9' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
	
		if(!("".equals(pmt.getCjrq_begin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getCjrq_begin()+"'";
		}
	
		if(!("".equals(pmt.getCjrq_end()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getCjrq_end()+"'";
		}

		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		//System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ����ɨ��¼��-ʵ�ﵵ��
	 * @author zwh 2011��12��17��
	 *
	 */
	public List<Row> openDA_GetSwdalrData(Object obj, int pagesize, int pagenum) throws CallDbException {
		SwdalrMXBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (SwdalrMXBean) obj;

		sbf=("SELECT A.ID AS ID,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ,B.TM AS TM," +
				"B.ZTLB AS ZTLB,B.ZT AS ZT,B.YGDH AS YGDH,C.F_TYPE AS F_TYPE,B.THYY AS THYY," +
				"A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH "+ 
				"FROM EF_DAJBXX A ,EF_ZLDA B,EF_DALX C "+ 
				"WHERE (A.F_STATE='��״̬'  AND A.CLZT='����'  AND A.F_TYPE='10' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
		if(!("".equals(pmt.getCjrq_begin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getCjrq_begin()+"'";
		}
		if(!("".equals(pmt.getCjrq_end()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getCjrq_end()+"'";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		//System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ����ɨ��¼��-֪ʶ����
	 * @author zwh 2011��12��17��
	 *
	 */
	public List<Row> openDA_GetZsdalrData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZsdalrBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (ZsdalrBean) obj;

		sbf=("SELECT A.ID AS ID,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ,B.TM AS TM "+ 
				"FROM EF_DAJBXX A ,EF_TSDA B,EF_DALX C "+
				"WHERE (A.F_STATE='��״̬'  AND A.CLZT='����'  AND A.F_TYPE='11' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
	
		if(!("".equals(pmt.getCjrq_begin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getCjrq_begin()+"'";
		}

		if(!("".equals(pmt.getCjrq_end()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getCjrq_end()+"'";
		}
	
		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		//System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ����ɨ��¼��-���µ���
	 * @author zwh 2011��12��17��
	 *
	 */
	public List<Row> openDA_GetRslrdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		RsdalrBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (RsdalrBean) obj;

		sbf=("SELECT A.ID AS ID,B.ENAME AS ENAME,B.YGDH AS YGDH,B.BH AS BH,B.SEX AS SEX,B.MZ AS MZ," +
				"B.SFZH AS SFZH,to_char(B.CSRQ,'yyyy-mm-dd') AS CSRQ,B.AGE AS AGE,B.HYZK AS HYZK," +
				"to_char(B.CJGZSJ,'yyyy-mm-dd') AS CJGZSJ,B.GL AS GL,to_char(B.RDTSJ,'yyyy-mm-dd') AS RDTSJ," +
				"B.DH AS DH,B.PHONE AS PHONE,B.EMAIL AS EMAIL,B.QQ AS QQ,B.XL AS XL,B.SCHOOL AS SCHOOL," +
				"B.ZY AS ZY,B.ADDRESS AS ADDRESS,B.HKSZD AS HKSZD,B.JG AS JG," +
				"to_char(B.DJRQ,'yyyy-mm-dd') AS DJRQ,B.DJR AS DJR,C.F_TYPE AS F_TYPE," +
				"A.YWLSH AS YWLSH,A.F_TYPE AS F_TYPEB "+ 
				"FROM EF_DAJBXX A ,EF_RSDA B,EF_DALX C "+
				"WHERE (A.F_STATE='��״̬' AND A.CLZT='����'  AND A.F_TYPE='12' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");

		if(!("".equals(pmt.getEname()))){
			sbf+=" AND B.ENAME like '%"+pmt.getEname()+"%'";
		}
		if(!("".equals(pmt.getDjrq_begin()))){
			sbf+=" AND to_char(B.DJRQ,'yyyy-mm-dd') >= '"+pmt.getDjrq_begin()+"'";
		}
		if(!("".equals(pmt.getDjrq_end()))){
			sbf+=" AND to_char(B.DJRQ,'yyyy-mm-dd') <= '"+pmt.getDjrq_end()+"'";
		}
		if(!("".equals(pmt.getDjr()))){
			sbf+=" AND B.DJR like '%"+pmt.getDjr()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by DJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ����ɨ��¼��-���ƻ��˵���
	 * @author zwh 2011��12��17��
	 *
	 */
	public List<Row> openDA_GetFzjhdaLrData(Object obj, int pagesize, int pagenum) throws CallDbException {
		FzjhdaLrBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (FzjhdaLrBean) obj;

		sbf=("SELECT A.ID AS ID,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ,B.TM AS TM," +
				"B.ZTLB AS ZTLB,B.ZT AS ZT,B.YGDH AS YGDH,C.F_TYPE AS F_TYPE,B.THYY AS THYY," +
				"A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH "+ 
				"FROM EF_DAJBXX A ,EF_ZLDA B,EF_DALX C "+
				"WHERE (A.F_STATE='��״̬'  AND A.CLZT='����'   AND A.F_TYPE='13' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE) ");

		if(!("".equals(pmt.getCjr()))){
			sbf+=" AND B.CJR like '%"+pmt.getCjr()+"%'";
		}
		if(!("".equals(pmt.getCjrq_begin()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getCjrq_begin()+"'";
		}
		if(!("".equals(pmt.getCjrq_end()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getCjrq_end()+"'";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM like '%"+pmt.getTm()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by CJRQ DESC";
		System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ����ɨ��¼��-���鵵����ѯ
	 * @author zwh 2011-12-13
	 */
	public List<Row> openDA_GetWsdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		WsdaMXBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (WsdaMXBean) obj;

		sbf=("SELECT A.ID AS ID,B.ZRZ AS ZRZ,B.ZRBM AS ZRBM,B.CJR AS CJR," +
				"to_char(B.CJSJ,'yyyy-mm-dd') AS CJSJ,B.YJML AS YJML,B.EJML AS EJML,B.SJML AS SJML," +
				"B.TM AS TM,B.YGDH AS YGDH,C.F_TYPE AS F_TYPE,A.YWLSH AS YWLSH,A.F_TYPE AS F_TYPEB " +
				"FROM EF_DAJBXX A ,EF_WSDA B,EF_DALX C " +
				"WHERE (A.F_STATE='��״̬' AND A.CLZT='����' AND A.F_TYPE='6' AND A.YGDH=B.YGDH AND C.ID=A.F_TYPE) ");
		
		if(!("".equals(pmt.getZrz()))){
			sbf+="AND B.ZRZ LIKE '%"+pmt.getZrz()+"%' ";
		}
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJSJ,'yyyy-mm-dd') >= '"+pmt.getBegin()+"'";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+=" AND to_char(B.CJSJ,'yyyy-mm-dd') <= '"+pmt.getEnd()+"'";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		
		/**
		 *  �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		
		sbf+="order by CJSJ DESC";
		//System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ����ɨ��¼��-�Ƽ�����¼��
	 * @author zwh 2011-12-15
	 *
	 */
	public List<Row> openDA_GetKjData(Object obj, int pagesize, int pagenum) throws CallDbException {
		KjMXBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (KjMXBean) obj;

		sbf=("SELECT A.ID AS ID,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ,B.TM AS TM," +
				"A.F_TYPE AS F_TYPEB,A.YWLSH AS YWLSH,C.F_TYPE AS F_TYPE,A.YGDH AS YGDH " +
				"FROM EF_DAJBXX A ,EF_ZLDA B,EF_DALX C " +
				"WHERE " +
				"(A.F_STATE='��״̬' AND A.CLZT='����' AND A.F_TYPE='7' AND A.YGDH=B.YGDH AND C.ID=A.F_TYPE) ");
			
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getBegin()+"'";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getEnd()+"'";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by CJRQ DESC";
		//System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ����ɨ��¼��-��Ƭ����¼��
	 * @author zwh 2011-12-15
	 *
	 */
	public List<Row> openDA_GetZpData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZpMXBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (ZpMXBean) obj;

		sbf=("SELECT A.ID,B.CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ,B.TM " +
				"FROM EF_DAJBXX A ,EF_TSDA B,EF_DALX C " +
				"WHERE (A.F_STATE='��״̬'  AND A.CLZT='����'  AND A.F_TYPE='8' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");
			
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-mm-dd') >= '"+pmt.getBegin()+"'";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+=" AND to_char(B.CJRQ,'yyyy-mm-dd') <= '"+pmt.getEnd()+"'";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+=" AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by CJRQ DESC";
		//System.out.println("==================sbf=="+sbf);
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ϵͳ����-����Ŀ¼�嵥
	 * @author jcl 2012-1-6
	 *
	 */
	public List<Row> openDA_GetJnmlqdData(Object obj,int pagesize, int pagenum) throws CallDbException {
		String sql = "SELECT ID,CLBM,CL FROM BM_JNMLSZ ORDER BY CLBM ASC";
		try {
			return bo.queryToList(sql, pagesize, pagenum);
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ϵͳ����-���⵵��������
	 * @author  jcl 2012-1-6
	 *
	 */
	public List<Row> openDA_GetXndagszData(Object obj,int pagesize, int pagenum) throws CallDbException {
		String sql = "SELECT ID,DASH,GH,GROW,HH FROM EF_DAGSZ";
		try {
			return bo.queryToList(sql, pagesize, pagenum);
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ϵͳ����-�ļ��б�
	 * @author  jcl 2012-1-6
	 *
	 */
	public List<Row> openDA_GetWjlbData(Object obj,int pagesize, int pagenum) throws CallDbException {
		String sql = "SELECT ID ,FIRST ,SECOND ,RANK  FROM EF_WSLB  WHERE (RANK='1' AND P_ID is null)";
		try {
			return bo.queryToList(sql, pagesize, pagenum);
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ϵͳ����-��������
	 * @author  jcl 2012-1-6
	 *
	 */
	public List<Row> openDA_GetDalxData(Object obj,int pagesize, int pagenum) throws CallDbException {
		String sql = "SELECT ID,F_TYPE,LXBZ FROM EF_DALX";
		try {
			return bo.queryToList(sql, pagesize, pagenum);
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������Ϣ���->�������
	 * @author zy 2012-1-10
	 */
	public List<Row> openDA_GetDaxxbgData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DajbxxBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (DajbxxBean) obj;


		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.GH AS GH,A.HH AS HH,A.BCNX AS BCNX,A.BMJB AS BMJB, "+
				"A.CLFS AS CLFS,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.JSR AS JSR, "+
				"to_char(A.JSRQ,'yyyy-mm-dd') AS JSRQ,A.CFWZ AS CFWZ,B.F_TYPE AS F_TYPE, "+
				"A.YGDH AS YGDH,A.YWLSH AS YWLSH,A.F_TYPE AS F_TYPEB FROM EF_DAJBXX A,EF_DALX B "+ 
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE=B.ID)");

		if(!("".equals(pmt.getTjr()))){
			sbf+=" AND A.TJR like '%"+pmt.getTjr()+"%'";
		}
		if(!("".equals(pmt.getJsr()))){
			sbf+=" AND A.JSR like '%"+pmt.getJsr()+"%'";
		}
		if(!("".equals(pmt.getJsrqBegin()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') >= '"+pmt.getJsrqBegin()+"'";
		}
		if(!("".equals(pmt.getJsrqEnd()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') <= '"+pmt.getJsrqEnd()+"'";
		}
		if(!("".equals(pmt.getTjrqBegin()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') >= '"+pmt.getTjrqBegin()+"'";
		}
		if(!("".equals(pmt.getTjrqEnd()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') <= '"+pmt.getTjrqEnd()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by JSRQ DESC";
		
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������Ϣ���->׷�ӹ���
	 * @author zy 2012-1-10
	 */
	public List<Row> openDA_GetZjglData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DajbxxBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (DajbxxBean) obj;


		sbf=("SELECT A.DAH AS DAH,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.JSR AS JSR," +
				"to_char(A.JSRQ,'yyyy-mm-dd') AS JSRQ,A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS," +
				"A.ZXRQ AS ZXRQ,A.CFWZ AS CFWZ,A.GH AS GH,A.HH AS HH,A.SXH AS SXH,B.F_TYPE AS F_TYPE," +
				"A.ID AS ID,A.YGDH AS YGDH,A.YWLSH AS YWLSH,A.F_TYPE AS F_TYPEB " +
				"FROM EF_DAJBXX A,EF_DALX B " +
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE=B.ID)");

		if(!("".equals(pmt.getTjr()))){
			sbf+=" AND A.TJR like '%"+pmt.getTjr()+"%'";
		}
		if(!("".equals(pmt.getJsr()))){
			sbf+=" AND A.JSR like '%"+pmt.getJsr()+"%'";
		}
		if(!("".equals(pmt.getJsrqBegin()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') >= '"+pmt.getJsrqBegin()+"'";
		}
		if(!("".equals(pmt.getJsrqEnd()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') <= '"+pmt.getJsrqEnd()+"'";
		}
		if(!("".equals(pmt.getTjrqBegin()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') >= '"+pmt.getTjrqBegin()+"'";
		}
		if(!("".equals(pmt.getTjrqEnd()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') <= '"+pmt.getTjrqEnd()+"'";
		}
		if(!("".equals(pmt.getF_type()))){
			sbf+=" AND B.F_TYPE like '%"+pmt.getF_type()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by JSRQ DESC";
	
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ����ɨ��¼��-��������
	 * @author zy 2012-1-10
	 */
	public List<Row> openDA_GetCcglData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DajbxxBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (DajbxxBean) obj;


		sbf=("SELECT A.DAH AS DAH,A.TJR AS TJR,to_char(A.TJRQ,'yyyy-mm-dd') AS TJRQ,A.JSR AS JSR," +
				"to_char(A.JSRQ,'yyyy-mm-dd') AS JSRQ,A.BCNX AS BCNX,A.BMJB AS BMJB,A.CLFS AS CLFS," +
				"A.ZXRQ AS ZXRQ,A.CFWZ AS CFWZ,A.GH AS GH,A.HH AS HH,A.SXH AS SXH,B.F_TYPE AS F_TYPE," +
				"A.ID AS ID,A.YGDH AS YGDH,A.YWLSH AS YWLSH,A.F_TYPE AS F_TYPEB " +
				"FROM EF_DAJBXX A,EF_DALX B " +
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE=B.ID)");

		if(!("".equals(pmt.getTjr()))){
			sbf+=" AND A.TJR like '%"+pmt.getTjr()+"%'";
		}
		if(!("".equals(pmt.getJsr()))){
			sbf+=" AND A.JSR like '%"+pmt.getJsr()+"%'";
		}
		if(!("".equals(pmt.getJsrqBegin()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') >= '"+pmt.getJsrqBegin()+"'";
		}
		if(!("".equals(pmt.getJsrqEnd()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') <= '"+pmt.getJsrqEnd()+"'";
		}
		if(!("".equals(pmt.getTjrqBegin()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') >= '"+pmt.getTjrqBegin()+"'";
		}
		if(!("".equals(pmt.getTjrqEnd()))){
			sbf+=" AND to_char(A.TJRQ,'yyyy-mm-dd') <= '"+pmt.getTjrqEnd()+"'";
		}
		if(!("".equals(pmt.getF_type()))){
			sbf+=" AND B.F_TYPE like '%"+pmt.getF_type()+"%'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by JSRQ DESC";
		
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������ѯ-��λ�鼯������ѯ
	 * @author czm 2011-12-15
	 */
	public List<Row> openDA_getBgglDwgjdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DwdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(DwdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH,B.DWZH AS DWZH," +
				"B.DW AS DW,B.DWDZ AS DWDZ,B.GJD AS GJD,B.CBWD AS CBWD,B.GJZGY AS GJZGY,to_char(B.KHRQ,'yyyy-mm-dd') AS KHRQ," +
				"to_char(B.XHRQ,'yyyy-mm-dd') AS XHRQ,to_char(B.FCRQ,'yyyy-mm-dd') AS FCRQ,A.BMJB AS BMJB,A.CFWZ AS CFWZ," +
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB FROM EF_DAJBXX A,EF_DWDA B,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' and A.F_TYPE='1' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID)");
		
		if(!("".equals(pmt.getDw()))){
			sbf+="AND B.DW LIKE '%"+pmt.getDw()+"%' ";
		}
		if(!("".equals(pmt.getKhrq_begin()))){
			sbf+=" AND to_char(B.KHRQ,'yyyy-mm-dd') >= '"+pmt.getKhrq_begin()+"'";
		}
		if(!("".equals(pmt.getKhrq_end()))){
			sbf+=" AND to_char(B.KHRQ,'yyyy-mm-dd') <= '"+pmt.getKhrq_end()+"'";
		}
		if(!("".equals(pmt.getXhrq_begin()))){
			sbf+=" AND to_char(B.XHRQ,'yyyy-mm-dd') >= '"+pmt.getXhrq_begin()+"'";
		}
		if(!("".equals(pmt.getXhrq_end()))){
			sbf+=" AND to_char(B.XHRQ,'yyyy-mm-dd') <= '"+pmt.getXhrq_end()+"'";
		}
		if(!("".equals(pmt.getFcrq_begin()))){
			sbf+=" AND to_char(B.FCRQ,'yyyy-mm-dd') >= '"+pmt.getFcrq_begin()+"'";
		}
		if(!("".equals(pmt.getFcrq_end()))){
			sbf+=" AND to_char(B.FCRQ,'yyyy-mm-dd') <= '"+pmt.getFcrq_end()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������Ϣ�������-������-���˹鼯����
	 * @author zy 2012-1-11
	 *
	 */
	public List<Row> openDA_getBgglGrgjdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		GjdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(GjdaBean)obj;
		sbf=("SELECT A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH,B.DW AS DW," +
				"B.ZGZH AS ZGZH,B.ZGXM AS ZGXM,B.DWZH AS DWZH,B.ZJHM AS ZJHM,B.XB AS XB,B.GJD AS GJD," +
				"to_char(B.KHRQ,'yyyy-mm-dd') AS KHRQ,B.CBWD AS CBWD,A.CFWZ AS CFWZ,A.YGDH AS YGDH,A.ID AS ID," +
				"A.JSR AS JSR,A.JSRQ AS JSRQ,A.BMJB AS BMJB,C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX  A,EF_GJDA B,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' and A.F_TYPE='2' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID)");
		


		if(!("".equals(pmt.getZgxm()))){
			sbf+="AND B.ZGXM LIKE '%"+pmt.getZgxm()+"%' ";
		}
		if(!("".equals(pmt.getZjhm()))){
			sbf+="AND B.ZJHM LIKE '%"+pmt.getZjhm()+"%' ";
		}
		if(!("".equals(pmt.getDw()))){
			sbf+="AND B.DW LIKE '%"+pmt.getDw()+"%' ";
		}
		if(!("".equals(pmt.getGjd()))){
			sbf+="AND B.GJD LIKE '%"+pmt.getGjd()+"%' ";
		}
		if(!("".equals(pmt.getKhrq_begin()))){
			sbf+=" AND to_char(B.KHRQ,'yyyy-mm-dd') >= '"+pmt.getKhrq_begin()+"'";
		}
		if(!("".equals(pmt.getKhrq_end()))){
			sbf+=" AND to_char(B.KHRQ,'yyyy-mm-dd') <= '"+pmt.getKhrq_end()+"'";
		}
		/**
		 *  �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������Ϣ�������-������-�����
	 * @author zy 2012-1-11
	 *
	 */
	public List<Row> openDA_getBgglDkgjdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		DkdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(DkdaBean)obj;
		sbf=("SELECT A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH,B.ZGZH AS ZGZH,B.ZGXM AS ZGXM," +
				"B.ZJHM AS ZJHM,B.DWZH AS DWZH,B.DW AS DW,B.DKNX AS DKNX,B.GFLX AS GFLX,B.GJD AS GJD,B.CBWD AS CBWD," +
				"A.ID AS ID,A.CFWZ AS CFWZ,A.BMJB AS BMJB,C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX  A,EF_DKDA B,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' and A.F_TYPE='4' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID)");
		

		if(!("".equals(pmt.getZgxm()))){
			sbf+="AND B.ZGXM LIKE '%"+pmt.getZgxm()+"%' ";
		}
		if(!("".equals(pmt.getGflx()))){
			sbf+="AND B.GFLX LIKE '%"+pmt.getGflx()+"%' ";
		}
		if(!("".equals(pmt.getDw()))){
			sbf+="AND B.DW LIKE '%"+pmt.getDw()+"%' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 *  ������Ϣ�������-������-֧ȡ����
	 * @author zy 2012-1 -11
	 *
	 */
	public List<Row> openDA_getBgglZqdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZqdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(ZqdaBean)obj;
		sbf=("SELECT A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH," +
				"B.ZGZH AS ZGZH,B.ZGXM AS ZGXM,B.ZJHM AS ZJHM,B.ZQRQ AS ZQRQ,B.DWZH AS DWZH," +
				"B.DW AS DW,A.CFWZ AS CFWZ,A.YGDH AS YGDH,A.ID AS ID,A.BMJB AS BMJB,"+
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB"+
				" FROM EF_DAJBXX A,EF_ZQDA B,EF_DALX C WHERE (A.F_STATE='�鵵' AND A.F_TYPE='3' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID) ");
		
	
		if(!("".equals(pmt.getZgxm()))){
			sbf+="AND B.ZGXM LIKE '%"+pmt.getZgxm()+"%' ";
		}
		if(!("".equals(pmt.getDw()))){
			sbf+="AND B.DW LIKE '%"+pmt.getDw()+"%' ";
		}
		if(!("".equals(pmt.getZqrq_begin()))){
			sbf+=" AND to_char(B.ZQRQ,'yyyy-mm-dd') >= '"+pmt.getZqrq_begin()+"'";
		}
		if(!("".equals(pmt.getZqrq_end()))){
			sbf+=" AND to_char(B.ZQRQ,'yyyy-mm-dd') <= '"+pmt.getZqrq_end()+"'";
		}
		/**
		 *  �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		 */
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������Ϣ�������-������-¥�̵���
	 * @author zy 2012-1 -11
	 *
	 */
	public List<Row> openDA_getBgglLpdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		LpdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(LpdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH,B.XMBH AS XMBH," +
				"B.XM AS XM,B.GJD AS GJD,B.CBWD AS CBWD,A.BMJB AS BMJB,to_char(A.JSRQ,'yyyy-mm-dd') AS JSRQ,A.CFWZ AS CFWZ," +
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX A,EF_LPDA B,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' and A.F_TYPE='5' and A.YGDH=B.YGDH and A.YWLSH=B.YWLSH and A.F_TYPE=C.ID)");
		
		
		if(!("".equals(pmt.getXm()))){
			sbf+="AND B.XM LIKE '%"+pmt.getXm()+"%' ";
		}
		if(!("".equals(pmt.getGjd()))){
			sbf+="AND B.GJD LIKE '%"+pmt.getGjd()+"%' ";
		}
		if(!("".equals(pmt.getJsrq_begin()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') >= '"+pmt.getJsrq_begin()+"'";
		}
		if(!("".equals(pmt.getJsrq_end()))){
			sbf+=" AND to_char(A.JSRQ,'yyyy-mm-dd') <= '"+pmt.getJsrq_end()+"'";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 *  ������Ϣ�������-������-���鵵�� 
	 * @author zy 2012-1 -12
	 *
	 */
	public List<Row> openDA_getBgglWsdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		WsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(WsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.YGDH AS YGDH,A.DAH AS DAH,A.TJR AS TJR,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW," +
				"A.HH AS HH,A.YWLSH AS YWLSH,A.TJRQ AS TJRQ,A.JSR AS JSR,A.BMJB AS BMJB,A.JSRQ AS JSRQ,A.BCNX AS BCNX," +
				"A.ZXR AS ZXR, A.ZXRQ AS ZXRQ,A.ZXYY AS ZXYY,C.ID AS F_TYPEB,A.CLFS AS CLFS,A.SXH AS SXH,C.F_TYPE AS F_TYPE," +
				"A.F_STATE AS F_STATE,B.TM AS TM,B.YJML AS YJML,B.EJML AS EJML,B.SJML AS SJML,B.ZRZ AS ZRZ,A.CFWZ AS CFWZ " +
				"FROM EF_DAJBXX A,EF_WSDA B ,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' and A.F_TYPE='6' and A.YGDH=B.YGDH AND A.F_TYPE=C.ID)");
		
		if(!("".equals(pmt.getDah()))){
			sbf+="AND A.DAH LIKE '%"+pmt.getDah()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getYjml()))){
			sbf+="AND B.YJML LIKE '%"+pmt.getYjml()+"%' ";
		}
		if(!("".equals(pmt.getEjml()))){
			sbf+="AND B.EJML LIKE '%"+pmt.getEjml()+"%' ";
		}
		if(!("".equals(pmt.getSjml()))){
			sbf+="AND B.SJML LIKE '%"+pmt.getSjml()+"%' ";
		}
		if(!("".equals(pmt.getZrz()))){
			sbf+="AND B.ZRZ LIKE '%"+pmt.getZrz()+"%' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+=" order by ID DESC";
		System.out.println("sbf===="+sbf) ;
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ������Ϣ�������-������-�Ƽ�����
	 * @author zy 2012-1 -12
	 *
	 */
	public List<Row> openDA_getBgglKjdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(ZldaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.YGDH AS YGDH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH," +
				"A.BCNX AS BCNX,A.BMJB AS BMJB,B.TM AS TM,A.YWLSH AS YWLSH,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX A ,EF_ZLDA B ,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE='7' AND A.YGDH=B.YGDH AND A.F_TYPE=C.ID)");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ������Ϣ�������-������-��Ƭ����
	 * @author zy 2012-1 -12
	 *
	 */
	public List<Row> openDA_getBgglZpdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(TsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.YGDH AS YGDH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH," +
				"A.BMJB AS BMJB,A.YWLSH AS YWLSH,A.BCNX AS BCNX,B.TM AS TM,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX A ,EF_TSDA B ,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE='8' AND A.YGDH=B.YGDH AND A.F_TYPE=C.ID)");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	
	/**
	 * ������Ϣ�������-������-¼�񵵰�
	 * @author zy 2011-1-12
	 *
	 */
	public List<Row> openDA_getBgglLxdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(TsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.YGDH AS YGDH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH," +
				"A.BCNX AS BCNX,B.TM AS TM,A.BMJB AS BMJB,A.YWLSH AS YWLSH,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX A ,EF_TSDA B ,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE='9' AND A.YGDH=B.YGDH AND A.F_TYPE=C.ID)");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������Ϣ�������-������-ʵ�ﵵ��
	 * @author zy 2012-1-12
	 *
	 */
	public List<Row> openDA_getBgglSwdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(ZldaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.YGDH AS YGDH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH," +
				"A.BCNX AS BCNX,B.TM AS TM,A.BMJB AS BMJB,A.YWLSH AS YWLSH,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX A ,EF_ZLDA B ,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE='10' AND A.YGDH=B.YGDH AND A.F_TYPE=C.ID)");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������Ϣ�������-������-֪ʶ����
	 * @author zy 2012-1-12
	 *
	 */
	public List<Row> openDA_getBgglZsdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		TsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(TsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.YGDH AS YGDH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH," +
				"A.BCNX AS BCNX,B.TM AS TM,A.BMJB AS BMJB,A.YWLSH AS YWLSH,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ," +
				"C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX A ,EF_TSDA B ,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE='11' AND A.YGDH=B.YGDH AND A.F_TYPE=C.ID) ");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������Ϣ�������-������-���µ���
	 * @author zy 2012-1-12
	 *
	 */
	public List<Row> openDA_getBgglRsdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		RsdaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(RsdaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.YGDH AS YGDH,B.ENAME AS ENAME,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH,A.BCNX AS BCNX," +
				"to_char(B.DJRQ,'yyyy-mm-dd') AS DJRQ,A.YWLSH AS YWLSH,A.BMJB AS BMJB,C.F_TYPE AS F_TYPE,B.DJR AS DJR,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX A ,EF_RSDA B ,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE='12' AND A.YGDH=B.YGDH AND A.F_TYPE=C.ID)");
		
		if(!("".equals(pmt.getDjr()))){
			sbf+="AND B.DJR LIKE '%"+pmt.getDjr()+"%' ";
		}
		if(!("".equals(pmt.getName()))){
			sbf+="AND B.ENAME LIKE '%"+pmt.getName()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.DJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.DJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ������Ϣ�������-������-���Ƶ���
	 * @author zy 2012-1-12
	 *
	 */
	public List<Row> openDA_getBgglFzdaData(Object obj, int pagesize, int pagenum) throws CallDbException {
		ZldaBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt=(ZldaBean)obj;
		sbf=("SELECT A.ID AS ID,A.DAH AS DAH,A.YGDH AS YGDH,A.DASH AS DASH,A.GH AS GH,A.GROW AS GROW,A.HH AS HH,A.BCNX AS BCNX,B.TM AS TM," +
				"A.BMJB AS BMJB,A.YWLSH AS YWLSH,B.CJR AS CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ,C.F_TYPE AS F_TYPE,C.ID AS F_TYPEB " +
				"FROM EF_DAJBXX A ,EF_ZLDA B ,EF_DALX C " +
				"WHERE (A.F_STATE='�鵵' AND A.F_TYPE='13' AND A.YGDH=B.YGDH AND A.F_TYPE=C.ID)");
		
		if(!("".equals(pmt.getCjr()))){
			sbf+="AND B.CJR LIKE '%"+pmt.getCjr()+"%' ";
		}
		if(!("".equals(pmt.getTm()))){
			sbf+="AND B.TM LIKE '%"+pmt.getTm()+"%' ";
		}
		if(!("".equals(pmt.getBegin()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')>='"+pmt.getBegin()+"' ";
		}
		if(!("".equals(pmt.getEnd()))){
			sbf+="AND to_char(B.CJRQ,'yyyy-MM-dd')<='"+pmt.getEnd()+"' ";
		}
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		sbf+="order by ID DESC";
		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sbf.toString(), pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ���Ĺ���-ʵ����Ĳ�ѯ
	 * @author xy  2012-1-10
	 *
	 */
	public List<Row> openDA_GetStjy(Object obj, int pagesize, int pagenum) throws CallDbException {
		JYBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (JYBean) obj;

		sbf=("SELECT A.ID,B.CJR,to_char(B.CJRQ,'yyyy-mm-dd') AS CJRQ,B.TM " +
				"FROM EF_DAJBXX A ,EF_TSDA B,EF_DALX C " +
				"WHERE (A.F_STATE='��״̬'  AND A.CLZT='����'  AND A.F_TYPE='8' AND A.YGDH=B.YGDH and C.ID=A.F_TYPE)");
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
		};
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	//----------------------δ��Ȩ�޿���
	/**
	 * ���Ĺ���-�ѽ���ʵ���ѯ
	 * @author xy  2012-1-10
	 *
	 */
	public List<Row> openDA_GetYjystda(Object obj, int pagesize, int pagenum) throws CallDbException {
		JYBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (JYBean) obj;

		sbf="SELECT DAH,DALX,JYJS,JYR,JYMD,to_char(JYRQ,'yyyy-mm-dd') AS JYRQ,to_char(NHRQ,'yyyy-mm-dd') AS NHRQ, " +
				" DJR,GH,HH,F_STATE,ID,YGDH,JYBH,GHRQ,BCDZ," +
				" SXH,DAZT FROM EF_JYJL WHERE (DAZT='ʵ��'  AND F_STATE='�ѽ��') " +
				pmt.getDah()+pmt.getJyr()+pmt.getDjr()+pmt.getTjrq()+pmt.getJsrq();
//		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
//		if(pmt.getIsAdmin()==2){
//			sbf+="";
//		}else{
//			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
//		};		
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ���Ĺ���-�ѹ黹��ʵ���ѯ
	 * @author xy  2012-1-10
	 *
	 */
	public List<Row> openDA_GetYghstda(Object obj, int pagesize, int pagenum) throws CallDbException {
		JYBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (JYBean) obj;

		sbf="SELECT DAH,DALX,JYJS,JYR,JYMD,to_char(JYRQ,'yyyy-mm-dd') AS JYRQ, " +
				" to_char(NHRQ,'yyyy-mm-dd') AS NHRQ,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ," +
				" DJR,GH,HH,F_STATE,ID,YGDH,JYBH,BCDZ,DAZT,SXH FROM EF_JYJL " +
				" WHERE (DAZT='ʵ��'  AND F_STATE='�ѹ黹') " +
				pmt.getDah()+pmt.getJyr()+pmt.getDjr()+pmt.getTjrq()+pmt.getJsrq();
//		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
//		if(pmt.getIsAdmin()==2){
//			sbf+="";
//		}else{
//			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
//		};		
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ���Ĺ���-����δ�黹��ʵ���ѯ
	 * @author xy  2012-1-10
	 *
	 */
	public List<Row> openDA_GetDqwghstda(Object obj, int pagesize, int pagenum) throws CallDbException {
		JYBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (JYBean) obj;

		sbf="SELECT DAH,DALX,JYJS,JYR,JYMD,to_char(JYRQ,'yyyy-mm-dd') AS JYRQ," +
				"to_char(NHRQ,'yyyy-mm-dd') AS NHRQ,DJR,GH,HH,F_STATE,ID,YGDH," +
				"JYBH,GHRQ,BCDZ,SXH,DAZT FROM EF_JYJL WHERE (NHRQ<SYSDATE  AND F_STATE='�ѽ��') " +
				pmt.getDah()+pmt.getJyr()+pmt.getDjr()+pmt.getTjrq()+pmt.getJsrq();
//		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
//		if(pmt.getIsAdmin()==2){
//			sbf+="";
//		}else{
//			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
//		};		
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ���Ĺ���-�ҵĵ��ӵ����������뵥
	 * @author xy  2012-1-10
	 *
	 */
	
	public List<Row> openDA_GetWddzda(Object obj, int pagesize, int pagenum) throws CallDbException {
		JYBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (JYBean) obj;

		sbf="SELECT ID,DAH,to_char(JYRQ,'yyyy-mm-dd') AS JYRQ,to_char(GHRQ,'yyyy-mm-dd') AS GHRQ," +
				"F_STATE FROM EF_JYJL WHERE (F_CREATOR_ID=1 AND DAZT<>'ʵ��') " +
				pmt.getDah()+pmt.getCxrq1()+pmt.getCxrq2()+pmt.getTjrq()+pmt.getJsrq()+"  ORDER BY JYRQ DESC";
//		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
//		if(pmt.getIsAdmin()==2){
//			sbf+="";
//		}else{
//			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
//		};		
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	
	/**
	 * ���Ĺ���-������ĵ��ӵ����������뵥
	 * @author xy  2012-1-10
	 *
	 */
	public List<Row> openDA_GetDcldzda(Object obj, int pagesize, int pagenum) throws CallDbException {
		JYBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (JYBean) obj;

		sbf="SELECT F_DATA_TITLE,F_START_USER_NAME,F_DATA_ID,F_PROCESS_DESCRIBE,to_char(F_START_TIME,'yyyy-mm-dd') AS F_START_TIME FROM V_MK_SYS_WF_LOGS_PROCESSING " +
				"WHERE (F_USER_ID=1 AND F_FLOW_KEY='wada.flowArchiveBorrow' AND F_DATA_ID IS NOT NULL  )  " +
				pmt.getDah()+pmt.getJyr()+pmt.getTjrq()+pmt.getJsrq()+" ORDER BY ID  DESC";
//		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
//		if(pmt.getIsAdmin()==2){
//			sbf+="";
//		}else{
//			sbf+=" AND A.GJD ='"+pmt.getGlb()+"' ";
//		};	
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	/**
	 * ���Ĺ���-�ҽ��ĵĵ��ӵ���
	 * @author xy  2012-1-10
	 *
	 */
	public List<Row> openDA_GetWjydzda(Object obj, int pagesize, int pagenum) throws CallDbException {
		JYBean pmt = null;
		SqlParameterExt spx = null;
		String sbf="";
		pmt = (JYBean) obj;

		sbf="SELECT B.ID AS ID,A.DAH AS DAH,A.YGDH AS YGDH,A.DALX AS DALX,B.CLFS AS CLFS,A.JYJS AS JYJS,A.JYR AS JYR," +
				"A.JYMD AS JYMD,to_char(A.JYRQ,'yyyy-mm-dd') AS JYRQ,A.NHRQ AS NHRQ," +
				"to_char(A.GHRQ,'yyyy-mm-dd') AS GHRQ,A.DJR AS DJR,B.BMJB AS BMJB,B.BCNX AS BCNX,A.DAZT AS DAZT,A.GH AS GH," +
				"A.HH AS HH,A.BCDZ AS BCDZ,A.SXH AS SXH FROM EF_JYJL A,EF_DAJBXX B " +
				"WHERE ( A.YGDH=B.YGDH AND A.F_CREATOR_ID=1 AND A.GHRQ>SYSDATE-1  AND A.F_RESULT='ͬ�����' " +
				"AND A.F_STATE='����' AND A.DAZT<>'ʵ��') " +
				pmt.getDah()+pmt.getJyr()+pmt.getTjrq()+pmt.getJsrq()+pmt.getCxrq1()+pmt.getCxrq2()+" ORDER BY A.F_FLOWLOGID DESC ";
		// �¼ӵ���Ȩ�޲�ѯ gjf 2012-4-11
		if(pmt.getIsAdmin()==2){
			sbf+="";
		}else{
			sbf+=" AND B.GJD ='"+pmt.getGlb()+"' ";
		};		
		try {
			return bo.queryToList(sbf, pagesize, pagenum);			
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}
	
	/**
	 * ��ʷ����¼��
	 * 
	 * 2012-2-17 chg by hd
	 * 
	 * @param obj
	 * @throws CallDbException
	 */
	public void executeDA_DKDAHR_SAVE(Object obj) throws CallDbException {

		DkdaHrecordBean dhb = (DkdaHrecordBean) obj;

		SqlParameterExt spx = null;

		short ret = 1;
		try {
			spx = new SqlParameterExt(); // ����ת��

			spx.add(new StringValue(dhb.getYgdh()));// p1
			spx.add(new StringValue(dhb.getYwlsh()));// p2
			spx.add(new StringValue(dhb.getTjr()));// p3
			spx.add(new DateValue(XTool.Str2Date(ImageUploadBean.getData())));// ��������//p4

			spx.add(new StringValue(dhb.getZxjbr()));// p5
			spx.add(new StringValue(dhb.getCbwd()));// p6
			spx.add(new StringValue(dhb.getYhjbr()));// p7
			spx.add(new StringValue(dhb.getZgxm()));// p8
			spx.add(new StringValue(dhb.getDw()));// p9

			spx.add(new StringValue(dhb.getXb()));// p10
			//spx.add(new LongValue(dhb.getNl()));
			spx.add(new StringValue(dhb.getHkszd())); // p11
			spx.add(new StringValue(dhb.getZjhm()));// p12
			spx.add(new StringValue(dhb.getJtzz()));// p13

			spx.add(new DoubleValue(dhb.getYjce()));// p14
			
			spx.add(new DoubleValue(dhb.getGjjye()));// p15
			spx.add(new StringValue(dhb.getHyzk()));// p16
			spx.add(new DoubleValue(dhb.getYsr()));// p17
			spx.add(new DoubleValue(dhb.getYjtsr()));// p18
			
			spx.add(new StringValue(dhb.getZzdh()));// p19
			spx.add(new StringValue(dhb.getSj()));// p20
			spx.add(new StringValue(dhb.getDwdh()));// p21
			spx.add(new StringValue(dhb.getGtrxm()));// p22
			spx.add(new StringValue(dhb.getGtrdw()));// p23

			spx.add(new StringValue(dhb.getGtrzh()));// p24
			spx.add(new DoubleValue(dhb.getGtryjce()));// p25
			spx.add(new DoubleValue(dhb.getGtrgjjye()));// p26
			spx.add(new DoubleValue(dhb.getGtrysr()));// p27
			spx.add(new StringValue(dhb.getGtrsfzh()));// p28

			spx.add(new StringValue(dhb.getGtrzzdh()));// p29
			spx.add(new StringValue(dhb.getGtrsj()));// p30
			spx.add(new StringValue(dhb.getGtrdwdh()));// p31
			spx.add(new StringValue(dhb.getQtrgx()));// p32
			spx.add(new StringValue(dhb.getQtrdwdh()));// p33

			spx.add(new DoubleValue(dhb.getQtrysr()));// p34
			spx.add(new StringValue(dhb.getQtrzzdh()));// p35
			spx.add(new StringValue(dhb.getQtrsj()));// p36
			spx.add(new StringValue(dhb.getQtrdw()));// p37
			spx.add(new StringValue(dhb.getGfxxdz()));// p38

			spx.add(new StringValue(dhb.getGflx()));// p39
			spx.add(new DoubleValue(dhb.getNgfmj()));// p40
			spx.add(new DoubleValue(dhb.getNgfzj()));// p41
			spx.add(new DoubleValue(dhb.getNgfyfje()));// p42
			spx.add(new StringValue(dhb.getNgfjfdw()));// p43

			spx.add(new StringValue(dhb.getNgfdwdh()));// p44
			spx.add(new StringValue(dhb.getNgfdwxsxkz()));// p45
			spx.add(new StringValue(dhb.getNgfdwtdzbh()));// p46
			String nDate = " ";
			if(dhb.getNgfjfrq().length()>0){
				nDate=dhb.getNgfjfrq().substring(0,10);
			}else
			{
				nDate=dhb.getNgfjfrq();
			}
			spx.add(new DateValue(XTool.Str2Date(nDate)));// p47

			spx.add(new StringValue(dhb.getNgffwsyqzsh()));// p48

			spx.add(new StringValue(dhb.getNgfxshth()));// p49
			spx.add(new StringValue(dhb.getDbdw()));// p50
			spx.add(new StringValue(dhb.getDbdywdz()));// p51
			spx.add(new StringValue(dhb.getDbcqsyr()));// p52
			spx.add(new StringValue(dhb.getDbfwsyqzh()));// p53

			spx.add(new DoubleValue(dhb.getDbfwmj()));// p54
			spx.add(new DoubleValue(dhb.getDbfwjz()));// p55
			spx.add(new StringValue(dhb.getDbzywmc()));// p56
			spx.add(new DoubleValue(dhb.getDbzywjz()));// p57
			//spx.add(new DoubleValue(dhb.getZczj()));// 

			spx.add(new DoubleValue(dhb.getGjjdk()));// p58
			//spx.add(new DoubleValue(dhb.getDjnx()));// 
			spx.add(new DoubleValue(dhb.getSydk()));// p59
			spx.add(new StringValue(dhb.getHkfs()));// p60
			spx.add(new StringValue(dhb.getTjrbm())); //p61
			spx.add(new StringValue(dhb.getGjd()));	//p62
			spx.add(new StringValue(dhb.getGjdbm()));	//p63
			spx.add(new IntValue(dhb.getDknx()));		//p64
			spx.add(new StringValue(dhb.getDwzh()));	//p65
			//spx.add(new StringValue(dhb.getCbwdbm()));	
			spx.add(new IntValue(dhb.getClfs()));		//p66
			spx.add(new StringValue(dhb.getF_state()));	//p67
			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);

		} catch (Exception e) {
			throw new CallDbException("ִ��[executeDA_DKDAHR_SAVE1]�������ݿ��������!"
					+ e.getMessage());
		}
		String sql = "p_dkdals_chg(?p1,?p2,?p3,?p4,?p5,?p6,"
				+ "?p7,?p8,?p9,?p10,?p11,?p12,?p13,?p14,?p15,?p16,"
				+ "?p17,?p18,?p19,?p20,?p21,?p22,?p23,?p24,?p25,?p26,"
				+ "?p27,?p28,?p29,?p30,?p31,?p32,?p33,?p34,?p35,?p36,"
				+ "?p37,?p38,?p39,?p40,?p41,?p42,?p43,?p44,?p45,?p46,"
				+ "?p47,?p48,?p49,?p50,?p51,?p52,?p53,?p54,?p55,?p56,"
				+ "?p57,?p58,?p59,?p60,?p61,?p62,?p63,?p64,?65,?66,?67,?68)";
		try {
			bo.setSqlParameterExt(spx);
			bo.executeProcedure(sql);
			ret = spx.get(68).getShort();
		} catch (Exception err) {
			throw new CallDbException("ִ��[executeDA_DKDAHR_SAVE2]�������ݿ��������!"
					+ err.getMessage());
		}
		if (ret != 0) {
			throw new CallDbException("ִ��[executeDA_DKDAHR_SAVE3]�������ݿ��������!",
					ret);
		}
	}
	
	
	/**
	 * �����鵵���÷��� 2012-4-12 by wl ����ר��
	 * 
	 * @param object
	 */

	public void executeDA_ARCHIVESELECTALL(Object obj) throws CallDbException {
		DajbxxBean bean = (DajbxxBean) obj;
		String[] idString = bean.getIdString().split(";");
		SqlParameterExt spx = null;
		String zt = bean.getBmjb() == null ? "��ͨ" : bean.getBmjb();
		try {
			int j = 0;
			for (int i = 0; i < idString.length; i++) {
				spx = new SqlParameterExt();
				j++;
				try {
					System.out.println(idString[i]+"===============================");
					long id = Long.parseLong(idString[i]);
					spx.add(new StringValue(idString[i]));
					spx.add(new StringValue(bean.getJsrbm()));
					spx.add(new StringValue(bean.getJsr()));
					spx.add(new StringValue(bean.getDash()));
					spx.add(new StringValue(bean.getGh()));
					spx.add(new StringValue(bean.getPh()));
					spx.add(new StringValue(bean.getGrow()));
					spx.add(new StringValue(bean.getHh()));
					spx.add(new IntValue(j));
					spx.add(new StringValue(zt));
					spx.add(new LongValue(bean.getBcnx() == 0 ? 20 : bean.getBcnx()));
					spx.add(new LongValue(id));
					executeDA_UPDATEDABH(bean,idString[i],j);
				} catch (Exception err) {
					throw new CallDbException("�������Ϸ�!"
							+ err.getMessage());
				}
				try {
					bo.clearParameters();
					bo.setSqlParameterExt(spx);
				
					bo.execute("update ef_dajbxx set f_state='�鵵', dah=?,jsrbm=?,jsr=?,dash=?,gh=?,ph=?,grow=?,hh=?,sxh=?,bmjb=?,bcnx=? where id=?");
					bo.closestmt();
				} catch (Exception err) {
					throw new CallDbException(err.getMessage());
				}
			}
		} catch (Exception e) {
			throw new CallDbException("ִ��sql����" + e.getMessage());
		}
	}

	private void executeDA_UPDATEDABH(Object obj,String id,int sxh) throws CallDbException{
		DajbxxBean bean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		try {
			spx.add(new StringValue(id));
			spx.add(new StringValue(bean.getDash()));
			spx.add(new StringValue(bean.getGh()));
			spx.add(new StringValue(bean.getPh()));
			spx.add(new StringValue(bean.getGrow()));
			spx.add(new StringValue(bean.getHh()));
			spx.add(new IntValue(sxh));
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!"
					+ err.getMessage());
		}
		try {
			bo.clearParameters();
			bo.setSqlParameterExt(spx);
		
			bo.execute("update ef_dagsz set dabh=? where dash=? and gh=? and ph=? and grow=? and hh=? and dno=?");
			bo.closestmt();
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
		
		
	}
	// ����������Ϣ�����ź�combox 2011-6-25 wm
	public List<Row> openDA_PHCOMBOX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;

		String str = "select ph from ef_dagsz where dash='" + z.getDash()
				+ "'" + " and gh='" + z.getDagh() + "' and f_type='"
				+ z.getF_type() + "'" + " and bum='" + z.getId()
				+ "' group by ph order by ph";

		try {
			return bo.queryToList(str);
		} catch (CallDbException e) {
			throw new CallDbException("ִ��[DA_GHROWCOMBOX]����" + e.getMessage());
		}
	}

	// ��ȡÿ�������������
	public List<Row> openDA_PHROWCOMBOXXX(Object obj) throws CallDbException {
		DagszBean z = (DagszBean) obj;
		String sql = "select b.grow,b.ph,b.f_type,count(dno)as dmax,"+
		 			 " (select  count(hh) from ef_dagsz a where a.dash='"+z.getDash()+"' and a.gh='"+z.getDagh()+"'"+
			         " and a.grow=b.grow and a.ph=b.ph  and a.f_type=b.f_type and dabh !='0') as dnum"+
				     " from ef_dagsz b where dash='"+z.getDash()+"' and gh='"+z.getDagh()+"' group by b.grow,b.ph,b.f_type order by b.grow,b.ph";
		try {
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("ִ��[DA_PHROWCOMBOXXX]����" + e.getMessage());
		}
	}
	/**
	 * ҵ��ϵͳɾ�����ϵ��õķ��� 2011.11.15 add by zy
	 * 
	 * 
	 * 2012��3��7�� chg by hd
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List openDA_JNML_CLYX_QUERY_FOR_OLD_APP_DEL(Object obj)
			throws CallDbException {

		// String dabh = openDA_DABH_QUERY(obj);
		//
		// DajbxxBean daBean = (DajbxxBean) obj;
		//
		// SqlParameterExt spx = null;
		//
		// spx = new SqlParameterExt();
		// try {
		// spx = new SqlParameterExt();
		// spx.add(new StringValue(daBean.getF_type()));// ��������
		// spx.add(new StringValue(daBean.getYgdh()));// Ԥ�鵵��
		// spx.add(new StringValue(daBean.getCzy()));
		// } catch (Exception err) {
		// throw new CallDbException("�������Ϸ�!" + err.getMessage());
		// }
		//
		// String sql = "";
		// /*
		// * ef_dalx���浵������,1-��λ,2-�鼯,3-֧ȡ,4-����,5-¥��
		// *
		// * 2011��7��21�� chg by hd
		// *
		// * 2011��7��25�� chg by hd
		// */
		// if (dabh == null || "".equals(dabh.trim())) {
		// if ("4".equals(daBean.getF_type())
		// || "3".equals(daBean.getF_type())) {
		// return null;
		// }
		// sql =
		// "select id, ygdh, clbm, cl, zt, czfs, F_CREATOR_NAME, F_CREATOR_DEPT_TIME, F_UPDATER_NAME, F_UPDATER_TIME , ( select f_type from ef_dalx where id = ? ) as f_type from ef_jnml where ygdh = ? and F_CREATOR_NAME = ? and (zt = '����' or zt = '����') order by clbm";
		// } else {
		// sql =
		// "select id, ygdh, clbm, cl, zt, czfs, F_CREATOR_NAME, F_CREATOR_DEPT_TIME, F_UPDATER_NAME, F_UPDATER_TIME , ( select f_type from ef_dalx where id = ? ) as f_type from ef_jnml where ygdh = ? and F_CREATOR_NAME = ? and id in ( "
		// + dabh + " ) and (zt = '����' or zt = '����') order by clbm";
		//
		// }
		//
		// try {
		// bo.setSqlParameterExt(spx);
		// return bo.queryToList(sql);
		// } catch (Exception e) {
		// throw new CallDbException("����Ŀ¼��Ϣ���ʧ�ܣ�");
		// }
		DajbxxBean daBean = (DajbxxBean) obj;

		SqlParameterExt spx = null;

		spx = new SqlParameterExt();

		String sql = "";

		/*
		 * ef_dalx���浵������,1-��λ,2-�鼯,3-֧ȡ,4-����,5-¥��
		 * 
		 * 2011��7��21�� chg by hd
		 * 
		 * 2011��7��25�� chg by hd
		 * 
		 * 2012��3��7�� chg by hd
		 */
		if ("4".equals(daBean.getF_type()) || "3".equals(daBean.getF_type())) {

			try {
				spx = new SqlParameterExt();
				spx.add(new StringValue(daBean.getF_type()));// ��������
				spx.add(new StringValue(daBean.getYgdh()));// Ԥ�鵵��
				spx.add(new StringValue(daBean.getYgdh()));// Ԥ�鵵��
				spx.add(new StringValue(daBean.getYwlsh()));// ҵ����ˮ��
			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}

			sql = "select id, ygdh, clbm, cl, zt, czfs, F_CREATOR_NAME, F_CREATOR_DEPT_TIME, F_UPDATER_NAME, F_UPDATER_TIME , ( select f_type from ef_dalx where id = ? ) as f_type from ef_jnml where ygdh = ? and id in ( select jnml_id from ef_dabh where ygdh = ? and ywlsh = ? ) and (zt = '����' or zt = '����') order by clbm";

		} else {

			try {
				spx = new SqlParameterExt();
				spx.add(new StringValue(daBean.getF_type()));// ��������
				spx.add(new StringValue(daBean.getYgdh()));// Ԥ�鵵��
			} catch (Exception err) {
				throw new CallDbException("�������Ϸ�!" + err.getMessage());
			}

			sql = "select id, ygdh, clbm, cl, zt, czfs, F_CREATOR_NAME, F_CREATOR_DEPT_TIME, F_UPDATER_NAME, F_UPDATER_TIME , ( select f_type from ef_dalx where id = ? ) as f_type from ef_jnml where ygdh = ? and (zt = '����' or zt = '����') order by clbm";

		}

		try {
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql.toLowerCase());
		} catch (Exception e) {
			throw new CallDbException("����Ŀ¼��Ϣ���ʧ�ܣ�");
		}
	}
	/*
	 * ҵ��ϵͳɾ�����ϵ��õķ���
	 * 2011.11.15 add by zy
	 * 
	 */
	
	public void executeDA_CL_DEL(Object obj) throws CallDbException {

		DajbxxBean dabean = (DajbxxBean) obj;
		SqlParameterExt spx = new SqlParameterExt();
		short ret = VL_SIX;
		
		try {
			Long id = Long.valueOf(dabean.getIdString()) ;
			spx.add(new LongValue(id));
			spx.add(new StringValue(dabean.getCzy()));
			spx.add(new StringValue(dabean.getYgdh()));
			spx.add(new StringValue(dabean.getYwlsh()));
			spx.add(new StringValue(dabean.getF_type())); // p23���Ϸ���

			spx.add(new ShortValue(ret), SqlParameterExt.SQL_OUTPUT);// p25����ֵ,0�ɹ�
			// <>0ʧ��
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		String sql = "p_ywda_cl_del(?p1,?p2,?p3,?p4,?p5,?p6)";
		bo.setSqlParameterExt(spx);
		bo.executeProcedure(sql);

		try {
			ret = spx.get(6).getShort();
		} catch (Exception err) {
			throw new CallDbException("ɾ�����ϲ��ɹ�!" + err.getMessage());
		}
	
		if (ret == VL_ONE) {
			throw new CallDbException("�����ڴ˷ݵ���!", ret);
		} else if (ret == VL_TWO) {
			throw new CallDbException("�����Ѿ��ύ!", ret);
		} else if (ret == VL_THREE) {
			throw new CallDbException("�˲��ϲ�����!", ret);
		} else if (ret == VL_FOUR) {
			throw new CallDbException("�˲����Ѿ�ɾ��!", ret);
		} else if (ret == VL_FIVE) {
			throw new CallDbException("����Ա��һ�²�����ɾ��!", ret);
		}

	}


}
