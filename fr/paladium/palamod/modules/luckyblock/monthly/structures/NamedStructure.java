package fr.paladium.palamod.modules.luckyblock.monthly.structures;

import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;

public class NamedStructure {
  private final String name;
  
  private final AbstractStructure structure;
  
  public static NamedStructureBuilder builder() {
    return new NamedStructureBuilder();
  }
  
  public static class NamedStructureBuilder {
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
  
  public String getName() {
    return this.name;
  }
  
  public AbstractStructure getStructure() {
    return this.structure;
  }
  
  public NamedStructure(String name, AbstractStructure structure) {
    this.name = name;
    this.structure = structure;
  }
  
  public AbstractStructure spawn() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\structures\NamedStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */