#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time : 2020/11/11 14:04 
# @Author : ywy
import os, sys

path = r'C:\Project\FRIDA-DEXDump\frida_dexdump\com.moutai.mall'# 文件夹目录
files = os.listdir(path)  # 得到文件夹下的所有文件名称
out_path =r'C:\Project\FRIDA-DEXDump\frida_dexdump\com.moutai.mall\out'  #输出文件夹
#路径上不要有中文!!!!!
s = []
for file in files:  # 遍历文件夹
    if file.find("dex") > 0:  ## 查找dex 文件
        sh = f'jadx -j 1 -r -d {out_path} {path}\\{file}'
        print(sh)
        os.system(sh)