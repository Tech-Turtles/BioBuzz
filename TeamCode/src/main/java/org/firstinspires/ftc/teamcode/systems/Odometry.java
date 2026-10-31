package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.Hardware;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class Odometry {
    // Define the three deadwheels
    DcMotorEx leftDeadWheel, rightDeadWheel, perpDeadWheel;
    public static class Constants {
        @Config("Odometry - DeadWheel Direction Constants")
        public static class Directions {
            public static float leftDeadWheelDirection = 1;
            public static float rightDeadWheelDirection = -1;
            public static float perpDeadWheelDirection = 1;
        }
        @Config("Odometry - DeadWheel Hardware Constants")
        public static class Hardware {
            public static float ticksPerMM = (float) (2000.0 / (Math.PI * 32.0));

        }
        @Config("Odometry - DeadWheel Mounting Constants")
        public static class Mounting {
            public static double trackWidth = 158.75; // distance between left and right pods
            public static double perpOffset = -146.05; // offset of perp pod from center
        }
    }

    Pose2D position;

    private double lastL, lastR, lastP;
    public Odometry(Robot robot) {
        // Define the deadwheels
        leftDeadWheel = robot.hardware.frontLeftDrive;
        rightDeadWheel = robot.hardware.frontRightDrive;
        perpDeadWheel = robot.hardware.rearLeftDrive;
        // get last values
        lastL = (Constants.Directions.leftDeadWheelDirection * leftDeadWheel.getCurrentPosition());
        lastR = (Constants.Directions.rightDeadWheelDirection * rightDeadWheel.getCurrentPosition());
        lastP = (Constants.Directions.perpDeadWheelDirection * perpDeadWheel.getCurrentPosition());
        // get position from robot
        position = robot.start;
    }

    public Pose2D position() {
        double l = (Constants.Directions.leftDeadWheelDirection * leftDeadWheel.getCurrentPosition());
        double r = (Constants.Directions.rightDeadWheelDirection * rightDeadWheel.getCurrentPosition());
        double p = (Constants.Directions.perpDeadWheelDirection * perpDeadWheel.getCurrentPosition());

        double dL = (l - lastL) / Constants.Hardware.ticksPerMM;
        double dR = (r - lastR) / Constants.Hardware.ticksPerMM;
        double dP = (p - lastP) / Constants.Hardware.ticksPerMM;
        lastL = l; lastR = r; lastP = p;

        double dTheta = (dR - dL) / Constants.Mounting.trackWidth;
        double dF = (dL + dR) / 2.0;
        double dS = dP - Constants.Mounting.perpOffset * dTheta;

        double dx, dy;
        if (Math.abs(dTheta) < 1e-9) {
            dx = dF; dy = dS;
        } else {
            double s = Math.sin(dTheta) / dTheta;
            double c = (1 - Math.cos(dTheta)) / dTheta;
            dx = s * dF - c * dS;
            dy = c * dF + s * dS;
        }

        double changeInX = dx * Math.cos(position.getHeading(AngleUnit.RADIANS)) - dy * Math.sin(position.getHeading(AngleUnit.RADIANS));
        double changeInY = dx * Math.sin(position.getHeading(AngleUnit.RADIANS)) + dy * Math.cos(position.getHeading(AngleUnit.RADIANS));
        double changeInHeading = dTheta;

        position = new Pose2D(DistanceUnit.MM, position.getX(DistanceUnit.MM) + changeInX, position.getY(DistanceUnit.MM) + changeInY, AngleUnit.RADIANS, position.getHeading(AngleUnit.RADIANS) + changeInHeading);

        return (position);

    }

    public Pose2D getStartPosition() {
        return new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.RADIANS, 0);
    }
}
