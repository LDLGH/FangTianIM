package com.ldl.module.imcore

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.ldl.module.imcore.interfaces.IFangIMCallBack
import com.ldl.module.imcore.interfaces.IFangIMMessageManager
import com.ldl.module.imcore.interfaces.IFangMessageStatusCallback
import com.ldl.module.imcore.model.ChatType
import com.ldl.module.imcore.model.MessageBuilder
import com.ldl.module.imcore.model.User
import com.tencent.imsdk.v2.*

/**
 * @author LDL
 * @date: 2022/8/9
 * @description:
 */
object IMMessageManager : IFangIMMessageManager {

    /**
     * 发送自定义消息
     *
     * @param body 自定义消息体
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     * @param callBack 发送消息的回调
     */
    override fun sendMessage(
        body: String,
        toId: String,
        chatType: ChatType,
        callBack: IFangMessageStatusCallback?
    ) {
        val v2TIMMessage =
            V2TIMManager.getMessageManager().createCustomMessage(body.toByteArray())
        var msgID = ""
        msgID = V2TIMManager.getMessageManager().sendMessage(
            v2TIMMessage,
            if (chatType == ChatType.Chat) toId else null,
            if (chatType == ChatType.GroupChat) toId else null,
            V2TIMMessage.V2TIM_PRIORITY_NORMAL,
            false,
            null,
            object :
                V2TIMSendCallback<V2TIMMessage> {
                override fun onSuccess(p0: V2TIMMessage?) {
                    callBack?.onMessageSuccess(p0!!)
                }

                override fun onError(p0: Int, p1: String?) {
                    LogUtils.e(p0, p1)
                    callBack?.onMessageError(v2TIMMessage, p0, p1, msgID)
                }

                override fun onProgress(p0: Int) {

                }

            })
        callBack?.onMessageSending(v2TIMMessage, msgID)
    }

    /**
     * 发送自定义文本消息
     *
     * @param content 文本
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     * @param callBack 发送消息的回调
     */
    override fun sendTxtMessage(
        content: String,
        toId: String,
        chatType: ChatType,
        user: User,
        callBack: IFangMessageStatusCallback?
    ) {
        val body = MessageBuilder.createTextMessage(content, chatType, user)
        sendMessage(GsonUtils.toJson(body), toId, chatType, callBack)
    }

    /**
     * 发送自定义图片消息
     *
     * @param fileName 文件名
     * @param localUrl 本地文件路径
     * @param remoteUrl 远程服务器文件地址
     * @param width 图片的宽度
     * @param height 图片的高度
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     * @param callBack 发送消息的回调
     */
    override fun sendImageMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        width: Int,
        height: Int,
        toId: String,
        chatType: ChatType,
        user: User,
        callBack: IFangMessageStatusCallback?
    ) {
        val body =
            MessageBuilder.createImageMessage(
                fileName,
                localUrl,
                remoteUrl,
                width,
                height,
                chatType,
                user
            )
        sendMessage(GsonUtils.toJson(body), toId, chatType, callBack)
    }

    /**
     * 发送自定义语音消息
     *
     * @param fileName 文件名
     * @param localUrl 本地文件路径
     * @param remoteUrl 远程服务器文件地址
     * @param duration 语音事件长度，单位为秒
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     * @param callBack 发送消息的回调
     */
    override fun sendVoiceMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        duration: Int,
        toId: String,
        chatType: ChatType,
        user: User,
        callBack: IFangMessageStatusCallback?
    ) {
        val body =
            MessageBuilder.createVoiceMessage(
                fileName,
                localUrl,
                remoteUrl,
                duration,
                chatType,
                user
            )
        sendMessage(GsonUtils.toJson(body), toId, chatType, callBack)
    }

    /**
     * 发送自定义文件消息
     *
     * @param fileName 文件名
     * @param localUrl 本地文件路径
     * @param remoteUrl 远程服务器文件地址
     * @param fileLength 文件大小
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     * @param callBack 发送消息的回调
     */
    override fun sendFileMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        fileLength: Long,
        toId: String,
        chatType: ChatType,
        user: User,
        callBack: IFangMessageStatusCallback?
    ) {
        val body =
            MessageBuilder.createFileMessage(
                fileName,
                localUrl,
                remoteUrl,
                fileLength,
                chatType,
                user
            )
        sendMessage(GsonUtils.toJson(body), toId, chatType, callBack)
    }

    /**
     * 发送自定义视频消息
     *
     * @param fileName 文件名
     * @param localUrl 本地文件路径
     * @param remoteUrl 远程服务器文件地址
     * @param fileLength 文件大小
     * @param thumbnailUrl 远程视频缩略图
     * @param localThumb 本地视频缩略图
     * @param thumbnailWidth 视频缩略图的宽度
     * @param thumbnailHeight 视频缩略图的高度
     * @param duration 视频时长, 单位为秒
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     * @param callBack 发送消息的回调
     */
    override fun sendVideoMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        fileLength: Long,
        thumbnailUrl: String,
        localThumb: String,
        thumbnailWidth: Int,
        thumbnailHeight: Int,
        duration: Int,
        toId: String,
        chatType: ChatType,
        user: User,
        callBack: IFangMessageStatusCallback?
    ) {
        val body = MessageBuilder.createVideoMessage(
            fileName,
            localUrl,
            remoteUrl,
            fileLength,
            thumbnailUrl,
            localThumb,
            thumbnailWidth,
            thumbnailHeight,
            duration,
            chatType,
            user
        )
        sendMessage(GsonUtils.toJson(body), toId, chatType, callBack)
    }

    /**
     * 重发消息
     *
     * @param message message
     */
    override fun resendMessage(message: V2TIMMessage, callBack: IFangMessageStatusCallback?) {
        var msgID = ""
        msgID = V2TIMManager.getMessageManager().sendMessage(
            message,
            if (message.groupID == null) message.userID else null,
            if (message.groupID == null) null else message.groupID,
            V2TIMMessage.V2TIM_PRIORITY_NORMAL,
            false,
            null,
            object :
                V2TIMSendCallback<V2TIMMessage> {
                override fun onSuccess(p0: V2TIMMessage?) {
                    callBack?.onMessageSuccess(p0!!)
                }

                override fun onError(p0: Int, p1: String?) {
                    callBack?.onMessageError(message, p0, p1, msgID)
                }

                override fun onProgress(p0: Int) {
                    // 自定义消息不会回调进度
                }

            })
        callBack?.onMessageSending(message, msgID)
    }

    /**
     * 删除当前会话的某条聊天记录
     *
     * @param selectedMessageList 删除消息列表
     */
    override fun removeMessage(
        selectedMessageList: List<V2TIMMessage>,
        callBack: IFangIMCallBack?
    ) {
        V2TIMManager.getMessageManager()
            .deleteMessages(selectedMessageList, object : V2TIMCallback {
                override fun onSuccess() {
                    callBack?.onSuccess()
                }

                override fun onError(p0: Int, p1: String?) {
                    callBack?.onError(p0, p1)
                }

            })
    }

    /**
     * 撤回消息
     *
     * @param v2TIMMessage V2TIMMessage
     * @param callBack IFangIMCallBack
     */
    override fun revokeMessage(v2TIMMessage: V2TIMMessage, callBack: IFangIMCallBack?) {
        V2TIMManager.getMessageManager().revokeMessage(v2TIMMessage, object : V2TIMCallback {
            override fun onSuccess() {
                callBack?.onSuccess()
            }

            override fun onError(p0: Int, p1: String?) {
                callBack?.onError(p0, p1)
            }

        })
    }

    /**
     * 注册消息监听来接收消息
     *
     * @param listener V2TIMAdvancedMsgListener
     */
    override fun addMessageListener(listener: V2TIMAdvancedMsgListener) {
        V2TIMManager.getMessageManager().addAdvancedMsgListener(listener)
    }

    /**
     * 移除消息监听来接收消息
     *
     * @param listener V2TIMAdvancedMsgListener
     */
    override fun removeMessageListener(listener: V2TIMAdvancedMsgListener) {
        V2TIMManager.getMessageManager().removeAdvancedMsgListener(listener)
    }

    /**
     * 拉取历史消息
     *
     * @param chatType 聊天类型
     * @param id 群聊或单聊id
     * @param pageSize 默认拉取20条消息
     * @param lastMsg 消息列表中的最后一条消息
     * @param callBack callBack
     * @see ChatType
     */
    override fun getHistoryMessageList(
        chatType: Int,
        id: String,
        pageSize: Int,
        lastMsg: V2TIMMessage?,
        callBack: (List<V2TIMMessage>) -> Unit
    ) {
        when (chatType) {
            ChatType.Chat.ordinal -> {
                V2TIMManager.getMessageManager()
                    .getC2CHistoryMessageList(
                        id,
                        pageSize,
                        lastMsg,
                        object : V2TIMValueCallback<List<V2TIMMessage>> {
                            override fun onSuccess(p0: List<V2TIMMessage>?) {
                                p0?.let {
                                    callBack.invoke(p0)
                                }
                            }

                            override fun onError(p0: Int, p1: String?) {
                                LogUtils.e(p0, p1)
                            }

                        })
            }
            ChatType.GroupChat.ordinal -> {
                V2TIMManager.getMessageManager()
                    .getGroupHistoryMessageList(
                        id,
                        pageSize,
                        lastMsg,
                        object : V2TIMValueCallback<List<V2TIMMessage>> {
                            override fun onSuccess(p0: List<V2TIMMessage>?) {
                                p0?.let {
                                    callBack.invoke(p0)
                                }
                            }

                            override fun onError(p0: Int, p1: String?) {
                                LogUtils.e(p0, p1)
                            }

                        })
            }
            else -> {
            }
        }
    }

    /**
     * 清空指定会话的未读消息数
     *
     * @param chatType 聊天类型
     * @param id 群聊或单聊id
     */
    override fun markAllMessagesAsRead(chatType: Int, id: String) {
        when (chatType) {
            ChatType.Chat.ordinal -> {
                V2TIMManager.getMessageManager().markC2CMessageAsRead(id, object : V2TIMCallback {
                    override fun onSuccess() {

                    }

                    override fun onError(p0: Int, p1: String?) {
                        LogUtils.e(p0, p1)
                    }

                })
            }
            ChatType.GroupChat.ordinal -> {
                V2TIMManager.getMessageManager().markGroupMessageAsRead(id, object : V2TIMCallback {
                    override fun onSuccess() {
                    }

                    override fun onError(p0: Int, p1: String?) {
                        LogUtils.e(p0, p1)
                    }

                })
            }
            else -> {
            }
        }
    }

}