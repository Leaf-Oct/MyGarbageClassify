package com.leafoct.myrubbishclassify;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        boolean status = NetworkUtil.getConnectivityStatusString(context);
        if (!status) {
            exit(context);
        }
    }

    private void exit(final Context context) {
        AlertDialog.Builder no_net = new AlertDialog.Builder(context);
        no_net.setTitle("啊哦");
        no_net.setCancelable(false);
        no_net.setMessage("是不是突然断网了");
        no_net.setPositiveButton("Remake", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();
                Intent i = new Intent(context, Login.class);
                context.startActivity(i);
            }
        });
        no_net.show();
    }
}
