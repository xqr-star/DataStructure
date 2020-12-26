import numpy as np
import random
import matplotlib.pyplot as plt  

#导入数据
data = np.loadtxt("E:\Python\k课程设计\k课程设计\\testSet.csv", dtype=np.float, delimiter=",")
plt.scatter(data[:,0],data[:,1])   #绘图 原图
plt.title('original data')
plt.show()

total_distance =0  #质心距离和
last_total_distance=0 #上次的质心距离和
total_distance_difference=0   # 质心距离差
K=4  # 将给定数据集构建一个包含K个随机质心的数组   
E = 0.001       
tempclass=np.zeros(data.shape[0]) #获得第0列的维度(行数)定义一个数组-- 80行
np.random.shuffle(data)#打乱数据集 这里打乱的原因就是防止我原始的数据如果按照顺序排序，需要进行多次迭代
centerArray=np.array(data[0:K])   # 质心为centre  质心是自己指定的取出前k个点作为质心

def distance(sub1,sub2):                         
    return np.sqrt(np.sum(np.square(sub1-sub2))) #　ρ = sqrt( (x1-x2)^2+(y1-y2)^2 )
            #开根   求和          平方 
            
#def kmeans(centerArray):
def kmeans(centerArray,num):
    global total_distance
    total_distance=0  #每一次进来都要重新计算质心距离
    global total_distance_difference
    global last_total_distance      
    for i in range(num):              #逐个遍历数据
        mindis=10000;                 #将初始距离设置一个比较大的数字质心的最小距离  
        sub1=data[i,:]                #获取矩阵x的第i行数据
        for j in range(K):            #遍历质心
            sub2=centerArray[j,:]     # 按行读取质心列表中的行向量
            temp = distance(sub1,sub2) # 逐个元素计算与质心的距离
            #print ("the disctent %d"%(temp)) #输出的是80个数据依次到4个质心的距离(一次迭代320)
            if (temp<mindis):         # 在k个质心里面选择距离最小的
                mindis=temp           
                tempclass[i]=j        #得到样本i 距离最近质心
                total_distance += temp    #将所有质心距离相加
    
    total_distance_difference = abs(total_distance - last_total_distance)
    last_total_distance = total_distance
    
    #更新质心
    for j in range(K): #遍历质心            #按照质心个数，统计每个质心下面的样本
        tempclassResult = data[tempclass==j]  #从聚类结果里面分别拿到每个类的样本  数组的布尔索引
        x1=np.mean(tempclassResult[:,0])   #取出tempclassResult里面第0列的值序列，并对这个序列计算均值
        x2=np.mean(tempclassResult[:,1])   
        centerArray[j,:]=[x1,x2] #更新质心数组里面的质心坐标
        
        
    #绘图   
    for i,c in zip(range(K),['r','y','b','g']): #zip()函数用于将可迭代的对象作为参数,将对象中
        cla =data[tempclass==i]                                   #对应的元素打包成一个元组
        p1=plt.scatter(cla[:,0],cla[:,1],marker='o',color=c,label='data')  
        
    p2=plt.scatter(centerArray[:,0],centerArray[:,1],marker='x',color='black',label='x')
    plt.title("cluster:" )  
    plt.legend(loc='upper right')  
    plt.show()  
    
    print('质心距离和差值：',total_distance_difference)   
                
# for i  in range(10):
#         print("i = %d" % i)
#         kmeans(centerArray,data.shape[0]) 

kmeans(centerArray,data.shape[0]) 
for i  in range(10):
        if total_distance_difference > E :
            print("i = %d" % i)
            kmeans(centerArray,data.shape[0])   #传递质心，数据的个数
        
    
   
    
    
    