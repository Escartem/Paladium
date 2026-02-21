package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.lib.apollon.container.abstracts.PaladiumTileInventory;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.miner.item.ItemCobbleBreakerUpgrade;
import fr.paladium.palamod.modules.paladium.common.items.ItemVoidStoneMinage;
import fr.paladium.palamod.modules.paladium.common.logics.cobblebreaker.CobbleBreakerUpgrade;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import fr.paladium.tutorial.common.event.CobbleBreakerGenerateEvent;
import java.util.UUID;
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
import net.minecraftforge.common.MinecraftForge;

public class TileCobbleBreaker extends PaladiumTileInventory implements ISidedInventory {
  private int work = 0;
  
  private UUID owner;
  
  public TileCobbleBreaker() {
    super("tile.CobbleBreaker", 8);
    this.owner = null;
  }
  
  private double applyPetSkill(double random) {
    if (this.owner == null || random <= 0.0D)
      return random; 
    EntityPlayerMP player = MonthlyUtils.getPlayer(this.owner);
    if (player == null)
      return random; 
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.GEOLOGY.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return random; 
    double percentage = response.getValueAsPercent(value);
    double deducted = random * percentage;
    return random - deducted;
  }
  
  private void callEvent(ItemStack item) {
    if (this.field_145850_b.field_72995_K)
      return; 
    EntityPlayerMP player = MonthlyUtils.getPlayer(this.owner);
    if (player == null)
      return; 
    MinecraftForge.EVENT_BUS.post((Event)new CobbleBreakerGenerateEvent((EntityPlayer)player, item));
  }
  
  public void func_145845_h() {
    if (func_70301_a(0) != null && (
      func_70301_a(0).func_77973_b().equals(Item.func_150898_a(Blocks.field_150347_e)) || 
      func_70301_a(0).func_77973_b() instanceof ItemVoidStoneMinage) && (
      func_70301_a(1) == null || (func_70301_a(1)).field_77994_a < 64 || 
      func_70301_a(2) == null || (func_70301_a(2)).field_77994_a < 64 || 
      func_70301_a(3) == null || (func_70301_a(3)).field_77994_a < 64 || 
      func_70301_a(4) == null || (func_70301_a(4)).field_77994_a < 64 || 
      func_70301_a(5) == null || (func_70301_a(5)).field_77994_a < 64 || 
      func_70301_a(6) == null || (func_70301_a(6)).field_77994_a < 64)) {
      if (this.work >= getUpgrade().getWork()) {
        if (!this.field_145850_b.field_72995_K) {
          double random = (int)(Math.random() * 250.0D);
          random = applyPetSkill(random);
          if (random <= 1.0D)
            if (func_70301_a(6) != null) {
              if ((func_70301_a(6)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(6).func_77973_b(), (func_70301_a(6)).field_77994_a + 1, func_70301_a(6).func_77960_j());
                func_70299_a(6, paladium);
                callEvent(paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_PALADIUM, 1, 0);
              func_70299_a(6, paladium);
              callEvent(paladium);
              PPalaDynamique.instance.addGenerated("COBBLE_BREAKER", 0.1111111111111111D);
            }  
          if (random > 1.0D && random <= 3.0D)
            if (func_70301_a(5) != null) {
              if ((func_70301_a(5)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(5).func_77973_b(), (func_70301_a(5)).field_77994_a + 1, func_70301_a(5).func_77960_j());
                callEvent(paladium);
                func_70299_a(5, paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_TITANE, 1, 0);
              func_70299_a(5, paladium);
              callEvent(paladium);
            }  
          if (random > 3.0D && random <= 7.0D)
            if (func_70301_a(4) != null) {
              if ((func_70301_a(4)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(4).func_77973_b(), (func_70301_a(4)).field_77994_a + 1, func_70301_a(4).func_77960_j());
                func_70299_a(4, paladium);
                callEvent(paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_AMETHYST, 1, 0);
              func_70299_a(4, paladium);
              callEvent(paladium);
            }  
          if (random > 7.0D && random <= 12.0D)
            if (func_70301_a(3) != null) {
              if ((func_70301_a(3)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(3).func_77973_b(), (func_70301_a(3)).field_77994_a + 1, func_70301_a(3).func_77960_j());
                func_70299_a(3, paladium);
                callEvent(paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_DIAMOND, 1, 0);
              func_70299_a(3, paladium);
              callEvent(paladium);
            }  
          if (random > 12.0D && random <= 20.0D)
            if (func_70301_a(2) != null) {
              if ((func_70301_a(2)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(2).func_77973_b(), (func_70301_a(2)).field_77994_a + 1, func_70301_a(2).func_77960_j());
                func_70299_a(2, paladium);
                callEvent(paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_GOLD, 1, 0);
              func_70299_a(2, paladium);
              callEvent(paladium);
            }  
          if (random > 20.0D && random <= 30.0D)
            if (func_70301_a(1) != null) {
              if ((func_70301_a(1)).field_77994_a < 64) {
                ItemStack paladium = new ItemStack(func_70301_a(1).func_77973_b(), (func_70301_a(1)).field_77994_a + 1, func_70301_a(1).func_77960_j());
                func_70299_a(1, paladium);
                callEvent(paladium);
              } 
            } else {
              ItemStack paladium = new ItemStack(ItemsRegister.PARTICLE_IRON, 1, 0);
              func_70299_a(1, paladium);
              callEvent(paladium);
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
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("work", this.work);
    if (this.owner != null)
      compound.func_74778_a("owner", FastUUID.toString(this.owner)); 
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.work = compound.func_74762_e("work");
    if (compound.func_74764_b("owner"))
      this.owner = FastUUID.parseUUID(compound.func_74779_i("owner")); 
  }
  
  public int[] func_94128_d(int side) {
    int[] slots = new int[1];
    return slots;
  }
  
  public boolean func_102007_a(int side, ItemStack stack, int slot) {
    if (slot == 1 && stack.func_77973_b() != ItemsRegister.PARTICLE_AMETHYST && stack
      .func_77973_b() != ItemsRegister.PARTICLE_DIAMOND && stack
      .func_77973_b() != ItemsRegister.PARTICLE_GOLD && stack
      .func_77973_b() != ItemsRegister.PARTICLE_IRON && stack
      .func_77973_b() != ItemsRegister.PARTICLE_PALADIUM && stack
      .func_77973_b() != ItemsRegister.PARTICLE_TITANE)
      return true; 
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
  
  public void setOwner(EntityPlayerMP player) {
    this.owner = player.func_110124_au();
  }
  
  public CobbleBreakerUpgrade getUpgrade() {
    if (func_70301_a(7) != null && 
      func_70301_a(7).func_77973_b() instanceof ItemCobbleBreakerUpgrade) {
      ItemCobbleBreakerUpgrade item = (ItemCobbleBreakerUpgrade)func_70301_a(7).func_77973_b();
      return item.getType();
    } 
    return CobbleBreakerUpgrade.DEFAULT;
  }
  
  public int getWork() {
    return this.work;
  }
  
  public void setWork(int work) {
    this.work = work;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TileCobbleBreaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */