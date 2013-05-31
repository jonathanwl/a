package com.wasoft.calldb.business.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wasoft.calldb.sql.value.TimestampValue;
import com.wasoft.calldb.CallDbException;
import com.wasoft.calldb.SqlParameterExt;
import com.wasoft.calldb.business.AbstractDbInterface;
import com.wasoft.calldb.sql.Row;
import com.wasoft.calldb.sql.value.IntValue;
import com.wasoft.calldb.sql.value.StringValue;
import com.wasoft.dataserver.ws.controlcenter.bean.SetPointDTO;

public class ControlCenterServiceImpl extends AbstractDbInterface {

	public ControlCenterServiceImpl() {
		super();
	}

	public String toString() {
		return getClass().getName();
	}

	public List<Row> openControlCenter_setPointSet(Object obj)
			throws CallDbException {

		@SuppressWarnings("unused")
		SetPointDTO xb = (SetPointDTO) obj;
		String sql = "select * from bm_a075 order by bm";

		try {
			SqlParameterExt spx = new SqlParameterExt();
			bo.setSqlParameterExt(spx);
			return bo.queryToList(sql);
		} catch (Exception e) {
			throw new CallDbException("执行方法[openControlCenter_setPointSet]出错"
					+ e.getMessage());
		}
	}

	public void executeControlCenter_delsetPointSet(Object obj)
			throws CallDbException {
		SetPointDTO xb = (SetPointDTO) obj;

		String sql = "delete from bm_a075 where bm=?";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			spx.add(new StringValue(xb.getBm()));
			bo.setSqlParameterExt(spx);
			bo.execute(sql);
		} catch (Exception e) {
			throw new CallDbException("执行方法[ControlCenter_delsetPointSet]出错："
					+ e.getMessage());
		}

	}

	public void executeControlCenter_addsetPointSet(Object obj)
			throws CallDbException {
		SetPointDTO xb = (SetPointDTO) obj;

		String sql = "insert into bm_a075(bm,mc,txdz,khyh,yhzh,fzr,cwzg,cwjz,cwsh,cwtz,yzbm,lxdh,czdh,znjl,zwdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			SqlParameterExt spx = new SqlParameterExt();
			spx.add(new StringValue(xb.getBm()));
			spx.add(new StringValue(xb.getMc()));
			spx.add(new StringValue(xb.getTxdz()));
			spx.add(new StringValue(xb.getKhyh()));
			spx.add(new StringValue(xb.getYhzh()));
			spx.add(new StringValue(xb.getFzr()));
			spx.add(new StringValue(xb.getCwzg()));
			spx.add(new StringValue(xb.getCwjz()));
			spx.add(new StringValue(xb.getCwsh()));
			spx.add(new StringValue(xb.getCwtz()));
			spx.add(new StringValue(xb.getYzbm()));
			spx.add(new StringValue(xb.getLxdh()));
			spx.add(new StringValue(xb.getCzdh()));
			spx.add(new IntValue(Integer.parseInt(xb.getZnjl())));
			System.out
					.println("-----------111--------executeControlCenter_addsetPointSet");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(xb.getZwdate());
			System.out
					.println("-------------222-------executeControlCenter_addsetPointSet");
			spx.add(new TimestampValue(date));
			bo.setSqlParameterExt(spx);
			System.out
					.println("-------------------333333333331---executeControlCenter_addsetPointSet");
			bo.execute(sql);
		} catch (Exception e) {
			throw new CallDbException("执行方法[ControlCenter_addsetPointSet]出错："
					+ e.getMessage());
		}

	}

	public void executeControlCenter_modifysetPointSet(Object obj)
			throws CallDbException {
		SetPointDTO xb = (SetPointDTO) obj;

		String sql = "update bm_a075 set  mc=?,txdz=?,khyh=?,yhzh=?,fzr=?,cwzg=?,cwjz=?,cwsh=?,cwtz=?,yzbm=?,lxdh=?,czdh=?,znjl=?,zwdate=? where bm=?  ";
		try {
			SqlParameterExt spx = new SqlParameterExt();

			spx.add(new StringValue(xb.getMc()));
			spx.add(new StringValue(xb.getTxdz()));
			spx.add(new StringValue(xb.getKhyh()));
			spx.add(new StringValue(xb.getYhzh()));
			spx.add(new StringValue(xb.getFzr()));
			spx.add(new StringValue(xb.getCwzg()));
			spx.add(new StringValue(xb.getCwjz()));
			spx.add(new StringValue(xb.getCwsh()));
			spx.add(new StringValue(xb.getCwtz()));
			spx.add(new StringValue(xb.getYzbm()));
			spx.add(new StringValue(xb.getLxdh()));
			spx.add(new StringValue(xb.getCzdh()));
			spx.add(new IntValue(Integer.parseInt(xb.getZnjl())));
			System.out.println("--------------------111111111111");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(xb.getZwdate());
			System.out.println("--------------------22222222222222222222");
			spx.add(new TimestampValue(date));
			spx.add(new StringValue(xb.getBm()));
			bo.setSqlParameterExt(spx);
			System.out.println("-------------------333333333331");
			bo.execute(sql);
		} catch (Exception e) {
			throw new CallDbException("执行方法[ControlCenter_addsetPointSet]出错："
					+ e.getMessage());
		}

	}
}
