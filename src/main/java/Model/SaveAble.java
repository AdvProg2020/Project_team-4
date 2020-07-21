package Model;

import java.io.Serializable;

public class SaveAble implements Serializable {

    String nameinsave;

    protected String getName() {
        return nameinsave;
    }

    public SaveAble() { }

    SaveAndLoad saveAndLoad = SaveAndLoad.getSaveAndLoad();
    //public abstract String getPassWord();

}
