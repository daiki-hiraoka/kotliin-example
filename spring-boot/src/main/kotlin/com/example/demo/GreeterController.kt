package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class HelloResponse(val message: String)

// RestControllerアノテーションをつけると戻り値のオブジェクトをJSONにシリアライズしてレスポンスとして返す
// @ControllerのままだとSpring Bootが戻り値をビュー名だと認識して、Thymeleafを通じて対応するHTMLを探しに行ってしまうのでエラーになる
@RestController
@RequestMapping("greeter")
class GreeterController {
    @GetMapping("/hello")
    fun hello(@RequestParam("name") name: String): HelloResponse {
        return HelloResponse("Hello $name")
    }

    @GetMapping("/hello/{name}")
    fun helloPathValue(@PathVariable("name") name: String): HelloResponse {
        return HelloResponse("Hello $name")
    }
}