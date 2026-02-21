package fr.paladium.palahologram.common.worlddata;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palahologram.common.hologram.Hologram;
import fr.paladium.palahologram.common.hologram.element.HologramElement;
import fr.paladium.palahologram.common.hologram.element.impl.ResourceHologramElement;
import fr.paladium.palahologram.common.hologram.element.impl.TextHologramElement;
import fr.paladium.palahologram.common.network.SCPacketSyncHolograms;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class HologramWorldData extends WorldSavedData {
  private static final String DATA_NAME = "palahologram_data";
  
  private final Map<String, Hologram> hologramData = new HashMap<>();
  
  public Map<String, Hologram> getHologramData() {
    return this.hologramData;
  }
  
  public HologramWorldData() {
    super("palahologram_data");
  }
  
  public HologramWorldData(String name) {
    super(name);
  }
  
  public void func_76184_a(NBTTagCompound nbt) {
    Set<String> set = nbt.func_150296_c();
    this.hologramData.clear();
    set.forEach(key -> {
          NBTTagCompound tag = nbt.func_74775_l(key);
          Hologram hologram = (new Hologram()).read(tag);
          this.hologramData.put(key, hologram);
        });
  }
  
  public void func_76187_b(NBTTagCompound nbt) {
    this.hologramData.forEach((key, hologram) -> {
          NBTTagCompound tag = new NBTTagCompound();
          hologram.write(tag);
          nbt.func_74782_a(key, (NBTBase)tag);
        });
  }
  
  public boolean appendHologram(String name, Hologram hologram) {
    if (this.hologramData.containsKey(name))
      return false; 
    this.hologramData.put(name, hologram);
    syncAll();
    save();
    return true;
  }
  
  @SideOnly(Side.SERVER)
  public boolean createHologram(String name, DoubleLocation location, String text) {
    if (this.hologramData.containsKey(name))
      return false; 
    List<String> list = new ArrayList<>();
    list.add(text);
    Hologram hologram = Hologram.builder().location(location).build();
    hologram.appendElement((HologramElement)new TextHologramElement(text));
    this.hologramData.put(name, hologram);
    syncAll();
    save();
    return true;
  }
  
  @SideOnly(Side.SERVER)
  public void removeHologram(String name) {
    if (!this.hologramData.containsKey(name))
      return; 
    this.hologramData.remove(name);
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void editHologramPosition(String name, DoubleLocation location) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    hologram.setLocation(location);
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configSpacing(String name, double spacing) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    hologram.setSpacing(spacing);
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configFollowPlayerX(String name, boolean x) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    hologram.setFollowPlayerX(x);
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configFollowPlayerY(String name, boolean y) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    hologram.setFollowPlayerY(y);
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configAlignment(String name, int index, Align alignment) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0) {
      if (index < hologram.getElements().size())
        ((HologramElement)hologram.getElements().get(index)).setAlignment(alignment); 
    } else {
      hologram.getElements().forEach(elem -> elem.setAlignment(alignment));
    } 
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configAddText(String name, String text) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    hologram.appendElement((HologramElement)new TextHologramElement(text));
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configTextFontSize(String name, int index, float fontSize) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0) {
      if (index < hologram.getElements().size()) {
        HologramElement element = hologram.getElements().get(index);
        if (element instanceof TextHologramElement)
          ((TextHologramElement)element).setFontSize(fontSize); 
      } 
    } else {
      hologram.getElements().forEach(elem -> {
            if (elem instanceof TextHologramElement)
              ((TextHologramElement)elem).setFontSize(fontSize); 
          });
    } 
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configInsertText(String name, int index, String text) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0 && index <= hologram.getElements().size())
      hologram.insertElement(index, (HologramElement)new TextHologramElement(text)); 
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configText(String name, int index, String text) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0 && index < hologram.getElements().size()) {
      HologramElement element = hologram.getElements().get(index);
      if (element instanceof TextHologramElement)
        ((TextHologramElement)element).setText(text); 
    } 
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configTextBackground(String name, int index, boolean background) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0 && index < hologram.getElements().size()) {
      HologramElement element = hologram.getElements().get(index);
      if (element instanceof TextHologramElement)
        ((TextHologramElement)element).setBackground(background); 
    } 
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configDelElement(String name, int index) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0 && index < hologram.getElements().size())
      hologram.getElements().remove(index); 
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configAngle(String name, double angle) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    hologram.setAngle(angle);
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configAddResource(String name, String resourceUrl) {
    if (!this.hologramData.containsKey(name)) {
      System.out.println("makoue");
      return;
    } 
    Hologram hologram = getHologramByName(name);
    hologram.appendElement((HologramElement)new ResourceHologramElement(resourceUrl));
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configResourceUrl(String name, int index, String resourceUrl) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0 && index < hologram.getElements().size()) {
      HologramElement element = hologram.getElements().get(index);
      if (element instanceof ResourceHologramElement)
        ((ResourceHologramElement)element).setResourceUrl(resourceUrl); 
    } 
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configResourceWidth(String name, int index, double width) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0 && index < hologram.getElements().size()) {
      HologramElement element = hologram.getElements().get(index);
      if (element instanceof ResourceHologramElement)
        ((ResourceHologramElement)element).setWidth(width); 
    } 
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configResourceHeight(String name, int index, double height) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0 && index < hologram.getElements().size()) {
      HologramElement element = hologram.getElements().get(index);
      if (element instanceof ResourceHologramElement)
        ((ResourceHologramElement)element).setHeight(height); 
    } 
    syncAll();
    save();
  }
  
  @SideOnly(Side.SERVER)
  public void configResourceSize(String name, int index, double width, double height) {
    if (!this.hologramData.containsKey(name))
      return; 
    Hologram hologram = getHologramByName(name);
    if (index >= 0 && index < hologram.getElements().size()) {
      HologramElement element = hologram.getElements().get(index);
      if (element instanceof ResourceHologramElement) {
        ((ResourceHologramElement)element).setWidth(width);
        ((ResourceHologramElement)element).setHeight(height);
      } 
    } 
    syncAll();
    save();
  }
  
  public void save() {
    World world = (FMLCommonHandler.instance().getSide() == Side.SERVER) ? MinecraftServer.func_71276_C().func_130014_f_() : (Minecraft.func_71410_x()).field_71439_g.func_130014_f_();
    func_76185_a();
    world.field_72988_C.func_75744_a();
  }
  
  @SideOnly(Side.SERVER)
  public void syncAll() {
    NBTTagCompound tag = new NBTTagCompound();
    func_76187_b(tag);
    (new SCPacketSyncHolograms(tag)).sendToAll();
  }
  
  @SideOnly(Side.SERVER)
  public void sync(EntityPlayerMP player) {
    NBTTagCompound tag = new NBTTagCompound();
    func_76187_b(tag);
    (new SCPacketSyncHolograms(tag)).send(player);
  }
  
  @SideOnly(Side.CLIENT)
  public void sync(NBTTagCompound tag) {
    func_76184_a(tag);
  }
  
  public Hologram getHologramByName(String name) {
    return this.hologramData.get(name);
  }
  
  public static HologramWorldData get() {
    Side side = FMLCommonHandler.instance().getSide();
    if (side == Side.CLIENT) {
      World world1 = (Minecraft.func_71410_x()).field_71439_g.func_130014_f_();
      HologramWorldData hologramWorldData = (HologramWorldData)world1.field_72988_C.func_75742_a(HologramWorldData.class, "palahologram_data");
      if (hologramWorldData == null) {
        hologramWorldData = new HologramWorldData();
        world1.field_72988_C.func_75745_a("palahologram_data", hologramWorldData);
        hologramWorldData.save();
      } 
      return hologramWorldData;
    } 
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    HologramWorldData data = (HologramWorldData)world.field_72988_C.func_75742_a(HologramWorldData.class, "palahologram_data");
    if (data == null) {
      data = new HologramWorldData();
      world.field_72988_C.func_75745_a("palahologram_data", data);
      data.save();
    } 
    return data;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\worlddata\HologramWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */