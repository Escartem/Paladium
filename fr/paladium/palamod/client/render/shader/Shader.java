package fr.paladium.palamod.client.render.shader;

import java.util.ArrayList;
import java.util.Iterator;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL20;

public class Shader {
  private boolean loaded = false;
  
  private int shaderID = 0;
  
  private ArrayList<ShaderPart> shaderPrograms;
  
  private String name;
  
  private ArrayList<String> uniforms;
  
  private ArrayList<String> attributes;
  
  public Shader(ArrayList<ShaderPart> inputShaderPrograms, String inputName) throws Exception {
    this.name = inputName;
    this.shaderID = ARBShaderObjects.glCreateProgramObjectARB();
    if (this.shaderID != 0) {
      this.shaderPrograms = inputShaderPrograms;
      for (Iterator<ShaderPart> it = this.shaderPrograms.iterator(); it.hasNext();)
        ARBShaderObjects.glAttachObjectARB(this.shaderID, ((ShaderPart)it.next()).shaderID()); 
      ARBShaderObjects.glLinkProgramARB(this.shaderID);
      int logLength = ARBShaderObjects.glGetObjectParameteriARB(this.shaderID, 35716);
      String log = "";
      if (logLength != 0)
        log = ARBShaderObjects.glGetInfoLogARB(this.shaderID, logLength); 
      if (GL20.glGetProgram(this.shaderID, 35714) != 1)
        throw new Exception("Program did not link:\n" + log); 
      if (logLength != 0)
        System.out.print("\nLink log:\n " + log + "\n"); 
      ARBShaderObjects.glValidateProgramARB(this.shaderID);
      logLength = ARBShaderObjects.glGetObjectParameteriARB(this.shaderID, 35716);
      log = "";
      if (logLength != 0)
        log = ARBShaderObjects.glGetInfoLogARB(this.shaderID, logLength); 
      if (GL20.glGetProgram(this.shaderID, 35715) != 1)
        throw new Exception("Program did not validate:\n" + log); 
      if (logLength != 0)
        System.out.print("\nValidate log:\n " + log + "\n"); 
      this.uniforms = new ArrayList<>();
      int i = 0;
      String previous = "";
      int count = GL20.glGetProgram(this.shaderID, 35718);
      while (i < count) {
        String uniform = GL20.glGetActiveUniform(this.shaderID, i, 100);
        if (!uniform.isEmpty()) {
          if (!previous.isEmpty() && uniform.matches(previous))
            break; 
          previous = uniform;
          this.uniforms.add(uniform);
        } 
        i++;
      } 
      this.attributes = new ArrayList<>();
      i = 0;
      previous = "";
      count = GL20.glGetProgram(this.shaderID, 35721);
      while (i < count) {
        String attribute = GL20.glGetActiveAttrib(this.shaderID, i, 100);
        if (!attribute.isEmpty()) {
          if (!previous.isEmpty() && attribute.matches(previous))
            break; 
          previous = attribute;
          this.attributes.add(attribute);
        } 
        i++;
      } 
      this.loaded = true;
    } else {
      this.loaded = false;
    } 
  }
  
  public void bind() {
    if (this.loaded)
      ARBShaderObjects.glUseProgramObjectARB(this.shaderID); 
  }
  
  public void unbind() {
    ARBShaderObjects.glUseProgramObjectARB(0);
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getUniformAddress(String name) throws Exception {
    if (this.uniforms == null)
      throw new Exception("Shader not loaded."); 
    for (int i = 0; i < this.uniforms.size(); i++) {
      if (((String)this.uniforms.get(i)).compareTo(name) == 0)
        return i; 
    } 
    String e = "Uniform (" + name + ") not found!\n";
    e = e + "Uniforms: " + this.uniforms.size() + "\n";
    int j;
    for (j = 0; j < this.uniforms.size(); j++)
      e = e + j + " (" + (String)this.uniforms.get(j) + ")\n"; 
    e = e + "Attributes: " + this.attributes.size() + "\n";
    for (j = 0; j < this.attributes.size(); j++)
      e = e + j + " (" + (String)this.attributes.get(j) + ")\n"; 
    throw new Exception(e);
  }
  
  public void setUniform1f(String name, float a) throws Exception {
    GL20.glUniform1f(getUniformAddress(name), a);
  }
  
  public void setUniform2f(String name, float a, float b) throws Exception {
    GL20.glUniform2f(getUniformAddress(name), a, b);
  }
  
  public void setUniform3f(String name, float a, float b, float c) throws Exception {
    GL20.glUniform3f(getUniformAddress(name), a, b, c);
  }
  
  public void setUniform4f(String name, float a, float b, float c, float d) throws Exception {
    GL20.glUniform4f(getUniformAddress(name), a, b, c, d);
  }
  
  public int getAttributeAddress(String name) throws Exception {
    if (this.attributes == null)
      throw new Exception("Shader not loaded."); 
    for (int i = 0; i < this.attributes.size(); i++) {
      if (((String)this.attributes.get(i)).compareTo(name) == 0)
        return i; 
    } 
    String e = "Attribute (" + name + ") not found!\n";
    e = e + "Uniforms: " + this.uniforms.size() + "\n";
    int j;
    for (j = 0; j < this.uniforms.size(); j++)
      e = e + j + " (" + (String)this.uniforms.get(j) + ")\n"; 
    e = e + "Attributes: " + this.attributes.size() + "\n";
    for (j = 0; j < this.attributes.size(); j++)
      e = e + j + " (" + (String)this.attributes.get(j) + ")\n"; 
    throw new Exception(e);
  }
  
  public void setAttribute1f(String name, float a) throws Exception {
    GL20.glVertexAttrib1f(getAttributeAddress(name), a);
  }
  
  public void setAttribute2f(String name, float a, float b) throws Exception {
    GL20.glVertexAttrib2f(getAttributeAddress(name), a, b);
  }
  
  public void setAttribute3f(String name, float a, float b, float c) throws Exception {
    GL20.glVertexAttrib3f(getAttributeAddress(name), a, b, c);
  }
  
  public void setAttribute4f(String name, float a, float b, float c, float d) throws Exception {
    GL20.glVertexAttrib4f(getAttributeAddress(name), a, b, c, d);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\render\shader\Shader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */