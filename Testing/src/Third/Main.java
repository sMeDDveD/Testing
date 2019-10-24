package Third;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Verificator verificator = new Verificator("Resources\\Third\\base1.txt");
        System.out.println(verificator.checkLoginPassword("sash", "kaskevi"));
        System.out.println(verificator.checkLoginPassword("sash", "kasevi"));
        System.out.println(verificator.checkLoginPassword("sash", "kasevi"));
        System.out.println(verificator.checkLoginPassword("sash", "kasevi"));
        System.out.println(verificator.checkLoginPassword("sash", "kashkevi"));
    }
}
