package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.commands.SoundTestCommand;
import fr.paladium.palamod.modules.luckyblock.structures.sound.impl.FirstSoundStructure;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class TestRythme extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Location location = new Location((Entity)player);
    FirstSoundStructure firstSoundStructure = new FirstSoundStructure(-1, location, (EntityPlayer)player);
    if (!firstSoundStructure.canSpawn()) {
      SoundTestCommand.addWaitingPlayer((EntityPlayer)player);
      PlayerUtils.sendMessage((EntityPlayer)player, firstSoundStructure.getConditions());
      PlayerUtils.sendMessage((EntityPlayer)player, new String[] { "§6Utilisez la command /soundtest pour réessayer." });
      return;
    } 
    firstSoundStructure.spawn();
  }
  
  public String getName() {
    return "Test de rythme";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "june/test_rythme";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\TestRythme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */