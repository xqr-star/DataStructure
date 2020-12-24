import numpy as np
import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.cluster import DBSCAN#基于密度
#sklearn.datasets.make_moons(n_samples=100,     shuffle=True,    noise=None, random_state=None)
                              #产生样本点的数量    是否重新洗牌、打乱  噪声          随机种子
X1, y1=datasets.make_moons(n_samples=1000, shuffle=True,noise=0.1,random_state=18050412115%30) #制作月亮型的数据
X2, y2 = datasets.make_blobs(n_samples=1000, n_features=2, centers=[[1.2,1.2]],  #？#样本总数，样本特征数默认是2   centers: 要生成的样本中心（类别）数，默认为3
                             cluster_std=[[0.1]], random_state=17050411103%30)  #每个类别的方差，默认为1  y2 的类别为什么都是0？
X = np.concatenate((X1, X2))  #纵向拼接
plt.figure(figsize=(12, 9))
plt.plot(X[:, 0], X[:, 1],  'o',markersize=6)
plt.show()

#默认参数 默认 半径eps=0.5, min_samples=5  #  MinPts — 给定点在 E 领域内成为核心对象的最小领域点数
db = DBSCAN().fit(X)   
core_samples_mask = np.zeros_like(db.labels_, dtype=bool) #函数主要是想实现构造一个矩阵,其维度与矩阵da.labels_一致，并为其初始化为全false；  
# db.labels_为所有样本的聚类索引，没有聚类索引为-1 
# db.core_sample_indices_# 所有核心样本的索引 
core_samples_mask[db.core_sample_indices_] = True  #将核心样本部分设置为true  
labels = db.labels_  #eps参数设置过大，导致全部聚成一个簇
n_clusters_ = len(set(labels)) - (1 if -1 in labels else 0)  # 获取聚类/簇个数。 除去噪声点

unique_labels = set(labels)  #set 函数去除重复元素 返回的是一个set
colors = [plt.cm.Spectral(each)   #colors是一个list
          for each in np.linspace(0, 1, len(unique_labels))] 
# np.linspace均匀分布的数值序列
#cmap = plt.cm.Spectral实现的功能是给label为1的点一种颜色，给label为0的点另一种颜色。  colors = plt.cm.Spectral(np.arange(5))
#np.linspace(0, 1, len(unique_labels)) #最后的结果取得就是0   ？？？
#这行代码的意思就是生成len(unique_labels)个可以和光谱对应的颜色值

plt.figure(figsize=(12, 9))
for k, col in zip(unique_labels, colors):#可迭代对象有：列表、元组、字典、字符串  于将可迭代的对象作为参数，将对象中对应的元素打包成一个个元组，然后返回由这些元组组成的列表
    class_member_mask = (labels == k) 
    if k == -1:   #被判定的噪声点
        cls =  'noise'
        xy = X[class_member_mask]
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor='k',
             markeredgecolor='k', markersize=6,label=cls)
    else:
        xy = X[class_member_mask & core_samples_mask]   #核心点   #   实现的功能就是布尔类型的数组索引表现的是全部都是核心点？
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),  #markerfacecolor：设定填充的颜色；
             markeredgecolor='k', markersize=10,label= 'class '+ str(k)+' core')  #markeredgecolor：设定边框的颜色
        
        xy = X[class_member_mask & ~core_samples_mask]  #边缘点  #，没有边缘点全部被当作是核心点 xy在这之前被置空了一次 array is empty
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),
             markeredgecolor='k', markersize=6,label= 'class '+ str(k)+' border')
plt.legend(loc='best')  #图例位置
plt.title('Estimated number of clusters: %d,original parameters' % n_clusters_) #估计集群数量结果
plt.show()



#更改系数 保持min_samples不变
# eps=0.1  min_samples=5
db = DBSCAN(eps=0.1).fit(X)
core_samples_mask = np.zeros_like(db.labels_, dtype=bool) #构造与db维度一样的矩阵
core_samples_mask[db.core_sample_indices_] = True  #核心点索引设置为true
labels = db.labels_ #[0,1,2,-1]
n_clusters_ = len(set(labels)) - (1 if -1 in labels else 0)  #除去噪声点
unique_labels = set(labels) # 是一个0，1，2，-1 的set
colors = [plt.cm.Spectral(each)   
          for each in np.linspace(0, 1, len(unique_labels))]  #类型是一个list list里面的元素只有一个元素是tuple 可迭代对象？
plt.figure(figsize=(12, 9))
for k, col in zip(unique_labels, colors):   #col这里的类型是一个tuple
    class_member_mask = (labels == k)  # 形成的是true false 的数组为什么？ -1 就是false
    if k == -1:   #被判定的噪声点 
        cls =  'noise'
        xy = X[class_member_mask]
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor='k',
             markeredgecolor='k', markersize=6,label=cls)
    else:
        xy = X[class_member_mask & core_samples_mask]   #核心点  core_samples_mask初始全部设置为ture 后面为什么会true false 交替 为什么会变在哪里变了？
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),
             markeredgecolor='k', markersize=12,label='class '+ str(k)+' core')
        xy = X[class_member_mask & ~core_samples_mask]  #边缘点
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),
             markeredgecolor='k', markersize=6,label='class '+ str(k)+' border')
plt.legend(loc='best')
plt.title('Estimated number of clusters: %d,decrease eps' % n_clusters_)
plt.show()

