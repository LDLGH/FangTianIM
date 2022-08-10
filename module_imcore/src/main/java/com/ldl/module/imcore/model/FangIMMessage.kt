package com.ldl.module.imcore.model

/**
 * @author LDL
 * @date: 2021/11/2
 * @description: 自定义消息
 */
class FangIMMessage(var msgType: Int, var body: FangIMMessageBody)

class FangTextIMMessage(var msgType: Int, var body: FangTextIMMessageBody)

class FangImageIMMessage(var msgType: Int, var body: FangImageIMMessageBody)

class FangVoiceIMMessage(var msgType: Int, var body: FangVoiceIMMessageBody)

class FangFileIMMessage(var msgType: Int, var body: FangNormalFileIMMessageBody)

class FangVideoIMMessage(var msgType: Int, var body: FangVideoIMMessageBody)

object MessageBuilder {

    /**
     * 创建一个文本发送消息
     *
     * @param message 文本
     * @return FangIMMessage
     */
    fun createTextMessage(message: String, chatType: ChatType, user: User): FangIMMessage {
        val body = FangTextIMMessageBody(message, user, chatType.ordinal)
        return FangIMMessage(MsgType.TXT.ordinal, body)
    }

    /**
     * 创建一个图片发送消息
     *
     * @param fileName 文件名
     * @param localUrl 本地文件路径
     * @param remoteUrl 远程服务器文件地址
     * @param width 图片的宽度
     * @param height 图片的高度
     * @return FangIMMessage
     */
    fun createImageMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        width: Int,
        height: Int,
        chatType: ChatType,
        user: User
    ): FangIMMessage {
        val body = FangImageIMMessageBody(width, height, user, chatType.ordinal)
        body.let {
            it.fileName = fileName
            it.localUrl = localUrl
            it.remoteUrl = remoteUrl
        }
        return FangIMMessage(MsgType.IMAGE.ordinal, body)
    }

    /**
     * 创建一个语音发送消息
     *
     * @param fileName 文件名
     * @param localUrl 本地文件路径
     * @param remoteUrl 远程服务器文件地址
     * @param duration 语音事件长度，单位为秒
     * @return FangIMMessage
     */
    fun createVoiceMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        duration: Int,
        chatType: ChatType,
        user: User
    ): FangIMMessage {
        val body =
            FangVoiceIMMessageBody(duration, user, chatType.ordinal)
        body.let {
            it.fileName = fileName
            it.localUrl = localUrl
            it.remoteUrl = remoteUrl
        }
        return FangIMMessage(MsgType.VOICE.ordinal, body)
    }

    /**
     * 创建一个普通文件发送消息
     *
     * @param fileName 文件名
     * @param localUrl 本地文件路径
     * @param remoteUrl 远程服务器文件地址
     * @return FangIMMessage
     */
    fun createFileMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        fileLength: Long,
        chatType: ChatType,
        user: User
    ): FangIMMessage {
        val body = FangNormalFileIMMessageBody(user, chatType.ordinal)
        body.let {
            it.fileName = fileName
            it.localUrl = localUrl
            it.remoteUrl = remoteUrl
            it.fileLength = fileLength
        }
        return FangIMMessage(MsgType.FILE.ordinal, body)
    }

    /**
     * 创建一个视频发送消息
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
     * @return FangIMMessage
     */
    fun createVideoMessage(
        fileName: String,
        localUrl: String,
        remoteUrl: String,
        fileLength: Long,
        thumbnailUrl: String,
        localThumb: String,
        thumbnailWidth: Int,
        thumbnailHeight: Int,
        duration: Int,
        chatType: ChatType,
        user: User
    ): FangIMMessage {
        val body =
            FangVideoIMMessageBody(
                thumbnailUrl,
                localThumb,
                thumbnailWidth,
                thumbnailHeight,
                duration,
                user,
                chatType.ordinal
            )
        body.let {
            it.fileName = fileName
            it.localUrl = localUrl
            it.remoteUrl = remoteUrl
            it.fileLength = fileLength
        }
        return FangIMMessage(MsgType.VIDEO.ordinal, body)
    }
}