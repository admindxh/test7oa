<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.lang.management.MemoryMXBean"%>
<%@page import="java.lang.management.MemoryUsage"%>
<%@page import="java.lang.management.RuntimeMXBean"%>
<%@page import="java.lang.management.ThreadMXBean"%>
<%@page import="sun.management.ManagementFactory"%>
<%@page import="com.sun.management.OperatingSystemMXBean"%>
<%--
	模块：系统管理--系统信息
--%>

<div style="width: 98%;margin: 0 auto;">					

						
	
			
			<%
			
			   out.append("=======================通过java来获取相关系统状态============================ ");
			   int i = (int)Runtime.getRuntime().totalMemory()/1024;//Java 虚拟机中的内存总量,以字节为单位
			   out.append("<br/>总的内存量     "+i);
			   int j = (int)Runtime.getRuntime().freeMemory()/1024;//Java 虚拟机中的空闲内存量
			   out.append("<br/>空闲内存量      "+j);
			   out.append("<br/>最大内存量      "+Runtime.getRuntime().maxMemory()/1024);

			   out.append("=======================OperatingSystemMXBean============================ ");
			   OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
			   out.append("<br/>"+osm.getFreeSwapSpaceSize()/1024);
			   out.append("<br/>"+osm.getFreePhysicalMemorySize()/1024);
			   out.append("<br/>"+osm.getTotalPhysicalMemorySize()/1024);
			   
			   //获取操作系统相关信息
			   out.append("<br/>操作系统多少位	 "+osm.getArch());
			   out.append("<br/>可用处理器数量 "+osm.getAvailableProcessors());
			   out.append("<br/>获取可用内存大小 "+osm.getCommittedVirtualMemorySize());
			   out.append("<br/>操作系统名称 "+osm.getName());
			   out.append("<br/>osm.getProcessCpuTime() "+osm.getProcessCpuTime());
			   out.append("<br/>osm.getVersion() "+osm.getVersion());
			   //获取整个虚拟机内存使用情况
			   out.append("=======================MemoryMXBean============================ ");
			   MemoryMXBean mm=(MemoryMXBean)ManagementFactory.getMemoryMXBean();
			   out.append("<br/>getHeapMemoryUsage "+mm.getHeapMemoryUsage());
			   out.append("<br/>虚拟机启动时从系统得到的内存（以字节为单位）  "+mm.getNonHeapMemoryUsage());
			   //返回 Java 虚拟机使用的非堆内存的当前使用量
			   MemoryUsage aMemoryUsage=mm.getNonHeapMemoryUsage();      //虚拟机启动时从系统得到的内存（以字节为单位）
			   out.append("<br/>虚拟机启动时从系统得到的内存:"+aMemoryUsage.getInit()/1024/1024);   //表示当前已经使用的内存量（以字节为单位）
			   out.append("<br/>当前已经使用的内存量:"+aMemoryUsage.getUsed()/1024/1024);   //表示保证可以由 Java 虚拟机使用的内存量（以字节为单位）
			   out.append("<br/>保证可以由 Java 虚拟机使用的内存量:"+aMemoryUsage.getCommitted()/1024/1024);   //表示可以用于内存管理的最大内存量（以字节为单位）
			   out.append("<br/>内存管理的最大内存量:"+aMemoryUsage.getMax()/1024/1024);
			   out.append("<br/>总体描述:"+aMemoryUsage.toString());
			   
			   //获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
			   out.append("=======================ThreadMXBean============================ ");
			   ThreadMXBean tm=(ThreadMXBean)ManagementFactory.getThreadMXBean();
			   out.append("<br/>getThreadCount "+tm.getThreadCount());
			   out.append("<br/>getPeakThreadCount "+tm.getPeakThreadCount());
			   out.append("<br/>getCurrentThreadCpuTime "+tm.getCurrentThreadCpuTime());
			   out.append("<br/>getDaemonThreadCount "+tm.getDaemonThreadCount());
			   out.append("<br/>getCurrentThreadUserTime "+tm.getCurrentThreadUserTime());
			   
			 
			   //获取运行时信息
			   out.append("=======================RuntimeMXBean============================ ");
			   RuntimeMXBean rmb=(RuntimeMXBean)ManagementFactory.getRuntimeMXBean();
			   out.append("<br/>getClassPath "+rmb.getClassPath());
			   out.append("<br/>getLibraryPath "+rmb.getLibraryPath());
			   out.append("<br/>getVmVersion "+rmb.getVmVersion());
			   
			   //java 信息
			   out.append("<br/>"+System.getProperty("java.version"));  
			   
			   
				//free和use和total均为KB
						long free=0;
						long use=0;
						long total=0;
						int kb=1024;
						Runtime rt=Runtime.getRuntime();
						total=rt.totalMemory();
						free=rt.freeMemory();
						use=total-free;
						out.append("<br/>系统内存已用的空间为："+use/kb+" MB");
						out.append("<br/>系统内存的空闲空间为："+free/kb+" MB");
						out.append("<br/>系统总内存空间为："+total/kb+" MB");
						OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
						long physicalFree=osmxb.getFreePhysicalMemorySize()/kb;
						long physicalTotal=osmxb.getTotalPhysicalMemorySize()/kb;
						long physicalUse=physicalTotal-physicalFree;
						String os=System.getProperty("os.name");
						out.append("<br/>操作系统的版本："+os);
						out.append("<br/>系统物理内存已用的空间为："+physicalFree+" MB");
						out.append("<br/>系统物理内存的空闲空间为："+physicalUse+" MB");
						out.append("<br/>总物理内存："+physicalTotal+" MB");
				        // 获得线程总数
				        ThreadGroup parentThread;
				        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
				                .getParent() != null; parentThread = parentThread.getParent())
				            ;
				        int totalThread = parentThread.activeCount();
				        out.append("<br/>获得线程总数:"+totalThread);
			
			%>
			
			
			
			
	
	
	

</div>
										
						
					
		

	

