package com.wasoft.dataserver.ws.da;

/**
 * web���񷢲��࣬���Ϊ@WebMethod�ķ�����Ϊ�����ṩ����ķ�������
 */
import java.io.File;
import java.util.*;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.WebMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wasoft.calldb.CallDbException;
import com.wasoft.calldb.DbMethod;
import com.wasoft.calldb.sql.NoSuchColumnException;
import com.wasoft.calldb.sql.Row;
import com.wasoft.calldb.sql.UnsupportedConversionException;
import com.wasoft.dataserver.ws.CommBase;
import com.wasoft.dataserver.ws.da.bean.*;
import com.wasoft.javabean.DataServiceTraceInfo;
import com.wasoft.dataserver.ws.da.bean.TxdajbxxBean;

@WebService(serviceName = "DaService", targetNamespace = "http://ws.da.wasoft.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Da extends CommBase {
	private static final Log log = LogFactory.getLog(Da.class);
	private static final String DIR_URL = "D:/HnWada/Weall Archive Application/WebRoot/tempImages";// �ڷ������ϱ���ͼƬ��ʱ�ļ��ľ���·��
	private static final String HTTP_URL = "http://10.230.1.151:8080/wada/tempImages/";
//	private static final String DIR_URL = "D:"+File.separator+"workplace"+File.separator+"Weall Archive Application BZ"+File.separator+"WebRoot"+File.separator+"tempImages";// �ڷ������ϱ���ͼƬ��ʱ�ļ��ľ���·��
//	private static final String HTTP_URL = "http:"+File.separator+File.separator+"localhost:8080"+File.separator+"wada"+File.separator+"tempImages"+File.separator;

	@WebMethod
	@WebResult(partName = "DagszBeanArray")
	public DagszBean[] ef_getDasAll() {
		String trace = "";
		DagszBean[] bean = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_SELEDASALL",
					null);
			bean = new DagszBean[lst.size()];
			int i = 0;
			for (Row row : lst) {
				DagszBean bean1 = new DagszBean();
				bean1.setDash(row.getTrimString("DASH"));
				bean[i++] = bean1;
			}
		} catch (Exception e) {
			trace = "<font color='red'>��ѯ�����ҳ���" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return bean;
	}

	@WebMethod
	@WebResult(partName = "int")
	public int ef_jnml_add(@WebParam(partName = "jnml") JnmlBean jnml) {
		int result = 1;
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_JNML_ADD", jnml);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ����Ŀ¼�ϴ�����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return result;
	}

	/**
	 * �����ļ��ϴ����÷��� by wl
	 * 
	 * @param bean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int file_upload(@WebParam(partName = "BlobBean") BlobBean bean) {
		System.out
		.println("+++++++++++++++++www��++++++++++++++++++++++++++++++++"
				+ bean.getP_id() + bean.getName());
		int result = 1;
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_FILE_UPLOAD", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�ļ��ϴ�����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return result;
	}

	@WebMethod
	@WebResult(partName = "DagszBeanArray")
	public DagszBean[] ef_getDagAll(@WebParam(partName = "bean") DagszBean bean2) {
		String trace = "";
		DagszBean[] bean = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_SELEDAGALL",
					bean2);
			bean = new DagszBean[lst.size()];
			int i = 0;
			for (Row row : lst) {
				DagszBean bean1 = new DagszBean();
				bean1.setDagh(row.getTrimString("GH"));
				bean[i++] = bean1;
			}
		} catch (Exception e) {
			trace = "<font color='red'>��ѯ���������" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return bean;
	}

	@WebMethod
	@WebResult(partName = "WslbBeanArray")
	public WslbBean[] ef_getFirstTree(
			@WebParam(partName = "bean") WslbBean bean2) {
		String trace = "";
		WslbBean[] bean = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GETFIRSTTREE",
					bean2);
			bean = new WslbBean[lst.size()];
			int i = 0;
			for (Row row : lst) {
				WslbBean bean1 = new WslbBean();
				bean1.setSecond(row.getTrimString("SECOND"));
				bean[i++] = bean1;
			}
		} catch (Exception e) {
			trace = "<font color='red'>��ѯ���������" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return bean;
	}

	@WebMethod
	@WebResult(partName = "WsTypeBeanArray")
	public WsTypeBean[] ef_getWsTypeFirstTree(
			@WebParam(partName = "bean") WsTypeBean bean2) {
		String trace = "";
		WsTypeBean[] bean = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open(
					"DA_GETWSTYPEFIRSTTREE", bean2);
			bean = new WsTypeBean[lst.size()];
			int i = 0;
			// ID,PID,TYPEID,MC
			for (Row row : lst) {
				WsTypeBean bean1 = new WsTypeBean();
				bean1.setId(Integer.valueOf(row.getTrimString("ID")));
				bean1.setPid(row.getTrimString("PID"));
				bean1.setTypeid(row.getTrimString("TYPEID"));
				bean1.setMc(row.getTrimString("MC"));
				bean[i++] = bean1;
			}
		} catch (Exception e) {
			trace = "<font color='red'>��ѯ���������" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return bean;
	}

	@WebMethod
	@WebResult(partName = "DagszBeanArray")
	public DagszBean[] ef_getDagh(@WebParam(partName = "dash") String dash) {
		String trace = "";
		DagszBean[] bean = null;
		DagszBean bean0 = new DagszBean();
		bean0.setDash(dash);
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_SELEDAS", dash);
			bean = new DagszBean[lst.size()];
			int i = 0;
			for (Row row : lst) {
				DagszBean bean1 = new DagszBean();
				bean1.setDagh(row.getTrimString("gh"));
				// bean1.setId(row.getLong("id"));
				bean[i++] = bean1;
			}
		} catch (Exception e) {
			trace = "<font color='red'>��ѯ�����ҳ���" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return bean;
	}

	/**
	 * ���������⵵���Ҳ�ѯ 2011- 2-23 XY
	 * 
	 * @param certification
	 *            ����
	 * @return type
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_(@WebParam(partName = "num") String num,
			@WebParam(partName = "bj") String bj) {
		String trace = "";
		XndaBean bean = new XndaBean();
		String str = "";
		bean.setNum(num);
		bean.setBj(bj);
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_SELEHOUSE",
					bean);
			for (Row row : lst) {
				if (bj.equals("dash")) {
					str += row.getString("gh") + ";";
				} else {
					str += row.getString("hh") + "";
				}
			}
		} catch (Exception e) {
			trace = "<font color='red'>��ѯ�������ã�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return str;
	}

	/**
	 * ���ӵ���ϵͳ ����Ԥ�鵵���鵵ע�����õķ��� 2010��9��7��
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_archive_chg(@WebParam(partName = "bean") DajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_ARCHIVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����鵵����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ������鵵���÷��� 2011��4��14�� wm
	 * 
	 * @param tblname
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_txarchive_chg(@WebParam(partName = "bean") TxdajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_TXARCHIVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ������鵵����:" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	@WebMethod
	@WebResult(partName = "long")
	public long ef_get_id(@WebParam(partName = "tblname") String tblname) {
		String trace = " ";
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GETID",
					new DajbxxBean(0, "", "", tblname));
			Row row = lst.get(0);
			try {
				return Long.parseLong(row.getTrimString("id"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NoSuchColumnException e) {
				e.printStackTrace();
			} catch (UnsupportedConversionException e) {
				e.printStackTrace();
			}

		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ������ȡidֵ����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return -1;
	}

	@WebMethod
	@WebResult(partName = "int")
	public int ef_archive_save(@WebParam(partName = "bean") DajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_ARCHIVE_SAVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	@WebMethod
	@WebResult(partName = "int")
	public int ef_ws_save(@WebParam(partName = "bean") WsdaBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_WS_SAVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ������������ķ��� 2011��3��7�� gjf
	 * 
	 * @param bean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_ts_save(@WebParam(partName = "bean") TsdaBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_TS_SAVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ���������鵵���õķ��� 2010��9��7�� wl
	 * 
	 * @param id
	 *            ,type
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "Int")
	public int ef_allDaGd_chg(@WebParam(partName = "bean") DajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_ARCHIVEALL", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ���������鵵����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ �����������ٵ��õķ��� 2010��9��7�� wl
	 * 
	 * @param id
	 *            ,type
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public int ef_da_destroy(@WebParam(partName = "id") String idString,
			@WebParam(partName = "xhr") String xhr,
			@WebParam(partName = "xhrbm") String xhrbm) {
		String trace = " ";
		DajbxxBean bean = new DajbxxBean();
		bean.setJsr(xhr);
		bean.setJsrbm(xhrbm);
		bean.setIdString(idString);
		try {
			DbMethod.makeDbSingleton().Execute("DA_DESTROY", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�������ٳ���" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ����ע�����õķ��� wl
	 * 
	 * @param
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_all_cancel(@WebParam(partName = "bean") DajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_CANCEL", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ����ע������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ����ע�����õķ��� jcl 2011-4-20
	 * 
	 * @param
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_all_txcancel(@WebParam(partName = "bean") DajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_TXCANCEL", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ����ע������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	// @WebMethod
	// @WebResult(partName = "PublicInfo")
	// public int ef_da_save(@WebParam(partName = "PublicInfo")
	// PublicInfo bean) {
	// String trace = " ";
	// // try {
	// // DbMethod.makeDbSingleton().Execute("DA_DELEARCHIVE", bean);
	// // } catch (CallDbException e) {
	// // trace = "<font color='red'>���ӵ���ϵͳɾ������" + e.getMessage()
	// // + "</font>";
	// // log.error(trace);
	// // return -1;
	// // } finally {
	// // DataServiceTraceInfo.getInstance().getTrace(trace,
	// // DataServiceTraceInfo.TYPE.Da);
	// // }
	// return 1;
	// }
	@WebMethod
	@WebResult(partName = "int")
	public int ef_init_archive(@WebParam(partName = "dass") int dass,
			@WebParam(partName = "dags") int dags,
			@WebParam(partName = "daps") int daps,
			@WebParam(partName = "dahs") int dahs) {
		String trace = " ";
		DagszBean bean = new DagszBean();
		bean.setDags(dags);
		bean.setDahs(dahs);
		bean.setDaps(daps);
		bean.setDass(dass);
		try {
			DbMethod.makeDbSingleton().Execute("DA_INIT_ARCHIVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ����ҳ�ʼ��ʧ�ܣ�" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ����ɾ����¼����������־ 2010��9��7�� wl
	 * 
	 * @param id
	 *            ,tblName
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public int ef_da_delete(@WebParam(partName = "id") String idString,
			@WebParam(partName = "tblnName") String tblName) {
		String trace = " ";
		PublicInfoBean bean = new PublicInfoBean();
		bean.setIdString(idString);
		bean.setTblName(tblName);
		try {
			DbMethod.makeDbSingleton().Execute("DA_DELEARCHIVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳɾ������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳ�ϴ����õķ��� wl
	 * 
	 * @param id
	 * @fileName
	 * @data
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_Archive_Clyx_Add(@WebParam(partName = "id") long id,
			@WebParam(partName = "fileName") String fileName,
			@WebParam(partName = "clyx") byte[] clyx) {
		String trace = "";
		try {
			System.out.println("upload.ws.dataLength====================>"
					+ clyx.length);
			DbMethod.makeDbSingleton().Execute("DA_ARCHIVE_ADD_BY_ID",
					new JnmlBean(id, fileName, clyx));
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳ�ϴ������������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳɨ��ͼƬ�ϴ����õķ��� 2010��9��14��
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_jnml_clyx_add(@WebParam(partName = "ygdh") String ygdh,
			@WebParam(partName = "clbm") String clbm,
			@WebParam(partName = "cl") String cl,
			@WebParam(partName = "clyx") byte[] clyx) {
		String trace = "";
		try {
			System.out.println("ִ��[ef_jnml_clyx_add]�ϴ�ͼƬ");
			System.out.println("upload.ws.dataLength====================>"
					+ clyx.length);
			DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
					new ImageScanBean(ygdh, clbm, cl, clyx));
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ�������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ �����鵵���õķ��� 2010��9��7��
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_dagd_chg(@WebParam(partName = "id") long id) {
		String trace = "���ӵ���ϵͳ�����鵵";
		String sql = "UPDATE EF_DAJBXX SET F_STATE='�鵵' WHERE ID=" + id;
		try {
			DbMethod.makeDbSingleton().Execute("Common_SETTABLE", sql);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����鵵����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ �����鵵���õķ��� 2010��9��7��
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_blob_add(@WebParam(partName = "filename") String filename,
			@WebParam(partName = "data") byte[] data) {
		String trace = "���ӵ���ϵͳ�����鵵";
		try {
			BlobBean blobbean = new BlobBean();
			blobbean.setData(data);
			blobbean.setName(filename);
			DbMethod.makeDbSingleton().Execute("DA_BLOB", blobbean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����鵵����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * wl ���ӵ���ϵͳ �����鵵���õķ��� 2010��9��7�� �����ļ��������ط���
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public BlobBean ef_blob_query(@WebParam(partName = "bean") BlobBean bean) {
		String trace = "���ӵ���ϵͳ�����鵵";
		try {
			DbMethod.makeDbSingleton().Execute("DA_BLOB_QUERY", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����鵵����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return bean;
	}

	@WebMethod
	@WebResult(partName = "StringArray")
	public String[] tqclbh() {
		String trace = "";
		String[] str = new String[2];
		BlobBean blobbean = new BlobBean();
		try {
			DbMethod.makeDbSingleton().Execute("DA_TQCLBH", blobbean);
			str[0] = blobbean.getName();
			str[1] = " ";
		} catch (CallDbException e) {
			trace = "";
			log.error(trace);
			str[0] = " ";
			str[1] = e.getMessage();
			return str;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return str;
	}

	@WebMethod
	@WebResult(partName = "StringArray")
	public String[] qybh(@WebParam(partName = "d055") String d055) {
		String trace = "";
		String[] str = new String[2];
		BlobBean blobbean = new BlobBean();
		blobbean.setName(d055);
		try {
			DbMethod.makeDbSingleton().Execute("DA_QYBH", blobbean);
			str[0] = blobbean.getName();
			str[1] = " ";
		} catch (CallDbException e) {
			trace = "";
			log.error(trace);
			str[0] = " ";
			str[1] = e.getMessage();
			return str;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return str;
	}

	/**
	 * ���������⵵���Ҳ�ѯ 2011-2-23 XY
	 * 
	 * @param certification
	 *            ����
	 * @return type
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_xnda_ch_dashquery() {
		String trace = "";
		XndaBean xnBean = new XndaBean();
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_DASH_QUERY",
					xnBean);
			if (list != null && list.size() > 0) {
				trace += "dash,";
				for (int i = 0; i < list.size(); i++) {
					Row row = list.get(i);
					trace += row.getTrimString("dash");
					if (i != list.size() - 1) {
						trace += ",";
					}
				}
			} else
				trace = "no";
		} catch (Exception e) {
			trace = "error";
			log.error(trace);
		}
		return trace;
	}

	/**
	 * ���⵵���Ҳ�ѯ ������ 2011-2-23 XY
	 * 
	 * @param certification
	 *            ����
	 * @return type
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_xnda_ch_ghquery(@WebParam(partName = "n") String n) {
		String trace = "";
		/*
		 * String[] array=new String[2]; array=n.split(","); String
		 * dash=array[1];
		 */
		String dash = n;
		XndaBean xnBean = new XndaBean();
		xnBean.setDash(dash);
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_GH_QUERY",
					xnBean);
			if (list != null && list.size() > 0) {
				trace += "gh,";
				for (int i = 0; i < list.size(); i++) {
					Row row = list.get(i);
					trace += row.getTrimString("gh");
					if (i != list.size() - 1) {
						trace += ",";
					}
				}
			} else
				trace = "no";
		} catch (Exception e) {
			trace = "error";
			log.error(trace);
		}
		return trace;
	}

	/**
	 * ���⵵���Ҳ�ѯ ������ 2011-2-23 XY
	 * 
	 * @param certification
	 * 
	 * @return type
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_xnda_ch_hhquery(@WebParam(partName = "n") String n) {
		String trace = "";
		String[] array = new String[2];
		array = n.split(",");
		String dash = array[0];
		String gh = array[1];

		XndaBean xnBean = new XndaBean();
		xnBean.setDash(dash);
		xnBean.setGh(gh);
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_HH_QUERY",
					xnBean);
			if (list != null && list.size() > 0) {
				trace += "hh,";
				for (int i = 0; i < list.size(); i++) {
					Row row = list.get(i);
					trace += row.getTrimString("hh");
					if (i != list.size() - 1) {
						trace += ",";
					}
				}
			} else
				trace = "no";
		} catch (Exception e) {
			trace = "error";
			log.error(trace);
		}
		return trace;
	}

	/**
	 * �����ţ����⵵���Ҳ�ѯ 2011-2-23 XY
	 * 
	 * @param certification
	 *            ����
	 * @return type
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_xnda_ch_dahquery(@WebParam(partName = "n") String n) {
		String trace = "";
		String[] array = new String[3];
		array = n.split(",");
		String dash = array[0];
		String gh = array[1];
		String hh = array[2];
		XndaBean xnBean = new XndaBean();
		xnBean.setDash(dash);
		xnBean.setGh(gh);
		xnBean.setHh(hh);
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_DAH_QUERY",
					xnBean);
			if (list != null && list.size() > 0) {
				trace += "dah,";
				for (int i = 0; i < list.size(); i++) {
					Row row = list.get(i);
					trace += row.getTrimString("dah");
					if (i != list.size() - 1) {
						trace += ",";
					}
				}
			} else
				trace = "no";
		} catch (Exception e) {
			trace = "error";
			log.error(trace);
		}
		System.out.print(trace);
		return trace;
	}

	/**
	 * �����ţ����⵵���Ҳ�ѯ 2011-2-23 xy
	 * 
	 * @param certification
	 *            ����
	 * @return type
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_xnda_ch_Arcquery(@WebParam(partName = "n") String n) {
		String trace = "";
		String[] array = new String[3];
		array = n.split(",");
		String dash = array[0];
		String gh = array[1];
		String hh = array[2];
		XndaBean xnBean = new XndaBean();
		xnBean.setDash(dash);
		xnBean.setGh(gh);
		xnBean.setHh(hh);
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_DAH_QUERY",
					xnBean);
			if (list != null && list.size() > 0) {
				trace += "dah,";
				for (int i = 0; i < list.size(); i++) {
					Row row = list.get(i);
					trace += row.getTrimString("dah");
					if (i != list.size() - 1) {
						trace += ",";
					}
				}
			} else
				trace = "no";
		} catch (Exception e) {
			trace = "error";
			log.error(trace);
		}
		System.out.print(trace);
		return trace;
	}

	// ���⵵���ҳ�ʼ�� 2011-5-31 wm
	@WebMethod
	public String DAINIT(InitDaBean initDaBean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_INIT", initDaBean);
		} catch (Exception e) {
			trace = e.getMessage();
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return trace;
	}

	// ������
	@WebMethod
	public DajbxxBean[] getDaJBXX(DajbxxBean bean) {
		String trace = "";
		DajbxxBean djb = null;
		DajbxxBean[] djbs = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_ZZBB", bean);
			djbs = new DajbxxBean[list.size()];
			System.out.println("������da��--------");
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				System.out.println(row.getColumnName(1)
						+ row.getTrimString("COUNT(*)"));
				djb = new DajbxxBean();
				djb.setF_type(row.getTrimString("F_TYPE"));
				djb.setBcnx(Integer.parseInt(row.getTrimString("COUNT(*)")));
				// System.out.println("������da��1--------"+row.getTrimString("F_TYPE"));
				djbs[i] = djb;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return djbs;
	}

	// �����ҳ�ʼ�� 2011-7-19 wm
	@WebMethod
	public String DASTYLE(DagszBean z) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_STYLE", z);
		} catch (Exception e) {
			trace = e.getMessage();
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}

		return trace;
	}

	// ���⵵����combox 2011-7-19 wm
	@WebMethod
	public DagszBean[] getDashcombox(@WebParam(partName = "bean") DagszBean bean) {
		String trace = "�����б�";
		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_COMBOX", bean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDash(row.getTrimString("DASH"));
			}
		} catch (Exception e) {
			trace = "<font color='red'>" + bean.getDash() + "�б��ѯ����"
					+ e.getMessage() + "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Zj);
		}
		return xn;
	}

	// ���⵵����combox 2011-7-19 wm
	@WebMethod
	public DagszBean[] getGhcombox(@WebParam(partName = "bean") DagszBean bean) {
		String trace = "�����б�";

		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton()
					.Open("DA_GHCOMBOX", bean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDagh(row.getTrimString("GH"));
			}
		} catch (Exception e) {
			trace = "<font color='red'>" + bean.getDash() + "�б��ѯ����"
					+ e.getMessage() + "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Zj);
		}
		return xn;
	}

	// ���⵵����combox 2011-7-19 wm
	@WebMethod
	public DagszBean[] getGhRowCombox(
			@WebParam(partName = "bean") DagszBean bean) {
		String trace = "�����б�";
		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GHROWCOMBOX",
					bean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDagrow(row.getTrimString("GROW"));
			}
		} catch (Exception e) {
			trace = "<font color='red'> EF_DAGSZ �б��ѯ����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Zj);
		}
		return xn;
	}

	// ���⵵����combox 2011-2-23 xy
	@WebMethod
	public DagszBean[] getHhCombox(@WebParam(partName = "bean") DagszBean bean) {
		String trace = "�����б�";
		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton()
					.Open("DA_HHCOMBOX", bean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDahh(row.getTrimString("HH"));
			}
		} catch (Exception e) {
			trace = "<font color='red'> EF_DAGSZ �б��ѯ����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Zj);
		}
		return xn;
	}

	// ���⵵����combox 2011-7-19 wm
	@WebMethod
	public DagszBean[] getDashcomboxX(
			@WebParam(partName = "bean") DagszBean daBean) {
		String trace = "�����б�";
		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_COMBOXX",
					daBean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDash(row.getTrimString("DASH"));
			}
		} catch (Exception e) {
			trace = "<font color='red'>" + "EF_DAGSZ �б��ѯ����"
					+ e.getMessage() + "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Zj);
		}
		return xn;
	}

	// ���⵵����combox 2011-7-19 wm
	@WebMethod
	public DagszBean[] getGhcomboxX(
			@WebParam(partName = "bean") DagszBean daBean) {
		String trace = "�����б�";
		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GHCOMBOXX",
					daBean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDagh(row.getTrimString("GH"));
			}
		} catch (Exception e) {
			trace = "<font color='red'>" + "EF_DAGSZ �б��ѯ����"
					+ e.getMessage() + "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Zj);
		}
		return xn;
	}
	
	// ���⵵������combox 2012-9-4 wm
	@WebMethod
	public DagszBean[] getPhComboxX(
			@WebParam(partName = "bean") DagszBean bean) {
		String trace = "�����б�";

		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_PHCOMBOXX",
					bean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDagrow(row.getTrimString("PH"));
			}
		} catch (Exception e) {
			trace = "<font color='red'> EF_DAGSZ �б��ѯ����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return xn;
	}

	// ���⵵����combox 2011-7-19 wm
	@WebMethod
	public DagszBean[] getGhRowComboxX(
			@WebParam(partName = "bean") DagszBean bean) {
		String trace = "�����б�";

		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GHROWCOMBOXX",
					bean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDagrow(row.getTrimString("GROW"));
			}
		} catch (Exception e) {
			trace = "<font color='red'> EF_DAGSZ �б��ѯ����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Zj);
		}
		return xn;
	}

	// ���⵵����combox 2011-7-19 wm
	@WebMethod
	public DagszBean[] getHhComboxX(@WebParam(partName = "bean") DagszBean bean) {
		String trace = "�����б�";
		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_HHCOMBOXX",
					bean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDahh(row.getTrimString("HH"));
			}
		} catch (Exception e) {
			trace = "<font color='red'> EF_DAGSZ �б��ѯ����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Zj);
		}
		return xn;
	}

	// ��ȡ�������еĻ�����Ϣ add by wm 2011-6-3
	@WebMethod
	public DajbxxBean[] getDAJBXXByHH(
			@WebParam(partName = "bean") DagszBean bean) {
		String trace = "";
		DajbxxBean[] daBean = null;
		try {
			List<Row> rows = DbMethod.makeDbSingleton().Open("DA_JBXXByHH",
					bean);
			if (rows != null && rows.size() > 0) {
				daBean = new DajbxxBean[rows.size()];

				Row row = null;

				for (int i = 0; i < rows.size(); i++) {

					row = rows.get(i);

					daBean[i] = new DajbxxBean();

					daBean[i].setId(row.getDefInt("ID"));

					daBean[i].setYgdh((row.getTrimString("ygdh")));

					daBean[i].setDah((row.getTrimString("dah")));

					daBean[i].setF_type((row.getTrimString("f_type")));

					daBean[i].setF_state((row.getTrimString("f_state")));

					daBean[i].setClfs(row.getDefInt("clfs"));

					daBean[i].setTjr((row.getTrimString("tjr")));

					daBean[i].setJsr((row.getTrimString("jsr")));

					daBean[i].setJsrq((row.getTrimString("jsrq")));

					daBean[i].setBmjb((row.getTrimString("bmjb")));

					daBean[i].setBcnx((row.getDefInt("bcnx")));

					daBean[i].setCfwz((row.getTrimString("cfwz")));

					daBean[i].setTjrq((row.getDateToString("tjrq")));

					daBean[i].setTjrbm(row.getTrimString("tjrbm"));
				}
			} else {
				daBean = new DajbxxBean[0];
			}
		} catch (Exception e) {
			trace = "<font color='red'> EF_DAJBXX �б��ѯ����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return daBean;

	}
	
	/**
	 * ���ӵ���ϵͳ ʵ����ĵ��õķ��� ���
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_StBorrow_chg(@WebParam(partName = "bean") JyjlBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_EFSTBORROW", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	// DA_ARCHIVE
	/**
	 * ���ӵ���ϵͳ ʵ����ĵ��õķ��� �黹
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_StGhBorrow_chg(@WebParam(partName = "bean") JyjlBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_EFSTGHBORROW", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����黹����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳɨ��ͼƬ�ϴ����õķ��� 2010��9��14��
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_jnml_clyx_addEntity(@WebParam(partName = "id") String id,
			@WebParam(partName = "clbm") String clbm,
			@WebParam(partName = "cl") String cl,
			@WebParam(partName = "clyx") byte[] clyx) {
		String trace = "";
		try {
			System.out.println("ִ��[ef_jnml_clyx_add]�ϴ�ͼƬ");
			System.out.println("upload.ws.dataLength====================>"
					+ clyx.length);
			DbMethod.makeDbSingleton().Execute(
					"DA_JNML_CLYX_ADD_BY_PROC_Entity",
					new ImageScanBean(id, clbm, cl, clyx));
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ�������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳɨ��ͼƬ�ϴ����õķ��� 2011��3��2�� by jcl ���벻��ȥ
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_jnml_clyx_addDestroy(@WebParam(partName = "id") String ygdh,
			@WebParam(partName = "clbm") String clbm,
			@WebParam(partName = "cl") String cl,
			@WebParam(partName = "clyx") byte[] clyx) {
		String trace = "";
		try {
			System.out.println("ִ��[ef_jnml_clyx_add]�ϴ�ͼƬ");
			System.out.println("upload.ws.dataLength====================>"
					+ clyx.length);
			DbMethod.makeDbSingleton().Execute(
					"DA_JNML_CLYX_ADD_BY_PROC_Destroy",
					new ImageScanBean(ygdh, clbm, cl, clyx));
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ�������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳɨ��ͼƬ���ص��õķ���
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public byte[] ef_jnml_clyx_queryEntity_tobyte(
			@WebParam(partName = "id") String id) {
		String trace = "";
		ImageScanBean isb = new ImageScanBean(id);
		byte[] clyx = null;
		try {
			DbMethod.makeDbSingleton()
					.Execute("DA_JNML_CLYX_QUERY_ENTITY", isb);
			clyx = isb.getClyx();
			if (clyx != null) {
				System.out.println("ִ��[ef_jnml_clyx_queryEntity_tobyte]����ͼƬ");
				System.out
						.println("download.ws.dataLength====================>"
								+ clyx.length);
			} else {
				throw new Exception(
						"ִ��[ef_jnml_clyx_queryEntity_tobyte]ͼƬ����ʧ��!");
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ���س���" + e.getMessage()
					+ "</font>";
			log.error(trace);
			clyx = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			clyx = null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return clyx;
	}

	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳɨ��ͼƬ���ص��õķ���
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_jnml_clyx_queryEntity(@WebParam(partName = "id") String id) {
		String trace = "";
		ImageScanBean isb = new ImageScanBean(id);
		byte[] clyx = null;
		String base64 = "";
		try {
			DbMethod.makeDbSingleton()
					.Execute("DA_JNML_CLYX_QUERY_ENTITY", isb);
			clyx = isb.getClyx();
			if (clyx != null) {
				System.out.println("ִ��[ef_jnml_clyx_query]����ͼƬ");
				System.out
						.println("download.ws.dataLength====================>"
								+ clyx.length);
				base64 = new sun.misc.BASE64Encoder().encode(clyx);
			} else {
				throw new Exception("ִ��[ef_jnml_clyx_query]ͼƬ����ʧ��!");
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ���س���" + e.getMessage()
					+ "</font>";
			log.error(trace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			base64 = "";
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return base64;
	}

	/**
	 * ������ʷ����ҵ����Ϣ�ύ
	 * 
	 * 2012��5��28�� add by HD
	 * 
	 * @param DkdaHrecordBean
	 *            daBean
	 * 
	 * @return String result
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_dklsda_commit(DkdaHrecordBean daBean) {

		String trace = "";

		daBean.setTjrq(ImageUploadBean.getData());
		
		daBean.setF_state("Ԥ�鵵");
		try {
			DbMethod.makeDbSingleton().Execute("DA_DKDAHR_SAVE", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_dklsda_commit]��������";
			log.error(trace);
			return e.getMessage();
		}

		return "success";
	}

	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳɨ��ͼƬ���ص��õķ��� jcl
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public byte[] ef_jnml_clyx_queryDestroy_tobyte(
			@WebParam(partName = "id") String id) {
		String trace = "";
		ImageScanBean isb = new ImageScanBean(id);
		byte[] clyx = null;
		try {
			DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_QUERY_DESTROY",
					isb);
			clyx = isb.getClyx();
			if (clyx != null) {
				System.out.println("ִ��[ef_jnml_clyx_queryDestroy_tobyte]����ͼƬ");
				System.out
						.println("download.ws.dataLength====================>"
								+ clyx.length);
			} else {
				throw new Exception(
						"ִ��[ef_jnml_clyx_queryDestroy_tobyte]ͼƬ����ʧ��!");
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ���س���" + e.getMessage()
					+ "</font>";
			log.error(trace);
			clyx = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			clyx = null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return clyx;
	}

	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳɨ��ͼƬ���ص��õķ��� jcl
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_jnml_clyx_queryDestroy(@WebParam(partName = "id") String id) {
		String trace = "";
		ImageScanBean isb = new ImageScanBean(id);
		byte[] clyx = null;
		String base64 = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_QUERY_DESTROY",
					isb);
			clyx = isb.getClyx();
			if (clyx != null) {
				System.out.println("ִ��[ef_jnml_clyx_query]����ͼƬ");
				System.out
						.println("download.ws.dataLength====================>"
								+ clyx.length);
				base64 = new sun.misc.BASE64Encoder().encode(clyx);
			} else {
				throw new Exception("ִ��[ef_jnml_clyx_query]ͼƬ����ʧ��!");
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ���س���" + e.getMessage()
					+ "</font>";
			log.error(trace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			base64 = "";
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return base64;
	}

	/**
	 * ��ѯEF_JNML�õ����Ϸ���
	 * 
	 * @param ygdh
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public int ef_getSelClfs(@WebParam(partName = "ygdh") String ygdh) {
		int num = -1;
		String trace = "";
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GETSELCLFS",
					ygdh);
			if (lst != null && lst.size() > 0) {
				Row row = (Row) lst.get(0);
				if (row.getDefInt("num") > 0) {
					num = row.getDefInt("num");
				}
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[DA_GETSELCLFS]��������";
			log.error(trace);
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * ���ӵ���ϵͳ ���������ƽ����õķ��� 2011��3��11�� gjf
	 * 
	 * @param id
	 *            ,type
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "Int")
	public int ef_allDaTransfer_chg(@WebParam(partName = "bean") DajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_TRANSFERALL", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ���������ƽ�����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	@WebMethod
	@WebResult(partName = "int")
	public int ef_zl_save(@WebParam(partName = "bean") ZldaBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_ZL_SAVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	// ����һ��Ŀ¼������ 2011-3-11 xy
	@WebMethod
	public XndaBean[] getYjmlCombox(@WebParam(partName = "XndaBean") XndaBean z) {
		String trace = "�����б�";
		String zd = "";
		XndaBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_YJMLCOMBOX", z);
			if ("YJML".equals(z.getBj())) {
				zd = "FIRST";
			}
			if ("EJML".equals(z.getBj())) {
				zd = "SECOND";
			}
			if ("SJML".equals(z.getBj())) {
				zd = "THIRD";
			}
			xn = new XndaBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				XndaBean comcx = new XndaBean();
				comcx.setDash(row.getTrimString(zd));
				xn[i] = comcx;
			}
		} catch (Exception e) {
			trace = "<font color='red'> EF_WSLB �б��ѯ����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Zj);
		}
		return xn;
	}

	/*
	 * Ӱ�񳷳����÷���
	 * 
	 * @param ID
	 * 
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_ArchiveCc_chg(@WebParam(partName = "ID") String ID) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_EFARCHIVECCCHG", ID);
		} catch (CallDbException e) {
			trace = "<font color='red'>Ӱ�񳷳����÷�������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	// jcl ��ǩ����
	@WebMethod
	@WebResult(partName = "int")
	public int ef_Panel_save(@WebParam(partName = "bean") DajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_PANEL_SAVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ������鵵��ǩ���� 2011��4��14��
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_Txpanel_save(@WebParam(partName = "bean") TxdajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_TXPANEL_SAVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ������������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);

		}
		return 1;

	}

	/**
	 * ���ӵ���ϵͳ �����˻ص��õķ��� 2011��3��21�� gjf
	 * 
	 * @param bean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "Int")
	public int ef_allDaReturnBack_chg(
			@WebParam(partName = "bean") DajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_RETURNBACKALL", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����˻س���" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���ӵ���ϵͳ ��ҵ���� ���� ����ɾ�����õķ��� 2011��3��24�� gjf
	 * 
	 * @param id
	 *            ,type
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public int ef_da_deall(@WebParam(partName = "bean") PublicInfoBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_DELALL", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�������ٳ���" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ɾ��δ�ύ�ĵ��� 2011.3.28 zy
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_da_del(DajbxxBean daBean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_DA_DEL", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_da_del]��������";
			log.error(trace);
			return e.getMessage();
		}
		return "ɾ���ɹ�";
	}

	/***
	 * ɾ������(ͨ�� ) 2011/3/29 daiht
	 * 
	 * @param bean
	 *            һ�㴫�����,һ������ID;
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public int ef_da_deall_ty(@WebParam(partName = "bean") PublicInfoBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_DELALL_TY", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�������ٳ���" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	@WebMethod
	@WebResult(partName = "String")
	public int ef_wslbxx(@WebParam(partName = "bean") PublicInfoBean bean,
			@WebParam(partName = "keyname") String keyname,
			@WebParam(partName = "keyname1") String keyname1,
			@WebParam(partName = "rank") String rank) {
		String trace = " ";
		Map map = new HashMap();
		map.put("bean", bean);
		map.put("keyname", keyname);
		map.put("keyname1", keyname1);
		map.put("rank", rank);
		try {
			DbMethod.makeDbSingleton().Execute("DA_WSLBXX", map);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�������ٳ���" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}
	
	@WebMethod
	@WebResult(partName = "String")
	public int ef_Wslbdel(@WebParam(partName = "bean") PublicInfoBean bean) {
		String trace = " ";
		System.out.println(bean.getIdString()+"------------");
		try {
			DbMethod.makeDbSingleton().Execute("DA_WSLBDEL", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�������ٳ���" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/**
	 * ���µ��� ��� 2011-04-02 gjf
	 * 
	 * @param bean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_rs_save(@WebParam(partName = "bean") RsdaBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_RS_SAVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	// 2011��4��18�� add by wm
	/**
	 * ��ѯ�˻ص�����Ϣ
	 */
	@WebMethod
	public DajbxxBean[] ef_thda_query_for_old_app(
			@WebParam(partName = "daBean") DaBean daBean) {

		List<Row> rows = null;

		// GjdaBean[] daBeanList = null;
		DajbxxBean[] daBeanList = null;
		try {

			rows = DbMethod.makeDbSingleton().Open("DA_THDA_QUERY_FOR_OLD_APP",
					daBean);

			if (rows != null && rows.size() > 0) {

				daBeanList = new DajbxxBean[rows.size()];

				Row row = null;

				for (int i = 0; i < rows.size(); i++) {

					row = rows.get(i);

					daBeanList[i] = new DajbxxBean();

					daBeanList[i].setYgdh((row.getTrimString("ygdh")));

					daBeanList[i].setDah((row.getTrimString("dah")));

					daBeanList[i].setF_type((row.getTrimString("f_type")));

					daBeanList[i].setF_state((row.getTrimString("f_state")));

					daBeanList[i].setClfs(row.getDefInt("clfs"));

					daBeanList[i].setTjr((row.getTrimString("tjr")));

					daBeanList[i].setJsr((row.getTrimString("jsr")));

					daBeanList[i].setBmjb((row.getTrimString("bmjb")));

					daBeanList[i].setBcnx((row.getDefInt("bcnx")));

					daBeanList[i].setCfwz((row.getTrimString("cfwz")));

					daBeanList[i].setTjrq((row.getDateToString("tjrq")));

					daBeanList[i].setThyy((row.getTrimString("thyy")));
				}
			} else {
				daBeanList = new DajbxxBean[0];
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return daBeanList;

	}

	/**
	 * ��������Ȩ֤�������� 2011��4��20�� add by wm
	 * 
	 * @param daBean
	 * @return
	 */
	@WebMethod
	public TxDkdaBean[] ef_txda_query_for_old_app(
			@WebParam(partName = "daBean") DaBean daBean) {
		List<Row> rows = null;

		// GjdaBean[] daBeanList = null;
		TxDkdaBean[] daBeanList = null;
		try {

			rows = DbMethod.makeDbSingleton().Open("DA_TxDA_QUERY_FOR_OLD_APP",
					daBean);

			if (rows != null && rows.size() > 0) {

				daBeanList = new TxDkdaBean[rows.size()];

				Row row = null;

				for (int i = 0; i < rows.size(); i++) {

					row = rows.get(i);

					daBeanList[i] = new TxDkdaBean();

					daBeanList[i].setYgdh((row.getTrimString("ygdh")));

					daBeanList[i].setDah((row.getTrimString("dah")));

					daBeanList[i].setF_type((row.getTrimString("f_type")));

					daBeanList[i].setF_state((row.getTrimString("f_state")));

					daBeanList[i].setClfs(row.getDefInt("clfs"));

					daBeanList[i].setTjr((row.getTrimString("tjr")));

					daBeanList[i].setJsr((row.getTrimString("jsr")));

					daBeanList[i].setJsrq((row.getTrimString("jsrq")));

					daBeanList[i].setBmjb((row.getTrimString("bmjb")));

					daBeanList[i].setBcnx((row.getDefInt("bcnx")));

					daBeanList[i].setCfwz((row.getTrimString("cfwz")));

					daBeanList[i].setTjrq((row.getDateToString("tjrq")));

					daBeanList[i].setTxqzh((row.getTrimString("txqzh")));

					daBeanList[i].setTxqzh((row.getTrimString("txqzh")));

					daBeanList[i].setQhr((row.getTrimString("qhr")));

					daBeanList[i].setQhrq((row.getDateToString("qhrq")));

					daBeanList[i].setThr((row.getTrimString("thr")));

					daBeanList[i].setBgyy((row.getTrimString("bgyy")));

					daBeanList[i].setBgrq((row.getDateToString("bgrq")));

					daBeanList[i].setBgr((row.getTrimString("bgr")));

				}
			} else {
				daBeanList = new TxDkdaBean[0];
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return daBeanList;

	}

	/**
	 * ��� ��־���� 2011-05-18 gjf
	 * 
	 * @param bean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "int")
	public int ef_rz_save(@WebParam(partName = "bean") RzBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_RZ_SAVE", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ�����������" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	// ���ݵ������Ͳ�ѯɨ����� 2011-7-7 add by wm
	@WebMethod
	public JnmlSzBean[] ef_getClmcByDalx(
			@WebParam(partName = "bean") DaSzJnmlBean bean) {
		List<Row> jnmlList = null;
		JnmlSzBean[] jnmlSzBean = null;
		try {
			jnmlList = DbMethod.makeDbSingleton().Open("DA_BM_JNMLSZByDalx",
					bean);
			jnmlSzBean = new JnmlSzBean[jnmlList.size()];
			for (int i = 0; i < jnmlList.size(); i++) {
				Row row = jnmlList.get(i);
				jnmlSzBean[i] = new JnmlSzBean();
				jnmlSzBean[i].setId(row.getLong("ID"));
				jnmlSzBean[i].setClbm(row.getTrimString("CLBM"));
				jnmlSzBean[i].setCl(row.getTrimString("CL"));
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jnmlSzBean;
	}

	// ɾ���������Ͷ�Ӧ�Ĳ���
	@WebMethod
	public int ef_DelCl(@WebParam(partName = "bean") DaSzJnmlBean bean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_DelCl", bean);
		} catch (CallDbException e) {
			trace += "<font color='red'>ɾ��������Ӧ���ϳ���: </font>" + e.getMessage();
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	// ���Ӳ������� 2011-7-7 add by wm
	@WebMethod
	@WebResult(partName = "int")
	public int ef_AddCl_jnml(@WebParam(partName = "bean") JnmlSzBean bean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_AddCL_JNML", bean);
		} catch (CallDbException e) {
			trace += "<font color='red'>���Ӳ������Ƴ���</font>" + e.getMessage();
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	// ��ѯ���еĲ�������
	@WebMethod
	public JnmlSzBean[] ef_GetAllCl(
			@WebParam(partName = "tableName") String tableName) {
		List<Row> jnmlList = null;
		JnmlSzBean[] jnmlSzBean = null;
		try {
			jnmlList = DbMethod.makeDbSingleton()
					.Open("DA_GetAllCl", tableName);
			jnmlSzBean = new JnmlSzBean[jnmlList.size()];
			for (int i = 0; i < jnmlList.size(); i++) {
				Row row = jnmlList.get(i);
				jnmlSzBean[i] = new JnmlSzBean();
				jnmlSzBean[i].setClbm(row.getTrimString("CLBM"));
				jnmlSzBean[i].setCl(row.getTrimString("CL"));
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jnmlSzBean;
	}
	
	@WebMethod
	public String ef_GetClbm(
			@WebParam(partName = "bean") ClbmBean clBean) {
		String clmb="";
		List<Row> clbmList=null;
		try{
			System.out.println(clBean.getTableName()+"--"+clBean.getCsz()+"--"+clBean.getGs());
			clbmList=DbMethod.makeDbSingleton().Open("DA_GetCLBM",clBean);
			Row row=clbmList.get(clbmList.size()-1);
			String clbm=row.getTrimString("SEQ");
			if(clbm.indexOf(".")!=-1){
				clbm=clbm.substring(0,clbm.indexOf("."));
			}
			int slength=clbm.length();
			int gs=clBean.getGs();
			if(slength>=gs){
				clmb=String.valueOf(clbm);
			}else{
				String q="0";
				int cz=gs-slength;
				for(int z=0;z<cz-1;z++){
					q=q+"0";
				}
				clmb=q+String.valueOf(clbm);
				System.out.println(clmb+"-----4444444-------");
			}
		}catch(Exception e){
			
		}
		return clmb;
	}
	
	@WebMethod
	public WslbBean[] ef_GetAllWslb(
			@WebParam(partName = "tableName") String tableName) {
		List<Row> jnmlList = null;
		WslbBean[] jnmlSzBean = null;
		try {
			jnmlList = DbMethod.makeDbSingleton()
					.Open("DA_GetAllWslb", tableName);
			jnmlSzBean = new WslbBean[jnmlList.size()];
			for (int i = 0; i < jnmlList.size(); i++) {
				Row row = jnmlList.get(i);
				jnmlSzBean[i] = new WslbBean();
				jnmlSzBean[i].setIdString(row.getTrimString("ID"));
				jnmlSzBean[i].setFirst(row.getTrimString("FIRST"));
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jnmlSzBean;
	}
	
	@WebMethod
	public WslbBean[] ef_GetAllWslb1(
			@WebParam(partName = "tableName") String tableName) {
		List<Row> jnmlList = null;
		WslbBean[] jnmlSzBean = null;
		try {
			jnmlList = DbMethod.makeDbSingleton()
					.Open("DA_GetAllWslb1", tableName);
			jnmlSzBean = new WslbBean[jnmlList.size()];
			for (int i = 0; i < jnmlList.size(); i++) {
				Row row = jnmlList.get(i);
				jnmlSzBean[i] = new WslbBean();
				jnmlSzBean[i].setIdString(row.getTrimString("ID"));
				jnmlSzBean[i].setSecond(row.getTrimString("SECOND"));
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jnmlSzBean;
	}
	

	// ��ѯ�������� 2011-7-8 add by wm
	@WebMethod
	public DagszBean[] ef_GetSelDept(
			@WebParam(partName = "tableName") String tableName) {
		List<Row> deptList = null;
		DagszBean[] dagszBean = null;
		try {
			deptList = DbMethod.makeDbSingleton().Open("DA_GetSelDept",
					tableName);
			dagszBean = new DagszBean[deptList.size()];
			for (int i = 0; i < deptList.size(); i++) {
				Row row = deptList.get(i);
				dagszBean[i] = new DagszBean();
				dagszBean[i].setId(Long.parseLong(row.getTrimString("ID")));
				dagszBean[i].setF_caption(row.getTrimString("F_CAPTION"));
			}
		} catch (Exception e) {
			
		}
		return dagszBean;
	}

		/**
	 * ��ѯ�����б�Ĺ��÷��� 2011-8-5 wl
	 * @param bean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "beanArray")
	public ComboxBean[] getCombox(@WebParam(partName = "bean") ComboxBean bean) {
		ComboxBean[] comboxBeanArray = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_ONLY_COMBOX",
					bean);
			comboxBeanArray = new ComboxBean[list.size()];
			ComboxBean comboxBean =null;
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				comboxBean = new ComboxBean();
				comboxBean.setDisplay(row.getTrimString("display"));
				comboxBean.setValue(row.getTrimString("value"));
				comboxBeanArray[i]= comboxBean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comboxBeanArray;
	}	
	/**
	 * 2011��7��14�� jcl
	 * 
	 * @param daBean
	 * @return
	 */
@WebMethod
	@WebResult(partName = "int")
	public DagszBean[] ef_select_all(@WebParam(partName = "bean")
	DajbxxBean bean) {
		DagszBean[] beans = null;
		List<Row> list = null;
		try {
			list = DbMethod.makeDbSingleton().Open("DA_Archive_All_P",bean);
			
			if (list != null && list.size() > 0) {
				beans = new DagszBean[list.size()];
				Row row = null;
				for (int i = 0; i < list.size(); i++) {
					row = list.get(i);
					beans[i] = new DagszBean();
					beans[i].setDash(row.getTrimString("dash"));
					beans[i].setDagh(row.getTrimString("gh"));
					beans[i].setGrow(row.getTrimString("grow"));
					beans[i].setDahh(row.getTrimString("hh"));
					beans[i].setDnum(Integer.parseInt(row.getTrimString("dno")));
				}
			}else {
				beans = new DagszBean[0];
				beans[0] = new DagszBean();
				beans[0].setGjdbm("��");
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beans;
	}
	

	// ��ѯ���еĵ�������
	@WebMethod
	public DaLxBean[] ef_getAllDalx(
			@WebParam(partName = "tableName") String tableName) {
		List<Row> dalxList = null;
		DaLxBean[] dalxBean = null;
		try {
			dalxList = DbMethod.makeDbSingleton().Open("DA_GetAllDalx",
					tableName);
			dalxBean = new DaLxBean[dalxList.size()];
			for (int i = 0; i < dalxList.size(); i++) {
				Row row = dalxList.get(i);
				dalxBean[i] = new DaLxBean();
				dalxBean[i].setName(row.getTrimString("Name"));
				dalxBean[i].setLxbz(row.getTrimString("LXBZ"));
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dalxBean;
	}

	@WebMethod
	public int ef_AddDaszJnml(@WebParam(partName = "bean") DaSzJnmlBean bean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_AddDaszJnml", bean);
		} catch (CallDbException e) {
			trace += "<font color='red'>ִ��[ef_AddDaszJnml]��������"
					+ e.getMessage();
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}

	/****************************** 2.0���� **********************************************/
	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳɨ��ͼƬ���ص��õķ��� 2010��9��29�� CHG BY HD
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_jnml_clyx_query(@WebParam(partName = "id") String id) {
		String trace = "";
		ImageScanBean isb = new ImageScanBean(id);
		byte[] clyx = null;
		String base64 = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_QUERY", isb);
			clyx = isb.getClyx();
			if (clyx != null) {
				System.out.println("ִ��[ef_jnml_clyx_query]����ͼƬ");
				System.out
						.println("download.ws.dataLength====================>"
								+ clyx.length);
				base64 = new sun.misc.BASE64Encoder().encode(clyx);
			} else {
				throw new Exception("ִ��[ef_jnml_clyx_query]ͼƬ����ʧ��!");
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ���س���" + e.getMessage()
					+ "</font>";
			log.error(trace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			base64 = "";
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return base64;
	}

	/**
	 * ���ӵ���ϵͳ ���ӵ���ϵͳɨ��ͼƬ���ص��õķ��� 2010��9��29�� CHG BY HD
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public byte[] ef_jnml_clyx_query_tobyte(@WebParam(partName = "id") String id) {
		String trace = "";
		ImageScanBean isb = new ImageScanBean(id);
		byte[] clyx = null;
		try {
			DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_QUERY", isb);
			clyx = isb.getClyx();
			if (clyx != null) {
				System.out.println("ִ��[ef_jnml_clyx_query_tobyte]����ͼƬ");
				System.out
						.println("download.ws.dataLength====================>"
								+ clyx.length);
			} else {
				throw new Exception("ִ��[ef_jnml_clyx_query_tobyte]ͼƬ����ʧ��!");
			
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ���س���" + e.getMessage()
					+ "</font>";
			System.out.print(e.getMessage());
			log.error(trace);
			clyx = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			clyx = null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return clyx;
	}

	/**
	 * ���ӵ���ϵͳ ����Ȩ֤����ɨ��ͼƬ���ص��õķ��� 2011��4��21�� ADD BY HD
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public byte[] ef_txjnml_clyx_query_tobyte(
			@WebParam(partName = "id") String id) {
		String trace = "";
		ImageScanBean isb = new ImageScanBean(id);
		byte[] clyx = null;
		try {
			DbMethod.makeDbSingleton().Execute("DA_TXJNML_CLYX_QUERY", isb);
			clyx = isb.getClyx();
			if (clyx != null) {
				System.out.println("ִ��[ef_txjnml_clyx_query_tobyte]����ͼƬ");
				System.out
						.println("download.ws.dataLength====================>"
								+ clyx.length);
			} else {
				throw new Exception("ִ��[ef_txjnml_clyx_query_tobyte]ͼƬ����ʧ��!");
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "<font color='red'>���ӵ���ϵͳͼƬ���س���" + e.getMessage()
					+ "</font>";
			log.error(trace);
			clyx = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			clyx = null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return clyx;
	}

	/**
	 * ����WebService jdk1.4�ͻ��˵ķ��� 2010��10��9��add by hd
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@WebMethod
	public DajbxxBean ef_dajbxx_query(@WebParam(partName = "id") long id) {
		String trace = "";
		DajbxxBean daBean = new DajbxxBean(id);
		List rows = null;
		Row row = null;
		try {
			rows = DbMethod.makeDbSingleton().Open("DA_DAJBXX_QUERY", daBean);
			if (rows != null && rows.size() > 0) {
				row = (Row) rows.get(0);
				daBean.setBcnx(row.getDefInt("BCNX"));
				daBean.setF_type(row.getTrimString("F_TYPE"));
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_dajbxx_query]��������";
			log.error(trace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return daBean;
	}

	/**
	 * 2.0ҵ��ϵͳ���õ�ҵ�񵵰����ӵķ���(ҵ�񵵰�ͨ��)
	 * 
	 * 2011��7��11�� add by HD
	 */
	@WebMethod
	public int ef_ywda_add_for_old_app(
			@WebParam(partName = "daBean") DajbxxBean daBean) {

		int r = -1;// ����ֵ
		
		// �жϵ���������Ϣ�����Ƿ��Ѿ�¼���˵�����Ϣ,���û�������ӵ���������Ϣ
		if (ef_dajbxx_check(daBean)) {
			boolean flag = false;
			String f_type = daBean.getF_type();
			System.out.println("��������"+f_type);
			// ���ݵ�����������ͬ��ҵ����Ϣ����������Ϣ
			if ("5".equals(f_type)) {
				flag = ef_lpda_add(daBean);
			} else if ("1".equals(f_type)) {
				flag = ef_dwda_add(daBean);
			} else if ("2".equals(f_type)) {
				flag = ef_gjda_add(daBean);
			} else if ("3".equals(f_type)) {
				flag = ef_zqda_add(daBean);
			} else if ("4".equals(f_type)) {
				flag = ef_dkda_add(daBean);
			}

			if (flag) {
				try {
					DbMethod.makeDbSingleton().Execute(
							"DA_JNML_CLYX_ADD_BY_PROC", daBean);
					r = 0;
				} catch (CallDbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ִ��[ef_ywda_add_for_old_app1]��������");
					return -1;
				}
			}

		} else {
			try {
				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						daBean);
				r = 0;
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_ywda_add_for_old_app2]��������");
				return -1;
			}
		}
		return r;
	}

	/*
	 * ����������Ϣ���� 2010��10��12�� add by HD
	 * 
	 * @param DajbxxBean daBean
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	private boolean ef_dajbxx_check(DajbxxBean daBean) {
		String trace = "";
		List rows = null;
		Row row = null;
		boolean flag = true;
		try {
			rows = DbMethod.makeDbSingleton().Open("DA_DAJBXX_CHECK", daBean);
			if (rows != null && rows.size() > 0) {
				row = (Row) rows.get(0);
				if (row.getDefInt("dasum") > 0) {
					flag = false;
				}
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[DA_DAJBXX_CHECK]��������";
			log.error(trace);
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * ���ӵ���ϵͳ 2.0ҵ��ϵͳɨ��ͼƬ����ȫ��ͼƬ���õķ��� 2011��05��30�� ADD BY HD
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public ImageScanBean[] ef_getDownload_FileAll(
			@WebParam(partName = "daBean") DajbxxBean daBean) {

		String trace = "";

		List<Row> rows = null;

		Row row = null;

		ImageScanBean[] image_downloadBeans = null;

		// ������ʱ�ļ���
		File tempDir = new File(DIR_URL);
		if (!tempDir.exists()) {
			tempDir.mkdir();
			System.out.println("tempDir make sucess");
		}
		// ɾ���ϴ����ص��ļ�
		if (tempDir.list().length > 0) {
			ImageUploadBean.cleanTempImages(tempDir);
		}

		try {
			System.out.println("111============================"+daBean.getYwlsh()) ;
			rows = DbMethod.makeDbSingleton().Open(
					"DA_JNML_CLYX_QUERY_FOR_OLD_APP", daBean);

			if (rows != null && rows.size() > 0) {

				image_downloadBeans = new ImageScanBean[rows.size()]; // ��ʼ��������Ķ���

				for (int i = 0; i < rows.size(); i++) {

					image_downloadBeans[i] = new ImageScanBean();

					row = rows.get(i);

					DajbxxBean daBeanTemp = new DajbxxBean();

					daBeanTemp.setIdString(row.getTrimString("id"));

					daBeanTemp.setYgdh(row.getTrimString("ygdh"));

					daBeanTemp.setClbm(row.getTrimString("clbm"));

					daBeanTemp.setCl(row.getTrimString("cl"));

					String url = getDownload_FileUrl(daBeanTemp); // �õ������ļ���url��ַ

					if (url.length() > 0) {

						image_downloadBeans[i].setUrl(url); // ���������ļ���url��ַ

						image_downloadBeans[i].setCl(row.getTrimString("cl"));// ���������ļ��ķ�������

						image_downloadBeans[i].setClbm(row
								.getTrimString("clbm")); // ���������ļ��ķ������
						
						image_downloadBeans[i].setF_type(row
								.getTrimString("f_type")); // ���������ļ��ĵ�������
					}
				}

			} else {
				image_downloadBeans = new ImageScanBean[0];
			}
		} catch (CallDbException e) {
			trace = "ִ��[ef_get_Download_FileAll]����";
			log.error(trace);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return image_downloadBeans;
	}

	/**
	 * ҵ��ϵͳ����˫���鿴����ʹ,���ô˷�������ʱ�ļ�������dataservice���ڷ������� 2011.4.26 zy
	 * 
	 * 
	 * 2011��5��30�� chg by HD
	 * 
	 * @param DajbxxBean
	 * @return
	 */

	@WebMethod
	public String getDownload_FileUrl(
			@WebParam(partName = "daBean") DajbxxBean daBean) {
		ImageUploadBean iub = new ImageUploadBean(DIR_URL);
		int b = 0;
		String url = "";
		try {
			daBean.setClyx(ef_jnml_clyx_query_tobyte(daBean.getIdString()));
			b = iub.getDownloadFileUrl(daBean.getClyx(), daBean.getYgdh(),
					daBean.getIdString(), daBean.getClbm());
			if (b == 1) {
				// url��ַ
				url = HTTP_URL + iub.getTempfile();// ָ������ͼƬ·����url��ַ
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * ���˹鼯�����Ĳ��ϸ��õ������֧ȡҵ����
	 * 
	 * 2011��7��12�� add by HD
	 * 
	 * 2012��3��21�� chg by HD
	 * 
	 * @param daBean
	 * @return
	 */

	@WebMethod
	public String ef_gjda_to_yw_for_old_app(
			@WebParam(partName = "daBean") DajbxxBean daBean) {

		String trace = "";
		
		daBean.setTjrq(ImageUploadBean.getData());

		String idString = daBean.getIdString();

		String jnml_id[] = idString.split(",");

		if (jnml_id == null || jnml_id.length <= 0) {
			
			return "û����Ҫ����Ĳ���!";
		
		} else {
			
			for (int i = 0; i < jnml_id.length; i++) {

				long id = Long.parseLong(jnml_id[i]);

				daBean.setId(id);

				try {
					DbMethod.makeDbSingleton().Execute("DA_GJDA_TO_YW", daBean);
				} catch (CallDbException e) {
					// TODO Auto-generated catch block
					trace = "ִ��[ef_gjda_to_yw_for_old_app]��������";
					log.error(trace);
					return e.getMessage();
				}

			}

			return "success";
		}

	}

	/**
	 * �����������Ϣ���� 2011��4��20�� add by HD
	 * 
	 * @param TxdajbxxBean
	 *            daBean
	 * @return
	 */

	@SuppressWarnings("unchecked")
	private boolean ef_txdajbxx_check(TxdajbxxBean daBean) {
		String trace = "";
		List rows = null;
		Row row = null;
		boolean flag = true;
		try {
			rows = DbMethod.makeDbSingleton().Open("DA_TXDAJBXX_CHECK", daBean);
			if (rows != null && rows.size() > 0) {
				row = (Row) rows.get(0);
				if (row.getDefInt("dasum") > 0) {
					flag = false;
				}
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_txdajbxx_check]��������";
			log.error(trace);
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * ����������Ϣ���� 2011��4��20�� add by HD
	 * 
	 * @param TxdajbxxBean
	 *            daBean
	 * @return
	 */

	@SuppressWarnings("unchecked")
	private boolean ef_dajbxx_check_for_txda(TxdajbxxBean daBean) {
		String trace = "";
		List rows = null;
		Row row = null;
		boolean flag = false;
		try {
			rows = DbMethod.makeDbSingleton().Open("DA_DAJBXX_CHECK_FOR_TXDA",
					daBean);
			if (rows != null && rows.size() > 0) {
				row = (Row) rows.get(0);
				if (row.getDefInt("dasum") > 0) {
					flag = true;
				}
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_dajbxx_check_for_txda]��������";
			log.error(trace);
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * ����Ȩ֤�������� 2011��4��20�� add by HD
	 * 
	 * @param TxdajbxxBean
	 *            daBean
	 * @return
	 */
	private boolean ef_txdkda_add(TxdajbxxBean daBean) {
		String trace = "";
		boolean flag = true;
		try {
			DbMethod.makeDbSingleton().Execute("DA_TXDKDA_ADD", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_txdkda_add]��������";
			log.error(trace);
			flag = false;
		}
		return flag;
	}

	/**
	 * ��������� 2010��10��12�� add by HD
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	private boolean ef_dkda_add(DajbxxBean daBean) {
		String trace = "";
		boolean flag = true;
		try {
			DbMethod.makeDbSingleton().Execute("DA_DKDA_ADD", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_dkda_add]��������";
			log.error(trace);
			flag = false;
		}
		return flag;
	}

	/**
	 * ������ύ 2010��10��15�� add by HD
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_dkda_chg(DkdaBean daBean) {
		String trace = "";
		daBean.setTjrq(ImageUploadBean.getData()); // add by wm 2011.5.11
		try {
			DbMethod.makeDbSingleton().Execute("DA_DKDA_CHG", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_dkda_chg]��������";
			log.error(trace);
			return e.getMessage();
		}
		return "success";
	}

	/**
	 * ��������� 2011��2��18�� add by HD
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	private boolean ef_gjda_add(DajbxxBean daBean) {
		String trace = "";
		boolean flag = true;
		try {
			DbMethod.makeDbSingleton().Execute("DA_GJDA_ADD", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_gjda_add]��������";
			log.error(trace);
			flag = false;
		}
		return flag;
	}

	/**
	 * ¥�̵������� 2011��3��7�� add by HD
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	private boolean ef_lpda_add(DajbxxBean daBean) {
		String trace = "";
		boolean flag = true;
		try {
			DbMethod.makeDbSingleton().Execute("DA_LPDA_ADD", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_lpda_add]��������";
			log.error(trace);
			flag = false;
		}
		return flag;
	}

	/**
	 * ¥�̵����ύ 2011��3��7�� add by HD
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_lpda_chg(LpdaBean daBean) {
		String trace = "";
		daBean.setTjrq(ImageUploadBean.getData()); // add by wm 2011.5.11
		try {
			DbMethod.makeDbSingleton().Execute("DA_LPDA_CHG", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_lpda_chg]��������";
			log.error(trace);
			return e.getMessage();
		}
		return "success";
	}

	/**
	 * ���˹鼯�����ύ 2011��2��16�� add by HD
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_gjda_chg(GjdaBean daBean) {
		String trace = "";
		daBean.setTjrq(ImageUploadBean.getData()); // add by wm 2011.5.11
		try {
			DbMethod.makeDbSingleton().Execute("DA_GJDA_CHG", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_gjda_chg]��������";
			log.error(trace);
			return e.getMessage();
		}
		return "success";
	}

	/**
	 * �鼯��λ�������� 2011��2��17�� add by HD
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	private boolean ef_dwda_add(DajbxxBean daBean) {
		String trace = "";
		boolean flag = true;
		try {
			DbMethod.makeDbSingleton().Execute("DA_DWDA_ADD", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_dwda_add]��������";
			log.error(trace);
			flag = false;
		}
		return flag;
	}

	/**
	 * �鼯��λ�����ύ 2011��2��16�� add by HD
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_dwda_chg(DwdaBean daBean) {
		String trace = "";
		daBean.setTjrq(ImageUploadBean.getData()); // add by wm 2011.5.11
		try {
			DbMethod.makeDbSingleton().Execute("DA_DWDA_CHG", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_dwda_chg]��������";
			log.error(trace);
			return e.getMessage();
		}
		return "success";
	}

	/**
	 * �鼯֧ȡ�������� 2011��2��21�� add by HD
	 * 
	 * @param DajbxxBean
	 *            daBean
	 * @return
	 */
	private boolean ef_zqda_add(DajbxxBean daBean) {
		String trace = "";
		boolean flag = true;
		try {
			DbMethod.makeDbSingleton().Execute("DA_ZQDA_ADD", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_zqda_add]��������";
			log.error(trace);
			flag = false;
		}
		return flag;
	}

	/**
	 * ���˹鼯�����ύ 2011��2��21�� add by HD
	 * 
	 * @param ZqdaBean
	 *            daBean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_zqda_chg(ZqdaBean daBean) {
		String trace = "";
		daBean.setTjrq(ImageUploadBean.getData()); // add by wm 2011.5.11
		try {
			DbMethod.makeDbSingleton().Execute("DA_ZQDA_CHG", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_zqda_chg]��������";
			log.error(trace);
			return e.getMessage();
		}
		return "success";
	}

	/**
	 * 2.0����ϵͳ���õĵ������ӵķ���
	 */
	@WebMethod
	public int ef_dkda_add_for_old_app(
			@WebParam(partName = "daBean") DajbxxBean daBean) {

		String ygdh = daBean.getYgdh();
		String clbm = daBean.getClbm();
		String cl = daBean.getCl();
		byte[] clyx = daBean.getClyx();

		// �жϵ���������Ϣ�����Ƿ��Ѿ�¼���˵�����Ϣ,���û�������ӵ���������Ϣ
		if (ef_dajbxx_check(daBean)) {
			if (ef_dkda_add(daBean)) {
				try {
					DbMethod.makeDbSingleton().Execute(
							"DA_JNML_CLYX_ADD_BY_PROC",
							new ImageScanBean(ygdh, clbm, cl, clyx));
				} catch (CallDbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ִ��[ef_dkda_add_for_old_app1]��������");
					return -1;
				}
			}
		} else {
			try {
				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						new ImageScanBean(ygdh, clbm, cl, clyx));
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_dkda_add_for_old_app2]��������");
				return -1;
			}
		}
		return 0;
	}

	/**
	 * 2.0����ϵͳ���õ�����Ȩ֤�������ӵķ���
	 */
	@WebMethod
	public int ef_txda_add_for_old_app(
			@WebParam(partName = "daBean") TxdajbxxBean daBean) {

		String ygdh = daBean.getYgdh();
		String clbm = daBean.getClbm();
		String cl = daBean.getCl();
		byte[] clyx = daBean.getClyx();
		daBean.setTjrq(ef_getWaterMark_Data());
		// �жϵ���������Ϣ�����Ƿ��Ѿ�¼������صĴ������Ϣ,���û�����������������
		if (ef_dajbxx_check_for_txda(daBean)) {
			// �ж������������Ϣ�����Ƿ��Ѿ�¼���˵�����Ϣ,���û�������ӵ���������Ϣ
			if (ef_txdajbxx_check(daBean)) {
				if (ef_txdkda_add(daBean)) {
					try {
						DbMethod.makeDbSingleton().Execute(
								"DA_TXJNML_CLYX_ADD_BY_PROC",
								new ImageScanBean(ygdh, clbm, cl, clyx));
					} catch (CallDbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("ִ��[ef_txda_add_for_old_app1]��������");
						return -1;
					}
				}
			} else {
				try {
					DbMethod.makeDbSingleton().Execute(
							"DA_TXJNML_CLYX_ADD_BY_PROC",
							new ImageScanBean(ygdh, clbm, cl, clyx));
				} catch (CallDbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ִ��[ef_txda_add_for_old_app2]��������");
					return -1;
				}
			}
			return 0;
		} else {
			System.out.println("û���ҵ������Ĵ������Ϣ!");
			return -1;
		}
	}

	/**
	 * 2.0����ϵͳ���õ�¥�̵������ӵķ��� 2011��3��7�� add by HD
	 */
	@WebMethod
	public int ef_lpda_add_for_old_app(
			@WebParam(partName = "daBean") DajbxxBean daBean) {

		String ygdh = daBean.getYgdh();
		String clbm = daBean.getClbm();
		String cl = daBean.getCl();
		byte[] clyx = daBean.getClyx();

		// �жϵ���������Ϣ�����Ƿ��Ѿ�¼���˵�����Ϣ,���û�������ӵ���������Ϣ
		if (ef_dajbxx_check(daBean)) {
			if (ef_lpda_add(daBean)) {
				try {
					DbMethod.makeDbSingleton().Execute(
							"DA_JNML_CLYX_ADD_BY_PROC",
							new ImageScanBean(ygdh, clbm, cl, clyx));
				} catch (CallDbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ִ��[ef_lpda_add_for_old_app1]��������");
					return -1;
				}
			}
		} else {
			try {
				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						new ImageScanBean(ygdh, clbm, cl, clyx));
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_lpda_add_for_old_app2]��������");
				return -1;
			}
		}
		return 0;
	}

	/**
	 * 2.0�鼯ϵͳ���õĸ��˹鼯�������ӵķ���
	 */
	@WebMethod
	public int ef_gjda_add_for_old_app(
			@WebParam(partName = "daBean") DajbxxBean daBean) {

		String ygdh = daBean.getYgdh();
		String clbm = daBean.getClbm();
		String cl = daBean.getCl();
		byte[] clyx = daBean.getClyx();

		// �жϵ���������Ϣ�����Ƿ��Ѿ�¼���˵�����Ϣ,���û�������ӵ���������Ϣ
		if (ef_dajbxx_check(daBean)) {
			if (ef_gjda_add(daBean)) {
				try {
					DbMethod.makeDbSingleton().Execute(
							"DA_JNML_CLYX_ADD_BY_PROC",
							new ImageScanBean(ygdh, clbm, cl, clyx));
				} catch (CallDbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ִ��[ef_gjda_add_for_old_app1]��������");
					return -1;
				}
			}
		} else {
			try {
				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						new ImageScanBean(ygdh, clbm, cl, clyx));
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_gjda_add_for_old_app2]��������");
				return -1;
			}
		}
		return 0;
	}

	/**
	 * 2.0�鼯ϵͳ���õĸ���֧ȡ�������ӵķ���
	 */
	@WebMethod
	public int ef_zqda_add_for_old_app(
			@WebParam(partName = "daBean") DajbxxBean daBean) {

		String ygdh = daBean.getYgdh();
		String clbm = daBean.getClbm();
		String cl = daBean.getCl();
		byte[] clyx = daBean.getClyx();

		// �жϵ���������Ϣ�����Ƿ��Ѿ�¼���˵�����Ϣ,���û�������ӵ���������Ϣ
		if (ef_dajbxx_check(daBean)) {
			if (ef_zqda_add(daBean)) {
				try {
					DbMethod.makeDbSingleton().Execute(
							"DA_JNML_CLYX_ADD_BY_PROC",
							new ImageScanBean(ygdh, clbm, cl, clyx));
				} catch (CallDbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ִ��[ef_zqda_add_for_old_app1]��������");
					return -1;
				}
			}
		} else {
			try {
				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						new ImageScanBean(ygdh, clbm, cl, clyx));
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_zqda_add_for_old_app2]��������");
				return -1;
			}
		}
		return 0;
	}

	/**
	 * 2.0�鼯ϵͳ���õĹ鼯��λ�������ӵķ���
	 */
	@WebMethod
	public int ef_dwda_add_for_old_app(
			@WebParam(partName = "daBean") DajbxxBean daBean) {

		String ygdh = daBean.getYgdh();
		String clbm = daBean.getClbm();
		String cl = daBean.getCl();
		byte[] clyx = daBean.getClyx();

		// �жϵ���������Ϣ�����Ƿ��Ѿ�¼���˵�����Ϣ,���û�������ӵ���������Ϣ
		if (ef_dajbxx_check(daBean)) {
			if (ef_dwda_add(daBean)) {
				try {
					DbMethod.makeDbSingleton().Execute(
							"DA_JNML_CLYX_ADD_BY_PROC",
							new ImageScanBean(ygdh, clbm, cl, clyx));
				} catch (CallDbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ִ��[ef_dwda_add_for_old_app1]��������");
					return -1;
				}
			}
		} else {
			try {
				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						new ImageScanBean(ygdh, clbm, cl, clyx));
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_dwda_add_for_old_app2]��������");
				return -1;
			}
		}
		return 0;
	}

	/**
	 * ���ӵ���ϵͳ 2.0ҵ��ϵͳɨ��ͼƬ���ص��õķ��� 2010��10��13�� ADD BY HD
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public DajbxxBean[] ef_jnml_query_for_old_app(
			@WebParam(partName = "daBean") DajbxxBean daBean) {
		String trace = "";
		List<Row> rows = null;
		Row row = null;
		DajbxxBean[] daBeanList = null;
		try {
			rows = DbMethod.makeDbSingleton().Open(
					"DA_JNML_CLYX_QUERY_FOR_OLD_APP", daBean);
			if (rows != null && rows.size() > 0) {
				daBeanList = new DajbxxBean[rows.size()];

				for (int i = 0; i < rows.size(); i++) {
					row = rows.get(i);
					daBeanList[i] = new DajbxxBean();
					daBeanList[i].setId(Long.valueOf(row.getTrimString("id")));
					daBeanList[i].setYgdh(row.getTrimString("ygdh"));
					daBeanList[i].setClbm(row.getTrimString("clbm"));
					daBeanList[i].setCl(row.getTrimString("cl"));
					// daBeanList[i].setClyx(ef_jnml_clyx_query_tobyte(row
					// .getTrimString("id")));
				}
			} else {
				daBeanList = new DajbxxBean[0];
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_jnml_clyx_query_for_old_app]����";
			log.error(trace);
			daBeanList = new DajbxxBean[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			daBeanList = new DajbxxBean[0];
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return daBeanList;
	}

	/**
	 * ���ӵ���ϵͳ 2.0ҵ��ϵͳ�����ɨ��ͼƬ���ص��õķ��� 2011��4��21�� ADD BY HD
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public TxdajbxxBean[] ef_txjnml_query_for_old_app(
			@WebParam(partName = "daBean") TxdajbxxBean daBean) {
		String trace = "";
		List<Row> rows = null;
		Row row = null;
		TxdajbxxBean[] daBeanList = null;
		try {
			rows = DbMethod.makeDbSingleton().Open(
					"DA_TXJNML_CLYX_QUERY_FOR_OLD_APP", daBean);
			if (rows != null && rows.size() > 0) {
				daBeanList = new TxdajbxxBean[rows.size()];

				for (int i = 0; i < rows.size(); i++) {
					row = rows.get(i);
					daBeanList[i] = new TxdajbxxBean();
					daBeanList[i].setId(Long.valueOf(row.getTrimString("id")));
					daBeanList[i].setYgdh(row.getTrimString("ygdh"));
					daBeanList[i].setClbm(row.getTrimString("clbm"));
					daBeanList[i].setCl(row.getTrimString("cl"));
					// daBeanList[i].setClyx(ef_jnml_clyx_query_tobyte(row
					// .getTrimString("id")));
				}
			} else {
				daBeanList = new TxdajbxxBean[0];
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_txjnml_clyx_query_for_old_app]����";
			log.error(trace);
			daBeanList = new TxdajbxxBean[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			daBeanList = new TxdajbxxBean[0];
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return daBeanList;
	}

	/**
	 * ��������ɨ�����ӵķ���
	 * 
	 * 2011��3��10�� add by HD
	 * 
	 * @param DaBean
	 * @return String
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_test_for_scan(@WebParam(partName = "dabean") DaBean dabean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_TEST_FOR_SCAN", dabean);
			trace = "succes";
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			trace = "ִ��[ef_test_for_scan]����ʧ��!";
		}
		return trace;
	}

	/**
	 * �����������Ϣ��ѯfor����ϵͳ2.0 2011��4��13�� add by HD
	 * 
	 * @param daBean
	 * @return
	 */

	@WebMethod
	public DkdaBean[] ef_dkda_query_for_old_app(
			@WebParam(partName = "daBean") DkdaBean daBean) {

		List<Row> rows = null;

		DkdaBean[] daBeanList = null;

		try {

			rows = DbMethod.makeDbSingleton().Open("DA_DKDA_QUERY_FOR_OLD_APP",
					daBean);

			if (rows != null && rows.size() > 0) {

				daBeanList = new DkdaBean[rows.size()];

				Row row = null;

				for (int i = 0; i < rows.size(); i++) {

					row = rows.get(i);

					daBeanList[i] = new DkdaBean();

					daBeanList[i].setCbwd(row.getTrimString("cbwd"));

					daBeanList[i].setGjd(row.getTrimString("gjd"));

					daBeanList[i].setDkhtbh(row.getTrimString("dkhtbh"));

					daBeanList[i].setDkhtrq(row.getDateToString("dkhtrq"));

					daBeanList[i].setFkyh(row.getTrimString("fkyh"));

					daBeanList[i].setFkrq(row.getDateToString("fkrq"));

					daBeanList[i].setZgxm(row.getTrimString("zgxm"));

					daBeanList[i].setZjhm(row.getTrimString("zjhm"));

					daBeanList[i].setGfxxdz(row.getTrimString("gfxxdz"));

					daBeanList[i].setDknx(row.getDefInt("dknx"));

					daBeanList[i].setClfs(row.getDefInt("clfs"));

					daBeanList[i].setDah((row.getTrimString("dah")));

					daBeanList[i].setYgdh((row.getTrimString("ygdh")));

					daBeanList[i].setF_state((row.getTrimString("f_state")));

					daBeanList[i].setF_type((row.getTrimString("f_type")));

					daBeanList[i].setTjr((row.getTrimString("tjr")));

					daBeanList[i].setTjrq((row.getDateToString("tjrq")));

					daBeanList[i].setJsr((row.getTrimString("jsr")));

					daBeanList[i].setJsrq((row.getDateToString("jsrq")));

					daBeanList[i].setBmjb((row.getTrimString("bmjb")));

					daBeanList[i].setBcnx((row.getDefInt("bcnx")));

					daBeanList[i].setCfwz((row.getTrimString("cfwz")));

				}
			} else {
				daBeanList = new DkdaBean[0];
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return daBeanList;

	}

	/**
	 * ¥�̵���������Ϣ��ѯfor����ϵͳ2.0 2011��4��13�� add by HD
	 * 
	 * @param daBean
	 * @return
	 */

	@WebMethod
	public LpdaBean[] ef_lpda_query_for_old_app(
			@WebParam(partName = "daBean") DaBean daBean) {

		List<Row> rows = null;

		LpdaBean[] daBeanList = null;

		try {

			rows = DbMethod.makeDbSingleton().Open("DA_LPDA_QUERY_FOR_OLD_APP",
					daBean);

			if (rows != null && rows.size() > 0) {

				daBeanList = new LpdaBean[rows.size()];

				Row row = null;

				for (int i = 0; i < rows.size(); i++) {

					row = rows.get(i);

					daBeanList[i] = new LpdaBean();

					daBeanList[i].setCbwd(row.getTrimString("cbwd"));

					daBeanList[i].setGjd(row.getTrimString("gjd"));

					// daBeanList[i].setDkhtbh(row.getTrimString("dkhtbh"));
					//
					// daBeanList[i].setDkhtrq(row.getDateToString("dkhtrq"));
					//
					// daBeanList[i].setFkyh(row.getTrimString("fkyh"));
					//
					// daBeanList[i].setFkrq(row.getDateToString("fkrq"));
					//
					// daBeanList[i].setZgxm(row.getTrimString("zgxm"));
					//
					// daBeanList[i].setZjhm(row.getTrimString("zjhm"));
					//
					// daBeanList[i].setGfxxdz(row.getTrimString("gfxxdz"));
					//
					// daBeanList[i].setDknx(row.getDefInt("dknx"));

					daBeanList[i].setClfs(row.getDefInt("clfs"));

					daBeanList[i].setDah((row.getTrimString("dah")));

					daBeanList[i].setYgdh((row.getTrimString("ygdh")));

					daBeanList[i].setF_state((row.getTrimString("f_state")));

					daBeanList[i].setTjr((row.getTrimString("tjr")));

					daBeanList[i].setTjrq((row.getDateToString("tjrq")));

					daBeanList[i].setJsr((row.getTrimString("jsr")));

					daBeanList[i].setJsrq((row.getDateToString("jsrq")));

					daBeanList[i].setBmjb((row.getTrimString("bmjb")));

					daBeanList[i].setBcnx((row.getDefInt("bcnx")));

					daBeanList[i].setCfwz((row.getTrimString("cfwz")));

				}
			} else {
				daBeanList = new LpdaBean[0];
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return daBeanList;

	}

	/**
	 * ���˹鼯����������Ϣ��ѯfor�鼯ϵͳ2.0 2011��4��13�� add by HD
	 * 
	 * @param daBean
	 * @return
	 */

	@WebMethod
	public GjdaBean[] ef_gjda_query_for_old_app(
			@WebParam(partName = "daBean") DaBean daBean) {

		List<Row> rows = null;

		GjdaBean[] daBeanList = null;

		try {

			rows = DbMethod.makeDbSingleton().Open("DA_GJDA_QUERY_FOR_OLD_APP",
					daBean);

			if (rows != null && rows.size() > 0) {

				daBeanList = new GjdaBean[rows.size()];

				Row row = null;

				for (int i = 0; i < rows.size(); i++) {

					row = rows.get(i);

					daBeanList[i] = new GjdaBean();

					daBeanList[i].setCbwd(row.getTrimString("cbwd"));

					daBeanList[i].setGjd(row.getTrimString("gjd"));

					// daBeanList[i].setDkhtbh(row.getTrimString("dkhtbh"));
					//
					// daBeanList[i].setDkhtrq(row.getDateToString("dkhtrq"));
					//
					// daBeanList[i].setFkyh(row.getTrimString("fkyh"));
					//
					// daBeanList[i].setFkrq(row.getDateToString("fkrq"));
					//
					// daBeanList[i].setZgxm(row.getTrimString("zgxm"));
					//
					// daBeanList[i].setZjhm(row.getTrimString("zjhm"));
					//
					// daBeanList[i].setGfxxdz(row.getTrimString("gfxxdz"));
					//
					// daBeanList[i].setDknx(row.getDefInt("dknx"));

					daBeanList[i].setClfs(row.getDefInt("clfs"));

					daBeanList[i].setDah((row.getTrimString("dah")));

					daBeanList[i].setYgdh((row.getTrimString("ygdh")));

					daBeanList[i].setF_state((row.getTrimString("f_state")));

					daBeanList[i].setTjr((row.getTrimString("tjr")));

					daBeanList[i].setTjrq((row.getDateToString("tjrq")));

					daBeanList[i].setJsr((row.getTrimString("jsr")));

					daBeanList[i].setJsrq((row.getDateToString("jsrq")));

					daBeanList[i].setBmjb((row.getTrimString("bmjb")));

					daBeanList[i].setBcnx((row.getDefInt("bcnx")));

					daBeanList[i].setCfwz((row.getTrimString("cfwz")));

				}
			} else {
				daBeanList = new GjdaBean[0];
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return daBeanList;

	}

	/**
	 * �鼯��λ����������Ϣ��ѯfor�鼯ϵͳ2.0 2011��4��13�� add by HD
	 * 
	 * @param daBean
	 * @return
	 */
	@WebMethod
	public DwdaBean[] ef_dwda_query_for_old_app(
			@WebParam(partName = "daBean") DaBean daBean) {

		List<Row> rows = null;

		DwdaBean[] daBeanList = null;

		try {

			rows = DbMethod.makeDbSingleton().Open("DA_DWDA_QUERY_FOR_OLD_APP",
					daBean);

			if (rows != null && rows.size() > 0) {

				daBeanList = new DwdaBean[rows.size()];

				Row row = null;

				for (int i = 0; i < rows.size(); i++) {

					row = rows.get(i);

					daBeanList[i] = new DwdaBean();

					daBeanList[i].setCbwd(row.getTrimString("cbwd"));

					daBeanList[i].setGjd(row.getTrimString("gjd"));

					// daBeanList[i].setDkhtbh(row.getTrimString("dkhtbh"));
					//
					// daBeanList[i].setDkhtrq(row.getDateToString("dkhtrq"));
					//
					// daBeanList[i].setFkyh(row.getTrimString("fkyh"));
					//
					// daBeanList[i].setFkrq(row.getDateToString("fkrq"));
					//
					// daBeanList[i].setZgxm(row.getTrimString("zgxm"));
					//
					// daBeanList[i].setZjhm(row.getTrimString("zjhm"));
					//
					// daBeanList[i].setGfxxdz(row.getTrimString("gfxxdz"));
					//
					// daBeanList[i].setDknx(row.getDefInt("dknx"));

					daBeanList[i].setClfs(row.getDefInt("clfs"));

					daBeanList[i].setDah((row.getTrimString("dah")));

					daBeanList[i].setYgdh((row.getTrimString("ygdh")));

					daBeanList[i].setF_state((row.getTrimString("f_state")));

					daBeanList[i].setTjr((row.getTrimString("tjr")));

					daBeanList[i].setTjrq((row.getDateToString("tjrq")));

					daBeanList[i].setJsr((row.getTrimString("jsr")));

					daBeanList[i].setJsrq((row.getDateToString("jsrq")));

					daBeanList[i].setBmjb((row.getTrimString("bmjb")));

					daBeanList[i].setBcnx((row.getDefInt("bcnx")));

					daBeanList[i].setCfwz((row.getTrimString("cfwz")));

				}
			} else {
				daBeanList = new DwdaBean[0];
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return daBeanList;

	}

	/**
	 * ֧ȡ����������Ϣ��ѯfor�鼯ϵͳ2.0 2011��4��13�� add by HD
	 * 
	 * @param daBean
	 * @return
	 */

	@WebMethod
	public ZqdaBean[] ef_zqda_query_for_old_app(
			@WebParam(partName = "daBean") DaBean daBean) {

		List<Row> rows = null;

		ZqdaBean[] daBeanList = null;

		try {

			rows = DbMethod.makeDbSingleton().Open("DA_ZQDA_QUERY_FOR_OLD_APP",
					daBean);

			if (rows != null && rows.size() > 0) {

				daBeanList = new ZqdaBean[rows.size()];

				Row row = null;

				for (int i = 0; i < rows.size(); i++) {

					row = rows.get(i);

					daBeanList[i] = new ZqdaBean();

					daBeanList[i].setCbwd(row.getTrimString("cbwd"));

					daBeanList[i].setGjd(row.getTrimString("gjd"));

					// daBeanList[i].setDkhtbh(row.getTrimString("dkhtbh"));
					//
					// daBeanList[i].setDkhtrq(row.getDateToString("dkhtrq"));
					//
					// daBeanList[i].setFkyh(row.getTrimString("fkyh"));
					//
					// daBeanList[i].setFkrq(row.getDateToString("fkrq"));
					//
					// daBeanList[i].setZgxm(row.getTrimString("zgxm"));
					//
					// daBeanList[i].setZjhm(row.getTrimString("zjhm"));
					//
					// daBeanList[i].setGfxxdz(row.getTrimString("gfxxdz"));
					//
					// daBeanList[i].setDknx(row.getDefInt("dknx"));

					daBeanList[i].setClfs(row.getDefInt("clfs"));

					daBeanList[i].setDah((row.getTrimString("dah")));

					daBeanList[i].setYgdh((row.getTrimString("ygdh")));

					daBeanList[i].setF_state((row.getTrimString("f_state")));

					daBeanList[i].setTjr((row.getTrimString("tjr")));

					daBeanList[i].setTjrq((row.getDateToString("tjrq")));

					daBeanList[i].setJsr((row.getTrimString("jsr")));

					daBeanList[i].setJsrq((row.getDateToString("jsrq")));

					daBeanList[i].setBmjb((row.getTrimString("bmjb")));

					daBeanList[i].setBcnx((row.getDefInt("bcnx")));

					daBeanList[i].setCfwz((row.getTrimString("cfwz")));

				}
			} else {
				daBeanList = new ZqdaBean[0];
			}
		} catch (CallDbException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			e.printStackTrace();
		}

		return daBeanList;

	}

	/**
	 * ҵ��ϵͳ����˫���鿴����ʹ���ô˷�������ʱ�ļ�������dataservice���ڷ������� 2011.4.26 zy
	 * 
	 * @param DajbxxBean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_get_Download_FileUrl(
			@WebParam(partName = "daBean") DajbxxBean daBean) {
		ImageUploadBean iub = new ImageUploadBean(DIR_URL);
		int b = 0;
		String url = "";
		try {
			daBean.setClyx(ef_jnml_clyx_query_tobyte(daBean.getIdString()));
			b = iub.getDownloadFileUrl(daBean.getClyx(), daBean.getYgdh(),
					daBean.getIdString(), daBean.getClbm());
			if (b == 1) {
				// url��ַ
				url = HTTP_URL + iub.getTempfile();// ָ������ͼƬ·����url��ַ
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * ҵ��ϵͳ����˫���鿴����ʹ���ô˷�������ʱ�ļ�������dataservice���ڷ������� 2011.5.11 add by wm �����
	 * 
	 * @param TxdajbxxBean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_getTxDownload_FileUrl(
			@WebParam(partName = "daBean") TxdajbxxBean daBean) {
		ImageUploadBean iub = new ImageUploadBean(DIR_URL);
		int b = 0;
		String url = "";
		try {
			daBean.setClyx(ef_txjnml_clyx_query_tobyte(daBean.getIdString()));
			b = iub.getDownloadFileUrl(daBean.getClyx(), daBean.getYgdh(),
					daBean.getIdString(), daBean.getClbm());
			if (b == 1) {
				// url��ַ
				url = HTTP_URL + iub.getTempfile();// ���Ե�url��ַ
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * ����weblogic��Ⱥ���⣬�õ�ˮӡ����ʾ�ĵ�ǰʱ��
	 * 
	 * 
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_getWaterMark_Data() {
		String curdate = "";

		curdate = ImageUploadBean.getWaterMark();

		return curdate;
	}
	
	/*
	 * ����Ŀ¼��ʾ�б���ã� ���ôη��� add by zy  2011.8.11
	 * 
	 */
	@WebMethod
	@WebResult(partName = "beanArray")
	public JnmlBean[] get_Jnml_View_Results(@WebParam(partName = "bean") DajbxxBean bean) {
		JnmlBean[] jnmlBean = null;
		List<Row> list = null;
		try {
			list = DbMethod.makeDbSingleton().Open("DA_GET_JNML_VIEW_RESULTS",bean);
			
			jnmlBean = new JnmlBean[list.size()];
			if (list != null && list.size() > 0) {
				
				Row row = null;
				for (int i = 0; i < list.size(); i++) {
					row = list.get(i);
					jnmlBean[i] = new JnmlBean();
					jnmlBean[i].setId(Long.parseLong(row.getTrimString("id"))) ;
					jnmlBean[i].setYjfs(Integer.parseInt(row.getTrimString("yjfs"))) ;
					jnmlBean[i].setFyfs(Integer.parseInt(row.getTrimString("fyfs"))) ;
					jnmlBean[i].setFyys(Integer.parseInt(row.getTrimString("fyys"))) ;
					jnmlBean[i].setId(Long.parseLong(row.getTrimString("id"))) ;
					jnmlBean[i].setYgdh(row.getTrimString("ygdh")) ;
					jnmlBean[i].setZt(row.getTrimString("zt")) ;
					jnmlBean[i].setCl(row.getTrimString("cl")) ;
					jnmlBean[i].setSfqq(row.getTrimString("sfqq")) ;
					System.out.println("setId===="+jnmlBean[i].getId()+"getYjfs===="+jnmlBean[i].getYjfs() +"getFyfs===="+jnmlBean[i].getFyfs()+"getFyys===="+ jnmlBean[i].getFyys()+"getYgdh===="+ jnmlBean[i].getYgdh()+"getZt===="+ jnmlBean[i].getZt()+"getSfqq===="+ jnmlBean[i].getSfqq()+"getCl===="+ jnmlBean[i].getCl());
				}
			}
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jnmlBean;
	}

	/*****************************************************************************************************/
	// ��ѯ����˳��� 2011-7-19 wm
	@WebMethod
	public DagszBean[] getDNoComboxX(@WebParam(partName = "bean") DagszBean bean) {
		String trace = "";
		DagszBean[] dagszBean = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_DNOCOMBOXX",
					bean);
			dagszBean = new DagszBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				dagszBean[i] = new DagszBean();
				dagszBean[i].setDno(row.getTrimString("DNO"));
			}
		} catch (Exception e) {
			trace += "<font color='red' ��ѯ˳��ų���>" + e.getMessage();
			log.error(trace);
		}
		return dagszBean;
	}

	// 2011-7-26 wm
	@WebMethod
	public DagszBean[] getGHROWCOMBOXXX(
			@WebParam(partName = "bean") DagszBean bean) {
		DagszBean[] dagszBean = null;
		List<Row> list = null;
		try {
			list = DbMethod.makeDbSingleton().Open("DA_GHROWCOMBOXXX", bean);
			dagszBean = new DagszBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				dagszBean[i] = new DagszBean();
				dagszBean[i].setDagrow(row.getTrimString("grow"));
				dagszBean[i].setDaph(row.getTrimString("ph"));
				dagszBean[i].setDahh(row.getTrimString("HH"));
				dagszBean[i].setF_type(row.getTrimString("F_TYPE"));
				dagszBean[i].setDags(Integer
						.parseInt(row.getTrimString("DMAX")));
				dagszBean[i].setDaps(Integer.parseInt(row.getTrimString("DNUM")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dagszBean;
	}
	//------------------------
	//����������Ϣ����˳���combox 2011-8-9wm
	@WebMethod
	public DagszBean[] getSxhCombox(@WebParam(partName="bean")DagszBean bean){
		String trace = "";
		DagszBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_SXHCOMBOX", bean);
			xn = new DagszBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DagszBean();
				xn[i].setDno(row.getTrimString("DNO"));
			}
		} catch (Exception e) {
			trace +="<font color='red'>����������Ϣ����˳��ų���</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	
	//���⵵���Ҳ�ѯ���ݲ��Ų�ѯ 2012-9-12 wm
	@WebMethod
	public DagszBean [] getSelBum(DagszBean bean,int pagesize,int pagenum){
		String trace = "";
		int count = 0;
		DagszBean [] dagszBean = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_SELBUM", bean,pagesize,pagenum);
			count = list.size() > 0 ? list.get(0).getDefInt("count") : 0;
			dagszBean = new DagszBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				dagszBean[i] = new DagszBean();
				dagszBean[i].setBum(row.getTrimString("F_CAPTION"));
				dagszBean[i].setCount(count);
			}
		} catch (Exception e) {
			trace += "<font color='red'>���ݲ��Ų�ѯ�����ҳ���</font>"+e.getMessage();
			log.error(trace);
		}
		return dagszBean;
	}
	/*
	 * ʵ�ֱ���Ĵ������ѯ����
	 */
	//�������ѯCombox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getDkCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";
   	 
		DajbxxBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_DKCOMBOX", bean);
       	 
			xn = new DajbxxBean[list.size()];
			 
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setDah(row.getTrimString("DAH"));
				xn[i].setGh(row.getTrimString("GH"));
				xn[i].setHh(row.getTrimString("HH"));
				xn[i].setZgzh(row.getTrimString("ZGZH"));
				xn[i].setZgxm(row.getTrimString("ZGXM"));
				xn[i].setZjhm(row.getTrimString("ZJHM"));
				xn[i].setDWZH(row.getTrimString("DWZH"));
				xn[i].setDW(row.getTrimString("DW"));
				xn[i].setDKNX(row.getTrimString("DKNX"));
				xn[i].setGjd(row.getTrimString("GJD"));
				xn[i].setGFLX(row.getTrimString("GFLX"));
				xn[i].setCbwd(row.getTrimString("CBWD"));
				xn[i].setCfwz(row.getTrimString("CFWZ"));
				
				
			}
		} catch (Exception e) {
			trace +="<font color='red'>�������ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ���ĵ�λ�鼯������ѯ����
	 */
	//��λ�鼯������ѯCombox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getDWGJCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";

		DajbxxBean [] xn = null;
		
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_DWGJCOMBOX", bean);
			xn = new DajbxxBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setDah(row.getTrimString("Dah"));
				xn[i].setGh(row.getTrimString("Gh"));
				xn[i].setHh(row.getTrimString("Hh"));
				xn[i].setDWZH(row.getTrimString("Dwzh"));
				xn[i].setDW(row.getTrimString("Dw"));
				xn[i].setDWDZ(row.getTrimString("Dwdz"));
				xn[i].setGjd(row.getTrimString("Gjd"));
				xn[i].setCbwd(row.getTrimString("Cbwd"));
				xn[i].setGjzgy(row.getTrimString("Gjzgy"));
				xn[i].setKhrq(row.getTrimString("Khrq"));
				xn[i].setXhrq(row.getTrimString("Xhrq"));
				xn[i].setFcrq(row.getTrimString("Fcrq"));
				xn[i].setCfwz(row.getTrimString("Cfwz"));
				System.out.println("xn[i].getDah()"+xn[i].getDah());
			}
		} catch (Exception e) {
			trace +="<font color='red'>��λ������ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ���ĸ��˹鼯������ѯ����
	 */
	//���˹鼯������ѯCombox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getGrgjCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";

		DajbxxBean [] xn = null;
		
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_GRGJCOMBOX", bean);
			xn = new DajbxxBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setDah(row.getTrimString("DAH"));
				xn[i].setGh(row.getTrimString("Gh"));
				xn[i].setHh(row.getTrimString("Hh"));
				xn[i].setZgzh(row.getTrimString("Zgzh"));
				xn[i].setZgxm(row.getTrimString("Zgxm"));
				xn[i].setDWZH(row.getTrimString("Dwzh"));
				xn[i].setDW(row.getTrimString("Dw"));
				xn[i].setZjhm(row.getTrimString("Zjhm"));
				xn[i].setXb(row.getTrimString("Xb"));
				xn[i].setKhrq(row.getTrimString("Khrq").substring(0, 10));
				xn[i].setGjd(row.getTrimString("Gjd"));
				xn[i].setCbwd(row.getTrimString("Cbwd"));
				xn[i].setCfwz(row.getTrimString("Cfwz"));
	
			}
		} catch (Exception e) {
			trace +="<font color='red'>�鼯������ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ����¥�̹鼯������ѯ����
	 */
	//¥�̹鼯������ѯCombox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getLpgjCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";

		DajbxxBean [] xn = null;
		
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_LPGJCOMBOX", bean);
			xn = new DajbxxBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setDah(row.getTrimString("Dah"));
				xn[i].setGh(row.getTrimString("Gh"));
				xn[i].setHh(row.getTrimString("Hh"));
				xn[i].setXmbh(row.getTrimString("Xmbh"));
				xn[i].setXm(row.getTrimString("Xm"));
				xn[i].setGjd(row.getTrimString("Gjd"));
				xn[i].setCbwd(row.getTrimString("Cbwd"));
				xn[i].setJsrq(row.getTrimString("Jsrq"));
				xn[i].setBmjb(row.getTrimString("Bmjb"));
				xn[i].setCfwz(row.getTrimString("Cfwz"));
				
				
			}
		} catch (Exception e) {
			trace +="<font color='red'>¥�̹鼯������ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ���Ĺ鵵������ѯ����
	 */
	//�鵵������ѯCombox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getGddaCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";

		DajbxxBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_GDDACOMBOX", bean);
			xn = new DajbxxBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setDah(row.getTrimString("Dah"));
				xn[i].setGh(row.getTrimString("Gh"));
				xn[i].setHh(row.getTrimString("Hh"));
				xn[i].setBcnx(row.getDefInt("Bcnx"));
				xn[i].setBmjb(row.getTrimString("Bmjb"));
				xn[i].setClfs(row.getDefInt("Clfs"));
				xn[i].setTjr(row.getTrimString("Tjr"));
				xn[i].setTjrq(row.getTrimString("TJRQ"));
				xn[i].setTjrq2(row.getTrimString("TJRQ"));
				xn[i].setF_type(row.getTrimString("F_type"));
				xn[i].setCfwz(row.getTrimString("Cfwz"));
				
			}
		
		} catch (Exception e) {
			trace +="<font color='red'>�鵵������ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ�������⵵����ѯ����
	 */
	//���⵵����ѯCombox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getTsdaCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";

		DajbxxBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TSDACOMBOX", bean);
			xn = new DajbxxBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setDah(row.getTrimString("Dah"));
				xn[i].setGh(row.getTrimString("GH"));
				xn[i].setHh(row.getTrimString("HH"));
				xn[i].setBmjb(row.getTrimString("BMJB"));
				xn[i].setBcnx(row.getDefInt("BCNX"));
				xn[i].setClfs(row.getDefInt("CLFS"));
				xn[i].setTM(row.getTrimString("TM"));
				xn[i].setZTLB(row.getTrimString("ZTLB"));
				xn[i].setZRZ(row.getTrimString("ZRZ"));
				xn[i].setCfwz(row.getTrimString("CFWZ"));
			
			}
		} catch (Exception e) {
			trace +="<font color='red'>���⵵����ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ�����������¼����
	 */
	//�������¼Combox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getTxmCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";

		DajbxxBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TXMCOMBOX", bean);
			xn = new DajbxxBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setGh(row.getTrimString("GH"));
				xn[i].setHh(row.getTrimString("HH"));
				xn[i].setTjr(row.getTrimString("TJR"));
				xn[i].setTjrq(row.getTrimString("TJRQ"));
				xn[i].setBmjb(row.getTrimString("BMJB"));
				xn[i].setBcnx(row.getDefInt("BCNX"));
			
			}
		} catch (Exception e) {
			trace +="<font color='red'>�������ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ�������鵵������
	 */
	//���鵵��Combox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getWSCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";
   	 
		DajbxxBean [] xn = null;
	
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_WSCOMBOX", bean);
			xn = new DajbxxBean[list.size()];
			
			 System.out.println(list.size());
			 for (int i = 0; i < list.size(); i++) {
				
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setDah(row.getTrimString("Dah"));
				xn[i].setGh(row.getTrimString("Gh"));
				xn[i].setHh(row.getTrimString("Hh"));
				xn[i].setBmjb(row.getTrimString("Bmjb"));
				xn[i].setBcnx(row.getDefInt("Bcnx"));
				xn[i].setClfs(row.getDefInt("Clfs"));
				xn[i].setTM(row.getTrimString("Tm"));
				xn[i].setYJML(row.getTrimString("YJML"));
				xn[i].setEJML(row.getTrimString("EJML"));
				xn[i].setSJML(row.getTrimString("SJML"));
				xn[i].setZRZ(row.getTrimString("ZRZ"));
				xn[i].setCfwz(row.getTrimString("Cfwz"));
				
				
			}
		} catch (Exception e) {
			trace +="<font color='red'>���鵵����ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ�������ټ�¼����
	 */
	//���ټ�¼Combox 2011-9-19ygp
	
	@WebMethod
	public XhjlBean[] getXhCombox(@WebParam(partName="bean")XhjlBean bean){
		String trace = "";
   	
		XhjlBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_XHCOMBOX", bean);    	 
       	 System.out.println(list.size());
			xn = new XhjlBean[list.size()];		 
				for (int i = 0; i < list.size(); i++) {
					Row row = list.get(i);
				xn[i] = new XhjlBean();
				xn[i].setDAH(row.getTrimString("DAH"));
				xn[i].setDALX(row.getTrimString("DALX"));
				xn[i].setGJD(row.getTrimString("GJD"));
				xn[i].setXHR(row.getTrimString("XHR"));
				xn[i].setXHRQ(row.getTrimString("XHRQ"));
				xn[i].setXHJDR(row.getTrimString("XHJDR"));
				xn[i].setDAZT(row.getTrimString("DAZT"));
				xn[i].setXHYY(row.getTrimString("XHYY"));
				
			}
		} catch (Exception e) {
			trace +="<font color='red'>���ټ�¼��ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ����֧ȡ�����鼯����
	 */
	//֧ȡ�����鼯Combox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getZqdaCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";
 
		DajbxxBean [] xn = null;
		
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_ZQDACOMBOX", bean);
   
			xn = new DajbxxBean[list.size()];
		
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setDah(row.getTrimString("Dah"));
				xn[i].setGh(row.getTrimString("Gh"));
				xn[i].setHh(row.getTrimString("Hh"));
				xn[i].setZgzh(row.getTrimString("Zgzh"));
				xn[i].setZgxm(row.getTrimString("Zgxm"));
				xn[i].setZjhm(row.getTrimString("Zjhm"));
				xn[i].setZQRQ(row.getTrimString("Zqrq").substring(0, 10));
				xn[i].setDWZH(row.getTrimString("Dwzh"));
				xn[i].setDW(row.getTrimString("Dw"));
				xn[i].setCfwz(row.getTrimString("Cfwz"));
		 
			}
		} catch (Exception e) {
			trace +="<font color='red'>֧ȡ������ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	/*
	 * ʵ�ֱ����ע�������鼯����
	 */
	//ע�������鼯Combox 2011-9-19ygp
	
	@WebMethod
	public DajbxxBean[] getZxCombox(@WebParam(partName="bean")DajbxxBean bean){
		String trace = "";

		DajbxxBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_ZXCOMBOX", bean);

			xn = new DajbxxBean[list.size()];

			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new DajbxxBean();
				xn[i].setDah(row.getTrimString("Dah"));
				xn[i].setGh(row.getTrimString("GH"));
				xn[i].setHh(row.getTrimString("HH"));
				xn[i].setBcnx(row.getDefInt("BCNX"));
				xn[i].setBmjb(row.getTrimString("BMJB"));
				xn[i].setClfs(row.getDefInt("CLFS"));
				xn[i].setF_type(row.getTrimString("F_TYPE"));
				xn[i].setZxr(row.getTrimString("ZXR"));
				xn[i].setZxrq(row.getTrimString("ZXRQ"));
				xn[i].setZxyy(row.getTrimString("ZXYY"));
				xn[i].setCfwz(row.getTrimString("CFWZ"));
			
			}
		} catch (Exception e) {
			trace +="<font color='red'>ע��������ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	
	
	
	
	//��������ͳ��  xy
	@WebMethod
	public TJReportBean[] getTJ(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setXHRQ_begin(row.getTrimString("XHRQ"));			
				xn[i].setDalx(row.getTrimString("DALX"));
				xn[i].setXhr(row.getTrimString("XHR"));
				xn[i].setGjd(row.getTrimString("GJD"));
				xn[i].setXhjdr(row.getTrimString("XHJDR"));					
			}
		} catch (Exception e) {
			trace +="<font color='red'>��������ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//��λ�鼯����ͳ��  xy
	@WebMethod
	public TJReportBean[] getTJDWGJ(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setRQ_begin(row.getTrimString("JSRQ"));			
				xn[i].setDw(row.getTrimString("DW"));
				xn[i].setGjd(row.getTrimString("GJD"));
				xn[i].setCbwd(row.getTrimString("CBWD"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>��λ�鼯����ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//���˹鼯����ͳ��  xy
	@WebMethod
	public TJReportBean[] getTJGRGJ(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setRQ_begin(row.getTrimString("JSRQ"));			
				xn[i].setDw(row.getTrimString("DW"));
				xn[i].setGjd(row.getTrimString("GJD"));
				xn[i].setCbwd(row.getTrimString("CBWD"));
			}
		} catch (Exception e) {
			trace +="<font color='red'>���˹鼯����ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//���˴����ͳ��  xy
	@WebMethod
	public TJReportBean[] getTJGRDK(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setRQ_begin(row.getTrimString("JSRQ"));			
				xn[i].setDw(row.getTrimString("DW"));
				xn[i].setGjd(row.getTrimString("GJD"));
				xn[i].setCbwd(row.getTrimString("CBWD"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>���˴����ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//����֧ȡ����ͳ�� getTJGRZQ  XY
	@WebMethod
	public TJReportBean[] getTJGRZQ(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setRQ_begin(row.getTrimString("JSRQ"));			
				xn[i].setDw(row.getTrimString("DW"));
				xn[i].setGjd(row.getTrimString("GJD"));
				xn[i].setCbwd(row.getTrimString("CBWD"));
				xn[i].setDWZH(row.getTrimString("DWZH"));
				xn[i].setZGZH(row.getTrimString("ZGZH"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>����֧ȡ����ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//¥�̵���ͳ��getTJLP XY
	@WebMethod
	public TJReportBean[] getTJLP(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setRQ_begin(row.getTrimString("JSRQ"));			
				xn[i].setXM(row.getTrimString("XM"));
				xn[i].setGjd(row.getTrimString("GJD"));
				xn[i].setCbwd(row.getTrimString("CBWD"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>¥�̵���ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	} 
	//�鵵����getTJGDGL  XY
	@WebMethod
	public TJReportBean[] getTJGDGL(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setJsrq(row.getTrimString("JSRQ"));
				xn[i].setF_TYPE(row.getTrimString("F_TYPE"));
				xn[i].setTJR(row.getTrimString("TJR"));
				xn[i].setJSR(row.getTrimString("JSR"));
				//xn[i].setDYR(row.getTrimString("DYR"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>�鵵�����ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//�����Ҳ��ͳ�� XY 
	@WebMethod
	public TJReportBean[] getTJDASCY(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   
		TJReportBean [] xn = null;

		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_CJTJ_REPORT", bean);
	
			xn = new TJReportBean[list.size()];		
		
			for (int i = 0; i < list.size(); i++) {
		
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setRQ_begin(row.getTrimString("GHRQ"));			
				xn[i].setDalx(row.getTrimString("DALX"));
				xn[i].setDjr(row.getTrimString("DJR"));
				xn[i].setJyr(row.getTrimString("JYR"));
				xn[i].setBcdz(row.getTrimString("BCDZ"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>�����Ҳ��ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//������Ϣ���ͳ�� XY
	@WebMethod
	public TJReportBean[] getTJDAXXBG(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setJsrq(row.getTrimString("JSRQ"));			
				xn[i].setF_TYPE(row.getTrimString("F_TYPE"));
				xn[i].setF_UPDATER_NAME(row.getTrimString("F_UPDATER_NAME"));
				xn[i].setTJR(row.getTrimString("TJR"));
				xn[i].setJSR(row.getTrimString("JSR"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>������Ϣ���ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//�������������ͳ�� XY
	@WebMethod
	public TJReportBean[] getTJDADJD(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setJsrq(row.getTrimString("JSRQ"));			
				xn[i].setF_TYPE(row.getTrimString("F_TYPE"));
				xn[i].setTJR(row.getTrimString("TJR"));
				xn[i].setJSR(row.getTrimString("JSR"));
				xn[i].setCFWZ(row.getTrimString("CFWZ"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>�������������ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//��������ͳ�� XY
	@WebMethod
	public TJReportBean[] getTJDAJD(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setZXRQ(row.getTrimString("ZXRQ"));			
				xn[i].setF_TYPE(row.getTrimString("F_TYPE"));
				xn[i].setTJR(row.getTrimString("TJR"));
				xn[i].setJSR(row.getTrimString("JSR"));
				xn[i].setZXR(row.getTrimString("ZXR"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>��������ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//����������ͳ�� XY
	@WebMethod
	public TJReportBean[] getTJDADXH(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			
			xn = new TJReportBean[list.size()];		
			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setJsrq(row.getTrimString("JSRQ"));			
				xn[i].setF_TYPE(row.getTrimString("F_TYPE"));
				xn[i].setTJR(row.getTrimString("TJR"));
				xn[i].setJSR(row.getTrimString("JSR"));
				xn[i].setCFWZ(row.getTrimString("CFWZ"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>����������ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//��������ͳ�� XY
	@WebMethod
	public TJReportBean[] getTJDAXH(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setRQ_begin(row.getTrimString("JSRQ"));			
				xn[i].setDw(row.getTrimString("DW"));
				xn[i].setGjd(row.getTrimString("GJD"));
				xn[i].setCbwd(row.getTrimString("CBWD"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>��������ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	//��������ͳ�� XY
	@WebMethod
	public TJReportBean[] getTJDAGZ(@WebParam(partName="bean") TJReportBean bean){
		String trace = "";
   	
		TJReportBean [] xn = null;
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_TJDWGJ_REPORT", bean);
			xn = new TJReportBean[list.size()];			
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				xn[i] = new TJReportBean();
				xn[i].setCzybm(row.getTrimString("CZYBM"));			
				xn[i].setCzy(row.getTrimString("CZY"));
				xn[i].setKsrq(row.getTrimString("KSRQ"));
				xn[i].setJsrq(row.getTrimString("JSRQ"));
				xn[i].setCznr(row.getTrimString("CZNR"));
				xn[i].setJSJ(row.getTrimString("JSJ"));
							
			}
		} catch (Exception e) {
			trace +="<font color='red'>��������ͳ�ƴ�ӡ����</font>"+e.getMessage();
			log.error(trace);
		}
		return xn;
	}
	
	/***
	 * ���������б�	2011-09-23 BY YGP
	 */
    @WebMethod
	public DajbxxBean[] getBcnx(@WebParam(partName = "bean") DajbxxBean bean) {
    	DajbxxBean[] bean1 = null;
		String trace = "";
		
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_GETBCNX", bean);
			bean1 = new DajbxxBean[list.size()];
			int i = 0;
			for (Row row : list) {
				DajbxxBean cb = new DajbxxBean(); // �����б���Ӧ���ַ���
				cb.setId(Long.parseLong(row.getTrimString("ID")));
				cb.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				bean1[i++] = cb;
				}
		}  catch (Exception e) {
			trace = "<font color='red'>����ͳ��-��ѯ�б����" + e.getMessage()
			+ "</font>";
				log.error(trace);
		}
		return bean1;
	}
	
	
	/**********************************************************************************
	 * ϵͳ���ã�������� 	2011-09-23 BY DHT										  *
	 **********************************************************************************/
	/***
	 * ���������б�	2011-09-23 BY DHT
	 */
    @WebMethod
	public DaLxBean[] getDalx(@WebParam(partName = "bean") DaLxBean bean) {
    	DaLxBean[] bean1 = null;
		String trace = "";
		
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_GETDALX", bean);
			bean1 = new DaLxBean[list.size()];
			int i = 0;
			for (Row row : list) {
				DaLxBean cb = new DaLxBean(); // �����б���Ӧ���ַ���
				cb.setId(Long.parseLong(row.getTrimString("ID")));
				cb.setF_type(row.getTrimString("F_TYPE"));
				bean1[i++] = cb;
				}
		}  catch (Exception e) {
			trace = "<font color='red'>ϵͳ���ã��������    ���������б� ��ѯ ʧ�ܣ�" + e.getMessage()
			+ "</font>";
				log.error(trace);
		}
		return bean1;
	}
    /***
	 * �����б�	2011-09-23 BY DHT
	 */
    @WebMethod
	public JnmlSzBean[] getJnmlsz(@WebParam(partName = "bean") JnmlSzBean bean) {
    	JnmlSzBean[] bean1 = null;
		String trace = "";
		try {
			List<Row> list = DbMethod.makeDbSingleton().Open("DA_GETJNMLSZ", bean);
			bean1 = new JnmlSzBean[list.size()];
			int i = 0;
			for (Row row : list) {
				JnmlSzBean cb = new JnmlSzBean(); // �����б���Ӧ���ַ���
				cb.setId(Long.parseLong(row.getTrimString("ID")));
				cb.setCl(row.getTrimString("CL"));
				cb.setClbm(row.getTrimString("CLBM"));
				bean1[i++] = cb;
				}
		}  catch (Exception e) {
			trace = "<font color='red'>ϵͳ���ã��������   �����б�  ��ѯ ʧ�ܣ�" + e.getMessage()
			+ "</font>";
				log.error(trace);
		}
		return bean1;
	}
	/**
	 * ��������������   2011-09-24 by DHT
	 */
    @WebMethod
	public DaSzJnmlBean da_daszJnml_add(@WebParam(partName = "bean")
		DaSzJnmlBean bean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_DASZJNML_ADD", bean);
		} catch (Exception e) {
			trace = e.getMessage();
			log.error(e.getMessage());
		}
		return bean;
	}
	/**
	 * �����������ɾ��   2011-09-24 by DHT
	 */
    @WebMethod
	public DaSzJnmlBean da_daszJnml_del(@WebParam(partName = "bean")
		DaSzJnmlBean bean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_DASZJNML_DEL", bean);
		} catch (Exception e) {
			trace = e.getMessage();
			log.error(e.getMessage());
		}
		return bean;
	}
	/**
	 * ������������޸�   2011-09-24 by DHT
	 */
    @WebMethod
	public DaSzJnmlBean da_daszJnml_chg(@WebParam(partName = "bean")
		DaSzJnmlBean bean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_DASZJNML_CHG", bean);
		} catch (Exception e) {
			trace = e.getMessage();
			log.error(e.getMessage());
		}
		return bean;
	}
    //������ѯ����----------------------------------------------------------------
	/**
	 * ������ѯ-���鵵����ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public WsdaBean[] getWscxdaData(WsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		WsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getWscxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new WsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				WsdaBean wsda=new WsdaBean();

				wsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				wsda.setYjml(row.getTrimString("YJML"));
				wsda.setEjml(row.getTrimString("EJML"));
				wsda.setSjml(row.getTrimString("SJML"));
				wsda.setTm(row.getTrimString("TM"));
				wsda.setYgdh(row.getTrimString("YGDH"));			
				wsda.setF_type(row.getTrimString("F_TYPE"));
				wsda.setDah(row.getTrimString("DAH"));
				wsda.setTjr(row.getTrimString("TJR"));
				wsda.setTjrq(row.getTrimString("TJRQ"));
				wsda.setGrow(row.getTrimString("GROW"));
				wsda.setDash(row.getTrimString("DASH"));
				wsda.setGh(row.getTrimString("GH"));
				wsda.setHh(row.getTrimString("HH"));
				wsda.setSxh(row.getTrimString("SXH"));
				wsda.setJsr(row.getTrimString("JSR"));
				wsda.setJsrq(row.getTrimString("JSRQ"));
				wsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				wsda.setZxr(row.getTrimString("ZXR"));
				wsda.setZxrq(row.getTrimString("ZXRQ"));
				wsda.setZxyy(row.getTrimString("ZXYY"));
				wsda.setClfs(Integer.parseInt(row.getTrimString("CLFS")));
				wsda.setZrz(row.getTrimString("ZRZ"));
				wsda.setCfwz(row.getTrimString("CFWZ"));
				wsda.setF_state(row.getTrimString("F_STATE"));
				wsda.setCount(count);
				arr[i++] = wsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-���鵵��  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-���⵵����ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public TsdaBean[] getTscxdaData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getTscxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new TsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				TsdaBean tsda=new TsdaBean();

				tsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				tsda.setTm(row.getTrimString("TM"));
				tsda.setYgdh(row.getTrimString("YGDH"));
				tsda.setF_type(row.getTrimString("F_TYPE"));
				tsda.setDah(row.getTrimString("DAH"));
				tsda.setTjr(row.getTrimString("TJR"));
				tsda.setTjrq(row.getTrimString("TJRQ"));
				tsda.setGrow(row.getTrimString("GROW"));
				tsda.setDash(row.getTrimString("DASH"));
				tsda.setGh(row.getTrimString("GH"));
				tsda.setHh(row.getTrimString("HH"));
				tsda.setJsr(row.getTrimString("JSR"));
				tsda.setJsrq(row.getTrimString("JSRQ"));
				tsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				tsda.setZxr(row.getTrimString("ZXR"));
				tsda.setZxrq(row.getTrimString("ZXRQ"));
				tsda.setZxyy(row.getTrimString("ZXYY"));
				tsda.setClfs(Integer.parseInt(row.getTrimString("CLFS")));
				tsda.setZrz(row.getTrimString("ZRZ"));
				tsda.setCfwz(row.getTrimString("CFWZ"));
				tsda.setF_state(row.getTrimString("F_STATE"));
				tsda.setZtlb(row.getTrimString("ZTLB"));
				tsda.setSxh(row.getTrimString("SXH"));
				tsda.setBmjb(row.getTrimString("BMJB"));
				tsda.setCfwz(row.getTrimString("CFWZ"));
				tsda.setCount(count);
				arr[i++] = tsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-���⵵��  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-�Ƽ�������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public ZldaBean[] getKjcxdaData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getZlcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean zlda=new ZldaBean();

				zlda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				zlda.setTm(row.getTrimString("TM"));
				zlda.setYgdh(row.getTrimString("YGDH"));			
				zlda.setF_type(row.getTrimString("F_TYPE"));
				zlda.setDah(row.getTrimString("DAH"));
				zlda.setCjr(row.getTrimString("CJR"));
				zlda.setGrow(row.getTrimString("GROW"));
				zlda.setDash(row.getTrimString("DASH"));
				zlda.setGh(row.getTrimString("GH"));
				zlda.setHh(row.getTrimString("HH"));
				zlda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				zlda.setCjrq(row.getDateToString("CJRQ"));
				zlda.setYwlsh(row.getTrimString("YWLSH"));
				zlda.setBmjb(row.getTrimString("BMJB"));
				zlda.setCount(count);
				arr[i++] = zlda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-�Ƽ�����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-��Ƭ������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public TsdaBean[] getZpcxdaData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getZpcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new TsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				TsdaBean tsda=new TsdaBean();

				tsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				tsda.setTm(row.getTrimString("TM"));
				tsda.setYgdh(row.getTrimString("YGDH"));			
				tsda.setF_type(row.getTrimString("F_TYPE"));
				tsda.setDah(row.getTrimString("DAH"));
				tsda.setCjr(row.getTrimString("CJR"));
				tsda.setGrow(row.getTrimString("GROW"));
				tsda.setDash(row.getTrimString("DASH"));
				tsda.setGh(row.getTrimString("GH"));
				tsda.setHh(row.getTrimString("HH"));
				tsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				tsda.setCjrq(row.getDateToString("CJRQ"));
				tsda.setYwlsh(row.getTrimString("YWLSH"));
				tsda.setBmjb(row.getTrimString("BMJB"));
				tsda.setCount(count);
				arr[i++] = tsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-��Ƭ����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-��Ƭ������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public TsdaBean[] getLxcxdaData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getLxcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new TsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				TsdaBean tsda=new TsdaBean();

				tsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				tsda.setTm(row.getTrimString("TM"));
				tsda.setYgdh(row.getTrimString("YGDH"));			
				tsda.setF_type(row.getTrimString("F_TYPE"));
				tsda.setDah(row.getTrimString("DAH"));
				tsda.setCjr(row.getTrimString("CJR"));
				tsda.setGrow(row.getTrimString("GROW"));
				tsda.setDash(row.getTrimString("DASH"));
				tsda.setGh(row.getTrimString("GH"));
				tsda.setHh(row.getTrimString("HH"));
				tsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				tsda.setCjrq(row.getDateToString("CJRQ"));
				tsda.setYwlsh(row.getTrimString("YWLSH"));
				tsda.setBmjb(row.getTrimString("BMJB"));
				tsda.setCount(count);
				arr[i++] = tsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-¼�񵵰�  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-ʵ�ﵵ����ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public ZldaBean[] getSwcxdaData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getSwcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean zlda=new ZldaBean();

				zlda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				zlda.setTm(row.getTrimString("TM"));
				zlda.setYgdh(row.getTrimString("YGDH"));			
				zlda.setF_type(row.getTrimString("F_TYPE"));
				zlda.setDah(row.getTrimString("DAH"));
				zlda.setCjr(row.getTrimString("CJR"));
				zlda.setGrow(row.getTrimString("GROW"));
				zlda.setDash(row.getTrimString("DASH"));
				zlda.setGh(row.getTrimString("GH"));
				zlda.setHh(row.getTrimString("HH"));
				zlda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				zlda.setCjrq(row.getDateToString("CJRQ"));
				zlda.setYwlsh(row.getTrimString("YWLSH"));
				zlda.setBmjb(row.getTrimString("BMJB"));
				zlda.setCount(count);
				arr[i++] = zlda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-�Ƽ�����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-֪ʶ������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public TsdaBean[] getZscxdaData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getLxcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new TsdaBean[lst.size()];
			System.out.println(count+"-0-0-0-0-0-0-0");
			int i = 0;

			for (Row row : lst) {
				TsdaBean tsda=new TsdaBean();

				tsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				tsda.setTm(row.getTrimString("TM"));
				tsda.setYgdh(row.getTrimString("YGDH"));			
				tsda.setF_type(row.getTrimString("F_TYPE"));
				tsda.setDah(row.getTrimString("DAH"));
				tsda.setCjr(row.getTrimString("CJR"));
				tsda.setGrow(row.getTrimString("GROW"));
				tsda.setDash(row.getTrimString("DASH"));
				tsda.setGh(row.getTrimString("GH"));
				tsda.setHh(row.getTrimString("HH"));
				tsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				tsda.setCjrq(row.getDateToString("CJRQ"));
				tsda.setYwlsh(row.getTrimString("YWLSH"));
				tsda.setBmjb(row.getTrimString("BMJB"));
				tsda.setCount(count);
				arr[i++] = tsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-֪ʶ����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-���Ƶ�����ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public ZldaBean[] getFzcxdaData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getFzcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean zlda=new ZldaBean();

				zlda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				zlda.setTm(row.getTrimString("TM"));
				zlda.setYgdh(row.getTrimString("YGDH"));			
				zlda.setF_type(row.getTrimString("F_TYPE"));
				zlda.setDah(row.getTrimString("DAH"));
				zlda.setCjr(row.getTrimString("CJR"));
				zlda.setGrow(row.getTrimString("GROW"));
				zlda.setDash(row.getTrimString("DASH"));
				zlda.setGh(row.getTrimString("GH"));
				zlda.setHh(row.getTrimString("HH"));
				zlda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				zlda.setCjrq(row.getDateToString("CJRQ"));
				zlda.setYwlsh(row.getTrimString("YWLSH"));
				zlda.setBmjb(row.getTrimString("BMJB"));
				zlda.setCount(count);
				arr[i++] = zlda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-�Ƽ�����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-���Ƶ�����ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public RsdaBean[] getRscxdaData(RsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		RsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getRscxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new RsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				RsdaBean rsda=new RsdaBean();

				rsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				rsda.setYgdh(row.getTrimString("YGDH"));			
				rsda.setF_type(row.getTrimString("F_TYPE"));
				rsda.setDah(row.getTrimString("DAH"));
				rsda.setGrow(row.getTrimString("GROW"));
				rsda.setDash(row.getTrimString("DASH"));
				rsda.setGh(row.getTrimString("GH"));
				rsda.setHh(row.getTrimString("HH"));
				rsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				rsda.setYwlsh(row.getTrimString("YWLSH"));
				rsda.setBmjb(row.getTrimString("BMJB"));
				rsda.setName(row.getTrimString("ENAME"));
				rsda.setDjr(row.getTrimString("DJR"));
				rsda.setDjrq(row.getTrimString("DJRQ"));
				rsda.setCount(count);
				arr[i++] = rsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-���µ���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-�������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public DkdaBean[] getDkcxdaData(DkdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		DkdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getDkcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new DkdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DkdaBean dkda=new DkdaBean();

				dkda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				dkda.setF_type(row.getTrimString("F_TYPE"));
				dkda.setDah(row.getTrimString("DAH"));
				dkda.setGrow(row.getTrimString("GROW"));
				dkda.setDash(row.getTrimString("DASH"));
				dkda.setGh(row.getTrimString("GH"));
				dkda.setHh(row.getTrimString("HH"));
				dkda.setBmjb(row.getTrimString("BMJB"));
				dkda.setZgzh(row.getTrimString("ZGZH"));
				dkda.setZgxm(row.getTrimString("ZGXM"));
				dkda.setDw(row.getTrimString("DW"));
				dkda.setDwzh(row.getTrimString("DWZH"));
				dkda.setDknx(Integer.parseInt(row.getTrimString("DKNX")));
				dkda.setGflx(row.getTrimString("GFLX"));
				dkda.setGjd(row.getTrimString("GJD"));
				dkda.setCbwd(row.getTrimString("CBWD"));
				dkda.setCfwz(row.getTrimString("CFWZ"));
				dkda.setPh(row.getTrimString("PH"));
				dkda.setSxh(row.getTrimString("SXH"));
				dkda.setCount(count);
				arr[i++] = dkda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-���µ���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-���˹鼯������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public GjdaBean[] getGjcxdaData(GjdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		GjdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getGjcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new GjdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				GjdaBean gjda=new GjdaBean();

				gjda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				gjda.setF_type(row.getTrimString("F_TYPE"));
				gjda.setDah(row.getTrimString("DAH"));
				gjda.setGrow(row.getTrimString("GROW"));
				gjda.setDash(row.getTrimString("DASH"));
				gjda.setGh(row.getTrimString("GH"));
				gjda.setHh(row.getTrimString("HH"));
				gjda.setBmjb(row.getTrimString("BMJB"));
				gjda.setZgzh(row.getTrimString("ZGZH"));
				gjda.setZgxm(row.getTrimString("ZGXM"));
				gjda.setDw(row.getTrimString("DW"));
				gjda.setDwzh(row.getTrimString("DWZH"));
				gjda.setGjd(row.getTrimString("GJD"));
				gjda.setCbwd(row.getTrimString("CBWD"));
				gjda.setCfwz(row.getTrimString("CFWZ"));
				gjda.setXb(row.getTrimString("XB"));
				gjda.setKhrq(row.getTrimString("KHRQ"));
				gjda.setJsr(row.getTrimString("JSR"));
				gjda.setJsrq(row.getTrimString("JSRQ"));
				gjda.setYgdh(row.getTrimString("YGDH"));
				gjda.setZjhm(row.getTrimString("ZJHM"));
				gjda.setCount(count);
				arr[i++] = gjda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-���˹鼯����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-֧ȡ������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public ZqdaBean[] getZqcxdaData(ZqdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZqdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getZqcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new ZqdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZqdaBean zqda=new ZqdaBean();

				zqda.setId(Integer.parseInt(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				zqda.setF_type(row.getTrimString("F_TYPE"));
				zqda.setDah(row.getTrimString("DAH"));
				zqda.setGrow(row.getTrimString("GROW"));
				zqda.setDash(row.getTrimString("DASH"));
				zqda.setGh(row.getTrimString("GH"));
				zqda.setHh(row.getTrimString("HH"));
				zqda.setBmjb(row.getTrimString("BMJB"));
				zqda.setZgzh(row.getTrimString("ZGZH"));
				zqda.setZgxm(row.getTrimString("ZGXM"));
				zqda.setDw(row.getTrimString("DW"));
				zqda.setDwzh(row.getTrimString("DWZH"));
				zqda.setCfwz(row.getTrimString("CFWZ"));
				zqda.setZjhm(row.getTrimString("ZJHM"));
				zqda.setZqrq(row.getTrimString("ZQRQ"));
				zqda.setYgdh(row.getTrimString("YGDH"));
				zqda.setCount(count);
				arr[i++] = zqda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-����֧ȡ����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-¥�̵�����ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public LpdaBean[] getLpcxdaData(LpdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		LpdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getLpcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new LpdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				LpdaBean zqda=new LpdaBean();

				zqda.setId(Integer.parseInt(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				zqda.setF_type(row.getTrimString("F_TYPE"));
				zqda.setDah(row.getTrimString("DAH"));
				zqda.setGrow(row.getTrimString("GROW"));
				zqda.setDash(row.getTrimString("DASH"));
				zqda.setGh(row.getTrimString("GH"));
				zqda.setHh(row.getTrimString("HH"));
				zqda.setBmjb(row.getTrimString("BMJB"));
				zqda.setCfwz(row.getTrimString("CFWZ"));
				zqda.setYgdh(row.getTrimString("YGDH"));
				zqda.setXm(row.getTrimString("XM"));
				zqda.setXmbh(row.getTrimString("XMBM"));
				zqda.setJsrq(row.getTrimString("JSRQ"));
				zqda.setCbwd(row.getTrimString("CBWD"));
				zqda.setCount(count);
				arr[i++] = zqda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-����֧ȡ����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}

	
	/**
	 * ������ѯ-��λ�鼯������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public DwdaBean[] getDwcxdaData(DwdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		DwdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getDwcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new DwdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DwdaBean dwda=new DwdaBean();

				dwda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				dwda.setF_type(row.getTrimString("F_TYPE"));
				dwda.setDah(row.getTrimString("DAH"));
				dwda.setGrow(row.getTrimString("GROW"));
				dwda.setDash(row.getTrimString("DASH"));
				dwda.setGh(row.getTrimString("GH"));
				dwda.setHh(row.getTrimString("HH"));
				dwda.setBmjb(row.getTrimString("BMJB"));
				dwda.setDw(row.getTrimString("DW"));
				dwda.setDwzh(row.getTrimString("DWZH"));
				dwda.setGjd(row.getTrimString("GJD"));
				dwda.setCbwd(row.getTrimString("CBWD"));
				dwda.setCfwz(row.getTrimString("CFWZ"));
				dwda.setKhrq(row.getTrimString("KHRQ"));
				dwda.setDwdz(row.getTrimString("DWDZ"));
				dwda.setGjzgy(row.getTrimString("GJZGY"));
				dwda.setXhrq(row.getTrimString("XHRQ"));
				dwda.setFcrq(row.getTrimString("FCRQ"));
				dwda.setCount(count);
				arr[i++] = dwda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-��λ�鼯����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ������ѯ-�鵵������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public DaBean[] getGdcxdaData(DaBean bean,int pagesize, int pagenum) {
		String trace = "";
		DaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getGdcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DaBean gdda=new DaBean();

				gdda.setCid(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				gdda.setYgdh(row.getTrimString("YGDH"));			
				gdda.setF_type(row.getTrimString("F_TYPE"));
				gdda.setDah(row.getTrimString("DAH"));
				gdda.setGrow(row.getTrimString("GROW"));
				gdda.setDash(row.getTrimString("DASH"));
				gdda.setGh(row.getTrimString("GH"));
				gdda.setHh(row.getTrimString("HH"));
				gdda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				gdda.setBmjb(row.getTrimString("BMJB"));
				gdda.setTjr(row.getTrimString("TJR"));
				gdda.setCfwz(row.getTrimString("CFWZ"));
				gdda.setTjrq(row.getTrimString("TJRQ"));
				gdda.setCount(count);
				arr[i++] = gdda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-�鵵����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-�鵵������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public DaBean[] getZxcxdaData(DaBean bean,int pagesize, int pagenum) {
		String trace = "";
		DaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getZxcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DaBean zxda=new DaBean();

				zxda.setCid(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				zxda.setYgdh(row.getTrimString("YGDH"));			
				zxda.setF_type(row.getTrimString("F_TYPE"));
				zxda.setDah(row.getTrimString("DAH"));
				zxda.setGh(row.getTrimString("GH"));
				zxda.setHh(row.getTrimString("HH"));
				zxda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				zxda.setBmjb(row.getTrimString("BMJB"));
				zxda.setClfs(Integer.parseInt(row.getTrimString("CLFS")));
				zxda.setCfwz(row.getTrimString("CFWZ"));
				zxda.setZxr(row.getTrimString("ZXR"));
				zxda.setZxrq(row.getTrimString("ZXRQ"));
				zxda.setZxyy(row.getTrimString("ZXYY"));
				zxda.setCount(count);
				arr[i++] = zxda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-ע������  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������ѯ-�鵵������ѯ
	 * @author czm 2011-12-15
	 */
	@WebMethod
	public ZdaBean[] getXhcxdaData(ZdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getXhcxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new ZdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZdaBean xhda=new ZdaBean();

				xhda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				xhda.setF_type(row.getTrimString("F_TYPE"));
				xhda.setDah(row.getTrimString("DAH"));
				xhda.setDazt(row.getTrimString("DAZT"));
				xhda.setGjd(row.getTrimString("GJD"));
				xhda.setXhjdr(row.getTrimString("XHJDR"));
				xhda.setXhyy(row.getTrimString("XHJDR"));
				xhda.setXhrq(row.getTrimString("XHRQ"));
				xhda.setXhr(row.getTrimString("XHR"));
				xhda.setCount(count);
				arr[i++] = xhda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������ѯ-���ٵ���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ���ٹ���-�����ٹ���
	 * @author zwh 2011��12��15��
	 *
	 */
	@WebMethod
	public XhjlBean[] getDxhglData(XhjlBean bean,int pagesize, int pagenum) {
		String trace = "";
		XhjlBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetDxhglData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new XhjlBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				XhjlBean mx=new XhjlBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setDah(row.getTrimString("DAH"));
				mx.setTjr(row.getTrimString("TJR"));
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setJsr(row.getTrimString("JSR"));
				
				mx.setTjrq(row.getTrimString("TJRQ"));
				mx.setJsrq(row.getTrimString("JSRQ"));				
				mx.setBcnx(row.getDefInt("BCNX"));
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setClfs(row.getDefInt("CLFS"));
				
				mx.setCfwz(row.getTrimString("CFWZ"));
				mx.setGh(row.getTrimString("GH"));				
				mx.setHh(row.getTrimString("HH"));
				mx.setF_STATE(row.getTrimString("F_STATE"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>���ٹ���-�����ٹ���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ���ٹ���-���ٹ���
	 * @author zwh 2011��12��15��
	 *
	 */
	@WebMethod
	public XhjlBean[] getXhglData(XhjlBean bean,int pagesize, int pagenum) {
		String trace = "";
		XhjlBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetXhglData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new XhjlBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				XhjlBean mx=new XhjlBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setDah(row.getTrimString("DAH"));
				mx.setGjd(row.getTrimString("GJD"));
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setXHR(row.getTrimString("XHR"));
				
				mx.setXHRQ(row.getTrimString("XHRQ"));
				mx.setXHJDR(row.getTrimString("XHJDR"));				
				mx.setDAZT(row.getTrimString("DAZT"));
				mx.setXHYY(row.getTrimString("XHYY"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>���ٹ���-���ٹ���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	 /* ��������-����������
	 * @author zwh 2011��12��15��
	 *
	 */
	@WebMethod
	public DjdglMXBean[] getDjdglData(DjdglMXBean bean,int pagesize, int pagenum) {
		String trace = "";
		DjdglMXBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetDjdglData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DjdglMXBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DjdglMXBean mx=new DjdglMXBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setDah(row.getTrimString("DAH"));
				mx.setTjr(row.getTrimString("TJR"));
				mx.setTjrq(row.getTrimString("TJRQ"));				
				mx.setJsr(row.getTrimString("JSR"));
				
				mx.setJsrq(row.getTrimString("JSRQ"));
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setZxr(row.getTrimString("ZXR"));
				mx.setZxrq(row.getDateToString("ZXRQ"));
				mx.setZxyy(row.getTrimString("ZXYY"));
				
				mx.setBcnx(row.getDefInt("BCNX"));
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setClfs(row.getDefInt("CLFS"));
				mx.setCfwz(row.getTrimString("CFWZ"));
				mx.setGh(row.getTrimString("GH"));
				
				mx.setHh(row.getTrimString("HH"));
				mx.setSxh(row.getDefInt("SXH"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>��������-����������  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ��������-��������
	 * @author zwh 2011��12��15��
	 *
	 */
	@WebMethod
	public JdglMXBean[] getJdglData(JdglMXBean bean,int pagesize, int pagenum) {
		String trace = "";
		JdglMXBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetJdglData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new JdglMXBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				JdglMXBean mx=new JdglMXBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setDah(row.getTrimString("DAH"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setZxr(row.getTrimString("ZXR"));
				
				mx.setZxrq(row.getTrimString("ZXRQ"));
				mx.setZxyy(row.getTrimString("ZXYY"));				
				mx.setBcnx(row.getDefInt("BCNX"));
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setClfs(row.getDefInt("CLFS"));
				
				mx.setCfwz(row.getTrimString("CFWZ"));
				mx.setDash(row.getTrimString("DASH"));
				mx.setGh(row.getTrimString("GH"));		
				mx.setGrow(row.getTrimString("GROW"));
				mx.setPh(row.getTrimString("PH"));
				mx.setHh(row.getTrimString("HH"));
				mx.setSxh(row.getTrimString("SXH"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>��������-��������  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}

	
	/**
	 * ������˹鵵-���鵵���鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public WsdaBean[] getWsDaGdData(WsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		WsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetWsDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new WsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				WsdaBean mx=new WsdaBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setTm(row.getTrimString("TM"));
				mx.setDash(row.getTrimString("DASH"));
				mx.setGrow(row.getTrimString("GROW"));
				mx.setGh(row.getTrimString("GH"));
				mx.setHh(row.getTrimString("HH"));				
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setYwlsh(row.getTrimString("YWLSH"));				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setClfs(row.getDefInt("CLFS"));			
				mx.setZrz(row.getTrimString("ZRZ"));
				
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				mx.setYjml(row.getTrimString("YJML"));
				mx.setEjml(row.getTrimString("EJML"));
				mx.setSjml(row.getTrimString("SJML"));
				mx.setTjr(row.getTrimString("TJR"));
				
				mx.setTjrq(row.getTrimString("TJRQ"));			
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-���鵵���鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������˹鵵-�Ƽ������鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public ZldaBean[] getKjDaGdData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetKjDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean mx=new ZldaBean();
				
				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setTm(row.getTrimString("TM"));
				mx.setZtlb(row.getTrimString("ZTLB"));
				mx.setCjr(row.getTrimString("CJR"));				
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				
				mx.setType(row.getTrimString("F_TYPE"));				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setCjrq(row.getTrimString("CJRQ"));			
				mx.setYgdh(row.getTrimString("YGDH"));			
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				
				mx.setTjr(row.getTrimString("TJR"));				
				mx.setTjrq(row.getTrimString("TJRQ"));			
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-�Ƽ������鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������˹鵵-��Ƭ�����鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public TsdaBean[] getZpDaGdData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetZpDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new TsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				TsdaBean mx=new TsdaBean();
 
				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setTm(row.getTrimString("TM"));
				mx.setZtlb(row.getTrimString("ZTLB"));
				mx.setCjr(row.getTrimString("CJR"));				
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				
				mx.setType(row.getTrimString("F_TYPE"));				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setCjrq(row.getTrimString("CJRQ"));			
				mx.setYgdh(row.getTrimString("YGDH"));			
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				
				mx.setTjr(row.getTrimString("TJR"));				
				mx.setTjrq(row.getTrimString("TJRQ"));
				mx.setZrz(row.getTrimString("ZRZ"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-��Ƭ�����鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������˹鵵-���µ����鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public RsdaBean[] getRsDaGdData(RsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		RsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetRsDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new RsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				RsdaBean mx=new RsdaBean();
	
				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setEname(row.getTrimString("ENAME"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				mx.setPhone(row.getTrimString("PHONE"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setTjr(row.getTrimString("TJR"));
				mx.setAddress(row.getTrimString("ADDRESS"));
				mx.setJg(row.getTrimString("JG"));
				mx.setDjrq(row.getTrimString("DJRQ"));
				mx.setDjr(row.getTrimString("DJR"));
				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));			
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-���µ����鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	

	/**
	 * ������˹鵵-¼�񵵰��鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public TsdaBean[] getLxDaGdData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetLxDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new TsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				TsdaBean mx=new TsdaBean();
 
				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setTm(row.getTrimString("TM"));
				mx.setZtlb(row.getTrimString("ZTLB"));
				mx.setCjr(row.getTrimString("CJR"));				
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				
				mx.setType(row.getTrimString("F_TYPE"));				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setCjrq(row.getTrimString("CJRQ"));			
				mx.setYgdh(row.getTrimString("YGDH"));			
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				
				mx.setTjr(row.getTrimString("TJR"));				
				mx.setTjrq(row.getTrimString("TJRQ"));
				mx.setZrz(row.getTrimString("ZRZ"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-¼�񵵰��鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������˹鵵-ʵ�ﵵ���鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public ZldaBean[] getSwDaGdData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetSwDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean mx=new ZldaBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setTm(row.getTrimString("TM"));
				mx.setZtlb(row.getTrimString("ZTLB"));
				mx.setCjr(row.getTrimString("CJR"));				
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				
				mx.setType(row.getTrimString("F_TYPE"));				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setCjrq(row.getTrimString("CJRQ"));			
				mx.setYgdh(row.getTrimString("YGDH"));			
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				
				mx.setTjr(row.getTrimString("TJR"));				
				mx.setTjrq(row.getTrimString("TJRQ"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-ʵ�ﵵ���鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������˹鵵-֪ʶ�����鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public TsdaBean[] getZsDaGdData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetZsDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new TsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				TsdaBean mx=new TsdaBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setTm(row.getTrimString("TM"));
				mx.setZtlb(row.getTrimString("ZTLB"));
				mx.setCjr(row.getTrimString("CJR"));				
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				
				mx.setType(row.getTrimString("F_TYPE"));				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setCjrq(row.getTrimString("CJRQ"));			
				mx.setYgdh(row.getTrimString("YGDH"));			
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				
				mx.setTjr(row.getTrimString("TJR"));				
				mx.setTjrq(row.getTrimString("TJRQ"));
				mx.setZrz(row.getTrimString("ZRZ"));
				
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-֪ʶ�����鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	

	/**
	 * ������˹鵵-���ƻ��˵����鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public ZldaBean[] getFzDaGdData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetFzDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean mx=new ZldaBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setTm(row.getTrimString("TM"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setZtlb(row.getTrimString("ZTLB"));
				mx.setCjr(row.getTrimString("CJR"));
				
				mx.setCjrq(row.getTrimString("CJRQ"));
				mx.setTjr(row.getTrimString("TJR"));
				mx.setTjrq(row.getTrimString("TJRQ"));
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				mx.setBmjb(row.getTrimString("BMJB"));
				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));			
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-���ƻ��˵����鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}

	/**
	 * ������˹鵵-�ۺϹ鵵����
	 * @author zwh 2011��12��20��
	 *
	 */
	@WebMethod
	public ZldaBean[] getZhGdGlData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetZhGdGlData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean mx=new ZldaBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setGh(row.getTrimString("GH"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setHh(row.getTrimString("HH"));
				mx.setJsr(row.getTrimString("JSR"));
				
				mx.setJsrq(row.getDateToString("JSRQ"));
				mx.setTjr(row.getTrimString("TJR"));
				mx.setTjrq(row.getTrimString("TJRQ"));
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				mx.setBmjb(row.getTrimString("BMJB"));
				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				mx.setClfs(row.getDefInt("CLFS"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-�ۺϹ鵵����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}

	/**
	 * ������˹鵵-���˴���鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public DkdaBean[] getGrDkGdData(DkdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		DkdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetGrDkGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DkdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DkdaBean mx=new DkdaBean();
	
				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setZgxm(row.getTrimString("ZGXM"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setXb(row.getTrimString("XB"));
				mx.setDw(row.getTrimString("DW"));
				
				mx.setGh(row.getTrimString("GH"));
				mx.setHh(row.getTrimString("HH"));
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setYwlsh(row.getTrimString("YWLSH"));
				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setClfs(row.getDefInt("CLFS"));
				mx.setCbwd(row.getTrimString("CBWD"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				mx.setGjd(row.getTrimString("GJD"));
				
				mx.setZjhm(row.getTrimString("ZJHM"));
				mx.setTjr(row.getTrimString("TJR"));
				mx.setTjrq(row.getTrimString("TJRQ"));
				mx.setFkyh(row.getTrimString("FKYH"));
				mx.setFkrq(row.getTrimString("FKRQ"));
				
				mx.setDknx(row.getDefInt("DKNX"));
				mx.setGflx(row.getTrimString("GFLX"));
				mx.setGfxxdz(row.getTrimString("GFXXDZ"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-���˴���鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������˹鵵-��λ�鼯�鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public DwdaBean[] getDwGjGdData(DwdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		DwdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetDwGjGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DwdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DwdaBean mx=new DwdaBean();
				
				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setDwdz(row.getTrimString("DWDZ"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setKhrq(row.getTrimString("KHRQ"));
				mx.setDw(row.getTrimString("DW"));
				
				mx.setGh(row.getTrimString("GH"));
				mx.setHh(row.getTrimString("HH"));
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setYwlsh(row.getTrimString("YWLSH"));
				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setClfs(row.getDefInt("CLFS"));
				mx.setCbwd(row.getTrimString("CBWD"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				mx.setGjd(row.getTrimString("GJD"));
				
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-��λ�鼯�鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������˹鵵-���˹鼯�鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public GjdaBean[] getGrGjGdData(GjdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		GjdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetGrGjGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new GjdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				GjdaBean mx=new GjdaBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setZgxm(row.getTrimString("ZGXM"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setXb(row.getTrimString("XB"));
				mx.setZjhm(row.getTrimString("ZJHM"));
				
				mx.setGh(row.getTrimString("GH"));
				mx.setHh(row.getTrimString("HH"));
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setYwlsh(row.getTrimString("YWLSH"));
				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setClfs(row.getDefInt("CLFS"));
				mx.setCbwd(row.getTrimString("CBWD"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				mx.setGjd(row.getTrimString("GJD"));
				
				mx.setDw(row.getTrimString("DW"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-���˹鼯�鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ������˹鵵-¥�̵����鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public LpdaBean[] getLpDaGdData(LpdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		LpdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetLpDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new LpdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				LpdaBean mx=new LpdaBean();

				mx.setId(Integer.parseInt(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setXm(row.getTrimString("XM"));
				mx.setGh(row.getTrimString("GH"));
				mx.setHh(row.getTrimString("HH"));
				
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setYwlsh(row.getTrimString("YWLSH"));				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setClfs(row.getDefInt("CLFS"));
				
				mx.setCbwd(row.getTrimString("CBWD"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				mx.setGjd(row.getTrimString("GJD"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-¥�̵����鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������˹鵵-֧ȡ�鵵
	 * @author zwh 2011��12��20��
	 */
	@WebMethod
	public ZqdaBean[] getZqDaGdData(ZqdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZqdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetZqDaGdData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new ZqdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZqdaBean mx=new ZqdaBean();

				mx.setId(Integer.parseInt(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setZgxm(row.getTrimString("ZGXM"));
				mx.setGh(row.getTrimString("GH"));
				mx.setHh(row.getTrimString("HH"));
				
				mx.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setYwlsh(row.getTrimString("YWLSH"));				
				mx.setBmjb(row.getTrimString("BMJB"));
				mx.setClfs(row.getDefInt("CLFS"));
				
				mx.setCbwd(row.getTrimString("CBWD"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				mx.setGjd(row.getTrimString("GJD"));
				mx.setZgzh(row.getTrimString("ZGZH"));
				mx.setZjhm(row.getTrimString("ZJHM"));
				
				mx.setDw(row.getTrimString("DW"));
				mx.setDwzh(row.getTrimString("DWZH"));
				mx.setTjr(row.getTrimString("TJR"));
				mx.setTjrq(row.getTrimString("TJRQ"));
				mx.setZqrq(row.getTrimString("ZQRQ"));
				
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������˹鵵-֧ȡ�鵵  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}

	/**
	 * ����ɨ��¼��-¼�񵵰�
	 * @author zwh 2011��12��17��
	 *
	 */
	@WebMethod
	public LxdaMXBean[] getLxdaData(LxdaMXBean bean,int pagesize, int pagenum) {
		String trace = "";
		LxdaMXBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetLxdaData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new LxdaMXBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				LxdaMXBean mx=new LxdaMXBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setCjr(row.getTrimString("CJR"));
				mx.setCjrq(row.getTrimString("CJRQ"));
				mx.setTm(row.getTrimString("TM"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>����ɨ��¼��-¼�񵵰�  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ����ɨ��¼��-ʵ�ﵵ��
	 * @author zwh 2011��12��17��
	 *
	 */
	@WebMethod
	public SwdalrMXBean[] getSwlrdaData(SwdalrMXBean bean,int pagesize, int pagenum) {
		String trace = "";
		SwdalrMXBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetSwdalrData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new SwdalrMXBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				SwdalrMXBean mx=new SwdalrMXBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setCjr(row.getTrimString("CJR"));
				mx.setCjrq(row.getTrimString("CJRQ"));
				mx.setTm(row.getTrimString("TM"));
				mx.setZtlb(row.getTrimString("ZTLB"));
				
				mx.setZt(row.getTrimString("ZT"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setThyy(row.getTrimString("THYY"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));
				
				mx.setYwlsh(row.getTrimString("YWLSH"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>����ɨ��¼��-ʵ�ﵵ��  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ����ɨ��¼��-֪ʶ����
	 * @author zwh 2011��12��17��
	 *
	 */
	@WebMethod
	public ZsdalrBean[] getZslrdaData(ZsdalrBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZsdalrBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetZsdalrData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new ZsdalrBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZsdalrBean mx=new ZsdalrBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setCjr(row.getTrimString("CJR"));
				mx.setCjrq(row.getTrimString("CJRQ"));
				mx.setTm(row.getTrimString("TM"));
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>����ɨ��¼��-֪ʶ����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ����ɨ��¼��-���µ���
	 * @author zwh 2011��12��17��
	 *
	 */
	@WebMethod
	public RsdalrBean[] getRslrdaData(RsdalrBean bean,int pagesize, int pagenum) {
		String trace = "";
		RsdalrBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetRslrdaData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new RsdalrBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				RsdalrBean mx=new RsdalrBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setEname(row.getTrimString("ENAME"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setBh(row.getTrimString("BH"));
				mx.setSex(row.getTrimString("SEX"));
				
				mx.setMz(row.getTrimString("MZ"));
				mx.setSfzh(row.getTrimString("SFZH"));
				mx.setCsrq(row.getTrimString("CSRQ"));
				mx.setAge(row.getDefInt("AGE"));
				mx.setHyzk(row.getTrimString("HYZK"));
				
				mx.setCjgzsj(row.getTrimString("CJGZSJ"));
				mx.setGl(row.getDefInt("GL"));
				mx.setRdtsj(row.getTrimString("RDTSJ"));
				mx.setDh(row.getTrimString("DH"));
				mx.setPhone(row.getTrimString("PHONE"));
				
				mx.setEmail(row.getTrimString("EMAIL"));
				mx.setQq(row.getTrimString("QQ"));
				mx.setXl(row.getTrimString("XL"));
				mx.setSchool(row.getTrimString("SCHOOL"));
				mx.setZy(row.getTrimString("ZY"));

				mx.setAddress(row.getTrimString("ADDRESS"));
				mx.setHkszd(row.getTrimString("HKSZD"));
				mx.setJg(row.getTrimString("JG"));
				mx.setDjrq(row.getTrimString("DJRQ"));
				mx.setDjr(row.getTrimString("DJR"));
				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setYwlsh(row.getTrimString("YWLSH"));
				mx.setTypeb(row.getTrimString("F_TYPEB"));			
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>����ɨ��¼��-���µ���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ����ɨ��¼��-���ƻ��˵���
	 * @author zwh 2011��12��17��
	 *
	 */
	@WebMethod
	public FzjhdaLrBean[] getFzjhdaLrData(FzjhdaLrBean bean,int pagesize, int pagenum) {
		String trace = "";
		FzjhdaLrBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetFzjhdaLrData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new FzjhdaLrBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				FzjhdaLrBean mx=new FzjhdaLrBean();

				mx.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				mx.setCjr(row.getTrimString("CJR"));
				mx.setYgdh(row.getTrimString("YGDH"));
				mx.setCjrq(row.getTrimString("CJRQ"));
				mx.setTm(row.getTrimString("TM"));
				
				mx.setZtlb(row.getTrimString("ZTLB"));
				mx.setZt(row.getTrimString("ZT"));
				mx.setThyy(row.getTrimString("THYY"));				
				mx.setType(row.getTrimString("F_TYPE"));
				mx.setYwlsh(row.getTrimString("YWLSH"));
				
				mx.setTypeb(row.getTrimString("F_TYPEB"));			
				mx.setCount(count);//
				
				arr[i++] = mx;
			}
		}catch (Exception e) {
			trace = "<font color='red'>����ɨ��¼��-���ƻ��˵���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ����ɨ��¼��-���鵵����ѯ
	 * @author zwh 2011-12-13
	 */
	@WebMethod
	public WsdaMXBean[] getWsdaData(WsdaMXBean bean,int pagesize, int pagenum) {
		String trace = "";
		WsdaMXBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetWsdaData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new WsdaMXBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				WsdaMXBean wsda=new WsdaMXBean();

				wsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				wsda.setZrz(row.getTrimString("ZRZ"));
				wsda.setZrbm(row.getTrimString("ZRBM"));
				wsda.setCjr(row.getTrimString("CJR"));
				wsda.setCjsj(row.getTrimString("CJSJ"));//����ʱ��
			System.out.println("===========================wsda.getCjsj"+wsda.getCjsj());	
				wsda.setYjml(row.getTrimString("YJML"));
				wsda.setEjml(row.getTrimString("EJML"));
				wsda.setSjml(row.getTrimString("SJML"));
				wsda.setTm(row.getTrimString("TM"));
				wsda.setYgdh(row.getTrimString("YGDH"));
				
				wsda.setType(row.getTrimString("F_TYPE"));
				wsda.setYwlsh(row.getTrimString("YWLSH"));
				wsda.setTypeb(row.getTrimString("F_TYPEB"));
				wsda.setCount(count);//
				arr[i++] = wsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>����ɨ��¼��-���鵵��  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ����ɨ��¼��-�Ƽ�����¼��
	 * @author zwh 2011-12-15
	 *
	 */
	@WebMethod
	public KjMXBean[] getKjData(KjMXBean bean,int pagesize, int pagenum) {
		String trace = "";
		KjMXBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetKjData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new KjMXBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				KjMXBean kj=new KjMXBean();

				kj.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				kj.setCjr(row.getTrimString("CJR"));
				kj.setCjrq(row.getTrimString("CJRQ"));
				kj.setTm(row.getTrimString("TM"));
				kj.setYgdh(row.getTrimString("YGDH"));
				
				kj.setType(row.getTrimString("F_TYPE"));
				kj.setYwlsh(row.getTrimString("YWLSH"));
				kj.setTypeb(row.getTrimString("F_TYPEB"));
				kj.setCount(count);//
				arr[i++] = kj;
			}
		}catch (Exception e) {
			trace = "<font color='red'>����ɨ��¼��-�Ƽ�����¼��  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	
	@WebMethod
	public ZpMXBean[] getZpData(ZpMXBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZpMXBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetZpData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new ZpMXBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZpMXBean zp=new ZpMXBean();

				zp.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				zp.setCjr(row.getTrimString("CJR"));
				zp.setCjrq(row.getTrimString("CJRQ"));
				zp.setTm(row.getTrimString("TM"));
				zp.setCount(count);//
				arr[i++] = zp;
			}
		}catch (Exception e) {
			trace = "<font color='red'>����ɨ��¼��-��Ƭ����¼��  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ϵͳ����-����Ŀ¼�嵥
	 * @author jcl 2012-1-6
	 *
	 */
	@WebMethod
	public DaSzJnmlBean[] getJnmlqd(DaSzJnmlBean bean,int pagesize, int pagenum) {
		DaSzJnmlBean[] arr =null; 
		String trace = "";
		int count = 0;
		try {
			List<Row> lst =DbMethod.makeDbSingleton().Open("DA_GetJnmlqdData",bean,pagesize,pagenum);
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DaSzJnmlBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DaSzJnmlBean jn=new DaSzJnmlBean();
				jn.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				jn.setClbm(row.getTrimString("CLBM"));
				jn.setCl(row.getTrimString("CL"));
				jn.setCount(count);
				arr[i++] = jn;
			}
		}catch (Exception e) {
			trace = "<font color='red'>ϵͳ����-����Ŀ¼�嵥---��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		}
		return arr;
	}
	/**
	 * ϵͳ����-���⵵����
	 * @author jcl 2012-1-6
	 *
	 */
	@WebMethod
	public DagszBean[] getXndagsz(DagszBean bean,int pagesize, int pagenum) {
		DagszBean[] arr =null; 
		String trace = "";
		int count = 0;
		try {
			List<Row> lst =DbMethod.makeDbSingleton().Open("DA_GetXndagszData",bean,pagesize,pagenum);
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DagszBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DagszBean jn=new DagszBean();
				jn.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				jn.setDash(row.getTrimString("DASH"));
				jn.setGh(row.getTrimString("GH"));
				jn.setGrow(row.getTrimString("GROW"));
				jn.setHh(row.getTrimString("HH"));
				jn.setCount(count);
				arr[i++] = jn;
			}
		}catch (Exception e) {
			trace = "<font color='red'>ϵͳ����-���⵵��������---��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		}
		return arr;
	}
	/**
	 * ϵͳ����-�ļ��б�
	 * @author  jcl 2012-1-6
	 *
	 */
	@WebMethod
	public WslbBean[] getWjlb(WslbBean bean,int pagesize, int pagenum) {
		WslbBean[] arr =null; 
		String trace = "";
		int count = 0;
		try {
			List<Row> lst =DbMethod.makeDbSingleton().Open("DA_GetWjlbData",bean,pagesize,pagenum);
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new WslbBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				WslbBean jn=new WslbBean();
				jn.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				jn.setFirst(row.getTrimString("FIRST"));
				jn.setSecond(row.getTrimString("SECOND"));
				jn.setRank(row.getTrimString("RANK"));
				jn.setCount(count);
				arr[i++] = jn;
			}
		}catch (Exception e) {
			trace = "<font color='red'>ϵͳ����-�ļ��б�---��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		}
		return arr;
	}
	/**
	 * ϵͳ����-��������
	 * @author  jcl 2012-1-6
	 *
	 */
	@WebMethod
	public DaLxBean[] getDalxDate(DaLxBean bean,int pagesize, int pagenum) {
		DaLxBean[] arr =null; 
		String trace = "";
		int count = 0;
		try {
			List<Row> lst =DbMethod.makeDbSingleton().Open("DA_GetDalxData",bean,pagesize,pagenum);
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DaLxBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DaLxBean jn=new DaLxBean();
				jn.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				jn.setType(row.getTrimString("F_TYPE"));
				jn.setLxbz(row.getTrimString("LXBZ"));
				jn.setCount(count);
				arr[i++] = jn;
			}
		}catch (Exception e) {
			trace = "<font color='red'>ϵͳ����-��������---��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		}
		return arr;
	}
	
	/**
	 * ������Ϣ���->�������
	 * @author zy 2012-1-10
	 */
	@WebMethod
	public DajbxxBean[] getDaxxbgglData(DajbxxBean bean,int pagesize, int pagenum) {
		String trace = "";
		DajbxxBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetDaxxbgData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DajbxxBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DajbxxBean dabg=new DajbxxBean();

				dabg.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				dabg.setDah(row.getTrimString("DAH")) ;
				dabg.setGh(row.getTrimString("GH")) ;
				dabg.setHh(row.getTrimString("HH")) ;
				dabg.setBcnx(row.getDefInt("BCNX")) ;
				dabg.setBmjb(row.getTrimString("BMJB")) ;
				dabg.setClfs(row.getDefInt("CLFS")) ;
				dabg.setTjr(row.getTrimString("TJR")) ;
				dabg.setTjrq(row.getTrimString("TJRQ")) ;
				dabg.setJsr(row.getTrimString("JSR")) ;
				dabg.setJsrq(row.getTrimString("JSRQ")) ;
				dabg.setCfwz(row.getTrimString("CFWZ")) ;
				dabg.setF_type(row.getTrimString("F_TYPE")) ;
				dabg.setYgdh(row.getTrimString("YGDH")) ;
				dabg.setYwlsh(row.getTrimString("YWLSH")) ;
				dabg.setF_typeb(row.getTrimString("F_TYPEB")) ;
				
				dabg.setCount(count);//
				
				arr[i++] = dabg;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ���->�������  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������Ϣ���->׷�ӹ���
	 * @author zy 2012-1-10
	 */
	@WebMethod
	public DajbxxBean[] getzjglData(DajbxxBean bean,int pagesize, int pagenum) {
		String trace = "";
		DajbxxBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetZjglData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DajbxxBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DajbxxBean dabg=new DajbxxBean();

				dabg.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				dabg.setDah(row.getTrimString("DAH")) ;
				dabg.setGh(row.getTrimString("GH")) ;
				dabg.setHh(row.getTrimString("HH")) ;
				dabg.setBcnx(row.getDefInt("BCNX")) ;
				dabg.setBmjb(row.getTrimString("BMJB")) ;
				dabg.setClfs(row.getDefInt("CLFS")) ;
				dabg.setTjr(row.getTrimString("TJR")) ;
				dabg.setTjrq(row.getTrimString("TJRQ")) ;
				dabg.setJsr(row.getTrimString("JSR")) ;
				dabg.setJsrq(row.getTrimString("JSRQ")) ;
				dabg.setCfwz(row.getTrimString("CFWZ")) ;
				dabg.setF_type(row.getTrimString("F_TYPE")) ;
				dabg.setYgdh(row.getTrimString("YGDH")) ;
				dabg.setYwlsh(row.getTrimString("YWLSH")) ;
				dabg.setF_typeb(row.getTrimString("F_TYPEB")) ;
				dabg.setZxrq(row.getTrimString("ZXRQ")) ;
				dabg.setSxh(row.getTrimString("SXH")) ;
				dabg.setCount(count);//
				
				arr[i++] = dabg;
			}
		}catch (Exception e) {
			trace = "<font color='red'> ������Ϣ���->׷�ӹ���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ����ɨ��¼��-��������
	 * @author zy 2012-1-10
	 */
	@WebMethod
	public DajbxxBean[] getccglData(DajbxxBean bean,int pagesize, int pagenum) {
		String trace = "";
		DajbxxBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetCcglData",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DajbxxBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DajbxxBean dabg=new DajbxxBean();

				dabg.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				dabg.setDah(row.getTrimString("DAH")) ;
				dabg.setGh(row.getTrimString("GH")) ;
				dabg.setHh(row.getTrimString("HH")) ;
				dabg.setBcnx(row.getDefInt("BCNX")) ;
				dabg.setBmjb(row.getTrimString("BMJB")) ;
				dabg.setClfs(row.getDefInt("CLFS")) ;
				dabg.setTjr(row.getTrimString("TJR")) ;
				dabg.setTjrq(row.getTrimString("TJRQ")) ;
				dabg.setJsr(row.getTrimString("JSR")) ;
				dabg.setJsrq(row.getTrimString("JSRQ")) ;
				dabg.setCfwz(row.getTrimString("CFWZ")) ;
				dabg.setF_type(row.getTrimString("F_TYPE")) ;
				dabg.setYgdh(row.getTrimString("YGDH")) ;
				dabg.setYwlsh(row.getTrimString("YWLSH")) ;
				dabg.setF_typeb(row.getTrimString("F_TYPEB")) ;
				dabg.setZxrq(row.getTrimString("ZXRQ")) ;
				dabg.setSxh(row.getTrimString("SXH")) ;
				
				dabg.setCount(count);//
				
				arr[i++] = dabg;
			}
		}catch (Exception e) {
			trace = "<font color='red'>����ɨ��¼��-��������  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * �������-������-��λ�鼯�������
	 * @author zy 2012-1-10
	 */
	@WebMethod
	public DwdaBean[] getBgglDwgjdaData(DwdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		DwdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglDwgjdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new DwdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DwdaBean dwda=new DwdaBean();

				dwda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				dwda.setF_type(row.getTrimString("F_TYPE"));
				dwda.setDah(row.getTrimString("DAH"));
				dwda.setGrow(row.getTrimString("GROW"));
				dwda.setDash(row.getTrimString("DASH"));
				dwda.setGh(row.getTrimString("GH"));
				dwda.setHh(row.getTrimString("HH"));
				dwda.setBmjb(row.getTrimString("BMJB"));
				dwda.setDw(row.getTrimString("DW"));
				dwda.setDwzh(row.getTrimString("DWZH"));
				dwda.setGjd(row.getTrimString("GJD"));
				dwda.setCbwd(row.getTrimString("CBWD"));
				dwda.setCfwz(row.getTrimString("CFWZ"));
				dwda.setKhrq(row.getTrimString("KHRQ"));
				dwda.setDwdz(row.getTrimString("DWDZ"));
				dwda.setGjzgy(row.getTrimString("GJZGY"));
				dwda.setXhrq(row.getTrimString("XHRQ"));
				dwda.setFcrq(row.getTrimString("FCRQ"));
				dwda.setF_typeb(row.getTrimString("F_TYPEB")) ;
				dwda.setCount(count);
				
				arr[i++] = dwda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>�������-������-��λ�鼯�������  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������Ϣ�������-������-���˹鼯����
	 * @author zy 2012-1-11
	 *
	 */
	@WebMethod
	public GjdaBean[] getBgglGrgjdaData(GjdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		GjdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglGrgjdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new GjdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				GjdaBean gjda=new GjdaBean();

				gjda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				gjda.setF_type(row.getTrimString("F_TYPE"));
				gjda.setDah(row.getTrimString("DAH"));
				gjda.setGrow(row.getTrimString("GROW"));
				gjda.setDash(row.getTrimString("DASH"));
				gjda.setGh(row.getTrimString("GH"));
				gjda.setHh(row.getTrimString("HH"));
				gjda.setBmjb(row.getTrimString("BMJB"));
				gjda.setZgzh(row.getTrimString("ZGZH"));
				gjda.setZgxm(row.getTrimString("ZGXM"));
				gjda.setDw(row.getTrimString("DW"));
				gjda.setDwzh(row.getTrimString("DWZH"));
				gjda.setGjd(row.getTrimString("GJD"));
				gjda.setCbwd(row.getTrimString("CBWD"));
				gjda.setCfwz(row.getTrimString("CFWZ"));
				gjda.setXb(row.getTrimString("XB"));
				gjda.setKhrq(row.getTrimString("KHRQ"));
				gjda.setJsr(row.getTrimString("JSR"));
				gjda.setJsrq(row.getTrimString("JSRQ"));
				gjda.setYgdh(row.getTrimString("YGDH"));
				gjda.setZjhm(row.getTrimString("ZJHM"));
				gjda.setYgdh(row.getTrimString("YGDH")) ;
				gjda.setF_typeb(row.getTrimString("F_TYPEB")) ;
				gjda.setCount(count);
				arr[i++] = gjda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-���˹鼯����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������Ϣ�������-������-�����
	 * @author zy 2012-1-11
	 *
	 */
	@WebMethod
	public DkdaBean[] getBgglDkgjdaData(DkdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		DkdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglDkgjdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new DkdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				DkdaBean dkda=new DkdaBean();

				dkda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				dkda.setF_type(row.getTrimString("F_TYPE"));
				dkda.setDah(row.getTrimString("DAH"));
				dkda.setGrow(row.getTrimString("GROW"));
				dkda.setDash(row.getTrimString("DASH"));
				dkda.setGh(row.getTrimString("GH"));
				dkda.setHh(row.getTrimString("HH"));
				dkda.setBmjb(row.getTrimString("BMJB"));
				dkda.setZgzh(row.getTrimString("ZGZH"));
				dkda.setZgxm(row.getTrimString("ZGXM"));
				dkda.setDw(row.getTrimString("DW"));
				dkda.setDwzh(row.getTrimString("DWZH"));
				dkda.setDknx(Integer.parseInt(row.getTrimString("DKNX")));
				dkda.setGflx(row.getTrimString("GFLX"));
				dkda.setGjd(row.getTrimString("GJD"));
				dkda.setCbwd(row.getTrimString("CBWD"));
				dkda.setCfwz(row.getTrimString("CFWZ"));
				dkda.setZjhm(row.getTrimString("ZJHM")) ;
				dkda.setBmjb(row.getTrimString("BMJB")) ;
				dkda.setF_typeb(row.getTrimString("F_TYPEB")) ;
				dkda.setCount(count);
				arr[i++] = dkda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-�����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 *  ������Ϣ�������-������-֧ȡ����
	 * @author zy 2012-1 -11
	 *
	 */
	@WebMethod
	public ZqdaBean[] getBgglZqdaData(ZqdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZqdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglZqdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new ZqdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZqdaBean zqda=new ZqdaBean();

				zqda.setId(Integer.parseInt(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				zqda.setF_type(row.getTrimString("F_TYPE"));
				zqda.setDah(row.getTrimString("DAH"));
				zqda.setGrow(row.getTrimString("GROW"));
				zqda.setDash(row.getTrimString("DASH"));
				zqda.setGh(row.getTrimString("GH"));
				zqda.setHh(row.getTrimString("HH"));
				zqda.setBmjb(row.getTrimString("BMJB"));
				zqda.setZgzh(row.getTrimString("ZGZH"));
				zqda.setZgxm(row.getTrimString("ZGXM"));
				zqda.setDw(row.getTrimString("DW"));
				zqda.setDwzh(row.getTrimString("DWZH"));
				zqda.setCfwz(row.getTrimString("CFWZ"));
				zqda.setZjhm(row.getTrimString("ZJHM"));
				zqda.setZqrq(row.getTrimString("ZQRQ"));
				zqda.setYgdh(row.getTrimString("YGDH"));
				zqda.setF_typeb(row.getTrimString("F_TYPEB"));
				zqda.setCount(count);
				arr[i++] = zqda;
			}
		}catch (Exception e) {
			trace = "<font color='red'> ������Ϣ�������-������-֧ȡ������" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	
	/**
	 * ������Ϣ�������-������-¥�̵���
	 * @author zy 2012-1 -11
	 *
	 */
	@WebMethod
	public LpdaBean[] getBgglLpdaData(LpdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		LpdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglLpdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new LpdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				LpdaBean zqda=new LpdaBean();

				zqda.setId(Integer.parseInt(row.getTrimString("ID")));//ȡsql������,�����ֶξ���						
				zqda.setF_type(row.getTrimString("F_TYPE"));
				zqda.setDah(row.getTrimString("DAH"));
				zqda.setGrow(row.getTrimString("GROW"));
				zqda.setDash(row.getTrimString("DASH"));
				zqda.setGh(row.getTrimString("GH"));
				zqda.setHh(row.getTrimString("HH"));
				zqda.setBmjb(row.getTrimString("BMJB"));
				zqda.setCfwz(row.getTrimString("CFWZ"));
				zqda.setYgdh(row.getTrimString("YGDH"));
				zqda.setXm(row.getTrimString("XM"));
				zqda.setXmbh(row.getTrimString("XMBM"));
				zqda.setJsrq(row.getTrimString("JSRQ"));
				zqda.setCbwd(row.getTrimString("CBWD"));
				zqda.setGjd(row.getTrimString("GJD")) ;
				zqda.setF_type(row.getTrimString("F_TYPEB"));
				zqda.setCount(count);
				arr[i++] = zqda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-¥�̵���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	
	/**
	 *  ������Ϣ�������-������-���鵵�� 
	 * @author zy 2012-1 -12
	 *
	 */
	@WebMethod
	public WsdaBean[] getBgglWsdaData(WsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		WsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglWsdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new WsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				WsdaBean wsda=new WsdaBean();

				wsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				wsda.setYjml(row.getTrimString("YJML"));
				wsda.setEjml(row.getTrimString("EJML"));
				wsda.setSjml(row.getTrimString("SJML"));
				wsda.setTm(row.getTrimString("TM"));
				wsda.setYgdh(row.getTrimString("YGDH"));			
				wsda.setF_type(row.getTrimString("F_TYPE"));
				wsda.setDah(row.getTrimString("DAH"));
				wsda.setTjr(row.getTrimString("TJR"));
				wsda.setTjrq(row.getTrimString("TJRQ"));
				wsda.setGrow(row.getTrimString("GROW"));
				wsda.setDash(row.getTrimString("DASH"));
				wsda.setGh(row.getTrimString("GH"));
				wsda.setHh(row.getTrimString("HH"));
				wsda.setSxh(row.getTrimString("SXH"));
				wsda.setJsr(row.getTrimString("JSR"));
				wsda.setJsrq(row.getTrimString("JSRQ"));
				wsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				wsda.setZxr(row.getTrimString("ZXR"));
				wsda.setZxrq(row.getTrimString("ZXRQ"));
				wsda.setZxyy(row.getTrimString("ZXYY"));
				wsda.setClfs(Integer.parseInt(row.getTrimString("CLFS")));
				wsda.setZrz(row.getTrimString("ZRZ"));
				wsda.setCfwz(row.getTrimString("CFWZ"));
				wsda.setF_state(row.getTrimString("F_STATE"));
				wsda.setBmjb(row.getTrimString("BMJB")) ;
				wsda.setYwlsh(row.getTrimString("YWLSH")) ;
				wsda.setCount(count);
				arr[i++] = wsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-���鵵��  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	
	/**
	 * ������Ϣ�������-������-�Ƽ�����
	 * @author zy 2012-1 -12
	 *
	 */
	@WebMethod
	public ZldaBean[] getBgglKjdaData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglKjdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean zlda=new ZldaBean();

				zlda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				zlda.setTm(row.getTrimString("TM"));
				zlda.setYgdh(row.getTrimString("YGDH"));			
				zlda.setF_type(row.getTrimString("F_TYPE"));
				zlda.setDah(row.getTrimString("DAH"));
				zlda.setCjr(row.getTrimString("CJR"));
				zlda.setGrow(row.getTrimString("GROW"));
				zlda.setDash(row.getTrimString("DASH"));
				zlda.setGh(row.getTrimString("GH"));
				zlda.setHh(row.getTrimString("HH"));
				zlda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				zlda.setCjrq(row.getTrimString("CJRQ"));
				zlda.setYwlsh(row.getTrimString("YWLSH"));
				zlda.setBmjb(row.getTrimString("BMJB"));
				zlda.setF_typeb(row.getTrimString("F_TYPEB"));
				zlda.setDah(row.getTrimString("DAH")) ;
				zlda.setCount(count);
				arr[i++] = zlda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-�Ƽ�����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ������Ϣ�������-������-��Ƭ����
	 * @author zy 2012-1 -12
	 *
	 */
	@WebMethod
	public TsdaBean[] getBgglZpdaData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglZpdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new TsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				TsdaBean tsda=new TsdaBean();

				tsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				tsda.setTm(row.getTrimString("TM"));
				tsda.setYgdh(row.getTrimString("YGDH"));			
				tsda.setF_type(row.getTrimString("F_TYPE"));
				tsda.setDah(row.getTrimString("DAH"));
				tsda.setCjr(row.getTrimString("CJR"));
				tsda.setGrow(row.getTrimString("GROW"));
				tsda.setDash(row.getTrimString("DASH"));
				tsda.setGh(row.getTrimString("GH"));
				tsda.setHh(row.getTrimString("HH"));
				tsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				tsda.setCjrq(row.getTrimString("CJRQ"));
				tsda.setYwlsh(row.getTrimString("YWLSH"));
				tsda.setBmjb(row.getTrimString("BMJB"));
				tsda.setF_typeb(row.getTrimString("F_TYPEB"));
				tsda.setCount(count);
				arr[i++] = tsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-��Ƭ����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ������Ϣ�������-������-¼�񵵰�
	 * @author zy 2011-1-12
	 *
	 */
	@WebMethod
	public TsdaBean[] getBgglLxdaData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglLxdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new TsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				TsdaBean tsda=new TsdaBean();

				tsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				tsda.setTm(row.getTrimString("TM"));
				tsda.setYgdh(row.getTrimString("YGDH"));			
				tsda.setF_type(row.getTrimString("F_TYPE"));
				tsda.setDah(row.getTrimString("DAH"));
				tsda.setCjr(row.getTrimString("CJR"));
				tsda.setGrow(row.getTrimString("GROW"));
				tsda.setDash(row.getTrimString("DASH"));
				tsda.setGh(row.getTrimString("GH"));
				tsda.setHh(row.getTrimString("HH"));
				tsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				tsda.setCjrq(row.getTrimString("CJRQ"));
				tsda.setYwlsh(row.getTrimString("YWLSH"));
				tsda.setBmjb(row.getTrimString("BMJB"));
				tsda.setF_typeb(row.getTrimString("F_TYPEB"));
				tsda.setCount(count);
				arr[i++] = tsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-¼�񵵰�  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������Ϣ�������-������-ʵ�ﵵ��
	 * @author zy 2012-1-12
	 *
	 */
	@WebMethod
	public ZldaBean[] getBgglSwdaData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglSwdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean zlda=new ZldaBean();

				zlda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				zlda.setTm(row.getTrimString("TM"));
				zlda.setYgdh(row.getTrimString("YGDH"));			
				zlda.setF_type(row.getTrimString("F_TYPE"));
				zlda.setDah(row.getTrimString("DAH"));
				zlda.setCjr(row.getTrimString("CJR"));
				zlda.setGrow(row.getTrimString("GROW"));
				zlda.setDash(row.getTrimString("DASH"));
				zlda.setGh(row.getTrimString("GH"));
				zlda.setHh(row.getTrimString("HH"));
				zlda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				zlda.setCjrq(row.getTrimString("CJRQ"));
				zlda.setYwlsh(row.getTrimString("YWLSH"));
				zlda.setBmjb(row.getTrimString("BMJB"));
				zlda.setF_typeb(row.getTrimString("F_TYPEB"));
				zlda.setCount(count);
				arr[i++] = zlda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-ʵ�ﵵ��  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������Ϣ�������-������-֪ʶ����
	 * @author zy 2012-1-12
	 *
	 */
	@WebMethod
	public TsdaBean[] getBgglZsdaData(TsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		TsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglZsdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new TsdaBean[lst.size()];
			System.out.println(count+"-0-0-0-0-0-0-0");
			int i = 0;

			for (Row row : lst) {
				TsdaBean tsda=new TsdaBean();

				tsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				tsda.setTm(row.getTrimString("TM"));
				tsda.setYgdh(row.getTrimString("YGDH"));			
				tsda.setF_type(row.getTrimString("F_TYPE"));
				tsda.setDah(row.getTrimString("DAH"));
				tsda.setCjr(row.getTrimString("CJR"));
				tsda.setGrow(row.getTrimString("GROW"));
				tsda.setDash(row.getTrimString("DASH"));
				tsda.setGh(row.getTrimString("GH"));
				tsda.setHh(row.getTrimString("HH"));
				tsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				tsda.setCjrq(row.getTrimString("CJRQ"));
				tsda.setYwlsh(row.getTrimString("YWLSH"));
				tsda.setBmjb(row.getTrimString("BMJB"));
				tsda.setF_typeb(row.getTrimString("F_TYPEB"));
				tsda.setCount(count);
				arr[i++] = tsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-֪ʶ����  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ������Ϣ�������-������-���µ���
	 * @author zy 2012-1-12
	 *
	 */
	@WebMethod
	public RsdaBean[] getBgglRsdaData(RsdaBean bean,int pagesize, int pagenum) {
		String trace = "";
		RsdaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglRsdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new RsdaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				RsdaBean rsda=new RsdaBean();

				rsda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				rsda.setYgdh(row.getTrimString("YGDH"));			
				rsda.setF_type(row.getTrimString("F_TYPE"));
				rsda.setDah(row.getTrimString("DAH"));
				rsda.setGrow(row.getTrimString("GROW"));
				rsda.setDash(row.getTrimString("DASH"));
				rsda.setGh(row.getTrimString("GH"));
				rsda.setHh(row.getTrimString("HH"));
				rsda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				rsda.setYwlsh(row.getTrimString("YWLSH"));
				rsda.setBmjb(row.getTrimString("BMJB"));
				rsda.setName(row.getTrimString("ENAME"));
				rsda.setDjr(row.getTrimString("DJR"));
				rsda.setDjrq(row.getTrimString("DJRQ"));
				rsda.setF_typeb(row.getTrimString("F_TYPEB"));
				rsda.setCount(count);
				arr[i++] = rsda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-���µ��� ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ������Ϣ�������-������-���Ƶ���
	 * @author zy 2012-1-12
	 *
	 */
	@WebMethod
	public ZldaBean[] getBgglFzdaData(ZldaBean bean,int pagesize, int pagenum) {
		String trace = "";
		ZldaBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_getBgglFzdaData",bean,pagesize,pagenum);
			
			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			System.out.println(count+"-0-0-0-0-0-0-0");
			arr= new ZldaBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				ZldaBean zlda=new ZldaBean();

				zlda.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���			
				zlda.setTm(row.getTrimString("TM"));
				zlda.setYgdh(row.getTrimString("YGDH"));			
				zlda.setF_type(row.getTrimString("F_TYPE"));
				zlda.setDah(row.getTrimString("DAH"));
				zlda.setCjr(row.getTrimString("CJR"));
				zlda.setGrow(row.getTrimString("GROW"));
				zlda.setDash(row.getTrimString("DASH"));
				zlda.setGh(row.getTrimString("GH"));
				zlda.setHh(row.getTrimString("HH"));
				zlda.setBcnx(Integer.parseInt(row.getTrimString("BCNX")));
				zlda.setCjrq(row.getDateToString("CJRQ"));
				zlda.setYwlsh(row.getTrimString("YWLSH"));
				zlda.setBmjb(row.getTrimString("BMJB"));
				zlda.setF_typeb(row.getTrimString("F_TYPEB"));
				zlda.setCount(count);
				arr[i++] = zlda;
			}
		}catch (Exception e) {
			trace = "<font color='red'>������Ϣ�������-������-���Ƶ���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ���Ĺ���-ʵ����Ĳ�ѯ
	 * @author xy  2012-1-10
	 *
	 */
	@WebMethod
	public JYBean[] getStjy(JYBean bean,int pagesize, int pagenum) {
		String trace = "";
		JYBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetStjy",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new JYBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				JYBean zp=new JYBean();

				zp.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				zp.setCjr(row.getTrimString("CJR"));
//				zp.setCjrq(row.getTrimString("CJRQ"));
//				zp.setTm(row.getTrimString("TM"));
				zp.setCount(count);//
				arr[i++] = zp;
			}
		}catch (Exception e) {
			trace = "<font color='red'>���Ĺ���-ʵ����Ĳ�ѯ  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	
	/**
	 * ���Ĺ���-�ѽ���ʵ���ѯ
	 * @author xy  2012-1-10
	 *
	 */
	@WebMethod
	public JYBean[] getYjystda(JYBean bean,int pagesize, int pagenum) {
		String trace = "";
		JYBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetYjystda",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new JYBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				JYBean zp=new JYBean();

				zp.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
//				  System.out.println("====================ID=");
			   zp.setF_state(row.getTrimString("F_STATE"));
				zp.setDah(row.getTrimString("DAH"));
//				  System.out.println("===================DAH=");
				zp.setDalx(row.getTrimString("DALX"));
	//			  System.out.println("====================DALX=");
				zp.setJyjs(row.getDefInt("JYJS"));
//				  System.out.println("====================JYJS=");
				zp.setJyr(row.getTrimString("JYR"));
//				  System.out.println("====================JYR=");
				zp.setJymd(row.getTrimString("JYMD"));
//				  System.out.println("====================JYMD=");
				zp.setTjrq(row.getTrimString("JYRQ"));
			//	  System.out.println("====================JYRQ=");
				zp.setJsrq(row.getTrimString("NHRQ"));
		//		  System.out.println("====================NHRQ=");
				zp.setDjr(row.getTrimString("DJR"));
	//			  System.out.println("====================DJR=");
				zp.setGh(row.getTrimString("GH"));
//				  System.out.println("===================GH=");
				zp.setHh(row.getTrimString("HH"));
//				  System.out.println("===================HH=");
				zp.setCount(count);//
				arr[i++] = zp;
			}
		}catch (Exception e) {
			trace = "<font color='red'>���Ĺ���-�ѽ���ʵ���ѯ  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ���Ĺ���-�ѹ黹��ʵ���ѯ
	 * @author xy  2012-1-10
	 *
	 */
	@WebMethod
	public JYBean[] getYghstda(JYBean bean,int pagesize, int pagenum) {
		String trace = "";
		JYBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetYghstda",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new JYBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				JYBean zp=new JYBean();

				zp.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
//				  System.out.println("====================ID=");
			   zp.setF_state(row.getTrimString("F_STATE"));
				zp.setDah(row.getTrimString("DAH"));
//				  System.out.println("===================DAH=");
				zp.setDalx(row.getTrimString("DALX"));
	//			  System.out.println("====================DALX=");
				zp.setJyjs(row.getDefInt("JYJS"));
//				  System.out.println("====================JYJS=");
				zp.setJyr(row.getTrimString("JYR"));
//				  System.out.println("====================JYR=");
				zp.setJymd(row.getTrimString("JYMD"));
//				  System.out.println("====================JYMD=");
				zp.setCxrq1(row.getTrimString("GHRQ"));
				//	  System.out.println("====================JYRQ=");
				zp.setTjrq(row.getTrimString("JYRQ"));
			//	  System.out.println("====================JYRQ=");
				zp.setJsrq(row.getTrimString("NHRQ"));
		//		  System.out.println("====================NHRQ=");
				zp.setDjr(row.getTrimString("DJR"));
	//			  System.out.println("====================DJR=");
				zp.setGh(row.getTrimString("GH"));
//				  System.out.println("===================GH=");
				zp.setHh(row.getTrimString("HH"));
//				  System.out.println("===================HH=");
				zp.setCount(count);//
				arr[i++] = zp;
			}
		}catch (Exception e) {
			trace = "<font color='red'>���Ĺ���-�ѹ黹��ʵ���ѯ  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ���Ĺ���-����δ�黹��ʵ���ѯ
	 * @author xy  2012-1-10
	 *
	 */
	@WebMethod
	public JYBean[] getDqwghstda(JYBean bean,int pagesize, int pagenum) {
		String trace = "";
		JYBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetDqwghstda",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new JYBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				JYBean zp=new JYBean();

				zp.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
//				  System.out.println("====================ID=");
			   zp.setF_state(row.getTrimString("F_STATE"));
				zp.setDah(row.getTrimString("DAH"));
//				  System.out.println("===================DAH=");
				zp.setDalx(row.getTrimString("DALX"));
	//			  System.out.println("====================DALX=");
				zp.setJyjs(row.getDefInt("JYJS"));
//				  System.out.println("====================JYJS=");
				zp.setJyr(row.getTrimString("JYR"));
//				  System.out.println("====================JYR=");
				zp.setJymd(row.getTrimString("JYMD"));
//				  System.out.println("====================JYMD=");
				zp.setTjrq(row.getTrimString("JYRQ"));
			//	  System.out.println("====================JYRQ=");
				zp.setJsrq(row.getTrimString("NHRQ"));
		//		  System.out.println("====================NHRQ=");
				zp.setDjr(row.getTrimString("DJR"));
	//			  System.out.println("====================DJR=");
				zp.setGh(row.getTrimString("GH"));
//				  System.out.println("===================GH=");
				zp.setHh(row.getTrimString("HH"));
//				  System.out.println("===================HH=");
				zp.setCount(count);//
				arr[i++] = zp;
			}
		}catch (Exception e) {
			trace = "<font color='red'>���Ĺ���-����δ�黹��ʵ���ѯ  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ���Ĺ���-�ҵĵ��ӵ����������뵥
	 * @author xy  2012-1-10
	 *
	 */
	@WebMethod
	public JYBean[] getWddzda(JYBean bean,int pagesize, int pagenum) {
		String trace = "";
		JYBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetWddzda",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new JYBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				JYBean zp=new JYBean();

				zp.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
//				  System.out.println("====================ID=");
			   zp.setF_state(row.getTrimString("F_STATE"));
			   
				zp.setDah(row.getTrimString("DAH"));
//				  System.out.println("===================DAH=");

				zp.setTjrq(row.getTrimString("JYRQ"));
			//	  System.out.println("====================JYRQ=");
				zp.setJsrq(row.getTrimString("GHRQ"));
		//		  System.out.println("====================NHRQ=");

				zp.setCount(count);//
				arr[i++] = zp;
			}
		}catch (Exception e) {
			trace = "<font color='red'>���Ĺ���-�ҵĵ��ӵ����������뵥  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ���Ĺ���-������ĵ��ӵ����������뵥
	 * @author xy  2012-1-10
	 *
	 */
	@WebMethod
	public JYBean[] getDcldzda(JYBean bean,int pagesize, int pagenum) {
		String trace = "";
		JYBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetDcldzda",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new JYBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				JYBean zp=new JYBean();

				zp.setId(Long.parseLong(row.getTrimString("F_DATA_ID")));//ȡsql������,�����ֶξ���
//				  System.out.println("====================ID=");

				zp.setDah(row.getTrimString("F_DATA_TITLE"));
//				  System.out.println("===================DAH=");
				zp.setF_state(row.getTrimString("F_PROCESS_DESCRIBE"));
	//			  System.out.println("====================DALX=");

				zp.setJyr(row.getTrimString("F_START_USER_NAME"));
//				  System.out.println("====================JYR=");

				zp.setTjrq(row.getTrimString("F_START_TIME"));
			//	  System.out.println("====================JYRQ=");
				zp.setCount(count);//
				arr[i++] = zp;
			}
		}catch (Exception e) {
			trace = "<font color='red'>���Ĺ���-������ĵ��ӵ����������뵥  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	/**
	 * ���Ĺ���-�ҽ��ĵĵ��ӵ���
	 * @author xy  2012-1-10
	 *
	 */
	@WebMethod
	public JYBean[] getWjydzda(JYBean bean,int pagesize, int pagenum) {
		String trace = "";
		JYBean[] arr=null;
		int count = 0;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_GetWjydzda",bean,pagesize,pagenum);

			count = lst.size() > 0 ? lst.get(0).getDefInt("count") : 0;
			arr= new JYBean[lst.size()];
			int i = 0;

			for (Row row : lst) {
				JYBean zp=new JYBean();

				zp.setId(Long.parseLong(row.getTrimString("ID")));//ȡsql������,�����ֶξ���
				  System.out.println("====================ID=");
			   zp.setF_state(row.getTrimString("F_STATE"));
				zp.setDah(row.getTrimString("DAH"));
				  System.out.println("===================DAH=");
				zp.setDalx(row.getTrimString("DALX"));
				  System.out.println("====================DALX=");
				zp.setJyjs(row.getDefInt("JYJS"));
				  System.out.println("====================JYJS=");
				zp.setJyr(row.getTrimString("JYR"));

				zp.setJymd(row.getTrimString("JYMD"));

				zp.setTjrq(row.getTrimString("JYRQ"));

				zp.setJsrq(row.getTrimString("NHRQ"));
		
				zp.setDjr(row.getTrimString("DJR"));

				zp.setGh(row.getTrimString("GH"));

				zp.setClfs(row.getDefInt("CLFS"));

				  
				  zp.setCxrq1(row.getTrimString("GHRQ"));

				  zp.setBmjb(row.getTrimString("BMJB"));
			
				  zp.setBcnx(row.getDefInt("BCNX"));
		
				  zp.setHh(row.getTrimString("HH"));
				
				  
	
				zp.setCount(count);//
				arr[i++] = zp;
			}
		}catch (Exception e) {
			trace = "<font color='red'>���Ĺ���-�ҽ��ĵĵ��ӵ���  ��ѯʧ�ܣ�" + e.getMessage() + "</font>";
			log.error(trace);
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return arr;
	}
	//�ض� ��ʷ����ģ��
	
	/**
	 * ������ʷ���� 2012-2-20 chg by HD
	 */
	@WebMethod
	public int ef_Dkdahr_Save(@WebParam(partName = "bean") DkdaHrecordBean bean) {
		try {
			DbMethod.makeDbSingleton().Execute("DA_DKDAHR_SAVE", bean);
		} catch (Exception e) {
			log.error(e.getMessage());
			return -1;
		}
		return 1;
	}

	/**
	 * �鼯��λ��ʷ���� 2012-2-16 add by HD
	 */
	@WebMethod
	public int ef_Dwdahr_Save(@WebParam(partName = "bean") DwdaHrecordBean bean) {
		try {
			DbMethod.makeDbSingleton().Execute("DA_DWDAHR_SAVE", bean);
		} catch (Exception e) {
			log.error(e.getMessage());
			return -1;
		}
		return 1;
	}

	/**
	 * ֧ȡ��ʷ���� 2012-2-16 add by HD
	 */
	@WebMethod
	public int ef_Zqdahr_Save(@WebParam(partName = "bean") ZqdaHrecordBean bean) {
		try {
			DbMethod.makeDbSingleton().Execute("DA_ZQDAHR_SAVE", bean);
		} catch (Exception e) {
			log.error(e.getMessage());
			return -1;
		}
		return 1;
	}

	/**
	 * ������ʷ���� 2012-2-16 add by HD
	 */
	@WebMethod
	public int ef_Cwdahr_Save(@WebParam(partName = "bean") CwdaHrecordBean bean) {
		try {
			DbMethod.makeDbSingleton().Execute("DA_CWDAHR_SAVE", bean);
		} catch (Exception e) {
			log.error(e.getMessage());
			return -1;
		}
		return 1;
	}

	/**
	 * 2.0ҵ��ϵͳ���õ���ʷ��������ӵķ���
	 * 
	 * 2012-2-16 add by HD
	 * 
	 * 2012-2-20 chg by HD
	 */
	@WebMethod
	public int ef_dklsda_add_for_old_app(
			@WebParam(partName = "daBean") DkdaHrecordBean daBean) {

		int r = -1;// ����ֵ

		DajbxxBean bean = new DajbxxBean();

		bean.setYgdh(daBean.getYgdh());// Ԥ�鵵��
		bean.setYwlsh(daBean.getYwlsh());// ҵ����ˮ��
		bean.setF_type(daBean.getF_type());// ��������
		bean.setClbm(daBean.getClbm());// ���ϱ���
		bean.setCl(daBean.getCl());// ��������
		bean.setClyx(daBean.getClyx());// ����Ӱ��
		bean.setCzy(daBean.getTjr());// ����Ա

		if (ef_dajbxx_check(bean)) { // ��鵵��������Ϣ���Ƿ���ڼ�¼,��������ھʹ���һ��
			if (ef_Dkdahr_Save(daBean) > 0) {// ������ʷ������¼
				// �ϴ�ɨ���
				try {
					DbMethod.makeDbSingleton().Execute(
							"DA_JNML_CLYX_ADD_BY_PROC", bean);
					r = 0;
				} catch (CallDbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ִ��[ef_dklsda_add_for_old_app]��������");
					return -1;
				}
			}
		} else {// �������������Ϣ���Ѿ�������Ϣ,��ֱ���ϴ�ɨ���
			// �ϴ�ɨ���
			try {
				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						bean);
				r = 0;
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_dklsda_add_for_old_app]��������");
				return -1;
			}
		}

		return r;
	}

	/**
	 * 2.0ҵ��ϵͳ���õ�֧ȡ��������ӵķ���
	 * 
	 * 2012-2-16 add by HD
	 * 
	 */
	@WebMethod
	public int ef_zqlsda_add_for_old_app(
			@WebParam(partName = "daBean") ZqdaHrecordBean daBean) {

		int r = -1;// ����ֵ

		// ������ʷ������¼
		if (ef_Zqdahr_Save(daBean) > 0) {
			// �ϴ�ɨ���
			try {
				DajbxxBean bean = new DajbxxBean();

				bean.setYgdh(daBean.getYgdh());// Ԥ�鵵��
				bean.setYwlsh(daBean.getYwlsh());// ҵ����ˮ��
				bean.setF_type(daBean.getF_type());// ��������
				bean.setClbm(daBean.getClbm());// ���ϱ���
				bean.setCl(daBean.getCl());// ��������
				bean.setClyx(daBean.getClyx());// ����Ӱ��
				bean.setCzy(daBean.getTjr());// ����Ա

				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						bean);
				r = 0;
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_zqlsda_add_for_old_app]��������");
				return -1;
			}
		}
		return r;
	}

	/**
	 * 2.0ҵ��ϵͳ���õ���ʷ�鼯��λ�������ӵķ���
	 * 
	 * 2012-2-16 add by HD
	 * 
	 */
	@WebMethod
	public int ef_dwlsda_add_for_old_app(
			@WebParam(partName = "daBean") DwdaHrecordBean daBean) {

		int r = -1;// ����ֵ

		// ������ʷ������¼
		if (ef_Dwdahr_Save(daBean) > 0) {
			// �ϴ�ɨ���
			try {
				DajbxxBean bean = new DajbxxBean();

				bean.setYgdh(daBean.getYgdh());// Ԥ�鵵��
				bean.setYwlsh(daBean.getYwlsh());// ҵ����ˮ��
				bean.setF_type(daBean.getF_type());// ��������
				bean.setClbm(daBean.getClbm());// ���ϱ���
				bean.setCl(daBean.getCl());// ��������
				bean.setClyx(daBean.getClyx());// ����Ӱ��
				bean.setCzy(daBean.getTjr());// ����Ա

				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						bean);
				r = 0;
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_dwlsda_add_for_old_app]��������");
				return -1;
			}
		}
		return r;
	}

	/**
	 * 2.0ҵ��ϵͳ���õ���ʷ�鼯��λ�������ӵķ���
	 * 
	 * 2012-2-16 add by HD
	 * 
	 */
	@WebMethod
	public int ef_cwlsda_add_for_old_app(
			@WebParam(partName = "daBean") CwdaHrecordBean daBean) {

		int r = -1;// ����ֵ

		// ������ʷ������¼
		if (ef_Cwdahr_Save(daBean) > 0) {
			// �ϴ�ɨ���
			try {
				DajbxxBean bean = new DajbxxBean();

				bean.setYgdh(daBean.getYgdh());// Ԥ�鵵��
				bean.setYwlsh(daBean.getYwlsh());// ҵ����ˮ��
				bean.setF_type(daBean.getF_type());// ��������
				bean.setClbm(daBean.getClbm());// ���ϱ���
				bean.setCl(daBean.getCl());// ��������
				bean.setClyx(daBean.getClyx());// ����Ӱ��
				bean.setCzy(daBean.getTjr());// ����Ա

				DbMethod.makeDbSingleton().Execute("DA_JNML_CLYX_ADD_BY_PROC",
						bean);
				r = 0;
			} catch (CallDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ִ��[ef_cwlsda_add_for_old_app]��������");
				return -1;
			}
		}
		return r;
	}
	
	
	/**
	 * ���ӵ���ϵͳ ���������鵵���õķ��� 2012��4��11�� by wl
	 * @param bean
	 * @return
	 */
	@WebMethod
	@WebResult(partName = "Int")
	public int ef_archiveSelectAll(@WebParam(partName = "bean") DajbxxBean bean) {
		String trace = " ";
		try {
			DbMethod.makeDbSingleton().Execute("DA_ARCHIVESELECTALL", bean);
		} catch (CallDbException e) {
			trace = "<font color='red'>���ӵ���ϵͳ���������鵵����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return -1;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return 1;
	}
	
	// ���⵵����combox 2011-7-19 wm
	@WebMethod
	public DagszBean[] getPhCombox(
			@WebParam(partName = "bean") DagszBean bean) {
		String trace = "�����б�";
		DagszBean[] xn = null;
		try {
			List<Row> lst = DbMethod.makeDbSingleton().Open("DA_PHCOMBOX",
					bean);
			xn = new DagszBean[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				Row row = lst.get(i);
				xn[i] = new DagszBean();
				xn[i].setDaph(row.getTrimString("PH"));
			}
		} catch (Exception e) {
			trace = "<font color='red'> EF_DAGSZ �б��ѯ����" + e.getMessage()
					+ "</font>";
			log.error(trace);
			return null;
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return xn;
	}

	//��ѯ�������������� 2012-07-17 wm
	@WebMethod
	public DagszBean[] getPhROWCOMBOXXX(
			@WebParam(partName = "bean") DagszBean bean) {
		DagszBean[] dagszBean = null;
		List<Row> list = null;
		try {
			list = DbMethod.makeDbSingleton().Open("DA_PHROWCOMBOXXX", bean);
			dagszBean = new DagszBean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = list.get(i);
				dagszBean[i] = new DagszBean();
				dagszBean[i].setDagrow(row.getTrimString("GROW"));
				dagszBean[i].setDaph(row.getTrimString("PH"));
				dagszBean[i].setF_type(row.getTrimString("F_TYPE"));
				dagszBean[i].setDags(Integer
						.parseInt(row.getTrimString("DMAX")));
				dagszBean[i].setDaps(Integer.parseInt(row.getTrimString("DNUM")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dagszBean;
	}
	/**
	 *ҵ��ϵͳɾ�����ϵ��÷���
	 *2011.11.15 add by zy
	 */
	@WebMethod
	public DajbxxBean[] ef_jnml_query_for_old_app_del(
			@WebParam(partName = "daBean") DajbxxBean daBean) {
		String trace = "";
		List<Row> rows = null;
		Row row = null;
		DajbxxBean[] daBeanList = null;
		try {
			rows = DbMethod.makeDbSingleton().Open(
					"DA_JNML_CLYX_QUERY_FOR_OLD_APP_DEL", daBean);
			if (rows != null && rows.size() > 0) {
				daBeanList = new DajbxxBean[rows.size()];

				for (int i = 0; i < rows.size(); i++) {
					row = rows.get(i);
					daBeanList[i] = new DajbxxBean();
					daBeanList[i].setId(Long.valueOf(row.getTrimString("id")));
					daBeanList[i].setYgdh(row.getTrimString("ygdh"));
					daBeanList[i].setClbm(row.getTrimString("clbm"));
					daBeanList[i].setCl(row.getTrimString("cl"));
					daBeanList[i].setZt(row.getTrimString("zt"));
					daBeanList[i].setCzfs(row.getTrimString("czfs"));
					daBeanList[i].setCzy(row.getTrimString("F_CREATOR_NAME"));
					daBeanList[i].setXgczy(row.getTrimString("F_UPDATER_NAME"));
					daBeanList[i].setCzy(row
							.getTrimString("F_CREATOR_DEPT_TIME"));
					daBeanList[i].setXgczy(row.getTrimString("F_UPDATER_TIME"));
					daBeanList[i].setF_type(row.getTrimString("f_type"));
				}
			} else {
				daBeanList = new DajbxxBean[0];
			}
		} catch (CallDbException e) {
			trace = "ִ��[ef_jnml_query_for_old_app_del]����";
			log.error(trace);
			daBeanList = new DajbxxBean[0];
		} catch (Exception e) {
			trace = "ִ��[ef_jnml_query_for_old_app_del]����";
			log.error(trace);
			daBeanList = new DajbxxBean[0];
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Da);
		}
		return daBeanList;
	}



	/**
	 * ҵ��ϵͳ����ɾ�����ϵ��÷���  
	 * 2011.11.15 add by zy
	 */
	@WebMethod
	@WebResult(partName = "String")
	public String ef_da_cl_del(DajbxxBean daBean) {
		String trace = "";
		try {
			DbMethod.makeDbSingleton().Execute("DA_CL_DEL", daBean);
		} catch (CallDbException e) {
			// TODO Auto-generated catch block
			trace = "ִ��[ef_da_cl_del]��������";
			log.error(trace);
			return e.getMessage();
		}
		return "success";
	}
}
