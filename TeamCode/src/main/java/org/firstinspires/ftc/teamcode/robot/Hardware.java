package org.firstinspires.ftc.teamcode.robot;

import android.view.WindowInsets;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Hardware {

    // HARDWARE DECLARATIONS
    // Declare drive motors
    public DcMotorEx frontLeftDrive, frontRightDrive, rearLeftDrive, rearRightDrive;
    // Declare IMU
    public IMU imu;
    // Declare pinpoint
    public GoBildaPinpointDriver pinpoint;

    // HARDWARE NAMES
    // Define drive motor names
    private static final String frontLeftDriveName = "frontLeftDrive";
    private static final String frontRightDriveName = "frontRightDrive";
    private static final String rearLeftDriveName = "rearLeftDrive";
    private static final String rearRightDriveName = "rearRightDrive";

    // Define IMU name
    private static final String imuName = "IMU";

    // Define GoBuilda Pinpoint name
    public static final String pinpointName = "pinpoint";

    // Positioning of IMU
    private static final RevHubOrientationOnRobot.LogoFacingDirection imuLogoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
    private static final RevHubOrientationOnRobot.UsbFacingDirection imuUsbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
    private static final IMU.Parameters imuPositioning = new IMU.Parameters(new RevHubOrientationOnRobot(imuLogoDirection, imuUsbDirection));

    // Configurations of pinpoint
    // This is the sideways distance of the Forward (x) pod. + is left of center, - is right of center
    private static final double xOffset = 0;
    // This is the forward/back distance of the Strafe (y) pod. + is forward of center, - is backward of center
    private static final double yOffset = 0;
    // Simple unit selection
    DistanceUnit unit = DistanceUnit.METER;

    // CONSTRUCTOR
    public Hardware(HardwareMap map) {
        // HARDWARE DEFINITIONS

        // Define drive motors
        frontLeftDrive = map.get(DcMotorEx.class, Hardware.frontLeftDriveName);
        frontRightDrive = map.get(DcMotorEx.class, Hardware.frontRightDriveName);
        rearLeftDrive = map.get(DcMotorEx.class, Hardware.rearLeftDriveName);
        rearRightDrive = map.get(DcMotorEx.class, Hardware.rearRightDriveName);

        // Define IMU
        imu = map.get(IMU.class, Hardware.imuName);

        // Define GoBuilda Pinpoint
        pinpoint = map.get(GoBildaPinpointDriver.class, Hardware.pinpointName);

        // HARDWARE CONFIGURATIONS

        // Select motors to reverse (might need to change based on how we mount our motors)
        rearRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        // Establish motor modes (run without encoder for now)
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set motor zero power behaviour
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Init IMU based on direction on robot placement
        imu.initialize(Hardware.imuPositioning);

        // Init the pinpoint
        pinpoint.setOffsets(xOffset, yOffset, unit);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        // This method is probably necessary after some testing:
        // pinpoint.setEncoderDirections();
        // For this it's crucial robot is not moving, or else the heading will drift
        pinpoint.resetPosAndIMU();
    }
}
