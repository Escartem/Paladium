package fr.paladium.palamod.common.api.adapters;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

class null implements Converter<ResponseBody, Object> {
  public Object convert(ResponseBody body) throws IOException {
    if (body.contentLength() == 0L)
      return null; 
    return delegate.convert(body);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\adapters\NullOnEmptyConverterFactory$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */