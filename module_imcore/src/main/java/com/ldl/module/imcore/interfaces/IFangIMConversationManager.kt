package com.ldl.module.imcore.interfaces

import com.tencent.imsdk.v2.V2TIMConversationListener
import com.tencent.imsdk.v2.V2TIMConversationResult
import com.tencent.imsdk.v2.V2TIMValueCallback

/**
 * @author LDL
 * @date: 2022/8/9
 * @description:
 */
interface IFangIMConversationManager {

    /**
     * 删除指定ID的对话和本地的聊天记录
     *
     * @param id 会话ID
     */
    fun deleteConversation(id: String, onSuccess: () -> Unit)

    /**
     * 获取未读消息总数
     *
     * @param callback callback
     */
    fun getTotalUnreadMessageCount(callback: (Long) -> Unit)

    /**
     * 注册聊天会话变更及收到会话已读的监听器
     *
     * @param listener V2TIMConversationListener
     */
    fun addConversationListener(listener: V2TIMConversationListener)

    /**
     * 移除聊天会话变更及收到会话已读的监听器
     *
     * @param listener V2TIMConversationListener
     */
    fun removeConversationListener(listener: V2TIMConversationListener)

    /**
     * 获取会话列表
     *
     * @param count 一次性拉取会话数量
     * @param callBack callBack
     */
    fun getConversationList(count: Int, callBack: V2TIMValueCallback<V2TIMConversationResult>)
}