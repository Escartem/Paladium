package fr.paladium.palamod.modules.luckyblock.commands;

import fr.paladium.palamod.Constants;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.config.ConfigManager;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class LuckyBlockCommand extends CommandBase {
  public String func_71517_b() {
    return "luckyblock";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "luckyblock <id>";
  }
  
  public List func_71514_a() {
    return Arrays.asList(new String[] { "lb", "luckyb", "luckyblocks", "lblock", "lblocks" });
  }
  
  public void func_71515_b(ICommandSender sender, String[] arguments) {
    try {
      EntityPlayerMP player = func_71521_c(sender);
      if (Constants.MOD_ENV != Constants.Environment.DEV);
      if (arguments.length == 1) {
        ItemStack lb = new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK);
        try {
          int event = Integer.valueOf(arguments[0]).intValue();
          if ((LuckyEvents.values()).length > event) {
            lb.func_77983_a("event", (NBTBase)new NBTTagInt(event));
            lb.func_151001_c("LuckyBlock §e" + 
                LuckyEvents.values()[event].getEvent().getName());
            player.field_71071_by.func_70441_a(lb);
          } else {
            throw new Exception();
          } 
        } catch (Exception e) {
          PlayerUtils.sendMessage((EntityPlayer)player, "§e" + arguments[0] + " §cn'est pas un id d'event correct.");
        } 
      } else if (arguments.length == 2) {
        if (arguments[0].equalsIgnoreCase("lock")) {
          try {
            int event = Integer.valueOf(arguments[1]).intValue();
            if ((LuckyEvents.values()).length > event) {
              if (ConfigManager.hasKey("luckyblock", String.valueOf(event))) {
                ConfigManager.writeConfig("luckyblock", String.valueOf(event), 
                    !ConfigManager.getBoolean("luckyblock", String.valueOf(event)));
                if (ConfigManager.getBoolean("luckyblock", String.valueOf(event))) {
                  PlayerUtils.sendMessage((EntityPlayer)player, "§e" + 
                      LuckyEvents.values()[event].getEvent().getName() + " §cà été bloqué");
                } else {
                  PlayerUtils.sendMessage((EntityPlayer)player, "§e" + 
                      LuckyEvents.values()[event].getEvent().getName() + " §qà été débloqué");
                } 
              } else {
                ConfigManager.writeConfig("luckyblock", String.valueOf(event), true);
                PlayerUtils.sendMessage((EntityPlayer)player, "§e" + 
                    LuckyEvents.values()[event].getEvent().getName() + " §cà été bloqué");
              } 
            } else {
              throw new Exception();
            } 
          } catch (Exception e) {
            PlayerUtils.sendMessage((EntityPlayer)player, "§e" + arguments[1] + " §cn'est pas un id d'event correct.");
          } 
        } else if (arguments[0].equalsIgnoreCase("list")) {
          try {
            int page = Integer.parseInt(arguments[1]);
            if (page * 10 > (LuckyEvents.values()).length)
              throw new Exception(); 
            PlayerUtils.sendHelpMessage((EntityPlayer)player, page);
          } catch (Exception e) {
            PlayerUtils.sendMessage((EntityPlayer)player, "§e" + arguments[1] + " §cn'est pas une page valide");
          } 
        } 
      } else {
        PlayerUtils.sendHelpMessage((EntityPlayer)player, 0);
      } 
    } catch (Exception e) {
      e.printStackTrace();
      sender.func_145747_a((IChatComponent)new ChatComponentText("Vous devez être un joueur."));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\LuckyBlockCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */