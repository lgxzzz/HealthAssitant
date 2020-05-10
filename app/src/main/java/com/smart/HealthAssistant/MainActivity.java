package com.smart.HealthAssistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.smart.HealthAssistant.fragment.AlarmFragment;
import com.smart.HealthAssistant.fragment.DataFragment;
import com.smart.HealthAssistant.fragment.HomeFragment;
import com.smart.HealthAssistant.fragment.MonitorFragment;
import com.smart.HealthAssistant.service.HealthAssitantService;
import com.smart.HealthAssistant.util.FragmentUtils;

public class MainActivity extends BaseActivtiy {

    private BottomNavigationView mBottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        startService(new Intent(this, HealthAssitantService.class));
    }

    public void init(){


        mBottomMenu = findViewById(R.id.bottom_menu);
        mBottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showFragment(item.getItemId());
                return true;
            }
        });

        mBottomMenu.setSelectedItemId(R.id.bottom_menu_home);

    }


    /**
     * 根据id显示相应的页面
     * @param menu_id
     */
    private void showFragment(int menu_id) {
        switch (menu_id){
            case R.id.bottom_menu_home:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, HomeFragment.getInstance(),R.id.main_frame);
                break;
            case R.id.bottom_menu_monitor:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, MonitorFragment.getInstance(),R.id.main_frame);
                break;
            case R.id.bottom_menu_alarm:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, AlarmFragment.getInstance(),R.id.main_frame);
                break;
            case R.id.bottom_menu_data:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, DataFragment.getInstance(),R.id.main_frame);
                break;
        }
    }

}
