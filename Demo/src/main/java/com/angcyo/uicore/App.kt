package com.angcyo.uicore

import com.angcyo.core.CoreApplication
import com.angcyo.download.DslDownload

/**
 *
 * Email:angcyo@126.com
 * @author angcyo
 * @date 2019/12/23
 * Copyright (c) 2019 ShenZhen O&M Cloud Co., Ltd. All rights reserved.
 */
class App : CoreApplication() {
    override fun onCreate() {
        super.onCreate()

        DslDownload.init(this)
    }
}