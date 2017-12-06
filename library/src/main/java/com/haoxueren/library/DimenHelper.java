package com.haoxueren.library;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DimenHelper
{
	private DisplayMetrics metrics;

	public DimenHelper()
	{
		metrics = Resources.getSystem().getDisplayMetrics();
	}

	/** px2sp */
	public int sp2px(int sp)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
	}

	 /** px2dp */
	 public int dp2px(int dp)
	 {
	 return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
	 }

	/** 获取屏幕的宽度； */
	public int getScreenWidth()
	{
		return metrics.widthPixels;
	}

	/** 获取屏幕的高度； */
	public int getScreenHeight()
	{
		return metrics.heightPixels;
	}

	/** 获取屏幕的密度； */
	public float getDensity()
	{
		return metrics.density;
	}

	/*********************** 【以下是静态方法区】 ***********************/

	/** sp2px */
	public static int sp2px(float sp)
	{
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		return (int) (metrics.scaledDensity * sp + 0.5f);
	}
	
	/** dp2px */
	public static int dp2px(float dp)
	{
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		return (int) (metrics.density * dp + 0.5f);
	}

}
