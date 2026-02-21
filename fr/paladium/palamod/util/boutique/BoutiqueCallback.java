package fr.paladium.palamod.util.boutique;

public interface BoutiqueCallback<T> {
  void onSuccess(T paramT);
  
  void onFail(T paramT, Throwable paramThrowable);
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\boutique\BoutiqueCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */