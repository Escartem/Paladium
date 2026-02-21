package fr.paladium.palaforgeutils.lib.sound;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.lang.reflect.Constructor;
import java.util.function.Consumer;
import java.util.function.Predicate;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.util.ResourceLocation;

public final class SoundRecord extends PositionedSound implements ITickableSound {
  private static SoundRegistry soundRegistry;
  
  private SoundCategory category;
  
  private Consumer<SoundRecord> updateCallback;
  
  private Predicate<SoundRecord> condition;
  
  private float defaultVolume;
  
  private boolean donePlaying;
  
  private float soundTransitionStartVolume;
  
  private float soundTransitionEndVolume;
  
  private long soundTransitionStartTime;
  
  private long soundTransitionEndTime;
  
  private Consumer<SoundRecord> soundTransitionCallback;
  
  private boolean streaming;
  
  private SoundRecord(@NonNull ResourceLocation resource, float volume, float pitch, boolean repeat, int repeatDelay, @NonNull ISound.AttenuationType attenuation, float x, float y, float z, @NonNull SoundCategory category) {
    super(resource);
    if (resource == null)
      throw new NullPointerException("resource is marked non-null but is null"); 
    if (attenuation == null)
      throw new NullPointerException("attenuation is marked non-null but is null"); 
    if (category == null)
      throw new NullPointerException("category is marked non-null but is null"); 
    this.field_147662_b = volume;
    this.field_147663_c = pitch;
    this.field_147660_d = x;
    this.field_147661_e = y;
    this.field_147658_f = z;
    this.field_147659_g = repeat;
    this.field_147665_h = repeatDelay;
    this.field_147666_i = attenuation;
    this.category = category;
  }
  
  @NonNull
  public static SoundRecord create(@NonNull SoundRecordServer server) {
    if (server == null)
      throw new NullPointerException("server is marked non-null but is null"); 
    SoundRecord record = new SoundRecord(new ResourceLocation(server.getSound()), server.getVolume(), server.getPitch(), server.isRepeat(), server.getRepeatDelay(), ISound.AttenuationType.valueOf(server.getAttenuation().toUpperCase()), server.getX(), server.getY(), server.getZ(), SoundCategory.valueOf(server.getCategory().toUpperCase()));
    if (server.isHasSoundTransition())
      record.volume(server.getSoundTransitionVolume(), server.getSoundTransitionDuration(), server.isSoundTransitionShouldStop()); 
    return record;
  }
  
  @NonNull
  public static SoundRecord create(@NonNull ResourceLocation resource) {
    if (resource == null)
      throw new NullPointerException("resource is marked non-null but is null"); 
    return new SoundRecord(resource, 0.25F, 1.0F, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F, SoundCategory.MASTER);
  }
  
  @NonNull
  public static SoundRecord create(@NonNull String sound) {
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    if (sound.contains(":"))
      return create(sound.split(":")[0], sound.split(":")[1]); 
    return create(new ResourceLocation("minecraft", sound));
  }
  
  @NonNull
  public static SoundRecord create(@NonNull String modId, @NonNull String sound) {
    if (modId == null)
      throw new NullPointerException("modId is marked non-null but is null"); 
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    return create(new ResourceLocation(modId, sound));
  }
  
  @NonNull
  public static SoundRecord create(@NonNull Object mod, @NonNull String sound) {
    if (mod == null)
      throw new NullPointerException("mod is marked non-null but is null"); 
    if (sound == null)
      throw new NullPointerException("sound is marked non-null but is null"); 
    if (!mod.getClass().isAnnotationPresent((Class)Mod.class))
      throw new IllegalArgumentException("The object is not a mod instance"); 
    Mod annotation = mod.getClass().<Mod>getAnnotation(Mod.class);
    return create(new ResourceLocation(annotation.modid(), sound));
  }
  
  @NonNull
  public SoundRecord volume(float volume) {
    this.field_147662_b = volume / 4.0F;
    return this;
  }
  
  @NonNull
  public SoundRecord volume(float volume, long transitionDuration) {
    volume(volume, transitionDuration, (Consumer<SoundRecord>)null);
    return this;
  }
  
  @NonNull
  public SoundRecord volume(float volume, long transitionDuration, boolean stop) {
    volume(volume, transitionDuration, stop ? (sound -> sound.donePlaying = true) : null);
    return this;
  }
  
  @NonNull
  public SoundRecord volume(float volume, long transitionDuration, Consumer<SoundRecord> callback) {
    if (this.field_147662_b == 0.0F)
      volume(0.01F); 
    this.soundTransitionStartVolume = this.field_147662_b;
    this.soundTransitionEndVolume = volume / 4.0F;
    this.soundTransitionStartTime = System.currentTimeMillis();
    this.soundTransitionEndTime = this.soundTransitionStartTime + transitionDuration;
    this.soundTransitionCallback = callback;
    return this;
  }
  
  @NonNull
  public SoundRecord pitch(float pitch) {
    this.field_147663_c = pitch;
    return this;
  }
  
  @NonNull
  public SoundRecord repeat(boolean repeat) {
    this.field_147659_g = repeat;
    return this;
  }
  
  @NonNull
  public SoundRecord repeat(boolean repeat, int repeatDelay) {
    this.field_147659_g = repeat;
    this.field_147665_h = repeatDelay;
    return this;
  }
  
  @NonNull
  public SoundRecord loop() {
    this.field_147659_g = true;
    return this;
  }
  
