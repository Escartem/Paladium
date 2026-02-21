package fr.paladium.palamod.modules.spellsv2.utils;

import fr.paladium.palamod.modules.spellsv2.spells.Aerum;
import fr.paladium.palamod.modules.spellsv2.spells.Aphone;
import fr.paladium.palamod.modules.spellsv2.spells.Batum;
import fr.paladium.palamod.modules.spellsv2.spells.Egg;
import fr.paladium.palamod.modules.spellsv2.spells.Explosif;
import fr.paladium.palamod.modules.spellsv2.spells.Freeze;
import fr.paladium.palamod.modules.spellsv2.spells.Gravity;
import fr.paladium.palamod.modules.spellsv2.spells.Ignis;
import fr.paladium.palamod.modules.spellsv2.spells.Inertium;
import fr.paladium.palamod.modules.spellsv2.spells.Mentalis;
import fr.paladium.palamod.modules.spellsv2.spells.Observatus;
import fr.paladium.palamod.modules.spellsv2.spells.Omniscience;
import fr.paladium.palamod.modules.spellsv2.spells.Perception;
import fr.paladium.palamod.modules.spellsv2.spells.Repulsion;

public enum Spells {
  AERUM((Spell)new Aerum()),
  BATUM((Spell)new Batum()),
  INERTIUM((Spell)new Inertium()),
  MENTALIS((Spell)new Mentalis()),
  REPLUSION((Spell)new Repulsion()),
  EXPLOSIF((Spell)new Explosif()),
  PERCEPTION((Spell)new Perception()),
  OMNISCIENCE((Spell)new Omniscience()),
  OBSERVATUS((Spell)new Observatus()),
  APHONE((Spell)new Aphone()),
  GRAVITY((Spell)new Gravity()),
  IGNIS((Spell)new Ignis()),
  FREEZE((Spell)new Freeze()),
  EGG((Spell)new Egg());
  
  Spell spell;
  
  public Spell getSpell() {
    return this.spell;
  }
  
  public void setSpell(Spell spell) {
    this.spell = spell;
  }
  
  Spells(Spell spell) {
    this.spell = spell;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv\\utils\Spells.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */