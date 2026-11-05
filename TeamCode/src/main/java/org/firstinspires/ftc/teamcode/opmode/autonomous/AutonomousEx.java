package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

public class
AutonomousEx extends OpModeEx {



    // def timer
    public ElapsedTime timer;
    public ElapsedTime matchTimer;

    // def enum for alliance
    public enum Alliance {
        BLUE,
        RED
    }
    // def var to store alliance
    public Alliance alliance;

    @Override
    public void init() {
        // run parent logic
        super.init();
        // def timer
        timer = new ElapsedTime();
        matchTimer = new ElapsedTime();
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
        // start the match timer
        matchTimer.reset();
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
    }

    public void stateMachine() {
        // pass for now
    }

    public void nextState() {
        // pass for now
    }
}
