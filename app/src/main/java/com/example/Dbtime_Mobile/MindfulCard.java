package com.example.Dbtime_Mobile;

import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MindfulCard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MindfulCard extends Fragment {
    private static final String ARG_CATEGORY = "category";
    String category;
    View frontface, backface;


    public MindfulCard() {
        // Required empty public constructor
    }

    public static MindfulCard newInstance(String category) {
        MindfulCard fragment = new MindfulCard();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    ValueAnimator flipAnimator;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.category = getArguments().getString(ARG_CATEGORY);
        }

        View view = getView();

        frontface = view.findViewById(R.id.mindful_card_frontface);
        backface = view.findViewById(R.id.mindful_card_backface);

        flipAnimator = ValueAnimator.ofFloat(0.5f, 0f);
        flipAnimator.addUpdateListener(new FlipListener(this.frontface, this.backface));
        flipAnimator.setDuration(2000);
        flipAnimator.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mindful_card, container, false);
    }
}

class FlipListener implements ValueAnimator.AnimatorUpdateListener {

    private final View mFrontView;
    private final View mBackView;
    private boolean mFlipped;

    public FlipListener(final View front, final View back) {
        this.mFrontView = front;
        this.mBackView = back;
        this.mBackView.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationUpdate(final ValueAnimator animation) {
        final float value = animation.getAnimatedFraction();
        final float scaleValue = 0.625f + (1.5f * (value - 0.5f) * (value - 0.5f));

        if(value <= 0.5f){
            this.mFrontView.setRotationY(180 * value);
            this.mFrontView.setScaleX(scaleValue);
            this.mFrontView.setScaleY(scaleValue);
            if(mFlipped){
                setStateFlipped(false);
            }
        } else {
            this.mBackView.setRotationY(-180 * (1f- value));
            this.mBackView.setScaleX(scaleValue);
            this.mBackView.setScaleY(scaleValue);
            if(!mFlipped){
                setStateFlipped(true);
            }
        }
    }

    private void setStateFlipped(boolean flipped) {
        mFlipped = flipped;
        this.mFrontView.setVisibility(flipped ? View.GONE : View.VISIBLE);
        this.mBackView.setVisibility(flipped ? View.VISIBLE : View.GONE);
    }
}