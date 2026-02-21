package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles;

import fr.paladium.lib.apollon.container.abstracts.PaladiumTileInventory;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable.ItemCrabEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ChunkUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.items.ItemVoidStoneMinage;
import fr.paladium.palamod.modules.paladium.common.logics.cobblebreaker.CobbleBreakerUpgrade;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import fr.paladium.palamod.util.FastUUID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityCrabCobbleBreaker extends PaladiumTileInventory implements ISidedInventory {
  public static final int GREEN_PALADIUM_SLOT = 7;
  
  public static final String TILE_ENTITY_ID = "tileEntityCrabCobbleBreaker";
  
  public static final String INVENTORY_NAME = "tileContainerCrabCobbleBreaker";
  
  public void setWork(int work) {
    this.work = work;
  }
  
  public void setEntityUniqueId(UUID entityUniqueId) {
    this.entityUniqueId = entityUniqueId;
  }
  
  public void setOwnerUniqueId(UUID ownerUniqueId) {
    this.ownerUniqueId = ownerUniqueId;
  }
  
  public void setSpawnMillis(long spawnMillis) {
    this.spawnMillis = spawnMillis;
  }
  
  public void setCuboid(Cuboid cuboid) {
    this.cuboid = cuboid;
  }
  
  private int work = 0;
  
  private UUID entityUniqueId;
  
  private UUID ownerUniqueId;
  
  private long spawnMillis;
  
  private Cuboid cuboid;
  
  public int getWork() {
    return this.work;
  }
  
  public UUID getEntityUniqueId() {
    return this.entityUniqueId;
  }
  
  public UUID getOwnerUniqueId() {
    return this.ownerUniqueId;
  }
  
  public long getSpawnMillis() {
    return this.spawnMillis;
  }
  
  public Cuboid getCuboid() {
    return this.cuboid;
  }
  
  public TileEntityCrabCobbleBreaker() {
    super("tileContainerCrabCobbleBreaker", 8);
    this.entityUniqueId = null;
    this.spawnMillis = System.currentTimeMillis();
  }
  
  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K && isExpired(System.currentTimeMillis())) {
      onExpired();
      return;
    } 
    if (func_70301_a(0) != null && (
      func_70301_a(0).func_77973_b().equals(Item.func_150898_a(Blocks.field_150347_e)) || 
      func_70301_a(0).func_77973_b() instanceof ItemVoidStoneMinage) && (
      func_70301_a(1) == null || (func_70301_a(1)).field_77994_a < 64 || 
      func_70301_a(2) == null || (func_70301_a(2)).field_77994_a < 64 || 
      func_70301_a(3) == null || (func_70301_a(3)).field_77994_a < 64 || 
      func_70301_a(4) == null || (func_70301_a(4)).field_77994_a < 64 || 
      func_70301_a(5) == null || (func_70301_a(5)).field_77994_a < 64 || 
      func_70301_a(6) == null || (func_70301_a(6)).field_77994_a < 64 || 
      func_70301_a(7) == null || (func_70301_a(7)).field_77994_a < 64)) {
      if (this.work >= getUpgrade().getWork()) {
        if (!this.field_145850_b.field_72995_K) {
          int random = (int)(Math.random() * 250.0D);
          boolean randomBoolean = (Math.random() < 0.5D);
          if (random <= 1 && randomBoolean)
            if (func_70301_a(7) != null) {
              if ((func_70301_a(7)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(7).func_77973_b(), (func_70301_a(7)).field_77994_a + 1, func_70301_a(7).func_77960_j());
                func_70299_a(7, paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_GREEN_PALADIUM, 1, 0);
              func_70299_a(7, paladium);
            }  
          if (random <= 1)
            if (func_70301_a(6) != null) {
              if ((func_70301_a(6)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(6).func_77973_b(), (func_70301_a(6)).field_77994_a + 1, func_70301_a(6).func_77960_j());
                func_70299_a(6, paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_PALADIUM, 1, 0);
              func_70299_a(6, paladium);
              PPalaDynamique.instance.addGenerated("COBBLE_BREAKER", 0.1111111111111111D);
            }  
          if (random > 1 && random <= 3)
            if (func_70301_a(5) != null) {
              if ((func_70301_a(5)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(5).func_77973_b(), (func_70301_a(5)).field_77994_a + 1, func_70301_a(5).func_77960_j());
                func_70299_a(5, paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_TITANE, 1, 0);
              func_70299_a(5, paladium);
            }  
          if (random > 3 && random <= 7)
            if (func_70301_a(4) != null) {
              if ((func_70301_a(4)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(4).func_77973_b(), (func_70301_a(4)).field_77994_a + 1, func_70301_a(4).func_77960_j());
                func_70299_a(4, paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_AMETHYST, 1, 0);
              func_70299_a(4, paladium);
            }  
          if (random > 7 && random <= 12)
            if (func_70301_a(3) != null) {
              if ((func_70301_a(3)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(3).func_77973_b(), (func_70301_a(3)).field_77994_a + 1, func_70301_a(3).func_77960_j());
                func_70299_a(3, paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_DIAMOND, 1, 0);
              func_70299_a(3, paladium);
            }  
          if (random > 12 && random <= 20)
            if (func_70301_a(2) != null) {
              if ((func_70301_a(2)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(2).func_77973_b(), (func_70301_a(2)).field_77994_a + 1, func_70301_a(2).func_77960_j());
                func_70299_a(2, paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_GOLD, 1, 0);
              func_70299_a(2, paladium);
            }  
          if (random > 20 && random <= 30)
            if (func_70301_a(1) != null) {
              if ((func_70301_a(1)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(1).func_77973_b(), (func_70301_a(1)).field_77994_a + 1, func_70301_a(1).func_77960_j());
                func_70299_a(1, paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_IRON, 1, 0);
              func_70299_a(1, paladium);
            }  
        } 
        if (func_70301_a(0).func_77973_b() instanceof ItemVoidStoneMinage) {
          ItemVoidStoneMinage itemVoidStoneMinage = (ItemVoidStoneMinage)func_70301_a(0).func_77973_b();
          int stone = itemVoidStoneMinage.getStone(func_70301_a(0));
          stone--;
          if (stone < 0)
            stone = 0; 
          itemVoidStoneMinage.setStone(func_70301_a(0), stone);
        } else {
          func_70298_a(0, 1);
        } 
        this.work = 0;
      } else {
        if (func_70301_a(0).func_77973_b() instanceof ItemVoidStoneMinage) {
          ItemVoidStoneMinage itemVoidStoneMinage = (ItemVoidStoneMinage)func_70301_a(0).func_77973_b();
          int stone = itemVoidStoneMinage.getStone(func_70301_a(0));
          if (stone <= 0)
            return; 
        } 
        this.work++;
      } 
    } else {
      this.work = 0;
    } 
  }
  
  private boolean isExpired(long now) {
    return (now - this.spawnMillis > ItemCrabEgg.COOLDOWN_DURATION.longValue());
  }
  
  private boolean isCrabUpper() {
    if (this.entityUniqueId == null)
      return false; 
    if (this.cuboid == null)
      this.cuboid = new Cuboid(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, (this.field_145848_d + 1), this.field_145849_e); 
    for (EntityLivingBase entity : ChunkUtils.getLivingEntities(this.field_145850_b, this.cuboid)) {
      if (entity.func_110124_au().equals(this.entityUniqueId))
        return true; 
    } 
    return false;
  }
  
  public void onExpired() {
    this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public void dropContent() {
    List<ItemStack> stacks = getItems();
    EntityPlayerMP entityPlayerMP = MonthlyUtils.getPlayer(this.ownerUniqueId);
    if (entityPlayerMP == null) {
      stacks.forEach(stack -> ItemUtils.spawnItemInWorld(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, stack));
    } else {
      stacks.forEach(stack -> InventoryUtils.giveOrDropitems(player, stack));
    } 
  }
  
  private List<ItemStack> getItems() {
    List<ItemStack> stacks = new ArrayList<>();
    for (int i = 0; i < func_70302_i_(); i++) {
      ItemStack stack = func_70301_a(i);
      if (stack != null)
        stacks.add(stack.func_77946_l()); 
    } 
    return stacks;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("work", this.work);
    if (this.entityUniqueId == null || this.ownerUniqueId == null)
      return; 
    compound.func_74778_a("entityUniqueId", FastUUID.toString(this.entityUniqueId));
    compound.func_74778_a("ownerUniqueId", FastUUID.toString(this.ownerUniqueId));
    compound.func_74772_a("spawnMillis", this.spawnMillis);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.work = compound.func_74762_e("work");
    if (compound.func_74764_b("entityUniqueId") && compound.func_74764_b("spawnMillis") && compound.func_74764_b("ownerUniqueId")) {
      this.entityUniqueId = FastUUID.parseUUID(compound.func_74779_i("entityUniqueId"));
      this.ownerUniqueId = FastUUID.parseUUID(compound.func_74779_i("ownerUniqueId"));
      this.spawnMillis = compound.func_74763_f("spawnMillis");
    } 
  }
  
  public int[] func_94128_d(int side) {
    int[] slots = new int[1];
    return slots;
  }
  
  public boolean func_102007_a(int side, ItemStack stack, int slot) {
    if (slot != 1 || stack.func_77973_b() == ItemsRegister.PARTICLE_AMETHYST || stack
      .func_77973_b() == ItemsRegister.PARTICLE_DIAMOND || stack
      .func_77973_b() == ItemsRegister.PARTICLE_GOLD || stack
      .func_77973_b() == ItemsRegister.PARTICLE_IRON || stack
      .func_77973_b() == ItemsRegister.PARTICLE_PALADIUM || stack
      .func_77973_b() == ItemsRegister.PARTICLE_TITANE || stack
      .func_77973_b() != ItemsRegister.PARTICLE_GREEN_PALADIUM);
    return false;
  }
  
  public boolean func_102008_b(int side, ItemStack stack, int slot) {
    return false;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : ((player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public CobbleBreakerUpgrade getUpgrade() {
    return CobbleBreakerUpgrade.PALADIUM;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\tiles\TileEntityCrabCobbleBreaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */