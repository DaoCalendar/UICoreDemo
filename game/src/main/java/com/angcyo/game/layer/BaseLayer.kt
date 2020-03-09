package com.angcyo.game.layer

import android.graphics.Canvas
import android.graphics.RectF
import com.angcyo.game.spirit.BaseSpirit
import com.angcyo.game.spirit.BaseSpirit.Companion.SPIRIT_STATUS_PAUSE_DRAW
import com.angcyo.game.spirit.BaseSpirit.Companion.SPIRIT_STATUS_PAUSE_UPDATE
import com.angcyo.library.ex.have
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 游戏渲染的层单位, 一层一层渲染
 * Email:angcyo@126.com
 * @author angcyo
 * @date 2020/03/09
 * Copyright (c) 2019 ShenZhen O&M Cloud Co., Ltd. All rights reserved.
 */

abstract class BaseLayer {

    companion object {
        //正常
        const val LAYER_STATUS_NORMAL = 0

        //暂停绘制
        const val LAYER_STATUS_PAUSE_DRAW = 0x1

        //暂停更新数据
        const val LAYER_STATUS_PAUSE_UPDATE = 0x2
    }

    /**层的状态*/
    var layerStatus: Int = LAYER_STATUS_NORMAL

    /**精灵列表*/
    val spiritList = CopyOnWriteArrayList<BaseSpirit>()

    /**[layer]在游戏引擎中的矩形坐标*/
    val layerRectF = RectF()

    fun addSpirit(spirit: BaseSpirit) {
        spiritList.add(spirit)
        spirit.attachToLayer(this)
    }

    fun removeSpirit(spirit: BaseSpirit) {
        spiritList.remove(spirit)
        spirit.detachToLayer(this)
    }

    fun clearSpirit() {
        val iterator = spiritList.iterator()
        while (iterator.hasNext()) {
            iterator.next()?.also { spirit ->
                spirit.detachToLayer(this)
            }
        }
        spiritList.clear()
    }

    /**尺寸更新*/
    open fun onLayerUpdate(width: Float, height: Float) {
        layerRectF.set(0f, 0f, width, height)
    }

    open fun draw(canvas: Canvas) {
        val iterator = spiritList.iterator()
        while (iterator.hasNext()) {
            iterator.next()?.also { spirit ->
                if (!spirit.spiritStatus.have(SPIRIT_STATUS_PAUSE_DRAW)) {
                    spirit.draw(canvas)
                }
            }
        }
    }

    open fun update() {
        val iterator = spiritList.iterator()
        while (iterator.hasNext()) {
            iterator.next()?.also { spirit ->
                if (!spirit.spiritStatus.have(SPIRIT_STATUS_PAUSE_UPDATE)) {
                    spirit.update()
                }
            }
        }
    }
}