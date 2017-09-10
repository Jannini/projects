import java.io.ByteArrayOutputStream;

/**
 * Created by Jani on 2017.05.05..
 */

//16 bites ket csatornas fajlokat bontunk.Ingyenes mintákat is szinte csak ilyen formátumba találtam. Ha valaki egy csatornás
    //wavot bontana akkor visszakapja ugyan azt kétszer..

public class Fajlbontas extends Filekezelo {
    //Ezekbe kerulnek majd első körbe a szeletelt darabok.
    ByteArrayOutputStream balkimenet = new ByteArrayOutputStream();
    ByteArrayOutputStream jobbkimenet = new ByteArrayOutputStream();
    ByteArrayOutputStream kezdes = new ByteArrayOutputStream();



//Az offsetek megadják, hogy hanyadik bajttol kezdjen el vágni,
    int offset;
    int offset2;

    //A wavfajl bal csatornajat levagja. Megadjuk, hogy honnantol mennyit vágjon ki és hova írja bele.
    //A ciklus működése:offsettöl kezdve megadott 4 bajtot ír.
    //2 adat 2 viszont üres bajt. Ha csak adattal lenne teleírva akkor felgyorsulna.
    //Mivel 4 bajtot beleirtunk a ciklus ugyan ennyit ugrik.

    public byte[] Balcsatorna(byte[] WavByte)throws Exception{
        byte[] filler = new byte[WavByte.length];
        for (offset = 44; offset < WavByte.length; offset = offset + 4) {
            balkimenet.write(WavByte, offset, 2);
            balkimenet.write(filler, offset, 2);
        }
        // A cimke/tag reszet vagja le a fajlnak.
        kezdes.write(WavByte, 0, 44);

        // Byte tombos taroloba kerulnek at a levagott reszek.
        byte[] bal = balkimenet.toByteArray();
        byte[] kezd = kezdes.toByteArray();

        //Osszerakjuk a tag reszt es a csatornához tartozo hang adatot.
        ByteArrayOutputStream egeszbal = new ByteArrayOutputStream();
        egeszbal.write(kezd);
        egeszbal.write(bal);
        byte[] balcs = egeszbal.toByteArray();

        //Visszaadja a bal csatornát
        return balcs;

    }
    //A wavfajl jobb csatornajat levagja. Megadjuk, hogy honnantol mennyit vágjon ki és hova írja bele.
    public byte[] Jobbcsatorna(byte[] WavByte)throws Exception{
        byte[] filler = new byte[WavByte.length];
        for (offset2 = 46; offset2 < WavByte.length; offset2 = offset2 + 4) {
            jobbkimenet.write(filler, offset2, 2);
            jobbkimenet.write(WavByte, offset2, 2);


        }
        // A cimke/tag reszet vagja le a fajlnak.
        kezdes.write(WavByte, 0, 44);

        // Byte tombos taroloba kerulnek at a levagott reszek.
        byte[] jobb = jobbkimenet.toByteArray();
        byte[] kezd = kezdes.toByteArray();

        //Osszerakjuk a tag reszt es a csatornához tartozo hang adatot.
        ByteArrayOutputStream egeszjobb = new ByteArrayOutputStream();
        egeszjobb.write(kezd);
        egeszjobb.write(jobb);
        byte[] jobbcs = egeszjobb.toByteArray();

        //Visszaadja a jobb csatornát
        return jobbcs;

    }
}
