package org.firstinspires.ftc.teamcode.opmode.autonomous.programs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.autonomous.AutonomousEx;
import org.opencv.core.Mat;

import java.util.Arrays;
import java.util.List;


@Autonomous(name = "ShootBot", group = "Bot")
public class ShootBot extends AutonomousEx {


    // all matchstates for this auto
    public enum MatchState {
        SHOOT,
        POSITION,
        PARK,
        OVER
    }


    // all constants for this auto, pay attention to naming convention "Auto name - Auto state"
    public static class Constants {
        @Config("Shoot Autonomous - Position Constants")
        public static class Position {
            public static double time = 2.3;
            public static double forward = -1.0;
            public static double strafe = -2;
            public static double twist = -0.4;
        }

        @Config("Shoot Autonomous - Shoot Constants")
        public static class Shoot {
            public static double time = 12;
        }

        // TODO: Rearrange update auto states for ShootBot

        @Config("Shoot Autonomous - Park Constants")
        public static class Park {
            public static double time = 1.3;
            public static double forward = 0.4;
            public static double strafe = 0;
            public static double twist = 0;
        }
    }

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

    // state handling
    @Override
    public void stateMachine() {
        switch (matchState) {
            case POSITION:
                if (timer.seconds() <= Constants.Position.time) {
                    position();
                } else {
                    nextState();
                }
                break;

            case SHOOT:
                if (timer.seconds() <= Constants.Shoot.time) {
                    shoot();
                } else {
                    nextState();
                }
                break;

            case PARK:
                if (timer.seconds() <= Constants.Park.time) {
                    park();
                } else {
                    nextState();
                }
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
                // stop all systems
                robot.stopAll();
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
        robot.drive.robotCentric(Constants.Position.forward, Constants.Position.strafe, Constants.Position.twist);
        // start spinning the shooter up
        robot.shooter.run();
    }

    public void shoot() {
        // run intake
        robot.intake.intake();
        // run shooter
        robot.shooter.run();
        // smart run the transfer (only if shooter is up to speed)
        robot.transfer.smartRun();
    }

    public void park() {
        robot.drive.robotCentric(Constants.Park.forward, Constants.Park.strafe, Constants.Park.twist);
    }
}


