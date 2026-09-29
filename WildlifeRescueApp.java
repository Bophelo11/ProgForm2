import java.util.ArrayList;
import java.util.Scanner;

public class WildlifeRescueApp {
    private static ArrayList<RescueCase> rescueCases = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    createRescueCase();
                    break;
                case "2":
                    searchRescueCase();
                    break;
                case "3":
                    updateRescueStatus();
                    break;
                case "4":
                    displayAllCases();
                    break;
                case "5":
                    generateReport();
                    break;
                case "6":
                    exit = true;
                    System.out.println("Exiting System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n-------------------------------------------");
        System.out.println("WILDLIFE RESCUE OPERATIONS SYSTEM");
        System.out.println("-------------------------------------------");
        System.out.println("1. Create Rescue Case");
        System.out.println("2. Search Rescue Case");
        System.out.println("3. Update Rescue Status");
        System.out.println("4. Display All Rescue Cases");
        System.out.println("5. Rescue Report");
        System.out.println("6. Exit");
        System.out.print("Select an option: ");
    }

    public static boolean isCaseIdUnique(String caseId) {
        for (RescueCase rc : rescueCases) {
            if (rc.getCaseId().equalsIgnoreCase(caseId)) {
                return false;
            }
        }
        return true;
    }

    public static RescueCase findCaseById(String caseId) {
        for (RescueCase rc : rescueCases) {
            if (rc.getCaseId().equalsIgnoreCase(caseId)) {
                return rc;
            }
        }
        return null;
    }

    private static void createRescueCase() {
        System.out.println("\n--- Create New Rescue Case ---");

        String caseId = getNonEmptyInput("Enter Rescue Case ID: ");
        if (!isCaseIdUnique(caseId)) {
            System.out.println("Error: Rescue Case ID must be unique. ID already exists.");
            return;
        }

        String animalName = getNonEmptyInput("Enter Animal Name: ");
        String species = getNonEmptyInput("Enter Species: ");
        String rescueLocation = getNonEmptyInput("Enter Rescue Location: ");
        String assignedRanger = getNonEmptyInput("Enter Assigned Ranger: ");
        int days = getPositiveIntInput("Enter Number of Rescue Days: ");
        double dailyCost = getPositiveDoubleInput("Enter Daily Care Cost (R): ");

        System.out.println("\nSelect Rescue Case Type:");
        System.out.println("1. Injured Animal Rescue");
        System.out.println("2. Orphaned Animal Rescue");
        System.out.println("3. Endangered Species Rescue");
        System.out.print("Option: ");
        String typeChoice = scanner.nextLine().trim();

        RescueCase newCase = null;

        switch (typeChoice) {
            case "1":
                String injury = getNonEmptyInput("Enter Injury Description: ");
                double vetCost = getPositiveDoubleInput("Enter Veterinary Treatment Cost (R): ");
                boolean surgery = getBooleanInput("Is Surgery Required? (yes/no): ");
                newCase = new InjuredAnimalRescue(caseId, animalName, species, rescueLocation, assignedRanger, days, dailyCost, injury, vetCost, surgery);
                break;
            case "2":
                int age = getPositiveIntInput("Enter Estimated Age in Months: ");
                double feedCost = getPositiveDoubleInput("Enter Feeding Cost (R): ");
                boolean foster = getBooleanInput("Is Foster Care Required? (yes/no): ");
                newCase = new OrphanedAnimalRescue(caseId, animalName, species, rescueLocation, assignedRanger, days, dailyCost, age, feedCost, foster);
                break;
            case "3":
                String classification = getNonEmptyInput("Enter Conservation Classification: ");
                double secCost = getPositiveDoubleInput("Enter Security Cost (R): ");
                boolean specialist = getBooleanInput("Is Specialist Team Required? (yes/no): ");
                newCase = new EndangeredSpeciesRescue(caseId, animalName, species, rescueLocation, assignedRanger, days, dailyCost, classification, secCost, specialist);
                break;
            default:
                System.out.println("Invalid type selection. Operation cancelled.");
                return;
        }

        rescueCases.add(newCase);
        System.out.println("Rescue Case created successfully!");
    }

    private static void searchRescueCase() {
        System.out.print("\nEnter Rescue Case ID to search: ");
        String id = scanner.nextLine().trim();
        RescueCase rc = findCaseById(id);
        if (rc != null) {
            System.out.println("\nCase Found:");
            System.out.println(rc.generateRescueSummary());
            System.out.println("Details: " + rc.getSpecificDetails());
        } else {
            System.out.println("Rescue Case not found.");
        }
    }

    private static void updateRescueStatus() {
        System.out.print("\nEnter Rescue Case ID to update: ");
        String id = scanner.nextLine().trim();
        RescueCase rc = findCaseById(id);
        if (rc != null) {
            System.out.println("Current Status: " + rc.getCurrentRescueStatus());
            System.out.println("Select Action:");
            System.out.println("1. Start Rescue Operation");
            System.out.println("2. Complete Rescue Operation");
            System.out.println("3. Custom Status Update");
            System.out.print("Option: ");
            String opt = scanner.nextLine().trim();
            if ("1".equals(opt)) {
                rc.startRescueOperation();
                System.out.println("Status updated to: " + rc.getCurrentRescueStatus());
            } else if ("2".equals(opt)) {
                rc.completeRescueOperation();
                System.out.println("Status updated to: " + rc.getCurrentRescueStatus());
            } else if ("3".equals(opt)) {
                String newStatus = getNonEmptyInput("Enter new status: ");
                rc.setCurrentRescueStatus(newStatus);
                System.out.println("Status updated successfully.");
            } else {
                System.out.println("Invalid option.");
            }
        } else {
            System.out.println("Rescue Case not found.");
        }
    }

    private static void displayAllCases() {
        if (rescueCases.isEmpty()) {
            System.out.println("\nNo rescue cases recorded.");
            return;
        }
        System.out.println("\n===========================================");
        System.out.println("ALL RESCUE CASES");
        System.out.println("===========================================");
        for (RescueCase rc : rescueCases) {
            System.out.println(rc.generateRescueSummary());
            System.out.println("-------------------------------------------");
        }
    }

    private static void generateReport() {
        System.out.println("\n===========================================");
        System.out.println("WILDLIFE RESCUE REPORT");
        System.out.println("===========================================");

        double totalCostSum = 0.0;
        for (RescueCase rc : rescueCases) {
            System.out.println("Case ID       : " + rc.getCaseId());
            System.out.println("Type          : " + rc.getRescueType());
            System.out.println("Species       : " + rc.getSpecies());
            System.out.println("Location      : " + rc.getRescueLocation());
            System.out.println("Assigned Ranger: " + rc.getAssignedRanger());
            System.out.println("Priority      : " + rc.determineRescuePriority());
            System.out.println("Status        : " + rc.getCurrentRescueStatus());
            double cost = rc.calculateTotalRescueCost();
            System.out.println("Total Cost    : R" + String.format("%.2f", cost));
            System.out.println("-------------------------------------------");
            totalCostSum += cost;
        }

        System.out.println("Total Rescue Cases : " + rescueCases.size());
        System.out.println("Total Estimated Rescue Cost : R" + String.format("%.2f", totalCostSum));
        System.out.println("===========================================");
    }

    // Input Validation Helpers
    private static String getNonEmptyInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be blank. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static int getPositiveIntInput(String prompt) {
        int val = -1;
        while (val <= 0) {
            System.out.print(prompt);
            try {
                val = Integer.parseInt(scanner.nextLine().trim());
                if (val <= 0) {
                    System.out.println("Value must be greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer format. Try again.");
            }
        }
        return val;
    }

    private static double getPositiveDoubleInput(String prompt) {
        double val = -1;
        while (val <= 0) {
            System.out.print(prompt);
            try {
                val = Double.parseDouble(scanner.nextLine().trim());
                if (val <= 0) {
                    System.out.println("Value must be greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric format. Try again.");
            }
        }
        return val;
    }

    private static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y") || input.equals("true")) {
                return true;
            } else if (input.equals("no") || input.equals("n") || input.equals("false")) {
                return false;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }
    }
}