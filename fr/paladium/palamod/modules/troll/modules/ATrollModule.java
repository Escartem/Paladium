package fr.paladium.palamod.modules.troll.modules;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public abstract class ATrollModule {
  public abstract boolean perform(EntityPlayer paramEntityPlayer, EntityPlayerMP paramEntityPlayerMP, String[] paramArrayOfString);
  
  public abstract String getName();
  
  public abstract String getDescription();
  
  public abstract String getUsage();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\troll\modules\ATrollModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */