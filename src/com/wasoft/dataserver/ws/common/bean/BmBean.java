package com.wasoft.dataserver.ws.common.bean;

public class BmBean {
	
	private	 String ckzh  = "";
	private	 String a001  = "";
	private	 String a002  = "";
	private  String qclx  = "";
	private	 String yhbm  = "";
	private	 String yh	  = "";
	private	 String cbwdbm= "";
	private	 String cbwd  = "";
	private	 int    count = 0;
	private	 String e001  = "";
	private	 String e002  = "";
	private	 String e003  = "";
	private	 String e021  = "";
	private	 String e022  = "";
	private	 double e034  = 0;
	private	 double e042  = 0;
	private	 String e043  = "";
	private	 int    g115  = 0;
	private	 String bmbm  = "";
	private	 String kmbm  = "";
	private	 String kmmc  = "";
	private	 int    isend = 0;
	private	 int    kmjb  = 0;
	private	 int 	kmlx  = 0;
	private	 int 	xmhs  = 0;
	private	 int 	sfqy  = 0;
	private	 long 	uid   = 0;
	private  String ret   = "";
	private  String fcyy  = "";           //xkq add ���ⶳԭ�� 2011-01-19
	private  short 	retShort = (short)0;  //xkq  add ���ز���  
	private  String zjhm  = "";           //xkq  add ֤������  2011-03-02
	private  String zgzt = "";            //xkq  add ְ��״̬  2011-03-02
	private  String a000 = "";            //xkq  add ���˵ǼǺ�   2011-03-02
	private  String a070 = "";            //xkq  add ְ��״̬  2011-03-02
	private  String zjlx = "";            //xkq  add ֤������ 2011-04-09
	private	 String e051  = "";   //��������
	private	 String f002  = "";   //Ӧ������
	private	 String f005  = "";   //Ӧ������
	private	 double f006  = 0.0;  //Ӧ������
	private	 double f007  = 0.0;  //Ӧ����Ϣ
	private	 double f008  = 0.0;  //Ӧ����Ϣ
	private  String yxdw  = "";   //��ѡ��λ
	private  String mkname= "";   //ģ������
	private  short ywlb  ;       //1-�˻��ϲ���2-�ڲ�ת�ƣ�3-��鼯��ת����3-��鼯��ת��
	private  String msg   = "";
	
	private	 String bm    = "";//��λ����
	private	 String mc    = "";//��λ����
	private	 String gjdbm = ""; //�鼯��
	private String ssyh; //��������
	private String ssqx;//��������
	private String sshy;//������ҵ
	private String zzjgdm;//��֯��������
	private String zgrs;//ְ������
	private String hjrs;//�������
	private String gzze;//�����ܶ�
	private String yjcze;//�½ɴ��ܶ�
	private String sscbwd;//�����а�����
	private String dwxz;//��λ����
	private String lsgx;//������ϵ
	private String zgbm;//���ܲ���
	private String fxr;//��н��
	private String dwysdm;//��λԤ�����
	private String dwdz;	//��λ��ַ
	private String yzbm;	//��������
	private String glbm;//�����������
	private String yhzh;//�˻������˺�:
	private String khyh;//��������
	private String szzs;//�Ƿ��������֤���־
	
	private String fxlb;//��н���
	private String jsjd;//���㾫��
	private String ydhjr;//Լ�������
	private String jccs;//�ɴ����
	private String qjny;//��������
	private String yjny;//Ӧ������
	private String jcblbm;//�ɴ��������
	private String dwjcl;//ְ����λ������:
	private String grjcl;//���˽�����
	private String czbtl;//����������
	private String bcjcl;//���䲹����
	private String jcblbz;//ְ���ɴ�����Ƿ����һ��
	
