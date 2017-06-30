package youtu.android601.coachmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import youtu.android601.R;

/**
 * Created by djf on 2017/6/30.
 */

public class CoachSelectFragment extends FragmentAbs {


    private static CoachSelectFragment mInstance;

    public static CoachSelectFragment newInstance() {
        if (mInstance == null) {
            synchronized (CoachSelectFragment.class) {
                if (mInstance == null) {
                    return new CoachSelectFragment();
                }
            }
        }
        return mInstance;
    }


    public CoachSelectFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @BindView(R.id.ll_show_results)
    LinearLayout llShowResults;
    @BindView(R.id.btn_pre)
    Button btnPre;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.hsv_coach_list)
    HorizontalScrollView coachListView;

    @BindView(R.id.ll_loading)
    LinearLayout llLoading;

    @BindView(R.id.ll_other)
    LinearLayout llOther;

    @BindView(R.id.tv_other_msg)
    TextView otherMsg;

    private Contract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coach_select, container, false);

        presenter.start("");

        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showResults(ArrayList<Object> list, boolean isMore) {

    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {

    }

    @Override
    public void initViews(View view) {

    }
}
