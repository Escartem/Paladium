package fr.paladium.palamod.modules.paladium.utils;

import net.minecraft.world.WorldSettings;

public interface IPlayerController {
  int getCurrentBlockY();
  
  void setCurrentBlockY(int paramInt);
  
  WorldSettings.GameType getGameType();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\IPlayerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */