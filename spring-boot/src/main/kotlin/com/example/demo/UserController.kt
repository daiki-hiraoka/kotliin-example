package com.example.demo

import com.example.demo.database.UserMapper
import com.example.demo.database.selectByPrimaryKey
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/users")
class UserController(
    val userMapper: UserMapper,
) {
    @GetMapping("/select/{id}")
    fun selectById(
        @PathVariable id: Int,
    ) = userMapper.selectByPrimaryKey(id)
}
