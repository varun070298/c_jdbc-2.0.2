<?xml version="1.0"?>
<!--
 Copyright (C) MX4J.
 All rights reserved.

 This software is distributed under the terms of the MX4J License version 1.0.
 See the terms of the MX4J License in the documentation provided with this software.

 Author: Carlos Quiroz (tibu@users.sourceforge.net)
 Revision: $Revision: 1.4 $
																																					-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8"/>

	<xsl:param name="html.stylesheet">stylesheet.css</xsl:param>
	<xsl:param name="html.stylesheet.type">text/css</xsl:param>
	<xsl:param name="head.title">viewmap.title</xsl:param>
	<xsl:param name="request.step">30</xsl:param>
	<xsl:param name="request.start">0</xsl:param>
	<xsl:param name="request.objectname"/>
	<xsl:param name="request.attribute"/>
	<xsl:param name="request.format"/>
	<xsl:param name="request.template"/>
	<xsl:include href="common.xsl"/>

	<!-- Main template -->
	<xsl:template match="/" name="main">
		<html>
			<xsl:call-template name="head"/>
			<body>
				<xsl:call-template name="toprow"/>
				<xsl:call-template name="tabs">
					<xsl:with-param name="selection">mbean</xsl:with-param>
				</xsl:call-template>
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td colspan="4" class="page_title">
							<xsl:call-template name="str">
								<xsl:with-param name="id">viewmap.main.title</xsl:with-param>
								<xsl:with-param name="p0"><xsl:value-of select="MBean/@objectname"/></xsl:with-param>
							</xsl:call-template>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="fronttab">
							<xsl:call-template name="str">
								<xsl:with-param name="id">viewmap.main.viewmaptitle</xsl:with-param>
								<xsl:with-param name="p0"><xsl:value-of select="MBean/Attribute/@classname"/></xsl:with-param>
								<xsl:with-param name="p1"><xsl:value-of select="MBean/Attribute/@name"/></xsl:with-param>
							</xsl:call-template>
						</td>
					</tr>
					<tr>
						<td class="fronttab">
							<div class="tableheader">
								<xsl:call-template name="str">
									<xsl:with-param name="id">viewmap.main.key</xsl:with-param>
								</xsl:call-template>
							</div>
						</td>
						<td class="fronttab">
							<div class="tableheader">
								<xsl:call-template name="str">
									<xsl:with-param name="id">viewmap.main.keyclass</xsl:with-param>
								</xsl:call-template>
							</div>
						</td>
						<td class="fronttab">
							<div class="tableheader">
								<xsl:call-template name="str">
									<xsl:with-param name="id">viewmap.main.value</xsl:with-param>
								</xsl:call-template>
							</div>
						</td>
						<td class="fronttab">
							<div class="tableheader">
								<xsl:call-template name="str">
									<xsl:with-param name="id">viewmap.main.valueclass</xsl:with-param>
								</xsl:call-template>
							</div>
						</td>
					</tr>
					<xsl:for-each select="MBean/Attribute/Map/Element">
						<xsl:sort order="ascending" select="@index"/>
						<xsl:if test="position()&lt;($request.step+$request.start) and position()&gt;$request.start">
							<xsl:variable name="classtype">
								<xsl:if test="(position() mod 2)=1">clearline</xsl:if>
								<xsl:if test="(position() mod 2)=0">darkline</xsl:if>
							</xsl:variable>
							<tr class="{$classtype}">
								<td class="aggregationrow"><xsl:value-of select="@key"/></td>
								<td class="aggregationrow"><xsl:value-of select="@keyclass"/></td>
								<td class="aggregationrow"><xsl:value-of select="@element"/></td>
								<td class="aggregationrow"><xsl:value-of select="@elementclass"/></td>
							</tr>
						</xsl:if>
					</xsl:for-each>

					<xsl:variable name="url">getattribute?objectname=<xsl:call-template name="uri-encode"><xsl:with-param name="uri"><xsl:value-of select="$request.objectname"/></xsl:with-param></xsl:call-template>&amp;attribute=<xsl:value-of select="$request.attribute"/>&amp;format=map&amp;template=viewmap&amp;locale=<xsl:value-of select="$request.locale"/></xsl:variable>
					<xsl:call-template name="aggregation-navigation">
						<xsl:with-param name="url" select="$url"/>
						<xsl:with-param name="total" select="count(MBean/Attribute/Map/Element)"/>
						<xsl:with-param name="start" select="$request.start"/>
						<xsl:with-param name="step" select="$request.step"/>
						<xsl:with-param name="str.prefix">viewmap.main</xsl:with-param>
					</xsl:call-template>

					<xsl:call-template name="mbeanview">
						<xsl:with-param name="objectname" select="MBean/@objectname"/>
						<xsl:with-param name="colspan" select="4"/>
					</xsl:call-template>
				</table>

				<xsl:call-template name="bottom"/>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

