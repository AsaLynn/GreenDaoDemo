package com.think.greendao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.demonstrate.DemonstrateUtil;
import com.example.demonstrate.DialogUtil;
import com.think.greendao.application.MyApplication;
import com.think.greendao.db.StudentDao;
import com.think.greendao.entity.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_greendao)
    Button btnGreendao;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.btn2)
    Button btn2;
    private StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        studentDao = MyApplication
                .getInstance()
                .getStudentDao();
    }

    @OnClick(R.id.btn_greendao)
    public void onViewClicked() {
        DialogUtil.showListDialog(this, "greendao的操作使用!", new String[]{
                "0增加一条数据",
                "1删除一条数据",
                "2修改一条数据",
                "3查询一条数据",
                "4增加List集合数据",
                "5查询所有的数据",
                "6查询指定字段的数据",
                "7查询指定字段的数据降序排列",
                "8查询指定字段的数据升序排列",
                "9组合查询数据",
                "10查询所有的数据,只取指定条数",
                "11查询所有,取指定条数,跳过某条",
                "12查询数据总条数",
                "13修改指定字段的数据",
                "14删除指定数据方式1",
                "15删除指定数据方式2",
                "16删除全部数据",
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //添加一条数据.
                        insert();
                        break;
                    case 1:
                        //删除一条数据
                        studentDao.deleteByKey(1l);
                        break;
                    case 2:
                        //修改一条数据
                        update();
                        break;
                    case 3:
                        //查询一条数据
                        load();
                        break;
                    case 4:
                        //插入一个集合的数据,insertList
                        insertList();
                        break;
                    case 5:
                        //查询所有的数据
                        list();
                        break;
                    case 6:
                        //查询指定姓名为张非3的数据
                        nameList();
                        break;
                    case 7:
                        //查询指定姓名为李四的信息并按照年龄排序-降序
                        ageOrderList();
                        break;
                    case 8:
                        //查询指定姓名为李四的信息并按照年龄排序-升序
                        //agaOrderAsc
                        agaOrderAsc();
                        break;
                    case 9:
                        // 查询姓名为"李四" 并且年龄小于17
                        nameAgeList();
                        break;
                    case 10:
                        //查询所有数据,只取前3条
                        limtList();
                        break;
                    case 11:
                        //查询所有,只要前3条,从指定条数索引处的下一条开始查询
                        limtOffsetList();
                        break;
                    case 12:
                        //查询数据总条数
                        listSize();
                        break;
                    case 13:
                        //修改姓名张非1的姓名
                        updateName();
                        break;
                    case 14:
                        //删除指定姓名为张非2的数据
                        executeDeleteWithoutDetachingEntities();
                        break;
                    case 15:
                        //通过指定对象删除数据,删除张非4
                        delStu();
                        break;
                    case 16:
                        //删除全部数据!
                        studentDao.deleteAll();
                        break;
                }
            }
        });
    }

    private void delStu() {
        Student student = studentDao.
                queryBuilder()
                .where(StudentDao.Properties.Name.eq("张非4"))
                .unique();
        studentDao.delete(student);
        DemonstrateUtil.showLogResult("删除,张非4");
    }

    private void executeDeleteWithoutDetachingEntities() {
        studentDao
                .queryBuilder()
                .where(StudentDao.Properties.Name.eq("张非2"))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
        DemonstrateUtil.showLogResult("删除成功!");
    }

    private void updateName() {
        Student student = studentDao.
                queryBuilder()
                .where(StudentDao.Properties.Name.eq("张非1"))
                .unique();
        if (null != student) {
            student.setName("张三");
            studentDao.update(student);
            DemonstrateUtil.showToastResult(this, "修改成功!");
            Student student1 = studentDao
                    .queryBuilder()
                    .where(StudentDao.Properties.Name.eq("张三"))
                    .unique();
            DemonstrateUtil.showLogResult(student1.toString());
        } else {
            DemonstrateUtil.showToastResult(this, "要修改的数据不存在!!");
        }
    }

    private void listSize() {
        /*tvShow.setText("总条数:" + studentDao.queryBuilder()
                .list().size());*/
        tvShow.setText("总条数:" + studentDao.count());
    }

    private void limtOffsetList() {
        List<Student> students = studentDao
                .queryBuilder()
                .limit(3)
                .offset(2)
                .list();
        tvShow.setText(students.toString());
        for (Student student : students) {
            DemonstrateUtil.showLogResult(student.toString());
        }
    }

    private void limtList() {
        List<Student> students = studentDao
                .queryBuilder()
                .limit(3)
                .list();
        tvShow.setText(students.toString());
        for (Student student : students) {
            DemonstrateUtil.showLogResult(student.toString());
        }
    }

    private void nameAgeList() {
        List<Student> students = studentDao
                .queryBuilder()
                .where(StudentDao.Properties.Name.eq("李四"), StudentDao.Properties.Age.le(17))
                .list();
        tvShow.setText(students.toString());
        for (Student stu : students) {
            DemonstrateUtil.showLogResult(stu.toString());
        }
    }

    private void agaOrderAsc() {
        List<Student> students = studentDao
                .queryBuilder()
                .where(StudentDao.Properties.Name.eq("李四"))
                .orderAsc(StudentDao.Properties.Age)
                .list();
        tvShow.setText(students.toString());
        for (Student stu : students) {
            DemonstrateUtil.showLogResult(stu.toString());
        }
    }

    private void ageOrderList() {
        List<Student> students = studentDao
                .queryBuilder()
                .where(StudentDao.Properties.Name.eq("李四"))
                .orderDesc(StudentDao.Properties.Age)
                .list();
        tvShow.setText(students.toString());
        for (Student student : students) {
            //DemonstrateUtil.showLogResult(student.toString());
        }
    }

    private void insertList() {
        studentDao.insertInTx(new ArrayList<Student>() {
            {
                for (int i = 1; i <= 5; i++) {
                    int num = i + 1;
                    num++;
                    add(new Student(null, "张非" + i, "吃饭" + i, num, 20));
                }
                add(new Student(null, "李四", "吃饭", 10, 15));
                add(new Student(null, "李四", "睡觉", 11, 16));
                add(new Student(null, "李四", "敲码", 12, 17));
                add(new Student(null, "李四", "爱说话", 13, 18));
            }
        });
    }

    private void update() {
        studentDao.update(new Student() {
            {
                setNumber(1);
                setName("王小明");
                setId(1l);
                setHobby("吃饭睡觉敲码");
            }
        });
        DemonstrateUtil.showToastResult(this, "数据修改!");
    }

    private void nameList() {
        List<Student> students = studentDao.queryBuilder().where(StudentDao.Properties.Name.eq("张非3")).list();
        tvShow.setText(students.toString());
        for (Student student : students) {
            DemonstrateUtil.showLogResult(student.toString());
        }
    }

    private void list() {
        List<Student> studentList = studentDao.queryBuilder().list();
        tvShow.setText(studentList.toString());
        for (Student student : studentList) {
            DemonstrateUtil.showLogResult(student.toString());
        }
    }

    private void load() {
        Student student = studentDao.load(1l);
        if (null != student) {
            tvShow.setText(student.toString());
        } else {
            tvShow.setText("查询的数据不存在!!");
            DemonstrateUtil.showToastResult(MyApplication.getInstance(), "数据不存在哦!!");
        }
    }

    private void insert() {
        //返回插入数据实体的id
        long insert = studentDao.insert(new Student() {
            {
                this.setHobby("写代码");
                this.setName("王小明");
                this.setNumber(1);
                this.setAge(22);
            }
        });
        if (insert > 0) {
            DemonstrateUtil.showToastResult(this, "成功,实体id" + insert);
        } else {
            DemonstrateUtil.showToastResult(this, "失败,实体id" + insert);
        }
    }

    @OnClick(R.id.btn2)
    public void onViewClicked2() {
        startActivity(new Intent(this,GreenDaoDemo1Activity.class));
    }
}
