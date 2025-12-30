
package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
    .mass(7.2)
    .forwardZeroPowerAcceleration(-43.587112)
            .lateralZeroPowerAcceleration(-57.22450985)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.3, 0, 0.001, 0.025))
            .headingPIDFCoefficients(new PIDFCoefficients(0.6, 0, 0.005, 0.025))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.01,0.0,0.001,0.6,0.025))
            .centripetalScaling(0.005);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("rf")
            .rightRearMotorName("rr")
            .leftRearMotorName("lr")
            .leftFrontMotorName("lf")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .xVelocity(51.9736)
           .yVelocity(44.2946);
    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .threeWheelLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)

                .build();
    }
    public static ThreeWheelConstants localizerConstants = new ThreeWheelConstants()
            .forwardTicksToInches(.0020261287634188952)
            .strafeTicksToInches(-.0020538308483531442)
            .turnTicksToInches(.0023361361365676312)
            .leftPodY(8)
            .rightPodY(-8)
            .strafePodX(-7)
            .leftEncoder_HardwareMapName("lr")
            .rightEncoder_HardwareMapName("rr")
            .strafeEncoder_HardwareMapName("lf")
            .leftEncoderDirection(Encoder.REVERSE)
            .rightEncoderDirection(Encoder.REVERSE)
            .strafeEncoderDirection(Encoder.FORWARD);

}
