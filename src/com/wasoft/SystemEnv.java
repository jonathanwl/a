package com.wasoft;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public class SystemEnv {
	private static final Log log = LogFactory.getLog(SystemEnv.class);

	static HashMap<String, String> hm = new HashMap<String, String>();

	static {
		hm.put("java.version", "Java 运行时环境版本");
		hm.put("java.vendor", "Java 运行时环境供应商");
		hm.put("java.vendor.url", "Java 供应商的 URL");
		hm.put("java.home", "Java 安装目录");
		hm.put("java.vm.specification.version", "Java 虚拟机规范版本");
		hm.put("java.vm.specification.vendor", "Java 虚拟机规范供应商");
		hm.put("java.vm.specification.name", "Java 虚拟机规范名称");
		hm.put("java.vm.version", "Java 虚拟机实现版本");
		hm.put("java.vm.vendor", "Java 虚拟机实现供应商");
		hm.put("java.vm.name", "Java 虚拟机实现名称");
		hm.put("java.specification.version", "Java 运行时环境规范版本");
		hm.put("java.specification.vendor", "Java 运行时环境规范供应商");
		hm.put("java.specification.name", "Java 运行时环境规范名称");
		hm.put("java.class.version", "Java 类格式版本号");
		hm.put("java.class.path", "Java 类路径");
		hm.put("java.library.path", "加载库时搜索的路径列表");
		hm.put("java.io.tmpdir", "默认的临时文件路径");
		hm.put("java.compiler", "要使用的 JIT 编译器的名称");
		hm.put("java.ext.dirs", "一个或多个扩展目录的路径");
		hm.put("os.name", "操作系统的名称");
		hm.put("os.arch", "操作系统的架构");
		hm.put("os.version", "操作系统的版本");
		hm.put("file.separator", "文件分隔符（在 UNIX 系统中是“/”）");
		hm.put("path.separator", "路径分隔符（在 UNIX 系统中是“:”）");
		hm.put("line.separator", "行分隔符（在 UNIX 系统中是“/n”）");
		hm.put("user.name", "用户的账户名称");
		hm.put("user.home", "用户的主目录");
		hm.put("user.dir", "用户的当前工作目录");
	}

	public static String getSystemEnv(String str) {
		return System.getProperty(str);
	}

	public static void main(String[] args) {
		log.debug(SystemInfo());
		// SystemInfo4Html();

		OSBean();
	}

	public static void OSBean() {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();

		// 总的物理内存
		long totalMemorySize = osmxb.getTotalPhysicalMemorySize();
		// 剩余的物理内存
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
		// 已使用的物理内存
		long usedMemory = totalMemorySize - freePhysicalMemorySize;

		ThreadGroup parentThread;
		for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
				.getParent() != null; parentThread = parentThread.getParent())
			;

		int totalThread = parentThread.activeCount();

		double cpuRatio = 0;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().startsWith("windows")) {
			cpuRatio = getCpuRatioForWindows();
		} else {
			cpuRatio = getCpuRateForLinux();
		}
		log.debug("totalMemorySize=" + toMb(totalMemorySize) + "MB");
		log.debug("freePhysicalMemorySize=" + toMb(freePhysicalMemorySize)
				+ "MB");
		log.debug("usedMemory=" + toMb(usedMemory) + "MB");
		log.debug("totalThread=" + totalThread);
		log.debug("cpuRatio=" + cpuRatio);
	}

	public static String SystemInfo4Html() {
		StringBuffer sb = new StringBuffer();
		sb.append("[\"").append(
				"<span style='color:red;'>运行环境:</span>\",\"\"],\n");
		sb.append("[\"").append("虚拟机可用处理器的数目\",\"").append(
				Runtime.getRuntime().availableProcessors()).append("\"],\n");
		sb.append("[\"").append("虚拟机空闲内存量\",\"").append(
				toMb(Runtime.getRuntime().freeMemory()) + "Mb")
				.append("\"],\n");
		sb.append("[\"").append("虚拟机试图使用的最大内存\",\"").append(
				toMb(Runtime.getRuntime().maxMemory()) + "Mb").append("\"],\n");
		sb.append("[\"").append("虚拟机中的内存总量\",\"").append(
				toMb(Runtime.getRuntime().totalMemory()) + "Mb").append(
				"\"],\n");

		sb.append("[\"").append(
				"<span style='color:red;'>系统属性:</span>\",\"\"],\n");
		sb.append(systemProperties(true));

		sb.append("[\"").append(
				"<span style='color:red;'>环境变量:</span>\",\"\"],\n");
		for (Map.Entry entry : System.getenv().entrySet()) {
			sb.append("[\"").append(entry.getKey()).append("\",\"").append(
					entry.getValue().toString().replaceAll("\\\\", "\\\\\\\\"))
					.append("\"],\n");
		}
		String str = sb.substring(0, sb.lastIndexOf(","));
		// System.out.println(str);
		return str;
	}

	public static String SystemInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("系统属性:\n");
		sb.append(systemProperties());
		sb.append("===============================================\n");

		sb
				.append(
						"虚拟机可用处理器的数目 = "
								+ Runtime.getRuntime().availableProcessors())
				.append("\n");
		sb.append(
				"虚拟机空闲内存量 = " + toMb(Runtime.getRuntime().freeMemory()) + "Mb")
				.append("\n");
		sb.append(
				"虚拟机试图使用的最大内存量 = " + toMb(Runtime.getRuntime().maxMemory())
						+ "Mb").append("\n");
		sb.append(
				"虚拟机中的内存总量 = " + toMb(Runtime.getRuntime().totalMemory())
						+ "Mb").append("\n");

		sb.append("================================================\n");
		sb.append("环境变量:\n");
		for (Map.Entry entry : System.getenv().entrySet()) {
			sb.append(entry.getKey() + " = " + entry.getValue()).append("\n");
		}

		sb.append("================================================\n");
		sb.append("可用字符集：\n");
		sb.append(getCharsets());
		return sb.toString();
	}

	public static String getSystemInfo() {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();
		// 总的物理内存
		long totalMemorySize = osmxb.getTotalPhysicalMemorySize();
		// 剩余的物理内存
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
		// 已使用的物理内存
		long usedMemory = totalMemorySize - freePhysicalMemorySize;
		ThreadGroup parentThread;
		for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
				.getParent() != null; parentThread = parentThread.getParent())
			;

		int totalThread = parentThread.activeCount();

		return "virtual_cpu_num:" + Runtime.getRuntime().availableProcessors()
				+ "," + "virtual_mem_free:\""
				+ toMb(Runtime.getRuntime().freeMemory()) + "Mb\","
				+ "virtual_mem_max:\"" + toMb(Runtime.getRuntime().maxMemory())
				+ "Mb\"," + "virtual_mem_total:\""
				+ toMb(Runtime.getRuntime().totalMemory()) + "Mb\"," +

				"totalMemorySize:\"" + toMb(totalMemorySize) + "MB\","
				+ "freePhysicalMemorySize:\"" + toMb(freePhysicalMemorySize)
				+ "MB\"," + "usedMemory:\"" + toMb(usedMemory) + "MB\","
				+ "totalThread:" + totalThread;

	}

	private static double toMb(long mem) {
		return (double) (mem / 1024 / 1024);
	}

	public static <K, V> String getCharsets() {
		StringBuffer sb = new StringBuffer();
		SortedMap<String, Charset> sm = java.nio.charset.Charset
				.availableCharsets();
		Iterator<String> itr = sm.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			Set<String> value = sm.get(key).aliases();

			sb.append(key).append(" = ").append(value.toString()).append("\n");
		}
		return sb.toString();
	}

	public static <K, V> String systemProperties() {
		return systemProperties(false);
	}

	@SuppressWarnings("unchecked")
	public static <K, V> String systemProperties(boolean bArray) {
		StringBuffer sb = new StringBuffer();
		Properties p = System.getProperties();
		TreeMap<K, V> map = new TreeMap<K, V>();
		map.putAll((Map<? extends K, ? extends V>) p);
		Iterator itr = map.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			String value = (String) map.get(key);
			if (bArray) {
				if (key.equals("line.separator"))
					continue;
				sb.append("[\"").append(key).append("\",\"").append(
						value.replaceAll("\\\\", "\\\\\\\\")).append("\"],\n");
			} else {
				sb.append(key).append(" = ").append(value).append("\n");
			}
		}
		return sb.toString();
	}

	public static String systemProperties_() {
		StringBuffer sb = new StringBuffer();
		Properties ps = System.getProperties();
		Enumeration<?> pn = ps.propertyNames();

		while (pn.hasMoreElements()) {
			String pName = (String) pn.nextElement();
			String pValue = ps.getProperty(pName);
			sb.append(pName + " = " + pValue).append("\n");
		}
		return sb.toString();
	}

	private static double getCpuRateForLinux() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader brStat = null;
		StringTokenizer tokenStat = null;
		try {
			log.debug("Get usage rate of CUP , linux version: " + linuxVersion);

			Process process = Runtime.getRuntime().exec("top -b -n 1");
			is = process.getInputStream();
			isr = new InputStreamReader(is);
			brStat = new BufferedReader(isr);

			if (linuxVersion.equals("2.4")) {
				brStat.readLine();
				brStat.readLine();
				brStat.readLine();
				brStat.readLine();

				tokenStat = new StringTokenizer(brStat.readLine());
				tokenStat.nextToken();
				tokenStat.nextToken();
				String user = tokenStat.nextToken();
				tokenStat.nextToken();
				String system = tokenStat.nextToken();
				tokenStat.nextToken();
				String nice = tokenStat.nextToken();

				System.out.println(user + " , " + system + " , " + nice);

				user = user.substring(0, user.indexOf("%"));
				system = system.substring(0, system.indexOf("%"));
				nice = nice.substring(0, nice.indexOf("%"));

				float userUsage = new Float(user).floatValue();
				float systemUsage = new Float(system).floatValue();
				float niceUsage = new Float(nice).floatValue();

				return (userUsage + systemUsage + niceUsage) / 100;
			} else {
				brStat.readLine();
				brStat.readLine();

				tokenStat = new StringTokenizer(brStat.readLine());
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				String cpuUsage = tokenStat.nextToken();

				System.out.println("CPU idle : " + cpuUsage);
				Float usage = new Float(cpuUsage.substring(0, cpuUsage
						.indexOf("%")));

				return (1 - usage.floatValue() / 100);
			}

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			freeResource(is, isr, brStat);
			return 1;
		} finally {
			freeResource(is, isr, brStat);
		}

	}

	private static void freeResource(InputStream is, InputStreamReader isr,
			BufferedReader br) {
		try {
			if (is != null)
				is.close();
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	private static double getCpuRatioForWindows() {
		try {
			String procCmd = System.getenv("windir")
					+ "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"
					+ "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
			// 取进程信息
			long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
			Thread.sleep(CPUTIME);
			long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
			if (c0 != null && c1 != null) {
				long idletime = c1[0] - c0[0];
				long busytime = c1[1] - c0[1];
				return Double.valueOf(
						PERCENT * (busytime) / (busytime + idletime))
						.doubleValue();
			} else {
				return 0.0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0.0;
		}
	}

	private static long[] readCpu(final Process proc) {
		long[] retn = new long[2];
		try {
			proc.getOutputStream().close();
			InputStreamReader ir = new InputStreamReader(proc.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = input.readLine();
			if (line == null || line.length() < FAULTLENGTH) {
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while ((line = input.readLine()) != null) {
				if (line.length() < wocidx) {
					continue;
				}
				// 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
				// ThreadCount,UserModeTime,WriteOperation
				String caption = Bytes.substring(line, capidx, cmdidx - 1)
						.trim();
				String cmd = Bytes.substring(line, cmdidx, kmtidx - 1).trim();
				if (cmd.indexOf("wmic.exe") >= 0) {
					continue;
				}
				// log.info("line="+line);
				if (caption.equals("System Idle Process")
						|| caption.equals("System")) {
					idletime += Long.valueOf(
							Bytes.substring(line, kmtidx, rocidx - 1).trim())
							.longValue();
					idletime += Long.valueOf(
							Bytes.substring(line, umtidx, wocidx - 1).trim())
							.longValue();
					continue;
				}

				kneltime += Long.valueOf(
						Bytes.substring(line, kmtidx, rocidx - 1).trim())
						.longValue();
				usertime += Long.valueOf(
						Bytes.substring(line, umtidx, wocidx - 1).trim())
						.longValue();
			}
			retn[0] = idletime;
			retn[1] = kneltime + usertime;
			return retn;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				proc.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static final int CPUTIME = 30;

	private static final int PERCENT = 100;

	private static final int FAULTLENGTH = 10;

	@SuppressWarnings("unused")
	private static final File versionFile = new File("/proc/version");

	private static String linuxVersion = null;

	public static class Bytes {
		public static String substring(String src, int start_idx, int end_idx) {
			byte[] b = src.getBytes();
			String tgt = "";
			for (int i = start_idx; i <= end_idx; i++) {
				tgt += (char) b[i];
			}
			return tgt;
		}
	}
}