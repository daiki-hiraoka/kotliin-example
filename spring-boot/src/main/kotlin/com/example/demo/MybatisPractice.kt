package com.example.demo

import database.UserMapper
import database.UserRecord
import database.selectByPrimaryKey
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder

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

fun main() {
    select()
}