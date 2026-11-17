
package ChatApp;

import java.util.List;

public class ChatAppDriver {
    public static void main(String[] args) {

        ChatServer server = new ChatServer();
        User hugo = new User("Hugo", server);
        User luisa = new User("Luisa", server);
        User krystal = new User("Krystal", server);

        server.registerUser(hugo);
        server.registerUser(luisa);
        server.registerUser(krystal);

        System.out.println("===== SENDING MESSAGES =====");

        hugo.sendMessage(List.of(luisa), "Hello Luisa!");
        luisa.sendMessage(List.of(hugo), "Hi Hugo!");
        hugo.sendMessage(List.of(luisa, krystal), "Hello everyone!");

        System.out.println("\n===== HUGO'S CHAT HISTORY =====");
        hugo.viewHistory();

        System.out.println("\n===== LUISA'S CHAT HISTORY =====");
        luisa.viewHistory();

        System.out.println("\n===== KRYSTAL'S CHAT HISTORY =====");
        krystal.viewHistory();

        System.out.println("\n===== BLOCKING USERS =====");

        server.blockUser(luisa, hugo);

        hugo.sendMessage(List.of(luisa, krystal), "Can everyone see this?");

        System.out.println("\nLuisa's history after blocking Hugo:");
        luisa.viewHistory();

        System.out.println("\nKrystal's history:");
        krystal.viewHistory();
        System.out.println("\n===== UNDO LAST MESSAGE =====");

        System.out.println("Hugo's history before undo:");
        hugo.viewHistory();

        System.out.println("\nKrystal's history before undo:");
        krystal.viewHistory();

        hugo.undoLastMessage();

        System.out.println("\nHugo's history after undo:");
        hugo.viewHistory();

        System.out.println("\nKrystal's history after undo:");
        krystal.viewHistory();
    }
}
