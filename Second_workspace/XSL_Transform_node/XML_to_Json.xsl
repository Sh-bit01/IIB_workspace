
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="text" encoding="UTF-8" omit-xml-declaration="yes"/>
    
  
  <!-- Match the root element "books" -->
  <xsl:template match="/books">
    
  
    <!-- Begin JSON array -->
    <xsl:text>[</xsl:text>
    <xsl:for-each select="book">
      <!-- Begin each JSON object -->
      <xsl:text>{</xsl:text>
      <xsl:text>"title": "</xsl:text><xsl:value-of select="title"/><xsl:text>", </xsl:text>
      <xsl:text>"author": "</xsl:text><xsl:value-of select="author"/><xsl:text>"</xsl:text>
      <!-- End each JSON object -->
      <xsl:text>}</xsl:text>
      
      <!-- Add comma if not the last element -->
      <xsl:if test="position() != last()">
        <xsl:text>, </xsl:text>
      </xsl:if>
    </xsl:for-each>
    <!-- End JSON array -->
    <xsl:text>]</xsl:text>
  </xsl:template>

</xsl:stylesheet>
