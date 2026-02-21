package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.entity;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.RepasDeRoi;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc.impl.EntityPlayerNPC;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityPlayerKing extends EntityPlayerNPC {
  private static final double HITBOX_SIZE = 0.5D;
  
  public static final int REQUIRED_CAKE_COUNT = 8;
  
  public static final String NPC_NAME = "Roi de la galette";
  
  public static final String DEFAULT_SKIN_PATH = "palamod:textures/entity/npc/player_king.png";
  
  private int cakeCount;
  
  private EntityPlayer owner;
  
  public EntityPlayerKing(World world) {
    super(world);
  }
  
  public EntityPlayerKing(EntityPlayer owner, double x, double y, double z, long durationMillis) {
    super(owner.field_70170_p, "Roi de la galette", "palamod:textures/entity/npc/player_king.png", x, y, z, durationMillis, true);
    this.owner = owner;
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.field_70170_p.field_72995_K)
      return; 
    if (this.owner == null || this.owner.field_70128_L) {
      func_70106_y();
      return;
    } 
    if (this.cakeCount >= 8) {
      dropReward();
      func_70106_y();
      MonthlyUtils.sendMessage(this.owner, new String[] { "§aVous avez donné 8 galettes au Roi de la galette et avez reçu une récompense." });
      MonthlyUtils.stopHeliosEvent((EntityPlayerMP)this.owner, RepasDeRoi.class);
      return;
    } 
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_70165_t - 0.5D, this.field_70163_u - 0.5D, this.field_70161_v - 0.5D, this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D);
    List<EntityItem> items = this.field_70170_p.func_72872_a(EntityItem.class, bounds);
    for (EntityItem item : items) {
      ItemStack stack = item.func_92059_d();
      if (stack == null || stack.func_77973_b() == null || stack.field_77994_a <= 0)
        continue; 
      if (!stack.func_77973_b().equals(ItemsRegister.GALETTE))
        continue; 
      this.cakeCount += stack.field_77994_a;
      item.func_70106_y();
    } 
  }
  
  private void dropReward() {
    ItemStack[] rewards = { new ItemStack(BlocksRegister.RANDOM_STATUE, 1), new ItemStack(GameRegistry.findItem("customnpcs", "npcCrown")) };
    for (ItemStack reward : rewards)
      ItemUtils.spawnItemAtEntity((Entity)this, reward); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\entity\EntityPlayerKing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */