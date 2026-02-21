package fr.paladium.palamod.client.utils;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.Constants;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Utils {
  private static Side side;
  
  public static void setSide(Side side) {
    Utils.side = side;
  }
  
  public static boolean isClient() {
    return side.isClient();
  }
  
  public static boolean isServer() {
    return side.isServer();
  }
  
  public static String getPlayerUsername() {
    if (side.isServer())
      return null; 
    return Minecraft.func_71410_x().func_110432_I().func_111285_a();
  }
  
  public static FontRenderer getFontRenderer() {
    return (Minecraft.func_71410_x()).field_71466_p;
  }
  
  public static void sleep(int value) {
    try {
      Thread.sleep(value);
    } catch (InterruptedException interruptedException) {}
  }
  
  public static URL constantURL(String input) {
    try {
      return new URL(input);
    } catch (MalformedURLException e) {
      throw new Error(e);
    } 
  }
  
  public static String parseUrl(String input) {
    return input.replace(" ", "%20");
  }
  
  public static void openBrowser(String URL) {
    if (Desktop.isDesktopSupported() && (
      isWindows() || isMac()))
      try {
        Desktop.getDesktop().browse(new URI(URL));
      } catch (Exception exception) {
        Constants.logger.error("Failed to open link " + URL + " in browser!", exception);
      }  
  }
  
  public static void openBrowser(URL uRL) {
    if (Desktop.isDesktopSupported() && (
      isWindows() || isMac()))
      try {
        Desktop.getDesktop().browse(uRL.toURI());
      } catch (Exception exception) {
        Constants.logger.error("Failed to open link " + uRL + " in browser!", exception);
      }  
  }
  
  public static void openExplorer(File file) {
    if (Desktop.isDesktopSupported())
      try {
        Desktop.getDesktop().open(file);
      } catch (Exception exception) {
        Constants.logger.error(exception);
      }  
  }
  
  public static boolean isWindows() {
    return (OperatingSystem.getCurrentPlatform() == OperatingSystem.WINDOWS);
  }
  
  public static boolean isMac() {
    return (OperatingSystem.getCurrentPlatform() == OperatingSystem.OSX);
  }
  
  public static boolean isLinux() {
    return (OperatingSystem.getCurrentPlatform() == OperatingSystem.LINUX);
  }
  
  public static boolean isRelease() {
    return (Constants.MOD_ENV == Constants.Environment.RELEASE);
  }
  
  public static boolean isDev() {
    return (Constants.MOD_ENV == Constants.Environment.DEV);
  }
  
  public static void checkFolder(File file) {
    if (!file.exists())
      file.mkdirs(); 
    if (!file.isDirectory() && 
      file.delete())
      file.mkdir(); 
  }
  
  public static ArrayList<File> listFiles(File folder) {
    File[] files = folder.listFiles();
    ArrayList<File> list = new ArrayList<>();
    if (files == null)
      return list; 
    for (File f : files) {
      if (f.isDirectory()) {
        list.addAll(listFiles(f));
      } else {
        list.add(f);
      } 
    } 
    return list;
  }
  
  public static String getMD5(File file) {
    if (file.isDirectory()) {
      Constants.logger.error("Cannot get MD5 of " + file.getAbsolutePath() + " as it doesn't exist");
      return "0";
    } 
    if (!file.exists()) {
      Constants.logger.error("Cannot get MD5 of " + file.getAbsolutePath() + " as it doesn't exist");
      return "0";
    } 
    StringBuffer sb = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      FileInputStream fis = new FileInputStream(file);
      byte[] dataBytes = new byte[1024];
      int nread = 0;
      while ((nread = fis.read(dataBytes)) != -1)
        md.update(dataBytes, 0, nread); 
      byte[] mdbytes = md.digest();
      sb = new StringBuffer();
      for (int i = 0; i < mdbytes.length; i++)
        sb.append(Integer.toString((mdbytes[i] & 0xFF) + 256, 16).substring(1)); 
      if (fis != null)
        fis.close(); 
    } catch (NoSuchAlgorithmException exception) {
      Constants.logger.catching(exception);
    } catch (FileNotFoundException exception) {
      Constants.logger.catching(exception);
    } catch (IOException exception) {
      Constants.logger.catching(exception);
    } 
    return sb.toString();
  }
  
  public static byte[] encodedBytes(String value) {
    return Base64.getEncoder().encode(value.getBytes());
  }
  
  public static String decodedBytes(byte[] value) {
    return new String(Base64.getDecoder().decode(value));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\clien\\utils\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */