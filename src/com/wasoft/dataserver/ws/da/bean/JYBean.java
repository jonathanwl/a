package com.wasoft.dataserver.ws.da.bean;
public class JYBean extends DaBean{
		
		private long cid;
		
		private long id;
		
		private String dalx;
		
		private int jyjs;  //借阅卷数
		
		private String cjr;
		
		private String jymd; //借阅目的
		
		private String jyr; //借阅人
		
		private String djr; //登记人
		
		private String tjrbm; // 提交人编码 varchar（20） TJRBM

		private String tjr; // 提交人 varchar（60） TJR

		private String tjrq; // 提交日期 date TJRQ

		private String jsr; // 接收人 varchar（60） TJR

		private String jsrq; // 接收日期 date TJRQ

		private int clfs; // CLFS

		private String f_state; // F_STATE

		private String f_type; // F_STATE

		private String dah; // 档案号 dah Varchar 20 自动生成

		private String ygdh; // 预归档号 ygdh Varchar 20 自动生成 外键

		private String ywlsh; // 业务流水号 ywlsh Varchar 20

		private String cbwdbm; // 承办网点编码 cbwdbm Varchar 6 业务库

		private String cbwd; // 承办网点名称 cbwd Varchar 60 业务库

		private String gjdbm; // 归集点编码 gjdbm Varchar 4 业务库

		private String gjd;// 归集点名称 gjd Varchar 60 业务库

		private int bcnx; // 保存年限

		private String bmjb; // 保密级别

		private String cfwz; // 存放位置

		private String cxrq1; // 查询日期开始

		private String cxrq2; // 查询日期结束

		private String dkhtbh; // 贷款合同编号
		
		private String Begin;
		
		private String End;
		
		private String dash;
		
		private String gh;
		
		private String grow;
		
		private String hh;
		
		private int count;

		private String zxr;
		
		private String zxrq;
		
		private String zxyy;
		
		public String getZxr() {
			return zxr;
		}

		public void setZxr(String zxr) {
			this.zxr = zxr;
		}

		public String getZxrq() {
			return zxrq;
		}

		public void setZxrq(String zxrq) {
			this.zxrq = zxrq;
		}

		public String getZxyy() {
			return zxyy;
		}

		public void setZxyy(String zxyy) {
			this.zxyy = zxyy;
		}

		public long getCid() {
			return cid;
		}

		public void setCid(long cid) {
			this.cid = cid;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public String getDash() {
			return dash;
		}

		public void setDash(String dash) {
			this.dash = dash;
		}

		public String getGh() {
			return gh;
		}

		public void setGh(String gh) {
			this.gh = gh;
		}

		public String getGrow() {
			return grow;
		}

		public void setGrow(String grow) {
			this.grow = grow;
		}

		public String getHh() {
			return hh;
		}

		public void setHh(String hh) {
			this.hh = hh;
		}

		public String getBegin() {
			return Begin;
		}

		public void setBegin(String begin) {
			Begin = begin;
		}

		public String getEnd() {
			return End;
		}

		public void setEnd(String end) {
			End = end;
		}

		public String getDkhtbh() {
			return dkhtbh;
		}

		public void setDkhtbh(String dkhtbh) {
			this.dkhtbh = dkhtbh;
		}

		public String getCbwd() {
			return cbwd;
		}

		public void setCbwd(String cbwd) {
			this.cbwd = cbwd;
		}

		public String getCbwdbm() {
			return cbwdbm;
		}

		public void setCbwdbm(String cbwdbm) {
			this.cbwdbm = cbwdbm;
		}

		public String getGjd() {
			return gjd;
		}

		public void setGjd(String gjd) {
			this.gjd = gjd;
		}

		public String getGjdbm() {
			return gjdbm;
		}

		public void setGjdbm(String gjdbm) {
			this.gjdbm = gjdbm;
		}

		public String getDah() {
			return dah;
		}

		public void setDah(String dah) {
			this.dah = dah;
		}

		public String getYgdh() {
			return ygdh;
		}

		public void setYgdh(String ygdh) {
			this.ygdh = ygdh;
		}

		public String getF_state() {
			return f_state;
		}

		public void setF_state(String f_state) {
			this.f_state = f_state;
		}

		public int getClfs() {
			return clfs;
		}

		public void setClfs(int clfs) {
			this.clfs = clfs;
		}

		public String getTjr() {
			return tjr;
		}

		public void setTjr(String tjr) {
			this.tjr = tjr;
		}

		public String getTjrbm() {
			return tjrbm;
		}

		public void setTjrbm(String tjrbm) {
			this.tjrbm = tjrbm;
		}

		public String getTjrq() {
			return tjrq;
		}

		public void setTjrq(String tjrq) {
			this.tjrq = tjrq;
		}

		public String getJsr() {
			return jsr;
		}

		public void setJsr(String jsr) {
			this.jsr = jsr;
		}

		public String getJsrq() {
			return jsrq;
		}

		public void setJsrq(String jsrq) {
			this.jsrq = jsrq;
		}

		public int getBcnx() {
			return bcnx;
		}

		public void setBcnx(int bcnx) {
			this.bcnx = bcnx;
		}

		public String getBmjb() {
			return bmjb;
		}

		public void setBmjb(String bmjb) {
			this.bmjb = bmjb;
		}

		public String getCfwz() {
			return cfwz;
		}

		public void setCfwz(String cfwz) {
			this.cfwz = cfwz;
		}

		public String getCxrq1() {
			return cxrq1;
		}

		public void setCxrq1(String cxrq1) {
			this.cxrq1 = cxrq1;
		}

		public String getCxrq2() {
			return cxrq2;
		}

		public void setCxrq2(String cxrq2) {
			this.cxrq2 = cxrq2;
		}

		public String getF_type() {
			return f_type;
		}

		public void setF_type(String fType) {
			f_type = fType;
		}

		public String getYwlsh() {
			return ywlsh;
		}

		public void setYwlsh(String ywlsh) {
			this.ywlsh = ywlsh;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getDalx() {
			return dalx;
		}

		public void setDalx(String dalx) {
			this.dalx = dalx;
		}

		public int getJyjs() {
			return jyjs;
		}

		public void setJyjs(int jyjs) {
			this.jyjs = jyjs;
		}

		public String getCjr() {
			return cjr;
		}

		public void setCjr(String cjr) {
			this.cjr = cjr;
		}

		public String getJymd() {
			return jymd;
		}

		public void setJymd(String jymd) {
			this.jymd = jymd;
		}

		public String getJyr() {
			return jyr;
		}

		public void setJyr(String jyr) {
			this.jyr = jyr;
		}

		public String getDjr() {
			return djr;
		}

		public void setDjr(String djr) {
			this.djr = djr;
		}

	}

