package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.List;

public class Input {

    // Declare gamepads
    public Gamepad gamepad1;
    public Gamepad gamepad2;

    // define speed modifier
    public double speedModifier = 1;

    public Input(Gamepad gamepad1, Gamepad gamepad2) {
        // Assign gamepads
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    public List<Double> getDrive(Gamepad drivepad) {
        if (drivepad.right_trigger_pressed) {
            speedModifier = 0.25;
        } else {
            speedModifier = 1;
        }
        // Get drive values from the gamepad decidedly used for driving
        double forward = drivepad.left_stick_y * speedModifier;
        double strafe = drivepad.left_stick_x * speedModifier;
        double twist = -drivepad.right_stick_x * speedModifier;
        // Create list to store these drive values
        List<Double> drive = new ArrayList<>();
        drive.add(forward);
        drive.add(strafe);
        drive.add(twist);
        // Give back the list of drive values
        return (drive);
    }

    public void rumble(List<Gamepad> gamepads, double duration) {
        // Loop to go through for each gamepad and rumble it
        for (Gamepad gamepad : gamepads) {
            gamepad.rumble((int) duration);
        }
    }
}
