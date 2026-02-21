package fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.nemesis.module.base.network.NemesisPacket;
import fr.paladium.palamod.modules.nemesis.module.impl.dump.NemesisDumpModule;
import io.netty.buffer.ByteBuf;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class SCPacketNemesisDumpRequest extends NemesisPacket {
  static {
    BBPacketNemesisDumpStreamData.registerStreamObject("NemesisModuleStreamObject", (Class)NemesisModuleStreamObject.class);
    BBPacketNemesisDumpStreamData.registerStreamObject("NemesisClassStreamObject", (Class)NemesisClassStreamObject.class);
    BBPacketNemesisDumpStreamData.registerStreamObject("NemesisCommandLineStreamObject", (Class)NemesisCommandLineStreamObject.class);
    BBPacketNemesisDumpStreamData.registerStreamObject("NemesisProgramStreamObject", (Class)NemesisProgramStreamObject.class);
    BBPacketNemesisDumpStreamData.registerStreamObject("NemesisModsStreamObject", (Class)NemesisModsStreamObject.class);
    BBPacketNemesisDumpStreamData.registerStreamObject("NemesisResourcePacksStreamObject", (Class)NemesisResourcePacksStreamObject.class);
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    List<BBPacketNemesisDumpStreamData.NemesisStreamObject> objects = new ArrayList<>();
    objects.add(new NemesisProgramStreamObject(getOpenPrograms()));
    for (List<AccessController.LoadedDllInfo> subList : getLoadedDllSublists())
      objects.add(new NemesisModuleStreamObject(subList)); 
    for (List<AccessController.LoadedClassInfo> subList : getLoadedClassSublists())
      objects.add(new NemesisClassStreamObject(subList)); 
    objects.add(new NemesisModsStreamObject(getModsList()));
    objects.add(new NemesisResourcePacksStreamObject(getResourcePacks()));
    objects.add(new NemesisCommandLineStreamObject(getCommandLine()));
    BBPacketNemesisDumpStreamData.setStream(new BBPacketNemesisDumpStreamData.NemesisStream(objects));
    NemesisDumpModule.getInstance().getNetwork().send(new BBPacketNemesisDumpStreamData((byte)0));
  }
  
  private static List<NemesisModsStreamObject.ModInfo> getModsList() {
    List<NemesisModsStreamObject.ModInfo> result = new ArrayList<>();
    try {
      for (ModContainer mc : Loader.instance().getModList()) {
        String name = mc.getName();
        String id = mc.getModId();
        String version = mc.getVersion();
        String source = "";
        try {
          File src = mc.getSource();
          if (src != null)
            source = src.getAbsolutePath(); 
        } catch (Throwable throwable) {}
        result.add(new NemesisModsStreamObject.ModInfo(name, id, version, source));
      } 
    } catch (Throwable throwable) {}
    return result;
  }
  
  private static List<String> getResourcePacks() {
    List<String> result = new ArrayList<>();
    try {
      Minecraft mc = Minecraft.func_71410_x();
      File rpDir = new File(mc.field_71412_D, "resourcepacks");
      if (rpDir.exists() && rpDir.isDirectory()) {
        File[] files = rpDir.listFiles();
        if (files != null)
          for (File f : files)
            result.add(f.getName());  
      } 
    } catch (Throwable throwable) {}
    return result;
  }
  
  private static List<List<AccessController.LoadedDllInfo>> getLoadedDllSublists() {
    List<AccessController.LoadedDllInfo> modules = AccessController.getLoadedDlls();
    modules.sort(Comparator.comparing(m1 -> m1.name));
    List<List<AccessController.LoadedDllInfo>> result = new ArrayList<>();
    for (int i = 0; i < modules.size(); i += 100)
      result.add(modules.subList(i, Math.min(i + 100, modules.size()))); 
    return result;
  }
  
  private static List<List<AccessController.LoadedClassInfo>> getLoadedClassSublists() {
    List<AccessController.LoadedClassInfo> classes = AccessController.getLoadedClassesNative();
    classes.sort(Comparator.comparing(c1 -> c1.name));
    List<List<AccessController.LoadedClassInfo>> result = new ArrayList<>();
    for (int i = 0; i < classes.size(); i += 100)
      result.add(classes.subList(i, Math.min(i + 100, classes.size()))); 
    return result;
  }
  
  private static String getCommandLine() {
    String cmd = System.getProperty("sun.java.command");
    if (cmd == null)
      return ""; 
    return cmd.replaceAll("--accessToken\\s+\\S+", "--accessToken ***");
  }
  
  private static List<NemesisProgramStreamObject.ProgramInfo> getOpenPrograms() {
    List<NemesisProgramStreamObject.ProgramInfo> result = new ArrayList<>();
    String os = System.getProperty("os.name").toLowerCase();
    try {
      if (os.contains("win")) {
        Process p = Runtime.getRuntime().exec(new String[] { "cmd", "/c", "tasklist /FO CSV /NH" });
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
          String line;
          while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty())
              continue; 
            String[] parts = line.split("\",\"");
            for (int i = 0; i < parts.length; i++)
              parts[i] = parts[i].replaceAll("^\"|\"$", ""); 
            String name = (parts.length > 0) ? parts[0] : "";
            long pid = 0L;
            if (parts.length > 1)
              try {
                pid = Long.parseLong(parts[1].replaceAll("\\D", ""));
              } catch (NumberFormatException ex) {
                pid = 0L;
              }  
            result.add(new NemesisProgramStreamObject.ProgramInfo(name, pid));
          } 
        } 
      } else {
        Process p = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", "ps -e -o pid=,comm=" });
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
          String line;
          while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty())
              continue; 
            String[] parts = line.trim().split("\\\\s+", 2);
            long pid = 0L;
            try {
              pid = Long.parseLong(parts[0].trim());
            } catch (Exception ex) {
              pid = 0L;
            } 
            String name = (parts.length > 1) ? parts[1] : ((parts.length > 0) ? parts[0] : "");
            result.add(new NemesisProgramStreamObject.ProgramInfo(name, pid));
          } 
        } 
      } 
    } catch (IOException iOException) {}
    return result;
  }
  
  public static class NemesisModuleStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
    private List<AccessController.LoadedDllInfo> moduleList;
    
    public NemesisModuleStreamObject() {}
    
    public NemesisModuleStreamObject(List<AccessController.LoadedDllInfo> moduleList) {
      this.moduleList = moduleList;
    }
    
    public List<AccessController.LoadedDllInfo> getModuleList() {
      return this.moduleList;
    }
    
    public void write(ByteBuf buf) {
      buf.writeInt(this.moduleList.size());
      for (AccessController.LoadedDllInfo module : this.moduleList) {
        ByteBufUtils.writeUTF8String(buf, module.name);
        ByteBufUtils.writeUTF8String(buf, module.path);
        buf.writeLong(module.size);
        buf.writeLong(module.loadTime);
        ByteBufUtils.writeUTF8String(buf, module.hash);
      } 
    }
  }
  
  public static class NemesisClassStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
    private List<AccessController.LoadedClassInfo> classList;
    
    public NemesisClassStreamObject() {}
    
    public NemesisClassStreamObject(List<AccessController.LoadedClassInfo> classList) {
      this.classList = classList;
    }
    
    public List<AccessController.LoadedClassInfo> getClassList() {
      return this.classList;
    }
    
    public void write(ByteBuf buf) {
      buf.writeInt(this.classList.size());
      for (AccessController.LoadedClassInfo clazz : this.classList)
        ByteBufUtils.writeUTF8String(buf, clazz.name); 
    }
  }
  
  public static class NemesisCommandLineStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
    private String commandLine;
    
    public NemesisCommandLineStreamObject() {}
    
    public NemesisCommandLineStreamObject(String commandLine) {
      this.commandLine = commandLine;
    }
    
    public String getCommandLine() {
      return this.commandLine;
    }
    
    public void write(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.commandLine);
    }
  }
  
  public static class NemesisProgramStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
    private List<ProgramInfo> programList;
    
    public NemesisProgramStreamObject() {}
    
    public NemesisProgramStreamObject(List<ProgramInfo> programList) {
      this.programList = programList;
    }
    
    public static class ProgramInfo {
      public String name;
      
      public long pid;
      
      public ProgramInfo() {}
      
      public ProgramInfo(String name, long pid) {
        this.name = name;
        this.pid = pid;
      }
    }
    
    public List<ProgramInfo> getProgramList() {
      return this.programList;
    }
    
    public void write(ByteBuf buf) {
      buf.writeInt(this.programList.size());
      for (ProgramInfo p : this.programList) {
        ByteBufUtils.writeUTF8String(buf, p.name);
        buf.writeLong(p.pid);
      } 
    }
  }
  
  public static class ProgramInfo {
    public String name;
    
    public long pid;
    
    public ProgramInfo() {}
    
    public ProgramInfo(String name, long pid) {
      this.name = name;
      this.pid = pid;
    }
  }
  
  public static class NemesisModsStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
    private List<ModInfo> modList;
    
    public NemesisModsStreamObject() {}
    
    public NemesisModsStreamObject(List<ModInfo> modList) {
      this.modList = modList;
    }
    
    public static class ModInfo {
      public String name;
      
      public String id;
      
      public String version;
      
      public String source;
      
      public ModInfo() {}
      
      public ModInfo(String name, String id, String version, String source) {
        this.name = name;
        this.id = id;
        this.version = version;
        this.source = source;
      }
    }
    
    public List<ModInfo> getModList() {
      return this.modList;
    }
    
    public void write(ByteBuf buf) {
      buf.writeInt(this.modList.size());
      for (ModInfo m : this.modList) {
        ByteBufUtils.writeUTF8String(buf, m.name);
        ByteBufUtils.writeUTF8String(buf, m.id);
        ByteBufUtils.writeUTF8String(buf, m.version);
        ByteBufUtils.writeUTF8String(buf, m.source);
      } 
    }
  }
  
  public static class ModInfo {
    public String name;
    
    public String id;
    
    public String version;
    
    public String source;
    
    public ModInfo() {}
    
    public ModInfo(String name, String id, String version, String source) {
      this.name = name;
      this.id = id;
      this.version = version;
      this.source = source;
    }
  }
  
  public static class NemesisResourcePacksStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
    private List<String> packList;
    
    public NemesisResourcePacksStreamObject() {}
    
    public NemesisResourcePacksStreamObject(List<String> packList) {
      this.packList = packList;
    }
    
    public List<String> getPackList() {
      return this.packList;
    }
    
    public void write(ByteBuf buf) {
      buf.writeInt(this.packList.size());
      for (String p : this.packList)
        ByteBufUtils.writeUTF8String(buf, p); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\common\packet\SCPacketNemesisDumpRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */