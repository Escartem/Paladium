package fr.paladium.palamod.modules.luckyblock.commands;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import org.bukkit.Bukkit;

public class EndiumLuckyBlockCommand extends CommandBase {
  public String func_71517_b() {
    return "endiumluckyblock";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "endiumluckyblock <player> <number>";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (args.length == 2) {
      int n = 1;
      try {
        n = Integer.parseInt(args[1]);
      } catch (Exception e) {
        sender.func_145747_a((IChatComponent)new ChatComponentText(func_71518_a(sender)));
        return;
      } 
      if (sender instanceof MinecraftServer) {
        EntityPlayerMP target = null;
        for (WorldServer world : (MinecraftServer.func_71276_C()).field_71305_c) {
          for (Object pl : world.field_73010_i) {
            EntityPlayerMP p = (EntityPlayerMP)pl;
            if (p.func_70005_c_().equalsIgnoreCase(args[0]))
              target = p; 
          } 
        } 
        if (target != null) {
          ItemStack item = new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, n);
          NBTTagCompound compound = new NBTTagCompound();
          compound.func_74768_a("version", 1);
          item.func_77982_d(compound);
          PlayerUtil.addItemStackToInventory(item, target.field_71071_by);
        } else {
          sender.func_145747_a((IChatComponent)new ChatComponentText("Ce joueur n'existe pas."));
        } 
      } else if (sender instanceof EntityPlayerMP) {
        EntityPlayerMP player = (EntityPlayerMP)sender;
        try {
          if (Bukkit.getPlayer(player.func_110124_au())
            .hasPermission("palamod.cmd.endiumluckyblock")) {
            EntityPlayerMP target = null;
            for (Object pl : player.field_70170_p.field_73010_i) {
              EntityPlayerMP p = (EntityPlayerMP)pl;
              if (p.func_70005_c_().equalsIgnoreCase(args[0]))
                target = p; 
            } 
            if (target != null) {
              ItemStack item = new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, n);
              NBTTagCompound compound = new NBTTagCompound();
              compound.func_74768_a("version", 1);
              item.func_77982_d(compound);
              PlayerUtil.addItemStackToInventory(item, target.field_71071_by);
            } else {
              PlayerUtils.sendMessage((EntityPlayer)player, "§cLe joueur §e" + args[0] + " §cn'existe pas.");
            } 
          } 
        } catch (Exception e) {
          PlayerUtils.sendMessage((EntityPlayer)player, "§cVous n'avez pas la permission d'executer cette commande.");
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\EndiumLuckyBlockCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */