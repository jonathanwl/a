package com.wasoft.javabean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wasoft.Constant;

public class LogInfo {

	protected static final Log log = LogFactory.getLog(LogInfo.class);

	private static String tableStyle = "<table border=\"1\" class=\"ThinLineTable\" borderColor=\"#000000\">";

	private HashMap<String, LogBank> logBanks = new HashMap<String, LogBank>();

	private HashMap<Integer, LogTrans> logTrans = new HashMap<Integer, LogTrans>();

	private static boolean isReceive(String line) {
		return line.indexOf("<font color='" + Constant.COLOR_RECEIVE + "'>") != -1;
	}

	private static boolean isSend(String line) {
		return line.indexOf("<font color='" + Constant.COLOR_SEND + "'>") != -1;
	}

	private static boolean isError(String line) {
		return line.indexOf("<font color=\"" + Constant.COLOR_ERROR + "\">") != -1;
	}

	/**
	 * 向银行统计表里加入记录
	 * 
	 * @param bank
	 *            银行编号
	 * @param recvCount
	 *            接受数据次数
	 * @param sendCount
	 *            发送数据次数
	 */
	private void addBank(String bank, int recvCount, int sendCount) {
		if (logBanks.containsKey(bank)) {
			logBanks.get(bank).incCount(recvCount, sendCount);
		} else {
			logBanks.put(bank, new LogBank(bank, recvCount, sendCount));
		}
	}

	/**
	 * 向交易统计表里加入记录
	 * 
	 * @param transCode
	 *            交易码
	 * @param transCount
	 *            交易次数
	 * @param failedCount
	 *            失败次数
	 */
	private void addTrans(int transCode, int transCount, int failedCount) {
		if (logTrans.containsKey(transCode)) {
			logTrans.get(transCode).incCount(transCount, failedCount);
		} else {
			logTrans.put(transCode, new LogTrans(transCode, transCount,
					failedCount));
		}
	}

