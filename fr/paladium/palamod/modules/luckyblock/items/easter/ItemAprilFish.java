package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.data.MarchPlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemAprilFish extends ItemFood implements ITooltipInformations {
  private static final String DESCRIPTION = "Transforme le skin du joueur qui l'a mangé en poisson et fait apparaît sur sa position un silverfish toutes les 30 minutes. Le joueur peut mettre ce effet sur un autre en effectuant un clic droit en tenant l'objet.";
  
  public ItemAprilFish() {
    super(0, 0.0F, false);
    func_77848_i();
    func_77655_b("april_fish");
    func_111206_d("palamod:april_fish");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(64);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return super.func_77659_a(itemStack, world, player); 
    EntityLivingBase target = getTarget(player, 1);
    if (target == player)
      return super.func_77659_a(itemStack, world, player); 
    if (!(target instanceof EntityPlayer))
      return super.func_77659_a(itemStack, world, player); 
    EntityPlayerMP targetPlayer = (EntityPlayerMP)target;
    applyEffect(targetPlayer);
    MonthlyUtils.sendMessage(player, new String[] { "§eVous avez transformé " + targetPlayer.getDisplayName() + " en poisson pour 24 heures !" });
    MonthlyUtils.sendMessage((EntityPlayer)targetPlayer, new String[] { "§eVous avez été transformé en poisson pour 24 heures !" });
    MonthlyUtils.decrementCurrentItem(player, itemStack);
    return itemStack;
  }
  
  public void func_77849_c(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      applyEffect((EntityPlayerMP)player); 
  }
  
  private void applyEffect(EntityPlayerMP target) {
    MarchPlayer eep = MarchPlayer.get((EntityPlayer)target);
    if (eep == null)
      return; 
    long durationMillis = TimeUnit.HOURS.toMillis(24L);
    long now = System.currentTimeMillis();
    eep.setFishExpirationMillis(now + durationMillis);
    MonthlyUtils.sendMessage((EntityPlayer)target, new String[] { "§eVous avez été transformé en poisson pour 24 heures !" });
  }
  
  private EntityLivingBase getTarget(EntityPlayer player, int range) {
    Vec3 playerPos = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
    Vec3 playerLook = player.func_70040_Z();
    Vec3 targetPos = playerPos.func_72441_c(playerLook.field_72450_a * range, playerLook.field_72448_b * range, playerLook.field_72449_c * range);
    MovingObjectPosition target = player.field_70170_p.func_72933_a(playerPos, targetPos);
    if (target != null && target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
      targetPos = Vec3.func_72443_a(target.field_72307_f.field_72450_a, target.field_72307_f.field_72448_b, target.field_72307_f.field_72449_c); 
    EntityLivingBase targetEntity = null;
    List<?> entities = player.field_70170_p.func_72839_b((Entity)player, player.field_70121_D.func_72321_a(playerLook.field_72450_a * range, playerLook.field_72448_b * range, playerLook.field_72449_c * range).func_72314_b(1.0D, 1.0D, 1.0D));
    double distance = Double.MAX_VALUE;
    for (Object obj : entities) {
      if (obj instanceof EntityLivingBase) {
        EntityLivingBase entity = (EntityLivingBase)obj;
        double entityDistance = entity.func_70011_f(player.field_70165_t, player.field_70163_u, player.field_70161_v);
        if (entityDistance < distance && entity.func_70067_L()) {
          Vec3 entityPos = Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e(), entity.field_70161_v);
          MovingObjectPosition entityTarget = (entityPos.func_72438_d(playerPos) < entityDistance) ? player.field_70170_p.func_72933_a(playerPos, entityPos) : null;
          if (entityTarget == null || entityTarget.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
            targetEntity = entity;
            distance = entityDistance;
          } 
        } 
      } 
    } 
    return targetEntity;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription("Transforme le skin du joueur qui l'a mangé en poisson et fait apparaît sur sa position un silverfish toutes les 30 minutes. Le joueur peut mettre ce effet sur un autre en effectuant un clic droit en tenant l'objet.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemAprilFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */