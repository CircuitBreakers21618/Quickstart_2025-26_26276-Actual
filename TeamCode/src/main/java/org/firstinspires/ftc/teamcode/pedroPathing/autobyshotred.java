package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@Autonomous(name = "SP not needed", group = "2024-25 SP")
public class autobyshotred extends LinearOpMode {

    DcMotor lf, rf;
    DcMotor lr, rr;
    int sleepTime = 100;
    int sleepTime2 = 500;
    @Override
    public void runOpMode() throws InterruptedException {
        lf = hardwareMap.get(DcMotor.class, "lf");
        lr = hardwareMap.get(DcMotor.class, "lr");
        rf = hardwareMap.get(DcMotor.class, "rf");
        rr = hardwareMap.get(DcMotor.class, "rr");
        waitForStart();
        lf.setPower(-1);
        lr.setPower(1);
        rf.setPower(-1);
        rr.setPower(-1);
        sleep(sleepTime);
        lf.setPower(0);
        lr.setPower(0);
        rf.setPower(-1);
        rr.setPower(-1);
        sleep(sleepTime);
        lf.setPower(-1);
        lr.setPower(1);
        rf.setPower(-1);
        rr.setPower(-1);
        sleep(sleepTime2);
        lf.setPower(0);
        lr.setPower(0);
        rf.setPower(0);
        rr.setPower(0);


    }
}

