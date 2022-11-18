package exhiber.world.block.crafter;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.Vars;
import mindustry.type.Weather;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;

public class WeatherCrafter extends GenericCrafter {

    public Weather Bweather;
    public WeatherCrafter(String name, Weather weather){
        super(name);
        Bweather = weather;
        update = true;
    }

    public class WeatherCrafterBuild extends GenericCrafterBuild{
            @Override
            public boolean shouldConsume (){
                return Bweather.isActive() && itemCapacity > items.total();
            }

            @Override
            public void updateTile(){
                if (shouldConsume()){
                    progress += getProgressIncrease(craftTime);
                    warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);
                } else {
                    warmup = Mathf.approachDelta(warmup, 0, warmupSpeed);
                }
                totalProgress += warmup * Time.delta;
                if(progress >= 1f){
                    craft();
                }
                dumpOutputs();
            }

    }
}
