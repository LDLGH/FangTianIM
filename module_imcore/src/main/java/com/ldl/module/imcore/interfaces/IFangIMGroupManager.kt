package com.ldl.module.imcore.interfaces

import com.tencent.imsdk.v2.V2TIMGroupInfo
import com.tencent.imsdk.v2.V2TIMGroupInfoResult
import com.tencent.imsdk.v2.V2TIMGroupMemberFullInfo

/**
 * @author LDL
 * @date: 2022/8/9
 * @description:
 */
interface IFangIMGroupManager {

    /**
     * 禁言所有成员
     *
     * @param groupId 群组id
     * @param callBack callBack
     */
    fun muteAllMembers(groupId: String, callBack: IFangIMCallBack?)


    /**
     * 解除所有成员禁言
     *
     * @param groupId 群组id
     * @param callBack callBack
     */
    fun unMuteAllMembers(groupId: String, callBack: IFangIMCallBack?)

    /**
     * 获取已加入的群组
     *
     * @param onSuccess onSuccess
     */
    fun getJoinedGroupList(onSuccess: (List<V2TIMGroupInfo>?) -> Unit, onError: () -> Unit)

    /**
     * 获取群资料
     *
     * @param groupIds 支持一次传入多个 groupID
     * @param onSuccess onSuccess
     */
    fun getGroupsInfo(groupIds: List<String>, onSuccess: (List<V2TIMGroupInfoResult>) -> Unit)

    /**
     * 获取群成员资料
     *
     * @param groupId groupId
     * @param userID userID
     * @param onSuccess onSuccess
     * @receiver
     */
    fun getGroupMembersInfo(
        groupId: String,
        userID: String,
        onSuccess: (List<V2TIMGroupMemberFullInfo>) -> Unit
    )
}