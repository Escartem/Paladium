package fr.paladium.pet.common.tile.cage;

import fr.paladium.pet.common.constant.PetTranslateEnum;

public enum CageStatus {
  UNFILLED,
  FILLED,
  STEP_1(PetTranslateEnum.TEXT_CAPTURE_STEP_1, 100),
  STEP_2(PetTranslateEnum.TEXT_CAPTURE_STEP_2, 200),
  STEP_3(PetTranslateEnum.TEXT_CAPTURE_STEP_3, 400);
  
  private PetTranslateEnum translate;
  
  private final int score;
  
  public PetTranslateEnum getTranslate() {
    return this.translate;
  }
  
  public int getScore() {
    return this.score;
  }
  
  CageStatus() {
    this.translate = PetTranslateEnum.TEXT_CAPTURE_STEP_1;
    this.score = 0;
  }
  
  CageStatus(PetTranslateEnum translate, int score) {
    this.translate = translate;
    this.score = score;
  }
  
  public static CageStatus getNextStep(CageStatus status) {
    if (status == null)
      return null; 
    if (status == STEP_1)
      return STEP_2; 
    if (status == STEP_2)
      return STEP_3; 
    return null;
  }
  
  public static CageStatus getStep(int score) {
    if (score < STEP_1.score)
      return STEP_1; 
    if (score < STEP_2.score)
      return STEP_2; 
    if (score < STEP_3.score)
      return STEP_3; 
    return null;
  }
  
  public static boolean isPlaying(CageStatus status) {
    if (status == null)
      return false; 
    return (status == STEP_1 || status == STEP_2 || status == STEP_3);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\tile\cage\CageStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */