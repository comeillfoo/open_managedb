package interaction.customer;

interface Reciever {
  void add(Managable element);
  void clear();
  void exit();
  void filterContains();
  void help();
  void write();
}