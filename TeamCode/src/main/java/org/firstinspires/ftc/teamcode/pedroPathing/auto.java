package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class auto extends OpMode {
    DcMotor lf, rf;
    DcMotor lr, rr;
    @Override
    public void init() {
        lf = hardwareMap.get(DcMotor.class, "lf");
        lr = hardwareMap.get(DcMotor.class, "lr");
        rf = hardwareMap.get(DcMotor.class, "rf");
        rr = hardwareMap.get(DcMotor.class, "rr");
    }

    @Override
    public void loop() {
        lf.setPower(1);
        lr.setPower(1);
        rf.setPower(1);
        rr.setPower(1);
        wait(500);
        lf.setPower(0);
        lr.setPower(0);
        rf.setPower(0);
        rr.setPower(0);

    }
}