	private String fzrxm;//����������
	private String frdbxm;//���˴�������
	private String frdbzjh;//���˴���֤����
	private String fzrdh;//��������ϵ�绰
	private String frzjlb;//����֤�����
	private String frdbdh;//���˴���绰
	private String zhyxm;//ר��Ա����
	private String zgyzjlb;//ר��Ա֤�����
	private String zgydh;//ר��Ա��ϵ�绰
	private String zgyzjh;//ר��Ա֤������	
	private String zgyyx;//ר��Ա��������
	private String fzryddh;//�������ƶ��绰
	private String fryddh;//�����ƶ��绰
	private String zgyyddh;//ר��Ա�ƶ��绰
	private String khrq;//��������
	private String grjzny;//GRJZNY�����´���������
	private String dwjzny;//DWJZNY��λ�´���������
	private String czjczy;//CZJCZY�����´���������
	private String bcjzny;//BCJZNY�����´���������
	private String khsqrq;//������������
	private String khczy;//��������Ա
	
	
	private String ybrxm;	//����������
	private String ybrdh;	//�����˵绰
	private int jcrs;
	private double ycje;
	private double dwzhye;
	private int wbzcbz;
	private String dwzt;
	private String jbrxm;
	private String jbrlxdh;
	private String jzny ;
	private	 String a076 ;
	private	 String a074;
	
	private String dateBein;//��ʼ����
	private String dateEnd;//��������
	private Double f011;//������Ϣ
	private Double e023;
	private Double e032;
	private String f026;
	private String f017;
	
	private String sprq; //��������
	private String spbz; //����״̬
	private String spczy;//��������Ա		
	private String g004;//����˵�λ
	
	private String lsnd; //���ѡ��
	private String kssj; //��ʼ����
	private String jssj; //��������
	
	private String a033;
	private String a034;
	private String a035;
	
	private String qyyyzhh; //��ҵӪҵִ��
	private String sydwfrzsh ;//��ҵ��λ����֤���
	private String xzdwpwh ;//������λ���ĺ�
	private String yhmc;
	private String zgbmbm;
	private String fddbr;
	private String lxdh;
	private String zgbmmc;
	private String jclx;	//�ɴ�����
	
	//cfcaǩ����
	private	 String pzid  = "";//Ψһ��ʶ��
	private	 String userid  = "";//��½userid
    private String loginmc;//��½����
    private String loginip;//��½ip
    private String sn;  //sn
    private String dn; //����������û�֤��DN����
    private String notb_string; //֤����Ч��ʼ����
    private String nota_string; //֤����Ч��ֹ����
    private String trans_msg;
    private String trans_sign;	
    private String fkdwmc;
  
	private String fkzh;

	
	/**
	 * @return the pzid
	 */
	public String getPzid() {
		return pzid;
	}
	/**
	 * @param pzid the pzid to set
	 */
	public void setPzid(String pzid) {
		this.pzid = pzid;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the loginmc
	 */
	public String getLoginmc() {
		return loginmc;
	}
	/**
	 * @param loginmc the loginmc to set
	 */
	public void setLoginmc(String loginmc) {
		this.loginmc = loginmc;
	}
	/**
	 * @return the loginip
	 */
	public String getLoginip() {
		return loginip;
	}
	/**
	 * @param loginip the loginip to set
	 */
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * @return the dn
	 */
	public String getDn() {
		return dn;
	}
	/**
	 * @param dn the dn to set
	 */
	public void setDn(String dn) {
		this.dn = dn;
	}
	/**
	 * @return the notb_string
	 */
	public String getNotb_string() {
		return notb_string;
	}
	/**
	 * @param notbString the notb_string to set
	 */
	public void setNotb_string(String notbString) {
		notb_string = notbString;
	}
	/**
	 * @return the nota_string
	 */
	public String getNota_string() {
		return nota_string;
	}
	/**
	 * @param notaString the nota_string to set
	 */
	public void setNota_string(String notaString) {
		nota_string = notaString;
	}
	/**
	 * @return the trans_msg
	 */
	public String getTrans_msg() {
		return trans_msg;
	}
	/**
	 * @param transMsg the trans_msg to set
	 */
	public void setTrans_msg(String transMsg) {
		trans_msg = transMsg;
	}
	/**
	 * @return the trans_sign
	 */
	public String getTrans_sign() {
		return trans_sign;
	}
	/**
	 * @param transSign the trans_sign to set
	 */
	public void setTrans_sign(String transSign) {
		trans_sign = transSign;
	}
	public String getJclx() {
		return jclx;
	}
	public void setJclx(String jclx) {
		this.jclx = jclx;
	}
	public String getZgbmmc() {
		return zgbmmc;
	}
	public void setZgbmmc(String zgbmmc) {
		this.zgbmmc = zgbmmc;
	}
	public String getFddbr() {
		return fddbr;
	}
	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getZgbmbm() {
		return zgbmbm;
	}
	public void setZgbmbm(String zgbmbm) {
		this.zgbmbm = zgbmbm;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getLsnd() {
		return lsnd;
	}
	public void setLsnd(String lsnd) {
		this.lsnd = lsnd;
	}
	public String getG004() {
		return g004;
	}
	public void setG004(String g004) {
		this.g004 = g004;
	}
	
	
	
	public String getF026() {
		return f026;
	}
	public void setF026(String f026) {
		this.f026 = f026;
	}
	public String getF017() {
		return f017;
	}
	public void setF017(String f017) {
		this.f017 = f017;
	}
	public Double getF011() {
		return f011;
	}
	public void setF011(Double f011) {
		this.f011 = f011;
	}
	public Double getE023() {
		return e023;
	}
	public void setE023(Double e023) {
		this.e023 = e023;
	}
	public Double getE032() {
		return e032;
	}
	public void setE032(Double e032) {
		this.e032 = e032;
	}
	public String getDateBein() {
		return dateBein;
	}
	public void setDateBein(String dateBein) {
		this.dateBein = dateBein;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	
	public String getJzny() {
		return jzny;
	}
	public void setJzny(String jzny) {
		this.jzny = jzny;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public short getYwlb() {
		return ywlb;
	}
	public void setYwlb(short ywlb) {
		this.ywlb = ywlb;
	}
	public String getQclx() {
		return qclx;
	}
	public void setQclx(String qclx) {
		this.qclx = qclx;
	}
	public String getYxdw() {
		return yxdw;
	}
	public void setYxdw(String yxdw) {
		this.yxdw = yxdw;
	}
	public String getMkname() {
		return mkname;
	}
	public void setMkname(String mkname) {
		this.mkname = mkname;
	}
	public String getFcyy() {
		return fcyy;
	}
	public void setFcyy(String fcyy) {
		this.fcyy = fcyy;
	}
	public int getIsend() {
		return isend;
	}
	public void setIsend(int isend) {
		this.isend = isend;
	}
	public String getKmbm() {
		return kmbm;
	}
	public void setKmbm(String kmbm) {
		this.kmbm = kmbm;
	}
	public int getKmjb() {
		return kmjb;
	}
	public void setKmjb(int kmjb) {
		this.kmjb = kmjb;
	}
	public int getKmlx() {
		return kmlx;
	}
	public void setKmlx(int kmlx) {
		this.kmlx = kmlx;
	}
	public String getKmmc() {
		return kmmc;
	}
	public void setKmmc(String kmmc) {
		this.kmmc = kmmc;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getA001() {
		return a001;
	}
	public void setA001(String a001) {
		this.a001 = a001;
	}
	public String getA002() {
		return a002;
	}
	public void setA002(String a002) {
		this.a002 = a002;
	}
	public String getCkzh() {
		return ckzh;
	}
	public void setCkzh(String ckzh) {
		this.ckzh = ckzh;
	}
	public String getGjdbm() {
		return gjdbm;
	}
	public void setGjdbm(String gjdbm) {
		this.gjdbm = gjdbm;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public String getYh() {
		return yh;
	}
	public void setYh(String yh) {
		this.yh = yh;
	}
	public String getYhbm() {
		return yhbm;
	}
	public void setYhbm(String yhbm) {
		this.yhbm = yhbm;
	}
	public String getE001() {
		return e001;
	}
	public void setE001(String e001) {
		this.e001 = e001;
	}
	public String getE002() {
		return e002;
	}
	public void setE002(String e002) {
		this.e002 = e002;
	}
	public String getE003() {
		return e003;
	}
	public void setE003(String e003) {
		this.e003 = e003;
	}
	public String getE021() {
		return e021;
	}
	public void setE021(String e021) {
		this.e021 = e021;
	}
	public String getE022() {
		return e022;
	}
	public void setE022(String e022) {
		this.e022 = e022;
	}
	
	public String getE043() {
		return e043;
	}
	public void setE043(String e043) {
		this.e043 = e043;
	}
	public double getE034() {
		return e034;
	}
	public void setE034(double e034) {
		this.e034 = e034;
	}
	public double getE042() {
		return e042;
	}
	public void setE042(double e042) {
		this.e042 = e042;
	}
	public int getG115() {
		return g115;
	}
	public void setG115(int g115) {
		this.g115 = g115;
	}
	public String getBmbm() {
		return bmbm;
	}
	public void setBmbm(String bmbm) {
		this.bmbm = bmbm;
	}
	public int getXmhs() {
		return xmhs;
	}
	public void setXmhs(int xmhs) {
		this.xmhs = xmhs;
	}
	public int getSfqy() {
		return sfqy;
	}
	public void setSfqy(int sfqy) {
		this.sfqy = sfqy;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}

	public short getRetShort() {
		return retShort;
	}
	public void setRetShort(short retShort) {
		this.retShort = retShort;
	}
	public String getF002() {
		return f002;
	}
	public void setF002(String f002) {
		this.f002 = f002;
	}
	public String getF005() {
		return f005;
	}
	public void setF005(String f005) {
		this.f005 = f005;
	}
	public double getF006() {
		return f006;
	}
	public void setF006(double f006) {
		this.f006 = f006;
	}
	public double getF007() {
		return f007;
	}
	public void setF007(double f007) {
		this.f007 = f007;
	}
	public double getF008() {
		return f008;
	}
	public void setF008(double f008) {
		this.f008 = f008;
	}
	public String getE051() {
		return e051;
	}
	public void setE051(String e051) {
		this.e051 = e051;
	}
	

	public String getJbrxm() {
		return jbrxm;
	}
	public void setJbrxm(String jbrxm) {
		this.jbrxm = jbrxm;
	}
	public String getJbrlxdh() {
		return jbrlxdh;
	}
	public void setJbrlxdh(String jbrlxdh) {
		this.jbrlxdh = jbrlxdh;
	}
	public String getDwdz() {
		return dwdz;
	}
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getYbrxm() {
		return ybrxm;
	}
	public void setYbrxm(String ybrxm) {
		this.ybrxm = ybrxm;
	}
	public String getYbrdh() {
		return ybrdh;
	}
	public void setYbrdh(String ybrdh) {
		this.ybrdh = ybrdh;
	}
	public int getJcrs() {
		return jcrs;
	}
	public void setJcrs(int jcrs) {
		this.jcrs = jcrs;
	}
	public double getYcje() {
		return ycje;
	}
	public void setYcje(double ycje) {
		this.ycje = ycje;
	}
	public double getDwzhye() {
		return dwzhye;
	}
	public void setDwzhye(double dwzhye) {
		this.dwzhye = dwzhye;
	}
	public int getWbzcbz() {
		return wbzcbz;
	}
	public void setWbzcbz(int wbzcbz) {
		this.wbzcbz = wbzcbz;
	}
	public String getDwzt() {
		return dwzt;
	}
	public void setDwzt(String dwzt) {
		this.dwzt = dwzt;
	}

	
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	
	public String getZgzt() {
		return zgzt;
	}
	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}

	public String getA070() {
		return a070;
	}
	public void setA070(String a070) {
		this.a070 = a070;
	}
	public String getA000() {
		return a000;
	}
	public void setA000(String a000) {
		this.a000 = a000;
	}
	public String getSsyh() {
		return ssyh;
	}
	public void setSsyh(String ssyh) {
		this.ssyh = ssyh;
	}
	public String getSsqx() {
		return ssqx;
	}
	public void setSsqx(String ssqx) {
		this.ssqx = ssqx;
	}
	public String getSshy() {
		return sshy;
	}
	public void setSshy(String sshy) {
		this.sshy = sshy;
	}
	public String getZzjgdm() {
		return zzjgdm;
	}
	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}
	public String getZgrs() {
		return zgrs;
	}
	public void setZgrs(String zgrs) {
		this.zgrs = zgrs;
	}
	public String getHjrs() {
		return hjrs;
	}
	public void setHjrs(String hjrs) {
		this.hjrs = hjrs;
	}
	public String getGzze() {
		return gzze;
	}
	public void setGzze(String gzze) {
		this.gzze = gzze;
	}
	public String getYjcze() {
		return yjcze;
	}
	public void setYjcze(String yjcze) {
		this.yjcze = yjcze;
	}
	public String getSscbwd() {
		return sscbwd;
	}
	public void setSscbwd(String sscbwd) {
		this.sscbwd = sscbwd;
	}
	public String getDwxz() {
		return dwxz;
	}
	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}
	public String getLsgx() {
		return lsgx;
	}
	public void setLsgx(String lsgx) {
		this.lsgx = lsgx;
	}
	public String getZgbm() {
		return zgbm;
	}
	public void setZgbm(String zgbm) {
		this.zgbm = zgbm;
	}
	public String getFxr() {
		return fxr;
	}
	public void setFxr(String fxr) {
		this.fxr = fxr;
	}
	public String getDwysdm() {
		return dwysdm;
	}
	public void setDwysdm(String dwysdm) {
		this.dwysdm = dwysdm;
	}
	public String getGlbm() {
		return glbm;
	}
	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}
	public String getYhzh() {
		return yhzh;
	}
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	public String getKhyh() {
		return khyh;
	}
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}
	public String getSzzs() {
		return szzs;
	}
	public void setSzzs(String szzs) {
		this.szzs = szzs;
	}
	public String getFzrxm() {
		return fzrxm;
	}
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}
	public String getFrdbxm() {
		return frdbxm;
	}
	public void setFrdbxm(String frdbxm) {
		this.frdbxm = frdbxm;
	}
	public String getFrdbzjh() {
		return frdbzjh;
	}
	public void setFrdbzjh(String frdbzjh) {
		this.frdbzjh = frdbzjh;
	}
	public String getFzrdh() {
		return fzrdh;
	}
	public void setFzrdh(String fzrdh) {
		this.fzrdh = fzrdh;
	}
	public String getFrzjlb() {
		return frzjlb;
	}
	public void setFrzjlb(String frzjlb) {
		this.frzjlb = frzjlb;
	}
	public String getFrdbdh() {
		return frdbdh;
	}
	public void setFrdbdh(String frdbdh) {
		this.frdbdh = frdbdh;
	}
	public String getZhyxm() {
		return zhyxm;
	}
	public void setZhyxm(String zhyxm) {
		this.zhyxm = zhyxm;
	}
	public String getZgyzjlb() {
		return zgyzjlb;
	}
	public void setZgyzjlb(String zgyzjlb) {
		this.zgyzjlb = zgyzjlb;
	}
	public String getZgydh() {
		return zgydh;
	}
	public void setZgydh(String zgydh) {
		this.zgydh = zgydh;
	}
	public String getZgyzjh() {
		return zgyzjh;
	}
	public void setZgyzjh(String zgyzjh) {
		this.zgyzjh = zgyzjh;
	}
	public String getZgyyx() {
		return zgyyx;
	}
	public void setZgyyx(String zgyyx) {
		this.zgyyx = zgyyx;
	}
	public String getFzryddh() {
		return fzryddh;
	}
	public void setFzryddh(String fzryddh) {
		this.fzryddh = fzryddh;
	}
	public String getFryddh() {
		return fryddh;
	}
	public void setFryddh(String fryddh) {
		this.fryddh = fryddh;
	}
	public String getZgyyddh() {
		return zgyyddh;
	}
	public void setZgyyddh(String zgyyddh) {
		this.zgyyddh = zgyyddh;
	}
	public String getFxlb() {
		return fxlb;
	}
	public void setFxlb(String fxlb) {
		this.fxlb = fxlb;
	}
	public String getJsjd() {
		return jsjd;
	}
	public void setJsjd(String jsjd) {
		this.jsjd = jsjd;
	}
	public String getYdhjr() {
		return ydhjr;
	}
	public void setYdhjr(String ydhjr) {
		this.ydhjr = ydhjr;
	}
	public String getJccs() {
		return jccs;
	}
	public void setJccs(String jccs) {
		this.jccs = jccs;
	}
	public String getYjny() {
		return yjny;
	}
	public void setYjny(String yjny) {
		this.yjny = yjny;
	}
	public String getJcblbm() {
		return jcblbm;
	}
	public void setJcblbm(String jcblbm) {
		this.jcblbm = jcblbm;
	}
	public String getDwjcl() {
		return dwjcl;
	}
	public void setDwjcl(String dwjcl) {
		this.dwjcl = dwjcl;
	}
	public String getGrjcl() {
		return grjcl;
	}
	public void setGrjcl(String grjcl) {
		this.grjcl = grjcl;
	}
	public String getCzbtl() {
		return czbtl;
	}
	public void setCzbtl(String czbtl) {
		this.czbtl = czbtl;
	}
	public String getBcjcl() {
		return bcjcl;
	}
	public void setBcjcl(String bcjcl) {
		this.bcjcl = bcjcl;
	}
	public String getJcblbz() {
		return jcblbz;
	}
	public void setJcblbz(String jcblbz) {
		this.jcblbz = jcblbz;
	}
	public String getKhrq() {
		return khrq;
	}
	public void setKhrq(String khrq) {
		this.khrq = khrq;
	}
	public String getGrjzny() {
		return grjzny;
	}
	public void setGrjzny(String grjzny) {
		this.grjzny = grjzny;
	}
	public String getDwjzny() {
		return dwjzny;
	}
	public void setDwjzny(String dwjzny) {
		this.dwjzny = dwjzny;
	}
	public String getCzjczy() {
		return czjczy;
	}
	public void setCzjczy(String czjczy) {
		this.czjczy = czjczy;
	}
	public String getBcjzny() {
		return bcjzny;
	}
	public void setBcjzny(String bcjzny) {
		this.bcjzny = bcjzny;
	}
	public String getQjny() {
		return qjny;
	}
	public void setQjny(String qjny) {
		this.qjny = qjny;
	}
	public String getA074() {
		return a074;
	}
	public void setA074(String a074) {
		this.a074 = a074;
	}
	public String getA076() {
		return a076;
	}
	public void setA076(String a076) {
		this.a076 = a076;
	}
	public String getSpbz() {
		return spbz;
	}
	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}
	public String getSpczy() {
		return spczy;
	}
	public void setSpczy(String spczy) {
		this.spczy = spczy;
	}
	public String getSprq() {
		return sprq;
	}
	public void setSprq(String sprq) {
		this.sprq = sprq;
	}
	public String getKhczy() {
		return khczy;
	}
	public void setKhczy(String khczy) {
		this.khczy = khczy;
	}
	public String getKhsqrq() {
		return khsqrq;
	}
	public void setKhsqrq(String khsqrq) {
		this.khsqrq = khsqrq;
	}
	public String getA033() {
		return a033;
	}
	public void setA033(String a033) {
		this.a033 = a033;
	}
	public String getA034() {
		return a034;
	}
	public void setA034(String a034) {
		this.a034 = a034;
	}
	public String getA035() {
		return a035;
	}
	public void setA035(String a035) {
		this.a035 = a035;
	}
	public String getQyyyzhh() {
		return qyyyzhh;
	}
	public void setQyyyzhh(String qyyyzhh) {
		this.qyyyzhh = qyyyzhh;
	}
	public String getSydwfrzsh() {
		return sydwfrzsh;
	}
	public void setSydwfrzsh(String sydwfrzsh) {
		this.sydwfrzsh = sydwfrzsh;
	}
	public String getXzdwpwh() {
		return xzdwpwh;
	}
	public void setXzdwpwh(String xzdwpwh) {
		this.xzdwpwh = xzdwpwh;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getFkdwmc() {
		return fkdwmc;
	}
	public void setFkdwmc(String fkdwmc) {
		this.fkdwmc = fkdwmc;
	}
	public String getFkzh() {
		return fkzh;
	}
	public void setFkzh(String fkzh) {
		this.fkzh = fkzh;
	}
	
}
