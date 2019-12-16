/**
 * @author 憨豆酒 YinDou
 * @date 20190930
 * @descripe 路径类，存储最佳长度和路径
 * @website https://github.com/ModestBean
 */
public class Path {
    private int bestLength; // 最佳长度
    private int[] bestTour; // 最佳路径
    private int cityNum; //城市数量

    public Path(int cityNum) {
        this.cityNum = cityNum;
    }

    /**
     * 在控制台中输出最佳长度及最佳路径
     */
    public void printBestLengthAndTour() {
        System.out.println("最佳长度: " + bestLength);
        System.out.print("最佳路径: ");
        for (int i = 0; i < cityNum ; i++) {
            System.out.print(bestTour[i] + 1 + "-");
        }
        System.out.println(bestTour[cityNum] + 1);
    }

    /**
     * 设置最佳长度
     */
    public void setBestLength(int inBestLength) {
        bestLength = inBestLength;
    }

    /**
     * 输出最佳长度
     *
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
     * 得到最佳路径
     */
    public int[] getBestTour() {
        return bestTour;
    }
}
