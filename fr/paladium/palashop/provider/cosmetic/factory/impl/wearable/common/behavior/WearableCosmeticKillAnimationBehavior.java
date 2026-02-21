package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.behavior;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palashop.provider.cosmetic.common.dto.behavior.CosmeticBehavior;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.network.BBPacketAnimateWearableCosmetic;
import java.util.List;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class WearableCosmeticKillAnimationBehavior extends CosmeticBehavior<ICosmetic> {
  private static final String ANIMATION_NAME = "kill";
  
  public WearableCosmeticKillAnimationBehavior() {
    super("wearable_kill_animation", new String[] { WearableCosmeticFactory.ID });
  }
  
  @SubscribeEvent
  public void onKill(LivingDeathEvent event) {
    if ((!(event.entity instanceof EntityPlayer) && !ForgeEnv.isIDE()) || !(event.source.func_76346_g() instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.source.func_76346_g();
    List<ICosmetic> cosmetics = getApplicableList((Entity)player);
    if (cosmetics.isEmpty())
      return; 
    EntityTracker tracker = ((WorldServer)player.field_70170_p).func_73039_n();
    for (ICosmetic cosmetic : cosmetics) {
      for (EntityPlayer entityPlayer : tracker.getTrackingPlayers((Entity)player)) {
        if (!(entityPlayer instanceof EntityPlayerMP))
          continue; 
        (new BBPacketAnimateWearableCosmetic(player.func_145782_y(), cosmetic.getId(), "kill", false)).send((EntityPlayerMP)entityPlayer);
      } 
      (new BBPacketAnimateWearableCosmetic(player.func_145782_y(), cosmetic.getId(), "kill", true)).send(player);
    } 
  }
  
  public void onRender(@NonNull Entity entity, @NonNull ICosmetic cosmetic) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
  }
  
  public void onTick(@NonNull Entity entity, @NonNull ICosmetic cosmetic) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\common\behavior\WearableCosmeticKillAnimationBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */