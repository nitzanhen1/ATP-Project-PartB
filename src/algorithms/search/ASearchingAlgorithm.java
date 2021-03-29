package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    @Override
    public Solution solve(ISearchable domain) {
        return null;
    }

    @Override
    public Solution search(ISearchable domain) {
        return null;
    }

    @Override
    public int getNumberOfNumbersEvaluated() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public long measureAlgorithmTimeMillis(ISearchable domain) {
        long t1 = System.currentTimeMillis();
        this.search(domain);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }
}
