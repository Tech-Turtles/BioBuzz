package org.firstinspires.ftc.teamcode.opmode.autonomous.programs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.autonomous.AutonomousEx;

import java.util.Arrays;
import java.util.List;


@Autonomous(name = "ParkBot", group = "Bot")
public class ParkBot extends AutonomousEx {

    public enum MatchState {
        PARK,
        OVER
    }

    public static class Constants {
        @Config("Park Autonomous - Park Constants")
        public static class Park {
            public static double time = 0.6;
            public static double forward = -0.38;
            public static double strafe = 0.64;
            public static double twist = 0.0;
        }
    }

    public MatchState matchState;

    @Override
    public void init() {
        // run parent
        super.init();
        // assuming blue alliance
        // set matchstate
        matchState = MatchState.PARK;
    }

    @Override
    public void stateMachine() {
        switch (matchState) {
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

    public void park() {
        robot.drive.robotCentric(Constants.Park.forward, Constants.Park.strafe, Constants.Park.twist);
    }

    @Override
    public void nextState() {
        // reset timer
        timer.reset();
        // state machine
        switch (matchState) {
            case PARK:
                // update matchstate
                matchState = MatchState.OVER;
                // brk code
                break;
        }
    }
}
