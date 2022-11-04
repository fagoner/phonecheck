docker rmi -f tdevopssolutions/phonecheck:dev || true
mvn clean package
docker build -t tdevopssolutions/phonechec:dev .
