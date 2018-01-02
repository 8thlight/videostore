import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VideoStoreTest {

    private Customer customer;
    private Movie newReleaseMovie1;
    private Movie regularMovie3;
    private Movie newReleaseMovie2;
    private Movie regularMovie1;
    private Movie newChildrensMovie;
    private Movie regularMove2;

    @Before
    public void setUp() {
        customer = new Customer("Fred");
        newReleaseMovie1 = new Movie("The Cell", Movie.NEW_RELEASE);
        newReleaseMovie2 = new Movie("The Tigger Movie", Movie.NEW_RELEASE);
        newChildrensMovie = new Movie("The Tigger Movie", Movie.CHILDRENS);
        regularMovie1 = new Movie("Plan 9 from Outer Space", Movie.REGULAR);
        regularMove2 = new Movie("8 1/2", Movie.REGULAR);
        regularMovie3 = new Movie("Eraserhead", Movie.REGULAR);
    }

    @Test
    public void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(newReleaseMovie1, 3));
        customer.statement();
        assertEquals(9.0, customer.getAmountOwed());
        assertEquals(2, customer.getFrequentRenterPoints());
    }

    @Test
    public void testDualNewReleaseStatement() {
        customer.addRental(new Rental(newReleaseMovie1, 3));
        customer.addRental(new Rental(newReleaseMovie2, 3));
        customer.statement();
        assertEquals(18.0, customer.getAmountOwed());
        assertEquals(4, customer.getFrequentRenterPoints());
    }

    @Test
    public void testSingleChildrensStatement() {
        customer.addRental(new Rental(newChildrensMovie, 3));
        customer.statement();
        assertEquals(1.5, customer.getAmountOwed());
        assertEquals(1, customer.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(regularMovie1, 1));
        customer.addRental(new Rental(regularMove2, 2));
        customer.addRental(new Rental(regularMovie3, 3));
        customer.statement();
        assertEquals(7.5, customer.getAmountOwed());
        assertEquals(3, customer.getFrequentRenterPoints());
    }
}