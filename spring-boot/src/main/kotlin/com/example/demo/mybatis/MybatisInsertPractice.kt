package com.example.demo.mybatis

import com.example.demo.database.UserMapper
import com.example.demo.database.UserRecord
import com.example.demo.database.insert
import com.example.demo.database.insertMultiple

fun main() {
    val mybatisInsertPractice = MybatisInsertPractice(MybatisSessionFactoryImpl())
//    mybatisInsertPractice.insert()
    mybatisInsertPractice.insertRecords()
}

class MybatisInsertPractice(
    private val mybatisSessionFactory: MybatisSessionFactory,
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

    fun insertRecords() {
        val userList = listOf(UserRecord(104, "Goro", 22, "Hi"), UserRecord(105, "Rokuro", 28, "Hey"))
        mybatisSessionFactory.withSession { session ->
            val mapper = session.getMapper(UserMapper::class.java)
            val count = mapper.insertMultiple(userList)
            session.commit()
            println("${count}行のレコードを挿入しました")
        }
    }
}
