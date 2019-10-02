% @author 憨豆酒 YinDou yindou97@163.com
% @date 20191002
% @description 相关和傅里叶变换 从图像中读取图案
clc;
clear all;
close all;
% 读取logo图像
I = imread('./images/logo.png'); 
I = rgb2gray(double(I));
[ni,mi]=size(I);
figure(1);imshow(I,'InitialMagnification','fit'); title('Original Image');
% 读取字母图像
J = imread('./images/pattern.png'); 
J = rgb2gray(double(J));
[nj,mj]=size(J);
figure(2); imshow(J,'InitialMagnification','fit'); title('Image');

% 将两幅图像规定到同一大小，填充边缘为黑色
I(:,mi:mi+mj)=0;
I(ni:ni+nj,:)=0;
figure(3);imshow(I,'InitialMagnification','fit'); title('Original Image');
J(:,mj:mi)=0;
J(nj:ni,:)=0;
J(:,mi:mi+mj)=0;
J(ni:ni+nj,:)=0;
figure(4); imshow(J,'InitialMagnification','fit'); title('Image');
[m,n]=size(I);

% 寻找字母位置
% 对I和J分别执行傅里叶变换
FI=fft2(double(I*255));
FJ=fft2(double(J*255));
% 对傅里叶J进行相关操作
CFJ=conj(FJ);
% 将傅里叶I和相关后的傅里叶J相乘
F=FI.*CFJ;
% 计算F的逆傅里叶变换
R=ifft2(F);
% 规则化R图像，使小于0的等于0
for i=1:ni
    for j=1:mi
        if(R(i,j)<0) 
            R(i,j)=0;
        end
    end
end
% 规则化矩阵R，让矩阵R的每一个值除以最大值
% max(max(R))为矩阵R的最大值
% 第一个max计算每一列最大值，第二个max计算每一列最大值中的最大值
R=R/max(max(R));

% 寻找最大的概率点，并记录
for i=1:m
    for j=1:n
        if(R(i,j)>0.95) 
            kx=i;ky=j;break;
        end
    end
end
% 绘制统计图
figure(6); plot(R(kx,:)),grid;
figure(7); plot(R(:,ky)),grid;

% 显示I中的字母位置
figure(10);imshow(I(kx:kx+nj,ky:ky+mj));
title('Search Image result');


