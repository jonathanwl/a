<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<!-- 
		<html>
			<head>
				<title>交易文件</title>
				<LINK REL="stylesheet" type="text/css" href="/monitor/css/jtpsoft.css"/>
			</head>
			<body>
				
			</body>
		</html>
		-->
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="transactions">
		<div style="text-align:right"><input class="button" type="button" name="refresh" value="刷 新" onclick="(new Sysconfig.transaction()).updateTrans();"/></div>
		<h2>
			<xsl:value-of select="clientname"/>
		</h2>		
		<table>
			<xsl:for-each select="transaction">
				<tr>
					<td>
						<table>
							<tbody>
								<tr>
									<td>
										<table style="color:red;">
											<tr>
												<td>
													<xsl:variable name="id"><xsl:value-of select="id"/></xsl:variable>
													<a name="{$id}"/>
													<b><xsl:number value="position()"/>.</b>
												</td>
												<td>
													<xsl:value-of select="id"/>
												</td>
												<td>
													<xsl:value-of select="name"/>
												</td>
												<td>
													<xsl:value-of select="describe"/>
												</td>
												<td>
													<xsl:if test="bank">
														（银行编码：<xsl:value-of select="bank"/>）
													</xsl:if>
												</td>
												<td style="color:green;">
													<xsl:if test="work-time">														
														工作时段：<xsl:value-of select="work-time"/>
													</xsl:if>													
												</td>
												<td style="color:green;">
													<xsl:if test="task-timer">														
														定时任务：<xsl:value-of select="task-timer"/>
													</xsl:if>													
												</td>												
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table>
											<tr valign="top">
												<td>|___</td>
												<td>
													<xsl:apply-templates select="file-fields"/>
												</td>
												<td>
													<xsl:apply-templates select="procedure"/>
												</td>
												<td style="color:green;">
													<xsl:if test="refid">
														<xsl:variable name="refid"><xsl:value-of select="refid"/></xsl:variable>
														参考<a href="#{$refid}"><xsl:value-of select="refid"/></a>
													</xsl:if>													
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>
	<xsl:template match="file-fields">
		<table border="1" width="400" class="ThinLineTable">
			<thead>
				<b>数据文件格式</b>
			</thead>
			<tr>
				<th>SN</th>
				<th>字段名</th>
				<th>字段类型</th>
				<th>字段宽度</th>
				<th>参数名</th>
			</tr>
			<xsl:for-each select="field">
				<tr>
					<td><xsl:number value="position()"/></td>
					<td>
						<xsl:value-of select="name"/>
					</td>
					<td>
						<xsl:value-of select="type"/>
					</td>
					<td>
						<xsl:value-of select="width"/>
					</td>
					<td>
						<xsl:value-of select="param-name"/>
					</td>
				</tr>
			</xsl:for-each>
				<tr>
					<td colspan="2">合计</td><td></td><td><b><xsl:value-of select="sum(field/width)"/></b></td><td></td>
				</tr>
		</table>
	</xsl:template>
	<xsl:template match="procedure">
		<table border="1" width="500" class="ThinLineTable">
			<thead>
				<b>数据库调用</b>
			</thead>
			<tr>
				<td colspan="5">
					<xsl:value-of select="name"/>
				</td>
			</tr>
			<tr>				
				<td colspan="5">
					<xsl:if test="name-ext">临时表：</xsl:if>
					<xsl:value-of select="name-ext"/>
				</td>
			</tr>
			<xsl:apply-templates select="sql-parameters"/>
			<xsl:apply-templates select="sql-rets"/>
		</table>
	</xsl:template>
	<xsl:template match="sql-parameters">
		<tr>
			<th>SN</th>
			<th>参数名</th>
			<th>参数类型</th>
			<th>数据类型</th>
			<th>数据初始值</th>
		</tr>
		<xsl:for-each select="param">
			<tr>
				<td><xsl:number value="position()"/></td>
				<td>
					<xsl:value-of select="param-name"/>
				</td>
				<td>
					<xsl:value-of select="param-type"/>
				</td>
				<td>
					<xsl:value-of select="data-type"/>
				</td>
				<td>
					<xsl:value-of select="data-value"/>
				</td>
			</tr>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="sql-rets">
		<tr>
			<td colspan="5">
				<table border="1" class="ThinLineTable" width="100%">
					<tr>
						<th>返回值</th>
						<th>返回信息</th>
					</tr>
					<xsl:for-each select="ret">
						<tr>
							<td>
								<xsl:value-of select="value"/>
							</td>
							<td>
								<xsl:value-of select="msg"/>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
