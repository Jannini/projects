import java.io.*;
import java.io.OutputStream;
import java.io.FileOutputStream;

//Megkapja a fajlnevet es a ket csatornat.
//Elsőként levágja a .wav reszt, hogy kiiratásnál elé lehessen irni, hogy hanyadik csatorna.
// Kirja két fájlba a két csatornát
public class Fileoutkezel extends Filekezelo {
    public void fileir(byte[] bal, byte[] jobb, String fajln)throws Exception {
        String fajlnev=fajln.replaceAll(".wav","");


        File balcsatorna = new File(fajlnev+"1.wav");
        OutputStream balcsat = new FileOutputStream(balcsatorna);
        balcsat.write(bal);
        balcsat.close();
        File jobbcsatorna = new File(fajlnev+"2.wav");
        OutputStream jobbcsat = new FileOutputStream(jobbcsatorna);
        jobbcsat.write(jobb);
        jobbcsat.close();







    }

}
