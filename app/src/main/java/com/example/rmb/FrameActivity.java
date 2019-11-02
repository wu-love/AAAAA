package com.example.rmb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentActivity;

public class FrameActivity extends FragmentActivity {

    private Fragment mFragments[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton rbtHome,rbtFunc,rbtSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fragment );

        mFragments = new Fragment[3];
        //fragmentManager = getSupportFragmentManager();
        fragmentManager = getFragmentManager();
        mFragments[0] = this.fragmentManager.findFragmentById(R.id.fragment_main);
        System.out.println("0====="+mFragments[0]+"=======");
        mFragments[1] = this.fragmentManager.findFragmentById(R.id.fragment_func);
        System.out.println("1====="+mFragments[1]+"=======");
        mFragments[2] = this.fragmentManager.findFragmentById(R.id.fragment_setting);
        System.out.println("2====="+mFragments[2]+"=======");
        fragmentTransaction =
                this.fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
        fragmentTransaction.show(mFragments[0]).commit();

        rbtHome = findViewById(R.id.radioHome);
        rbtFunc = findViewById(R.id.radioFunc);
        rbtSetting = findViewById(R.id.radioSetting);
        rbtHome.setBackgroundResource( R.drawable.shape3 );

        radioGroup = findViewById( R.id.bottomGroup );
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("radioGroup", "checkId=" + checkedId);
                fragmentTransaction =
                        FrameActivity.this.fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
                rbtHome.setBackgroundResource( R.drawable.shape2 );
                rbtFunc.setBackgroundResource( R.drawable.shape2 );
                rbtSetting.setBackgroundResource( R.drawable.shape2 );

                switch(checkedId){
                    case R.id.radioHome:

                        fragmentTransaction.show(mFragments[0]).commit();
                        rbtHome.setBackgroundResource( R.drawable.shape3 );
                        break;
                    case R.id.radioFunc:

                        fragmentTransaction.show(mFragments[1]).commit();
                        rbtFunc.setBackgroundResource( R.drawable.shape3 );
                        break;
                    case R.id.radioSetting:
                        fragmentTransaction.show(mFragments[2]).commit();
                        rbtSetting.setBackgroundResource( R.drawable.shape3 );
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu;this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.frame,menu );

        return true;
    }

}
