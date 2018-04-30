package com.think.greendao.entity;

import com.think.greendao.db.Car;
import com.think.greendao.db.CarDao;
import com.think.greendao.db.DaoSession;
import com.think.greendao.db.PictureDao;
import com.think.greendao.db.TeacherDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

/**
 * Created by think on 2017/12/3.
 */
@Entity(
        // 如果有一个以上的模式，可以告诉greendao实体属于哪个模式。
        //默认schema = "default",
        //schema = "default",
        // 标志允许实体类可有更新，删除，刷新方法
        active = true,
        //指定数据库中表的名称,默认情况下，该表的名称是实体类名。
        nameInDb = "teacher_tb",
        // true表示greenDAO创建数据库表(默认为true)，如果不用greenDAO创建表，将此设置为false。
        createInDb = true,
        // 是否应该生成所有的属性构造函数。无参构造函数总是要生成的
        generateConstructors = true,
        // 是否生成属性的getter和setter
        generateGettersSetters = true

)
public class Teacher {
    @Id
    private Long id;
    private String name;
    private Long pictureId;
    @ToOne(joinProperty = "pictureId")
    private Picture picture;
    @NotNull
    private int age;
    @ToMany(referencedJoinProperty = "ownerId")
    private List<Car> cars;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 648119699)
    private transient TeacherDao myDao;
    @Generated(hash = 1986840853)
    private transient Long picture__resolvedKey;

    @Generated(hash = 589179490)
    public Teacher(Long id, String name, Long pictureId, int age) {
        this.id = id;
        this.name = name;
        this.pictureId = pictureId;
        this.age = age;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1349174479)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeacherDao() : null;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1937024467)
    public Picture getPicture() {
        Long __key = this.pictureId;
        if (picture__resolvedKey == null || !picture__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PictureDao targetDao = daoSession.getPictureDao();
            Picture pictureNew = targetDao.load(__key);
            synchronized (this) {
                picture = pictureNew;
                picture__resolvedKey = __key;
            }
        }
        return picture;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1412118702)
    public void setPicture(Picture picture) {
        synchronized (this) {
            this.picture = picture;
            pictureId = picture == null ? null : picture.getPictureId();
            picture__resolvedKey = pictureId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1867445117)
    public List<Car> getCars() {
        if (cars == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CarDao targetDao = daoSession.getCarDao();
            List<Car> carsNew = targetDao._queryTeacher_Cars(id);
            synchronized (this) {
                if (cars == null) {
                    cars = carsNew;
                }
            }
        }
        return cars;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1055213807)
    public synchronized void resetCars() {
        cars = null;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pictureId=" + pictureId +
                ", picture=" + picture +
                ", age=" + age +
                ", cars=" + cars +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", picture__resolvedKey=" + picture__resolvedKey +
                '}';
    }
}
