package youtu.android601.coachmodel;

import java.io.Serializable;

/**
 * Created by djf on 2017/6/30.
 */

public class CoachBean implements Serializable {
    /**
     * id
     */
    private int id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 照片网络地址
     */
    private String picture;
    /**
     * 线路列表网络地址
     */
    private String routeUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }

    @Override
    public String toString() {
        return "CoachBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", routeUrl='" + routeUrl + '\'' +
                '}';
    }
}
