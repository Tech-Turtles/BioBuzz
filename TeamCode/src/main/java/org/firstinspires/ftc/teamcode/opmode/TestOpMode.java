package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Test OpMode")
public class TestOpMode extends OpModeEx {

    @Override
    public void init() {
        super.init();
        // Let users know as soon as the robot is initialized!
        telemetry.addLine("Initialized!");
        telemetry.update();
    }

    @Override
    public void start() {
        super.start();
        // Let users know as soon as the robot starts!
        telemetry.addLine("Started!");
        telemetry.update();
        // Make your intentions clear to the drive coach
        telemetry.addLine("Are you ready to rumble?");
        telemetry.update();
        // Now rumble the gamepads
        List<Gamepad> gamepads = new ArrayList<>();
        gamepads.add(input.gamepad1);
        gamepads.add(input.gamepad2);
        input.rumble(gamepads, 1000);
    }
}
