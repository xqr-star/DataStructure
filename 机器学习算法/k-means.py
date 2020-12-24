import numpy as np
import matplotlib.pyplot as plt 
#导入数据
data=np.loadtxt("E:\Python\k课程设计\k课程设计\\testSet.csv",delimiter=",")

plt.scatter(data[:,0],data[:,1])#散点展示 取出所有数据的第0列 第1列分别作为x，y
plt.show()

#定义欧几里得距离
def euclid(value,centre):
    return np.sqrt(sum(np.power(value-centre, 2)))     #　ρ = sqrt( (x1-x2)^2+(y1-y2)^2 )
            #开根   求和          平方 

k=4    #从图中大致看出，簇为4
#shuffle()是不能直接访问的，是random库中的方法需要导入 random 模块，然后通过 random 静态对象调用该方法。
np.random.shuffle(data)#打乱数据集 这里打乱的原因就是防止我原始的数据如果按照顺序排序，需要进行多次迭代
centre=np.array(data[0:k])   # 质心为centre  质心是自己指定的取出前k个点作为质心
print('初始指定的质心坐标分别为：',centre)


time=0
min_time=10 #至少迭代10次
E=0.001   #质心距离和差异期望
last_total_distance=0
flag=True

while flag: 
    #创建簇（list） 现在这个四个簇里面有哪些数据是未知的 
    lists = [[] for _ in range(k)]
    #各簇质心距离及质心距离和    
    total_distance=0   
    total_distance0=0
    total_distance1=0
    total_distance2=0
    total_distance3=0    
    #将数据集的每个点分类到簇中，质心为centre，距离为欧几里得距离 
    for value in data:
        # 计算每个点到不同质心的距离
        distance0=euclid(value,centre[0])
        distance1=euclid(value,centre[1])
        distance2=euclid(value,centre[2])
        distance3=euclid(value,centre[3])
        min_dis=min(distance0,distance1,distance2,distance3)
        #距离哪个质心最小，则归为哪个簇
        if min_dis==distance0:
            lists[0].append(value)
            total_distance0 += min_dis
        elif min_dis==distance1:
            lists[1].append(value)
            total_distance1 += min_dis
        elif min_dis==distance2:
            lists[2].append(value)
            total_distance2 += min_dis
        elif min_dis==distance3:
            lists[3].append(value)
            total_distance3 += min_dis
        
    total_distance = total_distance0+total_distance1+total_distance2+total_distance3
    total_distance_difference = abs(total_distance - last_total_distance) # 求绝对值函数 距离上一次有没有进步
    last_total_distance = total_distance
    
    cluster0=np.array(lists[0]) # 将 list转换为数组 为了后面画图
    cluster1=np.array(lists[1])
    cluster2=np.array(lists[2])
    cluster3=np.array(lists[3])
    
    print('迭代次数：',time)
    print('质心距离和差值：',total_distance_difference)   
    print('质心坐标分别为：',centre)
    
    #画出质心点在迭代过程中的变化情况
    plt.figure()
    plt.xlim([-8,8]) #设置x轴的数值显示范围。
    plt.ylim([-8,8])
    plt.title("K-means"+str(time))
    plt.scatter(centre[0,0],centre[0,1],color='r',marker='x') 
    plt.scatter(centre[1,0],centre[1,1],color='y',marker='x')
    plt.scatter(centre[2,0],centre[2,1],color='b',marker='x')
    plt.scatter(centre[3,0],centre[3,1],color='g',marker='x')
    plt.scatter(cluster0[:,0],cluster0[:,1],color='r')
    plt.scatter(cluster1[:,0],cluster1[:,1],color='y')
    plt.scatter(cluster2[:,0],cluster2[:,1],color='b')
    plt.scatter(cluster3[:,0],cluster3[:,1],color='g')
    plt.show() 
    
    
    if total_distance_difference<E and time >= min_time:  # 控制迭代次数即迭代停止的条件 
        flag=False 
        
        
    #计算出当前每个簇的新的质心--平均值的方法计算
    centre[0]=np.array(sum(cluster0)/len(cluster0)) 
    centre[1]=np.array(sum(cluster1)/len(cluster1))
    centre[2]=np.array(sum(cluster2)/len(cluster2))
    centre[3]=np.array(sum(cluster3)/len(cluster3))
    time += 1  #计数 



