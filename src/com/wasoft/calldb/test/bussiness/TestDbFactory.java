package com.wasoft.calldb.test.bussiness;

import com.wasoft.calldb.business.DbFactory;


public class TestDbFactory
{
 // private DbFactory dbFactory = null;

  public static void main(String[] args)
      throws Exception
  {
    String [] procName = (new DbFactory()).getAllProcedureName();
    for (int i = 0; i < procName.length; i++)
    {
      for (int j = i+1; j < procName.length; j++)
      {
        if (procName[i].equals(procName[j]))
        {
          System.out.println("the same name:[" + procName[i] + "]");
        }
      }
    }
    System.out.println("check over");
  }
}
