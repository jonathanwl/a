package com.wasoft.dataserver.ws.da.bean;

/**
 * ���ӵ���ϵͳ��ʹ�õ�bean,��Ӧ���ݿ��е�ef_jyjl��,����ID�ֶ�������
 * 
 * @author Yang 2010-9-19
 */
public class JyjlBean extends DaBean {

	public JyjlBean() {
	}

	private Long id; // ID Long 10 �Զ����� ����

	private String dah; // ������ dah Varchar 20 �Զ�����

	private String ygdh; // Ԥ�鵵�� ygdh Varchar 20 �Զ����� ���

	private String dalx; // �������� Dalx Varchar 20 ����ѡ��

	private int jyjs; // ���ľ��� Jyjs Int 2 ҳ��¼��

	private String jybh;

	private String jyr; // ������ Jyr Varchar 20 �Զ�����

	private String jymd; // ����Ŀ�� Jymd Varchar 10 ҳ��¼��

	private String jyrq; // �������� Jyrq Date 8 �Զ�����

	private String nhrq; // �⻹���� Nhrq Date 8 ҳ��¼��

	private String ghrq; // �黹���� hgrq Date 8

	private String djrbm; // �Ǽ��˱��� djrbm Varchar 10 ҳ��¼��

	public String getDash() {
		return dash;
	}

	public void setDash(String dash) {
		this.dash = dash;
	}

	public String getGrow() {
		return grow;
	}

	public void setGrow(String grow) {
		this.grow = grow;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	private String djr; // �Ǽ��� djr Varchar 10 ҳ��¼��

	private String bcdz; // ���������ַ Bcdz Varchar 120 �Զ�����
	
	private String dash; //�����Һ�

	private String gh; // ��� Gh varchar 60 �Զ�����
	
	private String grow; //��������
	
	private String ph;  //�����ź�	

	private String hh; // �к� Hh varchar 60 �Զ�����

	private int sxh; // ˳��� Sxh Int 5 �Զ�����

	private String dazt; // ����״̬ Dazt Varchar 10 �Զ�����

	private String chxx; // �߻���Ϣ chxx Varchar 100 ҳ��¼��

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getJybh() {
		return jybh;
	}

	public void setJybh(String jybh) {
		this.jybh = jybh;
	}

	public String getJyr() {
		return jyr;
	}

	public void setJyr(String jyr) {
		this.jyr = jyr;
	}

	public String getJymd() {
		return jymd;
	}

	public void setJymd(String jymd) {
		this.jymd = jymd;
	}

	public String getJyrq() {
		return jyrq;
	}

	public void setJyrq(String jyrq) {
		this.jyrq = jyrq;
	}

	public String getNhrq() {
		return nhrq;
	}

	public void setNhrq(String nhrq) {
		this.nhrq = nhrq;
	}

	public String getGhrq() {
		return ghrq;
	}

	public void setGhrq(String ghrq) {
		this.ghrq = ghrq;
	}

	public String getDjrbm() {
		return djrbm;
	}

	public void setDjrbm(String djrbm) {
		this.djrbm = djrbm;
	}

	public String getDjr() {
		return djr;
	}

	public void setDjr(String djr) {
		this.djr = djr;
	}

	public String getBcdz() {
		return bcdz;
	}

	public void setBcdz(String bcdz) {
		this.bcdz = bcdz;
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public String getHh() {
		return hh;
	}

	public void setHh(String hh) {
		this.hh = hh;
	}

	public int getSxh() {
		return sxh;
	}

	public void setSxh(int sxh) {
		this.sxh = sxh;
	}

	public String getDazt() {
		return dazt;
	}

	public void setDazt(String dazt) {
		this.dazt = dazt;
	}

	public String getChxx() {
		return chxx;
	}

	public void setChxx(String chxx) {
		this.chxx = chxx;
	}

}
