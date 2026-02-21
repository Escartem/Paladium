package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEndiumGauntlet extends BaseItem implements ITooltipWiki {
  private IIcon infiniteGauntletIcon;
  
  public ItemEndiumGauntlet() {
    super("endium_gauntlet");
    func_77655_b("endiumGauntlet");
    func_77625_d(1);
    func_77656_e(128);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
    int[] modifiers = UpgradeHelper.getUpgradeAmmount(stack);
    if (modifiers == null)
      return; 
    if (modifiers.length != 0) {
      list.add("§6§n§o§lStones:");
      list.add("");
    } 
    ArrayList<Integer> Ilist = new ArrayList<>();
    for (int i : modifiers) {
      if (i != 7 && !Ilist.contains(Integer.valueOf(i))) {
        int lvl = UpgradeHelper.getModifier(stack, i);
        String lvlDisplay = "";
        if (lvl > 1)
          lvlDisplay = " " + lvl; 
        list.add(" " + UpgradeHelper.getUpgradeName(i) + lvlDisplay);
        Ilist.add(Integer.valueOf(i));
      } 
    } 
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      if (player.func_70093_af()) {
        int[] modifiers = UpgradeHelper.getUpgradeAmmount(stack);
        if (modifiers == null)
          return stack; 
        if (modifiers.length == 0) {
          player.func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Their is no stone in the gauntlet!"));
          return stack;
        } 
        int selected = -1;
        if (stack.func_77978_p().func_74764_b("selected"))
          selected = stack.func_77978_p().func_74762_e("selected"); 
        if (selected == -1) {
          stack.func_77978_p().func_74768_a("selected", 0);
          return stack;
        } 
        ArrayList<Integer> Ilist = new ArrayList<>();
        for (int i : modifiers) {
          if (i != 7 && !Ilist.contains(Integer.valueOf(i)))
            Ilist.add(Integer.valueOf(i)); 
        } 
        selected++;
        if (selected == modifiers.length)
          selected = 0; 
        ArrayList<LegendaryStone.Effect> availablesStones = new ArrayList<>();
        for (LegendaryStone.Effect effect : LegendaryStone.Effect.values()) {
          if (Ilist.contains(Integer.valueOf(effect.getType())))
            availablesStones.add(effect); 
        } 
        LegendaryStone.Effect newSelected = availablesStones.get(selected);
        player.func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Select new stone " + newSelected
              .getDisplayName()));
        stack.func_77978_p().func_74768_a("selected", selected);
        String[] splittedUpgradeName = UpgradeHelper.getUpgradeName(newSelected.getType()).split(" ");
        String upgradeName = splittedUpgradeName[1];
        if (stack.func_82833_r().contains("Infinity Gauntlet")) {
          stack.func_151001_c("Infinity Gauntlet (" + upgradeName + ")");
        } else {
          stack.func_151001_c("Endium Gauntlet (" + upgradeName + ")");
        } 
      } else {
        int[] modifiers = UpgradeHelper.getUpgradeAmmount(stack);
        if (modifiers == null)
          return stack; 
        if (modifiers.length == 0) {
          player.func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Their is no stone in the gauntlet!"));
          return stack;
        } 
        int selected = -1;
        if (stack.func_77978_p().func_74764_b("selected"))
          selected = stack.func_77978_p().func_74762_e("selected"); 
        if (selected == -1) {
          player.func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "You have no selected stone!"));
          return stack;
        } 
        ArrayList<Integer> Ilist = new ArrayList<>();
        for (int i : modifiers) {
          if (i != 7 && !Ilist.contains(Integer.valueOf(i)))
            Ilist.add(Integer.valueOf(i)); 
        } 
        ArrayList<LegendaryStone.Effect> availablesStones = new ArrayList<>();
        for (LegendaryStone.Effect effect : LegendaryStone.Effect.values()) {
          if (Ilist.contains(Integer.valueOf(effect.getType())))
            availablesStones.add(effect); 
        } 
        LegendaryStone.Effect selectedStone = availablesStones.get(selected);
        LegendaryStone stone = null;
        if (selectedStone.equals(LegendaryStone.Effect.CHAOS)) {
          stone = (LegendaryStone)ItemsRegister.LEGENDARYSTONE_CHAOS;
        } else if (selectedStone.equals(LegendaryStone.Effect.FORTUNE)) {
          stone = (LegendaryStone)ItemsRegister.LEGENDARYSTONE_FORTUNE;
        } else if (selectedStone.equals(LegendaryStone.Effect.INVISIBILITY)) {
          stone = (LegendaryStone)ItemsRegister.LEGENDARYSTONE_INVISIBILITY;
        } else if (selectedStone.equals(LegendaryStone.Effect.POWER)) {
          stone = (LegendaryStone)ItemsRegister.LEGENDARYSTONE_POWER;
        } else if (selectedStone.equals(LegendaryStone.Effect.JOBS)) {
          stone = (LegendaryStone)ItemsRegister.LEGENDARYSTONE_JOBS;
        } else if (selectedStone.equals(LegendaryStone.Effect.TELEPORTATION)) {
          stone = (LegendaryStone)ItemsRegister.LEGENDARYSTONE_TELEPORTATION;
        } 
        if (stone == null || WorldGuardUtils.isItemEffectBlocked((Entity)player, new ItemStack((Item)stone)))
          return stack; 
        stone.func_77659_a(stack, world, player);
      }  
    return stack;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iiconRegister) {
    this.field_77791_bV = iiconRegister.func_94245_a(func_111208_A());
    this.infiniteGauntletIcon = iiconRegister.func_94245_a("palamod:infinite_gauntlet");
  }
  
  public IIcon func_77650_f(ItemStack itemStack) {
    String name = itemStack.func_82833_r().toLowerCase().trim();
    return name.startsWith("infinity gauntlet") ? this.infiniteGauntletIcon : super
      .func_77650_f(itemStack);
  }
  
  public IIcon getIcon(ItemStack stack, int pass) {
    return func_77650_f(stack);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemEndiumGauntlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */