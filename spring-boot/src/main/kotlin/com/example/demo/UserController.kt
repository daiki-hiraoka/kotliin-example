package com.example.demo

import com.example.demo.database.UserMapper
import com.example.demo.database.UserRecord
import com.example.demo.database.insert
import com.example.demo.database.selectByPrimaryKey
import com.example.demo.dto.UserInsertRequestDto
import com.example.demo.dto.UserInsertResponseDto
import org.springframework.web.bind.annotation.*

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

    @PostMapping("/insert")
    fun insert(@RequestBody request: UserInsertRequestDto): UserInsertResponseDto {
        val record = UserRecord(
            id = request.id,
            name = request.name,
            age = request.age,
            profile = request.profile
        )
        return UserInsertResponseDto(userMapper.insert(record))
    }
}
