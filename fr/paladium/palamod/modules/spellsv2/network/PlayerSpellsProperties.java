package fr.paladium.palamod.modules.spellsv2.network;

import fr.paladium.palamod.modules.spellsv2.utils.SpellManager;
import fr.paladium.palamod.modules.spellsv2.utils.SpellObj;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerSpellsProperties implements IExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_SPELLS";
  
  private EntityPlayer entity;
  
  private double spellPoints;
  
  public EntityPlayer getEntity() {
    return this.entity;
  }
  
  public void setEntity(EntityPlayer entity) {
    this.entity = entity;
  }
  
  public double getSpellPoints() {
    return this.spellPoints;
  }
  
  public void setSpellPoints(double spellPoints) {
    this.spellPoints = spellPoints;
  }
  
  private List<SpellObj> spells = new ArrayList<>();
  
  public List<SpellObj> getSpells() {
    return this.spells;
  }
  
  public void setSpells(List<SpellObj> spells) {
    this.spells = spells;
  }
  
  private boolean hasLogin = false;
  
  public boolean isHasLogin() {
    return this.hasLogin;
  }
  
  public void setHasLogin(boolean hasLogin) {
    this.hasLogin = hasLogin;
  }
  
  private Map<Integer, Long> delays = new HashMap<>();
  
  public Map<Integer, Long> getDelays() {
    return this.delays;
  }
  
  public void setDelays(Map<Integer, Long> delays) {
    this.delays = delays;
  }
  
  public PlayerSpellsProperties(EntityPlayer player) {
    this.entity = player;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    compound.func_74780_a("spellPoints", this.spellPoints);
    NBTTagList nbtSpells = new NBTTagList();
    for (SpellObj spell : this.spells) {
      NBTTagCompound spellData = new NBTTagCompound();
      spellData.func_74768_a("id", spell.getSpell().getId());
      spellData.func_74768_a("tier", spell.getTier());
      spellData.func_74757_a("unlock", spell.isUnlock());
      nbtSpells.func_74742_a((NBTBase)spellData);
    } 
    compound.func_74782_a("spells", (NBTBase)nbtSpells);
    compound.func_74757_a("hasLogin", this.hasLogin);
    NBTTagList nbtDelays = new NBTTagList();
    for (Iterator<Integer> iterator = this.delays.keySet().iterator(); iterator.hasNext(); ) {
      int i = ((Integer)iterator.next()).intValue();
      NBTTagCompound delayData = new NBTTagCompound();
      delayData.func_74768_a("id", i);
      delayData.func_74772_a("time", ((Long)this.delays.get(Integer.valueOf(i))).longValue());
      nbtDelays.func_74742_a((NBTBase)delayData);
    } 
    compound.func_74782_a("delays", (NBTBase)nbtDelays);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("spellPoints"))
      this.spellPoints = compound.func_74769_h("spellPoints"); 
    if (compound.func_74764_b("spells")) {
      NBTTagList spellsNbt = compound.func_150295_c("spells", 10);
      this.spells.clear();
      for (int i = 0; i < spellsNbt.func_74745_c(); i++) {
        NBTTagCompound spellData = spellsNbt.func_150305_b(i);
        Spells spell = Spells.values()[spellData.func_74762_e("id")];
        SpellObj spellObj = new SpellObj(spell.getSpell(), spellData.func_74762_e("tier"), spellData.func_74767_n("unlock"));
        if (!this.spells.contains(spellObj))
          this.spells.add(spellObj); 
      } 
    } 
    if (compound.func_74764_b("hasLogin"))
      this.hasLogin = compound.func_74767_n("hasLogin"); 
    if (compound.func_74764_b("delays")) {
      this.delays.clear();
      NBTTagList nbtDelays = compound.func_150295_c("delays", 10);
      for (int i = 0; i < nbtDelays.func_74745_c(); i++) {
        NBTTagCompound delayData = nbtDelays.func_150305_b(i);
        this.delays.put(Integer.valueOf(delayData.func_74762_e("id")), Long.valueOf(delayData.func_74763_f("time")));
      } 
    } 
  }
  
  public void resetPlayerSpells() {
    SpellManager.setPoints(this.entity, SpellManager.getLevel(this.entity));
    for (SpellObj spell : this.spells) {
      spell.setTier(0);
      spell.setUnlock(false);
    } 
  }
  
  public void init(Entity entity, World world) {
    this.entity = (EntityPlayer)entity;
  }
  
  public static final void register(EntityPlayer player) {
    player.registerExtendedProperties("palamod_SPELLS", new PlayerSpellsProperties(player));
  }
  
  public static final PlayerSpellsProperties get(EntityPlayer player) {
    return (PlayerSpellsProperties)player.getExtendedProperties("palamod_SPELLS");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\PlayerSpellsProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */