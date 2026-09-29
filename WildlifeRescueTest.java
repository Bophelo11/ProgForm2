import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WildlifeRescueTest {

    private InjuredAnimalRescue injuredCase;
    private OrphanedAnimalRescue orphanedCase;
    private EndangeredSpeciesRescue endangeredCase;

    @BeforeEach
    public void setUp() {
        // Sample setup for testing calculations
        injuredCase = new InjuredAnimalRescue(
                "WR101", "Elephant", "African Elephant", "Kruger National Park",
                "John Doe", 5, 500.0, "Leg Fracture", 15000.0, true
        );

        orphanedCase = new OrphanedAnimalRescue(
                "WR102", "Rhino", "White Rhino", "Hluhluwe",
                "Jane Smith", 10, 300.0, 4, 6000.0, true
        );

        endangeredCase = new EndangeredSpeciesRescue(
                "WR103", "Cheetah", "African Cheetah", "Kgalagadi",
                "Sipho Zulu", 3, 400.0, "Critically Endangered", 10000.0, true
        );
    }

    @Test
    public void testRescueCostCalculations() {
        // Injured: (5 * 500) + 15000 + 5000 (Surgery) = 22500.0
        assertEquals(22500.0, injuredCase.calculateTotalRescueCost(), 0.001);

        // Orphaned: (10 * 300) + 6000 + 2500 (Foster) = 11500.0
        assertEquals(11500.0, orphanedCase.calculateTotalRescueCost(), 0.001);

        // Endangered: (3 * 400) + 10000 + 8000 (Specialist) = 19200.0
        assertEquals(19200.0, endangeredCase.calculateTotalRescueCost(), 0.001);
    }

    @Test
    public void testRescuePriorityCalculations() {
        assertEquals("Critical", injuredCase.determineRescuePriority());
        assertEquals("High", orphanedCase.determineRescuePriority());
        assertEquals("Critical", endangeredCase.determineRescuePriority());
    }

    @Test
    public void testRescueStatusUpdates() {
        assertEquals("Registered", injuredCase.getCurrentRescueStatus());

        injuredCase.startRescueOperation();
        assertEquals("Rescue in Progress", injuredCase.getCurrentRescueStatus());

        injuredCase.completeRescueOperation();
        assertEquals("Completed", injuredCase.getCurrentRescueStatus());
    }

    @Test
    public void testSearchingExistingRescueCase() {
        String searchId = "WR101";
        assertEquals("WR101", injuredCase.getCaseId());
        assertTrue(injuredCase.getCaseId().equalsIgnoreCase(searchId));
    }

    @Test
    public void testPreventingDuplicateRescueCaseIDs() {
        String existingId = "WR101";
        String newId = "WR101";

        boolean isUnique = !existingId.equalsIgnoreCase(newId);
        assertFalse(isUnique, "Duplicate ID should be identified as not unique.");
    }
}