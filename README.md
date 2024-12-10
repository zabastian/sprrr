왜인지 다른 파일들은 안올라가서 시간이 없어 일단 올리겠습니다 ㅜㅜ

mysql작성해서 schedule이름으로 db생성하고 build.gradle 수정해놨습니다.(아래가 build.gradle파일입니다 왜 버전 2개가 들어갔는지는 모르겠네요)


plugins {
    id 'java'
    id 'org.springframework.boot' version '3.4.0'
    id 'io.spring.dependency-management' version '1.1.6'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'mysql:mysql-connector-java:8.0.29'
    implementation 'org.springframework.jdbc:spring-jdbc:5.3.9'
    implementation 'mysql:mysql-connector-java:8.0.28'
// JDBC Template
    implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
    useJUnitPlatform()
}

