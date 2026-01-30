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

@Autonomous(name = "Blue Auto FAST + Park", group = "Pedro")
public class blue_auto extends OpMode {

    // ---------- HARDWARE ----------
    DcMotor lf, rf;
    DcMotor lr, rr;
    DcMotor intakerel;
    DcMotor shooter1;

    Servo pusher;
    int sleeppusher = 800;
    int sleeppusher1 = 500;
    int sleeppusher2 = 2500;


    // ---------- PEDRO ----------
    Follower follower;
    Timer actionTimer;

    int state = 0;

    // ---------- POSES ----------
    Pose startPose = new Pose(56, 8, Math.toRadians(90));
    Pose scorePose = new Pose(72, 25, Math.toRadians(165));
    Pose scorePoseAfter = new Pose(72, 25, Math.toRadians(60));
    Pose parkPose = new Pose(60, 40, Math.toRadians(180));

    double pickupHeading = Math.toRadians(180);


    Pose pickup2Start = new Pose(50, 60, pickupHeading);

    Pose pickup2Fast  = new Pose(30, 60, pickupHeading);
    Pose pickup2Final = new Pose(17, 60, pickupHeading);


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
                .addPath(new BezierCurve(scorePose , pickup2Start))
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
                    follower.followPath(toPickup2, 1.0, true);
                    state = 1;
                }
                break;

            // ---------- PICKUP 2 ----------
            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(toPickup2, 1.0, true);
                    state = 2;
                }
                break;

            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(pickup2FastPath, 1.0, true);
                    shooter1.setPower(-1);
                    state = 3;
                }
                break;

            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(pickup2SlowPath, 0.6, true);
                    actionTimer.resetTimer();
                    state = 4;
                }
                break;

            case 4:
                if (!follower.isBusy() && actionTimer.getElapsedTimeSeconds() > 0.25) {
                    follower.followPath(scorePickup2, 1.0, true);
                    shooter1.setPower(0);
                    state = 5;
                }
                break;

            // ---------- ENDGAME PARK ----------
            case 5:
                if (!follower.isBusy()) {
                    sleep(sleeppusher1);
                    intakerel.setPower(-.83);
                    sleep(sleeppusher2);
                    pusher.setPosition(.7);
                    sleep(sleeppusher1);
                    pusher.setPosition(.1);
                    sleep(sleeppusher);
                    follower.followPath(parkPath, 1.0, true);
                    state = 6;
                }
                break;

            case 6:
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
