package com.ldl.module.imkit.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.blankj.utilcode.util.AppUtils

/**
 * @author LDL
 * @date: 2022/8/30
 * @description:
 */
object BadgeUtils {

    @SuppressLint("WrongConstant")
    @JvmStatic
    fun setBadgeNum(context: Context, number: Int, className: String) {
        if (BrandUtils.isBrandHuawei()) {
            try {
                val bundle = Bundle().apply {
                    putString("package", AppUtils.getAppPackageName())
                    putString("class", className)
                    putInt("badgenumber", number)
                }
                context.contentResolver.call(
                    Uri.parse("content://com.huawei.android.launcher.settings/badge/"),
                    "change_badge",
                    null,
                    bundle
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (BrandUtils.isBrandVivo()) {
            try {
                val intent = Intent().apply {
                    action = "launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM"
                    putExtra("packageName", AppUtils.getAppPackageName())
                    putExtra("class", className)
                    putExtra("notificationNum", number)
                    addFlags(0x01000000)
                }
                context.sendBroadcast(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}