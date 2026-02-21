package fr.paladium.palamod.modules.luckyblock.events.easter;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.data.event.SoftLikeLambData;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.rework.SoftLikeALambEvent;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class RainbowSheepListener {
  public static final HashSet<UUID> SHEEP_SET = new HashSet<>();
  
  @SubscribeEvent
  public void onLivingHurt(LivingAttackEvent event) {
    Entity entity = event.entity;
    if (entity == null || entity.field_70170_p.field_72995_K)
      return; 
    DamageSource source = event.source;
    if (source == null || !(source.func_76346_g() instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)source.func_76346_g();
    if (player == null)
      return; 
    SoftLikeLambData data = SoftLikeALambEvent.getInstance().getData(player);
    if (data != null)
      data.setDamaged(true); 
  }
  
  @SubscribeEvent
  public void onLivingDeath(LivingDeathEvent event) {
    if (event.entity == null || event.entity.field_70170_p.field_72995_K || !(event.entity instanceof EntitySheep))
      return; 
    EntitySheep sheep = (EntitySheep)event.entity;
    UUID uniqueId = sheep.func_110124_au();
    if (SHEEP_SET.removeIf(uuid -> uuid.equals(uniqueId)))
      ItemUtils.spawnItemInWorld(sheep.field_70170_p, sheep.field_70165_t, sheep.field_70163_u, sheep.field_70161_v, new ItemStack(ItemsRegister.RAINBOW_LEATHER, 5)); 
  }
  
  @SubscribeEvent
  public void onEntityDeath(LivingDeathEvent event) {
    if (!(event.entity instanceof EntitySheep))
      return; 
    World world = event.entity.field_70170_p;
    if (world.field_72995_K)
      return; 
    EntitySheep entity = (EntitySheep)event.entity;
    String nameTag = entity.func_94057_bL();
    if (nameTag == null || !nameTag.equals("§bPascal"))
      return; 
    ItemUtils.spawnItemInWorld(world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, new ItemStack(ItemsRegister.SHEEP_NOISE_BOX));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\easter\RainbowSheepListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */