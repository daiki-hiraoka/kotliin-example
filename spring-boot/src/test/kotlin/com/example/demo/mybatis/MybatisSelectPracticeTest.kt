package com.example.demo.mybatis

import org.junit.jupiter.api.Test

class MybatisSelectPracticeTest {
    private val practice = MybatisSelectPractice(MybatisSessionFactoryImpl())

    @Test
    fun select() {
        practice.select()
    }

    @Test
    fun selectWhere() {
        practice.selectWhere()
    }

    @Test
    fun selectWhere2() {
        practice.selectWhere2()
    }

    @Test
    fun selectCount() {
        practice.selectCount()
    }

    @Test
    fun selectAllCount() {
        practice.selectAllCount()
    }
}
