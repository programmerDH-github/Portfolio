import os
import time

from PIL import Image
import pytesseract
import cv2
import pyautogui as pag
import keyboard as key
import random

def tesseract():
    pytesseract.pytesseract.tesseract_cmd = R'C:\Program Files\Tesseract-OCR\tesseract'
    cv = cv2.imread("C:/Users/KTC SYSTEM/Desktop/poe/0000.png", cv2.IMREAD_COLOR) #이미지 가공
    str = pytesseract.image_to_string(cv, lang='kor') #이미지로부터 텍스트 인식
    str = str.replace(" ","")

    """원하는 옵션(방어도)"""
    if str.find("방어도") == -1:
        print("F")
        return False
    else:
        print("T")
        return True

""" 이미지 가공 """
def imageCut():
    image1 = Image.open("C:/Users/KTC SYSTEM/Documents/My Games/Path of Exile/Screenshots/screenshot-0000.png")
    cropped_img = image1.crop((20,100,600,170))
    cropped_img.save("C:/Users/KTC SYSTEM/Desktop/poe/0000.png")

""" 항상 같은 좌표 클릭 회피 """
def x_random_orb(x): #아이템 옵션을 설정하는 오브 X좌표 랜덤값 부여
    x = random.randrange(x,x+25)
    return x

def y_random_orb(y): #아이템 옵션을 설정하는 오브 Y좌표 랜덤값 부여
    y = random.randrange(y,y+25)
    return y

def x_random_item(x): #아이템 X좌표 랜덤값 부여
    x = random.randrange(x,x+60)
    return x

def y_random_item(y): #아이템 Y좌표 랜덤값 부여
    y = random.randrange(y,y+110)
    return y


def screenshot(): #게임 화면 캡쳐
    time.sleep(3)   #
    pag.press('F8')
    print('hi')



def start():
    pag.moveTo(x_random_orb(85), y_random_orb(200)) #X
    pag.rightClick()
    pag.moveTo(x_random_item(250), y_random_item(300))
    pag.click()



check = False
while True:
    if key.is_pressed('ESC') == True:
        break
    if key.is_pressed('F2') == True:
        check = True
    if key.is_pressed('F3') == True:
        check = False

    if check == True:
        start()
        screenshot()
        time.sleep(1)
        imageCut()
        os.remove("C:/Users/KTC SYSTEM/Documents/My Games/Path of Exile/Screenshots/screenshot-0000.png")
        if tesseract() == True:
            break
        time.sleep(2)
