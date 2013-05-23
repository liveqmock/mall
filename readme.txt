Hibernate与Spring集成的配置

关于数据库连接的配置
1、直接使用jdbc数据库连接
	配置hibernate.cfg.xml
		<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
	    <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
	    <property name="connection.url">jdbc:mysql://127.0.0.1:3306/59124</property>
	    <property name="connection.username">root</property>
	    <property name="connection.password">hnfealean</property>
	    <property name="hibernate.show_sql">true</property>
	    <property name="hibernate.hbm2ddl.auto">update</property>
2、配置数据源
	hibernate.cfg.xml中去掉数据库连接信息
	在Spring的配置文件中配置dataSource Bean，使用
		<context:property-placeholder location="classpath:jdbc.properties"/>来确定properties文件。也可直接写在bean中，取代${..}
		<bean id="dataSource" class="${dataSource}" destroy-method="close">
			<property name="driverClassName" value="${driverClassName}" />
			<property name="url" value="${url}" />
			<property name="username" value="${username}" />
			<property name="password" value="${password}" />
			<!-- 连接池启动时的初始值 -->
			<property name="initialSize" value="${initialSize}" />
			<!-- 连接池的最大值 -->
			<property name="maxActive" value="${maxActive}" />
			<!-- 最大空闲值，当经过一个高峰时间后，连接池 可以慢慢将已经用不到的连接慢慢释放一部分，一直减少到maxIdle为止-->
			<property name="maxIdle" value="${maxIdle}" />
			<!-- 最小空闲值。当空闲的连接数少于阈值时，连接池就会预申请一些连接，以免洪峰来临时来不及申请 -->
			<property name="minIdle" value="${minIdle}" />
		</bean>
	然后将数据源纳入Spring管理
	在sessionFactory Bean中以<property name="dataSource" ref="dataSource">引入数据源