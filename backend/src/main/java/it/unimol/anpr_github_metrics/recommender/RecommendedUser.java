package it.unimol.anpr_github_metrics.recommender;

import it.unimol.anpr_github_metrics.beans.User;

/**
 * This class create an object to store the info of a recommended user
 * @author Code Warrior Team
 */
public class RecommendedUser implements Comparable {
    private User user;
    private double coverage, weight;

    public RecommendedUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getCoverage() {
        return coverage;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Updates the user's coverage of an issue, meant as the number of changes in the issue over the total changes
     * that all the users worked on it made on the issue
     * @param amount the amount of changes made
     */
    public void updateCoverage(double amount){
        this.coverage += Math.abs(amount);
    }

    /**
     * Updates the user's issues weight give by the sum of the similarities between the issues on which the user worked
     * and a give open issue
     * @param amount the amount to add to the weight
     */
    public void updateWeight(double amount){
        this.weight += Math.abs(amount);
    }

    /**
     * This method returns the weighted coverage of the user on the issues he worked on.
     * @return a double between 0 (inclusive) and 1 (inclusive)
     */
    public double getWeightedCoverage(){
        return this.coverage / this.weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendedUser that = (RecommendedUser) o;

        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if(this.getWeightedCoverage() < ((RecommendedUser) o).getWeightedCoverage()){
            return -1;
        }else if(this.getWeightedCoverage() == ((RecommendedUser) o).getWeightedCoverage()){
            return 0;
        }else{
            return 1;
        }
    }
}
