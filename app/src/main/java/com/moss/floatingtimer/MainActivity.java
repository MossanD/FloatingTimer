package com.moss.floatingtimer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

public class MainActivity extends Activity {

    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPermissionAndStartService(true);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQUEST_CODE) {
            getPermissionAndStartService(false);
        }
    }

    /*
    * @Param isShowOverlayPermission オーバーレイアクセス権限画面を表示するか
    * */
    private void getPermissionAndStartService(boolean isShowOverlayPermission) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            startService();
        } else {
            if (Settings.canDrawOverlays(this)) {
                startService();
                return;
            }
            if (isShowOverlayPermission) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + this.getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }

    private void startService() {
        Intent intent = new Intent(this, FloatingTimerService.class);
        startService(intent);
        finishAndRemoveTask();
    }
}
