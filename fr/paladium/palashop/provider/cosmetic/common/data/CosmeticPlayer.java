package fr.paladium.palashop.provider.cosmetic.common.data;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.UIShopCosmetic;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CosmeticPlayer extends ExtendedEntityProperties {
  public static final String PROP_NAME = "palashop_COSMETIC";
  
  public static final int OUTFIT_SIZE = 10;
  
  public int getOutfitId() {
    return this.outfitId;
  }
  
  public List<OutfitCosmetic> getOutfitList() {
    return this.outfitList;
  }
  
  private int outfitId = 0;
  
  private final List<OutfitCosmetic> outfitList = new ArrayList<>();
  
  public CosmeticPlayer() {
    for (int i = 0; i < 10; i++)
      this.outfitList.add(new OutfitCosmetic()); 
  }
  
  @NonNull
  public OutfitCosmetic getOutfit() {
    return this.outfitList.get(this.outfitId % this.outfitList.size());
  }
  
  @NonNull
  public OutfitCosmetic getOutfit(int outfitId) {
    return this.outfitList.get(outfitId % this.outfitList.size());
  }
  
  @NonNull
  public CosmeticPlayer setOutfitId(int outfitId) {
    this.outfitId = outfitId;
    if (this.outfitId < 0)
      this.outfitId = this.outfitList.size() - 1; 
    if (this.outfitId >= this.outfitList.size())
      this.outfitId = 0; 
    return this;
  }
  
  @NonNull
  public CosmeticPlayer setOutfit(@NonNull OutfitCosmetic outfit) {
    if (outfit == null)
      throw new NullPointerException("outfit is marked non-null but is null"); 
    this.outfitList.set(this.outfitId, outfit);
    return this;
  }
  
  @NonNull
  public CosmeticPlayer incrementOutfitId() {
    return setOutfitId(this.outfitId + 1);
  }
  
  @NonNull
  public CosmeticPlayer decrementOutfitId() {
    return setOutfitId(this.outfitId - 1);
  }
  
  public void onRegister() {
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { CosmeticPlayerRegisterProviderEvent.pre(this), CosmeticPlayerRegisterProviderEvent.post(this) });
  }
  
  public void loadNBTData(NBTTagCompound root) {
    if (!root.func_74764_b("palashop_COSMETIC"))
      return; 
    NBTTagCompound nbt = root.func_74775_l("palashop_COSMETIC");
    if (nbt.func_74764_b("outfitId"))
      this.outfitId = nbt.func_74762_e("outfitId"); 
    if (nbt.func_74764_b("outfitList")) {
      this.outfitList.clear();
      NBTTagList list = nbt.func_150295_c("outfitList", 10);
      for (int i = 0; i < list.func_74745_c(); i++) {
        NBTTagCompound compound = list.func_150305_b(i);
        OutfitCosmetic outfit = new OutfitCosmetic();
        outfit.loadNBTData(compound);
        this.outfitList.add(outfit);
      } 
    } 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT && getEntity().equals((Minecraft.func_71410_x()).field_71439_g) && 
      UIShopCosmetic.cosmeticPlayerSignal != null) {
      UIShopCosmetic.cosmeticPlayerSignal.set(this);
      UIShopCosmetic.cosmeticPlayerSignal.publish();
    } 
  }
  
  public void saveNBTData(NBTTagCompound root) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74768_a("outfitId", this.outfitId);
    NBTTagList list = new NBTTagList();
    for (OutfitCosmetic outfit : this.outfitList) {
      NBTTagCompound compound = new NBTTagCompound();
      outfit.saveNBTData(compound);
      list.func_74742_a((NBTBase)compound);
    } 
    nbt.func_74782_a("outfitList", (NBTBase)list);
    root.func_74782_a("palashop_COSMETIC", (NBTBase)nbt);
  }
  
  @SideOnly(Side.CLIENT)
  public static CosmeticPlayer me() {
    if ((Minecraft.func_71410_x()).field_71439_g == null)
      return null; 
    return get((Entity)(Minecraft.func_71410_x()).field_71439_g);
  }
  
  public static CosmeticPlayer get(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return (CosmeticPlayer)entity.getExtendedProperties("palashop_COSMETIC");
  }
  
  @NonNull
  public EntityPlayer getPlayer() {
    return (EntityPlayer)getEntity();
  }
  
  public static class OutfitCosmetic {
    public Map<String, CosmeticPlayer.EquippedCosmetic> getEquippedCosmeticMap() {
      return this.equippedCosmeticMap;
    }
    
    private final Map<String, CosmeticPlayer.EquippedCosmetic> equippedCosmeticMap = new HashMap<>();
    
    public boolean isEmpty() {
      return this.equippedCosmeticMap.isEmpty();
    }
    
    @NonNull
    public Optional<CosmeticPlayer.EquippedCosmetic> get(@NonNull String factoryId) {
      if (factoryId == null)
        throw new NullPointerException("factoryId is marked non-null but is null"); 
      if (this.equippedCosmeticMap.containsKey(factoryId))
        return Optional.of(this.equippedCosmeticMap.get(factoryId)); 
      Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(factoryId);
      if (!optionalFactory.isPresent())
        return Optional.empty(); 
      CosmeticPlayer.EquippedCosmetic equippedCosmetic = ((CosmeticFactory)optionalFactory.get()).getEquippedCosmetic();
      if (equippedCosmetic == null)
        return Optional.empty(); 
      this.equippedCosmeticMap.put(factoryId, equippedCosmetic);
      return Optional.of(equippedCosmetic);
    }
    
    @NonNull
    public CosmeticPlayer.EquippedCosmetic getOrDefault(@NonNull String factoryId, @NonNull CosmeticPlayer.EquippedCosmetic defaultValue) {
      if (factoryId == null)
        throw new NullPointerException("factoryId is marked non-null but is null"); 
      if (defaultValue == null)
        throw new NullPointerException("defaultValue is marked non-null but is null"); 
      return this.equippedCosmeticMap.getOrDefault(factoryId, defaultValue);
    }
    
    @NonNull
    public List<CosmeticPlayer.EquippedCosmetic> get(@NonNull CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType type) {
      if (type == null)
        throw new NullPointerException("type is marked non-null but is null"); 
      List<CosmeticPlayer.EquippedCosmetic> list = new ArrayList<>();
      for (CosmeticPlayer.EquippedCosmetic equippedCosmetic : this.equippedCosmeticMap.values()) {
        if (equippedCosmetic.getType() == type)
          list.add(equippedCosmetic); 
      } 
      return list;
    }
    
    @NonNull
    public OutfitCosmetic set(@NonNull String factoryId, @NonNull CosmeticPlayer.EquippedCosmetic equippedCosmetic) {
      if (factoryId == null)
        throw new NullPointerException("factoryId is marked non-null but is null"); 
      if (equippedCosmetic == null)
        throw new NullPointerException("equippedCosmetic is marked non-null but is null"); 
      this.equippedCosmeticMap.put(factoryId, equippedCosmetic);
      return this;
    }
    
    @NonNull
    public OutfitCosmetic clear() {
      this.equippedCosmeticMap.clear();
      return this;
    }
    
    public void loadNBTData(@NonNull NBTTagCompound compound) {
      if (compound == null)
        throw new NullPointerException("compound is marked non-null but is null"); 
      this.equippedCosmeticMap.clear();
      if (!compound.func_74764_b("cosmetics"))
        return; 
      NBTTagList list = compound.func_150295_c("cosmetics", 10);
      for (int i = 0; i < list.func_74745_c(); i++) {
        NBTTagCompound elementCompound = list.func_150305_b(i);
        if (elementCompound.func_74764_b("factory") && elementCompound.func_74764_b("equipped")) {
          String factoryId = elementCompound.func_74779_i("factory");
          CosmeticPlayer.EquippedCosmetic equippedCosmetic = CosmeticPlayer.EquippedCosmetic.decode(elementCompound.func_74775_l("equipped"));
          if (equippedCosmetic == null) {
            System.out.println("Unable to decode equipped cosmetic for factory " + factoryId);
            System.out.println(elementCompound.func_74775_l("equipped"));
          } else {
            this.equippedCosmeticMap.put(factoryId, equippedCosmetic);
          } 
        } 
      } 
    }
    
    public void saveNBTData(@NonNull NBTTagCompound compound) {
      if (compound == null)
        throw new NullPointerException("compound is marked non-null but is null"); 
      NBTTagList list = new NBTTagList();
      for (Map.Entry<String, CosmeticPlayer.EquippedCosmetic> entry : this.equippedCosmeticMap.entrySet()) {
        NBTTagCompound elementCompound = new NBTTagCompound();
        NBTTagCompound equippedCompound = new NBTTagCompound();
        ((CosmeticPlayer.EquippedCosmetic)entry.getValue()).saveNBTData(equippedCompound);
        elementCompound.func_74778_a("factory", entry.getKey());
        elementCompound.func_74782_a("equipped", (NBTBase)equippedCompound);
        list.func_74742_a((NBTBase)elementCompound);
      } 
      compound.func_74782_a("cosmetics", (NBTBase)list);
    }
    
    @NonNull
    public OutfitCosmetic copy() {
      OutfitCosmetic copy = new OutfitCosmetic();
      for (Map.Entry<String, CosmeticPlayer.EquippedCosmetic> entry : this.equippedCosmeticMap.entrySet())
        copy.equippedCosmeticMap.put(entry.getKey(), ((CosmeticPlayer.EquippedCosmetic)entry.getValue()).copy()); 
      return copy;
    }
  }
  
  public static class EquippedCosmetic {
    private final EquippedCosmeticType type;
    
    private final ICosmetic[] cosmetics;
    
    private long wheelCooldown;
    
    protected EquippedCosmetic(@NonNull EquippedCosmeticType type, int size) {
      if (type == null)
        throw new NullPointerException("type is marked non-null but is null"); 
      this.type = type;
      this.cosmetics = new ICosmetic[size];
    }
    
    @NonNull
    public static <T extends EquippedCosmetic> T unique(int size) {
      return (T)new EquippedCosmetic(EquippedCosmeticType.UNIQUE, size);
    }
    
    @NonNull
    public static <T extends EquippedCosmetic> T wheel() {
      return (T)new EquippedCosmetic(EquippedCosmeticType.WHEEL, 4);
    }
    
    public static <T extends EquippedCosmetic> T decode(@NonNull NBTTagCompound compound) {
      if (compound == null)
        throw new NullPointerException("compound is marked non-null but is null"); 
      if (!compound.func_74764_b("type") || !compound.func_74764_b("class") || !compound.func_74764_b("size"))
        return null; 
      String className = compound.func_74779_i("class");
      int typeOrdinal = compound.func_74762_e("type");
      int size = compound.func_74762_e("size");
      try {
        EquippedCosmetic equippedCosmetic;
        Class<?> clazz = Class.forName(className);
        if (!EquippedCosmetic.class.isAssignableFrom(clazz))
          return null; 
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        T value = null;
        if (constructor.getParameterCount() == 0) {
          equippedCosmetic = (EquippedCosmetic)constructor.newInstance(new Object[0]);
        } else if (constructor.getParameterCount() == 2) {
          equippedCosmetic = (EquippedCosmetic)constructor.newInstance(new Object[] { EquippedCosmeticType.values()[typeOrdinal], Integer.valueOf(size) });
        } 
        if (equippedCosmetic == null)
          return null; 
        equippedCosmetic.loadNBTData(compound);
        return (T)equippedCosmetic;
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      } 
    }
    
    protected void loadNBTData(NBTTagCompound compound) {
      this.wheelCooldown = compound.func_74763_f("wheelCooldown");
      NBTTagList list = compound.func_150295_c("cosmetics", 10);
      for (int i = 0; i < list.func_74745_c(); i++) {
        NBTTagCompound cosmeticCompound = list.func_150305_b(i);
        if (cosmeticCompound.func_74764_b("id") && cosmeticCompound.func_74764_b("factory")) {
          String id = cosmeticCompound.func_74779_i("id");
          String factoryId = cosmeticCompound.func_74779_i("factory");
          Optional<CosmeticFactory> factory = CosmeticProvider.getInstance().getFactory(factoryId);
          if (factory.isPresent()) {
            Optional<? extends ICosmetic> cosmetic = ((CosmeticFactory)factory.get()).getCosmetic(id);
            if (cosmetic.isPresent()) {
              this.cosmetics[i] = cosmetic.get();
              continue;
            } 
          } 
        } 
        this.cosmetics[i] = null;
        continue;
      } 
    }
    
    public void saveNBTData(NBTTagCompound compound) {
      compound.func_74778_a("class", getClass().getName());
      compound.func_74768_a("type", this.type.ordinal());
      compound.func_74768_a("size", this.cosmetics.length);
      compound.func_74772_a("wheelCooldown", this.wheelCooldown);
      NBTTagList list = new NBTTagList();
      for (int i = 0; i < this.cosmetics.length; i++) {
        ICosmetic cosmetic = this.cosmetics[i];
        if (cosmetic != null) {
          NBTTagCompound cosmeticCompound = new NBTTagCompound();
          cosmeticCompound.func_74778_a("id", cosmetic.getId());
          cosmeticCompound.func_74778_a("factory", cosmetic.getFactory().getId());
          list.func_74742_a((NBTBase)cosmeticCompound);
        } else {
          list.func_74742_a((NBTBase)new NBTTagCompound());
        } 
      } 
      compound.func_74782_a("cosmetics", (NBTBase)list);
    }
    
    public boolean has(int index) {
      if (index < 0 || index >= this.cosmetics.length)
        return false; 
      return (this.cosmetics[index] != null);
    }
    
    public boolean has(@NonNull ICosmetic cosmetic) {
      if (cosmetic == null)
        throw new NullPointerException("cosmetic is marked non-null but is null"); 
      for (ICosmetic equipped : this.cosmetics) {
        if (equipped != null && equipped.getId().equals(cosmetic.getId()))
          return true; 
      } 
      return false;
    }
    
    @NonNull
    public Optional<ICosmetic> get(int index) {
      if (index < 0 || index >= this.cosmetics.length)
        return Optional.empty(); 
      return Optional.ofNullable(this.cosmetics[index]);
    }
    
    public ICosmetic getOrDefault(int index, ICosmetic defaultValue) {
      if (index < 0 || index >= this.cosmetics.length)
        return defaultValue; 
      return (this.cosmetics[index] != null) ? this.cosmetics[index] : defaultValue;
    }
    
    @NonNull
    public <T extends EquippedCosmetic> T set(int index, @NonNull ICosmetic cosmetic) {
      if (cosmetic == null)
        throw new NullPointerException("cosmetic is marked non-null but is null"); 
      if (!isValid(index, cosmetic))
        return (T)this; 
      this.cosmetics[index] = cosmetic;
      return (T)this;
    }
    
    @NonNull
    public <T extends EquippedCosmetic> T remove(int index) {
      if (index < 0 || index >= this.cosmetics.length)
        return (T)this; 
      this.cosmetics[index] = null;
      return (T)this;
    }
    
    @NonNull
    public <T extends EquippedCosmetic> T remove(@NonNull ICosmetic cosmetic) {
      if (cosmetic == null)
        throw new NullPointerException("cosmetic is marked non-null but is null"); 
      for (int i = 0; i < this.cosmetics.length; i++) {
        if (this.cosmetics[i] != null && this.cosmetics[i].getId().equals(cosmetic.getId())) {
          this.cosmetics[i] = null;
          break;
        } 
      } 
      return (T)this;
    }
    
    @NonNull
    public <T extends EquippedCosmetic> T equip(@NonNull ICosmetic cosmetic) {
      if (cosmetic == null)
        throw new NullPointerException("cosmetic is marked non-null but is null"); 
      return add(cosmetic, true);
    }
    
    @NonNull
    public <T extends EquippedCosmetic> T add(@NonNull ICosmetic cosmetic, boolean replace) {
      if (cosmetic == null)
        throw new NullPointerException("cosmetic is marked non-null but is null"); 
      boolean added = false;
      int i;
      for (i = 0; i < this.cosmetics.length; i++) {
        if (this.cosmetics[i] == null && isValid(i, cosmetic)) {
          this.cosmetics[i] = cosmetic;
          added = true;
          break;
        } 
      } 
      if (added || !replace)
        return (T)this; 
      for (i = 0; i < this.cosmetics.length; i++) {
        if (isValid(i, cosmetic)) {
          this.cosmetics[i] = cosmetic;
          break;
        } 
      } 
      return (T)this;
    }
    
    public boolean isValid(int index, @NonNull ICosmetic cosmetic) {
      if (cosmetic == null)
        throw new NullPointerException("cosmetic is marked non-null but is null"); 
      if (index < 0 || index >= this.cosmetics.length)
        return false; 
      return true;
    }
    
    @NonNull
    public <T extends EquippedCosmetic> T clear() {
      Arrays.fill((Object[])this.cosmetics, (Object)null);
      return (T)this;
    }
    
    @NonNull
    public <T extends EquippedCosmetic> T copy() {
      EquippedCosmetic copy = new EquippedCosmetic(this.type, this.cosmetics.length);
      System.arraycopy(this.cosmetics, 0, copy.cosmetics, 0, this.cosmetics.length);
      return (T)copy;
    }
    
    @NonNull
    public <T extends EquippedCosmetic> T wheelCooldown(long wheelCooldown) {
      this.wheelCooldown = wheelCooldown;
      return (T)this;
    }
    
    public ICosmetic[] getCosmetics() {
      return this.cosmetics;
    }
    
    public EquippedCosmeticType getType() {
      return this.type;
    }
    
    public long getWheelCooldown() {
      return this.wheelCooldown;
    }
    
    public enum EquippedCosmeticType {
      UNIQUE, WHEEL;
    }
  }
  
  public static class CosmeticPlayerRegisterProviderEvent extends ProviderEvent<CosmeticPlayer> {
    private CosmeticPlayerRegisterProviderEvent(@NonNull ProviderEvent.Phase phase, @NonNull CosmeticPlayer player) {
      super(phase, player);
      if (phase == null)
        throw new NullPointerException("phase is marked non-null but is null"); 
      if (player == null)
        throw new NullPointerException("player is marked non-null but is null"); 
    }
    
    @NonNull
    public static CosmeticPlayerRegisterProviderEvent pre(@NonNull CosmeticPlayer player) {
      if (player == null)
        throw new NullPointerException("player is marked non-null but is null"); 
      return new CosmeticPlayerRegisterProviderEvent(ProviderEvent.Phase.PRE, player);
    }
    
    @NonNull
    public static CosmeticPlayerRegisterProviderEvent post(@NonNull CosmeticPlayer player) {
      if (player == null)
        throw new NullPointerException("player is marked non-null but is null"); 
      return new CosmeticPlayerRegisterProviderEvent(ProviderEvent.Phase.POST, player);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\data\CosmeticPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */