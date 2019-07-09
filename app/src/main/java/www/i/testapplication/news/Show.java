package www.i.testapplication.news;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.i.testapplication.db.newshow.NewShow;
import www.i.testapplication.webview.WebViewActivity;

@AutoValue
public abstract class Show implements Parcelable {

    public static Show fromFavoriteShow(NewShow favoriteShow) {
        Map<String, String> image = new HashMap<>(1);
        image.put("original", favoriteShow.getUrlToImage());
//        Map<String, String> rating = new HashMap<>(1);
//        image.put("average", favoriteShow.getRating());
        return Show.builder()
                .title(favoriteShow.getTitle())
                .description(favoriteShow.getDescription())
                .url(favoriteShow.getUrl())
                .urlToImage(favoriteShow.getUrlToImage())
                .image(image)
                .publishedAt(favoriteShow.getPublishedAt())
                .content(favoriteShow.getContent())
                .build();
    }

    public static TypeAdapter<Show> typeAdapter(Gson gson) {
        return new AutoValue_Show.GsonTypeAdapter(gson);
    }

    public void onClick(View view)
    {
//        System.out.println(url());
        WebViewActivity.start(view.getContext(), url());
    }
    public static Builder builder() {
        return new AutoValue_Show.Builder();
    }

    @Nullable
    public abstract String title();

    @Nullable
    public abstract String description();

    @Nullable
    public abstract String url();

    @Nullable
    public abstract String urlToImage();

    @Nullable
    public abstract String publishedAt();

    @Nullable
    public abstract String content();

//    @Nullable
//    @SerializedName("network")
//    public abstract Channel airChannel();
//
//    @Nullable
//    @SerializedName("webChannel")
//    public abstract Channel webChannel();

    @Nullable
    public abstract Map<String, String> image();

//    @Nullable
//    @SerializedName("externals")
//    public abstract ExternalInfo externalInfo();

//    @Nullable
//    public abstract String summary();

//    @Nullable
//    public abstract Map<String, String> rating();

//    public abstract boolean isFavorite();


    public abstract Builder toBuilder();

    @AutoValue.Builder
    public static abstract class Builder {


        public abstract Builder title(String title);

        public abstract Builder description(String description);

        public abstract Builder url(String url);

        public abstract Builder urlToImage(String urlToImage);

        public abstract Builder publishedAt(String publishedAt);

        public abstract Builder content(String content);

        public abstract Builder image(Map<String, String> image);

//        public abstract Builder airChannel(Channel airChannel);
//
//        public abstract Builder webChannel(Channel webChannel);
//
//        public abstract Builder externalInfo(ExternalInfo externalInfo);
//
//        public abstract Builder isFavorite(boolean isFavorite);
//
//        public abstract Builder name(String name);
//
//        public abstract Builder rating(Map<String, String> rating);
//
//        public abstract Builder summary(String summary);


        public abstract Show build();
    }

//    @Override
//    public boolean equals(Object obj) {
//        return obj instanceof Show && id() == ((Show) obj).id();
//    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
