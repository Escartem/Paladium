package fr.paladium.palatrade.server.event;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palatrade.common.utils.Trade;
import net.minecraft.entity.player.EntityPlayerMP;

public class TradeSuccesEvent extends Event {
  private final EntityPlayerMP firstPlayer;
  
  private final EntityPlayerMP secondPlayer;
  
  private final Trade firstTrade;
  
  private final Trade secondTrade;
  
  private TradeSuccesEvent(EntityPlayerMP firstPlayer, EntityPlayerMP secondPlayer, Trade firstTrade, Trade secondTrade) {
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
    this.firstTrade = firstTrade;
    this.secondTrade = secondTrade;
  }
  
  public static TradeSuccesEvent of(EntityPlayerMP firstPlayer, EntityPlayerMP secondPlayer, Trade firstTrade, Trade secondTrade) {
    return new TradeSuccesEvent(firstPlayer, secondPlayer, firstTrade, secondTrade);
  }
  
  public EntityPlayerMP getFirstPlayer() {
    return this.firstPlayer;
  }
  
  public EntityPlayerMP getSecondPlayer() {
    return this.secondPlayer;
  }
  
  public Trade getFirstTrade() {
    return this.firstTrade;
  }
  
  public Trade getSecondTrade() {
    return this.secondTrade;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\server\event\TradeSuccesEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */