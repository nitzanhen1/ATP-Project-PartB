package algorithms.search;


public interface ISearchingAlgorithm {

    Solution solve (ISearchable domain);
    int getNumberOfNodesEvaluated();
    String getName();
    long measureAlgorithmTimeMillis(ISearchable domain);

}
