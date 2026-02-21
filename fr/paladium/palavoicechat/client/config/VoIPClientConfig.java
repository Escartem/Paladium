package fr.paladium.palavoicechat.client.config;

import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.voip.micro.MicrophoneActivationType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public class VoIPClientConfig {
  public void setVoiceActivationThreshold(double voiceActivationThreshold) {
    this.voiceActivationThreshold = voiceActivationThreshold;
  }
  
  public void setVoiceChatVolume(double voiceChatVolume) {
    this.voiceChatVolume = voiceChatVolume;
  }
  
  public void setMicrophoneAmplification(double microphoneAmplification) {
    this.microphoneAmplification = microphoneAmplification;
  }
  
  public void setOutputBufferSize(int outputBufferSize) {
    this.outputBufferSize = outputBufferSize;
  }
  
  public void setSpeaker(String speaker) {
    this.speaker = speaker;
  }
  
  public void setPlayerVolume(Map<String, PlayerVolumeSettings> playerVolume) {
    this.playerVolume = playerVolume;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof VoIPClientConfig))
      return false; 
    VoIPClientConfig other = (VoIPClientConfig)o;
    if (!other.canEqual(this))
      return false; 
    if (isVoiceChatEnabled() != other.isVoiceChatEnabled())
      return false; 
    if (Double.compare(getVoiceActivationThreshold(), other.getVoiceActivationThreshold()) != 0)
      return false; 
    if (Double.compare(getVoiceChatVolume(), other.getVoiceChatVolume()) != 0)
      return false; 
    if (Double.compare(getMicrophoneAmplification(), other.getMicrophoneAmplification()) != 0)
      return false; 
    if (getOutputBufferSize() != other.getOutputBufferSize())
      return false; 
    if (isAnimatedPlayerHead() != other.isAnimatedPlayerHead())
      return false; 
    Object this$micActivationType = getMicActivationType(), other$micActivationType = other.getMicActivationType();
    if ((this$micActivationType == null) ? (other$micActivationType != null) : !this$micActivationType.equals(other$micActivationType))
      return false; 
    Object this$microphone = getMicrophone(), other$microphone = other.getMicrophone();
    if ((this$microphone == null) ? (other$microphone != null) : !this$microphone.equals(other$microphone))
      return false; 
    Object this$speaker = getSpeaker(), other$speaker = other.getSpeaker();
    if ((this$speaker == null) ? (other$speaker != null) : !this$speaker.equals(other$speaker))
      return false; 
    Object<String, PlayerVolumeSettings> this$playerVolume = (Object<String, PlayerVolumeSettings>)getPlayerVolume(), other$playerVolume = (Object<String, PlayerVolumeSettings>)other.getPlayerVolume();
    return !((this$playerVolume == null) ? (other$playerVolume != null) : !this$playerVolume.equals(other$playerVolume));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof VoIPClientConfig;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + (isVoiceChatEnabled() ? 79 : 97);
    long $voiceActivationThreshold = Double.doubleToLongBits(getVoiceActivationThreshold());
    result = result * 59 + (int)($voiceActivationThreshold >>> 32L ^ $voiceActivationThreshold);
    long $voiceChatVolume = Double.doubleToLongBits(getVoiceChatVolume());
    result = result * 59 + (int)($voiceChatVolume >>> 32L ^ $voiceChatVolume);
    long $microphoneAmplification = Double.doubleToLongBits(getMicrophoneAmplification());
    result = result * 59 + (int)($microphoneAmplification >>> 32L ^ $microphoneAmplification);
    result = result * 59 + getOutputBufferSize();
    result = result * 59 + (isAnimatedPlayerHead() ? 79 : 97);
    Object $micActivationType = getMicActivationType();
    result = result * 59 + (($micActivationType == null) ? 43 : $micActivationType.hashCode());
    Object $microphone = getMicrophone();
    result = result * 59 + (($microphone == null) ? 43 : $microphone.hashCode());
    Object $speaker = getSpeaker();
    result = result * 59 + (($speaker == null) ? 43 : $speaker.hashCode());
    Object<String, PlayerVolumeSettings> $playerVolume = (Object<String, PlayerVolumeSettings>)getPlayerVolume();
    return result * 59 + (($playerVolume == null) ? 43 : $playerVolume.hashCode());
  }
  
  public String toString() {
    return "VoIPClientConfig(voiceChatEnabled=" + isVoiceChatEnabled() + ", micActivationType=" + getMicActivationType() + ", voiceActivationThreshold=" + getVoiceActivationThreshold() + ", voiceChatVolume=" + getVoiceChatVolume() + ", microphoneAmplification=" + getMicrophoneAmplification() + ", outputBufferSize=" + getOutputBufferSize() + ", microphone=" + getMicrophone() + ", speaker=" + getSpeaker() + ", playerVolume=" + getPlayerVolume() + ", animatedPlayerHead=" + isAnimatedPlayerHead() + ")";
  }
  
  public boolean isVoiceChatEnabled() {
    return this.voiceChatEnabled;
  }
  
  public MicrophoneActivationType getMicActivationType() {
    return this.micActivationType;
  }
  
  public double getVoiceActivationThreshold() {
    return this.voiceActivationThreshold;
  }
  
  public double getVoiceChatVolume() {
    return this.voiceChatVolume;
  }
  
  public double getMicrophoneAmplification() {
    return this.microphoneAmplification;
  }
  
  public int getOutputBufferSize() {
    return this.outputBufferSize;
  }
  
  public String getMicrophone() {
    return this.microphone;
  }
  
  public String getSpeaker() {
    return this.speaker;
  }
  
  public Map<String, PlayerVolumeSettings> getPlayerVolume() {
    return this.playerVolume;
  }
  
  public boolean isAnimatedPlayerHead() {
    return this.animatedPlayerHead;
  }
  
  private boolean voiceChatEnabled = true;
  
  private MicrophoneActivationType micActivationType = MicrophoneActivationType.PTT;
  
  private double voiceActivationThreshold = -50.0D;
  
  private double voiceChatVolume = 1.0D;
  
  private double microphoneAmplification = 1.0D;
  
  private int outputBufferSize = 6;
  
  private String microphone = "";
  
  private String speaker = "";
  
  private Map<String, PlayerVolumeSettings> playerVolume = new HashMap<>();
  
  private boolean animatedPlayerHead = true;
  
  public PlayerVolumeSettings getVolume(EntityPlayer player) {
    return getVolume(player.func_70005_c_());
  }
  
  public PlayerVolumeSettings getVolume(String player) {
    return this.playerVolume.getOrDefault(player, PlayerVolumeSettings.of(1.0D, false));
  }
  
  public void setPlayerVolume(String playerName, double volume, boolean muted) {
    this.playerVolume.put(playerName, PlayerVolumeSettings.of(volume, muted));
    save();
  }
  
  public void setMicActivationType(MicrophoneActivationType type) {
    this.micActivationType = type;
    save();
  }
  
  public void setVoiceChatEnabled(boolean value) {
    this.voiceChatEnabled = value;
    save();
  }
  
  public void setMicrophone(String micro) {
    this.microphone = micro;
    save();
  }
  
  public void setAnimatedPlayerHead(boolean animated) {
    this.animatedPlayerHead = animated;
    save();
  }
  
  private void save() {
    try {
      JsonConfigLoader.saveConfig(PalaVoiceChatMod.getClientProxy().getConfigPath(), this);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static class PlayerVolumeSettings {
    private final double volume;
    
    private final boolean muted;
    
    private PlayerVolumeSettings(double volume, boolean muted) {
      this.volume = volume;
      this.muted = muted;
    }
    
    public static PlayerVolumeSettings of(double volume, boolean muted) {
      return new PlayerVolumeSettings(volume, muted);
    }
    
    public double getVolume() {
      return this.volume;
    }
    
    public boolean isMuted() {
      return this.muted;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\config\VoIPClientConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */