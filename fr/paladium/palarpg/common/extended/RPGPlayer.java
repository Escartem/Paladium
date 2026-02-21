package fr.paladium.palarpg.common.extended;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palarpg.common.extended.playerdata.APlayerData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RPGPlayer extends ExtendedEntityProperties {
  public static final String PROP_NAME = "rpgplayer_eep";
  
  private static final List<Class<? extends APlayerData>> REGISTERED_PLAYER_DATA = new ArrayList<>();
  
  private final Map<String, APlayerData> playerData = new HashMap<>();
  
  private boolean synchronizing;
  
  public Map<String, APlayerData> getPlayerData() {
    return this.playerData;
  }
  
  public boolean isSynchronizing() {
    return this.synchronizing;
  }
  
  public void init(Entity entity, World world) {
    super.init(entity, world);
    initPlayerData();
  }
  
  public static RPGPlayer get(Entity entity) {
    return (RPGPlayer)entity.getExtendedProperties("rpgplayer_eep");
  }
  
  @SideOnly(Side.CLIENT)
  public static <T extends APlayerData> T getData(String dataName) {
    return getData((Entity)(Minecraft.func_71410_x()).field_71439_g, dataName);
  }
  
  public static <T extends APlayerData> T getData(Entity entity, String dataName) {
    RPGPlayer eep = get(entity);
    if (eep == null)
      return null; 
    T playerData = eep.getPlayerData(dataName);
    if (playerData == null)
      return null; 
    return playerData;
  }
  
  public static void sync(Entity entity) {
    if (entity instanceof EntityPlayerMP)
      syncPlayer((EntityPlayerMP)entity); 
  }
  
  public static void syncPlayer(EntityPlayerMP player) {
    if (get((Entity)player) != null)
      get((Entity)player).sync(); 
  }
  
  public static List<Class<? extends APlayerData>> getRegisteredPlayerData() {
    return REGISTERED_PLAYER_DATA;
  }
  
  @SafeVarargs
  public static void registerPlayerData(Class<? extends APlayerData>... allClazz) {
    REGISTERED_PLAYER_DATA.addAll(Arrays.asList(allClazz));
  }
  
  private void initPlayerData() {
    REGISTERED_PLAYER_DATA.forEach(clazz -> {
          APlayerData dataObject = null;
          try {
            if (!APlayerData.class.isAssignableFrom(clazz))
              throw new InstantiationException("[PalaRPG] dataObject class doesn't extends APlayerData"); 
            dataObject = (APlayerData)clazz.newInstance();
          } catch (InstantiationException|IllegalAccessException e) {
            System.out.println("[PalaRPG] Erreur lors du chargement de la PlayerData " + clazz.getName());
            e.printStackTrace();
            return;
          } 
          if (dataObject == null) {
            System.out.println("[PalaRPG] Erreur lors du chargement de la PlayerData " + clazz.getName());
            System.out.println("[PalaRPG] dataObject is null");
            return;
          } 
          dataObject.setEntity((EntityLivingBase)getEntity());
          this.playerData.put(dataObject.getName(), dataObject);
        });
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound allData = new NBTTagCompound();
    this.playerData.forEach((dataName, data) -> {
          NBTTagCompound nbt = new NBTTagCompound();
          data.write(nbt, !this.synchronizing);
          nbt.func_74778_a(data.getName() + "-class", data.getClazzName());
          allData.func_74782_a(data.getName(), (NBTBase)nbt);
        });
    compound.func_74782_a("rpgplayer_eep", (NBTBase)allData);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound allData = compound.func_74775_l("rpgplayer_eep");
    Set<String> keySet = allData.func_150296_c();
    keySet.forEach(key -> {
          APlayerData dataObject;
          NBTTagCompound baseNBT = allData.func_74775_l(key);
          String clazzName = baseNBT.func_74779_i(key + "-class");
          try {
            Class<?> clazz = Class.forName(clazzName, false, Thread.currentThread().getContextClassLoader());
            if (!APlayerData.class.isAssignableFrom(clazz))
              throw new InstantiationException("[PalaRPG] dataObject class doesn't extends APlayerData"); 
            dataObject = (APlayerData)clazz.newInstance();
          } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            System.out.println("[PalaRPG] Erreur lors du chargement de la PlayerData " + clazzName);
            e.printStackTrace();
            return;
          } 
          if (dataObject == null) {
            System.out.println("[PalaRPG] Erreur lors du chargement de la PlayerData " + clazzName);
            System.out.println("[PalaRPG] dataObject is null");
            return;
          } 
          dataObject.setEntity((EntityLivingBase)getEntity());
          dataObject.read(baseNBT, !this.synchronizing);
          this.playerData.put(key, dataObject);
        });
  }
  
  public void sync() {
    this.synchronizing = true;
    super.sync();
    this.synchronizing = false;
  }
  
  public void sync(EntityPlayerMP target) {
    this.synchronizing = true;
    super.sync(target);
    this.synchronizing = false;
  }
  
  public <T extends APlayerData> T getPlayerData(String name) {
    return (T)this.playerData.get(name);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\common\extended\RPGPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */