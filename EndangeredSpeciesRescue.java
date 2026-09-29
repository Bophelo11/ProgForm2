public class EndangeredSpeciesRescue extends RescueCase {
    private String conservationClassification;
    private double securityCost;
    private boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(String caseId, String animalName, String species, String rescueLocation,
                                   String assignedRanger, int numberOfRescueDays, double dailyCareCost,
                                   String conservationClassification, double securityCost, boolean specialistTeamRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, numberOfRescueDays, dailyCareCost);
        this.conservationClassification = conservationClassification;
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    @Override
    public double calculateTotalRescueCost() {
        double baseCost = (getNumberOfRescueDays() * getDailyCareCost()) + securityCost;
        if (specialistTeamRequired) {
            baseCost += 8000.00;
        }
        return baseCost;
    }

    @Override
    public String determineRescuePriority() {
        if ("Critically Endangered".equalsIgnoreCase(conservationClassification) || specialistTeamRequired) {
            return "Critical";
        }
        return "High";
    }

    @Override
    public String getSpecificDetails() {
        return "Classification: " + conservationClassification + ", Specialist Team Required: " + (specialistTeamRequired ? "Yes" : "No");
    }

    @Override
    public String getRescueType() {
        return "Endangered Species Rescue";
    }
}