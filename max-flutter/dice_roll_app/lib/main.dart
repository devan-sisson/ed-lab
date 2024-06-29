import "package:flutter/material.dart";
import "package:dice_roll_app/dice_roller.dart";
import "package:dice_roll_app/gradient_container.dart";

void main() {
  runApp(
    const MaterialApp(
      home: Scaffold(
        body: GradientContainer(
          colors: [
            Colors.deepPurple,
            Colors.pink,
            Color.fromARGB(255, 64, 11, 157),
          ],
          child: Center(
            child: DiceRoller()
          ),
        ),
      ),
    ),
  );
}
