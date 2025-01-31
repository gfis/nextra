1	#!java -cp dist/nextra.jar org.teherba.common.RegressionTester
2	
3	# Collection of testcases for nextra
4	# @(#) $Id: all.tca 955 yyyy-mm-dd hh:mm:ssZ gfis $
5	# 2024-11-25, Georg Fischer: copied from jextra
6	#-----------------------------------
7	## Caution:
8	# (1) Adjust the following line: URL where jextra was deployed
9	URL=http://localhost:8080/nextra/servlet
10	#================
11	PACKAGE=org.teherba.nextra.scan
12	
13	ECHO 000
14	
15	TEST LIN1 LineReader
16	CALL LineReader test/all.tests
