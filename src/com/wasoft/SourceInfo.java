package com.wasoft;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wasoft.util.FileHandler;

public class SourceInfo {

	private static final Log log = LogFactory.getLog(SourceInfo.class);

	public static void main(String[] args) {
		log.debug("start...");

		String filePath = Constant.UserDir + Constant.FILE_SEP;
		FileHandler fh = new FileHandler();

		log.debug(filePath + "src");

		fh.ListFile(filePath + "src");
		// fh.ListFile(filePath + "webapps");

		log.debug(fh.getHandleResult());

		log.debug("end");
	}

}
