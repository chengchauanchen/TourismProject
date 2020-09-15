package com.example.tourism.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil
{
	public static void showLongToast(Context context, String text)
	{
		if(context != null)
			Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
	public static void showShortToast(Context context, String text)
	{
		if(context != null)
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context context,int res) {
		if(context != null) {
			Toast.makeText(context,res,Toast.LENGTH_SHORT).show();
		}
	}
	public static void showToast(Context context,String res) {
		if(context != null) {
			Toast.makeText(context,res,Toast.LENGTH_SHORT).show();
		}
	}

	public static void showLongToast(Context context,int res) {
		if(context != null) {
			Toast.makeText(context,res,Toast.LENGTH_LONG).show();
		}
	}
}
