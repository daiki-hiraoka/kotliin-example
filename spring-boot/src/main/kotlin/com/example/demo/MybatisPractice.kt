package com.example.demo

import database.UserDynamicSqlSupport.User.name
import database.UserMapper
import database.select
import database.selectByPrimaryKey
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo

fun createSessionFactory(): SqlSessionFactory {
    val resource = "mybatis-config.xml"
    val inputStream = Resources.getResourceAsStream(resource)
    return SqlSessionFactoryBuilder().build(inputStream)
}

fun select() {
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val user = mapper.selectByPrimaryKey(100)
        print(user)
    }
}

fun selectWhere() {
    createSessionFactory().openSession().use { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val userList = mapper.select {
            where(name, isEqualTo("Jiro"))
        }
        print(userList)
    }
}

fun main() {
    select()
    selectWhere()
}