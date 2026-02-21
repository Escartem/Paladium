package fr.paladium.palacommunityevent.common.extended;

import fr.paladium.palacommunityevent.common.extended.data.CommunityEventData;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.common.pojo.advent.AdventCalendarType;
import fr.paladium.palacommunityevent.server.managers.PalaCommunityEventManager;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PalaCommunityEventEEP extends ExtendedEntityProperties {
  public static final String PROP_NAME = "comnityevnt_eep";
  
  public List<CommunityEventData> communityEventDatas = new ArrayList<>();
  
  public Map<Integer, AdventCalendarType[]> adventCalendarDatas = (Map)new HashMap<>();
  
  public void saveNBTData(NBTTagCompound compound) {
    compound.func_74768_a("nbCommunityEventDatas", this.communityEventDatas.size());
    int i = 0;
    for (CommunityEventData c : this.communityEventDatas) {
      compound.func_74782_a("communityEventData-" + i, (NBTBase)c.writeToNBT());
      i++;
    } 
    NBTTagList adventCalendarDatasNBT = new NBTTagList();
    for (Map.Entry<Integer, AdventCalendarType[]> entry : this.adventCalendarDatas.entrySet()) {
      NBTTagCompound adventCalendarNBT = new NBTTagCompound();
      NBTTagList adventCalendarListNBT = new NBTTagList();
      for (i = 0; i < ((AdventCalendarType[])entry.getValue()).length; i++) {
        NBTTagCompound value = new NBTTagCompound();
        value.func_74768_a("day", i);
        value.func_74778_a("value", ((AdventCalendarType[])entry.getValue())[i].name());
        adventCalendarListNBT.func_74742_a((NBTBase)value);
      } 
      adventCalendarNBT.func_74782_a("list", (NBTBase)adventCalendarListNBT);
      adventCalendarNBT.func_74768_a("year", ((Integer)entry.getKey()).intValue());
      adventCalendarDatasNBT.func_74742_a((NBTBase)adventCalendarNBT);
    } 
    compound.func_74782_a("adventCalendarDatasNBT", (NBTBase)adventCalendarDatasNBT);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    this.communityEventDatas = new ArrayList<>();
    int nbCommunityEventDatas = compound.func_74762_e("nbCommunityEventDatas");
    for (int i = 0; i < nbCommunityEventDatas; i++)
      this.communityEventDatas.add((new CommunityEventData()).readFromNBT(compound.func_74775_l("communityEventData-" + i))); 
    if (compound.func_150297_b("adventCalendarDatasNBT", 9)) {
      NBTTagList adventCalendarDatasNBT = compound.func_150295_c("adventCalendarDatasNBT", 10);
      for (int j = 0; j < adventCalendarDatasNBT.func_74745_c(); j++) {
        NBTTagCompound adventCalendarNBT = adventCalendarDatasNBT.func_150305_b(j);
        NBTTagList adventCalendarListNBT = adventCalendarNBT.func_150295_c("list", 10);
        AdventCalendarType[] adventCalendarTypes = new AdventCalendarType[adventCalendarListNBT.func_74745_c()];
        for (int m = 0; m < adventCalendarListNBT.func_74745_c(); m++) {
          NBTTagCompound value = adventCalendarListNBT.func_150305_b(m);
          int day = value.func_74762_e("day");
          AdventCalendarType type = AdventCalendarType.valueOf(value.func_74779_i("value"));
          adventCalendarTypes[day] = type;
        } 
        int k = adventCalendarNBT.func_74762_e("year");
        this.adventCalendarDatas.put(Integer.valueOf(k), adventCalendarTypes);
      } 
    } 
    int year = LocalDateTime.now().getYear();
    if (!this.adventCalendarDatas.containsKey(Integer.valueOf(year))) {
      AdventCalendarType[] data = new AdventCalendarType[24];
      Arrays.fill((Object[])data, AdventCalendarType.NONE);
      this.adventCalendarDatas.put(Integer.valueOf(year), data);
    } 
  }
  
  public CommunityEventData getDataOf(String communityEventId) {
    CommunityEventData communityEventData = new CommunityEventData(communityEventId);
    if (PalaCommunityEventManager.getInstance().getCommunityEvents().contains(new CommunityEvent(communityEventId))) {
      if (!this.communityEventDatas.contains(communityEventData))
        this.communityEventDatas.add(communityEventData); 
      int idx = this.communityEventDatas.indexOf(communityEventData);
      return this.communityEventDatas.get(idx);
    } 
    return null;
  }
  
  public AdventCalendarType[] getAdventCalendarData(int year) {
    if (!this.adventCalendarDatas.containsKey(Integer.valueOf(year))) {
      AdventCalendarType[] data = new AdventCalendarType[24];
      Arrays.fill((Object[])data, AdventCalendarType.NONE);
      this.adventCalendarDatas.put(Integer.valueOf(year), data);
    } 
    return this.adventCalendarDatas.get(Integer.valueOf(year));
  }
  
  public AdventCalendarType getAdventCalendarType(int year, int day) {
    return getAdventCalendarData(year)[day - 1];
  }
  
  public static PalaCommunityEventEEP get(Entity entity) {
    return (PalaCommunityEventEEP)entity.getExtendedProperties("comnityevnt_eep");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\extended\PalaCommunityEventEEP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */