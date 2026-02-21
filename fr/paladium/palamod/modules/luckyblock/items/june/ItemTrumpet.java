package fr.paladium.palamod.modules.luckyblock.items.june;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemTrumpet extends Item implements ITooltipInformations {
  public ItemTrumpet() {
    func_77655_b("trumpet");
    func_111206_d("palamod:trumpet");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      NBTTagCompound tag = itemStack.func_77978_p();
      if (tag == null)
        tag = new NBTTagCompound(); 
      long cooldown = 0L;
      if (tag != null && tag.func_74764_b("cooldown"))
        cooldown = tag.func_74763_f("cooldown"); 
      if (TimeUtil.now() - cooldown > 60L) {
        PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound("trumpet"), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, player.field_70165_t, player.field_70163_u, player.field_70161_v, 24.0D));
        tag.func_74772_a("cooldown", TimeUtil.now());
        itemStack.func_77982_d(tag);
      } 
    } 
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Joue un son très agréable.", "Ne fonctionne qu’une fois par minute." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\june\ItemTrumpet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */