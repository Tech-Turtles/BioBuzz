package org.firstinspires.ftc.teamcode.opmode.autonomous;

import android.os.Build;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;
import org.firstinspires.ftc.teamcode.utility.handling.Handler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class
AutonomousEx extends OpModeEx {



    // def timer
    public ElapsedTime timer;

    @Override
    public void init() {
        // run parent logic
        super.init();
        // def timer
        timer = new ElapsedTime();
    }

    @Override
    public void init_loop() {
        // run parent logic
        super.init_loop();

    }

    @Override
    public void start() {
        // parent start
        super.start();
        // start the timer
        timer.reset();
    }
    @Override
    public void loop() {
        super.loop();
        // state machine
        stateMachine();
    }

    @Override
    public void stop() {
        super.stop();
        // Stop motors
        robot.stopAll();

        // write position to file
        try {
            Handler.writePosition(robot.position);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stateMachine() {
        // pass for now
    }

    public void nextState() {
        // pass for now
    }
}
