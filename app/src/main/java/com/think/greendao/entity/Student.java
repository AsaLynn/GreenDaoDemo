package com.think.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by think on 2017/12/2.
 */
@Entity(nameInDb = "student_tb",createInDb = true)
public class Student {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "name")
    private String name;
    @Transient
    private int temp;
    @Property(nameInDb = "hobby")
    @NotNull@Index(name = "NO",unique = true)
    private String hobby;
    @Property(nameInDb = "number")
    @Unique
    private int number;
    private int age;
    @Generated(hash = 586419876)
    public Student(Long id, String name, @NotNull String hobby, int number,
            int age) {
        this.id = id;
        this.name = name;
        this.hobby = hobby;
        this.number = number;
        this.age = age;
    }
    @Generated(hash = 1556870573)
    public Student() {
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
    public String getHobby() {
        return this.hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", temp=" + temp +
                ", hobby='" + hobby + '\'' +
                ", number=" + number +
                ", age=" + age +
                '}';
    }

    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
