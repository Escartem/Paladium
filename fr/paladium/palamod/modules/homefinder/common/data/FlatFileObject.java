package fr.paladium.palamod.modules.homefinder.common.data;

import java.io.File;

public interface FlatFileObject {
  File getFile();
  
  void load();
  
  void save();
  
  Long getSaveInterval();
  
  void setSaveInterval();
  
  boolean canSave();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\data\FlatFileObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */