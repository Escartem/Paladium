package fr.paladium.palamod.modules.pillage.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemInfusedStone extends Item implements ITooltipWiki {
  private IIcon baseIcon;
  
  private IIcon stoneIcon;
  
  public ItemInfusedStone(String name) {
    func_77655_b(name);
    func_111206_d("palamod:pillage/home_finder/" + name);
    func_77637_a((CreativeTabs)Registry.PILLAGE_TAB);
    func_77625_d(1);
  }
  
  public void func_150895_a(Item item, CreativeTabs tab, List<ItemStack> subBlocks) {
    subBlocks.add(new ItemStack(item, 1, 0));
    subBlocks.add(new ItemStack(item, 1, 1));
  }
  
  public void func_77622_d(ItemStack stack, World world, EntityPlayer player) {
    setNBTTTimer(world, stack);
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity entity, int slot, boolean isInHand) {
    super.func_77663_a(stack, world, entity, slot, isInHand);
    if (stack.field_77994_a == 0)
      return; 
    if (world.field_72995_K)
      return; 
    if (!(entity instanceof EntityPlayer))
      return; 
    if (stack.func_77960_j() > 0)
      return; 
    if (stack.func_77942_o()) {
      if (stack.func_77978_p().func_74764_b("time")) {
        float time = (float)(200L - world.func_82737_E() - stack.func_77978_p().func_74763_f("time")) / 3.0F;
        if (time <= 0.0F) {
          ((EntityPlayer)entity).field_71071_by.func_70299_a(slot, null);
          ((EntityPlayer)entity).field_71071_by.func_70441_a(new ItemStack(this, 1, 1));
        } 
      } 
    } else {
      setNBTTTimer(world, stack);
    } 
  }
  
  private void setNBTTTimer(World world, ItemStack stack) {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74772_a("time", world.func_82737_E());
    stack.func_77982_d(compound);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean p_77624_4_) {
    if (stack.func_77942_o() && 
      stack.func_77978_p().func_74764_b("time")) {
      float time = (float)(200L - player.field_70170_p.func_82737_E() - stack.func_77978_p().func_74763_f("time")) / 3.0F;
      if (time < 0.0F)
        time = 0.0F; 
      tooltip.add("Transformation dans : " + String.format("%.1f", new Object[] { Float.valueOf(time) }) + " secondes");
    } 
  }
  
  public void func_94581_a(IIconRegister iconregister) {
    super.func_94581_a(iconregister);
    this.baseIcon = iconregister.func_94245_a(func_111208_A());
    this.stoneIcon = iconregister.func_94245_a("palamod:pillage/home_finder/cracked_stone");
  }
  
  public IIcon func_77617_a(int meta) {
    return (meta == 0) ? this.stoneIcon : this.baseIcon;
  }
  
  public String func_77667_c(ItemStack stack) {
    String suffix = (stack.func_77960_j() > 0) ? ("_" + stack.func_77960_j()) : "";
    return func_77658_a() + suffix;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#9.-home-remover-et-coordinate-jammer";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\items\ItemInfusedStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */