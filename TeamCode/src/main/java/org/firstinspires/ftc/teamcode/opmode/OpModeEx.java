package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.Hardware;

public class OpModeEx extends OpMode {

    // OPMODE DECLARATIONS
    // Declare hardware
    public Hardware hardware;

    // Declare pinpointReadout
    public Pose2D pinpointReadout;

    @Override
    public void init() {
        // Define hardware
        hardware = new Hardware(hardwareMap);
    }

    @Override
    public void init_loop() {
        // pass
    }

    @Override
    public void start() {
        // pass
    }

    @Override
    public void loop() {
        // Update pinpoint
        pinpointReadout = hardware.updatePinpoint();
    }
}
