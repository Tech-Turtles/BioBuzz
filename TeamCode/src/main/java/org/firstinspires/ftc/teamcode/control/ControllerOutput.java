package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControllerOutput {

    // Declare gamepads
    public Gamepad gamepad1;
    public Gamepad gamepad2;

    // constructor

    /**
     * @author Brantley
     * @param gamepad1 the first gamepad for output
     * @param gamepad2 the second gamepad for output
     */

    public ControllerOutput(Gamepad gamepad1, Gamepad gamepad2) {
        // Assign gamepads
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    // Rumble logic

    /**
     * @author Brantley
     * @param rumblepad the gamepad that will be rumbled
     * @param duration the time in ms that said gamepad will be rumbled
     */

    public void rumble(Gamepad rumblepad, double duration) {
        // rumble said gamepad
        rumblepad.rumble((int) duration);
    }

    /**
     * @author Brantley
     * @param duration the duration to rumble both gamepads, in ms
     */

    public void rumbleAll(double duration) {
        // rumble gamepads
        rumble(gamepad1, duration);
        rumble(gamepad2, duration);
    }
}
