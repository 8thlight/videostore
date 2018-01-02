import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<Rental>();
    private double amountOwed;
    private int frequentRenterPoints;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        amountOwed = 0;
        frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";

        for (Rental eachMovie : rentals) {
            double thisAmount = 0;

            thisAmount = amountAccruedForRentals(thisAmount, eachMovie);

            frequentRenterPoints++;

            if (eachMovie.getMovie().getPriceCode() == Movie.NEW_RELEASE
                    && eachMovie.getDaysRented() > 1)
                frequentRenterPoints++;

            result += "\t" + eachMovie.getMovie().getTitle() + "\t"
                    + String.valueOf(thisAmount) + "\n";
            amountOwed += thisAmount;

        }

        result += "You owed " + String.valueOf(amountOwed) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";


        return result;
    }

    private double amountAccruedForRentals(double thisAmount, Rental each) {
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public double getAmountOwed() {
        return amountOwed;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }
}
