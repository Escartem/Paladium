package fr.paladium.palamod.modules.paladium.common.logics;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.vecmath.Vector3f;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TileEntityTotem extends TileEntity {
  private static final String NBT_BLOCKCOORD = "BlockCoord";
  
  private static final String NBT_BLOCKBOOST = "BlockBoost";
  
  private static final String NBT_FUEL = "fuel";
  
  private static final int DEFAULT_MAX_BOOSTED_BLOCKS = 2048;
  
  public void setTimer(int timer) {
    this.timer = timer;
  }
  
  public void setBlockboost(int blockboost) {
    this.blockboost = blockboost;
  }
  
  public void setBlockList(List<Vector3f> blockList) {
    this.blockList = blockList;
  }
  
  public void setRadius(int radius) {
    this.radius = radius;
  }
  
  public void setWaiting(long waiting) {
    this.waiting = waiting;
  }
  
  public void setTimeforgrowth(long timeforgrowth) {
    this.timeforgrowth = timeforgrowth;
  }
  
  public void setFuel(ItemStack fuel) {
    this.fuel = fuel;
  }
  
  public void setOwnerId(UUID ownerId) {
    this.ownerId = ownerId;
  }
  
  public int timer = 0;
  
  public int getTimer() {
    return this.timer;
  }
  
  public int blockboost = 0;
  
  public int getBlockboost() {
    return this.blockboost;
  }
  
  public List<Vector3f> blockList = new ArrayList<>();
  
  protected int radius;
  
  public List<Vector3f> getBlockList() {
    return this.blockList;
  }
  
  public int getRadius() {
    return this.radius;
  }
  
  private long waiting = 0L;
  
  public long getWaiting() {
    return this.waiting;
  }
  
  private long timeforgrowth = 0L;
  
  private ItemStack fuel;
  
  private UUID ownerId;
  
  public long getTimeforgrowth() {
    return this.timeforgrowth;
  }
  
  public ItemStack getFuel() {
    return this.fuel;
  }
  
  public UUID getOwnerId() {
    return this.ownerId;
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    long now = System.currentTimeMillis();
    if (this.fuel != null && this.fuel.field_77994_a > 0) {
      if (this.timeforgrowth == 0L)
        this.timeforgrowth = now + 10000L; 
      if (this.blockList.isEmpty() && now > this.waiting) {
        this.waiting = now + 1000L;
        for (int i = -7; i < 8; i++) {
          for (int j = -7; j < 8; j++) {
            Block block = this.field_145850_b.func_147439_a(this.field_145851_c + j, this.field_145848_d - 1, this.field_145849_e + i);
            if (block instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockConnectedDirt)
              this.blockList.add(new Vector3f((this.field_145851_c + j), (this.field_145848_d - 1), (this.field_145849_e + i))); 
          } 
        } 
      } 
      if (!this.field_145850_b.field_72995_K && 
        now > this.timeforgrowth) {
        int maxBlocks = getMaxBoostedBlocks();
        this.timeforgrowth = System.currentTimeMillis() + 10000L;
        Random rand = this.field_145850_b.field_73012_v;
        this.blockList.forEach(vec -> {
              Block block = this.field_145850_b.func_147439_a((int)vec.x, (int)vec.y, (int)vec.z);
              Block toBoost = this.field_145850_b.func_147439_a((int)vec.x, (int)vec.y + 1, (int)vec.z);
              if (toBoost != null && toBoost != Blocks.field_150350_a && toBoost instanceof net.minecraftforge.common.IPlantable && this.fuel != null)
                if (block instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockConnectedDirt && toBoost instanceof IGrowable) {
                  IGrowable growable = (IGrowable)toBoost;
                  if (growable.func_149852_a(this.field_145850_b, this.field_145850_b.field_73012_v, (int)vec.x, (int)vec.y + 1, (int)vec.z)) {
                    this.field_145850_b.func_72926_e(2005, (int)vec.x, (int)vec.y + 1, (int)vec.z, 0);
                    this.blockboost++;
                    if (this.blockboost > maxBlocks) {
                      this.fuel.field_77994_a--;
                      if (this.fuel.field_77994_a == 0)
                        this.fuel = null; 
                      this.blockboost = 0;
                    } 
                  } 
                  for (int i = 0; i < rand.nextInt(2) + 1; i++) {
                    if (growable.func_149852_a(this.field_145850_b, this.field_145850_b.field_73012_v, (int)vec.x, (int)vec.y + 1, (int)vec.z))
                      toBoost.func_149674_a(this.field_145850_b, (int)vec.x, (int)vec.y + 1, (int)vec.z, rand); 
                  } 
                } else if (block instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockConnectedDirt && toBoost instanceof net.minecraftforge.common.IPlantable) {
                  this.field_145850_b.func_72926_e(2005, (int)vec.x, (int)vec.y + 1, (int)vec.z, 0);
                  this.blockboost++;
                  if (this.blockboost > maxBlocks) {
                    this.fuel.field_77994_a--;
                    if (this.fuel.field_77994_a == 0)
                      this.fuel = null; 
                    this.blockboost = 0;
                  } 
                  for (int i = 0; i < rand.nextInt(2) + 1; i++) {
                    if (this.field_145850_b.func_147437_c((int)vec.x, (int)vec.y + 2, (int)vec.z)) {
                      toBoost.func_149674_a(this.field_145850_b, (int)vec.x, (int)vec.y + 1, (int)vec.z, rand);
                    } else if (this.field_145850_b.func_147439_a((int)vec.x, (int)vec.y + 2, (int)vec.z).equals(toBoost)) {
                      toBoost.func_149674_a(this.field_145850_b, (int)vec.x, (int)vec.y + 2, (int)vec.z, rand);
                    } 
                  } 
                }  
            });
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      } 
    } 
    if (this.field_145850_b.field_72995_K && this.timer != 0)
      this.timer--; 
  }
  
  int getMaxBoostedBlocks() {
    if (this.ownerId == null)
      return 2048; 
    EntityPlayerMP player = MonthlyUtils.getPlayer(this.ownerId);
    if (player == null)
      return 2048; 
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.FERTILIZER.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return 2048; 
    double percentage = response.getValueAsPercent(value);
    int increase = (int)(2048.0D * percentage);
    return 2048 + increase;
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    super.onDataPacket(net, packet);
    func_145839_a(packet.func_148857_g());
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }
  
  public void func_145839_a(NBTTagCompound tagCompound) {
    super.func_145839_a(tagCompound);
    this.blockList.clear();
    NBTTagList tagList = tagCompound.func_150295_c("BlockCoord", 10);
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound tag = tagList.func_150305_b(i);
      this.blockList.add(new Vector3f(tag.func_74762_e("X"), tag.func_74762_e("Y"), tag.func_74762_e("Z")));
    } 
    this.blockboost = tagCompound.func_74762_e("BlockBoost");
    if (tagCompound.func_74764_b("fuel")) {
      this.fuel = ItemStack.func_77949_a(tagCompound.func_74775_l("fuel"));
    } else {
      this.fuel = null;
    } 
    if (tagCompound.func_74764_b("owner"))
      this.ownerId = FastUUID.parseUUID(tagCompound.func_74779_i("owner")); 
  }
  
  public void func_145841_b(NBTTagCompound tagCompound) {
    super.func_145841_b(tagCompound);
    NBTTagList coordlist = new NBTTagList();
    this.blockList.forEach(vec -> {
          NBTTagCompound nbt = new NBTTagCompound();
          nbt.func_74768_a("X", (int)vec.x);
          nbt.func_74768_a("Y", (int)vec.y);
          nbt.func_74768_a("Z", (int)vec.z);
          coordlist.func_74742_a((NBTBase)nbt);
        });
    tagCompound.func_74782_a("BlockCoord", (NBTBase)coordlist);
    tagCompound.func_74768_a("BlockBoost", this.blockboost);
    if (this.fuel != null) {
      NBTTagCompound nbt = new NBTTagCompound();
      this.fuel.func_77955_b(nbt);
      tagCompound.func_74782_a("fuel", (NBTBase)nbt);
    } 
    if (this.ownerId != null)
      tagCompound.func_74778_a("owner", FastUUID.toString(this.ownerId)); 
  }
  
  public void lightZone(EntityPlayer player) {
    long now = System.currentTimeMillis();
    if (this.field_145850_b.field_72995_K && 
      this.timer == 0)
      this.timer = 60; 
    if (now <= this.waiting)
      return; 
    this.blockList.clear();
    this.waiting = now + 10000L;
    for (int i = -7; i < 8; i++) {
      for (int j = -7; j < 8; j++) {
        Block block = this.field_145850_b.func_147439_a(this.field_145851_c + j, this.field_145848_d - 1, this.field_145849_e + i);
        if (block instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockConnectedDirt)
          this.blockList.add(new Vector3f((this.field_145851_c + j), (this.field_145848_d - 1), (this.field_145849_e + i))); 
      } 
    } 
  }
  
  public void addfuel(EntityPlayer player) {
    ItemStack fuel = player.func_70694_bm();
    if (this.fuel != null && this.fuel.func_77973_b().equals(fuel.func_77973_b())) {
      if (this.fuel.field_77994_a + 1 <= 64) {
        this.fuel.field_77994_a++;
        fuel.field_77994_a--;
      } 
      if (!this.field_145850_b.field_72995_K) {
        UseItemAchievement.performCheck(player, fuel, "FUEL_PALADIUM_TOTEM", 1);
        player.func_70062_b(0, fuel);
      } 
    } else if (!fuel.func_77973_b().equals(ItemsRegister.PALADIUM_INGOT)) {
      if (!this.field_145850_b.field_72995_K) {
        player.func_70062_b(0, fuel);
        player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §fVous ne pouvez pas remplir ce totem avec ce minerais."));
      } 
    } else {
      this.fuel = new ItemStack(fuel.func_77973_b(), 1);
      fuel.field_77994_a--;
      if (!this.field_145850_b.field_72995_K)
        player.func_70062_b(0, fuel); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TileEntityTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */