set path = "C:\Users\Lolas\Downloads\JCreatorV5LE_portable\JCreatorV5LE_portable\jdk\jdk1.8.0_25\bin";

java CopyFile test.java testCopy
javac Prints.java
java prints testCopy >> printFile.java
pause
javac printFile.java
java printFile