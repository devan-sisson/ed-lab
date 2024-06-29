import 'package:flutter/material.dart';

class GradientContainer extends StatelessWidget {
  const GradientContainer({super.key, required this.colors, this.child});

  final List<Color> colors;
  final Widget? child;

  @override
  Widget build(context) {
    return Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
              colors: colors, 
              begin: Alignment.bottomLeft,
              end: Alignment.topRight),
        ),
        child: child);
  }
}
