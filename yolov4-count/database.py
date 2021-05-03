import pymysql
import logging


class Database:
    # host = "localhost"
    # root = "root"
    # password = "123456"
    database = "stop"

    host = "106.15.74.153"
    root = "yrc"
    password = "haoran8060"

    __db = None

    def __init__(self):
        self.__db = pymysql.connect(host=self.host, user=self.root, password=self.password,
                                    database=self.database)

    def insert_database(self, device_id, count):
        insert = "insert into crowd_situation(device_id, cars_number) value({0},{1})".format(device_id, count)
        cursor = self.__db.cursor()
        try:
            cursor.execute(insert)
            self.__db.commit()
        except Exception as e:
            self.__db.rollback()
            logging.error(e)

    def select_device_id(self, device_name):
        select = "select id from device where name=\"{0}\"".format(device_name)
        cursor = self.__db.cursor()
        cursor.execute(select)
        id = cursor.fetchone()
        if id is None:
            return None
        return id[0]


if __name__ == "__main__":
    pass
