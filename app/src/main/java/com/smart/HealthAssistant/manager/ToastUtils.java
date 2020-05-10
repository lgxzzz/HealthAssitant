package com.smart.HealthAssistant.manager;

import android.content.Context;
import android.widget.Toast;

import com.aliyun.alink.linksdk.tools.ThreadTools;
import com.smart.HealthAssistant.ali.DemoApplication;

public class ToastUtils {

    public static void showToast(String message) {
        showToast(message, DemoApplication.getAppContext());
    }

    private static void showToast(final String message, final Context context) {
        ThreadTools.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
