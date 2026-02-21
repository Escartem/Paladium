package fr.paladium.pet.client.renderer.data;

import fr.paladium.pet.common.tile.cage.CageStatus;

public enum CageRenderState {
  IDLE, FOOD, PET;
  
  public static CageRenderState of(CageStatus status) {
    switch (status) {
      case UNFILLED:
        return IDLE;
      case FILLED:
        return FOOD;
    } 
    return PET;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\renderer\data\CageRenderState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */