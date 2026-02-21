package fr.paladium.palaforgeutils.lib.api.callback;

public abstract class ARetrofitCallback<T> implements IRetrofitCallback<T> {
  public void onFail(T value, Throwable error) {
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\api\callback\ARetrofitCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */