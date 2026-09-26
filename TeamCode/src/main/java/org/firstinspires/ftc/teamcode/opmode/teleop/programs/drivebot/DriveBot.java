package org.firstinspires.ftc.teamcode.opmode.teleop.programs.drivebot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleOpEx;

import java.util.List;

@TeleOp(name = "DriveBot", group = "DriveBot")
public class DriveBot extends TeleOpEx {

    // drive robot in teleop
    @Override
    public void teleop_loop() {
        super.teleop_loop();

        List<Double> drive_command = input.getDrive(input.gamepad1);

        double forward = drive_command.get(0);
        double strafe = drive_command.get(1);
        double twist = drive_command.get(2);

        robot.drive.robotCentric(forward, strafe, twist);


        double heading = (double) Math.round(position.getHeading(AngleUnit.DEGREES) * 10) / 10;
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