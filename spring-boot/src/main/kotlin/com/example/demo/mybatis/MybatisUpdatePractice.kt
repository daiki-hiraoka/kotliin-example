package com.example.demo.mybatis

import com.example.demo.database.UserDynamicSqlSupport.User.name
import com.example.demo.database.UserMapper
import com.example.demo.database.UserRecord
import com.example.demo.database.update
import com.example.demo.database.updateByPrimaryKeySelective
import com.example.demo.database.updateSelectiveColumns
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
            val mapper = session.getMapper(UserMapper::class.java)
            val count = mapper.updateByPrimaryKeySelective(user)
            session.commit()
            println("${count}行のレコードを更新しました")
        }
    }

    fun update2() {
        val user = UserRecord(profile = "Good Morning")
        MybatisSessionFactoryImpl().withSession { session ->
            val mapper = session.getMapper(UserMapper::class.java)
            val count =
                mapper.update {
                    updateSelectiveColumns(user)
                    where(name, isEqualTo("Shiro"))
                }
            session.commit()
            println("${count}行のレコードを更新しました")
        }
    }
}
