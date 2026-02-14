package com.example.demo.mybatis

import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder

class MybatisSessionFactoryImpl : MybatisSessionFactory {
    private fun createSessionFactory(): SqlSessionFactory {
        val resource = "mybatis-config.xml"
        val inputStream = Resources.getResourceAsStream(resource)
        return SqlSessionFactoryBuilder().build(inputStream)
    }

    override fun <T> withSession(block: (SqlSession) -> T): T {
        return createSessionFactory().openSession().use { session ->
            block(session)
        }
    }
}
