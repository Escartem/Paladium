package fr.paladium.palamod.modules.paladium.common.entities.ancient;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import fr.paladium.zephyrui.internal.mod.utils.command.uuid.UUIDUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class AncientHammerFakePlayerEntity extends EntityZombie implements IEntityAdditionalSpawnData {
  private String playerName;
  
  private UUID playerUUID;
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public UUID getPlayerUUID() {
    return this.playerUUID;
  }
  
  public AncientHammerFakePlayerEntity(World world) {
    super(world);
    this.playerName = "Dinnerbone";
    this.playerUUID = UUID.randomUUID();
    this.field_70178_ae = true;
  }
  
  public AncientHammerFakePlayerEntity(World world, EntityPlayer player) {
    super(world);
    func_70080_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70177_z, player.field_70125_A);
    this.playerName = player.func_70005_c_();
    this.playerUUID = player.func_110124_au();
    this.field_70178_ae = true;
    func_70062_b(0, player.func_70694_bm());
    func_70062_b(1, player.func_82169_q(0));
    func_70062_b(2, player.func_82169_q(1));
    func_70062_b(3, player.func_82169_q(2));
    func_70062_b(4, player.func_82169_q(3));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
    func_110148_a(EntityZombie.field_110186_bp).func_111128_a(0.0D);
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.field_70173_aa > 600 && !this.field_70170_p.field_72995_K)
      func_70106_y(); 
  }
  
  public void func_70106_y() {
    if (!this.field_70170_p.field_72995_K) {
      EntityPlayerMP player = PlayerUtils.getPlayer(this.playerUUID);
      if (player == null)
        return; 
      player.func_82142_c(false);
      ((WorldServer)this.field_70170_p).func_147487_a("portal", player.field_70165_t, player.field_70163_u + player.func_70047_e() / 2.0D, player.field_70161_v, 50, 0.4D, 0.4D, 0.4D, 0.0D);
      this.field_70170_p.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
      ItemAncientHammerPlayerData.get((EntityPlayer)player).setInvisibilityEffect(false);
    } 
    super.func_70106_y();
  }
  
  public int func_70658_aO() {
    int i = 0;
    ItemStack[] aitemstack = func_70035_c();
    int j = aitemstack.length;
    for (int k = 0; k < j; k++) {
      ItemStack itemstack = aitemstack[k];
      if (itemstack != null && itemstack.func_77973_b() instanceof ItemArmor) {
        int l = ((ItemArmor)itemstack.func_77973_b()).field_77879_b;
        i += l;
      } 
    } 
    return i;
  }
  
  protected void func_82160_b(boolean p_82160_1_, int p_82160_2_) {}
  
  protected void func_70600_l(int p_70600_1_) {}
  
  protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {}
  
  protected Item func_146068_u() {
    return null;
  }
  
  protected String func_70621_aR() {
    return "game.player.hurt";
  }
  
  protected String func_70673_aS() {
    return "game.player.die";
  }
  
  protected String func_145776_H() {
    return "game.player.swim";
  }
  
  protected String func_145777_O() {
    return "game.player.swim.splash";
  }
  
  protected String func_70639_aQ() {
    return null;
  }
  
  protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block block) {
    func_85030_a(block.field_149762_H.func_150498_e(), 0.15F, 1.0F);
  }
  
  public void func_70014_b(NBTTagCompound tag) {
    super.func_70014_b(tag);
    tag.func_74778_a("PlayerName", this.playerName);
    tag.func_74778_a("PlayerUUID", UUIDUtils.toString(this.playerUUID));
  }
  
  public void func_70037_a(NBTTagCompound tag) {
    super.func_70037_a(tag);
    this.playerName = tag.func_74779_i("PlayerName");
    this.playerUUID = UUIDUtils.from(tag.func_74779_i("PlayerUUID"));
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    ByteBufUtils.writeUTF8String(buffer, this.playerName);
    ByteBufUtils.writeUTF8String(buffer, UUIDUtils.toString(this.playerUUID));
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (!additionalData.isReadable())
      return; 
    this.playerName = ByteBufUtils.readUTF8String(additionalData);
    this.playerUUID = UUIDUtils.from(ByteBufUtils.readUTF8String(additionalData));
  }
  
  public boolean func_90999_ad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\entities\ancient\AncientHammerFakePlayerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */