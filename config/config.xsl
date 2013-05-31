<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml"/>
	<xsl:template match="/">	
		
		<xsl:apply-templates select="param"/>
		
	</xsl:template>
	
	<xsl:template match="param">		
		<properties>
			<!--
			<property>
				<name>配置</name>
				<value>
					<xsl:value-of select="config"/>
				</value>
			</property>
			-->	
			<xsl:apply-templates select="datasource"/>
			<xsl:apply-templates select="global"/>
			
		</properties>
	</xsl:template>
	<xsl:template match="datasource">
		<property>
			<name>&lt;font color="red"&gt;数据源&lt;/font&gt;</name>
			<value></value>			
		</property>
		<property>
			<name>连接类型</name>
			<value><xsl:value-of select="conntype"/></value>			
		</property>
		<property>
			<name>数据库类型</name>
			<value><xsl:value-of select="dbtype"/></value>			
		</property>
		<property>
			<name>数据库驱动</name>
			<value><xsl:value-of select="jdbc-driver"/></value>			
		</property>
		<property>
			<name>数据库地址</name>
			<value><xsl:value-of select="jdbc-url"/></value>			
		</property>
		<property>
			<name>数据库用户</name>
			<value>********</value>			
		</property>
		<property>
			<name>数据库密码</name>
			<value>********</value>			
		</property>
		<property>
			<name>&lt;font color="red"&gt;连接池配置&lt;/font&gt;</name>
			<value></value>			
		</property>
		<property>
			<name>初始化连接</name>
			<value><xsl:value-of select="datasource-initialsize"/></value>			
		</property>
		<property>
			<name>最大连接数量</name>
			<value><xsl:value-of select="datasource-maxactive"/></value>			
		</property>
		<property>
			<name>最大空闲连接</name>
			<value><xsl:value-of select="datasource-maxidle"/></value>			
		</property>
		<property>
			<name>最小空闲连接</name>
			<value><xsl:value-of select="datasource-minidle"/></value>			
		</property>
		<property>
			<name>超时等待时间</name>
			<value><xsl:value-of select="datasource-maxwait"/></value>			
		</property>
		<property>
			<name>是否进行无效的连接的回收</name>
			<value><xsl:value-of select="datasource-removeabandoned"/></value>			
		</property>
		<property>
			<name>回收无效连接超时</name>
			<value><xsl:value-of select="datasource-removeabandonedtimeout"/></value>			
		</property>
		<property>
			<name>是否在自动回收超时连接的时候打印连接的超时错误</name>
			<value><xsl:value-of select="datasource-logabandoned"/></value>			
		</property>
		<property>
			<name>取得连接时是否进行有效性验证</name>
			<value><xsl:value-of select="datasource-testonborrow"/></value>			
		</property>
		<property>
			<name>&lt;font color="red"&gt;weblogic连接池&lt;/font&gt;</name>
			<value></value>
		</property>
		<property>
			<name>weblogic-jndiname</name>
			<value>
				<xsl:value-of select="weblogic-jndiname"/>
			</value>
		</property>
		<property>
			<name>weblogic-user</name>
			<value>
				<xsl:value-of select="weblogic-user"/>
			</value>
		</property>
		<property>
			<name>weblogic-password</name>
			<value>********</value>
		</property>
		<property>
			<name>weblogic-url</name>
			<value>
				<xsl:value-of select="weblogic-url"/>
			</value>
		</property>
		<property>
			<name>weblogic-factory</name>
			<value>
				<xsl:value-of select="weblogic-factory"/>
			</value>
		</property>
	</xsl:template>
	<xsl:template match="global">	
		<property>
			<name>&lt;font color="red"&gt;全局参数&lt;/font&gt;</name>
			<value></value>
		</property>
		<property>
			<name>交易文件</name>
			<value>
				<xsl:value-of select="trans-file"/>
			</value>
		</property>
		<property>
			<name>中心服务器地址</name>
			<value>
				<xsl:value-of select="fund-server"/>
			</value>
		</property>
		<property>
			<name>中心服务器端口</name>
			<value>
				<xsl:value-of select="fund-port"/>
			</value>
		</property>
		<property>
			<name>银行服务器地址</name>
			<value>
				<xsl:value-of select="bank-server"/>
			</value>
		</property>
		<property>
			<name>银行服务器端口</name>
			<value>
				<xsl:value-of select="bank-port"/>
			</value>
		</property>
		<property>
			<name>单个CPU线程池</name>
			<value>
				<xsl:value-of select="pool-size"/>
			</value>
		</property>
		<property>
			<name>银行客户端线程数</name>
			<value>
				<xsl:value-of select="bank-client-thread-num"/>
			</value>
		</property>
		<property>
			<name>银行客户端线程模拟交易笔数</name>
			<value>
				<xsl:value-of select="bank-client-trans-num"/>
			</value>
		</property>
		<property>
			<name>银行客户端模拟交易最大时间间隔</name>
			<value>
				<xsl:value-of select="bank-client-trans-max-interval"/>
			</value>
		</property>
		<property>
			<name>中心客户端线程数</name>
			<value>
				<xsl:value-of select="fund-client-thread-num"/>
			</value>
		</property>
		<property>
			<name>中心客户端线程模拟交易笔数</name>
			<value>
				<xsl:value-of select="fund-client-trans-num"/>
			</value>
		</property>
		<property>
			<name>中心客户端线程模拟交易最大时间间隔</name>
			<value>
				<xsl:value-of select="fund-client-trans-max-interval"/>
			</value>
		</property>
		<property>
			<name>socket超时秒数</name>
			<value>
				<xsl:value-of select="conn-timeout"/>
			</value>
		</property>
		<property>
			<name>休眠毫秒数</name>
			<value>
				<xsl:value-of select="sleep-time"/>
			</value>
		</property>
		<property>
			<name>是否启用数据库监控</name>
			<value>
				<xsl:value-of select="db-monitor"/>
			</value>
		</property>
	</xsl:template>	
</xsl:stylesheet>
