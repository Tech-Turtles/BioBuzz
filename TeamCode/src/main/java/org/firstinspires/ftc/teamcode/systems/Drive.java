package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.Hardware;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class Drive {

    // Declare robot
    public Robot robot;

    // Driving constants
    public static class DriveConsts {
        // Driving speeds
        @Config("Drive Speeds")
        public static class DriveSpeeds {
            public static double forward = 1.0;
            public static double strafe = 1.2;
            public static double twist = 1.4;
        }
        // Wheel speeds
        @Config("Wheel Speeds")
        public static class WheelSpeeds {
            public static double fl = 1.00;
            public static double fr = 1.00;
            public static double rl = 1.00;
            public static double rr = 1.00;
        }
    }

    public Drive(Robot robot) {
        // Define robot
        this.robot = robot;
    }

    /**
     * @author Brantley
     * @param forward how forward the robot will be moving
     * @param strafe how much the robot will strafe right
     * @param twist how much the robot will rotate right
     */

    public void robotCentric(double forward, double strafe, double twist) {
        // Mecanum drive here
        mecanum(forward, strafe, twist);
    }

    /**
     * @author Brantley
     * @param forward how forward the robot will be moving
     * @param strafe how much to the right the robot will move
     * @param twist how much to the right the robot will turn
     * @param position the current position of the bot
     */

    public void fieldCentric(double forward, double strafe, double twist, Pose2D position) {
        // Get the heading of the robot
        double heading = position.getHeading(AngleUnit.RADIANS);
        // "rotate" forward and strafe values based on twist
        // heading is from pinpoint.getHeading(AngleUnit.RADIANS)
        double rotatedForward = forward * Math.cos(heading) - strafe * Math.sin(heading);
        double rotatedStrafe = forward * Math.sin(heading) + strafe * Math.cos(heading);

        // mecanum drive now
        mecanum(rotatedForward, rotatedStrafe, twist);
    }

    /**
     * @author Brantley
     * @param forward how much forward
     * @param strafe how much right
     * @param twist how much right turn
     */

    public void mecanum(double forward, double strafe, double twist) {
        // tune each value
        forward *= DriveConsts.DriveSpeeds.forward;
        strafe *= DriveConsts.DriveSpeeds.strafe;
        twist *= DriveConsts.DriveSpeeds.twist;
        // Get the raw power for each wheel
        double fl = DriveConsts.WheelSpeeds.fl * (forward + strafe + twist);
        double fr = DriveConsts.WheelSpeeds.fr * (forward - strafe - twist);
        double rl = DriveConsts.WheelSpeeds.rl * (forward - strafe + twist);
        double rr = DriveConsts.WheelSpeeds.rr * (forward + strafe - twist);

        // Get the scale factor
        double scale = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(twist), 1.0);

        // Define hardware for easier access
        Hardware hardware = robot.hardware;
        // Set motor powers
        hardware.frontLeftDrive.setPower(fl / scale);
        hardware.frontRightDrive.setPower(fr / scale);
        hardware.rearLeftDrive.setPower(rl / scale);
        hardware.rearRightDrive.setPower(rr / scale);
    }

    /**
     * Stops all motors
     * @author Brantley
     */

    public void stop() {
        // Define hardware for easier access
        Hardware hardware = robot.hardware;
        // Stop all motors
        hardware.frontLeftDrive.setPower(0);
        hardware.frontRightDrive.setPower(0);
        hardware.rearLeftDrive.setPower(0);
        hardware.rearRightDrive.setPower(0);
    }
}
