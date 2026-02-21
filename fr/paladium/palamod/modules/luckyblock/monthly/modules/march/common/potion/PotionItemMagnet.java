package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.awt.Color;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class PotionItemMagnet extends APotion {
  public PotionItemMagnet() {
    super("item_magnet", true, Color.ORANGE);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {
    float magnet = 0.05F;
    float value = 5.0F;
    World world = player.field_70170_p;
    AxisAlignedBB axis = AxisAlignedBB.func_72330_a(player.field_70165_t - value, player.field_70163_u - value, player.field_70161_v - value, player.field_70165_t + value, player.field_70163_u + value, player.field_70161_v + value);
    List<Entity> items = world.func_72872_a(Entity.class, axis);
    items.forEach(item -> {
          if (item instanceof EntityItem) {
            EntityItem entityItem = (EntityItem)item;
            entityItem.field_70159_w += (player.field_70165_t - entityItem.field_70165_t > 0.0D) ? magnet : -magnet;
            entityItem.field_70181_x += (player.field_70163_u - entityItem.field_70163_u > 0.0D) ? magnet : -magnet;
            entityItem.field_70179_y += (player.field_70161_v - entityItem.field_70161_v > 0.0D) ? magnet : -magnet;
          } 
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\potion\PotionItemMagnet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */