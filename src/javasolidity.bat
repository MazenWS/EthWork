@REM replace path value with the path of bin inside the jdk version dowloaded on your computer
set path = "jdk\jdk1.8.0_25\bin";

Copy test.java testCopy
javac Prints.java
java prints testCopy >> NewTest.java
pause
javac NewTest.java
java NewTest