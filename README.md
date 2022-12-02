This project parses Java files to extract method names

**Prerequisites:**
* Java >= 8
* Gradle = 7.4

**How to run**
* For convenience, a built jar is provided in *build/libs* directory
* To build, run *./gradlew build* command
* To run the jar file, run *java -jar build/libs/java-parser-1.0.jar \<filePath1> \<filePath2> ... filePaths*
  can be either absolute or relative.
* Output will be generated in *output.txt* file