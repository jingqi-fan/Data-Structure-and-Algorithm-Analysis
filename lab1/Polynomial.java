package lab1;

import java.util.Iterator;

public class Polynomial {
    private LinkedList<Term> terms;

    public Polynomial() {
        terms = new LinkedList<>();
    }

    public void addTerm(int coefficient, int exponent) {
        Term newTerm = new Term(coefficient, exponent);
        terms.addToBack(newTerm);
    }

    public Polynomial sum(Polynomial other) {
        Polynomial result = new Polynomial();

        Iterator<Term> thisIterator = terms.iterator();
        Iterator<Term> otherIterator = other.terms.iterator();

        Term thisTerm = thisIterator.hasNext() ? thisIterator.next() : null;
        Term otherTerm = otherIterator.hasNext() ? otherIterator.next() : null;

        while (thisTerm != null || otherTerm != null) {
            if (thisTerm == null) {
                result.addTerm(otherTerm.getCoefficient(), otherTerm.getExponent());
                otherTerm = otherIterator.hasNext() ? otherIterator.next() : null;
            } else if (otherTerm == null) {
                result.addTerm(thisTerm.getCoefficient(), thisTerm.getExponent());
                thisTerm = thisIterator.hasNext() ? thisIterator.next() : null;
            } else {
                int thisExponent = thisTerm.getExponent();
                int otherExponent = otherTerm.getExponent();

                if (thisExponent < otherExponent) {
                    result.addTerm(thisTerm.getCoefficient(), thisTerm.getExponent());
                    thisTerm = thisIterator.hasNext() ? thisIterator.next() : null;
                } else if (thisExponent > otherExponent) {
                    result.addTerm(otherTerm.getCoefficient(), otherTerm.getExponent());
                    otherTerm = otherIterator.hasNext() ? otherIterator.next() : null;
                } else {
                    int sumCoefficient = thisTerm.getCoefficient() + otherTerm.getCoefficient();

                    if (sumCoefficient != 0) {
                        result.addTerm(sumCoefficient, thisExponent);
                    }

                    thisTerm = thisIterator.hasNext() ? thisIterator.next() : null;
                    otherTerm = otherIterator.hasNext() ? otherIterator.next() : null;
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        if (terms.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        Iterator<Term> iterator = terms.iterator();

        while (iterator.hasNext()) {
            Term term = iterator.next();
            int coefficient = term.getCoefficient();
            int exponent = term.getExponent();

            if (coefficient != 0) {
                if (sb.length() > 0) {
                    if (coefficient > 0) {
                        sb.append(" + ");
                    } else {
                        sb.append(" - ");
                        coefficient = -coefficient;
                    }
                } else if (coefficient < 0) {
                    sb.append("-");
                    coefficient = -coefficient;
                }

                if (exponent == 0) {
                    sb.append(coefficient);
                } else if (exponent == 1) {
                    sb.append(coefficient).append("x");
                } else {
                    sb.append(coefficient).append("x^").append(exponent);
                }
            }
        }

        return sb.toString();
    }

    private static class Term {
        private int coefficient;
        private int exponent;

        public Term(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }

        public int getCoefficient() {
            return coefficient;
        }

        public int getExponent() {
            return exponent;
        }
    }
}

