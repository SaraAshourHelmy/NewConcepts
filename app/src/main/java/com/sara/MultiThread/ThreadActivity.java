package com.sara.MultiThread;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.sara.newconcepts.R;


public class ThreadActivity extends AppCompatActivity {

    ImageView img_thread;
    TypedArray imgs;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        img_thread = (ImageView) findViewById(R.id.img_thread);
        imgs = getResources().obtainTypedArray(R.array.pics);

        // img_thread.setImageDrawable(imgs.getDrawable(0));
        SetupThread();
    }

    private void SetupThread() {

        Thread thread = new Thread(new MyThreadWithHandler());
        thread.start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e("msg : ", msg.arg1 + "");
                img_thread.setImageDrawable(imgs.getDrawable(msg.arg1));
            }
        };

    }

    class MyThread implements Runnable {

        int i = 0;


        @Override
        public void run() {
            for (i = 0; i < imgs.length(); i++) {

                Log.e(" i = ", i + "");
                ThreadActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (i < imgs.length())
                            img_thread.setImageDrawable(imgs.getDrawable(i));
                    }
                });
                try {
                    switch (i) {
                        case 0:
                            Thread.sleep(2000);
                            break;
                        case 1:
                            Thread.sleep(3000);
                            break;
                        case 2:
                            Thread.sleep(5000);
                            break;
                        default:
                            Thread.sleep(200);
                            break;

                    }// with milli second , 1 second=1000
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyThreadWithHandler implements Runnable {

        @Override
        public void run() {
            int i;

            for (i = 0; i < imgs.length(); i++) {

                Message message = new Message();
                message.arg1 = i;
                handler.sendMessage(message);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

