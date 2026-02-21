package fr.paladium.palamod.modules.luckyblock.tileentity.june;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAlarmClock extends TileEntity {
  private String owner = null;
  
  public String getOwner() {
    return this.owner;
  }
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  private int hourTens = 0;
  
  public int getHourTens() {
    return this.hourTens;
  }
  
  public void setHourTens(int hourTens) {
    this.hourTens = hourTens;
  }
  
  private int hourOnes = 0;
  
  public int getHourOnes() {
    return this.hourOnes;
  }
  
  public void setHourOnes(int hourOnes) {
    this.hourOnes = hourOnes;
  }
  
  private int minuteTens = 0;
  
  public int getMinuteTens() {
    return this.minuteTens;
  }
  
  public void setMinuteTens(int minuteTens) {
    this.minuteTens = minuteTens;
  }
  
  private int minuteOnes = 0;
  
  public int getMinuteOnes() {
    return this.minuteOnes;
  }
  
  public void setMinuteOnes(int minuteOnes) {
    this.minuteOnes = minuteOnes;
  }
  
  private boolean soundActive = false;
  
  private boolean redstoneActive = false;
  
  public boolean isRedstoneActive() {
    return this.redstoneActive;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("owner"))
      this.owner = compound.func_74779_i("owner"); 
    if (compound.func_74764_b("hourTens"))
      this.hourTens = compound.func_74762_e("hourTens"); 
    if (compound.func_74764_b("hourOnes"))
      this.hourOnes = compound.func_74762_e("hourOnes"); 
    if (compound.func_74764_b("minuteTens"))
      this.minuteTens = compound.func_74762_e("minuteTens"); 
    if (compound.func_74764_b("minuteOnes"))
      this.minuteOnes = compound.func_74762_e("minuteOnes"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (this.owner != null)
      compound.func_74778_a("owner", this.owner); 
    compound.func_74768_a("hourTens", this.hourTens);
    compound.func_74768_a("hourOnes", this.hourOnes);
    compound.func_74768_a("minuteTens", this.minuteTens);
    compound.func_74768_a("minuteOnes", this.minuteOnes);
    super.func_145841_b(compound);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    if (this.owner != null)
      dataTag.func_74778_a("owner", this.owner); 
    dataTag.func_74768_a("hourTens", this.hourTens);
    dataTag.func_74768_a("hourOnes", this.hourOnes);
    dataTag.func_74768_a("minuteTens", this.minuteTens);
    dataTag.func_74768_a("minuteOnes", this.minuteOnes);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("owner"))
      this.owner = nbtData.func_74779_i("owner"); 
    if (nbtData.func_74764_b("hourTens"))
      this.hourTens = nbtData.func_74762_e("hourTens"); 
    if (nbtData.func_74764_b("hourOnes"))
      this.hourOnes = nbtData.func_74762_e("hourOnes"); 
    if (nbtData.func_74764_b("minuteTens"))
      this.minuteTens = nbtData.func_74762_e("minuteTens"); 
    if (nbtData.func_74764_b("minuteOnes"))
      this.minuteOnes = nbtData.func_74762_e("minuteOnes"); 
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (!this.field_145850_b.field_72995_K) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(11, this.hourTens * 10 + this.hourOnes);
      calendar.set(12, this.minuteTens * 10 + this.minuteOnes);
      TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
      calendar.setTimeZone(timeZone);
      Date date = calendar.getTime();
      if (date.getTime() == System.currentTimeMillis()) {
        if (!this.redstoneActive) {
          this.redstoneActive = true;
          func_70296_d();
          this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        } 
        if (!this.soundActive) {
          PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound("alarm_clock"), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, 16.0D));
          this.soundActive = true;
        } 
      } else {
        this.soundActive = false;
        if (this.redstoneActive) {
          this.redstoneActive = false;
          func_70296_d();
          this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\june\TileEntityAlarmClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */