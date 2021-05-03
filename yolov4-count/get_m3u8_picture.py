import cv2 as cv
import logging
import m3u8
import re
import requests


class CutPicture:
    def download_m3u8_video(self, device_id, url):
        main_url = re.match(".*/", url).group()

        video_name = str(device_id) + ".mp4"

        segments = m3u8.load(url).segments
        center = int(len(segments) / 2)
        ts = segments[center].uri
        ts_content = requests.get(main_url + ts).content
        while len(ts_content) % 16 != 0:
            ts_content += b"0"
        with open("video/" + video_name, "wb") as f:
            f.write(ts_content)

    def cut_picture(self, device_id):
        cap = cv.VideoCapture("video/" + str(device_id) + ".mp4")
        success, frame = cap.read()  # success判断是否读取成功
        if success:
            cv.imwrite("cut_picture/" + str(device_id) + ".jpg", frame)
        else:
            logging.error("图片截取错误")

    def download_full_video(self, device_id, url):
        main_url = re.match(".*/", url).group()
        ts = []
        video_name = str(device_id) + ".mp4"

        for seg in m3u8.load(url).segments:
            ts.append(seg.uri)

        for ts_url in ts:
            ts_content = requests.get(main_url + ts_url).content
            while len(ts_content) % 16 != 0:
                ts_content += b"0"
            with open("video/" + video_name, "ab") as f:
                f.write(ts_content)


if __name__ == "__main__":
    pass
