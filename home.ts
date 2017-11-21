import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { DeviceMotion, DeviceMotionAccelerationData } from '@ionic-native/device-motion';
import { DeviceOrientation, DeviceOrientationCompassHeading } from '@ionic-native/device-orientation';
import { Gyroscope, GyroscopeOrientation, GyroscopeOptions } from '@ionic-native/gyroscope';
import { File } from '@ionic-native/file';
import { FileTransfer, FileUploadOptions, FileTransferObject } from '@ionic-native/file-transfer';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  timestamp={
    acceleration:[],
    magnetic:[],
    orient:[]
  };
  acceleration={
    x:[],
    y:[],
    z:[],
  };
  orientation={
    x:[],
    y:[],
    z:[],
  };
  magnet={
    trueMagnet:[],
    headingMagnet:[],
    accuracy:[]
  };
  motionSubscription;
  orientationSubscription;
  gyroSubscription;

  ngrok:String="";


  constructor(public file: File,public fileTransfer:FileTransfer,
              public deviceMotion: DeviceMotion,public deviceOrient: DeviceOrientation,public gyro:Gyroscope) {

  }
   startSensor(){
     this.motionSubscription = this.deviceMotion.watchAcceleration().subscribe((acceleration: DeviceMotionAccelerationData) => {
       console.log(acceleration);
       this.timestamp.acceleration.push(acceleration.timestamp);
       this.acceleration.x.push(acceleration.x);
       this.acceleration.y.push(acceleration.y);
       this.acceleration.z.push(acceleration.z);
     });
     this.orientationSubscription = this.deviceOrient.watchHeading().subscribe(
       (compassHeading: DeviceOrientationCompassHeading) => {
         console.log(compassHeading);
         this.timestamp.magnetic.push(compassHeading.timestamp);
         this.magnet.headingMagnet.push(compassHeading.magneticHeading);
         this.magnet.trueMagnet.push(compassHeading.trueHeading);
         this.magnet.accuracy.push(compassHeading.headingAccuracy);
       }
     );
     this.gyroSubscription=this.gyro.watch()
       .subscribe((orientation: GyroscopeOrientation) => {
         console.log(orientation.x, orientation.y, orientation.z, orientation.timestamp);
         this.timestamp.orient.push(orientation.timestamp);
         this.orientation.x.push(orientation.x);
         this.orientation.y.push(orientation.y);
         this.orientation.y.push(orientation.y);
       });
  }
  EndSensor(){
     this.motionSubscription.unsubscribe();
     this.orientationSubscription.unsubscribe();
     this.gyroSubscription.unsubscribe();
     this.make_and_uploadCSV()

  }
  make_and_uploadCSV(){
    let id=0;
    let file="ID,Timestamp(acc),AccX,AccY,AccZ,OrientX,OrientY,OrientZ,magnetTrue,magnetHeading,magnetAccuracy,timestamp(orient),timestamp(magn)\n";
    for(let i=0;i<this.timestamp.acceleration.length;i++){
      file+=id+","+this.timestamp.acceleration[i]+","+this.acceleration.x[i]+","+this.acceleration.y[i]+","+this.acceleration.z[i]+""+
          this.orientation.x[i]+","+this.orientation.y[i]+","+this.orientation.z[i]+","+
          this.magnet.trueMagnet[i]+","+this.magnet.headingMagnet[i]+","+this.magnet.accuracy[i]+","+
          this.timestamp.orient[i]+","+this.timestamp.magnetic[i]+","+"\n";
          id++;
    }
    this.file.writeFile("./","sensorData.csv",file,false)
      .then(_=>{
      const file_transfer=this.fileTransfer.create();
      file_transfer.upload("sensorData.csv","http://"+this.ngrok+".ngrok.io/upload")
        .then(_=>{
          this.ResetSensorData();
        });

    });
  }
  ResetSensorData(){
    this.timestamp=null;
    this.acceleration=null;
    this.orientation=null;
    this.magnet=null;
  }

}
