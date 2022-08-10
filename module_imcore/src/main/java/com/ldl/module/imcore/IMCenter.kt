package com.ldl.module.imcore

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ProcessUtils
import com.ldl.module.imcore.interfaces.IFangIMCallBack
import com.ldl.module.imcore.interfaces.IFangIMManager
import com.tencent.imsdk.v2.*
import com.tencent.imsdk.v2.V2TIMManager.V2TIM_STATUS_LOGINED

/**
 * @author LDL
 * @date: 2021/11/1
 * @description:
 */
object IMCenter : IFangIMManager {

    /**
     * 初始化IM SDK
     *
     * @param application application
     * @param appId tim appId
     * @param isDebug isDebug
     * @return
     */
    override fun init(application: Application, appId: Int, isDebug: Boolean): Boolean {
        //防止SDK被初始化2次
        if (!ProcessUtils.isMainProcess()) {
            LogUtils.e("enter the service process!")
            return false
        }
        val config = V2TIMSDKConfig().apply {
            logLevel =
                if (isDebug) V2TIMSDKConfig.V2TIM_LOG_DEBUG else V2TIMSDKConfig.V2TIM_LOG_NONE
        }
        return V2TIMManager.getInstance().initSDK(application, appId, config)
    }

    /**
     * 添加 SDK 事件监听器
     *
     * @param listener V2TIMSDKListener
     */
    override fun addIMSDKListener(listener: V2TIMSDKListener) {
        V2TIMManager.getInstance().addIMSDKListener(listener)
    }

    /**
     * 使用token方式登录
     *
     * @param userId 账号
     * @param token token
     * @param callBack 通用的IM回调函数接口
     */
    override fun loginWithToken(userId: String, token: String, callBack: IFangIMCallBack?) {
        V2TIMManager.getInstance().login(userId, token, object : V2TIMCallback {
            override fun onSuccess() {
                callBack?.onSuccess()
            }

            override fun onError(p0: Int, p1: String?) {
                callBack?.onError(p0, p1)
            }

        })
    }

    /**
     * 异步退出登录
     *
     * @param callBack 通用的IM回调函数接口
     */
    override fun asyncLogout(callBack: IFangIMCallBack?) {
        V2TIMManager.getInstance().logout(object : V2TIMCallback {
            override fun onSuccess() {
                callBack?.onSuccess()
                // 反初始化 SDK
//                V2TIMManager.getInstance().unInitSDK()
            }

            override fun onError(p0: Int, p1: String?) {
                callBack?.onError(p0, p1)
            }

        })
    }

    /**
     * 获取登录状态
     *
     * @return
     */
    override fun isIMLogin(userId: String): Boolean {
        return V2TIMManager.getInstance().loginStatus == V2TIM_STATUS_LOGINED && V2TIMManager.getInstance().loginUser == userId
    }

    /**
     * 群组事件监听
     *
     * @param listener listener
     */
    override fun addGroupChangeListener(listener: V2TIMGroupListener) {
        V2TIMManager.getInstance().addGroupListener(listener)
    }

    /**
     * 移除群组变化监听器
     *
     * @param listener listener
     */
    override fun removeGroupChangeListener(listener: V2TIMGroupListener) {
        V2TIMManager.getInstance().removeGroupListener(listener)
    }

}