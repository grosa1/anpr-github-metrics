package it.unimol.anpr_github_metrics.github.proxies;

import com.jcabi.github.Issue;
import com.jcabi.github.Issues;
import com.jcabi.github.Repo;
import com.jcabi.github.Search;
import it.unimol.anpr_github_metrics.utils.DateUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author Simone Scalabrino.
 */
public class IssuesProxy implements Issues, Proxy<Issues> {
    private final RepoProxy repo;
    private final Issues origin;

    private final Map<Integer, Issue> issueCache;
    private final Map<Map<String, String>, Set<Issue>> iterableCache;
    private final Map<Map<String, String>, Date> iterableCacheLastUpdate;

    public IssuesProxy(RepoProxy repo, Issues origin) {
        this.repo = repo;
        this.origin = origin;
        this.issueCache = new HashMap<>();
        this.iterableCache = new HashMap<>();
        this.iterableCacheLastUpdate = new HashMap<>();
    }

    @Override
    public Repo repo() {
        return this.repo;
    }

    @Override
    public Issue get(int i) {
        if (!this.issueCache.containsKey(i))
            this.issueCache.put(i, new IssueProxy(this.repo, this.origin.get(i)));

        return this.issueCache.get(i);
    }

    @Override
    public Issue create(String s, String s1) throws IOException {
        return this.origin.create(s, s1);
    }

    @Override
    public Iterable<Issue> iterate(Map<String, String> map) {
        // If the "since" parameter is used, just fall back to the normal implementation, because
        // we can't handle it at the moment.
        if (map.containsKey("since"))
            return this.origin.iterate(map);

        if (!this.iterableCache.containsKey(map)) {
            createIterableCache(map);
        } else {
            updateIterableCache(map);
        }

        return this.iterableCache.get(map);
    }

    @Override
    public Iterable<Issue> search(Sort sort, Search.Order order, EnumMap<Qualifier, String> enumMap) throws IOException {
        return this.origin.search(sort, order, enumMap);
    }

    @Override
    public Issues getOrigin() {
        return this.origin;
    }

    private void createIterableCache(Map<String, String> map) {
        Set<Issue> matching = new HashSet<>();

        for (Issue issue : this.origin.iterate(map)) {
            saveIssueInCache(issue, matching);
        }

        this.iterableCache.put(map, matching);
        this.iterableCacheLastUpdate.put(map, new Date());
    }

    private void updateIterableCache(Map<String, String> map) {
        Set<Issue> matching = this.iterableCache.get(map);

        Map<String, String> newRequest = new HashMap<>(map);
        String sinceString = DateUtils.GITHUB_DATE.format(this.iterableCacheLastUpdate.get(map));
        newRequest.put("since", sinceString);

        for (Issue issue : this.origin.iterate(newRequest)) {
            saveIssueInCache(issue, matching);
        }

        this.iterableCacheLastUpdate.put(map, new Date());
    }

    private void saveIssueInCache(Issue issue, Set<Issue> matching) {
        Issue issueProxy = new IssueProxy(this.repo, issue);
        this.issueCache.put(issueProxy.number(), issueProxy);
        matching.add(issueProxy);
    }
}
