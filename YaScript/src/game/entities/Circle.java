package game.entities;
import game.WorldEntity;
import math.*;

public class Circle extends WorldEntity {
  Vector center;
  double radius;

  public Circle(Vector center, double radius){
  this.center = center;
  this.radius = radius;
  }
  @Override
  public double getDist(Vector point){
    return point.sub(center).length() - radius;
  }

}
