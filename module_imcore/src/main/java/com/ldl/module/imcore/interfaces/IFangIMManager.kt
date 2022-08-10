package com.ldl.module.imcore.interfaces

import android.app.Application
import com.tencent.imsdk.v2.V2TIMGroupListener
import com.tencent.imsdk.v2.V2TIMSDKListener

/**
 * @author LDL
 * @date: 2022/8/9
 * @description:
 */
interface IFangIMManager {

    /**
     * 初始化TIM SDK
     *
     * @param application application
     * @param appId appId
     * @return
     */
    fun init(application: Application, appId: Int, isDebug: Boolean): Boolean

    /**
     * 添加 SDK 事件监听器
     *
     * @param listener V2TIMSDKListener
     */
    fun addIMSDKListener(listener: V2TIMSDKListener)

    /**
     * 使用token方式登录
     *
     * @param userId 账号
     * @param token token
     * @param callBack 通用的IM回调函数接口
     */
    fun loginWithToken(userId: String, token: String, callBack: IFangIMCallBack?)

    /**
     * 异步退出登录
     *
     * @param callBack 通用的IM回调函数接口
     */
    fun asyncLogout(callBack: IFangIMCallBack?)

    /**
     * 获取登录状态
     *
     * @param userId 登录IM相关id
     * @return
     */
    fun isIMLogin(userId: String): Boolean


    /**
     * 群组事件监听
     *
     * @param listener listener
     */
    fun addGroupChangeListener(listener: V2TIMGroupListener)

    /**
     * 移除群组变化监听器
     *
     * @param listener listener
     */
    fun removeGroupChangeListener(listener: V2TIMGroupListener)
}