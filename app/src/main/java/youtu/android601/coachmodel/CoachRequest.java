package youtu.android601.coachmodel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by djf on 2017/6/30.
 */

public class CoachRequest implements Serializable{
    private int code;
    private String message;
    private ArrayList<CoachBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CoachBean> getData() {
        return data;
    }

    public void setData(ArrayList<CoachBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CoachRequest{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
