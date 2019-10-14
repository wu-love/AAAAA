package com.example.rmb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {

    private static final String TAG = "Rate";
    EditText rmb;
    TextView show;
    private float dollarRate = 0.1f;
    private float euroRate = 0.2f;
    private float wonRate = 0.3f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        rmb = findViewById(R.id.rmb);
        show = findViewById(R.id.showOut);
    }

    public void onClick(View btn) {
        //获取用户输入内容
        Log.i(TAG, "onClick: ");
        String str = rmb.getText().toString();
        Log.i(TAG, "onClick: get str=" + str);

        float r = 0;
        if (str.length() > 0) {
            r = Float.parseFloat(str);
        } else {
            //提示用户输入内容
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG, "onClick: r=" + r);

        //计算
        if (btn.getId() == R.id.btn_dollar) {
            show.setText(String.format("%.2f", r * dollarRate));
        } else if (btn.getId() == R.id.btn_euro) {
            show.setText(String.format("%.2f", r * euroRate));
        } else {
            show.setText(String.format("%.2f", r * wonRate));
        }
    }

    public void openOne(View btn) {
        Intent config = new Intent(this, ConfigActivity.class);
        config.putExtra("dollar_rate_key", dollarRate);
        config.putExtra("euro_rate_key", euroRate);
        config.putExtra("won_rate_key", wonRate);

        Log.i(TAG, "openOne: dollar_rate_key=" + dollarRate);
        Log.i(TAG, "openOne: euro_rate_key=" + euroRate);
        Log.i(TAG, "openOne: won_rate_key=" + wonRate);
        //startActivity(config);
        startActivityForResult(config,1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_set){

            Intent config = new Intent(this,ConfigActivity.class);
            config.putExtra("dollar_rate_key",dollarRate);
            config.putExtra("euro_rate_key",euroRate);
            config.putExtra("won_rate_key",wonRate);

            Log.i(TAG,"openOne: dollar_rate_key=" + dollarRate);
            Log.i(TAG,"openOne: euro_rate_key=" + euroRate);
            Log.i(TAG,"openOne: won_rate_key=" + wonRate);
            startActivityForResult(config,1);
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(requestCode==1 && resultCode==2){
            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat( "key_dollar",0.1f );
            euroRate = bundle.getFloat( "key_euro",0.1f );
            wonRate = bundle.getFloat( "key_won",0.1f );
            Log.i( TAG,"onActivityResult: dollarRate ="+ dollarRate );
            Log.i( TAG,"onActivityResult: euroRate="+ euroRate );
            Log.i( TAG,"onActivityResult: wonRate="+ wonRate );
        }
    }
}
        /*float val = 0;
        if(btn.getId()==R.id.btn_dollar){
            val = r * (1/6.7f);
        }else if(btn.getId()==R.id.btn_euro){
            val = r * (1/11f);
        }else{
            val = r * 500;
        }
        show.setText(String.valueOf(val));
    }

    public  void openOne(View btn){
        //打开一个页面Activity;
        Log.i("open","openOne:");
        Intent main = new Intent(this,MainActivity.class);
        Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ww.jd.com"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:12345678"));
        startActivity(main);
        startActivity(web);
        startActivity(intent);

        finish();
    }
*/
