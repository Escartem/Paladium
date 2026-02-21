package fr.paladium.palatrade.server.manager;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.palatrade.common.utils.Trade;

class null implements CresusCallback<Double> {
  public void onSuccess(Double value) {
    t1.setMaxMoney(value.doubleValue());
  }
  
  public void onFail(Double value, Throwable error) {
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\server\manager\TradeManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */