package algorithms.search;


public interface ISearchingAlgorithm {

    //gets an ISearchable problem, returns a solution
    Solution solve (ISearchable domain);

    int getNumberOfNodesEvaluated();
    String getName();
    long measureAlgorithmTimeMillis(ISearchable domain);

}
