package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

public class TeleOpEx extends OpModeEx {
    // setup match timer
    ElapsedTime timer;

    @Override
    public void init() {
        // Run parent init logic
        super.init();
        // Initialize match timer
        timer = new ElapsedTime();
    }
}
