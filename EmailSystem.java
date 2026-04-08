import java.util.*;

public class EmailSystem {
    private static MailServer server = new MailServer();
    private static Map<String, AccountClass> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static AccountClass currentUser = null;

    public static void main(String[] args) {

        accounts.put("minaal", new AccountClass("minaal", "123", "Gmail"));
        accounts.put("fatima", new AccountClass("fatima", "456", "Yahoo"));
        accounts.put("laiba" , new AccountClass("laiba ", "789", "Hotmail"));

        int option;
        do {
            printMainMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> signIn();
                case 2 -> signOut();
                case 3 -> sendEmail();
                case 4 -> readInbox();
                case 5 -> System.out.println("Exiting the program. Goodbye!");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Sign In");
        System.out.println("2. Sign Out");
        System.out.println("3. Send Email");
        System.out.println("4. Read Inbox Emails");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    private static void signIn() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        AccountClass account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            currentUser = account;
            System.out.println("Sign-in successful. Welcome, " + username + "!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void signOut() {
        if (currentUser == null) {
            System.out.println("No user is currently signed in.");
        } else {
            System.out.println("Goodbye, " + currentUser.getUsername() + "!");
            currentUser = null;
        }
    }

    private static void sendEmail() {
        if (!isSignedIn()) return;

        System.out.print("Enter recipient's username: ");
        String to = scanner.nextLine();
        if (!accounts.containsKey(to)) {
            System.out.println("Recipient not found.");
            return;
        }

        System.out.print("Enter email title: ");
        String title = scanner.nextLine();
        System.out.print("Enter email message: ");
        String message = scanner.nextLine();

        MailItem mail = new MailItem(to, currentUser.getUsername(), title, message);

        System.out.println("Email sent to " + to + "!");
    }

    private static void readInbox() {
        if (!isSignedIn()) return;

        int emailCount = server.howManyMailItems(currentUser.getUsername());
        if (emailCount == 0) {
            System.out.println("Your inbox is empty.");
            return;
        }

        List<MailItem> inbox = new ArrayList<>();
        MailItem mail;
        while ((mail = server.getNextMailItem(currentUser.getUsername())) != null) {
            inbox.add(mail);
        }

        System.out.println("\n--- Inbox ---");
        for (int i = 0; i < inbox.size(); i++) {
            System.out.println(i + ". " + inbox.get(i).getTitle());
        }

        System.out.print("Enter the email index to read (or -1 to return): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < inbox.size()) {
            inbox.get(index).print();
        } else if (index != -1) {
            System.out.println("Invalid index.");
        }
    }

    private static boolean isSignedIn() {
        if (currentUser == null) {
            System.out.println("You must be signed in to perform this action.");
            return false;
        }
        return true;
    }
}
