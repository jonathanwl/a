package com.wasoft.javabean;

import java.util.HashMap;
import java.util.Iterator;

public class LogBank {
	private String bank;
	private int recvCount;//接受次数
	private int sendCount;//发送次数
	
	private HashMap <String, LogBank> logBanks = new HashMap<String, LogBank>();
	private void incRecvCount(int recvCount)
	{
		this.recvCount += recvCount;
	}
	private void incSendCount(int sendCount)
	{
		this.sendCount += sendCount;
	}
	private void incCount(int recvCount, int sendCount)
	{
		incRecvCount(recvCount);
		incSendCount(sendCount);
	}
	private LogBank(String bank, int recvCount, int sendCount)
	{
		this.bank = bank;
		this.recvCount = recvCount;
		this.sendCount = sendCount;
	}
	public LogBank(){}
	
	public void addBank(String line)
	{
		
	}
	public void addBank(String bank, int recvCount, int sendCount)
	{
		if(logBanks.containsKey(bank))
		{
			logBanks.get(bank).incCount(recvCount, sendCount);
		}
		else
		{
			logBanks.put(bank, new LogBank(bank, recvCount, sendCount));
		}
	}	
	
	public String toHtmlString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<table border=\"1\" class=\"jtpsoft\"style=\"BORDER-RIGHT: 1px; BORDER-TOP: 1px; FONT-SIZE: x-small; MARGIN: 1px; BORDER-LEFT: 1px; BORDER-BOTTOM: 1px; BORDER-COLLAPSE: collapse; TEXT-ALIGN: left\" borderColor=\"#000000\" cellSpacing=\"1\" cellPadding=\"1\" border=\"1\">")
  			.append("<tr><th>银行</th><th>请求数据次数</th><th>发送数据次数</th><th>合计次数</th></tr>)");
		
		Iterator itr = logBanks.keySet().iterator();
		while (itr.hasNext())
        {
           	LogBank value = logBanks.get(itr.next());

            sb.append("<tr><td>").append(value.getBank()).append("</td>")
            	.append("<td>").append(value.getRecvCount()).append("</td>")
            	.append("<td>").append(value.getSendCount()).append("</td>")
            	.append("<td>").append(value.getTotalCount()).append("</td>")
            	.append("</tr>");
        }
		sb.append("</table>");
		return sb.toString();
	}
	public String getBank() {
		return bank;
	}

	public HashMap<String, LogBank> getLogBanks() {
		return logBanks;
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
