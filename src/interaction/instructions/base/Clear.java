package interaction.instructions.base;

import interaction.instructions.Command;

public class Clear implements Command {
  @Override public void Execute() {}
  @Override public String toString() {
    return "clear";
  }
  public static final String NAME = toString();
  public static final String BRIEF = "очищает коллекцию";
  public static final String SYNTAX = toString();
  public static final String DESCRIPTION = "Удаляет из коллекции все элементы. Равнозначно вызову метода clear().";
}