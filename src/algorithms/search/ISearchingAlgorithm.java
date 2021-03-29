package algorithms.search;


public interface ISearchingAlgorithm {
    Solution solve (ISearchable domain);
    Solution search (ISearchable domain);
    int getNumberOfNodesEvaluated();
    String getName();
    long measureAlgorithmTimeMillis(ISearchable domain);

}
