package www.i.testapplication.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import www.i.testapplication.news.AllShowsActivity;
import www.i.testapplication.webview.WebViewActivity;

@Module
public abstract class ActivityBuildersModule
{
    @ContributesAndroidInjector
    abstract AllShowsActivity bindAllShowsActivity();

    @ContributesAndroidInjector
    abstract WebViewActivity bindWebViewActivity();
}
