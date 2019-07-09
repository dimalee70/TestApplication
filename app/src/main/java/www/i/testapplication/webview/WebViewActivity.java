package www.i.testapplication.webview;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import www.i.testapplication.R;
import www.i.testapplication.base.TestApplicationBaseActivity;
import www.i.testapplication.databinding.ActivityAllShowsBinding;
import www.i.testapplication.databinding.ActivityWebviewBinding;

public class WebViewActivity extends TestApplicationBaseActivity
{

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    private ActivityWebviewBinding binding;
    private String url;

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra("url", url);
        context.startActivity(starter);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        setToolbar();
        url = (String) getIntent().getExtras().get("url");
        System.out.println("URL");
        System.out.println(url);
        binding.webView.loadUrl(url);
    }

    private void setToolbar() {
        Toolbar toolbar = binding.toolbar.toolbar;
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        toolbar.setSubtitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.shows);
    }
}
