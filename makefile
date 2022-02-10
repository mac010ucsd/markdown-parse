MarkdownParse.class : MarkdownParse.java
	javac MarkdownParse.java
MarkdownParseTest.class : MarkdownParseTest.java MarkdownParse.class
	javac -cp .:lib/junit-4.13.2.jar:hamcrest-core-1.3.jar MarkdownParseTest.java

runall : MarkdownParseTest.class MarkdownParse.class
	java -cp .:lib/junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest