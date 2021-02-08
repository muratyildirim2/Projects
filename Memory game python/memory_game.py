#
#author : Murat Yıldırım
#
from PyQt5.QtWidgets import * 
from PyQt5 import QtCore, QtGui 
from PyQt5.QtGui import * 
from PyQt5.QtCore import *
import random
import signal
import sys
import threading
import time
import logging

class Window(QMainWindow): 
  
    def __init__(self): 
        super().__init__() 
        self.start=True
        self.counter=0
      
        self.setWindowTitle("Memory Game ") 
        
       
        self.setGeometry(100, 100, 1200, 700) 
  
       
        self.UiComponents() 
  
        
        self.show() 
        self.hh=0
        self.flag=0
        self.isplaying=0
        self.number = 0
        self.numberofgame=0
        self.speed=1000
        self.coming=5
    
    def UiComponents(self): 
        self.numbers = list()
        head = QLabel("Memory Game", self) 
        self.level = QLabel("", self)
        self.howmany = QLabel("YOU WILL SEE 5 NUMBERS IN LEVEL 1 !!!", self)
        head.setGeometry(400, 20, 500, 100) 
        self.level.setGeometry(500, 120, 500, 100)
        
      
        self.howmany.setGeometry(700, 120, 500, 100)
        font = QFont('Times', 20) 
        font.setBold(True) 
        font.setItalic(False) 
        font.setUnderline(False)

        font1 = QFont('Times', 12) 
        font1.setBold(True) 
        font1.setItalic(False) 
        font1.setUnderline(False) 
    
        
        
       
        head.setFont(font)
        self.lineEntry = QLineEdit("",self)
        self.lineEntry.move(225,145)
        self.lineEntry.resize(200,40)

        self.qlabel = QLabel(self)
        self.qlabel.move(16,64)
        self.level.setFont(font)
        self.howmany.setFont(font1)
        self.howmany.setStyleSheet("color: red;")
        
       
        head.setAlignment(Qt.AlignCenter) 
  
       
        color = QGraphicsColorizeEffect(self) 
        color.setColor(Qt.darkCyan) 
        head.setGraphicsEffect(color) 
  
    
        self.info = QLabel("Welcome to Memory Game", self) 
        self.info.setFont(font1)
      
        self.info.setGeometry(450, 120, 600, 400) 
  
        
  
  
       
       
        
  
       
        control = QPushButton("Control", self)
        control.setGeometry(575, 600, 100, 40) 
        
  
       
        start = QPushButton("Start", self) 
        start.setGeometry(420, 600, 100, 40) 
  
        reset_game = QPushButton("Reset", self) 
  
    
        reset_game.setGeometry(730, 600, 100, 40) 
  
 
        color_red = QGraphicsColorizeEffect() 
        color_red.setColor(Qt.red) 
        reset_game.setGraphicsEffect(color_red) 
  
        color_green = QGraphicsColorizeEffect() 
        color_green.setColor(Qt.darkBlue) 
        start.setGraphicsEffect(color_green) 
  
     
        start.clicked.connect(self.start_action) 
        reset_game.clicked.connect(self.reset_action)
        control.clicked.connect(self.controlf)
    def showTime(self): 
       
         
         if self.counter == self.coming :
            self.start=False
            self.info.setGeometry(350, 120, 600, 400)  
            self.info.setText("Time to check!!")
            self.isplaying =1
            self.counter=0
            
            
             
         if self.start :
             
          a=random.randint(0,10)
          self.numbers.insert(self.counter,a)
          text = str(a) 
          x=random.randint(25,575)
          y=random.randint(25,375)
          self.info.setGeometry(x, y, 600, 400)
           
          self.info.setText(text)
          self.flag=0

          self.counter+=1       
         
            

    
    def start_action(self): 
           self.howmany.setText("")
           if self.numberofgame ==0 and self.hh==0 and self.isplaying==0 :
            timer = QTimer(self) 
            self.level.setText("Level : {}".format(self.numberofgame+1))
            
            self.hh+=1
 
           
            timer.timeout.connect(self.showTime) 
           
   
            
             
             
            
            timer.start(self.speed)
           
 
           
           
           if self.flag==1 :
            self.level.setText("Level : {}".format(self.numberofgame+1))
            self.start=True
            self.isplaying=1 
    def reset_action(self): 
   
        self.counter=0
        self.info.setGeometry(350, 120, 600, 400)
  
        self.info.setText("Welcome to Memory Game") 
        self.start=False
        self.numberofgame=0
        self.level.setText("Level : {}".format(self.numberofgame+1))
        self.speed=1000
        self.coming=5
        self.isplaying=0
        self.howmany.setText("YOU WILL SEE {} NUMBERS IN LEVEL {}!!!".format(self.coming,self.numberofgame+1))
        self.lineEntry.setText("")
    def controlf (self) :
           flag=1
           
           input_list = self.lineEntry.text().split(" ")
           for i in range(self.coming):
               if self.numbers[i]!= int(input_list[i]):
                   flag=0
                   break
               
           if flag== 1:
               self.info.setText("YOU WON!!!!!  LET'S GO TO NEXT LEVEL!!!")
               self.numberofgame+=1
               self.coming+=1
               self.isplaying=0
               self.flag=1
               self.howmany.setText("YOU WILL SEE {} NUMBERS IN LEVEL {}!!!".format(self.coming,self.numberofgame+1))
               self.lineEntry.setText("")
           else :
               self.info.setText("YOU LOST!!!!! START AGAIN FROM LEVEL 1!!!")
               self.counter=0
               self.info.setGeometry(350, 120, 600, 400)
               self.coming=5
               self.isplaying=0
               self.flag=1
               self.numberofgame=0
               self.howmany.setText("YOU WILL SEE {} NUMBERS IN LEVEL {}!!!".format(self.coming,self.numberofgame+1))
               self.lineEntry.setText("")
   
               
               self.start=False
               self.numberofgame=0
               self.level.setText("Level : {}".format(self.numberofgame+1))
               self.speed=1000
# create pyqt5 app 
App = QApplication(sys.argv) 
  
# create the instance of our Window 
window = Window() 
  
# start the app 
sys.exit(App.exec()) 
