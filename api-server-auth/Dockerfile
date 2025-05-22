# 파일명 : 반드시 Dockerfile , 내용 : 도커 이미지를 반드는 과정의 명령어들. 나열된 명령문을
#        차례대로 수행하여 이미지를 생성한다.명령어는 대문자 작성 규칙을 지키기.그래야 인자와 구별하기 쉽다.
# Use an official JDK image as a parent image. 이미지이름:태그
# 도커 이미지는 base 이미지 부터 시작하여 여러 층(layer)으로 이미지를 쌓아가며 만들어진다.
#            ㄴ 일반적으로 잘 알려진 공개 이미지로 예를 들면, ubuntu, node, python, httpd, mysql 등이 있다.
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
# 작업 디렉토리 지정하여 전환한다. (change directory: cd) 이후에 나오는 명령들은 모두 작업 디렉토리에서 실행된다.
#    ㄴ 도커 이미지(컨테이너) 안에서의 디렉토리 이다.
WORKDIR /app

# 리눅스 shell 명령문 실행하기. 아래 예시 처럼 특정 패키지 설치에 사용
# RUN sudo apt update
# RUN sudo apt install curl

# Copy the Spring Boot application JAR file to the container
# 호스트 컴퓨터에 있는 디렉토리나 파일을 Docker 이미지의 파일 시스템으로 복사.
# COPY . .  는 이미지 빌드를 수행한 디렉토리의 모든 파일을 컨테이너 app/ 디렉토리로 복사
COPY ./build/libs/api-server-auth-0.0.1-SNAPSHOT.jar /app/api-server-auth.jar
# 특정 폴더의 이미지 파일 복사
# context가 현재 디렉토리일 때, ./upload 폴더의 모든 파일을 /app/upload로 복사
COPY upload /app/upload
# Expose the port that the application will run on
# 네트워크 상에서 컨테이너로 들어오는 트래픽(통신)을 리스닝하는 포트와 프로토콜 tcp 또는 udp 지정.
# 기본프로토콜은 TCP. 해당 컨테이너에서만 사용하는 포트이다. 이 프로그램은 8080 포트를 사용하여 서비스 된다.
EXPOSE 8080

# Command to run the Spring Boot application
# 이미지를 컨테이너로 띄울 때 항상 실행되는 명령어. 아래 명령어로 백엔드 프로그램의 배포 파일을 실행한다.
# 이렇게 실행된 프로세스와 컨테이너의 라이프사이클은 동일하다.
ENTRYPOINT ["java", "-jar", "/app/api-server-auth.jar"]
