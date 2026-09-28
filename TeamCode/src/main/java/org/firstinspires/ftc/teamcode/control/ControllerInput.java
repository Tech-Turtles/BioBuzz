package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.List;

public class ControllerInput {

    // Declare gamepads
    public Gamepad gamepad1;
    public Gamepad gamepad2;

    /**
     * @author Brantley
     * @param gamepad1 the first gamepad for input
     * @param gamepad2 the second gamepad for input
     */
    public ControllerInput(Gamepad gamepad1, Gamepad gamepad2) {
        // Assign gamepads
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    // input getters

    // drive input

    /**
     * @author Brantley
     * @param drivepad the gamepad involved with collecting this driving info
     * @return a list with three values: forward, strafe, and twist in that order. Use forward = getDrive().get(0)...
     */

    public List<Double> getDrive(Gamepad drivepad) {
        // Get drive values from the gamepad decidedly used for driving
        double forward = drivepad.left_stick_y;
        double strafe = drivepad.left_stick_x;
        double twist = -drivepad.right_stick_x;

        // Create list to store these drive values
        List<Double> drive = new ArrayList<>();
        drive.add(forward);
        drive.add(strafe);
        drive.add(twist);
        // Give back the list of drive values
        return (drive);
    }

    // intake input

    /**
     * @param intakepad is the gamepad that provides intake control data here
     * @return the y of the left stick on the intakepad. Up means take in, down means outtake.
     * @author Brantley
     */

    public double getIntake(Gamepad intakepad) {
        return (intakepad.left_stick_y);
    }

    /**
     * @param intakepad the gamepad that handles idle intaking
     * @return the absolute value of the left stick y. Useful for keeping balls in and taking them in efficiently
     * @author Brantley
     */

    public double getIntakeIdle(Gamepad intakepad) {
        return ((double) Math.abs(intakepad.left_stick_y));
    }

    // game state input
    public boolean getEndgame() {
        // establish booleans
        boolean endgameForGamepad1 = gamepad1.left_stick_button && gamepad1.right_stick_button;
        boolean endgameForGamepad2 = gamepad2.left_stick_button && gamepad2.right_stick_button;
        // now return final test
        return (endgameForGamepad1 || endgameForGamepad2);
    }

    // pinpoint input
    public boolean getResetPinpoint(Gamepad resetpad) {
        // return the condition
        return (resetpad.dpadUpWasPressed());
    }
}
