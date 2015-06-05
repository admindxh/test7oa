
	            ╭───────────────────────╮
	 ────┤          	项目开发说明             		  	  ├────
	            ╰───────────────────────╯ 
		　                                                             
	   		───────────────────────────────── 
	───────
名称：企业智能网络办公系统（简称OA）	
架构：服务器：Spring MVC + Spring +Hibernate
	  前端：Easy UI 框架
	  数据库：Mysql

命名规范：
	    控制层： *Action
	    接口：I*;	
	    接口实现类:*Impl
	    业务层接口：I*Service
	    业务层实现类：*ServiceImpl

数据库：
	1.id采用UUID，32位字符串（char）
		
页面：
	1.字符串输出使用jstl，out，输出，转义输出。如果是在线编辑器输入的内容，
	显示页面用<my:scriptEscape value="" />输出
	
	  
目录结构：
src-
	config--基本的配置文件
	com.jxoa-commons-公共，基础的
			         base：父类 基类
			         config：基本配置
			         converter:Spring MVC 数据格式化
			         exception:异常处理
			         filter:过滤器
			         interceptor：拦截器
			         literceptor：监听器
			         model:模型对象
			         tag:自定义JSP标签
			         util：工具包
	com.jxoa.manager-详细功能模块实现 每个模块都包含 action service bean 
			        system:系统设置模块 ； 页面对应jsp/system 	页面文件名与包模块名一直
			        	  action:	LoginAction	登录模块
			        	  			UserAction	用户管理
			        	  			RoleAction	角色管理
			        	  			DeptAction	部门管理
			        	  			MenuAction	菜单管理
			        	  			ListValuesAction	字典值管理
			        	  			SystemLogAction		日志管理	
			        files:文件多管理模块   页面对应 jsp/files   
			        	  action:	PersonalFilesAction	个人文件
			        	  			CompanyFilesAction	公司文件
			        	  			ShareFilesAction	共享文件
			        coordination:协同办公模块  页面对应 coordination
			        	  action:	NoticeAction 通知管理
			        
			        common_platform:公共平台		页面：jsp/common_platform
			        
			        	  action:	MeetingAction：会议管理
			        	  			RoomAction：	   会议室设置
			        	  			
			        personalOffice:个人办公		页面;jsp/personalOffice
			        
			        
			        
			        administration:行政办公
			        
			        personnel_matters:人事
			        
			        
webRoot-
	errors:错误页面
	resourec:css，js，img等资源
	upfiles:附件上传保存目录：
			--personalFiles：个人文档
			--companyFiles:公司文件
	WEB-INF:
		--jsp:项目页面主要页面
			 --system：系统管理
			 		--organize:组织机构
			 			--dept:部门管理
			 			--role:角色管理
			 			--user:用户管理
					--menu:菜单管理
						--action:菜单对应的操作管理
					--log:日志管理
					--list_values:字典值管理	
					
			 --sms: 短信模块
			       
	

