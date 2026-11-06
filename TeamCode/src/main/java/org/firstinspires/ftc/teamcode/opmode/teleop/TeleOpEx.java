package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.OpModeEx;
import org.firstinspires.ftc.teamcode.utility.handling.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeleOpEx extends OpModeEx {
    @Override
    public Pose2D setStart() throws IOException {
        // get from file
        return Handler.readPosition();
    }
}
