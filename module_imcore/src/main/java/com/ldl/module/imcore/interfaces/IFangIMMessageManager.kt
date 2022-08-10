package com.ldl.module.imcore.interfaces

import com.ldl.module.imcore.model.ChatType
import com.ldl.module.imcore.model.User
import com.tencent.imsdk.v2.V2TIMAdvancedMsgListener
import com.tencent.imsdk.v2.V2TIMMessage

/**
 * @author LDL
 * @date: 2021/11/1
 * @description: 发送消息统一接口
 */
interface IFangIMMessageManager {
    /**
     * 发送自定义消息
     *
     * @param body 自定义消息体
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     */
    fun sendMessage(
        body: String,
        toId: String,
        chatType: ChatType,
        callBack: IFangMessageStatusCallback?
    )

    /**
     * 发送自定义文本消息
     *
     * @param content 文本
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     */
    fun sendTxtMessage(
        content: String,
        toId: String,
        chatType: ChatType,
        user: User,
        callBack: IFangMessageStatusCallback?
    )

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
     */
    fun sendImageMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        width: Int,
        height: Int,
        toId: String,
        chatType: ChatType,
        user: User,
        callBack: IFangMessageStatusCallback?
    )

    /**
     * 发送自定义语音消息
     *
     * @param fileName 文件名
     * @param localUrl 本地文件路径
     * @param remoteUrl 远程服务器文件地址
     * @param duration 语音事件长度，单位为秒
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     */
    fun sendVoiceMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        duration: Int,
        toId: String,
        chatType: ChatType,
        user: User,
        callBack: IFangMessageStatusCallback?
    )

    /**
     * 发送自定义文件消息
     *
     * @param fileName 文件名
     * @param localUrl 本地文件路径
     * @param remoteUrl 远程服务器文件地址
     * @param toId to指另一方环信id（或者群组id，聊天室id）
     * @param chatType 如果是群聊，设置chatType，默认是单聊
     */
    fun sendFileMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        fileLength: Long,
        toId: String,
        chatType: ChatType,
        user: User,
        callBack: IFangMessageStatusCallback?
    )

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
     */
    fun sendVideoMessage(
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
    )

    /**
     * 重发消息
     *
     * @param message message
     */
    fun resendMessage(message: V2TIMMessage, callBack: IFangMessageStatusCallback?)

    /**
     * 删除当前会话的某条聊天记录
     *
     * @param selectedMessageList 删除消息列表
     */
    fun removeMessage(selectedMessageList: List<V2TIMMessage>, callBack: IFangIMCallBack?)

    /**
     * 撤回消息
     *
     * @param v2TIMMessage V2TIMMessage
     * @param callBack IFangIMCallBack
     */
    fun revokeMessage(v2TIMMessage: V2TIMMessage, callBack: IFangIMCallBack?)

    /**
     * 注册消息监听来接收消息
     *
     * @param listener V2TIMAdvancedMsgListener
     */
    fun addMessageListener(listener: V2TIMAdvancedMsgListener)

    /**
     * 移除消息监听来接收消息
     *
     * @param listener V2TIMAdvancedMsgListener
     */
    fun removeMessageListener(listener: V2TIMAdvancedMsgListener)

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
    fun getHistoryMessageList(
        chatType: Int,
        id: String,
        pageSize: Int = 20,
        lastMsg: V2TIMMessage?,
        callBack: (List<V2TIMMessage>) -> Unit
    )

    /**
     * 清空指定会话的未读消息数
     *
     * @param chatType 聊天类型
     * @param id 群聊或单聊id
     */
    fun markAllMessagesAsRead(chatType: Int, id: String)
}

