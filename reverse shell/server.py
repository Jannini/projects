import socket
import threading
import os

class Server:

    host = None
    port = None
    socket = None
    ip=None
    port2=None
    data=None
    connection=None
    def __init__(self,host,port):


        self.host = host
        self.port = port
        self.socket = socket.socket()
        self.socket.bind((self.host, self.port))
        self.socket.listen(5)


    def send_command(self,command):

                if command == "quit":
                    self.connection.close()

                if len(str.encode(command)) > 0:
                    self.connection.send(str.encode(command))
                    response = str(self.connection.recv(1024), "utf-8")
                    return response





    def start_receiving(self):
            self.connection, self.address = self.socket.accept()
            self.ip = self.address[0]
            self.port2 = str(self.address[1])
            print("Accepting IP: {}  Port: {}".format(self.ip, self.port2))




    def save_incoming_messages(self,messages):
        self.data=messages







def main():
        print("My ip: ", socket.gethostbyname(socket.gethostname()))
        server=Server(socket.gethostbyname(socket.gethostname()),65535)
        server.start_receiving()