# eps过小导致簇的分裂
db = DBSCAN(eps=0.05).fit(X)
core_samples_mask = np.zeros_like(db.labels_, dtype=bool) #构造与db维度一样的矩阵
core_samples_mask[db.core_sample_indices_] = True  #核心点索引设置为true
labels = db.labels_ #[0,1,2,-1]
n_clusters_ = len(set(labels)) - (1 if -1 in labels else 0)  #除去噪声点
unique_labels = set(labels) # 是一个0，1，2，-1 的set
colors = [plt.cm.Spectral(each)   
          for each in np.linspace(0, 1, len(unique_labels))]  #类型是一个list list里面的元素只有一个元素是tuple 可迭代对象？
plt.figure(figsize=(12, 9))
for k, col in zip(unique_labels, colors):   #col这里的类型是一个tuple
    class_member_mask = (labels == k)  # 形成的是true false 的数组为什么？ -1 就是false
    if k == -1:   #被判定的噪声点 
        cls =  'noise'
        xy = X[class_member_mask]
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor='k',
             markeredgecolor='k', markersize=6,label=cls)
    else:
        xy = X[class_member_mask & core_samples_mask]   #核心点  core_samples_mask初始全部设置为ture 后面为什么会true false 交替 为什么会变在哪里变了？
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),
             markeredgecolor='k', markersize=12,label='class '+ str(k)+' core')
        xy = X[class_member_mask & ~core_samples_mask]  #边缘点
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),
             markeredgecolor='k', markersize=6,label='class '+ str(k)+' border')
plt.legend(loc='best')
plt.title('Estimated number of clusters: %d,decrease eps' % n_clusters_)
plt.show()



#更改系数  #保持eps不变
# eps=0.1  min_samples=4
db = DBSCAN(eps=0.1,min_samples = 4).fit(X) #min_sample  的指定参数不可以小于3 
core_samples_mask = np.zeros_like(db.labels_, dtype=bool) #构造与db维度一样的矩阵
core_samples_mask[db.core_sample_indices_] = True  #核心点索引设置为true
labels = db.labels_ #[0,1,2,-1]
n_clusters_ = len(set(labels)) - (1 if -1 in labels else 0)  #除去噪声点
unique_labels = set(labels) # 是一个0，1，2，-1 的set
colors = [plt.cm.Spectral(each)   
          for each in np.linspace(0, 1, len(unique_labels))]  #类型是一个list list里面的元素只有一个元素是tuple 可迭代对象？
plt.figure(figsize=(12, 9))
for k, col in zip(unique_labels, colors):   #col这里的类型是一个tuple
    class_member_mask = (labels == k)  # 形成的是true false 的数组为什么？ -1 就是false
    if k == -1:   #被判定的噪声点 
        cls =  'noise'
        xy = X[class_member_mask]
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor='k',
             markeredgecolor='k', markersize=6,label=cls)
    else:
        xy = X[class_member_mask & core_samples_mask]   #核心点  core_samples_mask初始全部设置为ture 后面为什么会true false 交替 为什么会变在哪里变了？
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),
             markeredgecolor='k', markersize=12,label='class '+ str(k)+' core')
        xy = X[class_member_mask & ~core_samples_mask]  #边缘点
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),
             markeredgecolor='k', markersize=6,label='class '+ str(k)+' border')
plt.legend(loc='best')
plt.title('Estimated number of clusters: %d,decrease min_samples' % n_clusters_)
plt.show()  #结果会把原来的噪音点处理成一类

#更改系数  #保持eps不变
# eps=0.1  min_samples=13
db = DBSCAN(eps=0.1,min_samples = 13).fit(X) #min_sample  的指定参数不可以小于3 
core_samples_mask = np.zeros_like(db.labels_, dtype=bool) #构造与db维度一样的矩阵
core_samples_mask[db.core_sample_indices_] = True  #核心点索引设置为true
labels = db.labels_ #[0,1,2,-1]
n_clusters_ = len(set(labels)) - (1 if -1 in labels else 0)  #除去噪声点
unique_labels = set(labels) # 是一个0，1，2，-1 的set
colors = [plt.cm.Spectral(each)   
          for each in np.linspace(0, 1, len(unique_labels))]  #类型是一个list list里面的元素只有一个元素是tuple 可迭代对象？
plt.figure(figsize=(12, 9))
for k, col in zip(unique_labels, colors):   #col这里的类型是一个tuple
    class_member_mask = (labels == k)  # 形成的是true false 的数组
    if k == -1:   #被判定的噪声点 
        cls =  'noise'
        xy = X[class_member_mask]
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor='k',
             markeredgecolor='k', markersize=6,label=cls)
    else:
        xy = X[class_member_mask & core_samples_mask]   #核心点  core_samples_mask初始全部设置为ture 
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),
             markeredgecolor='k', markersize=12,label='class '+ str(k)+' core')
        xy = X[class_member_mask & ~core_samples_mask]  #边缘点
        plt.plot(xy[:, 0], xy[:, 1], 'o', markerfacecolor=tuple(col),
             markeredgecolor='k', markersize=6,label='class '+ str(k)+' border')
plt.legend(loc='best')
plt.title('Estimated number of clusters: %d,increase min_samples' % n_clusters_)
plt.show()  #结果会把原来的噪音点处理成一类

