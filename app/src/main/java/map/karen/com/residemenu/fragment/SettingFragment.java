package map.karen.com.residemenu.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import map.karen.com.residemenu.R;
import map.karen.com.residemenu.drag.DraggableListener;
import map.karen.com.residemenu.drag.DraggableView;


public class SettingFragment extends Fragment {

    private View mView;
    private DraggableView draggableView;
    private VideoView videoView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_setting, container, false);
        initView();
        initClickListener();
        initLogic();
        return mView;
    }

    private void initView() {
        draggableView= (DraggableView) mView.findViewById(R.id.draggable_view);
        videoView= (VideoView) mView.findViewById(R.id.video_view);
        initializeVideoView();


    }

    private void initClickListener() {
        draggableView.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                startVideo();
            }
            //Empty
            @Override
            public void onMinimized() {
                //Empty
            }
            @Override
            public void onClosedToLeft() {
                pauseVideo();
            }
            public void onClosedToRight() {
                pauseVideo();
            }
        });


    }

    private void initLogic() {

    }


    /**
     * Pause the VideoView content.
     */
    private void pauseVideo() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    /**
     * Resume the VideoView content.
     */
    private void startVideo() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    /**
     * Initialize ViedeoView with a video by default.
     */
    private void initializeVideoView() {
        Uri path = Uri.parse("android.resource://map.karen.com.residemenu/" + R.raw.video);
        videoView.setVideoURI(path);
        videoView.start();
    }





}