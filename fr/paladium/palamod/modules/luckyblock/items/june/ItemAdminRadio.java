package fr.paladium.palamod.modules.luckyblock.items.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.helios.module.vanish.ModuleVanish;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayEmoteNoPvp;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;

public class ItemAdminRadio extends Item implements ITooltipInformations {
  public ItemAdminRadio() {
    func_77655_b("admin_radio");
    func_111206_d("palamod:admin_radio");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77627_a(true);
    func_77656_e(5);
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
      for (Object o : world.field_73010_i) {
        if (o instanceof EntityPlayerMP) {
          EntityPlayerMP p = (EntityPlayerMP)o;
          if (!p.field_71075_bZ.field_75098_d && !ModuleVanish.getInstance().isVanished() && PlayerLuckyBlockProperties.get((EntityPlayer)p).isAdmindanseAllowed())
            try {
              CraftServer server = (CraftServer)Bukkit.getServer();
              if (server.getPlayer(player.func_110124_au()).hasPermission("palamod.cosmetiques.emote.floss")) {
                PalaMod.getNetwork().sendTo((IMessage)new PacketPlayEmoteNoPvp("floss"), p);
                PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("admin_radio"), p);
              } 
            } catch (Exception exception) {} 
        } 
      } 
      PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("admin_radio"), (EntityPlayerMP)player);
      if (itemStack.func_77960_j() == itemStack.func_77958_k()) {
        world.func_72956_a((Entity)player, "random.break", 0.8F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
        itemStack.field_77994_a--;
      } 
    } 
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Joue un son entraînant qui fera danser les administrateurs." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\june\ItemAdminRadio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */