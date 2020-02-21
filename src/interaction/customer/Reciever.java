package interaction.customer;

import entity.Mappable;

import java.io.PrintStream;
import java.util.List;

public interface Reciever {
  void add(Mappable element);
  List<? extends Mappable> load();
  void unload();
  void man(String[] pages, PrintStream printer);
}