import logging
import redis
import json
from database import Database


class Redis:
    host = "106.15.74.153"
    port = "6379"
    password = "haoran8060"

    __device_table = {}
    __device_video = {}

    __has_init = False

    def __init__(self):
        self.pool = redis.ConnectionPool(host=self.host, port=self.port, password=self.password, decode_responses=True)
        self.receiver = redis.Redis(connection_pool=self.pool)
        self.db = Database()

    def has_update(self):
        if self.receiver.get("deviceChannel") is not None:
            return True
        return False

    def update_device(self):
        info = json.loads(self.receiver.get("rtmp:device"))
        for key, value in info.items():
            if key != "@class":
                # 内存设备表是否记录过该设备的id和m3u8的url
                id = self.__device_table.get(key)
                if id is None:
                    # 数据库设备表中是否有该设备
                    id = self.db.select_device_id(key)
                    # 如果数据库中没有该设备
                    if id is None:
                        logging.warning("该设备未登记！")
                    else:
                        self.__device_table[key] = id
                        self.__device_video[id] = value["url"]

    def get_device_video(self):
        return self.__device_video


if __name__ == "__main__":
    pass
