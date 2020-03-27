package exceptions;

/**
 * Класс-исключение, представляющий ошибку прочтения файла.
 * Выбрасывается при встрече строки, не связанной с командами или
 * параметрами.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class FileInvalidSyntaxException extends Exception {
  public FileInvalidSyntaxException(String message) { super(message); }
}
