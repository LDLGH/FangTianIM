package com.ldl.module.imkit.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ldl.module.imkit.R;

import java.io.File;

public class RecordButton extends AppCompatButton {


    public RecordButton(Context context) {
        super(context);
        init();
    }

    public RecordButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private String mFile = getContext().getFilesDir() + "/" + "voice_" + System.currentTimeMillis() + ".mp3";


    private OnFinishedRecordListener finishedListener;
    /**
     * 最短录音时间
     **/
    private int MIN_INTERVAL_TIME = 1000;
    /**
     * 最长录音时间
     **/
    private int MAX_INTERVAL_TIME = 1000 * 60;


    private static View view;

    private TextView mStateTV;

    private ImageView mStateIV;

    private MediaRecorder mRecorder;
    private ObtainDecibelThread mThread;
    private Handler volumeHandler;
    private Handler mHandler;

    private float y;


    public void setOnFinishedRecordListener(OnFinishedRecordListener listener) {
        finishedListener = listener;
    }


    private static long startTime;
    private Dialog recordDialog;
    private static int[] res = {R.drawable.ic_volume_0, R.drawable.ic_volume_1, R.drawable.ic_volume_2,
            R.drawable.ic_volume_3, R.drawable.ic_volume_4, R.drawable.ic_volume_5, R.drawable.ic_volume_6
            , R.drawable.ic_volume_7, R.drawable.ic_volume_8};


    @SuppressLint("HandlerLeak")
    private void init() {
        volumeHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == -100) {
                    stopRecording();
                    recordDialog.dismiss();
                } else if (msg.what != -1) {
                    mStateIV.setImageResource(res[msg.what]);
                }
            }
        };
        mHandler = new Handler();
        setText(StringUtils.getString(R.string.hold_to_speak));
    }

    private AnimationDrawable anim;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        y = event.getY();
        if (mStateTV != null && mStateIV != null && y < 0) {
            mStateTV.setText("松开手指,取消发送");
            mStateIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_cancel));
        } else if (mStateTV != null) {
            mStateTV.setText("手指上滑,取消发送");
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                setText("松开发送");
                initDialogAndStartRecord();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                this.setText("按住录音");
                if (y >= 0 && (System.currentTimeMillis() - startTime <= MAX_INTERVAL_TIME)) {
                    LogUtils.d("结束录音:");
                    finishRecord();

                } else if (y < 0) {  //当手指向上滑，会cancel
                    cancelRecord();
                }
                break;
        }

        return true;
    }

    /**
     * 初始化录音对话框 并 开始录音
     */
    private void initDialogAndStartRecord() {

        recordDialog = new Dialog(getContext(), R.style.like_toast_dialog_style);
        // view = new ImageView(getContext());
        view = View.inflate(getContext(), R.layout.dialog_record, null);
        mStateIV = (ImageView) view.findViewById(R.id.rc_audio_state_image);
        mStateTV = (TextView) view.findViewById(R.id.rc_audio_state_text);
        mStateIV.setImageDrawable(getResources().getDrawable(R.drawable.anim_mic));
        anim = (AnimationDrawable) mStateIV.getDrawable();
        anim.start();
        mStateIV.setVisibility(View.VISIBLE);
        //mStateIV.setImageResource(R.drawable.ic_volume_1);
        mStateTV.setVisibility(View.VISIBLE);
        mStateTV.setText("手指上滑,取消发送");
        recordDialog.setContentView(view, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        recordDialog.setOnDismissListener(onDismiss);
        WindowManager.LayoutParams lp = recordDialog.getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        startRecording();
        recordDialog.show();
    }

    /**
     * 放开手指，结束录音处理
     */
    private void finishRecord() {
        long intervalTime = System.currentTimeMillis() - startTime;
        if (intervalTime < MIN_INTERVAL_TIME) {
            LogUtils.d("录音时间太短");
            volumeHandler.sendEmptyMessageDelayed(-100, 500);
            //view.setBackgroundResource(R.drawable.ic_voice_cancel);
            mStateIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_wraning));
            mStateTV.setText("录音时间太短");
            anim.stop();
            FileUtils.delete(mFile);
            return;
        } else {
            stopRecording();
            recordDialog.dismiss();
        }
        LogUtils.d("录音完成的路径:" + mFile);
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(mFile);
            mediaPlayer.prepare();
            mediaPlayer.getDuration();
            LogUtils.d("获取到的时长:" + mediaPlayer.getDuration() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (finishedListener != null) {
            int duration = mediaPlayer.getDuration() / 1000;
            if (duration < 1) {
                duration = 1;
            }
            finishedListener.onFinishedRecord(mFile, duration);
        }

    }

    /**
     * 取消录音对话框和停止录音
     */
    public void cancelRecord() {
        stopRecording();
        recordDialog.dismiss();
        FileUtils.delete(mFile);
    }

    /**
     * 执行录音操作
     */
    private void startRecording() {
        mFile = getContext().getFilesDir() + "/" + "voice_" + System.currentTimeMillis() + ".mp3";
        if (mRecorder != null) {
            mRecorder.reset();
        } else {
            mRecorder = new MediaRecorder();
        }
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // .m4a 格式可以在 iOS 上直接播放
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);
        File file = new File(mFile);
        LogUtils.d("创建文件的路径:" + mFile);
        LogUtils.d("文件创建成功:" + file.exists());
        mRecorder.setOutputFile(mFile);
        try {
            mRecorder.prepare();
            mRecorder.setOnErrorListener((mr, what, extra) -> {
                LogUtils.e("recorder prepare failed!");
            });
            mRecorder.start();
            startTime = System.currentTimeMillis();
            // 最大录制时间之后需要停止录制
            mHandler.removeCallbacksAndMessages(null);
            mHandler.postDelayed(() -> {
                stopRecording();
                ToastUtils.showShort(R.string.record_limit_tips);
            }, 60 * 1000);
        } catch (Exception e) {
            LogUtils.d("preparestart异常,重新开始录音:" + e.toString());
            e.printStackTrace();
            stopRecording();
        }
    }


    private void stopRecording() {
        mHandler.removeCallbacksAndMessages(null);
        if (mThread != null) {
            mThread.exit();
            mThread = null;
        }
        if (mRecorder != null) {
            try {
                mRecorder.stop();//停止时没有prepare，就会报stop failed
                mRecorder.reset();
                mRecorder.release();
                mRecorder = null;
            } catch (RuntimeException pE) {
                pE.printStackTrace();
            } finally {
                if (recordDialog.isShowing()) {
                    recordDialog.dismiss();
                }
            }
        }

    }

    private class ObtainDecibelThread extends Thread {

        private volatile boolean running = true;

        public void exit() {
            running = false;
        }

        @Override
        public void run() {
            LogUtils.d("检测到的分贝001:");
            while (running) {
                if (mRecorder == null || !running) {
                    break;
                }
                // int x = recorder.getMaxAmplitude(); //振幅
                int db = mRecorder.getMaxAmplitude() / 600;
                LogUtils.d("检测到的分贝002:" + mRecorder);
                if (db != 0 && y >= 0) {


                    int f = (int) (db / 5);
                    if (f == 0)
                        volumeHandler.sendEmptyMessage(0);
                    else if (f == 1)
                        volumeHandler.sendEmptyMessage(1);
                    else if (f == 2)
                        volumeHandler.sendEmptyMessage(2);
                    else if (f == 3)
                        volumeHandler.sendEmptyMessage(3);
                    else if (f == 4)
                        volumeHandler.sendEmptyMessage(4);
                    else if (f == 5)
                        volumeHandler.sendEmptyMessage(5);
                    else if (f == 6)
                        volumeHandler.sendEmptyMessage(6);
                    else
                        volumeHandler.sendEmptyMessage(7);
                }

                volumeHandler.sendEmptyMessage(-1);
                if (System.currentTimeMillis() - startTime > 20000) {
                    finishRecord();
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private DialogInterface.OnDismissListener onDismiss = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            stopRecording();
        }
    };

    public interface OnFinishedRecordListener {
        public void onFinishedRecord(String audioPath, int time);
    }


}
