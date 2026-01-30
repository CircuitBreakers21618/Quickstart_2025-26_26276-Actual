package org.firstinspires.ftc.teamcode.pedroPathing;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class shoot {

    DcMotor lf, rf;
    DcMotor lr, rr;
    DcMotor intakerel;
    DcMotor shooter1;

    Servo pusher;
    int sleeppusher = 800;
    int sleeppusher1 = 500;
    int sleeppusher2 = 2500;

    public void init(HardwareMap hwMap){
        intakerel = hwMap.get(DcMotor.class, "intakerel");
        shooter1 = hwMap.get(DcMotor.class, "shooter1");

        pusher = hwMap.get(Servo.class, "pusher");


    }

    public void shoot2(){
        sleep(sleeppusher1);
        intakerel.setPower(-.83);
        sleep(sleeppusher2);
        pusher.setPosition(.7);
        sleep(sleeppusher1);
        pusher.setPosition(.1);
        sleep(sleeppusher);
        shooter1.setPower(-1);
        sleep(sleeppusher2);
        shooter1.setPower(0);
        sleep(sleeppusher2);
        pusher.setPosition(.7);
        sleep(sleeppusher1);
        pusher.setPosition(.1);
        sleep(sleeppusher1);
        intakerel.setPower(0);


    }
}
