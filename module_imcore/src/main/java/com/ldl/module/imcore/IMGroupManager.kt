package com.ldl.module.imcore

import com.blankj.utilcode.util.LogUtils
import com.ldl.module.imcore.interfaces.IFangIMCallBack
import com.ldl.module.imcore.interfaces.IFangIMGroupManager
import com.tencent.imsdk.v2.*

/**
 * @author LDL
 * @date: 2022/8/9
 * @description:
 */
object IMGroupManager : IFangIMGroupManager {

    /**
     * 禁言所有成员
     *
     * @param groupId 群组id
     * @param callBack callBack
     */
    override fun muteAllMembers(groupId: String, callBack: IFangIMCallBack?) {
        val v2TIMGroupInfo = V2TIMGroupInfo().apply {
            groupID = groupId
            isAllMuted = true
        }
        V2TIMManager.getGroupManager().setGroupInfo(v2TIMGroupInfo, object : V2TIMCallback {
            override fun onSuccess() {
                callBack?.onSuccess()
            }

            override fun onError(p0: Int, p1: String?) {
                callBack?.onError(p0, p1)
            }

        })
    }

    /**
     * 解除所有成员禁言
     *
     * @param groupId 群组id
     * @param callBack callBack
     */
    override fun unMuteAllMembers(groupId: String, callBack: IFangIMCallBack?) {
        val v2TIMGroupInfo = V2TIMGroupInfo().apply {
            groupID = groupId
            isAllMuted = false
        }
        V2TIMManager.getGroupManager().setGroupInfo(v2TIMGroupInfo, object : V2TIMCallback {
            override fun onSuccess() {
                callBack?.onSuccess()
            }

            override fun onError(p0: Int, p1: String?) {
                callBack?.onError(p0, p1)
            }

        })
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
        V2TIMManager.getGroupManager()
            .getJoinedGroupList(object : V2TIMValueCallback<List<V2TIMGroupInfo>> {
                override fun onSuccess(p0: List<V2TIMGroupInfo>?) {
                    onSuccess.invoke(p0)
                }

                override fun onError(p0: Int, p1: String?) {
                    LogUtils.e(p0, p1)
                    onError.invoke()
                }

            })
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
        V2TIMManager.getGroupManager()
            .getGroupsInfo(groupIds, object : V2TIMValueCallback<List<V2TIMGroupInfoResult>> {
                override fun onSuccess(p0: List<V2TIMGroupInfoResult>?) {
                    p0?.let {
                        onSuccess.invoke(it)
                    }
                }

                override fun onError(p0: Int, p1: String?) {
                    LogUtils.e(p0, p1)
                }
            })
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
        V2TIMManager.getGroupManager().getGroupMembersInfo(
            groupId,
            arrayListOf(userID),
            object : V2TIMValueCallback<List<V2TIMGroupMemberFullInfo>> {
                override fun onSuccess(p0: List<V2TIMGroupMemberFullInfo>?) {
                    p0?.let {
                        onSuccess.invoke(p0)
                    }
                }

                override fun onError(p0: Int, p1: String?) {
                }

            })
    }
}