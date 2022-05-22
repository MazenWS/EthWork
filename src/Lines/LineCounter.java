package Lines;

public class LineCounter {
    static int counter;

    public static void track(int lineNumber) {
        counter = lineNumber;
    }

    public static int getLine() {
        return counter;
    }

    public static void main(String[] args){
        String str = "11  2 3 ;     ";
        System.out.println(str.replaceAll("\\s+"," ").trim());
    }
}
