package com.angcyo.uicore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.angcyo.baidu.trace.DslBaiduTrace
import com.angcyo.base.dslFHelper
import com.angcyo.core.activity.BasePermissionsActivity
import com.angcyo.library.component.DslShortcut
import com.angcyo.library.component.dslShortcut
import com.angcyo.library.utils.Device
import com.angcyo.library.utils.RUtils
import com.angcyo.library.utils.checkApkExist
import com.angcyo.uicore.demo.R

/**
 *
 * Email:angcyo@126.com
 * @author angcyo
 * @date 2019/12/17
 * Copyright (c) 2019 ShenZhen O&M Cloud Co., Ltd. All rights reserved.
 */
class MainActivity : BasePermissionsActivity() {

    init {
//        permissions.add(
//            PermissionBean(
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                R.drawable.permission_sd,
//                "SD卡存储权限"
//            )
//        )
//        permissions.add(
//            PermissionBean(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                R.drawable.permission_gps,
//                "GPS权限"
//            )
//        )
//        permissions.add(
//            PermissionBean(
//                Manifest.permission.CAMERA,
//                R.drawable.permission_camera,
//                "摄像头权限"
//            )
//        )
//        permissions.add(
//            PermissionBean(
//                Manifest.permission.RECORD_AUDIO,
//                R.drawable.permission_record,
//                "麦克风权限"
//            )
//        )

        doubleBackTime = 1_000
    }

    override fun onCreateAfter(savedInstanceState: Bundle?) {
        super.onCreateAfter(savedInstanceState)

        dslShortcut(this) {
            //清空之前, 防止max崩溃
            shortcutAction = DslShortcut.ACTION_TYPE_REMOVE_ALL_SHORTCUT
        }

        dslShortcut(this) {
            shortcutAction = DslShortcut.ACTION_TYPE_DYNAMIC_SHORTCUT
            shortcutLabel = "CSDN博客"
            shortcutIconId = R.drawable.ic_logo_small
            shortcutIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://angcyo.blog.csdn.net"))
        }

//        dslShortcut(this) {
//            shortcutAction = DslShortcut.ACTION_TYPE_DYNAMIC_SHORTCUT
//            shortcutLabel = "官网"
//            shortcutIconId = R.drawable.ic_logo_small
//            shortcutIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.angcyo.com"))
//        }

        dslShortcut(this) {
            shortcutAction = DslShortcut.ACTION_TYPE_DYNAMIC_SHORTCUT
            shortcutLabel = "扫一扫"
            shortcutIconId = R.drawable.app_ic_scan_frame
            shortcutIntent = Intent(this@MainActivity, AppScanActivity::class.java)
        }

        if (checkApkExist("com.tencent.mobileqq")) {
            dslShortcut(this) {
                shortcutAction = DslShortcut.ACTION_TYPE_DYNAMIC_SHORTCUT
                shortcutLabel = "QQ咨询"
                shortcutIconId = R.drawable.ic_logo_small
                shortcutIntent = RUtils.chatQQIntent(this@MainActivity)
            }

            dslShortcut(this) {
                shortcutAction = DslShortcut.ACTION_TYPE_DYNAMIC_SHORTCUT
                shortcutLabel = "QQ入群学习"
                shortcutIconId = R.drawable.ic_logo_small
                shortcutIntent = RUtils.joinQQGroupIntent(this@MainActivity)
            }
        } else {
            dslShortcut(this) {
                shortcutAction = DslShortcut.ACTION_TYPE_DYNAMIC_SHORTCUT
                shortcutLabel = "Github"
                shortcutIconId = R.drawable.ic_logo_small
                shortcutIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/angcyo"))
            }
            dslShortcut(this) {
                shortcutAction = DslShortcut.ACTION_TYPE_DYNAMIC_SHORTCUT
                shortcutLabel = "掘金"
                shortcutIconId = R.drawable.ic_logo_small
                shortcutIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://juejin.im/user/576a151b2e958a00699c11f0")
                )
            }
        }

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onPermissionGranted() {
        super.onPermissionGranted()
        dslFHelper {
            removeAll()
            restore(MainFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        //densityAdapter(750, 2f)
        //densityRestore()
        //densityAdapterFrom(2183)
    }

    val dslBaiduTrace = DslBaiduTrace().apply {
        serviceId = 207762
        entityName = Device.androidId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dslBaiduTrace.startTrace(applicationContext)
    }

    override fun onDestroy() {
        super.onDestroy()
        dslBaiduTrace.stopTrace()
    }
}
