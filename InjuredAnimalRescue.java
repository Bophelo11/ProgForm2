public class InjuredAnimalRescue extends RescueCase {
    private String injuryDescription;
    private double veterinaryTreatmentCost;
    private boolean surgeryRequired;

    public InjuredAnimalRescue(String caseId, String animalName, String species, String rescueLocation,
                               String assignedRanger, int numberOfRescueDays, double dailyCareCost,
                               String injuryDescription, double veterinaryTreatmentCost, boolean surgeryRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, numberOfRescueDays, dailyCareCost);
        this.injuryDescription = injuryDescription;
        this.veterinaryTreatmentCost = veterinaryTreatmentCost;
        this.surgeryRequired = surgeryRequired;
    }

    @Override
    public double calculateTotalRescueCost() {
        double baseCost = (getNumberOfRescueDays() * getDailyCareCost()) + veterinaryTreatmentCost;
        if (surgeryRequired) {
            baseCost += 5000.00;
        }
        return baseCost;
    }

    @Override
    public String determineRescuePriority() {
        if (surgeryRequired) {
            return "Critical";
        }
        return "High";
    }

    @Override
    public String getSpecificDetails() {
        return "Injury: " + injuryDescription + ", Surgery Required: " + (surgeryRequired ? "Yes" : "No");
    }

    @Override
    public String getRescueType() {
        return "Injured Animal Rescue";
    }
}