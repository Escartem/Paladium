package fr.paladium.palavoicechat.client.voip.audio;

import javax.sound.sampled.AudioFormat;

public class AudioChannelConfig {
  private static AudioFormat monoFormat;
  
  private static AudioFormat stereoFormat;
  
  private static int dataLength;
  
  static {
    float sampleRate = 16000.0F;
    monoFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 16, 1, 2, sampleRate, false);
    stereoFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 16, 2, 4, sampleRate, false);
    dataLength = (int)(sampleRate / 200.0F * 16.0F);
    System.out.println("Setting sample rate to " + sampleRate + " Hz and data length to " + dataLength + " bytes");
  }
  
  public static AudioFormat getMonoFormat() {
    return monoFormat;
  }
  
  public static AudioFormat getStereoFormat() {
    return stereoFormat;
  }
  
  public static int getDataLength() {
    return dataLength;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\voip\audio\AudioChannelConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */