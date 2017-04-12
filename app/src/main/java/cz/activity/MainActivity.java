package cz.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.chenzhang.myapplication.R;
import java.util.Timer;
import java.util.TimerTask;
import cz.fragment.ClassTableFragment;
import cz.fragment.HomeFragment;
import cz.fragment.MineFragment;


public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private MineFragment mfragment;
    private HomeFragment hfragment;
    private ClassTableFragment cfragment;
    private static boolean isExit;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            FragmentManager fm = getSupportFragmentManager();
            // 开启Fragment事务
            FragmentTransaction transaction = fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    hfragment = new HomeFragment();
                    transaction.replace(R.id.content, hfragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    cfragment = new ClassTableFragment();
                    transaction.replace(R.id.content, cfragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    mfragment = new MineFragment();
                    transaction.replace(R.id.content, mfragment);
                    transaction.commit();
                    return true;

            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        setDefaulatFragmennt();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    public void setDefaulatFragmennt() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hfragment = new HomeFragment();
        transaction.replace(R.id.content, hfragment);
        transaction.commit();

    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); // 调用双击退出函数
        }
        return false;


    }

    /**
     * 双击退出函数
     */
    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 1000); // 如果1秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {

            System.exit(0);
        }
    }







}
