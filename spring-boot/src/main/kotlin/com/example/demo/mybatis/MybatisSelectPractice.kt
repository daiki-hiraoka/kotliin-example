package com.example.demo.mybatis

import database.UserDynamicSqlSupport.User.age
import database.UserDynamicSqlSupport.User.name
import database.UserMapper
import database.count
import database.select
import database.selectByPrimaryKey
import org.mybatis.dynamic.sql.SqlBuilder.*

class MybatisSelectPractice(
    private val mybatisSessionFactory: MybatisSessionFactory
) {
    fun select() = mybatisSessionFactory.withSession { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val user = mapper.selectByPrimaryKey(100)
        print(user)
    }

    fun selectWhere() = mybatisSessionFactory.withSession { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val userList = mapper.select {
            where(name, isEqualTo("Jiro"))
        }
        println(userList)
    }

    fun selectWhere2() = mybatisSessionFactory.withSession { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val userList = mapper.select {
            where(age, isGreaterThanOrEqualTo(25))
        }
        println(userList)
    }

    fun selectCount() = mybatisSessionFactory.withSession { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val count = mapper.count {
            where(age, isGreaterThanOrEqualTo(25))
        }
        println(count)
    }

    fun selectAllCount() = mybatisSessionFactory.withSession { session ->
        val mapper = session.getMapper(UserMapper::class.java)
        val count = mapper.count { allRows() }
        println(count)
    }

}