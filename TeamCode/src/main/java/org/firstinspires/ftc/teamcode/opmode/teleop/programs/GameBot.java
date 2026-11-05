package org.firstinspires.ftc.teamcode.opmode.teleop.programs;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.teleop.TeleOpEx;
import org.firstinspires.ftc.teamcode.systems.Shooter;
import org.firstinspires.ftc.teamcode.utility.commands.IntakeCommand;

@TeleOp(name = "GameBot", group = "Bot")
public class GameBot extends TeleOpEx {

    @Override
    public void loop() {
        // run parent logic
        super.loop();

        // drive code
        robot.drive.fieldCentric(input.getDrive(gamepad1), robot.position);

        // intake code
        robot.intake.smartIntake(input.getIntake(gamepad2));

        // shooter code
        if (input.getShooter(gamepad2)) {
            robot.shooter.run();
        } else {
            robot.shooter.stop();
        }
        robot.transfer.smartRun();

        // rpm code
        if (gamepad2.dpadUpWasPressed()) {
            Shooter.ShooterConsts.speed += 50;
        } else if (input.gamepad2.dpadDownWasPressed()) {
            Shooter.ShooterConsts.speed -= 50;
        }

        // kicker code
        if (gamepad1.right_trigger_pressed) {
            robot.kicker.setPositionOut();
        } else {
            robot.kicker.setPositionIn();
        }

        // odometry code
        if (input.getResetPosition(gamepad1)) {
            robot.position = robot.odometry.resetHeading(robot.position);
        }
    }

    @Override
    public void dashboard() {
        // run parent logic: draw the robot on the field
        super.dashboard();

        // create the stuff
        TelemetryPacket packet = new TelemetryPacket();

        // dashboard data
        packet.put("Shooter RPM", robot.shooter.getRPM());
        packet.put("Shooter Target", Shooter.ShooterConsts.speed);

        // telemetry data
        telemetry.addData("Shooter RPM", robot.shooter.getRPM());
        telemetry.addData("Shooter Target", Shooter.ShooterConsts.speed);

        // send the packet
        dashboard.sendTelemetryPacket(packet);
    }
}