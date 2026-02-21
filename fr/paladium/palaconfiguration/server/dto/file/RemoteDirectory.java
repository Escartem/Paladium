package fr.paladium.palaconfiguration.server.dto.file;

import fr.paladium.palaconfiguration.server.utils.FilePathUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.NonNull;

public final class RemoteDirectory extends RemoteObject {
  private final List<RemoteObject> files;
  
  public RemoteDirectory(String path, String name, String sha1, long size, boolean directory, List<RemoteObject> files) {
    super(path, name, sha1, size, directory);
    this.files = files;
  }
  
  @NonNull
  public List<RemoteObject> listFiles() {
    return listFiles(false);
  }
  
  @NonNull
  public List<RemoteObject> listFiles(boolean recursive) {
    return listFiles(recursive, (Predicate<RemoteObject>)null);
  }
  
  @NonNull
  public List<RemoteObject> listFiles(boolean recursive, String... extensions) {
    return listFiles(recursive, file -> {
          if (extensions == null || extensions.length == 0 || file.isDirectory())
            return true; 
          for (String extension : extensions) {
            if (file.getName().endsWith(extension))
              return true; 
          } 
          return false;
        });
  }
  
  @NonNull
  public List<RemoteObject> listFiles(boolean recursive, Predicate<RemoteObject> filter) {
    if (!recursive) {
      if (filter == null)
        return this.files; 
      return (List<RemoteObject>)this.files.stream().filter(filter).collect(Collectors.toList());
    } 
    List<RemoteObject> allFiles = new ArrayList<>(listFiles(false, filter));
    for (RemoteObject file : this.files) {
      if (filter != null && !filter.test(file))
        continue; 
      if (file.isDirectory())
        allFiles.addAll(((RemoteDirectory)file).listFiles(true, filter)); 
    } 
    return allFiles;
  }
  
  @NonNull
  public Optional<RemoteObject> getFile(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return getFile(name, false);
  }
  
  @NonNull
  public Optional<RemoteObject> getFile(@NonNull String name, boolean recursive) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    String normalizedName = FilePathUtils.normalize(name);
    if (normalizedName.contains("/")) {
      String[] parts = normalizedName.split("/");
      String firstPart = parts[0];
      Optional<RemoteObject> foundFile = getFile(firstPart, true);
      if (foundFile.isPresent()) {
        RemoteObject file = foundFile.get();
        if (file.isDirectory())
          return ((RemoteDirectory)file).getFile(normalizedName.substring(firstPart.length() + 1), true); 
        return Optional.of(file);
      } 
    } 
    if (!recursive)
      return this.files.stream().filter(file -> file.getName().equals(normalizedName)).findFirst(); 
    for (RemoteObject file : this.files) {
      if (file.getName().equals(normalizedName))
        return Optional.of(file); 
      if (file.isDirectory()) {
        Optional<RemoteObject> foundFile = ((RemoteDirectory)file).getFile(normalizedName, true);
        if (foundFile.isPresent())
          return foundFile; 
      } 
    } 
    return Optional.empty();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\dto\file\RemoteDirectory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */