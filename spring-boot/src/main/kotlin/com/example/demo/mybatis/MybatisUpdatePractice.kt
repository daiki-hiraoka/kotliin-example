package com.example.demo.mybatis

import database.UserRecord
import database.updateByPrimaryKeySelective

fun main() {
    val mybatisUpdatePractice = MybatisUpdatePractice()
    mybatisUpdatePractice.update()
}

class MybatisUpdatePractice {
    fun update() {
        val user = UserRecord(id = 105, profile = "Bye")
        MybatisSessionFactoryImpl().withSession { session ->
            val mapper = session.getMapper(database.UserMapper::class.java)
            val count = mapper.updateByPrimaryKeySelective(user)
            session.commit()
            println("${count}行のレコードを更新しました")
        }
    }
}
