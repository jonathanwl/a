package com.wasoft.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class ParseXML
{
  protected final Log log = LogFactory.getLog(getClass());
  private Properties props;

  public Properties getProps()
  {
    return this.props;
  }

  public void parse(String filename)
      throws Exception
  {
    ConfigParser handler = new ConfigParser();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setNamespaceAware(false);
    factory.setValidating(false);

    SAXParser parser = factory.newSAXParser();
    try {
        FileInputStream is = new FileInputStream(new File(filename));
        parser.parse(is, handler);     	
      //parser.parse(filename, handler);
      props = handler.getProps();
    }
    catch(Exception e)
    {
      log.error("解析配置文件出错：" + e.getMessage());
    }
    finally {
      factory = null;
      parser = null;
      handler = null;
    }
  }
  
  public String transFormer(String xmlFileName, String xslFileName)
  {
	  TransformerFactory tFactory = TransformerFactory.newInstance();
	  String outStr = "";
	  try
	  {
		  Transformer transformer = tFactory.newTransformer(new StreamSource(xslFileName));
		  
		  ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  transformer.transform(new StreamSource(xmlFileName), new StreamResult(baos));
		  outStr = baos.toString("UTF-8");
	  }
	  catch(Exception e)
	  {
		  log.error("转换xml出错：" + e.getMessage());
	  }
	  return outStr;
  }
}
