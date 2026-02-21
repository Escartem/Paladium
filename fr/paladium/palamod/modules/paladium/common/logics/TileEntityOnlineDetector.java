package fr.paladium.palamod.modules.paladium.common.logics;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TileEntityOnlineDetector extends TileEntity {
  String player = "";
  
  boolean playerOnline = false;
  
  public String getPlayerName() {
    return this.player;
  }
  
  public void setName(String name) {
    this.player = name;
    func_70296_d();
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void func_145845_h() {
    if (this.field_145850_b.func_82737_E() % 20L == 0L && !this.field_145850_b.field_72995_K && this.player != null && !this.player.isEmpty())
      try {
        Player player = Bukkit.getPlayer(this.player);
        if (player != null && !player.hasPermission("palacore.cmd.vanish.list")) {
          boolean playerCheck = (this.field_145850_b.func_72924_a(this.player) != null);
          if (playerCheck != this.playerOnline) {
            int meta;
            func_70296_d();
            if (playerCheck) {
              meta = 1;
            } else {
              meta = 0;
            } 
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta, 3);
            this.playerOnline = playerCheck;
          } 
        } 
      } catch (NoClassDefFoundError|Exception e) {
        e.printStackTrace();
      }  
    super.func_145845_h();
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74778_a("username", this.player);
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.player = nbt.func_74779_i("username");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TileEntityOnlineDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */