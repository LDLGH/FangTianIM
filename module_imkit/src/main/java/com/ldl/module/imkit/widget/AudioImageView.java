package com.ldl.module.imkit.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;

/**
 * @author LDL
 * @date: 2021/11/11
 * @description:
 */
public class AudioImageView extends androidx.appcompat.widget.AppCompatImageView {

    private boolean isInit;

    private int initHeight;

    public AudioImageView(Context context) {
        super(context);
    }

    public AudioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AudioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 开始播放
     */
    public void startPlay() {
        //动画
        if (animator != null) {
            animator.start();
        }
    }

    /**
     * 结束播放
     */
    public void stopPlay() {
        //动画
        if (animator != null) {
            animator.pause();
            animator.end();
            animator.cancel();
        }
        clearAnimation();
        if (initHeight != 0) {
            getLayoutParams().height = initHeight;
            requestLayout();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (initHeight == 0){
            initHeight = getMeasuredHeight();
        }
        if (!isInit && initHeight != 0) {

            animator = ValueAnimator.ofInt(0, initHeight, 0);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setDuration(500);
            animator.addUpdateListener(valueAnimator -> {
                if (getLayoutParams() != null && valueAnimator != null) {
                    int value = (int) valueAnimator.getAnimatedValue();
                    getLayoutParams().height = value;
                    requestLayout();
                }
            });
            isInit = true;
        }

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
//        stopPlay();
        super.onDetachedFromWindow();
    }

    private ValueAnimator animator;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
