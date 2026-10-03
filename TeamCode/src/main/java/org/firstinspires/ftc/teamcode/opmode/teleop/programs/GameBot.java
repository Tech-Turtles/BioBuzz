package org.firstinspires.ftc.teamcode.opmode.teleop.programs;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.lynx.LynxDcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleOpEx;
import org.firstinspires.ftc.teamcode.systems.Shooter;

import java.util.List;

@TeleOp(name = "GameBot", group = "Bot")
public class GameBot extends TeleOpEx {

    // drive robot in teleop
    @Override
    public void teleop_loop() {
        // run parent logic
        super.teleop_loop();

        // get list for driving
        List<Double> driveCommand = input.getDrive(input.gamepad1);
        // assign values from list
        double forward = driveCommand.get(0);
        double strafe = driveCommand.get(1);
        double twist = driveCommand.get(2);
        // now drive robot centric
        robot.drive.robotCentric(forward, strafe, twist);

        // passively intake based on drive commands
        robot.intake.intake(input.getIntakeIdle(input.gamepad1));
        // now actively intake from driver 2
        double intakeCommand = input.getIntake(input.gamepad2);
        // outtake
        if (intakeCommand > 0) {
            robot.intake.intake(intakeCommand);
        } else if (intakeCommand < 0) {
            robot.intake.outtake(intakeCommand);
        }

        // now run the shooter if necessary
        if (input.getShooter(input.gamepad2)) {
            robot.shooter.run();
        } else {
            robot.shooter.stop();
        }

        if (gamepad1.dpad_down) {
            Shooter.ShooterConsts.speed += 2;
        } else if (gamepad1.dpad_up) {
            Shooter.ShooterConsts.speed -= 2;
        }

        // update dashboard
        dashboard();
    }

    @Override
    public void endgame_loop() {
        super.endgame_loop();
        teleop_loop();
    }

    public void dashboard() {
        // create the stuff
        TelemetryPacket packet = new TelemetryPacket();
        Canvas fieldOverlay = packet.fieldOverlay();

        // get the odometry data
        double x = position.getX(DistanceUnit.INCH);
        double y = position.getY(DistanceUnit.INCH);
        double heading = position.getHeading(AngleUnit.RADIANS);

        // get arrow x and y
        double robotRadius = 9;
        double arrowX = x + robotRadius * Math.cos(heading);
        double arrowY = y + robotRadius * Math.sin(heading);

        // draw the robot's vector on the field
        fieldOverlay.setStrokeWidth(1);
        fieldOverlay.setStroke("#3F51B5");
        fieldOverlay.strokeCircle(x, y, robotRadius);
        fieldOverlay.strokeLine(x, y, arrowX, arrowY);

        // numerical telemetry
        packet.put("x (mm)", x);
        packet.put("y (mm)", y);
        packet.put("heading (deg)", Math.toDegrees(heading));

        // shooter data
        packet.put("Shooter RPM", robot.shooter.getRPM());
        packet.put("Shooter Target", Shooter.ShooterConsts.speed);
        packet.put("Ticks per Rotation", robot.hardware.shooterMotor.getMotorType().getTicksPerRev());
        packet.put("Max RPM", robot.hardware.shooterMotor.getMotorType().getMaxRPM());

        telemetry.addData("Shooter RPM", robot.shooter.getRPM());
        telemetry.addData("Shooter Target", Shooter.ShooterConsts.speed);

        // send the telemetry packet
        dashboard.sendTelemetryPacket(packet);
    }
}