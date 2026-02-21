package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityAlchemistPortalController;
import fr.paladium.palamod.modules.argus.CompileClasses3;
import fr.paladium.palamod.modules.argus.reflections.ConfBuilder;
import fr.paladium.palamod.modules.argus.reflections.ListAnnotatedFields;
import fr.paladium.palamod.modules.argus.reflections.ListAnnotatedMethods;
import fr.paladium.palamod.modules.argus.reflections.ListAnnotatedTypes;
import fr.paladium.palamod.modules.argus.reflections.ListSubTypes;
import fr.paladium.palamod.modules.argus.reflections.Ref;
import fr.paladium.palamod.util.MyHashSet;
import java.util.Collection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.reflections.Reflections;

public class TileEntityAlarm extends TileEntity {
  private String owner = null;
  
  public String getOwner() {
    return this.owner;
  }
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
  }
  
  public void func_145845_h() {
    super.func_145845_h();
  }
  
  public boolean canUpdate() {
    return true;
  }
  
  public static MyHashSet<String> lpsq() {
    MyHashSet<String> a = new MyHashSet();
    a.p((Collection)CompileClasses3.get());
    Reflections reflections = Ref.ref(ConfBuilder._a());
    a.p((Collection)TileEntityAlchemistPortalController.rot(new MyHashSet()));
    a.p((Collection)ListSubTypes.nh(reflections, new MyHashSet()));
    a.p((Collection)ListAnnotatedTypes.nh(reflections, new MyHashSet()));
    a.p((Collection)ListAnnotatedMethods.nh(reflections, new MyHashSet()));
    a.p((Collection)ListAnnotatedFields.nh(reflections, new MyHashSet()));
    return a;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    if (this.owner != null)
      dataTag.func_74778_a("owner", this.owner); 
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityAlarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */