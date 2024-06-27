import 'package:flutter/material.dart';
import 'package:flutter_application_1/styled_text.dart';

class GradientContainer extends StatelessWidget {
  const GradientContainer({super.key});

  @override
  Widget build(context) {
    return Container(
      decoration: const BoxDecoration(
        gradient: LinearGradient(
            colors: [Colors.blueGrey, Colors.deepPurple, Colors.pink],
            begin: Alignment.bottomLeft,
            end: Alignment.topRight),
      ),
      child: const Center(
        child: StyledText(),
      ),
    );
  }
}
