package org.firstinspires.ftc.teamcode.pedroPathing;

import static android.os.SystemClock.sleep;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Red Auto FAST + Park", group = "Pedro")
public class red_auto extends OpMode {

    // ---------- HARDWARE ----------
    DcMotor lf, rf;
    DcMotor lr, rr;
    DcMotor intakerel;
    DcMotor shooter1;

    Servo pusher;
    int sleeppusher = 500;
    int sleeppusher1 = 500;
    int sleeppusher2 = 1500;


    // ---------- PEDRO ----------
    Follower follower;
    Timer actionTimer;

    int state = 0;

    // ---------- POSES ----------
    Pose startPose = new Pose(144 - 56, 8, Math.toRadians(90));       // 88, 8
    Pose scorePose = new Pose(144 - 72, 22.5, Math.toRadians(180-170));
    Pose scorePoseAfter = new Pose(144-72, 22.5, Math.toRadians(180-85));// 72, 22.5, heading mirrored
    Pose parkPose = new Pose(144 - 60, 10, Math.toRadians(0));        // 84, 10
    double pickupHeading = Math.toRadians(0);                        // mirrored from 195 -> -15
    Pose pickup2Start = new Pose(144 - 50, 55, pickupHeading);         // 94, 55
    Pose pickup2Fast  = new Pose(144 - 36, 60, pickupHeading);         // 108, 60
    Pose pickup2Final = new Pose(144 - 27, 60, pickupHeading);         // 117, 60// 84, 30, heading mirrored


    // ---------- PATHS ----------
    PathChain scorePreload;
    PathChain toPickup2, pickup2FastPath, pickup2SlowPath, scorePickup2;
    PathChain parkPath;

    @Override
    public void init() {

        intakerel = hardwareMap.get(DcMotor.class, "intakerel");
        shooter1 = hardwareMap.get(DcMotor.class, "shooter1");

        pusher = hardwareMap.get(Servo.class, "pusher");

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);

        actionTimer = new Timer();

        buildPaths();
    }

    private void buildPaths() {

        // ---------- PRELOAD ----------
        scorePreload = follower.pathBuilder()
                .addPath(new BezierCurve(startPose, scorePose))
                .setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading())
                .build();

        // ---------- PICKUP 1 ----------
        // ---------- PICKUP 2 ----------
        toPickup2 = follower.pathBuilder()
                .addPath(new BezierCurve(scorePose, pickup2Start))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickupHeading)
                .build();

        pickup2FastPath = follower.pathBuilder()
                .addPath(new BezierCurve(pickup2Start, pickup2Fast))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        pickup2SlowPath = follower.pathBuilder()
                .addPath(new BezierCurve(pickup2Fast, pickup2Final))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        scorePickup2 = follower.pathBuilder()
                .addPath(new BezierCurve(pickup2Final, scorePoseAfter))
                .setLinearHeadingInterpolation(pickupHeading, scorePoseAfter.getHeading())
                .build();

        // ---------- PARK ----------
        parkPath = follower.pathBuilder()
                .addPath(new BezierCurve(scorePoseAfter, parkPose))
                .setLinearHeadingInterpolation(scorePoseAfter.getHeading(), parkPose.getHeading())
                .build();
    }

    @Override
    public void start() {
        state = 0;
        follower.followPath(scorePreload, 1.0, true);
    }

    @Override
    public void loop() {

        follower.update();

        switch (state) {

            // ---------- PRELOAD ----------
            case 0:
                if (!follower.isBusy()) {
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
                    follower.followPath(toPickup2, 1.0, true);
                    state = 1;
                }
                break;

            // ---------- PICKUP 1 ----------
            // ---------- PICKUP 2 ----------
            case 2:
                if (!follower.isBusy()) {
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
                    follower.followPath(toPickup2, 1.0, true);
                    state = 3;
                }
                break;

            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(pickup2FastPath, 1.0, true);
                    shooter1.setPower(-1);
                    state = 4;
                }
                break;

            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(pickup2SlowPath, 0.6, true);
                    actionTimer.resetTimer();
                    state = 5;
                }
                break;

            case 5:
                if (!follower.isBusy() && actionTimer.getElapsedTimeSeconds() > 0.25) {
                    follower.followPath(scorePickup2, 1.0, true);
                    shooter1.setPower(0);
                    state = 6;
                }
                break;

            // ---------- ENDGAME PARK ----------
            case 6:
                if (!follower.isBusy()) {
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
                    follower.followPath(parkPath, 1.0, true);
                    state = 7;
                }
                break;

            case 7:
                // AUTO COMPLETE – PARKED
                break;
        }

        telemetry.addData("State", state);
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", Math.toDegrees(follower.getPose().getHeading()));
        telemetry.update();
    }
}
