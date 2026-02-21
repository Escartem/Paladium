package fr.paladium.pet.common.utils;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.Calendar;
import java.util.Optional;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class PetUtils {
  public static final char COLOR_CHAR = '&';
  
  public static boolean aprilFool = false;
  
  public static void initAprilFool() {
    Calendar calendar = Calendar.getInstance();
    aprilFool = (calendar.get(2) == 2 && calendar.get(5) == 26);
  }
  
  public static double getValueAsPercent(double value) {
    if (value < 0.0D)
      return 0.0D; 
    return value / 100.0D;
  }
  
  public static boolean canUseCraft(EntityPlayer player, ItemStack stack) {
    Optional<IJobsPlayer> optJobsPlayer = PalaJobsAPI.inst().getJobsPlayer(player);
    if (!optJobsPlayer.isPresent())
      return false; 
    return ((IJobsPlayer)optJobsPlayer.get()).canCraft(stack);
  }
  
  public static boolean canInteract(EntityPlayer player, int x, int y, int z) {
    return canInteract(player, player.field_70170_p.func_147439_a(x, y, z), x, y, z);
  }
  
  public static boolean canInteract(EntityPlayer player, Block block, int x, int y, int z) {
    if (y < 0 || y > 256 || block == Blocks.field_150357_h)
      return false; 
    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, player.field_70170_p, Blocks.field_150350_a, 0, player);
    event.setResult(Event.Result.DENY);
    MinecraftForge.EVENT_BUS.post((Event)event);
    return !event.isCanceled();
  }
  
  public static boolean canInteractBedrock(EntityPlayer player, int x, int y, int z) {
    if (y <= 0 || y > 256)
      return false; 
    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, player.field_70170_p, Blocks.field_150350_a, 0, player);
    event.setResult(Event.Result.DENY);
    MinecraftForge.EVENT_BUS.post((Event)event);
    return !event.isCanceled();
  }
  
  public static void sendPrefixedMessage(ICommandSender sender, String... messages) {
    if (sender == null || messages == null || messages.length == 0)
      return; 
    sender.func_145747_a((IChatComponent)new ChatComponentText(TTT.format(PetTranslateEnum.MESSAGE_PREFIX.getId(), new Object[0]) + ChatColor.translateAlternateColorCodes('&', messages[0])));
    for (int i = 1; i < messages.length; i++)
      sender.func_145747_a((IChatComponent)new ChatComponentText(ChatColor.translateAlternateColorCodes('&', messages[i]))); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\commo\\utils\PetUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */