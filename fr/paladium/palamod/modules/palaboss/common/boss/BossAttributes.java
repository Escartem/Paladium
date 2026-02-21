package fr.paladium.palamod.modules.palaboss.common.boss;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.paladium.palamod.modules.palaboss.common.attacks.Attack;
import fr.paladium.palamod.modules.palaboss.common.attacks.AttackParamEntry;
import fr.paladium.palamod.modules.palaboss.common.attacks.AttacksRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BossAttributes {
  private transient int entityID;
  
  private transient String name;
  
  private JsonObject jsonRootObject;
  
  private Map<Attack, JsonObject> attacks;
  
  private ArrayList<BossLoot> loots;
  
  public BossAttributes(int entityID, String name) {
    this.entityID = entityID;
    this.name = name;
    this.attacks = new HashMap<>();
    this.loots = new ArrayList<>();
  }
  
  public BossAttributes parseAttributes(String jsonContent) {
    try {
      JsonParser parser = new JsonParser();
      this.jsonRootObject = parser.parse(jsonContent).getAsJsonObject();
      JsonArray lootsArray = this.jsonRootObject.getAsJsonArray("loots");
      for (int i = 0; i < lootsArray.size(); i++) {
        JsonObject attackElement = lootsArray.get(i).getAsJsonObject();
        this.loots.add(new BossLoot(attackElement.get("lootItemID").getAsString(), attackElement
              .get("count").getAsInt()));
      } 
      JsonArray attacksArray = this.jsonRootObject.getAsJsonArray("attacks");
      for (int j = 0; j < attacksArray.size(); j++) {
        JsonObject attackElement = attacksArray.get(j).getAsJsonObject();
        Class<? extends Attack> attackClass = AttacksRegistry.INSTANCE.getAttackByName(attackElement.get("attackName").getAsString());
        assert attackClass != null;
        Attack attack = attackClass.getDeclaredConstructor(new Class[] { JsonObject.class }).newInstance(new Object[] { attackElement });
        for (AttackParamEntry attackParam : attack.getAttackParams()) {
          if (attackElement.get(attackParam.getParamName()) != null)
            attackParam.setValue(attackElement.get(attackParam.getParamName())); 
        } 
        this.attacks.put(attack, attackElement);
      } 
    } catch (InstantiationException|IllegalAccessException|NoSuchMethodException|java.lang.reflect.InvocationTargetException e) {
      e.printStackTrace();
    } 
    return this;
  }
  
  public String getAttributeString(String key, String defaultValue) {
    JsonElement value = getAttributeElement(key);
    return (value == null) ? defaultValue : value.getAsString();
  }
  
  public int getAttributeInteger(String key, int defaultValue) {
    JsonElement value = getAttributeElement(key);
    return (value == null) ? defaultValue : value.getAsInt();
  }
  
  public float getAttributeFloat(String key, float defaultValue) {
    JsonElement value = getAttributeElement(key);
    return (value == null) ? defaultValue : value.getAsFloat();
  }
  
  public double getAttributeDouble(String key, double defaultValue) {
    JsonElement value = getAttributeElement(key);
    return (value == null) ? defaultValue : value.getAsDouble();
  }
  
  public JsonElement getAttributeElement(String key) {
    return this.jsonRootObject.get(key);
  }
  
  public JsonObject getJsonElement() {
    if (this.jsonRootObject == null)
      throw new IllegalStateException("Attributes must be parsed before accessing json root object"); 
    return this.jsonRootObject;
  }
  
  public int getEntityID() {
    return this.entityID;
  }
  
  public void setEntityID(int entityID) {
    this.entityID = entityID;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Map<Attack, JsonObject> getAttacks() {
    return this.attacks;
  }
  
  public ArrayList<BossLoot> getLoots() {
    return this.loots;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\boss\BossAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */