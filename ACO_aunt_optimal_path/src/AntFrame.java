/**
@author 憨豆酒 YinDou yindou97@163.com https://github.com/ModestBean
@descripe 提取目标颜色值
@version 1 191216 修改人：YinDou 添加JFrame窗体
*/
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class AntFrame extends Frame{
    private int[] x;
    private int[] y;
    private int cityNum;
    private int bestLength; // 最佳长度
    private int[] bestTour; // 最佳路径
    public AntFrame(int[] x, int[] y, int cityNum, int bestLength, int[] bestTour){
        this.x = x;
        this.y = y;
        this.cityNum = cityNum;
        this.bestLength = bestLength;
        this.bestTour = bestTour;
        initFrame();
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
        g.setColor(Color.RED);
        for (int i = 0; i < cityNum; i++) {
            g.fillOval(x[i] / 10 + XBias, y[i] / 10 + YBias, 5, 5); // 绘制点
            g.drawString(String.valueOf(i + 1), x[i] / 10 + 30, y[i] / 10 + YBias); // 绘制数字
        }
        g.setColor(Color.BLUE); // 设置城市之间连线的颜色
        for (int j = 0; j < cityNum - 1; j++) {
            g.drawLine(x[bestTour[j]] / 10 + XBias, y[bestTour[j]] / 10 + YBias, x[bestTour[j + 1]] / 10 + XBias, y[bestTour[j + 1]] / 10 + YBias);
        }

        // 将起始城市及终止城市单独画出
        g.setColor(Color.YELLOW); // 设置起始城市的颜色
        g.fillOval(x[bestTour[0]] / 10 + XBias, y[bestTour[0]] / 10 + YBias, 6, 6);
        g.setColor(Color.GREEN); // 设置终止城市的颜色
        g.fillOval(x[bestTour[cityNum - 1]] / 10 + XBias, y[bestTour[cityNum - 1]] / 10 + YBias, 6, 6);
    }
    
}
