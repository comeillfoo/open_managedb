package interaction.customer;

public abstract class Commander<K, V> implements Reciever {
  private final Map<K, V> elements;
  private final String varname;
}