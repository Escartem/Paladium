package fr.paladium.palamod.modules.miner.tileentity;

import fr.paladium.lib.apollon.container.abstracts.PaladiumTileInventory;
import fr.paladium.palajobs.core.utils.PlacedBlockData;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.factions.dto.leveling.Tristate;
import fr.paladium.palamod.modules.miner.utils.BlockData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class TileEntityDrawBridge extends PaladiumTileInventory {
  private int direction;
  
  private int animation;
  
  private int tick;
  
  public int getDirection() {
    return this.direction;
  }
  
  public void setAnimation(int animation) {
    this.animation = animation;
  }
  
  private Map<Integer, List<BlockData>> placed = new HashMap<>();
  
  private EntityPlayer lastPlayer;
  
  public TileEntityDrawBridge() {
    super("container.drawbridge", 16);
  }
  
  public void func_145845_h() {
    if (this.tick % 2 == 0 && !this.field_145850_b.field_72995_K) {
      if (this.animation >= (getContent()).length)
        this.animation = 0; 
      if (this.animation < 0)
        this.animation = (getContent()).length - 1; 
      int finalX = this.field_145851_c + ((this.direction == 0) ? 0 : ((this.direction == 1) ? (this.animation + 1) : ((this.direction == 2) ? 0 : (-this.animation - 1))));
      int finalY = this.field_145848_d;
      int finalZ = this.field_145849_e + ((this.direction == 0) ? (-this.animation - 1) : ((this.direction == 1) ? 0 : ((this.direction == 2) ? (this.animation + 1) : 0)));
      if (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
        if (getContent()[this.animation] != null) {
          if (isBlacklist(getContent()[this.animation].func_77973_b()) || (this.lastPlayer != null && !JobsBridge.canUseCraft(this.lastPlayer, getContent()[this.animation], false))) {
            this.animation++;
            return;
          } 
          Block block = Block.func_149634_a(getContent()[this.animation].func_77946_l().func_77973_b());
          int meta = getContent()[this.animation].func_77946_l().func_77960_j();
          if (block != null && block != Blocks.field_150350_a && block != BlocksRegister.DRAWBRIDGE && !block.hasTileEntity(0)) {
            Block b = this.field_145850_b.func_147439_a(finalX, finalY, finalZ);
            if (b == Blocks.field_150350_a && 
              getContent()[this.animation] != null && (block != BlocksRegister.ELEVATOR_BLOCK || canPlaceBlock(this.lastPlayer, this.field_145850_b, finalX, finalZ))) {
              this.field_145850_b.func_147449_b(finalX, finalY, finalZ, block);
              this.field_145850_b.func_72921_c(finalX, finalY, finalZ, meta, 3);
              PlacedBlockData.setPlaced(this.field_145850_b, finalX, finalY, finalZ);
              (getContent()[this.animation]).field_77994_a--;
              if ((getContent()[this.animation]).field_77994_a <= 0)
                getContent()[this.animation] = null; 
              List<BlockData> datas = this.placed.getOrDefault(Integer.valueOf(this.direction), new ArrayList<>());
              if (!datas.contains(new BlockData(block, finalX, finalY, finalZ, meta)))
                datas.add(new BlockData(block, finalX, finalY, finalZ, meta)); 
              this.placed.put(Integer.valueOf(this.direction), datas);
            } 
          } 
        } 
        this.animation++;
      } else if (this.placed.containsKey(Integer.valueOf(this.direction))) {
        List<BlockData> toRemove = new ArrayList<>();
        for (BlockData data : this.placed.get(Integer.valueOf(this.direction))) {
          Block b = this.field_145850_b.func_147439_a(finalX, finalY, finalZ);
          if (b == data.getBlock() && data.getPos().isEquals(finalX, finalY, finalZ) && (b != BlocksRegister.ELEVATOR_BLOCK || canPlaceBlock(this.lastPlayer, this.field_145850_b, finalX, finalZ))) {
            ItemStack item = getContent()[this.animation];
            if (item == null) {
              this.field_145850_b.func_147468_f(finalX, finalY, finalZ);
              PlacedBlockData.removePlaced(this.field_145850_b, finalX, finalY, finalZ);
              getContent()[this.animation] = new ItemStack(data.getBlock(), 1, data.getMeta());
            } else if (item.func_77973_b() == Item.func_150898_a(data.getBlock())) {
              Item contentItem = Item.func_150898_a(data.getBlock());
              if (item.func_77960_j() == contentItem.func_77612_l() && !item.func_77942_o()) {
                this.field_145850_b.func_147468_f(finalX, finalY, finalZ);
                PlacedBlockData.removePlaced(this.field_145850_b, finalX, finalY, finalZ);
                item.field_77994_a++;
                if (item.field_77994_a > Math.min(item.func_77976_d(), func_70297_j_()))
                  item.field_77994_a = Math.min(item.func_77976_d(), func_70297_j_()); 
              } 
            } 
            toRemove.add(data);
          } 
        } 
        for (BlockData data : toRemove)
          ((List)this.placed.get(Integer.valueOf(this.direction))).remove(data); 
        this.animation--;
      } 
    } 
    this.tick++;
    super.func_145845_h();
  }
  
  private boolean canPlaceBlock(EntityPlayer player, World world, int x, int z) {
    if (player == null)
      return PFactions.instance.getImpl().isFactionClaim(world, x, z); 
    return (PFactions.instance.getImpl().hasPermission(player, "BUILD", x, z) != Tristate.FALSE);
  }
  
  public void setDirection(int direction) {
    this.animation = 0;
    this.direction = direction;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74768_a("direction", this.direction);
    for (Iterator<Integer> iterator = this.placed.keySet().iterator(); iterator.hasNext(); ) {
      int dir = ((Integer)iterator.next()).intValue();
      NBTTagList placedNBT = new NBTTagList();
      for (BlockData data : this.placed.get(Integer.valueOf(dir))) {
        NBTTagCompound nbtCompound = new NBTTagCompound();
        nbtCompound.func_74768_a("id", Block.func_149682_b(data.getBlock()));
        nbtCompound.func_74768_a("x", data.getPos().getX());
        nbtCompound.func_74768_a("y", data.getPos().getY());
        nbtCompound.func_74768_a("z", data.getPos().getZ());
        nbtCompound.func_74768_a("meta", data.getMeta());
        placedNBT.func_74742_a((NBTBase)nbtCompound);
      } 
      compound.func_74782_a("dir" + dir, (NBTBase)placedNBT);
    } 
    super.func_145841_b(compound);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("direction"))
      this.direction = compound.func_74762_e("direction"); 
    for (int dir = 0; dir < 4; dir++) {
      if (compound.func_74764_b("dir" + dir)) {
        NBTTagList placedNBT = compound.func_150295_c("dir" + dir, 10);
        List<BlockData> datas = new ArrayList<>();
        for (int i = 0; i < placedNBT.func_74745_c(); i++) {
          NBTTagCompound nbtCompound = placedNBT.func_150305_b(i);
          int id = nbtCompound.func_74762_e("id");
          int x = nbtCompound.func_74762_e("x");
          int y = nbtCompound.func_74762_e("y");
          int z = nbtCompound.func_74762_e("z");
          int meta = nbtCompound.func_74762_e("meta");
          datas.add(new BlockData(Block.func_149729_e(id), x, y, z, meta));
        } 
        this.placed.put(Integer.valueOf(dir), datas);
      } 
    } 
    super.func_145839_a(compound);
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D);
  }
  
  public boolean func_94041_b(int slot, ItemStack item) {
    return !isBlacklist(item);
  }
  
  private boolean isBlacklist(ItemStack item) {
    return isBlacklist(item.func_77973_b());
  }
  
  private boolean isBlacklist(Item item) {
    Block b = Block.func_149634_a(item);
    return (b == null || b == Blocks.field_150350_a || b.hasTileEntity(0) || b.func_149744_f());
  }
  
  public EntityPlayer getLastPlayer() {
    return this.lastPlayer;
  }
  
  public void setLastPlayer(EntityPlayer lastPlayer) {
    this.lastPlayer = lastPlayer;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\tileentity\TileEntityDrawBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */