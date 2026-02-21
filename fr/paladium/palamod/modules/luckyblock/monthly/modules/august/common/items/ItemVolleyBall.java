package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities.EntityVolleyBall;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVolleyBall extends Item {
  public static final String NAME = "volley_ball";
  
  public ItemVolleyBall() {
    func_77655_b("volley_ball");
    func_111206_d("palamod:volley_ball");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
    world.func_72838_d((Entity)new EntityVolleyBall(world, (EntityLivingBase)player));
    MonthlyUtils.decrementCurrentItem(player, stack);
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemVolleyBall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */