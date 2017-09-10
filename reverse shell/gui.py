import sys
import server
import socket
import _thread
import tkinter
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *
import sys
from PyQt5.QtCore import *
from PyQt5.QtWidgets import (QWidget, QLCDNumber, QSlider,
    QVBoxLayout, QApplication)



class Example(QWidget):

    receivedText=None
    sentText=None
    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):
        sendButton = QPushButton('Kuldes')
        sendButton.clicked.connect(self.send_query)




        self.receivedText=QTextEdit()
        self.receivedText.setReadOnly(True)
        self.sentText = QTextEdit()

        grid=QGridLayout()
        grid.setSpacing(5)
        grid.addWidget(self.receivedText,1,0)
        grid.addWidget(self.sentText,2,0)
        grid.addWidget(sendButton, 3, 1)
        self.setLayout(grid)

        self.setGeometry(300, 300, 350, 300)
        self.setWindowTitle('Command Line Shell')
        self.show()

    def send_query(self):
        command=self.sentText.toPlainText()
        data=shellserver.send_command(command)
        print(data)
        self.set_receivedText(data)



    def keyPressEvent(self, event):
        key = event.key()
        print(key)


        if key == Qt.Key_Return:
            self.send_query()

    def set_receivedText(self,data):
        self.receivedText.setText(data)

class New_Window(QWidget):
    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):

        self.setGeometry(300, 300, 350, 300)
        self.setWindowTitle('Command Line Shell')
        grid = QGridLayout()
        grid.setSpacing(5)
        self.setLayout(grid)

if __name__ == '__main__':

    app = QApplication(sys.argv)
    ex = Example()
    new = New_Window()
    print("My ip: ", socket.gethostbyname(socket.gethostname()))
    shellserver = server.Server(socket.gethostbyname(socket.gethostname()), 65535)
    shellserver.start_receiving()
    sys.exit(app.exec_())
