public class Path {
    private int bestLength; // 最佳长度
    private int[] bestTour; // 最佳路径

    /**
     * 设置最佳长度
     */
    public void setBestLength(int inBestLength) {
        bestLength = inBestLength;
    }

    /**
     * 输出最佳长度
     * @return
     */
    public int getBestLength() {
        return bestLength;
    }

    /**
     * 初始最佳路径数组
     */
    public void initBestTour(int cityNum) {
        bestTour = new int[cityNum + 1];
    }

    /**
     * 设置最佳路径
     */
    public void setBestTour(int index, int value) {
        bestTour[index] = value;
    }

    /**
     * 输出最佳路径
     * @return
     */
    public int[] getBestTour() {
        return bestTour;
    }
}
