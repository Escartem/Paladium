package fr.paladium.palamod.modules.hunter.items;

import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemHunterBackpack extends Item {
  private final IIcon[] icons = new IIcon[4];
  
  public ItemHunterBackpack() {
    func_77637_a((CreativeTabs)Registry.HUNTER_TAB);
    func_77655_b("hunter_backpack");
    func_111206_d("palamod:backpack");
    func_77625_d(1);
  }
  
  public String func_77667_c(ItemStack item) {
    String type = "";
    switch (item.func_77960_j()) {
      case 0:
        type = "amethyste";
        break;
      case 1:
        type = "titane";
        break;
      case 2:
        type = "paladium";
        break;
      case 3:
        type = "endium";
        break;
    } 
    return "item.hunter_backpack_" + type;
  }
  
  public static String getUnlocalizedContainerTitle(ItemStack item) {
    String type = "";
    switch (item.func_77960_j()) {
      case 0:
        type = "amethyste";
        break;
      case 1:
        type = "titane";
        break;
      case 2:
        type = "paladium";
        break;
      case 3:
        type = "endium";
        break;
    } 
    return "backpack." + type;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (WorldGuardUtils.isItemEffectBlocked((Entity)player, stack)) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] " + EnumChatFormatting.RED + "Vous ne pouvez pas utiliser cet objet ici !"));
      return stack;
    } 
    if (!world.field_72995_K)
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_BACKPACK, world, 0, 0, 0); 
    return stack;
  }
  
  public void func_150895_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < 4; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  public String func_77653_i(ItemStack item) {
    String name = "";
    switch (item.func_77960_j()) {
      case 0:
        name = "§eBackpack en §dAmethyste";
        break;
      case 1:
        name = "§eBackpack en §7Titane";
        break;
      case 2:
        name = "§eBackpack en §cPaladium";
        break;
      case 3:
        name = "§eBackpack en §9Endium";
        break;
    } 
    return name;
  }
  
  public void func_94581_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:amethyst_backpack");
    this.icons[1] = register.func_94245_a("palamod:titan_backpack");
    this.icons[2] = register.func_94245_a("palamod:paladium_backpack");
    this.icons[3] = register.func_94245_a("palamod:endium_backpack");
    super.func_94581_a(register);
  }
  
  public IIcon func_77617_a(int damage) {
    return this.icons[damage];
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    switch (item.func_77960_j()) {
      case 0:
        list.add("§dAmethyste §edispose d’une seule ligne");
        break;
      case 1:
        list.add("§7Titane §edispose de 3 lignes");
        break;
      case 2:
        list.add("§cPaladium §edispose de 6 lignes");
        break;
      case 3:
        list.add("§9Endium §edispose de la même taille qu’un Gold Chest");
        break;
    } 
    list.add(" ");
    list.add("§c§lAttention§c le contenu du backpack");
    list.add("§csera jeté au sol à votre mort !");
    super.func_77624_a(item, player, list, flag);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemHunterBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */