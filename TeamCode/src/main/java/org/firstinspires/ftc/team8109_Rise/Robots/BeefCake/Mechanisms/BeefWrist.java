package org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8109_Rise.Hardware.Arms.ArmExtensions.ArmWrist;

public class BeefWrist extends ArmWrist {

    Gamepad gamepad1;

    public enum WristPosition{
        INTAKE_POSITION,
        MOVEMENT_POSITION,
        OUTTAKE_POSITION
    }

    public WristPosition wristPosition;

    boolean toggle1 = true;
    boolean toggle2 = false;

    boolean lastToggleX = false;

    public BeefWrist(Gamepad gamepad1, HardwareMap hardwareMap) {
        super("wrist", hardwareMap);

        this.gamepad1 = gamepad1;
        wristPosition = WristPosition.INTAKE_POSITION;
    }

    public void setPosition(){
        switch (wristPosition){
            case INTAKE_POSITION:
                setAngle(0);
                break;
            case MOVEMENT_POSITION:
                setAngle(50);
                break;

            case OUTTAKE_POSITION:
                setAngle(49);
                break;
        }
    }
}
