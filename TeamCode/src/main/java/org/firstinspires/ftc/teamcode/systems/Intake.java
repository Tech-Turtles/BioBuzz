package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.robot.Hardware;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class Intake {
    // define the intake motor
    public DcMotorEx intakeMotor;

    @Config("Intake Speeds")
    public static class IntakeConsts {
        // speed mod
        public static double speedModifer = 0.5;
    }

    public Intake(Robot robot) {
        // define intake motor
        intakeMotor = robot.hardware.intakeMotor;
    }

    /**
     * Runs the intake in
     * @author Brantley
     * @param speed the speed at which the intake intakes. 0-1.
     */

    public void intake(double speed) {
        intakeMotor.setPower(Math.abs(speed * IntakeConsts.speedModifer));
    }

    /**
     * Runs the intake out
     * @author Brantley
     * @param speed the speed at which the intake outtakes. 0-1.
     */

    public void outtake(double speed) {
        intakeMotor.setPower(-Math.abs(speed * IntakeConsts.speedModifer));
    }

    /**
     * Stops the intake motor. Note it does not brake.
     * @author Brantley
     */

    public void stop() {
        intakeMotor.setPower(0);
    }
}
