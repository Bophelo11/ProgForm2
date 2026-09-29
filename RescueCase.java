public abstract class RescueCase implements RescueOperations {
    private String caseId;
    private String animalName;
    private String species;
    private String rescueLocation;
    private String assignedRanger;
    private int numberOfRescueDays;
    private double dailyCareCost;
    private String currentRescueStatus;

    public RescueCase(String caseId, String animalName, String species, String rescueLocation,
                      String assignedRanger, int numberOfRescueDays, double dailyCareCost) {
        this.caseId = caseId;
        this.animalName = animalName;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.numberOfRescueDays = numberOfRescueDays;
        this.dailyCareCost = dailyCareCost;
        this.currentRescueStatus = "Registered";
    }

    public abstract double calculateTotalRescueCost();
    public abstract String determineRescuePriority();
    public abstract String getSpecificDetails();
    public abstract String getRescueType();

    @Override
    public void startRescueOperation() {
        this.currentRescueStatus = "Rescue in Progress";
    }

    @Override
    public void completeRescueOperation() {
        this.currentRescueStatus = "Completed";
    }

    @Override
    public String generateRescueSummary() {
        return "Case ID: " + caseId +
                "\nType: " + getRescueType() +
                "\nSpecies: " + species +
                "\nAssigned Ranger: " + assignedRanger +
                "\nPriority: " + determineRescuePriority() +
                "\nStatus: " + currentRescueStatus +
                "\nTotal Cost: R" + String.format("%.2f", calculateTotalRescueCost());
    }

    // Getters and Setters
    public String getCaseId() { return caseId; }
    public String getAnimalName() { return animalName; }
    public String getSpecies() { return species; }
    public String getRescueLocation() { return rescueLocation; }
    public String getAssignedRanger() { return assignedRanger; }
    public int getNumberOfRescueDays() { return numberOfRescueDays; }
    public double getDailyCareCost() { return dailyCareCost; }
    public String getCurrentRescueStatus() { return currentRescueStatus; }
    public void setCurrentRescueStatus(String status) { this.currentRescueStatus = status; }
}