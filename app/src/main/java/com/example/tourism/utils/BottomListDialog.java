package com.example.tourism.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.R;


/**
 * 创建日期：2017/12/6
 * 描述：底部List样式的弹窗
 */

public class BottomListDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout layout;
    private RecyclerView recyclerView;
    private TextView tvCancle;

    private Display display;
    private View view;

    public BottomListDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public View builder(int layoutID) {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                layoutID, null);

        // 获取自定义Dialog布局中的控件
       // layout =  view.findViewById(R.id.ll_bg);


        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.bottom_dialog);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);


        dialog.setContentView(view);
        // 调整dialog背景大小
        layout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 1.0), LinearLayout.LayoutParams.WRAP_CONTENT));

        return view;
    }
    public void setDismiss(){
        dialog.dismiss();
    }
    public boolean getIsShow(){
       return dialog.isShowing();
    }
    public void setShow(){
        dialog.show();
    }

    public void setDismissListen(DialogInterface.OnDismissListener listen){
        dialog.setOnDismissListener(listen);
    }

}
