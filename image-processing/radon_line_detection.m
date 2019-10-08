% @author 憨豆酒 YinDou yindou97@163.com
% @date 20191002
% @description radon直线检测 
% 建议先阅读本节博客：https://modestbean.github.io/2019/10/02/image-radon/#more
% 本节实现的为检测一条直线，若想实现检测多条直线，需要计算多次最大值
clc
clear all;
close all;

% 读取原始图像
I = fitsread('solarspectra.fts');
I = mat2gray(I);
figure(1),imshow(I);
figure(2),imshow(I);
BW = edge(I);
[height, width ] = size (I);
width_half = width / 2.0;
height_half = height / 2.0;

% 执行radon函数
theta = 1:180;
[R,xp] = radon(BW,theta);
[M,N]=size(R);
% reshape二维矩阵R，变换成一维向量
J=reshape(R,M*N,1);


% 寻找最大值的索引
% J排序，倒序
sort_J = sort(J, 'descend');
% 取向量J中最大值
% 本节实现的为检测一条直线，若想实现检测多条直线，需要计算第二大值，第三大值，第.....
max_value = sort_J(1);
% 得到在矩阵索引，在数组中的索引
[row,col] = find(R == max_value);

% 将数组索引变换成theta和distance，详情见：
max_line_theta = col;
max_line_distance = row - 129;


% k过图像中心点直线斜率，k2为检测直线斜率
k = tan(max_line_theta*pi/180.0);
k2 = -1.0/k;
% 坐标系原点
x0 = 0.0;
y0 = 0.0;

% 计算两条直线交点
% 定义变量x
syms x 
d = sqrt((k*x-y0)^2+(x-x0)^2);
soln = solve(d == abs(max_line_distance),x);
% radon的峰值在图像左侧，所以判断。 不同的图像注意区分，如若使用此代码记得修改
if soln(1)<=0
    xT = soln(1);
else
    xT = soln(2);
end
yT = k*xT;

% 检测的直线与图像上下的交点，涉及到图像坐标系变换，详情见博客
y1 = 0.0;
x1 = (height_half - y1 - yT)/k2 + xT + width_half;
y2 = 165.0;
x2 = (height_half - y2 - yT)/k2 + xT + width_half;
hold on;
% 绘制直线
line([x1, x2],[y1,y2],'color','r','LineWidth',1); 

% 绘制randon图
figure(3)
imagesc(theta, xp, R); colormap(hot);
xlabel('\theta (degrees)');
ylabel('x^{\prime} (pixels from center)');
title('R_{\theta} (x^{\prime})');
colorbar