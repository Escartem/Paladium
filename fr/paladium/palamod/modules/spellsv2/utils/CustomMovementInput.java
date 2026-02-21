package fr.paladium.palamod.modules.spellsv2.utils;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;

public class CustomMovementInput extends MovementInput {
  public MovementInput defaultMovement;
  
  public boolean freeze = false;
  
  public boolean confused = false;
  
  public CustomMovementInput(MovementInput moveInput) {
    this.defaultMovement = moveInput;
  }
  
  public void func_78898_a() {
    this.defaultMovement.func_78898_a();
    if (!this.freeze) {
      if (this.confused) {
        this.field_78901_c = this.defaultMovement.field_78899_d;
        this.field_78899_d = this.defaultMovement.field_78901_c;
        this.field_78900_b = -this.defaultMovement.field_78900_b;
        this.field_78902_a = -this.defaultMovement.field_78902_a;
      } else {
        this.field_78901_c = this.defaultMovement.field_78901_c;
        this.field_78899_d = this.defaultMovement.field_78899_d;
        this.field_78902_a = this.defaultMovement.field_78902_a;
        this.field_78900_b = this.defaultMovement.field_78900_b;
      } 
    } else {
      this.field_78901_c = false;
      this.field_78899_d = false;
      this.field_78902_a = 0.0F;
      this.field_78900_b = 0.0F;
    } 
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.CANCEL_JUMP))
      this.field_78901_c = false; 
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.CANCEL_FORWARD) && 
      this.field_78900_b > 0.0F)
      this.field_78900_b = 0.0F; 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv\\utils\CustomMovementInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */