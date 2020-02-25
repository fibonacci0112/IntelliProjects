package sample;

import javafx.scene.shape.Ellipse;

class MovingEllipse
{
    private double stepX; //
    private double stepY;
    private Ellipse c; //reference on a circle

    public void setGravity(double gravity)
    {
        this.gravity = gravity;
    }

    public double getGravity()
    {
        return gravity;
    }

    private double gravity = 0.9;

    MovingEllipse(Ellipse c, double dx, double dy)
    {
        this.c = c;
        stepX = dx;
        stepY = dy;
    }

    public double getStepX()
    {
        return stepX;
    }

    public void setStepX(double stepX)
    {
        this.stepX = stepX;
    }

    public double getStepY()
    {
        return stepY;
    }

    public void setStepY(double stepY)
    {
        this.stepY = stepY;
    }

    public Ellipse getEllipse()
    {
        return c;
    }

    public void setEllipse(Ellipse c)
    {
        this.c = c;
    }

    double getDistance(Ellipse e)
    {
        double x = c.getCenterX() - e.getCenterX();
        double y = c.getCenterY() - e.getCenterY();
        return Math.sqrt(x * x + y * y);
    }

    double getDistance(double coordX, double coordY)
    {
        double x = c.getCenterX() - coordX;
        double y = c.getCenterY() - coordY;
        return Math.sqrt(x * x + y * y);
    }
}

