package com.wasoft.model.xtgl;

import java.io.*;
import java.util.*;

import org.apache.commons.lang.builder.*;

public class User extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359411L;
    protected long userid=0;
    protected String username;
    protected String password;
    protected String confirmPassword;
    protected String address;
    protected String city;
    protected String postalCode;
    protected String email;
    protected String phoneNumber;
    protected String website;
    protected String passwordHint;
    protected String gjdbm;
    protected String lsgxbm;
    protected String lsgxmc;
    protected String dwbm;
    protected String dwmc;
    protected String yhbm = "";
    protected String zxjgbm;
    protected String dkspjbbm;
    protected String gjspjbbm;
    protected boolean enabled;
    protected String creator;
    protected Set roles = new HashSet();
    protected Set createdUsers;
    protected int retCode;//返回码
    protected String crud;
    private String loginmc;
    private String loginip;
    private String tpcode1 = ""; //记入日志返回码(系统)
    private String tpcode2 = ""; //记入日志返回码(模块)
    private String topic;//

    private Date zwdate;
    private String module_name="";
    private String webservice_method;
    private String method_name="";

    public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	/**
     * Returns the username.
     *
     * @return String
     *
     * @struts.validator type="required"
     * @hibernate.id column="username" length="20" generator-class="assigned"
     *               unsaved-value="version"
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password.
     * @return String
     *
     * @struts.validator type="required"
     * @struts.validator type="twofields" msgkey="errors.twofields"
     * @struts.validator-args arg1resource="userForm.password"
     * @struts.validator-args arg1resource="userForm.confirmPassword"
     * @struts.validator-var name="secondProperty" value="confirmPassword"
     * @hibernate.property column="password" not-null="true"
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the confirmedPassword.
     * @return String
     *
     * @struts.validator type="required"
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }


    /**
     * Returns the address.
     *
     * @return Address
     *
     * @hibernate.component
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the email.  This is an optional field for specifying a
     * different e-mail than the username.
     *
     * @return String
     *
     * @struts.validator type="required"
     * @struts.validator type="email"
     * @hibernate.property name="email" not-null="true" unique="true"
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the phoneNumber.
     *
     * @return String
     *
     * @struts.validator type="mask" msgkey="errors.phone"
     * @struts.validator-var name="mask" value="${phone}"
     * @hibernate.property column="phone_number" not-null="false"
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the website.
     *
     * @return String
     *
     * @struts.validator type="required"
     * @hibernate.property column="website" not-null="false"
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Returns the passwordHint.
     *
     * @return String
     *
     * @struts.validator type="required"
     * @hibernate.property column="password_hint" not-null="false"
     */
    public String getPasswordHint() {
        return passwordHint;
    }

    /**
     * Returns the user's roles.
     *
     * @return Set
     *
     * @hibernate.set table="user_role" cascade="save-update" lazy="false"
     * @hibernate.collection-key column="username"
     * @hibernate.collection-many-to-many class="com.jtpsoft.model.Role"
     *                                    column="role_name"
     */
    public Set getRoles() {
        return roles;
    }
    /**
     * Sets the username.
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password.
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the confirmedPassword.
     * @param confirmPassword The confirmed password to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Sets the address.
     * @param address The address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the email.
     * @param email The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phoneNumber.
     * @param phoneNumber The phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the website.
     * @param website The website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @param passwordHint The password hint to set
     */
    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    /**
     * Sets the roles.
     *
     * @param roles The roles to set
     */
    public void setRoles(Set roles) {
        this.roles = roles;
    }

    /**
     * @return Returns the enabled.
     * @hibernate.property column="enabled"
     */
    public boolean getEnabled() {
        // isEnabled doesnt' work for copying properties to Struts ActionForms
        return enabled;
    }

    /**
     * @param enabled The enabled to set.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRetCode(int retCode)
    {
        this.retCode = retCode;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setCrud(String crud)
    {
        this.crud = crud;
    }

    public void setCreatedUsers(Set createdUsers)
    {
        this.createdUsers = createdUsers;
    }

  public void setDkspjbbm(String dkspjbbm)
  {
    this.dkspjbbm = dkspjbbm;
  }

  public void setDwbm(String dwbm)
  {
    this.dwbm = dwbm;
  }

  public void setGjdbm(String gjdbm)
  {
    this.gjdbm = gjdbm;
  }

  public void setGjspjbbm(String gjspjbbm)
  {
    this.gjspjbbm = gjspjbbm;
  }

  public void setLsgxbm(String lsgxbm)
  {
    this.lsgxbm = lsgxbm;
  }

  public void setYhbm(String yhbm)
  {
    this.yhbm = yhbm;
  }

  public void setZxjgbm(String zxjgbm)
  {
    this.zxjgbm = zxjgbm;
  }

  public void setDwmc(String dwmc)
  {
    this.dwmc = dwmc;
  }

  public void setLoginmc(String loginmc)
  {
    this.loginmc = loginmc;
  }

  public void setLoginip(String loginip)
  {
    this.loginip = loginip;
  }

  public void setTpcode2(String tpcode2)
  {
    this.tpcode2 = tpcode2;
  }

  public void setTpcode1(String tpcode1)
  {
    this.tpcode1 = tpcode1;
  }

  public void setTopic(String topic)
  {
    this.topic = topic;
  }

  public void setZwdate(Date zwdate)
  {
    this.zwdate = zwdate;
  }

  public void setLsgxmc(String lsgxmc)
  {
    this.lsgxmc = lsgxmc;
  }

  public int getRetCode()
    {
        return this.retCode;
    }

    public String getCreator()
    {
        return creator;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public String getCrud()
    {
        return crud;
    }

    public Set getCreatedUsers()
    {
        return createdUsers;
    }

  public String getDkspjbbm()
  {
    return dkspjbbm;
  }

  public String getDwbm()
  {
    return dwbm;
  }

  public String getGjdbm()
  {
    return gjdbm;
  }

  public String getGjspjbbm()
  {
    return gjspjbbm;
  }

  public String getLsgxbm()
  {
    return lsgxbm;
  }

  public String getYhbm()
  {
    return yhbm;
  }

  public String getZxjgbm()
  {
    return zxjgbm;
  }

  public String getDwmc()
  {
    return dwmc;
  }

  public String getLoginmc()
  {
    return loginmc;
  }

  public String getLoginip()
  {
    return loginip;
  }

  public String getTpcode1()
  {
    return tpcode1;
  }

  public String getTpcode2()
  {
    return tpcode2;
  }

  public String getTopic()
  {
    return topic;
  }

  public Date getZwdate()
  {
    return zwdate;
  }
   
  public String getModule_name() {
	return module_name;
}


public void setModule_name(String module_name) {
	this.module_name = module_name;
}


public String getWebservice_method() {
	return webservice_method;
}


public void setWebservice_method(String webservice_method) {
	this.webservice_method = webservice_method;
}


public String getLsgxmc()
  {
    return lsgxmc;
  }

    class FuncPrivilege
    {
      boolean getF203(){return true;}
    }

    public String getAllCreatedUser4sql()
    {
        StringBuffer sb = new StringBuffer();
        //加入自己
        sb.append("'").append(userid).append("'").append(",");
        Iterator itr = createdUsers.iterator();
        while(itr.hasNext())
        {
            sb.append("'").append(((String)itr.next())).append("'").append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String getYhbm4sql()
    {
      StringBuffer sb = new StringBuffer();
      StringTokenizer st_yhbm = new StringTokenizer(yhbm,",");
      while(st_yhbm.hasMoreTokens())
      {
        sb.append("'").append(st_yhbm.nextToken()).append("'").append(",");
      }
      return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final User user = (User) o;

        if (userid != 0 ? userid!=user.getUserid() : user.getUserid() !=0)
        	return false;
        return true;
    }

    public int hashCode() {
        return (username != null ? username.hashCode() : 0);
    }

    /**
     * Generated using Commonclipse (http://commonclipse.sf.net)
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);

    }
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
}
