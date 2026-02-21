package fr.paladium.palamod.modules.shop.utils;

import fr.paladium.bigbrother.lib.metrics.MetricCounter;
import fr.paladium.palamod.metrics.bigbrother.BigBrotherImpl;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class BigBrotherUtils {
  public static void buy(EntityPlayerMP player, String itemName, int amount, double price) {
    BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)player), "shopSpent", price);
    MetricCounter metricCounterPrice = (new MetricCounter()).setName("adminshop_buy_price").register(BigBrotherImpl.instance.getApi());
    metricCounterPrice.newInstance(itemName, price);
    MetricCounter metricCounter = (new MetricCounter()).setName("adminshop_buy_quantity").register(BigBrotherImpl.instance.getApi());
    metricCounter.newInstance(itemName, amount);
  }
  
  public static void sell(EntityPlayerMP player, String itemName, int amount, double price) {
    BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)player), "shopSell", price);
    MetricCounter metricCounterPrice = (new MetricCounter()).setName("adminshop_sell_price").register(BigBrotherImpl.instance.getApi());
    metricCounterPrice.newInstance(itemName, price);
    MetricCounter metricCounter = (new MetricCounter()).setName("adminshop_sell_quantity").register(BigBrotherImpl.instance.getApi());
    metricCounter.newInstance(itemName, amount);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\sho\\utils\BigBrotherUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */