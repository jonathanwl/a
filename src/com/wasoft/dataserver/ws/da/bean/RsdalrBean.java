package com.wasoft.dataserver.ws.da.bean;

import java.io.Serializable;

/**
 * ����ɨ��¼��-���µ���
 * @author zwh 2011��12��17��
 * ������˹鵵-���µ����鵵
 * @author zwh 2011��12��20��
 */
public class RsdalrBean implements Serializable{
	private long id;//
	private String ename;//����//
	private String ygdh;//Ԥ�鵵��//
	private String bh;//���//
	private String sex;//�Ա�//
	
	private String mz;//����//
	private String sfzh;//���֤��//
	private String csrq;//��������//
	private int age;//����//
	private String hyzk;//����״��//
	
	private String cjgzsj;//�μӹ���ʱ��//
	private int gl;//����//
	private String rdtsj;//�뵳��ʱ��//
	private String dh;//�̶��绰//
	private String phone;//�ֻ�//
	
	private String email;//����//
	private String qq;//qq��//
	private String xl;//ѧ��//
	private String school;//��ҵԺУ//
	private String zy;//רҵ//
	
	private String address;//��ͥסַ//
	private String hkszd;//�������ڵ�//
	private String jg;//����//
	private String djrq;//�Ǽ�����//
	private String djrq_begin;
	
	private String djrq_end;
	private String djr;//�Ǽ���//
	private String type;//��������//
	private String ywlsh;//ҵ����ˮ��
	private String typeb;//�������ͱ��//
	private String glb;//��������
	
	private int isAdmin;//�Ƿ�Ϊϵͳ����Ա 
	
	public String getGlb() {
		return glb;
	}

	public void setGlb(String glb) {
		this.glb = glb;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	private int count;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getYgdh() {
		return ygdh;
	}

	public void setYgdh(String ygdh) {
		this.ygdh = ygdh;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHyzk() {
		return hyzk;
	}

	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	public String getCjgzsj() {
		return cjgzsj;
	}

	public void setCjgzsj(String cjgzsj) {
		this.cjgzsj = cjgzsj;
	}

	public int getGl() {
		return gl;
	}

	public void setGl(int gl) {
		this.gl = gl;
	}

	public String getRdtsj() {
		return rdtsj;
	}

	public void setRdtsj(String rdtsj) {
		this.rdtsj = rdtsj;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHkszd() {
		return hkszd;
	}

	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getDjrq() {
		return djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

	public String getDjrq_begin() {
		return djrq_begin;
	}

	public void setDjrq_begin(String djrqBegin) {
		djrq_begin = djrqBegin;
	}

	public String getDjrq_end() {
		return djrq_end;
	}

	public void setDjrq_end(String djrqEnd) {
		djrq_end = djrqEnd;
	}

	public String getDjr() {
		return djr;
	}

	public void setDjr(String djr) {
		this.djr = djr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYwlsh() {
		return ywlsh;
	}

	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}

	public String getTypeb() {
		return typeb;
	}

	public void setTypeb(String typeb) {
		this.typeb = typeb;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
