package org.firstinspires.ftc.teamcode.opmode.autonomous.programs;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.autonomous.AutonomousEx;
import org.firstinspires.ftc.teamcode.systems.Shooter;
import org.firstinspires.ftc.teamcode.utility.commands.DriveCommand;


@Autonomous(name = "ShootBot", group = "Bot")
public class ShootBot extends AutonomousEx {


    // all matchstates for this auto
    public enum MatchState {
        SHOOT,
        POSITION,
        PARK,
        OVER
    }

    public float timeAllowed = 30;


    // all constants for this auto, pay attention to naming convention "Auto name - Auto state"

    @Override
    public Pose2D setStart() {
        return (new Pose2D(DistanceUnit.INCH, -15, -60, AngleUnit.DEGREES, -90));
    }
    public static class Constants {
        @Config("Shoot Autonomous - Position Constants")
        public static class Position {
            public static double x = -40;
            public static double y = -38;
        }

        @Config("Shoot Autonomous - Shoot Constants")
        public static class Shoot {
            public static double time = 12;
        }

        // TODO: Rearrange update auto states for ShootBot

        @Config("Shoot Autonomous - Park Constants")
        public static class Park {
            public static double x = -60;
            public static double y = 30;
        }
    }

    double coeff;

    // to hold matchstate
    public MatchState matchState;

    // setup
    @Override
    public void init() {
        // run parent
        super.init();
        // assuming blue alliance
        // set matchstate
        matchState = MatchState.SHOOT;
    }

    @Override
    public void init_loop() {
        super.init_loop();

        if (alliance == Alliance.RED) {
            coeff = -1;
        } else if (alliance == Alliance.BLUE) {
            coeff = 1;
        }
    }

    // state handling
    @Override
    public void stateMachine() {
        switch (matchState) {
            case POSITION:
                position();
                break;

            case SHOOT:
                if (timer.seconds() <= Constants.Shoot.time) {
                    shoot();
                } else {
                    nextState();
                }
                break;

            case PARK:
                park();
                break;

            case OVER:
                stop();
                break;
        }
    }

    @Override
    public void nextState() {
        // reset timer
        timer.reset();
        // stop all systems
        robot.stopAll();
        // state machine
        switch (matchState) {
            case POSITION:
                // set matchstate to next
                matchState = MatchState.PARK;
                // break code
                break;

            case SHOOT:
                // update matchstate
                matchState = MatchState.POSITION;
                // break code
                break;

            case PARK:
                // update matchstate
                matchState = MatchState.OVER;
                // brk code
                break;
        }
    }

    // actual action logic, not just states or values
    public void position() {
        // dashboard
        dashboard();

        if (robot.drive.straightToXY(robot.position, new Pose2D(DistanceUnit.INCH, coeff * Constants.Position.x, coeff * Constants.Position.y, AngleUnit.RADIANS, 0))) {
            nextState();
        }
    }


    public void shoot() {
        // run intake
        robot.intake.intake(1);
        // run shooter
        robot.shooter.run();
        // smart run the transfer (only if shooter is up to speed)
        robot.transfer.smartRun();
        // dashboard
        dashboard();
    }

    public void park() {
        // dashboard
        dashboard();

        if (robot.drive.straightToXY(robot.position, new Pose2D(DistanceUnit.INCH, coeff * Constants.Park.x, coeff * Constants.Park.y, AngleUnit.RADIANS, 0))) {
            nextState();
        }
    }

    // dashboard method
    @Override
    public void dashboard() {
        // create the stuff
        TelemetryPacket packet = new TelemetryPacket();
        Canvas fieldOverlay = packet.fieldOverlay();

        // shooter data
        packet.put("Shooter RPM", robot.shooter.getRPM());
        packet.put("Shooter Target", Shooter.ShooterConsts.speed);
        packet.put("Max RPM", robot.hardware.shooterMotor.getMotorType().getMaxRPM());

        telemetry.addData("Shooter RPM", robot.shooter.getRPM());
        telemetry.addData("Shooter Target", Shooter.ShooterConsts.speed);

        telemetry.addData("State", matchState);
        telemetry.addData("Alliance", alliance);

        packet.put("State", matchState);
        packet.put("Alliance", alliance);

        // send the telemetry packet
        dashboard.sendTelemetryPacket(packet);
    }
}


