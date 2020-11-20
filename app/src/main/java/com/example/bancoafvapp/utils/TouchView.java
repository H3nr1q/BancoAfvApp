package com.example.bancoafvapp.utils;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

public class TouchView
{
    /**
     * Estende a area de toque da view.
     *
     * @param view   "View alvo".
     * @param dimens "dimensão a ser estendida".
     */
    public static void extend(final View view, final int dimens)
    {
        final View parent = (View)view.getParent();
        parent.post(new Runnable()
        {
            public void run()
            {
                final Rect rect = new Rect();
                view.getHitRect(rect);
                int dimension = PixelUtil.pxToDp(view.getContext(), dimens);
                rect.top -= dimension;
                rect.left -= dimension;
                rect.right += dimension;
                rect.bottom += dimension;
                parent.setTouchDelegate(new TouchDelegate(rect, view));
            }
        });
    }
}

