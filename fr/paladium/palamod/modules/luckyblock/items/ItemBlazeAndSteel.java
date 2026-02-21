package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlazeAndSteel extends Item implements ITooltipWiki {
  public ItemBlazeAndSteel() {
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77656_e(64);
    func_77655_b("blaze_and_steel");
    func_111206_d("palamod:blaze_and_steel");
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    p_77624_3_.add("§cAttention ca chauffe");
    super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
  }
  
  public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    if (p_77648_7_ == 0)
      p_77648_5_--; 
    if (p_77648_7_ == 1)
      p_77648_5_++; 
    if (p_77648_7_ == 2)
      p_77648_6_--; 
    if (p_77648_7_ == 3)
      p_77648_6_++; 
    if (p_77648_7_ == 4)
      p_77648_4_--; 
    if (p_77648_7_ == 5)
      p_77648_4_++; 
    if (!p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
      return false; 
    if (p_77648_3_.func_147437_c(p_77648_4_, p_77648_5_, p_77648_6_)) {
      p_77648_3_.func_72908_a(p_77648_4_ + 0.5D, p_77648_5_ + 0.5D, p_77648_6_ + 0.5D, "fire.ignite", 1.0F, field_77697_d
          .nextFloat() * 0.4F + 0.8F);
      p_77648_3_.func_147449_b(p_77648_4_, p_77648_5_, p_77648_6_, BlocksRegister.BLAZE_AND_STEEL_FIRE);
    } 
    p_77648_1_.func_77972_a(1, (EntityLivingBase)p_77648_2_);
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemBlazeAndSteel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */