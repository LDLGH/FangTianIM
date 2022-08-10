package com.ldl.module.imcore.interfaces

/**
 * @author LDL
 * @date: 2021/11/1
 * @description:通用的IM回调函数接口
 */
interface IFangIMCallBack {

    fun onSuccess()

    fun onError(code: Int, error: String?)

    fun onProgress(progress: Int, status: String?){}
}