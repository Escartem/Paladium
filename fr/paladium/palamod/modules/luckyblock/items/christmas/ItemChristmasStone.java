package fr.paladium.palamod.modules.luckyblock.items.christmas;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemChristmasStone extends Item implements ITooltipWiki {
  public ItemChristmasStone() {
    func_77656_e(1);
    func_77625_d(1);
    func_77655_b("christmas_stone");
    func_111206_d("palamod:christmas_stone");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      if (item.func_77942_o()) {
        if (item.func_77978_p().func_74764_b("lastUse")) {
          long lastUse = item.func_77978_p().func_74763_f("lastUse");
          if (System.currentTimeMillis() - lastUse > 86400000L) {
            perormEffect(player);
            item.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
          } else {
            player.func_145747_a((IChatComponent)new ChatComponentText("§cVeuillez attendre " + ((86400000L - 
                  System.currentTimeMillis() - lastUse) / 1000L / 60L / 60L) + " heures avant de pouvoir utiliser cet item"));
          } 
        } else {
          perormEffect(player);
          item.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        } 
      } else {
        perormEffect(player);
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74772_a("lastUse", System.currentTimeMillis());
        item.func_77982_d(compound);
      }  
    return item;
  }
  
  private void perormEffect(EntityPlayer player) {
    int x = (int)player.field_70165_t;
    int y = (int)player.field_70163_u;
    int z = (int)player.field_70161_v;
    for (int i = x - 4; i <= x + 4; i++) {
      for (int j = y - 4; j <= y + 4; j++) {
        for (int k = z - 4; k <= z + 4; k++) {
          BlockPos pos = new BlockPos(i, j, k);
          if (pos.distance(x, y, z) <= 4 && 
            EventUtils.canInteract(player, pos.getX(), pos.getY(), pos.getZ()))
            player.field_70170_p.func_147449_b(pos.getX(), pos.getY(), pos.getZ(), Blocks.field_150350_a); 
        } 
      } 
    } 
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public boolean func_77636_d(ItemStack item) {
    if (item.func_77942_o()) {
      if (item.func_77978_p().func_74764_b("lastUse")) {
        long lastUse = item.func_77978_p().func_74763_f("lastUse");
        return (System.currentTimeMillis() - lastUse > 86400000L);
      } 
      return true;
    } 
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#5.-lucky-block-noel";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\christmas\ItemChristmasStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */