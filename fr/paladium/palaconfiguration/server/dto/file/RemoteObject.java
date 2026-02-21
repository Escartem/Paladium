package fr.paladium.palaconfiguration.server.dto.file;

import java.util.Objects;
import lombok.NonNull;

public abstract class RemoteObject {
  private final String path;
  
  private final String name;
  
  private final String sha1;
  
  private final long size;
  
  private final boolean directory;
  
  public RemoteObject(String path, String name, String sha1, long size, boolean directory) {
    this.path = path;
    this.name = name;
    this.sha1 = sha1;
    this.size = size;
    this.directory = directory;
  }
  
  public String getPath() {
    return this.path;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getSha1() {
    return this.sha1;
  }
  
  public long getSize() {
    return this.size;
  }
  
  public boolean isDirectory() {
    return this.directory;
  }
  
  public boolean isFile() {
    return !this.directory;
  }
  
  @NonNull
  public RemoteFile asFile() {
    if (!isFile())
      throw new IllegalStateException("This object is not a file"); 
    return (RemoteFile)this;
  }
  
  @NonNull
  public RemoteDirectory asDirectory() {
    if (!isDirectory())
      throw new IllegalStateException("This object is not a directory"); 
    return (RemoteDirectory)this;
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (!(o instanceof RemoteObject))
      return false; 
    RemoteObject that = (RemoteObject)o;
    return (this.size == that.size && this.directory == that.directory && Objects.equals(this.path, that.path) && Objects.equals(this.sha1, that.sha1));
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.path, this.sha1, Long.valueOf(this.size), Boolean.valueOf(this.directory) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\dto\file\RemoteObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */