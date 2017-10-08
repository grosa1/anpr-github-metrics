package it.unimol.anpr_github_metrics.recommender;

import it.unimol.anpr_github_metrics.beans.Commit;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.github.GithubException;
import it.unimol.anpr_github_metrics.github.IssueExtractor;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class implement a Recommender engine for assignees recommendation
 * @author Code Warrior Team
 */
public class AssigneesRecommender {

    private ArrayList<User> users;
    private ArrayList<Issue> fixedIssues;

    /**
     * Instantiate the Assignees Recommender
     * @param users the users to take into account in recommendation
     * @param fixedIssues the list of fixed issues he users to take into account in recommendation tn recommendation
     */
    public AssigneesRecommender(ArrayList<User> users, ArrayList<Issue> fixedIssues) {
        this.users = users;
        fixedIssues.removeIf(issue -> !issue.isClosed());
        this.fixedIssues = fixedIssues;
    }

    /**
     * This method returns a list of recommended users ordered in descend order of ranking
     * @param issue an open issues which user recommendation is needed
     * @return a list of recommended users ordered in descend order of ranking
     * @throws ClosedIssueException if the <i>issue</i> is a closed issue
     * @throws GithubException if an error occurred with GitHub Api
     */
    public ArrayList<RecommendedUser> getRecommendation(Issue issue) throws ClosedIssueException, GithubException {

        if (issue.isClosed()) {
            throw new ClosedIssueException("The issue is closed. Pass an opened issue");
        }

        HashMap<Issue, Double> similarityMap = new HashMap<>();
        HashMap<Issue, ArrayList<Commit>> issueCommitMap = new HashMap<>();
        double distance = 0;

        for (Issue fixed : fixedIssues) {
            double titleSimilarity = VSM.computeTextualSimilarity(issue.getTitle(), fixed.getTitle());
            double bodySimilarity = VSM.computeTextualSimilarity(issue.getBody(), fixed.getBody());
            double similarity = (titleSimilarity + bodySimilarity) / 2;   // 0 <= similarity <= 1
            distance += 1 - similarity;

            similarityMap.put(fixed, similarity);
            issueCommitMap.put(fixed, (ArrayList<Commit>) IssueExtractorFactory.getInstance().getCommitsInvolvedInIssue(fixed));
        }

        final double meanDistance = fixedIssues.isEmpty() ? 0 : distance / fixedIssues.size();

        //Filter out all the issues with distance greater then meanDistance
        similarityMap = similarityMap
                .entrySet()
                .stream()
                .filter(p -> (1 - p.getValue()) <= meanDistance)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        ArrayList<RecommendedUser> recommendedUsers = new ArrayList<>();

        // Find user issues's coverage
        Iterator<Map.Entry<Issue, Double>> iterator = similarityMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Issue, Double> entry = iterator.next();
            Issue key = entry.getKey();
            double similarity = entry.getValue();

            ArrayList<Commit> commits = issueCommitMap.get(key);
            HashMap<User, Double> userChanges = new HashMap<>();

            int totalChanges = 0;
            for (Commit commit : commits) {
                double value = commit.getTotalAddedLines() + commit.getTotalRemovedLines();

                if (userChanges.containsKey(commit.getAuthor())) {
                    value += userChanges.get(commit.getAuthor());
                }
                userChanges.put(commit.getAuthor(), value);
                totalChanges += value;
            }

            // Update user's coverage
            for (Map.Entry<User, Double> users : userChanges.entrySet()) {
                User user = users.getKey();

                double weight = similarity;
                double coverage = users.getValue() / totalChanges * weight;

                if (recommendedUsers.contains(user)) {
                    RecommendedUser recommendedUser = recommendedUsers.get(recommendedUsers.indexOf(user));
                    recommendedUser.updateCoverage(coverage);
                    recommendedUser.updateWeight(weight);
                } else {
                    RecommendedUser recommendedUser = new RecommendedUser(user);
                    recommendedUser.updateCoverage(coverage);
                    recommendedUser.updateWeight(weight);
                }
            }
        }

        // Sort recommended list from desc order of ranking
        Collections.reverse(recommendedUsers);
        return recommendedUsers;
    }
}
