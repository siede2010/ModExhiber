package exhiber.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class ExEffects {
    public static Effect
    collectRain,brassCraft,heatEffect,superfluxeffect,chalkAoe
    ;
    public static void load() {
        collectRain = new Effect(12,e -> {
            color(Pal.lancerLaser);
            stroke(2f * e.fout());
            Lines.circle(e.x, e.y, 5f * e.fout());
        });
        brassCraft = new Effect(14,e -> {
            color(Color.white, Pal.heal, e.fin());
            stroke(0.5f + e.fout());

            randLenVectors(e.id, 8, 1f + e.fin() * 15f, e.rotation, 50f, (x, y) -> {
                lineAngle(e.x + Mathf.random(16)-8, e.y + Mathf.random(16)-8, Mathf.random(360), e.fout() * 3 + 1f);
            });
        });
        heatEffect = new Effect(32,e -> {
            color(Pal.missileYellow);
            Fill.circle(e.x,e.y,0.8f);
            color(Pal.turretHeat);
            for(int i = 19;i>=0;i--)
                Fill.circle(e.x + 1.1f*Mathf.sin(((i+e.fin())/19f)*360f),e.y + 1.1f*Mathf.cos(((i+e.fin())/19f)*360f),i*0.02f+0.1f);
        });
        superfluxeffect = new Effect(64,e -> {
            color(Pal.powerLight);
            Lines.stroke(1f);
            float ef = Mathf.sin(e.fin()*6.4f);
            for(int i = 2;i>0;i--)
                Lines.line(e.x+ef*8,e.y+ef*8,i % 2 == 0 ? e.x+ef*2 : e.x-ef*2,i % 2 == 0 ? e.y-ef*2 : e.y+ef*2);
            for(int i = 2;i>0;i--)
                Lines.line(e.x-ef*8,e.y+ef*8,i % 2 == 0 ? e.x+ef*2 : e.x-ef*2,i % 2 == 0 ? e.y+ef*2 : e.y-ef*2);
            for(int i = 2;i>0;i--)
                Lines.line(e.x+ef*8,e.y-ef*8,i % 2 == 0 ? e.x-ef*2 : e.x+ef*2,i % 2 == 0 ? e.y-ef*2 : e.y+ef*2);
            for(int i = 2;i>0;i--)
                Lines.line(e.x-ef*8,e.y-ef*8,i % 2 == 0 ? e.x+ef*2 : e.x-ef*2,i % 2 == 0 ? e.y-ef*2 : e.y+ef*2);
        });
        chalkAoe = new Effect(64,e -> {
            color(Color.white);
            Lines.stroke(e.fout());
            Lines.circle(e.x,e.y,e.fin()*8);
        });
    }
}
