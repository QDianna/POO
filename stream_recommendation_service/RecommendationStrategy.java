public class RecommendationStrategy {
    private IRecommendationStrategy strategy;

    public void setStrategy(IRecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public void generateRecommendation(int streamType, User user) {
        strategy.recommend(streamType, user);
    }
}
