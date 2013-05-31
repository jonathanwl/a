package com.wasoft.dataserver.ws.da.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/*
 * 决绝双机问题
 * 
 */
public class ImageUploadBean {

	public ImageUploadBean(String dirurl) {
		this.dirurl = dirurl;
	}

	private String tempfile;

	private String url;

	private String dirurl;

	public String getDirurl() {
		return dirurl;
	}

	public void setDirurl(String dirurl) {
		this.dirurl = dirurl;
	}

	public String getTempfile() {
		return tempfile;
	}

	public void setTempfile(String tempfile) {
		this.tempfile = tempfile;
	}

	/**
	 * 将临时文件存放在dataservice所在服务器
	 * 
	 * 
	 * 2011年5月31日 CHG by HD
	 * 
	 * @param request
	 * @return url地址
	 */
	public int getDownloadFileUrl(byte[] data, String ygdh, String idstring,
			String clbm) {

		// 得到应用程序在服务器上的物理地址
		// String dirUrl = "";

		/*
		 * dirUrl = request.getSession().getServletContext().getRealPath( "/") +
		 * "/elda/tempImages";
		 */

		// dirUrl = this.dirurl;
		int url = 1;

		// String path = "";

		// 创建临时文件夹
		File tempDir = new File(this.dirurl);
		if (!tempDir.exists()) {
			tempDir.mkdir();
			System.out.println("tempDir make sucess");
		}

		// 删除之前生成的临时文件,2011年5月31日 更新控件,下载显示所有图片时,不能在这里删除之前下载的文件

		// if (tempDir.list().length > 0) {
		// cleanTempImages(tempDir);
		// }

		// 生成新的临时文件
		File tempfile = null;

		try {

			String tempfilename = ygdh + idstring + clbm;

			// 利用正则表达式去掉生成的临时文件名的所有特殊字符

			tempfilename = tempfilename.trim().replaceAll("\\W", "");

			// 将下载的图片在服务器生成临时文件
			tempfile = File.createTempFile(tempfilename, "", tempDir);

			FileOutputStream fis = null;
			fis = new FileOutputStream(tempfile);
			fis.write(data);
			fis.close();

			// 得到下载的临时文件的url
			this.setTempfile(tempfile.getName());

		} catch (IOException e) {

			url = 0;
			e.printStackTrace();

		} finally {
			if (tempfile != null) {
				tempfile.deleteOnExit();
			}
		}

		return url;
	}

	/**
	 * 此方法用于删除临时文件夹下的临时文件 2010年9月29日 ADD BY HD
	 * 
	 * @param tempDir
	 */
	public static void cleanTempImages(File tempDir) {

		if (tempDir.exists()) {
			if (tempDir.isDirectory()) {
				File[] fileList = tempDir.listFiles();
				for (int i = 0; i < fileList.length; i++) {
					File tempImage = fileList[i];
					if (tempImage.isDirectory()) {
						while (tempImage.list().length > 0) {
							cleanTempImages(tempImage);
						}
						tempImage.delete();
					} else {
						tempImage.delete();
					}
				}
			} else {
				tempDir.delete();
			}
		}

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 解决双机问题，得到系统的当前时间 格式"yyyy年MM月dd日 HH时mm分ss秒" zy 2011.5.4
	 * 
	 * 
	 * @return
	 */
	public static String getWaterMark() {

		String curdate = "";

		// 获取当前的用户名和日期
		// 设置当前时区
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
		TimeZone.setDefault(tz);
		// 得到当前日期
		curdate = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(Calendar
				.getInstance().getTime());
		
		return curdate;
	}

	/**
	 * 解决双机问题，得到系统的当前时间 格式yyyy-MM-dd zy 2011.5.4
	 * 
	 * 
	 * @return
	 */
	public static String getData() {
		String nowDate = "";
		// 获取当前的用户名和日期
		// 设置当前时区
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
		TimeZone.setDefault(tz);
		// 得到当前日期
		nowDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar
				.getInstance().getTime());
		return nowDate;
	}

}
