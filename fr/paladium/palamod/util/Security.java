package fr.paladium.palamod.util;

import java.security.Permission;

public class Security extends SecurityManager {
  public void checkPermission(Permission permission) {
    System.out.println("permission: " + permission.getName() + " : " + permission.getActions());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\Security.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */