package walden.shoppingcart.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import walden.shoppingcart.demo.test1.Test1Activity;
import walden.shoppingcart.demo.test2.Test2Activity;
import walden.shoppingcart.demo.test3.Test3Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void test1(View v) {
        Test1Activity.toActivity(this);
    }

    public void test2(View v) {
        Test2Activity.toActivity(this);
    }

    public void test3(View v) {
        Test3Activity.toActivity(this);
    }
}
