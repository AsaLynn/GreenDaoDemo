package com.think.greendao;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.demonstrate.DemonstrateUtil;
import com.example.demonstrate.DialogUtil;
import com.think.greendao.application.MyApplication;
import com.think.greendao.db.Car;
import com.think.greendao.db.CarDao;
import com.think.greendao.db.PictureDao;
import com.think.greendao.db.TeacherDao;
import com.think.greendao.entity.Picture;
import com.think.greendao.entity.Teacher;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoDemo1Activity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv)
    TextView tv;
    private TeacherDao teacherDao;
    private PictureDao pictureDao;
    private CarDao carDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_demo1);
        ButterKnife.bind(this);
        teacherDao = MyApplication.getInstance()
                .getDaoSession()
                .getTeacherDao();
        pictureDao = MyApplication
                .getInstance()
                .getDaoSession()
                .getPictureDao();
        carDao = MyApplication
                .getInstance()
                .getDaoSession()
                .getCarDao();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        DialogUtil.showListDialog(this, "数据操作", new String[]{
                        "0插入picture数据",
                        "1插入car数据",
                        "2插入teacher数据",
                        "3获取指定teacher1的picture",
                        "4获取指定teacher2的car",
                        "5获取所有picture",
                        "6获取所有car",
                        "7获取所有teacher",
                }

                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //插入Picture数据
                                for (int i = 0; i < 5; i++) {
                                    pictureDao.insert(new Picture(null, "url1" + i));
                                }
                                DemonstrateUtil.showToastResult(GreenDaoDemo1Activity.this, "插入完毕!");
                                break;
                            case 1:
                                //插入car数据
                                for (int i = 0; i < 5; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        Car car = new Car();
                                        car.setOwnerId(i + 1l);
                                        if (i == 0) {
                                            car.setCarName("白车" + j);
                                        } else if (i == 1) {
                                            car.setCarName("黑车" + j);
                                        } else if (i == 2) {
                                            car.setCarName("红车" + j);
                                        } else if (i == 3) {
                                            car.setCarName("蓝车" + j);
                                        } else if (i == 4) {
                                            car.setCarName("黄车" + j);
                                        }
                                        carDao.insert(car);
                                    }
                                }
                                DemonstrateUtil.showToastResult(GreenDaoDemo1Activity.this, "插入完毕!");
                                break;
                            case 2:
                                //插入teacher数据.
                                for (int i = 0; i < 5; i++) {
                                    Long pictureId = i + 1l;
                                    //Long ownerId = i + 10l;
//                                    teacherDao.insert(new Teacher(null, "teacher" + i, pictureId, 20 + i));
                                    Teacher teacher = new Teacher(null, "teacher" + i, pictureId, 20 + i);
                                    //teacher.resetCars();
                                    teacherDao.insert(teacher);
                                }
                                DemonstrateUtil.showToastResult(GreenDaoDemo1Activity.this, "插入完毕!");
                                break;
                            case 3:
                                //获取指定的teacher1的Picture
                                Teacher teacher1 = teacherDao
                                        .queryBuilder()
                                        .where(TeacherDao.Properties.Name.eq("teacher1"))
                                        .unique();
                                if (null != teacher1) {
                                    Picture picture = teacher1.getPicture();
                                    tv.setText(picture.toString());
                                } else {
                                    DemonstrateUtil.showAllResult("您要的数据不存在!", tv);
                                }
                                break;
                            case 4:
                                //输出teacher2的car
                                Teacher teacher2 = teacherDao.queryBuilder()
                                        .where(TeacherDao.Properties.Name.eq("teacher2"))
                                        .unique();
                                if (null != teacher2) {
                                    List<Car> carList = teacher2.getCars();
                                    DemonstrateUtil.showAllResult(carList.toString(), tv);
                                } else {
                                    DemonstrateUtil.showAllResult("您要的数据不存在!", tv);
                                }
                                break;
                            case 5:
                                //输出所有的picture
                                List<Picture> pictures = pictureDao
                                        .loadAll();
                                tv.setText(pictures.toString());
                                for (Picture pic : pictures) {
                                    DemonstrateUtil.showLogResult(pic.toString());
                                }
                                break;
                            case 6:
                                //输出所有的car
                                List<Car> cars = carDao.loadAll();
                                DemonstrateUtil.showAllResult(cars.toString(), tv);
                                break;
                            case 7:
                                //输出所有的teacher
                                List<Teacher> teachers = teacherDao.loadAll();
                                DemonstrateUtil.showAllResult(teachers.toString(), tv);
                                break;
                        }
                    }
                });
    }
}
