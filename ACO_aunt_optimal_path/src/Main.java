import java.io.IOException;

/**
 * @author 憨豆酒 YinDou
 * @date 20190924
 * @descripe 测试算法运行效果
 * @website https://github.com/ModestBean
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // 读取城市信息
        String tspData = System.getProperty("user.dir") + "/resources/att48.txt";

        // 平均值、最大值、最小值
        double avg = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;

        // 计时器开始
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            // 城市数量, 蚂蚁数量, 迭代次数, Alpha, Beta, Rho, Q, QType
            /*
             * Alpha：信息启发式，Alpha值越大，蚂蚁选择之前走过路径的可能性就越大；值越小，蚁群搜索范围就越小，容易陷入局部最优。
             * Beta：期望启发式因子，Beta越大，蚁群越容易选择局部较短路径，这时算法收敛速度加快，但随机性不高，容易得到局部最优解。
             * M：蚁群数量，M越大，最优解越精确，但会产生重复解，消耗资源，增大时间复杂度。
             * RHO：信息蒸发因子，1-RHO表示残留因子，RHO过小，各路径上残留信息素过多，导致无效路径继续被搜素，影响算法收敛效率；RHO过大，有效路径可能会被放弃搜索，影响最优解的搜索。
             * Q：用于信息素增量的设置，在蚁周模型下，信息素增量=Q/当前解路径总长度。蚁周模型利用的是全局信息，即蚂蚁完成一个循环后更新所有路径上的信息素
             * */
            ACO aco = new ACO(48, 10, 100, 1.0, 10.0, 0.5, 10);
            // 初始化
            aco.init(tspData);
            // 移动，计算概率，选择路径
            aco.movement();
            avg += aco.getBestLength();
            if (aco.getBestLength() > max) {
                max = aco.getBestLength();
            }
            if (aco.getBestLength() < min) {
                min = aco.getBestLength();
            }
        }
        // 计时器结束
        long endTime = System.currentTimeMillis();

        System.out.println("平均值是" + avg / 10.0 + "\t" + "最小值是" + min + "\t" + "最大值是" + max + "\t" + "运行时间是" + (endTime - startTime) / 1000F + "秒");
    }
}
