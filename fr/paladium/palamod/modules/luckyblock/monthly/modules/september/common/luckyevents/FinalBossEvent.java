package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.event.OnPlayerEarnXp;
import fr.paladium.palamod.modules.hunter.entites.EntityCavernousZombie;
import fr.paladium.palamod.modules.hunter.entites.EntityFarmerChicken;
import fr.paladium.palamod.modules.hunter.entites.EntityFlowerMonster;
import fr.paladium.palamod.modules.hunter.entites.EntityMegaCreeper;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class FinalBossEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Le boss final";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 500;
  
  private static final String TEXTURE_PATH = "september/final_boss";
  
  private static final String WARNING_MESSAGE = "&aIl te faut gagner de l’expérience pour voir le boss !";
  
  private static final List<UUID> PLAYERS = new ArrayList<>();
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aIl te faut gagner de l’expérience pour voir le boss !" });
    PLAYERS.add(player.func_110124_au());
  }
  
  @SubscribeEvent
  public void onEarn(OnPlayerEarnXp event) {
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    if (!PLAYERS.contains(player.func_110124_au()))
      return; 
    PLAYERS.remove(player.func_110124_au());
    spawnEntity(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v);
  }
  
  public void spawnEntity(World world, double x, double y, double z) {
    EntityCavernousZombie entityCavernousZombie;
    EntityFarmerChicken entityFarmerChicken;
    EntityMegaCreeper entityMegaCreeper;
    EntityFlowerMonster entityFlowerMonster;
    Random random = new Random();
    int i = random.nextInt(4);
    EntityLivingBase entity = null;
    switch (i) {
      case 0:
        entityCavernousZombie = new EntityCavernousZombie(world);
        break;
      case 1:
        entityFarmerChicken = new EntityFarmerChicken(world);
        break;
      case 2:
        entityMegaCreeper = new EntityMegaCreeper(world);
        break;
      case 3:
        entityFlowerMonster = new EntityFlowerMonster(world);
        break;
    } 
    if (entityFlowerMonster == null)
      return; 
    entityFlowerMonster.func_70107_b(x, y, z);
    world.func_72838_d((Entity)entityFlowerMonster);
  }
  
  public String getName() {
    return "Le boss final";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public String getTexture() {
    return "september/final_boss";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\FinalBossEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */