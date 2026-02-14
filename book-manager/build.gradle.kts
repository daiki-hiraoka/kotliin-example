plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.thinkimi.gradle.MybatisGenerator") version "2.4"
}

group = "com.book.manager"
version = "0.0.1-SNAPSHOT"
description = "Book Manager project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:4.0.1")
	implementation("tools.jackson.module:jackson-module-kotlin")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:4.0.1")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.4.0")
	implementation("mysql:mysql-connector-java:8.0.33")
	mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.0")
	mybatisGenerator("mysql:mysql-connector-java:8.0.33")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

configurations {
	mybatisGenerator
}

tasks.withType<Test> {
	useJUnitPlatform()
}

mybatisGenerator {
	verbose = true
	configFile = file("${projectDir}/main/resources/generatorConfig.xml")
}