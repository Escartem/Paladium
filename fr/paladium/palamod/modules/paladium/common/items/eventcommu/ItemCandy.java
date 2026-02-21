package fr.paladium.palamod.modules.paladium.common.items.eventcommu;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemCandy extends Item implements ITooltipWiki {
  public enum Type {
    GREEN, YELLOW, BLUE, PINK, RAINBOW;
  }
  
  IIcon[] icons = new IIcon[(Type.values()).length];
  
  public ItemCandy() {
    func_77625_d(1);
    func_77655_b("job_candy");
    func_111206_d("palamod:job_candy");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public String func_77667_c(ItemStack p_77667_1_) {
    if (p_77667_1_.func_77960_j() != 0 && p_77667_1_.func_77960_j() <= (Type.values()).length)
      return func_77658_a() + "_" + Type.values()[p_77667_1_.func_77960_j() - 1]; 
    return func_77658_a();
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister p_94581_1_) {
    super.func_94581_a(p_94581_1_);
    for (int i = 0; i < (Type.values()).length; i++)
      this.icons[i] = p_94581_1_.func_94245_a("palamod:candy_" + Type.values()[i].name()); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int p_77617_1_) {
    if (p_77617_1_ != 0 && p_77617_1_ <= (Type.values()).length)
      return this.icons[p_77617_1_ - 1]; 
    return this.field_77791_bV;
  }
  
  public boolean func_77636_d(ItemStack itemStack) {
    return (itemStack.func_77960_j() == 0);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
    if (world.field_72995_K)
      return itemStack; 
    if (itemStack.func_77960_j() == 0) {
      int damage = world.field_73012_v.nextInt((Type.values()).length);
      itemStack.func_77964_b(damage + 1);
    } else if (itemStack.func_77960_j() > 0) {
      switch (itemStack.func_77960_j()) {
        case 1:
          JobsPlayer.get((Entity)entityPlayer).addXp(JobType.FARMER, ObjectiveType.FISH, 50000.0D, entityPlayer);
          break;
        case 2:
          JobsPlayer.get((Entity)entityPlayer).addXp(JobType.MINER, ObjectiveType.FISH, 50000.0D, entityPlayer);
          break;
        case 3:
          JobsPlayer.get((Entity)entityPlayer).addXp(JobType.HUNTER, ObjectiveType.FISH, 50000.0D, entityPlayer);
          break;
        case 4:
          JobsPlayer.get((Entity)entityPlayer).addXp(JobType.ALCHEMIST, ObjectiveType.FISH, 50000.0D, entityPlayer);
          break;
        case 5:
          JobsPlayer.get((Entity)entityPlayer).addXp(JobType.FARMER, ObjectiveType.FISH, 50000.0D, entityPlayer);
          JobsPlayer.get((Entity)entityPlayer).addXp(JobType.MINER, ObjectiveType.FISH, 50000.0D, entityPlayer);
          JobsPlayer.get((Entity)entityPlayer).addXp(JobType.HUNTER, ObjectiveType.FISH, 50000.0D, entityPlayer);
          JobsPlayer.get((Entity)entityPlayer).addXp(JobType.ALCHEMIST, ObjectiveType.FISH, 50000.0D, entityPlayer);
          break;
      } 
      itemStack.field_77994_a = 0;
    } 
    return itemStack;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
    super.func_150895_a(p_150895_1_, p_150895_2_, p_150895_3_);
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 2));
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 3));
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 4));
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 5));
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#4.-lucky-block-halloween";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\eventcommu\ItemCandy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */