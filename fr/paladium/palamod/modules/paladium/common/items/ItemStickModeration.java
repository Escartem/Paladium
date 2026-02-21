package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemStickModeration extends Item {
  public ItemStickModeration() {
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77625_d(1);
    func_77655_b("stick_moderation");
    func_111206_d("palamod:stick_moderation");
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K && BukkitUtils.hasPermission(player.func_110124_au(), "palamod.scan_stick"))
      PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_STICK_MODERATION, true), (EntityPlayerMP)player); 
    return super.func_77659_a(stack, world, player);
  }
  
  public List<String> getResultPlayer(ItemStack item, World world, EntityPlayerMP player, int itemId, int radius) {
    if (!world.field_72995_K && 
      BukkitUtils.hasPermission(player.func_110124_au(), "palamod.scan_stick")) {
      List<String> players = new ArrayList<>();
      Item filter = Item.func_150899_d(itemId);
      List<?> playerList = (radius > 0) ? world.field_73010_i : (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
      for (Object entity : playerList) {
        if (!(entity instanceof EntityPlayer))
          continue; 
        EntityPlayer pl = (EntityPlayer)entity;
        if (radius > 0) {
          if (Math.abs(getPlaneDistanceToEntity((Entity)pl, (Entity)player)) < radius && filter != null && pl.field_71071_by.func_146028_b(filter))
            players.add(pl.getDisplayName()); 
          continue;
        } 
        if (filter != null && pl.field_71071_by.func_146028_b(filter))
          players.add(pl.getDisplayName()); 
      } 
      return players;
    } 
    return null;
  }
  
  private float getPlaneDistanceToEntity(Entity entity, Entity target) {
    float f = (float)(entity.field_70165_t - target.field_70165_t);
    float f2 = (float)(entity.field_70161_v - target.field_70161_v);
    return MathHelper.func_76129_c(f * f + f2 * f2);
  }
  
  public String func_77653_i(ItemStack stack) {
    return "§eBaton de scan";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemStickModeration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */