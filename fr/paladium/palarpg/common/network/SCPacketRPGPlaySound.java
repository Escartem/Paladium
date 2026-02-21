package fr.paladium.palarpg.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import lombok.NonNull;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.util.ResourceLocation;

public class SCPacketRPGPlaySound extends ForgePacket {
  @PacketData
  private String sound;
  
  @PacketData
  private String category;
  
  @PacketData
  private float volume;
  
  @PacketData
  private float pitch;
  
  public SCPacketRPGPlaySound() {}
  
  public SCPacketRPGPlaySound(String sound, String category, float volume, float pitch) {
    this.sound = sound;
    this.category = category;
    this.volume = volume;
    this.pitch = pitch;
  }
  
  public SCPacketRPGPlaySound(@NonNull String sound) {
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    this.sound = sound;
    this.category = "master";
    this.volume = 1.0F;
    this.pitch = 1.0F;
  }
  
  public SCPacketRPGPlaySound(@NonNull String sound, @NonNull String category) {
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    if (category == null)
      throw new NullPointerException("category is marked non-null but is null"); 
    this.sound = sound;
    this.category = category;
    this.volume = 1.0F;
    this.pitch = 1.0F;
  }
  
  public SCPacketRPGPlaySound(@NonNull String sound, float volume) {
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    this.sound = sound;
    this.category = "master";
    this.volume = volume;
    this.pitch = 1.0F;
  }
  
  public SCPacketRPGPlaySound(@NonNull String sound, @NonNull String category, float volume) {
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    if (category == null)
      throw new NullPointerException("category is marked non-null but is null"); 
    this.sound = sound;
    this.category = category;
    this.volume = volume;
    this.pitch = 1.0F;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    SoundRecord.create(new ResourceLocation("palarpg", this.sound)).build(SoundCategory.func_147154_a(this.category)).volume(this.volume).pitch(this.pitch).play();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\common\network\SCPacketRPGPlaySound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */