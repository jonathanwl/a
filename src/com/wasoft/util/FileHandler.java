package com.wasoft.util;

import java.io.*;
import org.apache.commons.logging.*;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.wasoft.Constant;
import com.wasoft.calldb.sql.Row;
import com.wasoft.javabean.LogInfo;

public class FileHandler 
{
	protected static final Log log = LogFactory.getLog(FileHandler.class);
	private String data_dir = Constant.DATA_PATH;
	//是否需要以当前日期分割目录
	private boolean rqDir = true;
	
	private static long row = 0;
	private static int handleCount = 0;
	
	public FileHandler()
	{
		if (rqDir)
		{
			data_dir = getDir();
		}
		//log.debug("data_dar=" + data_dir);
	}
	
	public FileHandler(String data_dir)
	{
		createDir(data_dir);
		this.data_dir = data_dir;
	}
	public String getFilePath(String fileName)
	{
		return data_dir + Constant.FILE_SEP + fileName;
	}
	public String getDataDir()
	{
		return data_dir;
	}
	//获取以系统日期生成的目录
	public String getDir() 
	{
		String dir = data_dir + Constant.FILE_SEP + formatDate(new Date(), "yyyyMMdd");
		if (createDir(dir)) 
		{
			return dir;
		} 
		else 
		{
			return ".";
		}
	}
	/**
	 * 如果文件存在返回文件大小，否则返回0
	 * @param filePath
	 * @return
	 */
	public long getFileSize(String filePath)
	{
		File f = new File(filePath);
		if (f.exists())
		{
			return f.length();
		}
		else
		{
			return 0;
		}
	}
	public long getFileRow(String filePath)
	{
		int i_line = 0;
		try 
		{
		    BufferedReader br = new BufferedReader(
		    		new InputStreamReader(new FileInputStream(filePath),Constant.CHARSET));
			String Line = br.readLine();

			while (Line != null) 
			{
				i_line ++;
				Line = br.readLine();
			}			
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
		}
		return i_line;
	}
	//遍历给定目录处理文件
	public void ListFile(String dir)
	{		
		log.debug("List Dir: " + dir);
		File[] fs = getDirFile(dir);
		for(int i=0; i < fs.length; i++)
		{
			try
			{
				if (fs[i].isDirectory())
				{
					ListFile(fs[i].getAbsolutePath());
				}
				else if(fs[i].isFile())
				{
					FileHandle(fs[i]);
				}		
			}
			catch(Exception e)
			{
				log.error(e.getMessage());				
			}
		}		
	}	
	public String getHandleResult()
	{		
		return "row = " + row + ", handleCount = " + handleCount;
	}
	private void FileHandle(File file)
	{
		log.debug("file = " + file.getAbsolutePath());
		if (file.getName().lastIndexOf(".java") != -1)
		{
			row += getFileRow(file.getAbsolutePath());
			handleCount ++;
		}
	}
	// 创建文件
	public void createFile(String path, String filename) {
		try {
			File f = new File(path, filename);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (Exception e) {
			log.error("create file err:" + e.getMessage());
		}
	}
	public void createFile(String filePath) {
		try {
			File f = new File(filePath);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (Exception e) {
			log.error("create file err:" + e.getMessage());
		}
	}
	// 删除文件
	public void deleteFile(String filename){
		deleteFile(this.data_dir, filename);
	}
	public void deleteFile(String path, String filename) {
		try {
			File f = new File(path, filename);
			if (f.exists()) {
				f.delete();
			}
		} catch (Exception e) {
			log.error("delete file err:" + e.getMessage());
		}
	}

	// 创建目录
	public static boolean createDir(String dir) {
		try {
			File f = new File(dir);
			if (!f.exists()) {
				return f.mkdirs();
			} else {
				return true;
			}
		} catch (Exception e) {
			log.error("create director err:" + e.getMessage());
			return false;
		}
	}

	// 删除目录，包括里面的所有文件
	public void deleteDir(String dir) 
	{
		try 
		{
			File f = new File(dir);
			if (f.exists()) {
				if(!f.delete())
				{					
					File[] files = getDirFile(dir);
					for(int i=0; i< files.length; i++)
					{
						if(files[i].isDirectory())
						{
							deleteDir(files[i].getAbsolutePath());
						}
						else
						{
							files[i].delete();
						}
					}
					if(!f.delete())
					{
						log.error("can't delete the dir:" + dir);
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			log.error("delete director err:" + e.getMessage());
		}
	}

	// 返回目录下的所有文件
	public File[] getDirFile(String path) 
	{
		File d = new File(path);
		return d.listFiles();
	}

	// 读取文件内容
	public String readFile(String path, String filename) 
	{
		return readFile(path + Constant.FILE_SEP + filename);
	}
	
	public String readFile(String filePathName)
	{
		return readParseFile(filePathName, null);
	}
	
	public String readParseFile(String filePathName, LogInfo logInfo) 
	{
		StringBuffer sb = new StringBuffer();
		int i_line = 0;
		try 
		{
			//FileReader fr = new FileReader(filePahtName);
			//BufferedReader br = new BufferedReader(fr);
			
		    BufferedReader br = new BufferedReader(
		    		new InputStreamReader(new FileInputStream(filePathName),Constant.CHARSET));
			String Line = br.readLine();

			while (Line != null) 
			{
				i_line ++;
				if (logInfo != null)
				{
					try{logInfo.HandleLine(Line);}
					catch(Exception e){log.error("handle file [" + filePathName + "] line err[" + i_line + "]:" + e.getMessage());}
				}
				sb.append(Line);//.append("\n");
				Line = br.readLine();
			}
			br.close();
			//fr.close();
		} catch (Exception e) {
			log.error("read file [" + filePathName + "] err:" + e.getMessage());
		}
		return sb.toString().replaceAll("'", "\\\\\'");
	}	
	
	// 写入文件
	public boolean writeFile(String str, String filePath) 
	{
		try 
		{
			/*
			FileWriter fw = new FileWriter(filePath);
			fw.write(str);
			fw.close();
			*/
			FileOutputStream fos = new FileOutputStream(filePath, true);
			fos.write(str.getBytes(Constant.CHARSET));
			fos.close();
			return true;
		} catch (Exception e) {
			log.error("write file err:" + e.getMessage());
			return false;
		}
	}
	public boolean writeFile(String str, String path, String filename) 
	{
		String filePath = path + Constant.FILE_SEP + filename;
		return writeFile(str, filePath);
	}

	// 追加文件
	public boolean appendFile(String str, String path, String filename) 
	{
		String filePath = path + Constant.FILE_SEP + filename;
		return appendFile(str, filePath);		
	}
	public static boolean appendFile(String str, String filePath) 
	{		
		try
		{
			/*
			FileWriter fw = new FileWriter(filePath, true);
			fw.write(str);
			fw.close();
			*/
			FileOutputStream fos = new FileOutputStream(filePath, true);
			fos.write(str.getBytes(Constant.CHARSET));
			fos.close();
			return true;			
			
			/*
			 * RandomAccessFile rf = new RandomAccessFile(path +
			 * Constants.FILE_SEP + filename, "rw"); rf.seek(rf.length());
			 * rf.writeChars(str); rf.close(); return true;
			 */
		} catch (Exception e) {
			log.error("write file err:" + e.getMessage());
			return false;
		}
	}

	public boolean writeByteFile(byte[] b, String path, String filename) 
	{
		try 
		{
			File file = new File(path + Constant.FILE_SEP + filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file, true);
			fos.write(b);
			fos.close();
			return true;
		} catch (Exception e) {
			log.error("write file err:" + e.getMessage());
			return false;
		}
	}

	public static String formatDate(Date date, String pattern)
	{
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    return sdf.format(date);
	}
	
	public boolean moveFile(String filename, String dir)
	{
		File file = new File(filename);
		File newFile = new File(dir + File.separator + file.getName());
		System.out.println("newFile path:" + newFile.getAbsoluteFile());
		return file.renameTo(newFile);
	}
	
	public String getFileName(String filePath)
	{
		File file = new File(filePath);
		return file.getName();
	}
	/**
	 * 按机器时间和交易码生成文件名
	 * @param cmd
	 * @return
	 */
	public String getFileName(int cmd)
	{
		String str = String.valueOf(cmd);
		str = StringUtil.repeat("0", (3 - str.length()) ) + str;
		return data_dir + File.separator + str
				+ DateUtil.formatDate(new Date(), "_yyyyMMdd_HHmmss_SS") + ".txt";
	}
	
	public String getRandFileaName(int cmd)
	{
		String str = String.valueOf(cmd);
		str = StringUtil.repeat("0", (3 - str.length()) ) + str;
		return data_dir + File.separator + str
				+ "_" + ( new RandomGUID()).toString() + ".txt";
	}
	
	public String getFileName(String prefix, String postfix, String ext, String pattern)
	{
		return getFileName(prefix, postfix, ext, pattern, new Date());
	}
	public String getFileName(String prefix, String postfix, String ext, String pattern, Date dt)
	{
		return data_dir + File.separator + prefix
			+ DateUtil.formatDate(dt, pattern) + postfix + "." + ext;
	}
	public void produceFile(List <Row> lst, String filename)
			throws Exception {
		produceFile(lst, filename, "|", true);
	}

	public void produceFile(List <Row> lst, String filename,
			String separation) throws Exception {
		produceFile(lst, filename, separation, true);
	}

	public void produceFile(List <Row> lst, String filename,
			String separation, boolean Upper) throws Exception 
	{
		produceFile(lst, filename, separation, Upper, null, null);
	}
	public void produceFile(List <Row> lst, String filename,
			String separation, boolean Upper, int [] width, String [] mapNames) throws Exception 
	{		
		if (Upper)
			filename = filename.toUpperCase();
		log.debug("start produce file " + filename + "...");
		
		if (lst == null) 
		{
			log.debug("list is null,produce blank file.");
			appendFile("", data_dir, filename);
		} 
		else 
		{
			StringBuffer sb = new StringBuffer();			
			log.debug("file row count: " + lst.size());
			
			for (int k = 0, i = 0; i < lst.size(); i++, k++) 
			{
				Row row = lst.get(i);
				log.debug("compare field count with transFile...");
				if (width != null && (width.length != row.getColumnCount()) && mapNames == null)
				{
					log.error("width=" + width.length + ", ColumnCount=" + row.getColumnCount());
					throw new Exception("生成字段数与位长列表不匹配");
				}
				log.debug("set column count...");
				int colCount = mapNames != null ? mapNames.length : row.getColumnCount();
				log.debug("file column count:" + colCount);
				
				if ( k != 0)
					sb.append("\r\n");
				for (int j = 1; j <= colCount; j++)
				{
					//字段有固定宽度
					if (width != null)
					{
						int tmp = j;
						if (mapNames != null)//通过字段名来对应真实的列
						{							
							log.debug("原序：j=" + j + ",原字段名：" + row.getColumnName(j) + ",映射字段名：" + mapNames[j-1] + ",字段长度：" +width[j-1]);															
							j = row.getColumnIndex(mapNames[j-1]);
							log.debug("新序：j=" + j);
							//log.debug("保留原序：tmp=" + tmp + ",新序：j=" + j + ",新映射名：" + mapNames[j-1] + ",新字段长度：" +width[j-1] + "\n");
						}
						//日期型
						if (row.getColumnType(j) == Types.DATE ||
							row.getColumnType(j) == Types.TIME || 
							row.getColumnType(j) == Types.TIMESTAMP)
						{
							sb.append(StringUtil.FillWithChar(row.getDateToString(j), width[tmp-1], ' ', false)
									+ (j == row.getColumnCount() ? "" : separation));
						}
						//数字型，右对齐，左补零，如果小数点后位数不够，补零
						else if(row.getColumnType(j) == Types.REAL ||
								row.getColumnType(j) == Types.FLOAT ||
								row.getColumnType(j) == Types.DOUBLE ||								
								row.getColumnType(j) == Types.DECIMAL ||
								row.getColumnType(j) == Types.NUMERIC)
						{
							String value = row.getDefString(j);
							if (value.lastIndexOf(".") == -1)//无小数位
							{
								//value += ".00";
							}
							else if(value.indexOf(".") == value.length() - 2)//只有一位小数位
							{
								value += "0";
							}
							sb.append(StringUtil.FillWithChar(value, width[tmp-1], '0', true)
									+ (j == row.getColumnCount() ? "" : separation));
						}
						//整数型，右对齐，左补零
						else if(row.getColumnType(j) == Types.TINYINT ||
								row.getColumnType(j) == Types.SMALLINT ||
								row.getColumnType(j) == Types.INTEGER ||
								row.getColumnType(j) == Types.BIGINT)
						{
							sb.append(StringUtil.FillWithChar(row.getDefString(j), width[tmp-1], '0', true)
									+ (j == row.getColumnCount() ? "" : separation));
						}
						else//字符型，左对齐，右补空格
						{
							sb.append(StringUtil.FillWithChar(row.getDefString(j), width[tmp-1], ' ', false)
								+ (j == row.getColumnCount() ? "" : separation));
						}
						//sb.append("["+ row.getColumnType(j) + ","+width[tmp-1]+"]");
						j = tmp; //恢复原循环计数
					}
					else
					{
						sb.append(row.getDefString(j)
							+ (j == row.getColumnCount() ? "" : separation));
					}					
				}
				//sb.append("\r\n");
				if (k >= 5000) 
				{
					log.debug("block buffer");
					appendFile(sb.toString(), data_dir, filename);
					sb = new StringBuffer();
					k = 0;
				}
			}
			appendFile(sb.append("\r\n").toString(), data_dir, filename);
		}
		log.debug("produce file end");
	}
	
}
