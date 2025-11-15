package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "teleop lizzy")
public class teleop extends OpMode {

    DcMotor lf, rf;
    DcMotor lr, rr;
    DcMotor intakerel;
    DcMotor shooter1;
    DcMotor shooter2;
    Servo pusher;


    @Override
    public void init() {
        lf = hardwareMap.get(DcMotor.class, "lf");
        lr = hardwareMap.get(DcMotor.class, "lr");
        rf = hardwareMap.get(DcMotor.class, "rf");
        rr = hardwareMap.get(DcMotor.class, "rr");
        intakerel = hardwareMap.get(DcMotor.class, "intakerel");
        shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
        shooter2 = hardwareMap.get(DcMotor.class, "shooter2");
        pusher = hardwareMap.get(Servo.class, "pusher");


    }

    @Override
    public void loop() {
        lf.setPower(gamepad1.left_stick_y + -gamepad1.right_stick_y * 1.1 + gamepad1.right_stick_x);
        lr.setPower(-gamepad1.left_stick_y + -gamepad1.right_stick_y * 1.1+ gamepad1.right_stick_x);
        rf.setPower(gamepad1.left_stick_y + gamepad1.right_stick_y * 1.1 +gamepad1.right_stick_x);
        rr.setPower(gamepad1.left_stick_y + -gamepad1.right_stick_y * 1.1 + gamepad1.right_stick_x);
        if (gamepad1.a) {

            shooter1.setPower(.5);
            shooter2.setPower(-.5);
        }

        if (gamepad1.y) {

            shooter1.setPower(0);
            shooter2.setPower(0);
        }


        if (gamepad1.x) {
            intakerel.setPower(-1);
        }

        if (gamepad1.b) {
            intakerel.setPower(0);
        }

        if(gamepad1.right_bumper){
            pusher.setPosition(.5);
        }


        if(gamepad1.left_bumper){
            pusher.setPosition(0);
        }


    }
}