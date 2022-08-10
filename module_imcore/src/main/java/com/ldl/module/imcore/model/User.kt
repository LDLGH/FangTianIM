package com.ldl.module.imcore.model

/**
 * @author LDL
 * @date: 2022/7/1
 * @description:
 */
data class User(
    var userId: String,
    var userName: String,
    var userType: String = "s"
)
