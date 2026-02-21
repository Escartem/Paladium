package fr.paladium.palaforgeutils.lib.sound;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayerMP;

public final class SoundRecordServer implements IBufSerializable {
  private String sound;
  
  private String category;
  
  private float pitch;
  
  private float x;
  
  private float y;
  
  private float z;
  
  private boolean repeat;
  
  private int repeatDelay;
  
  private String attenuation;
  
  private float volume;
  
  private boolean hasSoundTransition;
  
  private float soundTransitionVolume;
  
  private long soundTransitionDuration;
  
  private boolean soundTransitionShouldStop;
  
  protected SoundRecordServer() {}
  
  public String getSound() {
    return this.sound;
  }
  
  public String getCategory() {
    return this.category;
  }
  
  public float getPitch() {
    return this.pitch;
  }
  
  public float getX() {
    return this.x;
  }
  
  public float getY() {
    return this.y;
  }
  
  public float getZ() {
    return this.z;
  }
  
  public boolean isRepeat() {
    return this.repeat;
  }
  
  public int getRepeatDelay() {
    return this.repeatDelay;
  }
  
  public String getAttenuation() {
    return this.attenuation;
  }
  
  public float getVolume() {
    return this.volume;
  }
  
  public boolean isHasSoundTransition() {
    return this.hasSoundTransition;
  }
  
  public float getSoundTransitionVolume() {
    return this.soundTransitionVolume;
  }
  
  public long getSoundTransitionDuration() {
    return this.soundTransitionDuration;
  }
  
  public boolean isSoundTransitionShouldStop() {
    return this.soundTransitionShouldStop;
  }
  
  private SoundRecordServer(@NonNull String sound, float volume, float pitch, boolean repeat, int repeatDelay, @NonNull String attenuation, float x, float y, float z, @NonNull String category) {
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    if (attenuation == null)
      throw new NullPointerException("attenuation is marked non-null but is null"); 
    if (category == null)
      throw new NullPointerException("category is marked non-null but is null"); 
    this.sound = sound;
    this.volume = volume;
    this.pitch = pitch;
    this.x = x;
    this.y = y;
    this.z = z;
    this.repeat = repeat;
    this.repeatDelay = repeatDelay;
    this.attenuation = attenuation;
    this.category = category;
  }
  
  public void read(ByteBuf buf) {
    this.sound = PacketSerialUtils.readString(buf);
    this.category = PacketSerialUtils.readString(buf);
    this.pitch = buf.readFloat();
    this.x = buf.readFloat();
    this.y = buf.readFloat();
    this.z = buf.readFloat();
    this.repeat = buf.readBoolean();
    this.repeatDelay = buf.readInt();
    this.attenuation = PacketSerialUtils.readString(buf);
    this.volume = buf.readFloat();
    this.hasSoundTransition = buf.readBoolean();
    this.soundTransitionVolume = buf.readFloat();
    this.soundTransitionDuration = buf.readLong();
    this.soundTransitionShouldStop = buf.readBoolean();
  }
  
  public void write(ByteBuf buf) {
    PacketSerialUtils.writeString(buf, this.sound);
    PacketSerialUtils.writeString(buf, this.category);
    buf.writeFloat(this.pitch);
    buf.writeFloat(this.x);
    buf.writeFloat(this.y);
    buf.writeFloat(this.z);
    buf.writeBoolean(this.repeat);
    buf.writeInt(this.repeatDelay);
    PacketSerialUtils.writeString(buf, this.attenuation);
    buf.writeFloat(this.volume);
    buf.writeBoolean(this.hasSoundTransition);
    buf.writeFloat(this.soundTransitionVolume);
    buf.writeLong(this.soundTransitionDuration);
    buf.writeBoolean(this.soundTransitionShouldStop);
  }
  
  @NonNull
  public static SoundRecordServer create(@NonNull String sound) {
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    return new SoundRecordServer(sound, 0.25F, 1.0F, false, 0, "none", 0.0F, 0.0F, 0.0F, "master");
  }
  
  @NonNull
  public static SoundRecordServer create(@NonNull String modId, @NonNull String sound) {
    if (modId == null)
      throw new NullPointerException("modId is marked non-null but is null"); 
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    return create(modId + ":" + sound);
  }
  
  @NonNull
  public static SoundRecordServer create(@NonNull Object mod, @NonNull String sound) {
    if (mod == null)
      throw new NullPointerException("mod is marked non-null but is null"); 
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    if (!mod.getClass().isAnnotationPresent((Class)Mod.class))
      throw new IllegalArgumentException("The object is not a mod instance"); 
    Mod annotation = mod.getClass().<Mod>getAnnotation(Mod.class);
    return create(annotation.modid(), sound);
  }
  
  @NonNull
  public SoundRecordServer sound(@NonNull String sound) {
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    this.sound = sound;
    return this;
  }
  
  @NonNull
  public SoundRecordServer volume(float volume) {
    this.volume = volume / 4.0F;
    return this;
  }
  
  @NonNull
  public SoundRecordServer volume(float volume, long transitionDuration) {
    volume(volume, transitionDuration, true);
    return this;
  }
  
  @NonNull
  public SoundRecordServer volume(float volume, long transitionDuration, boolean stop) {
    this.hasSoundTransition = true;
    this.soundTransitionVolume = volume;
    this.soundTransitionDuration = transitionDuration;
    this.soundTransitionShouldStop = stop;
    return this;
  }
  
  @NonNull
  public SoundRecordServer pitch(float pitch) {
    this.pitch = pitch;
    return this;
  }
  
  @NonNull
  public SoundRecordServer repeat(boolean repeat) {
    this.repeat = repeat;
    return this;
  }
  
  @NonNull
  public SoundRecordServer repeat(boolean repeat, int repeatDelay) {
    this.repeat = repeat;
    this.repeatDelay = repeatDelay;
    return this;
  }
  
  @NonNull
  public SoundRecordServer loop() {
    this.repeat = true;
    return this;
  }
  
  @NonNull
  public SoundRecordServer repeatDelay(int repeatDelay) {
    this.repeatDelay = repeatDelay;
    return this;
  }
  
  @NonNull
  public SoundRecordServer attenuation(@NonNull String attenuation) {
    if (attenuation == null)
      throw new NullPointerException("attenuation is marked non-null but is null"); 
    this.attenuation = attenuation;
    return this;
  }
  
  @NonNull
  public SoundRecordServer position(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }
  
  @NonNull
  public SoundRecordServer category(@NonNull String category) {
    if (category == null)
      throw new NullPointerException("category is marked non-null but is null"); 
    this.category = category;
    return this;
  }
  
  @NonNull
  public SoundRecordServer copy() {
    SoundRecordServer record = new SoundRecordServer(this.sound, this.volume, this.pitch, this.repeat, this.repeatDelay, this.attenuation, this.x, this.y, this.z, this.category);
    record.hasSoundTransition = this.hasSoundTransition;
    record.soundTransitionVolume = this.soundTransitionVolume;
    record.soundTransitionDuration = this.soundTransitionDuration;
    record.soundTransitionShouldStop = this.soundTransitionShouldStop;
    return record;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public SoundRecordServer play() {
    return play(PlayerSelector.ALL());
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public SoundRecordServer play(@NonNull EntityPlayerMP player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return play(PlayerSelector.PLAYER(player));
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public SoundRecordServer play(@NonNull PlayerSelector selector) {
    if (selector == null)
      throw new NullPointerException("selector is marked non-null but is null"); 
    SCPacketPlaySoundRecord packet = new SCPacketPlaySoundRecord(this);
    selector.apply(player -> PalaForgeUtilsMod.proxy.getNetwork().sendTo(packet, player));
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sound\SoundRecordServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */