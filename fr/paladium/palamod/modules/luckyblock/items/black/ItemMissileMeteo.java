package fr.paladium.palamod.modules.luckyblock.items.black;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.entity.black.EntityMissileMeteo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemMissileMeteo extends Item implements ITooltipWiki {
  public ItemMissileMeteo() {
    func_111206_d("palamod:missile_sol_air");
    func_77625_d(1);
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    if (!player.field_70170_p.field_72995_K) {
      boolean flag = true;
      for (int iy = y + 1; iy < 256; iy++) {
        if (!player.field_70170_p.func_147437_c(x, iy, z))
          flag = false; 
      } 
      if (flag) {
        EntityMissileMeteo missile = new EntityMissileMeteo(player.field_70170_p);
        missile.func_70107_b(x, (y + 2), z);
        missile.field_98038_p = true;
        boolean hasSpawned = player.field_70170_p.func_72838_d((Entity)missile);
        stack.field_77994_a = 0;
      } else {
        player.func_146105_b((IChatComponent)new ChatComponentText("§cLa trajectoire du missile doit être dégagée pour pouvoir être lancé."));
      } 
    } 
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#3.-lucky-block-noir";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\black\ItemMissileMeteo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */