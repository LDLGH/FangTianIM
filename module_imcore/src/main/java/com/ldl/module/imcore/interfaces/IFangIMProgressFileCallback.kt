package com.ldl.module.imcore.interfaces

import java.io.File

/**
 * @author LDL
 * @date: 2021/11/5
 * @description: 文件下载进度回调
 */
interface IFangIMProgressFileCallback {

    fun onSuccess(file: File)

    fun onProgress(progress: Int)

    fun onFailure(errorCode: Int, message: String)
}