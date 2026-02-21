package fr.paladium.palamod.modules.luckyblock.monthly.structures;

import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;

public class NamedStructureBuilder {
  private String name;
  
  private AbstractStructure structure;
  
  public NamedStructureBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public NamedStructureBuilder structure(AbstractStructure structure) {
    this.structure = structure;
    return this;
  }
  
  public NamedStructure build() {
    return new NamedStructure(this.name, this.structure);
  }
  
  public String toString() {
    return "NamedStructure.NamedStructureBuilder(name=" + this.name + ", structure=" + this.structure + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\structures\NamedStructure$NamedStructureBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */