package fr.paladium.palamod.modules.luckyblock.items.halloween;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.projectile.EntityWeb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpiderGlove extends ItemBow implements ITooltipWiki {
  public ItemSpiderGlove() {
    func_77656_e(128);
    func_77625_d(1);
    func_77655_b("spiderglove");
    func_111206_d("palamod:spiderglove");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public EnumAction func_77661_b(ItemStack p_77661_1_) {
    return EnumAction.bow;
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    p_77659_3_.func_71008_a(p_77659_1_, func_77626_a(p_77659_1_));
    return p_77659_1_;
  }
  
  public void func_77615_a(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
    int j = func_77626_a(p_77615_1_) - p_77615_4_;
    float f = j / 20.0F;
    f = (f * f + f * 2.0F) / 3.0F;
    if (f < 0.1D)
      return; 
    if (f > 1.0F)
      f = 1.0F; 
    EntityWeb entityarrow = new EntityWeb(p_77615_2_, (EntityLivingBase)p_77615_3_, f * 2.0F);
    p_77615_2_.func_72956_a((Entity)p_77615_3_, "random.bow", 1.0F, 1.0F / (field_77697_d
        .nextFloat() * 0.4F + 1.2F) + f * 0.5F);
    if (!p_77615_2_.field_72995_K) {
      p_77615_2_.func_72838_d((Entity)entityarrow);
      p_77615_1_.func_77972_a(1, (EntityLivingBase)p_77615_3_);
    } 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#4.-lucky-block-halloween";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\halloween\ItemSpiderGlove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */