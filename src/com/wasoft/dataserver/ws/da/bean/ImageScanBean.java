package com.wasoft.dataserver.ws.da.bean;

/**
 * 图片上传下载时用的bean 2010年9月15日 add
 * 
 * @author HD
 * @version 1.0
 */

public class ImageScanBean extends DaBean {

	private String id; // 主健

	private String dkhth; // 贷款合同号

	private String ygdh; // 预归档号

	private String ywlsh; // 业务流水号

	private String clbm; // 材料编码

	private String cl; // 材料名称

	private byte[] clyx; // 材料影像

	private int yjfs; // 原件份数

	private int fyfs; // 复印件份数

	private int smfs; // 扫描件份数

	private int smys; // 扫描件页数

	private int sfqq; // 是否齐全

	private String url; // 下载文件的url地址

	private String f_type; // F_STATE

	private String czy; // 上传操作员

	private String czybm; // 上传操作员

	public ImageScanBean(String ygdh, String clbm, String cl, byte[] clyx,
			String czy, String czybm) {
		super();
		this.ygdh = ygdh;
		this.clbm = clbm;
		this.cl = cl;
		this.clyx = clyx;
		this.czy = czy;
		this.czybm = czybm;
	}

	public ImageScanBean(String ygdh, String ywlsh, String clbm, String cl,
			byte[] clyx, String f_type, String czy, String czybm) {
		super();
		this.ygdh = ygdh;
		this.ywlsh = ywlsh;
		this.clbm = clbm;
		this.cl = cl;
		this.clyx = clyx;
		this.f_type = f_type;
		this.czy = czy;
		this.czybm = czybm;
	}

	public ImageScanBean() {
	}

	public int getSfqq() {
		return sfqq;
	}

	public void setSfqq(int sfqq) {
		this.sfqq = sfqq;
	}

	public int getSmfs() {
		return smfs;
	}

	public void setSmfs(int smfs) {
		this.smfs = smfs;
	}

	public int getSmys() {
		return smys;
	}

	public void setSmys(int smys) {
		this.smys = smys;
	}

	public ImageScanBean(String ygdh, String id, String clbm, String cl,
			byte[] clyx) {
		super();
		this.ygdh = ygdh;
		this.id = id;
		this.clbm = clbm;
		this.cl = cl;
		this.clyx = clyx;
	}

	public ImageScanBean(String ygdh, String clbm, String cl, byte[] clyx) {
		super();
		this.ygdh = ygdh;
		this.clbm = clbm;
		this.cl = cl;
		this.clyx = clyx;
	}

	public ImageScanBean(String id, String dkhth, String ygdh, String clbm,
			String cl, byte[] clyx, int yjfs, int fyfs, int smfs, int smys,
			int sfqq) {
		super();
		this.id = id;
		this.dkhth = dkhth;
		this.ygdh = ygdh;
		this.clbm = clbm;
		this.cl = cl;
		this.clyx = clyx;
		this.yjfs = yjfs;
		this.fyfs = fyfs;
		this.smfs = smfs;
		this.smys = smys;
		this.sfqq = sfqq;
	}

	public ImageScanBean(String dkhth, String ygdh, String clbm, String cl,
			byte[] clyx, int yjfs, int fyfs, int smfs, int smys, int sfqq) {
		super();
		this.dkhth = dkhth;
		this.ygdh = ygdh;
		this.clbm = clbm;
		this.cl = cl;
		this.clyx = clyx;
		this.yjfs = yjfs;
		this.fyfs = fyfs;
		this.smfs = smfs;
		this.smys = smys;
		this.sfqq = sfqq;
	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public String getClbm() {
		return clbm;
	}

	public void setClbm(String clbm) {
		this.clbm = clbm;
	}

	public byte[] getClyx() {
		return clyx;
	}

	public void setClyx(byte[] clyx) {
		this.clyx = clyx;
	}

	public int getFyfs() {
		return fyfs;
	}

	public void setFyfs(int fyfs) {
		this.fyfs = fyfs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYgdh() {
		return ygdh;
	}

	public void setYgdh(String ygdh) {
		this.ygdh = ygdh;
	}

	public int getYjfs() {
		return yjfs;
	}

	public void setYjfs(int yjfs) {
		this.yjfs = yjfs;
	}

	public String getDkhth() {
		return dkhth;
	}

	public void setDkhth(String dkhth) {
		this.dkhth = dkhth;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ImageScanBean(String id) {
		super();
		this.id = id;
	}

	public String getCzy() {
		return czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public String getCzybm() {
		return czybm;
	}

	public void setCzybm(String czybm) {
		this.czybm = czybm;
	}

	public String getYwlsh() {
		return ywlsh;
	}

	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}

	public String getF_type() {
		return f_type;
	}

	public void setF_type(String fType) {
		f_type = fType;
	}

}
