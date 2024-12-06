<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:web="http://www.example.com/webservice"
                version="1.0">

  <!-- Match the root element "books" -->
  <xsl:template match="/books">
    <!-- SOAP Envelope -->
    <soapenv:Envelope>
      <soapenv:Header/>
      <soapenv:Body>
        <!-- BookList wrapper -->
        <web:BookList>
          <!-- Loop through each book -->
          <xsl:for-each select="book">
            <web:book>
              <web:title><xsl:value-of select="title"/></web:title>
              <web:author><xsl:value-of select="author"/></web:author>
            </web:book>
          </xsl:for-each>
        </web:BookList>
      </soapenv:Body>
    </soapenv:Envelope>
  </xsl:template>

</xsl:stylesheet>
