package com.angcyo.uicore.demo

import android.os.Bundle
import android.view.View
import com.angcyo.uicore.base.AppDslFragment
import com.angcyo.uicore.dslitem.AppJsoupHtmlItem
import com.angcyo.uicore.dslitem.AppJsoupInputItem

/**
 *
 * Email:angcyo@126.com
 * @author angcyo
 * @date 2020/03/14
 */
class JsoupDemo : AppDslFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderDslAdapter {
            AppJsoupInputItem()()
            AppJsoupHtmlItem()()
        }
    }
}