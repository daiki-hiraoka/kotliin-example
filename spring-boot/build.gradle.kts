plugins {
    // kotlin()のプラグインはGradleでのKotlinプロジェクトのビルド、KotlinでのSpring Bootの使用で必要となる
    kotlin("jvm")
    kotlin("plugin.spring")
    // Spring BootアプリケーションをGradleから実行するためのもの。bootRunという起動タスクが提供されている
    id("org.springframework.boot")
    // 依存関係の管理のサポートをしてくれるプラグイン
    // Spring Bootの起動に必要なstarter関連の依存関係を追加するとき、入れているSpring Bootで必要となるバージョンを指定してくれる
    id("io.spring.dependency-management")
    id("com.thinkimi.gradle.MybatisGenerator") version "2.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Thymeleafというテンプレートエンジンを使用するためのstarter
    // ThymeleafでHTMLなどのwebページを作成する
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    // ルーティングなどのwebアプリケーションのサーバーサイドプログラムで必要な機能を提供するstarter
    // Spring MVC, Jackson, Tomcat（組み込みサーバー）などが自動で追加される
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // JacksonとはJSONのシリアライズ、デシリアライズをするライブラリ
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.mybatis:mybatis:3.5.6")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.2.1")
    implementation("mysql:mysql-connector-java:8.0.23")
    implementation("org.mybatis.generator:mybatis-generator-core:1.4.0")
    
    // MyBatis Generator用の依存関係
    mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.0")
    mybatisGenerator("mysql:mysql-connector-java:8.0.23")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configurations {
    mybatisGenerator
}

mybatisGenerator {
    verbose = true
    configFile = "${projectDir}/src/main/resources/generatorConfig.xml"
    mybatisProperties = mapOf(
        "jdbcUrl" to "jdbc:mysql://127.0.0.1:3306/example",
        "jdbcDriverClass" to "com.mysql.cj.jdbc.Driver",
        "jdbcUsername" to "root",
        "jdbcPassword" to "mysql"
    )
}