public class OrphanedAnimalRescue extends RescueCase {
    private int estimatedAgeMonths;
    private double feedingCost;
    private boolean fosterCareRequired;

    public OrphanedAnimalRescue(String caseId, String animalName, String species, String rescueLocation,
                                String assignedRanger, int numberOfRescueDays, double dailyCareCost,
                                int estimatedAgeMonths, double feedingCost, boolean fosterCareRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, numberOfRescueDays, dailyCareCost);
        this.estimatedAgeMonths = estimatedAgeMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    @Override
    public double calculateTotalRescueCost() {
        double baseCost = (getNumberOfRescueDays() * getDailyCareCost()) + feedingCost;
        if (fosterCareRequired) {
            baseCost += 2500.00;
        }
        return baseCost;
    }

    @Override
    public String determineRescuePriority() {
        if (estimatedAgeMonths < 6) {
            return "High";
        }
        return "Medium";
    }

    @Override
    public String getSpecificDetails() {
        return "Age: " + estimatedAgeMonths + " months, Foster Care Required: " + (fosterCareRequired ? "Yes" : "No");
    }

    @Override
    public String getRescueType() {
        return "Orphaned Animal Rescue";
    }
}