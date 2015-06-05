/**  
 * @Project: jxoa
 * @Title: SystemInfo.java
 * @Package com.oa.commons.util
 * @date 2013-4-18 下午2:11:57
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

/**
 * 
 * 类名：SystemInfo
 * 功能：服务器信息
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-18 下午2:11:57
 *
 */
public class SystemInfo {
	/**
	* @param 直接通过jdk来获取系统相关状态，
	*/
	public static void info() {
	  
	   System.out.println("=======================通过java来获取相关系统状态============================ ");
	   int i = (int)Runtime.getRuntime().totalMemory()/1024;//Java 虚拟机中的内存总量,以字节为单位
	   System.out.println("总的内存量     "+i);
	   int j = (int)Runtime.getRuntime().freeMemory()/1024;//Java 虚拟机中的空闲内存量
	   System.out.println("空闲内存量      "+j);
	   System.out.println("最大内存量      "+Runtime.getRuntime().maxMemory()/1024);

	   System.out.println("=======================OperatingSystemMXBean============================ ");
	   OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	   System.out.println(osm.getFreeSwapSpaceSize()/1024);
	   System.out.println(osm.getFreePhysicalMemorySize()/1024);
	   System.out.println(osm.getTotalPhysicalMemorySize()/1024);
	   
	   //获取操作系统相关信息
	   System.out.println("操作系统多少位	 "+osm.getArch());
	   System.out.println("可用处理器数量 "+osm.getAvailableProcessors());
	   System.out.println("获取可用内存大小 "+osm.getCommittedVirtualMemorySize());
	   System.out.println("操作系统名称 "+osm.getName());
	   System.out.println("osm.getProcessCpuTime() "+osm.getProcessCpuTime());
	   System.out.println("osm.getVersion() "+osm.getVersion());
	   //获取整个虚拟机内存使用情况
	   System.out.println("=======================MemoryMXBean============================ ");
	   MemoryMXBean mm=ManagementFactory.getMemoryMXBean();
	   System.out.println("getHeapMemoryUsage "+mm.getHeapMemoryUsage());
	   System.out.println("虚拟机启动时从系统得到的内存（以字节为单位）  "+mm.getNonHeapMemoryUsage());
	   //返回 Java 虚拟机使用的非堆内存的当前使用量
	   MemoryUsage aMemoryUsage=mm.getNonHeapMemoryUsage();      //虚拟机启动时从系统得到的内存（以字节为单位）
	   System.out.println("虚拟机启动时从系统得到的内存:"+aMemoryUsage.getInit()/1024/1024);   //表示当前已经使用的内存量（以字节为单位）
	   System.out.println("当前已经使用的内存量:"+aMemoryUsage.getUsed()/1024/1024);   //表示保证可以由 Java 虚拟机使用的内存量（以字节为单位）
	   System.out.println("保证可以由 Java 虚拟机使用的内存量:"+aMemoryUsage.getCommitted()/1024/1024);   //表示可以用于内存管理的最大内存量（以字节为单位）
	   System.out.println("内存管理的最大内存量:"+aMemoryUsage.getMax()/1024/1024);
	   System.out.println("总体描述:"+aMemoryUsage.toString());
	   
	   //获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
	   System.out.println("=======================ThreadMXBean============================ ");
	   ThreadMXBean tm=ManagementFactory.getThreadMXBean();
	   System.out.println("getThreadCount "+tm.getThreadCount());
	   System.out.println("getPeakThreadCount "+tm.getPeakThreadCount());
	   System.out.println("getCurrentThreadCpuTime "+tm.getCurrentThreadCpuTime());
	   System.out.println("getDaemonThreadCount "+tm.getDaemonThreadCount());
	   System.out.println("getCurrentThreadUserTime "+tm.getCurrentThreadUserTime());
	   
	 
	   //获取运行时信息
	   System.out.println("=======================RuntimeMXBean============================ ");
	   RuntimeMXBean rmb=ManagementFactory.getRuntimeMXBean();
	   System.out.println("getClassPath "+rmb.getClassPath());
	   System.out.println("getLibraryPath "+rmb.getLibraryPath());
	   System.out.println("getVmVersion "+rmb.getVmVersion());
	   
	   //java 信息
	   System.out.println(System.getProperty("java.version"));  
	   
	   
		//free和use和total均为KB
				long free=0;
				long use=0;
				long total=0;
				int kb=1024;
				Runtime rt=Runtime.getRuntime();
				total=rt.totalMemory();
				free=rt.freeMemory();
				use=total-free;
				System.out.println("系统内存已用的空间为："+use/kb+" MB");
				System.out.println("系统内存的空闲空间为："+free/kb+" MB");
				System.out.println("系统总内存空间为："+total/kb+" MB");
				OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
				long physicalFree=osmxb.getFreePhysicalMemorySize()/kb;
				long physicalTotal=osmxb.getTotalPhysicalMemorySize()/kb;
				long physicalUse=physicalTotal-physicalFree;
				String os=System.getProperty("os.name");
				System.out.println("操作系统的版本："+os);
				System.out.println("系统物理内存已用的空间为："+physicalFree+" MB");
				System.out.println("系统物理内存的空闲空间为："+physicalUse+" MB");
				System.out.println("总物理内存："+physicalTotal+" MB");
		        // 获得线程总数
		        ThreadGroup parentThread;
		        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
		                .getParent() != null; parentThread = parentThread.getParent())
		            ;
		        int totalThread = parentThread.activeCount();
		        System.out.println("获得线程总数:"+totalThread);
	}
	
}
