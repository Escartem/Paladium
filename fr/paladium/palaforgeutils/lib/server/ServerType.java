package fr.paladium.palaforgeutils.lib.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import lombok.NonNull;

public class ServerType {
  private ServerType(String name, ServerType parent) {
    this.name = name;
    this.parent = parent;
  }
  
  private static final Map<String, ServerType> SERVER_TYPES = new HashMap<>();
  
  public static final ServerType OTHER = register("OTHER");
  
  public static final ServerType LOBBY = register("LOBBY");
  
  public static final ServerType FACTION = register("FACTION");
  
  public static final ServerType MINAGE = register("MINAGE");
  
  public static final ServerType FARMLAND = register("FARMLAND");
  
  public static final ServerType DIM_MINER = register("DIM_MINER");
  
  public static final ServerType RPG = register("RPG");
  
  public static final ServerType WARZONE = register("WARZONE", FACTION);
  
  private final String name;
  
  private final ServerType parent;
  
  public String getName() {
    return this.name;
  }
  
  public ServerType getParent() {
    return this.parent;
  }
  
  @NonNull
  public static ServerType register(@NonNull String type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return register(type, null);
  }
  
  @NonNull
  public static ServerType register(@NonNull String type, ServerType parent) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (type.isEmpty())
      throw new IllegalArgumentException("ServerType cannot be empty"); 
    ServerType serverType = new ServerType(type, parent);
    SERVER_TYPES.put(type, serverType);
    return serverType;
  }
  
  public static Map<String, ServerType> getServerTypes() {
    return SERVER_TYPES;
  }
  
  @NonNull
  public static ServerType get(String type) {
    if (type == null || type.isEmpty())
      return OTHER; 
    return SERVER_TYPES.getOrDefault(type, OTHER);
  }
  
  public boolean is(@NonNull ServerType... types) {
    if (types == null)
      throw new NullPointerException("types is marked non-null but is null"); 
    for (ServerType type : types) {
      if (this.name.equalsIgnoreCase(type.getName()) || (this.parent != null && this.parent.is(new ServerType[] { type })))
        return true; 
    } 
    return false;
  }
  
  public boolean is(@NonNull String... types) {
    if (types == null)
      throw new NullPointerException("types is marked non-null but is null"); 
    for (String type : types) {
      if (this.name.equalsIgnoreCase(type) || (this.parent != null && this.parent.is(new String[] { type })))
        return true; 
    } 
    return false;
  }
  
  public boolean matches(@NonNull String type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return this.name.equalsIgnoreCase(type);
  }
  
  public boolean matches(@NonNull Pattern pattern) {
    if (pattern == null)
      throw new NullPointerException("pattern is marked non-null but is null"); 
    return pattern.matcher(this.name).matches();
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.name });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    ServerType other = (ServerType)obj;
    return Objects.equals(this.name, other.name);
  }
  
  public String toString() {
    return this.name;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\server\ServerType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */