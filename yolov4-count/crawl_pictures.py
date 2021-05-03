# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import re
import requests
import urllib.request

headers = {
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Encoding": "gzip, deflate, br",
    "Accept-Language": "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
    "Cache-Control": "no-cache",
    "Connection": "keep-alive",
    "Cookie": "BAIDUID_BFESS=F58C77A18EF5D10B1B2630BC19A166B7:FG=1; BIDUPSID=F58C77A18EF5D10B1B2630BC19A166B7; BDRCVFR[dG2JNJb_ajR]=mk3SLVN4HKm; __yjs_duid=1_d9f186b0935621a950715aa8cd609da21619171060464; userFrom=null; ab_sr=1.0.0_YzgxNTI1MjQ3ODkwZTIyZGExNThlYTI5Y2UyNjNkMTI1ZWE3ZmMzZjJkZWZmZTBlZDNlYmM2NjQ5Yjk4YmMxZmUyNWI2ZGFmZDZmNTMxM2JjZmU3OTNmNTY4NmUwNjE3",
    "Host": "image.baidu.com",
    "Pragma": "no-cache",
    "sec-ch-ua": '" Not A;Brand";v="99", "Chromium";v="90", "Microsoft Edge";v="90"',
    "sec-ch-ua-mobile": "?0",
    "Sec-Fetch-Dest": "document",
    "Sec-Fetch-Mode": "navigate",
    "Sec-Fetch-Site": "same-origin",
    "Sec-Fetch-User": "?1",
    "Upgrade-Insecure-Requests": "1",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.72 Safari/537.36 Edg/90.0.818.42"}


def getimg():
    img_list = []
    for pn in range(30, 301, 30):
        gsm = hex(pn)[2:]
        url = "https://image.baidu.com/search/acjson?tn=resultjson_com&logid=8586914456857095935&ipn=rj&ct=201326592&is=&fp=result&queryWord=%E7%94%B5%E5%8A%A8%E8%BD%A6&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=&ic=0&hd=&latest=&copyright=&word=%E7%94%B5%E5%8A%A8%E8%BD%A6&s=&se=&tab=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&expermode=&force=&pn=" + str(
            pn) + "&rn=30&gsm=" + str(gsm) + "&1619172405910="
        html = requests.get(url, headers=headers).text
        url_re = re.compile('"thumbURL":"(.*?)"')
        img_list += re.findall(url_re, html)
    return img_list


def download(img_list):
    count = 1
    for url in img_list:
        img = urllib.request.urlopen(url).read()
        with open("pictures/" + str(count) + ".jpg", "wb") as f:
            f.write(img)
        count += 1


download(getimg())
