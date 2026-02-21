package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums.DanceDirection;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBlunderbuss extends Item implements ITooltipInformations {
  public static final String NAME = "blunderbuss";
  
  public static final String DESCRIPTION = "Consomme 8 poudres de creeper par utilisation. Inflige de lourds dégâts aux monstres touchés. Il faut attendre 15 secondes entre chaque utilisation";
  
  private static final String COOLDOWN_MESSAGE = "§cVous devez attendre %s secondes avant de pouvoir utiliser cet objet.";
  
  private static final float GUN_DAMAGE = 20.0F;
  
  private static final double DISTANCE_MAX_RANGE = 7.0D;
  
  private static final long COOLDOWN_TIME = TimeUnit.SECONDS.toMillis(15L);
  
  public ItemBlunderbuss() {
    func_77655_b("blunderbuss");
    func_111206_d("palamod:blunderbuss");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    if (CooldownUtils.isOnCooldown((Entity)player, "blunderbuss")) {
      long now = System.currentTimeMillis();
      long cooldown = CooldownUtils.getCooldown((Entity)player, "blunderbuss");
      MonthlyUtils.sendMessage(player, new String[] { String.format("§cVous devez attendre %s secondes avant de pouvoir utiliser cet objet.", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldown - now)) }) });
      return itemStack;
    } 
    if (!verifyAndConsume(player))
      return itemStack; 
    CooldownUtils.setCooldown((Entity)player, "blunderbuss", COOLDOWN_TIME);
    MonthlyUtils.playSoundAround(player, "blunderbuss");
    EntityLivingBase target = findEntityLiving(world, player);
    if (target == null)
      return itemStack; 
    target.func_70097_a(DamageSource.field_76376_m, 20.0F);
    return itemStack;
  }
  
  private EntityLivingBase findEntityLiving(World world, EntityPlayer player) {
    Location playerLocation = new Location((Entity)player);
    Cuboid cuboid = new Cuboid(playerLocation.clone(-7.0D, -7.0D, -7.0D), playerLocation.clone(7.0D, 7.0D, 7.0D));
    for (Entity entity : cuboid.getEntities()) {
      if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
        Location location = new Location(entity);
        if (playerLocation.getDistance(location) > 7.0D)
          continue; 
        return (EntityLivingBase)entity;
      } 
    } 
    return null;
  }
  
  private EntityLiving findEntity(World world, EntityPlayer player) {
    Vec3 vec3 = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
    Vec3 vec = player.func_70040_Z();
    Vec3 targetVec = vec3.func_72441_c(vec.field_72450_a * 200.0D, vec.field_72448_b * 200.0D, vec.field_72449_c * 200.0D);
    MovingObjectPosition result = player.func_70614_a(200.0D, 1.0F);
    if (result != null && result.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
      Entity entity = result.field_72308_g;
      if (entity instanceof EntityLiving)
        return (EntityLiving)entity; 
    } 
    return null;
  }
  
  private EntityLiving rayTraceEntities(EntityPlayerMP player) {
    double reachDistance = 7.0D;
    Vec3 startVec = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
    Vec3 lookVec = player.func_70040_Z();
    Vec3 endVec = startVec.func_72441_c(lookVec.field_72450_a * reachDistance, lookVec.field_72448_b * reachDistance, lookVec.field_72449_c * reachDistance);
    AxisAlignedBB searchRange = AxisAlignedBB.func_72330_a(startVec.field_72450_a, startVec.field_72448_b, startVec.field_72449_c, endVec.field_72450_a, endVec.field_72448_b, endVec.field_72449_c);
    List<Entity> entities = player.field_70170_p.func_72839_b((Entity)player, searchRange);
    for (Entity entity : entities) {
      if (entity instanceof EntityLiving)
        return (EntityLiving)entity; 
    } 
    return null;
  }
  
  private List<Location> findLocations(EntityPlayer player) {
    List<Location> locations = new ArrayList<>();
    Location next = DanceDirection.getRelativeLocation(new Location((Entity)player), DanceDirection.FRONT);
    locations.add(next.clone());
    for (int i = 0; i < 6; i++) {
      next = DanceDirection.getRelativeLocation(next, DanceDirection.FRONT);
      locations.add(next.clone());
    } 
    return locations;
  }
  
  private EntityLiving findEntity(List<Location> locations) {
    if (locations.isEmpty())
      return null; 
    World world = ((Location)locations.get(0)).getWorld();
    List<Entity> entities = world.field_72996_f;
    return entities.stream()
      .filter(entity -> !(entity instanceof EntityPlayer))
      .filter(entity -> entity instanceof EntityLiving)
      .filter(entity -> !entity.field_70128_L)
      .map(entity -> (EntityLiving)entity)
      .filter(living -> {
          Location entityLocation = new Location((Entity)living);
          return locations.stream().anyMatch(());
        }).findFirst()
      .orElse(null);
  }
  
  private boolean verifyAndConsume(EntityPlayer player) {
    ItemStack powder = new ItemStack(Items.field_151016_H);
    boolean has = MonthlyUtils.hasItemsInInventory(player, powder, 8);
    if (!has)
      return false; 
    MonthlyUtils.removeItemsFromInventory(player, powder, 8);
    return true;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Consomme 8 poudres de creeper par utilisation. Inflige de lourds dégâts aux monstres touchés. Il faut attendre 15 secondes entre chaque utilisation");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemBlunderbuss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */