package fr.paladium.palamod.modules.luckyblock.monthly.tickable;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.listener.TickableServerListener;
import java.util.HashSet;
import net.minecraftforge.common.MinecraftForge;

public class TickableFeature {
  private static TickableFeature instance;
  
  private final HashSet<ATickable<?>> tickables;
  
  public HashSet<ATickable<?>> getTickables() {
    return this.tickables;
  }
  
  public TickableFeature() {
    instance = this;
    this.tickables = new HashSet<>();
    registerListeners();
  }
  
  public void addTickable(ATickable<?> tickable) {
    if (this.tickables.contains(tickable))
      return; 
    this.tickables.add(tickable);
  }
  
  private void registerListeners() {
    Object listener = new TickableServerListener(this);
    FMLCommonHandler.instance().bus().register(listener);
    MinecraftForge.EVENT_BUS.register(listener);
  }
  
  public static TickableFeature getInstance() {
    if (instance == null)
      instance = new TickableFeature(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\tickable\TickableFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */