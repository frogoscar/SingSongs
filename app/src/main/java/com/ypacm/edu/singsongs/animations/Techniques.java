
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ypacm.edu.singsongs.animations;

import com.ypacm.edu.singsongs.animations.attention.BounceAnimator;
import com.ypacm.edu.singsongs.animations.attention.FlashAnimator;
import com.ypacm.edu.singsongs.animations.attention.PulseAnimator;
import com.ypacm.edu.singsongs.animations.attention.RubberBandAnimator;
import com.ypacm.edu.singsongs.animations.attention.ShakeAnimator;
import com.ypacm.edu.singsongs.animations.attention.StandUpAnimator;
import com.ypacm.edu.singsongs.animations.attention.SwingAnimator;
import com.ypacm.edu.singsongs.animations.attention.TadaAnimator;
import com.ypacm.edu.singsongs.animations.attention.WaveAnimator;
import com.ypacm.edu.singsongs.animations.attention.WobbleAnimator;
import com.ypacm.edu.singsongs.animations.bouncing_entrances.BounceInAnimator;
import com.ypacm.edu.singsongs.animations.bouncing_entrances.BounceInDownAnimator;
import com.ypacm.edu.singsongs.animations.bouncing_entrances.BounceInLeftAnimator;
import com.ypacm.edu.singsongs.animations.bouncing_entrances.BounceInRightAnimator;
import com.ypacm.edu.singsongs.animations.bouncing_entrances.BounceInUpAnimator;
import com.ypacm.edu.singsongs.animations.fading_entrances.FadeInAnimator;
import com.ypacm.edu.singsongs.animations.fading_entrances.FadeInDownAnimator;
import com.ypacm.edu.singsongs.animations.fading_entrances.FadeInLeftAnimator;
import com.ypacm.edu.singsongs.animations.fading_entrances.FadeInRightAnimator;
import com.ypacm.edu.singsongs.animations.fading_entrances.FadeInUpAnimator;
import com.ypacm.edu.singsongs.animations.fading_exits.FadeOutAnimator;
import com.ypacm.edu.singsongs.animations.fading_exits.FadeOutDownAnimator;
import com.ypacm.edu.singsongs.animations.fading_exits.FadeOutLeftAnimator;
import com.ypacm.edu.singsongs.animations.fading_exits.FadeOutRightAnimator;
import com.ypacm.edu.singsongs.animations.fading_exits.FadeOutUpAnimator;
import com.ypacm.edu.singsongs.animations.flippers.FlipInXAnimator;
import com.ypacm.edu.singsongs.animations.flippers.FlipInYAnimator;
import com.ypacm.edu.singsongs.animations.flippers.FlipOutXAnimator;
import com.ypacm.edu.singsongs.animations.flippers.FlipOutYAnimator;
import com.ypacm.edu.singsongs.animations.rotating_entrances.RotateInAnimator;
import com.ypacm.edu.singsongs.animations.rotating_entrances.RotateInDownLeftAnimator;
import com.ypacm.edu.singsongs.animations.rotating_entrances.RotateInDownRightAnimator;
import com.ypacm.edu.singsongs.animations.rotating_entrances.RotateInUpLeftAnimator;
import com.ypacm.edu.singsongs.animations.rotating_entrances.RotateInUpRightAnimator;
import com.ypacm.edu.singsongs.animations.rotating_exits.RotateOutAnimator;
import com.ypacm.edu.singsongs.animations.rotating_exits.RotateOutDownLeftAnimator;
import com.ypacm.edu.singsongs.animations.rotating_exits.RotateOutDownRightAnimator;
import com.ypacm.edu.singsongs.animations.rotating_exits.RotateOutUpLeftAnimator;
import com.ypacm.edu.singsongs.animations.rotating_exits.RotateOutUpRightAnimator;
import com.ypacm.edu.singsongs.animations.sliders.SlideInDownAnimator;
import com.ypacm.edu.singsongs.animations.sliders.SlideInLeftAnimator;
import com.ypacm.edu.singsongs.animations.sliders.SlideInRightAnimator;
import com.ypacm.edu.singsongs.animations.sliders.SlideInUpAnimator;
import com.ypacm.edu.singsongs.animations.sliders.SlideOutDownAnimator;
import com.ypacm.edu.singsongs.animations.sliders.SlideOutLeftAnimator;
import com.ypacm.edu.singsongs.animations.sliders.SlideOutRightAnimator;
import com.ypacm.edu.singsongs.animations.sliders.SlideOutUpAnimator;
import com.ypacm.edu.singsongs.animations.zooming_entrances.ZoomInAnimator;
import com.ypacm.edu.singsongs.animations.zooming_entrances.ZoomInDownAnimator;
import com.ypacm.edu.singsongs.animations.zooming_entrances.ZoomInLeftAnimator;
import com.ypacm.edu.singsongs.animations.zooming_entrances.ZoomInRightAnimator;
import com.ypacm.edu.singsongs.animations.zooming_entrances.ZoomInUpAnimator;
import com.ypacm.edu.singsongs.animations.zooming_exits.ZoomOutAnimator;
import com.ypacm.edu.singsongs.animations.zooming_exits.ZoomOutDownAnimator;
import com.ypacm.edu.singsongs.animations.zooming_exits.ZoomOutLeftAnimator;
import com.ypacm.edu.singsongs.animations.zooming_exits.ZoomOutRightAnimator;
import com.ypacm.edu.singsongs.animations.zooming_exits.ZoomOutUpAnimator;

public enum Techniques {


    Flash(FlashAnimator.class),
    Pulse(PulseAnimator.class),
    RubberBand(RubberBandAnimator.class),
    Shake(ShakeAnimator.class),
    Swing(SwingAnimator.class),
    Wobble(WobbleAnimator.class),
    Bounce(BounceAnimator.class),
    Tada(TadaAnimator.class),
    StandUp(StandUpAnimator.class),
    Wave(WaveAnimator.class),


    BounceIn(BounceInAnimator.class),
    BounceInDown(BounceInDownAnimator.class),
    BounceInLeft(BounceInLeftAnimator.class),
    BounceInRight(BounceInRightAnimator.class),
    BounceInUp(BounceInUpAnimator.class),

    FadeIn(FadeInAnimator.class),
    FadeInUp(FadeInUpAnimator.class),
    FadeInDown(FadeInDownAnimator.class),
    FadeInLeft(FadeInLeftAnimator.class),
    FadeInRight(FadeInRightAnimator.class),

    FadeOut(FadeOutAnimator.class),
    FadeOutDown(FadeOutDownAnimator.class),
    FadeOutLeft(FadeOutLeftAnimator.class),
    FadeOutRight(FadeOutRightAnimator.class),
    FadeOutUp(FadeOutUpAnimator.class),

    FlipInX(FlipInXAnimator.class),
    FlipOutX(FlipOutXAnimator.class),
    FlipInY(FlipInYAnimator.class),
    FlipOutY(FlipOutYAnimator.class),
    RotateIn(RotateInAnimator.class),
    RotateInDownLeft(RotateInDownLeftAnimator.class),
    RotateInDownRight(RotateInDownRightAnimator.class),
    RotateInUpLeft(RotateInUpLeftAnimator.class),
    RotateInUpRight(RotateInUpRightAnimator.class),

    RotateOut(RotateOutAnimator.class),
    RotateOutDownLeft(RotateOutDownLeftAnimator.class),
    RotateOutDownRight(RotateOutDownRightAnimator.class),
    RotateOutUpLeft(RotateOutUpLeftAnimator.class),
    RotateOutUpRight(RotateOutUpRightAnimator.class),

    SlideInLeft(SlideInLeftAnimator.class),
    SlideInRight(SlideInRightAnimator.class),
    SlideInUp(SlideInUpAnimator.class),
    SlideInDown(SlideInDownAnimator.class),

    SlideOutLeft(SlideOutLeftAnimator.class),
    SlideOutRight(SlideOutRightAnimator.class),
    SlideOutUp(SlideOutUpAnimator.class),
    SlideOutDown(SlideOutDownAnimator.class),

    ZoomIn(ZoomInAnimator.class),
    ZoomInDown(ZoomInDownAnimator.class),
    ZoomInLeft(ZoomInLeftAnimator.class),
    ZoomInRight(ZoomInRightAnimator.class),
    ZoomInUp(ZoomInUpAnimator.class),

    ZoomOut(ZoomOutAnimator.class),
    ZoomOutDown(ZoomOutDownAnimator.class),
    ZoomOutLeft(ZoomOutLeftAnimator.class),
    ZoomOutRight(ZoomOutRightAnimator.class),
    ZoomOutUp(ZoomOutUpAnimator.class);


    private Class animatorClazz;

    private Techniques(Class clazz) {
        animatorClazz = clazz;
    }

    public com.ypacm.edu.singsongs.animations.BaseViewAnimator getAnimator() {
        try {
            return (com.ypacm.edu.singsongs.animations.BaseViewAnimator) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
