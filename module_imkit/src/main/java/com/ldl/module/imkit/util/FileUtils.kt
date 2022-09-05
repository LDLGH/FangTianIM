package com.ldl.module.imkit.util

import android.Manifest.permission
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.PermissionUtils
import java.io.File

/**
 * @author LDL
 * @date: 2022/8/26
 * @description:
 */
object FileUtils {

    @JvmStatic
    fun isFileExists(filePath: String): Boolean {
        if (PermissionUtils.isGranted(permission.READ_EXTERNAL_STORAGE)) {
            return FileUtils.isFileExists(filePath)
        } else {
            try {
                val f = File(filePath)
                if (!f.exists()) {
                    return false
                }
            } catch (e: Exception) {
                return false
            }
            return true
        }
    }

}