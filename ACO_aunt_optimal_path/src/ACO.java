/**
 * @author 憨豆酒 YinDou
 * @date 20190930
 * @descripe 主算法类
 * @website https://github.com/ModestBean
 */

import java.io.IOException;
import java.util.ArrayList;

public class ACO {
    private Ant[] ants; // 蚂蚁
    private int cityNum; // 城市数量

    private int[] x; // X坐标矩阵
    private int[] y; // Y坐标矩阵

    private ArrayList<Node> nodeList;// 节点城市列表

    public Path path;

    private int antNum; // 蚂蚁数量
    private int generation; // 迭代次数
    private double alpha; // 信息素重要程度系数
    private double beta; // 城市间距离重要程度系数
    private double rho; // 信息素残留系数
    private int Q; // 蚂蚁循环一周在经过的路径上所释放的信息素总量

    /**
     * 构造方法
     *
     * @param cityNum
     * @param antNum
     * @param generation
     * @param alpha
     * @param beta
     * @param rho
     * @param Q
     */
    public ACO(int cityNum, int antNum, int generation, double alpha, double beta, double rho, int Q) {
        this.cityNum = cityNum;
        this.antNum = antNum;
        this.generation = generation;
        this.alpha = alpha;
        this.beta = beta;
        this.rho = rho;
        this.Q = Q;

        ants = new Ant[antNum];
        path = new Path(cityNum);
        nodeList = new ArrayList<>();
    }

    /**
     * 初始化
     *
     * @param filename
     * @throws IOException
     */
    public void init(String filename) throws IOException {
        // 获取城市X坐标、Y坐标矩阵
        x = ReadFile.getX(cityNum, filename);
        y = ReadFile.getY(cityNum, filename);

        // 初始化距离与信息素矩阵
        for (int i = 0; i < cityNum; i++) {
            double distance[] = new double[cityNum];
            double start = antNum / 10628.0; // 计算初始信息素数值
            double pheromone[] = new double[cityNum];
            distance[i] = 0; // 本身城市距离0
            pheromone[i] = 0;
            for (int j = 0; j < cityNum; j++) {
                if (i != j) {
                    distance[j] = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])) / 10.0);
                    pheromone[j] = start;
                }
            }
            nodeList.add(new Node(i, distance, pheromone));
        }

        // 初始化最佳长度及最佳路径
        path.setBestLength(Integer.MAX_VALUE);
        path.initBestTour(cityNum);

        // 初始化antNum个蚂蚁
        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant(cityNum);
            ants[i].init(nodeList, alpha, beta);
        }
    }

    /**
     * 解决TSP问题
     */
    public void movement() {
        // 迭代generation次
        for (int g = 0; g < generation; g++) {
            // 对antNum只蚂蚁分别进行操作
            for (int currentAnt = 0; currentAnt < antNum; currentAnt++) {
                // 为每只蚂蚁分别选择一条路径，共antNum步
                for (int i = 1; i < cityNum; i++) {
                    ants[currentAnt].selectNextTrack(nodeList.get(i).getPheromone());
                }

                // 把这只蚂蚁起始城市再次加入其禁忌表中，使禁忌表中的城市最终形成一个循环
                ants[currentAnt].getTaboo().add(ants[currentAnt].getFirstCity());

                // 若这只蚂蚁走过所有路径的距离比当前的最佳距离小，则覆盖最佳距离及最佳路径
                if (ants[currentAnt].getTourLength() < path.getBestLength()) {
                    path.setBestLength(ants[currentAnt].getTourLength());
                    for (int k = 0; k < cityNum + 1; k++) {
                        path.setBestTour(k, ants[currentAnt].getTaboo().get(k).intValue());
                    }
                }
                // 更新这只蚂蚁信息素增量delta矩阵
                double[][] delta = ants[currentAnt].getDelta();
                for (int i = 0; i < cityNum; i++) {
                    for (int j : ants[currentAnt].getTaboo()) {
                        delta[i][j] = Q / ants[currentAnt].getTourLength(); // Ant-cycle System
                    }
                }
                ants[currentAnt].setDelta(delta);
            }

            // 更新信息素
            updatePheromone();

            // 重新初始化蚂蚁，走完一轮
            for (int i = 0; i < antNum; i++) {
                ants[i].init(nodeList, alpha, beta);
            }
        }

        // 打印最佳结果
        path.printBestLengthAndTour();
    }

    /**
     * 更新信息素
     */
    private void updatePheromone() {
        // 路径信息素增加
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                nodeList.get(i).getPheromone()[j] = nodeList.get(i).getPheromone()[j] * rho;
            }
        }
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                for (int ant = 0; ant < antNum; ant++) {
                    nodeList.get(i).getPheromone()[j] += ants[ant].getDelta()[i][j];
                }
            }
        }

        // 路径信息素挥发，rho
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                nodeList.get(i).getPheromone()[j] = nodeList.get(i).getPheromone()[j] * (1 - rho);
            }
        }
    }
}
