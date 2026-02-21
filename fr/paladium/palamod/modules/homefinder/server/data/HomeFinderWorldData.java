package fr.paladium.palamod.modules.homefinder.server.data;

import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class HomeFinderWorldData extends WorldSavedData {
  private static final String DATA_NAME = "palamod_HomeFinderData";
  
  private List<Location> finders;
  
  public void setFinders(List<Location> finders) {
    this.finders = finders;
  }
  
  public List<Location> getFinders() {
    return this.finders;
  }
  
  public HomeFinderWorldData() {
    this("palamod_HomeFinderData");
  }
  
  public HomeFinderWorldData(String dataName) {
    super(dataName);
    this.finders = new ArrayList<>();
  }
  
  public void func_76184_a(NBTTagCompound compound) {
    NBTTagCompound nbt = compound.func_74775_l("HomeFinderData");
    if (nbt == null)
      return; 
    NBTTagList finders = nbt.func_150295_c("finders", 10);
    for (int i = 0; i < finders.func_74745_c(); i++) {
      NBTTagCompound finder = finders.func_150305_b(i);
      Location location = new Location(finder.func_74769_h("locationX"), finder.func_74769_h("locationY"), finder.func_74769_h("locationZ"));
      this.finders.add(location);
    } 
    nbt.func_74782_a("finders", (NBTBase)finders);
    compound.func_74782_a("HomeFinderData", (NBTBase)nbt);
  }
  
  public void func_76187_b(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    NBTTagList finders = new NBTTagList();
    for (Location location : this.finders) {
      NBTTagCompound finder = new NBTTagCompound();
      finder.func_74780_a("locationX", location.getX());
      finder.func_74780_a("locationY", location.getY());
      finder.func_74780_a("locationZ", location.getZ());
      finders.func_74742_a((NBTBase)finder);
    } 
    nbt.func_74782_a("finders", (NBTBase)finders);
    compound.func_74782_a("HomeFinderData", (NBTBase)nbt);
  }
  
  public static HomeFinderWorldData get() {
    MinecraftServer server = MinecraftServer.func_71276_C();
    if (server == null || server.func_130014_f_() == null)
      return null; 
    return get(server.func_130014_f_());
  }
  
  public static HomeFinderWorldData get(World world) {
    HomeFinderWorldData data = (HomeFinderWorldData)world.func_72943_a(HomeFinderWorldData.class, "palamod_HomeFinderData");
    if (data == null) {
      data = new HomeFinderWorldData();
      data.func_76185_a();
      world.func_72823_a("palamod_HomeFinderData", data);
    } 
    return data;
  }
  
  public static void markBaseWorldDirty() {
    MinecraftServer server = MinecraftServer.func_71276_C();
    if (server == null || server.func_130014_f_() == null)
      return; 
    get(server.func_130014_f_()).func_76185_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\server\data\HomeFinderWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */