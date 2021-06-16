# 文件说明

cut_picture：存放每个摄像头视频中截取的、用于检测的一帧图片

video：存放每个摄像头实时拍摄的一段视频

yolov4：存放yolov4训练模型时需要的配置文件，有兴趣的朋友可以在此基础上训练自己的模型

codestyle.md：记录代码风格

count_cars.py：使用pytorch计算图中车辆数的代码

crawl_pictures.py: 爬取百度图库中有关电动车的图片的代码，参数可调整，有兴趣的朋友可自己尝试爬取

database.py: 将计算结果插入数据库的代码

get_m3u8_picture.py：从服务器获取每个摄像头拍摄的m3u8格式的视频，并截取其中一帧，存放至video和cut_picture中

main.py：运行整个程序的代码

my_redis.py：订阅Redis传来的有关摄像头上下线的通知，只获取在线摄像头的图像

requirements.txt：该python程序依赖的包，有兴趣的朋友可以使用```pip install -r requirements.txt```安装相关依赖包