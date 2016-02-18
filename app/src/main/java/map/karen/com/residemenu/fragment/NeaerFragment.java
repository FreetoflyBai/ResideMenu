package map.karen.com.residemenu.fragment;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import map.karen.com.residemenu.R;
import map.karen.com.residemenu.utils.Tools;
import map.karen.com.residemenu.view.ExplosionField;


public class NeaerFragment extends Fragment {

    private Activity context;
    private View rootView;
    private ImageView mImage;
    private ExplosionField mExplosionField;
    private RelativeLayout allLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_near, container, false);
        mExplosionField = ExplosionField.attach2Window(context);
        ininView();
        initListener();
        ininLogic();
        return rootView;
    }

    private void ininView() {
        allLayout= (RelativeLayout) rootView.findViewById(R.id.allLayout);
        mImage= (ImageView) rootView.findViewById(R.id.image);

    }

    private void initListener() {
        mImage.setOnClickListener(imageClick);
    }

    private void ininLogic() {

        allLayout.setBackground(new BitmapDrawable(Tools.getGreyImage(
                BitmapFactory.decodeResource(getResources(), R.drawable.slidingpane_home))));

    }

    View.OnClickListener imageClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mExplosionField.explode(v);

        }
    };


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }
}