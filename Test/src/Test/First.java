package Test;

public class First {
    public String getMessage() {
        // Local variable
        String message = "Hello, world!";
        return message;
    }

    public static void main(String[] args) {
        First first = new First();
        String returnedMessage = first.getMessage();
        System.out.println(returnedMessage);
    }
}
