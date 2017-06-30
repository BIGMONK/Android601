package youtu.android601.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by djf on 2017/6/12.
 */
@Entity
public class TestBEan {
    @Id(autoincrement = true)
    Long id;
    private String name;
    @NotNull
    private String branchId;
    private long duration;
    private long position;
    public long getPosition() {
        return this.position;
    }
    public void setPosition(long position) {
        this.position = position;
    }
    public long getDuration() {
        return this.duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public String getBranchId() {
        return this.branchId;
    }
    public void setBranchId(String branchId) {
        this.branchId = branchId;
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
    @Generated(hash = 1233339214)
    public TestBEan(Long id, String name, @NotNull String branchId, long duration,
            long position) {
        this.id = id;
        this.name = name;
        this.branchId = branchId;
        this.duration = duration;
        this.position = position;
    }
    @Generated(hash = 1519188792)
    public TestBEan() {
    }
}
