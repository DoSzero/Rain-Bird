package com.rabb.rainbird.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import com.rabb.rainbird.R
import com.rabb.rainbird.`interface`.CloseTask
import kotlin.math.abs

@SuppressLint("ViewConstructor")
class BirdView(c :Context, var closeTask: CloseTask):View(c) {

    private val otherEnemy = ArrayList<HashMap<String,Any>>()
    private var myPaint: Paint? = null
    private var myPosition = 0

    private var score = 0
    private var speed = 1
    private var time = 0

    var viewWidth = 0
    var viewHeight = 0

    init {
        myPaint = Paint()
    }

    @SuppressLint("DrawAllocation", "UseCompatLoadingForDrawables")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        viewWidth = this.measuredWidth
        viewHeight = this.measuredHeight

        // Add Enemy
        if (time % 700 < 10 +speed) {
            val map = HashMap<String,Any>()
            map["lane"] = (0..2).random()
            map["startTime"] = time
            otherEnemy.add(map)
        }

        time += 10 + speed
        val assetWidth = viewWidth / 5
        val assetHeight = assetWidth + 10
        myPaint!!.style = Paint.Style.FILL

        // Add Main Hero
        val assetsHero = resources.getDrawable(R.drawable.ic_bird,null)
        assetsHero.setBounds(
            myPosition * viewWidth / 3 + viewWidth / 15 + 25,
            viewHeight-2 - assetHeight,
            myPosition * viewWidth / 3 + viewWidth / 15 + assetWidth - 25 ,
            viewHeight - 2
        )
        assetsHero.draw(canvas!!)

        myPaint!!.color = Color.GREEN
        var highScore = 0

        for (i in otherEnemy.indices) {
            try {
                val carX = otherEnemy[i]["lane"] as Int * viewWidth / 3 + viewWidth / 15
                val carY = time - otherEnemy[i]["startTime"] as Int

                val assetsEnemy = resources.getDrawable(R.drawable.ic_seagull,null)
                assetsEnemy.setBounds(
                    carX + 25 ,
                    carY - assetHeight ,
                    carX + assetWidth - 25 ,
                    carY
                )
                assetsEnemy.draw(canvas)

                if (otherEnemy[i]["lane"] as Int  == myPosition){
                    if (carY > viewHeight - 2 - assetHeight
                        && carY < viewHeight - 2 ) {
                        closeTask.closeGame(score)
                    }
                }

                if (carY > viewHeight + assetHeight) {
                    otherEnemy.removeAt(i)
                    score++
                    speed = 1 + abs(score/8)
                    if (score > highScore){
                        highScore = score
                    }
                }

            } catch (e:Exception){
                e.printStackTrace()
            }
        }
        myPaint!!.color = Color.WHITE
        myPaint!!.textSize = 60f

        canvas.drawText("Score : $score",80f,80f,myPaint!!)
        canvas.drawText("Speed: $speed",380f,80f,myPaint!!)
        invalidate()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN ->{
                val x1 = event.x
                if (x1 < viewWidth/2){
                    if (myPosition> 0){
                        myPosition--
                    }
                }
                if (x1 > viewWidth / 2) {
                    if (myPosition<2){
                        myPosition++
                    }
                }
                invalidate()
            }
            MotionEvent.ACTION_UP->{}
        }
        return true
    }

}