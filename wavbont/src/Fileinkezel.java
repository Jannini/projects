import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Fileinkezel extends Filekezelo{
//String alapjan beolvassa a fajlt. Csak a projekt root mappájábol tud olvasni.
    //Először inputstreammé majd byte tömbbé alakítja, végül visszaadja a fajlt byte[] es majd ezzel dolgozunk.


    public byte[] Beolvas(String a) throws Exception {
        File wavfajl = new File(a);
         InputStream streamwav = new FileInputStream(wavfajl);
          buffer=streamwav.available();
        byte[] bajtwav=new byte[buffer];
        streamwav.read(bajtwav,0,buffer);
        return bajtwav;




    }



}
