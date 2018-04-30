package com.think.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by think on 2017/12/3.
 */
@Entity
public class Picture {
    @Id(autoincrement = true)
    private Long pictureId;
    @NotNull
    @Unique
    private String url;
    @Generated(hash = 2145315602)
    public Picture(Long pictureId, @NotNull String url) {
        this.pictureId = pictureId;
        this.url = url;
    }
    @Generated(hash = 1602548376)
    public Picture() {
    }
    public Long getPictureId() {
        return this.pictureId;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", url='" + url + '\'' +
                '}';
    }
}
