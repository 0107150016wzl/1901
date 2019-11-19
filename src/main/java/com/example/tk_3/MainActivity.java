package com.example.tk_3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private Toolbar toolbar;
    private NavigationView navigationview;
    private TabLayout tablayout;
    private ViewPager viewpager;
    private FragmentAdapter adapter;
    String str="http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1";
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1){
                List<javaBean.DataBean> data= (List<javaBean.DataBean>) msg.obj;

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initFragment();
    }

    private void initData() {
        new Thread(){
            @Override
            public void run() {
                try {
                    HttpURLConnection url = (HttpURLConnection) new URL(str).openConnection();
                    InputStream in = url.getInputStream();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    int lengh;
                    byte[] bytes = new byte[1024 * 4];
                    while ((lengh = in.read(bytes)) != -1) {
                        out.write(bytes, 0, lengh);
                    }
                    String s = out.toString();
                    javaBean javaBean = new Gson().fromJson(s, javaBean.class);
                    Message message = new Message();
                    message.obj=javaBean;
                    message.what=1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initFragment() {
        List<Fragment> list=new ArrayList<>();
        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new OneFragment());
        adapter = new FragmentAdapter(getSupportFragmentManager(),list);
        viewpager.setAdapter(adapter);
        tablayout.addTab(tablayout.newTab().setText("首页").setIcon(R.color.colorAccent));
        tablayout.addTab(tablayout.newTab().setText("菜单").setIcon(R.color.colorAccent));
        tablayout.addTab(tablayout.newTab().setText("设置").setIcon(R.color.colorAccent));
        tablayout.setupWithViewPager(viewpager);
        tablayout.getTabAt(0).setText("首页").setIcon(R.color.colorAccent);
        tablayout.getTabAt(1).setText("菜单").setIcon(R.color.colorAccent);
        tablayout.getTabAt(2).setText("设置").setIcon(R.color.colorAccent);
    }

    private void initView() {
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationview = (NavigationView) findViewById(R.id.navigationview);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.open, R.string.close);
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationview.setItemIconTintList(null);
        navigationview.getHeaderView(0).findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "头部", Toast.LENGTH_SHORT).show();
            }
        });
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.one:
                        Toast.makeText(MainActivity.this, "首页", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.two:
                        Toast.makeText(MainActivity.this, "彩带", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.three:
                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }

        });
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }
}
