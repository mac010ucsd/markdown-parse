javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar $1.java $2.java
java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore $2