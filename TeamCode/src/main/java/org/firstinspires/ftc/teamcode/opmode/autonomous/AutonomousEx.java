package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

public class AutonomousEx extends OpModeEx {
    // create timer object
    public ElapsedTime timer;
    // create object for time of autonomous match
    public double autonomousLength = 30;

    @Override
    public void init() {
        super.init();
        // create timer
        timer = new ElapsedTime();
    }

    @Override
    public void start() {
        timer.reset();
    }
}
