//package com.wasoft.dataserver.ws.controlcenter;
//
//import java.util.List;
//
//import javax.jws.WebParam;
//import javax.jws.WebResult;
//import javax.jws.WebService;
//import javax.jws.soap.SOAPBinding;
//import javax.jws.WebMethod;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import com.wasoft.Constant;
//import com.wasoft.calldb.DbMethod;
//import com.wasoft.calldb.sql.Row;
//import com.wasoft.dataserver.ws.CommBase;
//import com.wasoft.dataserver.ws.controlcenter.bean.BankEncodingDTO;
//import com.wasoft.dataserver.ws.controlcenter.bean.BorrowRateDTO;
//import com.wasoft.dataserver.ws.controlcenter.bean.ExameGradeDTO;
//import com.wasoft.dataserver.ws.controlcenter.bean.SetPointDTO;
//import com.wasoft.dataserver.ws.controlcenter.bean.SystemEncodingDTO;
//import com.wasoft.dataserver.ws.controlcenter.bean.SystemYearlyDTO;
//import com.wasoft.javabean.DataServiceTraceInfo;
//
//@WebService(serviceName = "controlcenterService", targetNamespace = "http://ws.controlcenter.wasoft.com/")
//@SOAPBinding(style = SOAPBinding.Style.RPC)
//public class ControlCenter extends CommBase {
//
//	private static final Log log = LogFactory.getLog(ControlCenter.class);
//
//	@SuppressWarnings("unused")
//	private String setErrorInfo(String info) {
//		return "<font color='" + Constant.COLOR_ERROR + "'>" + info + "</font>";
//	}
//
//	@WebMethod
//	@WebResult(partName = "version")
//	public String getVer() {
//		DataServiceTraceInfo.getInstance().getTrace("返回版本号",
//				DataServiceTraceInfo.TYPE.Console);
//		return "wasoft PersonalWebService ver 1.0";
//	}
//
//	// --------------------------------------------------------------------
//	/**
//	 * 归集点设置
//	 * 
//	 * @param certification
//	 *            口令
//	 * @return type
//	 */
//	@WebMethod
//	@WebResult(partName = "SetPointDTOArray")
//	public SetPointDTO[] setPointSet(
//
//	) {
//		String trace = "归集点设置查看";
//		SetPointDTO[] cbs = null;
//		@SuppressWarnings("unused")
//		SetPointDTO[] cbs1 = null;
//		@SuppressWarnings("unused")
//		SetPointDTO[] cbs2 = null;
//		try {
//
//			List<Row> lst = DbMethod.makeDbSingleton().Open(
//					"ControlCenter_setPointSet", null);
//
//			cbs = new SetPointDTO[lst.size()];
//			int i = 0;
//			for (Row row : lst) {
//				SetPointDTO cb = new SetPointDTO();
//				cb.setBm(row.getTrimString("bm"));
//				cb.setCwjz(row.getTrimString("cwjz"));
//				cb.setCwsh(row.getTrimString("cwsh"));
//				cb.setCwtz(row.getTrimString("cwtz"));
//				cb.setCwzg(row.getTrimString("cwzg"));
//				cb.setCzdh(row.getTrimString("czdh"));
//				cb.setFzr(row.getTrimString("fzr"));
//				cb.setKhyh(row.getTrimString("khyh"));
//				cb.setLxdh(row.getTrimString("lxdh"));
//				cb.setMc(row.getTrimString("mc"));
//				cb.setTxdz(row.getTrimString("txdz"));
//				// cb.setYgjrs(row.getTrimString("ygjrs"));
//				cb.setYhzh(row.getTrimString("yhzh"));
//				cb.setYzbm(row.getTrimString("yzbm"));
//				cb.setZnjl(row.getTrimString("znjl"));
//				cb.setZwdate(row.getDefDateToString("zwdate"));
//				cbs[i++] = cb;
//			}
//		} catch (Exception e) {
//			trace = "<font color='red'>归集点设置：" + e.getMessage() + "</font>";
//			log.error(trace);
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return cbs;
//	}
//
//	// ---------------------------
//	/**
//	 * 归集点添加
//	 * 
//	 * @param bm,mc,txdz,khyh,yhzh,fzr,cwzg,cwjz,cwsh,cwtz,yzbm,lxdh,czdh
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "SetPointDTOArray")
//	public int add_setPointSet(@WebParam(partName = "bm")
//	String bm, @WebParam(partName = "mc")
//	String mc, @WebParam(partName = "txdz")
//	String txdz, @WebParam(partName = "khyh")
//	String khyh, @WebParam(partName = "yhzh")
//	String yhzh, @WebParam(partName = "fzr")
//	String fzr, @WebParam(partName = "cwzg")
//	String cwzg, @WebParam(partName = "cwjz")
//	String cwjz, @WebParam(partName = "cwsh")
//	String cwsh, @WebParam(partName = "cwtz")
//	String cwtz, @WebParam(partName = "yzbm")
//	String yzbm, @WebParam(partName = "lxdh")
//	String lxdh, @WebParam(partName = "czdh")
//	String czdh, @WebParam(partName = "znjl")
//	String znjl, @WebParam(partName = "zwdate")
//	String zwdate, @WebParam(partName = "ygjrs")
//	String ygjrs) {
//		String trace = "归集点添加";
//		try {
//
//			DbMethod.makeDbSingleton().Execute(
//					"ControlCenter_addsetPointSet",
//					new SetPointDTO(bm, mc, txdz, khyh, yhzh, fzr, cwzg, cwjz,
//							cwsh, cwtz, yzbm, lxdh, czdh, znjl, zwdate, ygjrs));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>归集点添加出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	/**
//	 * 归集点删除
//	 * 
//	 * @param hth
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "SetPointDTOArray")
//	public int del_setPointSet(@WebParam(partName = "bm")
//	String bm) {
//		String trace = "归集点删除";
//		try {
//
//			DbMethod.makeDbSingleton().Execute("ControlCenter_delsetPointSet",
//					new SetPointDTO(bm));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>归集点删除出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	// ---------------------------
//	/**
//	 * 归集点修改
//	 * 
//	 * @param bm,mc,txdz,khyh,yhzh,fzr,cwzg,cwjz,cwsh,cwtz,yzbm,lxdh,czdh
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "SetPointDTOArray")
//	public int modify_setPointSet(@WebParam(partName = "bm")
//	String bm, @WebParam(partName = "mc")
//	String mc, @WebParam(partName = "txdz")
//	String txdz, @WebParam(partName = "khyh")
//	String khyh, @WebParam(partName = "yhzh")
//	String yhzh, @WebParam(partName = "fzr")
//	String fzr, @WebParam(partName = "cwzg")
//	String cwzg, @WebParam(partName = "cwjz")
//	String cwjz, @WebParam(partName = "cwsh")
//	String cwsh, @WebParam(partName = "cwtz")
//	String cwtz, @WebParam(partName = "yzbm")
//	String yzbm, @WebParam(partName = "lxdh")
//	String lxdh, @WebParam(partName = "czdh")
//	String czdh, @WebParam(partName = "znjl")
//	String znjl, @WebParam(partName = "zwdate")
//	String zwdate, @WebParam(partName = "ygjrs")
//	String ygjrs) {
//		String trace = "归集点修改";
//		try {
//
//			DbMethod.makeDbSingleton().Execute(
//					"ControlCenter_modifysetPointSet",
//					new SetPointDTO(bm, mc, txdz, khyh, yhzh, fzr, cwzg, cwjz,
//							cwsh, cwtz, yzbm, lxdh, czdh, znjl, zwdate, ygjrs));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>归集点修改出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	// ---------------------------
//	/**
//	 * 银行编码添加
//	 * 
//	 * @param bm,mc,txdz,khyh,yhzh,fzr,cwzg,cwjz,cwsh,cwtz,yzbm,lxdh,czdh
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "BankEncodingDTOArray")
//	public int add_bankEncodingSet(@WebParam(partName = "bm")
//	String bm, @WebParam(partName = "mc")
//	String mc, @WebParam(partName = "gjdbm")
//	String gjdbm, @WebParam(partName = "jgbm")
//	String jbbm, @WebParam(partName = "txip")
//	String txip, @WebParam(partName = "txdkh")
//	String txdkh
//
//	) {
//		String trace = "银行编码添加";
//		try {
//
//			DbMethod.makeDbSingleton().Execute(
//					"ControlCenter_addbankEncodingSet",
//					new BankEncodingDTO(bm, mc, gjdbm, jbbm, txip, txdkh));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>银行编码添加出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	/**
//	 * 银行编码删除
//	 * 
//	 * @param hth
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "BankEncodingDTOArray")
//	public int del_bankEncodingSet(@WebParam(partName = "bm")
//	String bm) {
//		String trace = "银行编码删除";
//		try {
//
//			DbMethod.makeDbSingleton()
//					.Execute("ControlCenter_delbankEncodingSet",
//							new BankEncodingDTO(bm));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>银行编码删除出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	// ---------------------------
//	/**
//	 * 银行编码修改
//	 * 
//	 * @param bm,mc,txdz,khyh,yhzh,fzr,cwzg,cwjz,cwsh,cwtz,yzbm,lxdh,czdh
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "BankEncodingDTOArray")
//	public int modify_bankEncodingSet(@WebParam(partName = "bm")
//	String bm, @WebParam(partName = "mc")
//	String mc, @WebParam(partName = "gjdbm")
//	String gjdbm, @WebParam(partName = "jgbm")
//	String jbbm, @WebParam(partName = "txip")
//	String txip, @WebParam(partName = "txdkh")
//	String txdkh) {
//		String trace = "银行编码修改";
//		try {
//
//			DbMethod.makeDbSingleton().Execute(
//					"ControlCenter_modifybankEncodingSet",
//					new BankEncodingDTO(bm, mc, gjdbm, jbbm, txip, txdkh));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>银行编码修改出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	/**
//	 * 归集点设置
//	 * 
//	 * @param certification
//	 *            口令
//	 * @return type
//	 */
//	@WebMethod
//	@WebResult(partName = "SetPointDTOArray")
//	public BankEncodingDTO[] bankEncodingSet(
//
//	) {
//		String trace = "银行编码设置查看";
//		BankEncodingDTO[] cbs = null;
//		BankEncodingDTO[] cbs1 = null;
//		BankEncodingDTO[] cbs2 = null;
//		try {
//
//			List<Row> lst = DbMethod.makeDbSingleton().Open(
//					"ControlCenter_bankEncodingSet", null);
//
//			cbs = new BankEncodingDTO[lst.size()];
//			cbs2 = new BankEncodingDTO[lst.size()];
//			int i = 0;
//			int j = 0;
//			for (Row row : lst) {
//				BankEncodingDTO cb = new BankEncodingDTO();
//				cb.setBm(row.getTrimString("bm"));
//				cb.setMc(row.getTrimString("mc"));
//				cb.setGjdbm(row.getTrimString("gjdbm"));
//				cb.setJgbm(row.getTrimString("jgbm"));
//				cb.setTxdkh(row.getTrimString("txdkh"));
//				cb.setTxip(row.getTrimString("txip"));
//				cbs[i++] = cb;
//
//				cbs2[j] = cbs[j];
//				j++;
//
//			}
//
//			cbs1 = new BankEncodingDTO[j];
//			for (int k = 0; k < j; k++) {
//				cbs1[k] = cbs2[k];
//			}
//
//		} catch (Exception e) {
//			trace = "<font color='red'>银行编码设置：" + e.getMessage() + "</font>";
//			log.error(trace);
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return cbs1;
//	}
//
//	// ---------------------------
//	/**
//	 * 审批级别添加
//	 * 
//	 * @param bm,mc,txdz,khyh,yhzh,fzr,cwzg,cwjz,cwsh,cwtz,yzbm,lxdh,czdh
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "ExameGradeDTOArray")
//	public int add_exameGradeSet(@WebParam(partName = "bm")
//	String bm, @WebParam(partName = "mc")
//	String mc, @WebParam(partName = "spbl")
//	String spbl, @WebParam(partName = "spsx")
//	String spsx
//
//	) {
//		String trace = "审批级别添加";
//		try {
//
//			DbMethod.makeDbSingleton().Execute(
//					"ControlCenter_addexameGradeSet",
//					new ExameGradeDTO(bm, mc, spbl, spsx));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>审批级别添加出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	/**
//	 * 银行编码删除
//	 * 
//	 * @param hth
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "ExameGradeDTOArray")
//	public int del_exameGradeSet(@WebParam(partName = "bm")
//	String bm) {
//		String trace = "审批级别删除";
//		try {
//
//			DbMethod.makeDbSingleton().Execute(
//					"ControlCenter_delexameGradeSet", new ExameGradeDTO(bm));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>审批级别删除出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	// ---------------------------
//	/**
//	 * 审批级别修改
//	 * 
//	 * @param bm,mc,txdz,khyh,yhzh,fzr,cwzg,cwjz,cwsh,cwtz,yzbm,lxdh,czdh
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "ExameGradeDTOArray")
//	public int modify_exameGradeSet(@WebParam(partName = "bm")
//	String bm, @WebParam(partName = "mc")
//	String mc, @WebParam(partName = "gjdbm")
//	String spbl, @WebParam(partName = "jgbm")
//	String spsx) {
//		String trace = "审批级别修改";
//		try {
//
//			DbMethod.makeDbSingleton().Execute(
//					"ControlCenter_modifyexameGradeSet",
//					new ExameGradeDTO(bm, mc, spbl, spsx));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>审批级别修改出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	/**
//	 * 归集点设置
//	 * 
//	 * @param certification
//	 *            口令
//	 * @return type
//	 */
//	@WebMethod
//	@WebResult(partName = "ExameGradeDTOArray")
//	public ExameGradeDTO[] exameGradeSet(
//
//	) {
//		String trace = "审批级别设置查看";
//		ExameGradeDTO[] cbs = null;
//		ExameGradeDTO[] cbs1 = null;
//		ExameGradeDTO[] cbs2 = null;
//		try {
//
//			List<Row> lst = DbMethod.makeDbSingleton().Open(
//					"ControlCenter_exameGradeSet", null);
//
//			cbs = new ExameGradeDTO[lst.size()];
//			cbs2 = new ExameGradeDTO[lst.size()];
//			int i = 0;
//			int j = 0;
//			for (Row row : lst) {
//				ExameGradeDTO cb = new ExameGradeDTO();
//				cb.setBm(row.getTrimString("bm"));
//				cb.setMc(row.getTrimString("mc"));
//				cb.setSpbl(row.getTrimString("spbl"));
//				cb.setSpsx(row.getTrimString("spsx"));
//				cbs[i++] = cb;
//
//				cbs2[j] = cbs[j];
//				j++;
//
//			}
//
//			cbs1 = new ExameGradeDTO[j];
//			for (int k = 0; k < j; k++) {
//				cbs1[k] = cbs2[k];
//			}
//
//		} catch (Exception e) {
//			trace = "<font color='red'>银行编码设置：" + e.getMessage() + "</font>";
//			log.error(trace);
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return cbs1;
//	}
//
//	// ---------------------------------------------业务参数
//	// ---------------------------------------------业务参数修改
//	// ---------------------------------------------日常指标
//	// ---------------------------------------------系统参数
//	// ---------------------------
//	/**
//	 * 系统年限修改
//	 * 
//	 * @param bm,mc,txdz,khyh,yhzh,fzr,cwzg,cwjz,cwsh,cwtz,yzbm,lxdh,czdh
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "SystemYearlyDTOArray")
//	public int modify_systemYearlySet(@WebParam(partName = "begindate")
//	String begindate, @WebParam(partName = "zwdate")
//	String zwdate, @WebParam(partName = "enddate")
//	String enddate, @WebParam(partName = "zwclgn")
//	String zwclgn, @WebParam(partName = "jzbhsfsr")
//	String jzbhsfsr, @WebParam(partName = "ver")
//	String ver, @WebParam(partName = "captions")
//	String captions, @WebParam(partName = "dzzhcd")
//	String dzzhcd, @WebParam(partName = "ckzhcd")
//	String ckzhcd, @WebParam(partName = "yhzhcd")
//	String yhzhcd, @WebParam(partName = "dwbmcd")
//	String dwbmcd, @WebParam(partName = "zgzhcd")
//	String zgzhcd, @WebParam(partName = "htbhcd")
//	String htbhcd, @WebParam(partName = "dwbmtype")
//	String dwbmtype, @WebParam(partName = "htbhtype")
//	String htbhtype, @WebParam(partName = "bttype")
//	String bttype
//
//	) {
//		String trace = "系统年限";
//		try {
//
//			DbMethod.makeDbSingleton()
//					.Execute(
//							"ControlCenter_modifyexameGradeSet",
//							new SystemYearlyDTO(begindate, zwdate, enddate,
//									zwclgn, jzbhsfsr, ver, captions, dzzhcd,
//									ckzhcd, yhzhcd, dwbmcd, zgzhcd, htbhcd,
//									dwbmtype, htbhtype, bttype));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>系统年限修改出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	/**
//	 * 系统年限设置
//	 * 
//	 * @param certification
//	 *            口令
//	 * @return type
//	 */
//	@WebMethod
//	@WebResult(partName = "SystemYearlyDTOArray")
//	public SystemYearlyDTO[] systemYearly(
//
//	) {
//		String trace = "系统年限设置查看";
//		SystemYearlyDTO[] cbs = null;
//		SystemYearlyDTO[] cbs1 = null;
//		SystemYearlyDTO[] cbs2 = null;
//		try {
//
//			List<Row> lst = DbMethod.makeDbSingleton().Open(
//					"ControlCenter_systemYearlySet", null);
//
//			cbs = new SystemYearlyDTO[lst.size()];
//			cbs2 = new SystemYearlyDTO[lst.size()];
//			int i = 0;
//			int j = 0;
//			for (Row row : lst) {
//				SystemYearlyDTO cb = new SystemYearlyDTO();
//
//				cb.setBegindate(row.getTrimString("begindate"));
//				cb.setEnddate(row.getTrimString("enddate"));
//				cb.setZwdate(row.getTrimString("zwdate"));
//				cb.setZwclgn(row.getTrimString("zwclgn"));
//				cb.setJzbhsfsr(row.getTrimString("jzbhsfsr"));
//				cb.setVer(row.getTrimString("ver"));
//				cb.setCaptions(row.getTrimString("captions"));
//				cb.setDzzhcd(row.getTrimString("dzzhcd"));
//				cb.setCkzhcd(row.getTrimString("ckzhcd"));
//				cb.setYhzhcd(row.getTrimString("yhzhcd"));
//				cb.setDwbmcd(row.getTrimString("dwbmcd"));
//				cb.setZgzhcd(row.getTrimString("zgzhcd"));
//				cb.setHtbhcd(row.getTrimString("htbhcd"));
//				cb.setDwbmtype(row.getTrimString("dwbmtype"));
//				cb.setHtbhtype(row.getTrimString("htbhtype"));
//				cb.setBttype(row.getTrimString("bttype"));
//				;
//				cbs[i++] = cb;
//
//				cbs2[j] = cbs[j];
//				j++;
//
//			}
//
//			cbs1 = new SystemYearlyDTO[j];
//			for (int k = 0; k < j; k++) {
//				cbs1[k] = cbs2[k];
//			}
//
//		} catch (Exception e) {
//			trace = "<font color='red'>系统年限设置：" + e.getMessage() + "</font>";
//			log.error(trace);
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return cbs1;
//	}
//
//	/**
//	 * 借款利率查询
//	 * 
//	 * @param certification
//	 *            口令
//	 * @return type
//	 */
//	@WebMethod
//	@WebResult(partName = "BorrowRateDTOArray")
//	public BorrowRateDTO[] borrowRate() {
//		String trace = "借款利率查询查看";
//		BorrowRateDTO[] cbs = null;
//		BorrowRateDTO[] cbs1 = null;
//		BorrowRateDTO[] cbs2 = null;
//		try {
//
//			List<Row> lst = DbMethod.makeDbSingleton().Open(
//					"ControlCenter_borrowRateSet", null);
//
//			cbs = new BorrowRateDTO[lst.size()];
//			cbs2 = new BorrowRateDTO[lst.size()];
//			int i = 0;
//			int j = 0;
//			for (Row row : lst) {
//				BorrowRateDTO cb = new BorrowRateDTO();
//
//				cb.setDkqx(row.getTrimString("dkqx"));
//				cb.setJsrq(row.getTrimString("jsrq"));
//				cb.setJzlldx(row.getTrimString("jzlldx"));
//				cb.setKsrq(row.getTrimString("ksrq"));
//				cb.setKssj(row.getTrimString("kssj"));
//				cb.setLldx(row.getTrimString("lldx"));
//				cb.setLlid(row.getTrimString("llid"));
//				cb.setLllb(row.getTrimString("lllb"));
//				cb.setMaxlldx(row.getTrimString("maxlldx"));
//				cb.setMinlldx(row.getTrimString("minlldx"));
//				cb.setYhlldx(row.getTrimString("yhlldx"));
//
//				cbs[i++] = cb;
//				cbs2[j] = cbs[j];
//				j++;
//
//			}
//
//			cbs1 = new BorrowRateDTO[j];
//			for (int k = 0; k < j; k++) {
//				cbs1[k] = cbs2[k];
//			}
//
//		} catch (Exception e) {
//			trace = "<font color='red'>借款利率查询设置：" + e.getMessage() + "</font>";
//			log.error(trace);
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return cbs1;
//	}
//
//	// ---------------------------
//	/**
//	 * 借款利率添加
//	 * 
//	 * @param bm,mc,txdz,khyh,yhzh,fzr,cwzg,cwjz,cwsh,cwtz,yzbm,lxdh,czdh
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "BorrowRateDTOArray")
//	public int add_borrowRateSet(@WebParam(partName = "dkqx")
//	String dkqx, @WebParam(partName = "jsrq")
//	String jsrq, @WebParam(partName = "jzlldx")
//	String jzlldx, @WebParam(partName = "ksrq")
//	String ksrq, @WebParam(partName = "kssj")
//	String kssj, @WebParam(partName = "lldx")
//	String lldx,
//	// @WebParam(partName = "llid") String llid,
//			@WebParam(partName = "lllb")
//			String lllb, @WebParam(partName = "maxlldx")
//			String maxlldx, @WebParam(partName = "minlldx")
//			String minlldx, @WebParam(partName = "yhlldx")
//			String yhlldx
//
//	) {
//		String trace = "借款利率添加";
//		try {
//
//			DbMethod.makeDbSingleton().Execute(
//					"ControlCenter_addborrowRateSet",
//					new BorrowRateDTO(dkqx, jsrq, jzlldx, ksrq, kssj, lldx,
//							lllb, maxlldx, minlldx, yhlldx));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>借款利率添加出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	/**
//	 * 借款利率删除
//	 * 
//	 * @param hth
//	 * @return
//	 */
//	@WebMethod
//	@WebResult(partName = "BorrowRateDTOArray")
//	public int del_borrowRateDTOSet(@WebParam(partName = "llid")
//	String llid) {
//		String trace = "借款利率删除";
//		try {
//
//			DbMethod.makeDbSingleton().Execute(
//					"ControlCenter_delborrowRateSet", new BorrowRateDTO(llid));
//
//		} catch (Exception e) {
//			trace = "<font color='red'>借款利率删除出错：" + e.getMessage() + "</font>";
//			log.error(trace);
//			return -1;
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return 1;
//	}
//
//	/**
//	 * 借款利率查询
//	 * 
//	 * @param certification
//	 *            口令
//	 * @return type
//	 */
//	@WebMethod
//	@WebResult(partName = "SystemEncodingDTOArray")
//	public SystemEncodingDTO[] systemEncoding() {
//		String trace = "借款利率查询查看";
//		SystemEncodingDTO[] cbs = null;
//		SystemEncodingDTO[] cbs1 = null;
//		SystemEncodingDTO[] cbs2 = null;
//		try {
//
//			List<Row> lst = DbMethod.makeDbSingleton().Open(
//					"ControlCenter_systemEncodingSet", null);
//
//			cbs = new SystemEncodingDTO[lst.size()];
//			cbs2 = new SystemEncodingDTO[lst.size()];
//			int i = 0;
//			int j = 0;
//			for (Row row : lst) {
//				SystemEncodingDTO cb = new SystemEncodingDTO();
//				cb.setBm(row.getTrimString("bm"));
//				cb.setCs1(row.getTrimString("cs1"));
//				cb.setCs2(row.getTrimString("cs2"));
//				cb.setFwmj(row.getTrimString("fwmj"));
//				cb.setMc(row.getTrimString("mc"));
//				cb.setSf(row.getTrimString("sf"));
//
//				cbs[i++] = cb;
//				cbs2[j] = cbs[j];
//				j++;
//
//			}
//
//			cbs1 = new SystemEncodingDTO[j];
//			for (int k = 0; k < j; k++) {
//				cbs1[k] = cbs2[k];
//			}
//
//		} catch (Exception e) {
//			trace = "<font color='red'>借款利率查询设置：" + e.getMessage() + "</font>";
//			log.error(trace);
//		} finally {
//			DataServiceTraceInfo.getInstance().getTrace(trace,
//					DataServiceTraceInfo.TYPE.ControlCenter);
//		}
//		return cbs1;
//	}
//
//}
