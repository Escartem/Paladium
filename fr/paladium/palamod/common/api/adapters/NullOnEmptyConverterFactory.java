package fr.paladium.palamod.common.api.adapters;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class NullOnEmptyConverterFactory extends Converter.Factory {
  public static Converter.Factory create() {
    return new NullOnEmptyConverterFactory();
  }
  
  @Nullable
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
    final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
    return new Converter<ResponseBody, Object>() {
        public Object convert(ResponseBody body) throws IOException {
          if (body.contentLength() == 0L)
            return null; 
          return delegate.convert(body);
        }
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\adapters\NullOnEmptyConverterFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */