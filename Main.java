// Emelie Lind, Javautvecklare 2023, emelie.lind@iths.se

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // Skapar listor för att lagra produkter
    public static ArrayList<Product> allFruits = new ArrayList<Product>();
    public static ArrayList<Product> shoppingCart = new ArrayList<Product>();

    // Skapar en scanner för att ta emot användarinmatning
    public static Scanner input = new Scanner(System.in);

    // Metoden main, startpunkt för programmet
    public static void main(String[] args) {
        User adminUser = new User("admin", "admin123", true); // Administratör

        // Lägger till produkter i allFruits-listan
        allFruits.add(new Product("Äpple", "Kärnfrukt", 5));
        allFruits.add(new Product("Persika", "Stenfrukt", new String[]{"Stenfrukt", "Kärna"}, 7));
        allFruits.add(new Product("Banan", "Bärfrukt", new String[]{"Bärfrukt", "Gul"}, 10.50));
        allFruits.add(new Product("Morot", "Rotfrukt", new String[]{"Rotfrukt", "Rot"}, 5));
        allFruits.add(new Product("Gurka", "Grönsak", 10));
        allFruits.add(new Product("Spenat", "Bladgrönsak", new String[]{"Grönsak"}, 30));

        // Lägger till kampanjvaror
        allFruits.add(new Product("Vattenmelon", "Bärfrukt", 12.50, true, "2 för 20 kr, ordinarie pris 1 för 12,50 kr."));
        allFruits.add(new Product("Avokado", "Frukt", 18, true, "2 för 15 kr, ordinarie pris 1 för 18 kr."));
        allFruits.add(new Product("Citron", "Citrus", 8, true, "5 för 30 kr, ordinarie pris 1 för 8 kr."));


        System.out.println("Välkommen till frukt och gröntvågen!");
        System.out.print("Är du administratör tryck 1, är du kund tryck 2. ");
        String userType = input.nextLine();

        if (userType.equalsIgnoreCase("1")) {
            loginAsAdmin(adminUser);
        } else if (userType.equalsIgnoreCase("2")) {
            showCustomerMenu();
        } else {
            System.out.println("Okänt användartyp. Programmet avslutas.");
        }
    }

    // Metod för att logga in administratörer
    public static void loginAsAdmin(User adminUser) {
        System.out.print("Ange administratörens användarnamn: ");
        String username = input.nextLine();
        System.out.print("Ange administratörens lösenord: ");
        String password = input.nextLine();

        if (adminUser.getUsername().equals(username) && adminUser.getPassword().equals(password)) {
            showAdminMenu();
        } else {
            System.out.println("Felaktigt användarnamn eller lösenord. Programmet avslutas.");
        }
    }

    // Metod för att visa administratörens meny
    public static void showAdminMenu() {
        System.out.println();
        System.out.println("Välkommen administratör! Vänligen gör ditt val nedan.");
        String[] adminMenuChoices = {
                "1. Skriv ut alla produkter, varugrupper och priser.",
                "2. Ändra produkt i en varugrupp.",
                "3. Ändra produktens pris.",
                "4. Ta bort en produkt." ,
                "5. Sök efter en produkt och visa kilopris." ,
                "6. Visa kampanjvaror." ,
                "7. Avsluta programmet."
        };

        // Styr om programmet ska fortsätta köras eller avslutas
        boolean runAdminProgram = true;

        // En do-while loop som kör programmet tills användaren väljer att avsluta
        do {
            System.out.println();
            System.out.println("Vänligen välj något av alternativen:");
            for (String adminMenuChoice : adminMenuChoices) {
                System.out.println(adminMenuChoice);
            }
            System.out.println();
            System.out.print("Skriv menysiffra: ");

            try {
                int adminUserInput = input.nextInt();
                input.nextLine();

                // Här använder jag en switch-sats som beroende på användarens val utför olika åtgärder
                switch (adminUserInput) {
                    case 0 -> runAdminProgram = false; // Avslutar program
                    case 1 -> printAllProducts(); // Skriver ut alla produkter
                    case 2 -> changeProductInfo(); // Ändrar produktinformation
                    case 3 -> changeProductPrice(); // Ändra produktens pris
                    case 4 -> removeProduct(); // Ta bort en produkt
                    case 5 -> navigateAndSearchProducts(); // Söka efter en produkt och visa kilopris
                    case 6 -> showCampaignProducts(); // Visa kampanjvaror
                    case 7 -> {
                        closeProgram(); // Avsluta program
                        System.exit(0); // Avsluta program
                    }
                    default -> {
                        System.out.println();
                        System.out.println("Ogiltigt val. Vänligen försök igen och ange ett alternativ mellan 1-7.");
                    }

                }
            } catch (InputMismatchException e) {
                System.out.println("Ogiltig inmatning. Vänligen ange ett heltal.");
                input.nextLine();
            }
        } while (runAdminProgram);
    }

    // Metod för att visa kundens meny
    public static void showCustomerMenu() {
        String[] customerMenuChoices = {
                "1. Skriv ut alla produkter, varugrupper och priser.",
                "2. Lägg till produkt i varukorgen",
                "3. Visa varukorg",
                "4. Sök efter en produkt och visa kilopris." ,
                "5. Visa kampanjvaror." ,
                "6. Avsluta programmet"
        };

        boolean runCustomerProgram = true;

        do {
            System.out.println();
            System.out.println("Välkommen kära kund! Vänligen gör ditt val nedan.");
            System.out.println();
            System.out.println("Vänligen välj något av alternativen:");
            for (String customerMenuChoice : customerMenuChoices) {
                System.out.println(customerMenuChoice);
            }
            System.out.println();
            System.out.print("Skriv menysiffra: ");

            try {
                int customerUserInput = input.nextInt();
                input.nextLine();

                switch (customerUserInput) {
                    case 1 -> printAllProducts();
                    case 2 -> addToShoppingCart();
                    case 3 -> viewShoppingCart();
                    case 4 -> navigateAndSearchProducts();
                    case 5 -> showCampaignProducts();
                    case 6 -> {
                        closeProgram();
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Ogiltigt val. Vänligen försök igen och ange ett alternativ mellan 1-6.");
                    }

                }
            } catch (InputMismatchException e) {
                System.out.println("Ogiltig inmatning. Vänligen ange ett heltal.");
                input.nextLine();
            }
        } while (runCustomerProgram);
    }

    // Metod för att skriva ut alla produkter (Admin val 1, kund val 1)
    public static void printAllProducts() {
        System.out.println();
        for (Product p : allFruits) {
            System.out.println(p.getName() + " ingår i varugruppen: " + p.getProductGroup() + ", " + p.getPrice());
        }
        System.out.println();
    }

    // Metod för att ändra produktinformation (Admin val 2)
    public static void changeProductInfo() {
        System.out.println("Vilken produkt vill du ändra på?");
        System.out.print("Ange produktens namn: ");
        String findProductName = input.nextLine();
        boolean updated = false;

        for (int i = 0; i < allFruits.size(); i++) {
            if (allFruits.get(i).getName().equals(findProductName)) {
                System.out.print("Ange den nya produkten: ");
                String newProductGroup = input.nextLine();
                allFruits.get(i).setName(newProductGroup);
                updated = true;
                System.out.println("Produkten i varugruppen har uppdaterats!\n");
                break;
            }
        }

        if (!updated) {
            System.out.println("Produkten kunde inte hittas.");
        }
    }

    // Metod för att visa kampanjvarpr (Admin val 6, kund val 5)
    public static void showCampaignProducts() {
        System.out.println("Kampanjvaror:");
        for (Product p : allFruits) {
            if (p.isCampaignProduct()) {
                System.out.println(p.getName() + " (" + p.getCampaignDescription() + ")");
            }
        }
    }

    // Metod för att ändra produktens pris (Admin val 3)
    public static void changeProductPrice() {
        System.out.println("Vilken produkt vill du ändra pris på?");
        System.out.println("Ange produktens namn: ");
        String findProductName = input.nextLine();
        boolean updated = false;

        for (Product p : allFruits) {
            if (p.getName().equals(findProductName)) {
                System.out.print("Ange produktens nya pris: ");
                try {
                    double newProductPrice = input.nextDouble();
                    input.nextLine();
                    p.setPrice(newProductPrice);
                    updated = true;
                    System.out.println("Produktens pris har ändrats!\n");
                } catch (InputMismatchException e) {
                    System.out.println("Ogiltigt pris. Vänligen ange ett giltigt pris.");
                    input.nextLine();
                }
                break;
            }
        }

        if (!updated) {
            System.out.println("Produkten kunde inte hittas.");
        }
    }

    // Metod för att ta bort en produkt (Admin val 4)
    public static void removeProduct() {
        System.out.print("Vilken produkt vill du ta bort?: ");
        String findProductName = input.nextLine();
        boolean removed = false;

        for (Product p : allFruits) {
            if (p.getName().equals(findProductName)) {
                allFruits.remove(p);
                removed = true;
                System.out.println("Produkten har tagits bort.\n");
                break;
            }
        }

        if (!removed) {
            System.out.println("Produkten kunde inte hittas.");
        }
    }

    // Metod för att lägga till produkter i kundens varukorg (Kund val 2)
    public static void addToShoppingCart() {
        System.out.print("Ange namnet på produkten du vill lägga till i varukorgen: ");
        String productName = input.nextLine();

        for (Product product : allFruits) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.print("Ange antal produkter: ");
                try {
                    int quantity = input.nextInt();
                    input.nextLine();
                    product.setQuantity(quantity);
                    shoppingCart.add(product);
                    System.out.println(quantity + " " + productName + " har lagts till i varukorgen.");
                    return;
                } catch (InputMismatchException e) {
                    System.out.println("Ogiltigt antal. Vänligen ange ett heltal.");
                    input.nextLine();
                }
            }
        }

        System.out.println("Produkten kunde inte hittas.");
    }


    // Metod för att visa kundens varukorg (Kund val 3)
    public static void viewShoppingCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Varukorgen är tom.");
        } else {
            int totalQuantity = 0;
            double totalCost = 0;
            System.out.println("Varukorg:");
            for (Product product : shoppingCart) {
                int quantity = product.getQuantity();
                System.out.println(product.getName() + " (" + quantity + " st) - " + product.getPrice() * quantity + " kr");
                totalCost += product.getPrice() * quantity;
                totalQuantity += quantity;
            }
            System.out.println("Totalt pris: " + totalCost + " kr");
            System.out.println("Antal varor i varukorgen: " + totalQuantity); // Använd totalQuantity
        }
    }


    // Metod för att navigera och söka efter produkter (Admin val 5, kund val 4)
    public static void navigateAndSearchProducts() {
        boolean continueNavigation = true;

        while (continueNavigation) {
            System.out.println("Navigera eller sök efter produkter:");
            System.out.print("Ange varugrupp, produktnamn, eller 'meny' för att gå tillbaka till huvudmenyn: ");
            String categoryOrSearch = input.nextLine();

            if (categoryOrSearch.equalsIgnoreCase("meny")) {
                continueNavigation = false;
            } else {
                boolean found = false;

                System.out.println("Sökresultat:");
                for (Product p : allFruits) {
                    if (p.getName().toLowerCase().contains(categoryOrSearch.toLowerCase()) ||
                            p.getProductGroup().equalsIgnoreCase(categoryOrSearch) ||
                            (p.getAllProducts() != null && Arrays.asList(p.getAllProducts()).contains(categoryOrSearch))) {
                        System.out.println("Namn: " + p.getName());
                        System.out.println("Varugrupp: " + p.getProductGroup());
                        System.out.println("Pris per kilo: " + p.getPrice());

                        System.out.print("Ange vikt i kg: ");
                        try {
                            double weight = input.nextDouble();
                            input.nextLine();

                            double totalPrice = p.calculatePriceByWeight(weight);
                            System.out.println("Totalt pris: " + String.format("%.2f", totalPrice) + " kr");

                            found = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Ogiltig vikt. Vänligen ange en giltig vikt.");
                            input.nextLine();
                        }
                    }
                }

                if (!found) {
                    System.out.println("Inga produkter hittades.");
                }
            }
        }
    }

    // Metod för att avsluta programmet (Admin val 7, kund val 6)
    public static void closeProgram() {
        System.out.println("Tack för att du använde frukt och gröntvågen!");
        System.out.println("Programmet avslutas. Välkommen åter!");
    }
}
