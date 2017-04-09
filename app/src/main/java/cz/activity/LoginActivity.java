package cz.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chenzhang.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cz.bean.LocalUser;
import cz.bean.Student;
import cz.utils.DBManager;

/**
 * Created by chenzhang on 2017/3/29.
 */


public class LoginActivity extends Activity {
    private EditText stu_num;
    private EditText password;
    private CheckBox checkBox;
    private Button login;
    private static boolean FLAG;
    private DBManager mgr;

    private String stu_no, pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initDate();
        doLogin();

    }

    public void initView() {
        stu_num = (EditText) findViewById(R.id.stu_num);
        password = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        login = (Button) findViewById(R.id.login);


    }

    public void initDate() {
        SharedPreferences mySharepre = getSharedPreferences("stu_info", Activity.MODE_PRIVATE);
        if (mySharepre.contains("stu_num")) {
            stu_num.setText(mySharepre.getString("stu_num", ""));
            password.setText(mySharepre.getString("password", ""));
            checkBox.setChecked(true);
        } else {
//            stu_no=stu_num.getText().toString().trim();
//            pwd=password.getText().toString().trim();
            checkBox.setChecked(false);
        }

    }

    public void doLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            SharedPreferences mySharepre = getSharedPreferences("stu_info", Activity.MODE_PRIVATE);

            @Override
            public void onClick(View v) {
                stu_no = stu_num.getText().toString().trim();
                pwd = password.getText().toString().trim();
                if (stu_no == null || "".equals(stu_no) || pwd == null || "".equals(pwd)) {
                    Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                    FLAG = false;
                    checkBox.setChecked(FLAG);
                } else {
                    if (checkBox.isChecked()) {
                        SharedPreferences.Editor editor = mySharepre.edit();
                        editor.putString("stu_num", stu_no);
                        editor.putString("password", pwd);
                        editor.commit();


                    } else {

                        SharedPreferences.Editor editor = mySharepre.edit();
                        editor.clear();
                        editor.commit();
                    }
                    doJudge(stu_no, pwd);


                }
            }
        });

    }


    public void doJudge(final String stu_no, final String pwd) {
        BmobQuery<Student> query = new BmobQuery<Student>();
        query.addWhereEqualTo("student_id", stu_no);
        query.findObjects(new FindListener<Student>() {

            public void done(List<Student> object, BmobException e) {
                if (e == null) {

                    for (Student student : object) {
                        if (pwd.equals(student.getPassword())) {
                            SharedPreferences mySharepre = getSharedPreferences("stu_info", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = mySharepre.edit();
                            editor.putString("objectid", student.getObjectId());
                            doDb(student);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "欢迎登陆", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                            SharedPreferences mySharepre = getSharedPreferences("stu_info", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = mySharepre.edit();
                            editor.clear();
                            editor.commit();
                            FLAG = false;
                            checkBox.setChecked(FLAG);
                        }

                    }
                } else {
                    Log.d("error", e.toString());
                }
            }
        });


    }


    public void doDb(Student student) {
        mgr = new DBManager(this);
        List<LocalUser> students = new ArrayList<LocalUser>();
        LocalUser stu = new LocalUser(student.getObjectId(), student.getStudent_id(), student.getPassword(), student.getStu_name());
        students.add(stu);
        Log.d("test", "doDb: "+students.size());
        mgr.add(students);




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //应用的最后一个Activity关闭时应释放DB
        mgr.closeDB();
    }


}
