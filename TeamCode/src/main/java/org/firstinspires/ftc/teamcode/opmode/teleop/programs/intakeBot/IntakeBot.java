package org.firstinspires.ftc.teamcode.opmode.teleop.programs.intakeBot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.OpModeEx;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleOpEx;

import java.util.List;

@TeleOp(name = "IntakeBot", group = "IntakeBot")
public class IntakeBot extends TeleOpEx {

    // drive robot in teleop
    @Override
    public void teleop_loop() {
        super.teleop_loop();

        boolean intakeCommand = input.getIntake(input.gamepad2);
        boolean outtakeCommand = input.getOuttake(input.gamepad2);

        if (intakeCommand) {
            robot.intake.intake();
        } else if (outtakeCommand) {
            robot.intake.outtake();
        } else {
            robot.intake.stop();
        }
    }

    @Override

    public void endgame_loop() {
        super.endgame_loop();
        teleop_loop();
    }
}