package com.overshade.scorekeeper;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class ScaleTransition {
    ScaleAnimation scale;
    ScaleAnimation unscale;
    View view;

    public ScaleTransition(View view, float fromW, float toW, float fromH, float toH, int duration) {
        this.view = view;
        scale = new ScaleAnimation(
                fromW, toW,
                fromH, toH,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        scale.setDuration(duration/2);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(unscale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        unscale = new ScaleAnimation(
                toW, fromW,
                toH, fromH,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        unscale.setDuration(duration/2);
    }

    public ScaleTransition start() {
        view.startAnimation(scale);
        return this;
    }

}
