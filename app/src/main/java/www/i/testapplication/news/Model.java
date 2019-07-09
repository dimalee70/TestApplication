package www.i.testapplication.news;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable
{
    @SerializedName("articles")
    private List<Show> list;

    public List<Show> getList() {
        return list;
    }

    public void setList(List<Show> list) {
        this.list = list;
    }
}
