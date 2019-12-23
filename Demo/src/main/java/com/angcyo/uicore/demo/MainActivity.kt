package com.angcyo.uicore.demo

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.angcyo.activity.BaseAppCompatActivity
import com.angcyo.base.dslFHelper
import com.angcyo.base.log
import com.angcyo.uicore.demo.fragment.Fragment1
import com.angcyo.uicore.demo.fragment.Fragment2
import com.angcyo.uicore.demo.fragment.Fragment3
import com.angcyo.widget.progress.DslProgressBar

/**
 *
 * Email:angcyo@126.com
 * @author angcyo
 * @date 2019/12/17
 * Copyright (c) 2019 ShenZhen O&M Cloud Co., Ltd. All rights reserved.
 */
class MainActivity : BaseAppCompatActivity() {

    override fun getActivityLayoutId() = R.layout.activity_main

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//    override fun onPostCreate(savedInstanceState: Bundle?) {
//        super.onPostCreate(savedInstanceState)
//
//        window.decorView.postDelayed({
//            TaskService.start(applicationContext, 2000)
//        }, 5000)
//    }

    override fun onCreateAfter(savedInstanceState: Bundle?) {
        super.onCreateAfter(savedInstanceState)

        val bar: DslProgressBar

        dslFHelper {
            show(Fragment1(), Fragment2(), Fragment3())
        }

        baseDslViewHolder.post {
            supportFragmentManager.log()
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
//        dslFHelper {
//            //removeAll()
//            //remove(Fragment3().getFragmentTag())
//            //restore(Fragment2().getFragmentTag())
//            //_test = fm.findFragmentByTag(Fragment3().getFragmentTag())
//            //show(Fragment1())
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            detach(supportFragmentManager.findFragmentByTag(Fragment2().getFragmentTag())!!)
//            commit()
//        }
//
//        baseDslViewHolder.postDelay(1000) {
//            supportFragmentManager.log()
//        }

        dslFHelper {
            removeLast()
        }
        baseDslViewHolder.post {
            supportFragmentManager.log()
        }
    }
}
