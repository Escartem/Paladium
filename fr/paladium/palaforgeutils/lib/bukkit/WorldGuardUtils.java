package fr.paladium.palaforgeutils.lib.bukkit;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.SetFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.worldguardbridge.common.dto.flag.impl.SetFlag;
import fr.paladium.worldguardbridge.common.manager.WGManager;
import fr.paladium.worldguardbridge.common.manager.utils.WGRegionList;
import java.util.Optional;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class WorldGuardUtils {
  @SideOnly(Side.SERVER)
  public static boolean checkFlag(World worldObj, double posX, double posY, double posZ, StateFlag flag, boolean defaultValue) {
    boolean valid = (flag.getDefault() == null) ? defaultValue : ((flag.getDefault() == StateFlag.State.ALLOW));
    if (!worldObj.field_72995_K)
      try {
        RegionManager regionManager = WorldGuardPlugin.inst().getRegionManager(Bukkit.getWorld(worldObj.func_72912_H().func_76065_j()));
        ApplicableRegionSet regions = regionManager.getApplicableRegions(new Location(Bukkit.getWorld(worldObj.func_72912_H().func_76065_j()), posX, posY, posZ));
        for (ProtectedRegion region : regions.getRegions()) {
          StateFlag.State state = (StateFlag.State)region.getFlag((Flag)flag);
          if (state == StateFlag.State.DENY) {
            valid = false;
            break;
          } 
        } 
      } catch (Exception|NoClassDefFoundError exception) {} 
    return valid;
  }
  
  @SideOnly(Side.SERVER)
  public static boolean checkFlag(World worldObj, double posX, double posY, double posZ, SetFlag<?> flags, Object target) {
    boolean valid = true;
    if (!worldObj.field_72995_K)
      try {
        RegionManager regionManager = WorldGuardPlugin.inst().getRegionManager(Bukkit.getWorld(worldObj.func_72912_H().func_76065_j()));
        ApplicableRegionSet regions = regionManager.getApplicableRegions(new Location(Bukkit.getWorld(worldObj.func_72912_H().func_76065_j()), posX, posY, posZ));
        for (ProtectedRegion region : regions.getRegions()) {
          Set<?> states = (Set)region.getFlag((Flag)flags);
          if (states != null)
            for (Object obj : states) {
              if (obj != null && 
                obj.toString().equals(target.toString()))
                valid = false; 
            }  
        } 
      } catch (Exception|NoClassDefFoundError e) {
        valid = true;
      }  
    return valid;
  }
  
  @SideOnly(Side.SERVER)
  public static Set<?> getStates(World worldObj, double posX, double posY, double posZ, SetFlag<?> flags) {
    if (!worldObj.field_72995_K)
      try {
        RegionManager regionManager = WorldGuardPlugin.inst().getRegionManager(Bukkit.getWorld(worldObj.func_72912_H().func_76065_j()));
        ApplicableRegionSet regions = regionManager.getApplicableRegions(new Location(Bukkit.getWorld(worldObj.func_72912_H().func_76065_j()), posX, posY, posZ));
        ProtectedRegion r = null;
        for (ProtectedRegion region : regions.getRegions()) {
          if (region.getFlag((Flag)flags) == null)
            continue; 
          if (r != null) {
            if (region.getPriority() > r.getPriority())
              r = region; 
            continue;
          } 
          r = region;
        } 
        if (r != null) {
          Set<?> states = (Set)r.getFlag((Flag)flags);
          if (states != null)
            return states; 
        } 
      } catch (Exception|NoClassDefFoundError exception) {} 
    return null;
  }
  
  @SideOnly(Side.SERVER)
  public static String getFlag(World worldObj, double x, double y, double z, StringFlag flag) {
    if (worldObj.field_72995_K)
      return null; 
    int priority = Integer.MIN_VALUE;
    String content = null;
    RegionManager regionManager = WorldGuardPlugin.inst().getRegionManager(Bukkit.getWorld(worldObj.func_72912_H().func_76065_j()));
    ApplicableRegionSet regions = regionManager.getApplicableRegions(new Location(Bukkit.getWorld(worldObj.func_72912_H().func_76065_j()), x, y, z));
    for (ProtectedRegion region : regions.getRegions()) {
      String flagContent = (String)region.getFlag((Flag)flag);
      if (flagContent != null && (
        priority == Integer.MIN_VALUE || region.getPriority() > priority)) {
        priority = region.getPriority();
        content = flagContent;
      } 
    } 
    return content;
  }
  
  public static boolean isItemEffectBlocked(World world, double posX, double posY, double posZ, int id) {
    WGRegionList regionList = WGManager.inst().getRegionListAt(world, posX, posY, posZ);
    if (regionList == null || regionList.isEmpty())
      return false; 
    Optional<SetFlag> flag = regionList.getFlag("item-effect", SetFlag.class);
    if (!flag.isPresent())
      return false; 
    SetFlag<Integer> setFlag = flag.get();
    if (((Set)setFlag.getValue()).isEmpty())
      return false; 
    return ((Set)setFlag.getValue()).contains(Integer.valueOf(id));
  }
  
  public static boolean isItemEffectBlocked(Entity entity, ItemStack item) {
    return isItemEffectBlocked(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, Item.func_150891_b(item.func_77973_b()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\bukkit\WorldGuardUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */