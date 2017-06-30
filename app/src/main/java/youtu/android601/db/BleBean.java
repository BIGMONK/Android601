package youtu.android601.db;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by djf on 2017/6/6.
 */
@Entity
public class BleBean {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    @NonNull
    private String mac;
    @NonNull
    private int type;
    @Transient
    private int  tempUsageCount; // not persisted
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getMac() {
        return this.mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 646539126)
    public BleBean(Long id, String name, @NonNull String mac, int type) {
        this.id = id;
        this.name = name;
        this.mac = mac;
        this.type = type;
    }
    @Generated(hash = 1543237087)
    public BleBean() {
    }
}
