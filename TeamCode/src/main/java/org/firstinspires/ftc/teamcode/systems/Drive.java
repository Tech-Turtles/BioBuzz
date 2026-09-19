package org.firstinspires.ftc.teamcode.systems;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Drive {

    // Declare hardware
    public Hardware hardware;
    public Odometry odometry;

    public static class DriveSpeeds {
        double forward, strafe, twist;
    }

    // set speed modifiers
    public Drive.DriveSpeeds speeds = new Drive.DriveSpeeds();

    public Drive(Hardware hardware, Odometry odometry) {
        // Define hardware
        this.hardware = hardware;
        this.odometry = odometry;

        // setup speed multipliers
        speeds.forward = 1.0;
        speeds.strafe = 1.0;
        speeds.twist = 1.0;
    }

    public void robotCentric(double forward, double strafe, double twist) {
        // Mecanum drive here
        mecanum(forward, strafe, twist);
    }

    public void fieldCentric(double forward, double strafe, double twist) {
        // Get the heading of the robot
        double heading = odometry.position().getHeading(AngleUnit.RADIANS);
        // "rotate" forward and strafe values based on twist
        // heading is from pinpoint.getHeading(AngleUnit.RADIANS)
        double rotatedForward = forward * Math.cos(heading) + strafe * Math.sin(heading);
        double rotatedStrafe = forward * Math.sin(heading) + strafe * Math.cos(heading);

        // mecanum drive now
        mecanum(rotatedForward, rotatedStrafe, twist);
    }

    public void mecanum(double forward, double strafe, double twist) {
        // Get the raw power for each wheel
        double fl = forward + strafe + twist;
        double fr = forward - strafe - twist;
        double rl = forward - strafe + twist;
        double rr = forward + strafe - twist;

        // Get the scale factor
        double scale = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(twist), 1.0);

        // Set motor powers
        hardware.frontLeftDrive.setPower(fl / scale);
        hardware.frontRightDrive.setPower(fr / scale);
        hardware.rearLeftDrive.setPower(rl / scale);
        hardware.rearRightDrive.setPower(rr / scale);
    }

    public void brake() {
        hardware.frontLeftDrive.setPower(0);
        hardware.frontRightDrive.setPower(0);
        hardware.rearLeftDrive.setPower(0);
        hardware.rearRightDrive.setPower(0);
    }
}
