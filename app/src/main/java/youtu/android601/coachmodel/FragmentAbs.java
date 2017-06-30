package youtu.android601.coachmodel;

import android.support.v4.app.Fragment;

/**
 * Created by djf on 2017/6/21.
 */

public abstract class FragmentAbs extends Fragment implements Contract.View {

    protected String TAG=this.getClass().getSimpleName();
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}
