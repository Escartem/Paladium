package fr.paladium.palamod.modules.miner.command;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.miner.item.ItemGodPickaxe;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.StringUtils;

public class CommandGodPickaxe extends CommandBase {
  public String func_71517_b() {
    return "godpickaxe";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/godpickaxe <create/add>";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)sender;
      if (args.length == 0) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m----------"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§b/godpickaxe create <level>"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§b/godpickaxe add <upgrade> <level>"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m----------"));
      } else if (args.length == 2 && "create".equalsIgnoreCase(args[0])) {
        int lvl = Integer.parseInt(args[1]);
        ItemStack stack = new ItemStack(ItemsRegister.GOD_PICKAXE);
        ItemGodPickaxe item = (ItemGodPickaxe)stack.func_77973_b();
        double xp = item.getXpForLevel(lvl - 1);
        item.addXp(player, stack, xp + 1.0D);
        PlayerUtil.addItemStackToInventory(stack, player.field_71071_by);
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §aGive effectué"));
      } else if (args.length == 3 && "add".equalsIgnoreCase(args[0])) {
        ItemStack stack = player.func_70694_bm();
        if (stack == null || !(stack.func_77973_b() instanceof ItemGodPickaxe)) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez avoir une GodPickaxe en main"));
          return;
        } 
        ItemGodPickaxe item = (ItemGodPickaxe)stack.func_77973_b();
        try {
          ItemGodPickaxe.Upgrades type = ItemGodPickaxe.Upgrades.valueOf(args[1].toUpperCase());
          int lvl = Integer.parseInt(args[2]);
          ItemGodPickaxe.Upgrade upgrade = new ItemGodPickaxe.Upgrade(type, lvl);
          item.addUpgrade(stack, upgrade);
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §aAmélioration ajoutée"));
        } catch (Exception e) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVoici la liste des upgrades possible"));
          player.func_145747_a((IChatComponent)new ChatComponentText("§8§m----------"));
          for (ItemGodPickaxe.Upgrades t : ItemGodPickaxe.Upgrades.values())
            player.func_145747_a((IChatComponent)new ChatComponentText("§8» §b" + StringUtils.capitalize(t.toString().toLowerCase()))); 
          player.func_145747_a((IChatComponent)new ChatComponentText("§8§m----------"));
          return;
        } 
      } else {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m----------"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§b/godpickaxe create <level>"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§b/godpickaxe add <upgrade> <level>"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m----------"));
      } 
    } 
  }
  
  public int func_82362_a() {
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\command\CommandGodPickaxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */