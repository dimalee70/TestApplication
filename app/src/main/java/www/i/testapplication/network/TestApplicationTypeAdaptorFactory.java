package www.i.testapplication.network;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;


@GsonTypeAdapterFactory
public abstract class TestApplicationTypeAdaptorFactory  implements TypeAdapterFactory
{
    public static TypeAdapterFactory create() {
        return new AutoValueGson_TestApplicationTypeAdaptorFactory();
    }
}
