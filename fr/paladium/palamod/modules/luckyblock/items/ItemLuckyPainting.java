package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityLuckyPainting;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class ItemLuckyPainting extends Item implements ITooltipWiki {
  public ItemLuckyPainting() {
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("lucky_painting");
    func_111206_d("palamod:lucky_painting");
    func_77625_d(16);
  }
  
  public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    if (p_77648_7_ == 0)
      return false; 
    if (p_77648_7_ == 1)
      return false; 
    if (!EventUtils.canInteract(p_77648_2_, p_77648_4_, p_77648_5_, p_77648_6_))
      return false; 
    int i1 = Direction.field_71579_d[p_77648_7_];
    EntityHanging entityhanging = createHangingEntity(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, i1);
    if (!p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
      return false; 
    if (entityhanging != null && entityhanging.func_70518_d()) {
      if (!p_77648_3_.field_72995_K)
        p_77648_3_.func_72838_d((Entity)entityhanging); 
      p_77648_1_.field_77994_a--;
    } 
    return true;
  }
  
  private EntityHanging createHangingEntity(World world, int x, int y, int z, int side) {
    return (EntityHanging)new EntityLuckyPainting(world, x, y, z, side);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemLuckyPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */