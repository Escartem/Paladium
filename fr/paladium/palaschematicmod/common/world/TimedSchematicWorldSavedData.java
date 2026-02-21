package fr.paladium.palaschematicmod.common.world;

import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class TimedSchematicWorldSavedData extends WorldSavedData {
  private static final String DATA_NAME = "palaschem_timed";
  
  private List<TimedSchematic> timedSchematics = new ArrayList<>();
  
  public List<TimedSchematic> getTimedSchematics() {
    return this.timedSchematics;
  }
  
  public TimedSchematicWorldSavedData() {
    super("palaschem_timed");
  }
  
  public TimedSchematicWorldSavedData(String name) {
    super(name);
  }
  
  public void func_76184_a(NBTTagCompound nbt) {
    NBTTagList schematicsList = nbt.func_150295_c("timedSchematics", 10);
    this.timedSchematics = new ArrayList<>();
    for (int i = 0; i < schematicsList.func_74745_c(); i++) {
      TimedSchematic schematic = new TimedSchematic();
      schematic.readFromNBT(schematicsList.func_150305_b(i));
      this.timedSchematics.add(schematic);
    } 
  }
  
  public void func_76187_b(NBTTagCompound nbt) {
    NBTTagList schematicsList = new NBTTagList();
    for (TimedSchematic schematic : this.timedSchematics)
      schematicsList.func_74742_a((NBTBase)schematic.writeToNBT()); 
    nbt.func_74782_a("timedSchematics", (NBTBase)schematicsList);
  }
  
  public void resetWsd() {
    this.timedSchematics.clear();
  }
  
  public static TimedSchematicWorldSavedData get(World world) {
    if (world.field_72988_C.func_75742_a(TimedSchematicWorldSavedData.class, "palaschem_timed") == null)
      world.field_72988_C.func_75745_a("palaschem_timed", new TimedSchematicWorldSavedData()); 
    WorldSavedData data = world.field_72988_C.func_75742_a(TimedSchematicWorldSavedData.class, "palaschem_timed");
    return (TimedSchematicWorldSavedData)data;
  }
  
  public void save(World world) {
    func_76185_a();
    world.field_72988_C.func_75744_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\world\TimedSchematicWorldSavedData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */