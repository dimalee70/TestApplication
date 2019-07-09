package www.i.testapplication.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import www.i.testapplication.news.AllShowsActivity;

public class SplashActivity  extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, AllShowsActivity.class);
        startActivity(intent);
        finish();


    }
}
