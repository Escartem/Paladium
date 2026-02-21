package fr.paladium.palamod.modules.miner.item;

import com.google.common.base.Strings;
import fr.paladium.common.utils.ITooltipWiki;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemSealedXpBottle extends ItemMiner implements ITooltipWiki {
  private final IIcon[] icons = new IIcon[6];
  
  public ItemSealedXpBottle() {
    super("sealed_xp_bottle");
    func_77625_d(1);
    func_111206_d("palamod:sealed_xp_bottle/");
  }
  
  public void func_94581_a(IIconRegister register) {
    for (int i = 0; i < this.icons.length; i++)
      this.icons[i] = register.func_94245_a(func_111208_A() + "state_" + i); 
    this.field_77791_bV = this.icons[0];
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (player.func_70093_af()) {
      if (player.field_71068_ca >= 1 && getLvlForXP(getXp(item)) < 30) {
        player.func_82242_a(-1);
        addXp(item, player.func_71050_bK());
      } 
    } else {
      int xp = getXp(item);
      if (xp > 0) {
        if (!world.field_72995_K)
          player.func_71023_q(xp); 
        addXp(item, -xp);
      } 
    } 
    return super.func_77659_a(item, world, player);
  }
  
  public void addXp(ItemStack item, int xp) {
    NBTTagCompound compound = new NBTTagCompound();
    if (item.func_77942_o())
      compound = item.func_77978_p(); 
    int oldXP = 0;
    if (compound.func_74764_b("sealed_xp"))
      oldXP = compound.func_74762_e("sealed_xp"); 
    oldXP += xp;
    if (getLvlForXP(oldXP) > 30)
      oldXP = getXPForLevel(30); 
    compound.func_74768_a("sealed_xp", oldXP);
    item.func_77982_d(compound);
  }
  
  public IIcon getIcon(ItemStack stack, int pass) {
    int xp = getXp(stack);
    int maxXp = getXPForLevel(30);
    float p = xp / maxXp * 100.0F;
    if (p > 100.0F)
      p = 100.0F; 
    int state = (int)(0.05F * p);
    return this.icons[state];
  }
  
  public IIcon func_77650_f(ItemStack stack) {
    int xp = getXp(stack);
    int maxXp = getXPForLevel(30);
    float p = xp / maxXp * 100.0F;
    if (p > 100.0F)
      p = 100.0F; 
    int state = (int)(0.05F * p);
    return this.icons[state];
  }
  
  public int getXp(ItemStack item) {
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("sealed_xp"))
      return item.func_77978_p().func_74762_e("sealed_xp"); 
    return 0;
  }
  
  public static int getXPForLevel(int lvl) {
    if (lvl <= 15)
      return lvl * 17; 
    if (lvl > 16 && lvl < 31)
      return (int)(1.5D * Math.pow(lvl, 2.0D) - 29.5D * lvl + 360.0D); 
    if (lvl > 30)
      return (int)(3.5D * Math.pow(lvl, 2.0D) - 151.5D * lvl + 2220.0D); 
    return 0;
  }
  
  public static int getLvlForXP(int xp) {
    if (xp <= 255)
      return xp / 17; 
    if (xp > 272 && xp < 887)
      return (int)((Math.sqrt((24 * xp - 5159)) + 59.0D) / 6.0D); 
    if (xp > 825)
      return (int)((Math.sqrt((56 * xp - 32511)) + 303.0D) / 14.0D); 
    return 0;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    int xp = getXp(item);
    int lvl = getLvlForXP(xp);
    list.add("§eContient");
    list.add(" §8> §b" + lvl + "/30");
    list.add(" ");
    list.add("§8[§r" + getProgressBar(xp - getXPForLevel(lvl), getXPForLevel(lvl + 1) - getXPForLevel(lvl), 20, '|', "§b", "§7") + "§8]");
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getProgressBar(int current, int max, int totalBars, char symbol, String completedColor, String notCompletedColor) {
    float percent = Math.min(current / max, 1.0F);
    int progressBars = (int)(totalBars * percent);
    return Strings.repeat(completedColor + symbol, progressBars) + Strings.repeat(notCompletedColor + symbol, totalBars - progressBars);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemSealedXpBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */