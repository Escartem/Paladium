package fr.paladium.palavoicechat.utils;

import net.minecraft.entity.player.EntityPlayer;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Utils {
  public static Vec3d getPositionVec(EntityPlayer player) {
    return new Vec3d(player.field_70165_t, player.field_70163_u, player.field_70161_v);
  }
  
  public static float percentageToDB(float percentage) {
    return (float)(10.0D * Math.log(percentage));
  }
  
  public static short bytesToShort(byte b1, byte b2) {
    return (short)((b2 & 0xFF) << 8 | b1 & 0xFF);
  }
  
  public static byte[] adjustVolumeMono(byte[] audio, float volume) {
    for (int i = 0; i < audio.length; i += 2) {
      short audioSample = bytesToShort(audio[i], audio[i + 1]);
      audioSample = (short)(int)(audioSample * volume);
      audio[i] = (byte)audioSample;
      audio[i + 1] = (byte)(audioSample >> 8);
    } 
    return audio;
  }
  
  public static byte[] adjustVolumeStereo(byte[] audio, float volumeLeft, float volumeRight) {
    for (int i = 0; i < audio.length; i += 2) {
      short audioSample = bytesToShort(audio[i], audio[i + 1]);
      audioSample = (short)(int)(audioSample * ((i % 4 == 0) ? volumeLeft : volumeRight));
      audio[i] = (byte)audioSample;
      audio[i + 1] = (byte)(audioSample >> 8);
    } 
    return audio;
  }
  
  public static byte[] convertToStereo(byte[] audio) {
    byte[] stereo = new byte[audio.length * 2];
    for (int i = 0; i < audio.length; i += 2) {
      stereo[i * 2] = audio[i];
      stereo[i * 2 + 1] = audio[i + 1];
      stereo[i * 2 + 2] = audio[i];
      stereo[i * 2 + 3] = audio[i + 1];
    } 
    return stereo;
  }
  
  public static byte[] convertToStereo(byte[] audio, float volumeLeft, float volumeRight) {
    byte[] stereo = new byte[audio.length * 2];
    for (int i = 0; i < audio.length; i += 2) {
      short audioSample = bytesToShort(audio[i], audio[i + 1]);
      short left = (short)(int)(audioSample * volumeLeft);
      short right = (short)(int)(audioSample * volumeRight);
      stereo[i * 2] = (byte)left;
      stereo[i * 2 + 1] = (byte)(left >> 8);
      stereo[i * 2 + 2] = (byte)right;
      stereo[i * 2 + 3] = (byte)(right >> 8);
    } 
    return stereo;
  }
  
  public static Pair<Float, Float> getStereoVolume(Vec3d playerPos, float yaw, Vec3d soundPos) {
    float fill;
    Vec3d d = soundPos.subtract(playerPos).normalize();
    Vec2f diff = new Vec2f((float)d.x, (float)d.z);
    float diffAngle = angle(diff, new Vec2f(-1.0F, 0.0F));
    float angle = normalizeAngle(diffAngle - yaw % 360.0F);
    float rot = angle / 180.0F;
    float perc = rot;
    if (rot < -0.5F) {
      perc = -(0.5F + rot + 0.5F);
    } else if (rot > 0.5F) {
      perc = 0.5F - rot - 0.5F;
    } 
    float left = Math.max(0.3F, (perc < 0.0F) ? Math.abs(perc * 2.0F) : 0.0F);
    float right = Math.max(0.3F, (perc >= 0.0F) ? (perc * 2.0F) : 0.0F);
    if (left > right) {
      fill = 1.0F - left;
    } else {
      fill = 1.0F - right;
    } 
    left += fill;
    right += fill;
    return (Pair<Float, Float>)new ImmutablePair(Float.valueOf(left), Float.valueOf(right));
  }
  
  private static float normalizeAngle(float angle) {
    angle %= 360.0F;
    if (angle <= -180.0F) {
      angle += 360.0F;
    } else if (angle > 180.0F) {
      angle -= 360.0F;
    } 
    return angle;
  }
  
  private static float angle(Vec2f vec1, Vec2f vec2) {
    return (float)Math.toDegrees(Math.atan2((vec1.getX() * vec2.getX() + vec1.getY() * vec2.getY()), (vec1
          .getX() * vec2.getY() - vec1.getY() * vec2.getX())));
  }
  
  public static double calculateAudioLevel(byte[] samples, int offset, int length) {
    double rms = 0.0D;
    for (int i = offset; i < length; i += 2) {
      double sample = bytesToShort(samples[i], samples[i + 1]) / 32767.0D;
      rms += sample * sample;
    } 
    int sampleCount = length / 2;
    rms = (sampleCount == 0) ? 0.0D : Math.sqrt(rms / sampleCount);
    return (rms > 0.0D) ? Math.min(Math.max(20.0D * Math.log10(rms), -127.0D), 0.0D) : -127.0D;
  }
  
  public static double getHighestAudioLevel(byte[] samples) {
    double highest = -127.0D;
    for (int i = 0; i < samples.length; i += 100) {
      double level = calculateAudioLevel(samples, i, Math.min(i + 100, samples.length));
      if (level > highest)
        highest = level; 
    } 
    return highest;
  }
  
  public static int getActivationOffset(byte[] samples, double activationLevel) {
    int highestPos = -1;
    for (int i = 0; i < samples.length; i += 100) {
      double level = calculateAudioLevel(samples, i, Math.min(i + 100, samples.length));
      if (level >= activationLevel)
        highestPos = i; 
    } 
    return highestPos;
  }
  
  public static double dbToPerc(double db) {
    return (db + 127.0D) / 127.0D;
  }
  
  public static double percToDb(double perc) {
    return perc * 127.0D - 127.0D;
  }
  
  public static float calculateAmplitude(byte[] audioData) {
    if (audioData == null || audioData.length == 0)
      return 0.0F; 
    return (float)dbToPerc(calculateAudioLevel(audioData, 0, audioData.length));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicecha\\utils\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */