package fr.paladium.palamod.modules.common;

import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palamod.modules.miner.PMiner;
import java.util.List;

public class CommonBridge {
  public static List<String> getOpponents() {
    return CommonModule.getInstance().getCombatTag().getOpponents();
  }
  
  public static boolean isFarmland() {
    return (CommonModule.getInstance().getConfig().getServerType() == ServerType.FARMLAND);
  }
  
  public static boolean isMinage() {
    return (CommonModule.getInstance().getConfig().getServerType() == ServerType.MINAGE);
  }
  
  public static boolean isDimMiner() {
    return PMiner.proxy.isMinerDimension();
  }
  
  public static boolean isEvent() {
    return "EVENT".equals(CommonModule.getInstance().getConfig().getServerName());
  }
  
  public static boolean is(String name) {
    return name.equalsIgnoreCase(CommonModule.getInstance().getConfig().getServerName());
  }
  
  public static boolean isInFight() {
    return CommonModule.getInstance().getCombatTag().inFight();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\common\CommonBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */