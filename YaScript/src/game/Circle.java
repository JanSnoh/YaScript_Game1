package game;
import math.*;

class Circle extends WorldEntity{
  Vector center;
  double radius;

  public Circle(Vector center, double radius){
  this.center = center;
  this.radius = radius;
  }
  @Override
  public double getDist(Vector point){
    return point.sub(center).lenght() - radius;
  }

}
