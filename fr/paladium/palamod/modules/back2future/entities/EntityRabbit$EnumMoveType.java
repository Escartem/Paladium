package fr.paladium.palamod.modules.back2future.entities;

enum EnumMoveType {
  NONE(0.0F, 0.0F, 30, 1),
  HOP(0.8F, 0.2F, 20, 10),
  STEP(1.0F, 0.45F, 14, 14),
  SPRINT(1.75F, 0.4F, 1, 8),
  ATTACK(2.0F, 0.7F, 7, 8);
  
  private final float speed;
  
  private final float field_180077_g;
  
  private final int duration;
  
  private final int field_180085_i;
  
  EnumMoveType(float typeSpeed, float p_i45866_4_, int typeDuration, int p_i45866_6_) {
    this.speed = typeSpeed;
    this.field_180077_g = p_i45866_4_;
    this.duration = typeDuration;
    this.field_180085_i = p_i45866_6_;
  }
  
  public float getSpeed() {
    return this.speed;
  }
  
  public float func_180074_b() {
    return this.field_180077_g;
  }
  
  public int getDuration() {
    return this.duration;
  }
  
  public int func_180073_d() {
    return this.field_180085_i;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityRabbit$EnumMoveType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */