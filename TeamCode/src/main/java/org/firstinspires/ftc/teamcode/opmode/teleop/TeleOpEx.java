package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

import java.util.ArrayList;
import java.util.List;

public class TeleOpEx extends OpModeEx {
    // setup match timer
    public ElapsedTime timer;

    // setup match state enum
    public enum MatchState {
        TELEOP,
        ENDGAME,
        OVER
    }

    public MatchState matchState;

    protected double teleopTime = 60;
    protected double endgameTime = 60;

    protected double time = 0;

    @Override
    public void init() {
        // Run parent init logic
        super.init();
        // Initialize match timer
        timer = new ElapsedTime();
        // speak to user
        telemetry.addLine("Initialized!");
        telemetry.update();
    }

    @Override
    public void start() {
        super.start();
        // Now that match is starting, reset the timer
        timer.reset();
        // We are in the teleop section of the match
        matchState = MatchState.TELEOP;
        // speak to user
        telemetry.addLine("Started!");
        telemetry.update();
        // Run the teleop init code
        teleop();
    }

    @Override
    public void loop() {
        super.loop();
        // Big state machine
        switch (matchState) {
            case TELEOP:
                // Make the timer a bit handier
                time = timer.seconds();
                // Run the logic for the teleop
                teleop_loop();
                // check if we get to go to endgame yet
                if (time >= teleopTime) {
                    matchState = MatchState.ENDGAME;
                    endgame();
                }
                // we don't want to start an infinite loop:
                break;

            case ENDGAME:
                // Set up time
                time = timer.seconds();
                // Run the endgame loop
                endgame_loop();
                // now update the state machine
                if (time >= (teleopTime + endgameTime)) {
                    matchState = MatchState.OVER;
                    over();
                }
                // this is very important
                break;

            case OVER:
                // We don't need to worry about time here
                // I really have no idea what someone would
                // put in a game over loop, but this is here
                // if anyone wants. Versatility is key.
                over_loop();
                // brake robot just in case
                robot.drive.brake();
                // now break code
                break;
        }
        // Just in case robot program needs to be restarted during match, immediately set to endgame
        boolean endgameForGamepad1 = (input.gamepad1.left_stick_button && input.gamepad2.right_stick_button);
        boolean endgameForGamepad2 = (input.gamepad2.left_stick_button && input.gamepad2.right_stick_button);
        // If either or, the state is endgame
        if (endgameForGamepad1 || endgameForGamepad2) {
            matchState = MatchState.ENDGAME;
            // call endgame setup method
            endgame();
        }

        if (input.gamepad1.left_bumper) {
            robot.odometry.pinpoint.resetPosAndIMU();
        }
    }

    // The below methods are meant to be overridden by
    // the children of this class so that they don't
    // have to mess with stuff like loop()

    public void teleop() {
        // currently, do nothing
    }

    public void teleop_loop() {
        // currently, do nothing
    }

    public void endgame() {
        // we can rumble the controllers here
        List<Gamepad> gamepads = new ArrayList<>();
        gamepads.add(input.gamepad1);
        gamepads.add(input.gamepad2);
        input.rumble(gamepads, 1000);
    }

    public void endgame_loop() {
        // currently, do nothing
    }

    public void over() {
        // do even more nothingness
    }

    public void over_loop() {
        // you guessed it: do nothing
    }
}
