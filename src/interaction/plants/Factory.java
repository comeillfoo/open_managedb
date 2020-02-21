package interaction.plants;

interface Factory<T> {
  <T> T make();
}