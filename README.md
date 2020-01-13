1. go to project directory
1. mvn clean install
1. java -jar target\benchmarks.jar 
- parameters:
  - -f fork
  - -w warmup
  - -i iteration
  - -r runtime
- example:
  - -java -jar target\benchmarks.jar -f 1 -w 1 -i 5 -r 1

presentation: https://drive.google.com/file/d/1Fp8tDrN56HQP_7uxD4RSiaa-xoJR3Wpi/view?usp=sharing 
