package fr.paladium.palarpg.module.dungeon.common.ranking;

import java.util.List;

public class DungeonRankingDataList {
  private final int index;
  
  private final boolean end;
  
  private final List<DungeonRankingData> list;
  
  public DungeonRankingDataList(int index, boolean end, List<DungeonRankingData> list) {
    this.index = index;
    this.end = end;
    this.list = list;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public boolean isEnd() {
    return this.end;
  }
  
  public List<DungeonRankingData> getList() {
    return this.list;
  }
  
  public int size() {
    return this.list.size();
  }
  
  public boolean isEmpty() {
    return this.list.isEmpty();
  }
  
  public DungeonRankingData get(int i) {
    return this.list.get(i);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\ranking\DungeonRankingDataList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */