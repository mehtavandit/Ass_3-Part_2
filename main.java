public class main {
    public static void main(String[] args) {
        EHITS a = new EHITS();

        a.add(a.generate(), "a");
        a.add(a.generate(), "b");
        a.add(a.generate(), "c");
        a.add(a.generate(), "d");
        a.display();



    }

}
