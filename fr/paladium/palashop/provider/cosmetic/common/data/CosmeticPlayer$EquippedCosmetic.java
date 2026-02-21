package fr.paladium.palashop.provider.cosmetic.common.data;

import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class EquippedCosmetic {
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


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\data\CosmeticPlayer$EquippedCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */