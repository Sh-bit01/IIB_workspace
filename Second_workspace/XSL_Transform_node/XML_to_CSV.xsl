<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text" encoding="UTF-8" omit-xml-declaration="yes"/>
  
  
  <!-- Match the root element "people" -->
  <xsl:template match="/people">
    <!-- Output the CSV header -->
    <!--  <xsl:text>ID,name,age&#10;</xsl:text>   -->

    <!-- Loop through each "person" element -->
    <xsl:for-each select="person">
      <!-- Output the CSV row for each person -->
      <xsl:value-of select="ID"/>
      <xsl:text>,</xsl:text>
      <xsl:value-of select="name"/>
      <xsl:text>,</xsl:text>
      <xsl:value-of select="age"/>
      <xsl:text>&#10;</xsl:text> <!-- New line after each row -->
    </xsl:for-each>
  </xsl:template>

</xsl:stylesheet>

