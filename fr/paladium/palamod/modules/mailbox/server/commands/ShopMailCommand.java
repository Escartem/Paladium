package fr.paladium.palamod.modules.mailbox.server.commands;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.Command;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class ShopMailCommand extends Command {
  public static final String COMMAND_NAME = "shopmail";
  
  public static final String PERMISSION = "paladium.shopmail";
  
  public String func_71517_b() {
    return "shopmail";
  }
  
  public boolean perform(ICommandSender sender, String[] args) {
    if (ForgeEnv.isDev() && args.length > 0 && args[0].equalsIgnoreCase("example")) {
      if (!(sender instanceof EntityPlayer))
        return false; 
      EntityPlayer player = (EntityPlayer)sender;
      String exampleDevArgs = "{username}|12340|palamod:tile.paladiumLuckyBlock 1 0 {MatS:{id:\"palamod:tile.paladiumLuckyBlock\",Count:1b,Damage:0s},tile:{Slots:[{Item:\"palamod:tile.paladiumLuckyBlock\",Meta:0s,Count:120}],MatS:{id:\"palamod:tile.paladiumLuckyBlock\",Count:1b,Damage:0s},Cap:32,x:0,y:0,z:0,id:\"StorageDrawers:tileDrawersStandard\",Dir:3b}}|minecraft:stone 2 0|CUSTOM MESSAGE";
      exampleDevArgs = exampleDevArgs.replace("{username}", player.func_70005_c_());
      ShopMailData shopMailData = ShopMailData.fromString(sender, exampleDevArgs.split(" "));
      if (shopMailData == null || !shopMailData.isValid()) {
        ChatUtils.sendColoredMessage(sender, new String[] { "§cVeuillez vérifier les arguments de la commande." });
        return true;
      } 
      shopMailData.sendMail(sender);
      return true;
    } 
    ShopMailData data = ShopMailData.fromString(sender, args);
    if (data == null || !data.isValid()) {
      ChatUtils.sendColoredMessage(sender, new String[] { "§cVeuillez vérifier les arguments de la commande." });
      return false;
    } 
    data.sendMail(sender);
    return true;
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/shopmail <username or UUID>|<order id>|<item> <quantity> <meta> (NBT) <- repeat for each item>|<custom message>";
  }
  
  public String getCommandUsage(ICommandSender sender, String[] strings) {
    return func_71518_a(sender);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\server\commands\ShopMailCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */