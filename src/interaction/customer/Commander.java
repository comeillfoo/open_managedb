package interaction.customer;

import entity.Mappable;

import java.util.HashMap;
import java.util.Map;

public abstract class Commander<K, V extends Mappable> implements Reciever {
  protected final Map<K, V> elements;
  protected final String envVar;
  public Commander(String envVar) {
    this.envVar = envVar;
    elements = new HashMap<>();
    for (Mappable element : load())
      add(element);
  }
}