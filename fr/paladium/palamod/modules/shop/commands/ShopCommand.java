package fr.paladium.palamod.modules.shop.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import fr.paladium.palamod.modules.shop.api.ShopManager;
import fr.paladium.palamod.modules.shop.data.ItemCategory;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class ShopCommand extends CommandBase {
  public String func_71517_b() {
    return "shop";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "shop";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_SHOP, true), player);
      ShopManager.getInstance().sendItems(player, ItemCategory.ALL, 0, null);
    } 
  }
  
  public int compareTo(ICommand command) {
    return func_71517_b().toLowerCase().compareTo(command.func_71517_b().toLowerCase());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\commands\ShopCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */