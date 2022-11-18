package exhiber.content;

import mindustry.world.meta.Attribute;

public class EXAttributes {
    public static Attribute
        watermill,tunnel
            ;
    public static void load(){
        watermill = Attribute.add("watermill");
        tunnel = Attribute.add("tunnel");
    }
}
