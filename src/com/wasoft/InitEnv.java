package com.wasoft;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wasoft.calldb.DbMethod;
import com.wasoft.calldb.sql.GjjmxDataSource;
import com.wasoft.util.FileHandler;
import com.wasoft.util.ParseXML;

public class InitEnv {

	private static final Log log = LogFactory.getLog(InitEnv.class);

	private static String userid = "admin";

	private static String password = "admin";

	public static boolean initServer() {
		return (initApp() && initCommonData());
	}

	/**
	 * 初始化应用环境
	 * 
	 * @return 成功返回true
	 */
	private static boolean initApp() {
		try {
			if (!FileHandler.createDir(Constant.DATA_PATH)) {
				log.error("创建日志目录不成功！");
				return false;
			}
			if (System.getProperty("app.config") != null) {
				Constant.CONGFIG_FILE = Constant.CONFIG_PATH
						+ System.getProperty("app.config");
			}
			if (System.getProperty("app.container") != null) {
				Constant.CONTAINER_FILE = Constant.CONFIG_PATH
						+ System.getProperty("app.container");
			}
			log.debug("解析配置文件");
			ParseXML px = new ParseXML();
			px.parse(Constant.CONGFIG_FILE);
			Properties prop = px.getProps();
			System.out.println("Constant.CONGFIG_FILE=" + Constant.CONGFIG_FILE
					+ "dbtye=" + Integer.parseInt(prop.getProperty("dbtype")));
			Constant.POOL_SIZE = Integer
					.parseInt(prop.getProperty("pool-size"));
			Constant.LogCounter = Integer.parseInt(prop
					.getProperty("log-counter"));
			Constant.WebServicePort = Integer.parseInt(prop
					.getProperty("webservice-port"));
			log.debug("初始化数据源...");

			int dbtype = Integer.parseInt(prop.getProperty("dbtype"));

			String driver = prop.getProperty("jdbc-driver");
			String dburl = prop.getProperty("jdbc-url");
			String dbuser = prop.getProperty("jdbc-user");
			String dbpassword = prop.getProperty("jdbc-password");

			DataSource ds = new GjjmxDataSource(driver, dburl, dbuser,
					dbpassword, prop.getProperty("datasource-initialsize"),
					prop.getProperty("datasource-maxactive"), prop
							.getProperty("datasource-maxidle"), prop
							.getProperty("datasource-minidle"), prop
							.getProperty("datasource-maxwait"), prop
							.getProperty("datasource-removeabandoned"), prop
							.getProperty("datasource-removeabandonedtimeout"),
					prop.getProperty("datasource-logabandoned"), prop
							.getProperty("datasource-testonborrow"));

			DbMethod dm = DbMethod.makeDbSingleton();
			dm.setDataSource(ds, dbtype);

			log.debug("初始化数据源完成");

			return true;
		} catch (Exception e) {
			log.error("初始化应用环境出错: " + e.getMessage());
			return false;
		}
	}

	private static boolean initCommonData() {
		try {
			log.debug("初始化基本数据信息...");

			log.debug("初始化信息完成！");

			return true;
		} catch (Exception e) {
			log.error("初始化银行信息出错：" + e.getMessage());
			return false;
		}
	}

	public static String getUserid() {
		return userid;
	}

	public static String getPassword() {
		return password;
	}

}
