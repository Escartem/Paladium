package fr.paladium.palamod.pulsar;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.pulsar.configs.IConfiguration;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.IPulse;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.palamod.pulsar.pulse.PulseMeta;
import fr.paladium.palamod.pulsar.pulse.PulseProxy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import org.reflections.scanners.SubTypesScanner;

public class PulseManager {
  private final LinkedHashMap<Object, PulseMeta> pulses = new LinkedHashMap<>();
  
  public static SubTypesScanner scanner = new SubTypesScanner(false);
  
  private IConfiguration config;
  
  private boolean blockNewRegistrations = false;
  
  private boolean configLoaded = false;
  
  private static PulseManager instance;
  
  public PulseManager(IConfiguration config) {
    this.config = config;
    instance = this;
  }
  
  public static PulseManager getInstance() {
    return instance;
  }
  
  public void registerPulse(Object pulse) {
    String id, description;
    boolean forced, enabled;
    if (this.blockNewRegistrations)
      throw new RuntimeException("A mod tried to register a plugin after preinit! Pulse: " + pulse); 
    if (!this.configLoaded) {
      this.config.load();
      this.configLoaded = true;
    } 
    boolean missingDeps = false;
    try {
      Pulse p = pulse.getClass().<Pulse>getAnnotation(Pulse.class);
      id = p.id();
      description = p.description();
      forced = p.forced();
      enabled = p.defaultEnable();
    } catch (NullPointerException exception) {
      throw new RuntimeException("Could not parse @Pulse annotation for Pulse: " + pulse);
    } 
    if (description.equals(""))
      description = null; 
    PulseMeta meta = new PulseMeta(id, description, forced, enabled);
    meta.setEnabled((!missingDeps && getEnabledFromConfig(meta)));
    if (meta.isEnabled()) {
      parseAndAddProxies(pulse);
      this.pulses.put(pulse, meta);
    } 
  }
  
  private boolean getEnabledFromConfig(PulseMeta meta) {
    if (meta.isForced())
      return true; 
    return this.config.isModuleEnabled(meta);
  }
  
  @Deprecated
  private void parseAndAddProxies(Object pulse) {
    try {
      for (Field f : pulse.getClass().getDeclaredFields()) {
        Constants.logger.debug("Parsing field: " + f);
        PulseProxy p = f.<PulseProxy>getAnnotation(PulseProxy.class);
        if (p != null) {
          Constants.logger.warn("Pulse " + pulse + " used the deprecated PulseProxy annotation. As of Pulsar, it's now preferred to use FML's SidedProxy annotation.");
          setProxyField(pulse, f, p.clientSide(), p.serverSide());
        } 
      } 
    } catch (Exception ex) {
      throw new RuntimeException("Pulse annotation parsing failed for Pulse " + pulse + "; " + ex);
    } 
  }
  
  @Deprecated
  private void setProxyField(Object pulse, Field f, String client, String server) throws Exception {
    boolean accessible = f.isAccessible();
    f.setAccessible(true);
    switch (FMLCommonHandler.instance().getSide()) {
      case CLIENT:
        f.set(pulse, Class.forName(client).newInstance());
        break;
      default:
        f.set(pulse, Class.forName(server).newInstance());
        break;
    } 
    f.setAccessible(accessible);
  }
  
  public void preInit(FMLPreInitializationEvent event) {
    if (!this.blockNewRegistrations)
      this.config.flush(); 
    this.blockNewRegistrations = true;
    for (Map.Entry<Object, PulseMeta> e : this.pulses.entrySet()) {
      if (hasRequiredPulses(e)) {
        Constants.logger.debug("Preinitialising Pulse " + ((PulseMeta)e.getValue()).getId() + "...");
        if (e.getKey() instanceof IPulse) {
          IPulse ip = (IPulse)e.getKey();
          ip.preInit(event);
          continue;
        } 
        findAndInvokeHandlers(e.getKey(), event);
      } 
    } 
  }
  
  public void init(FMLInitializationEvent event) {
    for (Map.Entry<Object, PulseMeta> e : this.pulses.entrySet()) {
      Constants.logger.debug("Initialising Pulse " + ((PulseMeta)e.getValue()).getId() + "...");
      if (e.getKey() instanceof IPulse) {
        IPulse ip = (IPulse)e.getKey();
        ip.init(event);
        Constants.logger.warn("Pulse " + ((PulseMeta)e.getValue()).getId() + " is using the deprecated IPulse interface.");
        continue;
      } 
      findAndInvokeHandlers(e.getKey(), event);
    } 
  }
  
  public void postInit(FMLPostInitializationEvent event) {
    for (Map.Entry<Object, PulseMeta> e : this.pulses.entrySet()) {
      Constants.logger.debug("Postinitialising Pulse " + ((PulseMeta)e.getValue()).getId() + "...");
      if (e.getKey() instanceof IPulse) {
        IPulse ip = (IPulse)e.getKey();
        ip.postInit(event);
        continue;
      } 
      findAndInvokeHandlers(e.getKey(), event);
    } 
  }
  
  public void serverStarting(FMLServerStartingEvent event) {
    for (Map.Entry<Object, PulseMeta> e : this.pulses.entrySet()) {
      Constants.logger.debug("Server starting Pulse " + ((PulseMeta)e.getValue()).getId() + "...");
      if (e.getKey() instanceof IPulse) {
        IPulse ip = (IPulse)e.getKey();
        ip.serverStarting(event);
        continue;
      } 
      findAndInvokeHandlers(e.getKey(), event);
    } 
  }
  
  public void serverStarted(FMLServerStartedEvent event) {
    for (Map.Entry<Object, PulseMeta> e : this.pulses.entrySet()) {
      Constants.logger.debug("Server started Pulse " + ((PulseMeta)e.getValue()).getId() + "...");
      if (e.getKey() instanceof IPulse) {
        IPulse ip = (IPulse)e.getKey();
        ip.serverStarted(event);
        continue;
      } 
      findAndInvokeHandlers(e.getKey(), event);
    } 
  }
  
  private void findAndInvokeHandlers(Object pulse, Object evt) {
    for (Method m : pulse.getClass().getDeclaredMethods()) {
      try {
        if (m.getAnnotation(Handler.class) != null) {
          Class[] pTypes = m.getParameterTypes();
          if (pTypes.length == 1) {
            Class pt = pTypes[0];
            if (pt.isAssignableFrom(evt.getClass()))
              m.invoke(pulse, new Object[] { evt }); 
          } 
        } 
      } catch (Exception exception) {
        Constants.logger.warn("Caught exception in findAndInvokeHandlers: " + exception);
        exception.printStackTrace();
      } 
    } 
  }
  
  private boolean hasRequiredPulses(Map.Entry<Object, PulseMeta> entry) {
    String deps = ((Pulse)entry.getKey().getClass().<Pulse>getAnnotation(Pulse.class)).pulsesRequired();
    if (!deps.equals("")) {
      String[] parsedDeps = deps.split(";");
      for (String s : parsedDeps) {
        if (!isPulseLoaded(s)) {
          Constants.logger.info("Skipping Pulse " + ((PulseMeta)entry.getValue()).getId() + "; missing pulse: " + s);
          return false;
        } 
      } 
    } 
    return true;
  }
  
  public boolean isPulseLoaded(String pulseId) {
    for (Map.Entry<Object, PulseMeta> entry : this.pulses.entrySet()) {
      if (((PulseMeta)entry.getValue()).getId().equals(pulseId))
        return true; 
    } 
    return false;
  }
  
  public int getSize() {
    return this.pulses.size();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\pulsar\PulseManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */