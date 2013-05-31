package com.wasoft.dataserver.ws.da.bean;

import java.io.Serializable;

/**
 * 材料扫描录入-人事档案
 * @author zwh 2011年12月17日
 * 档案审核归档-人事档案归档
 * @author zwh 2011年12月20日
 */
public class RsdalrBean implements Serializable{
	private long id;//
	private String ename;//姓名//
	private String ygdh;//预归档号//
	private String bh;//编号//
	private String sex;//性别//
	
	private String mz;//民族//
	private String sfzh;//身份证号//
	private String csrq;//出生日期//
	private int age;//年龄//
	private String hyzk;//婚姻状况//
	
	private String cjgzsj;//参加工作时间//
	private int gl;//工龄//
	private String rdtsj;//入党团时间//
	private String dh;//固定电话//
	private String phone;//手机//
	
	private String email;//邮箱//
	private String qq;//qq号//
	private String xl;//学历//
	private String school;//毕业院校//
	private String zy;//专业//
	
	private String address;//家庭住址//
	private String hkszd;//户口所在地//
	private String jg;//籍贯//
	private String djrq;//登记日期//
	private String djrq_begin;
	
	private String djrq_end;
	private String djr;//登记人//
	private String type;//档案类型//
	private String ywlsh;//业务流水号
	private String typeb;//档案类型编号//
	private String glb;//管理部名称
	
	private int isAdmin;//是否为系统管理员 
	
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
