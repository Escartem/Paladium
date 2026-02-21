package fr.paladium.palaforgeutils.lib.api.callback;

public interface IRetrofitCallback<T> {
  void onSuccess(T paramT);
  
  void onFail(T paramT, Throwable paramThrowable);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\api\callback\IRetrofitCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */