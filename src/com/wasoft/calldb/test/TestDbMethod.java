package com.wasoft.calldb.test;

import java.util.*;
import javax.sql.*;
import javax.naming.Context;
import java.sql.SQLException;
import javax.naming.InitialContext;
import java.lang.reflect.*;
import com.wasoft.calldb.CallDbException;
import com.wasoft.calldb.DbMethod;
import com.wasoft.calldb.sql.GjjmxDataSource;
import com.wasoft.calldb.sql.Row;
import com.wasoft.model.xtgl.User;

/**
 * 测试数据库执行方法
 * @author luojun
 * @version 1.0
 */
public class TestDbMethod{
  private DbMethod dbMethod = null;

  //测试不带User的Execute
  public void testExecute() throws CallDbException,Exception
  {
    String [] names = {
        "DPTJCTJ",//0
        "ROLECHG",//1
        "GETREPORT",//2
        "DPTBGCX",//3
    };
    String procedureName = names[3];
    TestExeCase testExeCase = new TestExeCase();
    Method method = testExeCase.getClass().getMethod(procedureName, null);
    dbMethod.Execute(procedureName, method.invoke(testExeCase, null));
  }
  //测试带User的Execute
  public void testExecute1() throws CallDbException,Exception
  {
    String [] names = {
        "DPTJCRD",//0
        "DPTDWJKDZCHG",//1
    };
    String procedureName = names[1];
    TestExeCase testExeCase = new TestExeCase();
    Method method = testExeCase.getClass().getMethod(procedureName, null);
    dbMethod.Execute(procedureName, method.invoke(testExeCase, null), getUser());
  }
  //测试不带User的Open
  public void testOpen() throws CallDbException,Exception
  {
    String [] names = {
        "GETTABLE",//0
        "P_DKHKJHCX",//1
        "P_DKHKMXCX",//2
    };
    String procedureName = names[1];
    TestOpenCase testOpenCase = new TestOpenCase();
    Method method = testOpenCase.getClass().getMethod(procedureName, null);
    List lst = dbMethod.Open(procedureName, method.invoke(testOpenCase, null));
    displayList(lst);
  }
  //测试带User的Open
  public void testOpen1() throws CallDbException,Exception
  {
    String [] names = {
        "DPTSYSRZCX",//0
        "DPTPLJCDWCX",//1
    };
    String procedureName = names[1];

    TestOpenCase testOpenCase = new TestOpenCase();
    Method method = testOpenCase.getClass().getMethod(procedureName, null);
    List lst = dbMethod.Open(procedureName, method.invoke(testOpenCase, null), getUser());
    displayList(lst);
  }
  //显示open返回的数据
  private void displayList(List lst) throws Exception
  {
    System.out.println("List size: " + lst.size());
    Iterator itr = lst.iterator();
    while(itr.hasNext())
    {
      Row row = (Row)itr.next();
      for(int i=1; i <= row.getColNum(); i++)
      {
        System.out.print(row.getString(i) + "\t");
      }
      System.out.println();
    }
  }
  private User getUser()
  {
	  return new User();
  }
  //获取环境
  private static Context getInitialContext() throws SQLException {
      Properties properties = null;
      try {
          properties = new Properties();

          properties.put(Context.INITIAL_CONTEXT_FACTORY,
                         "weblogic.jndi.WLInitialContextFactory");
          properties.put(Context.PROVIDER_URL, "t3://localhost:7001");
          properties.put(Context.SECURITY_PRINCIPAL, "weblogic");
          properties.put(Context.SECURITY_CREDENTIALS, "weblogic");

          return new InitialContext(properties);
      }
      catch (Exception e)
      {
          throw new SQLException(e.getMessage());
      }
    }
  public void testDbMethod() throws Exception
  {
    try
    {
        //Context ctx = getInitialContext();        
        //DataSource ds = (DataSource)ctx.lookup("jdbc/ggj");
        //DataSource ds = new GjjmxDataSource(0, "weblogic.jndi.WLInitialContextFactory",
        //						"t3://localhost:7001", 
        //						"weblogic", "weblogic", "jdbc/ggj", "gjj");
      
        //DataSource ds = new GjjmxDataSource("oracle.jdbc.driver.OracleDriver",
        //		"jdbc:oracle:thin:@192.168.0.115:1521:orcl",
        //		"gjjmx12_ta","adminconnect");
        DataSource ds = new GjjmxDataSource("com.ibm.as400.access.AS400JDBCDriver",
        		"jdbc:as400://192.168.0.20:50000/dq",
        		"jtpadmin","adminconn");
        dbMethod = DbMethod.makeDbSingleton();
        dbMethod.setDataSource(ds, 3);    	
        
	      /**@todo fill in the test code*/
	      this.testOpen();
	      //this.testOpen1();
	      //this.testExecute();
	      //this.testExecute1();
	      //extend();
    }
    catch(Exception e)
    {
      System.out.println("err:" + e.getMessage());
    }
  }
  public static void main(String[] args) throws Exception
  {
    new TestDbMethod().testDbMethod();    
  }

}
