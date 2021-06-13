from time import sleep
import threading
from count_cars import Yolov4Count
from get_m3u8_picture import CutPicture
from my_redis import Redis, lock

if __name__ == "__main__":
    counter = Yolov4Count()
    cuter = CutPicture()
    redis = Redis()

    redis.update_device()

    # 子线程监听是否有新设备上线
    thread = threading.Thread(target=redis.has_update)
    thread.start()

    while True:
        # 获取m3u8的url表
        lock.acquire()
        video_url = redis.get_device_video()
        lock.release()

        # 截取视频帧，并将检测结果插入数据库
        for id, url in video_url.items():
            cuter.download_m3u8_video(id, url)
            cuter.cut_picture(id)
            counter.predicate(id)

        sleep(30)
