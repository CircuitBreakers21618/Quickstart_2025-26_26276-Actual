package org.firstinspires.ftc.teamcode.pedroPathing;



import static android.os.SystemClock.sleep;

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

    Servo pusher;
    int sleeppusher = 500;
    int sleeppusher1 = 500;
    int sleeppusher2 = 2500;

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
        lf.setPower((gamepad1.left_stick_y + -gamepad1.right_stick_y * 1.1)*1 + gamepad1.right_stick_x);
        lr.setPower((-gamepad1.left_stick_y + -gamepad1.right_stick_y * 1.1)*1+ gamepad1.right_stick_x);
        rf.setPower((gamepad1.left_stick_y + gamepad1.right_stick_y * 1.1)*1 +gamepad1.right_stick_x);
        rr.setPower((gamepad1.left_stick_y + -gamepad1.right_stick_y * 1.1)*1 + gamepad1.right_stick_x);

        if (gamepad2.x) {
            shooter1.setPower(-.5);
        }

        if (gamepad2.b) {shooter1.setPower(0);
        }
        if (gamepad2.a) {
            intakerel.setPower(-.8);
            sleep(sleeppusher2);
            pusher.setPosition(.7);
            sleep(sleeppusher1);
            pusher.setPosition(.1);
            sleep(sleeppusher);
            shooter1.setPower(-1);
            sleep(sleeppusher2);
            shooter1.setPower(0);
            pusher.setPosition(.7);
            sleep(sleeppusher1);
            pusher.setPosition(.1);
            sleep(sleeppusher1);
            intakerel.setPower(0);


    }}}