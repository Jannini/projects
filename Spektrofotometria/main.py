import os
import matplotlib.pyplot as plt

class Main:

    UV_Data=[]
    ABS_Data=[]
    max_UV=[]
    max_ABS=[]
    Conc=[]
    m=0.0
    b=0.0
    number_of_unknowns = 0



    def data_Format(self,filename):
        file = open(filename, "r")
        list = file.readlines()
        for lines in list:
            data=lines.split()
            self.UV_Data+=data[0:1]
            self.ABS_Data+=data[1:2]
        file.close()


    def set_Data(self):
        self.max_ABS.append(max(self.ABS_Data))
        index=self.ABS_Data.index(self.max_ABS[-1])
        self.max_UV.append(self.UV_Data[index])
        self.ABS_Data[:] = []
        self.UV_Data[:] = []

    def calculate_Slope(self):

        self.max_ABS = list(map(float, self.max_ABS))
        avg_X=sum(self.max_ABS[0:-1*self.number_of_unknowns])/(len(self.max_ABS)-self.number_of_unknowns)
        avg_Y=sum(self.Conc[0:-1*self.number_of_unknowns])/(len(self.Conc)-self.number_of_unknowns)

        for i in range(len(self.max_ABS)-self.number_of_unknowns):
            nominator=0
            nominator+=((self.max_ABS[i]-avg_X)*(self.Conc[i]-avg_Y))
            denominator=0
            denominator+=((self.max_ABS[i]-avg_X)*(self.max_ABS[i]-avg_X))
            self.m=nominator/denominator



        for i in range(len(self.max_ABS)-self.number_of_unknowns):
            current_B=-1*self.m*self.max_ABS[i]+self.Conc[i]
            self.b+=current_B
        self.b=self.b/len(self.max_ABS)

        return self.m

    def fit_New_Data(self):
        for k in range(len(self.max_ABS)):
            if k > len(self.max_ABS) - self.number_of_unknowns - 1:
                self.Conc[k] = (self.max_ABS[k] * self.m + self.b)




if __name__ == '__main__':

    os.chdir("Data")
    main = Main()
    print("Tedd a szoveges fajlokat a main.py melle egy Data nevu mappaba.\nA fajlok neve tartalmazza a koncentraciot"
          "\nAz ismeretlen adattagu fajlok vege pedig legyen ellatva erre utalo szoval(pl legyen ism1,ism2 a vegen)\n")
    smallest_C = float(input("Ird be a legkisebb koncentraciot: "))
    step = float(input("Ird be a koncentraciok kozti lepesek nagysagat: "))
    main.number_of_unknowns = int(input("Ird be az ismeretlen adattagu mintak szamat: "))
    next_step = step



    for filename in os.listdir(os.getcwd()):
        main.data_Format(filename)
        main.Conc.append(smallest_C+next_step-step)
        next_step+=step
        main.set_Data()

    print(main.max_ABS)
    print(main.Conc)


    main.calculate_Slope()
    main.fit_New_Data()

    endpoint = max(main.max_ABS)+0.1*max(main.max_ABS)
    print(endpoint)
    print(main.Conc)

    xplot=[0,endpoint]
    yplot=[main.b,endpoint*main.m+main.b]
    plt.gcf().canvas.set_window_title("Spektrofotometria")
    plt.plot(xplot,yplot)
    plt.plot(main.max_ABS, main.Conc, 'ro')
    plt.axis([0, 1, 0, 0.2])
    plt.title("Spektrofotometria")
    plt.xlabel("ABS")
    plt.ylabel("c[mol/dm3]")
    plt.show()







