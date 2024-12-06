<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/books">
   <html lang="en">
   <head>
   
    <title>Book List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        h2 {
            font-size: 24px;
            color: #444;
            text-align: center;
            margin-bottom: 20px;
        }
        ul {
            list-style-type: none;
            padding: 0;
            max-width: 600px;
            margin: 0 auto;
        }
        li {
            background-color: #fff;
            margin: 10px 0;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        li strong {
            font-weight: bold;
            color: #2c3e50;
        }
    </style>
</head>
      <body>
        <h2>Book List</h2>
        <ul>
          <xsl:for-each select="book">
            <li>
              <strong><xsl:value-of select="title" /></strong> by <xsl:value-of select="author" />
            </li>
          </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
