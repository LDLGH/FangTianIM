package com.ldl.module.imcore.interfaces

import com.tencent.imsdk.v2.V2TIMMessage

/**
 * @author LDL
 * @date: 2022/7/1
 * @description:
 */
interface IRecallMessageCallback {

    fun recallMessageFinish(message: V2TIMMessage)

    fun recallMessageFail(errorCode: Int, description: String)

}