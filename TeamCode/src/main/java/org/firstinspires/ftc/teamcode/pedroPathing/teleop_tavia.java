package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "teleop tavia")
public class teleop_tavia extends OpMode {

    DcMotor lf, rf;
    DcMotor lr, rr;
   DcMotor intakerel;
   DcMotor shooter1;
    Servo pusher;


    Gamepad currentGamepad2 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();
    int intaketimer = 0;
    int shoottimer = 0;


    @Override
    public void init() {
        lf = hardwareMap.get(DcMotor.class, "lf");
        lr = hardwareMap.get(DcMotor.class, "lr");
        rf = hardwareMap.get(DcMotor.class, "rf");
        rr = hardwareMap.get(DcMotor.class, "rr");
        intakerel = hardwareMap.get(DcMotor.class, "intakerel");
        shooter1 = hardwareMap.get(DcMotor.class, "shooter1");

        pusher = hardwareMap.get(Servo.class, "pusher");







    }

    @Override
    public void loop() {

        lf.setPower((gamepad1.right_stick_y + -gamepad1.left_stick_x * 1.1)*1 + gamepad1.right_stick_x);
        lr.setPower((-gamepad1.right_stick_y + -gamepad1.left_stick_x * 1.1)*1 + gamepad1.right_stick_x);
        rf.setPower((gamepad1.right_stick_y + gamepad1.left_stick_x * 1.1)*1 + gamepad1.right_stick_x);
        rr.setPower((gamepad1.right_stick_y + -gamepad1.left_stick_x * 1.1)*1 + gamepad1.right_stick_x);
        if (gamepad2.a) {
        shooter1.setPower(-.5);
    }

     if (gamepad2.y) {shooter1.setPower(0);
    }
        if (gamepad2.x) {
            intakerel.setPower(-1);
        }
        if (gamepad2.b) {
            intakerel.setPower(0);
        }

        /*
        if (intaketimer == 1) { //position 0 -> 1



            intakerel.setPower(-1);
        if (currentGamepad2.x && !previousGamepad2.x) {
            if (intaketimer == 1) { //position 0 -> 1



                intakerel.setPower(-1);


                intaketimer = 0;

            } else if (intaketimer == 0) { //position 1 -> 0

                intakerel.setPower(0);
                intaketimer = 1;

            }

        }




        if (currentGamepad2.a && !previousGamepad2.a) {
            if (shoottimer == 1) { //position 0 -> 1



                shooter1.setPower(-.8);


                shoottimer = 0;

            } else if (intaketimer == 0) { //position 1 -> 0

                shooter1.setPower(0);
                shoottimer = 1;

            }

        }

*/
    }
}