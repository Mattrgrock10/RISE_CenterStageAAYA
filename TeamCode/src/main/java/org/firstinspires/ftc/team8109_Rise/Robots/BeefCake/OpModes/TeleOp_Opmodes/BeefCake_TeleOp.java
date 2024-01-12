package org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.OpModes.TeleOp_Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms.BeefWrist;
import org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms.BeefySlides;
import org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms.Bicep;
import org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms.Chassis;
import org.firstinspires.ftc.team8109_Rise.Robots.BeefCake.Mechanisms.SausageFingers;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.ViperSlides;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Wrist;

@TeleOp
public class BeefCake_TeleOp extends LinearOpMode {
    Chassis chassis;
    BeefySlides beefySlides;
    SausageFingers sausageFingers;
    BeefWrist wrist;
    Bicep arm;
    @Override
    public void runOpMode() throws InterruptedException {
        chassis = new Chassis(gamepad1, telemetry, hardwareMap);
        beefySlides = new BeefySlides(gamepad1, telemetry, hardwareMap);
        sausageFingers = new SausageFingers(gamepad1, telemetry, hardwareMap);
        wrist = new BeefWrist(gamepad1, hardwareMap);
        arm = new Bicep(gamepad1, telemetry, hardwareMap);

        while (opModeInInit()){
            beefySlides.setSlidePower();
            sausageFingers.setClawOpen();
            arm.bicepStates();
            wrist.setPosition();
            telemetry.addLine("Waiting For Start");
            telemetry.update();
        }

        while (opModeIsActive()){
            chassis.ManualDrive();
            beefySlides.toggleStates();
            beefySlides.slidesTelemetry();

            if (beefySlides.slidesState == BeefySlides.SlidesState.HOME){
//                arm.servoPosition = ServoIntakeArm.ServoPosition.INTAKE_POSITION;
                wrist.wristPosition = BeefWrist.WristPosition.INTAKE_POSITION;
            } else {
//                arm.servoPosition = ServoIntakeArm.ServoPosition.OUTTAKE_POSITION;
                wrist.wristPosition = BeefWrist.WristPosition.OUTTAKE_POSITION;
            }

            arm.slidesToggle(beefySlides.slidesState);

            sausageFingers.toggleClaw();
            wrist.setPosition();

            chassis.chassisTelemetry();

            chassis.update();

            telemetry.addData("Pose Estimate", chassis.getPoseEstimate());
            telemetry.addData("Getting Chassis Pose", chassis.getPoseVector());

            telemetry.update();
        }
    }
}