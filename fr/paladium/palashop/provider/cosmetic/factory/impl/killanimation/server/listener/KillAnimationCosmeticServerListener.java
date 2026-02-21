package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.entity.EntityKillAnimationCosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto.KillAnimationCosmetic;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class KillAnimationCosmeticServerListener {
  @SubscribeEvent
  public void onKill(LivingDeathEvent event) {
    if ((!(event.entity instanceof net.minecraft.entity.player.EntityPlayer) && !ForgeEnv.isIDE()) || !(event.source.func_76346_g() instanceof EntityPlayerMP))
      return; 
    Entity target = event.entity;
    EntityPlayerMP player = (EntityPlayerMP)event.source.func_76346_g();
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(KillAnimationCosmeticFactory.ID);
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    Optional<ICosmetic> optionalCosmetic = equippedCosmetic.get(0);
    if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof KillAnimationCosmetic))
      return; 
    KillAnimationCosmetic cosmetic = (KillAnimationCosmetic)optionalCosmetic.get();
    World world = event.entity.field_70170_p;
    world.func_72838_d((Entity)new EntityKillAnimationCosmetic(world, cosmetic, target, Math.abs(player.field_70177_z)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\server\listener\KillAnimationCosmeticServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */