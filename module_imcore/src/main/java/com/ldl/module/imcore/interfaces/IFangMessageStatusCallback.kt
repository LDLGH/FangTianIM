package com.ldl.module.imcore.interfaces

import com.tencent.imsdk.v2.V2TIMMessage

/**
 * @author LDL
 * @date: 2021/11/2
 * @description: 消息状态回调接口
 */
interface IFangMessageStatusCallback {

    fun onMessageSuccess(message: V2TIMMessage)

    fun onMessageError(message: V2TIMMessage, code: Int, error: String?, msgID: String)

    fun onMessageProgress(message: V2TIMMessage, progress: Int, status: String?)

    fun onMessageSending(message: V2TIMMessage, msgID: String)
}