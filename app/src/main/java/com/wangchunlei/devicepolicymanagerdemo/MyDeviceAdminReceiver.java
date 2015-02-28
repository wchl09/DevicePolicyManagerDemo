package com.wangchunlei.devicepolicymanagerdemo;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * <p>在“设置”——“安全”中用户想取消程序的设备管理器权限也会触发，这个广播接收器</p>
 * Created by Ray on 2015/2/28.
 */
public class MyDeviceAdminReceiver extends DeviceAdminReceiver {
	private final String tag = "Ray";

	/**
	 * 接收到广播
	 *
	 * @param context
	 * @param intent
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		Log.i(tag, "onReceive");
	}

	/**
	 * 同意
	 *
	 * @param context
	 * @param intent
	 */
	public void onEnabled(Context context, Intent intent) {
		Log.i(tag, "onEnabled");
	}

	/**
	 * 取消
	 *
	 * @param context
	 * @param intent
	 */
	public void onDisabled(Context context, Intent intent) {
		Log.i(tag, "onDisabled");
	}

	;

	/**
	 * 用户打开了取消程序设备管理器权限的页面
	 *
	 * @param context
	 * @param intent
	 * @return 用户点击“取消设备权限时”显示的Dialog中的话术
	 */
	@Override
	public CharSequence onDisableRequested(Context context, Intent intent) {
		retuen(context, intent); // 特殊方式延迟阻止取消设备管理
		Log.i(tag, "onDisableRequested");
		return "你真的要卸载了吗";
	}

	/**
	 * 当用户触发取消程序的设备管理权限页面时，强制弹出“设置”页面，让用户无法点击
	 *
	 * @param context
	 * @param intent
	 */
	private void retuen(Context context, Intent intent) {
		Intent intent1 = context.getPackageManager().getLaunchIntentForPackage("com.android.settings");
		intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent1);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
