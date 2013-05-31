package com.wasoft.model.xtgl;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Dbtrace extends BaseObject implements Serializable {

	private static final long serialVersionUID = 2333930064951456226L;
	private String dbtrace_id;
	private long userid;
	private String ip;
	private String module_name;
	private String webservice_method;	
	private String method_name;
	private Date start_time;
	private Date end_time;
	private double duration;
	private int ret_count;
	private String ret_error;
	

	public Dbtrace(String dbtrace_id)
	{
		this.dbtrace_id = dbtrace_id;
	}
	public void calDuration()
	{
		if (start_time != null && end_time != null)
		{		
			duration = (end_time.getTime() - start_time.getTime())/1000.0;
		}
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public boolean equals(Object o) {
		if (this == o) return true;
        if (!(o instanceof Dbtrace)) return false;

        final Dbtrace dbtrace = (Dbtrace) o;

        if (dbtrace_id != null ? !dbtrace_id.equals(dbtrace.dbtrace_id) : dbtrace.dbtrace_id != null) return false;

        return true;
	}
	public int hashCode() {
		return (dbtrace_id != null ? dbtrace_id.hashCode() : 0);
	}

	public String getDbtrace_id() {
		return dbtrace_id;
	}

	public void setDbtrace_id(String dbtrace_id) {
		this.dbtrace_id = dbtrace_id;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getMethod_name() {
		return method_name;
	}

	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}

	public int getRet_count() {
		return ret_count;
	}

	public void setRet_count(int ret_count) {
		this.ret_count = ret_count;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public String getRet_error() {
		return ret_error;
	}
	public void setRet_error(String ret_error) {
		this.ret_error = ret_error;
	}	
}

