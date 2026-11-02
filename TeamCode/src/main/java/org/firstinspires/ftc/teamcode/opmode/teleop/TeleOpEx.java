package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

import java.util.ArrayList;
import java.util.List;

public class TeleOpEx extends OpModeEx {

    ElapsedTime timer;

    @Override
    public void init() {
        // Run parent init logic
        super.init();
        // init timer
        timer = new ElapsedTime();
    }

    @Override
    public void start() {
        // run parent code
        super.start();
        // Reset timer
        timer.reset();
    }
}
