package fr.paladium.palamod.modules.enderchest.event;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PaladiumEnderChestListener {
  @SubscribeEvent
  public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent e) {
    EntityPlayer p = e.player;
    int size = 4;
    int maxSize = 5;
    try {
      if (BukkitUtils.hasPermission(p.func_110124_au(), "paladiumenderchest.upgrade.1"))
        size = 1; 
      if (BukkitUtils.hasPermission(p.func_110124_au(), "paladiumenderchest.upgrade.2"))
        size = 2; 
      if (BukkitUtils.hasPermission(p.func_110124_au(), "paladiumenderchest.upgrade.3"))
        size = 3; 
      if (BukkitUtils.hasPermission(p.func_110124_au(), "paladiumenderchest.upgrade.4"))
        size = 4; 
      if (BukkitUtils.hasPermission(p.func_110124_au(), "paladiumenderchest.add.1"))
        size++; 
      if (size > maxSize)
        size = maxSize; 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    String name = "§8EnderChest (§e" + p.func_70005_c_() + "§8)";
    if (name.length() >= 32)
      name = "EnderChest (" + p.func_70005_c_() + ")"; 
    try {
      ReflectionHelper.setPrivateValue(InventoryBasic.class, p.func_71005_bN(), name, new String[] { "inventoryTitle", "field_70483_a" });
    } catch (Exception e2) {
      e2.printStackTrace();
    } 
    try {
      ReflectionHelper.setPrivateValue(InventoryBasic.class, p.func_71005_bN(), Integer.valueOf(size * 9), new String[] { "slotsCount", "field_70481_b" });
    } catch (Exception e2) {
      e2.printStackTrace();
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onInteractEnderChest(PlayerInteractEvent e) {
    if (e.world.field_72995_K || e.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
      return; 
    Block block = e.world.func_147439_a(e.x, e.y, e.z);
    if (block instanceof net.minecraft.block.BlockEnderChest || block instanceof fr.paladium.palamod.modules.enderchest.block.BlockPaladiumEnderChest) {
      boolean canOpen = true;
      try {
        if (!BukkitUtils.hasPermission(e.entityPlayer.func_110124_au(), "paladiumenderchest.use"))
          canOpen = false; 
      } catch (NoClassDefFoundError exception) {
        exception.printStackTrace();
        canOpen = false;
      } 
      if (!canOpen) {
        e.useBlock = Event.Result.DENY;
        if (e.isCancelable())
          e.setCanceled(true); 
        e.entityPlayer.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cImpossible de charger votre EnderChest."));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\event\PaladiumEnderChestListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */