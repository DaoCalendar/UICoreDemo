package com.angcyo.uicore.demo

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.angcyo.base.dslAHelper
import com.angcyo.component.hawkInstallAndRestore
import com.angcyo.dsladapter.renderItem
import com.angcyo.tbs.open
import com.angcyo.uicore.base.AppDslFragment
import com.angcyo.widget.base.string

/**
 *
 * Email:angcyo@126.com
 * @author angcyo
 * @date 2020/03/01
 */
class TbsWebDemo : AppDslFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderDslAdapter {
            renderItem {
                itemLayoutId = R.layout.demo_tbs_web

                itemBindOverride = { itemHolder, _, _, _ ->
                    itemHolder.click(R.id.open_url) {
                        dslAHelper {
                            open {
                                uri = Uri.parse(itemHolder.tv(R.id.edit_text).string())
                            }
                        }
                    }

                    itemHolder.hawkInstallAndRestore("tbs_")
                }
            }
        }
    }
}