package fr.paladium.palamod.modules.luckyblock.entity.halloween;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc.impl.EntityPlayerNPC;
import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityJackLantern extends EntityPlayerNPC {
  private static final double HITBOX_SIZE = 0.5D;
  
  public static final int REQUIRED_COUNT = 1;
  
  public static final String NPC_NAME = "Jack-o-lantern";
  
  public static final String DEFAULT_SKIN_PATH = "palamod:textures/entity/npc/jack_lantern.png";
  
  private int count;
  
  private EntityPlayer owner;
  
  public EntityJackLantern(World world) {
    super(world);
  }
  
  public EntityJackLantern(EntityPlayer owner, double x, double y, double z, long durationMillis) {
    super(owner.field_70170_p, "Jack-o-lantern", "palamod:textures/entity/npc/jack_lantern.png", x, y, z, durationMillis, true);
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
    if (this.count >= 1) {
      func_70106_y();
      MonthlyUtils.sendMessage(this.owner, new String[] { "§aMerci bien, je t’offre ceci ca ne me servira à rien la ou je vais." });
      InventoryUtils.giveOrDropitems(this.owner, new ItemStack(ItemsRegister.ENDIUM_FRAGMENT, 4));
      return;
    } 
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_70165_t - 0.5D, this.field_70163_u - 0.5D, this.field_70161_v - 0.5D, this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D);
    List<EntityItem> items = this.field_70170_p.func_72872_a(EntityItem.class, bounds);
    for (EntityItem item : items) {
      ItemStack stack = item.func_92059_d();
      if (stack == null || stack.func_77973_b() == null || stack.field_77994_a <= 0)
        continue; 
      if (!stack.func_77973_b().equals(Item.func_150898_a(Blocks.field_150478_aa)))
        continue; 
      this.count += stack.field_77994_a;
      item.func_70106_y();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\halloween\EntityJackLantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */