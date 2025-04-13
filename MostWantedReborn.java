import java.util.*;
import java.util.concurrent.TimeUnit;

class Car {
    private final String name;
    private int horsepower;
    private int acceleration;
    private int grip;
    private int weight;
    private int upgradeLevel = 0;
    private boolean owned = false;
    private final int price;
    private int heatLevel = 0; 

    public Car(String name, int horsepower, int acceleration, int grip, int weight, int price) {
        this.name = name;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.grip = grip;
        this.weight = weight;
        this.price = price;
    }

    public void upgrade() {
        this.horsepower += 15;
        this.acceleration += 5;
        this.grip += 3;
        this.weight -= 50;
        upgradeLevel++;
    }

    public void reset() {
        this.horsepower = this.horsepower - (15 * upgradeLevel);
        this.acceleration = this.acceleration - (5 * upgradeLevel);
        this.grip = this.grip - (3 * upgradeLevel);
        this.weight = this.weight + (50 * upgradeLevel);
        upgradeLevel = 0;
    }

    public void increaseHeat() {
        this.heatLevel = Math.min(5, heatLevel + 1);
    }

    public void reduceHeat() {
        this.heatLevel = Math.max(0, heatLevel - 1);
    }

    public int getUpgradeCost() {
        return 300 + (upgradeLevel * 200);
    }

    public double getPerformanceScore(String weather, String trackType) {
        double weatherModifier = switch (weather) {
            case "Rainy" -> 0.8;
            case "Foggy" -> 0.7;
            case "Windy" -> 0.9;
            default -> 1.0;
        };

        double trackModifier = switch (trackType) {
            case "Quarter Mile" -> 1.0;
            case "Half Mile" -> 1.1;
            case "Airport Runway" -> 1.2;
            case "City Streets" -> 0.9;
            case "Highway" -> 1.15;
            default -> 1.0;
        };

        
        double heatBonus = 1.0 + (heatLevel * 0.05);

        return (horsepower * 0.4 * trackModifier * heatBonus) +
               (acceleration * 0.3 * weatherModifier) +
               (grip * 0.2 * weatherModifier) -
               (weight * 0.05) +
               (Math.random() * 10);
    }

    // Getters and setters
    public boolean isOwned() { return owned; }
    public void setOwned(boolean owned) { this.owned = owned; }
    public int getPrice() { return price; }
    public String getName() { return name; }
    public int getUpgradeLevel() { return upgradeLevel; }
    public int getHorsepower() { return horsepower; }
    public int getHeatLevel() { return heatLevel; }
    public String getStats() {
        return String.format("%s (HP: %d, Acc: %d, Grip: %d, Wt: %dkg | Upg: Lvl %d | Heat: %d/5)",
                name, horsepower, acceleration, grip, weight, upgradeLevel, heatLevel);
    }
}

public class MostWantedReborn {
    private static final Scanner scanner = new Scanner(System.in);
    private static int playerBalance = 25000000;
    private static int debt = 0;
    private static final double INTEREST_RATE = 0.2;
    private static final List<Car> garage = new ArrayList<>();
    private static final List<String> weatherConditions = List.of("Sunny", "Rainy", "Windy", "Foggy");
    private static final Random random = new Random();
    private static int careerLevel = 1;
    private static int careerWins = 0;
    private static final List<String> policeUnits = List.of(
        "Patrol Cruiser", "Interceptor", "SWAT Van", 
        "Police Helicopter", "Undercover Unit"
    );
    private static boolean policeUnlocked = false;

    public static void main(String[] args) throws InterruptedException {
        List<Car> carShop = new ArrayList<>(Arrays.asList(
            new Car("Mustang GT", 450, 80, 70, 1600, 1500),
            new Car("Dodge Hellcat", 700, 85, 60, 1800, 2500),
            new Car("Nissan GTR", 570, 90, 80, 1700, 3000),
            new Car("Tesla Model S", 1020, 95, 75, 2100, 4000),
            new Car("Koenigsegg Jesko", 1600, 99, 85, 1300, 10000),
            new Car("Chevy Camaro", 455, 78, 68, 1650, 2000),
            new Car("BMW M4", 503, 82, 74, 1600, 3500),
            new Car("Audi RS7", 591, 86, 76, 1950, 3700),
            new Car("Lamborghini Huracan", 630, 90, 82, 1422, 7500)
        ));

        
        System.out.println("""
            \u001B[31m
            ███╗   ███╗ ██████╗ ███████╗████████╗    ██╗    ██╗ █████╗ ███╗   ██╗████████╗███████╗██████╗ 
            ████╗ ████║██╔═══██╗██╔════╝╚══██╔══╝    ██║    ██║██╔══██╗████╗  ██║╚══██╔══╝██╔════╝██╔══██╗
            ██╔████╔██║██║   ██║███████╗   ██║       ██║ █╗ ██║███████║██╔██╗ ██║   ██║   █████╗  ██║  ██║
            ██║╚██╔╝██║██║   ██║╚════██║   ██║       ██║███╗██║██╔══██║██║╚██╗██║   ██║   ██╔══╝  ██║  ██║
            ██║ ╚═╝ ██║╚██████╔╝███████║   ██║       ╚███╔███╔╝██║  ██║██║ ╚████║   ██║   ███████╗██████╔╝
            ╚═╝     ╚═╝ ╚═════╝ ╚══════╝   ╚═╝        ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝╚═════╝
                                                                                                
            \u001B[33m██████╗ ███████╗██████╗  ██████╗ ██████╗ ███╗   ██╗    \u001B[0m                              
            \u001B[33m██╔══██╗██╔════╝██╔══██╗██╔═══██╗██╔══██╗████╗  ██║    \u001B[0m                              
            \u001B[33m██████╔╝█████╗  ██████╔╝██║   ██║██████╔╝██╔██╗ ██║    \u001B[0m                              
            \u001B[33m██╔══██╗██╔══╝  ██╔══██╗██║   ██║██╔══██╗██║╚██╗██║    \u001B[0m                              
            \u001B[33m██║  ██║███████╗██████╔╝╚██████╔╝██║  ██║██║ ╚████║    \u001B[0m                              
            \u001B[33m╚═╝  ╚═╝╚══════╝╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝    \u001B[0m                              
            """);

        TimeUnit.SECONDS.sleep(2);
        System.out.println("\n🏁 Welcome to MOST WANTED: REBORN - Outrun the Law or Become Legend!\n");

        mainLoop:
        while (true) {
            System.out.println("\n═══════════════ MAIN MENU ═══════════════");
            System.out.println("💰 Balance: $" + playerBalance + (debt > 0 ? " | 💳 Debt: $" + debt : ""));
            System.out.println("1. Street Race");
            System.out.println("2. Police Pursuit " + (policeUnlocked ? "🚔" : "(Locked)"));
            System.out.println("3. Career Mode (Level " + careerLevel + ")");
            System.out.println("4. Garage (" + garage.size() + " cars)");
            System.out.println("5. Black Market (" + carShop.size() + " cars)");
            System.out.println("6. Chop Shop (Launder Heat)");
            System.out.println("7. Bank (Loan Shark)");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            switch (scanner.nextInt()) {
                case 1 -> streetRace(carShop);
                case 2 -> {
                    if (policeUnlocked) policePursuit();
                    else System.out.println("🔒 Complete Level 2 in Career Mode to unlock Police Pursuit!");
                }
                case 3 -> careerMode();
                case 4 -> manageGarage();
                case 5 -> blackMarket(carShop);
                case 6 -> chopShop();
                case 7 -> bankMenu();
                case 8 -> { break mainLoop; }
                default -> System.out.println("Invalid option!");
            }
        }
        System.out.println("\nThanks for playing MOST WANTED: REBORN! Stay off the police radar! 🚔💨");
    }

    private static void streetRace(List<Car> allCars) throws InterruptedException {
        System.out.println("\n═══════════════ STREET RACE ═══════════════");
        if (garage.isEmpty()) {
            System.out.println("You need at least one car in your garage to race!");
            return;
        }

        String[] tracks = {"City Streets", "Industrial Zone", "Airport Runway", "Mountain Pass"};
        System.out.println("\nSelect a track:");
        for (int i = 0; i < tracks.length; i++) {
            System.out.println((i+1) + ". " + tracks[i]);
        }
        System.out.print("Enter track choice: ");
        String track = tracks[scanner.nextInt() - 1];

        String weather = weatherConditions.get(random.nextInt(weatherConditions.size()));
        System.out.println("\nWeather Conditions: " + weather);

        System.out.println("\nSelect your car:");
        for (int i = 0; i < garage.size(); i++) {
            System.out.println((i+1) + ". " + garage.get(i).getStats());
        }
        System.out.print("Enter car choice: ");
        Car playerCar = garage.get(scanner.nextInt() - 1);

        System.out.println("\nOpponents:");
        List<Car> opponents = new ArrayList<>();
        while (opponents.size() < 3) {
            Car opponent = allCars.get(random.nextInt(allCars.size()));
            if (!opponents.contains(opponent)) opponents.add(opponent);
        }
        for (int i = 0; i < opponents.size(); i++) {
            System.out.println((i+1) + ". " + opponents.get(i).getName());
        }

        System.out.print("Enter your bet amount: $");
        int bet = scanner.nextInt();
        if (bet <= 0 || bet > playerBalance) {
            System.out.println("Invalid bet amount!");
            return;
        }

        // 25% chance of police interference
        boolean policeInterference = random.nextInt(4) == 0 && playerCar.getHeatLevel() > 0;
        if (policeInterference) {
            System.out.println("\n🚨 POLICE SCANNER: Undercover units detected in the area!");
            System.out.println("This race might attract police attention!");
        }

        System.out.println("\nRace starting on " + track + "...");
        TimeUnit.SECONDS.sleep(1);

        // Race simulation
        Map<Car, Double> results = new HashMap<>();
        results.put(playerCar, playerCar.getPerformanceScore(weather, track));
        for (Car opponent : opponents) {
            results.put(opponent, opponent.getPerformanceScore(weather, track));
        }

        List<Map.Entry<Car, Double>> sortedResults = new ArrayList<>(results.entrySet());
        sortedResults.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("\nRace Results:");
        for (int i = 0; i < sortedResults.size(); i++) {
            System.out.println((i+1) + ". " + sortedResults.get(i).getKey().getName() +
                             " - Score: " + String.format("%.2f", sortedResults.get(i).getValue()));
        }

        if (sortedResults.get(0).getKey().equals(playerCar)) {
            int winnings = bet * 2;
            playerBalance += winnings;
            playerCar.increaseHeat();
            System.out.println("\n🎉 You won! Payout: $" + winnings);
            
            if (policeInterference) {
                System.out.println("\n🚨 POLICE NOTICE: Your heat level increased!");
                System.out.println("Current heat for " + playerCar.getName() + ": " + playerCar.getHeatLevel() + "/5");
            }
        } else {
            playerBalance -= bet;
            System.out.println("\n😢 You lost! Better luck next time.");
        }
    }

    private static void policePursuit() throws InterruptedException {
        System.out.println("\n═══════════════ POLICE PURSUIT ═══════════════");
        if (garage.isEmpty()) {
            System.out.println("You need at least one car to outrun the police!");
            return;
        }

        System.out.println("\nSelect your escape car:");
        for (int i = 0; i < garage.size(); i++) {
            System.out.println((i+1) + ". " + garage.get(i).getStats());
        }
        System.out.print("Enter choice: ");
        Car playerCar = garage.get(scanner.nextInt() - 1);

        int heatLevel = playerCar.getHeatLevel();
        if (heatLevel == 0) {
            System.out.println("\nThis car has no heat - police aren't interested!");
            return;
        }

        System.out.println("\n🚨 POLICE PURSUIT INITIATED - HEAT LEVEL: " + heatLevel + "/5");
        System.out.println("Police Units Deployed: " + heatLevel);
        
        String[] pursuitPhases = {
            "Lose Patrol Cars in City Streets",
            "Dodge Roadblocks on Highway",
            "Outrun Helicopter in Industrial Zone",
            "Lose SWAT Vans in Underground"
        };

        double escapeChance = 0.6 - (heatLevel * 0.08); 
        boolean escaped = random.nextDouble() < escapeChance;

        System.out.println("\nPursuit Sequence:");
        for (int i = 0; i < heatLevel && i < pursuitPhases.length; i++) {
            System.out.println("• " + pursuitPhases[i]);
            TimeUnit.MILLISECONDS.sleep(800);
        }

        if (escaped) {
            int reward = 5000 * heatLevel;
            playerBalance += reward;
            playerCar.reduceHeat();
            System.out.println("\n✅ ESCAPE SUCCESSFUL!");
            System.out.println("You escaped successfully from the cops and earned $" + reward);
            System.out.println("Heat level reduced to: " + playerCar.getHeatLevel());
        } else {
            int fine = 2000 * heatLevel;
            playerBalance = Math.max(0, playerBalance - fine);
            playerCar.increaseHeat();
            System.out.println("\n❌ BUSTED!");
            System.out.println("Paid $" + fine + " in fines");
            System.out.println("Heat level increased to: " + playerCar.getHeatLevel());
        }
    }

    private static void careerMode() throws InterruptedException {
        System.out.println("\n═══════════════ CAREER MODE ═══════════════");
        System.out.println("🏆 Level: " + careerLevel + " | Wins: " + careerWins);
        
        if (garage.isEmpty()) {
            System.out.println("You need at least one car to start your career!");
            return;
        }

        String[] levelTracks = {
            "City Streets", "Desert Highway", "Mountain Pass", 
            "Coastal Road", "Industrial Zone", "Raceway Park"
        };
        String[] levelOpponents = {
            "Dominic", "Letty", "Brian", "Mia", "Roman", "Tej"
        };

        String track = levelTracks[careerLevel - 1];
        String opponent = levelOpponents[careerLevel - 1];
        String weather = weatherConditions.get(random.nextInt(weatherConditions.size()));
        
        System.out.println("\nCAREER EVENT: " + track.toUpperCase());
        System.out.println("Opponent: " + opponent);
        System.out.println("Weather: " + weather);
        System.out.println("Prize: $" + (10000 * careerLevel));
        
        System.out.println("\nSelect your car:");
        for (int i = 0; i < garage.size(); i++) {
            System.out.println((i+1) + ". " + garage.get(i).getStats());
        }
        System.out.print("Enter choice: ");
        Car playerCar = garage.get(scanner.nextInt() - 1);

        // Generate opponent car
        int opponentPower = 400 + (careerLevel * 120);
        Car opponentCar = new Car(opponent + "'s Ride", opponentPower, 75 + careerLevel*5, 
                                65 + careerLevel*5, 1750 - careerLevel*50, 0);

        System.out.println("\n" + opponent + "'s Car: " + opponentCar.getStats());
        System.out.print("\nReady to race? (Enter to continue)");
        scanner.nextLine(); scanner.nextLine();

        System.out.println("\n3...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("GO! 🚦");

        double playerScore = playerCar.getPerformanceScore(weather, track);
        double opponentScore = opponentCar.getPerformanceScore(weather, track);

        System.out.println("\nRace Results:");
        System.out.printf("%s: %.2f\n", playerCar.getName(), playerScore);
        System.out.printf("%s: %.2f\n", opponentCar.getName(), opponentScore);

        if (playerScore > opponentScore) {
            int prize = 10000 * careerLevel;
            playerBalance += prize;
            careerWins++;
            playerCar.increaseHeat();
            
            System.out.println("\n🎉 YOU WON! Prize: $" + prize);
            System.out.println("Heat Level +1 (" + playerCar.getHeatLevel() + "/5)");
            
            if (careerWins >= 3) {
                careerLevel++;
                careerWins = 0;
                System.out.println("\n⭐ LEVEL UP! Now at Level " + careerLevel);
                if (careerLevel == 2) {
                    policeUnlocked = true;
                    System.out.println("🚔 POLICE PURSUIT MODE UNLOCKED!");
                }
            }
        } else {
            System.out.println("\n😢 You lost to " + opponent + ". Train harder!");
        }
    }

    private static void manageGarage() {
        System.out.println("\n═══════════════ YOUR GARAGE ═══════════════");
        if (garage.isEmpty()) {
            System.out.println("No cars in garage!");
            return;
        }

        for (int i = 0; i < garage.size(); i++) {
            System.out.println((i+1) + ". " + garage.get(i).getStats());
        }
        
        System.out.print("\nSelect car (0 to exit): ");
        int choice = scanner.nextInt() - 1;
        if (choice < 0 || choice >= garage.size()) return;
        
        Car car = garage.get(choice);
        System.out.println("\n1. Upgrade ($" + car.getUpgradeCost() + ")");
        System.out.println("2. Reset Mods");
        System.out.println("3. Back");
        System.out.print("Choose: ");
        
        switch (scanner.nextInt()) {
            case 1 -> {
                if (playerBalance >= car.getUpgradeCost()) {
                    car.upgrade();
                    playerBalance -= car.getUpgradeCost();
                    System.out.println("Upgrade complete!\n" + car.getStats());
                } else {
                    System.out.println("Insufficient funds!");
                }
            }
            case 2 -> {
                car.reset();
                System.out.println("Car reset to base specs\n" + car.getStats());
            }
            default -> {}
        }
    }

    private static void blackMarket(List<Car> carShop) {
        System.out.println("\n═══════════════ BLACK MARKET ═══════════════");
        for (int i = 0; i < carShop.size(); i++) {
            Car car = carShop.get(i);
            System.out.printf("%d. %-20s $%-8d %s\n", 
                (i+1), car.getName(), car.getPrice(), 
                car.isOwned() ? "(Owned)" : "");
        }
        
        System.out.print("\nSelect car to buy (0 to exit): ");
        int choice = scanner.nextInt() - 1;
        if (choice < 0 || choice >= carShop.size()) return;
        
        Car car = carShop.get(choice);
        if (car.isOwned()) {
            System.out.println("You already own this car!");
            return;
        }

        if (playerBalance >= car.getPrice()) {
            playerBalance -= car.getPrice();
            Car newCar = new Car(car.getName(), car.getHorsepower(), 
                                car.getHorsepower(), car.getHorsepower(), 
                                car.getHorsepower(), car.getPrice());
            newCar.setOwned(true);
            garage.add(newCar);
            System.out.println("PURCHASED: " + car.getName());
        } else {
            System.out.println("Not enough cash!");
        }
    }

    private static void chopShop() {
        System.out.println("\n═══════════════ CHOP SHOP ═══════════════");
        System.out.println("Reduce your heat level for a price");
        
        if (garage.isEmpty()) {
            System.out.println("No cars to modify!");
            return;
        }

        System.out.println("\nYour Cars:");
        for (int i = 0; i < garage.size(); i++) {
            Car car = garage.get(i);
            if (car.getHeatLevel() > 0) {
                System.out.printf("%d. %s (Heat: %d/5)\n", 
                    (i+1), car.getName(), car.getHeatLevel());
            }
        }

        System.out.print("\nSelect car (0 to exit): ");
        int choice = scanner.nextInt() - 1;
        if (choice < 0 || choice >= garage.size()) return;
        
        Car car = garage.get(choice);
        if (car.getHeatLevel() == 0) {
            System.out.println("This car has no heat!");
            return;
        }

        int cost = 2000 * car.getHeatLevel();
        System.out.printf("\nReduce heat from %d to %d for $%d?\n", 
                         car.getHeatLevel(), car.getHeatLevel()-1, cost);
        System.out.print("1. Yes\n2. No\nChoose: ");
        
        if (scanner.nextInt() == 1 && playerBalance >= cost) {
            playerBalance -= cost;
            car.reduceHeat();
            System.out.println("New heat level: " + car.getHeatLevel());
        } else {
            System.out.println("Transaction cancelled");
        }
    }
    private static void bankMenu() {
        System.out.println("\n═══════════════ LOAN SHARK ═══════════════");
        System.out.println("1. Quick Cash ($50,000) - 50% interest");
        System.out.println("2. Big Score ($250,000) - 75% interest");
        System.out.println("3. Millionaire ($1,000,000) - 100% interest");
        System.out.println("4. Repay Debt");
        System.out.println("5. Back to Menu");
        System.out.print("Choose: ");
        
        switch (scanner.nextInt()) {
            case 1 -> takeLoan(50000, 0.5);
            case 2 -> takeLoan(250000, 0.75);
            case 3 -> takeLoan(1000000, 1.0);
            case 4 -> repayDebt();
            default -> {}
        }
    }

    private static void takeLoan(int amount, double interest) {
        if (debt > 0) {
            System.out.println("\n❌ You already have outstanding debt!");
            return;
        }
        
        System.out.printf("\n⚠️ WARNING: This loan has %.0f%% interest!\n", interest*100);
        System.out.println("1. Take the $" + amount + " loan");
        System.out.println("2. Cancel");
        System.out.print("Choose: ");
        
        if (scanner.nextInt() == 1) {
            debt = amount + (int)(amount * interest);
            playerBalance += amount;
            System.out.printf("\n💸 Loan taken! You owe: $%,d\n", debt);
            System.out.println("The collectors will come for you if you don't pay...");
        }
    }

    private static void repayDebt() {
        if (debt == 0) {
            System.out.println("\nYou don't owe anything... yet");
            return;
        }
        
        System.out.printf("\nCurrent debt: $%,d\n", debt);
        System.out.println("Your balance: $" + playerBalance);
        System.out.println("1. Pay All");
        System.out.println("2. Custom Amount");
        System.out.println("3. Back");
        System.out.print("Choose: ");
        
        int choice = scanner.nextInt();
        if (choice == 1) {
            if (playerBalance >= debt) {
                playerBalance -= debt;
                debt = 0;
                System.out.println("\n✅ Debt cleared!");
            } else {
                System.out.println("\n❌ Not enough cash!");
            }
        } else if (choice == 2) {
            System.out.print("\nEnter amount: $");
            int payment = scanner.nextInt();
            if (payment > playerBalance) {
                System.out.println("❌ You don't have that much!");
            } else if (payment > debt) {
                System.out.println("❌ You're paying too much!");
            } else {
                debt -= payment;
                playerBalance -= payment;
                System.out.printf("\n✅ Paid $%,d | Remaining: $%,d\n", payment, debt);
            }
        }
    }
}