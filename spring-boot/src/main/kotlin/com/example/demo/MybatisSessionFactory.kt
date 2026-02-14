package com.example.demo

import org.apache.ibatis.session.SqlSession

interface MybatisSessionFactory {
    fun <T> withSession(block: (SqlSession) -> T): T
}
