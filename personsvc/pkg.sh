#mvn package -Dmaven.test.skip=true dockerfile:build
./gradlew build -x test docker