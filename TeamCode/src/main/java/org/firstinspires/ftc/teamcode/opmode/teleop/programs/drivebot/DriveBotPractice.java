package org.firstinspires.ftc.teamcode.opmode.teleop.programs.drivebot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
    }
}
