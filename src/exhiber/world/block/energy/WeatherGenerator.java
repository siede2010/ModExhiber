package exhiber.world.block.energy;

import arc.math.Mathf;
import mindustry.type.Weather;
import mindustry.world.blocks.power.ConsumeGenerator;

public class WeatherGenerator extends ConsumeGenerator {
    public Weather weather1;
    public WeatherGenerator(String name, Weather weather){
        super(name);
        weather1 = weather;
    }
    public class WeatherGeneratorBuild extends ConsumeGeneratorBuild {
        @Override
        public void updateTile() {
            boolean valid = weather1.isActive();

            warmup = Mathf.lerpDelta(warmup, valid ? 1f : 0f, warmupSpeed);
            productionEfficiency = warmup;
            generateTime = 1f;
        }
    }
}
