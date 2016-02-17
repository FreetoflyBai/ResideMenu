package map.karen.com.residemenu.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import map.karen.com.residemenu.R;
import map.karen.com.residemenu.utils.Tools;


public class HomeFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            ((ImageView)rootView.findViewById(R.id.greyImage)).setImageBitmap(
                    Tools.getGreyImage(
                            BitmapFactory.decodeResource(getResources(), R.drawable.clip)));
            return rootView;
        }
    }