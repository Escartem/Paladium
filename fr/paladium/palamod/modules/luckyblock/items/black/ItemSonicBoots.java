package fr.paladium.palamod.modules.luckyblock.items.black;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSonicBoots extends ItemArmor implements ITooltipWiki {
  public ItemSonicBoots(ItemArmor.ArmorMaterial p_i45325_1_) {
    super(p_i45325_1_, 0, 3);
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    if (!world.field_72995_K) {
      if (stack.func_77942_o()) {
        if (stack.func_77958_k() - stack.func_77960_j() > 1 && 
          !WorldGuardUtils.isItemEffectBlocked(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, 
            Item.func_150891_b(stack.func_77973_b())))
          if (world
            .func_147439_a(MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u) - 1, 
              MathHelper.func_76128_c(player.field_70161_v))
            .func_149721_r() && world
            .func_147437_c(MathHelper.func_76128_c(player.field_70165_t), 
              MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v))) {
            world.func_147449_b(MathHelper.func_76128_c(player.field_70165_t), 
                MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), BlocksRegister.FAKE_FIRE);
            stack.func_77972_a(1, (EntityLivingBase)player);
          }  
      } else {
        stack.field_77990_d = new NBTTagCompound();
      } 
    } else {
      player.field_70138_W = 1.0F;
    } 
  }
  
  public boolean func_82789_a(ItemStack stack0, ItemStack stack1) {
    return (stack1.func_77973_b() != null && stack1.func_77973_b() == Items.field_151129_at);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#3.-lucky-block-noir";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\black\ItemSonicBoots.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */