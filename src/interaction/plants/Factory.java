package interaction.plants;

interface Factory<T> {
  <T> T create();
  <T> T createFromFile(String pathname);
}