package fr.paladium.palawither.common.item.target;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.common.CommonProxy;
import fr.paladium.palawither.common.entity.targetable.IWitherCoordinateTargetable;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemWitherTargetPoint extends Item {
  public ItemWitherTargetPoint() {
    func_77625_d(16);
    func_111206_d("palawither:target/point");
    func_77655_b("wither_target_point");
    func_77637_a(CommonProxy.CREATIVE_TAB);
  }
  
  public boolean func_111207_a(ItemStack item, EntityPlayer player, EntityLivingBase entity) {
    if (player.field_70170_p.field_72995_K || !(entity instanceof IWitherCoordinateTargetable))
      return false; 
    Optional<DoubleLocation> targetLocation = getTargetLocation(item);
    if (!targetLocation.isPresent())
      return false; 
    IWitherCoordinateTargetable wither = (IWitherCoordinateTargetable)entity;
    if (!wither.canSetTargetLocation(player))
      return false; 
    wither.setTargetLocation(targetLocation.get());
    if (!player.field_71075_bZ.field_75098_d) {
      item.field_77994_a--;
      player.field_71069_bz.func_75142_b();
    } 
    return true;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    Optional<DoubleLocation> targetLocation = getTargetLocation(item);
    if (targetLocation.isPresent()) {
      DoubleLocation location = targetLocation.get();
      double distance = player.func_70011_f(location.getX(), location.getY(), location.getZ());
      list.add("Ciblage: §a" + String.format("%.0f", new Object[] { Double.valueOf(location.getX()) }) + ", " + String.format("%.0f", new Object[] { Double.valueOf(location.getY()) }) + ", " + String.format("%.0f", new Object[] { Double.valueOf(location.getZ()) }) + " §8(" + String.format("%.0f", new Object[] { Double.valueOf(distance) }) + "m)");
    } else {
      list.add("Ciblage: §cEn attente");
    } 
  }
  
  @NonNull
  public static ItemStack create(@NonNull DoubleLocation location) {
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    ItemStack item = new ItemStack(ItemsRegister.WITHER_TARGET_POINT);
    NBTTagCompound tag = item.func_77978_p();
    if (tag == null)
      item.func_77982_d(new NBTTagCompound()); 
    item.func_77978_p().func_74780_a("targetX", location.getX());
    item.func_77978_p().func_74780_a("targetY", location.getY());
    item.func_77978_p().func_74780_a("targetZ", location.getZ());
    return item;
  }
  
  @NonNull
  public static Optional<DoubleLocation> getTargetLocation(@NonNull ItemStack item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    if (!(item.func_77973_b() instanceof ItemWitherTargetPoint))
      return Optional.empty(); 
    NBTTagCompound tag = item.func_77978_p();
    if (tag == null || !tag.func_74764_b("targetX") || !tag.func_74764_b("targetY") || !tag.func_74764_b("targetZ"))
      return Optional.empty(); 
    double x = tag.func_74769_h("targetX");
    double y = tag.func_74769_h("targetY");
    double z = tag.func_74769_h("targetZ");
    return Optional.of(new DoubleLocation(x, y, z));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\item\target\ItemWitherTargetPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */