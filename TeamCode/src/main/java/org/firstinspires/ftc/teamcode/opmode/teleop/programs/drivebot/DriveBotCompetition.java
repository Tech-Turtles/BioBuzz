package org.firstinspires.ftc.teamcode.opmode.teleop.programs.drivebot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleOpCompetition;

import java.util.List;

@TeleOp(name = "DriveBot Competition", group = "DriveBot")
public class DriveBotCompetition extends TeleOpCompetition {

    // drive robot in teleop
    @Override
    public void teleop_loop() {
        super.teleop_loop();

        List<Double> drive_command = input.getDrive(input.gamepad1);

        double forward = drive_command.get(0);
        double strafe = drive_command.get(1);
        double twist = drive_command.get(2);

        robot.drive.fieldCentric(forward, strafe, twist);

        Pose2D position = robot.odometry.position();


        double heading = (double) Math.round(position.getHeading(AngleUnit.RADIANS) * 100) / 100;
        double x = Math.round(position.getX(DistanceUnit.MM));
        double y = Math.round(position.getY(DistanceUnit.MM));

        telemetry.addData("Heading", heading);
        telemetry.addData("X", x);
        telemetry.addData("Y", y);
        telemetry.update();
    }

    @Override

    public void endgame_loop() {
        super.endgame_loop();
        teleop_loop();
    }
}