package fr.paladium.palaconfiguration.server.dto.file.exception;

import fr.paladium.palaconfiguration.server.dto.file.RemoteFile;
import lombok.NonNull;

public class RemoteFileException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  private final RemoteFile file;
  
  public RemoteFile getFile() {
    return this.file;
  }
  
  public RemoteFileException(@NonNull RemoteFile file, @NonNull String message) {
    super(message);
    if (file == null)
      throw new NullPointerException("file is marked non-null but is null"); 
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
    this.file = file;
  }
  
  public RemoteFileException(@NonNull RemoteFile file, @NonNull String message, @NonNull Throwable cause) {
    super(message, cause);
    if (file == null)
      throw new NullPointerException("file is marked non-null but is null"); 
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
    if (cause == null)
      throw new NullPointerException("cause is marked non-null but is null"); 
    this.file = file;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\dto\file\exception\RemoteFileException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */