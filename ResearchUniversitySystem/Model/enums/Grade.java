package enums;

/*
 * @author eva
 */

public enum Grade {
    // using KBTU grading system:
    A("A", 4.0),
    A_MINUS("A-", 3.67),
    B_PLUS("B+", 3.33),
    B("B", 3.0),
    B_MINUS("B-", 2.67),
    C_PLUS("C+", 2.33),
    C("C", 2.0),
    C_MINUS("C-", 1.67),
    D("D", 1.0),
    F("F", 0.0),
    FX("FX", 0.0);  

    private final String letter;
    private final double numericValue;

    Grade(String letter, double numericValue) {
        this.letter = letter;
        this.numericValue = numericValue;
    }

    public String getLetter() {
        return letter;
    }

    public double getNumericValue() {
        return numericValue;
    }

    // determining the grade based on points:
    public static Grade fromPoints(double points) {
        if (points >= 95) return A;
        if (points >= 90) return A_MINUS;
        if (points >= 85) return B_PLUS;
        if (points >= 80) return B;
        if (points >= 75) return B_MINUS;
        if (points >= 70) return C_PLUS;
        if (points >= 65) return C;
        if (points >= 60) return C_MINUS;
        if (points >= 50) return D;
        return F;
    }
}
