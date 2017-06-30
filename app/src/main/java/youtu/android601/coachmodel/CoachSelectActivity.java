package youtu.android601.coachmodel;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import youtu.android601.R;

public class CoachSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_select);

        CoachSelectFragment coachSelectFragment = CoachSelectFragment.newInstance();

        if (!coachSelectFragment.isAdded()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fm_coachlist, coachSelectFragment, "CoachSelectFragment")
                    .commit();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(coachSelectFragment);
        fragmentTransaction.commit();


    }
}
