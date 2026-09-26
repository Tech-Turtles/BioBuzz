package org.firstinspires.ftc.teamcode.opmode.teleop.programs.shooterBot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.OpModeEx;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleOpEx;

import java.util.List;

@TeleOp(name = "ShooterBot", group = "ShooterBot")
public class ShooterBot extends TeleOpEx {

    // drive robot in teleop
    @Override
    public void teleop_loop() {
        super.teleop_loop();

        double shooterCommand = input.getShoot(input.gamepad1);

        robot.shooter.setSpeed(shooterCommand);
    }

    @Override

    public void endgame_loop() {
        super.endgame_loop();
        teleop_loop();
    }
}