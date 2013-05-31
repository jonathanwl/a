package com.wasoft.dataserver.ws.da.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/*
 * ����˫������
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
	 * ����ʱ�ļ������dataservice���ڷ�����
	 * 
	 * 
	 * 2011��5��31�� CHG by HD
	 * 
	 * @param request
	 * @return url��ַ
	 */
	public int getDownloadFileUrl(byte[] data, String ygdh, String idstring,
			String clbm) {

		// �õ�Ӧ�ó����ڷ������ϵ������ַ
		// String dirUrl = "";

		/*
		 * dirUrl = request.getSession().getServletContext().getRealPath( "/") +
		 * "/elda/tempImages";
		 */

		// dirUrl = this.dirurl;
		int url = 1;

		// String path = "";

		// ������ʱ�ļ���
		File tempDir = new File(this.dirurl);
		if (!tempDir.exists()) {
			tempDir.mkdir();
			System.out.println("tempDir make sucess");
		}

		// ɾ��֮ǰ���ɵ���ʱ�ļ�,2011��5��31�� ���¿ؼ�,������ʾ����ͼƬʱ,����������ɾ��֮ǰ���ص��ļ�

		// if (tempDir.list().length > 0) {
		// cleanTempImages(tempDir);
		// }

		// �����µ���ʱ�ļ�
		File tempfile = null;

		try {

			String tempfilename = ygdh + idstring + clbm;

			// ����������ʽȥ�����ɵ���ʱ�ļ��������������ַ�

			tempfilename = tempfilename.trim().replaceAll("\\W", "");

			// �����ص�ͼƬ�ڷ�����������ʱ�ļ�
			tempfile = File.createTempFile(tempfilename, "", tempDir);

			FileOutputStream fis = null;
			fis = new FileOutputStream(tempfile);
			fis.write(data);
			fis.close();

			// �õ����ص���ʱ�ļ���url
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
	 * �˷�������ɾ����ʱ�ļ����µ���ʱ�ļ� 2010��9��29�� ADD BY HD
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
	 * ���˫�����⣬�õ�ϵͳ�ĵ�ǰʱ�� ��ʽ"yyyy��MM��dd�� HHʱmm��ss��" zy 2011.5.4
	 * 
	 * 
	 * @return
	 */
	public static String getWaterMark() {

		String curdate = "";

		// ��ȡ��ǰ���û���������
		// ���õ�ǰʱ��
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
		TimeZone.setDefault(tz);
		// �õ���ǰ����
		curdate = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��").format(Calendar
				.getInstance().getTime());
		
		return curdate;
	}

	/**
	 * ���˫�����⣬�õ�ϵͳ�ĵ�ǰʱ�� ��ʽyyyy-MM-dd zy 2011.5.4
	 * 
	 * 
	 * @return
	 */
	public static String getData() {
		String nowDate = "";
		// ��ȡ��ǰ���û���������
		// ���õ�ǰʱ��
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
		TimeZone.setDefault(tz);
		// �õ���ǰ����
		nowDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar
				.getInstance().getTime());
		return nowDate;
	}

}
