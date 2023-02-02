package exhiber.content;

import exhiber.world.Icon;

public class EXIcons {
    public static Icon
        one,two,three,four,five
            ;
    public static void load()
    {
        one = new Icon("one"){{value = 1;localizedName="One";}};
        two = new Icon("two"){{value = 2;localizedName="Two";}};
        three = new Icon("three"){{value = 3;localizedName="Three";}};
        four = new Icon("four"){{value = 4;localizedName="Four";}};
        five = new Icon("five"){{value = 5;localizedName="Five";}};
    }
}