	/**
	 * 处理行数据，由读文件方法回调 将交易银行信息和交易信息进行统计
	 * 
	 * @param line
	 *            读入一行日志文件
	 */
	public void HandleLine(String line) throws Exception {
		try {
			// 加入银行统计信息表
			int recvCount, sendCount;
			String bank = "00";
			if (isReceive(line)) {
				recvCount = 1;
				sendCount = 0;
				bank = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
			} else if (isSend(line)) {
				recvCount = 0;
				sendCount = 1;
				bank = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
			} else {
				recvCount = 0;
				sendCount = 0;
			}
			if (recvCount > 0 || sendCount > 0) {
				addBank(bank, recvCount, sendCount);
			}

			// 加入交易统计信息表
			int transCount = 1, failedCount = 0;
			int index = line.indexOf("交易)");
			if (index > 3) {
				int transCode = Integer.parseInt(line.substring(index - 3,
						index));
				if (isError(line))
					failedCount++;
				addTrans(transCode, transCount, failedCount);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 交易银行信息统计
	 * 
	 * @param filePath
	 *            日志文件
	 * @param bank
	 *            银行编号
	 * @param method
	 *            统计方法，total:该银行所有请求 recv :银行接受数据请求 send :银行发送数据请求
	 * @return
	 */
	public static String BankStat(String filePath, String bank, String method) {
		StringBuffer sb = new StringBuffer(
				"<table border=\"0\" class=\"jtpsoft\">");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), Constant.CHARSET));
			String line = br.readLine();

			while (line != null) {
				if (bank.equals("all") || line.indexOf("[" + bank + "]") != -1) {
					if (method.equals("total") || // 合计
							(method.equals("recv") && isReceive(line)) || // 接受
							(method.equals("send") && isSend(line)))// 发送
					{
						sb.append(line).append("\n");
					}
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			log.error("read file err:" + e.getMessage());
		}
		return sb.append("</table>").toString();
	}

	/**
	 * 交易类型统计
	 * 
	 * @param filePath
	 *            日志文件
	 * @param transCode
	 *            交易码
	 * @param bz
	 *            1:所有，0:为失败
	 * @return
	 */
	public static String TransStat(String filePath, String transCode, String bz) {
		StringBuffer sb = new StringBuffer(
				"<table border=\"0\" class=\"jtpsoft\">");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), Constant.CHARSET));
			String line = br.readLine();

			while (line != null) {
				int index = line.indexOf("交易)");
				if (index < 3)
					continue;
				if (transCode.equals("all")
						|| line.substring(index - 3, index).equals(transCode)) {
					if (bz.equals("1") || (bz.equals("0") && isError(line))) {
						sb.append(line).append("\n");
					}
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			log.error("read file err:" + e.getMessage());
		}

		return sb.append("</table>").toString();
	}

	/**
	 * 将银行统计信息转化成html表格发给servlet请求
	 * 
	 * @return
	 */
	public String BankToHtmlString() {
		StringBuffer sb = new StringBuffer(tableStyle);
		sb
				.append("<tr align=\"center\"><th>银行</th><th>请求数据次数</th><th>发送数据次数</th><th>合计次数</th></tr>");
		int t_num = 0, t_recv = 0, t_send = 0, t_total = 0;

		Iterator<String> itr = logBanks.keySet().iterator();
		while (itr.hasNext()) {
			LogBank value = logBanks.get(itr.next());
			t_num++;
			t_recv += value.getRecvCount();
			t_send += value.getSendCount();
			t_total += value.getTotalCount();

			sb
					.append("<tr><td>")
					.append(value.getBank())
					.append("</td>")
					.append("<td align=\"right\" class=\"changeColor\"")
					// .append("
					// onclick=\"filterBank('recv','").append(value.getBank()).append("')")
					.append("\">")
					.append(value.getRecvCount())
					.append("</td>")
					.append("<td align=\"right\" class=\"changeColor\"")
					// .append("
					// onclick=\"filterBank('send','").append(value.getBank()).append("')")
					.append("\">").append(value.getSendCount())
					.append("</td>")
					.append("<td align=\"right\" class=\"changeColor\"")
					// .append("
					// onclick=\"filterBank('total','").append(value.getBank()).append("')")
					.append("\">").append(value.getTotalCount())
					.append("</td>").append("</tr>");
		}
		sb.append("<tr><td>").append("合计&nbsp;&nbsp;").append(t_num).append(
				"</td>").append("<td align=\"right\" class=\"changeColor\"")
		// .append(" onclick=\"filterBank('recv','all')")
				.append("\">").append(t_recv).append("</td>").append(
						"<td align=\"right\" class=\"changeColor\"")
				// .append(" onclick=\"filterBank('send','all')")
				.append("\">").append(t_send).append("</td>").append(
						"<td align=\"right\" class=\"changeColor\"")
				// .append(" onclick=\"filterBank('total','all'")
				.append(")\">").append(t_total).append("</td>").append(
						"</tr></table>");

		return sb.toString();// .replaceAll("'", "\\\\\'");
	}

	class LogBank {
		private String bank;

		private int recvCount;// 接受次数

		private int sendCount;// 发送次数

		private void incRecvCount(int recvCount) {
			this.recvCount += recvCount;
		}

		private void incSendCount(int sendCount) {
			this.sendCount += sendCount;
		}

		private void incCount(int recvCount, int sendCount) {
			incRecvCount(recvCount);
			incSendCount(sendCount);
		}

		private LogBank(String bank, int recvCount, int sendCount) {
			this.bank = bank;
			this.recvCount = recvCount;
			this.sendCount = sendCount;
		}

		public LogBank() {
		}

		public String getBank() {
			return bank;
		}

		public int getRecvCount() {
			return recvCount;
		}

		public int getSendCount() {
			return sendCount;
		}

		public int getTotalCount() {
			return recvCount + sendCount;
		}
	}

	class LogTrans {
		private int transCode;

		private int transCount;

		private int failedCount;

		public LogTrans(int transCode, int transCount, int failedCount) {
			this.transCode = transCode;
			this.transCount = transCount;
			this.failedCount = failedCount;
		}

		public void incTransCount(int c) {
			transCount += c;
		}

		public void incfailedCount(int c) {
			failedCount += c;
		}

		public void incCount(int transCount, int failedCount) {
			incTransCount(transCount);
			incfailedCount(failedCount);
		}

		public int getFailedCount() {
			return failedCount;
		}

		public int getTransCode() {
			return transCode;
		}

		public int getTransCount() {
			return transCount;
		}
	}
}
