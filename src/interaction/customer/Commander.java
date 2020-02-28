package interaction.customer;

import entity.Mappable;
import java.util.HashMap;
import java.util.Map;

abstract class Commander<K, V extends Mappable<K>> implements Receiver<K, V> {
  protected final Map<K, V> elements;
  protected final String envVar;
  protected Commander(String envVar) {
    this.envVar = envVar;
    elements = new HashMap<>();
    for (V element : load())
      add(element);
  }

  @Override public String review() {
    Class studyee = elements.getClass();
    StringBuilder info = new StringBuilder("\t$ Collection canonical name: " + studyee.getCanonicalName() + ";\n");
    info.append("\t$ Collection in generic view: " + studyee.toGenericString() + ";\n");
    info.append("\t$ As string: " + studyee + "\n");
    studyee = null; // TODO: is it possible memory performance?
    info.append("\t$ Is collection empty?: " + elements.isEmpty() + ";\n");
    info.append("\t$ Collection size: " + elements.size() + ";\n");
    return info.toString();
  }
}