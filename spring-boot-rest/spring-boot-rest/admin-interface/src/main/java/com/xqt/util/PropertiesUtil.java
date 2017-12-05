package com.xqt.util;


import java.io.*;
import java.util.Properties;

/**
 * @author 123
 */
public class PropertiesUtil implements Serializable {
	private static final long serialVersionUID = 1357457676994582623L;
	private String properiesName = "";

	public PropertiesUtil() {

	}

	public PropertiesUtil(String fileName) {
		this.properiesName = fileName;
	}

	public String readProperty(String key) {
		String value = "";
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
			Properties p = new Properties();
			p.load(new InputStreamReader(is, "UTF-8"));
			value = p.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	public Properties getProperties() {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			p.load(new InputStreamReader(is, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	public void writeProperty(String key, String value) {
		InputStream is = null;
		OutputStream os = null;
		Properties p = new Properties();
		try {
			is = new FileInputStream(properiesName);
			p.load(new InputStreamReader(is, "UTF-8"));
			os = new FileOutputStream(PropertiesUtil.class.getClassLoader()
					.getResource(properiesName).getFile());

			p.setProperty(key, value);
			p.store(os, key);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is){
					is.close();
				}
				if (null != os){
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
