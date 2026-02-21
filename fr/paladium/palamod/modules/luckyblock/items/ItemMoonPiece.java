package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import java.time.Duration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemMoonPiece extends Item {
  public ItemMoonPiece() {
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(16);
    func_77655_b("moon_piece");
    func_111206_d("palamod:moon_piece");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K && !WorldGuardUtils.isItemEffectBlocked((Entity)player, item)) {
      long date = 0L;
      if (item.field_77994_a == 1) {
        if (!item.func_77942_o())
          item.func_77982_d(new NBTTagCompound()); 
        if (item.func_77978_p().func_74764_b("moon_piece"))
          date = item.func_77978_p().func_74763_f("moon_piece"); 
        Duration duration = Duration.between(TimeUtil.fromLong(date), TimeUtil.nowZoned());
        if (date == 0L || duration.toMinutes() > 10L) {
          item.func_77978_p().func_74772_a("moon_piece", TimeUtil.now());
          player.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 1200, 15));
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§cVeuillez attendre §e" + (10L - duration.toMinutes()) + " minutes."));
        } 
      } 
    } 
    return item;
  }
  
  public boolean func_77636_d(ItemStack item) {
    long date = 0L;
    if (item.func_77942_o() && item.func_77978_p().func_74764_b("moon_piece"))
      date = item.func_77978_p().func_74763_f("moon_piece"); 
    Duration duration = Duration.between(TimeUtil.fromLong(date), TimeUtil.nowZoned());
    if (date == 0L || duration.toMinutes() > 10L)
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemMoonPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */