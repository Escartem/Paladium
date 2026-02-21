package fr.paladium.palarpg.module.equipment.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palarpg.module.equipment.common.manager.EquipmentRuneManager;
import fr.paladium.palarpg.module.equipment.common.rune.RPGRuneStat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class RPGEquipmentCommand {
  @SubCommand(command = "/rpg rune check", description = "Regarder la data d'une rune", permission = "palarpg.command.rpg.rune.check", sender = {SenderType.PLAYER})
  public void checkRune(CommandContext context) {
    EntityPlayerMP player = context.getPlayer();
    ItemStack stack = player.func_70694_bm();
    if (stack == null) {
      context.error("Vous ne tenez aucun objet");
      return;
    } 
    if (!(stack.func_77973_b() instanceof fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRune)) {
      context.error("L'objet tenu n'est pas une rune RPG");
      return;
    } 
    if (!stack.func_77942_o()) {
      context.error("La rune ne possède pas de data");
      return;
    } 
    NBTTagCompound nbt = stack.func_77978_p();
    context.send("§a=== Rune Data ===");
    RPGRuneStat rune1 = RPGRuneStat.create();
    rune1.read(nbt.func_74775_l("stat_0"));
    context.send("§eStat 1: §f" + rune1);
    if (!nbt.func_74764_b("stat_1"))
      return; 
    RPGRuneStat rune2 = RPGRuneStat.create();
    rune2.read(nbt.func_74775_l("stat_1"));
    context.send("§eStat 2: §f" + rune2);
  }
  
  @SubCommand(command = "/rpg rune gamble", description = "Crée la data d'une rune", permission = "palarpg.command.rpg.rune.gamble", sender = {SenderType.PLAYER})
  public void gambleRune(CommandContext context) {
    EntityPlayerMP player = context.getPlayer();
    ItemStack stack = player.func_70694_bm();
    if (stack == null) {
      context.error("Vous ne tenez aucun objet");
      return;
    } 
    if (!(stack.func_77973_b() instanceof fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRune)) {
      context.error("L'objet tenu n'est pas une rune RPG");
      return;
    } 
    stack.func_77973_b().func_77622_d(stack, player.field_70170_p, (EntityPlayer)player);
    context.send("§aLa rune a été régénérée !");
  }
  
  @SubCommand(command = "/rpg rune fusion", description = "Crée la data d'une rune", permission = "palarpg.command.rpg.rune.fusion", sender = {SenderType.PLAYER})
  public void fusionRune(CommandContext context) {
    EntityPlayerMP player = context.getPlayer();
    ItemStack stack = player.func_70694_bm();
    if (stack == null) {
      context.error("Vous ne tenez aucun objet");
      return;
    } 
    if (!(stack.func_77973_b() instanceof fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRune)) {
      context.error("L'objet tenu n'est pas une rune RPG");
      return;
    } 
    int nextItemSlot = player.field_71071_by.field_70461_c + 1;
    ItemStack nextItemStack = player.field_71071_by.func_70301_a(nextItemSlot);
    if (nextItemStack == null) {
      context.error("Vous devez avoir une seconde rune dans le slot a droite de votre main");
      return;
    } 
    if (!(nextItemStack.func_77973_b() instanceof fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRune)) {
      context.error("L'objet dans le slot a droite de votre main n'est pas une rune RPG");
      return;
    } 
    ItemStack resultRune = EquipmentRuneManager.fusion(stack, nextItemStack);
    InventoryUtils.giveOrDropitems((EntityPlayer)player, resultRune);
    context.send("§aLa fusion a été réalisée !");
  }
  
  @SubCommand(command = "/rpg rune apply", description = "Apply a un equipement", permission = "palarpg.command.rpg.rune.apply", sender = {SenderType.PLAYER})
  public void applyRune(CommandContext context) {
    EntityPlayerMP player = context.getPlayer();
    ItemStack stack = player.func_70694_bm();
    if (stack == null) {
      context.error("Vous ne tenez aucun objet");
      return;
    } 
    if (!(stack.func_77973_b() instanceof fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRune)) {
      context.error("L'objet tenu n'est pas une rune RPG");
      return;
    } 
    int nextItemSlot = (player.field_71071_by.field_70461_c + 1) % 9;
    ItemStack nextItemStack = player.field_71071_by.func_70301_a(nextItemSlot);
    if (nextItemStack == null) {
      context.error("Vous devez avoir une seconde rune dans le slot a droite de votre main");
      return;
    } 
    if (!(nextItemStack.func_77973_b() instanceof fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemArmor) && !(nextItemStack.func_77973_b() instanceof fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemSword)) {
      context.error("L'objet dans le slot a droite de votre main n'est pas un équipement RPG");
      return;
    } 
    int freeSlotIndex = EquipmentRuneManager.getFirstFreeRuneSlot(nextItemStack);
    if (freeSlotIndex == -1) {
      context.error("L'équipement n'a plus de slot de rune disponible");
      return;
    } 
    EquipmentRuneManager.apply(stack, nextItemStack, freeSlotIndex);
    context.send("§aL'application a été réalisée !");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\server\command\RPGEquipmentCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */