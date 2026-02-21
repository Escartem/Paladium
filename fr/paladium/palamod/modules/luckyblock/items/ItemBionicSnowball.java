package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityBionicSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBionicSnowball extends ItemSnowball implements ITooltipWiki {
  public ItemBionicSnowball() {
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77655_b("bionic_snowball");
    func_111206_d("palamod:bionic_snowball");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
      item.field_77994_a--; 
    world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (field_77697_d
        .nextFloat() * 0.4F + 0.8F));
    if (!world.field_72995_K)
      world.func_72838_d((Entity)new EntityBionicSnowball(world, (EntityLivingBase)player)); 
    return item;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemBionicSnowball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */