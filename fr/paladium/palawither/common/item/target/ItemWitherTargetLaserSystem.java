package fr.paladium.palawither.common.item.target;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palawither.common.CommonProxy;
import java.util.List;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemWitherTargetLaserSystem extends Item {
  public ItemWitherTargetLaserSystem() {
    func_77625_d(1);
    func_111206_d("palawither:target/laser_system");
    func_77655_b("wither_target_laser_system");
    func_77637_a(CommonProxy.CREATIVE_TAB);
    func_77656_e(10);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    setActive(stack, !isActive(stack));
    player.field_71069_bz.func_75142_b();
    return stack;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean flag) {
    list.add("§8[§cRMB§8] pour activer/désactiver le système de ciblage");
    list.add("§8[§cLMB§8] pour créer un point de ciblage");
    list.add("");
    list.add("§8[§cR§8] pour réinitialiser le système de ciblage");
    list.add("§8[§c↑§8][§c↓§8] pour changer la distance de ciblage");
  }
  
  @NonNull
  public static ItemStack setActive(@NonNull ItemStack item, boolean active) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    NBTTagCompound tag = item.func_77978_p();
    if (tag == null)
      item.func_77982_d(new NBTTagCompound()); 
    item.func_77978_p().func_74757_a("active", active);
    return item;
  }
  
  public static boolean isActive(@NonNull ItemStack item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    NBTTagCompound tag = item.func_77978_p();
    if (tag == null)
      return false; 
    return tag.func_74767_n("active");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\item\target\ItemWitherTargetLaserSystem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */