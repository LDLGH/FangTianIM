package com.ldl.module.imcore

import com.blankj.utilcode.util.LogUtils
import com.ldl.module.imcore.interfaces.IFangIMCallBack
import com.ldl.module.imcore.interfaces.IFangIMConversationManager
import com.tencent.imsdk.v2.*

/**
 * @author LDL
 * @date: 2022/8/9
 * @description:
 */
object IMConversationManager : IFangIMConversationManager {

    /**
     * 删除指定ID的对话和本地的聊天记录
     *
     * @param id 会话ID
     */
    override fun deleteConversation(id: String, onSuccess: () -> Unit) {
        V2TIMManager.getConversationManager().deleteConversation(id, object : V2TIMCallback {
            override fun onSuccess() {
                onSuccess.invoke()
            }

            override fun onError(p0: Int, p1: String?) {
                LogUtils.e(p0, p1)
            }

        })
    }

    /**
     * 获取未读消息总数
     *
     * @param callback callback
     */
    override fun getTotalUnreadMessageCount(callback: (Long) -> Unit) {
        V2TIMManager.getConversationManager()
            .getTotalUnreadMessageCount(object : V2TIMValueCallback<Long> {
                override fun onSuccess(p0: Long) {
                    callback.invoke(p0)
                }

                override fun onError(p0: Int, p1: String?) {
                    LogUtils.e(p0, p1)
                }

            })
    }

    /**
     * 注册聊天会话变更及收到会话已读的监听器
     *
     * @param listener V2TIMConversationListener
     */
    override fun addConversationListener(listener: V2TIMConversationListener) {
        V2TIMManager.getConversationManager().addConversationListener(listener)
    }

    /**
     * 移除聊天会话变更及收到会话已读的监听器
     *
     * @param listener V2TIMConversationListener
     */
    override fun removeConversationListener(listener: V2TIMConversationListener) {
        V2TIMManager.getConversationManager().removeConversationListener(listener)
    }

    /**
     * 获取会话列表
     *
     * @param count 一次性拉取会话数量
     * @param callBack callBack
     */
    override fun getConversationList(
        count: Int,
        callBack: V2TIMValueCallback<V2TIMConversationResult>
    ) {
        V2TIMManager.getConversationManager().getConversationList(0, count, callBack)
    }

}