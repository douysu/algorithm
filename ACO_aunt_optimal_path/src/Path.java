public class Path {
    private int bestLength; // 最佳长度
    private int[] bestTour; // 最佳路径

    /**
     * 设置最佳长度
     * @return
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
     * 输出最佳路径
     * @return
     */
    public int[] getBestTour() {
        return bestTour;
    }
}
