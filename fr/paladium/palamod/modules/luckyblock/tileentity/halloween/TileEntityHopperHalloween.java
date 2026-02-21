package fr.paladium.palamod.modules.luckyblock.tileentity.halloween;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class TileEntityHopperHalloween extends TileEntity {
  private int paladiumNeeded;
  
  private long timer;
  
  private EntityPlayer target;
  
  public int getPaladiumNeeded() {
    return this.paladiumNeeded;
  }
  
  public long getTimer() {
    return this.timer;
  }
  
  public void setTarget(EntityPlayer target) {
    this.target = target;
  }
  
  public EntityPlayer getTarget() {
    return this.target;
  }
  
  public TileEntityHopperHalloween() {
    this.paladiumNeeded = ((this.field_145850_b == null || this.field_145850_b.field_73012_v == null) ? new Random() : this.field_145850_b.field_73012_v).nextInt(35);
    this.timer = LocalDateTime.now(ZoneId.of("UTC")).toEpochSecond(ZoneOffset.UTC) + 300L;
  }
  
  public TileEntityHopperHalloween(EntityPlayer target) {
    this.paladiumNeeded = target.field_70170_p.field_73012_v.nextInt(35);
    this.timer = LocalDateTime.now(ZoneId.of("UTC")).toEpochSecond(ZoneOffset.UTC) + 300L;
    this.target = target;
  }
  
  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {
      if (this.timer < LocalDateTime.now(ZoneId.of("UTC")).toEpochSecond(ZoneOffset.UTC)) {
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a);
        if (this.target != null) {
          LuckyEvents event = LuckyEvents.getRandomBadEvent();
          this.target.func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Vous n'avez pas remplie le Hopper assez vite"));
          this.target.func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Vous allez avoir un gage (" + event
                
                .getEvent().getName() + ")"));
          event.getEvent().perform((EntityPlayerMP)this.target, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } 
        return;
      } 
      AxisAlignedBB bounds = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e).func_149668_a(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
      bounds = bounds.func_72314_b(0.0D, 1.0D, 0.0D);
      List<EntityItem> entities = this.field_145850_b.func_72872_a(EntityItem.class, bounds);
      entities.stream()
        .filter(e -> e.func_92059_d().func_77973_b().equals(ItemsRegister.PALADIUM_INGOT))
        .forEach(e -> {
            if (!e.field_70128_L && this.paladiumNeeded > 0) {
              this.paladiumNeeded -= (e.func_92059_d()).field_77994_a;
              e.func_70106_y();
              func_70296_d();
              if (this.paladiumNeeded < 0)
                this.paladiumNeeded = 0; 
            } 
          });
      if (this.paladiumNeeded == 0) {
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a);
      } else {
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      } 
    } 
  }
  
  public void func_145841_b(NBTTagCompound tag) {
    super.func_145841_b(tag);
    tag.func_74772_a("timer", this.timer);
    tag.func_74768_a("paladiumNeeded", this.paladiumNeeded);
  }
  
  public void func_145839_a(NBTTagCompound tag) {
    super.func_145839_a(tag);
    this.timer = tag.func_74763_f("timer");
    this.paladiumNeeded = tag.func_74762_e("paladiumNeeded");
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\halloween\TileEntityHopperHalloween.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */