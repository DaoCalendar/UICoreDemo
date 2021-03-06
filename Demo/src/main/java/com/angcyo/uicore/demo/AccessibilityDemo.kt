package com.angcyo.uicore.demo

import android.Manifest
import android.os.Bundle
import com.angcyo.core.component.accessibility.*
import com.angcyo.dsladapter.renderItem
import com.angcyo.library.ex.checkPermissions
import com.angcyo.uicore.base.AppDslFragment
import com.angcyo.uicore.demo.accessibility.AccessibilityWindow

/**
 * 无障碍服务测试demo
 * Email:angcyo@126.com
 * @author angcyo
 * @date 2020/06/23
 * Copyright (c) 2020 ShenZhen Wayto Ltd. All rights reserved.
 */
class AccessibilityDemo : AppDslFragment() {

    companion object {
        const val DY_PACKAGE_NAME = "com.ss.android.ugc.aweme"
        const val KS_PACKAGE_NAME = "com.smile.gifmaker"
    }

    override fun initBaseView(savedInstanceState: Bundle?) {
        super.initBaseView(savedInstanceState)

        //GestureInterceptor().install()

        StartAppAccessibilityInterceptor().install()
//        LogAccessibilityInterceptor().apply {
//            enable = false
//            install()
//        }

        //记录所有窗口变化, 以及窗口上所有节点信息
        LogWindowAccessibilityInterceptor().apply {
            filterPackageNameList.add(DY_PACKAGE_NAME)
            filterPackageNameList.add(KS_PACKAGE_NAME)
            logAllWindow = !BuildConfig.DEBUG
            install()
        }
    }

    override fun onFragmentShow(bundle: Bundle?) {
        super.onFragmentShow(bundle)

        if (AccessibilityPermission.haveAccessibilityService(fContext())) {
            AccessibilityWindow.show("准备", 0)
        }

        renderDslAdapter(true) {
            renderItem {
                itemLayoutId = R.layout.item_accessibility_demo

                itemBindOverride = { itemHolder, _, _, _ ->
                    itemHolder.cb(R.id.overlays_enable)?.isChecked =
                        AccessibilityPermission.haveDrawOverlays(fContext())
                    itemHolder.cb(R.id.accessibility_enable)?.isChecked =
                        AccessibilityPermission.haveAccessibilityService(fContext())

                    itemHolder.click(R.id.overlays_button) {
                        AccessibilityPermission.openOverlaysActivity(fContext())
                    }
                    itemHolder.click(R.id.accessibility_button) {
                        AccessibilityPermission.openAccessibilityActivity(fContext())
                    }
                    itemHolder.click(R.id.check_button) {
                        itemHolder.tv(R.id.lib_text_view)?.text =
                            "${AccessibilityPermission.check(fContext())}"
                    }

//                    //抖音
//                    itemHolder.click(R.id.dy_login_button) {
//                        douYinUserInterceptor.restart()
//                        douYinUserInterceptor.run()
//
//                        fContext().openApp(DY_PACKAGE_NAME)
//                    }
//                    itemHolder.click(R.id.dy_button) {
//                        itemHolder.tv(R.id.dy_edit)?.string()?.copy()
//
//                        douYinInterceptor.restart()
//                        douYinInterceptor.install()
//
//                        fContext().openApp(DY_PACKAGE_NAME)
//                    }
//                    itemHolder.click(R.id.dy_button2) {
//                        itemHolder.tv(R.id.dy_edit2)?.string()?.copy()
//
//                        douYinInterceptor.restart()
//                        douYinInterceptor.install()
//
//                        fContext().openApp(DY_PACKAGE_NAME)
//                    }
//
//                    //快手
//                    itemHolder.click(R.id.ks_login_button) {
//                        ksUserInterceptor.install()
//                        fContext().openApp(KS_PACKAGE_NAME)
//                    }
//
//                    itemHolder.click(R.id.ks_button) {
//                        itemHolder.tv(R.id.ks_edit)?.string()?.copy()
//
//                        ksLikeInterceptor.install()
//                        fContext().openApp(KS_PACKAGE_NAME)
//                    }
//
//                    itemHolder.click(R.id.ks_button2) {
//                        itemHolder.tv(R.id.ks_edit2)?.string()?.copy()
//
//                        ksLikeInterceptor.install()
//                        fContext().openApp(KS_PACKAGE_NAME)
//                    }
//
//                    //tip
//                    itemHolder.tv(R.id.lib_text_view)?.text = span {
//                        append("${DYLikeInterceptor.dyUserName}/${KSLikeInterceptor.ksUserName}")
//                        appendln()
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            append("GestureDuration:${GestureDescription.getMaxGestureDuration()}")
//                            appendln()
//                            append("MaxStrokeCount:${GestureDescription.getMaxStrokeCount()}") //最大支持多少个手指
//                            appendln()
//                        }
//                        append(DY_PACKAGE_NAME.appBean().string("未安装[抖音]"))
//                        appendln()
//                        append(KS_PACKAGE_NAME.appBean().string("未安装[快手]"))
//                    }

                    //系统权限对话框测试
                    itemHolder.click(R.id.lib_text_view) {
                        fContext().checkPermissions(
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.SEND_SMS,
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_SECURE_SETTINGS
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RAccessibilityService.clearInterceptor()
    }
}