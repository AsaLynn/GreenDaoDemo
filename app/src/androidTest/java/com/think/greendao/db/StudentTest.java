package com.think.greendao.db;

import com.think.greendao.entity.Student;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

public class StudentTest extends AbstractDaoTestLongPk<StudentDao, Student> {

    public StudentTest() {
        super(StudentDao.class);
    }

    @Override
    protected Student createEntity(Long key) {
        Student entity = new Student();
        entity.setId(key);
        entity.setHobby("吭哧吭哧的吃饭!");
        entity.setNumber(9);
        return entity;
    }

}
