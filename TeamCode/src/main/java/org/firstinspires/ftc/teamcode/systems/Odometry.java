package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
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
    }

    public Pose2D getPosition(Pose2D pos) {
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

        double changeInX = dx * Math.cos(pos.getHeading(AngleUnit.RADIANS)) - dy * Math.sin(pos.getHeading(AngleUnit.RADIANS));
        double changeInY = dx * Math.sin(pos.getHeading(AngleUnit.RADIANS)) + dy * Math.cos(pos.getHeading(AngleUnit.RADIANS));
        double changeInHeading = dTheta;

        Pose2D position = new Pose2D(DistanceUnit.MM, pos.getX(DistanceUnit.MM) + changeInX, pos.getY(DistanceUnit.MM) + changeInY, AngleUnit.RADIANS, pos.getHeading(AngleUnit.RADIANS) + changeInHeading);

        return (position);

    }

    public Pose2D getStartPosition() {
        return new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.RADIANS, 0);
    }

    public Pose2D resetHeading(Pose2D position) {
        return new Pose2D(DistanceUnit.MM, position.getX(DistanceUnit.MM), position.getY(DistanceUnit.MM), AngleUnit.RADIANS, 0);
    }
}
