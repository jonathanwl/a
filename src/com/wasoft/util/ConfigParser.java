package com.wasoft.util;

import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ConfigParser extends DefaultHandler {

	private Properties props;

	@SuppressWarnings("unused")
	private String currentName = null;

	private StringBuffer currentValue = new StringBuffer();

	public ConfigParser() {
		this.props = new Properties();
	}

	public Properties getProps() {
		return this.props;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		currentValue.delete(0, currentValue.length());
		this.currentName = qName;
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentValue.append(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		props.put(qName.toLowerCase(), currentValue.toString().trim());
	}

}
