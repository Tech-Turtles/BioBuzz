package org.firstinspires.ftc.teamcode.systems;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.Hardware;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.utility.commands.DriveCommand;

public class Drive {

    // Declare robot
    public Robot robot;

    public Drive(Robot robot) {
        // Define robot
        this.robot = robot;
    }

    /**
     * @author Brantley
     */

    public void robotCentric(DriveCommand command) {
        // Mecanum drive here
        mecanum(command.getForward(), command.getStrafe(), command.getTwist());
    }

    /**
     * @author Brantley
     * @param position the current position of the bot
     */

    public void fieldCentric(DriveCommand command, Pose2D position) {
        // Get the heading of the robot
        double heading = position.getHeading(AngleUnit.RADIANS);
        // "rotate" forward and strafe values based on twist
        // heading is from pinpoint.getHeading(AngleUnit.RADIANS)
        double rotatedForward = command.getForward() * Math.cos(-heading) - command.getStrafe() * Math.sin(-heading);
        double rotatedStrafe = command.getForward() * Math.sin(-heading) + command.getStrafe() * Math.cos(-heading);

        // mecanum command now
        mecanum(rotatedForward, rotatedStrafe, command.getTwist());
    }

    /**
     * @author Brantley
     * @param forward how much forward
     * @param strafe how much right
     * @param twist how much right turn
     */

    public void mecanum(double forward, double strafe, double twist) {
        // Get the raw power for each wheel
        double fl = (forward + strafe + twist);
        double fr = (forward - strafe - twist);
        double rl = (forward - strafe + twist);
        double rr = (forward + strafe - twist);

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
