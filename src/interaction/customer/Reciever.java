package interaction.customer;

import entity.Mappable;
import java.io.PrintStream;
import java.util.List;

public interface Reciever<K, V extends Mappable<K>> {
  void add(V element);
  void add(K key, V element);
  List<V> load();
  void unload();
  void clear();
  void man(String[] pages, PrintStream printer);
  String review();
  void removeKey();
  void save();
  void show();
  void update(V element);
  void maxByCreationDate();
  void filterContaines();
  void sumOfAnnual();
}