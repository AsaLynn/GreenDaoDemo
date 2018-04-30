package com.think.greendao.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by think on 2017/12/3.
 */
@Entity(nameInDb = "car_tb",createInDb = true,generateGettersSetters = true,generateConstructors = true)
public class Car {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "ownerId")
    private Long ownerId;
    @NotNull
    @Property(nameInDb = "carname")
    private String carName;
    @Generated(hash = 1750893407)
    public Car(Long id, Long ownerId, @NotNull String carName) {
        this.id = id;
        this.ownerId = ownerId;
        this.carName = carName;
    }
    @Generated(hash = 625572433)
    public Car() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOwnerId() {
        return this.ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    public String getCarName() {
        return this.carName;
    }
    public void setCarName(String carName) {
        this.carName = carName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", carName='" + carName + '\'' +
                '}';
    }
}
