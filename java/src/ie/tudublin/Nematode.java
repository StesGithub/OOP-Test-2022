package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Nematode
{
    NematodeVisualiser v;

    int length = 5;

    public int getLength() {
        return length;
    }


    public void setlength(int length) {
        this.length = length;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public float getW() {
        return w;
    }


    public void setW(float w) {
        this.w = w;
    }


    public int getLimbs() {
        return limbs;
    }


    public void setLimbs(int limbs) {
        this.limbs = limbs;
    }


    public boolean isEyes() {
        return eyes;
    }


    public void setEyes(boolean eyes) {
        this.eyes = eyes;
    }


    public char getGender() {
        return gender;
    }


    public void setGender(char gender) {
        this.gender = gender;
    }


    public float getR() {
        return r;
    }


    public void setR(float r) {
        this.r = r;
    }


    public float getEyeRadius() {
        return eyeRadius;
    }


    public void setEyeRadius(float eyeRadius) {
        this.eyeRadius = eyeRadius;
    }

    String name = "Test";
    float w = 50;

    int limbs = 1;
    boolean eyes = true;
    char gender = 'h';
    float r = w * 0.5f;
    float eyeRadius = w * 0.1f;

    public Nematode(NematodeVisualiser v, int length, String name, int limbs, boolean eyes, char gender) {
        this.v = v;
        this.length = length;
        this.name = name;
        this.limbs = limbs;
        this.eyes = eyes;
        this.gender = gender;
    }


    public Nematode(NematodeVisualiser v, TableRow row)
    {
        this(v, row.getInt("length"), row.getString("name"), row.getInt("limbs"), row.getInt("eyes") == 1, row.getString("gender").charAt(0));

    }

    
    public Nematode(NematodeVisualiser v)
    {
        this.v = v;
    }

    public void render(float cx, float cy)
    {
        //this calculates half the length of our nematode 
        float half = w * length * 0.5f; 
        v.pushMatrix();
        v.translate(cx, cy);
        v.translate(0, - half);
        v.noFill(); //sets fill colour to none 
        float hw = w / 2; //half width of our nematode
        //this is just the code to display its name 
        v.textSize(36);
        v.textAlign(PApplet.CENTER, PApplet.CENTER);
        v.text(name, 0, -w * 2);
        


        for(int i = 0 ; i < length ; i ++)
        {
            float y = i * w;
            v.ellipse(y, 0, w, w);       //elippse takes x and y cooridantes then size values for height and width

            if (limbs > 0)
            {
                v.line(y, -hw, y, - hw - hw); //this draws limbs out of our nematode using the hw - half width of nematode 
                v.line(y,hw, y, hw * 2);
            }
            if (eyes)
            {
                drawEye(-45);
                drawEye(45);
            }
        }

        drawGenitals();

        v.popMatrix();
    }

    private void drawGenitals()
    {
        switch (gender)
        {
            case 'm':
                {
                  float y1 =  (length * w) - r;
                  v.line(0, y1, 0,  y1 + r);
                  v.circle(0, y1 + r + eyeRadius, eyeRadius * 2.0f);
                }
                break;
            case 'f':
                {
                    float y =  ((length - 1) * w);
                    v.circle(0, y, eyeRadius * 4.0f);                      
                }
                break;
            case 'h':
                {
                    float y1 =  (length * w) - r;
                    v.line(0, y1, 0,  y1 + r);
                    v.circle(0, y1 + r + eyeRadius, eyeRadius * 2.0f);
  
                    float y =  ((length - 1) * w);
                    v.circle(0, y, eyeRadius * 4.0f);                      
                }
                break;
        }
    }

    private void drawEye(float angle)
    {
        float x1 = PApplet.sin(PApplet.radians(angle)) * (r); //calculates where the eye should be placed along the x axis judging by the radius of our nematode 
        float y1 = - PApplet.cos(PApplet.radians(angle)) * (r);
        
        float x2 = PApplet.sin(PApplet.radians(angle)) * (r + r); // caklcualtes eye two of our nematode but using double the radius to create eyelids twice the size of our eyeball
        float y2 = - PApplet.cos(PApplet.radians(angle)) * (r + r);
        float ex = PApplet.sin(PApplet.radians(angle)) * (r + r + eyeRadius); //this is to calculate the center point of our eye 
        float ey = - PApplet.cos(PApplet.radians(angle)) * (r + r + eyeRadius);
        v.circle(ex, ey, eyeRadius * 2.0f);
        v.line(x1, y1, x2, y2);

    }
}

