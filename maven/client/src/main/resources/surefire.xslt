<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:output method="xml" encoding="UTF-8" indent="yes" />

	<xsl:template match="/">
		<testsuite failures="{report/tests/@failed}" time="{report/tests/@time}"
			errors="0" skipped="0" tests="{report/tests/@failed + report/tests/@passed}"
			name="{report/tests/project/@name}">
			<xsl:for-each select="//scenario">
				<testcase time="{../@time}" classname="{//report/tests/project/@name}" name="{@name}">
					<xsl:if test="@status = 'failed'">
						<failure message="Q7 test failed" type="Q7Failure">
							<xsl:value-of select="." />
						</failure>
					</xsl:if>
				</testcase>
			</xsl:for-each>
		</testsuite>
	</xsl:template>

</xsl:stylesheet>