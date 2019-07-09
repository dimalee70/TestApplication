package www.i.testapplication.db.newshow;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import java.util.Objects;

@Entity(tableName = "new_shows")
public class NewShow
{
    @Nullable
    private String title;

    @Nullable
    private String description;

    @PrimaryKey
    @NonNull
    private String url;

    @Nullable
    private String urlToImage;

    @Nullable
    private String publishedAt;

    @Nullable
    private String content;

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(@Nullable String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @Nullable
    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(@Nullable String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewShow newShow = (NewShow) o;
        return Objects.equals(title, newShow.title) &&
                Objects.equals(description, newShow.description) &&
                Objects.equals(url, newShow.url) &&
                Objects.equals(urlToImage, newShow.urlToImage) &&
                Objects.equals(publishedAt, newShow.publishedAt) &&
                Objects.equals(content, newShow.content);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(title, description, url, urlToImage, publishedAt, content);
    }
}
