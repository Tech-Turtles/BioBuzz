package org.firstinspires.ftc.teamcode.opmode.teleop.programs.drivebot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleOpEx;

import java.util.List;

@TeleOp(name = "DriveBotPractice", group = "DriveBot")
public class DriveBotPractice extends TeleOpEx {
    @Override
    public  void loop() {
        List<Double> drive_command = input.getDrive(input.gamepad1);

        double forward = drive_command.get(0);
        double strafe = drive_command.get(1);
        double twist = drive_command.get(2);

        robot.drive.robotCentric(forward, strafe, twist);

        Pose2D position = robot.odometry.position();


        double heading = (double) Math.round(position.getHeading(AngleUnit.RADIANS) * 100) / 100;
        double x = Math.round(position.getX(DistanceUnit.MM));
        double y = Math.round(position.getY(DistanceUnit.MM));

        if (input.getIntake(input.gamepad2)) {
            robot.intake.intake();
        } else if (input.getOuttake(input.gamepad2)) {
            robot.intake.outtake();
        } else {
            robot.intake.stop();
        }

        telemetry.addData("Heading", heading);
        telemetry.addData("X", x);
        telemetry.addData("Y", y);
        telemetry.update();
    }
}
