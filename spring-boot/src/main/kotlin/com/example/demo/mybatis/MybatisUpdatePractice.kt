package com.example.demo.mybatis

import database.UserDynamicSqlSupport.User.name
import database.UserRecord
import database.update
import database.updateByPrimaryKeySelective
import database.updateSelectiveColumns
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo

fun main() {
    val mybatisUpdatePractice = MybatisUpdatePractice()
    mybatisUpdatePractice.update()
    mybatisUpdatePractice.update2()
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

    fun update2() {
        val user = UserRecord(profile = "Good Morning")
        MybatisSessionFactoryImpl().withSession { session ->
            val mapper = session.getMapper(database.UserMapper::class.java)
            val count = mapper.update {
                updateSelectiveColumns(user)
                where(name, isEqualTo("Shiro"))
            }
            session.commit()
            println("${count}行のレコードを更新しました")
        }
    }
}
