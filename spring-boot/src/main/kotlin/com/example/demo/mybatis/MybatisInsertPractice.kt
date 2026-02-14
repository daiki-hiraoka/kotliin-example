package com.example.demo.mybatis

import database.UserMapper
import database.UserRecord
import database.insert

fun main() {
    val mybatisInsertPractice = MybatisInsertPractice(MybatisSessionFactoryImpl())
    mybatisInsertPractice.insert()
}

class MybatisInsertPractice(
    private val mybatisSessionFactory: MybatisSessionFactory
) {
    fun insert() {
        val user = UserRecord(103, "Shiro", 18, "Hello")
        mybatisSessionFactory.withSession { session ->
            val mapper = session.getMapper(UserMapper::class.java)
            val count = mapper.insert(user)
            session.commit()
            println("${count}行のレコードを挿入しました")
        }
    }
}