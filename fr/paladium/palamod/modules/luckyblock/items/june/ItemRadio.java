package fr.paladium.palamod.modules.luckyblock.items.june;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemRadio extends Item implements ITooltipInformations {
  public ItemRadio() {
    func_77655_b("radio");
    func_111206_d("palamod:radio");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77627_a(true);
    func_77656_e(10);
    setNoRepair();
  }
  
  public boolean func_77616_k(ItemStack stack) {
    return false;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      itemStack.func_77964_b(itemStack.func_77960_j() + 1);
      int radius = 15;
      for (Object o : world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(player.field_70165_t - radius, player.field_70163_u - radius, player.field_70161_v - radius, player.field_70165_t + radius, player.field_70163_u + radius, player.field_70161_v + radius))) {
        if (o instanceof EntityPlayerMP) {
          EntityPlayerMP p = (EntityPlayerMP)o;
          p.func_70690_d(new PotionEffect(PLuckyBlock.RADIO.field_76415_H, 400));
        } 
      } 
      PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound("radio"), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, player.field_70165_t, player.field_70163_u, player.field_70161_v, radius));
      if (itemStack.func_77960_j() == itemStack.func_77958_k()) {
        world.func_72956_a((Entity)player, "random.break", 0.8F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
        itemStack.field_77994_a--;
      } 
    } 
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Joue un son entraînant qui fera danser les joueurs proches." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\june\ItemRadio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */