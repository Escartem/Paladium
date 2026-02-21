package fr.paladium.palamod.modules.luckyblock.items.june;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemParalyzingRadio extends Item implements ITooltipInformations {
  public ItemParalyzingRadio() {
    func_77655_b("paralyzing_radio");
    func_111206_d("palamod:paralyzing_radio");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77627_a(true);
    func_77656_e(20);
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
      NBTTagCompound tag = itemStack.func_77978_p();
      if (tag == null)
        tag = new NBTTagCompound(); 
      long cooldown = 0L;
      if (tag != null && tag.func_74764_b("cooldown"))
        cooldown = tag.func_74763_f("cooldown"); 
      if (TimeUtil.now() - cooldown > 3600L) {
        itemStack.func_77964_b(itemStack.func_77960_j() + 1);
        int radius = 10;
        for (Object o : world.func_72872_a(EntityLiving.class, AxisAlignedBB.func_72330_a(player.field_70165_t - radius, player.field_70163_u - radius, player.field_70161_v - radius, player.field_70165_t + radius, player.field_70163_u + radius, player.field_70161_v + radius))) {
          if (o instanceof EntityLiving) {
            EntityLiving mob = (EntityLiving)o;
            mob.func_70661_as().func_75499_g();
            mob.func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 200, 9));
          } 
        } 
        PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound("paralyzing_radio"), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, player.field_70165_t, player.field_70163_u, player.field_70161_v, radius));
        if (itemStack.func_77960_j() == itemStack.func_77958_k()) {
          world.func_72956_a((Entity)player, "random.break", 0.8F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
          itemStack.field_77994_a--;
        } 
        tag.func_74772_a("cooldown", TimeUtil.now());
        itemStack.func_77982_d(tag);
      } 
    } 
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Permet de paralyser les monstres proches pendant 10 secondes.", "Ne peut être utilisé que toutes les 60 minutes." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\june\ItemParalyzingRadio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */