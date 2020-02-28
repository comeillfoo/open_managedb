package interaction.customer;

import entity.Mappable;
import interaction.instructions.extended.Indicator;
import interaction.sender.Prompter;

import java.io.PrintStream;
import java.util.List;

public interface Reciever<K, V extends Mappable<K>> {
  void add(V element);
  void add(K key, V element);
  void add(K key, V element, Indicator sign);
  List<V> load();
  void unload();
  void clear();
  void man(String[] pages, PrintStream printer);
  String review();
  Mappable<K> cook(Prompter.ParamsCollector committed);
  K search(Integer id);
  void remove(K key);
  String survey(Indicator litmus);
  V lookFor(K key);
}