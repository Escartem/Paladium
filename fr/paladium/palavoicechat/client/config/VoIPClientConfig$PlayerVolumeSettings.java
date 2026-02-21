package fr.paladium.palavoicechat.client.config;

public class PlayerVolumeSettings {
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


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\config\VoIPClientConfig$PlayerVolumeSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */