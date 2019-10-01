/**
 * @author 憨豆酒 YinDou
 * @date 20190930
 * @descripe 节点城市类
 */
public class Node {
    private int id; //节点标号
    private double[] distance; // 距离矩阵
    private double[] pheromone; // 信息素矩阵

    public Node(int id, double[] distance, double[] pheromone) {
        this.id = id;
        this.distance = distance;
        this.pheromone = pheromone;
    }

    public double[] getDistance() {
        return distance;
    }

    public double[] getPheromone() {
        return pheromone;
    }
}
