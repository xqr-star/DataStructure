import numpy as np
from sklearn.linear_model import LinearRegression
#%% 当噪声较小时
rng=np.random.RandomState(10) #伪随机数生成器
x=100*rng.rand(50,4) #从生成的随机数中生成一个50行 4列   [0,1]均匀分布的随机数序列
x1=x[:,0] #表示取出一个特征值 
x1.shape=50,1 #增加一个维度 变成50行1列的数组
x2=x[:,1]
x2.shape=50,1
x3=x[:,2]
x3.shape=50,1
x4=x[:,3]
x4.shape=50,1
y=1.25*x1+2.5*x2+3.5*x3+4.5*x4-20+ 5 * rng.randn(50, 1) #生成标准正态分布的伪随机数 50行1列的伪随机数 
model=LinearRegression(fit_intercept=True)  #用最小二乘法  #fit_intercept=True 由截距
model.fit(x,y) #得到模型 x,y的每一行是一个样本，即要求是列向量  fit函数用于拟合数据的输入输出
a=np.linspace(0,50,1000)  #从0到50创建1000个等差数列
x1_fit=a[:,np.newaxis]   #将a转置成列 
x2_fit=a[:,np.newaxis]
x3_fit=a[:,np.newaxis]
x4_fit=a[:,np.newaxis]
x_fit=np.hstack((x1_fit,x2_fit,x3_fit,x4_fit)) #数组拼接，在水平方向上平铺
y_fit=model.predict(x_fit)
print("Model slope: ", model.coef_[0])#系数
print("Model intercept:", model.intercept_) #截距
print('方程的判定系数(R^2): %.6f' % model.score(x, y)) #x是test集中的属性，y是test集中的标签
#(R^2)= 1-（SSR/SST)，越接近1表示拟合能力越强    
#ssr = ((ytest - ytrain.mean())**2).sum() #求出预测数据与原始数据均值之差的平方和
#sst = ((ytrain - ytrain.mean())**2).sum()#求出原始数据和均值之差的平方和



#%% 当噪声较大时
rng=np.random.RandomState(10)
x=100*rng.rand(50,4)
x1=x[:,0]
x1.shape=50,1
x2=x[:,1]
x2.shape=50,1
x3=x[:,2]
x3.shape=50,1
x4=x[:,3]
x4.shape=50,1
y=1.25*x1+2.5*x2+3.5*x3+4.5*x4-20+ 100 * rng.randn(50, 1) 
model=LinearRegression(fit_intercept=True)
model.fit(x,y) 
a=np.linspace(0,50,1000)
x1_fit=a[:,np.newaxis]
x2_fit=a[:,np.newaxis]
x3_fit=a[:,np.newaxis]
x4_fit=a[:,np.newaxis]
x_fit=np.hstack((x1_fit,x2_fit,x3_fit,x4_fit))
y_fit=model.predict(x_fit)
print("Model slope: ", model.coef_[0])
print("Model intercept:", model.intercept_)
print('方程的判定系数(R^2): %.6f' % model.score(x, y))
