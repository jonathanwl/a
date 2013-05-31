package com.wasoft.javabean;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wasoft.Constant;
import com.wasoft.util.DateUtil;

public class DataServiceTraceInfo {
	private static final Log log = LogFactory
			.getLog(DataServiceTraceInfo.class);

	private static DataServiceTraceInfo self = null;
	private static int len = Runtime.getRuntime().availableProcessors() * Constant.POOL_SIZE;
	public static enum TYPE{Console,Ef,Wasys3_gz,Wasys3_gd,Wasys3_cw,Common,Zj,Gz_tq,Gz_zy,Gz_nddq,Gz_hbj,Gz_zhgl,Gz_lscxtj,Gd_kfsxmhzdw,Gd_dhgl,Gd_dksq,Gd_bzj,Gd_nddq,Gd_lscxtj,Gd_phhs,
		       Cw_pzgl,Cw_zbgl,Cw_pzcx,Cw_yhdz,Cw_lszbgl,Cw_lspzcx,Cw_lsyhdz,Cw_bbgl,Cw_lsbbgl,Cw_pjgl,Report,OA,Hr,Cc,Lmk,Ai,Da,Be,Sms,Bt,Website,Ds,Sfk,Wxj,Fa,Im,Dkdb,Gzdw,Gzlxjs};
	
	private Date serverTime;
	private String serverInfo;
	private TYPE type;	
	
	private int curIndex;
	private DataServiceTraceInfo [] dstis;
	
	private DataServiceTraceInfo()
	{
		curIndex = -1;
		dstis = new DataServiceTraceInfo [len];
		log.debug("curIndex = " + curIndex + ",len = " + len);
	}

	private DataServiceTraceInfo(Date serverTime, String serverInfo, TYPE type) {
		this.serverTime = serverTime;
		this.serverInfo = serverInfo;
		this.type = type;
	}

	public static DataServiceTraceInfo getInstance() {
		if (self == null) {
			self = new DataServiceTraceInfo();
		}
		return self;
	}

	public synchronized DataServiceTraceInfo getTrace(String transInfo,
			TYPE type) {
		return getTrace(new Date(), transInfo, type);
	}

	public synchronized DataServiceTraceInfo getTrace(Date transTime,
			String transInfo, TYPE type) {
		if (++curIndex >= len)// Ñ­»·Êý×é
		{
			curIndex = 0;
		}
		DataServiceTraceInfo dsti = new DataServiceTraceInfo(transTime,
				transInfo, type);

		dstis[curIndex] = dsti;
		// log.debug("i=" + curIndex + ":" + dstis[curIndex]);

		return dstis[curIndex];
	}

	public synchronized String getDataServiceTraceInfo2Json() {
		StringBuffer sb = new StringBuffer();

		sb.append("{trace_info:[");

		if (dstis[0] != null) {
			for (int i = curIndex; i >= 0; i--) {
				setTrace2Json(sb, i);

			}
			if (dstis[len - 1] != null) {
				for (int i = len - 1; i > curIndex; i--) {
					setTrace2Json(sb, i);
				}
			}
			sb.setCharAt(sb.length() - 1, ']');
		} else {
			sb.append("]");
		}
		return sb.append("}").toString();
	}

	public synchronized String getTraceInfo2Html() {
		StringBuffer sb = new StringBuffer();

		sb.append("<table border=\"0\">");

		int counter = 0;
		if (dstis[0] != null) {
			for (int i = curIndex; i >= 0; i--) {
				if (counter++ >= Constant.LogCounter) {
					break;
				}
				setTrace2Table(sb, i);
			}
			if (dstis[len - 1] != null) {
				for (int i = len - 1; i > curIndex; i--) {
					if (counter++ >= Constant.LogCounter) {
						break;
					}
					setTrace2Table(sb, i);
				}
			}
		}
		return sb.append("</table>").toString();
	}

	private void setTrace2Table(StringBuffer sb, int i) {
		sb.append("<tr><td width=\"150\">[").append(i > 9 ? i : "0" + i)
				.append("]").append(
						DateUtil.formatDate(dstis[i].getServerTime(),
								"yyyy-MM-dd HH:mm:ss")).append(
						"&nbsp;</td><td>").append(dstis[i].getServerInfo())
				.append("</td></tr>");

	}

	private void setTrace2Json(StringBuffer sb, int i) {
		sb.append("[\"").append(i > 9 ? i : "0" + i).append("\",\"").append(
				DateUtil.formatDate(dstis[i].getServerTime(),
						"yyyy-MM-dd HH:mm:ss")).append("\",\"").append(
				dstis[i].getServerInfo()).append("\"],");
	}

	public synchronized Date getServerTime() {
		return serverTime;
	}

	public synchronized void setServerTime(Date serverTime) {
		this.serverTime = serverTime;
	}

	public synchronized String getServerInfo() {
		return serverInfo;
	}

	public synchronized void setServerInfo(String serverInfo) {
		this.serverInfo = serverInfo;
	}

	public static void main(String[] args) {
		DataServiceTraceInfo.getInstance().getTrace("ddd", TYPE.Console);
	}
}
