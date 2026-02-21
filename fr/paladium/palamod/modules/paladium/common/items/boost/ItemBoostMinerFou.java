package fr.paladium.palamod.modules.paladium.common.items.boost;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.items.boost.hud.ModuleBoost;
import fr.paladium.palamod.modules.paladium.common.items.boost.packet.PlayerBoostEPropertiesSync;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBoostMinerFou extends Item implements ITooltipWiki {
  public ItemBoostMinerFou(String name, String texturename) {
    func_77625_d(1);
    func_77655_b(name);
    func_77637_a((CreativeTabs)Registry.POTION_TAB);
    func_111206_d("palamod:boost/" + texturename);
    func_77627_a(true);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    player.func_71008_a(stack, func_77626_a(stack));
    return stack;
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
      stack.field_77994_a--; 
    int toAdd = 0;
    switch (stack.func_77960_j()) {
      case 0:
        toAdd = 3600000;
        break;
      case 1:
        toAdd = 7200000;
        break;
      case 2:
        toAdd = 21600000;
        break;
      case 3:
        toAdd = 43200000;
        break;
    } 
    long currentDelay = PlayerBoostEProperties.get(player).getBoostMinerFou();
    long now = System.currentTimeMillis();
    if (currentDelay > now) {
      PlayerBoostEProperties.get(player).setBoostMinerFou(currentDelay + toAdd);
    } else {
      PlayerBoostEProperties.get(player).setBoostMinerFou(System.currentTimeMillis() + toAdd);
    } 
    if (player instanceof EntityPlayerMP) {
      PlayerBoostEPropertiesSync senderPacket = new PlayerBoostEPropertiesSync(PlayerBoostEProperties.get(player));
      ModuleBoost.getInstance().getNetwork().sendTo((IMessage)senderPacket, (EntityPlayerMP)player);
    } 
    return stack;
  }
  
  public void func_150895_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    list.add(new ItemStack(item, 1, 0));
    list.add(new ItemStack(item, 1, 1));
    list.add(new ItemStack(item, 1, 2));
    list.add(new ItemStack(item, 1, 3));
  }
  
  public String func_77653_i(ItemStack item) {
    String name = "";
    switch (item.func_77960_j()) {
      case 0:
        name = "§ePotion du §b§lMineur Fou §e1h";
        break;
      case 1:
        name = "§ePotion du §b§lMineur Fou §e2h";
        break;
      case 2:
        name = "§ePotion du §b§lMineur Fou §e6h";
        break;
      case 3:
        name = "§ePotion du §b§lMineur Fou §e12h";
        break;
    } 
    return name;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    super.func_77624_a(item, player, list, flag);
    list.add("Vous permet de casser instantanément");
    list.add("les blocs avec une §cPioche en Paladium§7.");
  }
  
  public int func_77626_a(ItemStack stack) {
    return 32;
  }
  
  public EnumAction func_77661_b(ItemStack stack) {
    return EnumAction.drink;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\boost\ItemBoostMinerFou.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */