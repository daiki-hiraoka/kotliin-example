package com.example.demo.mybatis

import database.UserDynamicSqlSupport.User.name
import database.delete
import database.deleteByPrimaryKey
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo

fun main() {
    val mybatisDeletePractice = MybatisDeletePractice(MybatisSessionFactoryImpl())
    mybatisDeletePractice.deleteByPrimaryKey()
    mybatisDeletePractice.delete()
}

class MybatisDeletePractice(
    private val mybatisSessionFactory: MybatisSessionFactory,
) {
    fun deleteByPrimaryKey() {
        MybatisSessionFactoryImpl().withSession { session ->
            val mapper = session.getMapper(database.UserMapper::class.java)
            val count = mapper.deleteByPrimaryKey(102)
            session.commit()
            println("${count}行のレコードを削除しました")
        }
    }

    fun delete() {
        MybatisSessionFactoryImpl().withSession { session ->
            val mapper = session.getMapper(database.UserMapper::class.java)
            val count = mapper.delete {
                where(name, isEqualTo("Jiro"))
            }
            session.commit()
            println("${count}行のレコードを削除しました")
        }
    }
}
