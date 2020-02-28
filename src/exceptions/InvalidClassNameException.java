package exceptions;

public class InvalidClassNameException extends Exception {
    private String exeptionTrigger;

    public InvalidClassNameException(String exeptionTrigger){
        this.exeptionTrigger = exeptionTrigger;
    }

    public void printErrMessage(){
        System.err.println("There is no such class-name:\""+exeptionTrigger+"\"");
    }
}
