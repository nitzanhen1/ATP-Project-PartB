package algorithms.search;


public interface ISearchingAlgorithm {
    Solution solve (ISearchable domain);
    Solution search (ISearchable domain);
    int getNumberOfNumbersEvaluated();
    String getName();
    long measureAlgorithmTimeMillis(ISearchable domain);

}