  @NonNull
  public SoundRecord repeatDelay(int repeatDelay) {
    this.field_147665_h = repeatDelay;
    return this;
  }
  
  @NonNull
  public SoundRecord attenuation(@NonNull ISound.AttenuationType attenuation) {
    if (attenuation == null)
      throw new NullPointerException("attenuation is marked non-null but is null"); 
    this.field_147666_i = attenuation;
    return this;
  }
  
  @NonNull
  public SoundRecord position(float x, float y, float z) {
    this.field_147660_d = x;
    this.field_147661_e = y;
    this.field_147658_f = z;
    return this;
  }
  
  @NonNull
  public SoundRecord category(@NonNull SoundCategory category) {
    if (category == null)
      throw new NullPointerException("category is marked non-null but is null"); 
    this.category = category;
    return this;
  }
  
  @NonNull
  public SoundRecord onUpdate(Consumer<SoundRecord> callback) {
    this.updateCallback = callback;
    return this;
  }
  
  @NonNull
  public SoundRecord condition(Predicate<SoundRecord> condition) {
    this.condition = condition;
    return this;
  }
  
  @NonNull
  public SoundRecord streaming(boolean streaming) {
    this.streaming = streaming;
    return this;
  }
  
  @NonNull
  public SoundRecord build() {
    return build(this.category);
  }
  
  @NonNull
  public SoundRecord build(@NonNull SoundCategory category) {
    if (category == null)
      throw new NullPointerException("category is marked non-null but is null"); 
    this.category = category;
    if (!func_147650_b().func_110623_a().endsWith(".ogg"))
      return this; 
    if (soundRegistry == null)
      soundRegistry = (SoundRegistry)ObfuscationReflectionHelper.getPrivateValue(SoundHandler.class, Minecraft.func_71410_x().func_147118_V(), new String[] { "sndRegistry", "field_147697_e" }); 
    if (soundRegistry == null)
      throw new IllegalStateException("Unable to access the SoundRegistry"); 
    if (soundRegistry.func_148741_d(func_147650_b()))
      return this; 
    SoundEventAccessorComposite composite = new SoundEventAccessorComposite(func_147650_b(), 1.0D, 1.0D, this.category);
    try {
      Minecraft.func_71410_x().func_110442_L().func_110536_a(func_147650_b());
      Constructor<SoundEventAccessor> constructor = SoundEventAccessor.class.getDeclaredConstructor(new Class[] { SoundPoolEntry.class, int.class });
      constructor.setAccessible(true);
      composite.func_148727_a((ISoundEventAccessor)constructor.newInstance(new Object[] { new SoundPoolEntry(func_147650_b(), 1.0D, 1.0D, this.streaming), Integer.valueOf(1) }));
      soundRegistry.func_148762_a(composite);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return this;
  }
  
  @NonNull
  public SoundRecord copy() {
    SoundRecord record = new SoundRecord(func_147650_b(), this.field_147662_b, this.field_147663_c, this.field_147659_g, this.field_147665_h, this.field_147666_i, this.field_147660_d, this.field_147661_e, this.field_147658_f, this.category);
    record.donePlaying = this.donePlaying;
    record.soundTransitionStartVolume = this.soundTransitionStartVolume;
    record.soundTransitionEndVolume = this.soundTransitionEndVolume;
    record.soundTransitionStartTime = this.soundTransitionStartTime;
    record.soundTransitionEndTime = this.soundTransitionEndTime;
    record.soundTransitionCallback = this.soundTransitionCallback;
    record.streaming = this.streaming;
    return record;
  }
  
  @NonNull
  public SoundRecord play() {
    Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)build());
    return this;
  }
  
  @NonNull
  public SoundRecord stop() {
    if (!isPlaying())
      return this; 
    Minecraft.func_71410_x().func_147118_V().func_147683_b((ISound)this);
    return this;
  }
  
  public boolean isPlaying() {
    return Minecraft.func_71410_x().func_147118_V().func_147692_c((ISound)this);
  }
  
  public void func_73660_a() {
    if (this.updateCallback != null)
      this.updateCallback.accept(this); 
    if (this.condition != null) {
      boolean shouldContinue = this.condition.test(this);
      if (shouldContinue && this.field_147662_b == 0.0F) {
        this.field_147662_b = this.defaultVolume;
        return;
      } 
      if (!shouldContinue && this.field_147662_b != 0.0F) {
        this.defaultVolume = this.field_147662_b;
        this.field_147662_b = 0.0F;
        return;
      } 
    } 
    if (this.soundTransitionStartTime <= 0L || this.soundTransitionEndTime <= 0L)
      return; 
    long currentTime = System.currentTimeMillis();
    if (currentTime >= this.soundTransitionEndTime) {
      this.field_147662_b = this.soundTransitionEndVolume;
      this.soundTransitionStartTime = -1L;
      this.soundTransitionEndTime = -1L;
      if (this.soundTransitionCallback != null)
        this.soundTransitionCallback.accept(this); 
      return;
    } 
    float transitionDuration = (float)(this.soundTransitionEndTime - this.soundTransitionStartTime);
    float transitionProgress = (float)(currentTime - this.soundTransitionStartTime) / transitionDuration;
    this.field_147662_b = this.soundTransitionStartVolume + (this.soundTransitionEndVolume - this.soundTransitionStartVolume) * transitionProgress;
    if (this.field_147662_b < 0.0F)
      this.field_147662_b = 0.0F; 
  }
  
  public boolean func_147667_k() {
    return this.donePlaying;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sound\SoundRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */