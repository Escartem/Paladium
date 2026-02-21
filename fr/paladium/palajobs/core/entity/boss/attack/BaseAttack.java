package fr.paladium.palajobs.core.entity.boss.attack;

import net.minecraft.entity.Entity;

public abstract class BaseAttack<T extends Entity> {
  public abstract boolean perform(T paramT);
  
  public abstract int getProbability();
  
  public abstract int getDuration();
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\attack\BaseAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */