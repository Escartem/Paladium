package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.data;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.ChanceObject;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.RandomObjectPicker;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

public class InvisibleData {
  public static final long DURATION = TimeUnit.MINUTES.toMillis(15L);
  
  private static final double RADIUS = 15.0D;
  
  private final UUID playerUniqueId;
  
  private final long eventExpirationMillis;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public long getEventExpirationMillis() {
    return this.eventExpirationMillis;
  }
  
  public InvisibleData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.eventExpirationMillis = System.currentTimeMillis() + DURATION;
  }
  
  private void giveReward(EntityPlayerMP player) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aTu as réussi à rester invisible, voici ta récompense !" });
    RandomObjectPicker<ItemStack> picker = new RandomObjectPicker(Arrays.asList(new ChanceObject[] { ChanceObject.of(15.0D, new ItemStack(ItemsRegister.GHOSTCAPE)), 
            ChanceObject.of(16.0D, new ItemStack(ItemsRegister.INVISIBLE_HELMET)), 
            ChanceObject.of(16.0D, new ItemStack(ItemsRegister.INVISIBLE_CHESTPLATE)), 
            ChanceObject.of(16.0D, new ItemStack(ItemsRegister.INVISIBLE_LEGGINGS)), 
            ChanceObject.of(16.0D, new ItemStack(ItemsRegister.INVISIBLE_BOOTS)), 
            ChanceObject.of(16.0D, new ItemStack(ItemsRegister.GHOSTLY_SWORD)) }));
    ItemUtils.spawnItemAtEntity((Entity)player, (ItemStack)picker.pickRandomObject());
  }
  
  public boolean isExpired(EntityPlayerMP player, long now) {
    if (player == null)
      return true; 
    if (now >= this.eventExpirationMillis) {
      giveReward(player);
      return true;
    } 
    AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a(player.field_70165_t - 15.0D, player.field_70163_u - 15.0D, player.field_70161_v - 15.0D, player.field_70165_t + 15.0D, player.field_70163_u + 15.0D, player.field_70161_v + 15.0D);
    List<EntityPlayerMP> players = player.field_70170_p.func_72872_a(EntityPlayerMP.class, boundingBox);
    for (EntityPlayerMP other : players) {
      if (other.equals(player))
        continue; 
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu as été repéré par " + other.getDisplayName() + " !" });
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\server\data\InvisibleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */