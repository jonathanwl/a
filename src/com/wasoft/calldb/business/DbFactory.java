/*
 * 数据库工厂
 */
package com.wasoft.calldb.business;

import com.wasoft.calldb.*;
import com.wasoft.calldb.business.impl.*;
import com.wasoft.calldb.business.impl.common.Wasys3_commonImpl;

import java.util.ArrayList;
import java.lang.reflect.Field;

public class DbFactory 
{
    public DbFactory() {}

    public String toString() 
    {
        return getClass().getName();
    }

    public AbstractDbInterface getDbInterface(String procedureName) 
    								throws CallDbException 
    {
        if (procedureName.startsWith("Common_")) {   //通用
            return new Wasys3_commonImpl();
        } 
        if (procedureName.startsWith("REPORT_")) {           //报表
            return new ReportServiceImpl();
        } 
        if (procedureName.startsWith("DA_")){               //数据审计
        	return new DaServiceImpl();
        }
        else {
            throw new CallDbException("调用过程约定名未定义或未实现!");
        }
    }

    // --------------------------------------------------------------------------
   
    public String[] getAllProcedureName() {
        String[] procName = null;
        try {
            ArrayList <String> list = new ArrayList <String>();
            Class <? extends DbFactory> cls = this.getClass();
            Field fieldlist[] = cls.getDeclaredFields();
            for (int i = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                if (fld.getType().toString().equals("class [Ljava.lang.String;")) {
                    Field field = cls.getDeclaredField(fld.getName());
                    String[] tmp = (String[]) field.get(null);
                    for (int j = 0; j < tmp.length; j++) {
                        list.add(tmp[j]);
                    }
                }
            }
            procName = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                procName[i] = list.get(i);
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
        return procName;
    }


    // --------------------------------------------------------------------------
    public static void main(String[] args) {
        //DbFactory dbFactory1 = new DbFactory();
    }
}
