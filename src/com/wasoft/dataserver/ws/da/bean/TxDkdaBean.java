package com.wasoft.dataserver.ws.da.bean;

public class TxDkdaBean extends DaBean {

	private long id; // ID Long 10 自动生成 主键

	private String txqzh; // 他项权证号

	private String ygdh; // 预归档号 外键

	private String qhr; // 取回人

	private String qhrq; // 取回日期

	private String thr; // 退回人

	private String bgyy; // 变更原因

	private String bgrq; // 变更日期

	private String bgr; // 变更人

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTxqzh() {
		return txqzh;
	}

	public void setTxqzh(String txqzh) {
		this.txqzh = txqzh;
	}

	public String getYgdh() {
		return ygdh;
	}

	public void setYgdh(String ygdh) {
		this.ygdh = ygdh;
	}

	public String getQhr() {
		return qhr;
	}

	public void setQhr(String qhr) {
		this.qhr = qhr;
	}

	public String getQhrq() {
		return qhrq;
	}

	public void setQhrq(String qhrq) {
		this.qhrq = qhrq;
	}

	public String getThr() {
		return thr;
	}

	public void setThr(String thr) {
		this.thr = thr;
	}

	public String getBgyy() {
		return bgyy;
	}

	public void setBgyy(String bgyy) {
		this.bgyy = bgyy;
	}

	public String getBgrq() {
		return bgrq;
	}

	public void setBgrq(String bgrq) {
		this.bgrq = bgrq;
	}

	public String getBgr() {
		return bgr;
	}

	public void setBgr(String bgr) {
		this.bgr = bgr;
	}
}
