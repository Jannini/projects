import struct
import socket
import binascii

def ethernet_data(data):
    mac1, mac2, type=struct.unpack('!6s 6s H', data[:14])
    final_mac1, final_mac2=convert_mac(mac1),convert_mac(mac2)
    return final_mac1,final_mac2,type,data[14:]

def convert_mac(data):
    newdata=map('{:02x}'.format,data)
    return ":".join(newdata).capitalize()

def ip_data(data):
    ip1,ip2=struct.unpack('!4s4s', data[24:32])
    return ".".join(map(str,ip1)),".".join(map(str,ip2))

def main():
    HOST = socket.gethostbyname(socket.gethostname())

    # create a raw socket and bind it to the public interface
    s = socket.socket(socket.AF_INET, socket.SOCK_RAW, 0)
    s.bind((HOST, 0))

    # receive all packages
    s.ioctl(socket.SIO_RCVALL, socket.RCVALL_ON)

    while True:
        data=s.recv(65565)
        print(ethernet_data(data))
        print(ip_data(data))
main()