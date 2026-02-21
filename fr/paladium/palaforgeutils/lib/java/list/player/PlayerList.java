package fr.paladium.palaforgeutils.lib.java.list.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class PlayerList {
  private final List<String> localList = new ArrayList<>();
  
  public boolean add(Entity e) {
    return add(e.func_110124_au());
  }
  
  public boolean add(UUID e) {
    return add(UUIDUtils.toString(e));
  }
  
  public boolean add(String e) {
    return this.localList.add(e);
  }
  
  public void clear() {
    this.localList.clear();
  }
  
  public boolean contains(Entity e) {
    return contains(e.func_110124_au());
  }
  
  public boolean contains(UUID e) {
    return contains(UUIDUtils.toString(e));
  }
  
  public boolean contains(String e) {
    return this.localList.contains(e);
  }
  
  @SideOnly(Side.SERVER)
  public Entity get(int index) {
    String key = this.localList.get(index);
    for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
      for (Object entityObj : ((World)worldServer).field_72996_f) {
        if (!(entityObj instanceof Entity))
          continue; 
        Entity entity = (Entity)entityObj;
        if (UUIDUtils.toString(entity.func_110124_au()).equals(key))
          return entity; 
      } 
    } 
    return null;
  }
  
  public int indexOf(Entity e) {
    return indexOf(e.func_110124_au());
  }
  
  public int indexOf(UUID e) {
    return indexOf(UUIDUtils.toString(e));
  }
  
  public int indexOf(String e) {
    return this.localList.indexOf(e);
  }
  
  public boolean isEmpty() {
    return this.localList.isEmpty();
  }
  
  public boolean remove(Entity e) {
    return remove(e.func_110124_au());
  }
  
  public boolean remove(UUID e) {
    return remove(UUIDUtils.toString(e));
  }
  
  public boolean remove(String e) {
    return this.localList.remove(e);
  }
  
  public String remove(int index) {
    return this.localList.remove(index);
  }
  
  public String set(int index, Entity element) {
    return set(index, element.func_110124_au());
  }
  
  public String set(int index, UUID element) {
    return set(index, UUIDUtils.toString(element));
  }
  
  public String set(int index, String element) {
    return this.localList.set(index, element);
  }
  
  public int size() {
    return this.localList.size();
  }
  
  public String[] toArray() {
    return this.localList.<String>toArray(new String[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\java\list\player\PlayerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */