package youtu.android601.coachmodel;

import android.content.Context;

import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;
import youtu.android601.utils.NetworkUtils;

/**
 * Created by djf on 2017/6/30.
 */

public class CoachPresenter implements Contract.Presenter {
    String TAG = this.getClass().getSimpleName();
    private Contract.View view;
    private Context context;
    private ArrayList<CoachBean> list;
    private String url;
    public CoachPresenter(Context context, Contract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
        this.list=new ArrayList<>();
    }

    @Override
    public void loadPosts(String url, final boolean clearing) {
        if (clearing){
            view.showLoading();
        }
        if (NetworkUtils.isNetworkConnected(context)){//判断网络是否可用
            OkHttpUtils.get(url).tag(this).execute(new JsonCallback<CoachRequest>(CoachRequest.class) {
                @Override
                public void onSuccess(CoachRequest bean, Call call, Response response) {
                    if (clearing){
                        list.clear();
                    }
                    if (bean!=null&&bean.getCode()==10000){
                        for (int i = 0; i <bean.getData().size() ; i++) {
                            list.add(bean.getData().get(i));
                        }
                    }
                    view.showResults(list,false);
                }


                @Override
                public void onError(Call call, Response response, Exception e) {
                    super.onError(call, response, e);
                    view.showError();
                }
            });
        }
    }

    @Override
    public void refresh() {
        loadPosts(url,true);
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void startReading(int position, boolean isLast) {

    }

    @Override
    public void start(String url) {
         this.url=url;
    }
}
