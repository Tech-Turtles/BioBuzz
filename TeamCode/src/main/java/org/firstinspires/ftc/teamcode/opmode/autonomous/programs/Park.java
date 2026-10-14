package org.firstinspires.ftc.teamcode.opmode.autonomous.programs;

import android.service.autofill.FieldClassification;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.autonomous.AutonomousEx;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Autonomous(name = "Park", group = "Bot")
public class Park extends AutonomousEx {

    public enum MatchState {
        STRAFE,
        OVER
    }

    public static class Constants {
        public static List<Double> vector = Arrays.asList(0.0, 1.0, 0.0);
    }

    public MatchState matchState;

    @Override
    public void init() {
        // run parent
        super.init();
        // assuming blue alliance
        // set matchstate
        matchState = MatchState.STRAFE;
    }

    @Override
    public void stateMachine() {
        switch (matchState) {
            case STRAFE:
                if (timer.seconds() <= 0.5) {
                    robot.drive.robotCentric(Constants.vector.get(0), Constants.vector.get(1), Constants.vector.get(0));
                } else {
                    timer.reset();
                    matchState = MatchState.OVER;
                }

                break;

            case OVER:
                stop();
                break;
        }
    }
}
