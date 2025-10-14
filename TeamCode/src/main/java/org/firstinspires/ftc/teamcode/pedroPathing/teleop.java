package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "teleop good")
public class teleop extends OpMode {

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
        lf.setPower(gamepad1.left_stick_y + -gamepad1.left_stick_x * 1.1 + gamepad1.right_stick_x);
        lr.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x * 1.1+ gamepad1.right_stick_x);
        rf.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x * 1.1 +gamepad1.right_stick_x);
        rr.setPower(gamepad1.left_stick_y + -gamepad1.left_stick_x * 1.1 + gamepad1.right_stick_x);



    }
}