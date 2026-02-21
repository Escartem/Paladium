package fr.paladium.palashop.provider.cosmetic.common.data;

import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class OutfitCosmetic {
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


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\data\CosmeticPlayer$OutfitCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */