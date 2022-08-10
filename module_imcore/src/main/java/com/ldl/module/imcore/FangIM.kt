package com.ldl.module.imcore

import android.app.Application
import com.ldl.module.imcore.interfaces.*
import com.ldl.module.imcore.model.ChatType
import com.ldl.module.imcore.model.User
import com.tencent.imsdk.v2.*

/**
 * @author LDL
 * @date: 2021/11/1
 * @description:
 */
object FangIM : IFangIMManager, IFangIMMessageManager, IFangIMConversationManager,
    IFangIMGroupManager {

    /**
     * 初始化IM SDK
     *
     * @param application application
     * @param appId tim appId
     * @param isDebug isDebug
     * @return
     */
    override fun init(application: Application, appId: Int, isDebug: Boolean): Boolean {
        return IMCenter.init(application, appId, isDebug)
    }

    /**
     * 添加 SDK 事件监听器
     *
     * @param listener V2TIMSDKListener
     */
    override fun addIMSDKListener(listener: V2TIMSDKListener) {
        IMCenter.addIMSDKListener(listener)
    }

    /**
     * 使用token方式登录
     *
     * @param userId 账号
     * @param token token
     * @param callBack 通用的IM回调函数接口
     */
    override fun loginWithToken(userId: String, token: String, callBack: IFangIMCallBack?) {
        IMCenter.loginWithToken(userId, token, callBack)
    }

    /**
     * 异步退出登录
     *
     * @param callBack 通用的IM回调函数接口
     */
    override fun asyncLogout(callBack: IFangIMCallBack?) {
        IMCenter.asyncLogout(callBack)
    }

    /**
     * 环信是否已登陆
     *
     * @return
     */
    override fun isIMLogin(userId: String): Boolean {
        return IMCenter.isIMLogin(userId)
    }

    /**
     * 群组事件监听
     *
     * @param listener listener
     */
    override fun addGroupChangeListener(listener: V2TIMGroupListener) {
        IMCenter.addGroupChangeListener(listener)
    }

    /**
     * 移除群组变化监听器
     *
     * @param listener listener
     */
    override fun removeGroupChangeListener(listener: V2TIMGroupListener) {
        IMCenter.removeGroupChangeListener(listener)
    }

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
        IMMessageManager.sendMessage(body, toId, chatType, callBack)
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
        IMMessageManager.sendTxtMessage(content, toId, chatType, user, callBack)
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
        IMMessageManager.sendImageMessage(
            fileName,
            localUrl,
            remoteUrl,
            width,
            height,
            toId,
            chatType,
            user,
            callBack
        )
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
        IMMessageManager.sendVoiceMessage(
            fileName,
            localUrl,
            remoteUrl,
            duration,
            toId,
            chatType,
            user,
            callBack
        )
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
        IMMessageManager.sendFileMessage(
            fileName,
            localUrl,
            remoteUrl,
            fileLength,
            toId,
            chatType,
            user,
            callBack
        )
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
        IMMessageManager.sendVideoMessage(
            fileName,
            localUrl,
            remoteUrl,
            fileLength,
            thumbnailUrl,
            localThumb,
            thumbnailWidth,
            thumbnailHeight,
            duration,
            toId,
            chatType,
            user,
            callBack
        )
    }

    /**
     * 重发消息
     *
     * @param message message
     */
    override fun resendMessage(message: V2TIMMessage, callBack: IFangMessageStatusCallback?) {
        IMMessageManager.resendMessage(message, callBack)
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
        IMMessageManager.removeMessage(selectedMessageList, callBack)
    }

    /**
     * 撤回消息
     *
     * @param v2TIMMessage V2TIMMessage
     * @param callBack IFangIMCallBack
     */
    override fun revokeMessage(v2TIMMessage: V2TIMMessage, callBack: IFangIMCallBack?) {
        IMMessageManager.revokeMessage(v2TIMMessage, callBack)
    }

    /**
     * 注册消息监听来接收消息
     *
     * @param listener V2TIMAdvancedMsgListener
     */
    override fun addMessageListener(listener: V2TIMAdvancedMsgListener) {
        IMMessageManager.addMessageListener(listener)
    }

    /**
     * 移除消息监听来接收消息
     *
     * @param listener V2TIMAdvancedMsgListener
     */
    override fun removeMessageListener(listener: V2TIMAdvancedMsgListener) {
        IMMessageManager.removeMessageListener(listener)
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
        IMMessageManager.getHistoryMessageList(chatType, id, pageSize, lastMsg, callBack)
    }

    /**
     * 清空指定会话的未读消息数
     *
     * @param chatType 聊天类型
     * @param id 群聊或单聊id
     */
    override fun markAllMessagesAsRead(chatType: Int, id: String) {
        IMMessageManager.markAllMessagesAsRead(chatType, id)
    }

    /**
     * 删除指定ID的对话和本地的聊天记录
     *
     * @param id 会话ID
     */
    override fun deleteConversation(id: String, onSuccess: () -> Unit) {
        IMConversationManager.deleteConversation(id, onSuccess)
    }

    /**
     * 获取未读消息数
     *
     * @param callback callback
     */
    override fun getTotalUnreadMessageCount(callback: (Long) -> Unit) {
        IMConversationManager.getTotalUnreadMessageCount(callback)
    }

    /**
     * 注册聊天会话变更及收到会话已读的监听器
     *
     * @param listener V2TIMConversationListener
     */
    override fun addConversationListener(listener: V2TIMConversationListener) {
        IMConversationManager.addConversationListener(listener)
    }

    /**
     * 移除聊天会话变更及收到会话已读的监听器
     *
     * @param listener V2TIMConversationListener
     */
    override fun removeConversationListener(listener: V2TIMConversationListener) {
        IMConversationManager.removeConversationListener(listener)
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
        IMConversationManager.getConversationList(count, callBack)
    }

    /**
     * 禁言所有成员
     *
     * @param groupId 群组id
     * @param callBack callBack
     */
    override fun muteAllMembers(groupId: String, callBack: IFangIMCallBack?) {
        IMGroupManager.muteAllMembers(groupId, callBack)
    }

    /**
     *解除所有成员禁言
     *
     * @param groupId 群组id
     * @param callBack callBack
     */
    override fun unMuteAllMembers(groupId: String, callBack: IFangIMCallBack?) {
        IMGroupManager.unMuteAllMembers(groupId, callBack)
    }


    /**
     * 获取已加入的群组
     *
     * @param onSuccess onSuccess
     */
    override fun getJoinedGroupList(
        onSuccess: (List<V2TIMGroupInfo>?) -> Unit,
        onError: () -> Unit
    ) {
        IMGroupManager.getJoinedGroupList(onSuccess, onError)
    }


    /**
     * 获取群资料
     *
     * @param groupIds 支持一次传入多个 groupID
     * @param onSuccess onSuccess
     */
    override fun getGroupsInfo(
        groupIds: List<String>,
        onSuccess: (List<V2TIMGroupInfoResult>) -> Unit
    ) {
        IMGroupManager.getGroupsInfo(groupIds, onSuccess)
    }

    /**
     * 获取群成员资料
     *
     * @param groupId groupId
     * @param userID userID
     * @param onSuccess onSuccess
     * @receiver
     */
    override fun getGroupMembersInfo(
        groupId: String,
        userID: String,
        onSuccess: (List<V2TIMGroupMemberFullInfo>) -> Unit
    ) {
        IMGroupManager.getGroupMembersInfo(groupId, userID, onSuccess)
    }
}