package fr.paladium.palavoicechat.client.voip.util;

import fr.paladium.palavoicechat.PalaVoiceChatMod;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class DataLines {
  @Nullable
  public static TargetDataLine getMicrophone() {
    String micName = PalaVoiceChatMod.getClientProxy().getClientConfig().getMicrophone();
    if (!micName.isEmpty()) {
      TargetDataLine mic = getMicrophoneByName(micName);
      if (mic != null)
        return mic; 
    } 
    return getDefaultMicrophone();
  }
  
  @Nullable
  public static SourceDataLine getSpeaker() {
    String speakerName = PalaVoiceChatMod.getClientProxy().getClientConfig().getSpeaker();
    if (!speakerName.isEmpty()) {
      SourceDataLine speaker = getSpeakerByName(speakerName);
      if (speaker != null)
        return speaker; 
    } 
    return getDefaultSpeaker();
  }
  
  @Nullable
  public static TargetDataLine getDefaultMicrophone() {
    return getDefaultDevice(TargetDataLine.class);
  }
  
  @Nullable
  public static SourceDataLine getDefaultSpeaker() {
    return getDefaultDevice(SourceDataLine.class);
  }
  
  @Nullable
  public static <T> T getDefaultDevice(Class<T> lineClass) {
    DataLine.Info info = new DataLine.Info(lineClass, null);
    try {
      return lineClass.cast(AudioSystem.getLine(info));
    } catch (Exception e) {
      return null;
    } 
  }
  
  @Nullable
  public static TargetDataLine getMicrophoneByName(String name) {
    return getDeviceByName(TargetDataLine.class, name);
  }
  
  @Nullable
  public static SourceDataLine getSpeakerByName(String name) {
    return getDeviceByName(SourceDataLine.class, name);
  }
  
  @Nullable
  public static <T> T getDeviceByName(Class<T> lineClass, String name) {
    Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    for (Mixer.Info mixerInfo : mixers) {
      Mixer mixer = AudioSystem.getMixer(mixerInfo);
      Line.Info lineInfo = new Line.Info(lineClass);
      if (mixer.isLineSupported(lineInfo) && 
        mixerInfo.getName().equals(name))
        try {
          return lineClass.cast(mixer.getLine(lineInfo));
        } catch (Exception exception) {} 
    } 
    return null;
  }
  
  public static List<String> getMicrophoneNames() {
    return getDeviceNames(TargetDataLine.class);
  }
  
  public static List<String> getSpeakerNames() {
    return getDeviceNames(SourceDataLine.class);
  }
  
  public static List<String> getDeviceNames(Class<?> lineClass) {
    List<String> names = new ArrayList<>();
    Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    for (Mixer.Info mixerInfo : mixers) {
      Mixer mixer = AudioSystem.getMixer(mixerInfo);
      Line.Info lineInfo = new Line.Info(lineClass);
      if (mixer.isLineSupported(lineInfo))
        names.add(mixerInfo.getName()); 
    } 
    return names;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\voi\\util\DataLines.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */