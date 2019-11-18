1. go to project directory
1. mvn clean install
1. java -jar target\benchmarks.jar 
parameters:
-f fork
-w warmup
-i iteration
-r runtime
example:
java -jar target\benchmarks.jar -f 1 -w 1 -i 5 -r 1
