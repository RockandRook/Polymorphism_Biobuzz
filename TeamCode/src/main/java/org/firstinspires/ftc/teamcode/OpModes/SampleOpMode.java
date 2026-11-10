package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class SampleOpMode extends LinearOpMode {
    DcMotor FLW,FRW,BLW,BRW;
    Servo servo;
    boolean servoPos;
    @Override
    public void runOpMode(){

        FLW = hardwareMap.get(DcMotor.class,"FLW");
        FRW = hardwareMap.get(DcMotor.class,"FRW");
        BLW = hardwareMap.get(DcMotor.class,"BLW");
        BRW = hardwareMap.get(DcMotor.class,"BRW");
        FLW.setDirection(DcMotorSimple.Direction.FORWARD);
        FRW.setDirection(DcMotorSimple.Direction.REVERSE);
        BLW.setDirection(DcMotorSimple.Direction.FORWARD);
        BRW.setDirection(DcMotorSimple.Direction.REVERSE);
        servo = hardwareMap.get(Servo.class,"servo");
        servoPos = false;

        waitForStart();

        while(opModeIsActive()){
            if(Math.abs(gamepad1.left_stick_y) > 0.1){
                FLW.setPower(gamepad1.left_stick_y);
                FRW.setPower(gamepad1.left_stick_y);
                BLW.setPower(gamepad1.left_stick_y);
                BRW.setPower(gamepad1.left_stick_y);
            }else if(Math.abs(gamepad1.left_stick_x) > 0.1) {
                FLW.setPower(gamepad1.left_stick_x);
                FRW.setPower(-gamepad1.left_stick_x);
                BLW.setPower(-gamepad1.left_stick_x);
                BRW.setPower(gamepad1.left_stick_x);
            }else if(Math.abs(gamepad1.right_stick_x) > 0.1){
                FLW.setPower(gamepad1.right_stick_x);
                FRW.setPower(-gamepad1.right_stick_x);
                BLW.setPower(gamepad1.right_stick_x);
                BRW.setPower(-gamepad1.right_stick_x);
            }

            if(gamepad1.triangleWasPressed()){
                if(servoPos) {
                    servo.setPosition(0.1);
                }else{
                    servo.setPosition(0.9);
                }
                servoPos = !servoPos;
            }
        }
    }
}
