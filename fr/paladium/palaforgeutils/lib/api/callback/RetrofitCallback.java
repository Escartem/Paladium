package fr.paladium.palaforgeutils.lib.api.callback;

import java.util.function.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallback<R, T> implements Callback<T> {
  private IRetrofitCallback<R> retrofitCallback;
  
  private Function<Response<T>, R> function;
  
  public RetrofitCallback(IRetrofitCallback<R> shopCallback, Function<Response<T>, R> function) {
    this.retrofitCallback = shopCallback;
    this.function = function;
  }
  
  public void onResponse(Call<T> call, Response<T> response) {
    if (!response.isSuccessful()) {
      this.retrofitCallback.onFail(null, new Throwable("Unexcepted response | Code: " + response.code()));
      return;
    } 
    this.retrofitCallback.onSuccess(this.function.apply(response));
  }
  
  public void onFailure(Call<T> call, Throwable error) {
    this.retrofitCallback.onFail(null, error);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\api\callback\RetrofitCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */