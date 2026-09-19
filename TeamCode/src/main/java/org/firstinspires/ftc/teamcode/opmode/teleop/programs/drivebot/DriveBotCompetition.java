package org.firstinspires.ftc.teamcode.opmode.teleop.programs.drivebot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.teleop.TeleOpEx;

import java.util.List;

@TeleOp(name = "DriveBotCompetition", group = "DriveBot")
public class DriveBotCompetition extends TeleOpEx {

    // drive robot in teleop
    @Override
    public void teleop_loop() {
        super.teleop_loop();

        List<Double> drive_command = input.getDrive(input.gamepad1);

        double forward = drive_command.get(0);
        double strafe = drive_command.get(1);
        double twist = drive_command.get(2);

        robot.drive.fieldCentric(forward, strafe, twist);
    }

    @Override

    public void endgame_loop() {
        super.endgame_loop();
        teleop_loop();
    }
}
