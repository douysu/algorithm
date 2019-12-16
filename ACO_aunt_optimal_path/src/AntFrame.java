/**
 @author 憨豆酒 YinDou yindou97@163.com https://github.com/ModestBean
 @descripe 提取目标颜色值
 @version 1 191216 修改人：YinDou 添加JFrame窗体
 @version 2 191216 修改人：YinDou 修改线程画线
*/
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;

public class AntFrame extends Frame{
    private int[] x;
    private int[] y;
    private int cityNum;
    private int bestLength; // 最佳长度
    private int[] bestTour; // 最佳路径
    private ArrayList<Integer> bestList;
    int lineNum = 0; // 线的数量

    public AntFrame(int[] x, int[] y, int cityNum, int bestLength, int[] bestTour){
        this.x = x;
        this.y = y;
        this.cityNum = cityNum;
        this.bestLength = bestLength;
        this.bestTour = bestTour;
        bestList = new ArrayList<>();
        initFrame();
        dynamicDrawLines();
    }

    public void initFrame(){
        this.setTitle("Ant Graphic Interface");
        setLocation(300, 300);
        setSize(850, 600);
        setVisible(true);
    }

    public void paint(Graphics g){
        int XBias = 30;
        int YBias = 50;
        if(lineNum == cityNum) {
            g.drawString("Max Length：" + bestLength, 10, 50); // 绘制最大长度
        }
        g.setColor(Color.RED);
        for (int i = 0; i < cityNum; i++) {
            g.fillOval(x[i] / 10 + XBias, y[i] / 10 + YBias, 5, 5); // 绘制点
            g.drawString(String.valueOf(i + 1), x[i] / 10 + 30, y[i] / 10 + YBias); // 绘制数字
        }
        g.setColor(Color.BLUE); // 设置城市之间连线的颜色
        for (int j = 0; j < bestList.size() - 1; j++) {
            g.drawLine(x[bestList.get(j)] / 10 + XBias, y[bestList.get(j)] / 10 + YBias, x[bestList.get(j + 1)] / 10 + XBias, y[bestList.get(j + 1)] / 10 + YBias);
        }
        // 将起始城市及终止城市单独画出
        g.setColor(Color.YELLOW); // 设置起始城市的颜色
        g.fillOval(x[bestTour[0]] / 10 + XBias, y[bestTour[0]] / 10 + YBias, 6, 6);
        g.setColor(Color.GREEN); // 设置终止城市的颜色
        g.fillOval(x[bestTour[cityNum - 1]] / 10 + XBias, y[bestTour[cityNum - 1]] / 10 + YBias, 6, 6);
    }
    public void dynamicDrawLines() {
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                while (lineNum <= cityNum){
                    try{
                        bestList.clear();
                        for (int j = 0; j < lineNum; j++) {
                            bestList.add(bestTour[j]);
                        }
                        repaint();//调用paint方法，画出当前点集合的线
                        Thread.sleep(500);
                        lineNum += 1;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
}
