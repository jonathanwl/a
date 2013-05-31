package com.wasoft.dataserver.ws;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.wasoft.calldb.sql.Row;

public class CommBase {
	/*
	 * 分页处理1
	 */
	public String[][] paging(List<Row> lis,int pagesize,int pagenum){	
	     String[][] str1=null;
	     String[] str2=null;
	     int i = 0;
	     try
	     {
	    	if(pagesize == 0)
		    {  
	           str1=new String[lis.size()][];
	           int j = 0;
	           for(Row row : lis){
	               i=row.getColumnCount();
	               str2 = new String[i];
	               for (int k= 0;k < i; k++){
	                   String str = " ";
	                  switch (row.getColumnType(k+1)){
	                     case Types.BLOB:
	                         break;
	                     case Types.FLOAT:
	                    	 str = (row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.NUMERIC:
	                    	 str = (row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.DOUBLE:
	                    	 str = (row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.DECIMAL:
	                    	 str = (row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.SMALLINT:
	                    	 str = (row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.BIGINT:
	                    	 str = (row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                       case Types.INTEGER:
	                    	   str = (row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     default:
	                         str = (row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?" ":row.getTrimString(k+1));
	                  }  
	                  str2[k]=str;
	               }
	               str1[j++]=str2;		
	            }
		     }
	    	 else
	    	 {
	    		 int k=(lis.size()<pagenum*pagesize?lis.size()-((pagenum-1)*pagesize):pagesize); 
	    		 str1=new String[k][];
	    		 int j = 0;
	    		 for(int m=(pagenum-1)*pagesize;m<lis.size()&&m<pagenum*pagesize;m++)
		    	 {
	    			 Row row1=lis.get(m);
	    			 i=row1.getColumnCount();
	                 str2 = new String[i];
	                 for(int r= 0;r < i; r++){
	                     String str = " ";
	                     switch (row1.getColumnType(r+1)){
	                       case Types.BLOB:
	                           break;
	                       case Types.FLOAT:
		                    	 str = (row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.NUMERIC:
		                    	 str = (row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.DOUBLE:
		                    	 str = (row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.DECIMAL:
		                    	 str = (row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.SMALLINT:
		                    	 str = (row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.BIGINT:
		                    	 str = (row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                       case Types.INTEGER:
		                    	   str = (row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
	                       default:
	                           str = (row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?" ":row1.getTrimString(r+1));
	                     }   
	                     str2[r]=str;
	                  }
	                  str1[j++]=str2;	
		         }  
	    	 }
	          return str1;
	     }
	     catch(Exception e)
	     {  
	         e.printStackTrace();
	     }
	     return null;
	}
	/*
	 * 分页处理2
	 */
	public String[][] paging1(List<Row> lis,int pagesize,int pagenum){	
	    String[][] str1=null;
	    String[] str2=null;
	    int i = 0;
	    try
	    {
	   	   if(pagesize == 0)
		   {  
	          str1=new String[lis.size()][];
	          int j = 0;
	          for(Row row : lis){
	              i=row.getColumnCount();
	              str2 = new String[i];
	              for (int k= 0;k < i; k++){
	                  String str = row.getColumnType(k+1)+"##"+row.getColumnName(k+1);
	                  switch (row.getColumnType(k+1)){
	                     case Types.BLOB:
	                    	 str += "##"+" ";
	                        break;
	                     case Types.FLOAT:
	                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.NUMERIC:
	                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.DOUBLE:
	                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.DECIMAL:
	                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.SMALLINT:
	                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.BIGINT:
	                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                     case Types.INTEGER:
	                    	   str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
	                           break;
	                    default:
	                    	   str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?" ":row.getTrimString(k+1));
	                 }   
	                 str2[k]=str;
	              }
	              str1[j++]=str2;		
	           }
		   }
	   	   else
	   	   {
	   		 int k=(lis.size()<pagenum*pagesize?lis.size()-((pagenum-1)*pagesize):pagesize); 
	   		 str1=new String[k][];
	   		 int j = 0;
	   		 for(int m=(pagenum-1)*pagesize;m<lis.size()&&m<pagenum*pagesize;m++)
		     {
	   			 Row row1=lis.get(m);
	   			 i=row1.getColumnCount();
	             str2 = new String[i];
	             for(int r= 0;r < i; r++){
	                 String str = row1.getColumnType(k+1)+"##"+row1.getColumnName(k+1);
	                    switch (row1.getColumnType(r+1)){
	                      case Types.BLOB:
	                    	  str += "##"+" ";
	                          break;
	                      case Types.FLOAT:
		                    	 str +="##"+(row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.NUMERIC:
		                    	 str +="##"+(row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.DOUBLE:
		                    	 str +="##"+(row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.DECIMAL:
		                    	 str +="##"+(row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.SMALLINT:
		                    	 str +="##"+(row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.BIGINT:
		                    	 str +="##"+(row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
		                     case Types.INTEGER:
		                    	 str +="##"+(row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?"0":row1.getTrimString(r+1));
		                           break;
	                         default:
	                             str+="##"+(row1.getTrimString(r+1)==null||row1.getTrimString(r+1).trim().equals("")?" ":row1.getTrimString(r+1));
	                    }   
	                    str2[r]=str;
	                 }
	                 str1[j++]=str2;	
		      }  
	   	   }
	       return str1;
	    }
	    catch(Exception e)
	    {  
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/*通用分页处理 
	 lis 查询集合
	 pagesize 每页记录数
	 pagenum  页数
	*/
	public List <Row> expand_paging(List<Row> lis,int pagesize,int pagenum){	
	     List <Row> list=null;
	     try
	     {
	    	if(pagesize == 0)
		    {  
	           list=lis;
		    }
	    	else
	    	{
	    		 list = new ArrayList <Row>();
	    		 for(int m=(pagenum-1)*pagesize;m<lis.size()&&m<pagenum*pagesize;m++)
		    	 {
	    			 Row row1=lis.get(m);
	    			 list.add(row1);
		         }  
	    	 }
	     }
	     catch(Exception e)
	     {  
	         e.printStackTrace();
	     }
	     return list;
	}

}
