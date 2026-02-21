package fr.paladium.pet.common.capture;

import fr.paladium.lib.apollon.utils.Color;

public class CaptureManager {
  public static final int WORST_SCORE = -150;
  
  private static final Color COLOR_N_50 = Color.decode("#FF3939");
  
  private static final Color COLOR_P_25 = Color.decode("#FFEB36");
  
  private static final Color COLOR_P_50 = Color.decode("#5ED42A");
  
  private static CaptureManager instance;
  
  private CaptureCategory category;
  
  public CaptureCategory getCategory() {
    return this.category;
  }
  
  public CaptureManager() {
    instance = this;
    registerCategory();
  }
  
  public static CaptureManager getInstance() {
    if (instance == null)
      instance = new CaptureManager(); 
    return instance;
  }
  
  public void registerCategory() {
    CaptureCategory category = new CaptureCategory(1500L);
    CaptureSection redPositive = new CaptureSection(25, COLOR_P_25, 10.0F);
    CaptureSection greenNegative = new CaptureSection(-50, COLOR_N_50, 15.0F);
    CaptureSection purplePositive = new CaptureSection(50, COLOR_P_50, 5.0F);
    category.addSection(greenNegative);
    category.addSection(redPositive);
    category.addSection(purplePositive);
    this.category = category;
  }
  
  public boolean isValidScore(int score) {
    if (score == -150)
      return true; 
    for (CaptureSection section : this.category.getSections()) {
      if (score == section.getValue())
        return true; 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\capture\CaptureManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */