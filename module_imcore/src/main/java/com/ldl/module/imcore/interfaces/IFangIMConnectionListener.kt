package com.ldl.module.imcore.interfaces

/**
 * @author LDL
 * @date: 2021/11/1
 * @description: 接收连接状态监听器
 */
interface IFangIMConnectionListener {

    /**
     * 成功连接到chat服务器时触发
     *
     */
    fun onConnected()

    /**
     * 和chat服务器断开连接时触发
     *
     * @param errorCode 错误码
     */
    fun onDisconnected(errorCode: Int)

}