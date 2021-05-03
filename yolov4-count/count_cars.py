import cv2 as cv
from database import Database


class Yolov4Count:
    yolov4_cfg = "yolov4/yolov4-custom.cfg"
    yolov4_weight = "yolov4/yolov4-custom.weights"
    names_path = "yolov4/electromobile.names"

    def __init__(self):
        self.db = Database()

    def predicate(self, device_id):
        net = cv.dnn_DetectionModel(self.yolov4_cfg, self.yolov4_weight)
        net.setInputSize(608, 608)
        net.setInputScale(1.0 / 255)
        net.setInputSwapRB(True)

        image = cv.imread("cut_picture/" + str(device_id) + ".jpg")
        classes, confidences, boxes = net.detect(image, confThreshold=0.1, nmsThreshold=0.4)

        self.db.insert_database(device_id, len(boxes))


if __name__ == "__main__":
    pass
